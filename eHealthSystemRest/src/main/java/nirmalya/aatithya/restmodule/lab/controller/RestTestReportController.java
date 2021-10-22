package nirmalya.aatithya.restmodule.lab.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.dao.RestTestReportDao;
import nirmalya.aatithya.restmodule.lab.model.RestTestReportModel;

@RestController
@RequestMapping(value = { "lab/" })
public class RestTestReportController {
	Logger logger = LoggerFactory.getLogger(RestTestReportController.class);

	@Autowired
	RestTestReportDao restTestReportDao;

	/*
	 * for test group
	 */

	@RequestMapping(value = "testGroupNameTypeLists", method = { RequestMethod.GET })
	public List<DropDownModel> testGroupNameType() {
		logger.info("Method : testGroupNameType starts");

		logger.info("Method : testGroupName ends");
		return restTestReportDao.testGroupNameTypeLists();
	}
	/*
	 *  Test Report Details
	 */

	@GetMapping(value = "rest-getReportType")
	public JsonResponse<List<RestTestReportModel>> getReportType(@RequestParam String id) {
		logger.info("Method : getReportType starts");
		System.out.println(id);
		logger.info("Method : getReportType ends");
		return restTestReportDao.getReportTypeDao(id);
	}
	// add Test Report

	@PostMapping(value = "rest-addLabTestReport")
	public JsonResponse<List<RestTestReportModel>> addLabTestReport(
			@RequestBody List<RestTestReportModel> testReport) {
		logger.info("Method : addLabTestReport starts");

		logger.info("Method : addLabTestReport ends");
		return restTestReportDao.addLabTestReportDao(testReport);
	}
	// view Test Report
	@GetMapping(value="rest-viewLabTestReport")
	public JsonResponse<List<RestTestReportModel>> viewLabTestReport(@RequestParam String id){
		logger.info("Method : viewLabTestReport starts");
		
		logger.info("Method : viewLabTestReport ends");
		return restTestReportDao.viewLabTestReportDao(id);
	}
}
