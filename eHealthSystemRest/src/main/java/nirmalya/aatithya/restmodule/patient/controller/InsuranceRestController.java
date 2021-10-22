package nirmalya.aatithya.restmodule.patient.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.employee.model.ReimbursementModel;
import nirmalya.aatithya.restmodule.patient.dao.InsuranceRestDao;
import nirmalya.aatithya.restmodule.patient.dao.LifeStyleHistoryDao;
import nirmalya.aatithya.restmodule.patient.model.InsuranceRestModel;
import nirmalya.aatithya.restmodule.patient.model.LifeStyleHistoryModel;
import nirmalya.aatithya.restmodule.patient.model.PatientHistoryModel;
@RestController
@RequestMapping("user/")
public class InsuranceRestController {
	
	Logger logger = LoggerFactory.getLogger(InsuranceRestController.class);

	@Autowired

	InsuranceRestDao insuranceRestDao;

	/*
	 * Post mapping for Add  Insurance
	 */
	@PostMapping(value = "patientInsuranceAdd")
	public JsonResponse<Object> patientInsuranceAdd(@RequestBody InsuranceRestModel employee) {
		logger.info("Method : patientInsuranceAdd starts");

		logger.info("Method : patientInsuranceAdd ends");
		return insuranceRestDao.patientInsuranceAdd(employee);
	}
	
	/*
	 * For Insurance
	 */
	@GetMapping(value = "getPatientInsuranceView")
	public JsonResponse<List<InsuranceRestModel>> getAllPatientInsuranceView() {
		logger.info("Method :getAllPatientInsuranceView starts");
		
		
		logger.info("Method :getAllPatientInsuranceView endss");
		return insuranceRestDao.getAllPatientInsuranceView();
		
	}
	
	/*
	 *
	 * Edit Insurance
	 *
	 */
	@RequestMapping(value = "edit-patient-insurance", method = { RequestMethod.GET })
	public JsonResponse<List<InsuranceRestModel>> editPatientInsurance(@RequestParam String id) {
		logger.info("Method : editPatientInsurance rest starts");

		logger.info("Method :editPatientInsurance rest ends");
		return insuranceRestDao.editPatientInsurance(id);
	}
	
	/*
	 * Post mapping for Add Life Style History
	 */
	@PostMapping(value = "addInsurance")
	public JsonResponse<Object> addInsurance(@RequestBody InsuranceRestModel employee) {
		logger.info("Method : addInsurance starts");

		logger.info("Method : addInsurance ends");
		return insuranceRestDao.addInsurance(employee);
	}
	
	
	
	
}
