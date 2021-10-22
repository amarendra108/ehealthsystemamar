package nirmalya.aatithya.restmodule.lab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.dao.HealthCheckUpRestDao;
import nirmalya.aatithya.restmodule.lab.model.HealthCheckUpRestModel;

@RestController
@RequestMapping("lab/")
public class HealthCheckUpRestController {
	Logger logger = LoggerFactory.getLogger(HealthCheckUpRestController.class);

	@Autowired

	HealthCheckUpRestDao healthCheckUpRestDao;
	
	/*
	 *   UHID NO autoSearch
	 */
	
	@GetMapping(value = "rest-getUhidNoAutoSearchList")
	public JsonResponse<List<HealthCheckUpRestModel>> getUhidNoAutoSearchList(
			@RequestParam String id) {
		logger.info("Method : getUhidNoAutoSearchList starts");

		logger.info("Method :getUhidNoAutoSearchList endss");
		return healthCheckUpRestDao.getUhidNoAutoSearchList(id);
	}
	

	/*
	 * Post mapping for Add Life Style History
	 */
	@PostMapping(value = "addHealthCheckUp")
	public JsonResponse<Object> addHealthCheckUp(@RequestBody HealthCheckUpRestModel employee) {
		logger.info("Method : addHealthCheckUp starts");

		logger.info("Method : addHealthCheckUp ends");
		return healthCheckUpRestDao.addHealthCheckUp(employee);
	}
	/*
	 * For Insurance
	 */
	@GetMapping(value = "getAllLabHealthCheckUpView")
	public JsonResponse<List<HealthCheckUpRestModel>> getAllLabHealthCheckUpView() {
		logger.info("Method :getAllLabHealthCheckUpView starts");
		
		
		logger.info("Method :getAllLabHealthCheckUpView endss");
		return healthCheckUpRestDao.getAllLabHealthCheckUpView();
		
	}
}
