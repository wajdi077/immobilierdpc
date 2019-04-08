package com.dpc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dpc.dto.ActivityDto;
import com.dpc.dto.ArtisantDto;
import com.dpc.dto.CategorieDto;
import com.dpc.dto.ChantierDto;
import com.dpc.dto.CodepostaleDto;
import com.dpc.dto.RegionDto;
import com.dpc.dto.VilleDto;
import com.dpc.service.ActivityService;
import com.dpc.service.ArtisantService;
import com.dpc.service.CategorieService;
import com.dpc.service.ChantierService;
import com.dpc.service.CodepostaleService;
import com.dpc.service.ReferentielService;
import com.dpc.service.RegionService;
import com.dpc.service.VilleService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")

@RestController

@RequestMapping("/immobilier/referentiel")

public class DpcImmobilierReferencielController extends MainController {

	@Autowired
	private ReferentielService referentielService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private VilleService villeService;
	@Autowired
	private CodepostaleService codepostaleService;

	@Autowired
	private RegionService regionService;

	@Autowired
	private CategorieService categorieService;
	@Autowired
	private ArtisantService artisantService;

	@Autowired
	private ChantierService chantierService;
	//@Autowired
	//private StatutService statutservice ;

	// -----/immobilier/referentiel/activities/all

	@ApiOperation(value = "getallactivities", notes = "afficher all activities.", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
			@ApiResponse(code = 404, message = "No bundle found for this key") })
	@RequestMapping(value = "/activities/all", method = RequestMethod.GET)
	public Collection<ActivityDto> getAllactivities() {
		//listCategories = 
		return this.activityService.getAllactivities().stream().map(activite -> convertActivityToDto(activite))
				.collect(Collectors.toList());
	}
	
	

	// -----/immobilier/referentiel/region/all
	@ApiOperation(value = "getallregion", notes = "get all region", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
			@ApiResponse(code = 404, message = "No region found for this key"),
			@ApiResponse(code = 200, message = "The bundle was successfully deleted.") })
	@RequestMapping(value = "/region/all", method = RequestMethod.GET)
	public Collection<RegionDto> getAllregion() {
		return this.regionService.getAllregion().stream().map(region -> convertRegionToDto(region))
				.collect(Collectors.toList());
	}

	// -----/immobilier/referentiel/ville/all
	@ApiOperation(value = "getallville", notes = "get a ville entry via its key.", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
			@ApiResponse(code = 404, message = "No region found for this key"),
			@ApiResponse(code = 200, message = "The bundle was successfully deleted.") })
	@RequestMapping(value = "/ville/all", method = RequestMethod.GET)
	public Collection<VilleDto> getAllville() {
		return this.villeService.getAllVille().stream().map(ville -> convertVilleToDto(ville))
				.collect(Collectors.toList());
	}

	// -----/immobilier/referentiel/codepostale/all
	@ApiOperation(value = "getAllcodepostale", notes = "get a codepostale entry via its key.", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
			@ApiResponse(code = 404, message = "No region found for this key"),
			@ApiResponse(code = 200, message = "The bundle was successfully deleted.") })
	@RequestMapping(value = "/codepostale/all", method = RequestMethod.GET)
	public Collection<CodepostaleDto> getAllcodepostale() {
		return this.codepostaleService.getAllCodepostale().stream()
				.map(codepostale -> convertCodepostaleToDto(codepostale)).collect(Collectors.toList());
	}

	// -----/immobilier/referentiel/artisant/all

	/*@ApiOperation(value = "getAllartisant", notes = "Delete a artisant entry via its key.", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
			@ApiResponse(code = 404, message = "No artisant found for this key"),
			@ApiResponse(code = 200, message = "The artisant was successfully deleted.") })
	@RequestMapping(value = "/artisant/all", method = RequestMethod.GET)
	public Collection<ArtisantDto> getAllartisant() {
		return this.artisantService.getAllartisant().stream().map(artisant -> convertArtisantToDto(artisant))
				.collect(Collectors.toList());
	}
*/
	// -----/immobilier/referentiel/chantier/all

	@ApiOperation(value = "getallchantier", notes = "afficher all chantier .", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
			@ApiResponse(code = 404, message = "No chantier found for this key"),
			@ApiResponse(code = 200, message = "The chantier was successfully deleted.") })
	@RequestMapping(value = "/chantier/all", method = RequestMethod.GET)
	public Collection<ChantierDto> getAllchantier() {
		return this.chantierService.getAllchantier().stream().map(chantier -> convertChantierToDto(chantier))
				.collect(Collectors.toList());
	}

	// -----/immobilier/referentiel/categorie/all

	@ApiOperation(value = "getallcategories", notes = "get a categorie entry via its key.", response = String.class)

	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid key"),
			@ApiResponse(code = 404, message = "No categorie found for this key"),
			@ApiResponse(code = 200, message = "The categorie was successfully deleted.") })
	@RequestMapping(value = "/categories/all", method = RequestMethod.GET)
	public Collection<CategorieDto> getAllCategories() {
		return this.categorieService.getAllCategories().stream().map(categorie -> convertCategorieToDto(categorie))
				.collect(Collectors.toList());
	}

	// --------/immobillier/referentiel/initreferentiel

	@SuppressWarnings("resource")
	@ApiOperation(value = "initReferentiel", notes = "Init Region,Ville,Codepostale,catrgorie et Activite from an excel "
			+ "file", response = String.class)

	@RequestMapping(value = "/initReferentiel", method = RequestMethod.POST, consumes = { "multipart/*" })

	public String initReferentiel(@RequestParam("file") MultipartFile file) throws IOException {

		File referentielFile = convertFromMultiPart(file);

		String regionName;
		String villeName;
		String codePostaleName;
		Double codePostaleCode;
		String activiteName;
		String categorieName;
		String photoName ;

		try {
			FileInputStream excelFile = new FileInputStream(referentielFile);
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet region = workbook.getSheetAt(ZERO);
			Sheet ville = workbook.getSheetAt(UN);
			Sheet codePostale = workbook.getSheetAt(DEUX);
			Sheet activite = workbook.getSheetAt(TROIS);
			Sheet categorie = workbook.getSheetAt(QUATRE);

			Iterator<Row> itLigneRegion = region.iterator();
			Iterator<Row> itLigneVille = ville.iterator();
			Iterator<Row> itLigneCodePostale = codePostale.iterator();
			Iterator<Row> itLigneActivite = activite.iterator();
			Iterator<Row> itLigneCategorie = categorie.iterator();
			Row currentLigneRegion = itLigneRegion.next();
			Row currentLigneVille = itLigneVille.next();
			Row currentLigneCodePostale = itLigneCodePostale.next();
			Row currentLigneActivite = itLigneActivite.next();
			Row currentLigneCategorie = itLigneCategorie.next();
			

			// insérer les regions du referentiel
			while (itLigneRegion.hasNext()) {
				currentLigneRegion = itLigneRegion.next();
				if ((currentLigneRegion.getCell(0) != null)
						&& !(currentLigneRegion.getCell(0).toString().equalsIgnoreCase(""))) {
					regionName = currentLigneRegion.getCell(ZERO).getStringCellValue();

					this.referentielService.createRegion(regionName);
				}
			}
			// insérer les villes du referentiel
			while (itLigneVille.hasNext()) {
				currentLigneVille = itLigneVille.next();
				if ((currentLigneVille.getCell(0) != null)
						&& !(currentLigneVille.getCell(0).toString().equalsIgnoreCase(""))) {
					villeName = currentLigneVille.getCell(ZERO).getStringCellValue();
					regionName = currentLigneVille.getCell(UN).getStringCellValue();
					this.referentielService.createVille(regionName, villeName);
				}
			}
			// insérer les codePostel du referentiel
			while (itLigneCodePostale.hasNext()) {
				currentLigneCodePostale = itLigneCodePostale.next();
				if ((currentLigneCodePostale.getCell(0) != null)
						&& !(currentLigneCodePostale.getCell(0).toString().equalsIgnoreCase(""))) {
					codePostaleName = currentLigneCodePostale.getCell(ZERO).getStringCellValue();
					codePostaleCode = currentLigneCodePostale.getCell(UN).getNumericCellValue();
					villeName = currentLigneCodePostale.getCell(DEUX).getStringCellValue();
					this.referentielService.createCodePostale(codePostaleName, codePostaleCode, villeName);
				}
			}
			// insérer les activites du referentiel
			while (itLigneActivite.hasNext()) {
				currentLigneActivite = itLigneActivite.next();
				if ((currentLigneActivite.getCell(0) != null)
						&& !(currentLigneActivite.getCell(0).toString().equalsIgnoreCase(""))) {
					activiteName = currentLigneActivite.getCell(ZERO).getStringCellValue();
					this.referentielService.createActivite(activiteName);
				}
			}
			// insérer les categories du referentiel
			while (itLigneCategorie.hasNext()) {
				currentLigneCategorie = itLigneCategorie.next();
				if ((currentLigneCategorie.getCell(0) != null)
						&& !(currentLigneCategorie.getCell(0).toString().equalsIgnoreCase(""))) {
					        categorieName = currentLigneCategorie.getCell(ZERO).getStringCellValue();
							activiteName = currentLigneCategorie.getCell(UN).getStringCellValue();
							photoName=currentLigneCategorie.getCell(DEUX).getStringCellValue();

					this.referentielService.createCategorie(categorieName,activiteName,photoName);
				}
			}
			return "The referetiel service was passed successfully : Region,Ville,Codepostale,catrgorie et Activite have been "
					+ "initialized.";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "Can not load reference.xls";
		}

	}

	public File convertFromMultiPart(MultipartFile multipartFile) throws IllegalStateException, IOException {
		File convFile = new File(FilenameUtils.getName(multipartFile.getOriginalFilename()));
		multipartFile.transferTo(convFile);
		return convFile;
	}

}