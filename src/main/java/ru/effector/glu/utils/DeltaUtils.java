package ru.effector.glu.utils;

import feign.Response;
import ru.effector.glu.GluClient;
import ru.effector.glu.interfaces.Executions;
import ru.effector.glu.interfaces.Models;
import ru.effector.glu.model.*;

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
    private String failoverAgent;

    public DeltaUtils(GluClient client, String failoverAgent) {
        this.client = client;
        this.failoverAgent = failoverAgent;
    }

    public void repaireModel() {
        Models models = client.models();
        Delta delta = models.getDelta(true);
        Model staticModel = models.getStaticModel();
        Map<String, ModelEntry> index = staticModel.entries.stream()
                .collect(Collectors.toMap(this::agentKey, Function.identity()));

        List<Plan> requiredPlans = new ArrayList<>();

        for (DeltaEntry entry : delta.delta.values()) {
            if (entry.entryState.invalid()) {
                switch (entry.statusInfo) {
                    case "should NOT be deployed":
                        Plan undeployPlan = new Plan();
                        undeployPlan.planAction = PlanAction.undeploy;
                        undeployPlan.systemFilter = "key='" + entry.key.currentValue + "'";

                        requiredPlans.add(undeployPlan);
                        break;
                    case "NOT deployed":
                        ModelEntry modelEntry = index.get(entry.agent.expectedValue);
                        if (modelEntry != null) {
                            modelEntry.agent = failoverAgent;
                        }

                        Plan deployPlan = new Plan();
                        deployPlan.planAction = PlanAction.deploy;
                        deployPlan.systemFilter = "key='" + entry.key.expectedValue;
                        requiredPlans.add(deployPlan);

                    default:
                        // TODO:
                }
            }
        }

        if (!hasActiveDeployments()) {
            staticModel.id = null;
            Response response = models.putStaticModel(staticModel);
            if (response.status() == 201) {
                String modelId = ResponseUtils.asString(response);
                response = models.putStaticModel(modelId);
            }

            if (response.status() / 100 == 2) {
                for (Plan plan : requiredPlans) {
                    Response planResponse = client.plans().putPlan(plan.planAction, plan.systemFilter, plan.order);
                    String planId = ResponseUtils.asString(planResponse);

                    Executions executions = client.executions();
                    String executionId = ResponseUtils.asString(executions.putExecution(planId));

                    ExecutionUtils executionUtils = new ExecutionUtils(executions);
                    executionUtils.awaitCompletion(planId, executionId);
                }
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
                .filter(d -> !"COMPLETED".equals(d.status))
                .collect(Collectors.toSet())
                .isEmpty();
    }
}
