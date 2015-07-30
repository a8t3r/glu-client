package ru.effector.glu.interfaces;

import feign.Response;
import ru.effector.glu.model.Deployment;

import javax.ws.rs.*;
import java.util.Map;

/**
 * @author Alexandr Kolosov
 * @since 28.07.2015
 */
public interface Deployments {

    @GET
    @Path("/deployments/current")
    Map<String, Deployment> getCurrentDeployments();

    @GET
    @Path("/deployments/archived")
    Map<String, Deployment> getArchivedDeployments(
            @QueryParam("offset") Integer offset,
            @QueryParam("max") Integer max,
            @QueryParam("order") String order,
            @QueryParam("sort") String sort);

    @DELETE
    @Path("/deployments/current")
    Map<String, Deployment> archiveCurrentDeployments();

    @GET
    @Path("/deployment/current/{deploymentId}")
    Response getCurrentDeployment(@PathParam("deploymentId") String deploymentId);

    @GET
    @Path("/deployment/archived/{deploymentId}")
    Response getArchivedDeployment(@PathParam("deploymentId") String deploymentId);

    @DELETE
    @Path("/deployment/current/{deploymentId}")
    void deleteDeployment(@PathParam("deploymentId") String deploymentId);

}
