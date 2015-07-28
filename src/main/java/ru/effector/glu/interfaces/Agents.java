package ru.effector.glu.interfaces;

import ru.effector.glu.model.Agent;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Map;

/**
 * @author Alexandr Kolosov
 * @since 28.07.2015
 */
@Path("/agents")
public interface Agents {

    @GET
    Map<String, Agent> getAgents();

    @GET
    @Path("/versions")
    Map<String, String> getAgentVersions();

}
