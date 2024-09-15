package com.ganesh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogMaster {
    private LogMaster() {
        throw new IllegalStateException("Utility class");
    }
    private static final Logger slf4jLogger = LoggerFactory.getLogger(LogMaster.class);
    public static void print(int data){
        slf4jLogger.info("{}",data);
    }
    public static void print(String data){
        slf4jLogger.info(data);
    }
    public static <T> void print(String fstring, T data){
        slf4jLogger.info(fstring,data);
    }
    public static <T> void print(String fstring, T data, T data2){
        slf4jLogger.info(fstring,data,data2);
    }

}
