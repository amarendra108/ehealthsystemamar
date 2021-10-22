package nirmalya.aathithya.webmodule.recruitment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.Constant;
import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.EmailCalendarEvent;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.recruitment.model.AddRecruitentModel;
import nirmalya.aathithya.webmodule.recruitment.model.CandidateDetailsModel;
import nirmalya.aathithya.webmodule.recruitment.model.HireActionModel;

@Controller
@RequestMapping(value = "recruitment")
public class HireActionController {

	Logger logger = LoggerFactory.getLogger(HireActionController.class);
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;
	
	@SuppressWarnings("unchecked")
	@GetMapping("review-requi-mstr-view-data")
	public @ResponseBody List<AddRecruitentModel> viewRequisitionThroughAjax(Model model, HttpServletRequest request, HttpSession session) {
		logger.info("Method : viewRequisitionThroughAjax starts");

		JsonResponse<List<AddRecruitentModel>> jsonResponse = new JsonResponse<List<AddRecruitentModel>>();

		int count = 0;
		try {

			jsonResponse = restTemplate.getForObject(env.getRecruitment() + "viewRequistion",
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			List<AddRecruitentModel> addreq = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<List<AddRecruitentModel>>() {
					});
			for(AddRecruitentModel m : addreq) {
				String date = "";
				count++;
				m.setCount(count);
				if(m.getActivityStatus().equals("1")) {
					
					m.setActivityStatus("Created");
				} else if(m.getActivityStatus().equals("2")) {
					m.setActivityStatus("Active");
				} else if(m.getActivityStatus().equals("3")) {
					m.setActivityStatus("Closed");
				}
				
				String dateFormat = (String) (session).getAttribute("DATEFORMAT");
				if(m.getJoinDate() != null && m.getJoinDate() != "") {
					date = DateFormatter.dateFormat(m.getJoinDate(),dateFormat);
					m.setJoinDate(date);
				}
				if(m.getCreatedOn() != null && m.getCreatedOn() != "") {
					date = DateFormatter.dateFormat(m.getCreatedOn(),dateFormat);
					m.setCreatedOn(date);
				}
			}
			
			jsonResponse.setBody(addreq);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method ; viewRequisitionThroughAjax ends");

		return jsonResponse.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-candidate-as-per-requisition")
	public @ResponseBody List<CandidateDetailsModel> getCandidateFromRequisition(Model model, HttpSession session, @RequestParam String id){
		
		logger.info("Method : getCandidateFromRequisition starts");
		
		JsonResponse<List<CandidateDetailsModel>> resp = new JsonResponse<List<CandidateDetailsModel>>();
		
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "getCandidateFromRequisition?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		List<CandidateDetailsModel> actionReq = mapper.convertValue(resp.getBody(),
				new TypeReference<List<CandidateDetailsModel>>() {
				});
		for(CandidateDetailsModel m : actionReq) {
			String date = "";
			
			String dateFormat = (String) (session).getAttribute("DATEFORMAT");
			if(m.getDob() != null && m.getDob() != "") {
				date = DateFormatter.dateFormat(m.getDob(),dateFormat);
				m.setDob(date);
			}
			if(m.getCreatedOn() != null && m.getCreatedOn() != "") {
				date = DateFormatter.dateFormat(m.getCreatedOn(),dateFormat);
				m.setCreatedOn(date);
			}

		}
		
		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		resp.setBody(actionReq);
		
		System.out.println(resp.getBody());
		logger.info("Method : getCandidateFromRequisition ends");
		
		return resp.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/review-add-schedule-interview")
	public @ResponseBody JsonResponse<Object> scheduleInterview(Model model, HttpSession session, @RequestBody HireActionModel action,
				BindingResult result) {
			logger.info("Method : scheduleInterview starts");
			
			JsonResponse<Object> res = new JsonResponse<Object>();
			
			String userId = "";
			String dateFormater = "";
			try {
				userId = (String) session.getAttribute("USER_ID");
				dateFormater = (String) session.getAttribute("DATEFORMAT");

			} catch (Exception e) {
				e.printStackTrace();
			}
			action.setCreatedBy(userId);
			if(action.getFromDate()!=null && action.getFromDate()!="") {
				action.setFromDate(DateFormatter.inputDateFormat(action.getFromDate(), dateFormater));
			}
			if(action.getToDate()!=null && action.getToDate()!="") {
				action.setToDate(DateFormatter.inputDateFormat(action.getToDate(), dateFormater));
			}
			try {
				res = restTemplate.postForObject(env.getRecruitment() + "scheduleInterview", action,
						JsonResponse.class);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (res.getMessage() == "" || res.getMessage() == null) {
				
				res.setMessage("Success");
				EmailCalendarEvent emailEvent = new EmailCalendarEvent();
				
				String mailFrom = Constant.mailFrom;
				String host = Constant.host;
				String port = Constant.port;
				String password = Constant.password;
				
				List<String> candMail = action.getCandEmail();
				List<String> interviewerMail = action.getInterviewerMail();
				
				candMail.addAll(interviewerMail);
				
				String dateFormat = (String) (session).getAttribute("DATEFORMAT");
				String fromDateTime = DateFormatter.inputDateFormat(action.getFromDate(), dateFormat) + " " + action.getFromTime() + ":00";
				String toDateTime =  DateFormatter.inputDateFormat(action.getToDate(), dateFormat) + " " + action.getToTime() + ":00";
				
				try {
					emailEvent.send(action.getTitle(), mailFrom, password, candMail, host, port, action.getLocationName(),
							action.getSummary(), action.getDescription(), fromDateTime, toDateTime);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(res);
			logger.info("Method : scheduleInterview ends");
			return res;

	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/review-get-schedule-list")
	public @ResponseBody List<HireActionModel> getScheduleDetails(Model model, HttpSession session, @RequestParam String id){
		
		logger.info("Method : getScheduleDetails starts");
		
		JsonResponse<List<HireActionModel>> resp = new JsonResponse<List<HireActionModel>>();
		
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "getScheduleDetails?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		List<HireActionModel> cand = mapper.convertValue(resp.getBody(),
				new TypeReference<List<HireActionModel>>() {
				});
		for(HireActionModel m : cand) {
			String date = "";
			
			String dateFormat = (String) (session).getAttribute("DATEFORMAT");
			if(m.getFromDate() != null && m.getFromDate() != "") {
				date = DateFormatter.dateFormat(m.getFromDate(),dateFormat);
				m.setFromDate(date);
			}
			if(m.getToDate() != null && m.getToDate() != "") {
				date = DateFormatter.dateFormat(m.getToDate(),dateFormat);
				m.setToDate(date);
			}
		}
		
		resp.setBody(cand);
		
		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		logger.info("Method : getScheduleDetails ends");
		
		return resp.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/review-edit-schedule-interview")
	public @ResponseBody JsonResponse<Object> editScheduleInterview(Model model, HttpSession session, @RequestBody HireActionModel action,
				BindingResult result) {
			logger.info("Method : editScheduleInterview starts");
			
			JsonResponse<Object> res = new JsonResponse<Object>();
			
			String userId = "";
			String dateFormater = "";
			try {
				userId = (String) session.getAttribute("USER_ID");
				dateFormater = (String) session.getAttribute("DATEFORMAT");

			} catch (Exception e) {
				e.printStackTrace();
			}
			action.setCreatedBy(userId);
			if(action.getFromDate()!=null && action.getFromDate()!="") {
				action.setFromDate(DateFormatter.inputDateFormat(action.getFromDate(), dateFormater));
			}
			if(action.getToDate()!=null && action.getToDate()!="") {
				action.setToDate(DateFormatter.inputDateFormat(action.getToDate(), dateFormater));
			}
			
			try {
				res = restTemplate.postForObject(env.getRecruitment() + "editScheduleInterview", action,
						JsonResponse.class);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (res.getMessage() == "" || res.getMessage() == null) {
				
				res.setMessage("Success");
				EmailCalendarEvent emailEvent = new EmailCalendarEvent();
				
				String mailFrom = Constant.mailFrom;
				String host = Constant.host;
				String port = Constant.port;
				String password = Constant.password;
				
				List<String> candMail = action.getCandEmail();
				List<String> interviewerMail = action.getInterviewerMail();
				
				candMail.addAll(interviewerMail);
				
				String dateFormat = (String) (session).getAttribute("DATEFORMAT");
				String fromDateTime = DateFormatter.inputDateFormat(action.getFromDate(), dateFormat) + " " + action.getFromTime() + ":00";
				String toDateTime =  DateFormatter.inputDateFormat(action.getToDate(), dateFormat) + " " + action.getToTime() + ":00";
				
				try {
					emailEvent.send(action.getTitle(), mailFrom, password, candMail, host, port, action.getLocationName(),
							action.getSummary(), action.getDescription(), fromDateTime, toDateTime);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			logger.info("Method : editScheduleInterview ends");
			return res;

	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/review-shortlist-candidate")
	public @ResponseBody JsonResponse<Object> shortListCandidate(Model model, HttpSession session, @RequestParam String id, @RequestParam String reqId, @RequestParam String status){
		
		logger.info("Method : shortListCandidate starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "shortListCandidate?id=" + id + "&reqId=" + reqId + "&status=" + status, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : shortListCandidate ends");
		
		return resp;
	}
	
	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @GetMapping("/review-interviewerName-forFeedback") public @ResponseBody
	 * JsonResponse<HireActionModel> getInterviewerName(Model model, HttpSession
	 * session, @RequestParam String candId, @RequestParam String reqId){
	 * 
	 * logger.info("Method : getInterviewerName starts");
	 * 
	 * JsonResponse<HireActionModel> resp = new JsonResponse<HireActionModel>();
	 * 
	 * try { resp = restTemplate.getForObject(env.getRecruitment() +
	 * "getInterviewerName?candId=" + candId + "&reqId=" + reqId,
	 * JsonResponse.class); } catch (RestClientException e) { e.printStackTrace(); }
	 * 
	 * if (resp.getMessage() == "" || resp.getMessage() == null) {
	 * 
	 * resp.setMessage("Success"); }
	 * 
	 * logger.info("Method : getInterviewerName ends");
	 * 
	 * return resp; }
	 */
	

	/*
	 * Add Feedback
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("review-add-feedback")
	public @ResponseBody JsonResponse<Object> addFeedback(HttpSession session,
			@RequestBody List<HireActionModel> feedBackModel) {
		logger.info("Method : addAssignDetails starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {

		}
		for (HireActionModel m : feedBackModel) {
			m.setCreatedBy(userId);
		}
		try {
			resp = restTemplate.postForObject(env.getRecruitment() + "rest-addFeedback", feedBackModel,
					JsonResponse.class);
			ObjectMapper mapper = new ObjectMapper();

			List<HireActionModel> seat = mapper.convertValue(resp.getBody(),
					new TypeReference<List<HireActionModel>>() {
					});

			resp.setBody(seat);
		} catch (Exception e) {

			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : addFeedback ends");

		return resp;
	}
	//View Feedback
	@SuppressWarnings("unchecked")
	@GetMapping("/review-get-feedback-list")
	public @ResponseBody List<HireActionModel> getFeedback(Model model, HttpSession session, @RequestParam String id){
		
		logger.info("Method : getFeedback starts");
		
		JsonResponse<List<HireActionModel>> resp = new JsonResponse<List<HireActionModel>>();
		
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "viewFeedbackDetails?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : getFeedback ends");
		
		return resp.getBody();
	}
	/*
	 * for edit
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "review-edit-feedbackDetails" })
	public @ResponseBody List<HireActionModel> editFeedbackDetails(@RequestParam String reqId,
			@RequestParam String candId, HttpSession session) {
		logger.info("Method : editFeedbackDetails starts");
		JsonResponse<List<HireActionModel>> resp = new JsonResponse<List<HireActionModel>>();
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "rest-editFeedback?reqId=" + reqId + "&candId=" + candId,
					JsonResponse.class);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();

		List<HireActionModel> feedList = mapper.convertValue(resp.getBody(),
				new TypeReference<List<HireActionModel>>() {
				});

		String dateFormat = "";
		try {

			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		int i = 0;
		for (HireActionModel a : feedList) {
			i = i + 1;
			a.setSlNo(i);
			if (a.getCreateDate() != null && a.getCreateDate() != "") {
				a.setCreateDate(DateFormatter.dateFormat(a.getCreateDate(), dateFormat));
			}
		}
		resp.setBody(feedList);
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");

		} else {
			// System.out.println(resp.getMessage());
			resp.setMessage("Success");
		}
		logger.info("Method : editFeedbackDetails ends");
		return feedList;
	}

	
}
