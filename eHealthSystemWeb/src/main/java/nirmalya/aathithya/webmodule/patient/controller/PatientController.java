package nirmalya.aathithya.webmodule.patient.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.employee.model.ScheduleManagementModel;
import nirmalya.aathithya.webmodule.notice.model.IntiateNoticeModel;
import nirmalya.aathithya.webmodule.patient.model.ImmunizationModel;
import nirmalya.aathithya.webmodule.patient.model.InsuranceModel;
import nirmalya.aathithya.webmodule.patient.model.LabTestReportModel;
import nirmalya.aathithya.webmodule.patient.model.LifeStyleHistoryModel;
import nirmalya.aathithya.webmodule.patient.model.LoginDetailsModel;
import nirmalya.aathithya.webmodule.patient.model.PatientDetailsNewModel;
import nirmalya.aathithya.webmodule.patient.model.PatientHistoryModel;
import nirmalya.aathithya.webmodule.patient.model.PatientMedicationModel;
import nirmalya.aathithya.webmodule.patient.model.TreatmentTrackerModel;

@Controller
@RequestMapping(value = { "user/" })
public class PatientController {
	Logger logger = LoggerFactory.getLogger(PatientController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;

	/*
	 * Get Mapping for view-my-dashboard
	 */
	/*
	 * @GetMapping("view-my-dashboard") public String viewMyDashboard(Model model,
	 * HttpSession session) {
	 * 
	 * logger.info("Method : viewMyDashboard starts");
	 * 
	 * 
	 * logger.info("Method : viewMyDashboard ends"); return
	 * "patient/patientDashboard"; }
	 */

	/*
	 * Get Mapping for view-doctor-insurance
	 */

	@GetMapping("view-patient-insurance")
	public String viewPatientInsurance(Model model, HttpSession session) {

		logger.info("Method : viewPatientInsurance starts");

		logger.info("Method : viewPatientInsurance ends");
		return "patient/patientInsurance";
	}

	/*
	 * Get Mapping for view-patient-hospital-bill
	 */

	@GetMapping("view-patient-hospital-bill")
	public String viewPatientHospitalBill(Model model, HttpSession session) {

		logger.info("Method : viewPatientHospitalBill starts");

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
		logger.info("Method : viewPatientHospitalBill ends");
		return "patient/patientHospitalBill";
	}

	/*
	 * Get Mapping for view-patient-consultation
	 */

	@GetMapping("book-appointment")
	public String bookAppointment(Model model, HttpSession session) {
		logger.info("Method : bookAppointment starts");
		logger.info("Method : bookAppointment ends");
		return "patient/bookAppointment";
	}

	/*
	 * Get Mapping for view-patient-details
	 */

	@GetMapping("view-patient-details")
	public String viewPatientDetails(Model model, HttpSession session) {
		logger.info("Method : viewPatientDetails starts");

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
			List<DropDownModel> bioMedicalList = Arrays.asList(allerName);

			model.addAttribute("bioMedicalList", bioMedicalList);
		} catch (Exception e) {
			e.printStackTrace();

		}

		logger.info("Method : viewPatientDetails ends");
		return "patient/patientDetails";
	}

	/*
	 * Get Mapping for view-patient-details
	 */

	@GetMapping("view-patient-history")
	public String viewPatientHistory(Model model, HttpSession session) {
		logger.info("Method : viewPatientHistory starts");
		logger.info("Method : viewPatientHistory ends");
		return "patient/view-patient-history";
	}

	/*
	 * Get Mapping for view-patient-family-details
	 */

	/*
	 * @GetMapping("view-patient-family-details") public String
	 * viewPatientFaimilyDetails(Model model, HttpSession session) {
	 * logger.info("Method : viewPatientFaimilyDetails starts");
	 * logger.info("Method : viewPatientFaimilyDetails ends"); return
	 * "patient/patientFamilyDtls"; }
	 */

