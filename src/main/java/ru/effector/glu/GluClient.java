package ru.effector.glu;

import feign.Feign;
import feign.Target;
import ru.effector.glu.interfaces.Agents;
import ru.effector.glu.interfaces.Models;

/**
 * @author Alexandr Kolosov
 * @since 28.07.2015
 */
public class GluClient {

    private final String username;
    private final String password;
    private final String serverUrl;

    public GluClient(String username, String password, String serverUrl) {
        this.username = username;
        this.password = password;
        this.serverUrl = serverUrl;
    }

    public Models models() {
        return build(Models.class);
    }

    public Agents agents() {
        return build(Agents.class);
    }

    public  <T> T build(Class<T> serviceInterface) {
        Feign builder = FeignBuilder.getFeign(username, password);
        return builder.newInstance(new Target.HardCodedTarget<T>(serviceInterface, serverUrl));
    }
}
