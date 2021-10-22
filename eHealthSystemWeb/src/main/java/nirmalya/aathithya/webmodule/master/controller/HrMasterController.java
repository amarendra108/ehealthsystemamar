package nirmalya.aathithya.webmodule.master.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.master.model.HrMasterModel;
import com.fasterxml.jackson.core.type.TypeReference;

@Controller
@RequestMapping(value = "master")
public class HrMasterController {

	Logger logger = LoggerFactory.getLogger(HrMasterController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping("/referenceHr")
	public String displayHrPage(Model model, HttpSession session) {
		logger.info("Method : hr starts");

		logger.info("Method : hr ends");
		return "master/referenceHr";
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/referenceHr-add-job-type")
	public @ResponseBody JsonResponse<Object> addJobMaster(@RequestBody HrMasterModel job, HttpSession session) {
		logger.info("Method : addJobMaster starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		job.setCreatedBy(userId);

		try {
			resp = restTemplate.postForObject(env.getMasterUrl() + "rest-addnew-job-type", job, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : addJobMaster starts");
		System.out.println(resp);

		return resp;
	}

//        View page for Hr job types

	@SuppressWarnings("unchecked")
	@GetMapping("/referenceHr-view-job-type")
	public @ResponseBody List<HrMasterModel> viewJobType(HttpSession session) {
		logger.info("Method : viewJobType starts");

		JsonResponse<List<HrMasterModel>> resp = new JsonResponse<List<HrMasterModel>>();
		List<HrMasterModel> returnList = new ArrayList<HrMasterModel>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "viewJobType", JsonResponse.class);
			returnList = resp.getBody();
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		System.out.println(returnList);
		logger.info("Method : viewJobType ends");
		return returnList;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/referenceHr-job-type-delete")
	public @ResponseBody JsonResponse<Object> deleteJobType(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteJobType starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteJobType?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteJobType ends");
		return resp;
	}

	/*
	 * --------------------------Reference HR WORK HOURS
	 * -----------------------------------------------------------------------
	 * 
	 */

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/referenceHr-add-work-hour")
	public @ResponseBody JsonResponse<Object> addWorkHour(@RequestBody HrMasterModel hrWorkHoursModel, Model model,
			HttpSession session) {
		logger.info("Method : add work hour starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		hrWorkHoursModel.setWorkCreatedBy(userId);

		try {
			hrWorkHoursModel.setWorkUpdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "rest-addnew-work-hour", hrWorkHoursModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : add work hour ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/referenceHr-view-work-hour")
	public @ResponseBody List<HrMasterModel> viewWorkHour() {

		logger.info("Method : view work hour starts");

		JsonResponse<List<HrMasterModel>> jsonResponse = new JsonResponse<List<HrMasterModel>>();

		try {
			jsonResponse = restTemplate.getForObject(env.getMasterUrl() + "rest-view-work-hour", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		List<HrMasterModel> addreq = mapper.convertValue(jsonResponse.getBody(),
				new TypeReference<List<HrMasterModel>>() {
				});
		String name = "";
		for (HrMasterModel m : addreq) {
			name = m.getWorkFromTime() + "-" + m.getWorkToTime() + " (" + m.getWorkFromDate() + "-" + m.getWorkToDate()
					+ ")";
			m.setWorkhourName(name);
			System.out.println(m.getWorkhourName());
		}

		jsonResponse.setBody(addreq);

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : view work hour ends");
		System.out.println(jsonResponse.getBody());

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/referenceHr-work-hour-delete")
	public @ResponseBody JsonResponse<Object> deleteWorkHour(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteWorkHour starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteWorkHour?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteWorkHour ends");
		return resp;
	}

	/*
	 * --------------------------Reference Education
	 * -----------------------------------------------------------------------
	 * 
	 */

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/referenceHr-add-education-level")
	public @ResponseBody JsonResponse<Object> addEducation(@RequestBody HrMasterModel hrWorkHoursModel, Model model,
			HttpSession session) {
		logger.info("Method :addEducation starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		hrWorkHoursModel.setEducationCreatedBy(userId);
		try {
			hrWorkHoursModel.setEducationupdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "addEducation", hrWorkHoursModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : addEducation ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/referenceHr-view-education-level")
	public @ResponseBody List<HrMasterModel> viewEducation() {

		logger.info("Method : viewEducation");

		JsonResponse<List<HrMasterModel>> jsonResponse = new JsonResponse<List<HrMasterModel>>();

		try {
			jsonResponse = restTemplate.getForObject(env.getMasterUrl() + "viewEducation", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : viewEducation");
		System.out.println(jsonResponse.getBody());

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/referenceHr-edu-delete")
	public @ResponseBody JsonResponse<Object> deleteEducation(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteEducation starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteEducation?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteEducation ends");
		return resp;
	}

	/*
	 * --------------------------Reference Job Band
	 * -----------------------------------------------------------------------
	 * 
	 */

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/referenceHr-add-job-band-level")
	public @ResponseBody JsonResponse<Object> addJobBand(@RequestBody HrMasterModel hrWorkHoursModel, Model model,
			HttpSession session) {
		logger.info("Method :addJobBand starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		hrWorkHoursModel.setJobBandCreatedBy(userId);
		try {
			hrWorkHoursModel.setJobBandupdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "addJobBand", hrWorkHoursModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : addJobBand ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/referenceHr-view-job-band-level")
	public @ResponseBody List<HrMasterModel> viewJobBand() {

		logger.info("Method : viewEducation");

		JsonResponse<List<HrMasterModel>> jsonResponse = new JsonResponse<List<HrMasterModel>>();

		try {
			jsonResponse = restTemplate.getForObject(env.getMasterUrl() + "viewJobBand", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : viewJobBand");
		System.out.println(jsonResponse.getBody());

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/referenceHr-job-band-delete")
	public @ResponseBody JsonResponse<Object> deleteJobBand(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteJobBand starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteJobBand?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteJobBand ends");
		return resp;
	}

	/*
	 * --------------------------Reference Benefits
	 * -----------------------------------------------------------------------
	 * 
	 */

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/referenceHr-add-benefits")
	public @ResponseBody JsonResponse<Object> addBenefits(@RequestBody HrMasterModel hrWorkHoursModel, Model model,
			HttpSession session) {
		logger.info("Method :addBenefits starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		hrWorkHoursModel.setBenifitCreatedBy(userId);
		try {
			hrWorkHoursModel.setBenifitupdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "addBenefits", hrWorkHoursModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : addBenefits ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/referenceHr-view-benefits")
	public @ResponseBody List<HrMasterModel> viewBenefits() {

		logger.info("Method : viewBenefits");

		JsonResponse<List<HrMasterModel>> jsonResponse = new JsonResponse<List<HrMasterModel>>();

		try {
			jsonResponse = restTemplate.getForObject(env.getMasterUrl() + "viewBenefits", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : viewBenefits");
		System.out.println(jsonResponse.getBody());

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/referenceHr-benefit-delete")
	public @ResponseBody JsonResponse<Object> deleteBenefits(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteBenefits starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteBenefits?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteBenefits ends");
		return resp;
	}

	/*
	 * --------------------------Address Type
	 * -----------------------------------------------------------------------
	 * 
	 */

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/referenceHr-add-address")
	public @ResponseBody JsonResponse<Object> addAddress(@RequestBody HrMasterModel hrWorkHoursModel, Model model,
			HttpSession session) {
		logger.info("Method :addAddress starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		hrWorkHoursModel.setAddressCreatedBy(userId);
		try {
			hrWorkHoursModel.setAddressupdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "addAddress", hrWorkHoursModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : addAddress ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/referenceHr-view-address")
	public @ResponseBody List<HrMasterModel> viewAddress() {

		logger.info("Method : viewAddress");

		JsonResponse<List<HrMasterModel>> jsonResponse = new JsonResponse<List<HrMasterModel>>();

		try {
			jsonResponse = restTemplate.getForObject(env.getMasterUrl() + "viewAddress", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : viewAddress");
		System.out.println(jsonResponse.getBody());

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/referenceHr-address-delete")
	public @ResponseBody JsonResponse<Object> deleteAddress(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteAddress starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteAddress?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteAddress ends");
		return resp;
	}

	/*
	 * --------------------------Blood Group Type
	 * -----------------------------------------------------------------------
	 * 
	 */

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/referenceHr-add-bloodGroup")
	public @ResponseBody JsonResponse<Object> addBloodgroup(@RequestBody HrMasterModel hrWorkHoursModel, Model model,
			HttpSession session) {
		logger.info("Method :addBloodgroup starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		hrWorkHoursModel.setBloodGroupCreatedBy(userId);
		try {
			hrWorkHoursModel.setBloodGroupsupdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "addBloodgroup", hrWorkHoursModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : addBloodgroup ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/referenceHr-view-bloodGroup")
	public @ResponseBody List<HrMasterModel> viewBloodGroup() {

		logger.info("Method : viewBloodGroup");

		JsonResponse<List<HrMasterModel>> jsonResponse = new JsonResponse<List<HrMasterModel>>();

		try {
			jsonResponse = restTemplate.getForObject(env.getMasterUrl() + "viewBloodGroup", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : viewBloodGroup");
		System.out.println(jsonResponse.getBody());

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/referenceHr-bloodGroup-delete")
	public @ResponseBody JsonResponse<Object> deleteBloodGroup(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteBloodGroup starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteBloodGroup?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteBloodGroup ends");
		return resp;
	}
	
	/*
	 * --------------------------Shift Type
	 * -----------------------------------------------------------------------
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/referenceHr-add-shift")
	public @ResponseBody JsonResponse<Object> addShift(@RequestBody HrMasterModel shiftModel, Model model,
			HttpSession session) {
		logger.info("Method : addShift starts");
			System.out.println(shiftModel);

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		shiftModel.setShiftCreatedBy(userId);

		try {
			shiftModel.setShiftUpdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "rest-addnew-shift", shiftModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}
		System.out.println(jsonResponse);
		logger.info("Method : addShift ends");
		

		return jsonResponse;
	}

	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/referenceHr-view-shift")
	public @ResponseBody List<HrMasterModel> viewShift() {

		logger.info("Method : viewShift Starts");

		JsonResponse<List<HrMasterModel>> jsonResponse = new JsonResponse<List<HrMasterModel>>();

		try {
			jsonResponse = restTemplate.getForObject(env.getMasterUrl() + "viewShift", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : viewShift Ends");
		System.out.println(jsonResponse.getBody());

		return jsonResponse.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/referenceHr-shift-delete")
	public @ResponseBody JsonResponse<Object> deleteShift(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteShift starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteshift?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteShift ends");
		return resp;
	}


	/*
	 * --------------------------Marital Type
	 * -----------------------------------------------------------------------
	 * 
	 */

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/referenceHr-add-marital")
	public @ResponseBody JsonResponse<Object> addMarital(@RequestBody HrMasterModel hrWorkHoursModel, Model model,
			HttpSession session) {
		logger.info("Method :addMarital starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		hrWorkHoursModel.setMaritalCreatedBy(userId);
		try {
			hrWorkHoursModel.setMaritalupdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "addMarital", hrWorkHoursModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : addMarital ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/referenceHr-view-marital")
	public @ResponseBody List<HrMasterModel> viewMarital() {

		logger.info("Method : viewMarital");

		JsonResponse<List<HrMasterModel>> jsonResponse = new JsonResponse<List<HrMasterModel>>();

		try {
			jsonResponse = restTemplate.getForObject(env.getMasterUrl() + "viewMarital", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : viewMarital");
		System.out.println(jsonResponse.getBody());

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/referenceHr-marital-delete")
	public @ResponseBody JsonResponse<Object> deleteMarital(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteMarital starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteMarital?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteMarital ends");
		return resp;
	}

	/*
	 * --------------------------Document Type
	 * -----------------------------------------------------------------------
	 * 
	 */

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/referenceHr-add-document")
	public @ResponseBody JsonResponse<Object> addDocument(@RequestBody HrMasterModel hrWorkHoursModel, Model model,
			HttpSession session) {
		logger.info("Method :addDocument starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		hrWorkHoursModel.setDocumentCreatedBy(userId);
		try {
			hrWorkHoursModel.setDocumentupdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "addDocument", hrWorkHoursModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : addDocument ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/referenceHr-view-document")
	public @ResponseBody List<HrMasterModel> viewDocument() {

		logger.info("Method : viewDocument");

		JsonResponse<List<HrMasterModel>> jsonResponse = new JsonResponse<List<HrMasterModel>>();

		try {
			jsonResponse = restTemplate.getForObject(env.getMasterUrl() + "viewDocument", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : viewDocument");
		System.out.println(jsonResponse.getBody());

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/referenceHr-document-delete")
	public @ResponseBody JsonResponse<Object> deleteDocument(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteDocument starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteDocument?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteDocument ends");
		return resp;
	}

	/*
	 * --------------------------Time Sheet Type
	 * -----------------------------------------------------------------------
	 * 
	 */

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/referenceHr-add-timeSheet-type")
	public @ResponseBody JsonResponse<Object> addTimeSheet(@RequestBody HrMasterModel hrWorkHoursModel, Model model,
			HttpSession session) {
		logger.info("Method :addTimeSheet starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		hrWorkHoursModel.setTimeSheetCreatedBy(userId);
		try {
			hrWorkHoursModel.setTimeSheetupdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "addTimeSheet", hrWorkHoursModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : addTimeSheet ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/referenceHr-view-timeSheet")
	public @ResponseBody List<HrMasterModel> viewTimeSheet() {

		logger.info("Method : viewTimeSheet");

		JsonResponse<List<HrMasterModel>> jsonResponse = new JsonResponse<List<HrMasterModel>>();

		try {
			jsonResponse = restTemplate.getForObject(env.getMasterUrl() + "viewTimeSheet", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : viewTimeSheet");
		System.out.println(jsonResponse.getBody());

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/referenceHr-timeSheet-delete")
	public @ResponseBody JsonResponse<Object> deleteTimeSheet(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteTimeSheet starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteTimeSheet?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteTimeSheet ends");
		return resp;
	}

	/*
	 * --------------------------Employment Type
	 * -----------------------------------------------------------------------
	 * 
	 */

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/referenceHr-add-empstatus-type")
	public @ResponseBody JsonResponse<Object> addEmpStatus(@RequestBody HrMasterModel hrWorkHoursModel, Model model,
			HttpSession session) {
		logger.info("Method :addEmpStatus starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		hrWorkHoursModel.setEmploymentstatusCreatedBy(userId);
		try {
			hrWorkHoursModel.setEmploymentstatusupdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "addEmpStatus", hrWorkHoursModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : addEmpStatus ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/referenceHr-view-empstatus")
	public @ResponseBody List<HrMasterModel> viewEmpStatus() {

		logger.info("Method : viewEmpStatus");

		JsonResponse<List<HrMasterModel>> jsonResponse = new JsonResponse<List<HrMasterModel>>();

		try {
			jsonResponse = restTemplate.getForObject(env.getMasterUrl() + "viewEmpStatus", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : viewEmpStatus");
		System.out.println(jsonResponse.getBody());

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/referenceHr-emp-status-delete")
	public @ResponseBody JsonResponse<Object> deleteEmpStatus(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteEmpStatus starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteEmpStatus?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteEmpStatus ends");
		return resp;
	}

	/*
	 * --------------------------Project Type
	 * -----------------------------------------------------------------------
	 * 
	 */

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/referenceHr-add-projectType")
	public @ResponseBody JsonResponse<Object> addprojectType(@RequestBody HrMasterModel hrWorkHoursModel, Model model,
			HttpSession session) {
		logger.info("Method :addprojectType starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		hrWorkHoursModel.setProjectTypeCreatedBy(userId);
		try {
			hrWorkHoursModel.setProjectTypeupdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "addprojectType", hrWorkHoursModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : addprojectType ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/referenceHr-view-projectType")
	public @ResponseBody List<HrMasterModel> viewprojectType() {

		logger.info("Method : viewprojectType");

		JsonResponse<List<HrMasterModel>> jsonResponse = new JsonResponse<List<HrMasterModel>>();

		try {
			jsonResponse = restTemplate.getForObject(env.getMasterUrl() + "viewprojectType", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : viewprojectType");
		System.out.println(jsonResponse.getBody());

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/referenceHr-projectType-delete")
	public @ResponseBody JsonResponse<Object> deleteprojectType(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteprojectType starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteprojectType?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteprojectType ends");
		return resp;
	}

	/*
	 * --------------------------Priority master
	 * -----------------------------------------------------------------------
	 *
	 */

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/referenceHr-add-priority-master")
	public @ResponseBody JsonResponse<Object> addPriority(@RequestBody HrMasterModel hrMasterModel, Model model,
			HttpSession session) {
		logger.info("Method :addPriority starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		hrMasterModel.setPriorityCreatedBy(userId);
		try {
			hrMasterModel.setPriorityUpdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "addPriority", hrMasterModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : addPriority ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/referenceHr-view-priority-master")
	public @ResponseBody List<HrMasterModel> viewPriority() {

		logger.info("Method : viewPriority start");

		JsonResponse<List<HrMasterModel>> jsonResponse = new JsonResponse<List<HrMasterModel>>();

		try {
			jsonResponse = restTemplate.getForObject(env.getMasterUrl() + "viewPriority", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : viewPriority ends");
		System.out.println(jsonResponse.getBody());

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/referenceHr-priority-master-delete")
	public @ResponseBody JsonResponse<Object> deletePriority(HttpSession session, @RequestParam String id) {
		logger.info("Method : deletePriority starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deletePriority?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deletePriority ends");
		return resp;
	}

	/*
	 * --------------------------Priority master
	 * -----------------------------------------------------------------------
	 *
	 */

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/referenceHr-add-gender-master")
	public @ResponseBody JsonResponse<Object> addGender(@RequestBody HrMasterModel hrMasterModel, Model model,
			HttpSession session) {
		logger.info("Method :addGender starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		hrMasterModel.setGenderCreatedBy(userId);
		try {
			hrMasterModel.setGenderupdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "addGender", hrMasterModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : addGender ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/referenceHr-view-gender-master")
	public @ResponseBody List<HrMasterModel> viewGender() {

		logger.info("Method : viewGender start");

		JsonResponse<List<HrMasterModel>> jsonResponse = new JsonResponse<List<HrMasterModel>>();

		try {
			jsonResponse = restTemplate.getForObject(env.getMasterUrl() + "viewGender", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : viewGender ends");
		System.out.println(jsonResponse.getBody());

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/referenceHr-gender-master-delete")
	public @ResponseBody JsonResponse<Object> deleteGender(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteGender starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteGender?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteGender ends");
		return resp;
	}

	/*
	 * --------------------------Dependent Relationship
	 * -----------------------------------------------------------------------
	 *
	 */

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/referenceHr-add-dependent-relationship")
	public @ResponseBody JsonResponse<Object> addDepRelationship(@RequestBody HrMasterModel hrMasterModel, Model model,
			HttpSession session) {
		logger.info("Method :addDepRelationship starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		hrMasterModel.setDepRelationCreatedBy(userId);
		try {
			hrMasterModel.setDepRelationupdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "addDepRelationship", hrMasterModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : addDepRelationship ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/referenceHr-view-emp-dependent-relationship")
	public @ResponseBody List<HrMasterModel> viewDepRelationship() {

		logger.info("Method : viewDepRelationship start");

		JsonResponse<List<HrMasterModel>> jsonResponse = new JsonResponse<List<HrMasterModel>>();

		try {
			jsonResponse = restTemplate.getForObject(env.getMasterUrl() + "viewDepRelationship", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : viewDepRelationship ends");
		System.out.println(jsonResponse.getBody());

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/referenceHr-dependent-relationship-delete")
	public @ResponseBody JsonResponse<Object> deleteDepRelationship(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteDepRelationship starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteDepRelationship?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteDepRelationship ends");
		return resp;
	}

	/*
	 * --------------------------Employee Dependent
	 * type---------------------------------------------------
	 *
	 *
	 */

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/referenceHr-add-employee-dependent-type")
	public @ResponseBody JsonResponse<Object> addDependentType(@RequestBody HrMasterModel hrMasterModel, Model model,
			HttpSession session) {
		logger.info("Method : addDependentType starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		hrMasterModel.setDependentCreatedBy(userId);
		try {
			hrMasterModel.setDependentUpdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "addDependentType", hrMasterModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : addDependentType ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/referenceHr-view-employee-dependent-type")
	public @ResponseBody List<HrMasterModel> viewDependentType() {

		logger.info("Method : viewDependentType start");

		JsonResponse<List<HrMasterModel>> jsonResponse = new JsonResponse<List<HrMasterModel>>();

		try {
			jsonResponse = restTemplate.getForObject(env.getMasterUrl() + "viewDependentType", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : viewDependentType ends");
		System.out.println(jsonResponse.getBody());

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/referenceHr-dependent-type-delete")
	public @ResponseBody JsonResponse<Object> deleteDependentType(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteDependentType starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteDependentType?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteDependentType ends");
		return resp;
	}

	/*
	 * --------------------------Employee Insurance
	 * Company---------------------------------------------------
	 *
	 *
	 */

	@SuppressWarnings("unchecked")
	@PostMapping(value = "/referenceHr-add-employee-insurance-company")
	public @ResponseBody JsonResponse<Object> addInsuranceCompany(@RequestBody HrMasterModel hrMasterModel, Model model,
			HttpSession session) {
		logger.info("Method : addInsuranceCompany starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		hrMasterModel.setInsuranceCreatedBy(userId);
		try {
			hrMasterModel.setInsuranceUpdatedBy(userId);
			jsonResponse = restTemplate.postForObject(env.getMasterUrl() + "addInsuranceCompany", hrMasterModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : addInsuranceCompany ends");

		return jsonResponse;
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/referenceHr-view-employee-insurance-company")
	public @ResponseBody List<HrMasterModel> viewInsuranceCompany() {

		logger.info("Method : viewInsuranceCompany start");

		JsonResponse<List<HrMasterModel>> jsonResponse = new JsonResponse<List<HrMasterModel>>();

		try {
			jsonResponse = restTemplate.getForObject(env.getMasterUrl() + "viewInsuranceCompany", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = jsonResponse.getMessage();

		if (message != null && message != "") {

		} else {

			jsonResponse.setMessage("Success");
		}

		logger.info("Method : viewInsuranceCompany ends");
		System.out.println(jsonResponse.getBody());

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/referenceHr-insurance-company-delete")
	public @ResponseBody JsonResponse<Object> deleteInsuranceCompany(HttpSession session, @RequestParam String id) {
		logger.info("Method : deleteInsuranceCompany starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "deleteInsuranceCompany?id=" + id,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : deleteInsuranceCompany ends");
		return resp;
	}

}
