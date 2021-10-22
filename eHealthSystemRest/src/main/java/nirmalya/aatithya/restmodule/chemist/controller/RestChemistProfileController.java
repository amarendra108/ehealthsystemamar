package nirmalya.aatithya.restmodule.chemist.controller;

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
import nirmalya.aatithya.restmodule.chemist.dao.RestChemistProfileDao;
import nirmalya.aatithya.restmodule.chemist.model.RestChemistDocumentUpload;
import nirmalya.aatithya.restmodule.chemist.model.RestChemistProfileModel;
import nirmalya.aatithya.restmodule.patient.model.RestLabTestReportModel;



@RestController
@RequestMapping("chemist/")
public class RestChemistProfileController {
	Logger logger = LoggerFactory.getLogger(RestChemistProfileController.class);

	@Autowired

	RestChemistProfileDao restChemistProfileDao;
	
	/*
	 * Edit For Doctor Profile
	 */

	@GetMapping(value = "editchemistProfiles")
	public JsonResponse<RestChemistProfileModel> editchemistProfiles(@RequestParam String id) {
		logger.info("Method : editchemistProfiles starts");
		
		logger.info("Method : editchemistProfiles ends");
		return restChemistProfileDao.editchemistProfiles(id);
	}
	
	/*
	 * Edit For Doctor Identification
	 */

	@GetMapping(value = "editchemistIdentifications")
	public JsonResponse<RestChemistProfileModel> editchemistIdentifications(@RequestParam String id) {
		logger.info("Method : editchemistIdentifications starts");
		
		logger.info("Method : editchemistIdentifications ends");
		return restChemistProfileDao.editchemistIdentifications(id);
	}
	
	/*
	 * For Home Address Edit
	 */

	@GetMapping(value = "editchemistHomeAdress")
	public JsonResponse<RestChemistProfileModel> editchemistHomeAdress(@RequestParam String id) {
		logger.info("Method : editchemistHomeAdress starts");
		
		logger.info("Method : editchemistHomeAdress ends");
		return restChemistProfileDao.editchemistHomeAdressDao(id);
	}
	
	/*
	 * For Home Address View
	 */

	@GetMapping(value = "viewchemistHomeAdress")
	public JsonResponse<RestChemistProfileModel> viewchemistHomeAdress(@RequestParam String id) {
		logger.info("Method : viewchemistHomeAdress starts");
		
		logger.info("Method : viewchemistHomeAdress ends");
		return restChemistProfileDao.viewchemistHomeAdressDao(id);
	}
	
	/*
	 * Post mapping for Doctor Profile
	 */
	@PostMapping(value = "addchemistProfile")
	public JsonResponse<Object> addchemistProfile(@RequestBody RestChemistProfileModel employee) {
		logger.info("Method : addDoctorProfile starts");

		logger.info("Method : addDoctorProfile ends");
		return restChemistProfileDao.addchemistProfileDao(employee);
	}
	
	
	/*
	 * Post mapping for Doctor Identification
	 */
	@PostMapping(value = "addchemistIdentification")
	public JsonResponse<Object> addDoctorIdentification(@RequestBody RestChemistProfileModel employee) {
		logger.info("Method : addDoctorIdentification starts");

		logger.info("Method : addDoctorIdentification ends");
		return restChemistProfileDao.addchemistIdentificationDao(employee);
	}
	
	/*
	 * Post mapping for Doctor Contact Details
	 */
	@PostMapping(value = "addchemistContactDetails")
	public JsonResponse<Object> addDoctorContactDetails(@RequestBody RestChemistProfileModel employee) {
		logger.info("Method : addDoctorContactDetails starts");

		logger.info("Method : addDoctorContactDetails ends");
		return restChemistProfileDao.addchemistContactDetailsDao(employee);
	}
	
	/*
	 * for Country list
	 */
	@RequestMapping(value = "getCountryLists-chemist", method = { RequestMethod.GET })
	public List<DropDownModel> getCountry() {
		logger.info("Method : getCountry starts");

		logger.info("Method : getCountry ends");
		return restChemistProfileDao.getCountryListsChemistDao();
	}
	
	/**
	 * Rest Controller - Get State List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getStateList-chemist")
	public JsonResponse<List<DropDownModel>> getStateList(@RequestParam Integer id) {
		logger.info("Method : getStateList starts");

		logger.info("Method : getStateList ends");
		return restChemistProfileDao.getStateListchemistDao(id);
	}
	
	/**
	 * Rest Controller - Get district List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getDistList-chemist")
	public JsonResponse<List<DropDownModel>> getDistLists(@RequestParam String state) {
		logger.info("Method : getDistLists starts");

		logger.info("Method : getDistLists ends");
		return restChemistProfileDao.getDistListschemistDao(state);
	}
	/**
	 * Rest Controller - Get City List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getCityListchemist")
	public JsonResponse<List<DropDownModel>> getCityList(@RequestParam String dist) {
		logger.info("Method : getCityList starts");

		logger.info("Method : getCityList ends");
		return restChemistProfileDao.getCityListchemistDao(dist);
	}

	
	/*
	 * Add Document Upload
	 */
	@PostMapping(value = "rest-adddrDocumentUpload-chemist")
	public ResponseEntity<JsonResponse<List<RestChemistProfileModel>>> RestChemistProfileModel(
			@RequestBody List<RestChemistProfileModel> RestChemistProfileModel) {
		logger.info("Method : adddrDocumentUpload starts");
		logger.info("Method : adddrDocumentUpload ends");
		return restChemistProfileDao.restadddrDocumentUploadChemistDao(RestChemistProfileModel);
	}
	
	/*
	 * View data for Document Attachment
	 */
	@GetMapping(value = "rest-viewDocumentAttachment-chemist")
	public JsonResponse<List<RestChemistProfileModel>> viewDocumentAttachment(@RequestParam String id ,String rolid) {
		logger.info("Method :view DocumentAttachment starts");
		logger.info("Method :view DocumentAttachment ends");
		return restChemistProfileDao.viewDocumentAttachmentChemistDao(id,rolid);
		
	}
	/*
	 * download Document Attachment
	 */
	
	@GetMapping(value="rest-attachmentDownload-chemist")
	public RestChemistDocumentUpload restattachmentDownload(String id){
		logger.info("Method : rest attachmentDownload starts");
		
		logger.info("Method : restattachmentDownload ends");
		return restChemistProfileDao.restattachmentDownloadChemistDao(id);
	}
}
