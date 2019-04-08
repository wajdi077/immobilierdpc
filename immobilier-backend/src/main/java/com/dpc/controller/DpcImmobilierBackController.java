package com.dpc.controller;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.dpc.domain.Activite;
import com.dpc.domain.Agence;
import com.dpc.domain.Artisant;
import com.dpc.domain.Categorie;
import com.dpc.domain.Chantier;
import com.dpc.domain.Client;
import com.dpc.domain.Codepostale;
import com.dpc.domain.Historique;
import com.dpc.dto.ActivityDto;
import com.dpc.dto.AgenceDto;
import com.dpc.dto.ArtisanDisplayDto;
import com.dpc.dto.ArtisantDto;
import com.dpc.dto.CategorieDto;
import com.dpc.dto.ChantierDto;
import com.dpc.dto.ChantierIdDto;
import com.dpc.dto.ClientChantierDto;
import com.dpc.dto.ClientDto;
import com.dpc.dto.CodepostaleDto;
import com.dpc.dto.HistoriqueDto;
import com.dpc.repository.CategorieRepository;
import com.dpc.service.ActivityService;
import com.dpc.service.AgenceService;
import com.dpc.service.ArtisantService;
import com.dpc.service.CategorieService;
import com.dpc.service.ChantierService;
import com.dpc.service.ClientService;
import com.dpc.service.CodepostaleService;
import com.dpc.service.HistoriqueService;
import com.dpc.service.StorageService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@CrossOrigin("*")

@RestController

@RequestMapping("/immobilier/back")

public class DpcImmobilierBackController extends MainController {

	@Autowired
	private ArtisantService artisantService;

	@Autowired
	private ClientService clientService;
	@Autowired
	CodepostaleService codepostaleservice ;
	@Autowired 
	ActivityService activityservice;
	
	@Autowired 
	HistoriqueService histservice;
	
	@Autowired
	AgenceService agenceservice ;
	
	@Autowired
	private ChantierService chantierService;
	@Autowired
	private StorageService storageService;
	@Autowired
	private CategorieRepository catRepo;
	@Autowired
	public CategorieService catService;
	
	ClientDto clientDto = null;

	CategorieDto categorieDto= null;
	
	List<String> files = new ArrayList<String>();
	//public List<Artisant>
	
	
	
	
	//upload image 
	
	@PostMapping("/upload")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			storageService.store(file);
			files.add(file.getOriginalFilename());
		

			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
	/*@GetMapping("/getallfiles")
	public ResponseEntity<List<String>> getListFiles(Model model) {
		List<String> fileNames = files
				.stream().map(fileName -> MvcUriComponentsBuilder
						.fromMethodName(DpcImmobilierBackController.class, "getFile", fileName).build().toString())
				.collect(Collectors.toList());
 
		return ResponseEntity.ok().body(fileNames);
	}
	*/
	
	@RequestMapping(value="/getallfiles",method=RequestMethod.GET)
	public ResponseEntity<List<String>> getListFiles(@RequestParam String nomact,Model model) {
		files = this.catRepo.filenames(nomact)
				.stream().map(fileName -> MvcUriComponentsBuilder
				.fromMethodName(DpcImmobilierBackController.class, "getFile", fileName).build().toString())
				.collect(Collectors.toList());
 
		return ResponseEntity.ok().body(files);
	}
	
