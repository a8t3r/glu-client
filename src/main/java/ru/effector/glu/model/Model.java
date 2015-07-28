package ru.effector.glu.model;

import java.util.List;
import java.util.Map;

/**
 * @author Alexandr Kolosov
 * @since 28.07.2015
 */
public class Model {

    public List<ModelEntry> entries;
    public String fabric;
    public String id;
    public Map<String, Object> metadata;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Model{");
        sb.append("fabric='").append(fabric).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
