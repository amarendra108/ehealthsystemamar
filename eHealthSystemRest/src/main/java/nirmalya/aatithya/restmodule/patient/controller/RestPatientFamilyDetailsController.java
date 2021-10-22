package nirmalya.aatithya.restmodule.patient.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.dao.RestPatientFamilyDetailsDao;
import nirmalya.aatithya.restmodule.patient.model.RestPatientFamilyDetailsModel;


@RestController
@RequestMapping("user/")
public class RestPatientFamilyDetailsController {
Logger logger = LoggerFactory.getLogger(RestPatientFamilyDetailsController.class);
	
	@Autowired
	
	RestPatientFamilyDetailsDao familydetailsdao;
	
	/*
	 * For Patient Login Details
	 */

	@GetMapping(value = "viewPatientFamilyDataDetails")
	public JsonResponse<List<RestPatientFamilyDetailsModel>> viewPatientFamilyDataDetails(String id) {
		logger.info("Method :viewPatientFamilyDataDetails starts");
		
		
		logger.info("Method :viewPatientFamilyDataDetails endss");
		return familydetailsdao.viewPatientFamilyDataDetails(id);
		
	}
}
