/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.scheduleprogram.restful.service;

import java.util.ArrayList;
import java.util.Date;
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
import sg.edu.nus.iss.phoenix.core.exceptions.DuplicateProgramSlot;
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
    @Path("/all/{startTimeStamp}/{endTimeStamp}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProgramSlot> retrieveAll(@PathParam("startTimeStamp") long startTimeStamp, @PathParam("endTimeStamp") long endTimeStamp) throws Exception {
        try {
            return service.retrieveAll(startTimeStamp, endTimeStamp);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<ProgramSlot>();
        }
    }
    
    /**
     * POST method for updating or creating an instance of resource
     * @param content representation for the resource
     */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String update(ProgramSlot ps) {
        try {
            ps.setDateOfProgram(new Date(ps.getDateOfProgramTimestamp()));
            ps.setDuration(new Date(ps.getDurationTimestamp()));
            service.update(ps);
            return "true";
        } catch (DuplicateProgramSlot e){
            return "duplicate";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }
    
    /**
     * POST method for creating an instance of resource
     * @param content representation for the resource
     */
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createRadioProgram(ProgramSlot ps) {
        try {
            ps.setDateOfProgram(new Date(ps.getDateOfProgramTimestamp()));
            ps.setDuration(new Date(ps.getDurationTimestamp()));
            service.create(ps);
            return "true";
        } catch (DuplicateProgramSlot e){
            return "duplicate";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }
        
    /**
     * DELETE method for deleting an instance of resource
     * @param name name of the resource
     */
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteRadioProgram(@PathParam("id") int id) {
        try {
            service.delete(id);
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }
}
