package exam.gold.stream;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StreamSample {

    public static void main(String[] args) {
        boolean result;

        result =  Arrays.asList(1,2,3,4,5,6,7,8,9,10).stream().allMatch((i)->i < 11);
        System.out.println("Result:"+result);

        result =  Arrays.asList(1,2,3,4,5,6,7,8,9,10).stream().allMatch((i)->i < 10);
        System.out.println("Result:"+result);

        int[] ia = IntStream.iterate(1, (i)->++i).limit(20).filter((i)->i%3 !=0).toArray();
        System.out.println("Result:"+ Arrays.toString(ia));

    }

}
