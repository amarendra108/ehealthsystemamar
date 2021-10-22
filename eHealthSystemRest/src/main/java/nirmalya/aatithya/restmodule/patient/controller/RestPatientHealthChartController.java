package nirmalya.aatithya.restmodule.patient.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.dao.RestPatientHealthChartDao;
import nirmalya.aatithya.restmodule.patient.model.RestLabTestReportModel;
@RestController
@RequestMapping(value = "user/")
public class RestPatientHealthChartController {
Logger logger = LoggerFactory.getLogger(RestPatientHealthChartController.class);
@Autowired
RestPatientHealthChartDao restPatientHealthChartDao;
/*
 * health-chart-graph
 */
@GetMapping(value="getpatienttestdetails-chart")
public JsonResponse<List<RestLabTestReportModel>> getPatientTestDetails(@RequestParam String id){
	logger.info("Method : getPatientTestDetails starts");
	
	logger.info("Method : getPatientTestDetails ends");
	return restPatientHealthChartDao.getPatientTestDetailsDao(id);
}
/*
 * health-chart-graph-details
 */
@GetMapping(value="rest-getPatientHealthChartDetails")
public JsonResponse<List<RestLabTestReportModel>> getPatientHealthChartDetails(@RequestParam String id){
	logger.info("Method : getPatientHealthChartDetails starts");
	
	logger.info("Method : getPatientHealthChartDetails ends");
	return restPatientHealthChartDao.getPatientHealthChartDetailsDao(id);
}



/**
 * Rest Controller - HAEMATOLOGICAL EXAMINATION
 *
 */
@GetMapping(value = "rest-healthChartForHematology")
public JsonResponse<List<DropDownModel>> healthChartForHematology(
		@RequestParam String userId) {
	logger.info("Method : healthChartForHematology starts");

	logger.info("Method : healthChartForHematology ends");
	return restPatientHealthChartDao.healthChartForHematologyDao(userId);
 }
/**
 * Rest Controller - Kidney Function Test
 *
 */
@GetMapping(value = "rest-healthChartKidneyTest")
public JsonResponse<List<DropDownModel>> healthChartKidneyTest(
		@RequestParam String userId) {
	logger.info("Method : healthChartKidneyTest starts");

	logger.info("Method : healthChartKidneyTest ends");
	return restPatientHealthChartDao.healthChartKidneyTestDao(userId);
 }
/**
 * Rest Controller - Lipid Profile
 *
 */
@GetMapping(value = "rest-healthChartForLipidProfile")
public JsonResponse<List<DropDownModel>> healthChartForLipidProfile(
		@RequestParam String userId) {
	logger.info("Method : healthChartForLipidProfile starts");

	logger.info("Method : healthChartForLipidProfile ends");
	return restPatientHealthChartDao.healthChartForLipidProfileDao(userId);
 }
/**
 * Rest Controller - Blood Sugar
 *
 */
@GetMapping(value = "rest-healthChartForBloodSugar")
public JsonResponse<List<DropDownModel>> healthChartForBloodSugar(
		@RequestParam String userId) {
	logger.info("Method : healthChartForBloodSugar starts");

	logger.info("Method : healthChartForBloodSugar ends");
	return restPatientHealthChartDao.healthChartForBloodSugarDao(userId);
 }
/**
 * Rest Controller -Electrolyte
 *
 */
@GetMapping(value = "rest-healthChartForElectrolyte")
public JsonResponse<List<DropDownModel>> healthChartForElectrolyte(
		@RequestParam String userId) {
	logger.info("Method : healthChartForElectrolyte starts");

	logger.info("Method : healthChartForElectrolyte ends");
	return restPatientHealthChartDao.healthChartForElectrolyteDao(userId);
 }
/**
 * Rest Controller - Liver Function Test
 *
 */
@GetMapping(value = "rest-healthChartForLiverTest")
public JsonResponse<List<DropDownModel>> healthChartForLiverTest(
		@RequestParam String userId) {
	logger.info("Method : healthChartForLiverTest starts");

	logger.info("Method : healthChartForLiverTest ends");
	return restPatientHealthChartDao.healthChartForLiverTestDao(userId);
 }
}
