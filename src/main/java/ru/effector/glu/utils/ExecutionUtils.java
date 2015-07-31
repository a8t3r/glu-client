package ru.effector.glu.utils;

import feign.Response;
import ru.effector.glu.interfaces.Executions;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Alexandr Kolosov
 * @since 28.07.2015
 */
public class ExecutionUtils {

    private final Executions executions;
    public ExecutionUtils(Executions executions) {
        this.executions = executions;
    }

    public Response awaitCompletion(String planId, String executionId) {
        return awaitCompletion(planId, executionId, 10);
    }

    public Response awaitCompletion(String planId, String executionId, int tries) {
        Response response = executions.getExecutionStatus(planId, executionId);
        Collection<String> collection = response.headers().getOrDefault("X-glu-completion", Collections.singleton("100:COMPLETED"));
        String header = collection.iterator().next();
        if ("100:COMPLETED".equals(header) || tries < 0) {
            return response;
        }

        if (header.contains("FAILED")) {
            throw new IllegalStateException("failed state for execution " + executionId);
        }

        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(10));
        return awaitCompletion(planId, executionId, tries - 1);
    }
}
