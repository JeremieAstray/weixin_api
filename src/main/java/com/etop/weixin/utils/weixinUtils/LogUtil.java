package com.etop.weixin.utils.weixinUtils;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 * @author Pengo.Wen
 * Created by pengo on 14-8-26.
 */
public class LogUtil {

    private static Logger objLog;

    private static Logger getLogger(){
        if(objLog == null){
            objLog = LogManager.getLogger(LogUtil.class);
        }
        return objLog;
    }

    private static void log(String level, Object msg){
        log(level, msg, null);
    }

    private static void log(String level, Throwable e){
        log(level, null, e);
    }

    private static void log(String level, Object msg, Throwable e){
        try {
            getLogger().log((Priority) Level.toLevel(level), msg, e);
        }catch (Exception ex){
            error(ex.getMessage());
        }
    }

    /**
     * 记录普通信息
     * @param message 信息内容
     * @param ex 异常对象
     */
    public static void info(String message, Exception ex){
        try {
            log("INFO", message, ex);
        }catch (Exception e){

        }
    }

    /**
     * 记录普通信息
     * @param message 信息内容
     */
    public static void info(String message){
        try {
            log("INFO", message);
        }catch (Exception e){

        }
    }

    /**
     * 记录错误信息
     * @param message 信息内容
     * @param ex 异常对象
     */
    public static void error(String message, Exception ex){
        try {
            log("ERROR", message, ex);
        }catch (Exception e){

        }
    }

    /**
     * 记录错误信息
     * @param message 信息内容
     */
    public static void error(String message){
        try{
            log("ERROR", message);
        }catch (Exception ex){

        }
    }

    /**
     * 记录警告信息
     * @param message 信息内容
     * @param ex 异常对象
     */
    public static void warn(String message, Exception ex){
        try {
            log("WARN", message, ex);
        }catch (Exception e){

        }
    }

    /**
     * 记录警告信息
     * @param message 信息内容
     */
    public static void warn(String message){
        try{
            log("WARN", message);
        }catch (Exception ex){

        }
    }

    /**
     * 记录调试信息
     * @param message 信息内容
     * @param ex 异常对象
     */
    public static void debug(String message, Exception ex){
        try {
            log("DEBUG", message, ex);
        }catch (Exception e){

        }
    }

    /**
     * 记录调试信息
     * @param message 信息内容
     */
    public static void debug(String message){
        try{
            log("DEBUG", message);
        }catch (Exception ex){

        }
    }

    /**
     * 记录程序致命性错误
     * @param message 信息内容
     * @param ex 异常对象
     */
    public static void fatal(String message, Exception ex){
        try {
            log("FATAL", message, ex);
        }catch (Exception e){

        }
    }

    /**
     * 记录程序致命性错误
     * @param message 信息内容
     */
    public static void fatal(String message){
        try{
            log("FATAL", message);
        }catch (Exception ex){

        }
    }

}
