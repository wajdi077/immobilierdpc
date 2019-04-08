package com.dpc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.domain.Activite;
import com.dpc.domain.Artisant;
import com.dpc.domain.Categorie;
import com.dpc.domain.Chantier;
import com.dpc.domain.Client;
import com.dpc.domain.Codepostale;
import com.dpc.domain.Contact;
import com.dpc.domain.Mail;
import com.dpc.domain.Statut;
import com.dpc.domain.Utilisateur;
import com.dpc.dto.ArtisanDisplayDto;
import com.dpc.dto.ArtisantDto;
import com.dpc.dto.CategorieDto;
import com.dpc.dto.ChantierDto;
import com.dpc.dto.ChantierinstDto;
import com.dpc.dto.ClientChantierDto;
import com.dpc.dto.ClientDto;
import com.dpc.dto.CodepostaleDto;
import com.dpc.dto.RegionDto;
import com.dpc.dto.UserDto;
import com.dpc.repository.ChantierRepository;
import com.dpc.repository.StatutRepository;
import com.dpc.repository.UtilisateurRepository;
import com.dpc.service.ActivityService;
import com.dpc.service.ArtisantService;
import com.dpc.service.CategorieService;
import com.dpc.service.ChantierService;
import com.dpc.service.ClientService;
import com.dpc.service.CodepostaleService;
import com.dpc.service.ContactService;
import com.dpc.service.EmailService;
import com.dpc.service.MailServiceImp;
import com.dpc.service.RegionService;

import freemarker.template.TemplateException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin("*")
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/immobilier/front")
public class DpcImmobilierFrontController extends MainController{
	
	
	@RequestMapping(value="/test",method = RequestMethod.GET)
	public String test()
	{
		return "wajdi";
	}
	
	@Autowired
	private ClientService clientService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private ArtisantService artisantService;
	@Autowired
	private ChantierService chantierService;
	@Autowired
	private CodepostaleService codepostaleservice;
	@Autowired
	private CategorieService categorieservice;
	@Autowired
	private ContactService contactservice;
    @Autowired
    private EmailService emailService;
    @Autowired
	private StatutRepository statutRepository;
    
    @Autowired
	private ChantierRepository chantierRepository;

    
    @Autowired 
    UtilisateurRepository iUtilisateur;
	
	 @Autowired
		MailServiceImp mailservice ;
	 


	
	ClientDto clientDto = null;
	ArtisantDto artisantDto = null;

	@Autowired
private ActivityService activiteservice;
	
	// -----/immobilier/front/client/all
	
