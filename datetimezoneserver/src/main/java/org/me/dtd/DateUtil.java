package org.me.dtd;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateUtil {

    //Never format the java.time types using SimpleDateFormat
    //in case if you have to do just do it as bellow
    public static String formatDate(LocalDate date) {
        Date utilDate = Date.from(date.atStartOfDay(ZoneOffset.UTC).toInstant());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        return dateFormat.format(utilDate);
    }

    public static String formatDate(LocalDateTime ldt) {
        System.out.println(ldt.toLocalTime());
        ZonedDateTime ldtZoned = ldt.atZone(ZoneId.systemDefault());
        ZonedDateTime utcZoned = ldtZoned.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println(utcZoned.toLocalTime());
        return null;
    }

}