	/*
	 * Get Mapping for patient immunization
	 */
	@GetMapping("view-patient-immunization")
	public String viewPatientImmunization(Model model, HttpSession session) {
		logger.info("Method : viewPatientImmunization starts");

		// drop down for Immunization Type
		try {
			DropDownModel[] immuType = restTemplate.getForObject(env.getUserUrl() + "getImmunizationTypeList",
					DropDownModel[].class);
			List<DropDownModel> immunizationTypeList = Arrays.asList(immuType);
			model.addAttribute("immunizationTypeList", immunizationTypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("patId", userId);

		logger.info("Method : viewPatientImmunization ends");
		return "patient/patientImmunization";
	}

	/*
	 * View patient immunization
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-immunization-viewDetails")
	public @ResponseBody List<ImmunizationModel> viewPatientImmunizThroughAjax(@RequestParam BigInteger id,
			HttpSession session, Model model) {

		logger.info("Method : viewPatientImmunizThroughAjax starts");

		JsonResponse<List<ImmunizationModel>> resp = new JsonResponse<List<ImmunizationModel>>();

		try {
			resp = restTemplate.getForObject(env.getUserUrl() + "rest-viewImmunization?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewPatientImmunizThroughAjax ends");
		return resp.getBody();
	}

	/*
	 * View view-patient-immunization-view-Throughajax
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-immunization-view-Throughajax")
	public @ResponseBody List<IntiateNoticeModel> viewPatientImmunizThroughAjax() {

		logger.info("Method : viewPatientImmunizThroughAjax starts");

		JsonResponse<List<IntiateNoticeModel>> resp = new JsonResponse<List<IntiateNoticeModel>>();

		try {
			resp = restTemplate.getForObject(env.getNotice() + "rest-viewNoticeinitiate", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewPatientImmunizThroughAjax ends");
		return resp.getBody();
	}

	

	/*
	 * Get Mapping for view-patient-treatment-tracker
	 */

	// @GetMapping("view-patient-medication")
	// public String viewPatientMedication(Model model, HttpSession session) {
	// logger.info("Method : viewPatientMedication ends");
	// return "patient/view-patient-medication";
	// }

	/*
	 * Get Mapping for view-patient-medical-data-upload
	 */

	@GetMapping("view-patient-medical-data-upload")
	public String viewPatientMedicalUpload(Model model, HttpSession session) {
		logger.info("Method : viewPatientMedicalUpload starts");
		logger.info("Method : viewPatientMedicalUpload ends");
		return "patient/patientMedicalUpload";
	}

	/*
	 * Get Mapping for view-patient-login-details
	 */

	@GetMapping("view-patient-login-details")
	public String viewPatientLoginDetails(Model model, HttpSession session) {
		logger.info("Method : viewPatientLoginDetails starts");
		logger.info("Method : viewPatientLoginDetails ends");
		return "patient/patientLoginDetails";
	}

	/*
	 * Get Mapping for view-patient-export-emr
	 */

	@GetMapping("view-patient-export-emr")
	public String viewPatientExportEmr(Model model, HttpSession session) {
		logger.info("Method : viewPatientExportEmr starts");
		logger.info("Method : viewPatientExportEmr ends");
		return "patient/patientExportEMR";
	}

	/*
	 * Get Mapping for view-patient-lifestyle-history
	 */

	@GetMapping("view-patient-lifestyle-history")
	public String viewPatientLifeHistory(Model model, HttpSession session) {
		logger.info("Method : viewPatientLifeHistory starts");

		try {
			DropDownModel[] smoking = restTemplate.getForObject(env.getUserUrl() + "getSmokingList",
					DropDownModel[].class);
			List<DropDownModel> getSmokingList = Arrays.asList(smoking);
			model.addAttribute("getSmokingList", getSmokingList);

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
		return "patient/patientLifeHistory";
	}

	/*
	 * Get Mapping for notice page '/view-my-account'
	 */

	@GetMapping("my-account")
	public String viewMyAccount(Model model, HttpSession session) {

		logger.info("Method : viewMyAccount starts");

		logger.info("Method : viewMyAccount ends");
		return "patient/MyAccount";
	}

	/*
	 * /view-my-patient profile' by Manmayee
	 */

	@GetMapping("my-profile")
	public String viewMyProfile(Model model, HttpSession session) {

		logger.info("Method : viewMyProfile starts");
		try {

			DropDownModel[] allerName = restTemplate.getForObject(env.getUserUrl() + "get-erelation-list",
					DropDownModel[].class);
			List<DropDownModel> erelationList = Arrays.asList(allerName);

			model.addAttribute("erelationList", erelationList);
		} catch (Exception e) {
			e.printStackTrace();

		}

		try {

			DropDownModel[] allerName = restTemplate.getForObject(env.getUserUrl() + "get-speciality-list",
					DropDownModel[].class);
			List<DropDownModel> specialityList = Arrays.asList(allerName);

			model.addAttribute("specialityList", specialityList);
		} catch (Exception e) {
			e.printStackTrace();

		}
		try {
			DropDownModel[] Gender = restTemplate.getForObject(env.getUserUrl() + "getgenderListnew",
					DropDownModel[].class);
			List<DropDownModel> genderTypeList = Arrays.asList(Gender);

			model.addAttribute("genderTypeList", genderTypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] BloodGroup = restTemplate.getForObject(env.getUserUrl() + "getbloodgroupListnew",
					DropDownModel[].class);
			List<DropDownModel> bloodgroupList = Arrays.asList(BloodGroup);

			model.addAttribute("bloodgroupList", bloodgroupList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] MaritalStatus = restTemplate.getForObject(env.getUserUrl() + "getmaritalstatusListnew",
					DropDownModel[].class);
			List<DropDownModel> maritalstatusList = Arrays.asList(MaritalStatus);

			model.addAttribute("maritalstatusList", maritalstatusList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewMyProfile ends");
		return "patient/view-patient-profile";
	}

	/*
	 * Get Mapping for view-patient-details
	 */

	// @GetMapping("/view-patient-consultation")
	// public String viewPatientConsultation(Model model, HttpSession session) {
	// logger.info("Method : viewPatientConsultation starts");
	//
	// try {
	// DropDownModel[] dropDownModel =
	// restTemplate.getForObject(env.getEmployeeUrl() + "get-department",
	// DropDownModel[].class);
	// List<DropDownModel> departmentList = Arrays.asList(dropDownModel);
	// model.addAttribute("departmentList", departmentList);
	// } catch (RestClientException e) {
	// e.printStackTrace();
	// }
	//
	// try {
	// DropDownModel[] dropDownModel =
	// restTemplate.getForObject(env.getEmployeeUrl() + "get-schedule",
	// DropDownModel[].class);
	// List<DropDownModel> scheduleList = Arrays.asList(dropDownModel);
	// model.addAttribute("scheduleList", scheduleList);
	// } catch (RestClientException e) {
	// e.printStackTrace();
	// }
	//
	// try {
	// DropDownModel[] dropDownModel =
	// restTemplate.getForObject(env.getEmployeeUrl() + "get-section",
	// DropDownModel[].class);
	// List<DropDownModel> sectionList = Arrays.asList(dropDownModel);
	// model.addAttribute("sectionList", sectionList);
	// } catch (RestClientException e) {
	// e.printStackTrace();
	// }
	//
	// try {
	// DropDownModel[] dropDownModel =
	// restTemplate.getForObject(env.getEmployeeUrl() + "get-shift",
	// DropDownModel[].class);
	// List<DropDownModel> shiftList = Arrays.asList(dropDownModel);
	// model.addAttribute("shiftList", shiftList);
	// } catch (RestClientException e) {
	// e.printStackTrace();
	// }
	//
	// try {
	// DropDownModel[] dropDownModel =
	// restTemplate.getForObject(env.getEmployeeUrl() + "get-employee",
	// DropDownModel[].class);
	// List<DropDownModel> employeeList = Arrays.asList(dropDownModel);
	// model.addAttribute("employeeList", employeeList);
	// } catch (RestClientException e) {
	// e.printStackTrace();
	// }
	//
	// logger.info("Method : schedule ends");
	// return "patient/patientConsultation";
	// }

	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-consultation-view-employee")
	public @ResponseBody List<ScheduleManagementModel> viewSchedule(Model model, HttpSession session) {
		logger.info("Method : viewSchedule starts");

		JsonResponse<List<ScheduleManagementModel>> jsonResponse = new JsonResponse<List<ScheduleManagementModel>>();

		try {

			jsonResponse = restTemplate.getForObject(env.getEmployeeUrl() + "schedule-management-employee",
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			List<ScheduleManagementModel> addreq = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<List<ScheduleManagementModel>>() {
					});

			String dateFormat = (String) (session).getAttribute("DATEFORMAT");

			for (ScheduleManagementModel m : addreq) {

				if (m.getFromDate() != null && m.getFromDate() != "") {
					m.setFromDate(DateFormatter.dateFormat(m.getFromDate(), dateFormat));
				}

				if (m.getToDate() != null && m.getToDate() != "") {
					m.setToDate(DateFormatter.dateFormat(m.getToDate(), dateFormat));
				}

				if (m.getCreatedOn() != null && m.getCreatedOn() != "") {
					m.setCreatedOn(DateFormatter.dateFormat(m.getCreatedOn(), dateFormat));
				}
			}

			jsonResponse.setBody(addreq);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method ; viewSchedule ends");

		return jsonResponse.getBody();
	}

	/*
	 * patient img
	 */
	@PostMapping("view-patient-details-upload-file")
	public @ResponseBody JsonResponse<Object> uploadFile(@RequestParam("file") MultipartFile inputFile,
			HttpSession session) {
		logger.info("Method : uploadFile controller function 'post-mapping' starts");

		JsonResponse<Object> response = new JsonResponse<Object>();

		try {
			response.setMessage(inputFile.getOriginalFilename());
			session.setAttribute("ProfileImg", inputFile);

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

	@PostMapping("view-patient-details-delete-file")
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

	/******************* * Patient Profile **************************************/
	/*
	 * view patient information
	 */
	@SuppressWarnings("unchecked")

	@GetMapping("view-patient-details-edit")
	public @ResponseBody JsonResponse<PatientDetailsNewModel> editpatientnew(Model model, HttpSession session) {

		logger.info("Method : editpatientnew starts");

		JsonResponse<PatientDetailsNewModel> jsonResponse = new JsonResponse<PatientDetailsNewModel>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");

			jsonResponse = restTemplate.getForObject(env.getUserUrl() + "editpatientnew?id=" + userId,
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();
			PatientDetailsNewModel customerNewModel = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<PatientDetailsNewModel>() {
					});
			String profile = null;
			if (customerNewModel.getFileDetails() != null && customerNewModel.getFileDetails() != ""
					&& !customerNewModel.getFileDetails().equals("null")) {

				profile = env.getBaseURL() + "document/profile/" + customerNewModel.getFileDetails();
				customerNewModel.setFileDetails(profile);
			}
			System.out.println("AAAA"+profile);

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

	/*
	 * view emergency contact
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-details-edit-emercontact")
	public @ResponseBody List<PatientDetailsNewModel> editpatientemercontactnew(HttpSession session) {

		logger.info("Method :editpatientemercontactnew starts");

		JsonResponse<List<PatientDetailsNewModel>> resp = new JsonResponse<List<PatientDetailsNewModel>>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getUserUrl() + "patientemercontactnew?id=" + userId,
					JsonResponse.class);
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
		return resp.getBody();
	}

	/*
	 * view family doctor
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-details-edit-famdoc")
	public @ResponseBody List<PatientDetailsNewModel> editpatientfamdocnew(HttpSession session) {

		logger.info("Method :editpatientfamdocnew starts");

		JsonResponse<List<PatientDetailsNewModel>> resp = new JsonResponse<List<PatientDetailsNewModel>>();

		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getUserUrl() + "editpatientfamdocnew?id=" + userId,
					JsonResponse.class);
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
		logger.info("Method :editpatientfamdocnew ends");
		return resp.getBody();
	}

	/*
	 * edit emergency contact
	 * 
	 */
	@SuppressWarnings("unchecked")

	@GetMapping("view-patient-details-emercontact-edit")
	public @ResponseBody JsonResponse<PatientDetailsNewModel> editEmergencyContactNew(Model model,
			@RequestParam String eId, HttpSession session) {

		logger.info("Method : editEmergencyContactNew starts");

		JsonResponse<PatientDetailsNewModel> jsonResponse = new JsonResponse<PatientDetailsNewModel>();
		try {
			jsonResponse = restTemplate.getForObject(env.getUserUrl() + "editEmergencyContactNew?id=" + eId,
					JsonResponse.class);

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

		logger.info("Method : editEmergencyContactNew ends");
		return jsonResponse;
	}

	/*
	 * view vital sign
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("view-patient-details-edit-vitalsign")
	public @ResponseBody JsonResponse<PatientDetailsNewModel> editpatientvitalsignnew(Model model,
			HttpSession session) {

		logger.info("Method : editpatientvitalsignnew starts");

		JsonResponse<PatientDetailsNewModel> jsonResponse = new JsonResponse<PatientDetailsNewModel>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			jsonResponse = restTemplate.getForObject(env.getUserUrl() + "editpatientvitalsignnew?id=" + userId,
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();
			PatientDetailsNewModel customerNewModel = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<PatientDetailsNewModel>() {
					});

			if (customerNewModel.getTempCs() != null) {
				Double fh = (customerNewModel.getTempCs() * (9 / 5)) + 32;
				customerNewModel.setTempFah(fh);
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

	/*
	 * Allergy Save
	 * 
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("view-patient-details-alergy-save")
	public @ResponseBody JsonResponse<Object> savedpatallergy(
			@RequestBody PatientDetailsNewModel patientDetailsNewModel, HttpSession session) {
		logger.info("Method : savedpatallergy starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		BigInteger pId = new BigInteger(userId);

		patientDetailsNewModel.setUserId(pId);
		System.out.println("pid" + pId);
		try {
			resp = restTemplate.postForObject(env.getUserUrl() + "savedpatallergy", patientDetailsNewModel,
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

		System.out.println("savedpatallergy" + resp);
		logger.info("Method : saveemercontactdetails starts");
		return resp;
	}

	/*
	 * VIEW alergy
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-details-alergy-through-ajax")
	public @ResponseBody List<PatientDetailsNewModel> getallergynewview(HttpSession session) {

		logger.info("Method :getallergynewview starts");

		JsonResponse<List<PatientDetailsNewModel>> resp = new JsonResponse<List<PatientDetailsNewModel>>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getUserUrl() + "getallergynewview?id=" + userId, JsonResponse.class);
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

	/*
	 * VIEW biomedical
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-details-biomedical-through-ajax")
	public @ResponseBody List<PatientDetailsNewModel> getbiomedicalnewview(HttpSession session) {

		logger.info("Method :getbiomedicalnewview starts");

		JsonResponse<List<PatientDetailsNewModel>> resp = new JsonResponse<List<PatientDetailsNewModel>>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getUserUrl() + "getbiomedicalnewview?id=" + userId,
					JsonResponse.class);
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
		return resp.getBody();
	}

	/*
	 * Save Biomedical
	 */

	@SuppressWarnings("unchecked")
	@PostMapping("view-patient-details-biomedical-save")
	public @ResponseBody JsonResponse<Object> savebiomedical(@RequestBody PatientDetailsNewModel patientDetailsNewModel,
			HttpSession session) {
		logger.info("Method : savebiomedical starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		BigInteger pId = new BigInteger(userId);

		patientDetailsNewModel.setUserId(pId);
		try {
			resp = restTemplate.postForObject(env.getUserUrl() + "savebiomedical", patientDetailsNewModel,
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

		logger.info("Method : savebiomedical starts");
		return resp;
	}

	/*
	 * VIEW FOR LOGIN DETAILS
	 */
	@SuppressWarnings("unchecked")

	@GetMapping("view-patient-login-details-login")
	public @ResponseBody List<LoginDetailsModel> getPatientLoginDetails(HttpSession session) {

		logger.info("Method :getPatientLoginDetails starts");

		JsonResponse<List<LoginDetailsModel>> resp = new JsonResponse<List<LoginDetailsModel>>();
		String userId="";
		try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getUserUrl() + "getPatientLoginDetails?id="+userId, JsonResponse.class);
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<LoginDetailsModel> loginDetailsModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<LoginDetailsModel>>() {
				});
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		for (LoginDetailsModel a : loginDetailsModel) {
			if (a.getDate() != null && a.getDate() != "") {
				a.setDate(DateFormatter.dateFormat(a.getDate(), dateFormat));
			}

		}

		resp.setBody(loginDetailsModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :getPatientLoginDetails ends");

		return resp.getBody();
	}

	/*
	 * ADD FOR LIFE STYLE HISTORY
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/view-patient-lifestyle-history-add")
	public @ResponseBody JsonResponse<Object> addPatientLifeHistory(Model model, HttpSession session,

			@RequestBody LifeStyleHistoryModel lifeStyleHistoryModel) {

		logger.info("Method : addPatientLifeHistory starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		BigInteger pId = new BigInteger(userId);

		lifeStyleHistoryModel.setPatientId(pId);

		try {
			resp = restTemplate.postForObject(env.getUserUrl() + "patientLifeHistoryAdd", lifeStyleHistoryModel,
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

		logger.info("Method : addPatientLifeHistory ends");

		return resp;
	}

	/*
	 * FOR EDIT LIFE STYLE
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-lifestyle-history-edit")
	public @ResponseBody JsonResponse<LifeStyleHistoryModel> editpatientLifeStyle(HttpSession session) {

		logger.info("Method : editpatientLifeStyle starts");

		JsonResponse<LifeStyleHistoryModel> response = new JsonResponse<LifeStyleHistoryModel>();
		String userId = "";
		try {

			userId = (String) session.getAttribute("USER_ID");

			response = restTemplate.getForObject(env.getUserUrl() + "editpatientLifeStyle?id=" + userId,
					JsonResponse.class);

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

		logger.info("Method : editpatientLifeStyle ends");
		return response;
	}

	/*
	 * VIEW FOR TREATMENT TRACKER
	 
	@SuppressWarnings("unchecked")

	@GetMapping("view-patient-treatment-tracker-user")
	public @ResponseBody List<TreatmentTrackerModel> getPatientTreatmentTracker(HttpSession session) {

		logger.info("Method :getPatientTreatmentTracker starts");

		JsonResponse<List<TreatmentTrackerModel>> resp = new JsonResponse<List<TreatmentTrackerModel>>();

		try {
			String userId = session.getAttribute("USER_ID").toString();
			System.out.println("userId"+userId);
			resp = restTemplate.getForObject(env.getUserUrl() + "rest-getPatientTreatmentTracker?id=" +userId, JsonResponse.class);
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

		}

		resp.setBody(treatmentTrackerModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :getPatientTreatmentTracker ends");

		return resp.getBody();
	}*/


	/*
	 * VIEW MAJOR ILLNES
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-history-through-ajax")
	public @ResponseBody List<PatientHistoryModel> getAllPatientHistory(HttpSession session) {

		logger.info("Method :getAllPatientHistory starts");

		JsonResponse<List<PatientHistoryModel>> resp = new JsonResponse<List<PatientHistoryModel>>();
		String userId = "";
		try {

			resp = restTemplate.getForObject(env.getUserUrl() + "getAllPatientHistory?id=" + userId,
					JsonResponse.class);
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

		}

		resp.setBody(patientHistoryModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :getAllPatientHistory ends");

		return resp.getBody();
	}

	/*
	 * VIEW MAJOR SURGERY
	 */
	@SuppressWarnings("unchecked")

	@GetMapping("view-patient-history-major-surgery")
	public @ResponseBody List<PatientHistoryModel> getPatientHistoryMajorSurgery(HttpSession session) {

		logger.info("Method :getPatientHistoryMajorSurgery starts");

		JsonResponse<List<PatientHistoryModel>> resp = new JsonResponse<List<PatientHistoryModel>>();
			
		try {

			String userId = session.getAttribute("USER_ID").toString();
			System.out.println("userId"+userId);
			resp = restTemplate.getForObject(env.getUserUrl() + "getPatientHistoryMajorSurgery?id=" +userId, JsonResponse.class);
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

	/*
	 * VIEW MEDICAL CONDITION
	 */
	/*
	 * VIEW MEDICAL CONDITION
	 */
	@SuppressWarnings("unchecked")

	@GetMapping("view-patient-history-medical-condition")
	public @ResponseBody List<PatientHistoryModel> getPatientHistoryMedicalCondition(HttpSession session) {

		logger.info("Method :getPatientHistoryMedicalCondition starts");

		JsonResponse<List<PatientHistoryModel>> resp = new JsonResponse<List<PatientHistoryModel>>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getUserUrl() + "rest-getPatientHistoryMedicalCondition?id=" + userId,
					JsonResponse.class);
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

		return resp.getBody();
	}

	/*
	 * View current medicine
	 */
	// @SuppressWarnings("unchecked")
	// @GetMapping("view-patient-medication-current")
	// public @ResponseBody List<PatientMedicationModel>
	// viewPatientCurrentMedicines(Model model, HttpSession session) {
	//
	// logger.info("Method : viewPatientCurrentMedicines starts");
	// JsonResponse<List<PatientMedicationModel>> resp = new
	// JsonResponse<List<PatientMedicationModel>>();
	// String userId="";
	// try {
	// userId = (String) session.getAttribute("USER_ID");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// if (resp.getMessage() != null) {
	//
	// resp.setCode(resp.getMessage());
	// resp.setMessage("Unsuccess");
	// } else {
	// resp.setMessage("Success");
	// }
	//
	// try {
	// resp = restTemplate.getForObject(env.getUserUrl() +
	// "rest-viewCurrentMedication?uId="+userId, JsonResponse.class);
	// } catch (RestClientException e) {
	// e.printStackTrace();
	// }
	//
	// logger.info("Method : viewPatientCurrentMedicines ends");
	// return resp.getBody();
	// }
	//
	/*
	 * View Doctor details
	 */
	// @SuppressWarnings("unchecked")
	// @GetMapping("view-patient-medication-doctor-details")
	// public @ResponseBody JsonResponse<List<PatientMedicationModel>>
	// viewPatientCurrentDrDetails(Model model, HttpSession session) {
	//
	// logger.info("Method : viewPatientCurrentDrDetails starts");
	// JsonResponse<List<PatientMedicationModel>> resp = new
	// JsonResponse<List<PatientMedicationModel>>();
	// String userId="";
	// try {
	// userId = (String) session.getAttribute("USER_ID");
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	//
	// try {
	// resp = restTemplate.getForObject(env.getUserUrl() +
	// "rest-doctorDetails?uId="+userId, JsonResponse.class);
	// } catch (RestClientException e) {
	// e.printStackTrace();
	// }
	// if (resp.getMessage() != null) {
	//
	// resp.setCode(resp.getMessage());
	// resp.setMessage("Unsuccess");
	// } else {
	// resp.setMessage("Success");
	// }
	//
	// logger.info("Method : viewPatientCurrentDrDetails ends");
	// return resp;
	// }

	/*
	 * autosearch
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "view-patient-details-family-get-list" })
	public @ResponseBody JsonResponse<PatientDetailsNewModel> getFamilyListAutoSearch(Model model,
			@RequestBody String searchValue, BindingResult result) {
		logger.info("Method : getFamilyListAutoSearch starts");

		JsonResponse<PatientDetailsNewModel> res = new JsonResponse<PatientDetailsNewModel>();

		try {
			res = restTemplate.getForObject(env.getUserUrl() + "getFamilyListAutoSearch?id=" + searchValue,
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

		logger.info("Method : getFamilyListAutoSearch ends");
		return res;
	}

	/*
	 * VIEW INSURANCE
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-insurance-through-ajax")
	public @ResponseBody List<InsuranceModel> getAllPatientInsuranceView(HttpSession session) {

		logger.info("Method :getAllPatientInsuranceView starts");

		JsonResponse<List<InsuranceModel>> resp = new JsonResponse<List<InsuranceModel>>();

		try {

			resp = restTemplate.getForObject(env.getUserUrl() + "getPatientInsuranceView", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<InsuranceModel> patientHistoryModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<InsuranceModel>>() {
				});
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		for (InsuranceModel a : patientHistoryModel) {

			if (a.getPremiumDueDate() != null && a.getPremiumDueDate() != "") {
				a.setPremiumDueDate(DateFormatter.dateFormat(a.getPremiumDueDate(), dateFormat));
			}

		}

		resp.setBody(patientHistoryModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :getAllPatientInsuranceView ends");

		return resp.getBody();
	}

	/*
	 * Edit Insurance
	 * 
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-insurance-edit")
	public @ResponseBody List<InsuranceModel> editPatientInsurance(@RequestParam String id, HttpSession session) {

		logger.info("Method : editPatientInsurance starts");

		JsonResponse<List<InsuranceModel>> resp = new JsonResponse<List<InsuranceModel>>();
		try {

			resp = restTemplate.getForObject(env.getUserUrl() + "edit-patient-insurance?id=" + id, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<InsuranceModel> grn = mapper.convertValue(resp.getBody(), new TypeReference<List<InsuranceModel>>() {
		});

		String dateFormat = "";
		try {

			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		for (InsuranceModel a : grn) {

			if (a.getPremiumDueDate() != null && a.getPremiumDueDate() != "") {
				a.setPremiumDueDate(DateFormatter.dateFormat(a.getPremiumDueDate(), dateFormat));
			}

		}

		resp.setBody(grn);
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");

		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : editPatientInsurance ends");

		return grn;
	}

	/*
	 * ADD FOR INSURANCE
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/view-patient-insurance-add")
	public @ResponseBody JsonResponse<Object> addInsurance(Model model, HttpSession session,

			@RequestBody InsuranceModel lifeStyleHistoryModel) {

		logger.info("Method : addInsurance starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		BigInteger pId = new BigInteger(userId);

		lifeStyleHistoryModel.setPatientId(pId);

		try {
			resp = restTemplate.postForObject(env.getUserUrl() + "addInsurance", lifeStyleHistoryModel,
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

		logger.info("Method : addInsurance ends");

		return resp;
	}

	/*
	 * save vitals
	 */

	@SuppressWarnings("unchecked")
	@PostMapping("view-patient-details-vitals-add")
	public @ResponseBody JsonResponse<Object> addvitalsSign(@RequestBody PatientDetailsNewModel patientDetailsNewModel,
			HttpSession session) {
		logger.info("Method : addvitalsSign starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		BigInteger pId = new BigInteger(userId);

		patientDetailsNewModel.setUserId(pId);

		try {
			resp = restTemplate.postForObject(env.getUserUrl() + "addvitalsSign", patientDetailsNewModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == "" || resp.getMessage() == null) {

			resp.setMessage("Success");
		}

		logger.info("Method : addvitalsSign starts");
		return resp;
	}

	/*
	 * save emergency contact
	 */

	@SuppressWarnings("unchecked")
	@PostMapping("view-patient-details-emercontact-save")
	public @ResponseBody JsonResponse<Object> saveemercontactdetails(
			@RequestBody PatientDetailsNewModel patientDetailsNewModel, HttpSession session) {
		logger.info("Method : saveemercontactdetails starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		BigInteger pId = new BigInteger(userId);

		patientDetailsNewModel.setUserId(pId);

		try {
			resp = restTemplate.postForObject(env.getUserUrl() + "saveemercontactdetails", patientDetailsNewModel,
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

		logger.info("Method : saveemercontactdetails starts");
		return resp;
	}

	/*
	 * save family doc
	 */

	@SuppressWarnings("unchecked")
	@PostMapping("view-patient-details-famdoc-save")
	public @ResponseBody JsonResponse<Object> savefamdocDetails(
			@RequestBody PatientDetailsNewModel patientDetailsNewModel, HttpSession session) {
		logger.info("Method : savefamdocDetails starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		BigInteger pId = new BigInteger(userId);

		patientDetailsNewModel.setUserId(pId);

		try {
			resp = restTemplate.postForObject(env.getUserUrl() + "savefamdocDetails", patientDetailsNewModel,
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

		logger.info("Method : savefamdocDetails starts");
		return resp;
	}

	/*
	 * save identification
	 * 
	 */

	@SuppressWarnings("unchecked")
	@PostMapping("view-patient-details-idefication-save")
	public @ResponseBody JsonResponse<Object> addidentidicationsave(Model model, HttpSession session,

			@RequestBody PatientDetailsNewModel patientDetailsNewModel) {

		logger.info("Method : addidentidicationsave starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		BigInteger pId = new BigInteger(userId);

		patientDetailsNewModel.setUserId(pId);

		try {
			resp = restTemplate.postForObject(env.getUserUrl() + "addidentidicationsave", patientDetailsNewModel,
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

		logger.info("Method : addidentidicationsave ends");

		return resp;
	}

	/*
	 * save contact
	 */

	@SuppressWarnings("unchecked")
	@PostMapping("view-patient-details-contact-save")
	public @ResponseBody JsonResponse<Object> addcontactsave(Model model, HttpSession session,

			@RequestBody PatientDetailsNewModel patientDetailsNewModel) {

		logger.info("Method : addcontactsave starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		BigInteger pId = new BigInteger(userId);

		patientDetailsNewModel.setUserId(pId);

		try {
			resp = restTemplate.postForObject(env.getUserUrl() + "addcontactsave", patientDetailsNewModel,
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

		logger.info("Method : addcontactsave ends");

		return resp;
	}

	/*
	 * save Education
	 */

	@SuppressWarnings("unchecked")
	@PostMapping("view-patient-details-edu-save")
	public @ResponseBody JsonResponse<Object> saveEducationdtl(Model model, HttpSession session,

			@RequestBody PatientDetailsNewModel patientDetailsNewModel) {

		logger.info("Method : saveEducationdtl starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		BigInteger pId = new BigInteger(userId);

		patientDetailsNewModel.setUserId(pId);

		try {
			resp = restTemplate.postForObject(env.getUserUrl() + "saveEducationdtl", patientDetailsNewModel,
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

		logger.info("Method : saveEducationdtl ends");

		return resp;
	}

	/*
	 * VIEW education
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-details-edit-edu")
	public @ResponseBody List<PatientDetailsNewModel> getEducationView(HttpSession session) {

		logger.info("Method :getEducationView starts");

		JsonResponse<List<PatientDetailsNewModel>> resp = new JsonResponse<List<PatientDetailsNewModel>>();

		try {

			resp = restTemplate.getForObject(env.getUserUrl() + "getEducationView", JsonResponse.class);
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

		logger.info("Method :getEducationView ends");

		return resp.getBody();
	}

	/*
	 * save family details
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-details-famDtl-save")
	public @ResponseBody JsonResponse<Object> savefaamilyDetails(Model model, HttpSession session,
			@RequestParam String relation, @RequestParam String uhidCardNo, @RequestParam String slno) {
		logger.info("Method : savefaamilyDetails starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			resp = restTemplate.getForObject(env.getUserUrl() + "savefaamilyDetails?relation=" + relation
					+ "&uhidCardNo=" + uhidCardNo + "&userId=" + userId + "&slno=" + slno, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : savefaamilyDetails starts");
		return resp;
	}

	/*
	 * view family details
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-details-edit-famDtl")
	public @ResponseBody List<PatientDetailsNewModel> getfamilyView(HttpSession session) {

		logger.info("Method :getfamilyView starts");

		JsonResponse<List<PatientDetailsNewModel>> resp = new JsonResponse<List<PatientDetailsNewModel>>();
		String userId="";
		try {
			userId = (String) session.getAttribute("USER_ID");
			resp = restTemplate.getForObject(env.getUserUrl() + "getfamilyView?id="+userId, JsonResponse.class);
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

		logger.info("Method :getfamilyView ends");
		return resp.getBody();
	}
	
	/*******************
	 * * DELETE EMERGENCY CONTACT
	 ***********************************************/
	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-details-delete-contact")
	public @ResponseBody JsonResponse<Object> deleteEmrgncyContact(Model model, HttpSession session,
			@RequestParam String slno) {
		logger.info("Method : deleteEmrgncyContact starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getUserUrl() + "deleteEmrgncyContact?slno=" + slno,
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

		logger.info("Method : deleteEmrgncyContact starts");
		return resp;
	}

	/*******************
	 * * DELETE Family Doctor
	 ***********************************************/
	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-details-delete-familyDoctor")
	public @ResponseBody JsonResponse<Object> deleteFamilyDoctor(Model model, HttpSession session,
			@RequestParam String slno) {
		logger.info("Method : deleteFamilyDoctor starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getUserUrl() + "deleteFamilyDoctor?slno=" + slno, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : deleteFamilyDoctor starts");
		return resp;
	}

	/*******************
	 * * DELETE FAMILY DETAILS
	 **************************************************/
	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-details-delete-family-details")
	public @ResponseBody JsonResponse<Object> deleteFamilyMember(Model model, HttpSession session,
			@RequestParam String slno) {
		logger.info("Method : deleteFamilyMember starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restTemplate.getForObject(env.getUserUrl() + "deleteFamilyMember?slno=" + slno, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : deleteFamilyMember starts");
		return resp;
	}

	/*******************
	 * * save personal
	 ***********************************************/

	@SuppressWarnings("unchecked")
	@PostMapping("view-patient-details-personal-save")
	public @ResponseBody JsonResponse<Object> addpersonalsave(Model model, HttpSession session,

			@RequestBody PatientDetailsNewModel patientDetailsNewModel) {

		logger.info("Method : addpersonalsave starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		BigInteger pId = new BigInteger(userId);

		patientDetailsNewModel.setUserId(pId);
		String space = "";
		if(patientDetailsNewModel.getDobid().equals(space)) {
			patientDetailsNewModel.setDobid(null);
		}
		MultipartFile inputFile = (MultipartFile) session.getAttribute("ProfileImg");
		byte[] bytes;
		String imageName = null;

		if (inputFile != null) {
			try {
				bytes = inputFile.getBytes();
				String[] fileType = inputFile.getContentType().split("/");
				imageName = saveAllImageString(bytes, fileType[1]);

				patientDetailsNewModel.setFileDetails(imageName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		try {
			resp = restTemplate.postForObject(env.getUserUrl() + "addpersonalsave", patientDetailsNewModel,
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

		logger.info("Method : addpersonalsave ends");

		return resp;
	}

	// dropdown for City list

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "view-patient-details-getCityList" })
	public @ResponseBody JsonResponse<Object> getCityNewList(@RequestParam String distId) {
		logger.info("Method : getCityNewList starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-getCityNewList?distId=" + distId,
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
		logger.info("Method : getCityNewList ends");
		return res;

	}

	/*
	 * drop down for district
	 */
	@SuppressWarnings("unchecked")

	@GetMapping(value = { "view-patient-details-getDistList" })
	public @ResponseBody JsonResponse<Object> getDistNewList(@RequestParam String state) {
		logger.info("Method : getDistNewList starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-getDistNewList?state=" + state,
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
		logger.info("Method : getDistNewList ends");
		return res;

	}
	
	/*
	 * FOR Contact Details Edit
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-details-edit-contactDetails")
	public @ResponseBody JsonResponse<PatientDetailsNewModel> editcontactDetailsAddress(HttpSession session) {

		logger.info("Method : editcontactDetailsAddress starts");
		
		JsonResponse<PatientDetailsNewModel> response = new JsonResponse<PatientDetailsNewModel>();
		String userId = "";
		try {
			
			
			userId = (String) session.getAttribute("USER_ID");
			
			response = restTemplate.getForObject(env.getUserUrl() + "editcontactDetailsAddress?id=" + userId,
					JsonResponse.class);
				
		} catch (

		RestClientException e) {
			e.printStackTrace();
		}
		
		
		ObjectMapper mapper = new ObjectMapper();

		PatientDetailsNewModel customer = mapper.convertValue(response.getBody(),
				new TypeReference<PatientDetailsNewModel>() {
				});
	
		
		response.setBody(customer);
		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}
		logger.info("Method : editcontactDetailsAddress ends");
		return response;
	}

	/************************* test report pdf *******************************/

	@SuppressWarnings("unchecked")
	@GetMapping("view-patient-test-report-view")
	public @ResponseBody List<LabTestReportModel> getPatientTestDetails(HttpSession session) {

		logger.info("Method : getPatientTestDetails starts");

		JsonResponse<List<LabTestReportModel>> response = new JsonResponse<List<LabTestReportModel>>();
		String userId = "";
		try {

			userId = (String) session.getAttribute("USER_ID");

			response = restTemplate.getForObject(env.getUserUrl() + "getpatienttestdetails?id=" + userId,
					JsonResponse.class);

		} catch (

		RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : getPatientTestDetails ends");
		return response.getBody();
	}

	/************************* Test Report *******************************/
	@GetMapping("view-patient-test-report")
	public String viewPatientTestReport(Model model, HttpSession session) {

		logger.info("Method : viewPatientTestReport starts");

		logger.info("Method : viewPatientTestReport ends");
		return "patient/lab-test-report";
	}

}
