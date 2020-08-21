package com.stnikolay.sweater.util;

import com.stnikolay.sweater.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class UserUtil {

    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }

    public static String getTime() {
        return new SimpleDateFormat("HH:mm:ss").format(getCalendar().getTime());
    }

    public static String getDate() {
        return new SimpleDateFormat("yyyy.MM.dd").format(getCalendar().getTime());
    }

    private static Calendar getCalendar() {
        return new GregorianCalendar();
    }
}
