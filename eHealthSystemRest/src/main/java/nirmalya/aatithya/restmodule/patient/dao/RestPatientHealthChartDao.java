package nirmalya.aatithya.restmodule.patient.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.model.RestLabTestReportModel;

@Repository
public class RestPatientHealthChartDao {
	Logger logger = LoggerFactory.getLogger(RestPatientHealthChartDao.class);

	@Autowired
	ServerDao serverDao;

	@Autowired
	EntityManager em;

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestLabTestReportModel>> getPatientTestDetailsDao(String id) {
		logger.info("Method : getPatientTestDetailsDao starts");
		List<RestLabTestReportModel> patientdetails = new ArrayList<RestLabTestReportModel>();

		JsonResponse<List<RestLabTestReportModel>> resp = new JsonResponse<List<RestLabTestReportModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_health_chart")
					.setParameter("patientid", id).getResultList();
			for (Object[] m : x) {
				RestLabTestReportModel reqEdit = new RestLabTestReportModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6]);
				patientdetails.add(reqEdit);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(patientdetails);
		logger.info("Method : getPatientTestDetailsDao ends");
		return resp;
	}

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestLabTestReportModel>> getPatientHealthChartDetailsDao(String id) {
		logger.info("Method : getPatientHealthChartDetailsDao starts");

		List<RestLabTestReportModel> patientdetails = new ArrayList<RestLabTestReportModel>();
		JsonResponse<List<RestLabTestReportModel>> resp = new JsonResponse<List<RestLabTestReportModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_health_chart_details")
					.setParameter("patientid", id).getResultList();

			for (Object[] m : x) {

				RestLabTestReportModel reqEdit = new RestLabTestReportModel(m[0], m[1].toString());
				patientdetails.add(reqEdit);
			}
			resp.setBody(patientdetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getPatientHealthChartDetailsDao ends");
		return resp;
	}

	// HAEMATOLOGICAL EXAMINATION

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> healthChartForHematologyDao(String userId) {

		logger.info("Method : healthChartForHematologyDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_healthChart_forHematology")
					.setParameter("patientid", userId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1], m[2].toString());
				stateList.add(dropDownModel);
			}
			resp.setBody(stateList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : healthChartForHematologyDao ends");
		return resp;
	}
	//Kidney Function Test

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> healthChartKidneyTestDao(String userId) {

		logger.info("Method : healthChartForHematologyDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_healthChart_forKidneyTest")
					.setParameter("patientid", userId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1], m[2].toString());
				stateList.add(dropDownModel);
			}
			resp.setBody(stateList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : healthChartKidneyTestDao ends");
		return resp;
	}
	// Lipid Profile

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> healthChartForLipidProfileDao(String userId) {

		logger.info("Method : healthChartForLipidProfileDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_healthChart_forLipidProfile")
					.setParameter("patientid", userId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1], m[2].toString());
				stateList.add(dropDownModel);
			}
			resp.setBody(stateList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : healthChartForLipidProfileDao ends");
		return resp;
	}
	// Blood Sugar

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> healthChartForBloodSugarDao(String userId) {

		logger.info("Method : healthChartForBloodSugarDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_healthChart_forBloodSugar")
					.setParameter("patientid", userId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1], m[2].toString());
				stateList.add(dropDownModel);
			}
			resp.setBody(stateList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : healthChartForBloodSugarDao ends");
		return resp;
	}
	// Electrolyte

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> healthChartForElectrolyteDao(String userId) {

		logger.info("Method : healthChartForElectrolyteDao starts");
		List<DropDownModel> elcList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_healthChart_forElectrolyte")
					.setParameter("patientid", userId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1], m[2].toString());
				elcList.add(dropDownModel);
			}
			resp.setBody(elcList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : healthChartForElectrolyteDao ends");
		return resp;
	}
	//Liver Function Test

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> healthChartForLiverTestDao(String userId) {

		logger.info("Method : healthChartForLiverTestDao starts");
		List<DropDownModel> resultList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_healthChart_forLiverTest")
					.setParameter("patientid", userId).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1], m[2].toString());
				resultList.add(dropDownModel);
			}
			resp.setBody(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : healthChartForLiverTestDao ends");
		return resp;
	}
}
