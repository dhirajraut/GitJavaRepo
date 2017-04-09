/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.intertek.entity.TimeZone;
import com.intertek.phoenix.common.Form;

/**
 * The Class DateUtil.
 * 
 * @version 1.0 May 27, 2009
 * @author Patni
 * @author lily.sun
 */
public class DateUtil {
    final static int MILLISEC_IN_MIN = 60 * 1000;
    /**
     * Name :stringToDate Purpose :To change the string date to the time stamp
     * 
     * @param strDt
     *            the str dt
     * 
     * @return the Timestamp
     */
    public static Timestamp stringToDate(String strDt) {
        Timestamp ts = null;

        if (strDt != null && !"".equals(strDt.trim())) {
            try {
                DateFormat dateFormat1 = new SimpleDateFormat(Form.getCurrentUserFormat());

                java.util.Date dt = dateFormat1.parse(strDt);
                ts = new Timestamp(dt.getTime());
            }
            catch (Exception e) {
                ts = null;
            }
        }
        return ts;
    }

    

    /**
     * Name :dateToString Purpose :To change the timestamp to the string date
     * 
     * @param dt
     *            the dt
     * 
     * @return the String
     */
    public static String dateToString(Timestamp dt) {
        String result = "";
        try {
            DateFormat dateFormat1 = new SimpleDateFormat(Form.getCurrentUserFormat());
            result = dateFormat1.format(dt);
        }
        catch (Exception e) {
            result = "";
        }
        return result;
    }

    /**
     * This method converts a TimeStamp that is in the source TimeZone to the destination TimeZone
     *  
     * @param relatedDate
     * @param sourceTz
     * @param destTz
     * @return
     */
    public static Timestamp getConvertedDateTime(java.sql.Timestamp relatedDate, TimeZone sourceTz, TimeZone destTz) {
        long originalDateInMilliSec = relatedDate.getTime();
        
        // Difference in minutes between time zones
        int timeZoneDiffInMins = 0;
        if ((destTz != null) && (sourceTz != null)) {
            timeZoneDiffInMins = Integer.parseInt(destTz.getUtcOffset()) - Integer.parseInt(sourceTz.getUtcOffset());
        }

        int convertedTimeInMilliSec = timeZoneDiffInMins * MILLISEC_IN_MIN;
        final long convertedTime = originalDateInMilliSec + convertedTimeInMilliSec;
        final Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(convertedTime);
        return new Timestamp(convertedTime);
    }
 
}