	@RequestMapping(value="/files/{filename:.+}",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> getFile(@PathVariable String filename) throws IOException {
		Resource file = storageService.loadFile(filename);
		byte[] bytes = StreamUtils.copyToByteArray(file.getInputStream());

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
	}
	//publier chantier

	 @ApiOperation(
	            value = "publierchantier",
	            notes = "publier chantier  " + "version and returns the chantier publié."
	           )
	    @RequestMapping(
	            value = "/publierchantier/id/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> PublierChantier( @PathVariable( value = "id") Long id)
	{
         this.chantierService.publierchantier(id);
      return new ResponseEntity<>(HttpStatus.OK);
		
	}
//bloquer chantier
	 @ApiOperation(
	            value = "bloquerchantier",
	            notes = "bloquer chantier  " + "version and returns the chantier publié."
	           )
	    @RequestMapping(
	            value = "/bloquerchantier/id/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> BloquerChantier( @PathVariable( value = "id") Long id )
	{
         this.chantierService.Bloquerchantier(id);
      return new ResponseEntity<>(HttpStatus.OK);
		
	}
	 
	 
	 
		@ApiOperation(value = "getAllartisant", notes = "Delete a artisant entry via its key.", response = String.class)
		@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
				@ApiResponse(code = 404, message = "No artisant found for this key"),
				@ApiResponse(code = 200, message = "The artisant was successfully deleted.") })
		@RequestMapping(value = "/agence/edit/{id}", method = RequestMethod.POST)
		public Agence  updateinfosagence(@PathVariable Long id ,  @RequestBody AgenceDto agencedto) {
			
			agencedto.setId(id);
			//Agence agenceupdated = this.agenceservice.findagence(id);
			Agence a = this.convertAgenceDtotoEntiry(agencedto);
              this.agenceservice.UpdateAgence(a);
           return  a ;
		
		}

	// -----/immobilier/back/artisant/all

	@ApiOperation(value = "getAllartisant", notes = "Delete a artisant entry via its key.", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
			@ApiResponse(code = 404, message = "No artisant found for this key"),
			@ApiResponse(code = 200, message = "The artisant was successfully deleted.") })
	@RequestMapping(value = "/artisant/all", method = RequestMethod.GET)
	public Collection<ArtisanDisplayDto> getAllartisant() {
		
		
		return this.artisantService.getAllartisant().stream().map(artisant -> convertArtisantdisplayToDto(artisant))
				.collect(Collectors.toList());
	}
	// -----/immobilier/back/historique/all

