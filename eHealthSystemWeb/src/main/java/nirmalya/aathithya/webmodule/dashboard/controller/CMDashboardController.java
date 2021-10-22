package nirmalya.aathithya.webmodule.dashboard.controller;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.user.controller.AccessController;
import nirmalya.aathithya.webmodule.user.model.CmDashboardModel;
import nirmalya.aathithya.webmodule.user.model.UserRolesAndModuleIdModel;

/**
 * @author Nirmalya Labs
 *
 */
@Controller
public class CMDashboardController {

	Logger logger = LoggerFactory.getLogger(AccessController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRolesAndModuleIdModel userModel;

	@GetMapping("/cmdashboard")
	public String cmDashboard(Model model, HttpSession session) {
		logger.info("Method : user starts");

		String userId1 = (String) session.getAttribute("USER_ID");
		try {
			CmDashboardModel[] region = restTemplate.getForObject(env.getUserUrl() + "cmDashboardGetIdd?id=" + userId1,
					CmDashboardModel[].class);
			List<CmDashboardModel> cmDashboardGetIdd = Arrays.asList(region);
			model.addAttribute("cmDashboardGetIdd", cmDashboardGetIdd);
			System.out.println("cmDashboardGetIdd@@@@@@@" + cmDashboardGetIdd);
		} catch (RestClientException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("Method : user ends");
		return "user/cm-dashboard";
	}
 
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "cmdashboard-genderWise-patientno" })
	public @ResponseBody JsonResponse<Object> getPatientNo(Model model, @RequestParam("state") String encodestate,
			@RequestParam("date") String encodedate, @RequestParam("type") String encodetype, HttpSession session) {

		logger.info("Method : getPatientNo starts");
		byte[] encodeByte1 = Base64.getDecoder().decode(encodestate.getBytes());
		byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
		byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

		String state = (new String(encodeByte1));
		String date = (new String(encodeByte2));
		String type = (new String(encodeByte3));

		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		System.out.println(state+" "+date+" "+type);

		try {
			resp = restTemplate.getForObject(
					env.getUserUrl() + "genderWise-patientno?state=" + state + "&date=" + date + "&type=" + type,
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
		logger.info("Method : getPatientNo ends");
		return resp;
	}
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "cmdashboard-areaWise-patientno" })
	public @ResponseBody JsonResponse<Object> getAreaPatientNo(Model model, @RequestParam("state") String encodestate,
			@RequestParam("date") String encodedate, @RequestParam("type") String encodetype, HttpSession session) {

		logger.info("Method : getAreaPatientNo starts");
		byte[] encodeByte1 = Base64.getDecoder().decode(encodestate.getBytes());
		byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
		byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

		String state = (new String(encodeByte1));
		String date = (new String(encodeByte2));
		String type = (new String(encodeByte3));

		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		System.out.println(state+" "+date+" "+type);

		try {
			resp = restTemplate.getForObject(
					env.getUserUrl() + "areWise-patientno?state=" + state + "&date=" + date + "&type=" + type,
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
		logger.info("Method : getAreaPatientNo ends");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "cmdashboard-ageWise-patientno" })
	public @ResponseBody JsonResponse<Object> getAgePatientNo(Model model, @RequestParam("state") String encodestate,
			@RequestParam("date") String encodedate, @RequestParam("type") String encodetype, HttpSession session) {

		logger.info("Method : getAgePatientNo starts");
		byte[] encodeByte1 = Base64.getDecoder().decode(encodestate.getBytes());
		byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
		byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

		String state = (new String(encodeByte1));
		String date = (new String(encodeByte2));
		String type = (new String(encodeByte3));

		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		System.out.println(state+" "+date+" "+type);

		try {
			resp = restTemplate.getForObject(
					env.getUserUrl() + "ageWise-patientno?state=" + state + "&date=" + date + "&type=" + type,
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
		logger.info("Method : getAgePatientNo ends");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "cmdashboard-diseaseWise-patientno" })
	public @ResponseBody JsonResponse<Object> getDiseaseMaleFemalePatientNo(Model model, @RequestParam("state") String encodestate,
			@RequestParam("date") String encodedate, @RequestParam("type") String encodetype, HttpSession session) {

		logger.info("Method : getAgePatientNo starts");
		byte[] encodeByte1 = Base64.getDecoder().decode(encodestate.getBytes());
		byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
		byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

		String state = (new String(encodeByte1));
		String date = (new String(encodeByte2));
		String type = (new String(encodeByte3));

		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		System.out.println(state+" "+date+" "+type);

		try {
			resp = restTemplate.getForObject(
					env.getUserUrl() + "diseaseWise-patientno?state=" + state + "&date=" + date + "&type=" + type,
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
		logger.info("Method : getDiseasePatientNo ends");
		return resp;
	}
	
	
	
	
	
	//High Risk Data Location Wise
	
			@SuppressWarnings("unchecked")
			@GetMapping(value = { "cmdashboard-high-risk-data-location-wise" })
			public @ResponseBody JsonResponse<Object> getHighRiskLocationWise(Model model, @RequestParam("state") String encodestate,
					@RequestParam("date") String encodedate, @RequestParam("type") String encodetype, HttpSession session) {

				logger.info("Method : getHighRiskLocationWise starts");
				byte[] encodeByte1 = Base64.getDecoder().decode(encodestate.getBytes());
				byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
				byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

				String state = (new String(encodeByte1));
				String date = (new String(encodeByte2));
				String type = (new String(encodeByte3));

				JsonResponse<Object> resp = new JsonResponse<Object>();
				
			//	System.out.println(state+" "+date+" "+type);

				try {
					resp = restTemplate.getForObject(
							env.getUserUrl() + "rest-getHighRiskLocationWise?state=" + state + "&date=" + date + "&type=" + type,
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
				logger.info("Method : getHighRiskLocationWise ends");
				return resp;
			}
			


		//Total Health Checkup Status
		
		@SuppressWarnings("unchecked")
		@GetMapping(value = { "cmdashboard-total-health-checkup-status" })
		public @ResponseBody JsonResponse<Object> getTotalHealthCheckup(Model model, @RequestParam("state") String encodestate,
				@RequestParam("date") String encodedate, @RequestParam("type") String encodetype, HttpSession session) {

			logger.info("Method : getAreaPatientNo starts");
			byte[] encodeByte1 = Base64.getDecoder().decode(encodestate.getBytes());
			byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
			byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

			String state = (new String(encodeByte1));
			String date = (new String(encodeByte2));
			String type = (new String(encodeByte3));

			JsonResponse<Object> resp = new JsonResponse<Object>();
			
			//System.out.println(state+" "+date+" "+type);

			try {
				resp = restTemplate.getForObject(
						env.getUserUrl() + "rest-totalHealthCheckup?state=" + state + "&date=" + date + "&type=" + type,
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
			logger.info("Method : getAreaPatientNo ends");
			return resp;
		}



	//Disese wise total patient no
		@SuppressWarnings("unchecked")
		@GetMapping(value = { "cmdashboard-dieaseWise-patientno" })
		public @ResponseBody JsonResponse<Object> getDiseaseWisePatientNo(Model model, @RequestParam("state") String encodestate,
				@RequestParam("date") String encodedate, @RequestParam("type") String encodetype, HttpSession session) {

			logger.info("Method : getDiseaseWisePatientNo starts");
			byte[] encodeByte1 = Base64.getDecoder().decode(encodestate.getBytes());
			byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
			byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

			String state = (new String(encodeByte1));
			String date = (new String(encodeByte2));
			String type = (new String(encodeByte3));

			JsonResponse<Object> resp = new JsonResponse<Object>();
			
			System.out.println(state+" "+date+" "+type);

			try {
				resp = restTemplate.getForObject(
						env.getUserUrl() + "dieaseWise-patientno?state=" + state + "&date=" + date + "&type=" + type,
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
			logger.info("Method : getDiseaseWisePatientNo ends");
			return resp;
		}


	//treanding disease

		@SuppressWarnings("unchecked")
		@GetMapping(value = { "cmdashboard-top-treanding-disease" })
		public @ResponseBody JsonResponse<Object> getreandingDisease(Model model, @RequestParam("state") String encodestate,
				@RequestParam("date") String encodedate, @RequestParam("type") String encodetype, HttpSession session) {

			logger.info("Method : getreandingDisease starts");
			byte[] encodeByte1 = Base64.getDecoder().decode(encodestate.getBytes());
			byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
			byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

			String state = (new String(encodeByte1));
			String date = (new String(encodeByte2));
			String type = (new String(encodeByte3));

			JsonResponse<Object> resp = new JsonResponse<Object>();
			
			System.out.println(state+" "+date+" "+type);

			try {
				resp = restTemplate.getForObject(
						env.getUserUrl() + "treanding-disease?state=" + state + "&date=" + date + "&type=" + type,
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
			logger.info("Method : getreandingDisease ends");
			return resp;
		}



	//Count test details
		@SuppressWarnings("unchecked")
		@GetMapping(value = { "cmdashboard-count-testDetails" })
		public @ResponseBody JsonResponse<Object> getCountTestDetails(Model model, @RequestParam("state") String encodestate,
				@RequestParam("date") String encodedate, @RequestParam("type") String encodetype, HttpSession session) {

			logger.info("Method : getCountTestDetails starts");
			byte[] encodeByte1 = Base64.getDecoder().decode(encodestate.getBytes());
			byte[] encodeByte2 = Base64.getDecoder().decode(encodedate.getBytes());
			byte[] encodeByte3 = Base64.getDecoder().decode(encodetype.getBytes());

			String state = (new String(encodeByte1));
			String date = (new String(encodeByte2));
			String type = (new String(encodeByte3));

			JsonResponse<Object> resp = new JsonResponse<Object>();
			
			try {
				resp = restTemplate.getForObject(
						env.getUserUrl() + "rest-countTestDetails?state=" + state + "&date=" + date + "&type=" + type,
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
}
