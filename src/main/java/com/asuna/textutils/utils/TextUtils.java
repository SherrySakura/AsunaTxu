package com.asuna.textutils.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {

    private final static String REF_MATCH_PARTEN = "\\[[0-9]{1,3}\\]|\\[([0-9]{1,3}(,|，)){1,10}[0-9]{1,3}\\]|\\[[0-9]{1,3}-[0-9]{1,3}\\]";

    public static String dropBlank(String ori){
        String result = "";
        StringBuilder builder = new StringBuilder();
        String[] splitOri = ori.split("\n");
        for (String item :
                splitOri) {
            if (item.length() == 0){
                continue;
            }
            int tail = item.length() - 1;
            if (GenUtils.isEnglishOrDigit(item.charAt(tail))){
                builder.append(item + " ");
            }else if (isConnectChar(item.charAt(tail))){
                item = item.substring(0, item.length() - 1);
                builder.append(item);
            } else {
                builder.append(item);
            }
        }
        if (builder.charAt(builder.length() - 1) == ' '){
            builder.deleteCharAt(builder.length() - 1);
        }
        result = builder.toString();
        return result;
    }

    public static void toggleDotCNToEN(char[] s) {
        int n = s.length;
        for (int i = 0; i < n; i++) {
            char now = s[i];
            switch (now){
                case '。':
                    s[i] = '.';
                    break;
                case '，':
                    s[i] = ',';
                    break;
                case '？':
                    s[i] = '?';
                    break;
                case '！':
                    s[i] = '!';
                    break;
                case '（':
                    s[i] = '(';
                    break;
                case '）':
                    s[i] = ')';
                    break;
                case '【':
                    s[i] = '[';
                    break;
                case  '】':
                    s[i] = ']';
                    break;
                case '；':
                    s[i] = ';';
                    break;
                case '：':
                    s[i] = ':';
                    break;
                case '“':
                    s[i] = '"';
                    break;
                case '”':
                    s[i] = '"';
                    break;
                case '‘':
                case '’':
                    s[i] = '\'';
                    break;
                case '《':
                    s[i] = '<';
                    break;
                case '》':
                    s[i] = '>';
                    break;

            }
        }
    }

    private static boolean isConnectChar(char c){
        if (c == '-'){
            return true;
        }
        return false;
    }

    public static String dropRef(String ori){
        if (ori == null || ori.isEmpty()){
            return "";
        }
        String res = "";
        res = ori.replaceAll(REF_MATCH_PARTEN, "");
        return res;
    }
}
