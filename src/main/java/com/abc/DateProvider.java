package com.abc;

import java.util.Calendar;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateProvider {
    private static DateProvider instance = null;

    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }

    public LocalDate localNow() {
    	
            ZoneId defaultZoneId = ZoneId.systemDefault();

            Instant instant = Calendar.getInstance().getTime().toInstant();

            return instant.atZone(defaultZoneId).toLocalDate();
    }
}
    
    
