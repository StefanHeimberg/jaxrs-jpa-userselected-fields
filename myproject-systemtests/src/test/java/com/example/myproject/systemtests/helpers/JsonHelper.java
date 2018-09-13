package com.example.myproject.systemtests.helpers;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
public final class JsonHelper {

    public static JsonObject entityAsJsonObject(final String entity) {
        return Json.createReader(new StringReader(entity)).readObject();
    }

    public static JsonArray entityAsJsonArray(final String entity) {
        return Json.createReader(new StringReader(entity)).readArray();
    }

    private JsonHelper() {
    }

}
