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
import nirmalya.aatithya.restmodule.patient.dao.PatientConcultantDao;
import nirmalya.aatithya.restmodule.patient.model.RestConsultantGetDoctorModel;
import nirmalya.aatithya.restmodule.patient.model.RestConsultantTimeInterval;
import nirmalya.aatithya.restmodule.patient.model.RestDoctorProfileModel;
import nirmalya.aatithya.restmodule.patient.model.RestPatientConsultantRestModel;
import nirmalya.aatithya.restmodule.patient.model.RestViewPatientConsultantModel;

@RestController
@RequestMapping(value = "user/")
public class RestPatientConcultantController {
	Logger logger = LoggerFactory.getLogger(RestPatientConcultantController.class);

	@Autowired
	PatientConcultantDao patientConcultantDao;


//patient consultant add
	@PostMapping(value = "patientConsultantAdd")
	public JsonResponse<Object> addPatientConsultant(@RequestBody RestPatientConsultantRestModel patientConsultantRestModel,
			@RequestParam String userid) {
		logger.info("Method : rest addPatientConsultant starts");

		logger.info("Method : rest addPatientConsultant ends");
		return patientConcultantDao.addPatientConsultantDao(patientConsultantRestModel,userid);
	}
	
	//view patient add
	@GetMapping(value = "getAllconsultant")
	public JsonResponse<List<RestViewPatientConsultantModel>> getAllquotation(@RequestParam String userid) {
	logger.info("Method :Allquotation starts");


	logger.info("Method :Allquotation endss");
	return patientConcultantDao.getAllconsolation(userid);

	}
	//view patient add
	@GetMapping(value = "getAllconsultantMmoreThenaOnemonth")
	public JsonResponse<List<RestViewPatientConsultantModel>> viewconsultantMorethanMonth(@RequestParam String userid) {
		logger.info("Method :viewquotationMorethanMonth rest starts");
		
		
		logger.info("Method :viewquotationMorethanMonth rest endss");
		return patientConcultantDao.viewconsultantMorethanMonth(userid);
		
	}
	//view patient add
	@RequestMapping(value = "getAllDoctorList", method = { RequestMethod.GET })
	public JsonResponse<List<RestConsultantGetDoctorModel>> getDoctorList(@RequestParam String id) {
		logger.info("Method :getDoctorList rest starts");
		
		
		logger.info("Method :getDoctorList rest endss");
		return patientConcultantDao.getDoctorList(id);
		
	}
	//check doctor avaibility 
		@RequestMapping(value = "doctorAvailablity", method = { RequestMethod.GET })
		public ResponseEntity<JsonResponse<List<RestConsultantTimeInterval>>> restgetDoctorAvaible( @RequestParam("doctorid") String doctorId) {
			logger.info("Method : restgetDoctorAvaible starts");
			logger.info("Method : restgetDoctorAvaible ends");
			return patientConcultantDao.getDoctorAvaible(doctorId);
		}
	//get rest time slot
		//@PostMapping(value = "gettimeslot")
		@RequestMapping(value = "getAlltimeslot", method = { RequestMethod.GET })
		public ResponseEntity<JsonResponse<List<DropDownModel>>> restGetAllTimeSlot( ) {
			logger.info("Method : restGetTimeSlot starts");
			logger.info("Method : restGetTimeSlot ends");
			return patientConcultantDao.getAllTimeSlot();
		}
		//get rest time slot
		@RequestMapping(value = "gettimeslot", method = { RequestMethod.GET })
		public ResponseEntity<JsonResponse<List<DropDownModel>>> restGetTimeSlot(String time, String date ,String doctorid) {
			logger.info("Method : restGetTimeSlot starts");
			logger.info("Method : restGetTimeSlot ends");
			return patientConcultantDao.getTimeSlot(time, date, doctorid);
		}
		//get doctor profile
		@GetMapping(value = "rest-getDoctorProfile")
		public ResponseEntity<JsonResponse<RestDoctorProfileModel>> getDoctorProfile(@RequestParam String id) {
			logger.info("Method : getDoctorProfile starts");

			logger.info("Method :getDoctorProfile ends");
			return patientConcultantDao.getDoctorProfile(id);
		}
		//save doctor rating
		@GetMapping(value = "rest-saveRating")
		public ResponseEntity<JsonResponse<Object>> saveRating(@RequestParam String ratingid,
				@RequestParam String doctorid, @RequestParam String patientid) {
			logger.info("Method : saveRating starts");
			
			logger.info("Method :saveRating ends");
			return patientConcultantDao.saveRatingDao(ratingid, doctorid, patientid);
		}
}
