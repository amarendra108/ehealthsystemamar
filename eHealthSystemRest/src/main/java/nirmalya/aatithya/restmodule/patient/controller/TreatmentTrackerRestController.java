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
import nirmalya.aatithya.restmodule.patient.dao.TreatmentTrackerRestDao;
import nirmalya.aatithya.restmodule.patient.model.LoginDetailsModel;
import nirmalya.aatithya.restmodule.patient.model.TreatmentTrackerModel;
@RestController
@RequestMapping("user/")
public class TreatmentTrackerRestController {
Logger logger = LoggerFactory.getLogger(TreatmentTrackerRestController.class);
	
	@Autowired
	
	TreatmentTrackerRestDao treatmentTrackerRestDao;
	/*
	 * For Patient Treatment Tracker
	 */
	@GetMapping(value = "rest-getPatientTreatmentTracker")
	public JsonResponse<List<TreatmentTrackerModel>> getPatientTreatmentTracker(@RequestParam String id) {
		logger.info("Method :getPatientTreatmentTracker starts");
		
		
		logger.info("Method :getPatientTreatmentTracker endss");
		return treatmentTrackerRestDao.getPatientTreatmentTracker(id);
		
	}
}
