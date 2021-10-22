package nirmalya.aatithya.restmodule.pm.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.pm.dao.RestHieDashboardDao;
import nirmalya.aatithya.restmodule.pm.model.RestHieDashboardModel;

@RestController
@RequestMapping("pm/")
public class RestHieDashboardController {
	Logger logger = LoggerFactory.getLogger(RestHieDashboardController.class);

	@Autowired

	RestHieDashboardDao restHieDashboardDao;
	/*
	 * for Country list id
	 */

	@RequestMapping(value = "rest-pmDashboardgetId", method = { RequestMethod.GET })
	public List<DropDownModel> pmDashboardgetId(@RequestParam String id) {
		logger.info("Method : pmDashboardgetId starts");

		logger.info("Method : pmDashboardgetId ends");
		return restHieDashboardDao.pmDashboardgetId(id);
	}

	// count test details
	@GetMapping(value = "rest-getCountHieDashboards")
	public ResponseEntity<JsonResponse<RestHieDashboardModel>> getCountHieDashboard(
			@RequestParam("country") String country, @RequestParam("date") String date,
			@RequestParam("type") String type) {
		logger.info("Method : getCountHieDashboard starts");

		logger.info("Method :getCountHieDashboard ends");
		return restHieDashboardDao.getCountHieDashboards(country, date, type);
	}

	// Diagnostic Lab
	@GetMapping(value = "rest-getDiagnosticLab")
	public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getDiagnosticLab(
			@RequestParam("country") String country, @RequestParam("date") String date,
			@RequestParam("type") String type) {
		logger.info("Method : getTotalHospital starts");

		logger.info("Method :getTotalHospital ends");
		return restHieDashboardDao.getDiagnosticLab(country, date, type);
	}

	// Pathology Lab
	@GetMapping(value = "rest-getPathologyLab")
	public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getPathologyLab(
			@RequestParam("country") String country, @RequestParam("date") String date,
			@RequestParam("type") String type) {
		logger.info("Method : getPathologyLab starts");

		logger.info("Method :getPathologyLab ends");
		return restHieDashboardDao.getPathologyLab(country, date, type);
	}

	// Total Pharmacy

	@GetMapping(value = "rest-getTotalPharmacy")
	public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalPharmacy(
			@RequestParam("country") String country, @RequestParam("date") String date,
			@RequestParam("type") String type) {
		logger.info("Method : getTotalPharmacy starts");

		logger.info("Method :getTotalPharmacy ends");
		return restHieDashboardDao.getTotalPharmacy(country, date, type);
	}

	// Total Ambulance

	
	@GetMapping(value = "rest-getTotalAmbulance")
	public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalAmbulance(@RequestParam("country") String country, @RequestParam("date") String date,@RequestParam("type") String type) {
		logger.info("Method : getTotalAmbulance starts");

		logger.info("Method :getTotalAmbulance ends");
		return restHieDashboardDao.getTotalAmbulance(country, date, type);
	}
	 

	// Total Blood Bank
	@GetMapping(value = "rest-getTotalBloodBank")
	public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalBloodBank(
			@RequestParam("country") String country, @RequestParam("date") String date,
			@RequestParam("type") String type) {
		logger.info("Method : getTotalBloodBank starts");

		logger.info("Method :getTotalBloodBank ends");
		return restHieDashboardDao.getTotalBloodBank(country, date, type);
	}

	// Total Insurance Service Provider
	@GetMapping(value = "rest-getTotalInsuranceProvider")
	public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalInsuranceProvider(
			@RequestParam("country") String country, @RequestParam("date") String date,
			@RequestParam("type") String type) {
		logger.info("Method : getTotalInsuranceProvider starts");

		logger.info("Method :getTotalInsuranceProvider ends");
		return restHieDashboardDao.getTotalInsuranceProvider(country, date, type);
	}

	// Total Other NGO and HSP
	/*
	 * @GetMapping(value = "rest-getTotalNgoHsp") public
	 * ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>
	 * getTotalNgoHsp(@RequestParam("country") String country,@RequestParam("date")
	 * String date,@RequestParam("type") String type) {
	 * logger.info("Method : getTotalNgoHsp starts");
	 * 
	 * logger.info("Method :getTotalNgoHsp ends"); return
	 * restHieDashboardDao.getTotalNgoHsp(country,date,type); }
	 */

