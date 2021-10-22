package nirmalya.aatithya.restmodule.patient.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.dao.LifeStyleHistoryDao;
import nirmalya.aatithya.restmodule.patient.model.LifeStyleHistoryModel;

@RestController
@RequestMapping("user/")
public class LifeStyleHistoryRestController {
	Logger logger = LoggerFactory.getLogger(LifeStyleHistoryRestController.class);

	@Autowired

	LifeStyleHistoryDao lifeStyleHistoryDao;

	/*
	 * For Smoking List Dropdown
	 */

	@RequestMapping(value = "getSmokingList", method = { RequestMethod.GET })
	public List<DropDownModel> getSmokingList() {
		logger.info("Method : getSmokingList starts");

		logger.info("Method : getSmokingList ends");
		return lifeStyleHistoryDao.getSmokingList();
	}

	/*
	 * For Alcohol List Dropdown
	 */

	@RequestMapping(value = "getAlcoholList", method = { RequestMethod.GET })
	public List<DropDownModel> getAlcoholList() {
		logger.info("Method : getAlcoholList starts");

		logger.info("Method : getAlcoholList ends");
		return lifeStyleHistoryDao.getAlcoholList();
	}

	/*
	 * Post mapping for Add Life Style History
	 */
	@PostMapping(value = "patientLifeHistoryAdd")
	public JsonResponse<Object> addPatientLifeHistory(@RequestBody LifeStyleHistoryModel employee) {
		logger.info("Method : addPatientLifeHistory starts");

		logger.info("Method : addPatientLifeHistory ends");
		return lifeStyleHistoryDao.patientLifeStyleHistoryAdd(employee);
	}

	/*
	 * Edit For Life Style History
	 */

	@GetMapping(value = "editpatientLifeStyle")
	public JsonResponse<LifeStyleHistoryModel> editpatientLifeStyle(@RequestParam String id) {
		logger.info("Method : editpatientLifeStyle starts");
		System.out.println(id);
		logger.info("Method : editpatientLifeStyle ends");
		return lifeStyleHistoryDao.editpatientLifeStyle(id);
	}

}
