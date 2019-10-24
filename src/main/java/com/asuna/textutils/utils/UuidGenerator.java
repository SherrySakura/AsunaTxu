package com.asuna.textutils.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UuidGenerator {

    public static String getTaskUuid(){
        SimpleDateFormat format = new SimpleDateFormat(TimeUtils.stranderMIS);
        return format.format(new Date());
    }
}
