package com.asuna.textutils.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public final static String standerdEN = "yyyy-MM-dd HH:mm:ss";
    public final static String standerdCN = "yyyy年MM月dd日 HH:mm:ss";
    public final static String stranderMIS = "yyyyMMddHHmmssSSS";
    public final static String DATE = "yyyy-MM-dd";
    public final static String TIME = "HH:mm:ss";

    private String partern;
    private SimpleDateFormat format;

    public static String getCurrentTimeCN(){
        SimpleDateFormat format = new SimpleDateFormat(standerdCN);
        return format.format(new Date());
    }

    public static String getCurrentTimeEN(){
        SimpleDateFormat format = new SimpleDateFormat(standerdEN);
        return format.format(new Date());
    }

    public TimeUtils(String partern){
        this.partern = partern;
        format = new SimpleDateFormat(partern);
    }

    public Date convertStringToDate(String ori) throws ParseException {
        return format.parse(ori);
    }

    public String convertDateToString(Date now){
        return format.format(now);
    }

    public String convertNowToString(){
        return format.format(new Date());
    }

    public void setPartern(String partern){
        this.partern = partern;
        format = new SimpleDateFormat(partern);
    }
}
