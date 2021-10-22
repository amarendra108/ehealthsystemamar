package nirmalya.aatithya.restmodule.doctor.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.doctor.dao.DoctorDashBoardDao;
import nirmalya.aatithya.restmodule.doctor.model.RestDoctorDashboardCountModel;
import nirmalya.aatithya.restmodule.doctor.model.RestDoctorDashboardPatientModel;
import nirmalya.aatithya.restmodule.doctor.model.RestPatientDetailDoctorDashBoardModel;

@RestController
@RequestMapping(value = { "doctor/" })
public class RestDoctorDashBoardController {
	Logger logger = LoggerFactory.getLogger(RestDoctorDashBoardController.class);

	@Autowired
	DoctorDashBoardDao doctorDashboardDao; 
	
	/*
	 * for doctor dashboard details list
	 */
	@RequestMapping(value = "getdashboardBooking", method = { RequestMethod.GET })
	public List<RestDoctorDashboardCountModel> getdashboardBooking(@RequestParam String id) {

		logger.info("Method : getdashboardBooking starts");
		logger.info("Method : getdashboardBooking ends");

		return doctorDashboardDao.getdashboardBooking(id);
	}
	/*
	 * for Action list
	 */
	@RequestMapping(value = "getactionType", method = { RequestMethod.GET })
	public List<DropDownModel> getactionType() {
		logger.info("Method : getactionType starts");
		
		logger.info("Method : getactionType ends");

		return doctorDashboardDao.getactionType();
	}
	/*
	 * for today,tomorrow date
	 */
	@RequestMapping(value = "getTomorrowDate", method = { RequestMethod.GET })
	public  JsonResponse<List<RestDoctorDashboardPatientModel>> getTomorrowDate(@RequestParam String userid, @RequestParam String date) {
		logger.info("Method : getTomorrowDate starts");

		logger.info("Method : getTomorrowDate ends");
		return doctorDashboardDao.getTomorrowDate(userid,date);
	}
	/*
	 * for Piechart list
	 */
	
	@RequestMapping(value = "getdashboardPiechart", method = { RequestMethod.GET })
	public JsonResponse<List<DropDownModel>> getdashboardPiechart(@RequestParam String id) {

		logger.info("Method : getdashboardPiechart starts");
		logger.info("Method : getdashboardPiechart ends");

		return doctorDashboardDao.getdashboardPiechart(id);
	}
	
	/*
	 * doctor dashboard update patient appointment
	 */
	@PostMapping(value = "getPatientDetails")
	public JsonResponse<Object> getpatientDetailsById(@RequestBody DropDownModel  dropdownmodel
			) {
		logger.info("Method : getpatientDetailsById starts");
		
		logger.info("Method : getpatientDetailsById ends");
		return doctorDashboardDao.getpatientDetailsById(dropdownmodel);
	}
	/*
	 * for Pateint list
	 */
	@RequestMapping(value = "getPatientDetails", method = { RequestMethod.GET })
	public JsonResponse<List<RestPatientDetailDoctorDashBoardModel>> getPatient(@RequestParam String id) {
		logger.info("Method : getPatient starts");

		logger.info("Method : getPatient ends");
		return doctorDashboardDao.getPatient(id);
	}
}
