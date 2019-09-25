package ru.inbox.savinov_vu.common.range;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Collection;



@NoArgsConstructor
@Data
public class RangeCollection {

    private Collection<LocalTimeRange> ranges;


    public RangeCollection(Collection<LocalTimeRange> range) {
        this.ranges = range;
    }


    public boolean contains(LocalTime localTime) {
        boolean result = ranges.stream().anyMatch(v -> v.isContainPoint(localTime));
        return result;
    }


    public int containsCount(LocalTime localTime) {
        long count = ranges.stream().filter(v -> v.isContainPoint(localTime)).count();
        return (int) count;
    }

    public int size() {
        return ranges.size();
    }

}
