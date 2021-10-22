package nirmalya.aatithya.restmodule.doctor.dao;

import java.math.BigInteger;
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
import nirmalya.aatithya.restmodule.doctor.model.RestDoctorDashboardCountModel;
import nirmalya.aatithya.restmodule.doctor.model.RestDoctorDashboardPatientModel;
import nirmalya.aatithya.restmodule.doctor.model.RestPatientDetailDoctorDashBoardModel;

@Repository
public class DoctorDashBoardDao {
	Logger logger = LoggerFactory.getLogger(DoctorDashBoardDao.class);
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
		public List<RestDoctorDashboardCountModel> getdashboardBooking(String id) {
			
			logger.info("Method : getBooking starts");
			List<RestDoctorDashboardCountModel> employmentList = new ArrayList<RestDoctorDashboardCountModel>();
			
		   	try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_dashboardbooking_count")
						.setParameter("doctorid", id).getResultList();
				for (Object[] m : x) {
					Object appointment =null;
					BigInteger t1 = (BigInteger) m[0];
					appointment = t1.toString();
					
					Object pending =null;
					BigInteger t2 = (BigInteger) m[1];
					pending = t2.toString();
					
					Object booked =null;
					BigInteger t3 = (BigInteger) m[2];
					booked = t3.toString();
					
					Object rejected =null;
					BigInteger t4 = (BigInteger) m[3];
					rejected = t4.toString();
					
					RestDoctorDashboardCountModel dropDownModel = new RestDoctorDashboardCountModel(appointment, pending, booked, rejected);
					employmentList.add(dropDownModel);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		   	logger.info("Method : getBooking ends");

			return employmentList;
		}
		/**
		 * for action Type
		 *
		 * @return
		 */
		
		@SuppressWarnings("unchecked")
		public List<DropDownModel> getactionType() {
			logger.info("Method : getactionType starts");

			List<DropDownModel> actionList = new ArrayList<DropDownModel>();
     
			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_action_list").getResultList();
				for (Object[] m : x) {
					
				
					DropDownModel dropDownModel = new DropDownModel(m[0],m[1]);
					actionList.add(dropDownModel);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info("Method : getactionType ends");

			return actionList;
		}
		/**
		 * for get Dashboard Table Date 
		 *
		 * @return
		 */
		@SuppressWarnings("unchecked")
		
			public  JsonResponse<List<RestDoctorDashboardPatientModel>> getTomorrowDate(String userid,String date) {
			System.out.println("datedatedate"+date);
				logger.info("Method : getTomorrowDate starts");
				
				List<RestDoctorDashboardPatientModel> dateList = new ArrayList<RestDoctorDashboardPatientModel>();
				JsonResponse<List<RestDoctorDashboardPatientModel>> resp = new JsonResponse<List<RestDoctorDashboardPatientModel>>();
				try {
					List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_dashboard_table")
							.setParameter("userid",userid).setParameter("date",date).getResultList();
					for (Object[] m : x) {
						Object TIME = null;
						if (m[1] != null) {
							TIME = m[1].toString();
						}	
						
						RestDoctorDashboardPatientModel dropDownModel = new RestDoctorDashboardPatientModel(m[0],TIME,m[2],m[3],m[4].toString(),m[5]);
						dateList.add(dropDownModel);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				resp.setBody(dateList);
				logger.info("Method : getTomorrowDate ends");
				return resp;
			}
		/**
		 * for Piechart list
		 *
		 * @return
		 */
		
		@SuppressWarnings("unchecked")
		public JsonResponse<List<DropDownModel>> getdashboardPiechart(String id) {
			
				logger.info("Method : getdashboardPiechart starts");

				List<DropDownModel> piechartList = new ArrayList<DropDownModel>();
				JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
	      
				try {
					List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_dashboardpiechart_count")
							.setParameter("doctorid", id).getResultList();
					for (Object[] m : x) {
						Object booked =null;
						BigInteger t1 = (BigInteger) m[0];
						booked = t1.toString();
						
						Object requested =null;
						BigInteger t2 = (BigInteger) m[1];
						requested = t2.toString();
						
						Object treated =null;
						BigInteger t3 = (BigInteger) m[2];
						treated = t3.toString();
						
						DropDownModel dropDownModel = new DropDownModel(booked, requested,treated);
						piechartList.add(dropDownModel);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				resp.setBody(piechartList);
				logger.info("Method : getdashboardPiechart ends");

				return resp;
			}
		
		/*
		 * doctor dashboard update patient appointment
		 */
				public JsonResponse<Object> getpatientDetailsById(DropDownModel  dropdownmodel) {
					logger.info("Method : getpatientDetailsById Dao starts");
					JsonResponse<Object> resp = new JsonResponse<Object>();
					try {
						
						 em.createNamedStoredProcedureQuery("doctor_update_patient_details_by_id")
								.setParameter("p_status", dropdownmodel.getKey())
								.setParameter("note", dropdownmodel.getName())
								.setParameter("opdid", dropdownmodel.getCode()).execute();
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
					logger.info("Method : getpatientDetailsById Dao ends");
					return resp;
				}

				@SuppressWarnings("unchecked")
				public JsonResponse<List<RestPatientDetailDoctorDashBoardModel>> getPatient(String id) {
					logger.info("Method : getPatient Dao starts");

					List<RestPatientDetailDoctorDashBoardModel> getCountryList = new ArrayList<RestPatientDetailDoctorDashBoardModel>();
					JsonResponse<List<RestPatientDetailDoctorDashBoardModel>> resp = new JsonResponse<List<RestPatientDetailDoctorDashBoardModel>>();
					
					try {

						List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_patient_details_by_id")
								.setParameter("patientid", id).getResultList();

						for (Object[] m : x) {

							RestPatientDetailDoctorDashBoardModel dropDownModel = new RestPatientDetailDoctorDashBoardModel(m[0].toString(), m[1], m[2],m[3], m[4]);
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
