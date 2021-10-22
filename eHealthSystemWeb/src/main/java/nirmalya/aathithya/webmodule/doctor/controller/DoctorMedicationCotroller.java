package nirmalya.aathithya.webmodule.doctor.controller;

import java.math.BigInteger;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.patient.model.PatientMedicationModel;

@Controller
@RequestMapping(value = { "doctor/" })
public class DoctorMedicationCotroller {
	// Current test details(Medication)
	Logger logger = LoggerFactory.getLogger(DoctorMedicationCotroller.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	/*
	 * 
	 * Get Mapping for patient medication details(Medicine)
	 * 
	 * 
	 * @GetMapping("doctor-patient-medication") public String
	 * viewPatientMedication(Model model, HttpSession session) {
	 * logger.info("Method : viewPatientMedication starts");
	 * 
	 * try {
	 * 
	 * DropDownModel[] medType = restTemplate.getForObject(env.getDoctorUrl() +
	 * "getMedTypeList", DropDownModel[].class); List<DropDownModel> medTypeList =
	 * Arrays.asList(medType);
	 * 
	 * model.addAttribute("medTypeList", medTypeList); } catch (RestClientException
	 * e) { e.printStackTrace(); }
	 * 
	 * logger.info("Method : viewPatientMedication ends"); return
	 * "doctor/doc-view-patient-medication"; }
	 * 
	 * 
	 * Medicine Name autoSearch
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @PostMapping(value = { "doctor-patient-medication-Details" })
	 * public @ResponseBody JsonResponse<PatientMedicationModel>
	 * getMedicineNameList(Model model,
	 * 
	 * @RequestBody String searchValue, BindingResult result) {
	 * logger.info("Method : getMedicineNameList starts");
	 * JsonResponse<PatientMedicationModel> res = new
	 * JsonResponse<PatientMedicationModel>();
	 * 
	 * try { res = restTemplate.getForObject(env.getDoctorUrl() +
	 * "rest-medicineNameList?id=" + searchValue, JsonResponse.class); } catch
	 * (Exception e) { e.printStackTrace(); }
	 * 
	 * if (res.getMessage() != null) {
	 * 
	 * res.setCode(res.getMessage()); res.setMessage("Unsuccess"); } else {
	 * res.setMessage("success"); }
	 * logger.info("Method : getMedicineNameList ends"); return res; }
	 * 
	 * 
	 * TestName Name autoSearch
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @PostMapping(value = { "doctor-patient-medication-test-autosearch" })
	 * public @ResponseBody JsonResponse<PatientMedicationModel>
	 * getTestNameList(Model model,
	 * 
	 * @RequestBody String searchValue, BindingResult result) {
	 * logger.info("Method : getTestNameList starts");
	 * JsonResponse<PatientMedicationModel> res = new
	 * JsonResponse<PatientMedicationModel>();
	 * 
	 * try { res = restTemplate.getForObject(env.getDoctorUrl() +
	 * "rest-TestNameListList?id=" + searchValue, JsonResponse.class); } catch
	 * (Exception e) { e.printStackTrace(); } if (res.getMessage() != null) {
	 * 
	 * res.setCode(res.getMessage()); res.setMessage("Unsuccess"); } else {
	 * res.setMessage("success"); }
	 * 
	 * logger.info("Method : getTestNameList ends");
	 * //System.out.println("@@@@@@@@@@"+res); return res; }
	 * 
	 * 
	 * 
	 * Add prescription details
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @PostMapping("doctor-patient-medication-addDetails") public @ResponseBody
	 * JsonResponse<Object> savePrescriptionDetails(Model model,
	 * 
	 * @RequestBody PatientMedicationModel patientModel, HttpSession session) {
	 * logger.info("Method : savePrescriptionDetails starts");
	 * 
	 * JsonResponse<Object> resp = new JsonResponse<Object>();
	 * 
	 * String drId = ""; String patientId = ""; String apptId = "";
	 * 
	 * try { drId = (String) session.getAttribute("USER_ID"); patientId=(String)
	 * session.getAttribute("PATIENT_ID"); apptId=(String)
	 * session.getAttribute("apptId"); } catch (Exception e) { e.printStackTrace();
	 * } BigInteger pId = new BigInteger(patientId);
	 * 
	 * patientModel.setDoctorId(drId); patientModel.setUserId(pId);
	 * patientModel.setApptNo(apptId); try {
	 * 
	 * resp = restTemplate.postForObject(env.getDoctorUrl() +
	 * "save-prescription-details", patientModel, JsonResponse.class); } catch
	 * (RestClientException e) { e.printStackTrace(); }
	 * 
	 * if (resp.getMessage() != "" && resp.getMessage() != null) {
	 * resp.setCode(resp.getMessage()); resp.setMessage("Unsuccess"); } else {
	 * resp.setMessage("Success"); }
	 * 
	 * logger.info("Method : savePrescriptionDetails starts"); return resp; }
	 * 
	 * 
	 * Add Test details
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @PostMapping("doctor-patient-medication-addTestDetails") public @ResponseBody
	 * JsonResponse<Object> addTestDetails(Model model,
	 * 
	 * @RequestBody PatientMedicationModel patientModel, HttpSession session) {
	 * logger.info("Method : savePrescriptionDetails starts");
	 * 
	 * JsonResponse<Object> resp = new JsonResponse<Object>();
	 * 
	 * String drId = ""; String patientId = ""; String apptId = ""; try { drId =
	 * (String) session.getAttribute("USER_ID"); patientId=(String)
	 * session.getAttribute("PATIENT_ID"); apptId=(String)
	 * session.getAttribute("apptId"); } catch (Exception e) { e.printStackTrace();
	 * } BigInteger pId = new BigInteger(patientId);
	 * 
	 * patientModel.setDoctorId(drId); patientModel.setUserId(pId);
	 * patientModel.setApptNo(apptId); try {
	 * 
	 * resp = restTemplate.postForObject(env.getDoctorUrl() + "save-test-details",
	 * patientModel, JsonResponse.class); } catch (RestClientException e) {
	 * e.printStackTrace(); }
	 * 
	 * if (resp.getMessage() != "" && resp.getMessage() != null) {
	 * resp.setCode(resp.getMessage()); resp.setMessage("Unsuccess"); } else {
	 * resp.setMessage("Success"); }
	 * 
	 * logger.info("Method : addTestDetails starts"); return resp; }
	 * 
	 * //Current medicine details
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @GetMapping("doctor-patient-medication-current") public @ResponseBody
	 * List<PatientMedicationModel> viewPatientCurrentMedicines(Model model,
	 * HttpSession session) {
	 * 
	 * logger.info("Method : viewPatientCurrentMedicines starts");
	 * JsonResponse<List<PatientMedicationModel>> resp = new
	 * JsonResponse<List<PatientMedicationModel>>(); String userId=""; try { userId
	 * = (String) session.getAttribute("PATIENT_ID"); } catch (Exception e) {
	 * e.printStackTrace(); } if (resp.getMessage() != null) {
	 * 
	 * resp.setCode(resp.getMessage()); resp.setMessage("Unsuccess"); } else {
	 * resp.setMessage("Success"); }
	 * 
	 * try { resp = restTemplate.getForObject(env.getUserUrl() +
	 * "rest-viewCurrentMedication?uId="+userId, JsonResponse.class); } catch
	 * (RestClientException e) { e.printStackTrace(); }
	 * 
	 * logger.info("Method : viewPatientCurrentMedicines ends"); return
	 * resp.getBody(); }
	 * 
	 * //Current test details(Medication)
	 * 
	 * @SuppressWarnings("unchecked")
	 * 
	 * @GetMapping("doctor-patient-medication-test-details") public @ResponseBody
	 * List<PatientMedicationModel> viewPatientTestDetails(Model model, HttpSession
	 * session) {
	 * 
	 * logger.info("Method : viewPatientTestDetails starts");
	 * JsonResponse<List<PatientMedicationModel>> resp = new
	 * JsonResponse<List<PatientMedicationModel>>(); String userId=""; try { userId
	 * = (String) session.getAttribute("PATIENT_ID");
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } if (resp.getMessage() != null)
	 * {
	 * 
	 * resp.setCode(resp.getMessage()); resp.setMessage("Unsuccess"); } else {
	 * resp.setMessage("Success"); }
	 * 
	 * try { resp = restTemplate.getForObject(env.getDoctorUrl() +
	 * "rest-view-testDetails?uId="+userId, JsonResponse.class); } catch
	 * (RestClientException e) { e.printStackTrace(); }
	 * 
	 * logger.info("Method : viewPatientTestDetails ends"); return resp.getBody(); }
	 *//**************** View Doctor details to show in accordion *******************//*
																						 * 
																						 * 
																						 * @SuppressWarnings(
																						 * "unchecked")
																						 * 
																						 * @GetMapping(
																						 * "doctor-patient-medication-view-doctor-details")
																						 * public @ResponseBody
																						 * JsonResponse<List<
																						 * PatientMedicationModel>>
																						 * viewPatientCurrentDrDetails(
																						 * Model model, HttpSession
																						 * session) {
																						 * 
																						 * logger.
																						 * info("Method : viewPatientCurrentDrDetails starts"
																						 * ); JsonResponse<List<
																						 * PatientMedicationModel>> resp
																						 * = new JsonResponse<List<
																						 * PatientMedicationModel>>();
																						 * String userId=""; try {
																						 * userId = (String)
																						 * session.getAttribute(
																						 * "PATIENT_ID"); } catch
																						 * (Exception e) {
																						 * e.printStackTrace(); }
																						 * 
																						 * 
																						 * try { resp =
																						 * restTemplate.getForObject(env
																						 * .getUserUrl() +
																						 * "rest-doctorDetails?uId="+
																						 * userId, JsonResponse.class);
																						 * } catch (RestClientException
																						 * e) { e.printStackTrace(); }
																						 * if (resp.getMessage() !=
																						 * null) {
																						 * 
																						 * resp.setCode(resp.getMessage(
																						 * ));
																						 * resp.setMessage("Unsuccess");
																						 * } else {
																						 * resp.setMessage("Success"); }
																						 * 
																						 * logger.
																						 * info("Method : viewPatientCurrentDrDetails ends"
																						 * ); return resp; }
																						 */

}
