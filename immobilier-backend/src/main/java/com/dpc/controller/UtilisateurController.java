package com.dpc.controller;



import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.domain.Authority;
import com.dpc.domain.Client;
import com.dpc.domain.Codepostale;
import com.dpc.domain.Utilisateur;
import com.dpc.dto.ClientDto;
import com.dpc.dto.ClientconecteDto;
import com.dpc.dto.CodepostaleDto;
import com.dpc.dto.ArtisantDto;
import com.dpc.dto.UtilisteurDto;
import com.dpc.param.DisableParam;
import com.dpc.repository.ClientRepository;
import com.dpc.repository.IAuthority;
import com.dpc.repository.UtilisateurRepository;
import com.dpc.service.ArtisantService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/user", produces = MediaType.APPLICATION_JSON_VALUE)

public class UtilisateurController extends MainController  {

	@Autowired
	private ArtisantService artisantService;
    @Autowired
    UtilisateurRepository userRepository;
    
    @Autowired 
    UtilisateurRepository iUtilisateur;
    
    
    @Autowired
    IAuthority iAuthority;
    
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
        return authentication.getName();
    }
    
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	//@PreAuthorize("hasRole('ADMIN')")
	public  @ResponseBody Map<String, Boolean> adduser(Model model , @RequestBody Utilisateur user ) {
		Boolean response;
		try {
			//System.out.println("profil "+user.getProfil());
			String authorityname = user.getProfil().replaceAll(" ", "_");
			Authority authority = iAuthority.findByname("ROLE_"+authorityname);
			user.setAuthorities(authority);
			System.out.println("auth"+ authority.getName());
			user.setLastPasswordResetDate(new Date(System.currentTimeMillis()));
			user.setEnabled(true);
			userRepository.save(user);
			Map<String, Boolean> success = new TreeMap<String, Boolean>();
			success.put("response", true);
			return success;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			e.printStackTrace();
			Map<String, Boolean> echec = new TreeMap<String, Boolean>();
			echec.put("response", false);
			return echec;
		}
		
		
	}
	
	//@PreAuthorize("hasRole('Admi')")
	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public @ResponseBody List<Utilisateur> getalluser(Model model) {
		return userRepository.findAll();
	}
	@RequestMapping(value = "/getallbyprofil", method = RequestMethod.GET)
	public @ResponseBody List<Utilisateur> getalluserbyprofil(Model model, String profil) {
		return userRepository.findByProfil(profil);
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/getbyusername", method = RequestMethod.GET)
	public  Utilisateur getalluser(Model model, String username) {
		return userRepository.findByUsername(username);
	}
	
	ClientconecteDto clientdto = null;
	
	@RequestMapping(value = "/getbyusernamedto", method = RequestMethod.GET)
    public ClientconecteDto getuserconect (Model model , String username) {
	
		Utilisateur user = userRepository.findByUsername(username);

		ClientconecteDto clientdto = convertUserConecteToDto(user);
	  return clientdto ;

		
	}
	
	
	
	
	
	
	
	public Collection<ArtisantDto> getartisantById(@PathVariable Long id) {
		return this.artisantService.getartisantById(id).stream().map(artisant -> convertArtisantToDto(artisant))
				.collect(Collectors.toList());	
	}
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	public  @ResponseBody Map<String, Boolean> diableuser(Model model, @RequestBody DisableParam disableParam) {
		Utilisateur utilisateur = userRepository.findByUsername(disableParam.getUsername());
		utilisateur.setEnabled(disableParam.getDisable());
		//System.out.println(disableParam.getUsername()+disableParam.getDisable());
		try {
			 userRepository.save(utilisateur);
			 Map<String, Boolean> success = new TreeMap<String, Boolean>();
				success.put("response", true);
				return success;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Boolean> echec = new TreeMap<String, Boolean>();
			echec.put("response", false);
			return echec;
		}		
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public   @ResponseBody Map<String, Boolean> removeuser(Model model, @RequestBody List<String> usernames) {
		Utilisateur utilisateurtodelete ;
		try {
			//System.out.println(usernames.size());
			for(int i=0; i<usernames.size();i++) {
				utilisateurtodelete = null;
				System.out.println(usernames.get(i));
				utilisateurtodelete = userRepository.findByUsername(usernames.get(i));
				if(utilisateurtodelete!=null) 
					System.out.println(usernames.get(i)+"slimyabhim");
				userRepository.delete(utilisateurtodelete);
				System.out.println(usernames.get(i)+"slimyabhim1");
			
			}
			
			Map<String, Boolean> success = new TreeMap<String, Boolean>();
			success.put("response", true);
			return success;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Boolean> echec = new TreeMap<String, Boolean>();
			echec.put("response", false);
			return echec;
		}
	}
	@Autowired
	ClientRepository clientrepo ;
	
	@RequestMapping(value = "/updatec", method = RequestMethod.POST)
	public @ResponseBody  void updateuser (Model model,  @RequestBody ClientDto clientdto) {
		

        Client client = clientrepo.findByEmail(clientdto.getEmail());
        Utilisateur user = userRepository.findByUsername(clientdto.getEmail());
        
        client.setNom(clientdto.getNom());
        client.setPrenom(clientdto.getPrenom());
        client.setTelephone(clientdto.getTelephone());
        user.setPassword(clientdto.getUserdto().getPassword());
       // client.setUser(user);
        
        clientrepo.save(client);
        userRepository.save(user);
        	
		
	
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Map<String, Boolean> updateuser(Model model, @RequestBody Utilisateur user) {
		//System.out.println();
		//System.out.println(user.getUsername()+"***"+user.getTelephone()+"***"+user.getEmail());

		//System.out.println();
		Utilisateur utilisateurtoupdate = userRepository.findByUsername(user.getUsername());
		
		if(utilisateurtoupdate!=null) {
			utilisateurtoupdate.setUsername(user.getUsername());
	
		 try {
			 userRepository.save(utilisateurtoupdate);
			 Map<String, Boolean> success = new TreeMap<String, Boolean>();
				success.put("response", true);
				return success;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Boolean> echec = new TreeMap<String, Boolean>();
			echec.put("response", false);
			return echec;
		}
		}else {
			Map<String, Boolean> echec = new TreeMap<String, Boolean>();
			echec.put("response", false);
			return echec;
		}
	}
	
	
	
	
	
	

	
	
	
	

	
	
	@RequestMapping(value = "/profil", method = RequestMethod.GET)
	public  @ResponseBody List<Utilisateur> getbyprofil(Model model,  String profil) {
		
			return iUtilisateur.getusersbyprofil(profil);
	}
	@RequestMapping(value = "/prof", method = RequestMethod.GET)
	public  @ResponseBody List<Utilisateur> getprof(Model model) {
		String profil="Prof";
			return iUtilisateur.getusersbyprofil(profil);
	}
	

	/************************************ GetNotificationbyUserId ************************************/

}
