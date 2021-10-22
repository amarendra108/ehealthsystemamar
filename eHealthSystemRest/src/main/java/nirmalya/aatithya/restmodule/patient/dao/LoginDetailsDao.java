package nirmalya.aatithya.restmodule.patient.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.model.LoginDetailsModel;

@Repository
public class LoginDetailsDao {
	Logger logger = LoggerFactory.getLogger(LoginDetailsDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	/*
	 * For Patient Login Details
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<LoginDetailsModel>> getPatientLoginDetails(String id) {
		logger.info("Method : getPatientLoginDetails Dao starts");

		List<LoginDetailsModel> getPatientLoginDetails = new ArrayList<LoginDetailsModel>();
		JsonResponse<List<LoginDetailsModel>> resp = new JsonResponse<List<LoginDetailsModel>>();

		try {	
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_login_details").setParameter("userid", id).getResultList();
			for (Object[] m : x) {				
				BigInteger t1 = (BigInteger) m[0];								
				Object DATE = null;
				Object TIME = null;
				if (m[1] != null) {
					String date1 = m[1].toString();
					String[] data = date1.split("\\s",2);
					DATE = data[0];
					TIME = data[1];
				}
				//System.out.println("getPatientLoginDetails" + getPatientLoginDetails);
				LoginDetailsModel viewdemo = new LoginDetailsModel(t1, DATE, m[2], m[3],TIME,m[4]);								
				getPatientLoginDetails.add(viewdemo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(getPatientLoginDetails);
		logger.info("Method : getPatientLoginDetails  Dao ends");
		return resp;
	}
}
