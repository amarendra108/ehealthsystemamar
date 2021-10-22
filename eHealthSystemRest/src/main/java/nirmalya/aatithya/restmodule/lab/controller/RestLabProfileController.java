package nirmalya.aatithya.restmodule.lab.controller;

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
import nirmalya.aatithya.restmodule.lab.dao.RestLabProfileDao;
import nirmalya.aatithya.restmodule.lab.model.RestLabDocumentModel;
import nirmalya.aatithya.restmodule.lab.model.RestLabProfileModel;

@RestController
@RequestMapping("lab/")
public class RestLabProfileController {
	Logger logger = LoggerFactory.getLogger(RestLabProfileController.class);

	@Autowired

	RestLabProfileDao restLabProfileDao;
	

	/*
	 * Edit For Lab Profile
	 */

	@GetMapping(value = "rest-editlabProfile")
	public JsonResponse<RestLabProfileModel> resteditlabProfile(@RequestParam String id) {
		logger.info("Method : rest editlabProfile starts");
		
		logger.info("Method : rest editlabProfile ends");
		return restLabProfileDao.editlabProfileDao(id);
	}
	
	/*
	 * Edit For Lab Identification
	 */

	@GetMapping(value = "rest-editlabIdentification")
	public JsonResponse<RestLabProfileModel> resteditlabIdentification(@RequestParam String id) {
		logger.info("Method : rest editlabIdentification starts");
		
		logger.info("Method : resteditlabIdentification ends");
		return restLabProfileDao.editlabIdentificationDao(id);
	}
	
	/*
	 * For Home Address Edit
	 */

	@GetMapping(value = "rest-editlabHomeAdress")
	public JsonResponse<RestLabProfileModel> resteditlabHomeAdress(@RequestParam String id) {
		logger.info("Method : rest editlabHomeAdress starts");
		
		logger.info("Method : rest editlabHomeAdress ends");
		return restLabProfileDao.editlabHomeAdressDao(id);
	}
	
	/*
	 * For Home Address View
	 */

	@GetMapping(value = "rest-viewlabHomeAdress")
	public JsonResponse<RestLabProfileModel> restviewlabHomeAdress(@RequestParam String id) {
		logger.info("Method : rest viewlabHomeAdress starts");
		
		logger.info("Method : rest viewlabHomeAdress ends");
		return restLabProfileDao.viewlabHomeAdressDao(id);
	}
	
	/*
	 * Post mapping for Doctor Profile
	 */
	@PostMapping(value = "rest-addLabProfile")
	public JsonResponse<Object> addLabProfile(@RequestBody RestLabProfileModel employee) {
		logger.info("Method : rest addLabProfile starts");

		logger.info("Method : rest addLabProfile ends");
		return restLabProfileDao.addLabProfileDao(employee);
	}
	
	
	/*
	 * Post mapping for Lab Identification
	 */
	@PostMapping(value = "rest-addLabIdentification")
	public JsonResponse<Object> addLabIdentification(@RequestBody RestLabProfileModel employee) {
		logger.info("Method : rest addLabIdentification starts");

		logger.info("Method : rest addLabIdentification ends");
		return restLabProfileDao.addLabIdentificationDao(employee);
	}
	
	/*
	 * Post mapping for Lab Contact Details
	 */
	@PostMapping(value = "rest-addLabContactDetails")
	public JsonResponse<Object> addLabContactDetails(@RequestBody RestLabProfileModel employee) {
		logger.info("Method : rest addLabContactDetails starts");

		logger.info("Method : rest addLabContactDetails ends");
		return restLabProfileDao.addLabContactDetailsDao(employee);
	}
	
	/*
	 * for Country list
	 */
	@RequestMapping(value = "getCountryLists-lab", method = { RequestMethod.GET })
	public List<DropDownModel> getCountryListslab() {
		logger.info("Method : getCountry starts");

		logger.info("Method : getCountry ends");
		return restLabProfileDao.getCountryListslabDao();
	}
	
	/**
	 * Rest Controller - Get State List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getStateList-lab")
	public JsonResponse<List<DropDownModel>> getStateList(@RequestParam Integer id) {
		logger.info("Method : getStateList starts");

		logger.info("Method : getStateList ends");
		return restLabProfileDao.getStateListlabDao(id);
	}
	
	/**
	 * Rest Controller - Get district List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getDistList-lab")
	public JsonResponse<List<DropDownModel>> getDistLists(@RequestParam String state) {
		logger.info("Method : getDistLists starts");

		logger.info("Method : getDistLists ends");
		return restLabProfileDao.getDistListlabDao(state);
	}
	/**
	 * Rest Controller - Get City List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getCityListlab")
	public JsonResponse<List<DropDownModel>> getCityList(@RequestParam String dist) {
		logger.info("Method : getCityList starts");

		logger.info("Method : getCityList ends");
		return restLabProfileDao.getCityListlabDao(dist);
	}

	
	/*
	 * Add Document Upload
	 */
	@PostMapping(value = "rest-adddrDocumentUpload-lab")
	public ResponseEntity<JsonResponse<List<RestLabProfileModel>>> adddrDocumentUploadlab(
			@RequestBody List<RestLabProfileModel> RestLabProfileModel) {
		logger.info("Method : adddrDocumentUpload starts");
		logger.info("Method : adddrDocumentUpload ends");
		return restLabProfileDao.restadddrDocumentUploadLabDao(RestLabProfileModel);
	}
	
	/*
	 * View data for Document Attachment
	 */
	@GetMapping(value = "rest-viewDocumentAttachment-lab")
	public JsonResponse<List<RestLabProfileModel>> viewDocumentAttachmentlab(@RequestParam String id ,String rolid) {
		logger.info("Method :view DocumentAttachment starts");
		logger.info("Method :view DocumentAttachment ends");
		return restLabProfileDao.viewDocumentAttachmentlabDao(id,rolid);
		
	}
	/*
	 * download Document Attachment
	 */
	
	@GetMapping(value="rest-attachmentDownload-lab")
	public RestLabDocumentModel restattachmentDownloadlab(String id){
		logger.info("Method : rest attachmentDownload lab starts");
		
		logger.info("Method :rest attachmentDownload lab ends");
		return restLabProfileDao.attachmentDownloadlabDao(id);
	}
}

