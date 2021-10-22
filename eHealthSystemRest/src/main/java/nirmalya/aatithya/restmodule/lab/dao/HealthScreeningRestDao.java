package nirmalya.aatithya.restmodule.lab.dao;

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
import nirmalya.aatithya.restmodule.lab.model.HealthScreeningRestModel;

@Repository

public class HealthScreeningRestDao {

	Logger logger = LoggerFactory.getLogger(HealthScreeningRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@SuppressWarnings("unchecked")
	public JsonResponse<List<HealthScreeningRestModel>> viewHealthScreen() {
		logger.info("Method : viewHealthScreen starts");

		List<HealthScreeningRestModel> getAllemployee = new ArrayList<HealthScreeningRestModel>();
		JsonResponse<List<HealthScreeningRestModel>> resp = new JsonResponse<List<HealthScreeningRestModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("view_health_screening").getResultList();

			for (Object[] m : x) {

				Integer age = null;
				if (m[2] != null) {
					BigDecimal bd = (BigDecimal) m[2];
					age = bd.intValue();
				}

				HealthScreeningRestModel so = new HealthScreeningRestModel(m[0].toString(), null, null, null, m[1],
						null, null, age, null, null, m[3], null, null, null, null, null, null, null, null, null, m[4]);

				getAllemployee.add(so);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setBody(getAllemployee);
		logger.info("Method : viewHealthScreen ends");
		return resp;
	}

	// AutoSearch
	@SuppressWarnings("unchecked")
	public JsonResponse<List<HealthScreeningRestModel>> getUhidNoAutoSearch(String id) {
		logger.info("Method : getUhidNoAutoSearch starts");
		List<HealthScreeningRestModel> orgNameList = new ArrayList<HealthScreeningRestModel>();
		JsonResponse<List<HealthScreeningRestModel>> resp = new JsonResponse<List<HealthScreeningRestModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("lab_uhidno_autosearch")
					.setParameter("searchValue", id).getResultList();
			for (Object[] m : x) {

				HealthScreeningRestModel dropDownModel = new HealthScreeningRestModel(null, null, null, null, null,
						null, null, null, m[0].toString(), m[1], m[2], null, null, null, null, null, m[3], null, null,
						null, null);
				orgNameList.add(dropDownModel);
			}
			resp.setBody(orgNameList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getUhidNoAutoSearch ends");
		return resp;
	}

	public JsonResponse<Object> addHealthScreeningLab(HealthScreeningRestModel lifeStyleHistoryModel) {
		logger.info("Method : addHealthScreening starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (lifeStyleHistoryModel.getuHIdNo() == null) {
				em.createNamedStoredProcedureQuery("lab_health_screening_add")
						.setParameter("p_patientIds", lifeStyleHistoryModel.getPatientId())
						.setParameter("p_getStartDate", lifeStyleHistoryModel.getStartDate())
						.setParameter("p_getStartTime", lifeStyleHistoryModel.getStartTime()).execute();
			} else {
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
		logger.info("Method : addHealthScreening ends");
		return resp;
	}

}
