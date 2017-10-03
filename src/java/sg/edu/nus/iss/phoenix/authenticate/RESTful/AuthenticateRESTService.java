/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.authenticate.RESTful;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.authenticate.service.AuthenticateService;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("/Login")
@RequestScoped
public class AuthenticateRESTService {

    @Context
    private UriInfo context;
    
    private AuthenticateService service; 

    public AuthenticateRESTService() {
            service = new AuthenticateService();
    }

    /**
     * Retrieves representation of an instance of sg.edu.nus.iss.phoenix.authenticate.RESTful.GenericResource
     * @return an instance of java.lang.String
     */
    @GET 
    // Path: http://localhost/<appln-folder-name>/login/dologin
    @Path("/doLogin")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    public AuthInfo doLogin(@QueryParam("username") String uname, 
            @QueryParam("password") String pwd){
        AuthInfo response = new AuthInfo();
        response.setUsername(uname);
        User user = checkCredentials(uname, pwd);
        if(user != null){
            response.setAuthStatus(true);
            String roles = "";
            for (Role role : user.getRoles()) {
                if(!roles.equals(""))
                    roles += ",";
                roles += role.getRole();
            }
            response.setRole(roles);
        }else{
            response.setAuthStatus(false);	
        }
        return response;		
    }
	
    /**
     * Method to check whether the entered credential is valid
     * 
     * @param uname
     * @param pwd
     * @return
     */
    private User checkCredentials(String uname, String pwd){
        System.out.println("Inside checkCredentials");
        User user = new User();
        user.setId(uname);
        user.setPassword(pwd);  
        user = service.validateUserIdPassword(user);
        return user;
    }
    
    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
/*    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    } */
}
