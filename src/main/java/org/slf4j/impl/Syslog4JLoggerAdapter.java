package org.slf4j.impl;

import org.productivity.java.syslog4j.Syslog;
import org.productivity.java.syslog4j.SyslogIF;
import org.slf4j.Marker;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

/**
 * Created by jeffrey on 4/11/2015.
 */
public class Syslog4JLoggerAdapter extends MarkerIgnoringBase implements LocationAwareLogger, Serializable {

    private final transient SyslogIF syslog;
    private Integer level;

    Syslog4JLoggerAdapter(SyslogIF syslog, Integer level) {
        this.syslog = syslog;
        //SyslogIF syslog = Syslog.getInstance("udp");
        //syslog.info("This is another INFO level log entry.");
    }

    public boolean isTraceEnabled() {
        return false;
    }

    public void trace(String msg) {
        // not for now
    }

    public void trace(String format, Object arg) {
        // not for now
    }

    public void trace(String format, Object arg1, Object arg2) {
        // not for now
    }

    public void trace(String format, Object... arguments) {
        // not for now
    }

    public void trace(String msg, Throwable t) {
        // not for now
    }

    public boolean isDebugEnabled() {
        if (level <= Syslog.LEVEL_DEBUG) return true;
        else return false;
    }

    public void debug(String msg) {
        syslog.debug(msg);
    }

    public void debug(String format, Object arg) {
        FormattingTuple ft = MessageFormatter.format(format, arg);
        syslog.debug(ft.getMessage());
    }

    public void debug(String format, Object arg1, Object arg2) {
        FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
        syslog.debug(ft.getMessage());
    }

    public void debug(String format, Object... arguments) {
        FormattingTuple ft = MessageFormatter.arrayFormat(format, arguments);
        syslog.debug(ft.getMessage());
    }

    public void debug(String msg, Throwable t) {
        syslog.debug(msg);
        for (String line : getStacktrace(t)) syslog.debug(line);
    }

    public boolean isInfoEnabled() {
        if (level >= Syslog.LEVEL_INFO) return true;
        else return false;
    }

    public void info(String msg) {
        syslog.info(msg);
    }

    public void info(String format, Object arg) {
        FormattingTuple ft = MessageFormatter.format(format, arg);
        syslog.info(ft.getMessage());
    }

    public void info(String format, Object arg1, Object arg2) {
        FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
        syslog.info(ft.getMessage());
    }

    public void info(String format, Object... argArray) {
        FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
        syslog.info(ft.getMessage());
    }

    public void info(String msg, Throwable t) {
        syslog.info(msg);
        for (String line : getStacktrace(t)) syslog.info(line);
    }

    public boolean isWarnEnabled() {
        if (level <= Syslog.LEVEL_WARN) return true;
        else return false;
    }

    public void warn(String msg) {
        syslog.warn(msg);
    }

    public void warn(String format, Object arg) {
        FormattingTuple ft = MessageFormatter.format(format, arg);
        syslog.warn(ft.getMessage());
    }

    public void warn(String format, Object arg1, Object arg2) {
        FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
        syslog.warn(ft.getMessage());
    }

    public void warn(String format, Object... argArray) {
        FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
        syslog.warn(ft.getMessage());
    }

    public void warn(String msg, Throwable t) {
        syslog.warn(msg);
        for (String line : getStacktrace(t)) syslog.warn(line);
    }

    public boolean isErrorEnabled() {
        if (level <= Syslog.LEVEL_ERROR) return true;
        else return false;
    }

    public void error(String msg) {
        syslog.error(msg);
    }

    public void error(String format, Object arg) {
        FormattingTuple ft = MessageFormatter.format(format, arg);
        syslog.error(ft.getMessage());
    }

    public void error(String format, Object arg1, Object arg2) {
        FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
        syslog.error(ft.getMessage());
    }

    public void error(String format, Object... argArray) {
        FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
        syslog.error(ft.getMessage());
    }

    public void error(String msg, Throwable t) {
        syslog.error(msg);
    }

    public void log(Marker marker, String callerFQCN, int level, String msg, Object[] argArray, Throwable t) {
        int syslogLevel;
        switch (level) {
            case LocationAwareLogger.TRACE_INT:
                syslogLevel = Syslog.LEVEL_DEBUG;
                break;
            case LocationAwareLogger.DEBUG_INT:
                syslogLevel = Syslog.LEVEL_DEBUG;
                break;
            case LocationAwareLogger.INFO_INT:
                syslogLevel = Syslog.LEVEL_INFO;
                break;
            case LocationAwareLogger.WARN_INT:
                syslogLevel = Syslog.LEVEL_WARN;
                break;
            case LocationAwareLogger.ERROR_INT:
                syslogLevel = Syslog.LEVEL_ERROR;
                break;
            default:
                throw new IllegalStateException("Level number " + level + " is not recognized.");
        }
        syslog.log(syslogLevel, msg);
    }

    private String[] getStacktrace(Throwable throwable) {
        StringWriter writer = new StringWriter();
        throwable.printStackTrace(new PrintWriter(writer));
        return writer.toString().split("\n");
    }
}
