package nirmalya.aatithya.restmodule.patient.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.patient.dao.RestPatientProfileDao;

@RestController
@RequestMapping("user/")
public class RestPatientProfileController {
Logger logger = LoggerFactory.getLogger(RestPatientProfileController.class);
	
	@Autowired
	
	RestPatientProfileDao RestPatientProfileDao;	
	
/*	@RequestMapping(value = "getgenderListnew", method = { RequestMethod.GET })
	public List<DropDownModel> getgenderListnew() {
		logger.info("Method : getgenderListnew starts");

		logger.info("Method : getgenderListnew ends");
		return RestPatientProfileDao.getgenderListnew();
	}

	
	@RequestMapping(value = "getbloodgroupListnew", method = { RequestMethod.GET })
	public List<DropDownModel> getbloodgroupList1() {
		logger.info("Method : getbloodgroupListnew starts");

		logger.info("Method : getbloodgroupListnew ends");
		return RestPatientProfileDao.getbloodgroupListnew();
	}

	@RequestMapping(value = "getmaritalstatusListnew", method = { RequestMethod.GET })
	public List<DropDownModel> getmaritalstatusListnew() {
		logger.info("Method : getmaritalstatusListnew starts");

		logger.info("Method : getmaritalstatusListnew ends");
		return RestPatientProfileDao.getmaritalstatusListnew();
	}*/
}
