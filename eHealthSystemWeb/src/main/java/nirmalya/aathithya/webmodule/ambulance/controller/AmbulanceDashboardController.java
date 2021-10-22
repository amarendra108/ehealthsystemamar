package nirmalya.aathithya.webmodule.ambulance.controller;

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

import nirmalya.aathithya.webmodule.ambulance.model.AmbulanceDashboardModel;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.common.utils.PdfGeneratatorUtil;
import nirmalya.aathithya.webmodule.doctor.model.PatientDetailDoctorDashBoardModel;

@Controller
@RequestMapping(value = { "ambulance/" })
public class AmbulanceDashboardController {

	Logger logger = LoggerFactory.getLogger(AmbulanceDashboardController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@Autowired
	PdfGeneratatorUtil pdfGeneratorUtil;
	@GetMapping("view-ambulance-dashboard")
	public String viewMyPatientProfile(Model model, HttpSession session) {

		logger.info("Method : ambulance dashboard starts");
		
		String userId = (String) session.getAttribute("USER_ID");

		try {
			AmbulanceDashboardModel[] dashboardregion = restTemplate.getForObject(
					env.getAmbulanceUrl() + "getdashboardBooking?id=" + userId, AmbulanceDashboardModel[].class);
			List<AmbulanceDashboardModel> DashboardRegiontList = Arrays.asList(dashboardregion);
			model.addAttribute("dashboardbookingList", DashboardRegiontList);

		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("Method : ambulance dashboard ends");
		return "ambulance/ambulance-dashboard";
	}
	
	//for  no of patient requested 
		@SuppressWarnings("unchecked")
		@GetMapping("view-ambulance-dashboard-view-agGrid")
		public @ResponseBody JsonResponse<List<AmbulanceDashboardModel>> viewpatient(@RequestParam String date,HttpSession session){
			logger.info("Method :viewpatient starts");
			String userid = (String)session.getAttribute("USER_ID");
			
			
			JsonResponse<List<AmbulanceDashboardModel>> resp = new JsonResponse<List<AmbulanceDashboardModel>>();

			try {
				resp = 
				restTemplate.getForObject(env.getAmbulanceUrl() + "rest-viewpatientrequested?userid="+userid+ "&date="+date, JsonResponse.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();

			List<AmbulanceDashboardModel> quotationNewModel = mapper.convertValue(resp.getBody(),
					new TypeReference<List<AmbulanceDashboardModel>>() {
					});
			for(AmbulanceDashboardModel m:quotationNewModel) {

			}
			resp.setBody(quotationNewModel);
			if (resp.getMessage() != null && resp.getMessage() != "") {
				resp.setCode(resp.getMessage());
				resp.setMessage("Unsuccess");

			} else {
				resp.setMessage("Success");
			}
				logger.info("Method :viewpatient ends");
			 System.out.println("RESPONSE@@@@@@@@@" + resp);
			return resp;
		}
		
	
		@SuppressWarnings("unchecked")
		@GetMapping(value = "/view-ambulance-dashboard-save-mdcine-status")
		public @ResponseBody JsonResponse<Object> saveChemistStatus(@RequestParam String orderid,Model model, HttpSession session, @RequestParam String status) {
			logger.info("Method : saveAmbulanceStatus  methods Starts");

			JsonResponse<Object> resp = new JsonResponse<Object>();
		      
			try { 
				resp = restTemplate.getForObject(env.getAmbulanceUrl() + "rest-saveAmbulanceStatus?orderid="+orderid+"&status="+status,JsonResponse.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (resp.getMessage() != null) {
				resp.setCode(resp																																																																																																											.getMessage());
				resp.setMessage("Unsuccess");
			} else {
				resp.setMessage("Success");
			}
			
			logger.info("Method : saveAmbulanceStatus methods ends");
			return resp;
		}
		
		
		@SuppressWarnings("unchecked")

		@GetMapping(value = { "view-ambulance-dashboard-patient-details-by-id" })
		public @ResponseBody JsonResponse<AmbulanceDashboardModel> getpatientDetailsById(HttpSession session,
				Model model, @RequestParam("id") String id) {
			logger.info("Method : getpatientDetailsById starts");
			JsonResponse<AmbulanceDashboardModel> res = new JsonResponse<AmbulanceDashboardModel>();
			try {
				res = restTemplate.getForObject(env.getAmbulanceUrl() + "getPatientDetails?id=" + id, JsonResponse.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(res+"res");
			if (res.getMessage() != null) {
				res.setCode(res.getMessage());
				res.setMessage("Unsuccess");
			} else {
				res.setMessage("success");
			}
			logger.info("Method : getpatientDetailsById ends");
			return res;
		}
}
