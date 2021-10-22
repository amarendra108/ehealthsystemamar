package nirmalya.aatithya.restmodule.pm.dao;

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
import nirmalya.aatithya.restmodule.pm.model.RestHieDashboardModel;
import nirmalya.aatithya.restmodule.user.dao.RestPMDashboardDao;
@Repository
public class RestHieDashboardDao {
	
	Logger logger = LoggerFactory.getLogger(RestPMDashboardDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	// Dashboard  GetIdd  Country list
		@SuppressWarnings("unchecked")
		public List<DropDownModel> pmDashboardgetId(String id) {
			logger.info("Method : pmDashboardgetId Dao starts");
			
			List<DropDownModel> pmDashboardgetId = new ArrayList<DropDownModel>();

			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("pm_dashboard_get_idd").setParameter("pmid", id).getResultList();

				for (Object[] m : x) {

					DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
					pmDashboardgetId.add(dropDownModel);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
	   	logger.info("Method : getCountryLists Dao ends");
			return pmDashboardgetId;
		}
		
		//count test details
		
				@SuppressWarnings("unchecked")
				public ResponseEntity<JsonResponse<RestHieDashboardModel>> getCountHieDashboards(String country,String date,String type) {
					logger.info("Method : countTestDetailsDao starts");

					RestHieDashboardModel req = new RestHieDashboardModel();
					JsonResponse<RestHieDashboardModel> resp = new JsonResponse<RestHieDashboardModel>();

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("mys_dashboard_view_count")
								.setParameter("country", country).setParameter("date", date).setParameter("type", type).getResultList();
						for (Object[] m : x) {
							RestHieDashboardModel countDetails = new RestHieDashboardModel(m[0],m[1],m[2],m[3],m[4],m[5],m[6],m[7]);
							
							req=countDetails;
						}
						resp.setBody(req);
					} catch (Exception e) {
						e.printStackTrace();
					} 

					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.set("MyResponseHeader", "MyValue");
					ResponseEntity<JsonResponse<RestHieDashboardModel>> response = new ResponseEntity<JsonResponse<RestHieDashboardModel>>(
							resp, responseHeaders, HttpStatus.CREATED);
					logger.info("Method : countTestDetailsDao ends")	;
					return response;
				}
				//Diagnostic Lab
				@SuppressWarnings("unchecked")
				public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getDiagnosticLab(String country,String date,String type) {
					logger.info("Method : getTotalHospitalDao starts");

					List<RestHieDashboardModel> req = new ArrayList<RestHieDashboardModel>();
					JsonResponse<List<RestHieDashboardModel>> resp = new JsonResponse<List<RestHieDashboardModel>>();

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("mys_dashboard_view_diagnostic_lab")
								.setParameter("country", country).setParameter("date", date).setParameter("type", type).getResultList();
						for (Object[] m : x) {
							
							RestHieDashboardModel reqemp = new RestHieDashboardModel(m[0],m[1],m[2]);
							
							req.add(reqemp);
						}
						resp.setBody(req);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.set("MyResponseHeader", "MyValue");

					ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> response = new ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>(
							resp, responseHeaders, HttpStatus.CREATED);
					logger.info("Method : getTotalHospitalDao ends")	;
					return response;
				}
				
				//Pathology Lab
				@SuppressWarnings("unchecked")
				public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getPathologyLab(String country,String date,String type) {
					logger.info("Method : getTotalHospitalDao starts");

					List<RestHieDashboardModel> req = new ArrayList<RestHieDashboardModel>();
					JsonResponse<List<RestHieDashboardModel>> resp = new JsonResponse<List<RestHieDashboardModel>>();

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("mys_dashboard_view_pathology_lab")
								.setParameter("country", country).setParameter("date", date).setParameter("type", type).getResultList();
						for (Object[] m : x) {
							
							RestHieDashboardModel reqemp = new RestHieDashboardModel(m[0],m[1],m[2]);
							
							req.add(reqemp);
						}
						resp.setBody(req);
					} catch (Exception e) {
						e.printStackTrace();
					} 
					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.set("MyResponseHeader", "MyValue");

					ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> response = new ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>(
							resp, responseHeaders, HttpStatus.CREATED);
					logger.info("Method : getPathologyLab ends")	;
					return response;
				}
				
