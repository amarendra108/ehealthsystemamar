package nirmalya.aathithya.webmodule.employee.controller;



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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.employee.model.EmployeeDocumentModel;
import nirmalya.aathithya.webmodule.employee.model.ManageEmployeeAddressModel;
import nirmalya.aathithya.webmodule.employee.model.ManageEmployeeBankDetailsModel;
import nirmalya.aathithya.webmodule.employee.model.ManageEmployeeBenifitModel;
import nirmalya.aathithya.webmodule.employee.model.ManageEmployeeDependentModel;
import nirmalya.aathithya.webmodule.employee.model.ManageEmployeeInsuranceModel;
import nirmalya.aathithya.webmodule.employee.model.ManageEmployeeModel;
import nirmalya.aathithya.webmodule.employee.model.ManageEmployeeWorkdetailsModel;
import nirmalya.aathithya.webmodule.procurment.model.InventoryVendorDocumentModel;
import nirmalya.aathithya.webmodule.procurment.model.InventoryVendorRfqModel;

/*
 * @author Nirmalya labs
 */
@Controller
@RequestMapping(value = { "employee/" })

public class ManageEmployeeController {
	Logger logger = LoggerFactory.getLogger(ManageEmployeeController.class);

	@Autowired
	RestTemplate restClient;

	@Autowired
	EnvironmentVaribles env;

