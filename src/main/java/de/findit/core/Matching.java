package de.findit.core;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Matching {

    private final HashMap<Record, Double> recordRatio = new HashMap<>();

    public Matching() {

    }

    /**
     * Diese funktion ermöglicht ein ergebnis mit einer bestimmten wichtigkeit einzufügen
     * @param record Das ergebnis
     * @param ratio Die wichtigkeit
     */
    public void addRecord(Record record, double ratio) {
        recordRatio.put(record, ratio);
    }

    /**
     *
     * @return liste mit den ergebnissen und ihrer wichtigkeit
     */
    public HashMap<Record, Double> getRecordRatio() {
        return recordRatio;
    }

    /**
     * Sortiere die liste, mit den gefundenen ergebnissen, nach ihrer wichtigkeit.
     */
    public LinkedHashMap<Record, Double> getSortedRecordRatio() {
        LinkedHashMap<Record, Double> collect = recordRatio.entrySet()
                .stream()
                .sorted(Map.Entry.<Record, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));
        return collect;
    }
}
