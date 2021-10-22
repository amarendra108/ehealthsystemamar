package nirmalya.aathithya.webmodule.employee.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.employee.model.ScheduleManagementModel;

@Controller
@RequestMapping(value = { "employee/" })
public class ScheduleManagementController {

	Logger logger = LoggerFactory.getLogger(ScheduleManagementController.class);

	@Autowired
	RestTemplate restClient;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/schedule-management")
	public String employee(Model model, HttpSession session) {
		logger.info("Method : schedule starts");

		try {
			DropDownModel[] dropDownModel = restTemplate.getForObject(env.getEmployeeUrl() + "get-department",
					DropDownModel[].class);
			List<DropDownModel> departmentList = Arrays.asList(dropDownModel);
			model.addAttribute("departmentList", departmentList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] dropDownModel = restTemplate.getForObject(env.getEmployeeUrl() + "get-schedule",
					DropDownModel[].class);
			List<DropDownModel> scheduleList = Arrays.asList(dropDownModel);
			model.addAttribute("scheduleList", scheduleList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] dropDownModel = restTemplate.getForObject(env.getEmployeeUrl() + "get-section",
					DropDownModel[].class);
			List<DropDownModel> sectionList = Arrays.asList(dropDownModel);
			model.addAttribute("sectionList", sectionList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] dropDownModel = restTemplate.getForObject(env.getEmployeeUrl() + "get-shift",
					DropDownModel[].class);
			List<DropDownModel> shiftList = Arrays.asList(dropDownModel);
			model.addAttribute("shiftList", shiftList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] dropDownModel = restTemplate.getForObject(env.getEmployeeUrl() + "get-employee",
					DropDownModel[].class);
			List<DropDownModel> employeeList = Arrays.asList(dropDownModel);
			model.addAttribute("employeeList", employeeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : schedule ends");
		return "employee/schedule-management";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("schedule-management-view-employee")
	public @ResponseBody List<ScheduleManagementModel> viewSchedule(Model model, HttpSession session) {
		logger.info("Method : viewSchedule starts");

		JsonResponse<List<ScheduleManagementModel>> jsonResponse = new JsonResponse<List<ScheduleManagementModel>>();

		try {

			jsonResponse = restClient.getForObject(env.getEmployeeUrl() + "schedule-management-employee",
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			List<ScheduleManagementModel> addreq = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<List<ScheduleManagementModel>>() {
					});

			String dateFormat = (String) (session).getAttribute("DATEFORMAT");

			for (ScheduleManagementModel m : addreq) {
				
				

				if (m.getFromDate() != null && m.getFromDate() != "") {
					m.setFromDate(DateFormatter.dateFormat(m.getFromDate(), dateFormat));
				}

				if (m.getToDate() != null && m.getToDate() != "") {
					m.setToDate(DateFormatter.dateFormat(m.getToDate(), dateFormat));
				}
				
				if (m.getCreatedOn() != null && m.getCreatedOn() != "") {
					m.setCreatedOn(DateFormatter.dateFormat(m.getCreatedOn(), dateFormat));
				}
			}

			jsonResponse.setBody(addreq);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method ; viewSchedule ends");
		
		return jsonResponse.getBody();
	}

	/*
	 * post Mapping for adding schedule
	 * 
	 */
	@SuppressWarnings({ "unchecked" })
	@PostMapping(value = "schedule-management-add-schedule-details")
	public @ResponseBody JsonResponse<Object> saveSchedule(@RequestBody List<ScheduleManagementModel> scheduleModel,
			HttpSession session) {
		logger.info("Method : saveSchedule function starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		String dateFormat = "";
		
		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		for (ScheduleManagementModel m : scheduleModel) {
			m.setCreatedBy(userId);
			if (m.getFromDate() != null && m.getFromDate() != "") {
				m.setFromDate(DateFormatter.dateFormat(m.getFromDate(), dateFormat));
			}
			if (m.getToDate() != null && m.getToDate() != "") {
				m.setToDate(DateFormatter.dateFormat(m.getToDate(), dateFormat));
			}
		}

		System.out.println("web" + scheduleModel);

		try {

			resp = restTemplate.postForObject(env.getEmployeeUrl() + "rest-add-schedule", scheduleModel,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			resp.setMessage("Success");
		}
		logger.info("Method : saveSchedule function Ends");
System.out.println("WEBBB"+resp);
		return resp;
	}

	/*
	 * for editing using employee id
	 * 
	 * 
	 */
	@GetMapping(value = { "schedule-management-edit-employee-trough-ajax" })
	public @ResponseBody List<ScheduleManagementModel> scheduleManagementEdit(@RequestParam String id,
			HttpSession session) {
		logger.info("Method : scheduleManagementEdit starts");

		List<ScheduleManagementModel> scheduleList = new ArrayList<ScheduleManagementModel>();

		if (id != null && id != "") {
			try {
				ScheduleManagementModel[] scheduleModel = restTemplate.getForObject(
						env.getEmployeeUrl() + "get-schedule-edit?id=" + id, ScheduleManagementModel[].class);

				scheduleList = Arrays.asList(scheduleModel);

				scheduleList.forEach(s -> s.setId(s.getShiftId()));

				int count = 0;
				
				for (ScheduleManagementModel m : scheduleModel) {
					
					count ++;
					m.setOrderId(count);
					
					String dateFormat = (String) session.getAttribute("DATEFORMAT");

					if (m.getFromDate() != null && m.getFromDate() != "") {
						m.setFromDate(DateFormatter.dateFormat(m.getFromDate(), dateFormat));
					}
					if (m.getToDate() != null && m.getToDate() != "") {
						m.setToDate(DateFormatter.dateFormat(m.getToDate(), dateFormat));
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.info("Method : scheduleManagementEdit ends");
		System.out.println("Webxxxx  EDIT"+scheduleList);
		return scheduleList;
	}

	/*
	 * post Mapping for delete schedule
	 * 
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "schedule-management-delete-th-ajax")
	public @ResponseBody JsonResponse<Object> deleteSchedule(
			@RequestBody ScheduleManagementModel scheduleModel, HttpSession session) {
		logger.info("Method : deleteSchedule function starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			scheduleModel.setCreatedBy(userId);
		} catch (Exception e) {

		}
		try {

			res = restTemplate.postForObject(env.getEmployeeUrl() + "rest-delete-schedule",
					scheduleModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = res.getMessage();

		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		logger.info("Method : deleteSchedule function Ends");
		return res;
	}
	

}
