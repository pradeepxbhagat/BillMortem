package com.billmartam.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by pp00344204 on 06/06/17.
 */
public class Util {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean isFloatingNumber(String word) {
        try {
            Float.parseFloat(word);
            String[] values = word.split("\\.");
            return  (values.length == 2);
        }catch (NumberFormatException e){
            return false;
        }
    }

    public static boolean checkPdfUrl(String url) {
        String[] urlElements = url.split("/");
        if(urlElements.length <=1){
            urlElements = url.split("\\\\");
        }
        String[] fileNameElements = urlElements[urlElements.length - 1].split("\\.");
        return fileNameElements.length >=2 && fileNameElements[fileNameElements.length -1].toLowerCase().equals("pdf");
    }

    public static boolean isHdfcDateFormat(String word) {
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        try {
            format.parse(word);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static String replaceSpaceInStringWithComma(String searchTerm) {
        return searchTerm.replace(" ",",");
    }

    public static String[] getCommaSplittedString(String searchTerm, String regex) {
        return searchTerm.split(regex);
    }

    public static boolean isCitiDateFormat(String word) {
        SimpleDateFormat format=new SimpleDateFormat("dd/MM");
        try {
            format.parse(word);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
