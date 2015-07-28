package ru.effector.glu.interfaces;

import feign.Response;

import javax.ws.rs.*;

/**
 * @author Alexandr Kolosov
 * @since 28.07.2015
 */
@Path("/plan/{planId}/execution")
public interface Executions {

    @POST
    Response putExecution(@PathParam("planId") String planId);

    @GET
    @Path("/{executionId}")
    String getExecution(
            @PathParam("planId") String planId,
            @PathParam("executionId") String executionId);

    @HEAD
    @Path("/{executionId}")
    Response getExecutionStatus(
            @PathParam("planId") String planId,
            @PathParam("executionId") String executionId);

    @DELETE
    @Path("/{executionId}")
    String deleteExecution(
            @PathParam("planId") String planId,
            @PathParam("executionId") String executionId);

}
