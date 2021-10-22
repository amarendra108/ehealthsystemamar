package nirmalya.aatithya.restmodule.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.api.dao.MobileUserRestAPIDao;
import nirmalya.aatithya.restmodule.api.model.APIAllergyModel;
import nirmalya.aatithya.restmodule.api.model.APIBioMedicalModel;
import nirmalya.aatithya.restmodule.api.model.APIMedicationModel;
import nirmalya.aatithya.restmodule.api.model.APIPharmacyModel;
import nirmalya.aatithya.restmodule.api.model.APIVitalSignModel;
import nirmalya.aatithya.restmodule.api.model.AppointmentListModel;
import nirmalya.aatithya.restmodule.api.model.AppointmentModel;
import nirmalya.aatithya.restmodule.api.model.CountryModel;
import nirmalya.aatithya.restmodule.api.model.DoctorAppointmentModel;
import nirmalya.aatithya.restmodule.api.model.EmergencyContactAPIModel;
import nirmalya.aatithya.restmodule.api.model.IsEContactFDoctorValidModel;
import nirmalya.aatithya.restmodule.api.model.SearchDetailsModel;
import nirmalya.aatithya.restmodule.api.model.SignUpModel;
import nirmalya.aatithya.restmodule.api.model.UserProfileAPIModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "api")
public class MobileUserRestAPIController {
	
	@Autowired
	MobileUserRestAPIDao mobileUserRestApiDao;

	Logger logger = LoggerFactory.getLogger(MobileUserRestAPIController.class);
	
	/** User Self Registration **/
	@PostMapping(value = "/user-self-registration")
	public ResponseEntity<JsonResponse<Object>> userSelfRegistrationApi(@RequestBody SignUpModel data) {
		logger.info("Method : userSelfRegistrationApi starts");
		
		logger.info("Method : userSelfRegistrationApi ends");
		return mobileUserRestApiDao.userSelfRegistrationApi(data);
	}
	
	/** Update User Profile **/
	@PostMapping(value = "/update-user-profile")
	public ResponseEntity<JsonResponse<Object>> updateUserProfileAPI(@RequestBody UserProfileAPIModel data) {
		logger.info("Method : updateUserProfileAPI starts");
		
		logger.info("Method : updateUserProfileAPI ends");
		return mobileUserRestApiDao.updateUserProfileAPI(data);
	}
	
	@PostMapping(value = "/update-user-profile-image")
	public ResponseEntity<JsonResponse<Object>> updateUserProfileImageAPI(@RequestBody UserProfileAPIModel data) {
		logger.info("Method : updateUserProfileImageAPI starts");
		
		logger.info("Method : updateUserProfileImageAPI ends");
		return mobileUserRestApiDao.updateUserProfileImageAPI(data);
	}
	
	/** User Dashboard **/
	@GetMapping(value = "/user-dashboard")
	public ResponseEntity<JsonResponse<IsEContactFDoctorValidModel>> userDashboard(@RequestParam String userid) {
		logger.info("Method : getPatientDetailsRestApi starts");
		
		logger.info("Method : getPatientDetailsRestApi ends");
		return mobileUserRestApiDao.userDashboard(userid);
	}
	
	@GetMapping(value = "/doctor-available-by-date")
	public ResponseEntity<Object> doctorAvailableByDate(@RequestParam String doctor,@RequestParam String date) {
		logger.info("Method : doctorAvailableByDate starts");
		
		System.out.println(doctor+"   "+date);
		
		logger.info("Method : doctorAvailableByDate ends");
		return mobileUserRestApiDao.doctorAvailableByDate(doctor,date);
	}
//	@GetMapping(value = "/doctor-available-by-date")
//	public ResponseEntity<JsonResponse<Object>> doctorAvailableByDate(@RequestParam String doctor,@RequestParam String date) {
//		logger.info("Method : doctorAvailableByDate starts");
//		
//		logger.info("Method : doctorAvailableByDate ends");
//		return mobileUserRestApiDao.doctorAvailableByDate(doctor,date);
//	}
	
	@GetMapping(value = "/doctor-available-by-time")
	public ResponseEntity<JsonResponse<Object>> doctorAvailableByTime(@RequestParam String doctor,@RequestParam String date,@RequestParam String time) {
		logger.info("Method : doctorAvailableByTime starts");
		
		logger.info("Method : doctorAvailableByTime ends");
		return mobileUserRestApiDao.doctorAvailableByTime(doctor,date,time);
	}
	
