/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anubhav.messenger.resources;

import com.anubhav.messenger.model.Profile;
import com.anubhav.messenger.service.ProfileService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Anubhav
 */
@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {
    private ProfileService profileService = new ProfileService();
    
    @GET
    public List<Profile> getAllProfiles(){
        return profileService.getAllProfiles();
    }
    
    @GET
    @Path("/{profileName}")
    public Profile getProfile(@PathParam("profileName") String profileName){
        return profileService.getProfile(profileName);
    }
    
    @POST
    public Profile addProfile(Profile profile){
        return profileService.addProfile(profile);
    }
     
    @DELETE
    @Path("/{profileName}")
    public void removeProfile(@PathParam("profileName") String profileName){
        profileService.removeProfile(profileName);
    }
    
    @PUT
    @Path("/{profileName}")
    public Profile updateProfile(@PathParam("profileName") String profileName,Profile profile){
        profile.setProfileName(profileName);
        return profileService.updateProfile(profile);
    }
}
