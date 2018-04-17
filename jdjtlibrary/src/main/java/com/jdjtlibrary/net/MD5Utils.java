package com.jdjtlibrary.net;

import java.security.MessageDigest;

/**
 * MD5加密:sign值中用到
 */
public class MD5Utils {
    public final static String key = "1qazSuoMusic2016Ggu168@WSX#EDC";

    public MD5Utils() {
    }

    public static final String byte2hexString(byte[] bytes) {
        StringBuffer buf = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buf.toString();
    }

    /**
     * md5加密
     *
     * @param sourceString
     * @return
     */
    public static String MD5Encode(String sourceString) {
        String resultString = null;
        try {
            resultString = new String(sourceString);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byte2hexString(md.digest(resultString.getBytes()));
        } catch (Exception ex) {
        }
        return resultString;
    }
}
