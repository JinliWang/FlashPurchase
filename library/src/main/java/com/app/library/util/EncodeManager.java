package com.app.library.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.PublicKey;

/**
 * Description:
 * Create By: MLS Co,Ltd
 */

public class EncodeManager {

    /**
     * http请求加密数据
     */
    public static String encode(String str, String publicKeyStr)  {
        PublicKey publicKey = RsaUtils.generatePublicKey(publicKeyStr);
        //用公钥加密
        byte[] rsa;
        try {
            rsa = RsaUtils.encryptData(str.getBytes("GBK"), publicKey);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
        //返回base64编码的str
        return Base64Utils.encode(rsa);
    }

    /**
     * 转换成UTF-8
     */
    public static String toUTF8(String str)
    {
        String encode = "";
        try {
            encode = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encode;
    }

    /**
     * 加密过程
     * */
    public static String Encryption(String value){
        //数据流A：对原始数据包进行Base64编码
        String base64=Base64Utils.encode(value.getBytes());
        //数据流B：对原始数据包进行MD5签名
        String md5= MD5Util.MD5(value);
        //取A/B流的最短长度
        int len=base64.length()>md5.length()?md5.length():base64.length();
        StringBuffer sb=new StringBuffer();
        //循环合并两个数据流，生成新的数据流
        for(int i=0;i<len;i++){
            sb.append(base64.charAt(i));
            sb.append(md5.charAt(i));
        }
        //将多于的流直接放到合并流尾部
        if(base64.length()>len){
            sb.append(base64.substring(len));
        }
        if(md5.length()>len){
            sb.append(md5.substring(len));
        }
        //返回合并流：混淆后的值
        return sb.toString();
    }

    /**
     * 较验有效性
     * */
    public static boolean Validate(String val){
        //定义数据流A：Base64数据流
        StringBuffer base64=new StringBuffer();
        //定义数据流B：MD5数据流
        StringBuffer md5=new StringBuffer();
        //计算分拆流关键点
        int len=val.length()>64?32:val.length()-32;
        //循环将数据流分拆到A/B流中
        for(int i=0;i<len;i++){
            base64.append(val.charAt(i*2));
            md5.append(val.charAt(i*2+1));
        }
        //根据规则将剩余流放到A|B流尾部
        if(val.length()>64){
            base64.append(val.substring(64));
        }else{
            md5.append(val.substring(2*md5.length()));
        }
        //根据数据流A反向Base64计算得出原始值
        String value=new String(Base64Utils.decode(base64.toString()));
        //将计算出的原始值进行MD5签名
        String check=MD5Util.MD5(value);
        //将计算出的MD5签名与分拆流出来的MD5签名进行比对
        if(check.equals(md5.toString())){
            return true;
        }
        return false;
    }

    /**
     * 解密过程 获取二维码信息
     * */
    public static String Decryption(String val){
        //解密过程前先进行数据流较验，较验失败直接返回Null
        if(!Validate(val)){
            return null;
        }
        //定义数据流A：Base64数据流
        StringBuffer base64=new StringBuffer();
        //计算分拆流关键点
        int len=val.length()>64?32:val.length()-32;
        //循环提取出数据流A对应的数值，即Base64编码值
        for(int i=0;i<len;i++){
            base64.append(val.charAt(i*2));
        }
        if(val.length()>64){
            base64.append(val.substring(64));
        }
        //将数据流A进行Base64反解码
        String value=new String(Base64Utils.decode(base64.toString()));
        //返回原始值
        return value;
    }

}
