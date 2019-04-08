package com.dpc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dpc.domain.Contact;


public interface ContactRepository  extends CrudRepository<Contact, Long>{
	@Query("SELECT 	datenvoie, nom, prenom from Contact  ORDER by datenvoie desc")
    public List<Contact>findByDatenvoie();
	public Contact findById(long id);
	//"select * from questionnaire where userprofileid=" + userProfileID +" ORDER by datecreated desc";
	


	

}
