package nirmalya.aatithya.restmodule.reception.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.model.RestPatientDetailsNewModel;
import nirmalya.aatithya.restmodule.reception.model.RestReceptionDashboardModel;
@Repository
public class RestReceptionDashboardDao {
	Logger logger = LoggerFactory.getLogger(RestReceptionDashboardDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	/**
	 * Dao Appointment
	 *
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List<RestReceptionDashboardModel> receptionCount(String id) {

		logger.info("Method : receptionCount starts");
System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiddddddddddddddddddddd"+id);
		List<RestReceptionDashboardModel> employmentList = new ArrayList<RestReceptionDashboardModel>();
		System.out.println(id);
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("reception_appointment_count")
					.setParameter("doctorid", id).getResultList();
			for (Object[] m : x) {
				RestReceptionDashboardModel dropDownModel = new RestReceptionDashboardModel(m[0], m[1], m[2],
						null, null, null, null);
				employmentList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("receptionCount" + employmentList);
		logger.info("Method : receptionCount ends");

		return employmentList;
	}
	// disease wise reception dashboard

			@SuppressWarnings("unchecked")
			public JsonResponse<List<DropDownModel>> viewPatientDisease(String id) {

				logger.info("Method : viewPatientDisease starts");
				List<DropDownModel> stateList = new ArrayList<DropDownModel>();
				JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
				try {
					List<Object[]> x = em.createNamedStoredProcedureQuery("reception_dashboard_patient_disease")
							.setParameter("receptionid", id).getResultList();
					for (Object[] m : x) {
						DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
						stateList.add(dropDownModel);
					}

					resp.setBody(stateList);

				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("viewPatientDisease========="+resp);
				logger.info("Method : viewPatientDisease ends");
				return resp;
			}
			
			//View  Appointment Status Wise

						@SuppressWarnings("unchecked")
						public JsonResponse<List<DropDownModel>> viewAppointmentStatus(String id) {

							logger.info("Method : viewAppointmentStatus starts");
							List<DropDownModel> stateList = new ArrayList<DropDownModel>();
							JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
							try {
								List<Object[]> x = em.createNamedStoredProcedureQuery("reception_dashboard_appointment_status")
										.setParameter("doctorid", id).getResultList();
								for (Object[] m : x) {
									DropDownModel dropDownModel = new DropDownModel(m[0], m[1],m[2]);
									stateList.add(dropDownModel);
								}

								resp.setBody(stateList);

							} catch (Exception e) {
								e.printStackTrace();
							}
							System.out.println("viewAppointmentStatus========="+resp);
							logger.info("Method : viewAppointmentStatus ends");
							return resp;
						}
	 
}
