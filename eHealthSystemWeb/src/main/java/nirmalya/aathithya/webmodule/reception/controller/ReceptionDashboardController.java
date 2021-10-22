package nirmalya.aathithya.webmodule.reception.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.patient.model.PatientDetailsNewModel;
import nirmalya.aathithya.webmodule.reception.model.ReceptionDashboardModel;

@Controller
@RequestMapping(value = { "reception/" })
public class ReceptionDashboardController {
	Logger logger = LoggerFactory.getLogger(ReceptionDashboardController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	/*
	 * Get Mapping for Reception Dashboard
	 */

	@GetMapping("view-reception-dashboard")
	public String viewReceiptionDashboard(Model model, HttpSession session) {

		logger.info("Method : viewReceiptionDashboard starts");

	//Reception  Count 
		
		String userId1 = (String) session.getAttribute("USER_ID");
		try {
			ReceptionDashboardModel[] region = restTemplate.getForObject(env.getReceptionUrl() + "rest-receptionCount?id="+userId1,
					ReceptionDashboardModel[].class);
			List<ReceptionDashboardModel> receptionCount = Arrays.asList(region);
			model.addAttribute("receptionCount", receptionCount);
			System.out.println("userId1============================"+userId1);
			System.out.println("receptionCount"+receptionCount);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Method : viewReceiptionDashboard ends");
		return "reception/view-reception-dashboard";
	}
	
	
	/*
	 * view  disease wise 	
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "view-reception-dashboard-patient-disease" })
	public @ResponseBody JsonResponse<Object> viewPatientDisease(Model model, HttpSession session) {
		logger.info("Method : viewPatientDisease starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId1 = (String) session.getAttribute("USER_ID");
		try {
			res = restTemplate.getForObject(env.getReceptionUrl() + "rest-viewPatientDiseasel?id="+userId1, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		System.out.println("viewPatientDisease =========="+res);
		logger.info("Method : viewPatientDisease ends");
		return res;
	}
	
	/*
	 * View  Appointment Status Wise 	
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "view-reception-dashboard-appointment-status" })
	public @ResponseBody JsonResponse<Object> viewAppointmentStatus(Model model, HttpSession session) {
		logger.info("Method : viewAppointmentStatus starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId2 = (String) session.getAttribute("USER_ID");
		try {
			res = restTemplate.getForObject(env.getReceptionUrl() + "rest-viewAppointmentStatus?id="+userId2, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		System.out.println("viewAppointmentStatus =========="+res);
		logger.info("Method : viewAppointmentStatus ends");
		return res;
	}
}
