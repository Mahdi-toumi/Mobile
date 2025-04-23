package com.enicarthage.coulisses.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static String formatDate(String dateString) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault());
            Date date = inputFormat.parse(dateString);
            return outputFormat.format(date);
        } catch (Exception e) {
            return dateString;
        }
    }

    public static String formatTime(BigDecimal time) {
        try {
            int totalMinutes = time.multiply(new BigDecimal(60)).intValue();
            int hours = totalMinutes / 60;
            int minutes = totalMinutes % 60;
            return String.format(Locale.getDefault(), "%02d:%02d", hours, minutes);
        } catch (Exception e) {
            return time.toString();
        }
    }
}