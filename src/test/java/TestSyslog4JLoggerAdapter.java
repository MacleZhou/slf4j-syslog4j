import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.productivity.java.syslog4j.Syslog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.IOException;

import static junit.framework.TestCase.fail;

/**
 * Created by jeffrey on 4/11/2015.
 */
public class TestSyslog4JLoggerAdapter {

    private Logger logger = LoggerFactory.getLogger(TestSyslog4JLoggerAdapter.class);
    private Marker notice = MarkerFactory.getMarker("FATAL");
    @BeforeClass
    public static void setup() {
//        System.setProperty("syslog4j.ident", "test");
//        System.setProperty("syslog4j.facility", "auth");
//        System.setProperty("syslog4j.level", "INFO");
    }

    @Test
    public void testTraceLogging() {
        try {
            logger.trace("Trace message about number {}", 5);
        } catch (Exception e) {
            fail(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void testDebugLogging() {
        try {
            logger.debug(notice, "TEST debug");
        } catch (Exception e) {
            fail(e.getMessage());
            e.printStackTrace();
        }
    }
    @Test
    public void testInfoLogging() {
        try {
            logger.info("TEST info!");
        } catch (Exception e) {
            fail(e.getMessage());
            e.printStackTrace();
        }
    }
    @Test
    public void testWarnLogging() {
        try {
            logger.warn("Warning at level {}", Syslog.LEVEL_WARN);

            logger.warn("Test throwable", new IOException("Some IOException"));
        } catch (Exception e) {
            fail(e.getMessage());
            e.printStackTrace();
        }
    }
    @Test
    public void testErrorLogging() {
        try {
            logger.error("Error message");
        } catch (Exception e) {
            fail(e.getMessage());
            e.printStackTrace();
        }
    }

}
