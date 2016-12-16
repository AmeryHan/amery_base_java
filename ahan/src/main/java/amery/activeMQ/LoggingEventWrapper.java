package amery.activeMQ;

import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.spi.LoggingEvent;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class LoggingEventWrapper implements Serializable {

private static final String ENHANCED_PATTERN_LAYOUT = "%throwable";

    private static final long serialVersionUID = 1L;

    private LoggingEvent loggingEvent;

    private Long timeStamp;

    private String level;

    private String logger;

    private String message;

    private String detail;

    private String ipAddress;

    private String hostName;

 

    public LoggingEventWrapper(LoggingEvent loggingEvent){

        this.loggingEvent = loggingEvent;

 

        //Format event and set detail field

        EnhancedPatternLayout layout = new EnhancedPatternLayout();

        layout.setConversionPattern(ENHANCED_PATTERN_LAYOUT);

        this.detail = layout.format(this.loggingEvent);

    }

 
/*
    public Long getTimeStamp() {

        return this.loggingEvent.timeStamp;

    }

 

    public String getLevel() {

        return this.loggingEvent.getLevel().toString();

    }

 

    public String getLogger() {

        return this.loggingEvent.getLoggerName();

    }

 

    public String getMessage() {

        return this.loggingEvent.getRenderedMessage();

    }

 */

    public String getDetail() {

        return this.detail;

    }

 

    public LoggingEvent getLoggingEvent() {

        return loggingEvent;

    }

 

    public String getIpAddress() {

        try {

            return InetAddress.getLocalHost().getHostAddress();

        } catch (UnknownHostException e) {

            return "Could not determine IP";

        }

    }

 

    public String getHostName() {

        try {

            return InetAddress.getLocalHost().getHostName();

        } catch (UnknownHostException e) {

            return "Could not determine Host Name";

        }

    }

}
