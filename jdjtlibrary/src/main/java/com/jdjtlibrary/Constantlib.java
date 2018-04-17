package com.jdjtlibrary;


public class Constantlib {

    /**
     * 1、联网方式
     * */
    public static final String NET_METHOD_GET="GET";  //get方式联网
    public static final String NET_METHOD_POST="POST";  //POST方式联网


    /**
     *2、网络代理设置
     * */
    public static String PROXY_IP = "";
    public static int PROXY_PORT = 0;
    /*
    * 3、日志管理常量
    * */
    public static final String LOG_TAG="lsh";   //日志tag名称


    /**
     *
     / 当前是否是正式环境还是测试环境
     *
     * 当是正式环境时，日志会关闭，一些测试开关会关闭 ， 第三方测试账号会切换
     *  true -- 线上
     *  false --测试
     * */
    public static final boolean isRelease=false;



    //----------------------页面上拉下拉加载(开始)--------------
    public static final int LOAD_PAGE_SIZE=0;
    //----------------------页面上拉下拉加载(结束)--------------


    public static final String LSKTVbanner="http://poc.handle.ktvdaren.com/php/wxpic.php?";  //banner图片
    public static final String LSKTVrecommand="http://kcloud.v2.service.ktvdaren.com/wxsearchservice.aspx?";
    public static final String BASEURLKTV="http://wx.ktvdaren.com/";
    public static final String KTVROOMMSG=BASEURLKTV+"roominfo/";
    public static final String BASEROOM="http://wx.handle.ktvdaren.com/interface/";
    public static final String KTVROOMUID=BASEROOM+"webbindroom_new.php?";
    public static final String KTV_CONTROL_CHANGE=BASEROOM+"nextsong.php?";
    public static final String KTV_CONTROL_ORIGINAL=BASEROOM+"ctoggle.php?";
    public static final String KTV_CONTROL_PLAYSTATE=BASEROOM+"ptoggle.php?";
    public static final String KTV_CONTROL_VOICE=BASEROOM+"mvol.php?";
    public static final String KTV_CONTROL_MINE=BASEROOM+"mmic.php?";
    public static final String KTV_MUSIC_Select=BASEROOM+"getktvsonglist.php?";
    public static final String KTV_MUSIC_Singed=BASEROOM+"oversonglist.php?";
    public static final String KTV_PLAY_Add=BASEROOM+"addsong.php?";
    public static final String KTV_PLAY_PopMusic=BASEROOM+"songlisttop.php?";
    //----------------pad(开始)------------
    public static final String KTV_BASE_MUSIC="http://kcloud.v2.service.ktvdaren.com/wxsearchservice.aspx?";
    public static final String KTV_BASE_STAR="http://kcloud.v2.service.ktvdaren.com/singerservice.aspx?";
    public static final String SINGER_PHOTO_BASE_URL = "http://bzmusic.ktvdaren.com/BzMusic/SingerImage/";
    //----------------pad(开始)------------
}
