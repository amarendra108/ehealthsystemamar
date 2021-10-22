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

import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.patient.model.PatientFamilyDetailsModel;

@Controller
@RequestMapping(value = { "user/" })
public class PatientFamilyDetailsController {
	Logger logger = LoggerFactory.getLogger(PatientFamilyDetailsController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	/*
	 * Get Mapping for view-patient-family-details
	 */

	@GetMapping("view-patient-family-details")
	public String viewPatientFaimilyDetails(Model model, HttpSession session) {
		logger.info("Method : viewPatientFaimilyDetails starts");
		logger.info("Method : viewPatientFaimilyDetails ends");
		return "patient/patientFamilyDtls";
	}
	

	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-family-details-familydata")
	public @ResponseBody List<PatientFamilyDetailsModel> viewPatientFamilyDataDetails(Model model,HttpSession session) {
			
		logger.info("Method :viewPatientFamilyDataDetails starts");

		JsonResponse<List<PatientFamilyDetailsModel>> resp = new JsonResponse<List<PatientFamilyDetailsModel>>();
		String userId="";	
		try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getUserUrl() + "viewPatientFamilyDataDetails?id="+userId, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<PatientFamilyDetailsModel> familydetailsModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<PatientFamilyDetailsModel>>() {
				});
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		for (PatientFamilyDetailsModel a : familydetailsModel) {
			if (a.getBirthDate() != null && a.getBirthDate() != "") {
				a.setBirthDate(DateFormatter.dateFormat(a.getBirthDate(), dateFormat));
			}
			if (a.getSince() != null && a.getSince() != "") {
				a.setSince(DateFormatter.dateFormat(a.getSince(), dateFormat));
			}

			System.out.println(familydetailsModel); 
		}

		resp.setBody(familydetailsModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :viewPatientFamilyDataDetails ends");
		System.out.println("RESPONSE" + resp.getBody());
		return resp.getBody();
	}
}
