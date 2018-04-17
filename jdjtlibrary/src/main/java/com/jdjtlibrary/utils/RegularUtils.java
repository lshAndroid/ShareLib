package com.jdjtlibrary.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * 正则表达式
 * @author wning
 */
public class RegularUtils {

    /**
     * 判别手机是否为正确手机号码；
     * 号码段分配如下：
     * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186
     * 电信：133、153、180、189、（1349卫通）
     */
    public static boolean isMobileNum(String mobiles) {
        String telRegex = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        return mobiles.matches(telRegex);
    }

    /**
     * 判别手机是否为正确手机号码；
     * 号码段分配如下：
     * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186
     * 电信：133、153、180、189、（1349卫通）
     */
    public static boolean isLoginMobileNum(String mobiles) {
        String telRegex = "[1][35678]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        return mobiles.matches(telRegex);
    }

    /**
     * 身份证号
     * @param text
     * @return
     */
    public static boolean isIDTrue(String text) {
        String regx = "[0-9]{17}x";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        return text.matches(regx) || text.matches(reg1) || text.matches(regex);
    }

    /**
     * 验证手机密码是否符合规范
     *
     * @return
     */
    public static boolean isPasswdOk(String passwd) {
        if (TextUtils.isEmpty(passwd)) {
            return false;
        } else if (passwd.length() > 16 || passwd.length() < 6) {
            return false;
        }
        return true;
    }


    /**
     * 格式化银行卡
     * @param cardNo 银行卡号
     * @return
     */
    public static String formatCard(String cardNo) {
        String card = "";
        card = cardNo.substring(0, 4) + "-XXXX-XXXX-";
        card += cardNo.substring(cardNo.length() - 4);
        return card;
    }

    /**
     * 检查用户输入的密码
     * @param password
     * @return
     */
    public static boolean checkUserPassword(String password) {
        if (password == null || "".equals(password)) {
            return false;
        }
        String patternStr = "^[a-zA-Z0-9]{6,16}$";
        return Pattern.matches(patternStr, password);
    }


}
