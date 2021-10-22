
package nirmalya.aathithya.webmodule.master.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import nirmalya.aathithya.webmodule.common.pagination.DataTableRequest;
import nirmalya.aathithya.webmodule.common.pagination.DataTableResponse;
import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.master.model.AttendanceModel;

@Controller

@RequestMapping(value = { "master/" })
public class AttendanceController {
	Logger logger = LoggerFactory.getLogger(AttendanceController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping(value = { "employee-attendance" })

	public String employeeAttendance(Model model, HttpSession session) {
		logger.info("Method : LeaveType starts");

		AttendanceModel attendenceDetails = new AttendanceModel();

		AttendanceModel attendenceDetailsSession = (AttendanceModel) session.getAttribute("attendenceDetails");
		Date d = new Date();
		Object newdate = DateFormatter.returnStringDate(d);
		String x = (String) newdate;
		attendenceDetails.setPunchinDate(x);
		String date = attendenceDetails.getPunchinDate();

		String empId = "";
		attendenceDetails.getIsOut();
		try {
			empId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String message = (String) session.getAttribute("message");

		if (message != null && message != "") {
			model.addAttribute("message", message);
		}

		session.setAttribute("message", "");

		if (attendenceDetailsSession != null) {
			model.addAttribute("attendenceDetails", attendenceDetailsSession);
			session.setAttribute("attendenceDetails", null);

		} else {
			model.addAttribute("attendenceDetails", attendenceDetails);
		}

		System.out.println("@@@" + attendenceDetails);
		date = LocalDate.now().toString();
		System.out.println("@@@@@@@" + date);
		try {
			DropDownModel[] dropDownModel = restTemplate.getForObject(
					env.getMasterUrl() + "get-details-punchout?empId=" + empId + "&date=" + date,
					DropDownModel[].class);
			List<DropDownModel> details = Arrays.asList(dropDownModel);

			model.addAttribute("details", details);
			String isOut = null;
			if (!details.isEmpty()) {
				isOut = details.get(0).getName();
			}
			System.out.println("details" + details);
			model.addAttribute("isOut", isOut);
			boolean arr = details.isEmpty();

			if (!arr) {
				return "master/employee-attendance";
			}

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : LeaveType ends");
		return "master/employee-attendance";
	}

	@SuppressWarnings("unchecked")

	@PostMapping("employee-attendance-add")
	public @ResponseBody JsonResponse<Object> addEmployeeAttendance(HttpSession session,

			@RequestBody AttendanceModel attendanceModel) {

		logger.info("Method : addEmployeeAttendance");

		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
			System.out.println(dateFormat);

		} catch (Exception e) {

		}
		if (attendanceModel.getPunchinTime() != null && attendanceModel.getPunchinTime() != "") {
			attendanceModel.setPunchinTime(DateFormatter.inputDateFormat(attendanceModel.getPunchinTime(), dateFormat));
		}

		// System.out.println(attendanceModel);

		JsonResponse<Object> resp = new JsonResponse<Object>();
		System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrsop" + resp);
		try {
			resp = restTemplate.postForObject(env.getMasterUrl() + "addEmployeeAttendances", attendanceModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : addEmployeeAttendance ends");
		System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrreturn" + resp);
		return resp;
	}

	// View Student

	@SuppressWarnings("unchecked")

	@GetMapping("/employee-attendance-view")
	public @ResponseBody List<AttendanceModel> viewEmployeeAttendance(HttpSession session) {

		logger.info("Method : viewEmployeestarts");

		JsonResponse<List<AttendanceModel>> resp = new JsonResponse<List<AttendanceModel>>();
		// System.out.println("studentModellllllllllllllllllllllllll" + resp);
		try {
		resp = restTemplate.getForObject(env.getMasterUrl() + "viewNewEmployeeAttendances", JsonResponse.class);
	}catch(

	RestClientException e)
	{
		e.printStackTrace();
	}

	ObjectMapper mapper = new ObjectMapper();

	// System.out.println("studentmappppppppppppppppppper" + mapper);

	List<AttendanceModel> studentModel=mapper.convertValue(resp.getBody(),new TypeReference<List<AttendanceModel>>(){});
	String dateFormat = "";
	try
	{
		dateFormat = (String) session.getAttribute("DATEFORMAT");
	}catch(
	Exception e)
	{

	}for(
	AttendanceModel a:studentModel)
	{
		// System.out.println("for rach lllllllllooooooopppppppppp" + studentModel);
		if (a.getPunchinDate() != null && a.getPunchinDate() != "") {
			a.setPunchinDate(DateFormatter.dateFormat(a.getPunchinDate(), dateFormat));
		}

		if (a.getPunchinTime() != null && a.getPunchinTime() != "") {
			a.setPunchinTime(DateFormatter.dateFormat(a.getPunchinTime(), dateFormat));
		}
		if (a.getPunchoutTime() != null && a.getPunchoutTime() != "") {
			a.setPunchoutTime(DateFormatter.dateFormat(a.getPunchoutTime(), dateFormat));
		}

	}

	if(resp.getMessage()!=""&&resp.getMessage()!=null)
	{
		resp.setCode(resp.getMessage());
		resp.setMessage("Unsuccess");
	}else
	{
		resp.setMessage("Success");
	}

	logger.info("Method : viewEmployeeAttendance ends");System.out.println("respwwwwwwwwwwwwwwwweb view"+resp);

	// System.out.println("studentModelwebview"+studentModel); 
	return studentModel;
	}

	/**
	 * View Attendance Details through ajax
	 *
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("/employee-attendance-details-throughAjax")
	public @ResponseBody DataTableResponse viewAttendencePunchoutThroughAjax(Model model, HttpServletRequest request,

			@RequestParam String param1) {
		logger.info("Method : viewAttendencePunchoutThroughAjax starts");
		DataTableResponse response = new DataTableResponse();
		DataTableRequest tableRequest = new DataTableRequest();
		try {
			String start = request.getParameter("start");
			String length = request.getParameter("length");
			String draw = request.getParameter("draw");

			tableRequest.setStart(Integer.parseInt(start));
			tableRequest.setLength(Integer.parseInt(length));
			tableRequest.setParam1(param1);

			JsonResponse<List<AttendanceModel>> jsonResponse = new JsonResponse<List<AttendanceModel>>();

			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "viewAttendencePunchoutThroughAjax",
					tableRequest, JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			List<AttendanceModel> attendenceDetails = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<List<AttendanceModel>>() {
					});

			/*
			 * for (AttendanceModel m : attendenceDetails) {
			 * 
			 * if (m.getPunchinLocation() == 1) { m.setStatus("office"); } else {
			 * m.setStatus("other"); }
			 * 
			 * if (m.getPunchOutLocation() == 1) { m.setAction("office"); } else {
			 * m.setAction("other"); }
			 * 
			 * }
			 */
			response.setRecordsTotal(jsonResponse.getTotal());
			response.setRecordsFiltered(jsonResponse.getTotal());
			response.setDraw(Integer.parseInt(draw));
			response.setData(attendenceDetails);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		System.out.println("rrrrrspppppppppppppppppppppppppppp" + response);
		logger.info("Method : viewAttendenceDetailsThroughAjax ends");
		return response;

	}

	/*
	 * Add Punch Out Form Post
	 */

	@SuppressWarnings("unchecked")

	@PostMapping("employee-attendance-punchout-add")

	public @ResponseBody JsonResponse<Object> addEmployeeAttendancePunchOut(HttpSession session,

			@RequestBody AttendanceModel attendanceModel) {

		logger.info("Method : addEmployeeAttendancePunchOut");

		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
			System.out.println(dateFormat);

		} catch (Exception e) {

		}

		if (attendanceModel.getPunchoutTime() != null && attendanceModel.getPunchoutTime() != "") {
			attendanceModel
					.setPunchoutTime(DateFormatter.inputDateFormat(attendanceModel.getPunchoutTime(), dateFormat));
		}

		// System.out.println(attendanceModel);

		JsonResponse<Object> resp = new JsonResponse<Object>();
		System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrsop" + resp);
		try {
			resp = restTemplate.postForObject(env.getMasterUrl() + "addEmployeeAttendancePunchOut", attendanceModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : addEmployeeAttendancePunchOut ends");
		System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrreturn" + resp);
		return resp;
	}

}
