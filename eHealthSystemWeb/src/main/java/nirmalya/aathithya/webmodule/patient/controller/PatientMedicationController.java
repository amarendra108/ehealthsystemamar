package nirmalya.aathithya.webmodule.patient.controller;

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

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.patient.model.MedicationCemistModel;
import nirmalya.aathithya.webmodule.patient.model.PatientMedicationModel;
import nirmalya.aathithya.webmodule.patient.model.PatientMedicationRequestedMdcnModel;
import nirmalya.aathithya.webmodule.patient.model.RequestMedcineModel;
import nirmalya.aathithya.webmodule.patient.model.RequestTestModel;
@Controller
@RequestMapping(value = { "user/" })
public class PatientMedicationController {
	Logger logger = LoggerFactory.getLogger(PatientMedicationController.class);
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	/*
	 * Get Mapping for patient-medication
	 */

	@GetMapping("view-patient-medication")
	public String viewPatientMedication(Model model, HttpSession session) {
		logger.info("Method : viewPatientMedication starts");
		
		logger.info("Method : viewPatientMedication ends");
		return "patient/view-patient-medication";
	}
	/*
	 * View current medicine	
	 */
		@SuppressWarnings("unchecked")
		@GetMapping("view-patient-medication-current")
		public @ResponseBody List<PatientMedicationModel> viewPatientCurrentMedicines(Model model, HttpSession session) {

			logger.info("Method : viewPatientCurrentMedicines starts");
			JsonResponse<List<PatientMedicationModel>> resp = new JsonResponse<List<PatientMedicationModel>>();
			String userId="";
			try {
				userId = (String) session.getAttribute("USER_ID");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (resp.getMessage() != null) {

				resp.setCode(resp.getMessage());
				resp.setMessage("Unsuccess");
			} else {
				resp.setMessage("Success");
			}
			
			try { 
				resp = restTemplate.getForObject(env.getUserUrl() + "rest-viewCurrentMedication?uId="+userId, JsonResponse.class);
			} catch (RestClientException e) {
				e.printStackTrace();
			}
		 
			logger.info("Method : viewPatientCurrentMedicines ends");
			return resp.getBody();
		}
		
		/*
		 * View Doctor details	
		 */
			@SuppressWarnings("unchecked")
			@GetMapping("view-patient-medication-doctor-details")
			public @ResponseBody JsonResponse<List<PatientMedicationModel>> viewPatientCurrentDrDetails(Model model, HttpSession session) {

				logger.info("Method : viewPatientCurrentDrDetails starts");
				JsonResponse<List<PatientMedicationModel>> resp = new JsonResponse<List<PatientMedicationModel>>();
				String userId="";
				try {
					userId = (String) session.getAttribute("USER_ID");
				} catch (Exception e) {
					e.printStackTrace();
				}
				

				try {
					resp = restTemplate.getForObject(env.getUserUrl() + "rest-doctorDetails?uId="+userId, JsonResponse.class);
				} catch (RestClientException e) {
					e.printStackTrace();
				}
				if (resp.getMessage() != null) {

					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("Success");
				}
			 
				logger.info("Method : viewPatientCurrentDrDetails ends");
				return resp;
			}
		/*************************************** start function for agGrid to get cchemist list ******************************/
		@GetMapping("/view-patient-medication-getChemist-through-agGrid")
		@ResponseBody List<MedicationCemistModel> veiwChemistForMedicine(HttpSession session, Model model, @RequestParam String place){
			logger.info("Methos : veiwChemistForMedicine starts");
			List<MedicationCemistModel> chemistlist = new ArrayList<MedicationCemistModel>();
			try {
				MedicationCemistModel [] chemistArray = restTemplate.getForObject(env.getUserUrl() + "rest-chemitstList?place="+place, MedicationCemistModel [] .class);
				chemistlist = Arrays.asList(chemistArray);
			}catch(RestClientException e){
				e.printStackTrace();
			}
			
			logger.info("Methos : veiwChemistForMedicine ends");
			return chemistlist;
		}
		/*************************************** start function for saving data ******************************/
		@SuppressWarnings("unchecked")
		@PostMapping("/view-patient-medication-save-request")
		@ResponseBody JsonResponse<RequestMedcineModel>addRequestMedicine(HttpSession session, Model model, @RequestBody RequestMedcineModel requestMedcineModel){
			logger.info("Methos : addRequestMedicine starts");
			System.out.println(requestMedcineModel);
			JsonResponse<RequestMedcineModel> response = new JsonResponse<RequestMedcineModel>();
			String userId="";
			try {
				userId = (String) session.getAttribute("USER_ID");
				requestMedcineModel.setPatientId(userId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			RequestMedcineModel req =new RequestMedcineModel();
			try {
				response = restTemplate.postForObject(env.getUserUrl() +"rest-send-requestMedcine", requestMedcineModel, JsonResponse.class);
			}catch(RestClientException e){
				e.printStackTrace();
			}
			logger.info("Methos : addRequestMedicine starts");
			return response;
		}
		/*************************************** start function for agGrid to get request mdcn list ******************************/
		@GetMapping("/view-patient-medication-view-request-mdcn-agGrid")
		@ResponseBody List<PatientMedicationRequestedMdcnModel> viewRequestMdcn(HttpSession session, Model model){
			logger.info("Methos : viewRequestMdcn starts");
			List<PatientMedicationRequestedMdcnModel> chemistlist = new ArrayList<PatientMedicationRequestedMdcnModel>();
			String userId="";
			try {
				userId = (String) session.getAttribute("USER_ID");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				PatientMedicationRequestedMdcnModel [] chemistArray = restTemplate.getForObject(env.getUserUrl() + "rest-viewRequestMdcn?userId="+userId, PatientMedicationRequestedMdcnModel [] .class);
				chemistlist = Arrays.asList(chemistArray);
				System.out.println(chemistlist);
			}catch(RestClientException e){
				e.printStackTrace();
			}
			
			logger.info("Methos : viewRequestMdcn ends");
			return chemistlist;
		}
		//Current test details(Medication)

		@SuppressWarnings("unchecked")
		@GetMapping("view-patient-medication-test-details")
		public @ResponseBody List<PatientMedicationModel> viewPatientTestDetails(Model model, HttpSession session) {

			logger.info("Method : viewPatientTestDetails starts");
			JsonResponse<List<PatientMedicationModel>> resp = new JsonResponse<List<PatientMedicationModel>>();
			String userId="";
			try {
				//userId = (String) session.getAttribute("PATIENT_ID");
				
				userId = (String) session.getAttribute("USER_ID");
				//userId=(String) session.getAttribute("PATIENT_ID");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (resp.getMessage() != null) {

				resp.setCode(resp.getMessage());
				resp.setMessage("Unsuccess");
			} else {
				resp.setMessage("Success");
			}
			
			try { 
				resp = restTemplate.getForObject(env.getUserUrl() + "rest-view-patient-test-details?uId="+userId, JsonResponse.class);
			} catch (RestClientException e) {
				e.printStackTrace();
			}
		 
			logger.info("Method : viewPatientTestDetails ends");
			return resp.getBody();
		}
		
/************************************** start function for agGrid to get Laborotory list *****************************/
@GetMapping("/view-patient-medication-getLabList-through-agGrid")
@ResponseBody List<MedicationCemistModel> veiwLabList(HttpSession session, Model model, @RequestParam String place){
	logger.info("Methos : veiwLabList  starts");
	List<MedicationCemistModel> chemistlist = new ArrayList<MedicationCemistModel>();
	try {
		MedicationCemistModel [] chemistArray = restTemplate.getForObject(env.getUserUrl() + "rest-veiwLabList?place="+place, MedicationCemistModel [] .class);
		chemistlist = Arrays.asList(chemistArray);
	}catch(RestClientException e){
		e.printStackTrace();
	}
	
	logger.info("Methos : veiwChemistForMedicine ends");
	return chemistlist;
}
/*************************************** start function for saving data of lab test request******************************/
@SuppressWarnings("unchecked")
@PostMapping("/view-patient-medication-save-lab-test-request")
@ResponseBody JsonResponse<RequestTestModel>addRequestTest(HttpSession session, Model model, @RequestBody RequestTestModel requestTestModel){
	logger.info("Methos : addRequestMedicine starts");
	//System.out.println(requestTestModel);
	JsonResponse<RequestTestModel> response = new JsonResponse<RequestTestModel>();
	String userId="";
	try {
		userId = (String) session.getAttribute("USER_ID");
		requestTestModel.setPatientId(userId);
	} catch (Exception e) {
		e.printStackTrace();
	}
	RequestTestModel req =new RequestTestModel();
	try {
		response = restTemplate.postForObject(env.getUserUrl() +"rest-send-requestLab", requestTestModel, JsonResponse.class);
	}catch(RestClientException e){
		e.printStackTrace();
	}
	logger.info("Methos : addRequestMedicine starts");
	return response;
}

}
