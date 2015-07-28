package ru.effector.glu.interfaces;

import feign.Response;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author Alexandr Kolosov
 * @since 28.07.2015
 */
public interface Deployments {

    @GET
    @Path("/deployments/current")
    Response getCurrentDeployments();

    @GET
    @Path("/deployments/archived")
    Response getArchivedDeployments();

    @DELETE
    @Path("/deployments/current")
    Response archiveCurrentDeployments();

    @GET
    @Path("/deployment/current/{deploymentId}")
    Response getCurrentDeployment(@PathParam("deploymentId") String deploymentId);

    @GET
    @Path("/deployment/archived/{deploymentId}")
    Response getArchivedDeployment(@PathParam("deploymentId") String deploymentId);

    @DELETE
    @Path("/deployment/current/{deploymentId}")
    Response deleteDeployment(@PathParam("deploymentId") String deploymentId);

}
