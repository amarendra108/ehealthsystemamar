package nirmalya.aatithya.restmodule.lab.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.dao.RestLabDashboardDao;
import nirmalya.aatithya.restmodule.lab.model.RestLabDashboardModel;
import nirmalya.aatithya.restmodule.lab.model.RestLabDashboardPatientModel;
import nirmalya.aatithya.restmodule.lab.model.RestLabPatientPriscriptionModel;
@RestController
@RequestMapping("lab/")
public class RestLabDashboardController {
	Logger logger = LoggerFactory.getLogger(RestLabDashboardController.class);

	@Autowired

	RestLabDashboardDao restLabDashboardDao;
	/**
	 *
	 * @return Appointment Count
	 */
	
	
	@RequestMapping(value = "labCountRegistered", method = { RequestMethod.GET })
	public List<RestLabDashboardModel> labCountRegistered(@RequestParam String id) {

		logger.info("Method : labCountRegistered starts");
		
		logger.info("Method : labCountRegistered ends");

		return restLabDashboardDao.labCountRegistered(id);
	}
	
	/**
	 * Rest Controller - disease wise total abnormal
	 *
	 */
	@GetMapping(value = "rest-viewTestReport")
	public JsonResponse<List<DropDownModel>> viewTestReport(String id) {
		logger.info("Method : viewTestReport starts");

		logger.info("Method : viewTestReport ends");
		return restLabDashboardDao.viewTestReport(id);
	}
	
	/**
	 * Rest Controller - disease wise total abnormal
	 *
	 */
	@GetMapping(value = "rest-viewTHealthCheckup")
	public JsonResponse<List<DropDownModel>> viewTHealthCheckup(String id) {
		logger.info("Method : viewTHealthCheckup starts");

		logger.info("Method : viewTHealthCheckup ends");
		return restLabDashboardDao.viewTHealthCheckup(id);
	}
	
//	/*
//	 * For Patient Login Details
//	 */
//	@GetMapping(value = "rest-getLabView")
//	public JsonResponse<List<RestLabDashboardModel>> getLabView() {
//		logger.info("Method :getLabView starts");
//		
//		
//		logger.info("Method :getLabView endss");
//		return restLabDashboardDao.getLabView();
//		
//	}
	/*
	 * For Patient table in lab-dashboard Details
	 */
	@GetMapping(value = "rest-getLabView")
	@RequestMapping(value = "rest-getLabView", method = { RequestMethod.GET })
	public JsonResponse<List<RestLabDashboardPatientModel>> getRestLabView(@RequestParam String userid,@RequestParam String date) {
		logger.info("Method :getRestLabView starts");
		//System.out.println("@@@@@@@@@@@@@"+date);

		logger.info("Method :getRestLabView endss");
		return restLabDashboardDao.getLabView(userid,date);

	}
	/* lab patient precription model controller
	 * 
	 */
	
	@RequestMapping(value = "getLabPatientprecription", method = { RequestMethod.GET })
	public JsonResponse<List<RestLabPatientPriscriptionModel>> getPrecriptionDetails(@RequestParam String userid,@RequestParam String patientid,
			@RequestParam String orderid) {
		
		
		logger.info("Method : getPatient starts");
		
		logger.info("Method : getPatient ends");
		return restLabDashboardDao.getPrecriptionDetails(userid,patientid, orderid);
	}
	
	@RequestMapping(value = "rest-saveLabStatus", method = { RequestMethod.GET })
	public JsonResponse<Object> saveLabStatus(@RequestParam String orderid, @RequestParam String status) {
		logger.info("Method : saveLabStatus starts");
		logger.info("Method : saveLabStatus ends");
		return restLabDashboardDao.saveLabStatusDao(orderid, status);
	}
}
