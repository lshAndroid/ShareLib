package com.jdjtlibrary.utils;

import java.util.regex.Pattern;


public class LoginUtils {

    /**
     * 判断用户有没有登录
     *
     * @return
     */
    public static boolean isLogin() {
        return MSharedPreferences.getBoolean(MSharedPreferences.LOGIN, false);
    }


    /**
     * 清楚登录信息
     */
    public static void clearLoginInfo() {
        MSharedPreferences.putString(MSharedPreferences.KEY_USER_INFO, "");
    }

    /**
     * 判断用户是不是第一次安装应用
     *
     * @return
     */
    public static boolean isFirstInstaller() {

        String info = MSharedPreferences.getString(MSharedPreferences.KEY_FIRST_ENTER, "");
        if (info == null || "".equals(info)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检查用户输入的密码
     *
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

    /**
     * 验证用户输入的手机号
     *
     * @param phone
     * @return
     */
    public static boolean checkUserPhone(String phone) {
        if (phone == null || "".equals(phone)) {
            return false;
        }
        String patternStr = "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
        return Pattern.matches(patternStr, phone);
    }

    //-----------------ktv部分(开始)---------------
    /**
     * 判断是否绑定房间
     * @return
     */
    public static boolean isBindRoom() {
        String infoBind = MSharedPreferences.getString(MSharedPreferences.KTVUID, "");
        if (infoBind==null||"".equals(infoBind)){
            return false;
        }else {
            return true;
        }
    }
    //-----------------ktv部分(结束)---------------

    //----------------xms绑定房间(开始)--------------
    public static boolean isBindXmsRoom() {
        String infoBind = MSharedPreferences.getString(MSharedPreferences.XMSBIND_PLAYIP, "");  //自己播放器必须的ip
        String infoHallid = MSharedPreferences.getString(MSharedPreferences.XMSBIND_HALLID, "");
        String infoName = MSharedPreferences.getString(MSharedPreferences.XMSBIND_NAME, "");
        if (infoHallid==null||"".equals(infoHallid)){
            return false;
        }
        if (infoName==null||"".equals(infoName)){
            return false;
        }
        if (infoBind==null||"".equals(infoBind)){
            return false;
        }else {
            return true;
        }
    }
    //----------------xms绑定房间(结束)--------------
    //-------------定居山绑定(开始)----------------------
    public static boolean isBindDJSRoom() {
//        String infoBind = MSharedPreferences.getString(MSharedPreferences.XMSBIND_PLAYIP, "");  //不一定需要
        String infoHallid = MSharedPreferences.getString(MSharedPreferences.XMSBIND_HALLID, "");
        String infoName = MSharedPreferences.getString(MSharedPreferences.XMSBIND_NAME, "");
        String infoDJSNo=MSharedPreferences.getString(MSharedPreferences.DJS_NO, "");//定居山必须的编号
        if (infoHallid==null||"".equals(infoHallid)){
            return false;
        }
        if (infoName==null||"".equals(infoName)){
            return false;
        }
        if (infoDJSNo==null||"".equals(infoDJSNo)){
            return false;
        }
//        if (infoBind==null||"".equals(infoBind)){
//            return false;
//        }
        else {
            return true;
        }
    }
    //-------------定居山绑定(结束)----------------------
    public static boolean isBindXmsUrl() {
        String infoBind = MSharedPreferences.getString(MSharedPreferences.XMSBIND_URL,"");
        if (infoBind==null||"".equals(infoBind)){
            return false;
        }else {
            return true;
        }
    }
    public static boolean isSunKtvServiceIP() {
        String infoServiceBind = MSharedPreferences.getString(MSharedPreferences.SunKTV_Service,"");
        String infoKtvBind = MSharedPreferences.getString(MSharedPreferences.SunKTV_IP,"");
        if (infoServiceBind==null||"".equals(infoServiceBind)){
            return false;
        }
        if (infoKtvBind==null||"".equals(infoKtvBind)){
            return false;
        }
        else {
            return true;
        }
    }

}
