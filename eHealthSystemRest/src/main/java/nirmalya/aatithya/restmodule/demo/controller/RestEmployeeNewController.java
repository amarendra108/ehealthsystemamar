package nirmalya.aatithya.restmodule.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.demo.dao.EmployeeNewDao;
import nirmalya.aatithya.restmodule.demo.model.RestEmployeeNewModel;
import nirmalya.aatithya.restmodule.recruitment.model.AddRecruitentModel;

@RestController
@RequestMapping("demo/")
public class RestEmployeeNewController {

	Logger logger = LoggerFactory.getLogger(RestEmployeeNewController.class);

	@Autowired
	EmployeeNewDao EmployeeNewDao;

	@RequestMapping(value = "getgenderlist2", method = { RequestMethod.GET })
	public List<DropDownModel> getgenderlist2() {
		logger.info("Method : demo starts");

		logger.info("Method : demo ends");
		return EmployeeNewDao.getgenderlist2();
	}

	@RequestMapping(value = "getmaritalstatusList2", method = { RequestMethod.GET })
	public List<DropDownModel> getmaritalstatusList2() {
		logger.info("Method : demo starts");

		logger.info("Method : demo ends");
		return EmployeeNewDao.getmaritalstatusList2();
	}

	@RequestMapping(value = "addEmp", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> addEmp(@RequestBody RestEmployeeNewModel employeeNewModel) {
		logger.info("Method :restAddemployee starts");
		System.out.println(employeeNewModel);
		logger.info("Method :restAddemployee endss");
		return EmployeeNewDao.addEmp(employeeNewModel);
	}

	@RequestMapping(value = "getAllemployee", method = { RequestMethod.GET})
	public JsonResponse<List<RestEmployeeNewModel>> getAllemployee() {
		logger.info("Method :restAddemployee starts");
		
		
		logger.info("Method :restAddemployee endss");
		return EmployeeNewDao.getAllemployee();
	}
	
	@RequestMapping(value = "editemployee", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<RestEmployeeNewModel>> editemployee(@RequestParam String id) {
		logger.info("Method : editemployee starts");

		logger.info("Method :editemployee ends");
		return EmployeeNewDao.editemployee(id);
	}
	@RequestMapping(value = "deleteemp", method = { RequestMethod.GET})
	 public JsonResponse<RestEmployeeNewModel> deleteemp(@RequestParam String id) {
		logger.info("Method : deleteemp starts");

		logger.info("Method : deleteemp ends");
		return EmployeeNewDao.deleteemp(id); 
	}

}
