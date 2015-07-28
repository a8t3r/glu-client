package ru.effector.glu.interfaces;

import feign.Response;
import ru.effector.glu.model.Order;
import ru.effector.glu.model.PlanAction;

import javax.ws.rs.*;

/**
 * @author Alexandr Kolosov
 * @since 28.07.2015
 */
@Path("/plans")
public interface Plans {

    @GET
    Response getPlans();

    @GET
    @Path("/{plan_id}")
    Response getPlan(@PathParam("plan_id") String planId);

    @POST
    Response putPlan(
            @QueryParam("planAction") PlanAction planAction,
            @QueryParam("systemFilter") String systemFilter,
            @QueryParam("order") Order order);

}
