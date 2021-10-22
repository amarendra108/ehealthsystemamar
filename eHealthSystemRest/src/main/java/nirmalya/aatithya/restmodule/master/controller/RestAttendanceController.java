package nirmalya.aatithya.restmodule.master.controller;

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

import nirmalya.aatithya.restmodule.common.utils.DataTableRequest;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.master.dao.RestAttendanceDao;
import nirmalya.aatithya.restmodule.master.model.RestAttendanceModel;

@RestController

@RequestMapping("master/")
public class RestAttendanceController {
	Logger logger = LoggerFactory.getLogger(RestAttendanceController.class);

	@Autowired
	RestAttendanceDao restStudentDao;

	// Post mapping for add employee

	@PostMapping(value = "addEmployeeAttendances")
	public ResponseEntity<JsonResponse<Object>> addEmployeeAttendances(@RequestBody RestAttendanceModel employee) {
		logger.info("Method : addEmployeeAttendances starts");

		logger.info("Method : addEmployeeAttendances ends");
		return restStudentDao.addEmployeeAttendances(employee);
	}

	// Post mapping foe view employee

	@GetMapping(value = "viewNewEmployeeAttendances")
	public JsonResponse<List<RestAttendanceModel>> viewNewEmployeeAttendances() {
		logger.info("Method : viewNewEmployeeAttendances");

		logger.info("Method : viewNewEmployeeAttendances ends");
		return restStudentDao.viewNewEmployeeAttendances();
	}

//	returns punchIn time

	@RequestMapping(value="get-details-punchout",method={RequestMethod.GET})

	public List<DropDownModel> getDetailsPunchout(@RequestParam String empId, @RequestParam String date) {
		logger.info("Method : getDetailsPunchout starts");
		logger.info("Method : getDetailsPunchout ends");
		return restStudentDao.getDetailsPunchout(empId, date);
	}

	//returns all attendence details

	@RequestMapping(value="/viewAttendencePunchoutThroughAjax",method={RequestMethod.POST})

	public ResponseEntity<JsonResponse<List<RestAttendanceModel>>> getAllAttendence(

			@RequestBody DataTableRequest request) {
		logger.info("Method : viewAttendencePunchoutThroughAjax starts");
		logger.info("Method : viewAttendencePunchoutThroughAjax endss");
		return restStudentDao.viewAttendencePunchoutThroughAjax(request);
	}

	// Post mapping for add employee

	@PostMapping(value = "addEmployeeAttendancePunchOut")
	public ResponseEntity<JsonResponse<Object>> addEmployeeAttendancePunchOut(
			@RequestBody RestAttendanceModel employee1) {
		logger.info("Method : addEmployeeAttendancePunchOut starts");

		logger.info("Method : addEmployeeAttendancePunchOut ends");
		return restStudentDao.addEmployeeAttendancePunchOut(employee1);
	}

}
