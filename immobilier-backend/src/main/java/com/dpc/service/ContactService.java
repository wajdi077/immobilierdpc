package com.dpc.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.repository.ContactRepository;

import com.dpc.domain.Contact;
@Service
public class ContactService {
	@Autowired
	ContactRepository messageReop;


    public Contact  EnvoyerMessage(Contact message) {
    	Date date = new Date();
   	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String d1= dateFormat.format(date);
    	message.setDatenvoie(d1);
    	Contact msg =   this.messageReop.save(message);
         return msg;
	}
	
    public List<Contact>getAllMessages() {
		return (List<Contact>) messageReop.findAll();
		
	}
    public Contact getById(long id)
    {return this.messageReop.findById(id);}
    
    public void deleteMessage(Long id) {
        this.messageReop.delete(id);
    }	
    public boolean messageExist( Long id) {
        return messageReop.exists(id);
}
}
