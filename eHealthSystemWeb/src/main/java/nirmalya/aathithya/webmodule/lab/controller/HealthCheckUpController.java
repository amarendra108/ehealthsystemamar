package nirmalya.aathithya.webmodule.lab.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.lab.model.HealthCheckUpModel;

@Controller
@RequestMapping(value = { "lab/" })
public class HealthCheckUpController {

	Logger logger = LoggerFactory.getLogger(HealthCheckUpController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;

	/*
	 * Get Mapping for view-doctor-dashboard
	 */

	@GetMapping("view-health-checkup")
	public String healthCheckUp(Model model, HttpSession session) {

		logger.info("Method : viewMyDashboard starts");

		logger.info("Method : viewMyDashboard ends");
		return "lab/healthCheckUp";
	}

	/*
	 * UHID No autoSearch
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "view-health-checkup-autosearch" })
	public @ResponseBody JsonResponse<HealthCheckUpModel> getUhidNoAutoSearchList(Model model,
			@RequestBody String searchValue, BindingResult result) {
		logger.info("Method : getUhidNoAutoSearchList starts");
		JsonResponse<HealthCheckUpModel> res = new JsonResponse<HealthCheckUpModel>();

		try {
			res = restTemplate.getForObject(env.getLabUrl() + "rest-getUhidNoAutoSearchList?id=" + searchValue,
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
		logger.info("Method : getUhidNoAutoSearchList ends");
		return res;
	}

	/*
	 * ADD FOR INSURANCE
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/view-health-checkup-add")
	public @ResponseBody JsonResponse<Object> addHealthCheckUp(Model model, HttpSession session,

			@RequestBody HealthCheckUpModel lifeStyleHistoryModel) {

		logger.info("Method : addHealthCheckUp starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			resp = restTemplate.postForObject(env.getLabUrl() + "addHealthCheckUp", lifeStyleHistoryModel,
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

		logger.info("Method : addHealthCheckUp ends");
		return resp;
	}

	/*
	 * VIEW INSURANCE
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("view-health-checkup-through-ajax")
	public @ResponseBody List<HealthCheckUpModel> getAllLabHealthCheckUpView(HttpSession session) {

		logger.info("Method :getAllLabHealthCheckUpView starts");

		JsonResponse<List<HealthCheckUpModel>> resp = new JsonResponse<List<HealthCheckUpModel>>();

		try {

			resp = restTemplate.getForObject(env.getLabUrl() + "getAllLabHealthCheckUpView", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<HealthCheckUpModel> patientHistoryModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<HealthCheckUpModel>>() {
				});
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}

		resp.setBody(patientHistoryModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :getAllLabHealthCheckUpView ends");
		return resp.getBody();
	}

}
