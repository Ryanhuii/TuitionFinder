package com.ryanhuii.tuitionfinder.utils;

import com.ryanhuii.tuitionfinder.model.Account;
import com.ryanhuii.tuitionfinder.model.Parent;
import org.springframework.context.ApplicationContext;

public class ParentUtils {
    private static ApplicationContext applicationContext;
    private static Account account;
    private static Parent parent;

    public static void setApplicationContext(ApplicationContext context) {
        ParentUtils.applicationContext = context;
    }

    public static void setAccount(Account account) {
        ParentUtils.account = account;
    }

    public static void setParent(Parent parent) {
        ParentUtils.parent = parent;
    }
}
