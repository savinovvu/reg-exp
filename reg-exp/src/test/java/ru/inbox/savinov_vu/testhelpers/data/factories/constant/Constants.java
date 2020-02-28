package ru.inbox.savinov_vu.testhelpers.data.factories.constant;

import ru.inbox.savinov_vu.common.util.DateTimeUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;



public class Constants {

  public static final String LOGIN = "login";


  public static class LocalDateConstant {

    public static final LocalDate START_UNIX_EPOCH_LOCAL_DATE = LocalDate.of(1970, 1, 1);

    public static final LocalDate START_XX_AGE_LOCALDATE = LocalDate.of(1900, 1, 1);

  }

  public static class LocalDateTimeConstant {

    public static final LocalDateTime START_UNIX_EPOCH_DATE_TIME = LocalDateTime.of(1970, 1, 1, 1, 1);

    public static final LocalDateTime START_XX_AGE_DATE_TIME = LocalDateTime.of(1900, 1, 1, 1, 1);


  }


  public static class DateConstant {

    public static final Date START_UNIX_EPOCH_DATE = DateTimeUtils.convertLocalDateTimeToDate(LocalDateTimeConstant.START_UNIX_EPOCH_DATE_TIME);

    public static final Date START_XX_AGE_DATE = DateTimeUtils.convertLocalDateTimeToDate(LocalDateTimeConstant.START_XX_AGE_DATE_TIME);

  }


}
