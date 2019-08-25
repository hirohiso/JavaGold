package exam.gold.time;

import static java.lang.System.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;

public class TimeSample {

    public static void main(String[] args) {
        DateTimeFormatter dtf1 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        DateTimeFormatter dtf2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        DateTimeFormatter dtf3 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        DateTimeFormatter dtf4 = DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL);
        DateTimeFormatter dtf5 = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        DateTimeFormatter dtf6 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
        out.println(LocalTime.now());
        //out.println(LocalTime.now().format(dtf4));
        out.println(LocalTime.now().format(dtf5));
        //逆もできる
        out.println(dtf5.format(LocalTime.now()));
        out.println(LocalDate.now());
        out.println(LocalDate.now().format(dtf1));
        out.println(LocalDate.now().format(dtf2));
        out.println(LocalDateTime.now());
        out.println(LocalDateTime.now().format(dtf3));
        out.println(OffsetTime.now());
        out.println(OffsetDateTime.now());
        out.println("------------------");
        LocalTime lt1 = LocalTime.of(1, 59, 5, 200);
        LocalTime lt2 = LocalTime.parse("08:31:09");
        //DateTimeParseException
        //LocalTime lt2 = LocalTime.parse("8:31:9");
        out.println(lt1);
        out.println(lt2);

        {
            LocalDate ld1 = LocalDate.of(2020, 12, 6);
            out.println(ld1);
        }

        {
            LocalDate ld1 = LocalDate.now();
            LocalDate ld2 = ld1.minusDays(2200);
            out.println(ld2);
        }
        /**
         * 書式
         */
        {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年 MM月 dd日 HH時mm分ss秒");
            out.println(LocalDateTime.now().format(dtf));
            String target = "2019年 08月 24日 13時41分03秒";
            LocalDateTime ldt = LocalDateTime.from(dtf.parse(target));
            out.println(ldt);
            //こっちも同じ挙動
            LocalDateTime ldt2 = LocalDateTime.parse(target,dtf);
            out.println(ldt2);
        }

        /*
         * タイムゾーン
         */
        {

            ZoneId zi = ZoneId.systemDefault();
            ZoneId zi2 = ZoneId.of("UTC+02:00");
            ZoneId zi3 = ZoneId.of("UTC-03:00");
            ZoneId zi4 = ZoneId.of("UTC");
            out.println(zi);
            out.println(zi2);

            ZonedDateTime zdt = ZonedDateTime.now(zi);
            ZonedDateTime zdt2 = ZonedDateTime.now(zi2);
            ZonedDateTime zdt3 = ZonedDateTime.now(zi3);
            ZonedDateTime zdt4 = ZonedDateTime.now(zi4);

            out.println(zdt);
            out.println(zdt2);
            out.println(zdt3);
            out.println(zdt4);
            //ゾーン情報が含まれているので、LONGのフォーマッタが使用できる
            out.println(zdt.format(dtf6));
            out.println(zdt4.format(dtf6));
        }

        /*
         * 時差
         */
        {
            OffsetDateTime odt = OffsetDateTime.now();
            out.println(odt);
            OffsetDateTime odt2 = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.of("+01:00"));
            out.println(odt2);
            //ローカルの時刻を固定し、時差だけ調整
            out.println(odt2.atZoneSimilarLocal(ZoneId.systemDefault()));
            //指定された時差を考慮して、ローカルの時刻を調整
            out.println(odt2.atZoneSameInstant(ZoneId.systemDefault()));

        }
        /**
         * 時間間隔
         */
        {
            LocalDate start = LocalDate.of(2011, 4, 1);
            LocalDate end = LocalDate.now();
            Period p = Period.between(start, end);
            out.println(p);
        }
        /*
         * インスタント
         */
        {
            Instant it1 = Instant.now();
            Instant it2 = Instant.MIN;
            out.println(it1);
            out.println(it2);
            it1.plus(10, ChronoUnit.DAYS);
            out.println(it1);

        }

    }

}
