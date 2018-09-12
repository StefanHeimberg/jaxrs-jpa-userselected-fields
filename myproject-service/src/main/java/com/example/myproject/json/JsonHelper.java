package com.example.myproject.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonStructure;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

/**
 *
 * @author Stefan Heimberg <kontakt@stefanheimberg.ch>
 */
public final class JsonHelper {

    public static final String toFormattedString(final JsonStructure jsonStructure) {
        final Map<String, Boolean> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);

        final JsonWriterFactory writerFactory = Json.createWriterFactory(config);

        try (final Writer writer = new StringWriter()) {
            writerFactory.createWriter(writer).write(jsonStructure);
            return writer.toString();
        } catch(IOException e) {
            throw new RuntimeException("JsonStructure could not be formatted", e);
        }
    }

    private JsonHelper() {
    }

}
