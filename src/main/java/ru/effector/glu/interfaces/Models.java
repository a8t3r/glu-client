package ru.effector.glu.interfaces;

import ru.effector.glu.model.Delta;
import ru.effector.glu.model.Model;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author Alexandr Kolosov
 * @since 28.07.2015
 */
@Path("/model")
public interface Models {

    @POST
    @Path("/static")
    String putStaticModel(Model model);

    @GET
    @Path("/static")
    Model getStaticModel();

    @GET
    @Path("/live")
    Model getLiveModel();

    @GET
    @Path("/delta")
    Delta getDelta();

}