				//Total Pharmacy
				
				@SuppressWarnings("unchecked")
				public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalPharmacy(String country,
						String date, String type) {
					logger.info("Method : getTotalPharmacy starts");

					List<RestHieDashboardModel> req = new ArrayList<RestHieDashboardModel>();
					JsonResponse<List<RestHieDashboardModel>> resp = new JsonResponse<List<RestHieDashboardModel>>();

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("mys_dashboard_view_total_pharmacy")
								.setParameter("country", country).setParameter("date", date).setParameter("type", type)
								.getResultList();
						for (Object[] m : x) {

							RestHieDashboardModel reqemp = new RestHieDashboardModel(m[0], m[1],null);

							req.add(reqemp);
						}
						resp.setBody(req);
					} catch (Exception e) {
						e.printStackTrace();
					}

					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.set("MyResponseHeader", "MyValue");

					ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> response = new ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>(
							resp, responseHeaders, HttpStatus.CREATED);
					logger.info("Method : getTotalPharmacy ends");
					return response;
				}
				 
				
				//Total Ambulance
				
				@SuppressWarnings("unchecked")
				public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalAmbulance(String country,
						String date, String type) {
					logger.info("Method : getTotalAmbulance starts");

					List<RestHieDashboardModel> req = new ArrayList<RestHieDashboardModel>();
					JsonResponse<List<RestHieDashboardModel>> resp = new JsonResponse<List<RestHieDashboardModel>>();

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("mys_dashboard_view_ambulance")
								.setParameter("country", country).setParameter("date", date).setParameter("type", type)
								.getResultList();
						for (Object[] m : x) {

							RestHieDashboardModel reqemp = new RestHieDashboardModel(m[0], m[1], m[2],m[3]);

							req.add(reqemp);
						}
						resp.setBody(req);
					} catch (Exception e) {
						e.printStackTrace();
					}

					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.set("MyResponseHeader", "MyValue");

					ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> response = new ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>(
							resp, responseHeaders, HttpStatus.CREATED);
					logger.info("Method : getTotalAmbulance ends");
					return response;
				}
				 
				//Total Blood Bank
				@SuppressWarnings("unchecked")
				public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalBloodBank(String country,String date,String type) {
					logger.info("Method : getTotalBloodBank starts");

					List<RestHieDashboardModel> req = new ArrayList<RestHieDashboardModel>();
					JsonResponse<List<RestHieDashboardModel>> resp = new JsonResponse<List<RestHieDashboardModel>>();

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("mys_dashboard_view_blood_bank")
								.setParameter("country", country).setParameter("date", date).setParameter("type", type).getResultList();
						for (Object[] m : x) {
							
							RestHieDashboardModel reqemp = new RestHieDashboardModel(m[0],m[1],m[2]);
							
							req.add(reqemp);
						}
						resp.setBody(req);
					} catch (Exception e) {
						e.printStackTrace();
					} 

					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.set("MyResponseHeader", "MyValue");
					ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> response = new ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>(
							resp, responseHeaders, HttpStatus.CREATED);
					logger.info("Method : getTotalBloodBank ends")	;
					return response;
				}
				
				
				//Total Other NGO and HSP
				/*
				 * @SuppressWarnings("unchecked") public
				 * ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>
				 * getTotalNgoHsp(String country,String date,String type) {
				 * logger.info("Method : getTotalNgoHsp starts");
				 * 
				 * List<RestHieDashboardModel> req = new ArrayList<RestHieDashboardModel>();
				 * JsonResponse<List<RestHieDashboardModel>> resp = new
				 * JsonResponse<List<RestHieDashboardModel>>();
				 * 
				 * try { List<Object[]> x =
				 * em.createNamedStoredProcedureQuery("mys_dashboard_total_ngo_hsp")
				 * .setParameter("country", country).setParameter("date",
				 * date).setParameter("type", type).getResultList(); for (Object[] m : x) {
				 * 
				 * RestHieDashboardModel reqemp = new RestHieDashboardModel(m[0],m[1],m[2]);
				 * 
				 * req.add(reqemp); } resp.setBody(req); } catch (Exception e) {
				 * e.printStackTrace(); }
				 * 
				 * HttpHeaders responseHeaders = new HttpHeaders();
				 * responseHeaders.set("MyResponseHeader", "MyValue");
				 * 
				 * ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> response = new
				 * ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>( resp,
				 * responseHeaders, HttpStatus.CREATED); //System.out.println(response);
				 * logger.info("Method : getTotalNgoHsp ends") ; return response; }
				 */
				
				//Total Blood Donor-Health Hero's
				/*
				 * @SuppressWarnings("unchecked") public
				 * ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>
				 * getTotalBloodDonor(String country,String date,String type) {
				 * logger.info("Method : getTotalBloodDonor starts");
				 * 
				 * List<RestHieDashboardModel> req = new ArrayList<RestHieDashboardModel>();
				 * JsonResponse<List<RestHieDashboardModel>> resp = new
				 * JsonResponse<List<RestHieDashboardModel>>();
				 * 
				 * try { List<Object[]> x =
				 * em.createNamedStoredProcedureQuery("mys_dashboard_total_blood_donor")
				 * .setParameter("country", country).setParameter("date",
				 * date).setParameter("type", type).getResultList(); for (Object[] m : x) {
				 * 
				 * RestHieDashboardModel reqemp = new RestHieDashboardModel(m[0],m[1],m[2]);
				 * 
				 * req.add(reqemp); } resp.setBody(req); } catch (Exception e) {
				 * e.printStackTrace(); }
				 * 
				 * HttpHeaders responseHeaders = new HttpHeaders();
				 * responseHeaders.set("MyResponseHeader", "MyValue");
				 * 
				 * ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> response = new
				 * ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>( resp,
				 * responseHeaders, HttpStatus.CREATED); //System.out.println(response);
				 * logger.info("Method : getTotalBloodDonor ends") ; return response; }
				 */
				
				//Total Organ Donor-Health Hero's
				
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalOrganDonor(String country, String date,
			String type) {
		logger.info("Method : getTotalOrganDonor starts");

		List<RestHieDashboardModel> req = new ArrayList<RestHieDashboardModel>();
		JsonResponse<List<RestHieDashboardModel>> resp = new JsonResponse<List<RestHieDashboardModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("mys_dashboard_total_organ_donor")
					.setParameter("country", country).setParameter("date", date).setParameter("type", type)
					.getResultList();
			for (Object[] m : x) {

				RestHieDashboardModel reqemp = new RestHieDashboardModel(m[0], m[1],null);

				req.add(reqemp);
			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> response = new ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>(
				resp, responseHeaders, HttpStatus.CREATED); // System.out.println(response);
		logger.info("Method : getTotalOrganDonor ends");
		return response;
	}
				 
				
				//Age Wise
				
				@SuppressWarnings("unchecked")
				public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalAgeWise(String country,
						String date, String type) {
					logger.info("Method : getTotalAgeWise starts");

					List<RestHieDashboardModel> req = new ArrayList<RestHieDashboardModel>();
					JsonResponse<List<RestHieDashboardModel>> resp = new JsonResponse<List<RestHieDashboardModel>>();

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("mys_dashboard_total_age_wise")
								.setParameter("country", country).setParameter("date", date).setParameter("type", type)
								.getResultList();
						for (Object[] m : x) {

							RestHieDashboardModel reqemp = new RestHieDashboardModel(m[0], m[1], null);

							req.add(reqemp);
						}
						resp.setBody(req);
					} catch (Exception e) {
						e.printStackTrace();
					}

					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.set("MyResponseHeader", "MyValue");

					ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> response = new ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>(
							resp, responseHeaders, HttpStatus.CREATED); 
				//	System.out.println("getTotalAgeWise"+response);
					logger.info("Method : getTotalAgeWise ends");
					return response;
				}
				 
				
				//Gender Wise
				
				@SuppressWarnings("unchecked")
				public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalGenderWise(String country,
						String date, String type) {
					logger.info("Method : getTotalGenderWise starts");

					List<RestHieDashboardModel> req = new ArrayList<RestHieDashboardModel>();
					JsonResponse<List<RestHieDashboardModel>> resp = new JsonResponse<List<RestHieDashboardModel>>();

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("mys_dashboard_total_gender_wise")
								.setParameter("country", country).setParameter("date", date).setParameter("type", type)
								.getResultList();
						for (Object[] m : x) {

							RestHieDashboardModel reqemp = new RestHieDashboardModel(m[0], m[1], m[2]);

							req.add(reqemp);
						}
						resp.setBody(req);
					} catch (Exception e) {
						e.printStackTrace();
					}

					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.set("MyResponseHeader", "MyValue");

					ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> response = new ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>(
							resp, responseHeaders, HttpStatus.CREATED); 
					logger.info("Method : getTotalGenderWise ends");
					return response;
				}
				 
				
				//Parameters Wise Testing
				/*
				 * @SuppressWarnings("unchecked") public
				 * ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>
				 * getTotalParameterTesting(String country,String date,String type) {
				 * logger.info("Method : getTotalParameterTesting starts");
				 * 
				 * List<RestHieDashboardModel> req = new ArrayList<RestHieDashboardModel>();
				 * JsonResponse<List<RestHieDashboardModel>> resp = new
				 * JsonResponse<List<RestHieDashboardModel>>();
				 * 
				 * try { List<Object[]> x =
				 * em.createNamedStoredProcedureQuery("mys_dashboard_total_parameter_testing")
				 * .setParameter("country", country).setParameter("date",
				 * date).setParameter("type", type).getResultList(); for (Object[] m : x) {
				 * 
				 * RestHieDashboardModel reqemp = new RestHieDashboardModel(m[0],m[1],m[2]);
				 * 
				 * req.add(reqemp); } resp.setBody(req); } catch (Exception e) {
				 * e.printStackTrace(); }
				 * 
				 * HttpHeaders responseHeaders = new HttpHeaders();
				 * responseHeaders.set("MyResponseHeader", "MyValue");
				 * 
				 * ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> response = new
				 * ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>( resp,
				 * responseHeaders, HttpStatus.CREATED); //System.out.println(response);
				 * logger.info("Method : getTotalParameterTesting ends") ; return response; }
				 */
				
				//Low Risk High Risk
				
				/*@SuppressWarnings("unchecked")
				public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalLowRisk(String country,
						String date, String type) {
					logger.info("Method : getTotalLowRisk starts");

					List<RestHieDashboardModel> req = new ArrayList<RestHieDashboardModel>();
					JsonResponse<List<RestHieDashboardModel>> resp = new JsonResponse<List<RestHieDashboardModel>>();

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("mys_dashboard_total_low_risk")
								.setParameter("country", country).setParameter("date", date).setParameter("type", type)
								.getResultList();
						for (Object[] m : x) {

							RestHieDashboardModel reqemp = new RestHieDashboardModel(m[0], m[1], m[2]);

							req.add(reqemp);
						}
						resp.setBody(req);
					} catch (Exception e) {
						e.printStackTrace();
					}

					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.set("MyResponseHeader", "MyValue");

					ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> response = new ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>(
							resp, responseHeaders, HttpStatus.CREATED); 
					System.out.println("getTotalLowRisk"+response);
					logger.info("Method : getTotalLowRisk ends");
					return response;
				}*/
				
				//Total Doctors Pvt./Govt.
				
				@SuppressWarnings("unchecked")
				public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalDoctorsPvt(String country,
						String date, String type) {
					logger.info("Method : getTotalDoctorsPvt starts");

					List<RestHieDashboardModel> req = new ArrayList<RestHieDashboardModel>();
					JsonResponse<List<RestHieDashboardModel>> resp = new JsonResponse<List<RestHieDashboardModel>>();

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("mys_dashboard_view_doctor_month_wise")
								.setParameter("country", country).setParameter("date", date).setParameter("type", type)
								.getResultList();
						for (Object[] m : x) {

							RestHieDashboardModel reqemp = new RestHieDashboardModel(m[0], m[1], m[2]);

							req.add(reqemp);
						}
						resp.setBody(req);
					} catch (Exception e) {
						e.printStackTrace();
					}

					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.set("MyResponseHeader", "MyValue");

					ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> response = new ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>(
							resp, responseHeaders, HttpStatus.CREATED);
					logger.info("Method : getTotalDoctorsPvt ends");
					return response;
				}
				 
				
				//Total Insurance Service Provider
				@SuppressWarnings("unchecked")
				public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalInsuranceProvider(String country,String date,String type) {
					logger.info("Method : getTotalInsuranceProvider starts");

					List<RestHieDashboardModel> req = new ArrayList<RestHieDashboardModel>();
					JsonResponse<List<RestHieDashboardModel>> resp = new JsonResponse<List<RestHieDashboardModel>>();

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("mys_dashboard_view_insurance")
								.setParameter("country", country).setParameter("date", date).setParameter("type", type).getResultList();
						for (Object[] m : x) {
							
							RestHieDashboardModel reqemp = new RestHieDashboardModel(m[0],m[1],null);
							
							req.add(reqemp);
						}
						resp.setBody(req);
					} catch (Exception e) {
						e.printStackTrace();
					} 

					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.set("MyResponseHeader", "MyValue");
					ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> response = new ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>(
							resp, responseHeaders, HttpStatus.CREATED);
					logger.info("Method : getTotalInsuranceProvider ends")	;
					return response;
				}
				
				@SuppressWarnings("unchecked")
				public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalHospitalDao(String country,String date,String type) {
					logger.info("Method : getTotalHospitalDao starts");

					List<RestHieDashboardModel> req = new ArrayList<RestHieDashboardModel>();
					JsonResponse<List<RestHieDashboardModel>> resp = new JsonResponse<List<RestHieDashboardModel>>();

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("mys_dashboard_view_hospital_area_wise")
								.setParameter("country", country).setParameter("date", date).setParameter("type", type).getResultList();
						for (Object[] m : x) {
							
							RestHieDashboardModel reqemp = new RestHieDashboardModel(m[0],m[1],m[2]);
							
							req.add(reqemp);
						}
						resp.setBody(req);
					} catch (Exception e) {
						e.printStackTrace();
					} 

					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.set("MyResponseHeader", "MyValue");

					ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> response = new ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>(
							resp, responseHeaders, HttpStatus.CREATED);
					logger.info("Method : getTotalHospitalDao ends")	;
					return response;
				}

				@SuppressWarnings("unchecked")
				public ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> getTotalClinicDao(String country, String date,
						String type) {
					logger.info("Method : getTotalClinicDao starts");

					List<RestHieDashboardModel> req = new ArrayList<RestHieDashboardModel>();
					JsonResponse<List<RestHieDashboardModel>> resp = new JsonResponse<List<RestHieDashboardModel>>();

					try {
						List<Object[]> x = em.createNamedStoredProcedureQuery("mys_dashboard_view_clinic_area_wise")
								.setParameter("country", country).setParameter("date", date).setParameter("type", type).getResultList();
						for (Object[] m : x) {
							
							RestHieDashboardModel reqemp = new RestHieDashboardModel(m[0],m[1],m[2]);
							
							req.add(reqemp);
						}
						resp.setBody(req);
					} catch (Exception e) {
						e.printStackTrace();
					} 

					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.set("MyResponseHeader", "MyValue");
					ResponseEntity<JsonResponse<List<RestHieDashboardModel>>> response = new ResponseEntity<JsonResponse<List<RestHieDashboardModel>>>(
							resp, responseHeaders, HttpStatus.CREATED);
					logger.info("Method : getTotalClinicDao ends")	;
					return response;
				}
}
