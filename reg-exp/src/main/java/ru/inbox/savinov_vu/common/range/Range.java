package ru.inbox.savinov_vu.common.range;

import java.time.temporal.Temporal;

import static java.util.Objects.nonNull;



public interface Range<T extends Temporal> {

    default boolean isPresent() {
        return nonNull(getStart()) && nonNull(getEnd());
    }

    boolean isRange();

    T getStart();

    T getEnd();


    default boolean intersectionWithAnotherRange(Range<T> anotherRange) {
        return isInsideAnotherRange(anotherRange)
            || isContainPoint(anotherRange.getStart())
            || isContainPoint(anotherRange.getEnd());
    }


    boolean isBeforeAnotherRange(Range<T> anotherRange);

    boolean isAfterAnotherRange(Range<T> anotherRange);

    boolean isInsideAnotherRange(Range<T> anotherRange);

    boolean isContainAnotherRange(Range<T> anotherRange);

    boolean isContainPoint(T point);


}
