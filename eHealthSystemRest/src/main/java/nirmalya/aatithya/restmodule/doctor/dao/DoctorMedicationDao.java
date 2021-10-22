package nirmalya.aatithya.restmodule.doctor.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.model.RestPatientMedicationModel;

@Repository
public class DoctorMedicationDao {
	Logger logger = LoggerFactory.getLogger(DoctorMedicationDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	/*
	 * Drop down for medicine Type
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getMedicineTypeDao() {
		logger.info("Method : getMedicineTypeDao starts");

		List<DropDownModel> dept = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_medicineType_list").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				dept.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getMedicineTypeDao ends");
		return dept;
	}

	/*
	 * AutoSearch MedicineList (Medication)
	 * 
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestPatientMedicationModel>> getMedNameAutoSearchListDao(String id) {
		logger.info("Method : getMedNameAutoSearchListDao starts");
		List<RestPatientMedicationModel> medNameList = new ArrayList<RestPatientMedicationModel>();
		JsonResponse<List<RestPatientMedicationModel>> resp = new JsonResponse<List<RestPatientMedicationModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_autosearch_medicine_list")
					.setParameter("searchValue", id).getResultList();
			for (Object[] m : x) {
				RestPatientMedicationModel dropDownModel = new RestPatientMedicationModel(m[0], m[1]);
				medNameList.add(dropDownModel);
			}
			resp.setBody(medNameList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getMedNameAutoSearchListDao ends");
		return resp;
	}
	


	/*
	 * add prescribed medicine details(Medication)
	 */	
	@SuppressWarnings("unchecked")
	public JsonResponse<Object> savePrescriptionDetailsDao(RestPatientMedicationModel addPrescriptionDetails) {
		logger.info("Method : savePrescriptionDetailsDao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (addPrescriptionDetails.getUserId() != null) {

				List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_patient_prescription_details")
						.setParameter("p_pid", addPrescriptionDetails.getUserId())
						.setParameter("p_mapid", addPrescriptionDetails.getMedId())
						.setParameter("p_medname", addPrescriptionDetails.getMedicineName())
						.setParameter("p_medtype", addPrescriptionDetails.getMedType())
						.setParameter("p_fromdate", addPrescriptionDetails.getFromDate())
						.setParameter("p_todate", addPrescriptionDetails.getToDate())
						.setParameter("p_morning", addPrescriptionDetails.getMorning())
						.setParameter("p_afternoon", addPrescriptionDetails.getAfterNoon())
						.setParameter("p_evening", addPrescriptionDetails.getEvening())
						.setParameter("p_remarks", addPrescriptionDetails.getRemarks())
						.setParameter("p_drId", addPrescriptionDetails.getDoctorId())
						.setParameter("p_appno", addPrescriptionDetails.getApptNo())
						.getResultList();

			}

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				System.out.println("err" + err);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		logger.info("Method : savePrescriptionDetailsDao ends");
		return resp;
	}

	/*
	 * AutoSearch Test Name with group(Medication)
	 * 
	 */
	@SuppressWarnings("unchecked")
		public JsonResponse<List<RestPatientMedicationModel>> getTestNameAutoSearchListDao(String id) {
			logger.info("Method : getTestNameAutoSearchListDao starts");
			
			
			List<RestPatientMedicationModel> testNameList = new ArrayList<RestPatientMedicationModel>();
			JsonResponse<List<RestPatientMedicationModel>> resp = new JsonResponse<List<RestPatientMedicationModel>>();
			
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_autosearch_test_list")
						.setParameter("searchValue", id).getResultList();
				for (Object[] m : x) {
					RestPatientMedicationModel testList = new RestPatientMedicationModel(m[0], m[1],m[2],null);
					testNameList.add(testList);
				}
				resp.setBody(testNameList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("Method : getTestNameAutoSearchListDao ends");
			return resp;
		}
	
	/*
	 * add test details(Medication)
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<Object> saveTestDetailsDao(RestPatientMedicationModel addPrescriptionDetails) {
		logger.info("Method : saveTestDetailsDao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (addPrescriptionDetails.getUserId() != null) {

				List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_patient_test_details")
						.setParameter("p_pid", addPrescriptionDetails.getUserId())
						.setParameter("p_testname", addPrescriptionDetails.getTestName())
						.setParameter("p_testgroup", addPrescriptionDetails.getTestGroup())
						.setParameter("p_remarks", addPrescriptionDetails.getTestRemarks())
						.setParameter("p_drid", addPrescriptionDetails.getDoctorId())
						.setParameter("p_appno", addPrescriptionDetails.getApptNo())
						.getResultList();

			}

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				System.out.println("err" + err);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		logger.info("Method : saveTestDetailsDao ends");
		return resp;
	}
	

	/*
	 * View Test Details(Medication)
	 */

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestPatientMedicationModel>> viewTestDetailsDoctorDao(String uId) {

		logger.info("Method in Dao: viewTestDetailsDao starts");

		List<RestPatientMedicationModel> testList = new ArrayList<RestPatientMedicationModel>();
		try {
			BigInteger userid = null;
			if (uId != null) {
				userid = new BigInteger(uId);
			}
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_medication_test_view")
					.setParameter("userid", userid).getResultList();

			for (Object[] m : x) {

				Object testDt = null;

				if (m[3] != null) {

					testDt = DateFormatter.returnStringDate(m[3]);
				}
				RestPatientMedicationModel testModel = new RestPatientMedicationModel(m[0], m[1], m[2], testDt, m[4]);
				testList.add(testModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		JsonResponse<List<RestPatientMedicationModel>> resp = new JsonResponse<List<RestPatientMedicationModel>>();
		resp.setBody(testList);
		logger.info("Method in Dao: viewTestDetailsDao ends");
		return resp;
	}
}
