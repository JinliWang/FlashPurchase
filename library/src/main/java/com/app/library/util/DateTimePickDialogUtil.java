package com.app.library.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.widget.TimePicker;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间选择控件 使用方法： private EditText inputDate;//需要设置的日期时间文本编辑框 private String
 * initDateTime="2012年9月3日 14:44",//初始日期时间值 在点击事件中使用：
 * inputDate.setOnClickListener(new OnClickListener() {
 *
 * @author
 * @Override public void onClick(View v) { DateTimePickDialogUtil
 * datePicKDialog=new
 * DateTimePickDialogUtil(SinvestigateActivity.this,initDateTime);
 * datePicKDialog.datePicKDialog(inputDate);
 * <p>
 * } });
 */
public class DateTimePickDialogUtil implements OnDateChangedListener, TimePicker.OnTimeChangedListener {

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // 获得日历实例
        Calendar calendar = Calendar.getInstance();
        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        currentDate = CalendarDay.from(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        dateStr = sdf.format(calendar.getTime());
        initDateTime = dateStr;
        ad.setTitle(dateStr);
    }

    private DatePicker datePicker;
    private TimePicker timePicker;
    private AlertDialog ad;
    private String dateStr;
    private String timeHourStr = "";
    private String timeMinuteStr = "";
    private CalendarDay currentDate;
    private CalendarDay maxDate;
    private CalendarDay minDate;
    private String initDateTime;
    private Activity activity;

    /**
     * 日期时间弹出选择框构造函数
     *
     * @param activity     ：调用的父activity
     * @param initDateTime 初始日期时间值，作为弹出窗口的标题和日期时间初始值
     */
    public DateTimePickDialogUtil(Activity activity, String initDateTime) {
        this.activity = activity;
        this.initDateTime = initDateTime;
    }

    public DateTimePickDialogUtil(Activity activity) {
        this.activity = activity;
    }

