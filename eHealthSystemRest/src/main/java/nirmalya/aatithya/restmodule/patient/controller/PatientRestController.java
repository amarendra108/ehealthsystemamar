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

import nirmalya.aatithya.restmodule.patient.dao.PatientRestDao;
import nirmalya.aatithya.restmodule.patient.model.PatientHistoryModel;

@RestController
@RequestMapping("user/")
public class PatientRestController {
Logger logger = LoggerFactory.getLogger(PatientRestController.class);
	
	@Autowired
	
	PatientRestDao patientRestDao;
	/*
	 * For Patient History
	 */
	
	@GetMapping(value = "getAllPatientHistory")
	public JsonResponse<List<PatientHistoryModel>> getAllPatientHistory(@RequestParam String id) {
		logger.info("Method :getAllPatientHistory starts");
		
		
		logger.info("Method :getAllPatientHistory endss");
		return patientRestDao.getAllPatientHistory(id);
		
	}
	
	/*
	 * For Patient Major Surgery
	 */
	@GetMapping(value = "getPatientHistoryMajorSurgery")
	public JsonResponse<List<PatientHistoryModel>> getPatientHistoryMajorSurgery(@RequestParam String id) {
		logger.info("Method :getPatientHistoryMajorSurgery starts");
		
		
		logger.info("Method :getPatientHistoryMajorSurgery endss");
		return patientRestDao.getPatientHistoryMajorSurgery(id);
		
	}
	
	/*
	 * For Patient Medical Condition
	 */
	@GetMapping(value = "rest-getPatientHistoryMedicalCondition")
	public JsonResponse<List<PatientHistoryModel>> getPatientHistoryMedicalCondition(@RequestParam String id) {
		logger.info("Method :getPatientHistoryMedicalCondition starts");
		
		
		logger.info("Method :getPatientHistoryMedicalCondition endss");
		return patientRestDao.getPatientHistoryMedicalCondition(id);
		
	}
}
