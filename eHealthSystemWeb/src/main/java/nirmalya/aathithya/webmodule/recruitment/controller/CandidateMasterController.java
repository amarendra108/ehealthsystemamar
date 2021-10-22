package nirmalya.aathithya.webmodule.recruitment.controller;


import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.procurment.model.InventoryVendorDocumentModel;
import nirmalya.aathithya.webmodule.recruitment.model.AddRecruitentModel;
import nirmalya.aathithya.webmodule.recruitment.model.CandidateAddressModel;
import nirmalya.aathithya.webmodule.recruitment.model.CandidateApplyRequisitionModel;
import nirmalya.aathithya.webmodule.recruitment.model.CandidateAwardsModel;
import nirmalya.aathithya.webmodule.recruitment.model.CandidateDetailsModel;
import nirmalya.aathithya.webmodule.recruitment.model.CandidateDocumentModel;
import nirmalya.aathithya.webmodule.recruitment.model.CandidateEducationModel;
import nirmalya.aathithya.webmodule.recruitment.model.CandidateReferenceModel;
import nirmalya.aathithya.webmodule.recruitment.model.CandidateSkillsModel;
import nirmalya.aathithya.webmodule.recruitment.model.CandidateSourceModel;
import nirmalya.aathithya.webmodule.recruitment.model.CandidateWorkExperienceModel;

@Controller
@RequestMapping(value = "recruitment")
public class CandidateMasterController {

