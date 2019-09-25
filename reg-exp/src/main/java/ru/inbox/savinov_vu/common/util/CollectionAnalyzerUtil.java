package ru.inbox.savinov_vu.common.util;

import lombok.Data;
import ru.inbox.savinov_vu.common.interfaces.entityInterfaces.Identify;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;



public class CollectionAnalyzerUtil {

    public static CollectionAnalyzerReport process(Collection<? extends Identify> first, Collection<? extends Identify> second) {

        if (isNull(first)) {
            first = new ArrayList<>();
        }
        if (isNull(second)) {
            second = new ArrayList<>();
        }

        Map<Integer, ? extends Identify> firstMap = getIdEnitytyMap(first);
        Map<Integer, ? extends Identify> secondMap = getIdEnitytyMap(second);


        CollectionAnalyzerReport collectionAnalyzerReport = new CollectionAnalyzerReport();

        List<? extends Identify> added = second.stream().filter(v -> !firstMap.containsKey(v.getId())).collect(Collectors.toList());

        List<? extends Identify> bothContained = first.stream().filter(v -> secondMap.containsKey(v.getId())).collect(Collectors.toList());

        List<? extends Identify> removed = first.stream().filter(v -> !secondMap.containsKey(v.getId())).collect(Collectors.toList());


        collectionAnalyzerReport.setFirstMap(firstMap);
        collectionAnalyzerReport.setSecondMap(secondMap);
        collectionAnalyzerReport.setAdded(added);
        collectionAnalyzerReport.setBothContained(bothContained);
        collectionAnalyzerReport.setRemoved(removed);

        return collectionAnalyzerReport;
    }


    /**
     * get identity for element of collection
     * if element id = null, then return negative value
     *
     * @param identifyColletion
     * @return
     */
    private static Map<Integer, ? extends Identify> getIdEnitytyMap(Collection<? extends Identify> identifyColletion) {
        AtomicInteger nullCounter = new AtomicInteger(0);
        return identifyColletion.stream().map(o -> {
            if (o.getId() == null) {
                o.setId(nullCounter.decrementAndGet());
            }
            return o;
        }).collect(Collectors.toMap(Identify::getId, v -> v));
    }


    @Data
    public static class CollectionAnalyzerReport {

        private CollectionAnalyzerReport() {
        }


        private Map<Integer, ? extends Identify> firstMap;

        private Map<Integer, ? extends Identify> secondMap;

        private List added;

        private List bothContained;

        private List removed;
    }

}
