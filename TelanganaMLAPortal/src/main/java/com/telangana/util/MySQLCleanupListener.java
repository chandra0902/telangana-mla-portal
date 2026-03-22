package com.telangana.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

public class MySQLCleanupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application Started");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            AbandonedConnectionCleanupThread.checkedShutdown();
            System.out.println("MySQL Cleanup Thread Stopped");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}