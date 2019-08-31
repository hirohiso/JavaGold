package exam.gold.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
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

        {
            StringBuilder sb = Stream.of("a", "b", "cd", "def", "ghijk", "lmnopqrs").collect(StringBuilder::new,
                    (r, a) -> r.append(a), (r, a) -> r.append(a));
            System.out.println(sb.toString());
        }
        {
            String s = Stream.of("a", "b", "cd", "def", "ghijk", "lmnopqrs").collect(Collectors.joining(":"));
            System.out.println(s);
        }
        {
            Map<Object, String> map2 = Stream.of("a", "b", "cd", "def", "ghijk", "lmnopqrs")
                    .collect(Collectors.groupingBy((s) -> s.length(), TreeMap::new,
                            Collectors.mapping(s -> s.length() + s, Collectors.joining(":"))));
            System.out.println(map2.toString());
        }
        {
            Map<Boolean, List<String>> map2 = Stream.of("a", "b", "cd", "def", "ghijk", "lmnopqrs")
                    .collect(Collectors.partitioningBy(s -> s.length() % 2 == 0));
            System.out.println(map2.toString());
        }
        {
            Supplier<StringBuilder> supplier = StringBuilder::new;
            BiConsumer<StringBuilder, String> accumulator = (sb, s) -> sb.append(s.toUpperCase()).append(":");
            BinaryOperator<StringBuilder> combiner = (sb1, sb2) -> sb1.append(sb2);
            Function<StringBuilder, String> finisher = sb -> sb.toString();

            Collector<String, StringBuilder, String> c = Collector.of(supplier, accumulator, combiner, finisher);

            String s = Stream.of("a", "b", "cd", "def", "ghijk", "lmnopqrs").collect(c);
            System.out.println(s);
        }
        {
            List<String> list = Arrays.asList("aadfgf", "bfeg", "z", "b", "acd", "q", "rth", "ghi", "lmno");
            Collections.sort(list, Comparator.naturalOrder());
            System.out.println(list.toString());
            Collections.sort(list, Comparator.reverseOrder());
            System.out.println(list.toString());

            Comparator<String> key1 = Comparator.comparingInt(s -> s.length());
            Comparator<String> key2 = Comparator.naturalOrder();
            Comparator<String> c = key1.thenComparing(key2);
            /* 一度に合成することができない
            Comparator<String> c2 =  Comparator.comparingInt(s -> s.length())
                    .thenComparing(Comparator.naturalOrder());
                    */
            //とおもってたけど型を明示したらいけた
            Comparator<String> c2 = (Comparator<String>) (Comparator.comparingInt((String s) -> s.length()))
                    .thenComparing(Comparator.naturalOrder()).reversed();

            Collections.sort(list, c);
            System.out.println(list.toString());
            Collections.sort(list, c2);
            System.out.println(list.toString());
        }
    }

}
