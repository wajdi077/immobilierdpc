package com.dpc.controller;




import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.dpc.domain.Artisant;
import com.dpc.domain.Categorie;
import com.dpc.domain.Chantier;
import com.dpc.domain.Client;
import com.dpc.domain.Codepostale;
import com.dpc.domain.Mail;
import com.dpc.domain.Notifications;
import com.dpc.domain.Utilisateur;
import com.dpc.dto.ArtisanDisplayDto;
import com.dpc.dto.ArtisantDto;
import com.dpc.dto.CategorieDto;
import com.dpc.dto.ChantierDto;
import com.dpc.dto.ClientArtisanDto;
import com.dpc.dto.ClientDto;
import com.dpc.dto.CodepostaleDto;
import com.dpc.dto.UserDto;
import com.dpc.dto.UtilisteurDto;
import com.dpc.repository.IAuthority;
import com.dpc.repository.UserRepository;
import com.dpc.service.ActivityService;
import com.dpc.service.ArtisantService;
import com.dpc.service.CategorieService;
import com.dpc.service.ClientService;
import com.dpc.service.CodepostaleService;
import com.dpc.service.EmailService;
import com.dpc.service.MailServiceImp;

import com.dpc.service.UserService;

import freemarker.template.TemplateException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")

@RestController

@RequestMapping("/immobilier/User")

public class DpcImmobilierGestionUser extends MainController {
	@Autowired
	UserService userService;
	@Autowired
	ClientService clientService;
	@Autowired
	ArtisantService artisantService;
	
	@Autowired 
	CategorieService categorieservice;
	@Autowired 
	ActivityService activityservice;
	@Autowired
	CodepostaleService codepostaleservice ;
	@Autowired
	MailServiceImp mailservice ;
	@Autowired
	private UserRepository userRespository;
	
	
	ArtisantDto artisantDto = null;
	ClientDto clientDto = null;
	UserDto usertDto = null;
	@Autowired
	IAuthority roleRepo;
	@Autowired
	private SimpMessagingTemplate template;
	
	Notifications  notif =new  Notifications(0);
	
	@RequestMapping(value="/username/{username}",method=RequestMethod.PUT)
	  public Utilisateur UpdatePassword(@PathVariable String username,@RequestBody Utilisateur u)
	  {
		  
		 Utilisateur us = new Utilisateur();
		
		  us=this.userService.getBylogin(username);
		//  us.setUsername(username);
		  us.setPassword(u.getPassword());
		  return this.userService.updateUser(us);
		   
	  }
    
    /*détails user(client et artisan)*/
		
			@RequestMapping(value = "/chantierDetails/{username}", method = RequestMethod.GET)
			
	public ClientArtisanDto  DetailUser(@PathVariable String username ) {
				System.out.println("profole"+username);
				Utilisateur u = this.userRespository.findByUsername(username);
				System.out.println("profole"+u.getProfil());
				if (u.getProfil().equals("Client"))
					{
					ClientArtisanDto cls= new ClientArtisanDto();
					Client c = this.clientService.findByEmail(username);
					System.out.println("client est "+c.getNom());
				//	cls= convertClientsToDto(c);
					cls.setEmail(c.getEmail());
					cls.setNom(c.getNom());
					cls.setPrenom(c.getPrenom());
					cls.setTelephone(c.getTelephone());
					cls.setEnabled(u.isEnabled());
					 return cls;
					}
				else{
					
						ClientArtisanDto cl= new ClientArtisanDto();
						Artisant a =this.artisantService.findByEmail(username);
						System.out.println("lartisant est "+a.getNom());
					  //  cl =convertArtisantsToDto(a);
						cl.setEmail(a.getEmail());
						cl.setNom(a.getNom());
						cl.setPrenom(a.getPrenom());
						cl.setTelephone(a.getTelephone());
						cl.setEnabled(u.isEnabled());
						return cl;
				
				}
			
		
			
			}

