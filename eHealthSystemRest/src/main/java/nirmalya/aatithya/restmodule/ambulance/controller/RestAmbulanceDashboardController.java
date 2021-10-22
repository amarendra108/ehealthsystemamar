package nirmalya.aatithya.restmodule.ambulance.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.ambulance.dao.RestAmbulanceDashboardDao;
import nirmalya.aatithya.restmodule.ambulance.model.AmbulanceDashboardRestModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.doctor.model.RestPatientDetailDoctorDashBoardModel;

@RestController
@RequestMapping("ambulance/")
public class RestAmbulanceDashboardController {
	Logger logger = LoggerFactory.getLogger(RestAmbulanceDashboardController.class);

	@Autowired

	RestAmbulanceDashboardDao restAmbulanceDashboardDao;
	/**
	 *
	 * @return Appointment Count
	 */
	
	
	@RequestMapping(value = "getdashboardBooking", method = { RequestMethod.GET })
	public List<AmbulanceDashboardRestModel> appointmentCount(@RequestParam String id) {

		logger.info("Method : appointmentCount starts");
		logger.info("Method : appointmentCount ends");

		return restAmbulanceDashboardDao.appointmentCount(id);
	}
	
	
	@RequestMapping(value = "rest-viewpatientrequested", method = { RequestMethod.GET })
	public  JsonResponse<List<AmbulanceDashboardRestModel>> getPatient(@RequestParam String userid,@RequestParam String date) {

		logger.info("Method : getPatient starts");
		logger.info("Method : getPatient ends");

		return restAmbulanceDashboardDao.getPatientPrescription(userid,date);
	}
	
	@RequestMapping(value = "rest-saveAmbulanceStatus", method = { RequestMethod.GET })
	public JsonResponse<Object> saveAmbulanceStatus(@RequestParam String orderid,  @RequestParam String status) {
		logger.info("Method : saveAmbulanceStatus starts");
		logger.info("Method : saveAmbulanceStatus ends");
		return restAmbulanceDashboardDao.saveAmbulanceStatusDao(orderid,status);
	}
	
	/*
	 * for Pateint list
	 */
	@RequestMapping(value = "getPatientDetails", method = { RequestMethod.GET })
	public JsonResponse<List<AmbulanceDashboardRestModel>> getPatient(@RequestParam String id) {
		logger.info("Method : getPatient starts");

		logger.info("Method : getPatient ends");
		return restAmbulanceDashboardDao.getPatient(id);
	}
	
}
