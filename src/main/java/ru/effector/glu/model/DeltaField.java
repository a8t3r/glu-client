package ru.effector.glu.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.effector.glu.utils.DeltaFieldJsonDeserializer;

import java.util.Objects;

/**
 * @author Alexandr Kolosov
 * @since 30.07.2015
 */
@JsonDeserialize(using = DeltaFieldJsonDeserializer.class)
public class DeltaField {
    public String currentValue;
    public String expectedValue;

    public boolean invalid() {
        return !Objects.equals(currentValue,  expectedValue);
    }
}
