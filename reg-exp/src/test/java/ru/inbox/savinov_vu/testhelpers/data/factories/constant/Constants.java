package ru.inbox.savinov_vu.testhelpers.data.factories.constant;

import ru.inbox.savinov_vu.common.util.DateTimeUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;



public class Constants {

  public static final String LOGIN = "login";


  public static class LocalDateConstant {

    public static final LocalDate START_UNIX_EPOCH = LocalDate.of(1970, 1, 1);

    public static final LocalDate START_XX_AGE = LocalDate.of(1900, 1, 1);

  }

  public static class LocalDateTimeConstant {

    public static final LocalDateTime START_UNIX_EPOCH = LocalDateTime.of(1970, 1, 1, 1, 1);

    public static final LocalDateTime START_XX_AGE = LocalDateTime.of(1900, 1, 1, 1, 1);


  }


  public static class DateConstant {

    public static final Date START_UNIX_EPOCH = DateTimeUtils.convertLocalDateTimeToDate(LocalDateTimeConstant.START_UNIX_EPOCH);

    public static final Date START_XX_AGE = DateTimeUtils.convertLocalDateTimeToDate(LocalDateTimeConstant.START_XX_AGE);

  }


}
