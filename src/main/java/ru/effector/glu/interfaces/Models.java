package ru.effector.glu.interfaces;

import ru.effector.glu.model.Model;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Alexandr Kolosov
 * @since 28.07.2015
 */
@Path("/model")
public interface Models {

    @GET
    @Path("/static")
    Model getStaticModel();

    @GET
    @Path("/live")
    Model getLiveModel();

    @GET
    @Path("/delta")
    Model getDelta();

}
