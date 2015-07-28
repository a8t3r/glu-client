package ru.effector.glu.interfaces;

import feign.Response;
import ru.effector.glu.model.Order;
import ru.effector.glu.model.PlanAction;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * @author Alexandr Kolosov
 * @since 28.07.2015
 */
@Path("/plans")
public interface Plans {

    @POST
    Response putPlan(
            @QueryParam("planAction") PlanAction planAction,
            @QueryParam("systemFilter") String systemFilter,
            @QueryParam("order") Order order);

}
