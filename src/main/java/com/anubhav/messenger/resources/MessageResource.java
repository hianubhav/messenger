/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anubhav.messenger.resources;

import com.anubhav.messenger.model.Message;
import com.anubhav.messenger.resources.beans.MessageFilterBean;
import com.anubhav.messenger.service.MessageService;
import java.net.URI;
import java.util.List;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilderException;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Anubhav
 */

@Path("/messages")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
    MessageService messageService = new MessageService();
    
    @DELETE
    @Path("/{messageId}")
    public void deleteMessage(@PathParam("messageId") long id){
        messageService.removeMessage(id);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Message> getJsonMessages(@BeanParam MessageFilterBean filterBean){
        System.out.println("Json method");
        if(filterBean.getYear()>0){
            return messageService.getAllMessagesforYear(filterBean.getYear());
        }
        else if(filterBean.getStart()>=0 && filterBean.getSize()>0){
            return messageService.getAllMessagesPaginatee(filterBean.getStart(), filterBean.getSize());
        }
        else return messageService.getAllMessages();
    }
    
    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Message> getXmlMessages(@BeanParam MessageFilterBean filterBean){
        System.out.println("xml method");
        if(filterBean.getYear()>0){
            return messageService.getAllMessagesforYear(filterBean.getYear());
        }
        else if(filterBean.getStart()>=0 && filterBean.getSize()>0){
            return messageService.getAllMessagesPaginatee(filterBean.getStart(), filterBean.getSize());
        }
        else return messageService.getAllMessages();
    }    
    private String getUriforSelf(UriInfo uriInfo, Message message){
        String url = uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(Long.toString(message.getId()))
                .build()
                .toString();
        return url;
    }
    @GET
    @Path("{messageId}")
    public Message getMessage(@PathParam("messageId") Long id,@Context UriInfo uriInfo){
        Message message =  messageService.getMessage(id);
        message.addLink(getUriforSelf(uriInfo, message), "self");
        message.addLink(getUriforProfile(uriInfo, message), "profile");
        message.addLink(getUriforComments(uriInfo,message), "comments");
        return message;
    }


    
    @POST
    public Response addMessage(Message message,@Context UriInfo uriInfo){
        message = messageService.addMesage(message);
        String id = String.valueOf(message.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
        return Response.created(uri)
                .entity(message)
                .build();
        
    }
    
    @PUT
    @Path("/{messageId}")
    public Message updateMessage(Message message,@PathParam("messageId") long id){
        message.setId(id);
        return messageService.updateMessage(message);
    }
    
    @Path("/{messageId}/comments")
    public CommentResource getCommentResource(){
        return new CommentResource();
    }

    private String getUriforProfile(UriInfo uriInfo, Message message) {
        String uri = uriInfo.getBaseUriBuilder()
                .path(ProfileResource.class)
                .path(message.getAuthor())
                .build()
                .toString();
        return uri;
    }

    private String getUriforComments(UriInfo uriInfo, Message message) {
        
        String uri = uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(MessageResource.class,"getCommentResource")
                .path(CommentResource.class)
                .resolveTemplate("messageId", message.getId())
                .build()
                .toString();
        return uri;    
    }
    
}
