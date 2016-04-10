package vk;

import java.util.HashMap;

/**
 * Created by aleksejpluhin on 07.04.16.
 */
public class Parametrs {
    private final HashMap<String, String> params;

    protected Parametrs() {
        params = new HashMap<>();
    }


    public String build() {
        if (params.isEmpty()) return "";
        final StringBuilder result = new StringBuilder();
        params.keySet().stream().forEach(key -> {
            result.append(key).append('=').append(params.get(key)).append('&');
        });
        return result.toString();
    }

    protected void setParams(String key, String value) {
        params.put(key, value);
    }
}