	@ApiOperation(value = "getAllclient", notes = "get all list of client .", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
			@ApiResponse(code = 404, message = "No artisant found for this key"),
			@ApiResponse(code = 200, message = "The artisant was successfully deleted.") })
	@RequestMapping(value = "/client/all", method = RequestMethod.GET)
	public Collection<ClientDto> getAllclient() {
		return this.clientService.getAllclient().stream().map(client -> convertClientToDto(client))
				.collect(Collectors.toList());
	}
	// -----/immobilier/front/region/all
		@ApiOperation(value = "getallregion", notes = "getall region.", response = String.class)
		@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
				@ApiResponse(code = 404, message = "No region found for this key"),
				@ApiResponse(code = 200, message = "The bundle was successfully deleted.") })
		@RequestMapping(value = "/region/all", method = RequestMethod.GET)
		public Collection<RegionDto> getAllregion() {
			return this.regionService.getAllregion().stream().map(region -> convertRegionToDto(region))
					.collect(Collectors.toList());

		}
		
		// Create new client
	    @ApiOperation(
	            value = "create new client",
	            notes = "create client " + "version and returns the client created.",
	            response = ClientDto.class)
	    @RequestMapping(
	            value = "/pages/client", method = RequestMethod.POST)
	    public ClientDto createClient(@RequestBody ClientDto clientDto) {
	        Client client = dtoToClient(clientDto);
	        Client clientCreated = this.clientService.createClient(client);
	        return convertClientToDto(clientCreated);
	    }

		// Update the client
	    @ApiOperation(
	            value = "updateclient",
	            notes = "Update client " + "version and returns the client updated.",
	            response = ClientDto.class)
	    @ApiResponses(
	            value = { @ApiResponse(
	                    code = 400, message = "Invalid client nom"),
	                    @ApiResponse(
	                            code = 404, message = "No client found for this nom"),
	                    @ApiResponse(
	                            code = 200, message = "The client was successfully updated.") })
	    @RequestMapping(
	            value = "/pages/client/id/{id}", method = RequestMethod.PUT)
	    
	    public ResponseEntity<String> updateClient(@PathVariable( value = "id") Long id, @RequestBody ClientDto clientDto)
	    {
	    	if (this.clientService.clientExist(id)) {
	    		 Client c = dtoToClient(clientDto);
	    		 c.setId(id);
		            this.clientService.updateClient(c);
		            return new ResponseEntity<>(HttpStatus.OK);
		        } else {
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
	    }
	    
	 // delete a client
	    @ApiOperation(
	            value = "delete client", notes = "Delete Client by its ID.", response = String.class)
	    @ApiResponses(
	            value = { @ApiResponse(
	                    code = 400, message = "Invalid client ID"),
	                    @ApiResponse(
	                            code = 404, message = "No client found for this ID"),
	                    @ApiResponse(
	                            code = 200, message = "The client was successfully deleted.") })
	    @RequestMapping(
	            value = "/pages/client/id/{id}", method = RequestMethod.DELETE)
	    
	    public ResponseEntity<String> deleteclient(@PathVariable(
	            value = "id") Long id) {

	        if (this.clientService.clientExist(id)) {
	            this.clientService.deleteClient(id);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    } 
	    
	    /* Artisant*/
	    
	 // create the default artisant
	    @ApiOperation(
	            value = "createNewartisant",
	            notes = "Create a new artisant " + "version and returns the template created.",
	            response = ArtisantDto.class)
	    @RequestMapping(
	            value = "/pages/artisant", method = RequestMethod.POST,produces = "application/json")
	    public ArtisantDto createArtisant(@RequestBody ArtisantDto artisantDto) {
	    	Artisant artisant = dtoToArtisant(artisantDto);
	    	Artisant artisantCreated = this.artisantService.createArtisant(artisant);
	        return convertArtisantToDto(artisantCreated);
	        	        
	    }
	    
	    // delete Artisant by id 
	    
	    @ApiOperation(value = "delete artisant", notes = "Delete Artisant by its ID.", response = String.class)
	    @ApiResponses( value = { @ApiResponse(code = 400, message = "Invalid artisant ID"),
	                     @ApiResponse(code = 404, message = "No artisant found for this ID"),
	                     @ApiResponse(code = 200, message = "The artisant was successfully deleted.") })
	    @RequestMapping( value = "/pages/artisant/id/{id}", method = RequestMethod.DELETE)
	    
	    public ResponseEntity<String> deleteartisant(@PathVariable( value = "id") Long id) 
	    {
	    	if (this.artisantService.artisantExist(id)) {
	            this.artisantService.deleteArtisant(id);
	            return new ResponseEntity<>(HttpStatus.OK);
	              } 
	    	else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    } 
  
		// Update the artisnat
	  
	    @ApiOperation(
	            value = "updateartisant",
	            notes = "Update artisant " + "version and returns the artisant updated.",
	            response = ArtisantDto.class)
	    @ApiResponses(
	            value = { @ApiResponse(
	                    code = 400, message = "Invalid artisant nom"),
	                    @ApiResponse(
	                            code = 404, message = "No artisant found for this nom"),
	                    @ApiResponse(
	                            code = 200, message = "The artisant was successfully updated.") })
	    @RequestMapping(
	            value = "/pages/updateartisant/id/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<String> updateArtisant(@PathVariable( value = "id") Long id, @RequestBody ArtisantDto artisantDto)
	    {
	    	if (this.artisantService.artisantExist(id)) {
	    		 Artisant a = dtoToArtisant(artisantDto);
	    		 Codepostale codepostale = a.getCodepostale();
	    		 a.setId(id);
	    		 a.getCodepostale();
	    		// a.setActivite(activite);
		            this.artisantService.updateArtisant(a);
		            return new ResponseEntity<>(HttpStatus.OK);
		        } else {
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
	    }
	    
	    @RequestMapping(
	            value = "/pages/updateartisant/email/{email}", method = RequestMethod.PUT)
	    public Artisant updateArtisantParMail(@PathVariable(value="email") String email ,@RequestBody ArtisanDisplayDto artisantdsipDto)
	    {
	    
	   System.out.println("dakhla"+email);
	    		 Artisant a = dtoToArtisantDisplay(artisantdsipDto);
	    		;
	    		  Utilisateur r= new Utilisateur();
	    		 Codepostale codepostale = this.codepostaleservice.findByCode(a.getCodepostale().getCode());
	    		 System.out.println("codeeeeeeeee"+codepostale);
	    		 Activite activite= this.activiteservice.getByNom(a.getActivite().getNom());
	    		 a.setActivite(activite);
	    		 a.setCodepostale(codepostale);
	    		
	    		 a.setAdresse(codepostale.getCode()+","+codepostale.getNom()+","+codepostale.getVille().getNom());
	    		/* r=this.userService.getBylogin(r.getUsername());
	    	    	a.setUser(r);
	    	System.out.println("user mail "+email);
	    */
	    		 
		     return this.artisantService.updateArtisant(a);
	    }
	    
	    
	    
	    
	 // -----/immobilier/referentiel/categorie/byactivitee

		@ApiOperation(value = "getallcategoriesbyactivite", notes = "get a categorie entry via its key.", response = String.class)

		@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
				@ApiResponse(code = 404, message = "No categorie found for this key"),
				@ApiResponse(code = 200, message = "The categorie was successfully deleted.") })
		@RequestMapping(value = "/categories/activite/", method = RequestMethod.GET)
		public Collection<CategorieDto> getAllCategoriesByActivite( String nom) {
			Activite a = new Activite();
			a=activiteservice.getByNom(nom);
			
			return this.categorieservice.GetCategorieByActivity(a).stream().map(categorie -> convertCategorieToDto(categorie))
					.collect(Collectors.toList());
		}
		
		// -----/immobilier/back/chantier/categorie

				@ApiOperation(value = "getchantierbycategorie", notes = "afficher les chantier suivant etat.", response = String.class)
				@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
						@ApiResponse(code = 404, message = "No chantier found for this key"),
						@ApiResponse(code = 200, message = "The chantier was successfully .") })
				@RequestMapping(value = "/chantier/categorie/{categorie}", method = RequestMethod.GET)
				public Collection<ChantierDto> getchantierByCategorie(@PathVariable String categorie) {
					Categorie cat =new Categorie();
					cat=categorieservice.GetByNom(categorie);
					
					return this.chantierService.getChantierByCategorie(cat).stream().map(chantier -> convertChantierToDto(chantier))
							.collect(Collectors.toList());
				}
				// -----/immobilier/back/chantier/activite

				@ApiOperation(value = "getchantierbyActivite", notes = "afficher les chantier suivant activite.", response = String.class)
				@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
						@ApiResponse(code = 404, message = "No chantier found for this key"),
						@ApiResponse(code = 200, message = "The chantier was successfully .") })
				@RequestMapping(value = "/chantier/activite/{activite}", method = RequestMethod.GET)
				public Collection<ChantierDto> getchantierByActivity(@PathVariable String activite) {
					
					return this.chantierService.GetChantierByActivite(activite).stream().map(chantier -> convertChantierToDto(chantier))
							.collect(Collectors.toList());
				}
				
				// -----/immobilier/back/chantier/client

				@ApiOperation(value = "getchantierbyclient", notes = "afficher les chantier suivant client.", response = String.class)
				@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
						@ApiResponse(code = 404, message = "No chantier found for this key"),
						@ApiResponse(code = 200, message = "The chantier was successfully .") })
				@RequestMapping(value = "/chantier/email/{email}", method = RequestMethod.GET)
				
				public Collection<ChantierDto> getchantierByClient(@PathVariable String email) {
					Client c =new Client();
					c = clientService.findByEmail(email);
					return this.chantierService.getChantierByClient(c).stream().map(chantier -> convertChantierToDto(chantier))
							.collect(Collectors.toList());
				}
				
				
				
				// -----/immobilier/back/demandechantier/client

				@ApiOperation(value = "demande chantier", notes = "demande chantier", response = String.class)
				@RequestMapping(value = "/chantier/creer/username/{username}", method = RequestMethod.POST)
				
				public ChantierDto DemandeChantier(@PathVariable( value = "username") String username ,@RequestBody ChantierinstDto chantierdto) throws MessagingException, IOException, TemplateException {
					/*Utilisateur u =new Utilisateur();
					u=this.iUtilisateur.findByUsername(u.getUsername());*/
			    	  String nom = chantierdto.getCodepostale();

					Client c=new Client();
					 System.out.println("client");
					c =this.clientService.findByEmail(username);
					 System.out.println("clientetape 2");
					String nomcategorie =chantierdto.getNomcategorie();
					 System.out.println("controller etape 3");
			    	Chantier ch =new Chantier ();
			    	
			    Categorie cat = categorieservice.GetByNom(nomcategorie);
				 System.out.println("controller etape 4"+cat.getNom());
			    	ch.setCategorie(cat);
			   	 System.out.println("controller etape 5");
			   	Codepostale codepostal = this.codepostaleservice.findByNom(nom);
		    	ch.setCodepostale(codepostal);
			    	 System.out.println("controller etape 6");
			         ch.setDiscription(chantierdto.getDiscription());
			         ch.setTypebatiment(chantierdto.getTypebatiment());
			         ch.setDate(chantierdto.getDate());

			    	 System.out.println("controller etape 7");
			   	 System.out.println("controller etape 8");
			        ch.setClient(c);
			   	 System.out.println("controller etape 9");
			        
			   // 	Client c = dtoToClient (chantierdto.getClient());
			    	

			     Utilisateur u = this.iUtilisateur.findByUsername(c.getEmail());
			      System.out.println("user"+u.getPassword());
			    	Chantier chantierCreated = this.chantierService.DemandeChantier(username,ch);
			    	Mail mail = new Mail();
				      mail.setFrom("no-reply@memorynotfound.com");
				      mail.setTo(c.getEmail());
				      mail.setSubject("demande devis");

				        Map<String, Object> model = new HashMap<String, Object>();
				        model.put("name", c.getNom());
				        model.put("location", "TUNISIE");
				        model.put("signature", "http://DIGITALPOWERCONSULTING.COM");
				        model.put("login", c.getEmail());
				        model.put("pass", u.getPassword());
				        model.put("devis",cat.getNom() );
				        	
				        mail.setModel(model);

				        this.emailService.sendSimpleMessage(mail);
			      
			        return convertChantierToDto(chantierCreated);
				}
						
			
			    //creer un chantier et creer le client qui publier ce chantier 
			    @RequestMapping(value = "chantierparClient/add", method = RequestMethod.POST)
			  
			    public Utilisateur createachantierparClient(@RequestBody ClientChantierDto clienchantierDto) throws MessagingException, IOException, TemplateException {
			    	
			    	String nomcategorie =clienchantierDto.getNomcategorie();
			    	  String nom = clienchantierDto.getCodepostale();

			    	
			    	Chantier ch =new Chantier ();
			    	
			    Categorie cat = categorieservice.GetByNom(nomcategorie);
			    	ch.setCategorie(cat);
			    	
			    	Codepostale codepostal = this.codepostaleservice.findByNom(nom);
			    	ch.setCodepostale(codepostal);
			         
			         ch.setDiscription(clienchantierDto.getDiscription());
			         ch.setDate(clienchantierDto.getDate());
			         ch.setTypebatiment(clienchantierDto.getTypebatiment());
			         ch.setAdresse(clienchantierDto.getAdresse());
			       
			      
			        
			    	Client c = dtoToClient (clienchantierDto.getClient());
			    	

			        // mail 

			    	//SimpleMailMessage registrationEmail = new SimpleMailMessage();
					//registrationEmail.setTo(c.getEmail());
					//registrationEmail.setSubject("Votre demander été bien enregistré");
					//registrationEmail.setFrom("immo.devis18@gmail.com");
					//String content = "Dear " +c.getNom()+" + " + c.getPrenom();
			     
			    //     System.out.println("Message"+content);
				  
			    	
			       
			    	Chantier chantierCreated = this.chantierService.CreateChantierClient(c,ch);
			      Utilisateur u = this.iUtilisateur.findByUsername(c.getEmail());
			      System.out.println("user"+u.getPassword());

			      Mail mail = new Mail();
			      mail.setFrom("no-reply@memorynotfound.com");
			      mail.setTo(c.getEmail());
			      mail.setSubject("demande devis");

			        Map<String, Object> model = new HashMap<String, Object>();
			        model.put("name", c.getNom());
			        model.put("location", "TUNISIE");
			        model.put("signature", "http://DIGITALPOWERCONSULTING.COM");
			        model.put("login", c.getEmail());
			        model.put("pass", u.getPassword());
			        model.put("devis",cat.getNom() );
			        	
			        mail.setModel(model);

			        this.emailService.sendSimpleMessage(mail);
				
			      
			      // registrationEmail.setText("bonjour Mr/Ms "+c.getNom()+""+c.getPrenom() +"/n utiliser ce login  pour se connecter a votre espace , mot passe : " +u.getPassword()+ " email "+u.getUsername()+"  lien :  http://localhost:4200/connect");
					
					//this.mailservice.sendEmail(registrationEmail);
			        return u;
			 
			    }  
	    
	 // envoyer message
	    @ApiOperation(
	            value = "envoyer message",
	            notes = "envoyer message " + "version and returns the template created.",
	            response = Contact.class)
	    @RequestMapping(
	            value = "/pages/contact", method = RequestMethod.POST,produces = "application/json")
	    public Contact envoyerMessagerie(@RequestBody Contact msg) {
	    	Contact message =this.contactservice.EnvoyerMessage(msg);
	    	
	        return message;
	        	        
	    }
	    //getAllmessage
	    @RequestMapping(value = "/contact/all", method = RequestMethod.GET)
		public List<Contact> getAllMessages() {
			return this.contactservice.getAllMessages();
		}
		//getmessagebyId
	    @RequestMapping(value = "/conatct/detail/all", method = RequestMethod.GET)
		public Contact getDetailMessages(Long id) {
			return  this.contactservice.getById(id);
		}  
 // delete message by id 
	    
	    @ApiOperation(value = "delete message", notes = "Delete message by its ID.", response = String.class)
	    @ApiResponses( value = { @ApiResponse(code = 400, message = "Invalid message ID"),
	                     @ApiResponse(code = 404, message = "No artisant found for this ID"),
	                     @ApiResponse(code = 200, message = "The message was successfully deleted.") })
	    @RequestMapping( value = "/conatct/id/{id}", method = RequestMethod.DELETE)
	    
	    public ResponseEntity<String> deletemessage(@PathVariable( value = "id") Long id) 
	    {
	    	if (this.contactservice.messageExist(id)) {
	            this.contactservice.deleteMessage(id);;
	            return new ResponseEntity<>(HttpStatus.OK);
	              } 
	    	else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    } 
	    
	    
		@RequestMapping(value="/obtenirProjet/email/{email}" , method=RequestMethod.POST)
		public boolean ObtenirProjet(@PathVariable String email,@RequestBody Long idchantier)
		{
			System.out.println(idchantier);
			System.out.println(email);
			Artisant artisan =new Artisant();
			try {
				artisan	=this.artisantService.findByEmail(email);
			
			
			
		
			
			
			
			Chantier ch = new Chantier();
			
			
			
			 ch = this.chantierService.findById(idchantier);
			 
	
			
		
			
			List<Chantier> chantiers =new ArrayList<>();
				
			
			chantiers =this.chantierService.findByArtiant(artisan);
			
			long nbChantier =  chantiers.stream().count();
			
			
			
			System.out.println("nombre de chantier"+nbChantier);
		
			
			if( nbChantier<=2)
			{	
				Statut s= this.statutRepository.findByNom("encharge");
				
				
				ch.setStatut(s);
				ch.setEtat("encharge");
				ch.setArtisan(artisan);
				ch.setRDV("projet est en charge par un professionnel");
				this.chantierRepository.save(ch);
				System.out.println("nombre de chantier"+nbChantier);
				return true ;
				
			}
		
			
			else
				return false ;
			}
			catch (Exception e) {
				return false;
			}
		
		}
	    
}