package nirmalya.aatithya.restmodule.bloodbank.controller;

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
import nirmalya.aatithya.restmodule.bloodbank.dao.RestBloodBankProfileDao;
import nirmalya.aatithya.restmodule.bloodbank.model.RestBloodBankDocumentUpload;
import nirmalya.aatithya.restmodule.bloodbank.model.RestBloodBankProfileModel;



@RestController
@RequestMapping("bloodbank/")
public class RestBloodBankProfileController {
	Logger logger = LoggerFactory.getLogger(RestBloodBankProfileController.class);

	@Autowired

	RestBloodBankProfileDao restBloodBankProfileDao;
	
	/*
	 * Edit For Doctor Profile
	 */

	@GetMapping(value = "rest-editBloodbankProfile")
	public JsonResponse<RestBloodBankProfileModel> editchemistProfiles(@RequestParam String id) {
		logger.info("Method : editchemistProfiles starts");
		
		logger.info("Method : editchemistProfiles ends");
		return restBloodBankProfileDao.editBloodbankProfileDao(id);
	}
	
	/*
	 * Edit For Doctor Identification
	 */

	@GetMapping(value = "rest-editBloodbankIdentification")
	public JsonResponse<RestBloodBankProfileModel> editchemistIdentifications(@RequestParam String id) {
		logger.info("Method : editchemistIdentifications starts");
		
		logger.info("Method : editchemistIdentifications ends");
		return restBloodBankProfileDao.editBloodbankIdentificationDao(id);
	}
	
	/*
	 * For Home Address Edit
	 */

	@GetMapping(value = "rest-editBloodbankHomeAdress")
	public JsonResponse<RestBloodBankProfileModel> editchemistHomeAdress(@RequestParam String id) {
		logger.info("Method : editchemistHomeAdress starts");
		
		logger.info("Method : editchemistHomeAdress ends");
		return restBloodBankProfileDao.editBloodbankHomeAdressDao(id);
	}
	
	/*
	 * For Home Address View
	 */

	@GetMapping(value = "rest-viewBloodbankHomeAdress")
	public JsonResponse<RestBloodBankProfileModel> viewchemistHomeAdress(@RequestParam String id) {
		logger.info("Method : viewchemistHomeAdress starts");
		
		logger.info("Method : viewchemistHomeAdress ends");
		return restBloodBankProfileDao.viewBloodbankHomeAdressDao(id);
	}
	
	/*
	 * Post mapping for Doctor Profile
	 */
	@PostMapping(value = "rest-addBloodbankProfile")
	public JsonResponse<Object> addchemistProfile(@RequestBody RestBloodBankProfileModel employee) {
		logger.info("Method : addDoctorProfile starts");

		logger.info("Method : addDoctorProfile ends");
		return restBloodBankProfileDao.addBloodbankProfileDao(employee);
	}
	
	
	/*
	 * Post mapping for Doctor Identification
	 */
	@PostMapping(value = "rest-addBloodbankIdentification")
	public JsonResponse<Object> addDoctorIdentification(@RequestBody RestBloodBankProfileModel employee) {
		logger.info("Method : addDoctorIdentification starts");

		logger.info("Method : addDoctorIdentification ends");
		return restBloodBankProfileDao.addBloodbankIdentificationDao(employee);
	}
	
	/*
	 * Post mapping for Doctor Contact Details
	 */
	@PostMapping(value = "rest-addBloodbankContactDetails")
	public JsonResponse<Object> addDoctorContactDetails(@RequestBody RestBloodBankProfileModel employee) {
		logger.info("Method : addDoctorContactDetails starts");

		logger.info("Method : addDoctorContactDetails ends");
		return restBloodBankProfileDao.addBloodbankContactDetailsDao(employee);
	}
	
	/*
	 * for Country list
	 */
	@RequestMapping(value = "getCountryLists-Bloodbank", method = { RequestMethod.GET })
	public List<DropDownModel> getCountry() {
		logger.info("Method : getCountry starts");

		logger.info("Method : getCountry ends");
		return restBloodBankProfileDao.getCountryListsBloodbankDao();
	}
	
	/**
	 * Rest Controller - Get State List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getStateList-Bloodbank")
	public JsonResponse<List<DropDownModel>> getStateList(@RequestParam Integer id) {
		logger.info("Method : getStateList starts");

		logger.info("Method : getStateList ends");
		return restBloodBankProfileDao.getStateListBloodbankDao(id);
	}
	
	/**
	 * Rest Controller - Get district List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getDistList-Bloodbank")
	public JsonResponse<List<DropDownModel>> getDistLists(@RequestParam String state) {
		logger.info("Method : getDistLists starts");

		logger.info("Method : getDistLists ends");
		return restBloodBankProfileDao.getDistListsBloodbankDao(state);
	}
	/**
	 * Rest Controller - Get City List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getCityListBloodbank")
	public JsonResponse<List<DropDownModel>> getCityList(@RequestParam String dist) {
		logger.info("Method : getCityList starts");

		logger.info("Method : getCityList ends");
		return restBloodBankProfileDao.getCityListBloodbankDao(dist);
	}

	
	/*
	 * Add Document Upload
	 */
	@PostMapping(value = "rest-adddrDocumentUpload-Bloodbank")
	public ResponseEntity<JsonResponse<List<RestBloodBankProfileModel>>> RestBloodBankProfileModel(
			@RequestBody List<RestBloodBankProfileModel> RestBloodBankProfileModel) {
		logger.info("Method : adddrDocumentUpload starts");
		logger.info("Method : adddrDocumentUpload ends");
		return restBloodBankProfileDao.adddrDocumentUploadBloodbankDao(RestBloodBankProfileModel);
	}
	
	/*
	 * View data for Document Attachment
	 */
	@GetMapping(value = "rest-viewDocumentAttachment-Bloodbank")
	public JsonResponse<List<RestBloodBankProfileModel>> viewDocumentAttachment(@RequestParam String id ,String rolid) {
		logger.info("Method :view DocumentAttachment starts");
		logger.info("Method :view DocumentAttachment ends");
		return restBloodBankProfileDao.viewDocumentAttachmentBloodbankDao(id,rolid);
		
	}
	/*
	 * download Document Attachment
	 */
	
	@GetMapping(value="rest-attachmentDownload-Bloodbank")
	public RestBloodBankDocumentUpload restattachmentDownload(String id){
		logger.info("Method : rest attachmentDownload starts");
		
		logger.info("Method : restattachmentDownload ends");
		return restBloodBankProfileDao.restattachmentDownloadBloodbankDao(id);
	}
}
