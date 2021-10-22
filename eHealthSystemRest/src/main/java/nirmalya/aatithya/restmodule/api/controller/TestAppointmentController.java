package nirmalya.aatithya.restmodule.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.api.dao.TestAppointmentDao;
import nirmalya.aatithya.restmodule.api.model.AppointmentModel;
import nirmalya.aatithya.restmodule.api.model.MedTelModel;
import nirmalya.aatithya.restmodule.api.model.SearchModel;
import nirmalya.aatithya.restmodule.api.model.TestModel;
import nirmalya.aatithya.restmodule.api.model.TestReportAPIModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "api")
public class TestAppointmentController {

	@Autowired
	TestAppointmentDao testAppointmentDao;

	Logger logger = LoggerFactory.getLogger(TestAppointmentController.class);
	
	/** MedTel Test Report **/
	@PostMapping(value = "/medtel-screening-test-report")
	public ResponseEntity<JsonResponse<Object>> screeningReportByMedTel(@RequestBody MedTelModel data) {
		logger.info("Method : screeningReportByMedTel starts");
		System.out.println(data);
		logger.info("Method : screeningReportByMedTel ends");
		return testAppointmentDao.screeningReportByMedTel(data);
	}
	
	@PostMapping(value = "/manual-screening-test-report")
	public ResponseEntity<JsonResponse<Object>> manualScreeningReportByMedTel(@RequestBody MedTelModel data) {
		logger.info("Method : manualScreeningReportByMedTel starts");
		System.out.println(data);
		logger.info("Method : manualScreeningReportByMedTel ends");
		return testAppointmentDao.manualScreeningReportByMedTel(data);
	}
	
	/** Others Test Report **/
	@PostMapping(value = "/system-screening-test-report")
	public ResponseEntity<JsonResponse<Object>>systemScreeningReport(@RequestBody MedTelModel data) {
		logger.info("Method : systemScreeningReport starts");
		
		logger.info("Method : systemScreeningReport ends");
		return testAppointmentDao.systemScreeningReport(data);
	}
	
	@PostMapping(value = "/save-screen-test-report")
	public ResponseEntity<JsonResponse<Object>> saveTestReport(@RequestBody TestReportAPIModel data) {
		logger.info("Method : saveTestReport starts");
		System.out.println(data);
		logger.info("Method : saveTestReport ends");
		return testAppointmentDao.saveTestReport(data);
	}
	
	/** Search Patient Id **/
	@GetMapping(value = "/get-regDetails")
	public ResponseEntity<JsonResponse<List<SearchModel>>> getDataByPatId(@RequestParam String regNo) {
		logger.info("Method : getDataByPatId starts");
		
		logger.info("Method : getDataByPatId ends");
		return testAppointmentDao.getDataByPatId(regNo);
	}
	
	/** Screen Test Entry **/
	@PostMapping(value = "/post-addLabAppointment")
	public ResponseEntity<JsonResponse<Object>> screenTestEntry(@RequestBody TestModel data) {
		logger.info("Method : screenTestEntry starts");
		
		logger.info("Method : screenTestEntry ends");
		return testAppointmentDao.screenTestEntry(data);
	}
	
	/** Screen Check Up Entry **/
	@PostMapping(value = "/post-addchkupAppointment")
	public ResponseEntity<JsonResponse<Object>> screenCheckUpEntry(@RequestBody TestModel data) {
		logger.info("Method : screenCheckUpEntry starts");
		
		logger.info("Method : screenCheckUpEntry ends");
		return testAppointmentDao.screenCheckUpEntry(data);
	}
	
	/** Appointment List By Date - Health Screening **/
	@GetMapping(value = "/view-labAppointmentlist")
	public ResponseEntity<JsonResponse<List<AppointmentModel>>> screeningTestListAppointmentByDate(@RequestParam String appontdt) {
		logger.info("Method : screeningTestListAppointmentByDate starts");
		
		logger.info("Method : screeningTestListAppointmentByDate ends");
		return testAppointmentDao.getScreeningTestListAppointmentByDate(appontdt);
	}
	
	/** Appointment List By Date - Health CheckUp **/
	@GetMapping(value = "/view-chkupAppointmentlist")
	public ResponseEntity<JsonResponse<List<AppointmentModel>>> screeningCheckUpListAppointmentByDate(@RequestParam String appontdt) {
		logger.info("Method : screeningCheckUpListAppointmentByDate starts");
		
		logger.info("Method : screeningCheckUpListAppointmentByDate ends");
		return testAppointmentDao.getScreeningCheckUpListAppointmentByDate(appontdt);
	}
	
	/** Change Appointment Status- Screening Test  **/
	@PostMapping(value = "/post-appointmentStatus")
	public ResponseEntity<JsonResponse<Object>> changeScreentTestStatus(@RequestBody TestModel data) {
		logger.info("Method : changeScreentTestStatus starts");
		
		logger.info("Method : changeScreentTestStatus ends");
		return testAppointmentDao.changeScreentTestStatus(data);
	}
	
	/** Change Appointment Status- Health Check Up  **/
	@PostMapping(value = "/post-chkupAppointmentStatus")
	public ResponseEntity<JsonResponse<Object>> changeCheckUpStatus(@RequestBody TestModel data) {
		logger.info("Method : changeCheckUpStatus starts");
		
		logger.info("Method : changeCheckUpStatus ends");
		return testAppointmentDao.changeCheckUpStatus(data); 
	}
	
	/** Health Screening - MedTel Test Data - Patient List **/
	@GetMapping(value = "/view-medteltest-list")
	public ResponseEntity<JsonResponse<List<MedTelModel>>> viewMedTeltestListdata(@RequestParam String page,@RequestParam String search) {
		logger.info("Method : viewMedTeltestListdata starts");
		
		logger.info("Method : viewMedTeltestListdata ends");
		return testAppointmentDao.viewMedTeltestListdata(page,search);
	}
	
	@GetMapping(value = "/view-medteltest-list-throughId")
	public ResponseEntity<JsonResponse<List<MedTelModel>>> viewMedTeltestListdataId(@RequestParam String id) {
		logger.info("Method : viewMedTeltestListdata starts");
		
		logger.info("Method : viewMedTeltestListdata ends");
		return testAppointmentDao.viewMedTeltestListdataId(id);
	}
	
	/** Health Screening - MedTel Test Data - Details **/
	@GetMapping(value = "/view-medteltest-details")
	public ResponseEntity<JsonResponse<Object>> viewMedTelTestDetails(@RequestParam String medtelid) {
		logger.info("Method : viewMedTelTestDetails starts");
		
		logger.info("Method : viewMedTelTestDetails ends");
		return testAppointmentDao.viewMedTelTestDetails(medtelid);
	}
}
