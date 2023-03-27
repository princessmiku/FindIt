package de.findit.core;

import de.findit.fileds.CustomField;
import de.findit.filter.CategoryFilter;
import de.findit.filter.Filter;
import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;


public class Record {

    private final String id;
    private final ArrayList<CustomField> customFields = new ArrayList<>();
    private final ArrayList<String> tags = new ArrayList<>();
    private final ArrayList<Filter> filters = new ArrayList<>();
    private final ArrayList<CategoryFilter> categories = new ArrayList<>();
    private final LevenshteinDistance ld = new LevenshteinDistance();
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
            System.out.println(customField.getValue());
            double queryRatio = jwd.apply(query, customField.getValue());
            ArrayList<Double> phrasesRatioArray = new ArrayList<>();
            for (String phrase: phrases) {
                System.out.println(phrase);
                if (findSubstring(phrase, customField.getValue())) {
                    System.out.println("  FOUND");
                    phrasesRatioArray.add(1.5d);
                }
                //System.out.println(phrase);
                //Double percent = jwd.apply(query, customField.getValue()) * countSpaces(phrase);
                //System.out.println(percent);
                //phrasesRatioArray.add(percent);
            }
            System.out.println("....");
            double phrasesRatio = 0d;
            for (Double phRa: phrasesRatioArray) {
                phrasesRatio += phRa;
            }
            phrasesRatio = phrasesRatio / phrasesRatioArray.size();
            totalRatio += queryRatio + phrasesRatio;
        }
        return totalRatio;
    }
    public int countSpaces(String string) {
        int spaceCount = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ' ') {
                spaceCount++;
            }
        }
        return spaceCount;
    }

    private static boolean isPrefix(String pattern, int p) {
        int m = pattern.length();
        for (int i = p, j = 0; i < m; i++, j++) {
            if (pattern.charAt(i) != pattern.charAt(j)) {
                return false;
            }
        }
        return true;
    }
    public static ArrayList<Integer> search(String pattern, String text) {
        ArrayList<Integer> indices = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        ArrayList<Integer> badChar = new ArrayList<>(Collections.nCopies(256, -1));
        ArrayList<Integer> goodSuffix = new ArrayList<>(Collections.nCopies(m + 1, 0));
        for (int i = 0; i < m; i++) {
            badChar.set(pattern.charAt(i), i);
        }
        ArrayList<Integer> suffix = new ArrayList<>(Collections.nCopies(m, 0));
        int lastPrefixIndex = m;
        for (int i = m - 1; i >= 0; i--) {
            if (isPrefix(pattern, i + 1)) {
                lastPrefixIndex = i + 1;
            }
            suffix.set(i, lastPrefixIndex - i + m - 1);
        }
        for (int i = 0; i < m - 1; i++) {
            int j = suffix.get(i + 1) - m + 1 + i;
            if (!isPrefix(pattern, j)) {
                suffix.set(i + 1, j - i);
            }
        }
        int j = 0;
        while (j <= n - m) {
            int i;
            for (i = m - 1; i >= 0 && pattern.charAt(i) == text.charAt(i + j); i--);
            // Überprüfung, ob i negativ ist, sonst gibt es kein Match
            if (i < 0) {
                indices.add(j);
                j += goodSuffix.get(m);
            } else {
                int badCharShift = i - badChar.get(text.charAt(i + j));
                int goodSuffixShift = 0;
                if (i < m - 1) {
                    goodSuffixShift = goodSuffix.get(i + 1);
                } else {
                    goodSuffixShift = 1;
                }
                j += Math.max(badCharShift, goodSuffixShift);
            }

            // Abbruchbedingung, wenn j größer oder gleich der Länge des Textes minus die Länge des Patterns ist
            if (j >= n - m) {
                break;
            }
        }

        return indices;
    }

    public static boolean findSubstring(String pattern, String text) {
        return !search(pattern, text).isEmpty();
    }

    public CustomField getCustomFieldByName(String name) {
        for (CustomField customField: customFields) {
            if (customField.getFieldName().equalsIgnoreCase(name)) {
                return customField;
            }
        }
        return new CustomField("ERROR", "ERROR");
    }
    public String getId() {
        return id;
    }
}
