package nirmalya.aatithya.restmodule.ambulance.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.ambulance.dao.RestAmbulaneProfileDao;
import nirmalya.aatithya.restmodule.ambulance.model.RestAmbulanceDocumentUpload;
import nirmalya.aatithya.restmodule.ambulance.model.RestAmbulaneProfileModel;



@RestController
@RequestMapping("ambulance/")
public class RestAmbulanceProfileController {
	Logger logger = LoggerFactory.getLogger(RestAmbulanceProfileController.class);

	@Autowired

	RestAmbulaneProfileDao restAmbulaneProfileDao;
	
	/*
	 * Edit For Doctor Profile
	 */

	@GetMapping(value = "editambulanceProfiles")
	public JsonResponse<RestAmbulaneProfileModel> editambulanceProfiles(@RequestParam String id) {
		logger.info("Method : editambulanceProfiles starts");
		
		logger.info("Method : editambulanceProfiles ends");
		return restAmbulaneProfileDao.editambulanceProfilesDao(id);
	}
	
	/*
	 * Edit For Doctor Identification
	 */

	@GetMapping(value = "editambulanceIdentifications")
	public JsonResponse<RestAmbulaneProfileModel> editambulanceIdentifications(@RequestParam String id) {
		logger.info("Method : editambulanceIdentifications starts");
		
		logger.info("Method : editambulanceIdentifications ends");
		return restAmbulaneProfileDao.editambulanceIdentifications(id);
	}
	
	/*
	 * For Home Address Edit
	 */

	@GetMapping(value = "editambulanceHomeAdress")
	public JsonResponse<RestAmbulaneProfileModel> editambulanceHomeAdress(@RequestParam String id) {
		logger.info("Method : editambulanceHomeAdress starts");
		
		logger.info("Method : editambulanceHomeAdress ends");
		return restAmbulaneProfileDao.editambulanceHomeAdressDao(id);
	}
	
	/*
	 * For Home Address View
	 */

	@GetMapping(value = "viewambulanceHomeAdress")
	public JsonResponse<RestAmbulaneProfileModel> viewambulanceHomeAdress(@RequestParam String id) {
		logger.info("Method : viewambulanceHomeAdress starts");
		
		logger.info("Method : viewambulanceHomeAdress ends");
		return restAmbulaneProfileDao.viewambulanceHomeAdressDao(id);
	}
	
	/*
	 * Post mapping for Doctor Profile
	 */
	@PostMapping(value = "addambulanceProfile")
	public JsonResponse<Object> addambulanceProfile(@RequestBody RestAmbulaneProfileModel employee) {
		logger.info("Method : addambulanceProfile starts");

		logger.info("Method : addambulanceProfile ends");
		return restAmbulaneProfileDao.addambulanceProfileDao(employee);
	}
	
	
	/*
	 * Post mapping for Doctor Identification
	 */
	@PostMapping(value = "addambulanceIdentification")
	public JsonResponse<Object> addambulanceIdentification(@RequestBody RestAmbulaneProfileModel employee) {
		logger.info("Method : addambulanceIdentification starts");

		logger.info("Method : addambulanceIdentification ends");
		return restAmbulaneProfileDao.addambulanceIdentificationDao(employee);
	}
	
	/*
	 * Post mapping for Doctor Contact Details
	 */
	@PostMapping(value = "addambulanceContactDetails")
	public JsonResponse<Object> addambulanceContactDetails(@RequestBody RestAmbulaneProfileModel employee) {
		logger.info("Method : addambulanceContactDetails starts");

		logger.info("Method : addambulanceContactDetails ends");
		return restAmbulaneProfileDao.addambulanceContactDetailsDao(employee);
	}
	
	/*
	 * for Country list
	 */
	@RequestMapping(value = "getCountryLists-ambulance", method = { RequestMethod.GET })
	public List<DropDownModel> getCountry() {
		logger.info("Method : getCountry starts");

		logger.info("Method : getCountry ends");
		return restAmbulaneProfileDao.getCountryListsambulanceDao();
	}
	
	/**
	 * Rest Controller - Get State List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getStateList-ambulance")
	public JsonResponse<List<DropDownModel>> getStateList(@RequestParam Integer id) {
		logger.info("Method : getStateList starts");

		logger.info("Method : getStateList ends");
		return restAmbulaneProfileDao.getStateListambulannceDao(id);
	}
	
	/**
	 * Rest Controller - Get district List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getDistList-ambulance")
	public JsonResponse<List<DropDownModel>> getDistLists(@RequestParam String state) {
		logger.info("Method : getDistLists starts");

		logger.info("Method : getDistLists ends");
		return restAmbulaneProfileDao.getDistListsambulanceDao(state);
	}
	/**
	 * Rest Controller - Get City List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getCityListambulance")
	public JsonResponse<List<DropDownModel>> getCityList(@RequestParam String dist) {
		logger.info("Method : getCityList starts");

		logger.info("Method : getCityList ends");
		return restAmbulaneProfileDao.getCityListambulanceDao(dist);
	}

	
	/*
	 * Add Document Upload
	 */
	@PostMapping(value = "rest-adddrDocumentUpload-ambulance")
	public ResponseEntity<JsonResponse<List<RestAmbulaneProfileModel>>> RestAmbulaneProfileModel(
			@RequestBody List<RestAmbulaneProfileModel> RestAmbulaneProfileModel) {
		logger.info("Method : adddrDocumentUpload starts");
		logger.info("Method : adddrDocumentUpload ends");
		return restAmbulaneProfileDao.restadddrDocumentUploadAmbulanceDao(RestAmbulaneProfileModel);
	}
	
	/*
	 * View data for Document Attachment
	 */
	@GetMapping(value = "rest-viewDocumentAttachment-ambulance")
	public JsonResponse<List<RestAmbulaneProfileModel>> viewDocumentAttachment(@RequestParam String id ,String rolid) {
		logger.info("Method :view DocumentAttachment starts");
		logger.info("Method :view DocumentAttachment ends");
		return restAmbulaneProfileDao.viewDocumentAttachmentAmbulanceDao(id,rolid);
		
	}
	/*
	 * download Document Attachment
	 */
	
	@GetMapping(value="rest-attachmentDownload-ambulance")
	public RestAmbulanceDocumentUpload restattachmentDownload(String id){
		logger.info("Method : rest attachmentDownload starts");
		
		logger.info("Method : restattachmentDownload ends");
		return restAmbulaneProfileDao.restattachmentDownloadAmbulanceDao(id);
	}
}
