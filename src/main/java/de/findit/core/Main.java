package de.findit.core;
import de.findit.fileds.CustomField;
import de.findit.fileds.DescriptionField;
import de.findit.fileds.TitleField;
import de.findit.fileds.UrlField;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        File file = null;
        try {
            file = new File(Main.class.getResource("/example-data.json").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // JSON-Datei als FileReader öffnen
        FileReader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // JSON-Objekt erstellen
        JSONArray jsonArray = new JSONArray(new JSONTokener(reader));

        // Suchmaschine erstellen
        SearchEngine engine = new SearchEngine();

        // Daten in die Suchmaschine einfügen
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String title = obj.getString("title");
            String description = obj.getString("description");
            String url = obj.getString("url");
            Record record = new Record("0");
            record.addCustomField(
                    new TitleField(title)
            );
            record.addCustomField(
                    new DescriptionField(description)
            );
            record.addCustomField(
                    new UrlField(url)
            );
            engine.addRecord(record);
        }
        // Suche durchführen
        Matching matching = engine.search("Tesla model s");
        for (Map.Entry<Record, Double> entry: matching.getSortedRecordRatio().entrySet()) {
            System.out.println(
                    "Result " + entry.getKey().getCustomFieldByName("title").getValue() +
                            " | RATIO: " + String.valueOf(entry.getValue())
            );
        }
    }
}