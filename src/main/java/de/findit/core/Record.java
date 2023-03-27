package de.findit.core;

import de.findit.fileds.CustomField;
import de.findit.filter.CategoryFilter;
import de.findit.filter.Filter;
import org.apache.commons.text.similarity.JaroWinklerDistance;

import java.util.ArrayList;
import java.util.HashMap;


public class Record {

    private final String id;
    private final ArrayList<CustomField> customFields = new ArrayList<>();
    private final ArrayList<String> tags = new ArrayList<>();
    private final ArrayList<Filter> filters = new ArrayList<>();
    private final ArrayList<CategoryFilter> categories = new ArrayList<>();
    private final JaroWinklerDistance jwd = new JaroWinklerDistance();

    public Record(String id) {
        this.id = id;
    }
    public Record(String id, ArrayList<CustomField> customFields) {
        this.id = id;
        this.customFields.addAll(customFields);
    }

    public void addCustomField(CustomField customField) {
        this.customFields.add(customField);
    }
    public double match(String query, String[] phrases) {
        double totalRatio = 0d;
        for (CustomField customField: customFields) {
            double queryRatio = jwd.apply(query, customField.getValue());
            if (queryRatio >= customField.getImportantPercent()) {
                queryRatio *= customField.getImportantPercentMultiple();
            }
            ArrayList<Double> phrasesRatioArray = new ArrayList<>();
            for (String phrase: phrases) {
                phrasesRatioArray.add(jwd.apply(query, customField.getValue()) * phrase.length());
            }
            double phrasesRatio = 0d;
            for (Double phRa: phrasesRatioArray) {

            }
        }
        return totalRatio;
    }
    private boolean findSubstring(String pattern, String text) {
        double ratio = jwd.apply(pattern, text);
        double threshold = 0.5f + ((text.length() - pattern.length()) / (10.0f * text.length()));
        return ratio >= threshold;
    }
    public String getId() {
        return id;
    }
}
