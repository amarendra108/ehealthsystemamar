package nirmalya.aatithya.restmodule.patient.dao;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.model.RestPatientFamilyDetailsModel;

@SuppressWarnings("unused")
@Repository
public class RestPatientFamilyDetailsDao {
	Logger logger = LoggerFactory.getLogger(RestPatientFamilyDetailsDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	/*
	 * For Patient Login Details
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestPatientFamilyDetailsModel>> viewPatientFamilyDataDetails(String id) {
		logger.info("Method : viewPatientFamilyDataDetails Dao starts");

		List<RestPatientFamilyDetailsModel> getPatientFamilyDetails = new ArrayList<RestPatientFamilyDetailsModel>();
		JsonResponse<List<RestPatientFamilyDetailsModel>> resp = new JsonResponse<List<RestPatientFamilyDetailsModel>>();
	
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_family_details")
					.setParameter("userid", id).getResultList();
			for (Object[] m : x) {
				
				
				RestPatientFamilyDetailsModel viewdemo = new RestPatientFamilyDetailsModel(m[0],m[1], m[2], m[3], m[4]);
				getPatientFamilyDetails.add(viewdemo);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("viewdemo@@@@@@@@" + getPatientFamilyDetails);
		resp.setBody(getPatientFamilyDetails);
		logger.info("Method : viewPatientFamilyDataDetails  Dao ends");
		return resp;
	}

}
