package org.example.features;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class JsonDataProvider {
    private String file = "src/test/java/org/example/features/test_data.json";

    // convert JSON file to map
    Map<?, ?> map;
    public JsonDataProvider() {
        Gson gson = new Gson();
        Reader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(file));
            map = gson.fromJson(reader, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(map);
    }

    public String getValue(String key) {
        return map.get(key).toString();
    }
}
