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
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.patient.model.TreatmentTrackerModel;

@Controller
@RequestMapping(value = { "user/" })

public class TreatmentTrackerWebController {

	Logger logger = LoggerFactory.getLogger(TreatmentTrackerWebController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	/*
	 * Get Mapping for view-patient-treatment-tracker
	 */

	@GetMapping("view-patient-treatment-tracker")
	public String viewPatientTreatmentTracker(Model model, HttpSession session) {
		logger.info("Method : viewPatientTreatmentTracker starts");
		logger.info("Method : viewPatientTreatmentTracker ends");
		return "patient/patientTreatmentTracker";
	}

	@SuppressWarnings("unchecked")

	@GetMapping("view-patient-treatment-tracker-user")
	public @ResponseBody List<TreatmentTrackerModel> getPatientTreatmentTracker(HttpSession session) {

		logger.info("Method :getPatientTreatmentTracker starts");

		JsonResponse<List<TreatmentTrackerModel>> resp = new JsonResponse<List<TreatmentTrackerModel>>();

		try {
			String userId = session.getAttribute("USER_ID").toString();
			System.out.println("userId" + userId);
			resp = restTemplate.getForObject(env.getUserUrl() + "rest-getPatientTreatmentTracker?id=" + userId,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		List<TreatmentTrackerModel> treatmentTrackerModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<TreatmentTrackerModel>>() {
				});

		resp.setBody(treatmentTrackerModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :getPatientTreatmentTracker ends");

		return resp.getBody();
	}

}
