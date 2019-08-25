package exam.gold.classes;

import java.util.Arrays;

public class EnumSample {

    public static void main(String[] args) {
        // TODO 自動生成されたメソッド・スタブ

        {
            Enum1 e = Enum1.Apple;
            System.out.printf("%s:%s\r\n", e, e.ordinal());
        }
        {
            Enum1 e = Enum1.Banana;
            System.out.printf("%s:%s\r\n", e, e.ordinal());
        }
        {
            System.out.printf("%s\r\n", Arrays.toString(Enum1.values()));
        }

        {
            System.out.printf("%s\r\n", Enum1.Apple.getColor());
            System.out.printf("%s\r\n", Enum1.Grape.getColor());
            System.out.printf("%s\r\n", Enum1.None.getColor());
        }
        {
            Enum1 e = Enum1.valueOf("Banana");
            System.out.printf("%s:%s\r\n", e, e.ordinal());
        }
        {
            Enum1 e = Enum1.Banana;
            Enum1 e2 = Enum1.None;
            //↓使えない
            //Enum1.None.test();
            //e2.test();

        }

    }

    //インターフェイスの継承が可能
    public static enum Enum1 implements AutoCloseable {
        //抽象メソッドやら特殊な変数はこんな感じで定義する
        None {
            private int test = 0;

            @Override
            public void close() throws Exception {
                //ここからだと使える？
                this.test();
            }

            //宣言しても外から見えない
            public void test() {

            }
        },
        Apple("RED") {

            @Override
            public void close() throws Exception {
            }
        },
        Banana("Yellow") {
            @Override
            public void close() throws Exception {
            }
        },
        Grape("Blue") {
            @Override
            public void close() throws Exception {
            }
        };

        private String color = "";

        private Enum1() {

        }

        private Enum1(String color) {
            this.color = color;
        }

        public String getColor() {
            return this.color;
        }

        //抽象メソッドの定義が可能
        @Override
        public abstract void close() throws Exception;

    }

}
