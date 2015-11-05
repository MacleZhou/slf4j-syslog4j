import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.IOException;

/**
 * Created by jeffrey on 4/11/2015.
 */
public class TestSyslog4JLoggerAdapter {

    private Logger logger = LoggerFactory.getLogger(TestSyslog4JLoggerAdapter.class);

    @BeforeClass
    public static void setup() {
//        System.setProperty("syslog4j.ident", "test");
//        System.setProperty("syslog4j.facility", "auth");
//        System.setProperty("syslog4j.level", "INFO");
    }
    @Test
    public void testLogging() {
        logger.debug("TEST debug");
        logger.info("TEST info!");

        logger.warn("Test throwable", new IOException("Some IOException"));
        logger.error("Error message");
    }
}
