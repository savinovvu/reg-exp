package ru.inbox.savinov_vu.common.range;

import lombok.Data;
import org.springframework.data.domain.Sort;



@Data
public class IntegerRange {

    private Integer start;

    private Integer end;

    private Sort.Direction direction;


    public IntegerRange(Integer first, Integer second) {
        if (first < second) {
            this.start = first;
            this.end = second;
            this.direction = Sort.Direction.ASC;
        } else {
            this.start = second;
            this.end = first;
            this.direction = Sort.Direction.DESC;
        }
    }

}
