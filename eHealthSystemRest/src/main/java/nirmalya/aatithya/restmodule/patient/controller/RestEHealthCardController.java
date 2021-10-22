package nirmalya.aatithya.restmodule.patient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import nirmalya.aatithya.restmodule.patient.dao.RestEHealthCardDao;
import nirmalya.aatithya.restmodule.patient.model.RestEHealthCardModel;

@RestController
@RequestMapping(value = "user/")
public class RestEHealthCardController {
Logger logger = LoggerFactory.getLogger(RestEHealthCardController.class);
	
	@Autowired
	RestEHealthCardDao restEHealthCardDao;

	/*
	 * for title list
	 */

	@RequestMapping(value = "getEhealthCardInformation", method = { RequestMethod.GET })
	public RestEHealthCardModel getEhealthCardDetails(@RequestParam String id) {
		logger.info("Method : getEhealthCardDetails starts");

		logger.info("Method : getEhealthCardDetails ends");
		return restEHealthCardDao.getEhealthCardDetailsDao(id);
	}

}
