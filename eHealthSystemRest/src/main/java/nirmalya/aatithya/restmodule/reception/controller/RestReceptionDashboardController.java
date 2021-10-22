package nirmalya.aatithya.restmodule.reception.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.model.RestPatientDetailsNewModel;
import nirmalya.aatithya.restmodule.reception.dao.RestReceptionDashboardDao;
import nirmalya.aatithya.restmodule.reception.model.RestReceptionDashboardModel;
@RestController
@RequestMapping("reception/")
public class RestReceptionDashboardController {
	Logger logger = LoggerFactory.getLogger(RestReceptionDashboardController.class);

	@Autowired

	RestReceptionDashboardDao restReceptionDashboardDao;
	
	/**
	 *
	 * @return Appointment Count
	 */
	
	
	@RequestMapping(value = "rest-receptionCount", method = { RequestMethod.GET })
	public List<RestReceptionDashboardModel> receptionCount(@RequestParam String id) {

		logger.info("Method : receptionCount starts");
		logger.info("Method : receptionCount ends");

		return restReceptionDashboardDao.receptionCount(id);
	}
	
	/**
	 * Rest Controller - disease wise total abnormal
	 *
	 */
	@GetMapping(value = "rest-viewPatientDiseasel")
	public JsonResponse<List<DropDownModel>> viewPatientDisease(String id) {
		logger.info("Method : viewPatientDisease starts");

		logger.info("Method : viewPatientDisease ends");
		return restReceptionDashboardDao.viewPatientDisease(id);
	}
	
	/**
	 * Rest Controller - disease wise total abnormal
	 *
	 */
	@GetMapping(value = "rest-viewAppointmentStatus")
	public JsonResponse<List<DropDownModel>> viewAppointmentStatus(String id) {
		logger.info("Method : viewAppointmentStatus starts");

		logger.info("Method : viewAppointmentStatus ends");
		return restReceptionDashboardDao.viewAppointmentStatus(id);
	}
}
