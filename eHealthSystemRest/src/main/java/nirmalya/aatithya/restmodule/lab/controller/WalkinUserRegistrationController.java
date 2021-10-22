package nirmalya.aatithya.restmodule.lab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.dao.WalkinUserRegistrationDao;
import nirmalya.aatithya.restmodule.user.model.RestUserRegistrationModel;


@RestController
@RequestMapping(value = { "lab/" })
public class WalkinUserRegistrationController {
	Logger logger = LoggerFactory.getLogger(WalkinUserRegistrationController.class);

	@Autowired
	WalkinUserRegistrationDao walkinUserRegistrationDao;

	@GetMapping(value = "rest-getAddress")
	public ResponseEntity<JsonResponse<RestUserRegistrationModel>> getAddress(
			@RequestParam String userId) {
		logger.info("Method : getAddress starts");

		logger.info("Method :getAddress ends");
		return walkinUserRegistrationDao.getAddressDao(userId);
	}
	
}
