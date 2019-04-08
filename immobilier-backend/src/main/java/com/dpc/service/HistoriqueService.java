package com.dpc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.domain.Chantier;
import com.dpc.domain.Historique;

import com.dpc.repository.HistoriqueRepository;
@Service
public class HistoriqueService {
	 @Autowired
	    private HistoriqueRepository histRepository;

	    public List<Historique> getAllHistorique(){
	    return (List<Historique>) histRepository.findAll();
	    }
	    public List<Historique> getHistByChantier(Chantier c){
		    return (List<Historique>) histRepository.findByChantier(c);
	    
}

}
