package nirmalya.aatithya.restmodule.notice.controller;

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
import nirmalya.aatithya.restmodule.notice.dao.RestIntiateNoticeDao;
import nirmalya.aatithya.restmodule.notice.model.RestIntiateNoticeModel;

@RestController
@RequestMapping(value = { "notice/" })
public class RestInitiateNoticeController {
	Logger logger = LoggerFactory.getLogger(RestInitiateNoticeController.class);

	@Autowired
	RestIntiateNoticeDao initiateNoticeDao;

	/*
	 * for employee list
	 */
	
	@RequestMapping(value = "getempLists", method = { RequestMethod.GET })
	public List<DropDownModel> getempLists() {
		logger.info("Method : getempLists starts");

		logger.info("Method : getempLists ends");
		return initiateNoticeDao.getempLists();
	}
	/*
	 * for Department list
	 */
	
	@RequestMapping(value = "getdeptLists", method = { RequestMethod.GET })
	public List<DropDownModel> getDeptLists() {
		logger.info("Method : getDeptLists starts");

		logger.info("Method : getDeptLists ends");
		return initiateNoticeDao.getDeptListDao();
	}

	/*
	 * auto search Notice Type
	 */
	
	@GetMapping(value = "getNoticeListByAutoSearch")
	public ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>> getNoticeListByAutoSearch(
			@RequestParam String id) {
		logger.info("Method : getNoticeListByAutoSearch starts");

		logger.info("Method :getNoticeListByAutoSearch endss");
		return initiateNoticeDao.getNoticeListByAutoSearch(id);
	}

	/*
	 * view Notice Initiate
	 */
	
	@GetMapping(value = "rest-viewNoticeinitiate")
	public JsonResponse<List<RestIntiateNoticeModel>> viewNoticeInitDao() {
		logger.info("Method : viewNoticeDtls starts");

		logger.info("Method : viewNoticeDtls ends");

		return initiateNoticeDao.viewNoticeInitDao();
	}

	/*
	 * Add initiate notice
	 */
	
	@PostMapping(value = "rest-add-notice")
	public ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>> restAddRfq(
			@RequestBody List<RestIntiateNoticeModel> initiateNoticeModel) {
		logger.info("Method : restAddRfq starts");
		logger.info("Method : restAddRfq ends");
		return initiateNoticeDao.restaddNoticeDao(initiateNoticeModel);
	}

	/*
	 * Edit Notice Details
	 */

	@GetMapping(value = "get-notice-edit")
	public List<RestIntiateNoticeModel> editNotice(@RequestParam String id) {
		logger.info("Method : editNotice starts");
		logger.info("Method : editNotice ends");
		return initiateNoticeDao.editNoticeinitiate(id);
	}

	/*
	 * Delete Notice Details
	 */

	@GetMapping(value = "delete-notice-details")
	public JsonResponse<RestIntiateNoticeModel> deleteNoticeDetails(@RequestParam String id) {
		logger.info("Method : deleteNoticeDetails starts");
		logger.info("Method : deleteNoticeDetails ends");
		return initiateNoticeDao.deleteNoticeDetailsDao(id);
	}

	/*
	 * Add Draft Details
	 */
	
	@PostMapping(value = "add-draft-details")
	public ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>> restAddDrafts(
			@RequestBody List<RestIntiateNoticeModel> initiateNoticeModel) {
		logger.info("Method : restAddDrafts starts");
		logger.info("Method : restAddDrafts ends");
		return initiateNoticeDao.restAddDraftsDao(initiateNoticeModel);
	}
	
	/*
	 * View Drafts
	 */

	@GetMapping(value = "rest-viewDrafttable")
	public JsonResponse<List<RestIntiateNoticeModel>> viewDraftDao(@RequestParam String noticeId,
			@RequestParam String createdBy) {
		logger.info("Method : viewNoticeDtls starts");

		logger.info("Method : viewNoticeDtls ends");

		return initiateNoticeDao.viewDraftDao(noticeId, createdBy);
	}

	/*
	 * Edit Draft Details
	 */

	@GetMapping(value = "edit-draft-details")
	public List<RestIntiateNoticeModel> EditDraftDetails(@RequestParam String id) {
		logger.info("Method : EditDraftDetails starts");
		logger.info("Method : EditDraftDetails ends");
		return initiateNoticeDao.editDraftDetailsDao(id);
	}
	
	/*
	 * Add send Details
	 */
	@PostMapping(value = "rest-send-details")
	public ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>> restAddSendDetails(
			@RequestBody List<RestIntiateNoticeModel> initiateNoticeModel) {
		logger.info("Method : restAddSendDetails starts");
		logger.info("Method : restAddSendDetails ends");
		return initiateNoticeDao.restAddSendDetails(initiateNoticeModel);
	}
	
	/*
	 * get mail  Details
	 */

	@RequestMapping(value = "getmaildetails", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> getmaildetails(@RequestParam String mylist) {
		logger.info("Method : getmaildetails starts");

		logger.info("Method : getmaildetails ends");
		return initiateNoticeDao.getmaildetails(mylist);
	}

	/*
	 * View all Details
	 */

	@GetMapping(value = "notice-show-details")
	public List<RestIntiateNoticeModel> viewDocuments(@RequestParam String id) {
		logger.info("Method : viewDocuments starts");
		logger.info("Method : viewDocuments ends");
		return initiateNoticeDao.viewDocumentsDao(id);
	}

	/*
	 * Add Meeting details
	 */
	
	@PostMapping(value = "rest-add-meetingdetails")
	public ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>> addMeetingDetailsRest(
			@RequestBody List<RestIntiateNoticeModel> initiateNoticeModel) {
		logger.info("Method : addMeetingDetailsRest starts");
		logger.info("Method : addMeetingDetailsRest ends");
		return initiateNoticeDao.addMeetingDetailsDao(initiateNoticeModel);
	}
}
