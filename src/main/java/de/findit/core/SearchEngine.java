package de.findit.core;

import java.util.ArrayList;

import static de.findit.utils.PhraseGenerator.getPhrasesFromQuery;

public class SearchEngine {

    private final ArrayList<Record> records = new ArrayList<>();
    public SearchEngine() {
    }

    /**
     * Füge ein ergebnis der suchmaschine hinzu
     * @param record ein ergebnis objekt
     */
    public void addRecord(Record record) {
        if (records.contains(record))
            return;
        records.add(record);
    }

    /**
     * Führe eine suche, ohne filter, aus
     * @param query einzugebender such text
     * @return eine Klasse mit allen gefundenen ergebnissen und ihrer wichtigkeit
     */
    public Matching search(String query) {
        query = query.strip().toLowerCase();
        String[] phrases = getPhrasesFromQuery(query);
        Matching matching = new Matching();
        for (Record record: records) {
            double ratio = record.match(query, phrases);
            if (ratio >= 0.5f)
                matching.addRecord(record, ratio);
        }
        return matching;
    }

    //public Matching filterSearch(String query) {
    //    Matching matching = new Matching();
    //    return matching;
    //}
}
