package ru.inbox.savinov_vu.common.range;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.inbox.savinov_vu.common.util.DateTimeUtils;

import java.time.LocalDate;
import java.util.Objects;



@AllArgsConstructor
@Getter
@Setter
public class LocalDateRange implements Range<LocalDate> {

  private LocalDate start;

  private LocalDate end;


  @Override
  public boolean isRange() {
    return isPresent() && start.isBefore(end);
  }


  @Override
  public boolean isBeforeAnotherRange(Range<LocalDate> anotherRange) {
    return isRange() && anotherRange.isRange()
      && end.isBefore(anotherRange.getStart());
  }


  @Override
  public boolean isAfterAnotherRange(Range<LocalDate> anotherRange) {
    return isRange() && anotherRange.isRange()
      && start.isAfter(anotherRange.getEnd());
  }


  @Override
  public boolean isInsideAnotherRange(Range<LocalDate> anotherRange) {
    return isRange() && anotherRange.isRange()
      && DateTimeUtils.isAfterOrEquals(start, anotherRange.getStart())
      && DateTimeUtils.isBeforeOrEquals(end, anotherRange.getEnd());
  }


  @Override
  public boolean isContainAnotherRange(Range<LocalDate> anotherRange) {
    return isRange() && anotherRange.isRange()
      && DateTimeUtils.isBeforeOrEquals(start, anotherRange.getStart())
      && DateTimeUtils.isAfterOrEquals(end, anotherRange.getEnd());
  }


  @Override
  public boolean isContainPoint(LocalDate point) {
    return isRange()
      && DateTimeUtils.isBeforeOrEquals(start, point)
      && DateTimeUtils.isAfterOrEquals(end, point);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    LocalDateRange that = (LocalDateRange) o;
    return Objects.equals(start, that.start) &&
      Objects.equals(end, that.end);
  }


  @Override
  public int hashCode() {
    return Objects.hash(start, end);
  }
}
