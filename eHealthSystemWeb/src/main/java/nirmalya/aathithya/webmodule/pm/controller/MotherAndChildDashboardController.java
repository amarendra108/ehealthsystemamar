package nirmalya.aathithya.webmodule.pm.controller;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.client.RestTemplate;

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;

@Controller
@RequestMapping(value = "pm/")
public class MotherAndChildDashboardController {
	Logger logger = LoggerFactory.getLogger(CallCenterDashboardController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@GetMapping("/mother-child-dashboard")
	public String healthCheckUpHighRisk(Model model, HttpSession session) {

		logger.info("Method : healthCheckUpHighRisk starts");
		
	
		logger.info("Method : healthCheckUpHighRisk ends");
		return "pm/motherAndChildDashboard";

	}
	/*
	 * view mc dashboard count
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("mother-child-dashboard-dashboardCount")
	public @ResponseBody JsonResponse<Object> dashboardCount(HttpSession session,
			@RequestParam String countrycode) {

		logger.info("Method : dashboardCount starts");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		try {
			map = restTemplate.getForObject("https://www.matrujyoti.in/api/get-dashboardcount?countrycode="+countrycode,
					Map.class);
			
			jsonResponse.setBody(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}
		logger.info("Method : dashboardCount ends");

		return jsonResponse;
	}
	/*
	 * Admission Vs Delivery
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("mother-child-dashboard-addmissionDelivery")
	public @ResponseBody JsonResponse<Object> addmissionDelivery(HttpSession session,
			@RequestParam String countrycode) {

		logger.info("Method : addmissionDelivery starts");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		try {
			map = restTemplate.getForObject("https://www.matrujyoti.in/api/get-admissionVsDelivry?countrycode="+countrycode,
					Map.class);
			
			jsonResponse.setBody(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}
		logger.info("Method : addmissionDelivery ends");

		return jsonResponse;
	}
	/*
	 * Day Wise Registration
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("mother-child-dashboard-dayWiseRegistration")
	public @ResponseBody JsonResponse<Object> dayWiseRegistration(HttpSession session,
			@RequestParam String countrycode) {

		logger.info("Method : dayWiseRegistration starts");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		try {
			map = restTemplate.getForObject("https://www.matrujyoti.in/api/get-dayWiseRegistration?countrycode="+countrycode,
					Map.class);
			
			jsonResponse.setBody(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}
		logger.info("Method : dayWiseRegistration ends");

		return jsonResponse;
	}
	/*
	 *Under AWW Anganwadi
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("mother-child-dashboard-underAWWAnganwadi")
	public @ResponseBody JsonResponse<Object> underAWWAnganwadi(HttpSession session,
			@RequestParam String countrycode) {

		logger.info("Method : underAWWAnganwadi starts");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		try {
			map = restTemplate.getForObject("https://www.matrujyoti.in/api/get-underAwwAnganwadi?countrycode="+countrycode,
					Map.class);
			
			jsonResponse.setBody(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}
		logger.info("Method : underAWWAnganwadi ends");

		return jsonResponse;
	}
	/*
	 *Total Child Delivered
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("mother-child-dashboard-totalChildDelivered")
	public @ResponseBody JsonResponse<Object> totalChildDelivered(HttpSession session,
			@RequestParam String countrycode) {

		logger.info("Method : totalChildDelivered starts");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		try {
			map = restTemplate.getForObject("https://www.matrujyoti.in/api/get-childDelivered?countrycode="+countrycode,
					Map.class);
			
			jsonResponse.setBody(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}
		logger.info("Method : totalChildDelivered ends");

		return jsonResponse;
	}
	/*
	 *Registered Beneficiaries
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("mother-child-dashboard-registeredBeneficiaries")
	public @ResponseBody JsonResponse<Object> registeredBeneficiaries(HttpSession session,
			@RequestParam String countrycode) {

		logger.info("Method : registeredBeneficiaries starts");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		try {
			map = restTemplate.getForObject("https://www.matrujyoti.in/api/get-registeredBeneficiaries?countrycode="+countrycode,
					Map.class);
			
			jsonResponse.setBody(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}
		logger.info("Method : registeredBeneficiaries ends");

		return jsonResponse;
	}
	/*
	 *Age Wise
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("mother-child-dashboard-ageWise")
	public @ResponseBody JsonResponse<Object> ageWise(HttpSession session,
			@RequestParam String countrycode) {

		logger.info("Method : ageWise starts");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		try {
			map = restTemplate.getForObject("https://www.matrujyoti.in/api/get-ageWise?countrycode="+countrycode,
					Map.class);
			
			jsonResponse.setBody(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}
		logger.info("Method : ageWise ends");

		return jsonResponse;
	}
	/*
	 *Total Registered Mother
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("mother-child-dashboard-totalRegisteredMother")
	public @ResponseBody JsonResponse<Object> totalRegisteredMother(HttpSession session,
			@RequestParam String countrycode) {

		logger.info("Method : totalRegisteredMother starts");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		try {
			map = restTemplate.getForObject("https://www.matrujyoti.in/api/get-totalregisteredMother?countrycode="+countrycode,
					Map.class);
			
			jsonResponse.setBody(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}
		logger.info("Method : totalRegisteredMother ends");

		return jsonResponse;
	}
	/*
	 *Total Admissions
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("mother-child-dashboard-totalAdmissions")
	public @ResponseBody JsonResponse<Object> totalAdmissions(HttpSession session,
			@RequestParam String countrycode) {

		logger.info("Method : totalAdmissions starts");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		try {
			map = restTemplate.getForObject("https://www.matrujyoti.in/api/get-totaladmission?countrycode="+countrycode,
					Map.class);
			
			jsonResponse.setBody(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}
		logger.info("Method : totalAdmissions ends");

		return jsonResponse;
	}
	/*
	 *Total Health Check Up
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("mother-child-dashboard-totalHealthCheckUp")
	public @ResponseBody JsonResponse<Object> totalHealthCheckUp(HttpSession session,
			@RequestParam String countrycode) {

		logger.info("Method : totalHealthCheckUp starts");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		try {
			map = restTemplate.getForObject("https://www.matrujyoti.in/api/get-totalhealthcheckup?countrycode="+countrycode,
					Map.class);
			
			jsonResponse.setBody(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}
		logger.info("Method : totalHealthCheckUp ends");

		return jsonResponse;
	}
	/*
	 *Type of Delivery
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("mother-child-dashboard-typeOfDelivery")
	public @ResponseBody JsonResponse<Object> typeOfDelivery(HttpSession session,
			@RequestParam String countrycode) {

		logger.info("Method : typeOfDelivery starts");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		try {
			map = restTemplate.getForObject("https://www.matrujyoti.in/api/get-typeOfDelivery?countrycode="+countrycode,
					Map.class);
			
			jsonResponse.setBody(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}
		logger.info("Method : typeOfDelivery ends");
		return jsonResponse;
	}
	/*
	 *Total Mortality
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("mother-child-dashboard-totalMortality")
	public @ResponseBody JsonResponse<Object> totalMortality(HttpSession session,
			@RequestParam String countrycode) {

		logger.info("Method : totalMortality starts");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		try {
			map = restTemplate.getForObject("https://www.matrujyoti.in/api/get-totalmortality?countrycode="+countrycode,
					Map.class);
			
			jsonResponse.setBody(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}
		logger.info("Method : totalMortality ends");

		return jsonResponse;
	}
	/*
	 *Total High Risk Mothers
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("mother-child-dashboard-totalHighRiskMothers")
	public @ResponseBody JsonResponse<Object> totalHighRiskMothers(HttpSession session,
			@RequestParam String countrycode) {

		logger.info("Method : totalHighRiskMothers starts");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		try {
			map = restTemplate.getForObject("https://www.matrujyoti.in/api/get-totalhighriskmothers?countrycode="+countrycode,
					Map.class);
			
			jsonResponse.setBody(map);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}
		logger.info("Method : totalHighRiskMothers ends");

		return jsonResponse;
	}
}
