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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.notice.dao.RestNoticeTypeMastreDao;
import nirmalya.aatithya.restmodule.notice.model.RestNoticeTypeMasterModel;

@RestController
@RequestMapping("notice/")
public class RestNoticeTypeMasterController {
	Logger logger = LoggerFactory.getLogger(RestNoticeTypeMasterController.class);
	@Autowired
	RestNoticeTypeMastreDao noticeDao;

	/*
	 * View Notice Details
	 *
	 */

	@GetMapping(value = "rest-viewNoticeDetails")
	public JsonResponse<List<RestNoticeTypeMasterModel>> viewNoticeDtls() {
		logger.info("Method : viewNoticeDtls starts");

		logger.info("Method : viewNoticeDtls ends");

		return noticeDao.viewNoticeDtlsDao();
	}

	/*
	 * Add Notice Details
	 *
	 */
	@PostMapping(value = "addNotice")
	public ResponseEntity<JsonResponse<Object>> addRestNotice(@RequestBody RestNoticeTypeMasterModel noticerestModel) {
		logger.info("Method :restAddcust starts");

		logger.info("Method :restAddcust endss");
		return noticeDao.addNotice(noticerestModel);
	}

	/*
	 * Edit Notice Details
	 *
	 */
	@GetMapping(value = "editNotice")
	public ResponseEntity<JsonResponse<RestNoticeTypeMasterModel>> editNotice(@RequestParam String id) {
		logger.info("Method : editNotice starts");

		logger.info("Method :editNotice ends");
		return noticeDao.editNotice(id);
	}

	/*
	 * Delete Notice Details
	 *
	 */
	@GetMapping(value = "deleteNotice")
	public JsonResponse<RestNoticeTypeMasterModel> deleteNotice(@RequestParam String deleteId) {
		logger.info("Method : deleteNotice starts");
		System.out.println("%%%%%%%%%%" + deleteId);
		logger.info("Method :deleteNotice ends");
		return noticeDao.deleteNotice(deleteId);
	}
}
