package com.plivo.contactbook.util;

import java.util.regex.Pattern;

public class ContactBookUtil {
    public static boolean isValidEmail(String searchString) {
        String email_pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(email_pattern);
        return pattern.matcher(searchString).matches();
    }
}
