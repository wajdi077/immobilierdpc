package com.dpc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dpc.domain.Codepostale;
import com.dpc.repository.CodepostaleRepository;

@Service
public class CodepostaleService {
	
	@Autowired
	private CodepostaleRepository codepostaleRepository;
	
	public List<Codepostale>getAllCodepostale(){
		return (List<Codepostale>)codepostaleRepository.findAll();
	}

	
	public Codepostale findByCode(Long code) {
		return this.codepostaleRepository.findByCode(code);
	}
	
	public Codepostale findByNom(String nom){
		return this.codepostaleRepository.findByNom(nom);
	}
	
}






