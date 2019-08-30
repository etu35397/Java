package main.java.factory;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

public class DateFactory {

    public static GregorianCalendar sqlTimestampToGregorianCalendar(Timestamp sqlDate) {
        GregorianCalendar calendar = new GregorianCalendar();
        if (sqlDate != null)
            calendar.setTimeInMillis(sqlDate.getTime());
        else
            calendar = null;
        return calendar;
    }

    public static GregorianCalendar sqlDateToGregorianCalendar(Date sqlDate) {
        GregorianCalendar calendar = new GregorianCalendar();
        if (sqlDate != null)
            calendar.setTime(sqlDate);
        else
            calendar = null;
        return calendar;
    }

    public static Date gregorianCalendarToSqlDate(GregorianCalendar calendar) {
        return new Date(calendar.getTimeInMillis());
    }

    public static Timestamp gregorianCalendarToSqlTimeStamp(GregorianCalendar calendar) {
        return new java.sql.Timestamp(calendar.getTimeInMillis());
    }

    public static GregorianCalendar dateToGregorianCalendar(java.util.Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }
}
