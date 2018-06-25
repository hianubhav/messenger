/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anubhav.messenger.service;

import com.anubhav.messenger.database.DatabaseClass;
import com.anubhav.messenger.exception.DataNotFoundException;
import com.anubhav.messenger.model.Message;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Anubhav
 */
public class MessageService {
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public MessageService() {
		messages.put(1L, new Message(1, "Hello World", "anubhav"));
		messages.put(2L, new Message(2, "Hello Jersey", "anubhav"));
	}
	
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values()); 
	}
        
        public List<Message> getAllMessagesforYear(int year){
            List<Message> messageForYear = new ArrayList<>();
            Calendar cal = Calendar.getInstance();
            for(Message message : messages.values()){
                cal.setTime(message.getCreated());
                if(cal.get(Calendar.YEAR)==year){
                    messageForYear.add(message);
                }
            }
            return messageForYear;
        }
        
        public List<Message> getAllMessagesPaginatee(int start,int size){
            List<Message> list = new ArrayList<Message>(messages.values());
            if(start+size > list.size()) return new ArrayList<Message>();
            return list.subList(start,start+size);
        }
        public Message getMessage(long id){
            Message message =  messages.get(id);
            if(message==null) {
                throw new DataNotFoundException("getMessage messageId="+id);
            }
            return message;
        }
        
        public Message addMesage(Message message){
            message.setId(messages.size()+1);
            messages.put(message.getId(),message);
            return message;
        }
        
        public Message updateMessage(Message message){
            if(message.getId()<=0) return null;
            messages.put(message.getId(),message);
            return message;
        }
        
        public Message removeMessage(Long id){
            return messages.remove(id);
        }
}
