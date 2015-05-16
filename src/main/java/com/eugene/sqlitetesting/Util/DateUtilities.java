package com.eugene.sqlitetesting.Util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtilities {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    /**
     * Convert date to a simple format String
     */
    public static String dateToString(Date d) {
        return dateFormat.format(d);
    }

    public static int lastX = 0;

    /*
    Returns Date - 1
     */
    public static Date previousDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        Calendar mCalendar = new GregorianCalendar(year, month, day);
        mCalendar.add(Calendar.DATE, lastX-- - 1);
        return mCalendar.getTime();
    }

    /*
    Returns Date + 1
    */
    public static Date nextDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        Calendar mCalendar = new GregorianCalendar(year, month, day);
        mCalendar.add(Calendar.DATE, lastX++ + 1);
        return mCalendar.getTime();
    }

}
