package nirmalya.aatithya.restmodule.patient.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.dao.PatientMedicationDao;
import nirmalya.aatithya.restmodule.patient.model.RestMedicationCemistModel;
import nirmalya.aatithya.restmodule.patient.model.RestPatientMedicationModel;
import nirmalya.aatithya.restmodule.patient.model.RestPatientMedicationRequestedMdcnModel;
import nirmalya.aatithya.restmodule.patient.model.RestRequestMedcineModel;
import nirmalya.aatithya.restmodule.patient.model.RestRequestTestModel;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value = { "user/" })
public class RestPatientMedicationController {
	Logger logger = LoggerFactory.getLogger(RestPatientMedicationController.class);

	@Autowired
	PatientMedicationDao medicationDao;

	/*
	 * view current medicine
	 */

	@GetMapping(value = "rest-viewCurrentMedication")
	public JsonResponse<List<RestPatientMedicationModel>> viewMedicineDetails(@RequestParam String uId) {
		logger.info("Method : viewMedicineDetails starts");

		logger.info("Method : viewMedicineDetails ends");

		return medicationDao.viewCurrentMedicationDao(uId);
	}
	
	  
	/* view current doctor details */
	  
	  
	  @GetMapping(value = "rest-doctorDetails") public
	  JsonResponse<List<RestPatientMedicationModel>> viewCurrentDrDetails(String uId) {
	  logger.info("Method : viewCurrentDrDetails starts");
	  
	  
	  logger.info("Method : viewCurrentDrDetails ends");
	  
	  return medicationDao.viewCurrentDrDetailsDao(uId); 
	  }
	  /*************************************** Rest start function for agGrid to get chemist list ******************************/
	  @GetMapping(value = "rest-chemitstList")
	  public List<RestMedicationCemistModel>veiwChemistForMedicine(@RequestParam String place) {
		  logger.info("Method : veiwChemistForMedicine starts");
		  logger.info("Method : veiwChemistForMedicine starts");
		  return medicationDao.veiwChemistForMedicineDao(place);
	  }
	  /*************************************** Rest start function for agGrid to get chemist list ******************************/
	  @PostMapping("/rest-send-requestMedcine")
	  @ResponseBody public JsonResponse<RestRequestMedcineModel>addRequestMedicine(@RequestBody RestRequestMedcineModel requestMedcineModel) {
		  logger.info("Method : addRequestMedicine starts");
		  logger.info("Method : addRequestMedicine starts");
		  return medicationDao.addRequestMedicineDao(requestMedcineModel);
	  }
	  /*************************************** start function for agGrid to get request mdcn list ******************************/
	  @GetMapping(value = "rest-viewRequestMdcn")
	  public List<RestPatientMedicationRequestedMdcnModel>viewRequestMdcn(@RequestParam String userId) {
		  logger.info("Method : viewRequestMdcn starts");
		  logger.info("Method : viewRequestMdcn starts");
		  return medicationDao.viewRequestMdcnDao(userId);
	  }
	  /*
		 * view Test Details
		 */

		
		  @GetMapping(value = "rest-view-patient-test-details") public
		  JsonResponse<List<RestPatientMedicationModel>> viewTestDetailsPatient(@RequestParam
		  String uId) { logger.info("Method : viewTestDetails starts");
		  
		  logger.info("Method : viewTestDetails ends");
		  
		  return medicationDao.viewTestDetailsPatientDao(uId); }

			/***************************************
			 * Rest start function for agGrid to get Lab list
			 ******************************/
			@GetMapping(value = "rest-veiwLabList")
			public List<RestMedicationCemistModel> veiwLabList(@RequestParam String place) {
				logger.info("Method : veiwLabList starts");
				logger.info("Method : veiwLabList end");
				return medicationDao.veiwLabListDao(place);
			}
			  /************************************** Rest start function for agGrid to save Test *****************************/
			  @PostMapping("/rest-send-requestLab")
			  @ResponseBody public JsonResponse<RestRequestTestModel>addRequestTest(@RequestBody RestRequestTestModel restRequestTestModel) {
				  logger.info("Method : addRequestTest starts");
				  logger.info("Method : addRequestTest end");
				  return medicationDao.addRequestTestDao(restRequestTestModel);
			  }
}
