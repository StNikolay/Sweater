package com.stnikolay.sweater.util;

import com.stnikolay.sweater.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }
}
