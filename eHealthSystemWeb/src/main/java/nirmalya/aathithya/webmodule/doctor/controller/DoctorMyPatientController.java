package nirmalya.aathithya.webmodule.doctor.controller;

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
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.doctor.model.ViewMyPatientModel;
@Controller
@RequestMapping(value = { "doctor/" })
public class DoctorMyPatientController {
	Logger logger = LoggerFactory.getLogger(DoctorController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;
	/*
	 * Get Mapping for view-my-patient'
	 */

	@GetMapping("view-my-patient")
	public String viewMyPatient(Model model, HttpSession session) {

		logger.info("Method : viewMyPatient starts");

		
		logger.info("Method : viewMyPatient ends");
		return "doctor/myPatient";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("view-my-patient-list")
	public @ResponseBody List<ViewMyPatientModel> doctorViewMyPatient(HttpSession session, Model model){
		logger.info("Method : doctorViewMyPatient starts");
		String userid = (String)session.getAttribute("USER_ID");
		JsonResponse<List<ViewMyPatientModel>> resp =new JsonResponse<List<ViewMyPatientModel>> ();
		try {

			resp = restTemplate.getForObject(env.getDoctorUrl() + "rest-getMyPatient?userid="+userid, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<ViewMyPatientModel> mypatientList = mapper.convertValue(resp.getBody(),
				new TypeReference<List<ViewMyPatientModel>>() {
				});
		resp.setBody(mypatientList);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		logger.info("Method : doctorViewMyPatient starts");
		return resp.getBody();
	}
}
