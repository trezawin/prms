package sg.edu.nus.iss.phoenix.user.service.restful;


import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.RecordExistsException;
import sg.edu.nus.iss.phoenix.radioprogram.service.ProgramService;
import sg.edu.nus.iss.phoenix.user.service.UserService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kmb
 */
@Path("users")
@RequestScoped
public class UserRESTService {
    
    private UserService service;
    
    public UserRESTService(){
        this.service = new UserService();
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers(){
        try {
            return this.service.findAllUsers();
        } catch (Exception e) {
            return new ArrayList<User>();
        }
    }
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createUser(User user){
        try {
            this.service.processCreate(user);
            return "true";
        } catch(RecordExistsException e){
            return "existing_user";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }
    
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateUser(User user){
        try {
            this.service.processModify(user);
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }
    
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{userId}")
    public String deleteUser(@PathParam("userId") String userId){
        try {
            this.service.delete(userId);
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }
}
