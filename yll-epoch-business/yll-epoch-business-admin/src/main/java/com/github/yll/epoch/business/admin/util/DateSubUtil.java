package com.github.yll.epoch.business.admin.util;

import java.text.ParseException;
import java.util.Date;

/**
 * @author luliang_yu
 * @date 2018-11-28
 */

public class DateSubUtil {

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Date beginDate;
        Date endDate;
        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
            day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
            System.out.println("相隔的天数=" + day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

    public static long getDaySub(long beginDateMillies, long endDateMillies) {
        long day = 0;
        day = (endDateMillies - beginDateMillies) / (24 * 60 * 60 * 1000);
        System.out.println("相隔的天数=" + day);
        return day;
    }

    public static long getDaySub(Date beginDate, Date endDate) {
        long day = 0;
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        System.out.println("相隔的天数=" + day);
        return day;
    }

    public static void main(String[] args) {
        getDaySub("2018-11-29 23:59:59", "2018-11-30 00:00:00");
    }
}
