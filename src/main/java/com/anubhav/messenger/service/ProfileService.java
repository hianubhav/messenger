/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anubhav.messenger.service;

import com.anubhav.messenger.database.DatabaseClass;
import com.anubhav.messenger.exception.DataNotFoundException;
import com.anubhav.messenger.model.Profile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Anubhav
 */
public class ProfileService {
    private Map<String,Profile> profiles = DatabaseClass.getProfiles();
    
    public List<Profile> getAllProfiles(){
        return new ArrayList<Profile>(profiles.values());
    }
    
    public Profile addProfile(Profile profile){
        profile.setId(profiles.size()+1);
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }
    
    public Profile getProfile(String profileName){
        Profile profile = profiles.get(profileName);
        if(profile == null) throw new DataNotFoundException("getProfile profileName="+profileName);
        return profiles.get(profileName);
    }
    public Profile updateProfile(Profile profile){
        if(profile.getProfileName().isEmpty()) throw new DataNotFoundException("updateProfile profileNotFound");
        profiles.put(profile.getProfileName(),profile);
        return profile;
    }

    public ProfileService() {
        profiles.put("anubhav",new Profile(1L,"anubhav","anubhav","gupta"));
    }
    
    public  Profile removeProfile(String profileName){
        return profiles.remove(profileName);
    }
}
