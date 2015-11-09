package org.slf4j.impl;

import org.productivity.java.syslog4j.Syslog;
import org.productivity.java.syslog4j.SyslogIF;
import org.productivity.java.syslog4j.util.SyslogUtility;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by jeffrey on 4/11/2015.
 */
public class Syslog4JLoggerFactory implements ILoggerFactory {

    private SyslogIF syslog;
    private Integer level;
    // key: name (String), value: a Log4jLoggerAdapter;
    private ConcurrentMap<String, Logger> loggerMap;

    public Syslog4JLoggerFactory() {
        loggerMap = new ConcurrentHashMap<String, Logger>();
        Properties syslog4jProperties = new Properties();
        if (System.getProperties().containsKey("syslog4j.configurationFile")) {
            try {
                syslog4jProperties.load(new FileInputStream(new File(System.getProperty("syslog4j.configurationFile"))));
            } catch (IOException e) {
                syslog4jProperties = System.getProperties();
            }
        } else {
            try {
                syslog4jProperties.load(this.getClass().getResourceAsStream("/syslog4j.properties"));
            } catch (IOException | NullPointerException e) {
                syslog4jProperties = System.getProperties();
            }
        }
        //level = convertLevel(syslog4jProperties.getProperty("syslog4j.level", "WARN"));
        level = SyslogUtility.getLevel(syslog4jProperties.getProperty("syslog4j.level", "WARN"));
        syslog = Syslog.getInstance("unix_syslog");
        syslog.getConfig().setIdent(syslog4jProperties.getProperty("syslog4j.ident", "syslog4j"));
        syslog.getConfig().setFacility(syslog4jProperties.getProperty("syslog4j.facility", "USER"));
    }

//    private Integer convertLevel(String levelString) {
//        return SyslogUtility.getLevel(levelString.toUpperCase());
//        switch (levelString.toUpperCase()) {
//            case "DEBUG":
//                return Syslog.LEVEL_DEBUG;
//            case "INFO":
//                return Syslog.LEVEL_INFO;
//            case "ERROR":
//                return Syslog.LEVEL_ERROR;
//            case "NOTICE":
//                return Syslog.LEVEL_NOTICE;
//            case "EMERG":
//                return Syslog.LEVEL_EMERGENCY;
//            case "CRIT":
//                return Syslog.LEVEL_CRITICAL;
//            case "ALERT":
//                return Syslog.LEVEL_ALERT;
//            case "WARN":
//            default:
//                return Syslog.LEVEL_WARN;
//
//        }
//    }

    public Logger getLogger(String name) {
        if (loggerMap.containsKey(name) && loggerMap.get(name)!=null) {
            return loggerMap.get(name);
        } else {
            Logger logger = new Syslog4JLoggerAdapter(syslog, level);
            loggerMap.put(name, logger);
            return logger;
        }
    }

}
