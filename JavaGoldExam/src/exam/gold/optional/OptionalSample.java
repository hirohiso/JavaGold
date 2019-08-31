package exam.gold.optional;

import java.util.Optional;

public class OptionalSample {

    public static void main(String[] args) {
        {
            //例外が発生する
            //Optional<Integer> opt1 = Optional.of(null);
            Optional<Integer> opt2 = Optional.of(-1234);
        }
        {

            Optional<Integer> opt1 = Optional.ofNullable(null);
            Optional<Integer> opt2 = Optional.ofNullable(-1234);
            Optional<Integer> opt3 = Optional.empty();

            System.out.format("null:%s,Object:%s,empty:%s\n", opt1,opt2,opt3);

            System.out.format("null:%s,Object:%s,empty:%s\n", opt1.isPresent(),opt2.isPresent(),opt3.isPresent());
            System.out.format("null:%s,Object:%s,empty:%s\n", opt1.orElse(0),opt2.orElse(0),opt3.orElse(0));
            System.out.format("null:%s,Object:%s,empty:%s\n", opt1.map(i->i%7),opt2.map(i->i%7),opt3.map(i->i%7));

        }
    }

}
