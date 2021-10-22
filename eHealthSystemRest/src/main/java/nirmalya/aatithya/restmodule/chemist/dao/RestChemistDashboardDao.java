package nirmalya.aatithya.restmodule.chemist.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.chemist.model.RestChemistDashboardModel;
import nirmalya.aatithya.restmodule.chemist.model.RestChemistPrescriptionDetailsModel;
import nirmalya.aatithya.restmodule.chemist.model.RestChemistPrescriptionModel;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.doctor.dao.DoctorDashBoardDao;
import nirmalya.aatithya.restmodule.doctor.model.RestDoctorDashboardPatientModel;

@Repository

public class RestChemistDashboardDao {
	
	Logger logger = LoggerFactory.getLogger(DoctorDashBoardDao.class);
	@Autowired
	EntityManager em;
	@Autowired
	ServerDao serverDao;
	
	public List<RestChemistDashboardModel> getAppointment(String userId) {
		logger.info("Method : All Chemist Dashboard Dao starts");
//		List<RestChemistDashboardModel> getAllemployee = new ArrayList<RestChemistDashboardModel>();
		List<RestChemistDashboardModel> resp = new ArrayList<RestChemistDashboardModel>();
		
		try {

		List<Object[]> x = em.createNamedStoredProcedureQuery("chemist_dashboard").setParameter("userId", userId).getResultList();
			
                for (Object[] m : x) {		
				
				RestChemistDashboardModel viewdemo = new RestChemistDashboardModel(m[0].toString(),m[1].toString(),m[2].toString(),m[3].toString());
				resp.add(viewdemo);
			}
                
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		logger.info("Method : All Chemist Dashboard Dao ends");

		return resp;
	}

	/**************************** view patient prescription  **************************/
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestChemistPrescriptionModel>> getPatientPrescription(String userid,String date) {
		logger.info("Method : getPatientTreatmentTracker Dao starts");

		List<RestChemistPrescriptionModel> getPatientTreatmentTracker = new ArrayList<RestChemistPrescriptionModel>();
		JsonResponse<List<RestChemistPrescriptionModel>> resp = new JsonResponse<List<RestChemistPrescriptionModel>>();
		System.out.println(userid+", "+date);
		try {	
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_prescription_view")
																.setParameter("userid",userid)
																.setParameter("date",date).getResultList();		
			
			for (Object[] m : x) {	
				
				
				RestChemistPrescriptionModel viewdemo = new RestChemistPrescriptionModel(m[0],m[1],m[2],m[3],m[4], m[5]);								
				getPatientTreatmentTracker.add(viewdemo);
			
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("getPatientLoginDetails" + getPatientTreatmentTracker);
		resp.setBody(getPatientTreatmentTracker);
		logger.info("Method : getPatientTreatmentTracker  Dao ends");
		return resp;
	}
	

	/**************************** view modal all medicine accroding to uhid no **************************/
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestChemistPrescriptionDetailsModel>> getPrecriptionDetails(String userid,String PatientName) {
		logger.info("Method : getPatientPrecription Dao starts");

		List<RestChemistPrescriptionDetailsModel> getPatient = new ArrayList<RestChemistPrescriptionDetailsModel>();
		JsonResponse<List<RestChemistPrescriptionDetailsModel>> resp = new JsonResponse<List<RestChemistPrescriptionDetailsModel>>();

		try {	
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_prescription_model")
					                                            .setParameter("userid",userid)
				                                          	   .setParameter("patientName",PatientName)
				                                          	     .getResultList();	
		
			for (Object[] m : x) {	
				RestChemistPrescriptionDetailsModel viewdemo = new RestChemistPrescriptionDetailsModel(m[0],m[1],m[2]
						,m[3],m[4],m[5],m[6],m[7],m[8], m[9]);								
				getPatient.add(viewdemo);
			
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(getPatient);
		logger.info("Method : getPatientPrecription  Dao ends");
		return resp;
	}
	
//	/**********************************************************************************************************/
//	@SuppressWarnings("unchecked")
//	public JsonResponse<List<RestChemistPrescriptionDetailsModel>> getPatientPrecriptionDetails(List<RestChemistPrescriptionDetailsModel> restChemistPrescriptionDetailsModel) {
//		logger.info("Method : getPatientPrecription Dao starts");
//
//		List<RestChemistPrescriptionDetailsModel> getPatient = new ArrayList<RestChemistPrescriptionDetailsModel>();
//		JsonResponse<List<RestChemistPrescriptionDetailsModel>> resp = new JsonResponse<List<RestChemistPrescriptionDetailsModel>>();
//         System.out.println("getPatientPrecriptionDetails"+getPatient);
//		try {	
//			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_prescription_details_store").getResultList();	
//			System.out.println("@@@@"+restChemistPrescriptionDetailsModel);
//			for (Object[] m : x) {	
//				
//				
//				RestChemistPrescriptionDetailsModel viewdemo = new RestChemistPrescriptionDetailsModel(m[0],m[1],m[2],m[3],m[4],null,null,null,null, null);								
//				getPatient.add(viewdemo);
//			
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		 System.out.println("getPatientPrecriptionDetails"+getPatient);
//		System.out.println("patient name" + restChemistPrescriptionDetailsModel);
//		resp.setBody(getPatient);
//		logger.info("Method : getPatientPrecription  Dao ends");
//		return resp;
//	}

	/**************************** save status after reject/confirm medicine **************************/
	@SuppressWarnings("unchecked")
	public JsonResponse<Object> saveChemistStatusDao(String orderid, String status) {
		// TODO Auto-generated method stub
		logger.info("Method : saveChemistStatusDao  Dao starts");
		System.out.println(orderid+","+status);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {	
			List<Object[]> x= em.createNamedStoredProcedureQuery("chemist_save_medicine_status")
					.setParameter("p_status", status)
					.setParameter("p_orderid", orderid)
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
		logger.info("Method : saveChemistStatusDao  Dao ends");
		return resp;
	}
	
	
	


}
