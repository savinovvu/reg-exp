package ru.inbox.savinov_vu.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;



/**
 * Provides list of date/time patterns
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeFormats {

    /**
     * Provides list of date patterns
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class DateFormat {
        public static final String LONG = "MMM d, yyyy";
        public static final String LONG_CHART = "MMMM d, yyyy";
        public static final String BY = "MMMM, yyyy";
        public static final String DB = "yyyy-MM-dd";

        public static final String HUMAN_DATE = "MM/dd/yyyy";
        public static final String CLIENT_REMINDER_DATE = "MM/dd";
        public static final String MONTH_HUMAN_DATE = "MM/yyyy";
        public static final String SHORT_HUMAN_DATE = "MM/dd/yy";
        public static final String LONG_DATE = "MMM d, yyyy";
        public static final String LONG_MONTH = "MMMM d, yyyy";
        public static final String LONG_WITH_DAY_OF_WEEK = "EEEE, dd MMMM, yyyy";
        public static final String MONTH_AND_DAY = "MMM dd";
        public static final String MONTH_AND_YEAR = "MMM yy";
        public static final String MONTH_AND_FULL_YEAR = "MMM yyyy";
        public static final String FULL_MONTH_AND_YEAR = "MMMM yyyy";
        public static final String CC_EXP_DATE = "MM/yy";
        public static final String CSV = "yyyy-MM-dd";
        public static final String MONTH_DAY_AND_YEAR = "MMM, dd, yyyy";

        public static final String SHORT_MONTH_AND_YEAR = "MMM yy";
        public static final String MONTH_AND_DAY_12HOURS = "MMM dd hh:mma";
        public static final String STRF_TIME = "E, d MMM 'at' hh:mma";
        public static final String DB_YEAR_MONTH = "yyyyMM";
    }

    /**
     * Provides list of date-time patterns
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class DateTimeFormat {
        public static final String DB = "yyyy-MM-dd HH:mm:ss";

        public static final String HUMAN = "MM/dd/yyyy HH:mm";
        public static final String HUMAN_DATE = "MM/dd/yyyy";
        public static final String SHORT_WITH_ZONE = "hh:mma zzz";
        public static final String HUMAN_WITH_ZONE = "MM/dd/yyyy hh:mm a zzz";
        public static final String HUMAN_WITH_12_HOURS = "MM/dd/yyyy hh:mma";
        public static final String HUMAN_AT_WITH_12_HOURS = "MM/dd/yyyy @ hh:mma";
        public static final String LONG = "MMM dd, yyyy HH:mm";
        public static final String LONG_12_HOURS = "MMM dd, yyyy hh:mm a";
        public static final String LONG_WITH_DAY_OF_WEEK = "EEEE, dd MMMMM yyyy, hh:mma";
        public static final String MONTH_AND_DAY_24_HOURS = "MMM dd HH:mm";
        public static final String MONTH_AND_DAY_12_HOURS = "MMM dd hh:mma";
        public static final String MONTH_AND_DAY_12_HOURS_PAGE = "MMM dd hh:mm a";
        public static final String MONTH_AND_DAY_NOTE ="dd MMM";
        public static final String TIME_12_NOTE = "hh:mm a";
        public static final String DB_DATE = "yyyy-MM-dd";
        public static final String TIME_12 = "hh:mma";
        public static final String TIME_24 = "HH:mm";
        public static final String PICKER = "yyyy-MM-dd hh:mm a";
        public static final String CSV = "yyyy-MM-dd HH:mm:ss";
        public static final String CSV_DATE = "yyyy-MM-dd";


        public static final String DB_ZONE = "yyyy-MM-dd HH:mm:ss Z";
        public static final String HUMAN_DATE_CHART = "yyyy-MM-dd";
        public static final String ISO_OFFSET_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ssZ";
        public static final String YAML = "yyyy-MM-dd HH:mm:ss.n X";

        public static final String FULL = "dd MMMM yyyy HH:mm";
        public static final String CALENDAR_DATE_TIME_PATTERN = "MMM d, yyyy h:mm a";
        public static final String REMINDER_PICKER = "EEE MMM d HH:mm:ss z yyyy"; //ex: Thu Nov 29 14:33:20 GMT 2001
        public static final String REMINDER_DATETIMEPICKER = "yyyy-M-d h:mm a"; //ex: 2016-10-20 6:14 PM
        public static final String SCHEDULE_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
        public static final String STRFTIME = "dd MMM yyyy hh:mm:ss";
    }


    /**
     * Provides list of time patterns
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TimeFormat {
        public static final String CLIENT_REMINDER_TIME = "h:mm a";
        public static final String WITH_HOURS = "HH:mm:ss";
        public static final String WITHOUT_HOURS = "mm:ss";
        public static final String SHORT_WITH_ZONE = "hh:mm a zzz";
        public static final String JT = "[hh, mm, ss]";
        public static final String HOURS_MERIDIEM = "hh a";
        public static final String HOURS_MINUTES_MERIDIEM = "hh:mm a";
        public static final String ONLY_HOURS = "HH";
        public static final String HOURS_MINUTES = "HH:mm";
    }
}
