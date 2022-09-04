package me.gmx.product_rating_project.util;

import java.util.regex.Pattern;

public class ValidationUtil {
    static final Pattern ALPHANUMERIC = Pattern.compile("[^a-zA-Z0-9]");

    public static boolean isAlphaNumeric(String s){
        return ALPHANUMERIC.matcher(s).find();
    }

}
