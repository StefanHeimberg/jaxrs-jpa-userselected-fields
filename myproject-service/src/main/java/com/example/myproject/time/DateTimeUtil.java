package com.example.myproject.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
public class DateTimeUtil {
    
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    private static DateTimeFormatter defaultDateTimeFormatter;
    
    public static DateTimeFormatter getDefaultDateTimeFormatter() {
        if(null == defaultDateTimeFormatter) {
            defaultDateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);
        }
        return defaultDateTimeFormatter;
    }
    
    public static String toFormattedString(final LocalDateTime dateTime) {
        if(null == dateTime) {
            return null;
        }
        return dateTime.format(DateTimeUtil.getDefaultDateTimeFormatter());
    }
    
}
