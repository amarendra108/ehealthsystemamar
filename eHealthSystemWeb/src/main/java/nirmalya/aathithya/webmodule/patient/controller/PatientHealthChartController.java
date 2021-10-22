package nirmalya.aathithya.webmodule.patient.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.patient.model.LabTestReportModel;

@Controller
@RequestMapping(value = { "user/" })
public class PatientHealthChartController {
	Logger logger = LoggerFactory.getLogger(PatientConcultantController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping("view-patient-health-chart")
	public String viewPatientHealthChart(Model model, HttpSession session) {

		logger.info("Method : viewPatientHealthChart starts");

		logger.info("Method : viewPatientHealthChart ends");
		return "patient/patientHealthChart";
	}
	/*
	 * Location wise customer ticket
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("/view-patient-health-chart-graph")
	@ResponseBody
	JsonResponse<List<LabTestReportModel>> getPatientHealthChart(HttpSession session) {

		logger.info("Method : getPatientHealthChart starts");

		JsonResponse<List<LabTestReportModel>> jsonResponse = new JsonResponse<List<LabTestReportModel>>();
		String userId = "";
		userId = (String) session.getAttribute("USER_ID");
		try {
			jsonResponse = restTemplate.getForObject(env.getUserUrl() + "getpatienttestdetails-chart?id=" + userId,
					JsonResponse.class);
		} catch (
		RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();
		List<LabTestReportModel> gatPatientdetails = mapper.convertValue(jsonResponse.getBody(),
				new TypeReference<List<LabTestReportModel>>() {
				});
		jsonResponse.setBody(gatPatientdetails);
		logger.info("Method : getPatientHealthChart ends");
		return jsonResponse;
	}
	
	/*
	 * 
	 * health-chart-graph-details
	 */
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-patient-health-chart-details")
	@ResponseBody JsonResponse<List<LabTestReportModel>> getPatientHealthChartDetails(HttpSession session) {
		
		logger.info("Method : getPatientHealthChartDetails starts");
		
		JsonResponse<List<LabTestReportModel>> jsonResponse = new JsonResponse<List<LabTestReportModel>>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			jsonResponse = restTemplate.getForObject(env.getUserUrl() + "rest-getPatientHealthChartDetails?id=" + userId,
					JsonResponse.class);
		} catch (
		RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();
		List<LabTestReportModel> gatPatientdetails = mapper.convertValue(jsonResponse.getBody(),
				new TypeReference<List<LabTestReportModel>>() {
				});
		jsonResponse.setBody(gatPatientdetails);
		logger.info("Method : getPatientHealthChartDetails ends");
		
		return jsonResponse;
	}
	/*
	 * HAEMATOLOGICAL EXAMINATION
	 */
	@SuppressWarnings("unchecked")

	@GetMapping("view-patient-health-chart-forHematology")
	public @ResponseBody JsonResponse<Object> forHematology(HttpSession session) {
		logger.info("Method : viewdmDashboardAreaWiseTotalTest starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId = "";
		userId = (String) session.getAttribute("USER_ID");
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-healthChartForHematology?userId=" + userId,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null && res.getMessage() != "") {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("Success");
		}
		//System.out.println("forHematology==========" + res);
		logger.info("Method : forHematology ends");
		return res;
	}

	/*
	 * Kidney Function Test
	 */
	@SuppressWarnings("unchecked")

	@GetMapping("view-patient-health-chart-forKidneyTest")
	public @ResponseBody JsonResponse<Object> forKidneyTest(HttpSession session) {
		logger.info("Method : forKidneyTest starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId = "";
		userId = (String) session.getAttribute("USER_ID");
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-healthChartKidneyTest?userId=" + userId,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null && res.getMessage() != "") {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("Success");
		}
		//System.out.println("forKidneyTest==========" + res);
		logger.info("Method : forKidneyTest ends");
		return res;
	}

	/*
	 * Lipid Profile
	 */
	@SuppressWarnings("unchecked")

	@GetMapping("view-patient-health-chart-forLipidProfile")
	public @ResponseBody JsonResponse<Object> forLipidProfile(HttpSession session) {
		logger.info("Method : forLipidProfile starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId = "";
		userId = (String) session.getAttribute("USER_ID");
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-healthChartForLipidProfile?userId=" + userId,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null && res.getMessage() != "") {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("Success");
		}
		//System.out.println("forLipidProfile==========" + res);
		logger.info("Method : forLipidProfile ends");
		return res;
	}

	/*
	 * Blood Sugar
	 */
	@SuppressWarnings("unchecked")

	@GetMapping("view-patient-health-chart-forBloodSugar")
	public @ResponseBody JsonResponse<Object> forBloodSugar(HttpSession session) {
		logger.info("Method : forBloodSugar starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId = "";
		userId = (String) session.getAttribute("USER_ID");
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-healthChartForBloodSugar?userId=" + userId,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null && res.getMessage() != "") {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");

		} else {
			res.setMessage("Success");
		}
		//System.out.println("forBloodSugar==========" + res);
		logger.info("Method : forBloodSugar ends");
		return res;
	}

	/*
	 * Electrolyte
	 */
	@SuppressWarnings("unchecked")

	@GetMapping("view-patient-health-chart-forElectrolyte")
	public @ResponseBody JsonResponse<Object> forElectrolyte(HttpSession session) {
		logger.info("Method : forElectrolyte starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId = "";
		userId = (String) session.getAttribute("USER_ID");
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-healthChartForElectrolyte?userId=" + userId,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null && res.getMessage() != "") {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");

		} else {
			res.setMessage("Success");
		}
		//System.out.println("forElectrolyte==========" + res);
		logger.info("Method : forElectrolyte ends");
		return res;
	}

	/*
	 * Liver Function Test
	 */
	@SuppressWarnings("unchecked")

	@GetMapping("view-patient-health-chart-forLiverTest")
	public @ResponseBody JsonResponse<Object> forLiverTest(HttpSession session) {
		logger.info("Method : forLiverTest starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId = "";
		userId = (String) session.getAttribute("USER_ID");
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-healthChartForLiverTest?userId=" + userId,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null && res.getMessage() != "") {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");

		} else {
			res.setMessage("Success");
		}
		//System.out.println("forLiverTest==========" + res);
		logger.info("Method : forLiverTest ends");
		return res;
	}
}
