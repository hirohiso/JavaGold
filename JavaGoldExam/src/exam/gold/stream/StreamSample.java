package exam.gold.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamSample {

    public static void main(String[] args) {
        boolean result;

        result = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream().allMatch((i) -> i < 11);
        System.out.println("Result:" + result);

        result = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).stream().allMatch((i) -> i < 10);
        System.out.println("Result:" + result);

        int[] ia = IntStream.iterate(1, (i) -> ++i).limit(20).filter((i) -> i % 3 != 0).toArray();
        System.out.println("Result:" + Arrays.toString(ia));



        List<String> strList = Arrays.asList("abc", "de", "1", "fghi", "jkl", "z", "qryuoy");
        Stream<String> stremString = strList.stream();
        String result3 = stremString.filter(s -> s.length() >= 2)
                .map(s -> s.length() + ":" + s)
                .collect(Collectors.joining(","));
        System.out.println(result3);

        Map<Integer, String> map = new TreeMap<>();
        map.put(1, "Alc");
        map.put(2, "Buffer");
        map.put(3, "Caloly");
        map.forEach((k, v) -> System.out.println(k + ":" + v));

        Stream.of("a", "b", "c", "d", "e", "f");

    }

}
