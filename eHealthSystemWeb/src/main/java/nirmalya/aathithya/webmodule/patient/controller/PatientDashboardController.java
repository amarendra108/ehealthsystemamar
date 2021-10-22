package nirmalya.aathithya.webmodule.patient.controller;

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
import java.util.List;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.patient.model.PatientDashboardModel;
import nirmalya.aathithya.webmodule.patient.model.PatientDetailsNewModel;
import nirmalya.aathithya.webmodule.patient.model.PatientRequestAmbulanceModel;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigInteger;
import java.util.Arrays;
@Controller
@RequestMapping(value = { "user/" })
public class PatientDashboardController {
	Logger logger = LoggerFactory.getLogger(PatientDashboardController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	/*
	 * Get Mapping for view-my-dashboard
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("view-my-dashboard")
	public String viewMyDashboard(Model model, HttpSession session) {

		logger.info("Method : viewMyDashboard starts");
		// Dashboard Medication
		String userId = (String) session.getAttribute("USER_ID");
		try {
			PatientDashboardModel[] region1 = restTemplate.getForObject(env.getUserUrl() + "getBooking1?id="+userId,
					PatientDashboardModel[].class);
			List<PatientDashboardModel> RegiontList1 = Arrays.asList(region1);
			model.addAttribute("bookinngList1", RegiontList1);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Appointment Count 
		
				String userId1 = (String) session.getAttribute("USER_ID");
				try {
					PatientDashboardModel[] region = restTemplate.getForObject(env.getUserUrl() + "appointmentCount?id="+userId1,
							PatientDashboardModel[].class);
					List<PatientDashboardModel> appointmentCount = Arrays.asList(region);
					model.addAttribute("appointmentCount", appointmentCount);
				} catch (RestClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//		List<String> modlist = (List<String>) session.getAttribute("MODULELIST");
//		
//		session.setAttribute("moduleId", modlist.get(0));
				
		//alergy name drop down		
				try {

					DropDownModel[] allerName = restTemplate.getForObject(env.getUserUrl() + "get-allerName-Dashboard-list",
							DropDownModel[].class);
					List<DropDownModel> alernameList = Arrays.asList(allerName);

					model.addAttribute("alernameList", alernameList);
				} catch (Exception e) {
					e.printStackTrace();

				}
//alergy type drop down
				try {

					DropDownModel[] allerName = restTemplate.getForObject(env.getUserUrl() + "get-allertype-Dashboard-list",
							DropDownModel[].class);
					List<DropDownModel> alertypeList = Arrays.asList(allerName);

					model.addAttribute("alertypeList", alertypeList);
				} catch (Exception e) {
					e.printStackTrace();

				}		
		
		logger.info("Method : viewMyDashboard ends");
		return "patient/patientDashboard";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("view-my-dashboard-profile")
	public @ResponseBody JsonResponse<PatientDashboardModel> editDashBoardProfile(HttpSession session) {

		logger.info("Method : editDashBoardProfile starts");
		
		JsonResponse<PatientDashboardModel> response = new JsonResponse<PatientDashboardModel>();
		String userId = "";
		try {

			userId = (String) session.getAttribute("USER_ID");

			response = restTemplate.getForObject(env.getUserUrl() + "editDashBoardProfile?id=" + userId,
					JsonResponse.class);
		} catch (

		RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		PatientDashboardModel customer = mapper.convertValue(response.getBody(),
				new TypeReference<PatientDashboardModel>() {
				});
		
		String profile = null;
		if (customer.getPatImage() != null && customer.getPatImage() != ""
				&& !customer.getPatImage().equals("null")) {

			profile = env.getBaseURL() + "document/profile/" + customer.getPatImage();
			customer.setPatImage(profile);
		}

		response.setBody(customer);
		System.out.println("@@@@@@@@@@DDDDDDDD"+profile);
		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}
		System.out.println("@@@@@@@@@@@"+response);
		logger.info("Method : editDashBoardProfile ends");
		return response;
	}
	
	
	// Emergency Contact
	@SuppressWarnings("unchecked")
	@GetMapping("view-my-dashboard-emergency-contact-profile")
	public @ResponseBody JsonResponse<PatientDashboardModel> editDashBoardEmergencyContact(HttpSession session) {

		logger.info("Method : editDashBoardEmergencyContact starts");
		
		JsonResponse<PatientDashboardModel> response = new JsonResponse<PatientDashboardModel>();
		String userId = "";
		try {

			userId = (String) session.getAttribute("USER_ID");

			response = restTemplate.getForObject(env.getUserUrl() + "editDashBoardEmergencyContact?id=" + userId,
					JsonResponse.class);
		} catch (

		RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		PatientDashboardModel customer = mapper.convertValue(response.getBody(),
				new TypeReference<PatientDashboardModel>() {
				});
	
		
		response.setBody(customer);
		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}
		logger.info("Method : editDashBoardEmergencyContact ends");
		return response;
	}
	
	
	//Appointment Dashboard
	
	@SuppressWarnings("unchecked")

	@GetMapping("view-my-dashboard-appointment")
	public @ResponseBody JsonResponse<PatientDashboardModel> dashboardAppointment(HttpSession session) {

		logger.info("Method : dashboardAppointment starts");

		JsonResponse<PatientDashboardModel> response = new JsonResponse<PatientDashboardModel>();
		String userId = "";
		try {

			userId = (String) session.getAttribute("USER_ID");

			response = restTemplate.getForObject(env.getUserUrl() + "dashboardAppointment?id=" + userId,
					JsonResponse.class);
		} catch (

		RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		PatientDashboardModel customer = mapper.convertValue(response.getBody(),
				new TypeReference<PatientDashboardModel>() {
				});

		response.setBody(customer);
		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}
		logger.info("Method : dashboardAppointment ends");
		return response;
	}
	
	//Insurance Dashboard
	
		@SuppressWarnings("unchecked")
		@GetMapping("view-my-dashboard-ainsurance")
		public @ResponseBody JsonResponse<PatientDashboardModel> dashboardInsurance(HttpSession session) {

			logger.info("Method : dashboardInsurance starts");
			
			JsonResponse<PatientDashboardModel> response = new JsonResponse<PatientDashboardModel>();
			String userId = "";
			try {

				userId = (String) session.getAttribute("USER_ID");

				response = restTemplate.getForObject(env.getUserUrl() + "dashboardInsurance?id=" + userId,
						JsonResponse.class);
			} catch (

			RestClientException e) {
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();

			PatientDashboardModel customer = mapper.convertValue(response.getBody(),
					new TypeReference<PatientDashboardModel>() {
					});
		
			
			response.setBody(customer);
			if (response.getMessage() != null && response.getMessage() != "") {
				response.setCode(response.getMessage());
				response.setMessage("Unsuccess");

			} else {
				response.setMessage("Success");
			}
			logger.info("Method : dashboardInsurance ends");
			return response;
		}
		

		//Allergy Dashboard
		@SuppressWarnings("unchecked")
		@GetMapping("view-my-dashboard-aallergy")
		public @ResponseBody JsonResponse<PatientDashboardModel> dashboardAllergy(HttpSession session) {

			logger.info("Method : dashboardAllergy starts");
			
			JsonResponse<PatientDashboardModel> response = new JsonResponse<PatientDashboardModel>();
			String userId = "";
			try {

				userId = (String) session.getAttribute("USER_ID");

				response = restTemplate.getForObject(env.getUserUrl() + "dashboardAllergy?id=" + userId,
						JsonResponse.class);
			} catch (

			RestClientException e) {
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();

			PatientDashboardModel customer = mapper.convertValue(response.getBody(),
					new TypeReference<PatientDashboardModel>() {
					});
		
			
			response.setBody(customer);
			if (response.getMessage() != null && response.getMessage() != "") {
				response.setCode(response.getMessage());
				response.setMessage("Unsuccess");

			} else {
				response.setMessage("Success");
			}
			logger.info("Method : dashboardAllergy ends");
			return response;
		}
			
			
		
			
			//Test Report Name Dashboard
			
			@SuppressWarnings("unchecked")
			@GetMapping("view-my-dashboard-test-report-name")
			public @ResponseBody JsonResponse<PatientDashboardModel> dashboardTestReportn(HttpSession session) {

				logger.info("Method : dashboardTestReportn starts");
				
				JsonResponse<PatientDashboardModel> response = new JsonResponse<PatientDashboardModel>();
				String userId = "";
				try {

					userId = (String) session.getAttribute("USER_ID");

					response = restTemplate.getForObject(env.getUserUrl() + "dashboardTestReportn?id=" + userId,
							JsonResponse.class);
				} catch (

				RestClientException e) {
					e.printStackTrace();
				}
				ObjectMapper mapper = new ObjectMapper();

				PatientDashboardModel customer = mapper.convertValue(response.getBody(),
						new TypeReference<PatientDashboardModel>() {
						});
			
				
				response.setBody(customer);
				if (response.getMessage() != null && response.getMessage() != "") {
					response.setCode(response.getMessage());
					response.setMessage("Unsuccess");

				} else {
					response.setMessage("Success");
				}
				logger.info("Method : dashboardTestReportn ends");
				return response;
			}
			
			//Patient Family Doctors  Dashboard
			@SuppressWarnings("unchecked")
			@GetMapping("view-my-dashboard-family-doctors")
			public @ResponseBody JsonResponse<PatientDashboardModel> dashboardFamilyDoctors(HttpSession session) {

				logger.info("Method : dashboardFamilyDoctors starts");
				
				JsonResponse<PatientDashboardModel> response = new JsonResponse<PatientDashboardModel>();
				String userId = "";
				try {

					userId = (String) session.getAttribute("USER_ID");

					response = restTemplate.getForObject(env.getUserUrl() + "dashboardFamilyDoctors?id=" + userId,
							JsonResponse.class);
				} catch (

				RestClientException e) {
					e.printStackTrace();
				}
				ObjectMapper mapper = new ObjectMapper();

				PatientDashboardModel customer = mapper.convertValue(response.getBody(),
						new TypeReference<PatientDashboardModel>() {
						});
			
				
				response.setBody(customer);
				if (response.getMessage() != null && response.getMessage() != "") {
					response.setCode(response.getMessage());
					response.setMessage("Unsuccess");

				} else {
					response.setMessage("Success");
				}
				logger.info("Method : dashboardFamilyDoctors ends");
				return response;
			}
			
			//Patient MedCondition Name  Dashboard
			@SuppressWarnings("unchecked")
			@GetMapping("view-my-dashboard-med-condition-name")
			public @ResponseBody JsonResponse<PatientDashboardModel> dashboardMedCondtnName(HttpSession session) {

				logger.info("Method : dashboardMedCondtnName starts");
				
				JsonResponse<PatientDashboardModel> response = new JsonResponse<PatientDashboardModel>();
				String userId = "";
				try {

					userId = (String) session.getAttribute("USER_ID");

					response = restTemplate.getForObject(env.getUserUrl() + "dashboardMedCondtnName?id=" + userId,
							JsonResponse.class);
				} catch (

				RestClientException e) {
					e.printStackTrace();
				}
				ObjectMapper mapper = new ObjectMapper();

				PatientDashboardModel customer = mapper.convertValue(response.getBody(),
						new TypeReference<PatientDashboardModel>() {
						});
			
				
				response.setBody(customer);
				if (response.getMessage() != null && response.getMessage() != "") {
					response.setCode(response.getMessage());
					response.setMessage("Unsuccess");

				} else {
					response.setMessage("Success");
				}
				logger.info("Method : dashboardMedCondtnName ends");
				return response;
			}
			
			
			/*
			 * VIEW alergy
			 */

			@SuppressWarnings("unchecked")
			@GetMapping("view-my-dashboard-alergy-through-ajax")
			public @ResponseBody List<PatientDetailsNewModel> allergyDashboardView(HttpSession session) {

				logger.info("Method :allergyDashboardView starts");

				JsonResponse<List<PatientDetailsNewModel>> resp = new JsonResponse<List<PatientDetailsNewModel>>();
				String userId = "";

				try {
					userId = (String) session.getAttribute("USER_ID");
					resp = restTemplate.getForObject(env.getUserUrl() + "allergyDashboardView?id=" + userId, JsonResponse.class);
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

				logger.info("Method :allergyDashboardView ends");
				return resp.getBody();
			}
		
			/*
			 * Allergy Save
			 * 
			 */
			@SuppressWarnings("unchecked")
			@PostMapping("view-my-dashboard-alergy-save")
			public @ResponseBody JsonResponse<Object> savedpatallergyDashboard(
					@RequestBody PatientDetailsNewModel patientDetailsNewModel, HttpSession session) {
				logger.info("Method : savedpatallergyDashboard starts");
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
					resp = restTemplate.postForObject(env.getUserUrl() + "savedpatallergyDashboard", patientDetailsNewModel,
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

				logger.info("Method : savedpatallergyDashboard starts");
				return resp;
			}
			
			/*
			 * view vital sign
			 */

					
			@SuppressWarnings("unchecked")
			@GetMapping("view-my-dashboard-edit-vitalsign")
			public @ResponseBody List<PatientDetailsNewModel> editpatientvitalsignnew(HttpSession session) {

				logger.info("Method :editpatientvitalsignnew starts");

				JsonResponse<List<PatientDetailsNewModel>> resp = new JsonResponse<List<PatientDetailsNewModel>>();
				String userId = "";

				try {
					userId = (String) session.getAttribute("USER_ID");
					resp = restTemplate.getForObject(env.getUserUrl() + "editpatientvitalsignnew-dashboard?id=" + userId, JsonResponse.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				ObjectMapper mapper = new ObjectMapper();

				List<PatientDetailsNewModel> patientHistoryModel = mapper.convertValue(resp.getBody(),
						new TypeReference<List<PatientDetailsNewModel>>() {
						});
				
				if (patientHistoryModel.get(0).getTempCs() != null) {
					Double fh = (patientHistoryModel.get(0).getTempCs() * (9 / 5)) + 32;
					patientHistoryModel.get(0).setTempFah(fh);
				}

				resp.setBody(patientHistoryModel);
				if (resp.getMessage() != "" && resp.getMessage() != null) {
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("Success");
				}
							System.out.println("sdfffffffffffff"+resp.getBody());
				logger.info("Method :editpatientvitalsignnew ends");
				return resp.getBody();
			}
			
			/*
			 * vitals Save
			 * 
			 */
			@SuppressWarnings("unchecked")
			@PostMapping("view-my-dashboard-alergy-vitals-add")
			public @ResponseBody JsonResponse<Object> savevitalsDashboard(
					@RequestBody PatientDetailsNewModel patientDetailsNewModel, HttpSession session) {
				logger.info("Method : savevitalsDashboard starts");
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
					resp = restTemplate.postForObject(env.getUserUrl() + "savevitalsDashboard", patientDetailsNewModel,
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
				System.out.println("Add############################"+resp);
				logger.info("Method : savevitalsDashboard starts");
				return resp;
			}
			/*
			 * AutoSearch For Ambulance		
			 */
					
					@SuppressWarnings("unchecked")
					@PostMapping(value = { "view-my-dashboard-ambulance-get-list" })
					public @ResponseBody JsonResponse<PatientRequestAmbulanceModel> getFamilyListAutoSearch(Model model,
							@RequestBody String searchValue, BindingResult result) {
						logger.info("Method : getAmbulanceListAutoSearch starts");
						
						JsonResponse<PatientRequestAmbulanceModel> res = new JsonResponse<PatientRequestAmbulanceModel>();

						try {
							res = restTemplate.getForObject(env.getUserUrl()+ "getAmbulanceListAutoSearch?id=" + searchValue,
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
						
						logger.info("Method : getAmbulanceListAutoSearch ends");
						return res;
					}
					/*
					 * save patient Family Request	
					 */
							
					@SuppressWarnings("unchecked")
					@PostMapping("view-my-dashboard-ambulanceRequest-save")
					public @ResponseBody JsonResponse<Object> savepatientAmbulanceRequest(
							@RequestBody PatientRequestAmbulanceModel patientRequestAmbulanceModel, HttpSession session) {
						logger.info("Method : savepatientAmbulanceRequest starts");
						JsonResponse<Object> resp = new JsonResponse<Object>();
						String userId = "";
						//String roleId = "";
						
						//ArrayList<String> demo = new ArrayList<String>();

						
						try {
							userId = (String) session.getAttribute("USER_ID");
							//demo = (ArrayList<String>) session.getAttribute("USER_ROLES");

						} catch (Exception e) {
							e.printStackTrace();
						}
							String pId = new String(userId);
							patientRequestAmbulanceModel.setPatientId(pId);
							
							

						
						try {
							resp = restTemplate.postForObject(env.getUserUrl() + "rest-savepatientAmbulanceRequest", patientRequestAmbulanceModel,
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

						logger.info("Method : savepatientAmbulanceRequest starts");
						return resp;
					}		
					
		/*
		 * view Ambulane Request			
		 */
					
					 @SuppressWarnings("unchecked")
						@GetMapping("view-my-dashboard-ambulance-request")
						public @ResponseBody List<PatientRequestAmbulanceModel> viewAmbulancaRequest(HttpSession session,@RequestParam String place) {

							logger.info("Method :viewAmbulancaRequest starts");

							JsonResponse<List<PatientRequestAmbulanceModel>> resp = new JsonResponse<List<PatientRequestAmbulanceModel>>();
							String userId="";
							try {
								userId = (String) session.getAttribute("USER_ID");
								resp = restTemplate.getForObject(env.getUserUrl() + "rest-viewAmbulancaRequest?id="+userId + "&place=" +place, JsonResponse.class);
							} catch (Exception e) {
								e.printStackTrace();
							}
							ObjectMapper mapper = new ObjectMapper();

							List<PatientRequestAmbulanceModel> patientHistoryModel = mapper.convertValue(resp.getBody(),
									new TypeReference<List<PatientRequestAmbulanceModel>>() {
									});
							resp.setBody(patientHistoryModel);
							if (resp.getMessage() != "" && resp.getMessage() != null) {
								resp.setCode(resp.getMessage());
								resp.setMessage("Unsuccess");
							} else {
								resp.setMessage("Success");
							}
							System.out.println("SSSSS######"+resp.getBody());
							logger.info("Method :viewAmbulancaRequest ends");
							return resp.getBody();
						}
				
					 /*
						 * view emergency contact
						 */
						@SuppressWarnings("unchecked")
						@GetMapping("view-my-dashboard-edit-emercontact")
						public @ResponseBody List<PatientDetailsNewModel> editpatientemercontactnew(HttpSession session) {

							logger.info("Method :editpatientemercontactnew starts");

							JsonResponse<List<PatientDetailsNewModel>> resp = new JsonResponse<List<PatientDetailsNewModel>>();
							String userId = "";
							try {
								userId = (String) session.getAttribute("USER_ID");
								resp = restTemplate.getForObject(env.getUserUrl() + "patientemercontactDashboard?id=" + userId,
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
						@GetMapping("view-my-dashboard-edit-famdoc")
						public @ResponseBody List<PatientDetailsNewModel> editpatientfamdocnew(HttpSession session) {

							logger.info("Method :editpatientfamdocnew starts");

							JsonResponse<List<PatientDetailsNewModel>> resp = new JsonResponse<List<PatientDetailsNewModel>>();

							String userId = "";
							try {
								userId = (String) session.getAttribute("USER_ID");
								resp = restTemplate.getForObject(env.getUserUrl() + "editpatientfamdocDashboard?id=" + userId,
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
}
