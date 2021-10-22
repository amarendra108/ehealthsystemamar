package nirmalya.aatithya.restmodule.demo.controller;

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

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.demo.dao.StudentNewDao;
import nirmalya.aatithya.restmodule.demo.model.RestStudentNewModel;

@RestController
@RequestMapping("demo/")

public class RestStudentNewController {
	
	Logger logger = LoggerFactory.getLogger(RestStudentNewController.class);
	
	@Autowired
	
	StudentNewDao StudentNewDao;
	
	
	
	@PostMapping(value = "addstud")
	public ResponseEntity<JsonResponse<Object>> addstud(@RequestBody RestStudentNewModel studentNewModel) {
		logger.info("Method :restAdd starts");
		System.out.println(studentNewModel);
		logger.info("Method :restAdd endss");
		return StudentNewDao.addstud(studentNewModel);
	}
	
	@GetMapping(value = "get-country-list")
	public List<DropDownModel> getcountrylist() {
		logger.info("Method : demo starts");

		logger.info("Method : demo ends");
		return StudentNewDao.getcountrylist();
	}
	@GetMapping(value = "getbloodGroup")
	public List<DropDownModel> getbloodGroup() {
		logger.info("Method : demo starts");

		logger.info("Method : demo ends");
		return StudentNewDao.getbloodGroup();
	}

	@GetMapping(value = "rest-get-stateList-New")
	public ResponseEntity<JsonResponse<List<DropDownModel>>>getstateList(@RequestParam String id) {
		logger.info("Method : getstateList starts");
		logger.info("Method : getstateList ends");
		return StudentNewDao.getstateList(id);
	}

	@GetMapping(value = "/rest-get-distList-New")
	public ResponseEntity<JsonResponse<List<DropDownModel>>>getdistList(@RequestParam String id) {
		logger.info("Method : getdistList starts");

		logger.info("Method : getdistList end");
		return StudentNewDao.getdistList(id);
	}
	
	@GetMapping(value = "getAllstudentt")
	public JsonResponse<List<RestStudentNewModel>> getAllstudentt() {
		logger.info("Method :restAddemployee starts");
		
		
		logger.info("Method :restAddemployee endss");
		return StudentNewDao.getAllstudentt();
	}
	
	@GetMapping(value = "editstudentt")
	public ResponseEntity<JsonResponse<RestStudentNewModel>> editstudentt(@RequestParam String id) {
		logger.info("Method : editstudentt starts");

		logger.info("Method :editstudentt ends");
		return StudentNewDao.editstudentt(id);
	}
	@GetMapping(value = "deletestudd")
	 public JsonResponse<RestStudentNewModel> deleteemp(@RequestParam String id) {
		logger.info("Method : deletestudd starts");

		logger.info("Method : deletestudd ends");
		return StudentNewDao.deletestudd(id); 
	}

}
