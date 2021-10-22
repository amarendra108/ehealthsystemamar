package nirmalya.aathithya.webmodule.doctor.controller;

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

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.doctor.model.DoctorDashboardCountModel;
import nirmalya.aathithya.webmodule.doctor.model.DoctorDashboardPatientModel;
import nirmalya.aathithya.webmodule.doctor.model.PatientDetailDoctorDashBoardModel;

@Controller
@RequestMapping(value = { "doctor" })
public class DoctorDashBoardController {
	Logger logger = LoggerFactory.getLogger(DoctorDashBoardController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	/*
	 * Get Mapping for view-doctor-dashboard
	 */

	@GetMapping("view-doctor-dashboard")
	public String viewDocDashboard(Model model, HttpSession session) {

		logger.info("Method : viewDocDashboard starts");

		String userId = (String) session.getAttribute("USER_ID");

		// View doctor dashboard details

		try {
			DoctorDashboardCountModel[] dashboardregion = restTemplate.getForObject(
					env.getDoctorUrl() + "getdashboardBooking?id=" + userId, DoctorDashboardCountModel[].class);
			List<DoctorDashboardCountModel> DashboardRegiontList = Arrays.asList(dashboardregion);
			model.addAttribute("dashboardbookingList", DashboardRegiontList);

		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("Method : viewDocDashboard ends");
		return "doctor/doctorDashboard";
	}

	/*
	 * 
	 * get dashboard table date through ajax
	 * 
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("view-doctor-dashboard-tomorrow")
	public @ResponseBody JsonResponse<List<DoctorDashboardPatientModel>> getTomorrowDate(HttpSession session,
			Model model, @RequestParam String date) {
		logger.info("Method : getTomorrowDate starts");

		JsonResponse<List<DoctorDashboardPatientModel>> jsonResponse = new JsonResponse<List<DoctorDashboardPatientModel>>();
		String userid = (String) session.getAttribute("USER_ID");
		List<DropDownModel> actionList = new ArrayList<DropDownModel>();
		try {
			DropDownModel[] actionType = restTemplate.getForObject(env.getDoctorUrl() + "getactionType",
					DropDownModel[].class);

			actionList = Arrays.asList(actionType);
			model.addAttribute("actionList", actionList);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			jsonResponse = restTemplate.getForObject(
					env.getDoctorUrl() + "getTomorrowDate?date=" + date + "&userid=" + userid, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();

		}

		ObjectMapper mapper = new ObjectMapper();

		List<DoctorDashboardPatientModel> doctorPatientModel = mapper.convertValue(jsonResponse.getBody(),
				new TypeReference<List<DoctorDashboardPatientModel>>() {
				});

		for (DoctorDashboardPatientModel m : doctorPatientModel) {
			m.setActionList(actionList);
		}
		
		jsonResponse.setBody(doctorPatientModel);

		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}
		logger.info("Method : getTomorrowDate ends");
		return jsonResponse;
	}
	/*
	 * 
	 * get DashBoardPieChart through ajax
	 * 
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("view-doctor-dashboard-piechart")
	public @ResponseBody JsonResponse<List<DropDownModel>> getDashBoardPieChart(HttpSession session, Model model) {
		logger.info("Method : getDashBoardPieChart starts");

		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		String userid = (String) session.getAttribute("USER_ID");
		try {
			jsonResponse = restTemplate.getForObject(env.getDoctorUrl() + "getdashboardPiechart?id=" + userid,
					JsonResponse.class);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}

		logger.info("Method : getDashBoardPieChart ends");
		return jsonResponse;
	}

	/*
	 * doctor dashboard update patient appointment
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "view-doctor-dashboard-update-patient-appointment" })
	public @ResponseBody JsonResponse<Object> getpatientDetailsById(HttpSession session, Model model,
			@RequestBody DropDownModel dropdownmodel) {
		logger.info("Method : getpatientDetailsById starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.postForObject(env.getDoctorUrl() + "getPatientDetails", dropdownmodel,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getpatientDetailsById ends");

		return res;
	}

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "view-doctor-dashboard-patient-details-by-id" })
	public @ResponseBody JsonResponse<PatientDetailDoctorDashBoardModel> getpatientDetailsById(HttpSession session,
			Model model, @RequestParam("id") String id) {
		logger.info("Method : getpatientDetailsById starts");
		JsonResponse<PatientDetailDoctorDashBoardModel> res = new JsonResponse<PatientDetailDoctorDashBoardModel>();
		try {
			res = restTemplate.getForObject(env.getDoctorUrl() + "getPatientDetails?id=" + id, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getpatientDetailsById ends");
		return res;
	}
	

	
}
