package nirmalya.aatithya.restmodule.lab.dao;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.model.HealthCheckUpRestModel;

@Repository
public class HealthCheckUpRestDao {

	Logger logger = LoggerFactory.getLogger(HealthCheckUpRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	// AutoSearch
	@SuppressWarnings("unchecked")
	public JsonResponse<List<HealthCheckUpRestModel>> getUhidNoAutoSearchList(String id) {
		logger.info("Method : getUhidNoAutoSearchList starts");
		List<HealthCheckUpRestModel> orgNameList = new ArrayList<HealthCheckUpRestModel>();
		JsonResponse<List<HealthCheckUpRestModel>> resp = new JsonResponse<List<HealthCheckUpRestModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("lab_uhidno_autosearch")
					.setParameter("searchValue", id).getResultList();
			for (Object[] m : x) {

				HealthCheckUpRestModel dropDownModel = new HealthCheckUpRestModel(null, null, m[0].toString(), m[1],
						m[2], m[3], null, null, null, null, null);
				orgNameList.add(dropDownModel);
			}
			resp.setBody(orgNameList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getUhidNoAutoSearchList ends");
		return resp;
	}

	/*
	 * Add Life Style
	 */
	public JsonResponse<Object> addHealthCheckUp(HealthCheckUpRestModel lifeStyleHistoryModel) {
		logger.info("Method : addHealthCheckUp starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (lifeStyleHistoryModel.getuHIdNo() == null) {
				em.createNamedStoredProcedureQuery("lab_health_checkup_add")
						.setParameter("p_patientIds", lifeStyleHistoryModel.getPatientId())
						.setParameter("p_getAppointmentDate", lifeStyleHistoryModel.getAppointmentDate())
						.setParameter("p_getAppointmentTime", lifeStyleHistoryModel.getAppointmentTime()).execute();
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
		logger.info("Method : addHealthCheckUp ends");
		return resp;
	}

	/*
	 * ForHealth Checkup View
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<HealthCheckUpRestModel>> getAllLabHealthCheckUpView() {
		logger.info("Method : getAllLabHealthCheckUpView Dao starts");

		List<HealthCheckUpRestModel> getAllemployee = new ArrayList<HealthCheckUpRestModel>();
		JsonResponse<List<HealthCheckUpRestModel>> resp = new JsonResponse<List<HealthCheckUpRestModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("view_health_checkup").getResultList();
			for (Object[] m : x) {
				Integer age = null;
				if (m[3] != null) {
					BigDecimal bd = (BigDecimal) m[3];
					age = bd.intValue();
				}

				HealthCheckUpRestModel viewdemo = new HealthCheckUpRestModel(m[0].toString(), null, null, null, m[1],
						null, null, null, m[2], age, m[4]);

				getAllemployee.add(viewdemo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(getAllemployee);
		logger.info("Method : getAllLabHealthCheckUpView Dao ends");
		return resp;
	}

}
