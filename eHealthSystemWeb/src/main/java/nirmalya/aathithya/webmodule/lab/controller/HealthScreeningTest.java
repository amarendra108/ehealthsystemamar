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

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.lab.model.HealthScreeningModel;

@Controller
@RequestMapping(value = { "lab/" })
public class HealthScreeningTest {

	Logger logger = LoggerFactory.getLogger(HealthScreeningTest.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;

	@GetMapping("healthScreening")
	public String healthScreening(Model model, HttpSession session) {

		logger.info("Method : healthScreening starts");

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("labId", userId);

		logger.info("Method : healthScreening ends");
		return "lab/healthScreening";
	}

	/*
	 * View
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("healthScreening-details-view-health-screen")
	public @ResponseBody List<HealthScreeningModel> viewHealthScreen(HttpSession session, Model model) {

		logger.info("Method : viewHealthScreen starts");

		JsonResponse<List<HealthScreeningModel>> resp = new JsonResponse<List<HealthScreeningModel>>();

		try {
			resp = restTemplate.getForObject(env.getLabUrl() + "viewHealthScreen", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewHealthScreen ends");
		return resp.getBody();
	}

	/*
	 * UHID No autoSearch
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "healthScreening-autosearch" })
	public @ResponseBody JsonResponse<HealthScreeningModel> getUhidNoAutoSearch(Model model,
			@RequestBody String searchValue, BindingResult result) {
		logger.info("Method : getUhidNoAutoSearch starts");
		JsonResponse<HealthScreeningModel> res = new JsonResponse<HealthScreeningModel>();

		try {
			res = restTemplate.getForObject(env.getLabUrl() + "rest-getUhidNoAutoSearch?id=" + searchValue,
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
		logger.info("Method : getUhidNoAutoSearch ends");
		return res;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/healthScreening-add-lab")
	public @ResponseBody JsonResponse<Object> addHealthScreening(Model model, HttpSession session,

			@RequestBody HealthScreeningModel lifeStyleHistoryModel) {

		logger.info("Method : addHealthScreening starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			resp = restTemplate.postForObject(env.getLabUrl() + "addHealthScreeningLab", lifeStyleHistoryModel,
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

		logger.info("Method : addHealthScreening ends");
		return resp;
	}

}