	// Create new admin user
    @ApiOperation(
            value = "create usert",
            notes = "create user" + "version and returns the user created.",
            response = UtilisteurDto.class)
    @RequestMapping(
            value = "/add/admin", method = RequestMethod.POST)
    public UtilisteurDto createadmin(@RequestBody UtilisteurDto utilisateurDto) {
    	
        Utilisateur u = dtoToUtilisateur(utilisateurDto);
        u.setProfil("Admin");
        u.setAuthorities(this.roleRepo.findByname("ROLE_"+u.getProfil()));
        u.setEnabled(true);
        u.setCreatedOn(u.getCreatedOn());
        u.setLastPasswordResetDate(new Date(System.currentTimeMillis()));
        Utilisateur userCreated = this.userService.creatUser(u);
        return convertUtilisateurToDto(userCreated);
    }
	
	
	
	// -----/immobilier/GestionUser/artisant/all

	
		// All users
		@RequestMapping(value = "/user/all", method = RequestMethod.GET)
         public Collection<UserDto> getAllUsers() {
				return this.userService.getAllUser().stream().map(user-> convertUserToDto(user))
					.collect(Collectors.toList());
		}
		
		
		
		
		 
	    @ApiOperation(value = "delete user", notes = "Delete user by its ID.", response = String.class)
	    @ApiResponses( value = { @ApiResponse(code = 400, message = "Invalid user ID"),
	                     @ApiResponse(code = 404, message = "No chantier found for this ID"),
	                     @ApiResponse(code = 200, message = "The chantier was successfully deleted.") })
	    @RequestMapping( value = "/delete/login/{login}", method = RequestMethod.DELETE)
	    
	    public ResponseEntity<String> deleteuser(@PathVariable( value = "login") String login) 
	    {  this.userService.deleteUser(login);
	            return new ResponseEntity<>(HttpStatus.OK);
	               
	    } 
		
		
	
	
	


	// send mail

	 
	 
		// Create new user profile artisant
    @ApiOperation(
            value = "create new artisant",
            notes = "create artisant " + "version and returns the created artisant", response = UserDto.class )
    @RequestMapping(value = "artisant/add", method = RequestMethod.POST)
    public UserDto createartisant(@RequestBody ArtisantDto artisantDto) {
    
 /*   long code = artisantDto.getCodePostale();
    
    String activite=artisantDto.getActivitee();
    System.out.println("dto activite"+activite);
    Utilisateur r= new Utilisateur();
    
    	Artisant a = dtoToArtisant (artisantDto);
    	a.setCodepostale(this.codepostaleservice.findByCode(code));
    	a.setActivite(this.activityservice.getByNom(activite));
    	 System.out.println("Nom actvité"+(this.activityservice.getByNom(activite)));
    	r=this.userService.getBylogin(r.getUsername());
    	a.setUser(r);
    
    	Utilisateur userCreated = this.userService.createUserArtisant(a);
 */
        long code = artisantDto.getCodePostale();
        String activite=artisantDto.getActivite();
        Utilisateur r= new Utilisateur();
        
        	Artisant a = dtoToArtisant (artisantDto);
        	a.setCodepostale(this.codepostaleservice.findByCode(code));
        	a.setActivite(this.activityservice.getByNom(activite));
        	r=this.userService.getBylogin(r.getUsername());
        	a.setUser(r);
        
        	Utilisateur userCreated = this.userService.createUserArtisant(a);
    	
    	SimpleMailMessage registrationEmail = new SimpleMailMessage();
 
		registrationEmail.setTo(a.getEmail());
		registrationEmail.setSubject("Compte Professionnel ImmoDevis");
		registrationEmail.setFrom("immo.devis18@gmail.com");
		String content = "Notre cher Professionnel,\n " + ""+ userCreated.getArtisant().getNom()+" " + userCreated.getArtisant().getPrenom()+"\r\n" 
		+ "Bienvenu à notre platforme ImmoDevis"+"\r\n" +"Votre Login est:"+" " +userCreated.getUsername()
		+"\r\n"+"Votre Mot de passe est :"+ userCreated.getPassword()+ "\r\n" +"\r\n"; 
    
         
       registrationEmail.setText(content +	
       		"Ceci le lien de votre Espace profesionnel\r\n" +"http://207.180.211.159/ImmoProfessionel/Acceuil\"\r\n"
       	+ 
       		" \r\n" + 
       		" \r\n" +
       		"Merci d'avoir choisi ImmoDevis!"+"\r\n"+
       		"À très bientôt,"+"\r\n"+
       		"L'équipe ImmoDevis."+"\r\n"
       	
       	 );
       List < Object > attachments = new ArrayList < Object > ();
       attachments.add(new ClassPathResource("immo devis.bmp"));
       //registrationEmail.setAttachments(attachments);
      
     
		this.mailservice.sendEmail(registrationEmail);
    	
        return convertUserToDto(userCreated);
    	
} 
    @Autowired
    private EmailService emailService;
    
    

