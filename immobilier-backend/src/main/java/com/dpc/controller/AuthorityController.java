package com.dpc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.domain.AccessUrl;
import com.dpc.domain.Authority;
import com.dpc.domain.GestionUrl;
import com.dpc.domain.Utilisateur;
import com.dpc.repository.IAccessUrl;
import com.dpc.repository.IAuthority;
import com.dpc.repository.IGestionUrl;
import com.dpc.repository.UtilisateurRepository;




@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/role")
public class AuthorityController {

	@Autowired
	IAuthority iAuthority;
	
	@Autowired
	UtilisateurRepository iUtilisateur;
	
	@Autowired
	IAccessUrl iAccessUrl;
	
	@Autowired
	IGestionUrl iGestionUrl;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody Map<String, Boolean> addAthority(Model model, @RequestBody Authority authority) {
		AccessUrl accessUrl;
		List<GestionUrl> gestionUrls = iGestionUrl.findAll();
		
		try {
			iAuthority.save(authority);
			
			for(GestionUrl url:gestionUrls) {
				accessUrl = new AccessUrl();
				//accessUrl.setId(null);
				accessUrl.setAuthority(authority);
				accessUrl.setGestionUrl(url);
				accessUrl.setAddop(false);
				accessUrl.setGetallop(false);
				accessUrl.setGetbyidop(false);
				accessUrl.setRemoveop(false);
				accessUrl.setUpdateop(false);
				iAccessUrl.save(accessUrl);
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

	@RequestMapping(value = "/getall", method = RequestMethod.GET)
	public List<Authority> getallAuthority(Model model) {
		List<Authority> authorities = iAuthority.findAll();
		return authorities;
		/*
		 * ArrayList<String> arrayListrole = new ArrayList<String>(); for(int i=0 ;
		 * i<authorities.size() ; i++ ) { String tmp =
		 * authorities.get(i).getName().replaceAll("ROLE_", ""); tmp =
		 * tmp.replaceAll("_", " "); arrayListrole.add(tmp); } return arrayListrole;
		 */
	}

	@RequestMapping(value = "/getbyid", method = RequestMethod.GET)
	public Authority getbyGroupeid(Model model, Long id) {
		return iAuthority.findOne(id);
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public @ResponseBody Map<String, Boolean> removeAuthority(Model model, @RequestBody List<Long> ids) {
		try {
			Authority authority;
			System.out.println("listedebut");
			System.out.println(ids);
			System.out.println("listefin");
			
			for (int i = 0; i < ids.size(); i++) {
				authority = iAuthority.findOne(ids.get(i));
				if(authority != null){
					iAccessUrl.delete(authority.getAccessUrls());
					List<Utilisateur> utilisateurs = iUtilisateur.getusersinauthority(authority);
					for (int j = 0; j < utilisateurs.size(); j++) {
						utilisateurs.get(j).setAuthorities(null);
						iUtilisateur.save(utilisateurs.get(j));
					}
				iAuthority.delete(authority);
				}
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

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Map<String, Boolean> updateAuthority(Model model, @RequestBody Authority groupe) {
		try {
			iAuthority.save(groupe);
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
	
	@RequestMapping(value = "/getgestion", method = RequestMethod.GET)
	public List<AccessUrl> getgestion(Model model, Long id) {
		return iAccessUrl.findbyauthority(iAuthority.findOne(id));
	}

	@RequestMapping(value = "/updategestion", method = RequestMethod.POST)
	public @ResponseBody Map<String, Boolean> updateGestion(Model model, @RequestBody AccessUrl accessUrl) {
		try {
			AccessUrl OldaccessUrl=iAccessUrl.findOne(accessUrl.getId());
			GestionUrl gestionUrl=OldaccessUrl.getGestionUrl();
			Authority authority=OldaccessUrl.getAuthority();
			accessUrl.setAuthority(authority);
			accessUrl.setGestionUrl(gestionUrl);
			iAccessUrl.save(accessUrl);
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
}
