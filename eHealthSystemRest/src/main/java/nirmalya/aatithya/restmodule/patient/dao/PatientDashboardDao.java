package nirmalya.aatithya.restmodule.patient.dao;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.model.PatientDashboardRestModel;
import nirmalya.aatithya.restmodule.patient.model.RestPatientDetailsNewModel;
import nirmalya.aatithya.restmodule.patient.model.RestPatientRequestAmbulanceModel;
@Repository
public class PatientDashboardDao {
	Logger logger = LoggerFactory.getLogger(PatientDashboardDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	
	/*
	 * For DashBoard Profile
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<PatientDashboardRestModel> editDashBoardProfile(String id) {
		logger.info("Method : editDashBoardProfile starts");

		PatientDashboardRestModel req = new PatientDashboardRestModel();
		JsonResponse<PatientDashboardRestModel> resp = new JsonResponse<PatientDashboardRestModel>();
		

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_dashboard_profile_bloodgroup")
					.setParameter("dashboardID", id)
					.getResultList();
			for (Object[] m : x) {
				
				PatientDashboardRestModel reqEdit = new PatientDashboardRestModel(null,m[0],m[1],m[2],m[3],m[4]
						,m[5],m[6],m[7],null);
				req=reqEdit;	
				System.out.println("RESRT#$%6789"+req);
				
			}
			resp.setBody(req);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : editDashBoardProfile ends");
		
		return resp;
	}
	  //Dashboard Insurance
	  
		@SuppressWarnings("unchecked")
		public JsonResponse<PatientDashboardRestModel> dashboardInsurance(String id) {
			logger.info("Method : dashboardInsurance starts");

			PatientDashboardRestModel req = new PatientDashboardRestModel();
			JsonResponse<PatientDashboardRestModel> resp = new JsonResponse<PatientDashboardRestModel>();

			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("dashboard_Insurancet")
						.setParameter("dashboardID", id).getResultList();
				for (Object[] m : x) {

					PatientDashboardRestModel reqEdit = new PatientDashboardRestModel(m[0], m[1],m[2]);
					req = reqEdit;

				}
				resp.setBody(req);
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("Method : dashboardInsurance ends");

			return resp;
		}
		 //Dashboard Allergy
		  
		@SuppressWarnings("unchecked")
		public JsonResponse<PatientDashboardRestModel> dashboardAllergy(String id) {
			logger.info("Method : dashboardAllergy starts");

			PatientDashboardRestModel req = new PatientDashboardRestModel();
			JsonResponse<PatientDashboardRestModel> resp = new JsonResponse<PatientDashboardRestModel>();

			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("patient_dash_board_allergy")
						.setParameter("dashboardID", id).getResultList();
				for (Object[] m : x) {

					PatientDashboardRestModel reqEdit = new PatientDashboardRestModel(m[0], m[1],m[2],null);
					req = reqEdit;

				}
				resp.setBody(req);
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("Method : dashboardAllergy ends");

			return resp;
		}
		
		
		@SuppressWarnings("unchecked")
		public JsonResponse<PatientDashboardRestModel> editDashBoardEmergencyContact(String id) {
			logger.info("Method : editDashBoardEmergencyContact starts");

			PatientDashboardRestModel req = new PatientDashboardRestModel();
			JsonResponse<PatientDashboardRestModel> resp = new JsonResponse<PatientDashboardRestModel>();

			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("patient_editDashBoardEmergencyContact")
						.setParameter("dashboardID", id).getResultList();
				for (Object[] m : x) {

					PatientDashboardRestModel reqEdit = new PatientDashboardRestModel(m[0],m[1],m[2],m[3],null,null);
					req = reqEdit;


				}
				resp.setBody(req);
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("Method : editDashBoardProfile ends");

			return resp;
		}
		
		
		//Dashboard Medical Condition
		@SuppressWarnings("unchecked")
		public JsonResponse<PatientDashboardRestModel> dashboardTestReportn(String id) {
			logger.info("Method : dashboardAllergy starts");

			PatientDashboardRestModel req = new PatientDashboardRestModel();
			JsonResponse<PatientDashboardRestModel> resp = new JsonResponse<PatientDashboardRestModel>();
			

			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("dashboard_test_report").setParameter("dashboardID", id)
						.getResultList();
				for (Object[] m : x) {	
					
					PatientDashboardRestModel reqEdit = new PatientDashboardRestModel(m[0].toString(),m[1]);
					req=reqEdit;				
					
				}
				resp.setBody(req);
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("Method : dashboardTestReport ends");
			
			return resp;
		}	
		/**
		 * for Medication list
		 *
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public List<PatientDashboardRestModel> getBooking1(String id) {

			logger.info("Method : getBooking starts");

			List<PatientDashboardRestModel> employmentList = new ArrayList<PatientDashboardRestModel>();

			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("dashboard_medication")
						.setParameter("dashboardID", id).getResultList();
				for (Object[] m : x) {
					
					PatientDashboardRestModel dropDownModel = new PatientDashboardRestModel(m[0],m[1],m[2],m[3],m[4]);
					employmentList.add(dropDownModel);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("Method : getBooking ends");

			return employmentList;
		} 
		
		//Dashboard Appointment
		
		@SuppressWarnings("unchecked")
		public JsonResponse<PatientDashboardRestModel> dashboardAppointment(String id) {
			logger.info("Method : dashboardMedicalCondn starts");

			PatientDashboardRestModel req = new PatientDashboardRestModel();
			JsonResponse<PatientDashboardRestModel> resp = new JsonResponse<PatientDashboardRestModel>();

			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("dashboard_appointment")
						.setParameter("dashboardID", id).getResultList();
				for (Object[] m : x) {

					PatientDashboardRestModel reqEdit = new PatientDashboardRestModel(m[0], m[1].toString(), m[2], m[3],
							m[4],null, null,null,null);
					req = reqEdit;

				}
				resp.setBody(req);
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("Method : dashboardMedicalCondn ends");

			return resp;
		}
		
		 
		//Dashboard Family Doctors
				@SuppressWarnings("unchecked")
				public JsonResponse<PatientDashboardRestModel> dashboardFamilyDoctors(String id) {
					logger.info("Method : dashboardFamilyDoctors starts");

					PatientDashboardRestModel req = new PatientDashboardRestModel();
					JsonResponse<PatientDashboardRestModel> resp = new JsonResponse<PatientDashboardRestModel>();
					

					try {

						List<Object[]> x = em.createNamedStoredProcedureQuery("dashboard_family_doctors").setParameter("dashboardID", id)
								.getResultList();
						for (Object[] m : x) {	
							
							PatientDashboardRestModel reqEdit = new PatientDashboardRestModel(m[0],m[1],m[2],null,null,
									null,null,null,null,null,null,null);
							req=reqEdit;				
							
						}
						resp.setBody(req);
					} catch (Exception e) {
						e.printStackTrace();
					}
					logger.info("Method : dashboardFamilyDoctors ends");
					
					return resp;
				}	
				
				
				
				
				//Dashboard Medical Condition Name
				@SuppressWarnings("unchecked")
				public JsonResponse<PatientDashboardRestModel> dashboardMedCondtnName(String id) {
					logger.info("Method : dashboardFamilyDoctors starts");

					PatientDashboardRestModel req = new PatientDashboardRestModel();
					JsonResponse<PatientDashboardRestModel> resp = new JsonResponse<PatientDashboardRestModel>();
					

					try {

						List<Object[]> x = em.createNamedStoredProcedureQuery("dashboard_medical_condition").setParameter("dashboardID", id)
								.getResultList();
						for (Object[] m : x) {	
							
							PatientDashboardRestModel reqEdit = new PatientDashboardRestModel(null,null,null,null,null,
									null,null,null,null,null,m[0],m[1]);
							req=reqEdit;				
							
							
						}
						resp.setBody(req);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					logger.info("Method : dashboardMedCondtnName ends");
					
					return resp;
				}			
				/**
				 * Dao Appointment
				 *
				 * @return
				 */
				
				@SuppressWarnings("unchecked")
				public List<PatientDashboardRestModel> appointmentCount(String id) {

					logger.info("Method : appointmentCount starts");

					List<PatientDashboardRestModel> employmentList = new ArrayList<PatientDashboardRestModel>();
					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("patient_count_appointment")
								.setParameter("dashboardid", id).getResultList();
						for (Object[] m : x) {
							PatientDashboardRestModel dropDownModel = new PatientDashboardRestModel(m[0], m[1], null,
									m[2], m[3], m[4], null, null, null, null, null, null, null);
							employmentList.add(dropDownModel);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					logger.info("Method : appointmentCount ends");

					return employmentList;
				}
				/*
				 * View Allergy
				 * 
				 */
				@SuppressWarnings("unchecked")
				public JsonResponse<List<RestPatientDetailsNewModel>> allergyDashboardViewDao(String id) {
					logger.info("Method : allergyDashboardViewDao starts");

					List<RestPatientDetailsNewModel> getAllemployee = new ArrayList<RestPatientDetailsNewModel>();
					JsonResponse<List<RestPatientDetailsNewModel>> resp = new JsonResponse<List<RestPatientDetailsNewModel>>();

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("user_view_patient_details_alergy")
								.setParameter("userid", id).getResultList();

						for (Object[] m : x) {

							RestPatientDetailsNewModel reqemp = new RestPatientDetailsNewModel(null, m[0], null, null, null, null,
									null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
									null, null, null, null, null, null, null, null, null, null, null, null, null, m[1], null, m[2],
									null, m[3], null, m[4], m[5], null, null, null, null, null, null, null, null, null, null, null,
									null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
									null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
									null, null, null);
							getAllemployee.add(reqemp);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					resp.setBody(getAllemployee);
					logger.info("Method : allergyDashboardViewDao ends");
					return resp;
				}
				/*
				 * drop down for allergyName
				 */
				@SuppressWarnings("unchecked")
				public List<DropDownModel> getallerNamelistDashboardDao() {
					logger.info("Method : getallerNamelist starts");

					List<DropDownModel> alernameList = new ArrayList<DropDownModel>();

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_allername_drpdwn_details")
								.getResultList();

						for (Object[] m : x) {
							DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
							alernameList.add(dropDownModel);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

					logger.info("Method : getallerNamelist ends");
					return alernameList;
				}

				/*
				 * drop down for allergyType
				 */
				@SuppressWarnings("unchecked")
				public List<DropDownModel> getallertypelistDashboardDao() {
					logger.info("Method : getallertypelist starts");

					List<DropDownModel> alernameList = new ArrayList<DropDownModel>();

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_allertype_drpdwn_details")
								.getResultList();

						for (Object[] m : x) {
							DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
							alernameList.add(dropDownModel);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

					logger.info("Method : getallertypelist ends");
					return alernameList;
				}	
				
				/*
				 * save patient allergy
				 * 
				 */

				public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> savedpatallergyDashboardDao(
						RestPatientDetailsNewModel restPatientDetailsNewModel) {
					logger.info("Method : savedpatallergy starts");

					JsonResponse<RestPatientDetailsNewModel> resp = new JsonResponse<RestPatientDetailsNewModel>();
					try {

						if (restPatientDetailsNewModel.getUserId() != null) {

							em.createNamedStoredProcedureQuery("user_patient_details_alergy_add")
									.setParameter("p_id", restPatientDetailsNewModel.getUserId())
									.setParameter("p_alernm", restPatientDetailsNewModel.getAllerNameId())
									.setParameter("p_alertype", restPatientDetailsNewModel.getAlergenTypeId())
									.setParameter("p_alerseverty", restPatientDetailsNewModel.getAllerSeverity())
									.setParameter("p_alerrect", restPatientDetailsNewModel.getAllerRection())
									.setParameter("p_alerupdatedby", restPatientDetailsNewModel.getDrUpdatedby())

									.execute();

						}

					} catch (Exception e) {
						try {
							String[] err = serverDao.errorProcedureCall(e);

							resp.setCode(err[0]);
							resp.setMessage(err[1]);

						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}

					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.set("MyResponseHeader", "MyValue");

					ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> response = new ResponseEntity<JsonResponse<RestPatientDetailsNewModel>>(
							resp, responseHeaders, HttpStatus.CREATED);

					logger.info("Method : savedpatallergy ends");
					return response;
				}		
				
				/*
				 * view Vitals
				 */
				@SuppressWarnings("unchecked")
				public JsonResponse<List<RestPatientDetailsNewModel>> editpatientvitalsignnewdashboardDao(String id) {
					logger.info("Method : editpatientvitalsignnew starts");

					List<RestPatientDetailsNewModel> getAllemployee = new ArrayList<RestPatientDetailsNewModel>();
					JsonResponse<List<RestPatientDetailsNewModel>> resp = new JsonResponse<List<RestPatientDetailsNewModel>>();

					try {

						// Integer id2 = Integer.parseInt(id);
						System.out.println("@@@@" + id);
						List<Object[]> x = em.createNamedStoredProcedureQuery("user_edit_patient_vitalsign_details")
								.setParameter("userid", id).getResultList();

						for (Object[] m : x) {
							String data = null;
							Double data1 = null;
							if (m[9] != null) {
								data = m[9].toString();
							}

							RestPatientDetailsNewModel reqemp = new RestPatientDetailsNewModel(null, null, null, null, null, null,
									null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
									null, null, null, m[0], m[1], m[2], m[3], null, m[4], m[5], m[6], m[7], m[8], null, null, null,
									null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
									null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
									null, null, null, null, null, null, null, null, null, null, null, null, null, data, null, null,
									null, null, null);
							getAllemployee .add(reqemp);					}

						
						resp.setBody(getAllemployee);
					} catch (Exception e) {
						e.printStackTrace();
					}

					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.set("MyResponseHeader", "MyValue");

					resp.setBody(getAllemployee);
					logger.info("Method : allergyDashboardViewDao ends");
					return resp;
				}
				/*
				 * save patient vitals
				 * 
				 */

				public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> savevitalsDashboardDao(
						RestPatientDetailsNewModel restPatientDetailsNewModel) {
					logger.info("Method : savedpatallergy starts");

					JsonResponse<RestPatientDetailsNewModel> resp = new JsonResponse<RestPatientDetailsNewModel>();
					try {

						if (restPatientDetailsNewModel.getUserId() == null) {

							em.createNamedStoredProcedureQuery("patient_details_vitals_add")

									.setParameter("actual_value1", restPatientDetailsNewModel.getHeightId())

									.setParameter("actual_value2", restPatientDetailsNewModel.getWeightid())

									.setParameter("actual_value3", restPatientDetailsNewModel.getBmi())

									.setParameter("actual_value4", restPatientDetailsNewModel.getTempCs())

									.setParameter("actual_value5", restPatientDetailsNewModel.getBldpsr())

									.setParameter("actual_value6", restPatientDetailsNewModel.getSysDis())

									.setParameter("actual_value7", restPatientDetailsNewModel.getPulseMin())

									.setParameter("actual_value8", restPatientDetailsNewModel.getRespBpm())

									.setParameter("actual_value9", restPatientDetailsNewModel.getOxygenPer())

									.execute();

						} else {

							em.createNamedStoredProcedureQuery("patient_details_vitals_modify")
									.setParameter("p_id", restPatientDetailsNewModel.getUserId())
									.setParameter("actual_value1", restPatientDetailsNewModel.getHeightId())

									.setParameter("actual_value2", restPatientDetailsNewModel.getWeightid())

									.setParameter("actual_value3", restPatientDetailsNewModel.getBmi())

									.setParameter("actual_value4", restPatientDetailsNewModel.getTempCs())

									.setParameter("actual_value5", restPatientDetailsNewModel.getBldpsr())

									.setParameter("actual_value6", restPatientDetailsNewModel.getSysDis())

									.setParameter("actual_value7", restPatientDetailsNewModel.getPulseMin())

									.setParameter("actual_value8", restPatientDetailsNewModel.getRespBpm())

									.setParameter("actual_value9", restPatientDetailsNewModel.getOxygenPer()).execute();
						}

					} catch (Exception e) {
						try {
							String[] err = serverDao.errorProcedureCall(e);

							resp.setCode(err[0]);
							resp.setMessage(err[1]);

						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}

					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.set("MyResponseHeader", "MyValue");

					ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> response = new ResponseEntity<JsonResponse<RestPatientDetailsNewModel>>(
							resp, responseHeaders, HttpStatus.CREATED);
						System.out.println("@@@@@@@@@@@@@@@@@@"+response);
					logger.info("Method : savedpatallergy ends");
					return response;
				}		
		
				
				/*
				 * AutoSearch for Family Details	
				 */
				@SuppressWarnings("unchecked")
				public ResponseEntity<JsonResponse<List<RestPatientRequestAmbulanceModel>>> getAmbulanceListAutoSearch(String id) {
					logger.info("Method : getAmbulanceListAutoSearch starts");
					List<RestPatientRequestAmbulanceModel> itemNameList = new ArrayList<RestPatientRequestAmbulanceModel>();
					JsonResponse<List<RestPatientRequestAmbulanceModel>> resp = new JsonResponse<List<RestPatientRequestAmbulanceModel>>();
				

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("patient_autoserach_ambulance_owner").setParameter("searchValue", id)
								.getResultList();
						
						for (Object[] m : x) {
							
							RestPatientRequestAmbulanceModel reqemp = new RestPatientRequestAmbulanceModel(m[0],m[1]);
							itemNameList.add(reqemp);
						}
						resp.setBody(itemNameList);
					} catch (Exception e) {
						e.printStackTrace();
					}
					ResponseEntity<JsonResponse<List<RestPatientRequestAmbulanceModel>>> response = new ResponseEntity<JsonResponse<List<RestPatientRequestAmbulanceModel>>>(
							resp, HttpStatus.CREATED);
					logger.info("Method : getAmbulanceListAutoSearch ends");
					return response;
				}
				/*
				 * DAO Function to Add Ambulance Request
				 *
				 */

				@SuppressWarnings({ "unused" })
				public ResponseEntity<JsonResponse<RestPatientRequestAmbulanceModel>> savepatientAmbulanceRequestDao(
						RestPatientRequestAmbulanceModel restPatientRequestAmbulanceModel) {
					logger.info("Method : restadddrDocumentUpload starts");
					
					JsonResponse<RestPatientRequestAmbulanceModel> resp = new JsonResponse<RestPatientRequestAmbulanceModel>();

						try {
							
							if (restPatientRequestAmbulanceModel.getPatientId() != null) {


								em.createNamedStoredProcedureQuery("patient_ambulance_request_save")
								.setParameter("p_pid", restPatientRequestAmbulanceModel.getPatientId())
								.setParameter("p_ambid", restPatientRequestAmbulanceModel.getAmbulanceId())
								//.setParameter("p_ambname", restPatientRequestAmbulanceModel.getAmbulaneOwnerName())
								.setParameter("p_ftmdest", restPatientRequestAmbulanceModel.getFrmDestination())
								.setParameter("p_todest", restPatientRequestAmbulanceModel.getToDestination())
								.setParameter("p_patnote", restPatientRequestAmbulanceModel.getPatientNote())
								.setParameter("p_dateid", restPatientRequestAmbulanceModel.getDateId())
								.setParameter("p_status", restPatientRequestAmbulanceModel.getStatus())
								
								.execute();
						
							}

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
					
						HttpHeaders responseHeaders = new HttpHeaders();
						responseHeaders.set("MyResponseHeader", "MyValue");
;
					ResponseEntity<JsonResponse<RestPatientRequestAmbulanceModel>> response = new ResponseEntity<JsonResponse<RestPatientRequestAmbulanceModel>>(
							resp, HttpStatus.CREATED);
					logger.info("Method : restadddrDocumentUpload ends");
					return response;

				}
/*
 * view Ambulance Requests
 */
				@SuppressWarnings("unchecked")
				public JsonResponse<List<RestPatientRequestAmbulanceModel>> viewAmbulancaRequestDao(String id,String place) {
					logger.info("Method :viewAmbulancaRequest Dao Starts");

					List<RestPatientRequestAmbulanceModel> getAllemployee = new ArrayList<RestPatientRequestAmbulanceModel>();
					JsonResponse<List<RestPatientRequestAmbulanceModel>> resp = new JsonResponse<List<RestPatientRequestAmbulanceModel>>();

					try {
						String userid = null;
						if (id != null) {
							userid = new String(id);
						}
						System.out.println(userid);
						List<Object[]> x = em.createNamedStoredProcedureQuery("patient_ambulance_request_view")
								.setParameter("userid", userid).setParameter("place", place).getResultList();

						for (Object[] m : x) {
							String data = null;
							if (m[6] != null) {
								data = m[6].toString();
							}
							RestPatientRequestAmbulanceModel reqemp = new RestPatientRequestAmbulanceModel(null,m[0],m[1],m[2],m[3],m[4],m[5],data,m[7]);
							getAllemployee.add(reqemp);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("$$$$$$$$$$$$$$$$$$SSSSSS"+resp);
					resp.setBody(getAllemployee);
					logger.info("Method : viewAmbulancaRequest Dao Ends");
					return resp;
				}	
				
				/*
				 * View data for emergency contact
				 */
				@SuppressWarnings("unchecked")
				public JsonResponse<List<RestPatientDetailsNewModel>> patientemercontactDashboardDao(String id) {
					logger.info("Method : Patient Emergency contact Dao Starts");

					List<RestPatientDetailsNewModel> getAllemployee = new ArrayList<RestPatientDetailsNewModel>();
					JsonResponse<List<RestPatientDetailsNewModel>> resp = new JsonResponse<List<RestPatientDetailsNewModel>>();

					try {
						BigInteger userId = null;
						if (id != null) {
							userId = new BigInteger(id);
						}
						List<Object[]> x = em.createNamedStoredProcedureQuery("user_edit_patient_emercontact_details")
								.setParameter("userid", userId).getResultList();

						for (Object[] m : x) {

							RestPatientDetailsNewModel reqemp = new RestPatientDetailsNewModel(m[0], m[1], m[2], m[3], m[4], m[5]);
							getAllemployee.add(reqemp);
							// System.out.println("===getAllemployee====="+getAllemployee);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
					resp.setBody(getAllemployee);
					logger.info("Method : Patient emergency contact Dao Ends");
					return resp;
				}
				/*
				 * View data for Family Doctor
				 */

				@SuppressWarnings("unchecked")
				public JsonResponse<List<RestPatientDetailsNewModel>> editpatientfamdocDashboardDao(String id) {
					logger.info("Method : editpatientfamdocnew starts");

					List<RestPatientDetailsNewModel> getAllemployee = new ArrayList<RestPatientDetailsNewModel>();
					JsonResponse<List<RestPatientDetailsNewModel>> resp = new JsonResponse<List<RestPatientDetailsNewModel>>();

					try {
						BigInteger userId = null;
						if (id != null) {
							userId = new BigInteger(id);
						}
						List<Object[]> x = em.createNamedStoredProcedureQuery("user_edit_patient_famdoc_details")
								.setParameter("userid", userId).getResultList();

						for (Object[] m : x) {

							RestPatientDetailsNewModel reqemp = new RestPatientDetailsNewModel(m[0], m[1], m[2], m[3], m[4], m[5],
									null);
							getAllemployee.add(reqemp);
							System.out.println("family ssss" + getAllemployee);
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

					resp.setBody(getAllemployee);
					System.out.println("editpatientfamdocnew" + resp);
					logger.info("Method : editpatientfamdocnew ends");
					return resp;
				}		
}
