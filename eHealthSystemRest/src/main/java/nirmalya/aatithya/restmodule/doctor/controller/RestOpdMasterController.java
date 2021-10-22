package nirmalya.aatithya.restmodule.doctor.controller;

import java.util.List;

import javax.persistence.EntityManager;

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

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.doctor.dao.OpdMasterDao;
import nirmalya.aatithya.restmodule.doctor.model.RestOpdMasterModel;

@RestController
@RequestMapping(value = { "doctor/" })
public class RestOpdMasterController {

	Logger logger = LoggerFactory.getLogger(RestOpdMasterController.class);

	@Autowired
	EntityManager em;
	
	@Autowired
	OpdMasterDao opdMasterDao;
	
	
	
	/*
	 * view opd Name list
	 */

	@GetMapping(value = "rest-viewdoctor-opd")
	public JsonResponse<List<RestOpdMasterModel>> getOpdMasterViewRest(@RequestParam String id) {
		logger.info("Method : getOpdMasterViewRest starts");

		logger.info("Method :getOpdMasterViewRest endss");
		return opdMasterDao.getOpdMasterViewDao(id);
	}
	
	/*
	 * Add opd details
	 */

	@PostMapping(value = "save-opd-details")
	public JsonResponse<Object> saveOpdDetails(
			@RequestBody RestOpdMasterModel restOpdMasterNewModel) {
		logger.info("Method : saveOpdDetails starts");
		System.out.println(restOpdMasterNewModel);
		logger.info("Method : saveOpdDetails ends");
		return opdMasterDao.saveOpdDetailsDao(restOpdMasterNewModel);
		
	}

	/*
	 *
	 * Edit Opd details
	 *
	 */
	@RequestMapping(value = "rest-edit-opd-details", method = { RequestMethod.GET })
	public JsonResponse<List<RestOpdMasterModel>> editOpdDetails(@RequestParam String id) {
		logger.info("Method : editOpdDetails rest starts");

		logger.info("Method :editOpdDetails rest ends");
		return opdMasterDao.editOpdDetails(id);
	}
	
	
	/*
	 * Delete opd details
	 */
	
	@RequestMapping(value = "rest-deletedoctor-opd", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deleteOpdDetails(@RequestParam String id) {
		logger.info("Method : deleteOpdDetails starts");

		logger.info("Method : deleteOpdDetails ends");
		return opdMasterDao.deleteOpdDetails(id);
	}

}
