package com.dpc.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.dpc.domain.Utilisateur;
import com.dpc.repository.UtilsateurRepo;

import java.util.List;

@Service
public class UserServiceImpl  {
    @Autowired
    private UtilsateurRepo userRepository;

   
    public Utilisateur findByUsername( String username ) throws UsernameNotFoundException {
        Utilisateur u = userRepository.findByUsername( username );
        return u;
    }

    public Utilisateur findById( Long id ) throws AccessDeniedException {
        Utilisateur u = userRepository.findOne( id );
        return u;
    }

    public List<Utilisateur> findAll() throws AccessDeniedException {
        List<Utilisateur> result = userRepository.findAll();
        return result;
    }
    
    public List<Utilisateur> findbyprofil(String profil){
    	
    	
    	
    	return  this.userRepository.findByProfil(profil);
    }
}
