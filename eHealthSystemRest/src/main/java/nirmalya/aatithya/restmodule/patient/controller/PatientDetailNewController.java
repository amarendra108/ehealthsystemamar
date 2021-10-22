package nirmalya.aatithya.restmodule.patient.controller;

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
import nirmalya.aatithya.restmodule.patient.dao.PatientDetailsDao;
import nirmalya.aatithya.restmodule.patient.model.RestPatientDetailsNewModel;

@RestController
@RequestMapping("user/")
public class PatientDetailNewController {
Logger logger = LoggerFactory.getLogger(PatientDetailNewController.class);
	
	@Autowired
	
	PatientDetailsDao PatientDetailsDao;	

	@GetMapping(value = "editpatientnew")
	public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> editpatientnew(@RequestParam String id) {
		logger.info("Method : editpatientnew starts");

		logger.info("Method :editpatientnew ends");
		return PatientDetailsDao.editpatientnew(id);
	}
	
	
	/*
	 * View data for emergency contact
	 */
	@GetMapping(value = "patientemercontactnew")
	public JsonResponse<List<RestPatientDetailsNewModel>> editpatientemercontactnew(@RequestParam String id) {
		logger.info("Method :patient emercontact contact starts");
		
		
		logger.info("Method :patient emercontact ends");
		return PatientDetailsDao.patientemercontactnew(id);
		
	}
	
	/*view data foe family doctor*/
	
	@GetMapping(value = "editpatientfamdocnew")
	public JsonResponse<List<RestPatientDetailsNewModel>> editpatientfamdocnew(@RequestParam String id) {
		logger.info("Method :patientfamdocnew starts");
		
		
		logger.info("Method :patientfamdocnew ends");
		return PatientDetailsDao.editpatientfamdocnew(id);
		
	}
	


	@GetMapping(value = "editpatientvitalsignnew")
	public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> editpatientvitalsignnew(@RequestParam String id) {
		logger.info("Method : editpatientvitalsignnew starts");

		logger.info("Method :editpatientvitalsignnew ends");
		return PatientDetailsDao.editpatientvitalsignnew(id);
	}
	
	@RequestMapping(value = "get-allerName-list", method = { RequestMethod.GET })
	public List<DropDownModel> getallerNamelist() {
		logger.info("Method : getallerNamelist starts");

		logger.info("Method : getallerNamelist ends");
		return PatientDetailsDao.getallerNamelist();

	}
	
	@RequestMapping(value = "get-allertype-list", method = { RequestMethod.GET })
	public List<DropDownModel> getallertypelist() {
		logger.info("Method : getallertypelist starts");

		logger.info("Method : getallertypelist ends");
		return PatientDetailsDao.getallertypelist();

	}

