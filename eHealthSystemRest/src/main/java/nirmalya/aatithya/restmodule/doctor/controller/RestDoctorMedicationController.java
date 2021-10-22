package nirmalya.aatithya.restmodule.doctor.controller;

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

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.doctor.dao.DoctorMedicationDao;
import nirmalya.aatithya.restmodule.patient.model.RestPatientMedicationModel;

@RestController
@RequestMapping(value = { "doctor/" })
public class RestDoctorMedicationController {
	Logger logger = LoggerFactory.getLogger(RestDoctorMedicationController.class);

	@Autowired
	DoctorMedicationDao medicationDao;
	
	/*
	 * AutoSearch for Medicine list
	 */

	@GetMapping(value = "rest-medicineNameList")
	public JsonResponse<List<RestPatientMedicationModel>> getmedNameAutoSearchList(@RequestParam String id) {
		logger.info("Method : getmedNameAutoSearchList starts");

		logger.info("Method :getmedNameAutoSearchList ends");
		return medicationDao.getMedNameAutoSearchListDao(id);
	}
	
	/*
	 * AutoSearch for Test Name
	 */

	@GetMapping(value = "rest-TestNameListList")
	public JsonResponse<List<RestPatientMedicationModel>> getTestNameAutoSearchList(@RequestParam String id) {
		logger.info("Method : getTestNameAutoSearchList starts");

		logger.info("Method :getTestNameAutoSearchList endss");
		return medicationDao.getTestNameAutoSearchListDao(id);
	}

	/*
	 * Drop down medicine type list
	 */

	@RequestMapping(value = "getMedTypeList", method = { RequestMethod.GET })
	public List<DropDownModel> getMedTypeLists() {
		logger.info("Method : getMedTypeLists starts");

		logger.info("Method : getMedTypeLists ends");
		return medicationDao.getMedicineTypeDao();
	}

	/*
	 * Add prescription details
	 */

	@PostMapping(value = "save-prescription-details")
	public JsonResponse<Object> savePrescriptionDetails(
			@RequestBody RestPatientMedicationModel restPatientDetailsNewModel) {
		logger.info("Method : savePrescriptionDetails starts");

		logger.info("Method : savePrescriptionDetails ends");
		return medicationDao.savePrescriptionDetailsDao(restPatientDetailsNewModel);
	}
	/*
	 * Add Test details
	 */

	@PostMapping(value = "save-test-details")
	public JsonResponse<Object> saveTestDetails(
			@RequestBody RestPatientMedicationModel restPatientDetailsNewModel) {
		logger.info("Method : saveTestDetails starts");

		logger.info("Method : saveTestDetails ends");
		return medicationDao.saveTestDetailsDao(restPatientDetailsNewModel);
	}
	/*
	 * View prescribed test details(medication)
	 * 
	 */
	@GetMapping(value = "rest-view-testDetails")
	public JsonResponse<List<RestPatientMedicationModel>> viewTestDetailsDoctor(@RequestParam String uId) {
		logger.info("Method : viewTestDetailsDoctor starts");

		logger.info("Method : viewTestDetailsDoctor ends");

		return medicationDao.viewTestDetailsDoctorDao(uId);
	}
}
