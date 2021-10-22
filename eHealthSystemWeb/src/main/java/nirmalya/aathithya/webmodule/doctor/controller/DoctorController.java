package nirmalya.aathithya.webmodule.doctor.controller;

import java.math.BigInteger;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.notice.model.IntiateNoticeModel;
import nirmalya.aathithya.webmodule.patient.model.ImmunizationModel;
import nirmalya.aathithya.webmodule.patient.model.LifeStyleHistoryModel;
import nirmalya.aathithya.webmodule.patient.model.PatientDetailsNewModel;
import nirmalya.aathithya.webmodule.patient.model.PatientFamilyDetailsModel;
import nirmalya.aathithya.webmodule.patient.model.PatientHistoryModel;
import nirmalya.aathithya.webmodule.patient.model.PatientMedicationModel;
import nirmalya.aathithya.webmodule.patient.model.TreatmentTrackerModel;

import org.springframework.web.multipart.MultipartFile;
@Controller
@RequestMapping(value = { "doctor/" })
public class DoctorController {
	Logger logger = LoggerFactory.getLogger(DoctorController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;

	/*
	 * Get Mapping for view-doctor-dashboard
	 */


	/*
	 * View Doctor Dashboard
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("view-doctor-dashboard-view-Throughajax")
	public @ResponseBody List<IntiateNoticeModel> viewDocDashboardThroughAjax() {

		logger.info("Method : viewDocDashboardThroughAjax starts");

		JsonResponse<List<IntiateNoticeModel>> resp = new JsonResponse<List<IntiateNoticeModel>>();

		try {
			resp = restTemplate.getForObject(env.getNotice() + "rest-viewNoticeinitiate", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewDocDashboardThroughAjax ends");
		return resp.getBody();
	}

	///////////////////////////////////////////////////////////// MyPatient
	/*
	 * Get Mapping for view-my-patient'
	 */

//	@GetMapping("view-my-patient")
//	public String viewMyPatient(Model model, HttpSession session) {
//
//		logger.info("Method : viewMyPatient starts");
//
//		// drop down for employee list
//		try {
//			DropDownModel[] notice = restTemplate.getForObject(env.getNotice() + "getempLists", DropDownModel[].class);
//			List<DropDownModel> noticelists = Arrays.asList(notice);
//			model.addAttribute("emplists", noticelists);
//
//		} catch (RestClientException e) {
//			e.printStackTrace();
//		}
//
//		try {
//			DropDownModel[] notice = restTemplate.getForObject(env.getNotice() + "getdeptLists", DropDownModel[].class);
//			List<DropDownModel> noticelists = Arrays.asList(notice);
//			model.addAttribute("deptList", noticelists);
//
//		} catch (RestClientException e) {
//			e.printStackTrace();
//		}
//		logger.info("Method : viewMyPatient ends");
//		return "doctor/myPatient";
//	}
//
//	/////////////////////////////////
//
//	/*
//	 * View view-my-patient-view-Throughajax
//	 */
//	@SuppressWarnings("unchecked")
//	@GetMapping("view-my-patient-view-Throughajax")
//	public @ResponseBody List<IntiateNoticeModel> viewMyPatientThroughAjax() {
//
//		logger.info("Method : viewMyPatientThroughAjax starts");
//
//		JsonResponse<List<IntiateNoticeModel>> resp = new JsonResponse<List<IntiateNoticeModel>>();
//
//		try {
//			resp = restTemplate.getForObject(env.getNotice() + "rest-viewNoticeinitiate", JsonResponse.class);
//		} catch (RestClientException e) {
//			e.printStackTrace();
//		}
//
//		logger.info("Method : viewMyPatientThroughAjax ends");
//		return resp.getBody();
//	}

	///////////////////////////////////////////////////// view-doctor-profile
	/*
	 * Get Mapping for view-doctor-profile
	 */

	@GetMapping("view-doctor-profile")
	public String employee(Model model, HttpSession session) {

		logger.info("Method : employee starts");

		try {
			DropDownModel[] Gender = restTemplate.getForObject(env.getEmployeeUrl() + "getgenderList1",
					DropDownModel[].class);
			List<DropDownModel> genderTypeList = Arrays.asList(Gender);

			model.addAttribute("genderTypeList", genderTypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		try {
			DropDownModel[] Nationality = restTemplate.getForObject(env.getEmployeeUrl() + "getnationalityList1",
					DropDownModel[].class);
			List<DropDownModel> nationalityList = Arrays.asList(Nationality);

			model.addAttribute("nationalityList", nationalityList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] BloodGroup = restTemplate.getForObject(env.getEmployeeUrl() + "getbloodgroupList1",
					DropDownModel[].class);
			List<DropDownModel> bloodgroupList = Arrays.asList(BloodGroup);

			model.addAttribute("bloodgroupList", bloodgroupList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] MaritalStatus = restTemplate.getForObject(env.getEmployeeUrl() + "getmaritalstatusList1",
					DropDownModel[].class);
			List<DropDownModel> maritalstatusList = Arrays.asList(MaritalStatus);

			model.addAttribute("maritalstatusList", maritalstatusList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] Country = restTemplate.getForObject(env.getEmployeeUrl() + "getCountryList",
					DropDownModel[].class);
			List<DropDownModel> counntryList = Arrays.asList(Country);

			model.addAttribute("counntryList", counntryList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {

			DropDownModel[] addressType = restTemplate.getForObject(env.getRecruitment() + "addressTypeList",
					DropDownModel[].class);
			List<DropDownModel> addressTypeList = Arrays.asList(addressType);
			model.addAttribute("addressTypeList", addressTypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] JobType = restTemplate.getForObject(env.getEmployeeUrl() + "getJobType1",
					DropDownModel[].class);
			List<DropDownModel> jobtypeList = Arrays.asList(JobType);

			model.addAttribute("jobtypeList", jobtypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] Department = restTemplate.getForObject(env.getEmployeeUrl() + "getDepartmentList1",
					DropDownModel[].class);
			List<DropDownModel> DepartmentList = Arrays.asList(Department);

			model.addAttribute("DepartmentList", DepartmentList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		try {
			DropDownModel[] JobType = restTemplate.getForObject(env.getEmployeeUrl() + "getJobType1",
					DropDownModel[].class);
			List<DropDownModel> jobtypeList = Arrays.asList(JobType);

			model.addAttribute("jobtypeList", jobtypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] Department = restTemplate.getForObject(env.getEmployeeUrl() + "getDepartmentList1",
					DropDownModel[].class);
			List<DropDownModel> DepartmentList = Arrays.asList(Department);

			model.addAttribute("DepartmentList", DepartmentList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] TimesheetType = restTemplate.getForObject(env.getEmployeeUrl() + "getTimesheetType1",
					DropDownModel[].class);
			List<DropDownModel> TimesheetList = Arrays.asList(TimesheetType);

			model.addAttribute("TimesheetList", TimesheetList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {

			DropDownModel[] band = restTemplate.getForObject(env.getRecruitment() + "bandList", DropDownModel[].class);
			List<DropDownModel> bandList = Arrays.asList(band);
			model.addAttribute("bandList", bandList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] employmentType = restTemplate.getForObject(env.getEmployeeUrl() + "getemploymentType1",
					DropDownModel[].class);
			List<DropDownModel> employmentstatusList = Arrays.asList(employmentType);

			model.addAttribute("employmentstatusList", employmentstatusList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {

			DropDownModel[] manager = restTemplate.getForObject(env.getEmployeeUrl() + "EmployeeList",
					DropDownModel[].class);
			List<DropDownModel> managerList = Arrays.asList(manager);
			model.addAttribute("EmployeeList", managerList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] Benefits = restTemplate.getForObject(env.getEmployeeUrl() + "getBenefits",
					DropDownModel[].class);
			List<DropDownModel> benefitsList = Arrays.asList(Benefits);

			model.addAttribute("benefitsList", benefitsList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] dependentType = restTemplate.getForObject(env.getEmployeeUrl() + "dependentTypeList",
					DropDownModel[].class);
			List<DropDownModel> dependentTypeList = Arrays.asList(dependentType);

			model.addAttribute("dependentTypeList", dependentTypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] relationship = restTemplate.getForObject(env.getEmployeeUrl() + "relationshipList",
					DropDownModel[].class);
			List<DropDownModel> relationshipList = Arrays.asList(relationship);

			model.addAttribute("relationshipList", relationshipList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] Bank = restTemplate.getForObject(env.getEmployeeUrl() + "getBankNameList",
					DropDownModel[].class);
			List<DropDownModel> BankNameList = Arrays.asList(Bank);

			model.addAttribute("BankNameList", BankNameList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] Bank = restTemplate.getForObject(env.getEmployeeUrl() + "insuranceCompanyList",
					DropDownModel[].class);
			List<DropDownModel> insuranceCompanyList = Arrays.asList(Bank);

			model.addAttribute("insuranceCompanyList", insuranceCompanyList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] Bank = restTemplate.getForObject(env.getEmployeeUrl() + "documentTypeList",
					DropDownModel[].class);
			List<DropDownModel> documentTypeList = Arrays.asList(Bank);

			model.addAttribute("documentTypeList", documentTypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : employee ends");
		return "doctor/myProfile";
	}

	/*
	 * Get Mapping for view-doctor-opd
	 */

	@GetMapping("view-doctor-opd")
	public String viewDoctorOPD(Model model, HttpSession session) {

		logger.info("Method : viewDoctorOPD starts");

		// drop down for employee list
		try {
			DropDownModel[] notice = restTemplate.getForObject(env.getNotice() + "getempLists", DropDownModel[].class);
			List<DropDownModel> noticelists = Arrays.asList(notice);
			model.addAttribute("emplists", noticelists);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] notice = restTemplate.getForObject(env.getNotice() + "getdeptLists", DropDownModel[].class);
			List<DropDownModel> noticelists = Arrays.asList(notice);
			model.addAttribute("deptList", noticelists);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : viewDoctorOPD ends");
		return "doctor/opd";
	}

	/////////////////////////////////

	/*
	 * view-doctor-opd-view-Throughajax
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("view-doctor-opd-view-Throughajax")
	public @ResponseBody List<IntiateNoticeModel> viewDoctorOPDThroughAjax() {

		logger.info("Method : viewMyAccountThroughAjax starts");

		JsonResponse<List<IntiateNoticeModel>> resp = new JsonResponse<List<IntiateNoticeModel>>();

		try {
			resp = restTemplate.getForObject(env.getNotice() + "rest-viewNoticeinitiate", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewDoctorOPDThroughAjax ends");
		return resp.getBody();
	}

	/////////////////////////////////////////////////////////////////// doctor/view-my-account

	/*
	 * Get Mapping for notice page '/doctor/view-my-patient'
	 */

	@GetMapping("view-my-account")
	public String viewMyAccount(Model model, HttpSession session) {

		logger.info("Method : viewMyAccount starts");

		// drop down for employee list
		try {
			DropDownModel[] notice = restTemplate.getForObject(env.getNotice() + "getempLists", DropDownModel[].class);
			List<DropDownModel> noticelists = Arrays.asList(notice);
			model.addAttribute("emplists", noticelists);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] notice = restTemplate.getForObject(env.getNotice() + "getdeptLists", DropDownModel[].class);
			List<DropDownModel> noticelists = Arrays.asList(notice);
			model.addAttribute("deptList", noticelists);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : viewMyAccount ends");
		return "doctor/myAccount";
	}

	/*
	 * View Notice Details
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("view-my-account-view-Throughajax")
	public @ResponseBody List<IntiateNoticeModel> viewMyAccountThroughAjax() {

		logger.info("Method : viewMyAccountThroughAjax starts");

		JsonResponse<List<IntiateNoticeModel>> resp = new JsonResponse<List<IntiateNoticeModel>>();

		try {
			resp = restTemplate.getForObject(env.getNotice() + "rest-viewNoticeinitiate", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewMyAccountThroughAjax ends");
		return resp.getBody();
	}

	

	/*
	 * Get Mapping for patient medication details(Medicine)
	 */

	@GetMapping("doctor-patient-medication")
	public String viewPatientMedication(Model model, HttpSession session) {
		logger.info("Method : viewPatientMedication starts");

		try {
			
			DropDownModel[] medType = restTemplate.getForObject(env.getDoctorUrl() + "getMedTypeList",
					DropDownModel[].class);
			List<DropDownModel> medTypeList = Arrays.asList(medType);

			model.addAttribute("medTypeList", medTypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewPatientMedication ends");
		return "doctor/doc-view-patient-medication";
	}

	/*
	 * Medicine Name autoSearch
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "doctor-patient-medication-Details" })
	public @ResponseBody JsonResponse<PatientMedicationModel> getMedicineNameList(Model model,
			@RequestBody String searchValue, BindingResult result) {
		logger.info("Method : getMedicineNameList starts");
		JsonResponse<PatientMedicationModel> res = new JsonResponse<PatientMedicationModel>();

		try {
			res = restTemplate.getForObject(env.getDoctorUrl() + "rest-medicineNameList?id=" + searchValue,
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
		logger.info("Method : getMedicineNameList ends");
		return res;
	}

	/*
	 * TestName Name autoSearch
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "doctor-patient-medication-test-autosearch" })
	public @ResponseBody JsonResponse<PatientMedicationModel> getTestNameList(Model model,
		   @RequestBody String searchValue, BindingResult result) {
		logger.info("Method : getTestNameList starts");
		JsonResponse<PatientMedicationModel> res = new JsonResponse<PatientMedicationModel>();

		try {
			res = restTemplate.getForObject(env.getDoctorUrl() + "rest-TestNameListList?id=" + searchValue,
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
		
		logger.info("Method : getTestNameList ends");
		return res;
	}

	
	/*
	 * Add prescription details
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("doctor-patient-medication-addDetails")
	public @ResponseBody JsonResponse<Object> savePrescriptionDetails(Model model,
			@RequestBody PatientMedicationModel patientModel, HttpSession session) {
		logger.info("Method : savePrescriptionDetails starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String drId = "";
		String patientId = "";
		String apptIdL = "";
		
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (patientModel.getFromDate() != null && patientModel.getFromDate() != "") {
			patientModel.setFromDate(DateFormatter.inputDateFormat(patientModel.getFromDate(), dateFormat));
		}
		
		if (patientModel.getToDate() != null && patientModel.getToDate() != "") {
			patientModel.setToDate(DateFormatter.inputDateFormat(patientModel.getToDate(), dateFormat));
		}
		try {
			drId = (String) session.getAttribute("USER_ID");
			patientId=(String) session.getAttribute("PATIENT_ID");
			apptIdL=(String) session.getAttribute("apptId");
		} catch (Exception e) {
			e.printStackTrace();
		}
		BigInteger pId = new BigInteger(patientId);
		
		patientModel.setDoctorId(drId);
		patientModel.setUserId(pId);
		patientModel.setApptNo(apptIdL);
		System.out.println("patientModel.setApptNo(apptIdL);"+patientModel.getApptNo());
		try {
			
			resp = restTemplate.postForObject(env.getDoctorUrl() + "save-prescription-details", patientModel,
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

		logger.info("Method : savePrescriptionDetails starts");
		return resp;
	}
	
	/*
	 * Add Test details
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("doctor-patient-medication-addTestDetails")
	public @ResponseBody JsonResponse<Object> addTestDetails(Model model,
			@RequestBody PatientMedicationModel patientModel, HttpSession session) {
		logger.info("Method : savePrescriptionDetails starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		String drId = "";
		String patientId = "";
		String apptmtId = "";
		try {
			drId = (String) session.getAttribute("USER_ID");
			patientId=(String) session.getAttribute("PATIENT_ID");
			apptmtId=(String) session.getAttribute("apptId");
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		BigInteger pId = new BigInteger(patientId);
		
		patientModel.setDoctorId(drId);
		patientModel.setUserId(pId);
		patientModel.setApptNo(apptmtId);
		try {
			
			resp = restTemplate.postForObject(env.getDoctorUrl() + "save-test-details", patientModel,
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

		logger.info("Method : addTestDetails starts");
		return resp;
	}
	
	//Current medicine details
	@SuppressWarnings("unchecked")
	@GetMapping("doctor-patient-medication-current")
	public @ResponseBody List<PatientMedicationModel> viewPatientCurrentMedicines(Model model, HttpSession session) {

		logger.info("Method : viewPatientCurrentMedicines starts");
		JsonResponse<List<PatientMedicationModel>> resp = new JsonResponse<List<PatientMedicationModel>>();
		String userId="";
		try {
			userId = (String) session.getAttribute("PATIENT_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (resp.getMessage() != null) {

			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		
		try { 
			resp = restTemplate.getForObject(env.getUserUrl() + "rest-viewCurrentMedication?uId="+userId, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	 
		logger.info("Method : viewPatientCurrentMedicines ends");
		return resp.getBody();
	}
	
	//Current test details(Medication)
	
		@SuppressWarnings("unchecked")
		@GetMapping("doctor-patient-medication-test-details")
		public @ResponseBody List<PatientMedicationModel> viewPatientTestDetails(Model model, HttpSession session) {

			logger.info("Method : viewPatientTestDetails starts");
			JsonResponse<List<PatientMedicationModel>> resp = new JsonResponse<List<PatientMedicationModel>>();
			String userId="";
			try {
				userId = (String) session.getAttribute("PATIENT_ID");
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (resp.getMessage() != null) {

				resp.setCode(resp.getMessage());
				resp.setMessage("Unsuccess");
			} else {
				resp.setMessage("Success");
			}
			
			try { 
				resp = restTemplate.getForObject(env.getDoctorUrl() + "rest-view-testDetails?uId="+userId, JsonResponse.class);
			} catch (RestClientException e) {
				e.printStackTrace();
			}
		 
			logger.info("Method : viewPatientTestDetails ends");
			return resp.getBody();
		}
	/**************** View Doctor details to show in accordion  *******************/
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("doctor-patient-medication-view-doctor-details")
	public @ResponseBody JsonResponse<List<PatientMedicationModel>> viewPatientCurrentDrDetails(Model model, HttpSession session) {

		logger.info("Method : viewPatientCurrentDrDetails starts");
		JsonResponse<List<PatientMedicationModel>> resp = new JsonResponse<List<PatientMedicationModel>>();
		String userId="";
		try {
			userId = (String) session.getAttribute("PATIENT_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		try {
			resp = restTemplate.getForObject(env.getUserUrl() + "rest-doctorDetails?uId="+userId, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		if (resp.getMessage() != null) {

			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
	 
		logger.info("Method : viewPatientCurrentDrDetails ends");
		return resp;
	}
	
	
	//EMR FUNCTIONALITY START
	@GetMapping("view-doctor-patient-details")
	public String viewPatientDetails(Model model, HttpSession session,@RequestParam("id") String encodeId,@RequestParam("apptId") String appointmentId) {
		logger.info("Method : viewPatientDetails starts");
		//System.out.println("@@@@@id@@@@@@@@"+id);
		byte[] decodeId = Base64.getDecoder().decode(encodeId.getBytes());
		//byte[] appointmentId = Base64.getDecoder().decode(appointId.getBytes());
		//System.out.println(appointId);
System.out.println(appointmentId);
		String patid = (new String(decodeId));
		session.setAttribute("PATIENT_ID", patid);
		session.setAttribute("apptId", appointmentId);
		try {

			DropDownModel[] allerName = restTemplate.getForObject(env.getUserUrl() + "get-allerName-list",
					DropDownModel[].class);
			List<DropDownModel> alernameList = Arrays.asList(allerName);

			model.addAttribute("alernameList", alernameList);
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		
		try {

			DropDownModel[] allerName = restTemplate.getForObject(env.getUserUrl() + "get-allertype-list",
					DropDownModel[].class);
			List<DropDownModel> alertypeList = Arrays.asList(allerName);

			model.addAttribute("alertypeList", alertypeList);
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		try {

			DropDownModel[] allerName = restTemplate.getForObject(env.getUserUrl() + "get-bname-list",
					DropDownModel[].class);
			List<DropDownModel> bnamelist = Arrays.asList(allerName);

			model.addAttribute("bnamelist", bnamelist);
		} 
		
		catch (Exception e) {
			e.printStackTrace();
			
		}
		String userId = "";

		try {
			userId = (String) session.getAttribute("PATIENT_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("patId", userId);
		
		logger.info("Method : viewPatientDetails ends");
		return "doctor/patientrdetails";
	}
	@PostMapping("view-doctor-patient-upload-file")
	public @ResponseBody JsonResponse<Object> uploadFile(@RequestParam("file") MultipartFile inputFile,
			HttpSession session) {
		logger.info("Method : uploadFile controller function 'post-mapping' starts");

		JsonResponse<Object> response = new JsonResponse<Object>();

		try {
			response.setMessage(inputFile.getOriginalFilename());
			session.setAttribute("quotationPFile", inputFile);
			
			
			
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : uploadFile controller function 'post-mapping' ends");
		return response;
	}
	@SuppressWarnings("unused")
	private String saveAllImageString(byte[] imageBytes, String ext) {
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

			Path path = Paths.get(env.getFileUploadMaster() + imageName);
			if (imageBytes != null) {
				Files.write(path, imageBytes);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : saveAllImage ends");
		return imageName;
		
	}
	@PostMapping("view-doctor-patient-details-delete-file")
	public @ResponseBody JsonResponse<Object> deleteFile(HttpSession session) {
		logger.info("Method : deleteFile controller function 'post-mapping' starts");
		
		JsonResponse<Object> response = new JsonResponse<Object>();
		
		try {
			session.removeAttribute("quotationPFile");
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : deleteFile controller function 'post-mapping' ends");
		return response;
	}

	@SuppressWarnings("unchecked")

	@GetMapping("view-doctor-patient-details-edit")
	public @ResponseBody JsonResponse<PatientDetailsNewModel> editpatientnew(Model model,
			HttpSession session) {

		logger.info("Method : editpatientnew starts");

		JsonResponse<PatientDetailsNewModel> jsonResponse = new JsonResponse<PatientDetailsNewModel>();
		String userId="";
		try {
			userId = (String) session.getAttribute("PATIENT_ID");
			
			jsonResponse = restTemplate.getForObject(env.getUserUrl() + "editpatientnew?id=" + userId, JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();
			PatientDetailsNewModel customerNewModel = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<PatientDetailsNewModel>() {
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

		logger.info("Method : editpatientnew ends");
		return jsonResponse;
	}


	@SuppressWarnings("unchecked")

	@GetMapping("view-doctor-patient-details-edit-vitalsign")
	public @ResponseBody JsonResponse<PatientDetailsNewModel> editpatientvitalsignnew(Model model,
			HttpSession session) {

		logger.info("Method : editpatientvitalsignnew starts");

		JsonResponse<PatientDetailsNewModel> jsonResponse = new JsonResponse<PatientDetailsNewModel>();
		String userId="";
		try {
			userId = (String) session.getAttribute("PATIENT_ID");
			
			///Integer id1 = Integer.parseInt(userId);
			jsonResponse = restTemplate.getForObject(env.getUserUrl() + "editpatientvitalsignnew?id=" + userId, JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();
			PatientDetailsNewModel customerNewModel = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<PatientDetailsNewModel>() {
					});
			
			if(customerNewModel.getTempCs()!=null) {
				Double fh = (customerNewModel.getTempCs() * (9/5)) + 32;
				customerNewModel.setTempFah(fh);
				System.out.println("tempfah"+fh);
			}
		
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

		
		logger.info("Method : editpatientvitalsignnew ends");
		return jsonResponse;
	}
	@SuppressWarnings("unchecked")
	@GetMapping("view-doctor-patient-details-alergy-through-ajax")
	public @ResponseBody List<PatientDetailsNewModel> getallergynewview(HttpSession session) {

		logger.info("Method :getallergynewview starts");

		JsonResponse<List<PatientDetailsNewModel>> resp = new JsonResponse<List<PatientDetailsNewModel>>();
			
		try {

			resp = restTemplate.getForObject(env.getUserUrl() + "getallergynewview", JsonResponse.class);
	} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<PatientDetailsNewModel> patientHistoryModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<PatientDetailsNewModel>>() {
				});
		
	
	

		resp.setBody(patientHistoryModel);
	if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :getallergynewview ends");
		
		return resp.getBody();
	}
	@SuppressWarnings("unchecked")
	@GetMapping("view-doctor-patient-details-biomedical-through-ajax")
	public @ResponseBody List<PatientDetailsNewModel> getbiomedicalnewview(HttpSession session) {

		logger.info("Method :getbiomedicalnewview starts");

		JsonResponse<List<PatientDetailsNewModel>> resp = new JsonResponse<List<PatientDetailsNewModel>>();
			
		try {

			resp = restTemplate.getForObject(env.getUserUrl() + "getbiomedicalnewview", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<PatientDetailsNewModel> patientHistoryModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<PatientDetailsNewModel>>() {
				});
		
		
		

		resp.setBody(patientHistoryModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :getbiomedicalnewview ends");
		//System.out.println("RESPONSE" + resp);
		return resp.getBody();
	}
	 @SuppressWarnings("unchecked")
	@GetMapping("view-doctor-patient-details-edit-emercontact")
	public @ResponseBody List<PatientDetailsNewModel> editpatientemercontactnew(HttpSession session) {

		logger.info("Method :editpatientemercontactnew starts");

		JsonResponse<List<PatientDetailsNewModel>> resp = new JsonResponse<List<PatientDetailsNewModel>>();
		try {

			resp = restTemplate.getForObject(env.getUserUrl() + "editpatientemercontactnew", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<PatientDetailsNewModel> patientHistoryModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<PatientDetailsNewModel>>() {
				});
		
		
		

		resp.setBody(patientHistoryModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :editpatientemercontactnew ends");
		System.out.println("ediemer"+resp.getBody());
		return resp.getBody();
	}
	
		/*
		 * view family doctor
		 */
//		 @SuppressWarnings("unchecked")
//		@GetMapping("view-doctor-patient-details-edit-famdoc")
//		public @ResponseBody List<PatientDetailsNewModel> editpatientfamdocnew(HttpSession session) {
//
//			logger.info("Method :editpatientfamdocnew starts");
//				
//			JsonResponse<List<PatientDetailsNewModel>> resp = new JsonResponse<List<PatientDetailsNewModel>>();
//				
//			try {
//
//				resp = restTemplate.getForObject(env.getUserUrl() + "editpatientfamdocnew", JsonResponse.class);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			ObjectMapper mapper = new ObjectMapper();
//
//			List<PatientDetailsNewModel> patientHistoryModel = mapper.convertValue(resp.getBody(),
//					new TypeReference<List<PatientDetailsNewModel>>() {
//					});
//			
//
//			resp.setBody(patientHistoryModel);
//			System.out.println("patientHistoryModel"+patientHistoryModel);
//			if (resp.getMessage() != "" && resp.getMessage() != null) {
//				resp.setCode(resp.getMessage());
//				resp.setMessage("Unsuccess");
//			} else {
//				resp.setMessage("Success");
//			}
//
//			logger.info("Method :editpatientfamdocnew ends");
//			System.out.println("editfam"+resp.getBody());
//			return resp.getBody();
//		}
		 @GetMapping("doctor-view-patient-history")
			public String viewPatientHistory(Model model, HttpSession session) {
				logger.info("Method : viewPatientHistory starts");
				logger.info("Method : viewPatientHistory ends");
				return "doctor/doctor-view-patient-history";
			}
		 @SuppressWarnings("unchecked")
			@GetMapping("doctor-view-patient-history-through-ajax")
			public @ResponseBody List<PatientHistoryModel> getAllPatientHistory(HttpSession session) {

				logger.info("Method :getAllPatientHistory starts");

				JsonResponse<List<PatientHistoryModel>> resp = new JsonResponse<List<PatientHistoryModel>>();
					
				try {

					resp = restTemplate.getForObject(env.getUserUrl() + "getAllPatientHistory", JsonResponse.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				ObjectMapper mapper = new ObjectMapper();

				List<PatientHistoryModel> patientHistoryModel = mapper.convertValue(resp.getBody(),
						new TypeReference<List<PatientHistoryModel>>() {
						});
				String dateFormat = "";
				try {
					dateFormat = (String) session.getAttribute("DATEFORMAT");
				} catch (Exception e) {

				}
				for (PatientHistoryModel a : patientHistoryModel) {
					if (a.getDate() != null && a.getDate() != "") {
						a.setDate(DateFormatter.dateFormat(a.getDate(), dateFormat));
					}

					System.out.println(patientHistoryModel); 

				}

				resp.setBody(patientHistoryModel);
				if (resp.getMessage() != "" && resp.getMessage() != null) {
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("Success");
				}

				logger.info("Method :getAllPatientHistory ends");
				System.out.println("RESPONSE" + resp);
				return resp.getBody();
			}
		 @SuppressWarnings("unchecked")

			@GetMapping("doctor-view-patient-history-major-surgery")
			public @ResponseBody List<PatientHistoryModel> getPatientHistoryMajorSurgery(HttpSession session) {

				logger.info("Method :getPatientHistoryMajorSurgery starts");

				JsonResponse<List<PatientHistoryModel>> resp = new JsonResponse<List<PatientHistoryModel>>();
					
				try {

					resp = restTemplate.getForObject(env.getUserUrl() + "getPatientHistoryMajorSurgery", JsonResponse.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				ObjectMapper mapper = new ObjectMapper();

				List<PatientHistoryModel> patientHistoryModel = mapper.convertValue(resp.getBody(),
						new TypeReference<List<PatientHistoryModel>>() {
						});
				String dateFormat = "";
				try {
					dateFormat = (String) session.getAttribute("DATEFORMAT");
				} catch (Exception e) {

				}
				for (PatientHistoryModel a : patientHistoryModel) {
					if (a.getSurgeryDate() != null && a.getSurgeryDate() != "") {
						a.setSurgeryDate(DateFormatter.dateFormat(a.getSurgeryDate(), dateFormat));
					}

					System.out.println(patientHistoryModel); 

				}

				resp.setBody(patientHistoryModel);
				if (resp.getMessage() != "" && resp.getMessage() != null) {
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("Success");
				}

				logger.info("Method :getPatientHistoryMajorSurgery ends");
				System.out.println("RESPONSE" + resp);
				return resp.getBody();
			}
		 @SuppressWarnings("unchecked")

			@GetMapping("doctor-view-patient-history-medical-condition")
			public @ResponseBody List<PatientHistoryModel> getPatientHistoryMedicalCondition(HttpSession session) {

				logger.info("Method :getPatientHistoryMedicalCondition starts");

				JsonResponse<List<PatientHistoryModel>> resp = new JsonResponse<List<PatientHistoryModel>>();
					
				try {

					resp = restTemplate.getForObject(env.getUserUrl() + "getPatientHistoryMedicalCondition", JsonResponse.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				ObjectMapper mapper = new ObjectMapper();

				List<PatientHistoryModel> patientHistoryModel = mapper.convertValue(resp.getBody(),
						new TypeReference<List<PatientHistoryModel>>() {
						});
				resp.setBody(patientHistoryModel);
				if (resp.getMessage() != "" && resp.getMessage() != null) {
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("Success");
				}

				logger.info("Method :getPatientHistoryMedicalCondition ends");
				System.out.println("RESPONSE" + resp);
				return resp.getBody();
			}
			
		 @GetMapping("doc-view-patient-immunization")
			public String viewPatientImmunization(Model model, HttpSession session) {
				logger.info("Method : viewPatientImmunization starts");
				
				// drop down for Immunization Type
				try {
					DropDownModel[] immuType = restTemplate.getForObject(env.getUserUrl() + "getImmunizationTypeList",
							DropDownModel[].class);
					List<DropDownModel> immunizationTypeList = Arrays.asList(immuType
							);
					model.addAttribute("immunizationTypeList", immunizationTypeList);
				} catch (RestClientException e) {
					e.printStackTrace();
				}
				String userId = "";

				try {
					userId = (String) session.getAttribute("PATIENT_ID");
				} catch (Exception e) {
					e.printStackTrace();
				}
				model.addAttribute("patId", userId);
				
				logger.info("Method : viewPatientImmunization ends");
				return "doctor/docpatientImmunization";
			}
			
			/*
			 * View patient immunization 
			 */
			@SuppressWarnings("unchecked")
			@GetMapping("doc-view-patient-immunization-viewDetails")
			public @ResponseBody List<ImmunizationModel> viewPatientImmunizThroughAjax(@RequestParam BigInteger id, HttpSession session,
					Model model) {

				logger.info("Method : viewPatientImmunizThroughAjax starts");

				JsonResponse<List<ImmunizationModel>> resp = new JsonResponse<List<ImmunizationModel>>();

				try {
					resp = restTemplate.getForObject(env.getUserUrl() + "rest-viewImmunization?id="+ id, JsonResponse.class);
				} catch (RestClientException e) {
					e.printStackTrace();
				}

				logger.info("Method : viewPatientImmunizThroughAjax ends");
				return resp.getBody();
			}
			
			@GetMapping("doc-view-patient-lifestyle-history")
			public String viewPatientLifeHistory(Model model, HttpSession session) {
				logger.info("Method : viewPatientLifeHistory starts");
				
				try {
					DropDownModel[] smoking = restTemplate.getForObject(env.getUserUrl() + "getSmokingList",
							DropDownModel[].class);
					List<DropDownModel> getSmokingList = Arrays.asList(smoking);
					model.addAttribute("getSmokingList", getSmokingList);
					//System.out.println("getSmokingList"+getSmokingList);
				} catch (RestClientException e) {
					e.printStackTrace();

				}
				
				try {
					DropDownModel[] alcohol = restTemplate.getForObject(env.getUserUrl() + "getAlcoholList",
							DropDownModel[].class);
					List<DropDownModel> getAlcoholList = Arrays.asList(alcohol);
					model.addAttribute("getAlcoholList", getAlcoholList);

				} catch (RestClientException e) {
					e.printStackTrace();

				}
				logger.info("Method : viewPatientLifeHistory ends");
				return "doctor/docpatientLifeHistory";
			}
			@SuppressWarnings("unchecked")
			@GetMapping("doc-view-patient-lifestyle-history-edit")
			public @ResponseBody JsonResponse<LifeStyleHistoryModel> editpatientLifeStyle(HttpSession session) {

				logger.info("Method : editpatientLifeStyle starts");
				
				JsonResponse<LifeStyleHistoryModel> response = new JsonResponse<LifeStyleHistoryModel>();
				String userId = "";
				try {
					
					
					userId = (String) session.getAttribute("PATIENT_ID");
					
					response = restTemplate.getForObject(env.getUserUrl() + "editpatientLifeStyle?id=" + userId,
							JsonResponse.class);
						System.out.println("userId"+userId);
				} catch (

				RestClientException e) {
					e.printStackTrace();
				}
				
				
				ObjectMapper mapper = new ObjectMapper();

				LifeStyleHistoryModel customer = mapper.convertValue(response.getBody(),
						new TypeReference<LifeStyleHistoryModel>() {
						});
			
				
				response.setBody(customer);
				if (response.getMessage() != null && response.getMessage() != "") {
					response.setCode(response.getMessage());
					response.setMessage("Unsuccess");

				} else {
					response.setMessage("Success");
				}
				//System.out.println("webbbb====" + response);
				logger.info("Method : editpatientLifeStyle ends");
				return response;
			}
			@GetMapping("doc-view-patient-treatment-tracker")
			public String viewPatientTreatmentTracker(Model model, HttpSession session) {
				logger.info("Method : viewPatientTreatmentTracker starts");
				logger.info("Method : viewPatientTreatmentTracker ends");
				return "doctor/docpatientTreatmentTracker";
			}
			@SuppressWarnings("unchecked")

			@GetMapping("doc-view-patient-treatment-tracker-user")
			public @ResponseBody List<TreatmentTrackerModel> getPatientTreatmentTracker(HttpSession session) {

				logger.info("Method :getPatientTreatmentTracker starts");

				JsonResponse<List<TreatmentTrackerModel>> resp = new JsonResponse<List<TreatmentTrackerModel>>();
					
				try {

					resp = restTemplate.getForObject(env.getUserUrl() + "getPatientTreatmentTracker", JsonResponse.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				ObjectMapper mapper = new ObjectMapper();

				List<TreatmentTrackerModel> treatmentTrackerModel = mapper.convertValue(resp.getBody(),
						new TypeReference<List<TreatmentTrackerModel>>() {
						});
				String dateFormat = "";
				try {
					dateFormat = (String) session.getAttribute("DATEFORMAT");
				} catch (Exception e) {

				}
				for (TreatmentTrackerModel a : treatmentTrackerModel) {
					if (a.getFromDate() != null && a.getFromDate() != "") {
						a.setFromDate(DateFormatter.dateFormat(a.getFromDate(), dateFormat));
					}
					if (a.getToDate() != null && a.getToDate() != "") {
						a.setToDate(DateFormatter.dateFormat(a.getToDate(), dateFormat));
					}
					//System.out.println(treatmentTrackerModel); 

				}

				resp.setBody(treatmentTrackerModel);
				if (resp.getMessage() != "" && resp.getMessage() != null) {
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("Success");
				}

				logger.info("Method :getPatientTreatmentTracker ends");
				System.out.println("RESPONSE" + resp);
				return resp.getBody();
			}
			@GetMapping("doc-view-patient-family-details")
			public String viewPatientFaimilyDetails(Model model, HttpSession session) {
				logger.info("Method : viewPatientFaimilyDetails starts");
				logger.info("Method : viewPatientFaimilyDetails ends");
				return "doctor/docpatientFamilyDtls";
			}
			

			@SuppressWarnings("unchecked")
			@GetMapping("doc-view-patient-family-details-familydata")
			public @ResponseBody List<PatientFamilyDetailsModel> viewPatientFamilyDataDetails(Model model,HttpSession session) {
					
				logger.info("Method :viewPatientFamilyDataDetails starts");

				JsonResponse<List<PatientFamilyDetailsModel>> resp = new JsonResponse<List<PatientFamilyDetailsModel>>();
					
				try {

					resp = restTemplate.getForObject(env.getUserUrl() + "viewPatientFamilyDataDetails", JsonResponse.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				ObjectMapper mapper = new ObjectMapper();

				List<PatientFamilyDetailsModel> familydetailsModel = mapper.convertValue(resp.getBody(),
						new TypeReference<List<PatientFamilyDetailsModel>>() {
						});
				
				String dateFormat = "";
				try {
					dateFormat = (String) session.getAttribute("DATEFORMAT");
				} catch (Exception e) {

				}
				for (PatientFamilyDetailsModel a : familydetailsModel) {
					if (a.getBirthDate() != null && a.getBirthDate() != "") {
						a.setBirthDate(DateFormatter.dateFormat(a.getBirthDate(), dateFormat));
					}
					if (a.getSince() != null && a.getSince() != "") {
						a.setSince(DateFormatter.dateFormat(a.getSince(), dateFormat));
					}


				}

				resp.setBody(familydetailsModel);
				if (resp.getMessage() != "" && resp.getMessage() != null) {
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("Success");
				}

				logger.info("Method :viewPatientFamilyDataDetails ends");
				return resp.getBody();
			}
}