package nirmalya.aatithya.restmodule.doctor.controller;

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
import nirmalya.aatithya.restmodule.doctor.dao.RestDoctorProfileDao;
import nirmalya.aatithya.restmodule.doctor.model.RestDoctorDocumentUpload;
import nirmalya.aatithya.restmodule.doctor.model.RestDoctorProfileModel;
import nirmalya.aatithya.restmodule.patient.model.RestLabTestReportModel;



@RestController
@RequestMapping("doctor/")
public class RestDoctorProfileController {
	Logger logger = LoggerFactory.getLogger(RestDoctorProfileController.class);

	@Autowired

	RestDoctorProfileDao restDoctorProfileDao;
	
	/*
	 * Edit For Doctor Profile
	 */

	@GetMapping(value = "editdoctorProfiles")
	public JsonResponse<RestDoctorProfileModel> editdoctorProfile(@RequestParam String id) {
		logger.info("Method : editdoctorProfile starts");
		
		logger.info("Method : editdoctorProfile ends");
		return restDoctorProfileDao.editdoctorProfile(id);
	}
	
	/*
	 * Edit For Doctor Identification
	 */

	@GetMapping(value = "editdoctorIdentifications")
	public JsonResponse<RestDoctorProfileModel> editdoctorIdentification(@RequestParam String id) {
		logger.info("Method : editdoctorIdentification starts");
		
		logger.info("Method : editdoctorIdentification ends");
		return restDoctorProfileDao.editdoctorIdentification(id);
	}
	
	/*
	 * For Home Address Edit
	 */

	@GetMapping(value = "editdoctorHomeAdress")
	public JsonResponse<RestDoctorProfileModel> editdoctorHomeAdress(@RequestParam String id) {
		logger.info("Method : editdoctorHomeAdress starts");
		
		logger.info("Method : editdoctorHomeAdress ends");
		return restDoctorProfileDao.editdoctorHomeAdress(id);
	}
	
	/*
	 * For Home Address View
	 */

	@GetMapping(value = "viewdoctorHomeAdress")
	public JsonResponse<RestDoctorProfileModel> viewdoctorHomeAdress(@RequestParam String id) {
		logger.info("Method : viewdoctorHomeAdress starts");
		
		logger.info("Method : viewdoctorHomeAdress ends");
		return restDoctorProfileDao.viewdoctorHomeAdress(id);
	}
	
	/*
	 * Post mapping for Doctor Profile
	 */
	@PostMapping(value = "addDoctorProfile")
	public JsonResponse<Object> addDoctorProfile(@RequestBody RestDoctorProfileModel employee) {
		logger.info("Method : addDoctorProfile starts");

		logger.info("Method : addDoctorProfile ends");
		return restDoctorProfileDao.addDoctorProfile(employee);
	}
	
	
	/*
	 * Post mapping for Doctor Identification
	 */
	@PostMapping(value = "addDoctorIdentification")
	public JsonResponse<Object> addDoctorIdentification(@RequestBody RestDoctorProfileModel employee) {
		logger.info("Method : addDoctorIdentification starts");

		logger.info("Method : addDoctorIdentification ends");
		return restDoctorProfileDao.addDoctorIdentification(employee);
	}
	
	/*
	 * Post mapping for Doctor Contact Details
	 */
	@PostMapping(value = "addDoctorContactDetails")
	public JsonResponse<Object> addDoctorContactDetails(@RequestBody RestDoctorProfileModel employee) {
		logger.info("Method : addDoctorContactDetails starts");

		logger.info("Method : addDoctorContactDetails ends");
		return restDoctorProfileDao.addDoctorContactDetails(employee);
	}
	
	/*
	 * for Country list
	 */
	@RequestMapping(value = "getCountryLists-doctor", method = { RequestMethod.GET })
	public List<DropDownModel> getCountry() {
		logger.info("Method : getCountry starts");

		logger.info("Method : getCountry ends");
		return restDoctorProfileDao.getCountryLists();
	}
	
	/**
	 * Rest Controller - Get State List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getStateList-doctor")
	public JsonResponse<List<DropDownModel>> getStateList(@RequestParam Integer id) {
		logger.info("Method : getStateList starts");

		logger.info("Method : getStateList ends");
		return restDoctorProfileDao.getStateListDao(id);
	}
	
	/**
	 * Rest Controller - Get district List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getDistList-doctor")
	public JsonResponse<List<DropDownModel>> getDistLists(@RequestParam String state) {
		logger.info("Method : getDistLists starts");

		logger.info("Method : getDistLists ends");
		return restDoctorProfileDao.getDistLists(state);
	}
	/**
	 * Rest Controller - Get City List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getCityListdoctor")
	public JsonResponse<List<DropDownModel>> getCityList(@RequestParam String dist) {
		logger.info("Method : getCityList starts");

		logger.info("Method : getCityList ends");
		return restDoctorProfileDao.getCityListDao(dist);
	}

	
	/*
	 * Add Document Upload
	 */
	@PostMapping(value = "rest-adddrDocumentUpload")
	public ResponseEntity<JsonResponse<List<RestDoctorProfileModel>>> restDoctorProfileModel(
			@RequestBody List<RestDoctorProfileModel> restDoctorProfileModel) {
		logger.info("Method : adddrDocumentUpload starts");
		logger.info("Method : adddrDocumentUpload ends");
		return restDoctorProfileDao.restadddrDocumentUploadDao(restDoctorProfileModel);
	}
	
	/*
	 * View data for Document Attachment
	 */
	@GetMapping(value = "rest-viewDocumentAttachment")
	public JsonResponse<List<RestDoctorProfileModel>> viewDocumentAttachment(@RequestParam String id ,String rolid) {
		logger.info("Method :view DocumentAttachment starts");
		logger.info("Method :view DocumentAttachment ends");
		return restDoctorProfileDao.viewDocumentAttachmentDao(id,rolid);
		
	}
	/*
	 * download Document Attachment
	 */
	
	@GetMapping(value="rest-attachmentDownload")
	public RestDoctorDocumentUpload restattachmentDownload(String id){
		logger.info("Method : rest attachmentDownload starts");
		
		logger.info("Method : restattachmentDownload ends");
		return restDoctorProfileDao.restattachmentDownloadDao(id);
	}
}
