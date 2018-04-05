package com.app.library.util;

import java.math.BigDecimal;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: String帮助类
 * Create By: MLS Co,Ltd
 */
public class StringUtil {

    /**
     * 是否是电话号码
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isTel(String phoneNumber) {
        String expression = "((^(13|14|15|18|17)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.matches();
    }

    /**
     * 四舍五入 设置double 保留num位小数
     *
     * @param num
     * @param d
     * @return
     */
    public static String limitDouble2(double d, int num) {
        String d2 = String.format("%.2f", d);
        return new BigDecimal(d2).toString();
    }

    /**匹配&或全角状态字符或标点*/
    public static final String PATTERN="&|[\uFE30-\uFFA0]|‘’|“”";

    public static String replaceSpecialtyStr(String str,String pattern,String replace){
        if(isBlankOrNull(pattern))
            pattern="\\s*|\t|\r|\n";//去除字符串中空格、换行、制表
        if(isBlankOrNull(replace))
            replace="";
        return Pattern.compile(pattern).matcher(str).replaceAll(replace);

    }
    public static boolean isBlankOrNull(String str){
        if(null==str)return true;
        //return str.length()==0?true:false;
        return str.length()==0;
    }

    /**清除数字和空格*/
    public static  String cleanBlankOrDigit(String str){
        if(isBlankOrNull(str))return "null";
        return Pattern.compile("\\d|\\s").matcher(str).replaceAll("");
    }


    /**
     * Unicode 编码并不只是为某个字符简单定义了一个编码，而且还将其进行了归类。

     /pP 其中的小写 p 是 property 的意思，表示 Unicode 属性，用于 Unicode 正表达式的前缀。

     大写 P 表示 Unicode 字符集七个字符属性之一：标点字符。\\pP‘’“”]",如果在 JDK 5 或以下的环境中，全角单引号对、双引号对

     其他六个是
     L：字母；
     M：标记符号（一般不会单独出现）；
     Z：分隔符（比如空格、换行等）；
     S：符号（比如数学符号、货币符号等）；
     N：数字（比如阿拉伯数字、罗马数字等）；
     C：其他字符
     *
     * */
    public static void main(String[] args){

        System.out.println(replaceSpecialtyStr("中国电信2011年第一批IT设备集中采购-存储备份&（），)(UNIX服务器",PATTERN , ""));

    }
    /**
     * 返回首字母
     * @param strChinese
     * @param bUpCase
     * @return
     */
    public static String getPYIndexStr(String strChinese, boolean bUpCase) {
        try {
            StringBuffer buffer = new StringBuffer();
            byte b[] = strChinese.getBytes("GBK");//把中文转化成byte数组
            for (int i = 0; i < b.length; i++) {
                if ((b[i] & 255) > 128) {
                    int char1 = b[i++] & 255;
                    char1 <<= 8;//左移运算符用“<<”表示，是将运算符左边的对象，向左移动运算符右边指定的位数，并且在低位补零。其实，向左移n位，就相当于乘上2的n次方
                    int chart = char1 + (b[i] & 255);
                    buffer.append(getPYIndexChar((char) chart, bUpCase));
                    continue;
                }
                char c = (char) b[i];
                if (!Character.isJavaIdentifierPart(c))//确定指定字符是否可以是 Java 标识符中首字符以外的部分。
                    c = 'A';
                buffer.append(c);
            }
            return buffer.toString();
        } catch (Exception e) {
            System.out.println((new StringBuilder()).append("\u53D6\u4E2D\u6587\u62FC\u97F3\u6709\u9519").append(e.getMessage()).toString());
        }
        return null;
    }

    /**
     * 得到首字母
     * @param strChinese
     * @param bUpCase
     * @return
     */
    private static char getPYIndexChar(char strChinese, boolean bUpCase) {
        int charGBK = strChinese;
        char result;
        if (charGBK >= 45217 && charGBK <= 45252)
            result = 'A';
        else if (charGBK >= 45253 && charGBK <= 45760)
            result = 'B';
        else if (charGBK >= 45761 && charGBK <= 46317)
            result = 'C';
        else if (charGBK >= 46318 && charGBK <= 46825)
            result = 'D';
        else if (charGBK >= 46826 && charGBK <= 47009)
            result = 'E';
        else if (charGBK >= 47010 && charGBK <= 47296)
            result = 'F';
        else if (charGBK >= 47297 && charGBK <= 47613)
            result = 'G';
        else if (charGBK >= 47614 && charGBK <= 48118)
            result = 'H';
        else if (charGBK >= 48119 && charGBK <= 49061)
            result = 'J';
        else if (charGBK >= 49062 && charGBK <= 49323)
            result = 'K';
        else if (charGBK >= 49324 && charGBK <= 49895)
            result = 'L';
        else if (charGBK >= 49896 && charGBK <= 50370)
            result = 'M';
        else if (charGBK >= 50371 && charGBK <= 50613)
            result = 'N';
        else if (charGBK >= 50614 && charGBK <= 50621)
            result = 'O';
        else if (charGBK >= 50622 && charGBK <= 50905)
            result = 'P';
        else if (charGBK >= 50906 && charGBK <= 51386)
            result = 'Q';
        else if (charGBK >= 51387 && charGBK <= 51445)
            result = 'R';
        else if (charGBK >= 51446 && charGBK <= 52217)
            result = 'S';
        else if (charGBK >= 52218 && charGBK <= 52697)
            result = 'T';
        else if (charGBK >= 52698 && charGBK <= 52979)
            result = 'W';
        else if (charGBK >= 52980 && charGBK <= 53688)
            result = 'X';
        else if (charGBK >= 53689 && charGBK <= 54480)
            result = 'Y';
        else if (charGBK >= 54481 && charGBK <= 55289)
            result = 'Z';
        else
            result = (char) (65 + (new Random()).nextInt(25));
        if (!bUpCase)
            result = Character.toLowerCase(result);
        return result;
    }
}