	// Create new user profile client
@ApiOperation(
        value = "create new client",
        notes = "create client " + "version and returns the created client", response = UserDto.class )
@RequestMapping(value = "incrit/client", method = RequestMethod.POST)
public UserDto inscriClient(@RequestBody ClientDto clientDto) throws MessagingException, IOException, TemplateException {
            Utilisateur r= new Utilisateur();
          Client c = dtoToClient(clientDto);
	r=this.userService.getBylogin(c.getEmail());
	System.out.println("mailContro"+c.getEmail());
	c.setUser(r);

	Utilisateur userCreated = this.userService.inscritClient(c);

	//SimpleMailMessage registrationEmail = new SimpleMailMessage();
	//registrationEmail.setTo(c.getEmail());
	//registrationEmail.setSubject("Votre compte été bien creé");
	//registrationEmail.setFrom("immo.devis18@gmail.com");
	//String content = "Dear " + c.getNom()+" + " + c.getPrenom()+"+"+ userCreated.getUsername()+"+"+ userCreated.getPassword() ;
 
   //  System.out.println("Message"+content);
     
  // registrationEmail.setText(content + "</p><img src='cid:\"immo devis.bmp\"></body></html>");

	//this.mailservice.sendEmail(registrationEmail);
	//notif.increment();
	//System.out.println("notifications"+notif.increment(););

	// Push notifications to front-end
	
	
	Mail mail = new Mail();
    mail.setFrom("no-reply@memorynotfound.com");
    mail.setTo(c.getEmail());
    mail.setSubject("votre compte été bien creé ");

      Map<String, Object> model = new HashMap<String, Object>();

      model.put("name", c.getNom());
      model.put("location", "TUNISIE");
      model.put("signature", "http://DIGITALPOWERCONSULTING.COM");
      model.put("login", c.getEmail());
      model.put("pass", userCreated.getPassword());
      mail.setModel(model);

      
      
      this.emailService.sendSimpleMessageinscri(mail);

      	
	
	
	
//	template.convertAndSend("/topic/notification", notif);
	
    return convertUserToDto(userCreated);
    	
}
    
    

//bloquer user

	 @ApiOperation(
	            value = "bloquer user",
	            notes = "bloquer user " + "version and returns the bloquer user.",
	            response = UserDto.class)
	    @RequestMapping(
	            value = "/bloquerUser/id/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> bloquerUser( @PathVariable( value = "id") String id )
	{
        this.userService.BloquerUser(id);;
     return new ResponseEntity<>(HttpStatus.OK);
		
	} 

	//Débloquer user

		 @ApiOperation(
		            value = "debloquer user",
		            notes = "debloquer user " + "version and returns the debloquer user.",
		            response = UserDto.class)
		    @RequestMapping(
		            value = "/debloquerUser/id/{id}", method = RequestMethod.POST)
		public ResponseEntity<String> debloquerUser( @PathVariable( value = "id") String id )
		{
	        this.userService.DebloquerUser(id);
	     return new ResponseEntity<>(HttpStatus.OK);
			
		} 
    
    
    
    
    
    
    
    
    
    
}