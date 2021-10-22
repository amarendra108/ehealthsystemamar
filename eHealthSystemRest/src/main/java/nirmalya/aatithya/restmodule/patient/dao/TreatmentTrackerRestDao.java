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
import nirmalya.aatithya.restmodule.patient.model.TreatmentTrackerModel;
@Repository
public class TreatmentTrackerRestDao {
	Logger logger = LoggerFactory.getLogger(TreatmentTrackerRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	/*
	 * For Patient Login Details
	 */
	/*
	 * For Patient Login Details
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<TreatmentTrackerModel>> getPatientTreatmentTracker(String id) {
		logger.info("Method : getPatientTreatmentTracker Dao starts");

		List<TreatmentTrackerModel> getPatientTreatmentTracker = new ArrayList<TreatmentTrackerModel>();
		JsonResponse<List<TreatmentTrackerModel>> resp = new JsonResponse<List<TreatmentTrackerModel>>();

		try {	
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_treatment_tracker").setParameter("pId", id).getResultList();
			for (Object[] m : x) {	
//				Object fromDate = null;
//				if (m[3] != null) {
//					fromDate = m[3].toString();
//				}	
//				Object toDate = null;
//				if (m[4] != null) {
//					toDate = m[4].toString();
//				}
				
				//System.out.println("getPatientLoginDetails" + getPatientTreatmentTracker);
				TreatmentTrackerModel viewdemo = new TreatmentTrackerModel(m[0],m[1],m[2],m[3],m[4],m[5],m[6]
						,m[7],m[8]);								
				getPatientTreatmentTracker.add(viewdemo);
				
				
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(getPatientTreatmentTracker);
		logger.info("Method : getPatientTreatmentTracker  Dao ends");
		return resp;
	}
}
