package nirmalya.aatithya.restmodule.patient.controller;

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
import nirmalya.aatithya.restmodule.patient.dao.RestLabTestReportDao;
import nirmalya.aatithya.restmodule.patient.model.RestLabTestReportModel;

@RestController
@RequestMapping(value = "user/")
public class RestLabTestReportController {
Logger logger = LoggerFactory.getLogger(RestLabTestReportController.class);
	
	@Autowired
	RestLabTestReportDao labtestreportDao;
	

	
	@GetMapping(value="getpatienttestdetails")
	public JsonResponse<List<RestLabTestReportModel>> getPatientTestDetails(@RequestParam String id){
		logger.info("Method : getPatientTestDetails starts");
		
		logger.info("Method : getPatientTestDetails ends");
		return labtestreportDao.getPatientTestDetails(id);
	}
	//PDF
	@GetMapping(value="rest-getTestTetailss")
	public JsonResponse<List<RestLabTestReportModel>> getTestTetailss(@RequestParam String id,@RequestParam String id2){
		logger.info("Method : getTestTetailss starts");
		
		logger.info("Method : getTestTetailss ends");
		return labtestreportDao.getTestTetailssDao(id,id2);
	}
}
