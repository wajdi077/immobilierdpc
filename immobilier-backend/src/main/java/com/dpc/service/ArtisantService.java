package com.dpc.service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dpc.domain.Artisant;
import com.dpc.domain.Chantier;
import com.dpc.domain.Client;
import com.dpc.repository.ArtisantRepository;
@Service
public class ArtisantService {
	
	 @Autowired
	    private ArtisantRepository artisantRepository;

	    public List<Artisant> getAllartisant(){
	    return (List<Artisant>) artisantRepository.findAll();

}

	   
	    public Artisant createArtisant(Artisant artisant) {
	      Artisant s =   this.artisantRepository.save(artisant);
	         return s;
		}
	    public Artisant findBytelephone(long telephone)
	    { return this.artisantRepository.findByTelephone(telephone);}
	    
	    public void deleteArtisant(Long id) {
	        this.artisantRepository.delete(id);
	    }	
	   
	    
	    public Artisant updateArtisant(Artisant artisant) {
	        return this.artisantRepository.save(artisant);
	    }
	    public boolean artisantExist( Long id) {
	        return artisantRepository.exists(id);
}
	 
	public List<Artisant> getartisantById(Long id) {
			return (List<Artisant>)artisantRepository.findById(id);
			
		}
	public Artisant getartisant_userById(Long id) {
		return  this.artisantRepository.findOne(id);}
	
	public  Artisant findByEmail(String email)
	{
		return this.artisantRepository.findByEmail(email);
	
		}
}