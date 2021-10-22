package nirmalya.aatithya.restmodule.patient.controller;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.dao.RestImmunizationDao;
import nirmalya.aatithya.restmodule.patient.model.RestImmunizationModel;
import nirmalya.aatithya.restmodule.user.model.RestUserRegistrationModel;

@RestController
@RequestMapping(value = { "user/" })
public class RestImmunizationController {
	Logger logger = LoggerFactory.getLogger(RestImmunizationController.class);

	@Autowired
	RestImmunizationDao restImmunizationDao;

	/*
	 * for get Immunization Type
	 */

	@RequestMapping(value = "getImmunizationTypeList", method = { RequestMethod.GET })
	public List<DropDownModel> getImmunizationType() {
		logger.info("Method : getImmunizationType starts");

		logger.info("Method : getImmunizationType ends");
		return restImmunizationDao.getImmunizationTypeList();
	}

	// View Details
	@RequestMapping(value = "rest-viewImmunization", method = { RequestMethod.GET })
	public JsonResponse<List<RestImmunizationModel>> viewImmunization(@RequestParam BigInteger id) {
		logger.info("Method : viewImmunization starts");

		logger.info("Method : viewImmunization ends");
		return restImmunizationDao.viewImmunizationDao(id);
	}

}
