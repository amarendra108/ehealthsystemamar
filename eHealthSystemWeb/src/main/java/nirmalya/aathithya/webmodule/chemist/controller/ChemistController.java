package nirmalya.aathithya.webmodule.chemist.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.chemist.model.ChemistDashboardModel;
import nirmalya.aathithya.webmodule.chemist.model.ChemistPrescriptionDetailsModel;
import nirmalya.aathithya.webmodule.chemist.model.ChemistPrescriptionModel;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.patient.model.PatientMedicationModel;

@Controller
@RequestMapping("chemist/")
public class ChemistController{
	Logger logger = LoggerFactory.getLogger(ChemistController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;


	/*
	 * Get Mapping for ChemistDashboard
	 */

	@GetMapping("chemist-dashboard")
	public String viewChemistDashboard(Model model, HttpSession session) {

		logger.info("Method : viewChemistDashboard Starts");
		
		
		String userId = (String) session.getAttribute("USER_ID");

		try {
			//System.out.println(env.getChemistUrl());
			ChemistDashboardModel[] data = restTemplate.getForObject(
					env.getChemistUrl() + "chemistDashboard?userId=" + userId, ChemistDashboardModel[].class);
			List<ChemistDashboardModel> dataList = Arrays.asList(data);
			if(dataList.size()> 0)
			{
				model.addAttribute("allappointmwnt",dataList.get(0).getTodayAppointment());
				model.addAttribute("allpending",dataList.get(0).getPendingRequest());
				model.addAttribute("allbooked",dataList.get(0).getBookedRequest());
				model.addAttribute("allrejected",dataList.get(0).getRejectedRequest());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : viewChemistDashboard Ends");
		return "chemist/chemistDashboard";
	}
	
	
	//for r no of patient equested 
	@SuppressWarnings("unchecked")
	@GetMapping("chemist-dashboard-view-agGrid")
	public @ResponseBody JsonResponse<List<ChemistPrescriptionModel>> viewpatient(@RequestParam String date,HttpSession session){
		logger.info("Method :viewpatient starts");
		String userid = (String)session.getAttribute("USER_ID");
		
		
		JsonResponse<List<ChemistPrescriptionModel>> resp = new JsonResponse<List<ChemistPrescriptionModel>>();

		try {
			resp = 
			restTemplate.getForObject(env.getChemistUrl() + "rest-viewpatient?userid="+userid+ "&date="+date, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<ChemistPrescriptionModel> quotationNewModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<ChemistPrescriptionModel>>() {
				});
		for(ChemistPrescriptionModel m:quotationNewModel) {
//			m.setType("Appointment");
//			final String time = m.getTime();
//
//			try {
//			    final SimpleDateFormat sdf = new SimpleDateFormat("hh.mm.ss aa");
//			    final Date dateObj = sdf.parse(time);
//			    System.out.println(dateObj);
//			    m.setTime(dateObj.toString());
//			} catch (final ParseException e) {
//			    e.printStackTrace();
//			}
		}
		resp.setBody(quotationNewModel);
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");

		} else {
			resp.setMessage("Success");
		}
		
		

		logger.info("Method :viewpatient ends");
		// System.out.println("RESPONSE" + resp);
		return resp;
	}
	
	//******************************
	
	@SuppressWarnings("unchecked")
	@GetMapping("chemist-dashboard-model")
	public @ResponseBody List<ChemistPrescriptionDetailsModel> viewpatientdesc(@RequestParam String patientName,HttpSession session){
		logger.info("Method :viewpatientdesc starts");
		String userid = (String)session.getAttribute("USER_ID");
		
		
		JsonResponse<List<ChemistPrescriptionDetailsModel>> resp = new JsonResponse<List<ChemistPrescriptionDetailsModel>>();

		try {
			resp = 
			restTemplate.getForObject(env.getChemistUrl() + "getAllprecription?userid="+userid+ "&PatientName="+patientName, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<ChemistPrescriptionDetailsModel> quotationModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<ChemistPrescriptionDetailsModel>>() {
				});
		
		for(ChemistPrescriptionDetailsModel m:quotationModel) {
		}
		resp.setBody(quotationModel);
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");

		} else {
			resp.setMessage("Success");
		}
		

		logger.info("Method :viewpatientdesc ends");
		// System.out.println("RESPONSE" + resp);
		return resp.getBody();
	}
		
	
	
//	/*
//	 * Add prescription details
//	 */
//	@SuppressWarnings({ "unused", "unchecked" })
//	@PostMapping(value = "/chemist-dashboard-medicine-details")
//	
//	public @ResponseBody JsonResponse<ChemistPrescriptionDetailsModel> savePrescriptionDetails(
//			@RequestBody List<ChemistPrescriptionDetailsModel> chemistPrescriptionDetailsmodel,
//			Model model, HttpSession session) {
//		logger.info("Method : store medicine and prescription details methods Starts");
//
//		JsonResponse<ChemistPrescriptionDetailsModel> resp = new JsonResponse<ChemistPrescriptionDetailsModel>();
//	    System.out.println(chemistPrescriptionDetailsmodel);
//	      
//	    
//		/*
//		 * String userId; try { userId = (String)session.getAttribute("USER_ID");
//		 * }catch(Exception e) { e.printStackTrace(); }
//		 */
//		
//		try { 
//			resp = restTemplate.postForObject(env.getChemistUrl() + "addPatientDatas", chemistPrescriptionDetailsmodel,JsonResponse.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (resp.getMessage() != null) {
//			resp.setCode(resp																																																																																																											.getMessage());
//			resp.setMessage("Unsuccess");
//		} else {
//			resp.setMessage("success");
//		}
//		
//		logger.info("Method : store medicine and prescription details methods ends");
//		return resp;
//	}
	@SuppressWarnings({ "unused", "unchecked" })
	@GetMapping(value = "/chemist-dashboard-save-mdcine-status")
	public @ResponseBody JsonResponse<Object> saveChemistStatus(@RequestParam String orderid,Model model, HttpSession session, @RequestParam String status) {
		logger.info("Method : saveChemistStatus  methods Starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
	    System.out.println(orderid);
	      
	    
		/*
		 * String userId; try { userId = (String)session.getAttribute("USER_ID");
		 * }catch(Exception e) { e.printStackTrace(); }
		 */
		
		try { 
			resp = restTemplate.getForObject(env.getChemistUrl() + "rest-saveChemistStatus?orderid="+orderid+"&status="+status,JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (resp.getMessage() != null) {
			resp.setCode(resp																																																																																																											.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		
		logger.info("Method : ssaveChemistStatus methods ends");
		return resp;
	}
}