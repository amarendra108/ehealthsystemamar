package nirmalya.aatithya.restmodule.patient.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.asset.model.RestAssetCodeModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.dao.LifeStyleHistoryDao;
import nirmalya.aatithya.restmodule.patient.dao.PatientDashboardDao;
import nirmalya.aatithya.restmodule.patient.model.LifeStyleHistoryModel;
import nirmalya.aatithya.restmodule.patient.model.PatientDashboardRestModel;
import nirmalya.aatithya.restmodule.patient.model.RestPatientDetailsNewModel;
import nirmalya.aatithya.restmodule.patient.model.RestPatientRequestAmbulanceModel;

@RestController
@RequestMapping("user/")
public class PatientDashboardRestController {
	Logger logger = LoggerFactory.getLogger(PatientDashboardRestController.class);

	@Autowired

	PatientDashboardDao patientDashboardDao;

	// Profile
	@GetMapping(value = "editDashBoardProfile")
	public JsonResponse<PatientDashboardRestModel> editDashBoardProfile(@RequestParam String id) {
		logger.info("Method : editDashBoardProfile starts");
		System.out.println(id);
		logger.info("Method : editDashBoardProfile ends");
		return patientDashboardDao.editDashBoardProfile(id);
	}
	 
	// Insurance
	@RequestMapping(value = "dashboardInsurance", method = { RequestMethod.GET })
	public JsonResponse<PatientDashboardRestModel> dashboardInsurance(@RequestParam String id) {

		logger.info("Method : dashboardInsurance starts");
		logger.info("Method : dashboardInsurance ends");

		return patientDashboardDao.dashboardInsurance(id);
	}
	
	 
		// Allergy
		@RequestMapping(value = "dashboardAllergy", method = { RequestMethod.GET })
		public JsonResponse<PatientDashboardRestModel> dashboardAllergy(@RequestParam String id) {

			logger.info("Method : dashboardAllergy starts");
			logger.info("Method : dashboardAllergy ends");

			return patientDashboardDao.dashboardAllergy(id);
		}
		
		// Emergency Contact
				@RequestMapping(value = "editDashBoardEmergencyContact", method = { RequestMethod.GET })
				public JsonResponse<PatientDashboardRestModel> editDashBoardEmergencyContact(@RequestParam String id) {

					logger.info("Method : editDashBoardEmergencyContact starts");
					logger.info("Method : editDashBoardEmergencyContact ends");

					return patientDashboardDao.editDashBoardEmergencyContact(id);
				}
				
				/**
				 *
				 * For Test Report dashboard
				 */
				@RequestMapping(value = "dashboardTestReportn", method = { RequestMethod.GET })
				public JsonResponse<PatientDashboardRestModel> dashboardTestReportn(@RequestParam String id) {

					logger.info("Method : dashboardTestReportn starts");
					logger.info("Method : dashboardTestReportn ends");

					return patientDashboardDao.dashboardTestReportn(id);
				}
				
				/**
				 *
				 * @return Medication
				 */
				@RequestMapping(value = "getBooking1", method = { RequestMethod.GET })
				public List<PatientDashboardRestModel> getBooking1(@RequestParam String id) {

					logger.info("Method : getBooking1 starts");
					logger.info("Method : getBooking1 ends");

					return patientDashboardDao.getBooking1(id);
				}
				
				
				@GetMapping(value = "dashboardAppointment")
				public JsonResponse<PatientDashboardRestModel> dashboardAppointment(@RequestParam String id) {
					logger.info("Method : dashboardAppointment starts");
					System.out.println(id);
					logger.info("Method : dashboardAppointment ends");
					return patientDashboardDao.dashboardAppointment(id);
				}
				 
				
				 
			
				
				/**
				 *
				 * For Family Doctor dashboard
				 */
				@RequestMapping(value = "dashboardFamilyDoctors", method = { RequestMethod.GET })
				public JsonResponse<PatientDashboardRestModel> dashboardFamilyDoctors(@RequestParam String id) {

					logger.info("Method : dashboardFamilyDoctors starts");
					logger.info("Method : dashboardFamilyDoctors ends");

					return patientDashboardDao.dashboardFamilyDoctors(id);
				}
				
				/**
				 *
				 * For Family Doctor dashboard
				 */
				@RequestMapping(value = "dashboardMedCondtnName", method = { RequestMethod.GET })
				public JsonResponse<PatientDashboardRestModel> dashboardMedCondtnName(@RequestParam String id) {

					logger.info("Method : dashboardMedCondtnName starts");
					logger.info("Method : dashboardMedCondtnName ends");

					return patientDashboardDao.dashboardMedCondtnName(id);
				}
				/**
				 *
				 * @return Appointment Count
				 */
				
				
				@RequestMapping(value = "appointmentCount", method = { RequestMethod.GET })
				public List<PatientDashboardRestModel> appointmentCount(@RequestParam String id) {

					logger.info("Method : appointmentCount starts");
					logger.info("Method : appointmentCount ends");

					return patientDashboardDao.appointmentCount(id);
				}
				