	@RequestMapping(value = "get-bname-list", method = { RequestMethod.GET })
	public List<DropDownModel> getbnamelist() {
		logger.info("Method : getbnamelist starts");

		logger.info("Method : getbnamelist ends");
		return PatientDetailsDao.getbnamelist();

	}
	@RequestMapping(value = "savedpatallergy", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> saveemployeeaddress(
			@RequestBody RestPatientDetailsNewModel restPatientDetailsNewModel) {
		logger.info("Method : savedpatallergy starts");

		logger.info("Method : savedpatallergy ends");
		return PatientDetailsDao.savedpatallergy(restPatientDetailsNewModel);
	}

	
	@GetMapping(value = "getallergynewview")
	public JsonResponse<List<RestPatientDetailsNewModel>> getallergynewview(@RequestParam String id) {
		logger.info("Method :getallergynewview starts");
		
		
		logger.info("Method :getallergynewview endss");
		return PatientDetailsDao.getallergynewview(id);
		
	}
	


	
	@GetMapping(value = "getbiomedicalnewview")
	public JsonResponse<List<RestPatientDetailsNewModel>> getbiomedicalnewview(@RequestParam String id) {
		logger.info("Method :getbiomedicalnewview starts");
		
		
		logger.info("Method :getbiomedicalnewview endss");
		return PatientDetailsDao.getbiomedicalnewview(id);
		
	}
	
	@RequestMapping(value = "savebiomedical", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> savebiomedical(
			@RequestBody RestPatientDetailsNewModel restPatientDetailsNewModel) {
		logger.info("Method : savebiomedical starts");

		logger.info("Method : savebiomedical ends");
		return PatientDetailsDao.savebiomedical(restPatientDetailsNewModel);
	}

	
	@GetMapping(value = "getFamilyListAutoSearch")
	public ResponseEntity<JsonResponse<List<RestPatientDetailsNewModel>>> getFamilyListAutoSearch(
			@RequestParam String id) {
		logger.info("Method : getFamilyListAutoSearch starts");

		logger.info("Method :getFamilyListAutoSearch endss");
		return PatientDetailsDao.getFamilyListAutoSearch(id);
	}
	
	@RequestMapping(value = "addvitalsSign", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> addvitalsSign(
			@RequestBody RestPatientDetailsNewModel restPatientDetailsNewModel) {
		logger.info("Method : addvitalsSign starts");

		logger.info("Method : addvitalsSign ends");
		return PatientDetailsDao.addvitalsSign(restPatientDetailsNewModel);
	}
	
	@RequestMapping(value = "get-erelation-list", method = { RequestMethod.GET })
	public List<DropDownModel> erelationlist() {
		logger.info("Method : erelationlist starts");

		logger.info("Method : erelationlist ends");
		return PatientDetailsDao.erelationlist();

	}
	@GetMapping(value = "editEmergencyContactNew")
	public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> editEmergencyContactNew(@RequestParam String id) {
		logger.info("Method : editEmergencyContactNew starts");

		logger.info("Method :editEmergencyContactNew ends");
		return PatientDetailsDao.editEmergencyContactNew(id);
	}

	
	@RequestMapping(value = "saveemercontactdetails", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> saveemercontactdetails(
			@RequestBody RestPatientDetailsNewModel restPatientDetailsNewModel) {
		logger.info("Method : saveemercontactdetails starts");

		logger.info("Method : saveemercontactdetails ends");
		return PatientDetailsDao.saveemercontactdetails(restPatientDetailsNewModel);
	}
	
	@RequestMapping(value = "get-speciality-list", method = { RequestMethod.GET })
	public List<DropDownModel> getspecialitylist() {
		logger.info("Method : getspecialitylist starts");

		logger.info("Method : getspecialitylist ends");
		return PatientDetailsDao.getspecialitylist();

	}
	
	
	@RequestMapping(value = "savefamdocDetails", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> savefamdocDetails(
			@RequestBody RestPatientDetailsNewModel restPatientDetailsNewModel) {
		logger.info("Method : savefamdocDetails starts");

		logger.info("Method : savefamdocDetails ends");
		return PatientDetailsDao.savefamdocDetails(restPatientDetailsNewModel);
	}
	
	@PostMapping(value = "addidentidicationsave")
	public JsonResponse<Object> addidentidicationsave(@RequestBody RestPatientDetailsNewModel employee) {
		logger.info("Method : addidentidicationsave starts");

		logger.info("Method : addidentidicationsave ends");
		return PatientDetailsDao.addidentidicationsave(employee);
	}
	

	@PostMapping(value = "addcontactsave")
	public JsonResponse<Object> addcontactsave(@RequestBody RestPatientDetailsNewModel employee) {
		logger.info("Method : addcontactsave starts");

		logger.info("Method : addcontactsave ends");
		return PatientDetailsDao.addcontactsave(employee);
	}

	@GetMapping(value = "getEducationView")
	public JsonResponse<List<RestPatientDetailsNewModel>> getEducationView() {
		logger.info("Method :getEducationView starts");
		
		
		logger.info("Method :getEducationView endss");
		return PatientDetailsDao.getEducationView();
		
	}
	
	@PostMapping(value = "saveEducationdtl")
	public JsonResponse<Object> saveEducationdtl(@RequestBody RestPatientDetailsNewModel employee) {
		logger.info("Method : saveEducationdtl starts");

		logger.info("Method : saveEducationdtl ends");
		return PatientDetailsDao.saveEducationdtl(employee);
	}
	
	@RequestMapping(value="savefaamilyDetails", method={ RequestMethod.GET })
	public JsonResponse<Object> savefaamilyDetails(@RequestParam String relation,
			@RequestParam String uhidCardNo, @RequestParam String userId,  @RequestParam String slno) {
		logger.info("Method : savefaamilyDetails starts");

		logger.info("Method : savefaamilyDetails ends");
		return PatientDetailsDao.savefaamilyDetails(relation, uhidCardNo, userId, slno);
	}
	
	@GetMapping(value = "getfamilyView")
	public JsonResponse<List<RestPatientDetailsNewModel>> getfamilyView(String id) {
		logger.info("Method :getfamilyView starts");
		
		
		logger.info("Method :getfamilyView endss");
		return PatientDetailsDao.getfamilyView(id);
		
	}
		
	
	
	@RequestMapping(value = "getgenderListnew", method = { RequestMethod.GET })
	public List<DropDownModel> getgenderListnew() {
		logger.info("Method : getgenderListnew starts");

		logger.info("Method : getgenderListnew ends");
		return PatientDetailsDao.getgenderListnew();
	}

	
	@RequestMapping(value = "getbloodgroupListnew", method = { RequestMethod.GET })
	public List<DropDownModel> getbloodgroupListnew() {
		logger.info("Method : getbloodgroupListnew starts");

		logger.info("Method : getbloodgroupListnew ends");
		return PatientDetailsDao.getbloodgroupListnew();
	}

	@RequestMapping(value = "getmaritalstatusListnew", method = { RequestMethod.GET })
	public List<DropDownModel> getmaritalstatusListnew() {
		logger.info("Method : getmaritalstatusListnew starts");

		logger.info("Method : getmaritalstatusListnew ends");
		return PatientDetailsDao.getmaritalstatusListnew();
	}
	
	@RequestMapping(value = "deleteFamilyMember", method = { RequestMethod.GET })
	public JsonResponse<Object> deleteFamilyMemberRest( @RequestParam String slno) {
		logger.info("Method : deleteFamilyMemberRest starts");
		
		logger.info("Method : deleteFamilyMemberRest ends");
		return PatientDetailsDao.deleteFamilyMemberDao(slno);
	}
	
	
	@RequestMapping(value = "deleteEmrgncyContact", method = { RequestMethod.GET })
	public JsonResponse<Object> deleteEmrgncyContactRest( @RequestParam String slno) {
		logger.info("Method : deleteEmrgncyContactRest starts");
		
		logger.info("Method : deleteEmrgncyContactRest ends");
		return PatientDetailsDao.deleteEmrgncyContactDao(slno);
	}
	
	@RequestMapping(value = "deleteFamilyDoctor", method = { RequestMethod.GET })
	public JsonResponse<Object> deleteFamilyDoctorRest( @RequestParam String slno) {
		logger.info("Method : deleteFamilyDoctorFRest starts");
		
		logger.info("Method : deleteFamilyDoctorRest ends");
		return PatientDetailsDao.deleteFamilyDoctorDao(slno);
	}

	
	@PostMapping(value = "addpersonalsave")
	public JsonResponse<Object> addpersonalsave(@RequestBody RestPatientDetailsNewModel employee) {
		logger.info("Method : addpersonalsave starts");

		logger.info("Method : addpersonalsave ends");
		return PatientDetailsDao.addpersonalsave(employee);
	}
	/**
	 * Rest Controller - Get City List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getCityNewList")
	public JsonResponse<List<DropDownModel>> getCityNewList(@RequestParam String distId) {
		logger.info("Method : getCityNewList starts");

		logger.info("Method : getCityNewList ends");
		return PatientDetailsDao.getCityNewListDao(distId);
	}
	/**
	 * Rest Controller - Get district List For Drop Down
	 *
	 */
	@GetMapping(value = "rest-getDistNewList")
	public JsonResponse<List<DropDownModel>> getDistNewList(@RequestParam String state) {
		logger.info("Method : getDistNewList starts");

		logger.info("Method : getDistNewList ends");
		return PatientDetailsDao.getDistNewListDao(state);
	}
	
	
	@RequestMapping(value = "get-password-patient", method = { RequestMethod.GET })
	public List<DropDownModel> getPateintPw(@RequestParam("id") String id) {
		logger.info("Method : getPateintPw starts");
		
		logger.info("Method : getPateintPw ends");
		return PatientDetailsDao.getPateintPw(id);
		
	}
	
	/*
	 * For Contact Details Edit
	 */

	@GetMapping(value = "editcontactDetailsAddress")
	public JsonResponse<RestPatientDetailsNewModel> editcontactDetailsAddress(@RequestParam String id) {
		logger.info("Method : editcontactDetailsAddress starts");
		
		logger.info("Method : editcontactDetailsAddress ends");
		return PatientDetailsDao.editcontactDetailsAddress(id);
	}	

}
