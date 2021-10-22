package nirmalya.aatithya.restmodule.recruitment.controller;

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
import nirmalya.aatithya.restmodule.recruitment.dao.HireActionDao;
import nirmalya.aatithya.restmodule.recruitment.model.ActionEmployeeDetailsModel;
import nirmalya.aatithya.restmodule.recruitment.model.CandidateDetailsModel;
import nirmalya.aatithya.restmodule.recruitment.model.HireActionModel;

@RestController
@RequestMapping("recruitment/")
public class HireActionRestController {

	Logger logger = LoggerFactory.getLogger(HireActionRestController.class);

	@Autowired
	HireActionDao hireActionDao;

	@RequestMapping(value = "EmployeeFullList", method = { RequestMethod.GET })
	public List<ActionEmployeeDetailsModel> EmployeeFullList() {
		logger.info("Method : EmployeeFullList starts");

		logger.info("Method : EmployeeFullList ends");
		return hireActionDao.EmployeeFullList();
	}

	@GetMapping(value = "getCandidateFromRequisition")
	public ResponseEntity<JsonResponse<List<CandidateDetailsModel>>> getCandidateFromRequisition(
			@RequestParam String id) {
		logger.info("Method : getCandidateFromRequisition starts");

		logger.info("Method : getCandidateFromRequisition ends");
		return hireActionDao.getCandidateFromRequisition(id);
	}

	@PostMapping(value = "scheduleInterview")
	public ResponseEntity<JsonResponse<Object>> scheduleInterview(@RequestBody HireActionModel action) {
		logger.info("Method : scheduleInterview starts");

		logger.info("Method : scheduleInterview ends");
		return hireActionDao.scheduleInterview(action);
	}

	@GetMapping(value = "getScheduleDetails")
	public ResponseEntity<JsonResponse<List<HireActionModel>>> getScheduleDetails(@RequestParam String id) {
		logger.info("Method : getScheduleDetails starts");

		logger.info("Method : getScheduleDetails ends");
		return hireActionDao.getScheduleDetails(id);
	}

	@PostMapping(value = "editScheduleInterview")
	public ResponseEntity<JsonResponse<Object>> editScheduleInterview(@RequestBody HireActionModel action) {
		logger.info("Method : editScheduleInterview starts");

		logger.info("Method : editScheduleInterview ends");
		return hireActionDao.editScheduleInterview(action);
	}

	@GetMapping(value = "shortListCandidate")
	public ResponseEntity<JsonResponse<Object>> shortListCandidate(@RequestParam String id, @RequestParam String reqId,
			@RequestParam String status) {
		logger.info("Method : shortListCandidate starts");

		logger.info("Method : shortListCandidate ends");
		return hireActionDao.shortListCandidate(id, reqId, status);
	}

	/**
	 * Rest Controller - Get Rating Category For Drop Down
	 *
	 */
	@RequestMapping(value = "ratingCategoryList", method = { RequestMethod.GET })
	public List<DropDownModel> getRatingCategory() {
		logger.info("Method : getCategory starts");

		logger.info("Method : getCategory ends");
		return hireActionDao.getRatingCategoryList();
	}

	/**
	 * Get Rating Type For Drop Down
	 *
	 */

	@RequestMapping(value = "rest-getRatingType", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getRatingType(@RequestParam("category") String category) {
		logger.info("Method : getRatingType starts");

		logger.info("Method : getRatingType ends");
		return hireActionDao.getRatingTypeDao(category);
	}

	/*
	 * add FeedBack
	 */
	@PostMapping(value = "rest-addFeedback")
	public ResponseEntity<JsonResponse<List<HireActionModel>>> addFeedback(
			@RequestBody List<HireActionModel> feedBackModel) {
		logger.info("Method :addFeedback starts");

		logger.info("Method :addFeedback endss");
		return hireActionDao.addFeedbackDao(feedBackModel);
	}
	/*
	 * View Feedback
	 */

	@GetMapping(value = "viewFeedbackDetails")
	public JsonResponse<List<HireActionModel>> getFeedbackDetails(@RequestParam String id) {
		logger.info("Method : getFeedbackDetails starts");

		logger.info("Method : getFeedbackDetails ends");
		return hireActionDao.getFeedbackDetails(id);
	}

	/*
	 * edit Feedback
	 */
	@GetMapping(value = "rest-editFeedback")
	public JsonResponse<List<HireActionModel>> editFeedback(@RequestParam String reqId, @RequestParam String candId) {
		logger.info("Method : editFeedback starts");

		logger.info("Method : editFeedback endss");
		return hireActionDao.editFeedbackDao(reqId, candId);
	}

}
