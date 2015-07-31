package ru.effector.glu.recovery;

import feign.Response;
import ru.effector.glu.GluClient;
import ru.effector.glu.interfaces.Executions;
import ru.effector.glu.interfaces.Models;
import ru.effector.glu.model.*;
import ru.effector.glu.utils.ExecutionUtils;
import ru.effector.glu.utils.ResponseUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Alexandr Kolosov
 * @since 30.07.2015
 */
public class DeltaUtils {

    private GluClient client;
    private RecoveryStrategy strategy;

    public DeltaUtils(GluClient client, RecoveryStrategy strategy) {
        this.client = client;
        this.strategy = strategy;
    }

    public void repairModel() {
        Models models = client.models();
        Delta delta = models.getDelta(true);
        Model staticModel = models.getStaticModel();
        Map<String, ModelEntry> index = staticModel.entries.stream()
                .collect(Collectors.toMap(this::agentKey, Function.identity()));

        List<Plan> requiredPlans = new ArrayList<>();
        for (DeltaEntry entry : delta.delta.values()) {
            if (entry.entryState.invalid()) {
                requiredPlans.add(buildPlan(index, entry));
            }
        }

        if (!requiredPlans.isEmpty() && !hasActiveDeployments()) {
            staticModel.id = null;
            Response response = models.putStaticModel(staticModel);
            if (response.status() == 201) {
                String modelId = ResponseUtils.asString(response);
                response = models.putStaticModel(modelId);
            }

            if (response.status() / 100 == 2) {
                performPlans(requiredPlans);
            }
        }
    }

    private Plan buildPlan(Map<String, ModelEntry> index, DeltaEntry entry) {
        switch (entry.statusInfo) {
            case "should NOT be deployed":
                return buildPlan(PlanAction.undeploy, entry.key.currentValue);
            case "NOT deployed":
                ModelEntry modelEntry = index.get(entry.agent.expectedValue);
                if (modelEntry != null) {
                    modelEntry.agent = strategy.getRecoveryAgentForEntry(modelEntry);
                }

                return buildPlan(PlanAction.deploy, entry.key.expectedValue);

            default:
                if ("notExpectedState".equals(entry.status)) {
                    return buildPlan(PlanAction.redeploy, entry.key.expectedValue);
                }

                throw new IllegalStateException("?!");
        }
    }

    private Plan buildPlan(PlanAction a, String keyValue) {
        Plan deployPlan = new Plan();
        deployPlan.planAction = a;
        deployPlan.systemFilter = "key='" + keyValue + "'";
        return deployPlan;
    }

    private void performPlans(List<Plan> requiredPlans) {
        for (Plan plan : requiredPlans) {
            Response planResponse = client.plans().putPlan(plan.planAction, plan.systemFilter, plan.order);
            String planId = ResponseUtils.asString(planResponse);
            if (planId != null && !planId.isEmpty()) {
                Executions executions = client.executions();
                String executionId = ResponseUtils.asString(executions.putExecution(planId));

                ExecutionUtils executionUtils = new ExecutionUtils(executions);
                executionUtils.awaitCompletion(planId, executionId);
            }
        }
    }

    private String agentKey(ModelEntry e) {
        return agentKey(e.agent, e.mountPoint);
    }

    private String agentKey(String agent, String mountPoint) {
        return agent + ":" + mountPoint;
    }

    private boolean hasActiveDeployments() {
        Map<String, Deployment> deployments = client.deployments().getCurrentDeployments();
        return deployments != null && !deployments
                .values().stream()
                .filter(d -> (!"COMPLETED".equals(d.status) && !"FAILED".equals(d.status)))
                .collect(Collectors.toSet())
                .isEmpty();
    }
}
