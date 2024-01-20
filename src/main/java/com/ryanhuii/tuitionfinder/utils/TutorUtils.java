package com.ryanhuii.tuitionfinder.utils;

import com.ryanhuii.tuitionfinder.model.Account;
import com.ryanhuii.tuitionfinder.model.Parent;
import com.ryanhuii.tuitionfinder.model.Tutor;
import org.springframework.context.ApplicationContext;

public class TutorUtils {
    private static ApplicationContext applicationContext;
    private static Account account;
    private static Tutor tutor;

    public static void setApplicationContext(ApplicationContext context) {
        TutorUtils.applicationContext = context;
    }

    public static void setAccount(Account account) {
        TutorUtils.account = account;
    }

    public static void setTutor(Tutor tutor) {
        TutorUtils.tutor = tutor;
    }
}
