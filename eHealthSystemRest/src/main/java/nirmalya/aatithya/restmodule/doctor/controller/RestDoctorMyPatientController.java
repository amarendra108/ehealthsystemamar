package nirmalya.aatithya.restmodule.doctor.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.doctor.dao.DoctorMyPatientDao;
import nirmalya.aatithya.restmodule.doctor.model.RestViewMyPatientModel;
import nirmalya.aatithya.restmodule.patient.controller.RestPatientConcultantController;

@RestController
@RequestMapping(value = "doctor/")
public class RestDoctorMyPatientController {
	Logger logger = LoggerFactory.getLogger(RestPatientConcultantController.class);

	@Autowired
	DoctorMyPatientDao doctorMyPatientDao;
	
	//view patient add
		@GetMapping(value = "rest-getMyPatient")
		public JsonResponse<List<RestViewMyPatientModel>> doctorViewMyPatient(@RequestParam String userid) {
		logger.info("Method :doctorViewMyPatient starts");


		logger.info("Method :doctorViewMyPatient endss");
		return doctorMyPatientDao.doctorViewMyPatientDao(userid);

		}
}
