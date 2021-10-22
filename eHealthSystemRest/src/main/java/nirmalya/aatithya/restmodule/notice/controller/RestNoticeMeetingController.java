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
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.notice.dao.RestNoticeMeetingDao;
import nirmalya.aatithya.restmodule.notice.model.RestIntiateNoticeModel;

	@RestController
	@RequestMapping(value = { "notice/" })
	public class RestNoticeMeetingController {
		Logger logger = LoggerFactory.getLogger(RestInitiateNoticeController.class);

		@Autowired
		RestNoticeMeetingDao noticeMeetingDao;
		
		/*
		 * for employee list
		 */
		 
		@RequestMapping(value = "getempListsMeeting", method = { RequestMethod.GET })
		public List<DropDownModel> getempListsMeeting() {
			logger.info("Method : getempListsMeeting starts");

			logger.info("Method : getempListsMeeting ends");
			return noticeMeetingDao.getempListsMeetingDao();
		}
		/*
		 * for Department list
		 */
		
		@RequestMapping(value = "getdeptListsMeeting", method = { RequestMethod.GET })
		public List<DropDownModel> getDeptListsMeeting() {
			logger.info("Method : getDeptListsMeeting starts");

			logger.info("Method : getDeptListsMeeting ends");
			return noticeMeetingDao.getDeptListMeetingDao();
		}
		
		/*
		 * Add Meeting details
		 */
		
		@PostMapping(value = "rest-add-meetingDetails")
		public ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>> addMeetingDetails(
				@RequestBody List<RestIntiateNoticeModel> initiateNoticeModel) {
			logger.info("Method : addMeetingDetails starts");
			logger.info("Method : addMeetingDetails ends");
			return noticeMeetingDao.addMeetingDetailsDao(initiateNoticeModel);
		}
		
		/*
		 * View Meeting Details
		 */

		@GetMapping(value = "notice-meeting-view-rest")
		public List<RestIntiateNoticeModel> viewNewMeetingDetails() {
			logger.info("Method : viewNewMeetingDetails starts");

			logger.info("Method : viewNewMeetingDetails ends");

			return noticeMeetingDao.viewMeetingDetailsDao();
		}

}
