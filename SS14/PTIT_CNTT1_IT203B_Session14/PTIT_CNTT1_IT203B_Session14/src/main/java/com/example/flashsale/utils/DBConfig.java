package com.example.flashsale.utils;

import java.io.InputStream;
import java.util.Properties;

public class DBConfig {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = DBConfig.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {

            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Cannot load DB config", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}