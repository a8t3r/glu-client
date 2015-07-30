package ru.effector.glu.model;

/**
 * @author Alexandr Kolosov
 * @since 30.07.2015
 */
public class Deployment {

    public String description;
    public Long endTime;
    public Long startTime;
    public String status;
    public String username;
    public Integer totalSteps;
    public Integer completedSteps;
    public String viewURL;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Deployment{");
        sb.append("description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