		/*
		 * Allergy view		
		 */
				
				@GetMapping(value = "allergyDashboardView")
				public JsonResponse<List<RestPatientDetailsNewModel>> allergyDashboardView(@RequestParam String id) {
					logger.info("Method :allergyDashboardView starts");
					
					
					logger.info("Method :allergyDashboardView endss");
					return patientDashboardDao.allergyDashboardViewDao(id);
					
				}
				
	/*
	 * allergy name drp down
	 * 			
	 */
				
				@RequestMapping(value = "get-allerName-Dashboard-list", method = { RequestMethod.GET })
				public List<DropDownModel> getallerNamelist() {
					logger.info("Method : getallerNamelist starts");

					logger.info("Method : getallerNamelist ends");
					return patientDashboardDao.getallerNamelistDashboardDao();

				}
				
				@RequestMapping(value = "get-allertype-Dashboard-list", method = { RequestMethod.GET })
				public List<DropDownModel> getallertypelist() {
					logger.info("Method : getallertypelist starts");

					logger.info("Method : getallertypelist ends");
					return patientDashboardDao.getallertypelistDashboardDao();

				}
				@RequestMapping(value = "savedpatallergyDashboard", method = { RequestMethod.POST })
				public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> saveemployeeaddress(
						@RequestBody RestPatientDetailsNewModel restPatientDetailsNewModel) {
					logger.info("Method : savedpatallergy starts");

					logger.info("Method : savedpatallergy ends");
					return patientDashboardDao.savedpatallergyDashboardDao(restPatientDetailsNewModel);
				}	
				
/*
 * view vitals
 */
				@GetMapping(value = "editpatientvitalsignnew-dashboard")
				public JsonResponse<List<RestPatientDetailsNewModel>> editpatientvitalsignnew(@RequestParam String id) {
					logger.info("Method :editpatientvitalsignnew starts");
					
					
					logger.info("Method :editpatientvitalsignnew endss");
					return patientDashboardDao.editpatientvitalsignnewdashboardDao(id);
					
				}
				
				@RequestMapping(value = "savevitalsDashboard", method = { RequestMethod.POST })
				public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> savevitalsDashboard(
						@RequestBody RestPatientDetailsNewModel restPatientDetailsNewModel) {
					logger.info("Method : savevitalsDashboard starts");

					logger.info("Method : savevitalsDashboard ends");
					return patientDashboardDao.savevitalsDashboardDao(restPatientDetailsNewModel);
				}	
				
				/**
				 * 
				 * AutoSearch For Ambulance		
				 */
						
						@GetMapping(value = "getAmbulanceListAutoSearch")
						public ResponseEntity<JsonResponse<List<RestPatientRequestAmbulanceModel>>> getAmbulanceListAutoSearch(
								@RequestParam String id) {
							logger.info("Method : getAmbulanceListAutoSearch starts");

							logger.info("Method :getAmbulanceListAutoSearch endss");
							return patientDashboardDao.getAmbulanceListAutoSearch(id);
						}	
						/**
						 * Add Document Upload
						 */
						@PostMapping(value = "rest-savepatientAmbulanceRequest")
						public ResponseEntity<JsonResponse<RestPatientRequestAmbulanceModel>> savepatientAmbulanceRequest(
								@RequestBody RestPatientRequestAmbulanceModel restPatientRequestAmbulanceModel) {
							logger.info("Method : savepatientAmbulanceRequest starts");
							logger.info("Method : savepatientAmbulanceRequest ends");
							return patientDashboardDao.savepatientAmbulanceRequestDao(restPatientRequestAmbulanceModel);
						}
						/*
						 * View data for ambulance request
						 */
						@GetMapping(value = "rest-viewAmbulancaRequest")
						public JsonResponse<List<RestPatientRequestAmbulanceModel>> viewAmbulancaRequest(@RequestParam String id,String place) {
							logger.info("Method :viewAmbulancaRequest starts");
							
							
							logger.info("Method :viewAmbulancaRequest ends");
							return patientDashboardDao.viewAmbulancaRequestDao(id,place);
							
						}	
						
						/*
						 * View data for emergency contact
						 */
						@GetMapping(value = "patientemercontactDashboard")
						public JsonResponse<List<RestPatientDetailsNewModel>> patientemercontactDashboard(@RequestParam String id) {
							logger.info("Method :patient emercontact contact starts");
							
							
							logger.info("Method :patient emercontact ends");
							return patientDashboardDao.patientemercontactDashboardDao(id);
							
						}
						
						/*view data foe family doctor*/
						
						@GetMapping(value = "editpatientfamdocDashboard")
						public JsonResponse<List<RestPatientDetailsNewModel>> editpatientfamdocDashboard(@RequestParam String id) {
							logger.info("Method :patientfamdocnew starts");
							
							
							logger.info("Method :patientfamdocnew ends");
							return patientDashboardDao.editpatientfamdocDashboardDao(id);
							
						}
}
