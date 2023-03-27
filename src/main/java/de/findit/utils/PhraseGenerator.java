package de.findit.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhraseGenerator {

    public static String[] getPhrasesFromQuery(String query) {
        List<String> words = Arrays.asList(query.split(" "));
        // Phrasen der Länge 1
        List<String> phrases = new ArrayList<>(words);
        // Phrasen der Länge 2
        for (int i = 0; i < words.size() - 1; i++) {
            phrases.add(words.get(i) + " " + words.get(i + 1));
        }
        // Phrasen der Länge 3
        for (int i = 0; i < words.size() - 2; i++) {
            phrases.add(words.get(i) + " " + words.get(i + 1) + " " + words.get(i + 2));
        }
        // Phrasen der Länge 4
        for (int i = 0; i < words.size() - 3; i++) {
            phrases.add(words.get(i) + " " + words.get(i + 1) + " " + words.get(i + 2) + " " + words.get(i + 3));
        }
        // Phrasen der Länge 5
        for (int i = 0; i < words.size() - 4; i++) {
            phrases.add(words.get(i) + " " + words.get(i + 1) + " " + words.get(i + 2) + " " + words.get(i + 3) + " " + words.get(i + 4));
        }
        // Konvertieren einer arraylist in einen string array und diese zurückgeben
        return phrases.toArray(new String[0]);
    }

}
