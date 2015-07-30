package ru.effector.glu.model;

import java.util.List;

/**
 * @author Alexandr Kolosov
 * @since 28.07.2015
 */
public class DeltaEntry extends ModelEntry {

    public DeltaField entryState;
    public String state;
    public String status;
    public String statusInfo;
    public List<String> errorValueKeys;
    public String key;

}
