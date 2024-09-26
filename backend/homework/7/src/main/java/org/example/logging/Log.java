package org.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
enum LogLevel
{
    DEBUG,
    ERROR,
    INFO,
    TRACE
}
public class Log {
    private Log(){}
    /**
     * @param object Message to be passed to the logger
     * @param type Type of the message passed to the logger
     */
    public static void customLogger(String message, String type) {
        switch (LogLevel.valueOf(String.valueOf(type).toUpperCase()))  {
            case ERROR -> logger.error(message);
            case DEBUG -> logger.debug(message);
            case TRACE -> logger.trace(message);
            default -> logger.info(message);
        }
    }
    /**
     * Logs a message with a specified type.
     * @param  messageDouble  the message to be logged as a double
     * @param  type           the type of log level (e.g., Constants.ERROR_LOGGER, Constants.INFO_LOGGER, Constants.DEBUG_LOGGER)
     */
    public static void customLogger(Double messageDouble, String type) {
        String message = messageDouble.toString();
        switch (LogLevel.valueOf(String.valueOf(type).toUpperCase()))  {
            case ERROR -> logger.error(message);
            case DEBUG -> logger.debug(message);
            case TRACE -> logger.trace(message);
            default -> logger.info(message);
        }
    }

    public static void customLogger(Integer messageInteger, String type) {
        String message = messageInteger.toString();
        switch (LogLevel.valueOf(String.valueOf(type).toUpperCase()))  {
            case ERROR -> logger.error(message);
            case DEBUG -> logger.debug(message);
            case TRACE -> logger.trace(message);
            default -> logger.info(message);
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(Log.class);
}