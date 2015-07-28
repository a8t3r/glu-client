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
        return "ModelEntry{" + "mountPoint='" + mountPoint + '\'' + '}';
    }
}