	Logger logger = LoggerFactory.getLogger(CandidateMasterController.class);
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "view-candidate-get-state-list" })
	public @ResponseBody JsonResponse<Object> getStateNameForLocation(Model model, @RequestBody String tCountry,
			BindingResult result) {
		logger.info("Method : getStateNameForLocation starts");
		
		JsonResponse<Object> res = new JsonResponse<Object>();
		
		try {
			System.out.println(env.getMasterUrl() + "getStateListForLoc?id=" + tCountry);
			res = restTemplate.getForObject(env.getMasterUrl() + "getStateListForLoc?id=" + tCountry,
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
		
		logger.info("Method : getStateNameForLocation ends");
		return res;

	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "view-candidate-get-city-list" })
	public @ResponseBody JsonResponse<Object> getCityForLocation(Model model, @RequestBody String tCountry,
			BindingResult result) {
		logger.info("Method : getCityForLocation starts");
		
		JsonResponse<Object> res = new JsonResponse<Object>();
		
		try {
			res = restTemplate.getForObject(env.getMasterUrl() + "getCityForLocation?id=" + tCountry,
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
		
		logger.info("Method : getCityForLocation ends");
		return res;
		
	}
	
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
	
	@PostMapping("/view-candidate-upload-file")
	public @ResponseBody JsonResponse<Object> uploadFile(@RequestParam("file") MultipartFile inputFile,
			HttpSession session) {
		logger.info("Method : uploadimage controller  starts");

		JsonResponse<Object> response = new JsonResponse<Object>();

		try {
			response.setMessage(inputFile.getOriginalFilename());
			System.out.println(inputFile);
			session.setAttribute("employeePFile", inputFile);

		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : uploadimage controller ' ends");
		return response;
	}

	@PostMapping("/view-candidate-delete-file")
	public @ResponseBody JsonResponse<Object> deleteFile(HttpSession session) {
		logger.info("Method : deleteFile uploadimage controller starts");

		JsonResponse<Object> response = new JsonResponse<Object>();

		try {
			session.removeAttribute("employeePFile");
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : deleteFile uploadimage controller ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/view-candidate-add-ajax")
	public @ResponseBody JsonResponse<CandidateDetailsModel> addCandidate(Model model, HttpSession session, @RequestBody CandidateDetailsModel reqModel){
		
		logger.info("Method : addCandidate starts");
		
		JsonResponse<CandidateDetailsModel> resp = new JsonResponse<CandidateDetailsModel>();
		String userId = "";
		String dateFormat = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}
		reqModel.setCreatedBy(userId);
		
		MultipartFile inputFile = (MultipartFile) session.getAttribute("employeePFile");
		byte[] bytes;
		String imageName = null;

		if (inputFile != null) {
			try {
				bytes = inputFile.getBytes();
				String[] fileType = inputFile.getContentType().split("/");
				imageName = saveAllImage(bytes, fileType[1]);
				System.out.println(imageName);

				reqModel.setFileUpload(imageName);
				System.out.println(reqModel.getFileUpload());
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if(reqModel.getDob()!=null && reqModel.getDob()!="") {
			reqModel.setDob(DateFormatter.inputDateFormat(reqModel.getDob(), dateFormat));
		}
		
		try {
			resp = restTemplate.postForObject(env.getRecruitment() + "addCandidate", reqModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : addCandidate ends");
		
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-candidate-view-ajax")
	public @ResponseBody List<CandidateDetailsModel> viewCandidates(HttpSession session) {
		logger.info("Method : viewCandidates starts");

		JsonResponse<List<CandidateDetailsModel>> resp = new JsonResponse<List<CandidateDetailsModel>>();
		
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "viewCandidates", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		} 
		
		ObjectMapper mapper = new ObjectMapper();

		List<CandidateDetailsModel> cand = mapper.convertValue(resp.getBody(),
				new TypeReference<List<CandidateDetailsModel>>() {
				});
		for(CandidateDetailsModel m : cand) {
			String date = "";
			
			String dateFormat = (String) (session).getAttribute("DATEFORMAT");
			if(m.getDob() != null && m.getDob() != "") {
				date = DateFormatter.dateFormat(m.getDob(),dateFormat);
				m.setDob(date);
			}
			if(m.getCreatedOn() != null && m.getCreatedOn() != "") {
				date = DateFormatter.dateFormat(m.getCreatedOn(),dateFormat);
				m.setCreatedOn(date);
			}
		}
		
		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		resp.setBody(cand);
		
		System.out.println(resp.getBody());
		logger.info("Method : viewCandidates ends");
		return resp.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-candidate-edit-candidate")
	public @ResponseBody JsonResponse<List<CandidateDetailsModel>> editCandidates(HttpSession session, String id) {
	logger.info("Method : editCandidates starts");

	JsonResponse<List<CandidateDetailsModel>> resp = new JsonResponse<List<CandidateDetailsModel>>();

	try {
	resp = restTemplate.getForObject(env.getRecruitment() + "editCandidates?id=" + id, JsonResponse.class);
	} catch (RestClientException e) {
	e.printStackTrace();
	}

	ObjectMapper mapper = new ObjectMapper();

	List<CandidateDetailsModel> empDetails = mapper.convertValue(resp.getBody(),
	new TypeReference<List<CandidateDetailsModel>>() {
	});
	if (empDetails.get(0).getFileUpload() != null && empDetails.get(0).getFileUpload() != ""
	&& !empDetails.get(0).getFileUpload().equals("null")) {
	String fileEmployeeimg = env.getBaseURL() + "document/employee/" + empDetails.get(0).getFileUpload();

	empDetails.get(0).setFileUpload(fileEmployeeimg);

	}

	String dateFormat = (String) (session).getAttribute("DATEFORMAT");
	
	for(CandidateDetailsModel m : empDetails) {
		if(m.getDob() != null && m.getDob() != "") {
			String date = DateFormatter.dateFormat(m.getDob(),dateFormat);
			m.setDob(date);
		}
	}
	
	resp.setBody(empDetails);

	String message = resp.getMessage();

	if (message != null && message != "") {

	} else {
		
	resp.setMessage("Success");
	}


	logger.info("Method : editCandidates ends");
	return resp;
	}
	
	@Scheduled(cron = "*/5 * * * * ?")
	public void scheduleTaskUsingCronExpression() {
	    System.out.println(
	      "schedule tasks using cron jobs - ");
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/view-candidate-add-address")
	public @ResponseBody JsonResponse<Object> addAddress(Model model, HttpSession session, @RequestBody CandidateAddressModel reqModel){
		
		logger.info("Method : addCandidate starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}
		reqModel.setCreatedBy(userId);
		try {
			resp = restTemplate.postForObject(env.getRecruitment() + "addAddress", reqModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : addCandidate ends");
		
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-candidate-view-address")
	public @ResponseBody List<CandidateAddressModel> viewAddress(HttpSession session, @RequestParam String id ) {
		logger.info("Method : viewAddress starts");

		JsonResponse<List<CandidateAddressModel>> resp = new JsonResponse<List<CandidateAddressModel>>();
		
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "viewAddress?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		} 
		
		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		System.out.println(resp.getBody());
		logger.info("Method : viewAddress ends");
		return resp.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-candidate-delete-address")
	public @ResponseBody JsonResponse<Object> deleteAddress(Model model, HttpSession session, @RequestParam String id){
		
		logger.info("Method : deleteAddress starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "deleteAddress?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : deleteAddress ends");
		
		return resp;
	}
	
	
	@SuppressWarnings("unchecked")
	@PostMapping("/view-candidate-add-education")
	public @ResponseBody JsonResponse<Object> addEducation(Model model, HttpSession session, @RequestBody CandidateEducationModel reqModel){
		
		logger.info("Method : addEducation starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}
		reqModel.setCreatedBy(userId);
		try {
			resp = restTemplate.postForObject(env.getRecruitment() + "addEducation", reqModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : addEducation ends");
		
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-candidate-view-education")
	public @ResponseBody List<CandidateEducationModel> viewEducation(HttpSession session, @RequestParam String id ) {
		logger.info("Method : viewEducation starts");

		JsonResponse<List<CandidateEducationModel>> resp = new JsonResponse<List<CandidateEducationModel>>();
		
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "viewEducation?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		} 
		
		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : viewEducation ends");
		return resp.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-candidate-delete-education")
	public @ResponseBody JsonResponse<Object> deleteEducation(Model model, HttpSession session, @RequestParam String id){
		
		logger.info("Method : deleteEducation starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "deleteEducation?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : deleteEducation ends");
		
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/view-candidate-add-skills")
	public @ResponseBody JsonResponse<Object> addSkills(Model model, HttpSession session, @RequestBody CandidateSkillsModel reqModel){
		
		logger.info("Method : addSkills starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}
		reqModel.setCreatedBy(userId);
		try {
			resp = restTemplate.postForObject(env.getRecruitment() + "addSkills", reqModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : addSkills ends");
		
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-candidate-view-skills")
	public @ResponseBody List<CandidateSkillsModel> viewSkills(HttpSession session, @RequestParam String id ) {
		logger.info("Method : viewSkills starts");

		JsonResponse<List<CandidateSkillsModel>> resp = new JsonResponse<List<CandidateSkillsModel>>();
		
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "viewSkills?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		} 
		
		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		
		logger.info("Method : viewSkills ends");
		return resp.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-candidate-delete-skills")
	public @ResponseBody JsonResponse<Object> deleteSkill(Model model, HttpSession session, @RequestParam String id){
		
		logger.info("Method : deleteSkill starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "deleteSkill?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : deleteSkill ends");
		
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/view-candidate-add-workExperience")
	public @ResponseBody JsonResponse<Object> addWorkExperience(Model model, HttpSession session, @RequestBody CandidateWorkExperienceModel reqModel){
		
		logger.info("Method : addWorkExperience starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		String dateFormat = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");

		} catch (Exception e) {
			e.printStackTrace();
		}
		reqModel.setCreatedBy(userId);
		
		if(reqModel.getWorkFrom()!=null && reqModel.getWorkFrom()!="") {
			reqModel.setWorkFrom(DateFormatter.inputDateFormat(reqModel.getWorkFrom(), dateFormat));
		}
		if(reqModel.getWorkTill()!=null && reqModel.getWorkTill()!="") {
			reqModel.setWorkTill(DateFormatter.inputDateFormat(reqModel.getWorkTill(), dateFormat));
		}
		
		try {
			resp = restTemplate.postForObject(env.getRecruitment() + "addWorkExperience", reqModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : addWorkExperience ends");
		
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-candidate-view-workExperience")
	public @ResponseBody List<CandidateWorkExperienceModel> viewWorkExperience(HttpSession session, @RequestParam String id ) {
		logger.info("Method : viewWorkExperience starts");

		JsonResponse<List<CandidateWorkExperienceModel>> resp = new JsonResponse<List<CandidateWorkExperienceModel>>();
		
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "viewWorkExperience?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		} 
		
		ObjectMapper mapper = new ObjectMapper();

		List<CandidateWorkExperienceModel> experience = mapper.convertValue(resp.getBody(),
				new TypeReference<List<CandidateWorkExperienceModel>>() {
				});
		for(CandidateWorkExperienceModel m : experience) {
			String date = "";
			
			String dateFormat = (String) (session).getAttribute("DATEFORMAT");
			if(m.getWorkFrom() != null && m.getWorkFrom() != "") {
				date = DateFormatter.dateFormat(m.getWorkFrom(),dateFormat);
				m.setWorkFrom(date);
			}
			if(m.getWorkTill() != null && m.getWorkTill() != "") {
				date = DateFormatter.dateFormat(m.getWorkTill(),dateFormat);
				m.setWorkTill(date);
			}
		}
		
		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}
		
		resp.setBody(experience);
		
		logger.info("Method : viewWorkExperience ends");
		return resp.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-candidate-delete-workExperience")
	public @ResponseBody JsonResponse<Object> deleteWorkExperience(Model model, HttpSession session, @RequestParam String id){
		
		logger.info("Method : deleteWorkExperience starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "deleteWorkExperience?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : deleteWorkExperience ends");
		
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/view-candidate-add-award")
	public @ResponseBody JsonResponse<Object> addAward(Model model, HttpSession session, @RequestBody CandidateAwardsModel reqModel){
		
		logger.info("Method : addWorkExperience starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}
		reqModel.setCreatedBy(userId);
		try {
			resp = restTemplate.postForObject(env.getRecruitment() + "addAward", reqModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : addWorkExperience ends");
		
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-candidate-view-award")
	public @ResponseBody List<CandidateAwardsModel> viewAward(HttpSession session, @RequestParam String id ) {
		logger.info("Method : viewAward starts");

		JsonResponse<List<CandidateAwardsModel>> resp = new JsonResponse<List<CandidateAwardsModel>>();
		
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "viewAward?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		} 
		
		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		
		logger.info("Method : viewAward ends");
		return resp.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-candidate-delete-award")
	public @ResponseBody JsonResponse<Object> deleteAward(Model model, HttpSession session, @RequestParam String id){
		
		logger.info("Method : deleteAward starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "deleteAward?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : deleteAward ends");
		
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/view-candidate-add-reference")
	public @ResponseBody JsonResponse<Object> addReference(Model model, HttpSession session, @RequestBody CandidateReferenceModel reqModel){
		
		logger.info("Method : addReference starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}
		reqModel.setCreatedBy(userId);
		try {
			resp = restTemplate.postForObject(env.getRecruitment() + "addReference", reqModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : addReference ends");
		
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-candidate-view-reference")
	public @ResponseBody List<CandidateReferenceModel> viewReference(HttpSession session, @RequestParam String id ) {
		logger.info("Method : viewReference starts");

		JsonResponse<List<CandidateReferenceModel>> resp = new JsonResponse<List<CandidateReferenceModel>>();
		
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "viewReference?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		} 
		
		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		
		logger.info("Method : viewReference ends");
		return resp.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-candidate-delete-reference")
	public @ResponseBody JsonResponse<Object> deleteReference(Model model, HttpSession session, @RequestParam String id){
		
		logger.info("Method : deleteReference starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "deleteReference?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : deleteReference ends");
		
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/view-candidate-add-source")
	public @ResponseBody JsonResponse<Object> addSource(Model model, HttpSession session, @RequestBody CandidateSourceModel reqModel){
		
		logger.info("Method : addSource starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}
		reqModel.setCreatedBy(userId);
		try {
			resp = restTemplate.postForObject(env.getRecruitment() + "addSource", reqModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : addSource ends");
		
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-candidate-view-source")
	public @ResponseBody List<CandidateSourceModel> viewSource(HttpSession session, @RequestParam String id ) {
		logger.info("Method : viewSource starts");

		JsonResponse<List<CandidateSourceModel>> resp = new JsonResponse<List<CandidateSourceModel>>();
		
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "viewSource?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		} 
		
		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		
		logger.info("Method : viewSource ends");
		return resp.getBody();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-candidate-delete-source")
	public @ResponseBody JsonResponse<Object> deleteSource(Model model, HttpSession session, @RequestParam String id){
		
		logger.info("Method : deleteSource starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			resp = restTemplate.getForObject(env.getRecruitment() + "deleteSource?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : deleteSource ends");
		
		return resp;
	}
	
		@SuppressWarnings("unchecked")
		@PostMapping("/view-candidate-apply-requisition")
		public @ResponseBody JsonResponse<Object> applyRequisition(Model model, HttpSession session, @RequestBody CandidateApplyRequisitionModel applyReq){
			
			logger.info("Method : applyRequisition starts");
			
			JsonResponse<Object> resp = new JsonResponse<Object>();
			
			String userId = "";
			try {
				userId = (String) session.getAttribute("USER_ID");

			} catch (Exception e) {
				e.printStackTrace();
			}
			applyReq.setCreatedBy(userId);
			try {
				resp = restTemplate.postForObject(env.getRecruitment() + "applyRequisition", applyReq, JsonResponse.class);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			if (resp.getMessage() == "" || resp.getMessage() == null) {
				
				resp.setMessage("Success");
			}

			
			logger.info("Method : applyRequisition ends");
			
			return resp;
		}
		
		/*@SuppressWarnings("unchecked")
		@GetMapping("/view-candidate-apply-req-list")
		public @ResponseBody JsonResponse<List<CandidateApplyRequisitionModel>> getApplyReqList(Model model, HttpSession session, @RequestParam String id){
			
			logger.info("Method : getApplyReqList starts");
			
			JsonResponse<List<CandidateApplyRequisitionModel>> resp = new JsonResponse<List<CandidateApplyRequisitionModel>>();
			
			try {
				resp = restTemplate.getForObject(env.getRecruitment() + "getApplyReqList?id=" + id, JsonResponse.class);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			if (resp.getMessage() == "" || resp.getMessage() == null) {
				
				resp.setMessage("Success");
			}

			
			logger.info("Method : getApplyReqList ends");
			
			return resp;
		}*/
		
		@SuppressWarnings("unchecked")
		@GetMapping("/view-candidate-requisition")
		public @ResponseBody List<AddRecruitentModel> getRequisitionOfCandidate(Model model, HttpSession session, @RequestParam String id){
			
			logger.info("Method : getRequisitionOfCandidate starts");
			
			JsonResponse<List<AddRecruitentModel>> resp = new JsonResponse<List<AddRecruitentModel>>();
			
			try {
				resp = restTemplate.getForObject(env.getRecruitment() + "getRequisitionOfCandidate?id=" + id, JsonResponse.class);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			ObjectMapper mapper = new ObjectMapper();

			List<AddRecruitentModel> req = mapper.convertValue(resp.getBody(),
					new TypeReference<List<AddRecruitentModel>>() {
					});
			for(AddRecruitentModel m : req) {
				String date = "";
				
				String dateFormat = (String) (session).getAttribute("DATEFORMAT");
				if(m.getJoinDate() != null && m.getJoinDate() != "") {
					date = DateFormatter.dateFormat(m.getJoinDate(),dateFormat);
					m.setJoinDate(date);
				}

			}
			
			if (resp.getMessage() == "" || resp.getMessage() == null) {
				
				resp.setMessage("Success");
			}

			resp.setBody(req);
			logger.info("Method : getRequisitionOfCandidate ends");
			
			return resp.getBody();
		}
		
		//Document Upload
		
		@SuppressWarnings("unchecked")
		@RequestMapping(value = "view-candidate-document-list", method = { RequestMethod.POST })
		public @ResponseBody JsonResponse<Object> addShortListcandidate(@RequestBody CandidateDocumentModel shortList,
				Model model,MultipartFile file, HttpSession session)throws IOException {
			logger.info("Method : addShortListcandidate function starts");

			JsonResponse<Object> resp = new JsonResponse<Object>();
			String userId = "";
			
			try {
				userId = (String) session.getAttribute("USER_ID");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			shortList.setCreatedBy(userId);

			for (InventoryVendorDocumentModel a : shortList.getDocumentList()) {
				if (a.getImageNameEdit() != null && a.getImageNameEdit() != "") {
					a.setFileName(a.getImageNameEdit());
				} else {
					if (a.getFileName() != null && a.getFileName() != "") {
						String delimiters = "\\.";
						String[] x = a.getFileName().split(delimiters);

						if (x[1].contentEquals("png") || x[1].contentEquals("jpg") || x[1].contentEquals("jpeg")) {

							for (String s1 : a.getDocumentFile()) {
								if (s1 != null)
									try {
										byte[] bytes = Base64.getDecoder().decode(s1);
										String imageName = saveAllImage(bytes);
										a.setFileName(imageName);
									} catch (Exception e) {
										e.printStackTrace();
									}
							}
						} else if (x[1].contentEquals("pdf")) {
							for (String s1 : a.getDocumentFile()) {
								try {
									byte[] bytes = Base64.getDecoder().decode(s1);
									String pdfName = saveAllPdf(bytes);
									a.setFileName(pdfName);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						} else if (x[1].contentEquals("docx")) {
							for (String s1 : a.getDocumentFile()) {
								try {
									byte[] bytes = Base64.getDecoder().decode(s1);
									String pdfName = saveAllDocx(bytes);
									a.setFileName(pdfName);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						} else if (x[1].contentEquals("doc")) {
							for (String s1 : a.getDocumentFile()) {
								try {
									byte[] bytes = Base64.getDecoder().decode(s1);
									String pdfName = saveAllDoc(bytes);
									a.setFileName(pdfName);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						} else if (x[1].contentEquals("xls")) {
							for (String s1 : a.getDocumentFile()) {
								try {
									byte[] bytes = Base64.getDecoder().decode(s1);
									String pdfName = saveAllXls(bytes);
									a.setFileName(pdfName);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						} else if (x[1].contentEquals("xlsx")) {
							for (String s1 : a.getDocumentFile()) {
								try {
									byte[] bytes = Base64.getDecoder().decode(s1);
									String pdfName = saveAllXlsx(bytes);
									a.setFileName(pdfName);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
			
				try {
					
					resp = restTemplate.postForObject(env.getRecruitment() + "addDocumentList", shortList,
							JsonResponse.class);
				} catch (RestClientException e) {
					e.printStackTrace();
				}
			
			String message = resp.getMessage();

			if (message != null && message != "") {

			} else {
				resp.setMessage("Success");
			}
			logger.info("Method : addShortListcandidate function Ends");
			return resp;
		}
		
		@GetMapping(value = { "view-candidate-edit-doc-ajax" })
		public @ResponseBody JsonResponse<List<InventoryVendorDocumentModel>> viewCandidateDocEdit(@RequestParam String id, HttpSession session,
				Model model) {
			logger.info("Method : viewCandidateDocEdit starts");
		JsonResponse<List<InventoryVendorDocumentModel>> resp = new JsonResponse<List<InventoryVendorDocumentModel>>();
			List<InventoryVendorDocumentModel> documentList = new ArrayList<InventoryVendorDocumentModel>();
			
			if (id != null && id != "") {
				try {
					
					InventoryVendorDocumentModel[] inventoryStockModel = restTemplate.getForObject(
							env.getRecruitment() + "viewCandidateDocEdit?id=" + id,
							InventoryVendorDocumentModel[].class);
					documentList = Arrays.asList(inventoryStockModel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
				
				if (documentList != null) {
					for (InventoryVendorDocumentModel m : documentList) {

						if (m.getFileName() != null && m.getFileName() != "") {

							String[] extension = m.getFileName().split("\\.");
							if (extension.length == 2) {
								if (extension[1].equals("xls") || extension[1].equals("xlsx")) {

									String docPath = "<i class=\"fa fa-file-excel-o excel\" title= " + m.getFileName()
											+ "></i> ";

									m.setAction(docPath);
								}
								if (extension[1].equals("pdf")) {
									String docPath = " <i class=\"fa fa-file-pdf-o excel pdf\"   title=" + m.getFileName()
											+ " ;></i> ";

									m.setAction(docPath);
								}
								if (extension[1].equals("doc") || extension[1].equals("dox")
										|| extension[1].equals("docx")) {
									String docPath = " <i class=\"fa fa-file-word-o \" aria-hidden=\"true\"  title="
											+ m.getFileName() + "></i> ";
									m.setAction(docPath);
								}
								if (extension[1].equals("png") || extension[1].equals("jpg")
										|| extension[1].equals("jpeg")) {
									String docPath = " <i class=\"fa fa-picture-o \"\" aria-hidden=\"true\" title="
											+ m.getFileName() + "></i>  ";
									m.setAction(docPath);
								}
							} else {
								m.setAction("N/A");
								m.setFileName("");
							}
						} else {
							m.setAction("N/A");
							m.setFileName("");
						}
						m.setAction("<a href=\"/document/procurment/" + m.getFileName() + "\" target=\"_blank\" >"
								+ m.getAction() + "</a>");

					}
				}
				
				//String message = resp.getMessage();
				
				resp.setBody(documentList);
				
				if (resp.getBody().size() > 0) {
					resp.setMessage("success");
				} else {
					resp.setMessage("success");
				}
				
				
			System.out.println(resp);
			logger.info("Method : viewCandidateDocEdit ends");
			return resp;
		}
		
		// SAVE ALL FILE

		public String saveAllImage(byte[] imageBytes) {
			logger.info("Method : saveAllImage starts");

			String imageName = null;

			try {
				if (imageBytes != null) {
					long nowTime = new Date().getTime();
					imageName = nowTime + ".png";
				}

				Path path = Paths.get(env.getFileUploadProcurement() + imageName);
				if (imageBytes != null) {
					Files.write(path, imageBytes);

					ByteArrayInputStream in = new ByteArrayInputStream(imageBytes);
					Integer height = 50;
					Integer width = 50;

					try {
						BufferedImage img = ImageIO.read(in);
						if (height == 0) {
							height = (width * img.getHeight()) / img.getWidth();
						}
						if (width == 0) {
							width = (height * img.getWidth()) / img.getHeight();
						}

						Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

						BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
						imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);

						ByteArrayOutputStream buffer = new ByteArrayOutputStream();

						ImageIO.write(imageBuff, "png", buffer);

						byte[] thumb = buffer.toByteArray();

						Path pathThumb = Paths.get(env.getFileUploadProcurement() + "thumb/" + imageName);
						Files.write(pathThumb, thumb);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info("Method : saveAllImage ends");
			return imageName;
		}

		/*
		 * for save all pdf in folder and return name
		 */

		public String saveAllPdf(byte[] imageBytes) {
			logger.info("Method : saveAllPdf starts");

			String pdfName = null;

			try {
				if (imageBytes != null) {
					long nowTime = new Date().getTime();
					pdfName = nowTime + ".pdf";
				}

				Path path = Paths.get(env.getFileUploadProcurement() + pdfName);
				if (imageBytes != null) {
					Files.write(path, imageBytes);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info("Method : saveAllPdf ends");
			return pdfName;
		}

		public String saveAllDocx(byte[] imageBytes) {
			logger.info("Method : saveAllDocx starts");

			String pdfName = null;

			try {
				if (imageBytes != null) {
					long nowTime = new Date().getTime();
					pdfName = nowTime + ".docx";
				}

				Path path = Paths.get(env.getFileUploadProcurement() + pdfName);
				if (imageBytes != null) {
					Files.write(path, imageBytes);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info("Method : saveAllDocx ends");
			return pdfName;
		}

		public String saveAllDoc(byte[] imageBytes) {
			logger.info("Method : saveAllDoc starts");

			String pdfName = null;

			try {
				if (imageBytes != null) {
					long nowTime = new Date().getTime();
					pdfName = nowTime + ".doc";
				}

				Path path = Paths.get(env.getFileUploadProcurement() + pdfName);
				if (imageBytes != null) {
					Files.write(path, imageBytes);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info("Method : saveAllDoc ends");
			return pdfName;
		}

		public String saveAllXls(byte[] imageBytes) {
			logger.info("Method : saveAllDoc starts");

			String pdfName = null;

			try {
				if (imageBytes != null) {
					long nowTime = new Date().getTime();
					pdfName = nowTime + ".xls";
				}

				Path path = Paths.get(env.getFileUploadProcurement() + pdfName);
				if (imageBytes != null) {
					Files.write(path, imageBytes);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info("Method : saveAllDoc ends");
			return pdfName;
		}

		public String saveAllXlsx(byte[] imageBytes) {
			logger.info("Method : saveAllDoc starts");

			String pdfName = null;

			try {
				if (imageBytes != null) {
					long nowTime = new Date().getTime();
					pdfName = nowTime + ".xlsx";
				}

				Path path = Paths.get(env.getFileUploadProcurement() + pdfName);
				if (imageBytes != null) {
					Files.write(path, imageBytes);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info("Method : saveAllDoc ends");
			return pdfName;
		}
		
		
}
