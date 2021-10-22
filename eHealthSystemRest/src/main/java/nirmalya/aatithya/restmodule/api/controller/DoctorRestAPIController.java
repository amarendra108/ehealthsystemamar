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

import nirmalya.aatithya.restmodule.api.dao.DoctorRestAPIDao;
import nirmalya.aatithya.restmodule.api.model.APILabTestReportModel;
import nirmalya.aatithya.restmodule.api.model.APIMedicationModel;
import nirmalya.aatithya.restmodule.api.model.AppointmentListModel;
import nirmalya.aatithya.restmodule.api.model.CountryModel;
import nirmalya.aatithya.restmodule.api.model.DoctorRegistrationModel;
import nirmalya.aatithya.restmodule.api.model.PersonalInformationModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "api")
public class DoctorRestAPIController {

	@Autowired
	DoctorRestAPIDao dctorRestAPIDao;

	Logger logger = LoggerFactory.getLogger(DoctorRestAPIController.class);
	
	@GetMapping(value = "/view-doctor-appointment-list")
	public ResponseEntity<JsonResponse<List<AppointmentListModel>>> viewDoctorAppointmentList(@RequestParam String userid,@RequestParam String date, @RequestParam String status) {
		logger.info("Method : viewDoctorAppointmentList starts");
		
		logger.info("Method : viewDoctorAppointmentList ends");
		return dctorRestAPIDao.viewDoctorAppointmentList(userid,date,status);
	}
	
	@GetMapping(value = "/change-user-appointment-status")
	public ResponseEntity<JsonResponse<Object>> changeUserAppointmentStatus(@RequestParam String appid,@RequestParam String appstatus) {
		logger.info("Method : changeUserAppointmentStatus starts");
		
		logger.info("Method : changeUserAppointmentStatus ends");
		return dctorRestAPIDao.changeUserAppointmentStatus(appid,appstatus);
	}
	
	/** Doctor Registration **/
	@PostMapping(value = "/doctor-registration-details")
	public ResponseEntity<JsonResponse<Object>> doctorRegistration(@RequestBody DoctorRegistrationModel data) {
		logger.info("Method : doctorRegistration starts");
		
		logger.info("Method : doctorRegistration ends");
		return dctorRestAPIDao.doctorRegistrationDao(data);
	}
	
	/** User Personal Information Registration **/
	@GetMapping(value = "/user-personal-information-doctor")
	public ResponseEntity<JsonResponse<List<PersonalInformationModel>>> userpersonalInformation(@RequestParam String userid) {
		logger.info("Method : userpersonalInformation starts");

		logger.info("Method : userpersonalInformation ends");
		return dctorRestAPIDao.userpersonalInformationDao(userid);
	}
	
	@GetMapping(value = "/get-medicine-list-with-type")
	public ResponseEntity<JsonResponse<List<CountryModel>>> medicineListWithType() {
		logger.info("Method : medicineListWithType starts");
		
		logger.info("Method : medicineListWithType ends");
		return dctorRestAPIDao.getMedicineListWithType();
	}

	@GetMapping(value = "/user-labtest-report-doctor")
	public ResponseEntity<JsonResponse<List<APILabTestReportModel>>> userLabTestReportDoctor(@RequestParam String userid) {
		logger.info("Method : userLabTestReportDoctor starts");

		logger.info("Method : userLabTestReportDoctor ends");
		return dctorRestAPIDao.userLabTestReportDoctor(userid);
	} 
	
	@GetMapping(value = "/user-medication-doctor")
	public ResponseEntity<JsonResponse<List<APIMedicationModel>>> userMedicationData(@RequestParam String userid) {
		logger.info("Method : userMedicationData starts");
		
		logger.info("Method : userMedicationData ends");
		return dctorRestAPIDao.userMedicationData(userid);
	}
	
	@PostMapping(value = "/post-user-medication-doctor")
	public ResponseEntity<JsonResponse<Object>> postUserMedicationDoctor(@RequestBody List<APIMedicationModel> data) {
		logger.info("Method : postUserMedicationDoctor starts");
		
		logger.info("Method : postUserMedicationDoctor ends");
		return dctorRestAPIDao.postUserMedicationDoctor(data);
	}
	
	@PostMapping(value = "/post-user-test-by-doctor")
	public ResponseEntity<JsonResponse<Object>> postUserTestByDoctor(@RequestBody List<APIMedicationModel> data) {
		logger.info("Method : postUserTestByDoctor starts");
		
		logger.info("Method : postUserTestByDoctor ends");
		return dctorRestAPIDao.postUserTestByDoctor(data);
	}
	@GetMapping(value = "/delete-test-by-app-no")
	public ResponseEntity<Object> deleteTestByAppNo(@RequestParam String appno,@RequestParam String srlone,@RequestParam String srltwo) {
		logger.info("Method : deleteTestByAppNo starts");
		
		logger.info("Method : deleteTestByAppNo ends");
		return dctorRestAPIDao.deleteTestByAppNo(appno,srlone,srltwo);
	}
}
