package nirmalya.aatithya.restmodule.chemist.controller;

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

import nirmalya.aatithya.restmodule.chemist.dao.RestChemistDashboardDao;
import nirmalya.aatithya.restmodule.chemist.model.RestChemistDashboardModel;
import nirmalya.aatithya.restmodule.chemist.model.RestChemistPrescriptionDetailsModel;
import nirmalya.aatithya.restmodule.chemist.model.RestChemistPrescriptionModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.customer.model.RestCustoomerNewModel;

@RestController
@RequestMapping(value =  "chemist/" )
public class RestChemistDashboardController {
	
	Logger logger = LoggerFactory.getLogger(RestChemistDashboardController.class);

	@Autowired
	RestChemistDashboardDao chemistdashboardDao; 
	
	
	@RequestMapping(value = "chemistDashboard", method = { RequestMethod.GET })
	public List<RestChemistDashboardModel> getAppointment(@RequestParam String userId)
	{
		logger.info("Method :getAllPatient starts");
		
		logger.info("Method :getAllPatient endss");
		return chemistdashboardDao.getAppointment(userId);

	}
	
	
	/* precription model controller
	 * 
	 */
	
	@RequestMapping(value = "rest-viewpatient", method = { RequestMethod.GET })
	public JsonResponse<List<RestChemistPrescriptionModel>> getPatient(@RequestParam String userid,@RequestParam String date) {
		
		
		logger.info("Method : getPatient starts");
		
		logger.info("Method : getPatient ends");
		return chemistdashboardDao.getPatientPrescription(userid,date);
	}
	
	
	/* precription model controller
	 * 
	 */
	
	@RequestMapping(value = "getAllprecription", method = { RequestMethod.GET })
	public JsonResponse<List<RestChemistPrescriptionDetailsModel>> getPrecriptionDetails(@RequestParam String userid,@RequestParam String PatientName) {
		
		
		logger.info("Method : getPatient starts");
		

		logger.info("Method : getPatient ends");
		return chemistdashboardDao.getPrecriptionDetails(userid,PatientName);
	}
	

//	/* precription data model controller
//	 * 
//	 */
//	
//	@RequestMapping(value = "addPatientDatas", method = { RequestMethod.POST })
//	public JsonResponse<List<RestChemistPrescriptionDetailsModel>> getPrecriptionData(@RequestBody List<RestChemistPrescriptionDetailsModel> restChemistPrescriptionDetailsModel) {
//        logger.info("Method : getPrecriptionData starts");
//        logger.info("Method : getPrecriptionData ends");
//	return chemistdashboardDao.getPatientPrecriptionDetails(restChemistPrescriptionDetailsModel);
//	}
	
	
	@RequestMapping(value = "rest-saveChemistStatus", method = { RequestMethod.GET })
	public JsonResponse<Object> saveChemistStatus(@RequestParam String orderid, @RequestParam String status) {
		logger.info("Method : saveChemistStatus starts");
		logger.info("Method : saveChemistStatus ends");
		return chemistdashboardDao.saveChemistStatusDao(orderid, status);
	}
	
	
	
	 

}