    public void init(DatePicker datePicker, Date min, Date max) {
        Calendar calendar = Calendar.getInstance();
        if (!(null == initDateTime || "".equals(initDateTime))) {
            try {
                calendar = this.getCalendarByInitData(initDateTime);
            } catch (Exception e) {
                initDateTime = calendar.get(Calendar.YEAR) + "年"
                        + calendar.get(Calendar.MONTH) + "月"
                        + calendar.get(Calendar.DAY_OF_MONTH) + "日 ";
            }

        } else {
            initDateTime = calendar.get(Calendar.YEAR) + "年"
                    + calendar.get(Calendar.MONTH) + "月"
                    + calendar.get(Calendar.DAY_OF_MONTH) + "日 ";
        }

        if (min != null) {
            datePicker.setMinDate(min.getTime());
        }
        datePicker.setCalendarViewShown(false);
        if (max != null) {
            datePicker.setMaxDate(max.getTime());
        }
        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), this);

    }

    public void initTimePicker() {
        timePicker.setOnTimeChangedListener(this);
        Date d = new Date();
        timeHourStr = transferInt(d.getHours());
        timeMinuteStr = transferInt(d.getMinutes());
    }

    /**
     * 弹出日期时间选择框方法
     *
     * @param inputDate:为需要设置的日期时间文本编辑框
     * @return
     */
    public AlertDialog datePicKDialog(final TextView inputDate) {
        return datePicKDialog(inputDate, null, null, null);
    }

    /**
     * 弹出日期时间选择框方法
     *
     * @param listener:listener
     * @return
     */
    public AlertDialog datePicKDialog(Date min, final OnDatePickListener listener) {
        return datePicKDialog(null, min, null, listener);
    }

    /**
     * 弹出日期时间选择框方法
     *
     * @return
     */
    public AlertDialog datePicKDialog(final TextView inputDate, Date min, Date max) {
        return datePicKDialog(inputDate, min, max, null);
    }

    /**
     * 弹出日期时间选择框方法
     *
     * @param inputDate:为需要设置的日期时间文本编辑框
     * @return
     */
    public AlertDialog datePicKDialog(final TextView inputDate, Date min, Date max, final OnDatePickListener listener) {
        datePicker = new DatePicker(activity);
        if (min != null) {
            minDate = CalendarDay.from(min);
        }
        if (max != null) {
            maxDate = CalendarDay.from(max);
        }

        init(datePicker, min, max);
        ad = new AlertDialog.Builder(activity)
//                .setTitle(initDateTime)
                .setView(datePicker)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        if (minDate != null && currentDate.isBefore(minDate)) {
                            ToastUtil.show("不能低于最小时间");
                            return;
                        }
                        if (maxDate != null && currentDate.isAfter(maxDate)) {
                            ToastUtil.show("不能超过最大时间");
                            return;
                        }
                        if (listener != null) {
                            listener.datePicker(dateStr);
                        }
                        if (inputDate != null) {
                            inputDate.setText(dateStr);
                        }

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                }).show();
        onDateChanged(datePicker, 0, 0, 0);
        return ad;
    }

    /**
     * 日期和时间选择框方法
     *
     * @return
     */
    public AlertDialog dateAndTimePicKDialog(Date min, Date max, final OnDateAndTimePickListener listener) {
        datePicker = new DatePicker(activity);
        if (min != null) {
            minDate = CalendarDay.from(min);
        }
        if (max != null) {
            maxDate = CalendarDay.from(max);
        }
        init(datePicker, min, max);

        ad = new AlertDialog.Builder(activity)
//                .setTitle(initDateTime)
                .setView(datePicker)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        if (minDate != null && currentDate.isBefore(minDate)) {
                            ToastUtil.show("不能低于最小时间");
                            return;
                        }
                        if (maxDate != null && currentDate.isAfter(maxDate)) {
                            ToastUtil.show("不能超过最大时间");
                            return;
                        }

                        timePicker = new TimePicker(activity);
                        initTimePicker();
                        new AlertDialog.Builder(activity)
                                .setView(timePicker)
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (listener != null) {
                                            listener.datePicker(dateStr, timeHourStr + ":" + timeMinuteStr);
                                        }
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {

                                    }
                                })
                                .show();


                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                }).show();
        onDateChanged(datePicker, 0, 0, 0);
        return ad;
    }

    /**
     * 实现将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒,并赋值给calendar
     *
     * @param initDateTime 初始日期时间值 字符串型
     * @return Calendar
     */
    private Calendar getCalendarByInitData(String initDateTime) {
        Calendar calendar = Calendar.getInstance();
        // 将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒
        String date = splitString(initDateTime, "日", "index", "front"); // 日期
        String time = splitString(initDateTime, "日", "index", "back");  // 时间

        String yearStr = splitString(date, "年", "index", "front"); // 年份
        String monthAndDay = splitString(date, "年", "index", "back"); // 月日

        String monthStr = splitString(monthAndDay, "月", "index", "front"); // 月
        String dayStr = splitString(monthAndDay, "月", "index", "back"); // 日
//
//		String hourStr = spliteString(time, ":", "index", "front"); // 时
//		String minuteStr = spliteString(time, ":", "index", "back"); // 分

        int currentYear = Integer.valueOf(yearStr.trim()).intValue();
        int currentMonth = Integer.valueOf(monthStr.trim()).intValue() - 1;
        int currentDay = Integer.valueOf(dayStr.trim()).intValue();
//		int currentHour = Integer.valueOf(hourStr.trim()).intValue();
//		int currentMinute = Integer.valueOf(minuteStr.trim()).intValue();

        calendar.set(currentYear, currentMonth, currentDay);
        return calendar;
    }

    /**
     * 截取子串
     *
     * @param srcStr      源串
     * @param pattern     匹配模式
     * @param indexOrLast
     * @param frontOrBack
     * @return
     */
    public static String splitString(String srcStr, String pattern,
                                     String indexOrLast, String frontOrBack) {
        String result = "";
        int loc = -1;
        if (indexOrLast.equalsIgnoreCase("index")) {
            loc = srcStr.indexOf(pattern); // 取得字符串第一次出现的位置
        } else {
            loc = srcStr.lastIndexOf(pattern); // 最后一个匹配串的位置
        }
        if (frontOrBack.equalsIgnoreCase("front")) {
            if (loc != -1)
                result = srcStr.substring(0, loc); // 截取子串
        } else {
            if (loc != -1)
                result = srcStr.substring(loc + 1, srcStr.length()); // 截取子串
        }
        return result;
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        timeHourStr = transferInt(hourOfDay);
        timeMinuteStr = transferInt(minute);
    }

    private String transferInt(int i) {
        return i < 10 ? "0" + i : i + "";
    }

    public interface OnDatePickListener {
        void datePicker(String date);

    }

    public interface OnDateAndTimePickListener {
        void datePicker(String date, String time);
    }

}
