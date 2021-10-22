package nirmalya.aatithya.restmodule.patient.dao;

import javax.persistence.EntityManager;
import java.util.List;
import java.math.BigInteger;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.model.LifeStyleHistoryModel;
import nirmalya.aatithya.restmodule.common.ServerDao;

@Repository
public class LifeStyleHistoryDao {
	Logger logger = LoggerFactory.getLogger(LifeStyleHistoryDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;


	/*
	 * Return Smoking List
	 */

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getSmokingList() {
		logger.info("Method : getSmokingList Dao starts");

		List<DropDownModel> getSmokingList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_smoking_list").getResultList();

			for (Object[] m : x) {

				Object smoke = null;
				if (m[0] != null) {
					smoke = m[0].toString();
				}
				DropDownModel dropDownModel = new DropDownModel(smoke, m[1]);
				getSmokingList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getSmokingList Dao ends");
		System.out.println("Smoking List" + getSmokingList);
		return getSmokingList;
	}


	/*
	 * Return Alcohol List
	 */

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getAlcoholList() {
		logger.info("Method : getAlcoholList Dao starts");

		List<DropDownModel> getAlcoholList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_patient_alcohol_list").getResultList();

			for (Object[] m : x) {
				Object alcohol = null;
				if (m[0] != null) {
					alcohol = m[0].toString();
				}
				DropDownModel dropDownModel = new DropDownModel(alcohol, m[1]);
				getAlcoholList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getAlcoholList Dao ends");
		System.out.println("Alcohol List" + getAlcoholList);
		return getAlcoholList;
	}


	/*
	 * Add Life Style
	 */
	public JsonResponse<Object> patientLifeStyleHistoryAdd(LifeStyleHistoryModel lifeStyleHistoryModel) {
		logger.info("Method : saveVendorOrder starts");
		System.out.println("######" + lifeStyleHistoryModel);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (lifeStyleHistoryModel.getLifeStyleId() == null) {
				em.createNamedStoredProcedureQuery("patient_life_style_history_add")
						.setParameter("p_patientId", lifeStyleHistoryModel.getPatientId())
						.setParameter("p_smokingId", lifeStyleHistoryModel.getSmokingId())
						.setParameter("p_alcoholId", lifeStyleHistoryModel.getAlcoholId())
						.setParameter("p_diet", lifeStyleHistoryModel.getDiet())
						.setParameter("p_exercise", lifeStyleHistoryModel.getExercise())
						.setParameter("p_occupation", lifeStyleHistoryModel.getOccupation())
						.setParameter("p_pets", lifeStyleHistoryModel.getPets())
						.execute();
			}

		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				System.out.println(err);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("ADD DAO" + resp);
		logger.info("Method : saveVendorOrder ends");
		return resp;
	}


	/*
	 * For Edit Patient Life Style
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<LifeStyleHistoryModel> editpatientLifeStyle(String id) {
		logger.info("Method : getCustomerDetails starts");

		LifeStyleHistoryModel req = new LifeStyleHistoryModel();
		JsonResponse<LifeStyleHistoryModel> resp = new JsonResponse<LifeStyleHistoryModel>();
		
//	int id1 = Integer.parseInt(id);
		//BigInteger id1 = (BigInteger) id;					
	long l1=Long.parseLong(id); 
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_edit_patient_life_style")
					.setParameter("userId", l1).getResultList();
			for (Object[] m : x) {								
				LifeStyleHistoryModel reqEdit = new LifeStyleHistoryModel(m[0],m[1],m[2],null,m[3],null,m[4],m[5],
						 m[6],m[7]);
				req=reqEdit;				
				System.out.println("req@@@@@"+req);
			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DaOOOOO"+resp);
		logger.info("Method : getCustomerDetails ends");
		
		return resp;
	}
}