	@PostMapping(value = "/post-doctor-appointment")
	public ResponseEntity<JsonResponse<Object>> postDoctorAppointment(@RequestBody DoctorAppointmentModel data) {
		logger.info("Method : postDoctorAppointment starts");
		
		logger.info("Method : postDoctorAppointment ends");
		return mobileUserRestApiDao.postDoctorAppointment(data);
	}
	
//	@GetMapping(value = "/get-user-appointment-list")
//	public ResponseEntity<JsonResponse<List<AppointmentListModel>>> userAppointmentList(@RequestParam String userid) {
//		logger.info("Method : userAppointmentList starts");
//		
//		System.out.println(userid);
//		
//		logger.info("Method : userAppointmentList ends");
//		return mobileUserRestApiDao.getUserAppointmentList(userid);
//	}
	@GetMapping(value = "/get-user-appointment-list")
	public ResponseEntity<JsonResponse<List<AppointmentListModel>>> userAppointmentList(@RequestParam String userid,@RequestParam String status,@RequestParam String date) {
		logger.info("Method : userAppointmentList starts");
		
		logger.info("Method : userAppointmentList ends");
		return mobileUserRestApiDao.getUserAppointmentList(userid,status,date);
	}
	
	/** Appointment List By Date & User - Health Screening - User **/
	@GetMapping(value = "/view-user-screentest-appointment-list")
	public ResponseEntity<JsonResponse<List<AppointmentModel>>> userScreenTestAppointmentList(@RequestParam String userid, @RequestParam String appontdt) {
		logger.info("Method : userScreenTestAppointmentList starts");
		
		logger.info("Method : userScreenTestAppointmentList ends");
		return mobileUserRestApiDao.getUserScreenTestAppointmentList(userid,appontdt);
	}
	
	/** Appointment List By Date & User - Health CheckUp - User **/
	@GetMapping(value = "/view-user-checkup-appointment-list")
	public ResponseEntity<JsonResponse<List<AppointmentModel>>> userCheckUpAppointmentList(@RequestParam String userid, @RequestParam String appontdt) {
		logger.info("Method : userCheckUpAppointmentList starts");
		
		logger.info("Method : userCheckUpAppointmentList ends");
		return mobileUserRestApiDao.getUserCheckUpAppointmentList(userid,appontdt);
	}
	
	@GetMapping(value = "/view-user-vital-sign-details")
	public ResponseEntity<JsonResponse<List<APIVitalSignModel>>> userVitalSignDetails(@RequestParam String userid) {
		logger.info("Method : userVitalSignDetails starts");
		
		logger.info("Method : userVitalSignDetails ends");
		return mobileUserRestApiDao.getUserVitalSignDetails(userid);
	}
	
	@GetMapping(value = "/view-user-emergency-details-api")
	public ResponseEntity<Object> getEmergencyDetails(@RequestParam String userid) {
		logger.info("Method : getEmergencyDetails starts");
		
		logger.info("Method : getEmergencyDetails ends");
		return mobileUserRestApiDao.getEmergencyDetails(userid);
	}
	
	@GetMapping(value = "/sms-to-emergency-contact-users")
	public ResponseEntity<Object> smsToEmergencyContactUsers(@RequestParam String userid,@RequestParam String mapurl,@RequestParam String longi,@RequestParam String lati) {
		logger.info("Method : smsToEmergencyContactUsers starts");
		
		logger.info("Method : smsToEmergencyContactUsers ends");
		return mobileUserRestApiDao.smsToEmergencyContactUsers(userid,mapurl,longi,lati);
	}
	
	@PostMapping(value = "/post-emergency-contact-api")
	public ResponseEntity<Object> postEmergencyContactApi(@RequestBody EmergencyContactAPIModel data) {
		logger.info("Method : postEmergencyContactApi starts");
		
		logger.info("Method : postEmergencyContactApi ends");
		return mobileUserRestApiDao.postEmergencyContactApi(data);
	}
	
	@PostMapping(value = "/update-vital-signs")
	public ResponseEntity<Object> updateVitalSigns(@RequestBody List<CountryModel> data) {
		logger.info("Method : updateVitalSigns starts");
		
		logger.info("Method : updateVitalSigns ends");
		return mobileUserRestApiDao.updateVitalSigns(data);
	}
	
	@PostMapping(value = "/post-user-emergency-message")
	public ResponseEntity<Object> postUserEmergencyMessage(@RequestBody EmergencyContactAPIModel data) {
		logger.info("Method : postUserEmergencyMessage starts");
		
		logger.info("Method : postUserEmergencyMessage ends");
		return mobileUserRestApiDao.postUserEmergencyMessage(data);
	}
	
	@GetMapping(value = "/view-user-medicine-details-by-appno")
	public ResponseEntity<JsonResponse<List<APIMedicationModel>>> viewUserMedicineDetailsByAppNo(@RequestParam String appno) {
		logger.info("Method : viewUserMedicineDetailsByAppNo starts");
		
		logger.info("Method : viewUserMedicineDetailsByAppNo ends");
		return mobileUserRestApiDao.getUserMedicineDetailsByAppNo(appno);
	}
	
