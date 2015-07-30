package ru.effector.glu.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ru.effector.glu.model.DeltaField;

import java.io.IOException;

/**
 * @author Alexandr Kolosov
 * @since 30.07.2015
 */
public class DeltaFieldJsonDeserializer extends JsonDeserializer<DeltaField> {
    @Override
    public DeltaField deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        DeltaField field = new DeltaField();
        JsonToken currentToken = p.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            String fieldName;
            while (p.getCurrentToken() != JsonToken.END_OBJECT && (fieldName = p.nextFieldName()) != null) {
                if ("ev".equals(fieldName)) {
                    field.expectedValue = p.nextTextValue();
                } else {
                    field.currentValue = p.nextTextValue();
                }
            }
        } else {
            field.expectedValue = p.getValueAsString();
            field.currentValue = field.expectedValue;
        }

        return field;
    }
}
