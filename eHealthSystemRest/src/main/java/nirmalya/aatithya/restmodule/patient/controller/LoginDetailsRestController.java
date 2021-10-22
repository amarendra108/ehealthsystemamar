package nirmalya.aatithya.restmodule.patient.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.dao.LoginDetailsDao;

import nirmalya.aatithya.restmodule.patient.model.LoginDetailsModel;

@RestController
@RequestMapping("user/")
public class LoginDetailsRestController {
Logger logger = LoggerFactory.getLogger(LoginDetailsRestController.class);
	
	@Autowired
	
	LoginDetailsDao loginDetailsDao;
	/*
	 * For Patient Login Details
	 */
	@GetMapping(value = "getPatientLoginDetails")
	public JsonResponse<List<LoginDetailsModel>> getPatientLoginDetails(@RequestParam String id) {
		logger.info("Method :getPatientLoginDetails starts");
		
		
		logger.info("Method :getPatientLoginDetails endss");
		return loginDetailsDao.getPatientLoginDetails(id)
;
		
	}
}
