package nirmalya.aatithya.restmodule.lab.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.model.RestLabDashboardModel;
import nirmalya.aatithya.restmodule.lab.model.RestLabDashboardPatientModel;
import nirmalya.aatithya.restmodule.lab.model.RestLabPatientPriscriptionModel;
@Repository
public class RestLabDashboardDao {
	Logger logger = LoggerFactory.getLogger(RestLabDashboardDao.class);

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
	public List<RestLabDashboardModel> labCountRegistered(String id) {

		logger.info("Method : labCountRegistered starts");

		List<RestLabDashboardModel> employmentList = new ArrayList<RestLabDashboardModel>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("lab_dashboard_count")
					.setParameter("userid", id).getResultList();
			for (Object[] m : x) {
				RestLabDashboardModel dropDownModel = new RestLabDashboardModel(m[0], m[1], m[2],
						m[3], m[4], m[5], m[6], m[7]);
				employmentList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("dropDownModel@@@@@@@"+employmentList);
		logger.info("Method : labCountRegistered ends");

		return employmentList;
	}
	//View  Test Report

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> viewTestReport(String id) {

		logger.info("Method : viewTestReport starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("lab_dashboard_test_report_done").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1],null);
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("Method : viewTestReport ends");
		return resp;
	}
	
	//Health Check Up

		@SuppressWarnings("unchecked")
		public JsonResponse<List<DropDownModel>> viewTHealthCheckup(String id) {

			logger.info("Method : viewTestReport starts");
			List<DropDownModel> stateList = new ArrayList<DropDownModel>();
			JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("lab_dashboard_health_checkup").getResultList();
				for (Object[] m : x) {
					DropDownModel dropDownModel = new DropDownModel(m[0], m[1],null);
					stateList.add(dropDownModel);
				}

				resp.setBody(stateList);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			logger.info("Method : viewTHealthCheckup ends");
			return resp;
		}
		
//		/*
//		 * For Patient Login Details
//		 */
//		@SuppressWarnings("unchecked")
//		public JsonResponse<List<RestLabDashboardModel>> getLabView() {
//			logger.info("Method : getLabView Dao starts");
//
//			List<RestLabDashboardModel> restLabDashboardModel = new ArrayList<RestLabDashboardModel>();
//			JsonResponse<List<RestLabDashboardModel>> resp = new JsonResponse<List<RestLabDashboardModel>>();
//
//			try {	
//				List<Object[]> x = em.createNamedStoredProcedureQuery("lab_dashboard_view").getResultList();
//				for (Object[] m : x) {				
//					
//					
//					
//					RestLabDashboardModel viewdemo = new RestLabDashboardModel(m[0], m[1], m[2], null,m[3]);								
//					restLabDashboardModel.add(viewdemo);
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			resp.setBody(restLabDashboardModel);
//			logger.info("Method : getLabView  Dao ends");
//			return resp;
//		}
		/*
		 * For Patient Details in labdashboard
		 */
	
		@SuppressWarnings("unchecked")
		public JsonResponse<List<RestLabDashboardPatientModel>> getLabView(String userid, String date) {
			logger.info("Method : getLabView Dao starts");
			//System.out.println("@@@@@@@@@@@@@" + date);
			//System.out.println("@@@@@@@@@@@@@" + userid);

			List<RestLabDashboardPatientModel> restLabDashboardPatientModel = new ArrayList<RestLabDashboardPatientModel>();
			JsonResponse<List<RestLabDashboardPatientModel>> resp = new JsonResponse<List<RestLabDashboardPatientModel>>();

			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("lab_dashboard_view")
						.setParameter("userid", userid).setParameter("reqdate", date).getResultList();
				for (Object[] m : x) {

					RestLabDashboardPatientModel viewdemo = new RestLabDashboardPatientModel(m[0], m[1], m[2], m[3],
							m[4], m[5], m[6]);
					restLabDashboardPatientModel.add(viewdemo);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.setBody(restLabDashboardPatientModel);
			System.out.println("@@@@@@@@@@@@@@@@@@kkkk" + resp);
			logger.info("Method : getLabView  Dao ends");
			return resp;
		}
		
		/**********************lab patient precription dao by keshav******************/
		@SuppressWarnings("unchecked")
		public JsonResponse<List<RestLabPatientPriscriptionModel>> getPrecriptionDetails(String userid,
				String patientid, String orderid) {
			logger.info("Method : getLabPatientPrecription Dao starts");

			List<RestLabPatientPriscriptionModel> getPatient = new ArrayList<RestLabPatientPriscriptionModel>();
			JsonResponse<List<RestLabPatientPriscriptionModel>> resp = new JsonResponse<List<RestLabPatientPriscriptionModel>>();

			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("lab_dashboard_patient_modal")
						.setParameter("userid", userid)
						.setParameter("patientid", patientid)
						.setParameter("orderid", orderid)
						.getResultList();
//				System.out.println("@@@@" + userid);
//				System.out.println("@@@@" + patientid);
//				System.out.println("@@@@" + orderid);
				for (Object[] m : x) {

					RestLabPatientPriscriptionModel viewdemo = new RestLabPatientPriscriptionModel(m[0], m[1], m[2],
							m[3],m[4]);
					getPatient.add(viewdemo);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.setBody(getPatient);
			logger.info("Method : getLabPatientPrecription  Dao ends");
			return resp;
		}
		/**************************** save status after reject/confirm medicine **************************/
		@SuppressWarnings("unchecked")
		public JsonResponse<Object> saveLabStatusDao(String orderid, String status) {
			// TODO Auto-generated method stub
			logger.info("Method : saveLabStatusDao  Dao starts");
			System.out.println(orderid+","+status);
			JsonResponse<Object> resp = new JsonResponse<Object>();
			try {	
				List<Object[]> x= em.createNamedStoredProcedureQuery("lab_save_lab_status")
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
			logger.info("Method : saveLabStatusDao  Dao ends");
			return resp;
		}
}
