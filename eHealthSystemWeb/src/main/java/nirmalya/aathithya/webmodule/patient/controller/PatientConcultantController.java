package nirmalya.aathithya.webmodule.patient.controller;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.patient.model.ConsultantGetDoctorModel;
import nirmalya.aathithya.webmodule.patient.model.ConsultantTimeInterval;
import nirmalya.aathithya.webmodule.patient.model.DoctorProfileModel;
import nirmalya.aathithya.webmodule.patient.model.PatientConsultantModel;
import nirmalya.aathithya.webmodule.patient.model.ViewPatientConsultantModel;

@Controller
@RequestMapping(value = { "user/" })
public class PatientConcultantController {
	Logger logger = LoggerFactory.getLogger(PatientConcultantController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	/*
	 * Get Mapping for view-patient-details
	 */

	@GetMapping("/view-patient-consultation")
	public String viewPatientConsultation(Model model, HttpSession session) {
		logger.info("Method : viewPatientConsultation starts");
		// drop down for Speciality list
		try {
			DropDownModel[] spl = restTemplate.getForObject(env.getUserUrl() + "get-speciality-list",
					DropDownModel[].class);
			List<DropDownModel> specialityList = Arrays.asList(spl);
			model.addAttribute("specialityList", specialityList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewPatientConsultation ends");
		return "patient/patientConsultation";
	}

	// post mapping for addding data

	@SuppressWarnings("unchecked")
	@PostMapping("/view-patient-consultation-add")
	public @ResponseBody JsonResponse<Object> addPatientConsultant(Model model, HttpSession session,
			@RequestBody PatientConsultantModel patientConsultantModel) {

		logger.info("Method : addPatientConsultant starts");
		String userid = (String) session.getAttribute("USER_ID");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.postForObject(env.getUserUrl() + "patientConsultantAdd?userid=" + userid,
					patientConsultantModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : addPatientConsultant ends");
		return resp;
	}

	@SuppressWarnings("unchecked")

	@GetMapping("view-patient-consultation-through-ajax")
	public @ResponseBody List<ViewPatientConsultantModel> viewquotation(HttpSession session) {

		logger.info("Method :viewcustomer starts");

		JsonResponse<List<ViewPatientConsultantModel>> resp = new JsonResponse<List<ViewPatientConsultantModel>>();
		String userid = (String) session.getAttribute("USER_ID");
		try {

			resp = restTemplate.getForObject(env.getUserUrl() + "getAllconsultant?userid=" + userid,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();

		List<ViewPatientConsultantModel> quotationNewModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<ViewPatientConsultantModel>>() {
				});
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		for (ViewPatientConsultantModel a : quotationNewModel) {

			if (a.getDate() != null && a.getDate() != "") {
				a.setDate(DateFormatter.dateFormat(a.getDate(), dateFormat));
			}

		}
		resp.setBody(quotationNewModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :viewcustomer ends");
		return resp.getBody();
	}

	// more than this month
	@SuppressWarnings("unchecked")

	@GetMapping("view-patient-consultation-through-more-thanone month-ajax")
	public @ResponseBody List<ViewPatientConsultantModel> viewquotationMorethanMonth(HttpSession session) {

		logger.info("Method :viewquotationMorethanMonth starts");

		JsonResponse<List<ViewPatientConsultantModel>> resp = new JsonResponse<List<ViewPatientConsultantModel>>();
		String userid = (String) session.getAttribute("USER_ID");
		try {

			resp = restTemplate.getForObject(env.getUserUrl() + "getAllconsultantMmoreThenaOnemonth?userid=" + userid,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<ViewPatientConsultantModel> quotationNewModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<ViewPatientConsultantModel>>() {
				});
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		for (ViewPatientConsultantModel a : quotationNewModel) {

			if (a.getDate() != null && a.getDate() != "") {
				a.setDate(DateFormatter.dateFormat(a.getDate(), dateFormat));
			}

		}
		resp.setBody(quotationNewModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :viewquotationMorethanMonth ends");
		return resp.getBody();
	}

	// Doctor list
	@SuppressWarnings("unchecked")

	@GetMapping("view-patient-consultation-doctor-list")
	public @ResponseBody List<Object> getDoctorList(HttpSession session, @RequestParam String id) {

		logger.info("Method :getDoctorList starts");

		JsonResponse<List<Object>> resp = new JsonResponse<List<Object>>();

		try {

			resp = restTemplate.getForObject("https://1331.co.in/api/Doc/GetDocList?speciality=" + id,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<Object> quotationNewModel = mapper.convertValue(resp.getBody(), new TypeReference<List<Object>>() {
		});
		resp.setBody(quotationNewModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		logger.info("Method :getDoctorList ends");
		return resp.getBody();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-consultation-doctor-list-ehs")
	public @ResponseBody List<ConsultantGetDoctorModel> getDoctorListEhs(HttpSession session, @RequestParam String id) {
		
		logger.info("Method :getDoctorListEhs starts");
		
		JsonResponse<List<ConsultantGetDoctorModel>> resp = new JsonResponse<List<ConsultantGetDoctorModel>>();
		
		try {
			
			resp = restTemplate.getForObject(env.getUserUrl() + "getAllDoctorList?id="+id, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();
		
		List<ConsultantGetDoctorModel> quotationNewModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<ConsultantGetDoctorModel>>() {
		});
		resp.setBody(quotationNewModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		
		logger.info("Method :getDoctorListEhs ends");
		return resp.getBody();
	}
	/*
	 * 
	 * get doctor availbity thorugh ajax
	 * 
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "view-patient-consultation-avaibality" })
	public @ResponseBody List<ConsultantTimeInterval> getDoctorAvaible(HttpSession session,
			@RequestParam("doctorid") String doctorid) {
		logger.info("Method : getDoctorAvaible starts");
		JsonResponse<List<ConsultantTimeInterval>> res = new JsonResponse<List<ConsultantTimeInterval>>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "doctorAvailablity?doctorid=" + doctorid,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<ConsultantTimeInterval> quotationNewModel = mapper.convertValue(res.getBody(),
				new TypeReference<List<ConsultantTimeInterval>>() {
				});
		res.setBody(quotationNewModel);
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getDoctorAvaible ends");
		return res.getBody();
	}

	/*
	 * get all time sslot for appointment booking
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "view-patient-consultation-get-doctor-availabilityDate" })
	public @ResponseBody JsonResponse<List<DropDownModel>> getDocAvailabilityDate(HttpSession session,
			@RequestParam("doctor") String doctor, @RequestParam("appointdate") String appointdate,
			@RequestParam String hospitalid) {
		logger.info("Method : getDocAvailabilityDate starts");
		JsonResponse<List<DropDownModel>> res = new JsonResponse<List<DropDownModel>>();
		try {
			res = restTemplate.getForObject("https://1331.co.in/api/Doc/GetDocSlotInfo?doctor=" + doctor
					+ "&appointdate=" + appointdate + "&hospitalid=" + hospitalid, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getCode());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getDocAvailabilityDate ends");
		return res;
	}
	// post mapping for add soltBooking details

		@SuppressWarnings("unchecked")
		@PostMapping("/view-patient-consultation-add-slotDetails")
		public @ResponseBody JsonResponse<Object> addSlotBookingDetails(Model model, HttpSession session,
				@RequestBody PatientConsultantModel patientConsultantModel) {

			logger.info("Method : addSlotBookingDetails starts");
			byte[] encodeByte1 = Base64.getDecoder().decode(patientConsultantModel.getTime().getBytes());
			String time = (new String(encodeByte1));
			String time1 = time.replaceAll("\\s", "");
			patientConsultantModel.setTime(time1);
			String userid = (String) session.getAttribute("USER_ID");
			patientConsultantModel.setUserid(userid);
			JsonResponse<Object> response = new JsonResponse<Object>();
			
			Map<String,Object> map = new HashMap<String,Object>();
			
			try {

				map = restTemplate.postForObject("https://1331.co.in/api/Doc",
						patientConsultantModel, Map.class);

			} catch (RestClientException e) {
				e.printStackTrace();
			}
			
			if (map.get("code") == "" || map.get("code") == null || !map.get("code").equals("success")) {
				response.setCode("Unsuccess");
			} else {
				try {
					patientConsultantModel.setAppointid(map.get("aptid").toString());
					response = restTemplate.postForObject("http://api.ehealthsystem.com/nirmalyaRest/api/geo-post-doctor-appointment",
						patientConsultantModel, JsonResponse.class);
				} catch (RestClientException e) {
					e.printStackTrace();
				}
				if (response.getCode() == "" || response.getCode() == null || !response.getCode().equals("success")) {
				} else {
				}
			}
			
			String resMessage=null;
			resMessage=(String) map.get("message");
				response.setMessage(resMessage);
			
			
			logger.info("Method : addSlotBookingDetails ends");
			return response;
		}

	/*
	 * get all time sslot for appointment booking
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "view-patient-consultation-get-time-slot" })
	public @ResponseBody JsonResponse<List<DropDownModel>> getAllTime(HttpSession session) {
		logger.info("Method : getAllTime starts");
		JsonResponse<List<DropDownModel>> res = new JsonResponse<List<DropDownModel>>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "getAlltimeslot", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getAllTime ends");
		return res;
	}

	/*
	 * get all time sslot for appointment booking
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "view-patient-consultation-check-slot" })
	public @ResponseBody JsonResponse<List<DropDownModel>> getTime(HttpSession session,
			@RequestParam("time") String time, @RequestParam("date") String date, @RequestParam String doctorid) {
		logger.info("Method : getTime starts");
		JsonResponse<List<DropDownModel>> res = new JsonResponse<List<DropDownModel>>();
		try {
			res = restTemplate.getForObject(
					env.getUserUrl() + "gettimeslot?time=" + time + "&date=" + date + "&doctorid=" + doctorid,
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
		logger.info("Method : getTime ends");
		return res;
	}

	/********************** view doctor profile ***********************/
	@SuppressWarnings("unchecked")

	@GetMapping("view-patient-consultation-doctor-profile")
	public @ResponseBody JsonResponse<DoctorProfileModel> getDoctorProfile(Model model, HttpSession session,
			@RequestParam String id) {

		logger.info("Method : getDoctorProfile starts");

		JsonResponse<DoctorProfileModel> jsonResponse = new JsonResponse<DoctorProfileModel>();
		try {
			jsonResponse = restTemplate.getForObject(env.getUserUrl() + "rest-getDoctorProfile?id=" + id,
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();
			DoctorProfileModel doctorProfileModel = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<DoctorProfileModel>() {
					});
			String drProfDoc = null;
			if (doctorProfileModel.getFileDetails() != null && doctorProfileModel.getFileDetails() != ""
					&& !doctorProfileModel.getFileDetails().equals("null")) {

				drProfDoc = env.getBaseURL() + "document/docprofile/" + doctorProfileModel.getFileDetails();
				doctorProfileModel.setFileDetails(drProfDoc);
			}

			jsonResponse.setBody(doctorProfileModel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}

		logger.info("Method : getDoctorProfile ends");

		return jsonResponse;
	}

	/********************** save rating of doctor ***********************/
	@SuppressWarnings("unchecked")

	@GetMapping("view-patient-consultation-save-rating")
	public @ResponseBody JsonResponse<Object> saveRating(Model model, HttpSession session,
			@RequestParam String ratingid, @RequestParam String doctorid) {

		logger.info("Method : saveRating starts");

		String patientid = (String) session.getAttribute("USER_ID");
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		try {
			jsonResponse = restTemplate.getForObject(env.getUserUrl() + "rest-saveRating?ratingid=" + ratingid
					+ "&doctorid=" + doctorid + "&patientid=" + patientid, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}

		logger.info("Method : saveRating ends");

		return jsonResponse;
	}

}
