package com.app.library.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Description: date帮助类
 *
 */
public class DateUtil {

    public static final String defaultDatePattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认的日期格式 .
     */
    public static String DEFAULT_DATE_REGEX_SIMPLE = "yyyy-MM-dd";

    // 获取当前日期
    public static String getCurrnetDate() {
        return toStrBySecond(System.currentTimeMillis());
    }

    /**
     * 比较两个日期
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compare(Long date1, Long date2) {
        return date1 < date2;
    }

    // 格式到秒
    public static String strToStr(String date, String fromPattern, String toPattern) {
        Date date1 = str2Date(date, fromPattern);
        return fotmat2Str(toPattern, date1);
    }

    // 格式到秒
    public static String toStrBySecond(Long time) {
        return fotmat2Str("yyyy-MM-dd HH:mm:ss", time);
    }

    // 格式到分
    public static String toStrByMinute(Long time) {
        return fotmat2Str("yyyy-MM-dd HH:mm", time);
    }

    // 格式到分
    public static String toStrByMinute(Date time) {
        return fotmat2Str("yyyy-MM-dd HH:mm", time);
    }

    // 格式到天
    public static String toStrByDay(Long time) {
        return fotmat2Str("yyyy-MM-dd", time);
    }

    // 格式到天
    public static String toStrByDay(Date date) {
        return fotmat2Str("yyyy-MM-dd", date.getTime());
    }

    public static String fotmat2Str(String reg, Long time) {
        return new SimpleDateFormat(reg).format(time);
    }

    public static String fotmat2Str(String reg, Date time) {
        return new SimpleDateFormat(reg).format(time);
    }

    /**
     * 使用预设格式将字符串转为Date
     */
    public static Date str2Date(String strDate) {
        return str2Date(strDate, DEFAULT_DATE_REGEX_SIMPLE);
    }

    /**
     * 使用参数Format将字符串转为Date
     */
    public static Date str2Date(String strDate, String pattern) {
        try {
            return TextUtils.isEmpty(strDate) ? null : new SimpleDateFormat(
                    pattern).parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用参数Format将字符串转为Date
     */
    public static String formatDateStr(String strDate, String currentPattern, String pattern) {
        return fotmat2Str(pattern, str2Date(strDate, currentPattern));

    }

    /**
     * 获取当天的零点
     *
     * @return
     */
    public static Date getDayFirst(Date date) {
        return formatToDate(date, DEFAULT_DATE_REGEX_SIMPLE);
    }

    /**
     * 获取第二天的零点
     *
     * @return
     */
    public static Date getDayEnd(Date date) {
        return addDays(getDayFirst(date), 1);
    }

    /**
     * 获取当天的零点
     *
     * @return
     */
    public static String getDayFirstStr(Date date) {
        return fotmat2Str(DEFAULT_DATE_REGEX_SIMPLE, formatToDate(date, DEFAULT_DATE_REGEX_SIMPLE));
    }

    /**
     * 获取第二天的零点
     *
     * @return
     */
    public static String getDayEndStr(Date date) {
        return fotmat2Str(DEFAULT_DATE_REGEX_SIMPLE, addDays(getDayFirst(date), 1));
    }

    /**
     * 获取当天的零点
     *
     * @return
     */
    public static String getDayFirstStr(String date) {
        return fotmat2Str(DEFAULT_DATE_REGEX_SIMPLE, formatToDate(str2Date(date, DEFAULT_DATE_REGEX_SIMPLE), DEFAULT_DATE_REGEX_SIMPLE));
    }

    /**
     * 获取第二天的零点
     *
     * @return
     */
    public static String getDayEndStr(String date) {
        return fotmat2Str(DEFAULT_DATE_REGEX_SIMPLE, addDays(getDayFirst(str2Date(date, DEFAULT_DATE_REGEX_SIMPLE)), 1));
    }

    public static Date formatToDate(Date date, String regex) {
        SimpleDateFormat sdf = new SimpleDateFormat(regex);
        String date2 = sdf.format(date);
        return str2Date(date2, regex);
    }

    /**
     * 获取当天是一年第几个星期
     *
     * @param day
     * @return
     */
    public static int getWeekNum(Calendar day) {
        int week = day.get(Calendar.WEEK_OF_YEAR);
        if (day.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return week - 1;
        }
        return week;
    }

    /**
     * 日期增加指定天数
     */
    public static Date addDays(Date date, int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, days);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime();   //这个时间就是日期往后推一天的结果
        return date;
    }

    /**
     * 获取当前周的第一天
     *
     * @return
     */
    public static String getWeekFirst(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        dayOfWeek = (dayOfWeek + 6) % 7;
        if (dayOfWeek == 0) dayOfWeek = 7;
        calendar.add(Calendar.DATE, -dayOfWeek + 1);

        return fotmat2Str(DEFAULT_DATE_REGEX_SIMPLE, calendar.getTime());
    }

    public static String addDay(Date date, int i) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.add(Calendar.DATE, i);

        return fotmat2Str(DEFAULT_DATE_REGEX_SIMPLE, calendar.getTime());
    }

    public static Date dateAddDay(Date date, int i) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.add(Calendar.DATE, i);

        return calendar.getTime();
    }


    /**
     * 获取当前周的最后一天 (24点 即第二天零点)
     *
     * @return
     */
    public static String getWeekEnd(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        dayOfWeek = (dayOfWeek + 6) % 7;
        if (dayOfWeek == 0) dayOfWeek = 7;
        calendar.add(Calendar.DATE, 8 - dayOfWeek);

        return fotmat2Str(DEFAULT_DATE_REGEX_SIMPLE, calendar.getTime());
    }

    /**
     * 获取当前月的第一天
     *
     * @return
     */
    public static String getMonthFirst(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return fotmat2Str(DEFAULT_DATE_REGEX_SIMPLE, calendar.getTime());
    }

    /**
     * 获取当前月的最后一天 (24点 即第二天零点)
     *
     * @return
     */
    public static String getMonthEnd(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);

        return fotmat2Str(DEFAULT_DATE_REGEX_SIMPLE, calendar.getTime());
    }

    /**
     * 获取是星期几
     *
     * @return
     */
    public static int getWeek(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(str2Date(date).getTime());
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    public static String getWeekCH(int input) {
        String sd = "";
        switch (input) {
            case 1:
                sd = "一";
                break;
            case 2:
                sd = "二";
                break;
            case 3:
                sd = "三";
                break;
            case 4:
                sd = "四";
                break;
            case 5:
                sd = "五";
                break;
            case 6:
                sd = "六";
                break;
            case 0:
                sd = "日";
                break;
            default:
                break;
        }
        return sd;
    }

    public static String clanderTodatetime(Calendar calendar, String style) {
        SimpleDateFormat formatter = new SimpleDateFormat(style);
        return formatter.format(calendar.getTime());
    }
}