		@ApiOperation(value = "getAllHistorique", notes = "hist.", response = String.class)
		@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
				@ApiResponse(code = 404, message = "No "),
				@ApiResponse(code = 200, message = "show") })
		@RequestMapping(value = "/historique/all", method = RequestMethod.GET)
		public Collection<HistoriqueDto> getAllHistorique() {
			
			
	return this.histservice.getAllHistorique().stream().map(historic -> convertHistoricToDto(historic))
					
					.collect(Collectors.toList());
		}
		
	@ApiOperation(value = "getAllartisantsansdto", notes = "Delete a artisant entry via its key.", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
			@ApiResponse(code = 404, message = "No artisant found for this key"),
			@ApiResponse(code = 200, message = "The artisant was successfully deleted.") })
	@RequestMapping(value = "/artisants/id/{id}", method = RequestMethod.GET)
	
	public ArtisanDisplayDto getAllartisants(@PathVariable Long id) {
		ArtisanDisplayDto artisand = new ArtisanDisplayDto();
		Artisant a =new Artisant();
		a= artisantService.getartisant_userById(id);
		Codepostale codepostale = a.getCodepostale();
		CodepostaleDto codedto = convertCodepostaleToDto(codepostale);
	
		Activite act = a.getActivite();
		ActivityDto actdto = convertActivityToDto(act);
		artisand = convertArtisantdisplayToDto(a);
		artisand.setActivite(actdto);
		artisand.setCodepostale(codedto);
		return artisand;
}

	//allartisans
		@RequestMapping(value = "/artisants/email/{email}", method = RequestMethod.GET)
		
		public ArtisanDisplayDto getAllartisantByMail(@PathVariable String  email) {
			ArtisanDisplayDto artisand = new ArtisanDisplayDto();
			
			System.out.println("input1 "+email);
			
			Artisant a =new Artisant();
			
			a= artisantService.findByEmail(email);
			
		//	System.out.println("emaillSER"+artisantService.findByEmail(email));
			
		
		//System.out.println("email***"+a.getEmail());
//		System.out.println("codepss"+ a.getCodepostale());
		
			Codepostale codepostale = a.getCodepostale();
			
			CodepostaleDto codedto = convertCodepostaleToDto(codepostale);
			
			
			//System.out.println("codepSSSS"+codedto );
		
			//Activite act = a.getActivite();
			//ActivityDto actdto = convertActivityToDto(act);
			artisand = convertArtisantdisplayToDto(a);
			//artisand.setActivite(actdto);
			artisand.setCodepostale(codedto);
			return artisand;
	}
	
	
	// -----/immobilier/back/artisant/id

		@ApiOperation(value = "getartisantbyId", notes = "afficher chaque artisant par son Id.", response = Number.class)
		@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
				@ApiResponse(code = 404, message = "No artisant found for this key"),
				@ApiResponse(code = 200, message = "The artisant was successfully .") })
		@RequestMapping(value = "/artisant/id/{id}", method = RequestMethod.GET)
		public Collection<ArtisantDto> getartisantById(@PathVariable Long id) {
			return this.artisantService.getartisantById(id).stream().map(artisant -> convertArtisantToDto(artisant))
					.collect(Collectors.toList());	
		}
		

		/*details chantiers*/

				@ApiOperation(value = "getchantierbyId", notes = "afficher chaque chantier par son Id.", response = Number.class)
			
				@RequestMapping(value = "/chantierDetails/id/{id}", method = RequestMethod.GET)
				
				public ChantierDto  getchantierById(@PathVariable Long id) {
					String ville;
					Chantier c =  chantierService.findById(id) ;
					
					
					Codepostale codep=c.getCodepostale();
					CodepostaleDto codepdto = convertCodepostaleToDto(codep);
					
					Client client = c.getClient();
					ClientDto  clientdto = convertClientToDto(client);
					
					Categorie cat = c.getCategorie();
					CategorieDto catdto = convertCategorieToDto(cat);
					
					ChantierDto chantier = new ChantierDto();
					chantier = convertChantierToDto(c);
					chantier.setClient(clientdto);
					chantier.setCategorie(catdto);
					return chantier;
				}

	// -----/immobilier/back/chantier/all

	@ApiOperation(value = "getallchantier", notes = "afficher all chantier .", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
			@ApiResponse(code = 404, message = "No chantier found for this key"),
			@ApiResponse(code = 200, message = "The chantier was successfully deleted.") })
	@RequestMapping(value = "/chantier/all", method = RequestMethod.GET)
	public Collection<ChantierDto> getAllchantier() {
		return this.chantierService.getAllchantier().stream().map(chantier -> convertChantierToDto(chantier))
				.collect(Collectors.toList());
	}
	
	
	

	// -----/immobilier/back/agence/all

	@ApiOperation(value = "getallagence", notes = "afficher all infos agence .", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
			@ApiResponse(code = 404, message = "No agence found for this key"),
			@ApiResponse(code = 200, message = "The agence was successfully deleted.") })
	@RequestMapping(value = "/agence/all", method = RequestMethod.GET)
	public Collection<Agence> getAllagence() {
		return this.agenceservice.getAgence();
	}
	
	
	// getallchantier sans dto
	

	@ApiOperation(value = "getallchantiers", notes = "afficher all chantier .", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
			@ApiResponse(code = 404, message = "No chantier found for this key"),
			@ApiResponse(code = 200, message = "The agence was successfully deleted.") })

	@RequestMapping(value = "/chantier/allid", method = RequestMethod.GET)
	public Collection<ChantierIdDto> getAllchantiers() {
	
		return this.chantierService.getAllchantier().stream().map(chantier -> convertChantierIdToDto(chantier))
				.collect(Collectors.toList()); 
		}
	
	// -----/immobilier/back/chantier/etat

	@ApiOperation(value = "getchantierbyetat", notes = "afficher les chantier suivant etat.", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
			@ApiResponse(code = 404, message = "No chantier found for this key"),
			@ApiResponse(code = 200, message = "The chantier was successfully .") } )
	@RequestMapping(value = "/chantier/etat/{etat}", method = RequestMethod.GET)
	public Collection<ChantierDto> getchantierByEtat(@PathVariable String etat) {
		return this.chantierService.getChantierByEtat(etat).stream().map(chantier -> convertChantierToDto(chantier))
				.collect(Collectors.toList());
	}
	

	
	
	// -----/immobilier/back/chantier/region

	@ApiOperation(value = "getChantiersByRegion", notes = "revoie la liste des chantiers par région.", response = ChantierDto.class)

	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
			@ApiResponse(code = 404, message = "No categorie found for this key"),
			@ApiResponse(code = 200, message = "The categorie was successfully deleted.") })
	@RequestMapping(value = "/chantiers/region/nomregion/{nomregion}", method = RequestMethod.GET)
	public Collection<ChantierDto> getChantiersByRegion(@PathVariable String nomregion) {
		return this.chantierService.getChantiersByRegion(nomregion).stream().map(chn -> convertChantierToDto(chn))
				.collect(Collectors.toList());
	}
	
	
	 // delete chantier by id 
    
    @ApiOperation(value = "delete chantier", notes = "Delete chantier by its ID.", response = String.class)
    @ApiResponses( value = { @ApiResponse(code = 400, message = "Invalid artisant ID"),
                     @ApiResponse(code = 404, message = "No chantier found for this ID"),
                     @ApiResponse(code = 200, message = "The chantier was successfully deleted.") })
    @RequestMapping( value = "/back/chantier/id/{id}", method = RequestMethod.DELETE)
    
    public ResponseEntity<String> deletechantier(@PathVariable( value = "id") Long id) 
    {  this.chantierService.deletechantier(id);
            return new ResponseEntity<>(HttpStatus.OK);
               
    } 
	
	
}