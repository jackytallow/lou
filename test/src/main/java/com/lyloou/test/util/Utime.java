package com.lyloou.test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utime {

    private static final SimpleDateFormat SDF_ONE = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
    private static final SimpleDateFormat SDF_TWO = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);

    public static int[] getValidTime(String timeStart) {
        int hourOfDay;
        int minute;
        if (timeStart == null) {
            Calendar instance = Calendar.getInstance();
            hourOfDay = instance.get(Calendar.HOUR_OF_DAY);
            minute = instance.get(Calendar.MINUTE);
        } else {
            String[] ts = timeStart.split(":");
            hourOfDay = Integer.parseInt(ts[0]);
            minute = Integer.parseInt(ts[1]);
        }
        return new int[]{hourOfDay, minute};
    }

    public static String getTimeString(int h, int m) {
        return (h < 10 ? ("0" + h) : ("" + h)) +
                ":" +
                (m < 10 ? ("0" + m) : ("" + m));
    }


    public static String getInterval(String timeStart, String timeEnd) {
        if (timeStart == null || timeEnd == null) {
            return null;
        }
        if (timeStart.compareTo(timeEnd) > 0) {
            return null;
        }
        int[] startArr = Utime.getValidTime(timeStart);
        int[] endArr = Utime.getValidTime(timeEnd);

        int sH = startArr[0];
        int sM = startArr[1];

        int eH = endArr[0];
        int eM = endArr[1];
        int spendAllMinute = (eH * 60 + eM) - (sH * 60 + sM);
        int spendHour = spendAllMinute / 60;
        int spendMinute = spendAllMinute % 60;
        return Utime.getTimeString(spendHour, spendMinute);
    }

    public static String transferDay(String day) {
        Date parse = null;
        try {
            parse = SDF_TWO.parse(day);
        } catch (ParseException e) {
            return null;
        }
        return SDF_ONE.format(parse);
    }
}