package nirmalya.aatithya.restmodule.lab.controller;

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

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.dao.HealthScreeningRestDao;
import nirmalya.aatithya.restmodule.lab.model.HealthScreeningRestModel;
import nirmalya.aatithya.restmodule.patient.controller.PatientDetailNewController;
import nirmalya.aatithya.restmodule.patient.dao.PatientDetailsDao;
import nirmalya.aatithya.restmodule.patient.model.RestPatientDetailsNewModel;

@RestController
@RequestMapping("lab")
public class HealthScreeningRestController {

Logger logger = LoggerFactory.getLogger(HealthScreeningRestController.class);
	
	@Autowired
	
	HealthScreeningRestDao healthScreeningRestDao;	
	
	
	
	
	
	
	
	@GetMapping(value = "viewHealthScreen")
	public JsonResponse<List<HealthScreeningRestModel>> viewHealthScreen() {
		logger.info("Method :viewHealthScreen starts");
		
		
		logger.info("Method :viewHealthScreen endss");
		return healthScreeningRestDao.viewHealthScreen();
		
	}
	
	/*
	 *   UHID NO autoSearch
	 */
	
	@GetMapping(value = "rest-getUhidNoAutoSearch")
	public JsonResponse<List<HealthScreeningRestModel>> getUhidNoAutoSearch(
			@RequestParam String id) {
		logger.info("Method : getUhidNoAutoSearch starts");

		logger.info("Method :getUhidNoAutoSearch endss");
		return healthScreeningRestDao.getUhidNoAutoSearch(id);
	}
	
	
	@PostMapping(value = "addHealthScreeningLab")
	public JsonResponse<Object> addHealthScreeningLab(@RequestBody HealthScreeningRestModel employee) {
		logger.info("Method : addHealthScreeningLab starts");

		logger.info("Method : addHealthScreeningLab ends");
		return healthScreeningRestDao.addHealthScreeningLab(employee);
	}
}