	@PostMapping(value = "/find-health-provider-details")
	public ResponseEntity<JsonResponse<List<SearchDetailsModel>>> findHealthProviderDetails(@RequestBody SearchDetailsModel data) {
		logger.info("Method : findHealthProviderDetails starts");
		
		System.out.println(data.getLongi()+"/"+data.getLati()+"/"+data.getAddr()+"/"+data.getCity()+"/"+data.getHealthpro()+"/"+data.getType());
		
		logger.info("Method : findHealthProviderDetails ends");
		return mobileUserRestApiDao.findHealthProviderDetails(data.getLongi(),data.getLati(),data.getAddr(),data.getCity(),data.getHealthpro(),data.getType());
	}
	
	@GetMapping(value = "/delete-medicine-by-app-no")
	public ResponseEntity<Object> deleteMedicineByAppNo(@RequestParam String appno,@RequestParam String srlone,@RequestParam String srltwo) {
		logger.info("Method : deleteMedicineByAppNo starts");
		
		logger.info("Method : deleteMedicineByAppNo ends");
		return mobileUserRestApiDao.deleteMedicineByAppNo(appno,srlone,srltwo);
	}
	
	@PostMapping(value = "/get-pharmacy-list-by-location")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> pharmacyListByLocation(@RequestBody SearchDetailsModel data) {
		logger.info("Method : pharmacyListByLocation starts");
		
		logger.info("Method : pharmacyListByLocation ends");
		return mobileUserRestApiDao.getPharmacyListByLocation(data);
	}
	
	@PostMapping(value = "/get-lab-list-by-location")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> labListByLocation(@RequestBody SearchDetailsModel data) {
		logger.info("Method : labListByLocation starts");
		
		logger.info("Method : labListByLocation ends");
		return mobileUserRestApiDao.getLabListByLocation(data);
	}
	
	@PostMapping(value = "/post-pharmacy-request-api")
	public ResponseEntity<Object> postPharmacyRequestApi(@RequestBody APIPharmacyModel data) {
		logger.info("Method : postEmergencyContactApi starts");
		
		logger.info("Method : postEmergencyContactApi ends");
		return mobileUserRestApiDao.postPharmacyRequestApi(data);
	}
	
	@PostMapping(value = "/post-lab-request-api")
	public ResponseEntity<Object> postLabRequestApi(@RequestBody APIPharmacyModel data) {
		logger.info("Method : postLabRequestApi starts");
		
		logger.info("Method : postLabRequestApi ends");
		return mobileUserRestApiDao.postLabRequestApi(data);
	}
	
	@GetMapping(value = "/view-user-allergy-list-api")
	public ResponseEntity<JsonResponse<List<APIAllergyModel>>> viewUserAllergyListApi(@RequestParam String userid) {
		logger.info("Method : viewUserAllergyListApi starts");
		 
		logger.info("Method : viewUserAllergyListApi ends");
		return mobileUserRestApiDao.viewUserAllergyListApi(userid);
	} 
	
	@GetMapping(value = "/view-user-biomedical-implant-list-api")
	public ResponseEntity<JsonResponse<List<APIBioMedicalModel>>> viewUserBioMedicalImplantListApi(@RequestParam String userid) {
		logger.info("Method : viewUserBioMedicalImplantListApi starts");
		
		logger.info("Method : viewUserBioMedicalImplantListApi ends");
		return mobileUserRestApiDao.viewUserBioMedicalImplantListApi(userid);
	}
	
	@PostMapping(value = "/post-allergies-api")
	public ResponseEntity<Object> postAllergiesApi(@RequestBody APIAllergyModel data) {
		logger.info("Method : postEmergencyContactApi starts");
		
		logger.info("Method : postEmergencyContactApi ends");
		return mobileUserRestApiDao.postAllergiesApiDao(data);
	}
	
	@PostMapping(value = "/post-biomedical-implants-api")
	public ResponseEntity<Object> postBioMedicalImplantsApi(@RequestBody APIBioMedicalModel data) {
		logger.info("Method : postBioMedicalImplantsApi starts");
		
		logger.info("Method : postBioMedicalImplantsApi ends");
		return mobileUserRestApiDao.postBioMedicalImplantsApi(data);
	}
	
	@PostMapping(value = "/get-pathology-list-by-location")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> pathologyListByLocation(@RequestBody SearchDetailsModel data) {
		logger.info("Method : pathologyListByLocation starts");
		
		logger.info("Method : pathologyListByLocation ends");
		return mobileUserRestApiDao.getPathologyListByLocation(data);
	}
	
	@GetMapping(value = "/view-user-test-details-by-appno")
	public ResponseEntity<JsonResponse<List<APIMedicationModel>>> viewUserTestDetailsByAppNo(@RequestParam String appno) {
		logger.info("Method : viewUserTestDetailsByAppNo starts");
		
		logger.info("Method : viewUserTestDetailsByAppNo ends");
		return mobileUserRestApiDao.getUserTestDetailsByAppNo(appno);
	}
	
}
