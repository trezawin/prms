/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.scheduleprogram.restful.service;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import sg.edu.nus.iss.phoenix.scheduleprogram.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.scheduleprogram.service.ScheduleService;

/**
 *
 * @author treza
 */
@Path("schedule")
@RequestScoped
public class ScheduleRESTService {

    private ScheduleService service;

    /**
     * Creates a new instance of ScheduleRESTService
     */
    public ScheduleRESTService() {
        service = new ScheduleService();
    }

    /**
     * Retrieves all program slot.
     * @return an instance of resource
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProgramSlot> retrieveAll() {
        return service.retrieveAll();
    }
    
    /**
     * POST method for updating or creating an instance of resource
     * @param content representation for the resource
     */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(ProgramSlot ps) {
        service.update(ps);
    }
    
    /**
     * POST method for creating an instance of resource
     * @param content representation for the resource
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createRadioProgram(ProgramSlot ps) {
        service.create(ps);
    }
        
    /**
     * DELETE method for deleting an instance of resource
     * @param name name of the resource
     */
    @DELETE
    @Path("/delete/{psid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteRadioProgram(@PathParam("psid") Long id) {
        service.delete(id);
    }
}
