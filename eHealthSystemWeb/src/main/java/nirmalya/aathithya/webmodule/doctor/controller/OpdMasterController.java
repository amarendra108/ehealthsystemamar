package nirmalya.aathithya.webmodule.doctor.controller;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.doctor.model.OpdMasterModel;

@Controller
@RequestMapping(value = { "doctor/" })
public class OpdMasterController {
	Logger logger = LoggerFactory.getLogger(OpdMasterController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	/*
	 * Get Mapping for doctor-opd
	 */

	@GetMapping("doctor-opd")
	public String viewDoctorOPD(Model model, HttpSession session) {

		logger.info("Method : viewDoctorOPD starts");

		logger.info("Method : viewDoctorOPD ends");
		return "doctor/opd";
	}

	/*
	 * view-doctor-opd-view-Throughajax
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("doctor-opd-view-details")
	public @ResponseBody List<OpdMasterModel> viewDoctorOpdData(HttpSession session) {

		logger.info("Method : viewDoctorOpdData starts");

		JsonResponse<List<OpdMasterModel>> resp = new JsonResponse<List<OpdMasterModel>>();
		String userId=null;
		String dateFormat = "";
		
		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			resp = restTemplate.getForObject(env.getDoctorUrl() + "rest-viewdoctor-opd?id=" +userId, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();

		List<OpdMasterModel> grn = mapper.convertValue(resp.getBody(), new TypeReference<List<OpdMasterModel>>() {
		});
		
		for (OpdMasterModel om : grn) {

			if (om.getOpdDate() != null && om.getOpdDate() != "") {
				om.setOpdDate(DateFormatter.dateFormat(om.getOpdDate(), dateFormat));
			}

		}

		logger.info("Method : viewDoctorOpdData ends");
		return resp.getBody();
	}
	
	/*
	 * Add prescription details
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("doctor-opd-add-details")
	public @ResponseBody JsonResponse<Object> saveOpdDetails(Model model,
			@RequestBody OpdMasterModel opdmastermodel, HttpSession session) {
		logger.info("Method : saveOpdDetails starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		String doctorId = "";
		String dateFormat = "";
		try {
			doctorId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (opdmastermodel.getOpdDate() != null && opdmastermodel.getOpdDate() != "") {
			opdmastermodel.setOpdDate(DateFormatter.inputDateFormat(opdmastermodel.getOpdDate(), dateFormat));
		}
		String pId = new String(doctorId);

		opdmastermodel.setDrId(pId);
		try {
			resp = restTemplate.postForObject(env.getDoctorUrl() + "save-opd-details", opdmastermodel,
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
		logger.info("Method : saveOpdDetails starts");
		return resp;
	}
	
	
	/*
	 * Edit opd details
	 * 
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("doctor-opd-details-edit")
	public @ResponseBody List<OpdMasterModel> editOpdDetails(@RequestParam String id, HttpSession session) {

		logger.info("Method : editOpdDetails starts");

		JsonResponse<List<OpdMasterModel>> resp = new JsonResponse<List<OpdMasterModel>>();
		try {

			resp = restTemplate.getForObject(env.getDoctorUrl() + "rest-edit-opd-details?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<OpdMasterModel> grn = mapper.convertValue(resp.getBody(), new TypeReference<List<OpdMasterModel>>() {
		});

		String dateFormat = "";
		try {

			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		
		  for (OpdMasterModel om : grn) {
		  
		  if (om.getOpdDate() != null && om.getOpdDate() != "") {
		  om.setOpdDate(DateFormatter.dateFormat(om.getOpdDate(), dateFormat)); }
		  
		  }

		resp.setBody(grn);
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");

		} else {
			resp.setMessage("Success");
		}

	
		logger.info("Method : editOpdDetails ends");

		return grn;
	}
	

	/*
	 * delete-doctor-opd-view-Throughajax
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("doctor-opd-delete-details")
	public @ResponseBody JsonResponse<List<OpdMasterModel>> deleteDoctorOpdData(Model model, @RequestParam String id,
			 HttpSession session) {

		logger.info("Method : deleteDoctorOpdData starts");

		JsonResponse<List<OpdMasterModel>> resp = new JsonResponse<List<OpdMasterModel>>();
		
		try {
			resp = restTemplate.getForObject(env.getDoctorUrl() + "rest-deletedoctor-opd?id=" +id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : deleteDoctorOpdData ends");
		return resp;
	}

}
