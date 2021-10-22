package nirmalya.aatithya.restmodule.dashboard.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.dashboard.dao.RestCmDao;
import nirmalya.aatithya.restmodule.dashboard.model.CMDashboardGraphicalModel;
import nirmalya.aatithya.restmodule.user.model.DmDashboardRestModel;

@RestController
@RequestMapping("user/")
public class RestCmController {


	Logger logger = LoggerFactory.getLogger(RestCmController.class);
		
		@Autowired
		
		RestCmDao cmDashboardDao;	
		@GetMapping(value = "genderWise-patientno")
		public ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> cmDashboardGenderWisePatientno(@RequestParam("state") String state,@RequestParam("date") String date,@RequestParam("type") String type) {
			logger.info("Method : viewcmDashboardDailyRegistration starts");

			logger.info("Method :viewcmDashboardDailyRegistration ends");
			return cmDashboardDao.cmDashboardGenderWisePatientno(state,date,type);
		}
		
		@GetMapping(value = "areWise-patientno")
		public ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> cmDashboardAreaWisePatientno(@RequestParam("state") String state,@RequestParam("date") String date,@RequestParam("type") String type) {
			logger.info("Method : cmDashboardAreaWisePatientno starts");

			logger.info("Method :cmDashboardAreaWisePatientno ends");
			return cmDashboardDao.cmDashboardAreaWisePatientno(state,date,type);
		}
		@GetMapping(value = "ageWise-patientno")
		public ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> cmDashboardAgeWisePatientno(@RequestParam("state") String state,@RequestParam("date") String date,@RequestParam("type") String type) {
				logger.info("Method : cmDashboardAgeWisePatientno starts");

			logger.info("Method :cmDashboardAgeWisePatientno ends");
			return cmDashboardDao.cmDashboardAgeWisePatientno(state,date,type);
		}
		
		@GetMapping(value = "diseaseWise-patientno")
		public ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> cmDashboardDiseaseMaleFemaleWisePatientno(@RequestParam("state") String state,@RequestParam("date") String date,@RequestParam("type") String type) {
				logger.info("Method : cmDashboardAgeWisePatientno starts");

			logger.info("Method :cmDashboardAgeWisePatientno ends");
			return cmDashboardDao.cmDashboardDiseaseMaleFemaleWisePatientno(state,date,type);
		}
		
		
		@GetMapping(value = "riskWise-patientno")
		public ResponseEntity<JsonResponse<CMDashboardGraphicalModel>> cmDashboardRiskWisePatientno(@RequestParam String stateId,@RequestParam String date,@RequestParam String type) {
			logger.info("Method : cmDashboardRiskWisePatientno starts");

			logger.info("Method :cmDashboardRiskWisePatientno ends");
			return cmDashboardDao.cmDashboardRiskWisePatientno(stateId,date,type);
		}
		@GetMapping(value = "phcWise-patientno")
		public ResponseEntity<JsonResponse<CMDashboardGraphicalModel>> cmDashboardPhcWisePatientno(@RequestParam String stateId,@RequestParam String date,@RequestParam String type) {
			logger.info("Method : cmDashboardPhcWisePatientno starts");

			logger.info("Method :cmDashboardRiskWisePatientno ends");
			return cmDashboardDao.cmDashboardPhcWisePatientno(stateId,date,type);
		}
	/*	@GetMapping(value = "dieaseWise-patientno")
		public ResponseEntity<JsonResponse<CMDashboardGraphicalModel>> cmDashboardDieaseWisePatientno(@RequestParam String stateId,@RequestParam String date,@RequestParam String type) {
			logger.info("Method : cmDashboardDieaseWisePatientno starts");

			logger.info("Method :cmDashboardDieaseWisePatientno ends");
			return cmDashboardDao.cmDashboardDieaseWisePatientno(stateId,date,type);
		}*/
		@GetMapping(value = "percentageDieaseWise-patientno")
		public ResponseEntity<JsonResponse<CMDashboardGraphicalModel>> cmDashboardpercentageDieaseWisePatientno(@RequestParam String stateId,@RequestParam String date,@RequestParam String type) {
			logger.info("Method : cmDashboardpercentageDieaseWisePatientno starts");

			logger.info("Method :cmDashboardpercentageDieaseWisePatientno ends");
			return cmDashboardDao.cmDashboardpercentageDieaseWisePatientno(stateId,date,type);
		}
		
		
		//Total Health Checkup Status
		
				@GetMapping(value = "rest-totalHealthCheckup")
				public ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> totalHealthCheckup(@RequestParam("state") String state,@RequestParam("date") String date,@RequestParam("type") String type) {
					logger.info("Method : totalHealthCheckup starts");

					logger.info("Method :totalHealthCheckup ends");
					return cmDashboardDao.totalHealthCheckup(state,date,type);
				}
				
		//High Risk Data Location Wise
				
						@GetMapping(value = "rest-getHighRiskLocationWise")
						public ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> getHighRiskLocationWise(@RequestParam("state") String state,@RequestParam("date") String date,@RequestParam("type") String type) {
							logger.info("Method : getHighRiskLocationWise starts");

							logger.info("Method :totalHealthCheckup ends");
							return cmDashboardDao.getHighRiskLocationWise(state,date,type);
						}



		// Rest Disese wise total patient no
				@GetMapping(value = "dieaseWise-patientno")
				public JsonResponse<List<CMDashboardGraphicalModel>> cmDashboardDieaseWisePatientno(@RequestParam String state,@RequestParam String date,@RequestParam String type) {
					logger.info("Method : cmDashboardDieaseWisePatientno starts");

					logger.info("Method :cmDashboardDieaseWisePatientno ends");
					return cmDashboardDao.cmDashboardDieaseWisePatientnoDao(state,date,type);
				}


		//Treanding Disease

		@GetMapping(value = "treanding-disease")
				public ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> cmDashboardTreandingDisease(@RequestParam("state") String state,@RequestParam("date") String date,@RequestParam("type") String type) {
						logger.info("Method : cmDashboardTreandingDisease starts");

					logger.info("Method :cmDashboardTreandingDisease ends");
					return cmDashboardDao.cmDashboardTreandingDisease(state,date,type);
				}

		//count test details
				@GetMapping(value = "rest-countTestDetails")
				public ResponseEntity<JsonResponse<CMDashboardGraphicalModel>> countTestDetails(@RequestParam("state") String state,@RequestParam("date") String date,@RequestParam("type") String type) {
					logger.info("Method : countTestDetails starts");

					logger.info("Method :countTestDetails ends");
					return cmDashboardDao.countTestDetailsDao(state,date,type);
				}	
}
