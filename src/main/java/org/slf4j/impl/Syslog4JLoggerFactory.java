package org.slf4j.impl;

import org.productivity.java.syslog4j.Syslog;
import org.productivity.java.syslog4j.SyslogIF;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

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
        level = Integer.parseInt(System.getProperty("syslog4j.level", "4"));
        syslog = Syslog.getInstance("unix_syslog");
        syslog.getConfig().setIdent(System.getProperty("syslog4j.ident", "syslog4j"));
        syslog.getConfig().setFacility(System.getProperty("syslog4j.facility", "USER"));

    }

    public Logger getLogger(String name) {
        if (loggerMap.containsKey(name)) {
            return loggerMap.get(name);
        } else {
//            syslog = Syslog.getInstance("unix_syslog");
//            syslog.getConfig().setIdent(name);
//            syslog.getConfig().setFacility();
            //syslog.getConfig().setFacility("kernel");
            Logger logger = new Syslog4JLoggerAdapter(syslog, level);
            loggerMap.put(name, logger);
            return logger;
        }
    }

}
