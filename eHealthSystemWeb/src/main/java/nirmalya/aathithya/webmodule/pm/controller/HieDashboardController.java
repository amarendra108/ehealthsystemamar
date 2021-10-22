package nirmalya.aathithya.webmodule.pm.controller;

import java.util.Arrays;
import java.util.Base64;
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

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;

@Controller
@RequestMapping(value = "pm/")
public class HieDashboardController {
	Logger logger = LoggerFactory.getLogger(HieDashboardController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@GetMapping("/hie-dashboard")
	public String hieDashboard(Model model, HttpSession session) {

		logger.info("Method : hieDashboard starts");
		
		String userId1 = (String) session.getAttribute("USER_ID");
		try {
			DropDownModel[] region = restTemplate.getForObject(env.getUserUrl() + "rest-pmDashboardgetId?id=" + userId1,
					DropDownModel[].class);
			List<DropDownModel> pMDashboardModel = Arrays.asList(region);
			model.addAttribute("pMDashboardModel", pMDashboardModel);
			System.out.println("pMDashboardModel@@@@@@@" + pMDashboardModel);
		} catch (RestClientException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		logger.info("Method : hieDashboard ends");
		return "pm/hieDashboard";

	}
	
	//Count test details
			@SuppressWarnings("unchecked")
			@GetMapping(value = { "/hie-dashboard-count" })
			public @ResponseBody JsonResponse<Object> getCountHieDashboard(Model model, @RequestParam("country") String encodecountry,
					@RequestParam("date") String encodedate, @RequestParam("type") String encodetype, HttpSession session) {

				logger.info("Method : getCountHieDashboard starts");
				byte[] encodeByte1 = Base64.getDecoder().decode(encodecountry.getBytes());
				byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
				byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

				String country = (new String(encodeByte1));
				String date = (new String(encodeByte2));
				String type = (new String(encodeByte3));

				JsonResponse<Object> resp = new JsonResponse<Object>();
				
				try {
					resp = restTemplate.getForObject(env.getPmUrl() + "rest-getCountHieDashboards?country=" + country + "&date=" + date + "&type=" + type,
							JsonResponse.class);

				} catch (RestClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (resp.getMessage() != null && resp.getMessage() != "") {
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("success");
				}
				
				logger.info("Method : getCountTestDetails ends");
				return resp;
			}
			
			//Diagnsostic Lab
			@SuppressWarnings("unchecked")
			@GetMapping(value = { "hie-dashboard-total-diagnostic-lab" })
			public @ResponseBody JsonResponse<Object> getTotalHospitals(Model model, @RequestParam("country") String encodecountry,
					@RequestParam("date") String encodedate, @RequestParam("type") String encodetype, HttpSession session) {

				logger.info("Method : getTotalHospital starts");
				byte[] encodeByte1 = Base64.getDecoder().decode(encodecountry.getBytes());
				byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
				byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

				String country = (new String(encodeByte1));
				String date = (new String(encodeByte2));
				String type = (new String(encodeByte3));

				JsonResponse<Object> resp = new JsonResponse<Object>();
				
				System.out.println(country+" "+date+" "+type);

				try {
					resp = restTemplate.getForObject(
							env.getPmUrl() + "rest-getDiagnosticLab?country=" + country + "&date=" + date + "&type=" + type,
							JsonResponse.class);

				} catch (RestClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (resp.getMessage() != null && resp.getMessage() != "") {
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("success");
				}
				logger.info("Method : getTotalHospital ends");
				return resp;
			}
			
			
			
			
			
			//Pathology Lab
			@SuppressWarnings("unchecked")
			@GetMapping(value = { "hie-dashboard-total-pathology-lab" })
			public @ResponseBody JsonResponse<Object> getPathologyLab(Model model, @RequestParam("country") String encodecountry,
					@RequestParam("date") String encodedate, @RequestParam("type") String encodetype, HttpSession session) {

				logger.info("Method : getPathologyLab starts");
				byte[] encodeByte1 = Base64.getDecoder().decode(encodecountry.getBytes());
				byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
				byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

				String country = (new String(encodeByte1));
				String date = (new String(encodeByte2));
				String type = (new String(encodeByte3));

				JsonResponse<Object> resp = new JsonResponse<Object>();
				
				System.out.println(country+" "+date+" "+type);

				try {
					resp = restTemplate.getForObject(
							env.getPmUrl() + "rest-getPathologyLab?country=" + country + "&date=" + date + "&type=" + type,
							JsonResponse.class);

				} catch (RestClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (resp.getMessage() != null && resp.getMessage() != "") {
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("success");
				}
				logger.info("Method : getPathologyLab ends");
				return resp;
			}
			
			//Total Pharmacy
			@SuppressWarnings("unchecked")
			@GetMapping(value = { "hie-dashboard-total-pharmacy" })
			public @ResponseBody JsonResponse<Object> getTotalPharmacy(Model model, @RequestParam("country") String encodecountry,
					@RequestParam("date") String encodedate, @RequestParam("type") String encodetype, HttpSession session) {

				logger.info("Method : getTotalPharmacy starts");
				byte[] encodeByte1 = Base64.getDecoder().decode(encodecountry.getBytes());
				byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
				byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

				String country = (new String(encodeByte1));
				String date = (new String(encodeByte2));
				String type = (new String(encodeByte3));

				JsonResponse<Object> resp = new JsonResponse<Object>();
				
				System.out.println(country+" "+date+" "+type);

				try {
					resp = restTemplate.getForObject(
							env.getPmUrl() + "rest-getTotalPharmacy?country=" + country + "&date=" + date + "&type=" + type,
							JsonResponse.class);

				} catch (RestClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (resp.getMessage() != null && resp.getMessage() != "") {
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("success");
				}
				logger.info("Method : getTotalPharmacy ends");
				return resp;
			}
			
			//Total Ambulance
			@SuppressWarnings("unchecked")
			@GetMapping(value = { "hie-dashboard-total-ambulance" })
			public @ResponseBody JsonResponse<Object> getTotalAmbulance(Model model, @RequestParam("country") String encodecountry,
					@RequestParam("date") String encodedate, @RequestParam("type") String encodetype, HttpSession session) {

				logger.info("Method : getTotalAmbulance starts");
				byte[] encodeByte1 = Base64.getDecoder().decode(encodecountry.getBytes());
				byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
				byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

				String country = (new String(encodeByte1));
				String date = (new String(encodeByte2));
				String type = (new String(encodeByte3));

				JsonResponse<Object> resp = new JsonResponse<Object>();
				
				System.out.println(country+" "+date+" "+type);

				try {
					resp = restTemplate.getForObject(
							env.getPmUrl() + "rest-getTotalAmbulance?country=" + country + "&date=" + date + "&type=" + type,
							JsonResponse.class);

				} catch (RestClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (resp.getMessage() != null && resp.getMessage() != "") {
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("success");
				}
				logger.info("Method : getTotalAmbulance ends");
				return resp;
			}
			
			//Total Blood Bank
			@SuppressWarnings("unchecked")
			@GetMapping(value = { "hie-dashboard-total-blood-bank" })
			public @ResponseBody JsonResponse<Object> getTotalBloodBank(Model model, @RequestParam("country") String encodecountry,
					@RequestParam("date") String encodedate, @RequestParam("type") String encodetype, HttpSession session) {

				logger.info("Method : getTotalBloodBank starts");
				byte[] encodeByte1 = Base64.getDecoder().decode(encodecountry.getBytes());
				byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
				byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

				String country = (new String(encodeByte1));
				String date = (new String(encodeByte2));
				String type = (new String(encodeByte3));

				JsonResponse<Object> resp = new JsonResponse<Object>();
				
				System.out.println(country+" "+date+" "+type);

				try {
					resp = restTemplate.getForObject(
							env.getPmUrl() + "rest-getTotalBloodBank?country=" + country + "&date=" + date + "&type=" + type,
							JsonResponse.class);

				} catch (RestClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (resp.getMessage() != null && resp.getMessage() != "") {
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("success");
				}
				logger.info("Method : getTotalBloodBank ends");
				return resp;
			}
			
			//Total Insurance Service Provider
			@SuppressWarnings("unchecked")
			@GetMapping(value = { "hie-dashboard-total-insurance-provider" })
			public @ResponseBody JsonResponse<Object> getTotalInsuranceProvider(Model model, @RequestParam("country") String encodecountry,
					@RequestParam("date") String encodedate, @RequestParam("type") String encodetype, HttpSession session) {

				logger.info("Method : getTotalInsuranceProvider starts");
				byte[] encodeByte1 = Base64.getDecoder().decode(encodecountry.getBytes());
				byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
				byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

				String country = (new String(encodeByte1));
				String date = (new String(encodeByte2));
				String type = (new String(encodeByte3));

				JsonResponse<Object> resp = new JsonResponse<Object>();
				
				System.out.println(country+" "+date+" "+type);

				try {
					resp = restTemplate.getForObject(
							env.getPmUrl() + "rest-getTotalInsuranceProvider?country=" + country + "&date=" + date + "&type=" + type,
							JsonResponse.class);

				} catch (RestClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (resp.getMessage() != null && resp.getMessage() != "") {
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("success");
				}
			//	System.out.println("getTotalInsuranceProvider"+resp);
				logger.info("Method : getTotalInsuranceProvider ends");
				return resp;
			}
			
			
			
			
			
			
			//Gender Wise
			@SuppressWarnings("unchecked")
			@GetMapping(value = { "hie-dashboard-total-gender-wise" })
			public @ResponseBody JsonResponse<Object> getTotalGenderWise(Model model, @RequestParam("country") String encodecountry,
					@RequestParam("date") String encodedate, @RequestParam("type") String encodetype, HttpSession session) {

				logger.info("Method : getTotalGenderWise starts");
				byte[] encodeByte1 = Base64.getDecoder().decode(encodecountry.getBytes());
				byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
				byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

				String country = (new String(encodeByte1));
				String date = (new String(encodeByte2));
				String type = (new String(encodeByte3));

				JsonResponse<Object> resp = new JsonResponse<Object>();
				
				System.out.println(country+" "+date+" "+type);

				try {
					resp = restTemplate.getForObject(
							env.getPmUrl() + "rest-getTotalGenderWises?country=" + country + "&date=" + date + "&type=" + type,
							JsonResponse.class);

				} catch (RestClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (resp.getMessage() != null && resp.getMessage() != "") {
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("success");
				}
				logger.info("Method : getTotalGenderWise ends");
				return resp;
			}
			
			
		
			
			//Total Doctors Pvt./Govt.
			
			@SuppressWarnings("unchecked")

			@GetMapping(value = { "hie-dashboard-total-doctors-pvt-govt" })
			public @ResponseBody JsonResponse<Object> getTotalDoctorsPvt(Model model,
					@RequestParam("country") String encodecountry,

					@RequestParam("date") String encodedate, @RequestParam("type") String encodetype,
					HttpSession session) {

				logger.info("Method : getTotalDoctorsPvt starts");
				byte[] encodeByte1 = Base64.getDecoder().decode(encodecountry.getBytes());
				byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
				byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

				String country = (new String(encodeByte1));
				String date = (new String(encodeByte2));
				String type = (new String(encodeByte3));

				JsonResponse<Object> resp = new JsonResponse<Object>();

				System.out.println(country + " " + date + " " + type);

				try {
					resp = restTemplate.getForObject(env.getPmUrl() + "rest-getTotalDoctorsPvt?country=" + country
							+ "&date=" + date + "&type=" + type, JsonResponse.class);

				} catch (RestClientException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (resp.getMessage() != null && resp.getMessage() != "") {
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("success");
				}
				logger.info("Method : getTotalDoctorsPvt ends");
				return resp;
			}
			/*************************** get Hospital********************/
			@SuppressWarnings("unchecked")
			@GetMapping(value = { "hie-dashboard-total-hospital" })
			public @ResponseBody JsonResponse<Object> getTotalHospital(Model model, @RequestParam("country") String encodecountry,
					@RequestParam("date") String encodedate, @RequestParam("type") String encodetype, HttpSession session) {

				logger.info("Method : getTotalHospital starts");
				byte[] encodeByte1 = Base64.getDecoder().decode(encodecountry.getBytes());
				byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
				byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

				String country = (new String(encodeByte1));
				String date = (new String(encodeByte2));
				String type = (new String(encodeByte3));

				JsonResponse<Object> resp = new JsonResponse<Object>();
				
				System.out.println(country+" "+date+" "+type);

				try {
					resp = restTemplate.getForObject(
							env.getPmUrl() + "rest-hie-getTotalHospital?country=" + country + "&date=" + date + "&type=" + type,
							JsonResponse.class);

				} catch (RestClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (resp.getMessage() != null && resp.getMessage() != "") {
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("success");
				}
				logger.info("Method : getTotalHospital ends");
				return resp;
			}
			/************************** get clicic*********************/
			@SuppressWarnings("unchecked")
			@GetMapping(value = { "hie-dashboard-total-clinic" })
			public @ResponseBody JsonResponse<Object> getTotalClinic(Model model, @RequestParam("country") String encodecountry,
					@RequestParam("date") String encodedate, @RequestParam("type") String encodetype, HttpSession session) {

				logger.info("Method : getTotalClinic starts");
				byte[] encodeByte1 = Base64.getDecoder().decode(encodecountry.getBytes());
				byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
				byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

				String country = (new String(encodeByte1));
				String date = (new String(encodeByte2));
				String type = (new String(encodeByte3));

				JsonResponse<Object> resp = new JsonResponse<Object>();
				
				System.out.println(country+" "+date+" "+type);

				try {
					resp = restTemplate.getForObject(
							env.getPmUrl() + "rest-hie-getTotalClinic?country=" + country + "&date=" + date + "&type=" + type,
							JsonResponse.class);

				} catch (RestClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (resp.getMessage() != null && resp.getMessage() != "") {
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("success");
				}
				logger.info("Method : getTotalClinic ends");
				return resp;
			}	 
}
