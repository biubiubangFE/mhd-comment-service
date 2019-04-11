package com.mhdss.comment.utils;

public class OrderUtil {

    public static String getOutTradeNo() {
        return DateUtil.getNowYMDHMSM();
    }


    public static String getTimeStart() {
        return DateUtil.getNowYMDHMS();
    }
}
