package nirmalya.aathithya.webmodule.lab.controller;

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
import nirmalya.aathithya.webmodule.lab.model.LabDashboardModel;
import nirmalya.aathithya.webmodule.lab.model.LabDashboardPatientModel;
import nirmalya.aathithya.webmodule.lab.model.LabPatientPrescriptionModel;

@Controller
@RequestMapping(value = { "lab/" })
public class LabDashboardController {
	Logger logger = LoggerFactory.getLogger(LabDashboardController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	/*
	 * Get Mapping for view-doctor-insurance
	 */

	@GetMapping("lab-dashboard")
	public String labDashboard(Model model, HttpSession session) {

		logger.info("Method : labDashboard starts");

		//Count Registered
		
		String userId1 = (String) session.getAttribute("USER_ID");
		try {
			LabDashboardModel[] region = restTemplate.getForObject(env.getLabUrl() + "labCountRegistered?id="+userId1,
					LabDashboardModel[].class);
			List<LabDashboardModel> labCountRegistered = Arrays.asList(region);
			model.addAttribute("labCountRegistered", labCountRegistered);
		
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
			
		logger.info("Method : labDashboard ends");
		return "lab/lab-dashboard";
	}
	
	/*
	 * View  Test Report Done 	
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "lab-dashboard-test-report" })
	public @ResponseBody JsonResponse<Object> viewTestReport(Model model, HttpSession session) {
		logger.info("Method : viewTestReport starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId2 = (String) session.getAttribute("USER_ID");
		
		try {
			res = restTemplate.getForObject(env.getLabUrl() + "rest-viewTestReport?id="+userId2, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		
		logger.info("Method : viewTestReport ends");
		return res;
	}
	
	/*
	 * View  Health Check Up 	
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "lab-dashboard-health-checkup" })
	public @ResponseBody JsonResponse<Object> viewTHealthCheckup(Model model, HttpSession session) {
		logger.info("Method : viewTHealthCheckup starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId2 = (String) session.getAttribute("USER_ID");
		
		try {
			res = restTemplate.getForObject(env.getLabUrl() + "rest-viewTHealthCheckup?id="+userId2, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		
		logger.info("Method : viewTHealthCheckup ends");
		return res;
	}
	
//	/*
//	 * VIEW FOR LAB DASHBOARD AG GRID
//	 */
//	@SuppressWarnings("unchecked")
//	@GetMapping("lab-dashboard-ag-grid")
//	public @ResponseBody List<LabDashboardModel> getLabView(HttpSession session) {
//
//		logger.info("Method :getLabView starts");
//
//		JsonResponse<List<LabDashboardModel>> resp = new JsonResponse<List<LabDashboardModel>>();
//			
//		try {
//
//			resp = restTemplate.getForObject(env.getLabUrl() + "rest-getLabView", JsonResponse.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		ObjectMapper mapper = new ObjectMapper();
//
//		List<LabDashboardModel> labDashboardModel = mapper.convertValue(resp.getBody(),
//				new TypeReference<List<LabDashboardModel>>() {
//				});
//		
//		resp.setBody(labDashboardModel);
//		if (resp.getMessage() != "" && resp.getMessage() != null) {
//			resp.setCode(resp.getMessage());
//			resp.setMessage("Unsuccess");
//		} else {
//			resp.setMessage("Success");
//		}
//
//		logger.info("Method :getLabView ends");
//		
//		return resp.getBody();
//	}
	/*
	 * VIEW FOR LAB DASHBOARD AG GRID 
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("lab-dashboard-ag-grid")
	public @ResponseBody JsonResponse<List<LabDashboardPatientModel>> getLabViewDashboard(@RequestParam String date,
			HttpSession session) {

		logger.info("Method :getLabViewDashboard starts");

		String userid = (String) session.getAttribute("USER_ID");
		
		JsonResponse<List<LabDashboardPatientModel>> resp = new JsonResponse<List<LabDashboardPatientModel>>();

		try {
			resp = restTemplate.getForObject(env.getLabUrl() + "rest-getLabView?&userid=" + userid + "&date=" + date,
					JsonResponse.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		
		logger.info("Method :getLabViewDashboard ends");

		return resp;
	}

	/****************************************
	 * for ag grid table
	 *****************************/
	@SuppressWarnings("unchecked")
	@GetMapping("lab-dashboard-modal")
	public @ResponseBody List<LabPatientPrescriptionModel> viewlabPatientDesc(@RequestParam String patientid,@RequestParam String orderid,
			HttpSession session) {
		logger.info("Method :viewlabPatientDesc starts");
		String userid = (String) session.getAttribute("USER_ID");

		
		JsonResponse<List<LabPatientPrescriptionModel>> resp = new JsonResponse<List<LabPatientPrescriptionModel>>();

		try {
			resp = restTemplate.getForObject(
					env.getLabUrl() + "getLabPatientprecription?userid=" + userid + "&patientid=" + patientid+"&orderid=" + orderid,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<LabPatientPrescriptionModel> quotationModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<LabPatientPrescriptionModel>>() {
				});

		for (LabPatientPrescriptionModel m : quotationModel) {
		}
		resp.setBody(quotationModel);
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");

		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :viewlabPatientDesc ends");
		// System.out.println("RESPONSE" + resp);
		return resp.getBody();
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	@GetMapping(value = "/lab-dashboard-save-lab-status")
	public @ResponseBody JsonResponse<Object> saveLabStatus(@RequestParam String orderid,Model model, HttpSession session, @RequestParam String status) {
		logger.info("Method : saveLabStatus  methods Starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
	    System.out.println(orderid);
	      
	    
		
		try { 
			resp = restTemplate.getForObject(env.getLabUrl() + "rest-saveLabStatus?orderid="+orderid+"&status="+status,JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (resp.getMessage() != null) {
			resp.setCode(resp																																																																																																											.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		
		logger.info("Method : saveLabStatus methods ends");
		return resp;
	}
	
}
