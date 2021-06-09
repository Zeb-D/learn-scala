package com.yd.scala.hello;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author created by Zeb灬D on 2020-08-20 14:18
 */
public class LocalDateTimeTest {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static void main(String[] args) {
        String startDay = "20200819";
        LocalDate parseStart = LocalDate.parse(startDay, DATE_TIME_FORMATTER);
        System.out.println(parseStart.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());

        System.out.println(LocalDate.now().format(DATE_TIME_FORMATTER));

        double pressurePercent = new BigDecimal(3)
                .divide(new BigDecimal(2 + 3 + 5 + 1), 2, BigDecimal.ROUND_HALF_UP)
                .setScale(2, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
//        System.out.println(pressurePercent);

        long res = new BigDecimal(1).divide(new BigDecimal(2), 2, BigDecimal.ROUND_HALF_UP).longValue();
        System.out.println(res);
    }

}
