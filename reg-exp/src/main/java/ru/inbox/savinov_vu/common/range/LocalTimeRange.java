package ru.inbox.savinov_vu.common.range;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.inbox.savinov_vu.common.util.DateTimeUtils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



@AllArgsConstructor
@Getter
@Setter
public class LocalTimeRange implements Range<LocalTime> {

    private LocalTime start;

    private LocalTime end;


    @Override
    public boolean isRange() {
        return isPresent() && start.isBefore(end);
    }


    @Override
    public boolean isBeforeAnotherRange(Range<LocalTime> anotherRange) {
        return isRange() && anotherRange.isRange()
            && end.isBefore(anotherRange.getStart());
    }


    @Override
    public boolean isAfterAnotherRange(Range<LocalTime> anotherRange) {
        return isRange() && anotherRange.isRange()
            && start.isAfter(anotherRange.getEnd());
    }


    @Override
    public boolean isInsideAnotherRange(Range<LocalTime> anotherRange) {
        return isRange() && anotherRange.isRange()
            && DateTimeUtils.isAfterOrEquals(start, anotherRange.getStart())
            && DateTimeUtils.isBeforeOrEquals(end, anotherRange.getEnd());
    }


    @Override
    public boolean isContainAnotherRange(Range<LocalTime> anotherRange) {
        return isRange() && anotherRange.isRange()
            && DateTimeUtils.isBeforeOrEquals(start, anotherRange.getStart())
            && DateTimeUtils.isAfterOrEquals(end, anotherRange.getEnd());
    }


    @Override
    public boolean isContainPoint(LocalTime point) {
        return isRange()
            && DateTimeUtils.isBeforeOrEquals(start, point)
            && DateTimeUtils.isAfterOrEquals(end, point);
    }


    public List<LocalTime> getTimeEveryHalfHourFromStartHourEvery() {
        int min = 30;
        LocalTime startPoint = findStartPoint(min);
        LocalTime endPoint = findEndPoint(min);
        List<LocalTime> times = findLocalTimesPoints(startPoint, endPoint);
        return times;
    }


    private List<LocalTime> findLocalTimesPoints(LocalTime next, LocalTime endPoint) {
        if (next.equals(endPoint)) {
            return new ArrayList();
        }

        List<LocalTime> times = new ArrayList<>();
        while (true) {
            if (endPoint.equals(next)) {
                times.add(next);
                break;
            }
            times.add(next);
            next = next.plusMinutes(30);
        }
        return times;
    }


    private LocalTime findStartPoint(int min) {
        int minute = start.getMinute();
        int delta = minute % min;
        if (delta == 0) {
            return start;
        }
        int appendMin = min - delta;
        LocalTime localTime = start.plusMinutes(appendMin);
        return localTime;
    }


    private LocalTime findEndPoint(int min) {
        int minute = end.getMinute();
        int delta = minute % min;
        int appendMin = 0 - delta;
        LocalTime localTime = end.plusMinutes(appendMin);
        return localTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalTimeRange that = (LocalTimeRange) o;
        return Objects.equals(start, that.start) &&
            Objects.equals(end, that.end);
    }


    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }


    public static LocalTimeRange of(LocalTime startTime, int plusDays, int plusHours, int plusMinutes) {
        if (plusDays > 0) {
            return new LocalTimeRange(startTime, LocalTime.MAX);
        }
        int hour = startTime.getHour();
        int minutes = startTime.getMinute();
        int additionHours = (minutes + plusMinutes) / 60;
        int hourRange = hour + plusHours + additionHours;

        if (hourRange >= 24) {
            return new LocalTimeRange(startTime, LocalTime.MAX);
        }

        LocalTime endTime = startTime.plusHours(plusHours).plusMinutes(plusMinutes);
        return new LocalTimeRange(startTime, endTime);
    }

    public static LocalTimeRange of(LocalTime startTime, int plusDays, int plusHours, int plusMinutes, Boolean allDay) {
        if (plusDays > 0) {
            return new LocalTimeRange(startTime, LocalTime.MAX);
        }

        if (allDay) {
            return new LocalTimeRange(LocalTime.MIN, LocalTime.MAX);
        }

        return of(startTime, plusDays, plusHours, plusMinutes);
    }



    public static LocalTimeRange from(String startTime, String endTime) {
        return new LocalTimeRange(LocalTime.parse(startTime), LocalTime.parse(endTime));
    }
}
