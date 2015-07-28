package ru.effector.glu.model;

import java.util.List;
import java.util.Map;

/**
 * @author Alexandr Kolosov
 * @since 28.07.2015
 */
public class ModelEntry {

    public String agent;
    public Map<String, String> initParameters;
    public Map<String, Object> metadata;
    public String mountPoint;
    public String script;
    public List<String> tags;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ModelEntry{");
        sb.append("mountPoint='").append(mountPoint).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
