package com.dpc.service;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.domain.Activite;
import com.dpc.domain.Artisant;
import com.dpc.domain.Authority;
import com.dpc.domain.Categorie;
import com.dpc.domain.Chantier;
import com.dpc.domain.Client;
import com.dpc.domain.Codepostale;
import com.dpc.domain.Historique;
import com.dpc.domain.Region;
import com.dpc.domain.Statut;
import com.dpc.domain.Utilisateur;
import com.dpc.domain.Ville;
import com.dpc.dto.CategorieDto;
import com.dpc.dto.ClientDto;
import com.dpc.repository.ActivityRepository;
import com.dpc.repository.CategorieRepository;
import com.dpc.repository.ChantierRepository;
import com.dpc.repository.ClientRepository;
import com.dpc.repository.CodepostaleRepository;
import com.dpc.repository.HistoriqueRepository;
import com.dpc.repository.IAuthority;
import com.dpc.repository.RegionRepository;
import com.dpc.repository.StatutRepository;
import com.dpc.repository.UserRepository;
import com.dpc.repository.VilleRepository;
import java.util.Date;



@Service
public class ChantierService {
	@Autowired
	private UserRepository userRespository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private StatutRepository statutRepository;

	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private ChantierRepository chantierRepository;
	@Autowired
	private CodepostaleRepository codePostaleRepo;
	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired
	private ActivityRepository activiteRepository;
	@Autowired
	private HistoriqueRepository hisRepo;
	@Autowired
	IAuthority roleRepository;
	
	public List<Chantier> findByArtiant(Artisant artisan)
	{
		return (List<Chantier>)chantierRepository.findByArtisan(artisan); 
			
	}
	
	public List<Chantier> getAllchantier() {
		

		return (List<Chantier>) chantierRepository.findAll();

	}
	
	 public Chantier findById(Long idchantier)
	 {return this.chantierRepository.findById(idchantier);}
	 
	 
	 public void deletechantier(Long idchantier)
	 { this.chantierRepository.delete(idchantier);}
	 

	public List<Chantier> getChantiersByRegion(String nomregion) {
		Region r = regionRepository.findByNom(nomregion);

		List<Ville> lville = villeRepository.findByRegionId(r.getId());


		List<Codepostale> lcode = new ArrayList<Codepostale>();
		List<Chantier> lchantier = new ArrayList<Chantier>();

		for (Ville ville : lville) {
		
			List<Codepostale> lcodeP = codePostaleRepo.findByVilleId(ville.getId());
			lcode.addAll(lcodeP);
		}
		for (Codepostale code : lcode) {


			List<Chantier>	lchantiers =chantierRepository.findByCodepostaleId(code.getId());
			lchantier.addAll(lchantiers);
			return lchantier;
		}
		System.out.println("amina list chaniters "+lchantier);

		return lchantier;
	}

	public List<Chantier>GetChantierByActivite(String activite)
	{
		
				
		List<Chantier> lchantier = new ArrayList<Chantier>();

		Activite act= new Activite();
	

		act= activiteRepository.findByNom(act.getNom());
	
		Categorie lcategorie =categorieRepository.getcatbyactivite(act);
				
	
		lchantier = chantierRepository.findByCategorie(lcategorie);
		return lchantier;
		}
	 public boolean chantierExist( Long id) {
	        return chantierRepository.exists(id);
}
	 
	public void publierchantier(Long idchantier) 
	
	{
		Chantier ch =new Chantier ();
		ch=chantierRepository.findOne(idchantier);
		Statut  statut= statutRepository.findByNom("publier");
		ch.setStatut(statut);
		ch.setEtat(statut.getNom());
		ch.setPubliedon(new Date(System.currentTimeMillis()));
		Chantier chs =new Chantier ();
		chs=	this.chantierRepository.save(ch);
		
	}
	
	public void Bloquerchantier(Long idchantier) 
	
	{
		Chantier ch =new Chantier ();
		ch=chantierRepository.findOne(idchantier);
		Statut  statut= statutRepository.findByNom("annuler");
		ch.setStatut(statut);
		ch.setEtat(statut.getNom());
		ch.setAnnuledon(new Date(System.currentTimeMillis()));
		this.chantierRepository.save(ch);
		Chantier chs =new Chantier ();
		chs=this.chantierRepository.save(ch);
		
	}

	
	
	
	public Chantier FindclientToChantier(Client  client)
	{Chantier cha ;
	cha = clientRepository.findByOneClient(client);
			//chantierRepository.findByOneClient(client);
		return cha;
		}
	
	
	public List<Chantier> getChantierByEtat(String etat) {
		return (List<Chantier>) chantierRepository.findByEtat(etat);
	}
	
