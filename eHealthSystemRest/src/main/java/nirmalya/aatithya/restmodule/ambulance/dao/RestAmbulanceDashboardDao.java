package nirmalya.aatithya.restmodule.ambulance.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.ambulance.model.AmbulanceDashboardRestModel;
import nirmalya.aatithya.restmodule.chemist.model.RestChemistPrescriptionModel;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.doctor.model.RestPatientDetailDoctorDashBoardModel;
@Repository
public class RestAmbulanceDashboardDao {
	
	Logger logger = LoggerFactory.getLogger(RestAmbulanceDashboardDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	


	/**
	 * for getdashboardBooking count
	 *
	 * @return
	 */	
	@SuppressWarnings("unchecked")
	public List<AmbulanceDashboardRestModel> appointmentCount(String id) {
		
		logger.info("Method : getBooking starts");
		List<AmbulanceDashboardRestModel> employmentList = new ArrayList<AmbulanceDashboardRestModel>();
		
	   	try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("ambulance_dashboard_count")
					.setParameter("userid", id).getResultList();
			for (Object[] m : x) {
			
				AmbulanceDashboardRestModel dropDownModel = new AmbulanceDashboardRestModel(m[0],m[1],m[2],m[3],m[4],m[5],m[6],m[7]);
				employmentList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	   	System.out.println("employmentList" + employmentList);
	   	logger.info("Method : getBooking ends");

		return employmentList;
	}
	
	
	
	/**************************** view patient prescription  **************************/
	@SuppressWarnings("unchecked")
	public JsonResponse<List<AmbulanceDashboardRestModel>> getPatientPrescription(String userid,String date) {
		logger.info("Method : getPatientTreatmentTracker Dao starts");

		List<AmbulanceDashboardRestModel> getPatientList = new ArrayList<AmbulanceDashboardRestModel>();
		JsonResponse<List<AmbulanceDashboardRestModel>> resp = new JsonResponse<List<AmbulanceDashboardRestModel>>();
		System.out.println(userid+", "+date);
		try {	
			List<Object[]> x = em.createNamedStoredProcedureQuery("ambulance_dashboard_table")
																.setParameter("userid",userid)
																.setParameter("date",date).getResultList();		
			
			for (Object[] m : x) {	
				
				
				AmbulanceDashboardRestModel viewdemo = new AmbulanceDashboardRestModel(m[0],m[1],m[2],m[3],m[4], m[5], m[6],m[7],null);								
				getPatientList.add(viewdemo);
			
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("getPatientLoginDetails" + getPatientList);
		resp.setBody(getPatientList);
		logger.info("Method : getPatientTreatmentTracker  Dao ends");
		return resp;
	}


	@SuppressWarnings("unchecked")
	public JsonResponse<Object> saveAmbulanceStatusDao(String orderid,String status) {
		// TODO Auto-generated method stub
				logger.info("Method : saveAmbulanceStatus  Dao starts");
				JsonResponse<Object> resp = new JsonResponse<Object>();
				try {	
					List<Object[]> x= em.createNamedStoredProcedureQuery("save_ambulance_status")
							.setParameter("p_order", orderid)
							.setParameter("p_status", status)
							.getResultList();
					
				} catch (Exception e) {
					try {
						String[] err = serverDao.errorProcedureCall(e);
						resp.setCode(err[0]);
						resp.setMessage(err[1]);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
				logger.info("Method : saveAmbulanceStatus  Dao ends");
				return resp;
	}
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<AmbulanceDashboardRestModel>> getPatient(String id) {
		logger.info("Method : getPatient Dao starts");

		List<AmbulanceDashboardRestModel> getCountryList = new ArrayList<AmbulanceDashboardRestModel>();
		JsonResponse<List<AmbulanceDashboardRestModel>> resp = new JsonResponse<List<AmbulanceDashboardRestModel>>();
		
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("ambulance_patient_details_by_id")
					.setParameter("patientid", id).getResultList();

			for (Object[] m : x) {

				AmbulanceDashboardRestModel dropDownModel = new AmbulanceDashboardRestModel(m[0].toString(), m[1]);
				getCountryList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(getCountryList);
		logger.info("Method : getPatient Dao ends");
		return resp;
	}
}
