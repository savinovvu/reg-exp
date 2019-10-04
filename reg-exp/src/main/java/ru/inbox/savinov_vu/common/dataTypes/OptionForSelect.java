package ru.inbox.savinov_vu.common.dataTypes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.inbox.savinov_vu.common.interfaces.enumInterfaces.DataEnum;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



@Getter
@EqualsAndHashCode
public class OptionForSelect {

    private final String value;

    private final String label;

    private final Map<String, String> attributes;


    public OptionForSelect(Integer value, String label) {
        this(String.valueOf(value), label, Collections.emptyMap());
    }


    public OptionForSelect(BigDecimal value, String label) {
        this(String.valueOf(value), label, Collections.emptyMap());
    }


    public OptionForSelect(String value, String label) {
        this(value, label, Collections.emptyMap());
    }


    public OptionForSelect(String value) {
        this(value, value);
    }


    public OptionForSelect(String value, String label, Map<String, String> attributes) {
        this.value = value;
        this.label = label;
        this.attributes = attributes;
    }


    public static List<OptionForSelect> of(List<? extends DataEnum> enums) {
        List<OptionForSelect> result = enums.stream().map(OptionForSelect::of).collect(Collectors.toList());
        return result;
    }


    public static OptionForSelect of(DataEnum dataEnum) {
        OptionForSelect result = new OptionForSelect(String.valueOf(dataEnum.getValue()), dataEnum.getRuLabel());
        return result;
    }


}
