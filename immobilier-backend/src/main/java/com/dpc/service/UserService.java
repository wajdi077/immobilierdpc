package com.dpc.service;

import java.sql.Date;
import java.util.List;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.config.SecurityUtility;
import com.dpc.config.WebSecurityConfig;
import com.dpc.domain.Activite;
import com.dpc.domain.Artisant;
import com.dpc.domain.Authority;
import com.dpc.domain.Categorie;
import com.dpc.domain.Chantier;
import com.dpc.domain.Client;
import com.dpc.domain.Codepostale;
import com.dpc.domain.Region;
import com.dpc.domain.Statut;
import com.dpc.domain.Utilisateur;
import com.dpc.domain.Ville;
import com.dpc.repository.ArtisantRepository;
import com.dpc.repository.ClientRepository;
import com.dpc.repository.CodepostaleRepository;
import com.dpc.repository.IAuthority;
//import com.dpc.repository.IAuthority;
import com.dpc.repository.MailService;
import com.dpc.repository.RegionRepository;

import com.dpc.repository.UserRepository;
import com.dpc.repository.VilleRepository;

@Service
public class UserService  {
	@Autowired
	private UserRepository userRespository;
	@Autowired
	ArtisantRepository artisanRepository;
	@Autowired
IAuthority rolerepository;

	 @Autowired
	  CodepostaleRepository codepostaleRepository;
	 @Autowired
	  RegionRepository regionRepository;
	 @Autowired
	  VilleRepository villeRepository;
	 @Autowired
	  MailServiceImp mailService;
	 @Autowired
	 ClientRepository clientRepo;
	
	public List<Utilisateur> getAllUser(){
		  
	    return (List<Utilisateur>) userRespository.findAll();

	}
	  public void sendUserConfirmation(Artisant a)
	  { mailService.sendEmail(a);}
	
	/*public List<User>getAllUserClient()
	{return this.userRespository.findByClient();
	}
	
	
	public List<User>getAllUserArtisant(){
		return this.userRespository.findByArtisant();
	}*/
	    public Utilisateur creatUser(Utilisateur user) {
	        return this.userRespository.save(user);
		}
	    public void deleteUser(String login) {
	    	Utilisateur u = new Utilisateur();
	    	u = this.userRespository.findByUsername(login);
	        this.userRespository.delete(u);
	    }	
	    public Utilisateur updateUser(Utilisateur user) {
	        return this.userRespository.save(user);
	    }
	    public boolean UserExist( Long id) {
	        return userRespository.exists(id);
	       
}
	    public Utilisateur getBylogin(String login)
	    
	     { return this.userRespository.findByUsername(login);}
	    

	    
	  
	    
	    
	    
	    
	    
	    
	    /* inscription d'un pro       */
	    public Utilisateur createUserArtisant(Artisant userartisan ) {
	    	 
	    	
	     String randomPass = alphaNumericString(6);
	    
	         Artisant a = new Artisant ();
	            a = userartisan ;
	            
	    	 Codepostale cp =new Codepostale();
	    	 Activite act = new Activite();
	    	 act=userartisan.getActivite();
	    	
	    	cp=userartisan.getCodepostale();
	    	 Ville ville = new Ville();
	    	ville = villeRepository.findByListcodepostale(cp);
	    	
	    	Region region = new Region ();
	    region =regionRepository.findByListville(ville);
	    	a.setAdresse(cp.getCode()+","+cp.getNom()+","+ville.getNom()+","+region.getNom());
	    	
	    	
	    this.artisanRepository.save(userartisan);
	    
	    Utilisateur user = new Utilisateur();
	    user.setUsername(a.getEmail());
	    user.setPassword(randomPass);
	    user.setCreatedOn(user.getCreatedOn());
	    user.setArtisant(userartisan);
   
       Authority userRole = rolerepository.findByname("ROLE_Artisan");
       user.setAuthorities(userRole);
    	user.setLastPasswordResetDate(new Date(System.currentTimeMillis()));
    	user.setEnabled(true);
    	user.setProfil("Artisan");
    	return this.userRespository.save(user);

		
		}
	    
	    
	    /*inscription client */
	    

	    public Utilisateur inscritClient(Client client ) {
	    	  String randomPass = alphaNumericString(6);
	  	    
	    	
	    /* String randomPass = SecurityUtility.randomPassword();*/
	    /* String passencod = SecurityUtility.passwordEncoder().encode(randomPass);*/
	     this.clientRepo.save(client);
	    Client c=new Client();
	    c=client;
	    Utilisateur user = new Utilisateur();
	    user.setUsername(c.getEmail());
	    System.out.println("mailservice"+c.getEmail());
	    user.setPassword(randomPass);
	    user.setCreatedOn(user.getCreatedOn());
	    user.setClient(client);
   
       Authority userRole = rolerepository.findByname("ROLE_Client");
       user.setAuthorities(userRole);
    	user.setLastPasswordResetDate(new Date(System.currentTimeMillis()));
    	user.setEnabled(true);
    	user.setProfil("Client");
    	return this.userRespository.save(user);

		
		}
	    
	    
	    
		public void BloquerUser(String iduser) 
		
		{
			Utilisateur u =new Utilisateur();
		
			u=this.userRespository.findByUsername(iduser);
			u.setEnabled(false);
			u.setBlockedate(new Date(System.currentTimeMillis()));
			this.userRespository.save(u);
			/*   client ma3ach najem yaccedi bih compte so solution?*/
			
		}
		   
				public void DebloquerUser(String iduser) 
				
				{
					
					Utilisateur u =new Utilisateur();
				
					u=this.userRespository.findByUsername(iduser);
					u.setEnabled(true);
					u.setDeblockedate(new Date(System.currentTimeMillis()));
					this.userRespository.save(u);
					/*   client ma3ach najem yaccedi bih compte so solution?*/
					
				}
	    
	    public static String alphaNumericString(int len) {
	        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        Random rnd = new Random();

	        StringBuilder sb = new StringBuilder(len);
	        for (int i = 0; i < len; i++) {
	            sb.append(AB.charAt(rnd.nextInt(AB.length())));
	        }
	        return sb.toString();
	    }
	    
	    
	    
}