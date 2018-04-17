package com.jdjtlibrary.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.regex.Pattern;

//判断字符串
public class StringUtils {

    /**
     * 判断字符串是不是空
     *
     * @param str
     * @return
     */
    public static boolean isEmptyString(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 把中文转成Unicode码
     *
     * @param str
     * @return
     */
    public static String chinaToUnicode(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int chr1 = (char) str.charAt(i);
            if (chr1 >= 19968 && chr1 <= 171941) {//汉字范围 \u4e00-\u9fa5 (中文)
                result += "\\u" + Integer.toHexString(chr1);
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }

    /**
     * 判断是否为中文字符
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    //检测是否有emoji字符
    public static boolean containsEmoji(String source) {
        if (source.equals(null)) {
            return false;
        }
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (isEmojiCharacter(codePoint)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source
     * @return
     * @author
     */
    public static String filterEmoji(String source) {

        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) {// 如果不包含 则将字符append
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            } else {
            }
        }

        if (buf == null) {
            return source;// 如果没有找到 emoji表情，则返回源字符串
        } else {
            if (buf.length() == len) {// 这里的意义在于尽可能少的toString，因为会重新生成字符串
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }

    /**
     * 设置消息展开后的显示问题
     *
     * @param msg
     * @param text
     * @param isLoadAll
     */
    public static void setLineCount(Context context, TextView msg, String text, boolean isLoadAll) {

        if (msg == null || text == null || "".equals(text)) {
            return;
        }
        if (isLoadAll) {
            msg.setLines(getLineCount(msg, text, context));
            msg.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        } else {
            msg.setLines(1);
            msg.setEllipsize(TextUtils.TruncateAt.END);
        }
        msg.setText(text);
        msg.requestLayout();
    }

    /**
     * 获取行数
     *
     * @param textView
     * @param str
     * @param context
     * @return
     */
    public static int getLineCount(TextView textView, String str, Context context) {
        float screenW = context.getResources().getDisplayMetrics().widthPixels;
        float paddingLeft = ((RelativeLayout) textView.getParent()).getPaddingLeft();
        float paddingReft = ((RelativeLayout) textView.getParent()).getPaddingRight();
        int line = (int) Math.ceil((textView.getPaint().measureText(str) / (screenW - paddingLeft - paddingReft)));
        return line;
    }

    /**
     * 验证特殊字符
     *
     * @param str
     * @return
     */
    public static boolean checkInputString(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        String patternStr = "^[,.<>/?;:'\"`~!#$%^&*()-_=+\\sa-zA-Z0-9\\u4e00-\\u9fa5]+$";
        return Pattern.matches(patternStr, str);
    }

    /**
     * 替换中文标点符号
     *
     * @param str
     * @return
     */
    public static String replaceChinesePunctuation(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int n = 0; n < str.length(); n++) {
            if (isChinesePunctuation(str.charAt(n))) {
                builder.append(",");
            } else {
                builder.append(str.charAt(n));
            }
        }
        return builder.toString();
    }

    /**
     * 根据UnicodeBlock方法判断中文标点符号
     *
     * @param c
     * @return
     */
    private static boolean isChinesePunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION ||
                ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION ||
                ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS ||
                ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS) {
            return true;
        } else {
            return false;
        }
    }
}
