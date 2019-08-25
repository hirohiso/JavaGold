package exam.gold.lamda;

import java.time.LocalDate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.UnaryOperator;

public class LamdaSample {

    public static void main(String[] args) {
        Lamda1 lam = ()->{};
        System.out.println(Lamda1.getName());
        System.out.println(lam.getClass().getName());
        System.out.println(lam.getToday());

        /**
         * 汎用型関数
         */
        {
            Function<String, Integer> func1 = s ->s.length();
            UnaryOperator<String> func2 = s -> s + "!!!!";
            Consumer<String> con1 = s ->{};
            System.out.println(func1.apply("Hello World!"));
            System.out.println(func2.apply("Hello World!"));
            con1.accept("222222");
            // i -> System.out.println(i);と同義
            IntConsumer con2 = System.out::println;
            con2.accept(12345);

            IntBinaryOperator ibo = Math::max;

            //２つの引数のうち最大値を選択して、出力する
            con2.accept(ibo.applyAsInt(13, 45));
        }

    }

    //こいつをつけてあげれば関数型IFかコンパイラが判断してくれる
    @FunctionalInterface
    public interface Lamda1 {
        //SAM要件
        //たった一つの抽象メソッド
        void execute();
        //staticがあってもよい
        static String getName(){
            return "test";
        };

        //defaultがあってもよい
        default LocalDate getToday(){
            return LocalDate.now();
        }
    }

}
