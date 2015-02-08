package fox.spiteful.forbidden;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogHandler {
    public static final Logger logger = LogManager.getLogger("Forbidden Magic");

    public static void log(Level level, Throwable e, String message) {
        log(level, message);
        e.printStackTrace();
    }

    public static void log(Level level, String message) {
        logger.log(level, message);
    }
}
