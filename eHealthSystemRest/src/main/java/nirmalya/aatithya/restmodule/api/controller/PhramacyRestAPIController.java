package nirmalya.aatithya.restmodule.api.controller;

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

import nirmalya.aatithya.restmodule.api.dao.PharmacyRestAPIDao;
import nirmalya.aatithya.restmodule.api.model.APIMedicationModel;
import nirmalya.aatithya.restmodule.api.model.APIPharmacyOrderModel;
import nirmalya.aatithya.restmodule.api.model.DoctorRegistrationModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "api")
public class PhramacyRestAPIController {

	@Autowired
	PharmacyRestAPIDao pharmacyRestAPIDao;

	Logger logger = LoggerFactory.getLogger(PhramacyRestAPIController.class);
	
	@PostMapping(value = "/post-pharmacy-registration")
	public ResponseEntity<JsonResponse<Object>> pharmacyRegistration(@RequestBody DoctorRegistrationModel data) {
		logger.info("Method : pharmacyRegistration starts");
		
		logger.info("Method : pharmacyRegistration ends");
		return pharmacyRestAPIDao.pharmacyRegistration(data);
	}
	
	@GetMapping(value = "/view-user-pharmacy-orderlist-by-id")
	public ResponseEntity<JsonResponse<List<APIPharmacyOrderModel>>> viewUserPharmacyOrderById(@RequestParam String userid) {
		logger.info("Method : viewUserPharmacyOrderById starts");
		
		logger.info("Method : viewUserPharmacyOrderById ends");
		return pharmacyRestAPIDao.getUserPharmacyOrderByIdDao(userid);
	}
	
	@GetMapping(value = "/view-user-lab-orderlist-by-id")
	public ResponseEntity<JsonResponse<List<APIPharmacyOrderModel>>> viewUserLabOrderById(@RequestParam String userid) {
		logger.info("Method : viewUserLabOrderById starts");
		
		logger.info("Method : viewUserLabOrderById ends");
		return pharmacyRestAPIDao.getUserLabOrderByIdDao(userid);
	}
	
	@GetMapping(value = "/view-status-lab-orderlist-by-id")
	public ResponseEntity<JsonResponse<List<APIPharmacyOrderModel>>> viewAcceptedLabOrderById(@RequestParam String userid,@RequestParam String status) {
		logger.info("Method : viewAcceptedLabOrderById starts");
		
		logger.info("Method : viewAcceptedLabOrderById ends");
		return pharmacyRestAPIDao.viewAcceptedLabOrderById(userid,status);
	}
	
	@GetMapping(value = "/view-requested-medicine-details")
	public ResponseEntity<JsonResponse<List<APIMedicationModel>>> viewRequestedMedicineDetails(@RequestParam String orderid) {
		logger.info("Method : viewRequestedMedicineDetails starts");
		
		logger.info("Method : viewRequestedMedicineDetails ends");
		return pharmacyRestAPIDao.viewRequestedMedicineDetails(orderid);
	}
	
	@GetMapping(value = "/view-requested-test-details")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> viewRequestedTestDetails(@RequestParam String orderid) {
		logger.info("Method : viewRequestedTestDetails starts");
		
		logger.info("Method : viewRequestedTestDetails ends");
		return pharmacyRestAPIDao.viewRequestedTestDetails(orderid);
	}
	
	@GetMapping(value = "/change-pharmacy-status")
	public ResponseEntity<JsonResponse<Object>> changePharmacyStatus(@RequestParam String orderid,@RequestParam String status) {
		logger.info("Method : changePharmacyStatus starts");
		
		logger.info("Method : changePharmacyStatus ends");
		return pharmacyRestAPIDao.changePharmacyStatus(orderid,status);
	}
	
	@GetMapping(value = "/change-lab-status")
	public ResponseEntity<JsonResponse<Object>> changeLabStatus(@RequestParam String orderid,@RequestParam String status) {
		logger.info("Method : changeLabStatus starts");
		 
		logger.info("Method : changeLabStatus ends");
		return pharmacyRestAPIDao.changeLabStatus(orderid,status);
	}
	
}