	// Total Blood Donor-Health Hero's

	/*@GetMapping(value = "rest-getTotalBloodDonor")
	public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalBloodDonor(
			@RequestParam("country") String country, @RequestParam("date") String date,
			@RequestParam("type") String type) {
		logger.info("Method : getTotalBloodDonor starts");

		logger.info("Method :getTotalBloodDonor ends");
		return restHieDashboardDao.getTotalBloodDonor(country, date, type);
	}*/
	 

	// Total Organ Donor-Health Hero's
	
	@GetMapping(value = "rest-getTotalOrganDonor")
	public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalOrganDonor(
			@RequestParam("country") String country, @RequestParam("date") String date,
			@RequestParam("type") String type) {
		logger.info("Method : getTotalOrganDonor starts");

		logger.info("Method :getTotalOrganDonor ends");
		return restHieDashboardDao.getTotalOrganDonor(country, date, type);
	}

	// Age Wise

	@GetMapping(value = "rest-getTotalAgeWise")
	public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalAgeWise(
			@RequestParam("country") String country, @RequestParam("date") String date,
			@RequestParam("type") String type) {
		logger.info("Method : getTotalAgeWise starts");

		logger.info("Method :getTotalAgeWise ends");
		return restHieDashboardDao.getTotalAgeWise(country, date, type);
	}

	// Gender Wise

	@GetMapping(value = "rest-getTotalGenderWises")
	public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalGenderWise(
			@RequestParam("country") String country, @RequestParam("date") String date,
			@RequestParam("type") String type) {
		logger.info("Method : getTotalGenderWise starts");

		logger.info("Method :getTotalGenderWise ends");
		return restHieDashboardDao.getTotalGenderWise(country, date, type);
	}

	// Parameters Wise Testing
	/*
	 * @GetMapping(value = "rest-getTotalParameterTesting") public
	 * ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>
	 * getTotalParameterTesting(@RequestParam("country") String
	 * country,@RequestParam("date") String date,@RequestParam("type") String type)
	 * { logger.info("Method : getTotalParameterTesting starts");
	 * 
	 * logger.info("Method :getTotalParameterTesting ends"); return
	 * restHieDashboardDao.getTotalParameterTesting(country,date,type); }
	 */

	// Low Risk High Risk

	/*@GetMapping(value = "rest-getTotalLowRisk")
	public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalLowRisk(
			@RequestParam("country") String country, @RequestParam("date") String date,
			@RequestParam("type") String type) {
		logger.info("Method : getTotalLowRisk starts");

		logger.info("Method :getTotalLowRisk ends");
		return restHieDashboardDao.getTotalLowRisk(country, date, type);
	}*/

	// Total Doctors Pvt./Govt.

	@GetMapping(value = "rest-getTotalDoctorsPvt")
	public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalDoctorsPvt(
			@RequestParam("country") String country, @RequestParam("date") String date,
			@RequestParam("type") String type) {
		logger.info("Method : getTotalDoctorsPvt starts");

		logger.info("Method :getTotalDoctorsPvt ends");
		return restHieDashboardDao.getTotalDoctorsPvt(country, date, type);
	}
	/************************** get Hospital*********************/
	@GetMapping(value = "rest-hie-getTotalHospital")
	public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalHospital(@RequestParam("country") String country,@RequestParam("date") String date,@RequestParam("type") String type) {
		logger.info("Method : getTotalHospital starts");

		logger.info("Method :getTotalHospital ends");
		return restHieDashboardDao.getTotalHospitalDao(country,date,type);
	}
	/************************** get Clinic*********************/
	@GetMapping(value = "rest-hie-getTotalClinic")
	public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalClinic(@RequestParam("country") String country,@RequestParam("date") String date,@RequestParam("type") String type) {
		logger.info("Method : getTotalClinic starts");
		
		logger.info("Method :getTotalClinic ends");
		return restHieDashboardDao.getTotalClinicDao(country,date,type);
	}
}
