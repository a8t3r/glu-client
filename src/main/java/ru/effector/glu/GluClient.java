package ru.effector.glu;

import feign.Feign;
import feign.Target;
import ru.effector.glu.interfaces.*;
import ru.effector.glu.utils.FeignBuilder;

import java.util.Base64;

/**
 * @author Alexandr Kolosov
 * @since 28.07.2015
 */
public class GluClient {

    private final String serverUrl;
    private final String token;

    public GluClient(String username, String password, String serverUrl) {
        String token = username + ":" + password;
        this.token = "Basic " + Base64.getEncoder().encodeToString(token.getBytes());
        this.serverUrl = serverUrl;
    }

    public Deployments deployments() {
        return build(Deployments.class);
    }

    public Executions executions() {
        return build(Executions.class);
    }

    public Models models() {
        return build(Models.class);
    }

    public Plans plans() {
        return build(Plans.class);
    }

    public Agents agents() {
        return build(Agents.class);
    }

    public  <T> T build(Class<T> serviceInterface) {
        Feign builder = FeignBuilder.getFeign(token);
        return builder.newInstance(new Target.HardCodedTarget<T>(serviceInterface, serverUrl));
    }
}