	// Summary
	@GetMapping("/view-manage-employee")
	public String employee(Model model, HttpSession session) {

		logger.info("Method : employee starts");

		try {
			DropDownModel[] Gender = restClient.getForObject(env.getEmployeeUrl() + "getgenderList1",
					DropDownModel[].class);
			List<DropDownModel> genderTypeList = Arrays.asList(Gender);

			model.addAttribute("genderTypeList", genderTypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		try {
			DropDownModel[] Nationality = restClient.getForObject(env.getEmployeeUrl() + "getnationalityList1",
					DropDownModel[].class);
			List<DropDownModel> nationalityList = Arrays.asList(Nationality);

			model.addAttribute("nationalityList", nationalityList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] BloodGroup = restClient.getForObject(env.getEmployeeUrl() + "getbloodgroupList1",
					DropDownModel[].class);
			List<DropDownModel> bloodgroupList = Arrays.asList(BloodGroup);

			model.addAttribute("bloodgroupList", bloodgroupList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] MaritalStatus = restClient.getForObject(env.getEmployeeUrl() + "getmaritalstatusList1",
					DropDownModel[].class);
			List<DropDownModel> maritalstatusList = Arrays.asList(MaritalStatus);

			model.addAttribute("maritalstatusList", maritalstatusList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		try {
			DropDownModel[] Country = restClient.getForObject(env.getEmployeeUrl() + "getCountryList",
					DropDownModel[].class);
			List<DropDownModel> counntryList = Arrays.asList(Country);

			model.addAttribute("counntryList", counntryList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		try {

			DropDownModel[] addressType = restClient.getForObject(env.getRecruitment() + "addressTypeList",
			DropDownModel[].class);
			List<DropDownModel> addressTypeList = Arrays.asList(addressType);
			model.addAttribute("addressTypeList", addressTypeList);
			} catch (RestClientException e) {
			e.printStackTrace();
			}
		
		try {
			DropDownModel[] JobType = restClient.getForObject(env.getEmployeeUrl() + "getJobType1",
					DropDownModel[].class);
			List<DropDownModel> jobtypeList = Arrays.asList(JobType);

			model.addAttribute("jobtypeList", jobtypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] Department = restClient.getForObject(env.getEmployeeUrl() + "getDepartmentList1",
					DropDownModel[].class);
			List<DropDownModel> DepartmentList = Arrays.asList(Department);

			model.addAttribute("DepartmentList", DepartmentList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}try {
			DropDownModel[] JobType = restClient.getForObject(env.getEmployeeUrl() + "getJobType1",
					DropDownModel[].class);
			List<DropDownModel> jobtypeList = Arrays.asList(JobType);

			model.addAttribute("jobtypeList", jobtypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] Department = restClient.getForObject(env.getEmployeeUrl() + "getDepartmentList1",
					DropDownModel[].class);
			List<DropDownModel> DepartmentList = Arrays.asList(Department);

			model.addAttribute("DepartmentList", DepartmentList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		try {
			DropDownModel[] TimesheetType = restClient.getForObject(env.getEmployeeUrl() + "getTimesheetType1",
					DropDownModel[].class);
			List<DropDownModel> TimesheetList = Arrays.asList(TimesheetType);

			model.addAttribute("TimesheetList", TimesheetList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		try {
			
			DropDownModel[] band = restClient.getForObject(env.getRecruitment() + "bandList",
					DropDownModel[].class);
			List<DropDownModel> bandList = Arrays.asList(band);
			model.addAttribute("bandList", bandList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] employmentType = restClient.getForObject(env.getEmployeeUrl() + "getemploymentType1",
					DropDownModel[].class);
			List<DropDownModel> employmentstatusList = Arrays.asList(employmentType);

			model.addAttribute("employmentstatusList", employmentstatusList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		try {
			
			DropDownModel[] manager = restClient.getForObject(env.getEmployeeUrl() + "EmployeeList",
					DropDownModel[].class);
			List<DropDownModel> managerList = Arrays.asList(manager);
			model.addAttribute("EmployeeList", managerList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		try {
			DropDownModel[] Benefits = restClient.getForObject(env.getEmployeeUrl() + "getBenefits",
					DropDownModel[].class);
			List<DropDownModel> benefitsList = Arrays.asList(Benefits);

			model.addAttribute("benefitsList", benefitsList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		try {
			DropDownModel[] dependentType = restClient.getForObject(env.getEmployeeUrl() + "dependentTypeList",
					DropDownModel[].class);
			List<DropDownModel> dependentTypeList = Arrays.asList(dependentType);

			model.addAttribute("dependentTypeList", dependentTypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		try {
			DropDownModel[] relationship = restClient.getForObject(env.getEmployeeUrl() + "relationshipList",
					DropDownModel[].class);
			List<DropDownModel> relationshipList = Arrays.asList(relationship);

			model.addAttribute("relationshipList", relationshipList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		try {
			DropDownModel[] Bank = restClient.getForObject(env.getEmployeeUrl() + "getBankNameList",
					DropDownModel[].class);
			List<DropDownModel> BankNameList = Arrays.asList(Bank);

			model.addAttribute("BankNameList", BankNameList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		try {
			DropDownModel[] Bank = restClient.getForObject(env.getEmployeeUrl() + "insuranceCompanyList",
					DropDownModel[].class);
			List<DropDownModel> insuranceCompanyList = Arrays.asList(Bank);

			model.addAttribute("insuranceCompanyList", insuranceCompanyList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		try {
			DropDownModel[] Bank = restClient.getForObject(env.getEmployeeUrl() + "documentTypeList",
					DropDownModel[].class);
			List<DropDownModel> documentTypeList = Arrays.asList(Bank);

			model.addAttribute("documentTypeList", documentTypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		logger.info("Method : employee ends");

		return "employee/view-manage-employee";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("view-manage-employee-manager-list")
	public @ResponseBody JsonResponse<List<DropDownModel>> getManager(Model model, @RequestParam String id,
			HttpSession session) {
		logger.info("Method : getManager starts");

		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();

		try {
			resp = restClient.getForObject(
					env.getEmployeeUrl() + "getManager?id="+ id,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :  getManager ends");
		return resp;
	}
	
	@PostMapping("/view-manage-employee-upload-file")
	public @ResponseBody JsonResponse<Object> uploadFile(@RequestParam("file") MultipartFile inputFile,
			HttpSession session) {
		logger.info("Method : employee uploadimage controller  starts");

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

		logger.info("Method : employee uploadimage controller ' ends");
		return response;
	}

	@PostMapping("/view-manage-employee-delete-file")
	public @ResponseBody JsonResponse<Object> deleteFile(HttpSession session) {
		logger.info("Method : deleteFile employee uploadimage controller starts");

		JsonResponse<Object> response = new JsonResponse<Object>();

		try {
			session.removeAttribute("employeePFile");
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : deleteFile employee uploadimage controller ends");
		return response;
	}

	
	
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "view-manage-employee-state-list" })
	public @ResponseBody JsonResponse<Object> getStateNameForemployee(Model model, @RequestBody String tCountry,
			BindingResult result) {
		logger.info("Method : getStateNameForemployee starts");
		
		JsonResponse<Object> res = new JsonResponse<Object>();
		
		try {
			res = restClient.getForObject(env.getMasterUrl() + "getStateListForLoc?id=" + tCountry,
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
		
		logger.info("Method : getStateNameForemployee ends");
		return res;

	}
	
	
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "view-manage-employee-city-list" })
	public @ResponseBody JsonResponse<Object> getCityForcity(Model model, @RequestBody String tCountry,
	BindingResult result) {
	logger.info("Method : getCityForLocation starts");

	JsonResponse<Object> res = new JsonResponse<Object>();

	try {
	res = restClient.getForObject(env.getMasterUrl() + "getCityForLocation?id=" + tCountry,
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

	logger.info("Method : getCityForcity ends");
	return res;

	}
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "view-manage-employee-state-list-bank" })
	public @ResponseBody JsonResponse<Object> getStateNameForemployeebank(Model model, @RequestBody String tCountry,
			BindingResult result) {
		logger.info("Method : getStateNameForemployee starts");
		
		JsonResponse<Object> res = new JsonResponse<Object>();
		
		try {
			res = restClient.getForObject(env.getMasterUrl() + "getStateListForLoc?id=" + tCountry,
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
		
		logger.info("Method : getStateNameForemployee ends");
		return res;

	}
	
	
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "view-manage-employee-city-list-bank" })
	public @ResponseBody JsonResponse<Object> getCityForcitybank(Model model, @RequestBody String tCountry,
	BindingResult result) {
	logger.info("Method : getCityForLocation starts");

	JsonResponse<Object> res = new JsonResponse<Object>();

	try {
	res = restClient.getForObject(env.getMasterUrl() + "getCityForLocation?id=" + tCountry,
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

	logger.info("Method : getCityForcity ends");
	return res;

	}
	
	
	
	@SuppressWarnings("unchecked")
	@PostMapping("/view-manage-employee-master-save")
	public @ResponseBody JsonResponse<Object> savemanangeemployee(@RequestBody ManageEmployeeModel manageEmployeeModel,
			HttpSession session) {
		logger.info("Method : saveemployee personalMaster starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";
		String dateFormat = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}

		manageEmployeeModel.setCreatedBy(userId);

		MultipartFile inputFile = (MultipartFile) session.getAttribute("employeePFile");
		byte[] bytes;
		String imageName = null;

		if (inputFile != null) {
			try {
				bytes = inputFile.getBytes();
				String[] fileType = inputFile.getContentType().split("/");
				imageName = saveAllImage(bytes, fileType[1]);
				System.out.println(imageName);

				manageEmployeeModel.setFileEmployeeimg(imageName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		if(manageEmployeeModel.getDob()!=null && manageEmployeeModel.getDob()!="") {
			manageEmployeeModel.setDob(DateFormatter.inputDateFormat(manageEmployeeModel.getDob(), dateFormat));
		}
		
		try {

			resp = restClient.postForObject(env.getEmployeeUrl() + "saveemployeeMaster", manageEmployeeModel,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			session.removeAttribute("employeePFile");
			resp.setMessage("Success");
		}

		logger.info("Method : saveemployee personalMaster End");
		return resp;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("view-manage-employee-master-through-ajax")
	public @ResponseBody List<ManageEmployeeModel> empolyeeaja(Model model, HttpSession session) {
		logger.info("Method : vendorLocationThroughAjax starts");

		JsonResponse<List<ManageEmployeeModel>> jsonResponse = new JsonResponse<List<ManageEmployeeModel>>();

		try {

			jsonResponse = restClient.getForObject(env.getEmployeeUrl() + "view-manage-employee-maste",
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			List<ManageEmployeeModel> addreq = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<List<ManageEmployeeModel>>() {
					});
			
			String dateFormat = (String) (session).getAttribute("DATEFORMAT");
			
			for(ManageEmployeeModel m : addreq) {
				
				if(m.getDob() != null && m.getDob() != "") {
					m.setDob(DateFormatter.dateFormat(m.getDob(),dateFormat));
				}
			}
			
			jsonResponse.setBody(addreq);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method ; vendorLocationThroughAjax ends");

		return jsonResponse.getBody();
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

	@SuppressWarnings("unchecked")
	@PostMapping("/view-manage-employee-address-save")
	public @ResponseBody JsonResponse<Object> saveemployeeaddress(
			@RequestBody ManageEmployeeAddressModel manageEmployeeAddressModel, HttpSession session) {
		logger.info("Method : saveemployeeaddress starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		manageEmployeeAddressModel.setCreatedBy(userId);

		try {
			resp = restClient.postForObject(env.getEmployeeUrl() + "saveemployeeaddress", manageEmployeeAddressModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {

			resp.setMessage("Success");
		}


		logger.info("Method : saveemployeeaddress starts");
		return resp;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("view-manage-employee-address-through-ajax")
	public @ResponseBody List<ManageEmployeeAddressModel> vendorLocationThroughAjax(Model model,
			HttpServletRequest request, @RequestParam String id) {
		logger.info("Method : vendorLocationThroughAjax starts");

		JsonResponse<List<ManageEmployeeAddressModel>> jsonResponse = new JsonResponse<List<ManageEmployeeAddressModel>>();

		try {

			jsonResponse = restClient.getForObject(env.getEmployeeUrl() + "viewEmployeeadd?id=" + id,
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			List<ManageEmployeeAddressModel> addreq = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<List<ManageEmployeeAddressModel>>() {
					});

			for(ManageEmployeeAddressModel m : addreq) {
				if(m.getStatus().equals("1")) {
					m.setStatusId("Active");
				} else if(m.getStatus().equals("0")){
					m.setStatusId("Inactive");
				}
			}
			
			jsonResponse.setBody(addreq);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method ; vendorLocationThroughAjax ends");

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/view-manage-employee-workdetails-save")
	public @ResponseBody JsonResponse<Object> saveemployeeworkdetails(
			@RequestBody ManageEmployeeWorkdetailsModel manageEmployeeWorkdetailsModel, HttpSession session) {
		logger.info("Method : saveemployeeworkdetails starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";
		String dateFormat = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}

		manageEmployeeWorkdetailsModel.setCreatedBy(userId);

		if(manageEmployeeWorkdetailsModel.getStartDate()!=null && manageEmployeeWorkdetailsModel.getStartDate()!="") {
			manageEmployeeWorkdetailsModel.setStartDate(DateFormatter.inputDateFormat(manageEmployeeWorkdetailsModel.getStartDate(), dateFormat));
		}
		if(manageEmployeeWorkdetailsModel.getEndDate()!=null && manageEmployeeWorkdetailsModel.getEndDate()!="") {
			manageEmployeeWorkdetailsModel.setEndDate(DateFormatter.inputDateFormat(manageEmployeeWorkdetailsModel.getEndDate(), dateFormat));
		}
		
		try {
			resp = restClient.postForObject(env.getEmployeeUrl() + "saveemployeeworkdetails",
					manageEmployeeWorkdetailsModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			session.removeAttribute("employeePFile");
			resp.setMessage("Success");
		}

		System.out.println("Success");

		logger.info("Method : saveemployeeworkdetails starts");
		return resp;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("view-manage-employee-work-ajax")
	public @ResponseBody List<ManageEmployeeWorkdetailsModel> viewmanageemployeeworkajax(HttpSession session, @RequestParam String id) {
		logger.info("Method : viewmanageemployeeworkajax starts");

		JsonResponse<List<ManageEmployeeWorkdetailsModel>> jsonResponse = new JsonResponse<List<ManageEmployeeWorkdetailsModel>>();

		try {

			jsonResponse = restClient.getForObject(env.getEmployeeUrl() + "viewEmployeework?id=" + id,
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			List<ManageEmployeeWorkdetailsModel> addreq = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<List<ManageEmployeeWorkdetailsModel>>() {
					});

			String dateFormat = (String) (session).getAttribute("DATEFORMAT");
			for(ManageEmployeeWorkdetailsModel m :addreq) {
				if(m.getManagerName() == null || m.getManagerName() == "" || m.getManagerName() == "null") {
					m.setManagerName("");
				}
				if(m.getStartDate() != null && m.getStartDate() != "") {
					m.setStartDate(DateFormatter.dateFormat(m.getStartDate(),dateFormat));
				}
				if(m.getEndDate() != null && m.getEndDate() != "") {
					m.setEndDate(DateFormatter.dateFormat(m.getEndDate(),dateFormat));
				}
				
			}
			
			jsonResponse.setBody(addreq);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method ; viewmanageemployeeworkajax ends");

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@PostMapping("view-manage-employee-delete-address-emp")
	public @ResponseBody JsonResponse<Object> deleteempaddress(Model model, @RequestParam String id,
			HttpSession session) {
		logger.info("Method : delectRequistion starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		String createdBy = "";

		try {
			createdBy = (String) session.getAttribute("USER_ID");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			resp = restClient.getForObject(env.getEmployeeUrl() + "deleteaddressemp?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("success");
		}

		logger.info("Method :  deleteempaddress ends");
		return resp;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/view-manage-employee-dependent-save")
	public @ResponseBody JsonResponse<Object> saveemployeedependent(
			@RequestBody ManageEmployeeDependentModel manageEmployeeDependentModel, HttpSession session) {
		logger.info("Method : saveemployeedependent starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";
		String dateFormat = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}

		manageEmployeeDependentModel.setCreatedBy(userId);

		if(manageEmployeeDependentModel.getDepdob()!=null && manageEmployeeDependentModel.getDepdob()!="") {
			manageEmployeeDependentModel.setDepdob(DateFormatter.inputDateFormat(manageEmployeeDependentModel.getDepdob(), dateFormat));
		}
		
		try {
			resp = restClient.postForObject(env.getEmployeeUrl() + "saveemployeedependent",
					manageEmployeeDependentModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			session.removeAttribute("employeePFile");
			resp.setMessage("Success");
		}

		System.out.println("Success");

		logger.info("Method : saveemployeedependent starts");
		return resp;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("view-manage-employee-dependent-ajax")
	public @ResponseBody List<ManageEmployeeDependentModel> viewmanageemployeedependent(HttpSession session, @RequestParam String id) {
		logger.info("Method : viewmanageemployeedependent starts");

		JsonResponse<List<ManageEmployeeDependentModel>> jsonResponse = new JsonResponse<List<ManageEmployeeDependentModel>>();

		try {

			jsonResponse = restClient.getForObject(env.getEmployeeUrl() + "viewempdepent?id=" + id, JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			List<ManageEmployeeDependentModel> addreq = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<List<ManageEmployeeDependentModel>>() {
					});

			String dateFormat = (String) (session).getAttribute("DATEFORMAT");
			
			for(ManageEmployeeDependentModel m : addreq) {
				
				if(m.getDepdob() != null && m.getDepdob() != "") {
					m.setDepdob(DateFormatter.dateFormat(m.getDepdob(),dateFormat));
				}
			}
			
			jsonResponse.setBody(addreq);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method ; viewmanageemployeedependent ends");

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/view-manage-employee-bankdetails-save")
	public @ResponseBody JsonResponse<Object> saveemployeebank(
			@RequestBody ManageEmployeeBankDetailsModel manageEmployeeBankDetailsModel, HttpSession session) {
		logger.info("Method : saveemployeebank starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		manageEmployeeBankDetailsModel.setCreatedBy(userId);

		try {
			resp = restClient.postForObject(env.getEmployeeUrl() + "saveemployeebank",
					manageEmployeeBankDetailsModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			session.removeAttribute("employeePFile");
			resp.setMessage("Success");
		}

		System.out.println("Success");

		logger.info("Method : saveemployeebank starts");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("view-manage-employee-bankdetails-ajax")
	public @ResponseBody List<ManageEmployeeBankDetailsModel> viewmanageemployeebankdetails(Model model,
			HttpServletRequest request, @RequestParam String id) {
		logger.info("Method : viewmanageemployeebankdetails starts");

		JsonResponse<List<ManageEmployeeBankDetailsModel>> jsonResponse = new JsonResponse<List<ManageEmployeeBankDetailsModel>>();

		try {

			jsonResponse = restClient.getForObject(env.getEmployeeUrl() + "viewebank?id=" + id, JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			List<ManageEmployeeBankDetailsModel> addreq = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<List<ManageEmployeeBankDetailsModel>>() {
					});

			jsonResponse.setBody(addreq);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method ; viewmanageemployeedependent ends");

		return jsonResponse.getBody();
	}
	
	
	
	@SuppressWarnings("unchecked")
	@PostMapping("/view-manage-employee-insurancedetails-save")
	public @ResponseBody JsonResponse<Object> saveempinsurance(
			@RequestBody ManageEmployeeInsuranceModel manageEmployeeInsuranceModel, HttpSession session) {
		logger.info("Method : saveempinsurance starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";
		String dateFormat = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}

		manageEmployeeInsuranceModel.setCreatedBy(userId);

		if(manageEmployeeInsuranceModel.getEifromdate()!=null && manageEmployeeInsuranceModel.getEifromdate()!="") {
			manageEmployeeInsuranceModel.setEifromdate(DateFormatter.inputDateFormat(manageEmployeeInsuranceModel.getEifromdate(), dateFormat));
		}
		if(manageEmployeeInsuranceModel.getEitodate()!=null && manageEmployeeInsuranceModel.getEitodate()!="") {
			manageEmployeeInsuranceModel.setEitodate(DateFormatter.inputDateFormat(manageEmployeeInsuranceModel.getEitodate(), dateFormat));
		}
		
		try {
			resp = restClient.postForObject(env.getEmployeeUrl() + "saveempinsurance",
					manageEmployeeInsuranceModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			session.removeAttribute("employeePFile");
			resp.setMessage("Success");
		}

		//System.out.println("Success");

		logger.info("Method : saveempinsurance starts");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/view-manage-employee-benifit")
	public @ResponseBody JsonResponse<Object> saveempbenifit(
			@RequestBody ManageEmployeeBenifitModel manageEmployeeBenifitModel, HttpSession session) {
		logger.info("Method : saveempbenifit starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		manageEmployeeBenifitModel.setCreatedby(userId);

		try {
			resp = restClient.postForObject(env.getEmployeeUrl() + "savebenifit",
					manageEmployeeBenifitModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			session.removeAttribute("employeePFile");
			resp.setMessage("Success");
		}

		//System.out.println("Success");

		logger.info("Method : saveempbenifit starts");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("view-manage-employee-benifit-ajax")
	public @ResponseBody JsonResponse<List<ManageEmployeeBenifitModel>> viewempbenifit(Model model,
			HttpServletRequest request, @RequestParam String id) {
		logger.info("Method : ManageEmployeeBenifitModel starts");

		JsonResponse<List<ManageEmployeeBenifitModel>> jsonResponse = new JsonResponse<List<ManageEmployeeBenifitModel>>();

		try {

			jsonResponse = restClient.getForObject(env.getEmployeeUrl() + "viewbenifit?id=" + id, JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			List<ManageEmployeeBenifitModel> addreq = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<List<ManageEmployeeBenifitModel>>() {
					});

			jsonResponse.setBody(addreq);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if(jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setMessage("Unsuccess");
		} else {
				jsonResponse.setMessage("Success");
		}
		
		logger.info("Method ; ManageEmployeeBenifitModel ends");

		return jsonResponse;
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("view-manage-employee-insurancedetails-ajax")
	public @ResponseBody List<ManageEmployeeInsuranceModel> viewmanageemployeeinsurancedetails(HttpSession session, @RequestParam String id) {
		logger.info("Method : viewmanageemployeeinsurancedetails starts");

		JsonResponse<List<ManageEmployeeInsuranceModel>> jsonResponse = new JsonResponse<List<ManageEmployeeInsuranceModel>>();

		try {

			jsonResponse = restClient.getForObject(env.getEmployeeUrl() + "vieweinsurance?id=" + id, JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			List<ManageEmployeeInsuranceModel> addreq = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<List<ManageEmployeeInsuranceModel>>() {
					});

			String dateFormat = (String) (session).getAttribute("DATEFORMAT");
			
			for(ManageEmployeeInsuranceModel m : addreq) {
				
				if(m.getEifromdate() != null && m.getEifromdate() != "") {
					m.setEifromdate(DateFormatter.dateFormat(m.getEifromdate(),dateFormat));
				}
				if(m.getEitodate() != null && m.getEitodate() != "") {
					m.setEitodate(DateFormatter.dateFormat(m.getEitodate(),dateFormat));
				}
			
			}
			
			jsonResponse.setBody(addreq);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method ; viewmanageemployeeinsurancedetails ends");
		
		return jsonResponse.getBody();
	}
	

	@SuppressWarnings("unchecked")
	@PostMapping("/view-manage-employee-master-edit")
	public @ResponseBody JsonResponse<Object> editmanageEmployeemaster(@RequestParam String employeeId,
			HttpSession session) {
		logger.info("Method : editmanageEmployeemaster starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restClient.getForObject(env.getEmployeeUrl() + "editmanageEmployeemasterById?id=" + employeeId,
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			ManageEmployeeModel empDetails = mapper.convertValue(resp.getBody(),
					new TypeReference<ManageEmployeeModel>() {
					});
			if (empDetails.getFileEmployeeimg() != null && empDetails.getFileEmployeeimg() != ""
					&& !empDetails.getFileEmployeeimg().equals("null")) {
					String fileEmployeeimg = env.getBaseURL() + "document/employee/" + empDetails.getFileEmployeeimg();

					empDetails.setFileEmployeeimg(fileEmployeeimg);
					}
			
			String dateFormat = (String) (session).getAttribute("DATEFORMAT");
			if(empDetails.getDob() != null && empDetails.getDob() != "") {
				empDetails.setDob(DateFormatter.dateFormat(empDetails.getDob(),dateFormat));
			}
			
			resp.setBody(empDetails);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			resp.setMessage("success");
		}

		logger.info("Method : editmanageEmployeemaster end");
		return resp;
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-manage-employee-address-delete")
	public @ResponseBody JsonResponse<Object> deleteAddress(Model model, HttpSession session, @RequestParam String id){
		
		logger.info("Method : deleteAddress starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			resp = restClient.getForObject(env.getEmployeeUrl() + "deleteAddress?id=" + id, JsonResponse.class);
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
	@GetMapping("/view-manage-employee-work-delete")
	public @ResponseBody JsonResponse<Object> deletework(Model model, HttpSession session, @RequestParam String id){
		
		logger.info("Method : deletework starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			resp = restClient.getForObject(env.getEmployeeUrl() + "deletework?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : deletework ends");
		
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-manage-employee-dependent-delete")
	public @ResponseBody JsonResponse<Object> deletedependent(Model model, HttpSession session, @RequestParam String id){
		
		logger.info("Method : deletedependent starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			resp = restClient.getForObject(env.getEmployeeUrl() + "deletedependent?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : deletedependent ends");
		
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-manage-employee-bank-delete")
	public @ResponseBody JsonResponse<Object> deletebank(Model model, HttpSession session, @RequestParam String id){
		
		logger.info("Method : deletebank starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			resp = restClient.getForObject(env.getEmployeeUrl() + "deletebank?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : deletebank ends");
		
		return resp;
	}
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/view-manage-employee-insurancedetails-delete")
	public @ResponseBody JsonResponse<Object> deleteinsurance(Model model, HttpSession session, @RequestParam String id){
		
		logger.info("Method : deleteinsurance starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			resp = restClient.getForObject(env.getEmployeeUrl() + "deleteinsurance?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {
			
			resp.setMessage("Success");
		}

		
		logger.info("Method : deleteinsurance ends");
		
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("view-manage-employee-team-list")
	public @ResponseBody JsonResponse<List<DropDownModel>> getTeam(Model model, @RequestParam String id,
			HttpSession session) {
		logger.info("Method : getTeam starts");

		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();

		try {
			resp = restClient.getForObject(
					env.getEmployeeUrl() + "getTeam?id="+ id,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :  getTeam ends");
		return resp;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/view-manage-employee-add-doc-ajax")
	public @ResponseBody JsonResponse<Object> addDoc(@RequestBody EmployeeDocumentModel employeeDocumentModel,
			HttpSession session) {
		logger.info("Method : addDoc starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		employeeDocumentModel.setCreatedBy(userId);

		for (InventoryVendorDocumentModel a : employeeDocumentModel.getDocumentList()) {
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
			resp = restClient.postForObject(env.getEmployeeUrl() + "addDoc", employeeDocumentModel,
					JsonResponse.class);

			
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			resp.setMessage("success");
		}

		logger.info("Method : addDoc end");
		return resp;
	}
	
	@GetMapping(value = { "view-manage-employee-edit-doc-ajax" })
	public @ResponseBody JsonResponse<List<InventoryVendorDocumentModel>> viewEmpDocEdit(@RequestParam String id, HttpSession session,
			Model model) {
		logger.info("Method : viewEmpDocEdit starts");
	JsonResponse<List<InventoryVendorDocumentModel>> resp = new JsonResponse<List<InventoryVendorDocumentModel>>();
		List<InventoryVendorDocumentModel> documentList = new ArrayList<InventoryVendorDocumentModel>();
		
		if (id != null && id != "") {
			try {
				
				InventoryVendorDocumentModel[] inventoryStockModel = restClient.getForObject(
						env.getEmployeeUrl() + "viewEmpDocEdit?id=" + id,
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
						}
					} else {
						m.setAction("N/A");
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
				resp.setMessage("Unsuccess");
			}
			
			
		System.out.println(resp);
		logger.info("Method : viewEmpDocEdit ends");
		return resp;
	}
	
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