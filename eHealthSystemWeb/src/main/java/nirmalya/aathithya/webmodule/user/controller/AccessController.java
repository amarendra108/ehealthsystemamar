package nirmalya.aathithya.webmodule.user.controller;

import java.io.File;
import java.net.URLEncoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.Base64;

import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import nirmalya.aathithya.webmodule.common.utils.CommonUsed;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.patient.model.EHealthCardModel;
import nirmalya.aathithya.webmodule.user.model.Activity;
import nirmalya.aathithya.webmodule.user.model.ActivityAvlFunctionModel;
import nirmalya.aathithya.webmodule.user.model.CmDashboardModel;
import nirmalya.aathithya.webmodule.user.model.DmDashboardModel;
import nirmalya.aathithya.webmodule.user.model.Function;
import nirmalya.aathithya.webmodule.user.model.Module;
import nirmalya.aathithya.webmodule.user.model.PMDashboardModel;
import nirmalya.aathithya.webmodule.user.model.User;
import nirmalya.aathithya.webmodule.user.model.UserRegistrationModel;
import nirmalya.aathithya.webmodule.user.model.UserRolesAndModuleIdModel;

/**
 * @author Nirmalya Labs
 *
 */
@Controller
public class AccessController {

	Logger logger = LoggerFactory.getLogger(AccessController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRolesAndModuleIdModel userModel;

	/**
	 * Function to check connection
	 *
	 */
	@GetMapping("welcome")
	public String welcome(Model model, HttpSession session) {
		logger.info("Method : welcome starts");

		logger.info("Method : welcome ends");
		return "welcome";
	}

	/**
	 * Function to check connection
	 *
	 */
	@GetMapping("hrms-dashboard")
	public String hrmsDashboard(Model model, HttpSession session) {
		logger.info("Method : hrmsDashboard starts");

		logger.info("Method : hrmsDashboard ends");
		return "employee/hrms-dashboard";
	}

	@GetMapping("/hrms-index")
	public String hrmsIndex(Model model, HttpSession session) {
		logger.info("Method : hrmsIndex starts");

		logger.info("Method : hrmsIndex ends");
		return "hrms-index";
	}

	/**
	 * Function for home page
	 *
	 */
	@GetMapping("/")
	public String home(Model model) {
		logger.info("Method : / starts");

		logger.info("Method : / ends");
		// return "app_index";
		// return "nerp-login";
		// return "nerp_home";
		// drop down for state list
		// drop down for user role list

		try {
			DropDownModel[] state = restTemplate.getForObject(env.getUserUrl() + "getStateLists-doctor",
					DropDownModel[].class);
			List<DropDownModel> stateList = Arrays.asList(state);
			model.addAttribute("stateList", stateList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		// return "ehealthsystemindex";
		return "ehealth-index/index";
	}

	// about us
	@GetMapping("/about-us")
	public String aboutus(Model model) {
		logger.info("Method : aboutus starts");
		try {
			DropDownModel[] state = restTemplate.getForObject(env.getUserUrl() + "getStateLists-doctor",
					DropDownModel[].class);
			List<DropDownModel> stateList = Arrays.asList(state);
			model.addAttribute("stateList", stateList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : aboutus ends");
		return "ehealth-index/aboutus";
	}

	// vaccine Tracker
	@GetMapping("/offering-vaccineTracker")
	public String vaccine(Model model) {
		logger.info("Method : vaccine starts");
		try {
			DropDownModel[] state = restTemplate.getForObject(env.getUserUrl() + "getStateLists-doctor",
					DropDownModel[].class);
			List<DropDownModel> stateList = Arrays.asList(state);
			model.addAttribute("stateList", stateList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : vaccine ends");
		return "ehealth-index/vaaccineimmunizationtracker";
	}

	// preventive health check-up
	@GetMapping("/offering-preventive-healthCheckup")
	public String preventiveHealthCheckup(Model model) {
		logger.info("Method : preventiveHealthCheckup starts");
		try {
			DropDownModel[] state = restTemplate.getForObject(env.getUserUrl() + "getStateLists-doctor",
					DropDownModel[].class);
			List<DropDownModel> stateList = Arrays.asList(state);
			model.addAttribute("stateList", stateList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : preventiveHealthCheckup ends");
		return "ehealth-index/preventive_health_check-up";
	}

	// mother child tracking
	@GetMapping("/offering-mother-childTracking")
	public String motherChildTracking(Model model) {
		logger.info("Method : motherChildTracking starts");
		try {
			DropDownModel[] state = restTemplate.getForObject(env.getUserUrl() + "getStateLists-doctor",
					DropDownModel[].class);
			List<DropDownModel> stateList = Arrays.asList(state);
			model.addAttribute("stateList", stateList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : motherChildTracking ends");
		return "ehealth-index/mother_child_tracking";
	}

	// Health Info Exchange
	@GetMapping("/offering-healthInformation")
	public String healthInfo(Model model) {
		logger.info("Method : healthInfo starts");
		try {
			DropDownModel[] state = restTemplate.getForObject(env.getUserUrl() + "getStateLists-doctor",
					DropDownModel[].class);
			List<DropDownModel> stateList = Arrays.asList(state);
			model.addAttribute("stateList", stateList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : healthInfo ends");
		return "ehealth-index/hie";
	}

	// covid19
	@GetMapping("/offering-covid19")
	public String covid19(Model model) {
		logger.info("Method : covid19 starts");
		try {
			DropDownModel[] state = restTemplate.getForObject(env.getUserUrl() + "getStateLists-doctor",
					DropDownModel[].class);
			List<DropDownModel> stateList = Arrays.asList(state);
			model.addAttribute("stateList", stateList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : covid19 ends");
		return "ehealth-index/covid-19";
	}

	// advance Stem Cell
	@GetMapping("/offering-advanceStemCell")
	public String advanceStemCell(Model model) {
		logger.info("Method : advanceStemCell starts");
		try {
			DropDownModel[] state = restTemplate.getForObject(env.getUserUrl() + "getStateLists-doctor",
					DropDownModel[].class);
			List<DropDownModel> stateList = Arrays.asList(state);
			model.addAttribute("stateList", stateList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : advanceStemCell ends");
		return "ehealth-index/advance_stem_cell";
	}

	// single identity digital smart card
	@GetMapping("/offering-singleIdentity-digitalSmartCard")
	public String singleIdentity(Model model) {
		logger.info("Method : singleIdentity starts");
		try {
			DropDownModel[] state = restTemplate.getForObject(env.getUserUrl() + "getStateLists-doctor",
					DropDownModel[].class);
			List<DropDownModel> stateList = Arrays.asList(state);
			model.addAttribute("stateList", stateList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : singleIdentity ends");
		return "ehealth-index/single_identity_digital_smart_card";
	}

	// Health In formatics Network Value Chain
	@GetMapping("/offering-hivc")
	public String hivc(Model model) {
		logger.info("Method : hivc starts");
		try {
			DropDownModel[] state = restTemplate.getForObject(env.getUserUrl() + "getStateLists-doctor",
					DropDownModel[].class);
			List<DropDownModel> stateList = Arrays.asList(state);
			model.addAttribute("stateList", stateList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : hivc ends");
		return "ehealth-index/hivc";
	}

	// Health Data Management Solutions
	@GetMapping("/offering-hdms")
	public String hdms(Model model) {
		logger.info("Method : hdms starts");
		try {
			DropDownModel[] state = restTemplate.getForObject(env.getUserUrl() + "getStateLists-doctor",
					DropDownModel[].class);
			List<DropDownModel> stateList = Arrays.asList(state);
			model.addAttribute("stateList", stateList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : hdms ends");
		return "ehealth-index/hdms";
	}

	// Leadership
	@GetMapping("/leadership")
	public String leadership(Model model) {
		logger.info("Method : aboutus starts");
		try {
			DropDownModel[] state = restTemplate.getForObject(env.getUserUrl() + "getStateLists-doctor",
					DropDownModel[].class);
			List<DropDownModel> stateList = Arrays.asList(state);
			model.addAttribute("stateList", stateList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : aboutus ends");
		return "ehealth-index/leadership";
	}

	// What We Offer
	@GetMapping("/what-we-offer")
	public String offer(Model model) {
		logger.info("Method : aboutus starts");
		try {
			DropDownModel[] state = restTemplate.getForObject(env.getUserUrl() + "getStateLists-doctor",
					DropDownModel[].class);
			List<DropDownModel> stateList = Arrays.asList(state);
			model.addAttribute("stateList", stateList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : aboutus ends");
		return "ehealth-index/whatweoffer";
	}

	// FAQ
	@GetMapping("/faq")
	public String faq(Model model) {
		logger.info("Method : faq starts");
		try {
			DropDownModel[] state = restTemplate.getForObject(env.getUserUrl() + "getStateLists-doctor",
					DropDownModel[].class);
			List<DropDownModel> stateList = Arrays.asList(state);
			model.addAttribute("stateList", stateList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : faq ends");
		return "ehealth-index/faq";
	}

	// News
	@GetMapping("/news")
	public String news(Model model) {
		logger.info("Method : news starts");
		try {
			DropDownModel[] state = restTemplate.getForObject(env.getUserUrl() + "getStateLists-doctor",
					DropDownModel[].class);
			List<DropDownModel> stateList = Arrays.asList(state);
			model.addAttribute("stateList", stateList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : news ends");
		return "ehealth-index/news";
	}

	// contact us
	@GetMapping("/contact-us")
	public String contactus(Model model) {
		logger.info("Method : contactus starts");
		try {
			DropDownModel[] state = restTemplate.getForObject(env.getUserUrl() + "getStateLists-doctor",
					DropDownModel[].class);
			List<DropDownModel> stateList = Arrays.asList(state);
			model.addAttribute("stateList", stateList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : contactus ends");
		return "ehealth-index/contactus";
	}

	/**
	 * Function for Forgot Password page
	 *
	 */
	@GetMapping("/forgot-password")
	public String forgotePasword(Model model) {
		logger.info("Method : forgotePasword starts");

		logger.info("Method : forgotePasword ends");
		return "ehealthsystemForgotPwd";
	}

	/**
	 * Function for welcome page
	 *
	 */
	@GetMapping("/address-wise-registration")
	public String welcomeregd(Model model, String name, @RequestParam("country") String countryId,
			@RequestParam("state") String stateId, @RequestParam("stateName") String StateName,
			@RequestParam("logo") String logo, @RequestParam("cm") String cm, HttpSession session) {
		// public String welcome(Model model) {
		logger.info("Method : / starts");

		try {
			DropDownModel[] role = restTemplate.getForObject(env.getUserUrl() + "getroleUserLists",
					DropDownModel[].class);
			List<DropDownModel> roleUserList = Arrays.asList(role);
			model.addAttribute("roleUserList", roleUserList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		model.addAttribute("countryId", countryId);
		model.addAttribute("stateId", stateId);
		model.addAttribute("stateName", StateName);
		model.addAttribute("logo", logo);
		model.addAttribute("cm", cm);

		logger.info("Method : / ends");
		return "ehealthsystemindex";
	}

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/get-district-list" })
	public @ResponseBody JsonResponse<Object> getDistrictLists(@RequestParam Integer state) {
		logger.info("Method : getDistrictLists starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-getDistrictList?id=" + state, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getDistrictLists ends");
		return res;

	}
	// dropdown for City list

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/get-city-list" })
	public @ResponseBody JsonResponse<Object> getCityLists(@RequestParam String districtid) {
		logger.info("Method : getCityLists starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-getCityLists?dist=" + districtid,
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
		logger.info("Method : getCityLists ends");
		return res;

	}

	/**
	 * Function to show register user form
	 *
	 */
	/*
	 * @GetMapping("register") public String addUser(Model model, HttpSession
	 * session) { logger.info("Method : register starts");
	 * 
	 * User user = new User();
	 * 
	 * User form = (User) session.getAttribute("suser");
	 * 
	 * String message = (String) session.getAttribute("message"); if (message !=
	 * null && message != "") { model.addAttribute("message", message); }
	 * 
	 * session.setAttribute("message", "");
	 * 
	 * if (form != null) { form.setUserPassword(null); model.addAttribute("user",
	 * form); session.setAttribute("suser", null); } else {
	 * model.addAttribute("user", user); }
	 * 
	 * logger.info("Method : register ends"); return "register"; }
	 */
	/**
	 * Function to show register user form for ehealthsystem
	 *
	 */
	@GetMapping("/register")
	public String addUserEhealth(Model model, @RequestParam("role") String role, @RequestParam("name") String name,
			@RequestParam("country") String countryId, @RequestParam("state") String stateId,
			@RequestParam("dist") String distId, @RequestParam("city") String cityId, HttpSession session) {
		// public String addUserEhealth(Model model, HttpSession session) {
		logger.info("Method : user starts");

		// drop down for Title list
		try {
			DropDownModel[] title = restTemplate.getForObject(env.getUserUrl() + "geTitleLists", DropDownModel[].class);
			List<DropDownModel> tittleList = Arrays.asList(title);
			model.addAttribute("tittleList", tittleList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		// drop down for country list
		try {
			DropDownModel[] country = restTemplate.getForObject(env.getUserUrl() + "getCountryLists",
					DropDownModel[].class);
			List<DropDownModel> countryList = Arrays.asList(country);
			model.addAttribute("countryList", countryList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		// drop down for Blood Group list
		try {
			DropDownModel[] blood = restTemplate.getForObject(env.getUserUrl() + "getBloodGrpLists",
					DropDownModel[].class);
			List<DropDownModel> bloodGrpList = Arrays.asList(blood);
			model.addAttribute("bloodGrpList", bloodGrpList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		// drop down for Gender list
		try {
			DropDownModel[] gender = restTemplate.getForObject(env.getUserUrl() + "getGenderLists",
					DropDownModel[].class);
			List<DropDownModel> genderList = Arrays.asList(gender);
			model.addAttribute("genderList", genderList);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		// drop down for Organisation Type
		try {
			DropDownModel[] organisation = restTemplate.getForObject(env.getUserUrl() + "getOrganisationTypeListLists",
					DropDownModel[].class);
			List<DropDownModel> organisationTypeList = Arrays.asList(organisation);
			model.addAttribute("organisationTypeList", organisationTypeList);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		User user = new User();

		User form = (User) session.getAttribute("suser");

		String message = (String) session.getAttribute("message");
		if (message != null && message != "") {
			model.addAttribute("message", message);
		}

		session.setAttribute("message", "");

		if (form != null) {
			form.setUserPassword(null);
			model.addAttribute("user", form);
			session.setAttribute("suser", null);
		} else {
			model.addAttribute("user", user);
		}

		model.addAttribute("roleId", role);
		model.addAttribute("roleName", name);
		model.addAttribute("countryId", countryId);
		model.addAttribute("stateId", stateId);
		model.addAttribute("distId", distId);
		model.addAttribute("cityId", cityId);

		logger.info("Method : user ends");
		return "user/registrationFormUser";
	}

	// dropdown for Speciality list
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "/user-getSpecialityList" })
	public @ResponseBody JsonResponse<Object> getSpecialityList(@RequestParam Integer role) {
		logger.info("Method : getSpecialityList starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-getSpecialityList?id=" + role, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getSpecialityList ends");
		return res;
	}
	// dropdown for ORG City list

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "register-getCityList-org" })
	public @ResponseBody JsonResponse<Object> getCityListorgs(@RequestParam String dist) {
		logger.info("Method : getCityList org starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-getCityList-org?dist=" + dist, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getCityList org ends");
		return res;

	}
	// dropdown for state list on Organisation

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/register-getStateList-org" })
	public @ResponseBody JsonResponse<Object> getStateLists(@RequestParam Integer country) {
		logger.info("Method : getStateList starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-getStateList-org?id=" + country,
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
		logger.info("Method : getStateList ends");
		return res;

	}

	// dropdown for ORG District list

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "register-getDistList-org" })
	public @ResponseBody JsonResponse<Object> getDistLists(@RequestParam String state) {
		logger.info("Method : getDistLists starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-getDistList-org?state=" + state,
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
		logger.info("Method : getDistLists ends");
		return res;

	}

	/*
	 * ADD FOR ORGANISATION MODAL
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/register-organisation-add")
	public @ResponseBody JsonResponse<Object> addOrganisation(Model model, HttpSession session,

			@RequestBody UserRegistrationModel userRegistrationModel) {

		logger.info("Method : addOrganisation starts");

		MultipartFile inputFile = (MultipartFile) session.getAttribute("quotationPFile");
		byte[] bytes;
		String imageName1 = null;

		if (inputFile != null) {
			try {
				bytes = inputFile.getBytes();
				String[] fileType = inputFile.getContentType().split("/");
				imageName1 = saveAllImages(bytes, fileType[1]);

				userRegistrationModel.setOrgdocName(imageName1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			resp = restTemplate.postForObject(env.getUserUrl() + "rest-addOrganisation", userRegistrationModel,
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

		logger.info("Method : addOrganisation ends");
		return resp;
	}

	// Upload image
	public String saveAllImages(byte[] imageBytes, String ext) {
		logger.info("Method : saveAllImage starts");

		String imageName1 = null;

		try {

			if (imageBytes != null) {
				long nowTime = new Date().getTime();
				if (ext.contentEquals("jpeg")) {
					imageName1 = nowTime + ".jpg";
				} else {
					imageName1 = nowTime + "." + ext;
				}

			}

			Path path = Paths.get(env.getFileUploadEmployee() + imageName1);
			if (imageBytes != null) {
				Files.write(path, imageBytes);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : saveAllImage ends");
		return imageName1;
	}
	/*
	 * function for DM dashboard
	 */

	@GetMapping("/dmdashboard")
	public String dmDashboard(Model model, HttpSession session) {
		logger.info("Method : user starts");

		String userId1 = (String) session.getAttribute("USER_ID");
		try {
			DmDashboardModel[] region = restTemplate.getForObject(env.getUserUrl() + "dmDashboardGetIdd?id=" + userId1,
					DmDashboardModel[].class);
			List<DmDashboardModel> dmDashboardGetIdd = Arrays.asList(region);
			model.addAttribute("dmDashboardGetIdd", dmDashboardGetIdd);
		} catch (RestClientException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Method : user ends");
		return "user/dmDashboard";
	}

	@GetMapping("/DM/dashboard-details")
	public String testReport(Model model, HttpSession session) {
		logger.info("Method :test Registration  starts");

		logger.info("Method : test Registration ends");
		return "user/dmDashboardTotalRegd";
	}

	/*
	 * view dm dashboard count
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("dmdashboard-view-count")
	public @ResponseBody JsonResponse<DmDashboardModel> viewdmDashboardCount(Model model, HttpSession session,
			@RequestParam String countryId, @RequestParam String stateId, @RequestParam String districtId) {

		logger.info("Method : viewdmDashboardCount starts");

		JsonResponse<DmDashboardModel> jsonResponse = new JsonResponse<DmDashboardModel>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			jsonResponse = restTemplate.getForObject(env.getUserUrl() + "viewdmDashboardCount?countryId=" + countryId
					+ "&stateId=" + stateId + "&districtId=" + districtId, JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();
			DmDashboardModel customerNewModel = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<DmDashboardModel>() {
					});
			jsonResponse.setBody(customerNewModel);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}

		logger.info("Method : viewdmDashboardCount ends");

		return jsonResponse;
	}

	/*
	 * view dm dashboard view total registration(Ag grid)
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("dmdashboard-view-regd")
	public @ResponseBody List<DmDashboardModel> viewdmDashboardTotalRegd(Model model, HttpSession session,
			@RequestParam String countryId, @RequestParam String stateId, @RequestParam String districtId,
			@RequestParam String typeId) {

		logger.info("Method : viewdmDashboardTotalRegd starts");

		JsonResponse<List<DmDashboardModel>> patientDetails = new JsonResponse<List<DmDashboardModel>>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			patientDetails = restTemplate.getForObject(env.getUserUrl() + "dmDashboardViewRegd?countryId=" + countryId
					+ "&stateId=" + stateId + "&districtId=" + districtId + "&typeId=" + typeId, JsonResponse.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : viewdmDashboardTotalRegd ends");

		return patientDetails.getBody();
	}

	/*
	 * view dm dashboard age wise total test
	 */
	@SuppressWarnings("unchecked")

	@GetMapping("dmdashboard-view-ageWiseTotalTest")
	public @ResponseBody JsonResponse<DmDashboardModel> viewdmDashboardAgeWiseTotalTest(Model model,
			HttpSession session, @RequestParam String countryId, @RequestParam String stateId,
			@RequestParam String districtId) {

		logger.info("Method : viewdmDashboardAgeWiseTotalTest starts");

		JsonResponse<DmDashboardModel> jsonResponse = new JsonResponse<DmDashboardModel>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			jsonResponse = restTemplate
					.getForObject(env.getUserUrl() + "rest-viewdmDashboardAgeWiseTotalTest?countryId=" + countryId
							+ "&stateId=" + stateId + "&districtId=" + districtId, JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();
			DmDashboardModel customerNewModel = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<DmDashboardModel>() {
					});
			jsonResponse.setBody(customerNewModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}
		logger.info("Method : viewdmDashboardAgeWiseTotalTest ends");
		return jsonResponse;
	}

	/*
	 * view dm dashboard area wise total test
	 */
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/dmdashboard-view-areaWiseTotalTest" })
	public @ResponseBody JsonResponse<Object> viewdmDashboardAreaWiseTotalTest(@RequestParam String countryId,
			@RequestParam String stateId, @RequestParam String districtId) {
		logger.info("Method : viewdmDashboardAreaWiseTotalTest starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-viewdmDashboardAreaWiseTotalTest?countryId="
					+ countryId + "&stateId=" + stateId + "&districtId=" + districtId, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		// System.out.println("area wise=========="+res);
		logger.info("Method : viewdmDashboardAreaWiseTotalTest ends");
		return res;
	}

	/*
	 * view dm dashboard disease wise total abnormal
	 */
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/dmdashboard-view-diseaseWiseTotalAbnormal" })
	public @ResponseBody JsonResponse<Object> viewdmDashboardDiseaseWiseTotalAbnormal(@RequestParam String countryId,
			@RequestParam String stateId, @RequestParam String districtId) {
		logger.info("Method : viewdmDashboardDiseaseWiseTotalAbnormal starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-viewdmDashboardDiseaseWiseTotalAbnormal?countryId="
					+ countryId + "&stateId=" + stateId + "&districtId=" + districtId, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		// System.out.println("Abnormal =========="+res);
		logger.info("Method : viewdmDashboardDiseaseWiseTotalAbnormal ends");
		return res;
	}

	/*
	 * view dm dashboard disease wise total highrisk
	 */
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/dmdashboard-view-diseaseWiseTotalHighRisk" })
	public @ResponseBody JsonResponse<Object> viewdmDashboardDiseaseWiseTotalHighRisk(@RequestParam String countryId,
			@RequestParam String stateId, @RequestParam String districtId) {
		logger.info("Method : viewdmDashboardDiseaseWiseTotalHighRisk starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-viewdmDashboardDiseaseWiseTotalHighRisk?countryId="
					+ countryId + "&stateId=" + stateId + "&districtId=" + districtId, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		// System.out.println("High Risk =========="+res);
		logger.info("Method : viewdmDashboardDiseaseWiseTotalHighRisk ends");
		return res;
	}

	//////////////////////////////////////////////////////////////// web controller
	//////////////////////////////////////////////////////////////// /////////////////////////////////////////////////////////////////////////
	/*************************************************
	 * function to get pie chart for male and female in dm dashboard
	 */

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/dmdashboard-health-check-up-male-female" })
	public @ResponseBody JsonResponse<Object> viewdmDashboardMaleFemale(@RequestParam String countryId,
			@RequestParam String stateId, @RequestParam String districtId) {
		logger.info("Method : viewdmDashboardMaleFemale starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-viewdmDashboardMaleFemale?countryId=" + countryId
					+ "&stateId=" + stateId + "&districtId=" + districtId, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("Success");
		}
		logger.info("Method : viewdmDashboardMaleFemale ends");
		return res;

	}

	/*************************************************
	 * function to get pie chart for normal and abnormal in dm dashboard
	 */
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/dmdashboard-health-check-up-normal-abNormal" })
	public @ResponseBody JsonResponse<Object> viewdmDashboardNormalAbNormal(@RequestParam String countryId,
			@RequestParam String stateId, @RequestParam String districtId) {
		logger.info("Method : viewdmDashboardNormalAbNormal starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-viewdmDashboardNormalAbNormal?countryId="
					+ countryId + "&stateId=" + stateId + "&districtId=" + districtId, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("Success");
		}
		logger.info("Method : viewdmDashboardNormalAbNormal ends");
		return res;

	}

	/*************************************************
	 * function to get pie chart for normal and abnormal in dm dashboard
	 */
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/dmdashboard-health-check-up-disease-area-wise" })
	public @ResponseBody JsonResponse<Object> viewdmDashboardAreaWiseCheckUp(@RequestParam String countryId,
			@RequestParam String stateId, @RequestParam String districtId) {
		logger.info("Method : viewdmDashboardAreaWiseCheckUp starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-viewdmDashboardAreaWiseCheckUp?countryId="
					+ countryId + "&stateId=" + stateId + "&districtId=" + districtId, JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();
			List<DropDownModel> dataList = mapper.convertValue(res.getBody(), new TypeReference<List<DropDownModel>>() {
			});

			Double sum = 0.0;
			for (DropDownModel m : dataList) {
				sum = sum + Double.parseDouble(m.getKey());
			}
			for (DropDownModel m : dataList) {
				Double key = Double.parseDouble(m.getKey());
				Double percent = (key / sum) * 100;
				m.setCode(percent.toString());
			}
			res.setBody(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("Success");
		}
		logger.info("Method : viewdmDashboardAreaWiseCheckUp ends");
		return res;

	}

	/*************************************************
	 * function to get pie chart for Normal Area Wise in dm dashboard
	 */
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/dmdashboard-health-check-up-area-wise-normal" })
	public @ResponseBody JsonResponse<Object> viewdmDashboardAreaWiseNormalCheckUp(@RequestParam String countryId,
			@RequestParam String stateId, @RequestParam String districtId) {
		logger.info("Method : viewdmDashboardAreaWiseNormalCheckUp starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-viewdmDashboardAreaWiseNormalCheckUp?countryId="
					+ countryId + "&stateId=" + stateId + "&districtId=" + districtId, JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();
			List<DropDownModel> dataList = mapper.convertValue(res.getBody(), new TypeReference<List<DropDownModel>>() {
			});

			Double sum = 0.0;
			for (DropDownModel m : dataList) {
				sum = sum + Double.parseDouble(m.getKey());
			}
			for (DropDownModel m : dataList) {
				Double key = Double.parseDouble(m.getKey());
				Double percent = (key / sum) * 100;
				m.setCode(percent.toString());
			}
			res.setBody(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("Success");
		}
		logger.info("Method : viewdmDashboardAreaWiseNormalCheckUp ends");
		return res;

	}

	/*************************************************
	 * function to get pie chart for High Risk Area Wise in dm dashboard
	 */
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/dmdashboard-health-check-up-area-wise-highrisk" })
	public @ResponseBody JsonResponse<Object> viewdmDashboardAreaWiseHighriskCheckUp(@RequestParam String countryId,
			@RequestParam String stateId, @RequestParam String districtId) {
		logger.info("Method : viewdmDashboardAreaWiseHighriskCheckUp starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-viewdmDashboardAreaWiseHighriskCheckUp?countryId="
					+ countryId + "&stateId=" + stateId + "&districtId=" + districtId, JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();
			List<DropDownModel> dataList = mapper.convertValue(res.getBody(), new TypeReference<List<DropDownModel>>() {
			});

			Double sum = 0.0;
			for (DropDownModel m : dataList) {
				sum = sum + Double.parseDouble(m.getKey());
			}
			for (DropDownModel m : dataList) {
				Double key = Double.parseDouble(m.getKey());
				Double percent = (key / sum) * 100;
				m.setCode(percent.toString());
			}
			res.setBody(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("Success");
		}
		logger.info("Method : viewdmDashboardAreaWiseHighriskCheckUp ends");
		return res;
	}

	/************************************************
	 * function to get pie chart for normal and abnormal in dm dashboard /
	 */
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/dmdashboard-health-check-up-gender-wise-disease-details" })
	public @ResponseBody JsonResponse<Object> genderWiseDiseaseCount(@RequestParam String countryId,
			@RequestParam String stateId, @RequestParam String districtId) {
		logger.info("Method : genderWiseDiseaseCount starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-genderWiseDiseaseCount?countryId=" + countryId
					+ "&stateId=" + stateId + "&districtId=" + districtId, JsonResponse.class);
			ObjectMapper mapper = new ObjectMapper();
			List<DropDownModel> dataList = mapper.convertValue(res.getBody(), new TypeReference<List<DropDownModel>>() {
			});
			res.setBody(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("Success");
		}
		logger.info("Method : genderWiseDiseaseCount ends");
		return res;

	}

	/*
	 *
	 * PM Dashboard
	 *
	 *
	 */

	/*
	 * function for PM dashboard
	 */

	@GetMapping("/pmdashboard")
	public String pmDashboard(Model model, HttpSession session) {
		logger.info("Method : pmDashboard starts");

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

		logger.info("Method : pmDashboard ends");
		return "user/pmDashboard";
	}

	/*
	 * View Pm Dashboard Count
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("/pmdashboard-view-count")
	public @ResponseBody JsonResponse<PMDashboardModel> viewpmDashboardCount(Model model, HttpSession session,
			@RequestParam String countryId) {

		logger.info("Method : viewpmDashboardCount starts");

		JsonResponse<PMDashboardModel> jsonResponse = new JsonResponse<PMDashboardModel>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			jsonResponse = restTemplate.getForObject(
					env.getUserUrl() + "rest-viewpmDashboardCount?countryId=" + countryId, JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();
			PMDashboardModel customerNewModel = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<PMDashboardModel>() {
					});
			jsonResponse.setBody(customerNewModel);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}

		logger.info("Method : viewpmDashboardCount ends");

		return jsonResponse;
	}

	// dropdown for state list

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/register-getStateList" })
	public @ResponseBody JsonResponse<Object> getStateList(@RequestParam Integer country) {
		logger.info("Method : getStateList starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-getStateList?id=" + country, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getStateList ends");
		return res;

	}
	// dropdown for District list

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "register-getDistList" })
	public @ResponseBody JsonResponse<Object> getDistList(@RequestParam String state) {
		logger.info("Method : getDistList starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-getDistList?state=" + state, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getDistList ends");
		return res;

	}
	// dropdown for City list

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "register-getCityList" })
	public @ResponseBody JsonResponse<Object> getCityList(@RequestParam String dist) {
		logger.info("Method : getCityList starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-getCityList?dist=" + dist, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getCityList ends");
		return res;

	}

	/*
	 * Organization Name autoSearch
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "register-autoSearch-organization" })
	public @ResponseBody JsonResponse<UserRegistrationModel> getOrganizationAutoSearchList(Model model,
			@RequestBody String searchValue, BindingResult result) {
		logger.info("Method : getOrganizationAutoSearchList starts");
		JsonResponse<UserRegistrationModel> res = new JsonResponse<UserRegistrationModel>();

		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-getOrganizationAutoSearchList?id=" + searchValue,
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
		logger.info("Method : getOrganizationAutoSearchList ends");
		return res;
	}

	/**
	 * Function to show plan card form for e-healthSystem
	 *
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("/register-planCard")
	public @ResponseBody JsonResponse<List<UserRegistrationModel>> getPlanCard(Model model, @RequestParam String id,
			HttpSession session) {

		logger.info("Method : getPlanCard starts");
		JsonResponse<List<UserRegistrationModel>> jsonResponse = new JsonResponse<List<UserRegistrationModel>>();
		try {
			jsonResponse = restTemplate.getForObject(env.getUserUrl() + "rest-getPlanCard?id=" + id,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		List<UserRegistrationModel> type = mapper.convertValue(jsonResponse.getBody(),
				new TypeReference<List<UserRegistrationModel>>() {
				});

		jsonResponse.setBody(type);
		String message = (String) session.getAttribute("message");

		if (message != null && message != "") {
			model.addAttribute("message", message);
		}

		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}

		logger.info("Method : getPlanCard ends");
		return jsonResponse;
	}

	public static String generateRandom(int length) {
		Random random = new Random();
		char[] digits = new char[length];
		digits[0] = (char) (random.nextInt(9) + '1');
		for (int i = 1; i < length; i++) {
			digits[i] = (char) (random.nextInt(10) + '0');
		}
		return new String(digits);
	}

	/**
	 * Function to add registration user form for e-healthSystem
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/register-add-user")
	public @ResponseBody JsonResponse<Object> addUserRegistration(HttpSession session,
			@RequestBody UserRegistrationModel regdModel) {
		logger.info("Method : addUserRegistration starts");
		// int n = 8;
		// String pass = AutoPasswordGenerator.getAlphaNumericString(n);
		String pass = "$2a$10$SkYOZORZ4PUSURzL1fmvk.RcUwCfLk/R826sxBXKx/ZZyoQvkcaa6";
		JsonResponse<Object> resp = new JsonResponse<Object>();

		// QR CODE

		String file = null;
		BigInteger UId = regdModel.getPatientId();

		try {
			String ccode = null;
			String scode = null;
			if (regdModel.getCountryCode() != null) {

				ccode = regdModel.getCountryCode().toString();
				ccode = ccode.substring(0, 2);
				scode = regdModel.getStateCode().toString();
				scode = scode.substring(0, 2);
			} else {
				ccode = "10";
				scode = "50";
			}

			String randomNumber = generateRandom(12);
			String genCode = ccode.concat(scode).concat(randomNumber);

			BigInteger pId = new BigInteger(genCode);
			regdModel.setPatientId(pId);

			// QR CODE

			file = "QR" + pId + ".png";

			/*
			 * if (pass != null && pass != "") { pass = passwordEncoder.encode(pass);
			 * regdModel.setPassword(pass); }
			 */
			regdModel.setPassword(pass);

			// QR CODE

			regdModel.setqRFileName(file);

			MultipartFile inputFile = (MultipartFile) session.getAttribute("registerProfileImg");
			byte[] bytes;
			String imageName = null;

			if (inputFile != null) {
				try {
					bytes = inputFile.getBytes();
					String[] fileType = inputFile.getContentType().split("/");
					imageName = saveAllImage(bytes, fileType[1]);

					regdModel.setFileProfileimg(imageName);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			/*
			 * String imageName = ""; regdModel.setFileProfileimg(imageName);
			 */
			resp = restTemplate.postForObject(env.getUserUrl() + "rest-addUserRegistration", regdModel,
					JsonResponse.class);
			ObjectMapper mapper = new ObjectMapper();

			List<UserRegistrationModel> seat = mapper.convertValue(resp.getBody(),
					new TypeReference<List<UserRegistrationModel>>() {
					});

			resp.setBody(seat);
		} catch (Exception e) {

			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		}

		// QR CODE

		else {
			String msg = "Welcome to eHealthSystem. You are registered successfully! Your UserId is "
					+ regdModel.getPatientId() + " or " + regdModel.getMob() + " and password is User@123 ";

			String encodemsg = null;
			try {
				encodemsg = URLEncoder.encode(msg, "UTF-8");
			} catch (UnsupportedEncodingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			try {
				CommonUsed.sendSMS(regdModel.getMob(), encodemsg);
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			String qrdata = "UniqueId : " + regdModel.getPatientId() + "\nMobile : " + regdModel.getMob() + "\nName : "
					+ regdModel.getfName() + "  " + regdModel.getlName();

			try {
				String qrCodeData = qrdata;
				String filePath = env.getUserqrcodeUrl() + file;

				String charset = "UTF-8";// "ISO-8859-1";

				Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();

				hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
				BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),
						BarcodeFormat.QR_CODE, 200, 200, hintMap);
				MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1),
						new File(filePath));

				logger.info("Method : qrcode function Ends");

			} catch (Exception e) {
				System.err.println(e);
			}
			resp.setMessage("Success");

		}
		logger.info("Method : addUserRegistration ends");

		return resp;
	}

	// Upload image
	public String saveAllImage(byte[] imageBytes, String ext) {
		logger.info("Method : saveAllImage starts");

		String imageName = null;

		try {

			if (imageBytes != null) {
				long nowTime = new Date().getTime();
				if (ext.contentEquals("jpeg")) {
					imageName = nowTime + ".jpg";
				} else {
					imageName = nowTime + "." + ext;
				}

			}

			Path path = Paths.get(env.getFileUploadEmployee() + imageName);
			if (imageBytes != null) {
				Files.write(path, imageBytes);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : saveAllImage ends");
		return imageName;
	}

	/**
	 * Function to add registration Prof form for e-healthSystem
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/register-add-Professional")
	public @ResponseBody JsonResponse<Object> addProfRegistration(HttpSession session,
			@RequestBody UserRegistrationModel regdModel) {
		logger.info("Method : addProfRegistration starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		MultipartFile inputFile = (MultipartFile) session.getAttribute("registerProfileImg");
		MultipartFile inputFile1 = (MultipartFile) session.getAttribute("registerFile");
		byte[] bytes;
		String imageName = null;

		if (inputFile != null) {
			try {
				bytes = inputFile.getBytes();
				String[] fileType = inputFile.getContentType().split("/");
				imageName = saveAllImage(bytes, fileType[1]);

				regdModel.setFileProfileimg(imageName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (inputFile1 != null) {
			try {
				bytes = inputFile1.getBytes();
				String[] fileType = inputFile1.getContentType().split("/");
				imageName = saveAllImage(bytes, fileType[1]);

				regdModel.setFileUpload(imageName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		String jType = regdModel.getJobType();
		if (jType == "") {
			regdModel.setJobType(null);
		} else {
			regdModel.setJobType(jType);
		}
		try {
			String pass = "$2a$10$SkYOZORZ4PUSURzL1fmvk.RcUwCfLk/R826sxBXKx/ZZyoQvkcaa6";
			regdModel.setPassword(pass);

			resp = restTemplate.postForObject(env.getUserUrl() + "rest-addProfRegistration", regdModel,
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			List<UserRegistrationModel> seat = mapper.convertValue(resp.getBody(),
					new TypeReference<List<UserRegistrationModel>>() {
					});

			resp.setBody(seat);
		} catch (Exception e) {

			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			session.removeAttribute("registerProfileImg");
			session.removeAttribute("registerFile");
			resp.setMessage("Success");
		}
		logger.info("Method : addProfRegistration ends");

		return resp;
	}

	/************************** profile picture upload ***************************/
	@PostMapping("/register-add-details-upload-profileImage")
	public @ResponseBody JsonResponse<Object> uploadProfileImg(@RequestParam("file") MultipartFile inputFile,
			HttpSession session) {
		logger.info("Method : user uploadimage controller  starts");

		JsonResponse<Object> response = new JsonResponse<Object>();

		try {
			response.setMessage(inputFile.getOriginalFilename());
			session.setAttribute("registerProfileImg", inputFile);

		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : user uploadimage controller ' ends");
		return response;
	}

	@PostMapping("/register-add-details-delete-profileImage")
	public @ResponseBody JsonResponse<Object> deleteFile(HttpSession session) {
		logger.info("Method : deleteFile register uploadimage controller starts");

		JsonResponse<Object> response = new JsonResponse<Object>();

		try {
			session.removeAttribute("registerProfileImg");
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : deleteFile register uploadimage controller ends");
		return response;
	}

	@PostMapping("/register-add-details-upload-file")
	public @ResponseBody JsonResponse<Object> uploadFile(@RequestParam("file") MultipartFile inputFile1,
			HttpSession session) {
		logger.info("Method : user file controller  starts");

		JsonResponse<Object> response = new JsonResponse<Object>();

		try {
			response.setMessage(inputFile1.getOriginalFilename());
			session.setAttribute("registerFile", inputFile1);

		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : user File controller ' ends");
		return response;
	}

	/**
	 * Function show login form
	 *
	 */
	@GetMapping("/login")
	public String login(Model model, HttpSession session) {
		logger.info("Method : login starts");

		String message = (String) session.getAttribute("loginMessage");

		if (message != null && message != "") {
			model.addAttribute("loginMessage", message);
			session.setAttribute("loginMessage", null);
		}

		logger.info("Method : login starts");
		// return "app_index";
		// return "nerp-login";
		// return "nerp_home";
		return "ehealthsystemlogin";
	}

	/**
	 * Function show index page after login
	 *
	 */

	@GetMapping("access-denied")
	public String accessDenied(Model model, HttpSession session) {
		logger.info("Method : access-denied starts");

		logger.info("Method : access-denied ends");
		return "accessDenied";
	}

	/**
	 * Function to logout user
	 *
	 */
	@GetMapping("logout")
	public String logout(Model model, HttpSession session) {
		logger.info("Method : access-denied Starts");

		session.invalidate();

		logger.info("Method : access-denied ends");
		return "redirect:";
	}

	/**
	 * Function to post register user form
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("addUser")
	public String addUserForm(@ModelAttribute User user, Model model, HttpSession session) {
		logger.info("Method POST : addUser starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		try {
			String enc = user.getUserPassword();
			if (enc != null && enc != "") {
				enc = passwordEncoder.encode(enc);
				user.setUserPassword(enc);
			}

			jsonResponse = restTemplate.postForObject(env.getUserUrl() + "registerUser", user, JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (jsonResponse.getMessage() != "") {
			session.setAttribute("message", jsonResponse.getMessage());
			session.setAttribute("suser", user);
			return "redirect:register";
		}

		logger.info("Method POST : addUser ends");
		return "redirect:login";
	}

	/**
	 * for dashboard index page
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/index")
	public String index(Model model, HttpSession session) {
		logger.info("Method : index starts");

		String dashboard = (String) session.getAttribute("DASHBOARD");

		logger.info("Method : index  extend-index ends");
		// return dashboard;
		return "new-index";
		// return "extend-index";
	}

	// @SuppressWarnings("unchecked")
	// @PostMapping(value = { "index-sales-report" })
	// public @ResponseBody JsonResponse<MapModel1> getSalesReportGraph(Model model)
	// {
	// logger.info("Method : getSalesReportGraph starts");
	// JsonResponse<MapModel1> res = new JsonResponse<MapModel1>();
	//
	// try {
	// res = restTemplate.getForObject(env.getRestaurantUrl() + "dbSalesReport",
	// JsonResponse.class);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// if (res.getMessage() != null) {
	// res.setCode(res.getMessage());
	// res.setMessage("Unsuccess");
	// } else {
	// res.setMessage("success");
	// }
	// logger.info("Method : getSalesReportGraph ends");
	//
	// return res;
	// }
	//
	// @SuppressWarnings("unchecked")
	// @PostMapping(value = { "index-order-report" })
	// public @ResponseBody JsonResponse<MapModel1> getOrderReportGraph(Model model)
	// {
	// logger.info("Method : getOrderReportGraph starts");
	// JsonResponse<MapModel1> res = new JsonResponse<MapModel1>();
	//
	// try {
	// res = restTemplate.getForObject(env.getRestaurantUrl() + "dbOrderReport",
	// JsonResponse.class);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// if (res.getMessage() != null) {
	// res.setCode(res.getMessage());
	// res.setMessage("Unsuccess");
	// } else {
	// res.setMessage("success");
	// }
	// logger.info("Method : getOrderReportGraph ends");
	//
	// return res;
	// }

	// for order status page

	// @GetMapping("/order-status")
	// public String dashboard(Model model) {
	// logger.info("Method : /dashboard starts");
	//
	// try {
	// OrderStatusModel[] order = restTemplate.getForObject(env.getRestaurantUrl() +
	// "getOrderStatus",
	// OrderStatusModel[].class);
	// List<OrderStatusModel> orderList1 = Arrays.asList(order);
	//
	// model.addAttribute("orderList1", orderList1);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// try {
	// OrderStatusModel[] order2 = restTemplate.getForObject(env.getRestaurantUrl()
	// + "getOrderStatusReady",
	// OrderStatusModel[].class);
	// List<OrderStatusModel> orderList2 = Arrays.asList(order2);
	//
	// model.addAttribute("orderList2", orderList2);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// try {
	// OrderStatusModel[] discount =
	// restTemplate.getForObject(env.getRestaurantUrl() + "getDiscountDetails",
	// OrderStatusModel[].class);
	// List<OrderStatusModel> discountList = Arrays.asList(discount);
	//
	// for (int i = 0; i < discountList.size(); i++) {
	// if (i % 4 == 0) {
	// discountList.get(i).setStatus("bg1.jpg");
	// discountList.get(i).setDiscountImage("offer.png");
	// } else if (i % 4 == 1) {
	// discountList.get(i).setStatus("bg2.jpg");
	// discountList.get(i).setDiscountImage("offer2.png");
	// } else if (i % 4 == 2) {
	// discountList.get(i).setStatus("bg3.jpg");
	// discountList.get(i).setDiscountImage("offer3.png");
	// } else {
	// discountList.get(i).setStatus("bg4.jpg");
	// discountList.get(i).setDiscountImage("offer4.png");
	// }
	// }
	//
	// model.addAttribute("discountList", discountList);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// logger.info("Method : /dashboard ends");
	// return "dashboard";
	// // return "app_index";
	// }

	/**
	 * Web Controller for view all assigned kitchen to restaurant
	 *
	 */
	// @SuppressWarnings("unchecked")
	// @GetMapping("/restaurant/kitchen-staff-order-details")
	// public String getKitchenStaffOrderDetails(@RequestParam String id, Model
	// model) {
	//
	// logger.info("Method : getKitchenStaffOrderDetails starts");
	//
	// DataTableRequest tableRequest = new DataTableRequest();
	//
	// try {
	// // String UserId = (String) session.getAttribute("USER_ID");
	//
	// tableRequest.setParam1(id);
	// // tableRequest.setUserId(UserId);
	//
	// JsonResponse<List<KitchenStaffFoodOrderListModel>> jsonResponse = new
	// JsonResponse<List<KitchenStaffFoodOrderListModel>>();
	//
	// jsonResponse = restTemplate.postForObject(env.getKitchenUrl() +
	// "getFoodListForView", tableRequest,
	// JsonResponse.class);
	//
	// ObjectMapper mapper = new ObjectMapper();
	//
	// List<KitchenStaffFoodOrderListModel> assignTS =
	// mapper.convertValue(jsonResponse.getBody(),
	// new TypeReference<List<KitchenStaffFoodOrderListModel>>() {
	// });
	//
	// String s = "";
	//
	// for (KitchenStaffFoodOrderListModel m : assignTS) {
	//
	// byte[] pId = Base64.getEncoder().encode(m.getFoodOrderId().getBytes());
	//
	// /*
	// * m.
	// * setFoodOrderId("<a data-toggle='modal' title='View' data-target='#myModal1'
	// href='javascript:void(0)' onclick='viewInModel(\""
	// * + new String(pId) + "\")'>"+m.getFoodOrderId()+"</a>");
	// */
	//
	// s = s + "<a href='javascript:void(0)'" + " onclick='printPDF(\"" + new
	// String(pId)
	// + "\")' ><i class=\"fa fa-download\" title=\"PDF\"
	// style=\"color:#d00c08;font-size:24px;\"></i></a>&nbsp;&nbsp;";
	//
	// if (m.getFoodPrepareStatus() == 1) {
	// s = s + "<a href='javascript:void(0)'" + " onclick='changePrepareStatus(\"" +
	// new String(pId)
	// + "\")' ><i class=\"fa fa-cutlery\" title=\"Receive\"
	// style=\"color:#e30f0f;font-size:24px;\"></i></a>&nbsp;&nbsp;";
	// } else {
	// if (m.getKitchenStatus() == 1) {
	//
	// s = s + "<a href='javascript:void(0)'" + " onclick='changeKitchenStatus(\"" +
	// new String(pId)
	// + "\")' ><i class=\"fa fa-times-circle\" title=\"In Progress\"
	// style=\"color:#e30f0f;font-size:24px;\"></i></a>&nbsp;&nbsp;";
	//
	// } else if (m.getKitchenStatus() == 2) {
	//
	// s = s + "<a href='javascript:void(0)'"
	// + "' onclick='' ><i class=\"fa fa-check-circle\" title=\"Ready To Delivered\"
	// style=\"color:#090;cursor:
	// context-menu;font-size:24px;\"></i></a>&nbsp;&nbsp;";
	// }
	// }
	//
	// m.setAction(s);
	// s = "";
	//
	// }
	// model.addAttribute("orderData", assignTS);
	// model.addAttribute("storeId", id);
	// JsonResponse<Object> res = new JsonResponse<Object>();
	//
	// try {
	// res = restTemplate.getForObject(env.getKitchenUrl() + "getOrderSummary?id=" +
	// id, JsonResponse.class);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// ObjectMapper mapper1 = new ObjectMapper();
	//
	// List<KitchenItemDetailsModel> itemSummery =
	// mapper1.convertValue(res.getBody(),
	// new TypeReference<List<KitchenItemDetailsModel>>() {
	// });
	// model.addAttribute("itemSummery", itemSummery);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// logger.info("Method : getKitchenStaffOrderDetails ends");
	// return "kitchen/gocool-get-kitchen-order-status";
	// }

	/**
	 * Web Controller - Get details For Modal
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/restaurant/kitchen-staff-order-details-modal" })
	public @ResponseBody JsonResponse<Object> modalQuotation(Model model, @RequestBody String index,
			BindingResult result) {

		logger.info("Method : summaryModal starts");
		JsonResponse<Object> res = new JsonResponse<Object>();

		try {
			res = restTemplate.getForObject(env.getKitchenUrl() + "getOrderSummary?id=" + index, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (res.getMessage() != null) {

			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}

		logger.info("Method : summaryModal ends");
		return res;
	}

	/**
	 * for dashboard index page
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/index-get-function-list/{id}")
	public String getMenuDetails(Model model, HttpSession session, @PathVariable String id) {
		logger.info("Method : index starts");
		List<Module> module = new ArrayList<Module>();
		List<Function> funDetails = new ArrayList<Function>();
		String activityUrl = "";
		try {

			module = (List<Module>) session.getAttribute("MENU");
			if (module != null && module.size() > 0) {
				List<Module> fList = module.stream().filter(s -> s.getModuleId().equals(id))
						.collect(Collectors.toList());
				if (!fList.isEmpty()) {
					funDetails = fList.get(0).getModule();
					for (Function a : funDetails) {
						if (a.getFunction() != null) {
							a.setDefaultUrl(a.getFunction().get(0).getActivity());
						}

					}
				}

				session.setAttribute("funList", funDetails);
				session.setAttribute("moduleId", id);
				if (!funDetails.isEmpty()) {
					activityUrl = funDetails.get(0).getFunction().get(0).getActivity();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : index  extend-index ends");

		// return "extend-index2";
		// return "redirect:view-my-dashboard";
		return "redirect:" + activityUrl;

	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = { "/index-get-activity-list" })
	public @ResponseBody JsonResponse<List<Activity>> getActivityList(@RequestParam String funId,
			@RequestParam String moduleId, HttpSession session) {
		logger.info("Method : getActivityList starts");

		JsonResponse<List<Activity>> res = new JsonResponse<List<Activity>>();
		List<Activity> activityList = new ArrayList<Activity>();

		try {
			List<Module> module = new ArrayList<Module>();
			try {

				module = (List<Module>) session.getAttribute("MENU");
				if (module != null && module.size() > 0) {
					List<Module> fList = module.stream().filter(s -> s.getModuleId().equals(moduleId))
							.collect(Collectors.toList());
					if (fList != null) {
						List<Function> funDetails = (List<Function>) fList.get(0).getModule().stream()
								.filter(a -> a.getFunctionId().equals(funId)).collect(Collectors.toList());
						activityList = funDetails.get(0).getFunction();
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			res.setBody(activityList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (res.getMessage() != null) {

			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getActivityList ends");
		return res;
	}

	/**
	 * for dashboard index page
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/index-get-function-list-resp")
	public @ResponseBody JsonResponse<List<Function>> getFunctionDetails(Model model, HttpSession session,
			@RequestParam String moduleId) {
		logger.info("Method : getFunctionDetails starts");

		JsonResponse<List<Function>> res = new JsonResponse<List<Function>>();
		List<Activity> activityList = new ArrayList<Activity>();

		List<Module> module = new ArrayList<Module>();
		List<Function> funDetails = new ArrayList<Function>();
		String activityUrl = "";
		try {

			module = (List<Module>) session.getAttribute("MENU");
			if (module != null && module.size() > 0) {
				List<Module> fList = module.stream().filter(s -> s.getModuleId().equals(moduleId))
						.collect(Collectors.toList());
				if (!fList.isEmpty()) {
					funDetails = fList.get(0).getModule();
					for (Function a : funDetails) {
						if (a.getFunction() != null) {
							a.setDefaultUrl(a.getFunction().get(0).getActivity());
							a.setDefaultUrlId(a.getFunction().get(0).getActivityId());
						}

					}
				}

				session.setAttribute("funList", funDetails);
				session.setAttribute("moduleId", moduleId);
				if (!funDetails.isEmpty()) {
					activityUrl = funDetails.get(0).getFunction().get(0).getActivity();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		res.setBody(funDetails);
		logger.info("Method : getFunctionDetails ends");
		// return "extend-index2";
		return res;

	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = { "/index-get-module-list" })
	public @ResponseBody JsonResponse<List<Module>> getModuleDetails(Model model, HttpSession session,
			@RequestParam String moduleId) {
		logger.info("Method : getModuleDetails starts");

		JsonResponse<List<Module>> res = new JsonResponse<List<Module>>();

		List<Module> module = (List<Module>) session.getAttribute("MENU");

		res.setBody(module);
		logger.info("Method : getModuleDetails ends");

		return res;

	}

	@SuppressWarnings("unchecked")
	@PostMapping("/index-get-avl-function-list")
	public @ResponseBody JsonResponse<ActivityAvlFunctionModel> getAvlFunctionByActivityRole(
			@RequestBody List<DropDownModel> data, HttpSession session) {
		logger.info("Method : updateModule starts");

		JsonResponse<ActivityAvlFunctionModel> resp = new JsonResponse<ActivityAvlFunctionModel>();

		try {
			resp = restTemplate.postForObject(env.getUserUrl() + "getAvlFunctionByActivityRole", data,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : getAvlFunctionByActivityRole starts");
		return resp;
	}

	/**
	 * Function to check connection
	 *
	 */
	@GetMapping("error")
	public String error(Model model, HttpSession session) {
		logger.info("Method : error starts");

		logger.info("Method : error ends");
		return "error";
	}

	/*
	 * Mobile API for EhealthCard
	 * 
	 */

	@GetMapping("download-ehealthcard")
	public String ehealthcardDetails(Model model, HttpSession session, @RequestParam String userid) {
		logger.info("Method : ehealthcardDetailsApi starts");
		System.out.println("cardDetailscardDetails" + userid);
		byte[] encodeByte = Base64.getDecoder().decode(userid.getBytes());
		String userId = (new String(encodeByte));
		System.out.println("cardDetailscardDetails" + userId);
		try {
			EHealthCardModel title = restTemplate
					.getForObject(env.getUserUrl() + "getEhealthCardInformation?id=" + userId, EHealthCardModel.class);
			EHealthCardModel cardDetails = title;
			System.out.println("cardDetailsapi" + cardDetails);
			model.addAttribute("cardDetails", cardDetails);

			ObjectMapper mapper = new ObjectMapper();
			EHealthCardModel eHealthCardDetails = mapper.convertValue(cardDetails,
					new TypeReference<EHealthCardModel>() {

					});
			String profilePath = null;
			String qRPath = null;
			// profilePath = env.getBaseURL() + "document/profile/" +
			// eHealthCardDetails.getProfileImg();
			// qRPath = env.getBaseURL() + "document/qrcode/" +
			// eHealthCardDetails.getQrCode();
			if (eHealthCardDetails.getProfileImg() == null || eHealthCardDetails.getProfileImg() == "") {
				profilePath = "../assets/eHealth/images/user_img.jpg";
			} else {
				profilePath = env.getBaseURL() + "document/profile/" + eHealthCardDetails.getProfileImg();
			}

			if (eHealthCardDetails.getQrCode() == null || eHealthCardDetails.getQrCode() == "") {
				qRPath = "../assets/eHealth/images/not_qrcode1.jpg";
			} else {
				qRPath = env.getBaseURL() + "document/qrcode/" + eHealthCardDetails.getQrCode();
			}

			model.addAttribute("profileimg", profilePath);
			model.addAttribute("qRCode", qRPath);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : ehealthcardDetailsApi ends");
		return "patient/patientidcard";
	}

	/****************************************************
	 * CM DASHBOARD
	 ****************************************************/
	/*
	 * function for CM dashboard
	 */

	// @GetMapping("/cmdashboard")
	// public String cmDashboard(Model model, HttpSession session) {
	// logger.info("Method : user starts");
	//
	// String userId1 = (String) session.getAttribute("USER_ID");
	// try {
	// CmDashboardModel[] region = restTemplate.getForObject(env.getUserUrl() +
	// "cmDashboardGetIdd?id=" + userId1,
	// CmDashboardModel[].class);
	// List<CmDashboardModel> cmDashboardGetIdd = Arrays.asList(region);
	// model.addAttribute("cmDashboardGetIdd", cmDashboardGetIdd);
	// System.out.println("cmDashboardGetIdd@@@@@@@"+cmDashboardGetIdd);
	// } catch (RestClientException e) { // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// logger.info("Method : user ends");
	// return "user/cmDashboard";
	// }
	/*
	 * view cm dashboard count
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("cmdashboard-view-count")
	public @ResponseBody JsonResponse<CmDashboardModel> viewcmDashboardCount(Model model, HttpSession session,
			@RequestParam String stateId) {

		logger.info("Method : viewcmDashboardCount starts");

		JsonResponse<CmDashboardModel> jsonResponse = new JsonResponse<CmDashboardModel>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			jsonResponse = restTemplate.getForObject(env.getUserUrl() + "viewcmDashboardCount?stateId=" + stateId,
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();
			CmDashboardModel customerNewModel = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<CmDashboardModel>() {
					});
			jsonResponse.setBody(customerNewModel);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}

		logger.info("Method : viewcmDashboardCount ends");

		return jsonResponse;
	}
	/*
	 * view dm dashboard view total registration(Ag grid)
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("cmdashboard-view-regd")
	public @ResponseBody List<CmDashboardModel> viewcmDashboardTotalRegd(Model model, HttpSession session,
			@RequestParam String stateId, @RequestParam String typeId) {

		logger.info("Method : viewcmDashboardTotalRegd starts");

		JsonResponse<List<CmDashboardModel>> patientDetails = new JsonResponse<List<CmDashboardModel>>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			patientDetails = restTemplate.getForObject(
					env.getUserUrl() + "cmDashboardViewRegd?stateId=" + stateId + "&typeId=" + typeId,
					JsonResponse.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : viewcmDashboardTotalRegd ends");

		return patientDetails.getBody();
	}

	/*
	 * view cm dashboard age wise total test
	 */
	@SuppressWarnings("unchecked")

	@GetMapping("cmdashboard-view-ageWiseTotalTest")
	public @ResponseBody JsonResponse<CmDashboardModel> viewcmDashboardAgeWiseTotalTest(Model model,
			HttpSession session, @RequestParam String stateId) {

		logger.info("Method : viewcmDashboardAgeWiseTotalTest starts");

		JsonResponse<CmDashboardModel> jsonResponse = new JsonResponse<CmDashboardModel>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			jsonResponse = restTemplate.getForObject(
					env.getUserUrl() + "rest-viewcmDashboardAgeWiseTotalTest?stateId=" + stateId, JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();
			CmDashboardModel customerNewModel = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<CmDashboardModel>() {
					});
			jsonResponse.setBody(customerNewModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}
		logger.info("Method : viewcmDashboardAgeWiseTotalTest ends");
		return jsonResponse;
	}

	/*
	 * view cm dashboard area wise total test
	 */
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/cmdashboard-view-areaWiseTotalTest" })
	public @ResponseBody JsonResponse<Object> viewcmDashboardAreaWiseTotalTest(@RequestParam String stateId) {
		logger.info("Method : viewcmDashboardAreaWiseTotalTest starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(
					env.getUserUrl() + "rest-viewcmDashboardAreaWiseTotalTest?stateId=" + stateId, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : viewcmDashboardAreaWiseTotalTest ends");
		return res;
	}

	/*
	 * view dm dashboard disease wise total highrisk
	 */
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/cmdashboard-view-diseaseWiseTotalHighRisk" })
	public @ResponseBody JsonResponse<Object> viewcmDashboardDiseaseWiseTotalHighRisk(@RequestParam String stateId) {
		logger.info("Method : viewcmDashboardDiseaseWiseTotalHighRisk starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(
					env.getUserUrl() + "rest-viewcmDashboardDiseaseWiseTotalHighRisk?stateId=" + stateId,
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
		// System.out.println("High Risk =========="+res);
		logger.info("Method : viewcmDashboardDiseaseWiseTotalHighRisk ends");
		return res;
	}

	/*
	 * view cm dashboard disease wise total abnormal
	 */
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/cmdashboard-view-diseaseWiseTotalAbnormal" })
	public @ResponseBody JsonResponse<Object> viewcmDashboardDiseaseWiseTotalAbnormal(@RequestParam String stateId) {
		logger.info("Method : viewcmDashboardDiseaseWiseTotalAbnormal starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(
					env.getUserUrl() + "rest-viewcmDashboardDiseaseWiseTotalAbnormal?stateId=" + stateId,
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
		// System.out.println("Abnormal =========="+res);
		logger.info("Method : viewcmDashboardDiseaseWiseTotalAbnormal ends");
		return res;
	}

	//////////////////////////////////////////////////////////////// web controller
	//////////////////////////////////////////////////////////////// /////////////////////////////////////////////////////////////////////////
	/*************************************************
	 * function to get pie chart for male and female in cm dashboard
	 */

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/cmdashboard-health-check-up-male-female" })
	public @ResponseBody JsonResponse<Object> viewcmDashboardMaleFemale(@RequestParam String stateId) {
		logger.info("Method : viewcmDashboardMaleFemale starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-viewcmDashboardMaleFemale?stateId=" + stateId,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("Success");
		}
		logger.info("Method : viewcmDashboardMaleFemale ends");
		return res;

	}

	/*************************************************
	 * function to get pie chart for normal and abnormal in dm dashboard
	 */
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/cmdashboard-health-check-up-normal-abNormal" })
	public @ResponseBody JsonResponse<Object> viewcmDashboardNormalAbNormal(@RequestParam String stateId) {
		logger.info("Method : viewcmDashboardNormalAbNormal starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-viewcmDashboardNormalAbNormal?stateId=" + stateId,
					JsonResponse.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("Success");
		}
		logger.info("Method : viewcmDashboardNormalAbNormal ends");
		return res;

	}

	/*************************************************
	 * function to get pie chart for disease-area-wise in cm dashboard
	 */
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/cmdashboard-health-check-up-disease-area-wise" })
	public @ResponseBody JsonResponse<Object> viewcmDashboardAreaWiseCheckUp(@RequestParam String stateId) {
		logger.info("Method : viewcmDashboardAreaWiseCheckUp starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-viewcmDashboardAreaWiseCheckUp?stateId=" + stateId,
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();
			List<DropDownModel> dataList = mapper.convertValue(res.getBody(), new TypeReference<List<DropDownModel>>() {
			});

			Double sum = 0.0;
			for (DropDownModel m : dataList) {
				sum = sum + Double.parseDouble(m.getKey());
			}
			for (DropDownModel m : dataList) {
				Double key = Double.parseDouble(m.getKey());
				Double percent = (key / sum) * 100;
				m.setCode(percent.toString());
			}
			res.setBody(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("Success");
		}
		logger.info("Method : viewcmDashboardAreaWiseCheckUp ends");
		return res;

	}

	/*************************************************
	 * function to get pie chart for Normal Area Wise in dm dashboard
	 */
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/cmdashboard-health-check-up-area-wise-normal" })
	public @ResponseBody JsonResponse<Object> viewcmDashboardAreaWiseNormalCheckUp(@RequestParam String stateId) {
		logger.info("Method : viewcmDashboardAreaWiseNormalCheckUp starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(
					env.getUserUrl() + "rest-viewcmDashboardAreaWiseNormalCheckUp?stateId=" + stateId,
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();
			List<DropDownModel> dataList = mapper.convertValue(res.getBody(), new TypeReference<List<DropDownModel>>() {
			});

			Double sum = 0.0;
			for (DropDownModel m : dataList) {
				sum = sum + Double.parseDouble(m.getKey());
			}
			for (DropDownModel m : dataList) {
				Double key = Double.parseDouble(m.getKey());
				Double percent = (key / sum) * 100;
				m.setCode(percent.toString());
			}
			res.setBody(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("Success");
		}
		logger.info("Method : viewcmDashboardAreaWiseNormalCheckUp ends");
		return res;

	}

	/*************************************************
	 * function to get pie chart for High Risk Area Wise in dm dashboard
	 */
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/cmdashboard-health-check-up-area-wise-highrisk" })
	public @ResponseBody JsonResponse<Object> viewcmDashboardAreaWiseHighriskCheckUp(@RequestParam String stateId) {
		logger.info("Method : viewdmDashboardAreaWiseHighriskCheckUp starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(
					env.getUserUrl() + "rest-viewcmDashboardAreaWiseHighriskCheckUp?stateId=" + stateId,
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();
			List<DropDownModel> dataList = mapper.convertValue(res.getBody(), new TypeReference<List<DropDownModel>>() {
			});

			Double sum = 0.0;
			for (DropDownModel m : dataList) {
				sum = sum + Double.parseDouble(m.getKey());
			}
			for (DropDownModel m : dataList) {
				Double key = Double.parseDouble(m.getKey());
				Double percent = (key / sum) * 100;
				m.setCode(percent.toString());
			}
			res.setBody(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("Success");
		}
		logger.info("Method : viewcmDashboardAreaWiseHighriskCheckUp ends");
		return res;

	}

	/**
	 * Function for get otp
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/get-otp")
	public @ResponseBody JsonResponse<Object> getOtp(HttpSession session, @RequestBody DropDownModel dropDownModel) {
		logger.info("Method : getOtp starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {

			resp = restTemplate.postForObject(env.getApiUrl() + "forgot-password-get-otp", dropDownModel,
					JsonResponse.class);
			ObjectMapper mapper = new ObjectMapper();

			DropDownModel seat = mapper.convertValue(resp.getBody(), new TypeReference<DropDownModel>() {
			});

			resp.setBody(seat);
		} catch (Exception e) {

			e.printStackTrace();
		}

		if (resp.getCode() == "" && resp.getCode() == null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");

		}
		System.out.println("resp=====" + resp);
		logger.info("Method : getOtp ends");

		return resp;
	}

	/**
	 * Function for save new password
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/save-new-password")
	public @ResponseBody JsonResponse<Object> saveNewPassword(HttpSession session,
			@RequestBody DropDownModel dropDownModel) {
		logger.info("Method : saveNewPassword starts");
        String pass=dropDownModel.getCode();
		if (pass != null && pass != "") {
			pass = passwordEncoder.encode(pass);
			dropDownModel.setCode(pass);
		}

		JsonResponse<Object> resp = new JsonResponse<Object>();
		System.out.println("dropDownModel=====" + dropDownModel);
		try {

			resp = restTemplate.postForObject(env.getApiUrl() + "change-password", dropDownModel, JsonResponse.class);
			ObjectMapper mapper = new ObjectMapper();

			DropDownModel seat = mapper.convertValue(resp.getBody(), new TypeReference<DropDownModel>() {
			});

			resp.setBody(seat);
		} catch (Exception e) {

			e.printStackTrace();
		}

		if (resp.getCode() == "" && resp.getCode() == null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");

		}
		System.out.println("resp save=====" + resp);
		logger.info("Method : saveNewPassword ends");

		return resp;
	}
}