	public List<Chantier> getChantierpublier() {
		String etat = "publier";
		return (List<Chantier>) chantierRepository.findByEtat(etat);
	}
	
	
	public List<Chantier>getChantierByCategorie(Categorie cat)
	{
		 return (List<Chantier>) chantierRepository.findByCategorie(cat);
		
	}
	 public List<Chantier> getChantierByClient(Client c)
	 {
		return( List<Chantier>) chantierRepository.findByClient(c);
	 }
	 
		/*      déposer une demande du chantier  */
	 public Chantier DemandeChantier(String username,Chantier ch)
	 { 
		 System.out.println("********1");

			Date date = new Date();
		   	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    	String d1= dateFormat.format(date);
		    	 System.out.println("********2");
	
		 Categorie cat = new  Categorie ();
		 cat=ch.getCategorie();
		 System.out.println("********3");
	

  	
  	Client c =new Client();
  	c =this.clientRepository.findByEmail(username);
  	 System.out.println("********"+c.getEmail());
  	 System.out.println("********7");

  	Utilisateur u = new Utilisateur();
	  u	=this.userRespository.findByUsername(username);
	  System.out.println("********8");
	  System.out.println("utilisateur"+u.getUsername());
  
		Statut  statut= this.statutRepository.findByNom("en_cours");
		ch.setStatut(statut);
		ch.setEtat(statut.getNom());
		ch.setCategorie(cat);
		ch.setRDV("rendez vous en cours de traitement ");
		ch.setCreatedon(new Date(System.currentTimeMillis()));
		ch.setClient(c);
		//ch.setAdresse(cp.getCode()+","+cp.getNom()+","+ville.getNom()+","+region.getNom());
		System.out.println("********9");
		//ch.setAdresse("adresse");
		

		Chantier chantier = new Chantier();
		chantier = this.chantierRepository.save(ch);
		System.out.println("********10");
		Historique h = new Historique();
		System.out.println("********11");
		h.setAction("depot d'un projet");
		h.setDiscriptif("ce chantier demandé en cours ");
		h.setDate(d1);
		h.setAction("demande de chantier");
		h.setUser(u);
		System.out.println("********12");
	

		Historique histo = this.hisRepo.save(h);

	
		 return chantier;	 
	 }
	
	 
	 
	/*      déposer une demande du chantier avec inscrpt lors premier fois      */
	public Chantier CreateChantierClient(Client client, Chantier c )
	{

		Date date = new Date();
	   	 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	String d1= dateFormat.format(date);
	//	Codepostale cp =new Codepostale();
		
		//cp=c.getCodepostale();
		// Ville ville = new Ville();
		//ville = villeRepository.findByListcodepostale(cp);
		
		
	//Region region = new Region ();
	//region =regionRepository.findByListville(ville);
	
		this.clientRepository.save(client);


	Statut  statut= this.statutRepository.findByNom("en_cours");
	c.setStatut(statut);
	c.setEtat(statut.getNom());
		
   c.setClient(client);
   c.setCreatedon(new Date(System.currentTimeMillis()));
String randomPass = alphaNumericString(6);

Utilisateur user = new Utilisateur();
user.setUsername(client.getEmail());
user.setPassword(randomPass);
user.setCreatedOn(user.getCreatedOn());
System.out.println("date creation"+user.getCreatedOn());
user.setClient(client);

Authority userRole = roleRepository.findByname("ROLE_Client");
user.setAuthorities(userRole);
user.setLastPasswordResetDate(new Date(System.currentTimeMillis()));
user.setEnabled(true);
user.setProfil("Client");
 this.userRespository.save(user);

//c.setEtat("en cours");
c.setRDV("rendez vous en cours de traitement ");
Historique h = new Historique();
h.setAction("depot d'un projet");
h.setDiscriptif("projet envoyer par"+c.getClient().getNom()+c.getClient().getPrenom());
h.setDate(d1);
h.setAction("demande de chantier");

this.hisRepo.save(h);
c.setAdresse("adresse");

this.chantierRepository.save(c);
				
		return c ; 
	}
	
	
	
	/*  fct pr générer un emot de passe automatique   */
	 public static String alphaNumericString(int len) {
	        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        Random rnd = new Random();

	        StringBuilder sb = new StringBuilder(len);
	        for (int i = 0; i < len; i++) {
	            sb.append(AB.charAt(rnd.nextInt(AB.length())));
	        }
	        return sb.toString();
	    }
	 
	 
	 
	 // obtenir projet
	 
	 public Chantier ObtenirChantier(Long idchantier)
	 { Chantier cha = new Chantier();
	 
		 
		 
		 return cha; }
	 

}


