package nirmalya.aatithya.restmodule.patient.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.model.PatientHistoryModel;

@Repository
public class PatientRestDao {
	Logger logger = LoggerFactory.getLogger(PatientRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	/*
	 * For Patient History
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<PatientHistoryModel>> getAllPatientHistory(String id) {
		logger.info("Method : PatientIllness Dao starts");
		List<PatientHistoryModel> getAllemployee = new ArrayList<PatientHistoryModel>();
		JsonResponse<List<PatientHistoryModel>> resp = new JsonResponse<List<PatientHistoryModel>>();
		try {						
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_history_view").setParameter("pid", id).getResultList();
			for (Object[] m : x) {
				Object DATE = null;
				if (m[1] != null) {
					DATE = m[1].toString();
				}			
				
			//	System.out.println("getAllemployee" + getAllemployee);
				PatientHistoryModel viewdemo = new PatientHistoryModel(null,m[0],DATE,m[2],null);
				
				//System.out.println("PatientIllness  : " + viewdemo);
				getAllemployee.add(viewdemo);
				//System.out.println("getAllemployee" + getAllemployee);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(getAllemployee);
		logger.info("Method : PatientIllness Dao ends");
		return resp;
	}
	
	/*
	 * For Patient Major Surgery
	 */
	/*
	 * For Patient Major Surgery
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<PatientHistoryModel>> getPatientHistoryMajorSurgery(String id) {
		logger.info("Method : getPatientHistoryMajorSurgery Dao starts");
		List<PatientHistoryModel> getAllemployee = new ArrayList<PatientHistoryModel>();
		JsonResponse<List<PatientHistoryModel>> resp = new JsonResponse<List<PatientHistoryModel>>();
		try {						
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_major_surgery").setParameter("pid", id).getResultList();
			for (Object[] m : x) {
				Object DATE = null;
				if (m[2] != null) {
					DATE = m[2].toString();
				}			
			//	System.out.println("getAllemployee" + getAllemployee);
				PatientHistoryModel viewdemo = new PatientHistoryModel(m[0], m[1], DATE, m[3],m[4],null);	
				//System.out.println("viewdemo@@@@@@@@" + viewdemo);
				getAllemployee.add(viewdemo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(getAllemployee);
		logger.info("Method : getPatientHistoryMajorSurgery  Dao ends");
		return resp;
	}
	
	/*
	 * For Patient Medical Condition
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<PatientHistoryModel>> getPatientHistoryMedicalCondition(String id) {
		logger.info("Method : getPatientHistoryMedicalCondition Dao starts");

		List<PatientHistoryModel> getAllemployee = new ArrayList<PatientHistoryModel>();
		JsonResponse<List<PatientHistoryModel>> resp = new JsonResponse<List<PatientHistoryModel>>();

		try {			
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_medical_condition").setParameter("pId", id).getResultList();

			for (Object[] m : x) {		
			//	System.out.println("getAllemployee" + getAllemployee);
				PatientHistoryModel viewdemo = new PatientHistoryModel(m[0], m[1]);								
				getAllemployee.add(viewdemo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(getAllemployee);
		logger.info("Method : getPatientHistoryMedicalCondition  Dao ends");

		return resp;

	}

}
