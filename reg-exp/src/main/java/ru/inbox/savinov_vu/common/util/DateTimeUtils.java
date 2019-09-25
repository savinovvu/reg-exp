package ru.inbox.savinov_vu.common.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.isNull;



public class DateTimeUtils {

    public static String convertTimeFormat(String time, String inputTimeFormat, String outputTimeFormat) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputTimeFormat);
        LocalTime localTime = LocalTime.parse(time, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputTimeFormat);
        String result = localTime.format(outputFormatter);
        return result;
    }


    public static boolean isBeforeOrEquals(LocalTime before, LocalTime after) {
        if (isNull(before) || isNull(after)) {
            return false;
        }

        return before.isBefore(after) || before.equals(after);
    }


    public static boolean isAfterOrEquals(LocalTime after, LocalTime before) {
        if (isNull(before) || isNull(after)) {
            return false;
        }
        return after.isAfter(before) || after.equals(before);
    }


    public static boolean isBeforeOrEquals(LocalDate before, LocalDate after) {
        if (isNull(before) || isNull(after)) {
            return false;
        }

        return before.isBefore(after) || before.equals(after);
    }


    public static boolean isAfterOrEquals(LocalDate after, LocalDate before) {
        if (isNull(before) || isNull(after)) {
            return false;
        }
        return after.isAfter(before) || after.equals(before);
    }

}
