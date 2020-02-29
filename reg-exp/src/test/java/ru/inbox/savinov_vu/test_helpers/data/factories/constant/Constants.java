package ru.inbox.savinov_vu.test_helpers.data.factories.constant;

import ru.inbox.savinov_vu.common.util.DateTimeUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;



public class Constants {

  public static final String LOGIN = "login";

  public static final String TOO_LONG_WORD = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

  public static final String LONG_EMPTY_STRING = "                                          ";

  public static class LocalDateConstant {

    public static final LocalDate START_UNIX_EPOCH_LOCAL_DATE = LocalDate.of(1970, 1, 1);

    public static final LocalDate GAGARINS_FLY_DAY_LOCAL_DATE = LocalDate.of(1961, 4, 12);

  }

  public static class LocalDateTimeConstant {

    public static final LocalDateTime START_UNIX_EPOCH_DATE_TIME = LocalDateTime.of(1970, 1, 1, 1, 1);

    public static final LocalDateTime GAGARINS_FLY_DAY_DATE_TIME = LocalDateTime.of(1961, 4, 12, 1, 1);


  }


  public static class DateConstant {

    public static final Date START_UNIX_EPOCH_DATE = DateTimeUtils.convertLocalDateTimeToDate(LocalDateTimeConstant.START_UNIX_EPOCH_DATE_TIME);

    public static final Date GAGARINS_FLY_DAY_DATE = DateTimeUtils.convertLocalDateTimeToDate(LocalDateTimeConstant.GAGARINS_FLY_DAY_DATE_TIME);

  }


}
