package com.huce.library.util;

import java.util.Calendar;

public class CalendarUtil {
    public static int getMonthsBetween(Calendar startCalendar, Calendar endCalendar) {
        int startYear = startCalendar.get(Calendar.YEAR);
        int startMonth = startCalendar.get(Calendar.MONTH);
        int endYear = endCalendar.get(Calendar.YEAR);
        int endMonth = endCalendar.get(Calendar.MONTH);

        return (endYear - startYear) * 12 + (endMonth - startMonth);
    }
}
