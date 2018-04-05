package com.mls.library.util;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Description:
 * Create By: MLS Co,Ltd
 */

public class NumUtil {

    /**
     * 两数相乘，保留两位小数点
     *
     * @return
     */
    public static String mul(String a, String b) {
        if (TextUtils.isEmpty(a)) {
            return b;
        }
        if (TextUtils.isEmpty(b)) {
            return a;
        }
        Double c = Double.parseDouble(a);
        Double d = Double.parseDouble(b);
        return limitDouble2(c * d, 2);
    }

    /**
     * 两数相加，保留两位小数点
     *
     * @return
     */
    public static String add(String a, String b) {
        if (TextUtils.isEmpty(a)) {
            return b;
        }
        if (TextUtils.isEmpty(b)) {
            return a;
        }
        Double c = Double.parseDouble(a);
        Double d = Double.parseDouble(b);
        return limitDouble2(c + d, 2);
    }

    /**
     * 两数相减，保留两位小数点
     *
     * @return
     */
    public static String sub(String a, String b, int num) {
        Double c;
        Double d;
        if (TextUtils.isEmpty(a))
            c = 0D;
        else
            c = Double.parseDouble(a);

        if (TextUtils.isEmpty(b))
            d = 0D;
        else
            d = Double.parseDouble(b);
        return limitDouble2(c - d, num);
    }


    /**
     * 两数相加，保留两位小数点
     *
     * @return
     */
    public static String add(String... a) {
        String begin = null;
        for (String s : a) {
            begin = add(begin, s);
        }
        return begin;
    }

    /**
     * 四舍五入 设置double 保留num位小数
     *
     * @param num
     * @param d
     * @return
     */
    public static String limitDouble2(double d, int num) {
        String d1 = String.format("%." + num + "f", d);
        String d2 = new BigDecimal(d1).toString();
        if (d2.indexOf(".") > 0) {
            //正则表达
            d2 = d2.replaceAll("0+?$", "");//去掉后面无用的零
            d2 = d2.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return d2;
    }

    /**
     * 四舍五入 设置double 保留num位小数  不去末尾
     *
     * @param num
     * @param d
     * @return
     */
    public static String limitDouble2NoElli(double d, int num) {
        return String.format("%." + num + "f", d);
    }

    /**
     * 四舍五入 设置double 保留num位小数 不去末尾
     *
     * @param num
     */

    public static String limitDouble2NoElli(String s, int num) {

        if (null == s) {
            return "0";
        }
        double d;
        try {
            d = Double.parseDouble(s);
            return limitDouble2NoElli(d, num);
        } catch (Exception e) {
            e.printStackTrace();
            return "0.00";
        }
    }

    public static String limitDouble2(String s, int num) {

        if (null == s) {
            return "0";
        }
        double d;
        try {
            d = Double.parseDouble(s);
            return limitDouble2(d, num);
        } catch (Exception e) {
            e.printStackTrace();
            return "0.00";
        }
    }

    public static String hiddenPhone(String phone) {
        if (TextUtils.isEmpty(phone)) return "";
        String begin = "";
        String end = "";
        int starlength = 0;
        int length = phone.length();
        if (length <= 3) {
            begin = phone.substring(0, 1);
            starlength = length - 1;
        } else if (length <= 7) {
            begin = phone.substring(0, 3);
            starlength = length - 3;
        } else {
            begin = phone.substring(0, 3);
            end = phone.substring(length - 4, length);
            starlength = length - 7;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(begin);
        for (int i = 0; i < starlength; i++) {
            sb.append("*");
        }
        sb.append(end);
        return sb.toString();
    }

    public static String calculateSize(Long size) {
        String msize;
        if (size < 1024 * 1024) {
            msize = size / 1024 + "KB";
        } else {
            msize = NumUtil.limitDouble2(size / 1024 / 1024, 2) + "MB";
        }
        return msize;
    }

    /**
     * 数字转中文
     *
     * @return
     */
    public static String num2str(int intInput) {
        String si = String.valueOf(intInput);
        String sd = "";
        if (si.length() == 1) // 個
        {
            sd += GetCH(intInput);
            return sd;
        } else if (si.length() == 2)// 十
        {
            if (si.substring(0, 1).equals("1"))
                sd += "十";
            else
                sd += (GetCH(intInput / 10) + "十");
            sd += num2str(intInput % 10);
        } else if (si.length() == 3)// 百
        {
            sd += (GetCH(intInput / 100) + "百");
            if (String.valueOf(intInput % 100).length() < 2)
                sd += "零";
            sd += num2str(intInput % 100);
        } else if (si.length() == 4)// 千
        {
            sd += (GetCH(intInput / 1000) + "千");
            if (String.valueOf(intInput % 1000).length() < 3)
                sd += "零";
            sd += num2str(intInput % 1000);
        } else if (si.length() == 5)// 萬
        {
            sd += (GetCH(intInput / 10000) + "万");
            if (String.valueOf(intInput % 10000).length() < 4)
                sd += "零";
            sd += num2str(intInput % 10000);
        }

        return sd;
    }

    private static String GetCH(int input) {
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
            case 7:
                sd = "七";
                break;
            case 8:
                sd = "八";
                break;
            case 9:
                sd = "九";
                break;
            default:
                break;
        }
        return sd;
    }

    /**
     * 千分位
     */

    public static String format(Double d) {
        DecimalFormat format = new DecimalFormat(",##0.00");
        return format.format(d); // 123,456,789.99
    }
}
