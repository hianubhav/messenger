/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anubhav.messenger.service;

import com.anubhav.messenger.database.DatabaseClass;
import com.anubhav.messenger.exception.DataNotFoundException;
import com.anubhav.messenger.model.Comment;
import com.anubhav.messenger.model.ErrorMessage;
import com.anubhav.messenger.model.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author Anubhav
 */
public class CommentService {
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Comment> getAllComments(long messageId) {
                Message message = messages.get(messageId);
                if (message==null) throw new DataNotFoundException("getAllComments messageId="+messageId);
		Map<Long, Comment> comments = message.getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId, long commentId) {
            ErrorMessage errorMessage = new ErrorMessage("",404,"https://google.com");
            Response response;
            	Message message = messages.get(messageId);
		if (message == null) {
                        errorMessage.setErrorMessge("WebApplicationException getComment messageId="+messageId);
                        response = Response.serverError().entity(errorMessage).build();
			throw new WebApplicationException(response);
		}
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		Comment comment = comments.get(commentId);
		if (comment == null) {
                        errorMessage.setErrorMessge("WebApplicationException getComment commentId="+commentId);
                        response = Response.serverError().entity(errorMessage).build();
			throw new WebApplicationException(response);
		}
		return comment;
	}
	
	public Comment addComment(long messageId, Comment comment) {
                Message message = messages.get(messageId);
                if(message == null) throw new NullPointerException("addComment messageId="+messageId);
		Map<Long, Comment> comments = message.getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment) {
                Message message = messages.get(messageId);
                if(message == null) throw new DataNotFoundException("updateComment messageId="+messageId);
		Map<Long, Comment> comments = message.getComments();
		if (comment.getId() <= 0) {
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId) {
                Message message = messages.get(messageId);
                if(message == null) throw new DataNotFoundException("removeComment messageId="+messageId);
		Map<Long, Comment> comments = message.getComments();
		return comments.remove(commentId);
	}    
}
