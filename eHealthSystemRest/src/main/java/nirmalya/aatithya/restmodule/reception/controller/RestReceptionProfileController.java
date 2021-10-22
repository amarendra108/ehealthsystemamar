package nirmalya.aatithya.restmodule.reception.controller;

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
import nirmalya.aatithya.restmodule.reception.dao.RestReceptionProfileDao;
import nirmalya.aatithya.restmodule.reception.model.RestReceptionProfileDocumentModel;
import nirmalya.aatithya.restmodule.reception.model.RestReceptionProfileModel;



@RestController
@RequestMapping("reception/")
public class RestReceptionProfileController {
	Logger logger = LoggerFactory.getLogger(RestReceptionProfileController.class);

	@Autowired

	RestReceptionProfileDao restReceptionProfileDao;
	
	/*
	 * Edit For Doctor Profile
	 */

	@GetMapping(value = "editreceptionProfiles")
	public JsonResponse<RestReceptionProfileModel> editreceptionProfiles(@RequestParam String id) {
		logger.info("Method : editreceptionProfiles starts");
		
		logger.info("Method : editreceptionProfiles ends");
		return restReceptionProfileDao.editdoctorProfileDao(id);
	}
	
	/*
	 * Edit For Doctor Identification
	 */

	@GetMapping(value = "editreceptionIdentifications")
	public JsonResponse<RestReceptionProfileModel> editreceptionIdentifications(@RequestParam String id) {
		logger.info("Method : editreceptionIdentifications starts");
		
		logger.info("Method : editreceptionIdentifications ends");
		return restReceptionProfileDao.editreceptionIdentificationsDao(id);
	}
	
	/*
	 * For Home Address Edit
	 */

	@GetMapping(value = "editreceptionHomeAdress")
	public JsonResponse<RestReceptionProfileModel> editreceptionHomeAdress(@RequestParam String id) {
		logger.info("Method : editreceptionHomeAdress starts");
		
		logger.info("Method : editreceptionHomeAdress ends");
		return restReceptionProfileDao.editreceptionHomeAdressDao(id);
	}
	
	/*
	 * For Home Address View
	 */

	@GetMapping(value = "viewreceptionHomeAdress")
	public JsonResponse<RestReceptionProfileModel> viewreceptionHomeAdress(@RequestParam String id) {
		logger.info("Method : viewreceptionHomeAdress starts");
		
		logger.info("Method : viewreceptionHomeAdress ends");
		return restReceptionProfileDao.viewreceptionHomeAdressDao(id);
	}
	
	/*
	 * Post mapping for Doctor Profile
	 */
	@PostMapping(value = "addReceptionProfile")
	public JsonResponse<Object> addReceptionProfile(@RequestBody RestReceptionProfileModel employee) {
		logger.info("Method : addReceptionProfile starts");

		logger.info("Method : addReceptionProfile ends");
		return restReceptionProfileDao.addReceptionProfileDao(employee);
	}
	
	
	/*
	 * Post mapping for Doctor Identification
	 */
	@PostMapping(value = "addReceptionIdentification")
	public JsonResponse<Object> addReceptionIdentification(@RequestBody RestReceptionProfileModel employee) {
		logger.info("Method : addReceptionIdentification starts");

		logger.info("Method : addReceptionIdentification ends");
		return restReceptionProfileDao.addReceptionIdentificationDao(employee);
	}
	
	/*
	 * Post mapping for Doctor Contact Details
	 */
	@PostMapping(value = "addReceptionContactDetails")
	public JsonResponse<Object> addReceptionContactDetails(@RequestBody RestReceptionProfileModel employee) {
		logger.info("Method : addReceptionContactDetails starts");

		logger.info("Method : addReceptionContactDetails ends");
		return restReceptionProfileDao.addReceptionContactDetailsDao(employee);
	}
	
	/*
	 * for Country list
	 */
	@RequestMapping(value = "getCountryLists-reception", method = { RequestMethod.GET })
	public List<DropDownModel> getCountry() {
		logger.info("Method : getCountry starts");

		logger.info("Method : getCountry ends");
		return restReceptionProfileDao.getCountryListsreceptionDao();
	}
	
	/**
	 * Rest Controller - Get State List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getStateList-reception")
	public JsonResponse<List<DropDownModel>> getStateList(@RequestParam Integer id) {
		logger.info("Method : getStateList starts");

		logger.info("Method : getStateList ends");
		return restReceptionProfileDao.getStateListreceptionDao(id);
	}
	
	/**
	 * Rest Controller - Get district List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getDistList-reception")
	public JsonResponse<List<DropDownModel>> getDistLists(@RequestParam String state) {
		logger.info("Method : getDistLists starts");

		logger.info("Method : getDistLists ends");
		return restReceptionProfileDao.getDistListreceptionDao(state);
	}
	/**
	 * Rest Controller - Get City List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getCityList-reception")
	public JsonResponse<List<DropDownModel>> getCityList(@RequestParam String dist) {
		logger.info("Method : getCityList starts");

		logger.info("Method : getCityList ends");
		return restReceptionProfileDao.getCityListreceptionDao(dist);
	}

	
	/*
	 * Add Document Upload
	 */
	@PostMapping(value = "rest-adddrDocumentUpload-reception")
	public ResponseEntity<JsonResponse<List<RestReceptionProfileModel>>> RestReceptionProfileModel(
			@RequestBody List<RestReceptionProfileModel> RestReceptionProfileModel) {
		logger.info("Method : adddrDocumentUpload starts");
		logger.info("Method : adddrDocumentUpload ends");
		return restReceptionProfileDao.adddrDocumentUploadreceptionDao(RestReceptionProfileModel);
	}
	
	/*
	 * View data for Document Attachment
	 */
	@GetMapping(value = "rest-viewDocumentAttachment-receptiopn")
	public JsonResponse<List<RestReceptionProfileModel>> viewDocumentAttachment(@RequestParam String id ,String rolid) {
		logger.info("Method :view DocumentAttachment starts");
		logger.info("Method :view DocumentAttachment ends");
		return restReceptionProfileDao.viewDocumentAttachmentreceptiopnDao(id,rolid);
		
	}
	/*
	 * download Document Attachment
	 */
	
	@GetMapping(value="rest-attachmentDownload-reception")
	public RestReceptionProfileDocumentModel restattachmentDownload(String id){
		logger.info("Method : rest attachmentDownload starts");
		
		logger.info("Method : restattachmentDownload ends");
		return restReceptionProfileDao.attachmentDownloadreceptionDao(id);
	}
}
