import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by jeffrey on 4/11/2015.
 */
public class TestSyslog4JLoggerAdapter {

    private Logger logger = LoggerFactory.getLogger(TestSyslog4JLoggerAdapter.class);

    @Test
    public void testLogging() {
        logger.debug("TEST debug");
        logger.info("TEST info!");

        logger.warn("Test throwable", new IOException("Some IOException"));

    }
}
