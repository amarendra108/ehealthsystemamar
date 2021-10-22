package nirmalya.aatithya.restmodule.dashboard.dao;

import java.util.ArrayList;
import java.util.HashSet;
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
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.dashboard.model.CMDashboardGraphicalModel;
import nirmalya.aatithya.restmodule.user.dao.DmDashboardDao;


@Repository
public class RestCmDao {
	Logger logger = LoggerFactory.getLogger(DmDashboardDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> cmDashboardGenderWisePatientno(String stateId,String date,String type) {
		logger.info("Method : viewdmDashboardCount starts");

		List<CMDashboardGraphicalModel> req = new ArrayList<CMDashboardGraphicalModel>();
		JsonResponse<List<CMDashboardGraphicalModel>> resp = new JsonResponse<List<CMDashboardGraphicalModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_genderwise_patientno")
					.setParameter("stateId", stateId).setParameter("date", date).setParameter("type", type).getResultList();
			for (Object[] m : x) {
				String date1 = null;
				
				date1 = DateFormatter.returnStringDateNew(m[0].toString());
				CMDashboardGraphicalModel reqemp = new CMDashboardGraphicalModel(date1, m[1],null,null,null,null,null,m[2]);
				
				req.add(reqemp);
			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		} 

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> response = new ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);
		System.out.println(response);
		logger.info("Method : viewdmDashboardCount ends")	;
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> cmDashboardAreaWisePatientno(String stateId,String date,String type) {
		logger.info("Method : viewdmDashboardCount starts");

		
		List<CMDashboardGraphicalModel> req = new ArrayList<CMDashboardGraphicalModel>();
		JsonResponse<List<CMDashboardGraphicalModel>> resp = new JsonResponse<List<CMDashboardGraphicalModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_areawise_patientno")
					.setParameter("stateId", stateId).setParameter("date", date).setParameter("type", type).getResultList();
			for (Object[] m : x) {
				String date1 = null;
				
				//date1 = DateFormatter.returnStringDateNew(m[0].toString());
				CMDashboardGraphicalModel reqemp = new CMDashboardGraphicalModel(null,null,m[0],null,null,null,null,m[1]);
				
				req.add(reqemp);
			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		} 

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> response = new ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);
		System.out.println(response);
		return response;
	}
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> cmDashboardAgeWisePatientno(String stateId,String date,String type) {
		logger.info("Method : cmDashboardAgeWisePatientno starts");

		
		List<CMDashboardGraphicalModel> req = new ArrayList<CMDashboardGraphicalModel>();
		JsonResponse<List<CMDashboardGraphicalModel>> resp = new JsonResponse<List<CMDashboardGraphicalModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_agewise_healthcheckup")
					.setParameter("stateId", stateId).setParameter("date", date).setParameter("type", type).getResultList();
			for (Object[] m : x) {
				String date1 = null;
				
				//date1 = DateFormatter.returnStringDateNew(m[0].toString());
				CMDashboardGraphicalModel reqemp =new CMDashboardGraphicalModel(null,null,null,m[0],null,null,null,m[1]);
				
				
				req.add(reqemp);
			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		} 

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> response = new ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);
		System.out.println(response);
	
		return response;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> cmDashboardDiseaseMaleFemaleWisePatientno(String stateId,String date,String type) {
		logger.info("Method : cmDashboardAgeWisePatientno starts");

		
		List<CMDashboardGraphicalModel> req = new ArrayList<CMDashboardGraphicalModel>();
		JsonResponse<List<CMDashboardGraphicalModel>> resp = new JsonResponse<List<CMDashboardGraphicalModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_genderwise_disease")
					.setParameter("stateId", stateId).setParameter("date", date).setParameter("type", type).getResultList();
			for (Object[] m : x) {
				String date1 = null;
				
				//date1 = DateFormatter.returnStringDateNew(m[0].toString());
				CMDashboardGraphicalModel reqemp = new CMDashboardGraphicalModel(null,m[0],null,null,m[1],null,null,m[2]);
				
				
				req.add(reqemp);
			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		} 

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> response = new ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);
		System.out.println(response);
	
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<CMDashboardGraphicalModel>> cmDashboardRiskWisePatientno(String stateId,String date,String type) {
		logger.info("Method : cmDashboardTestWisePatientno starts");

		CMDashboardGraphicalModel req = new CMDashboardGraphicalModel();
		JsonResponse<CMDashboardGraphicalModel> resp = new JsonResponse<CMDashboardGraphicalModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_riskwise_patientno")
					.setParameter("stateId", stateId).setParameter("date", date).setParameter("type", type).getResultList();

			for (Object[] m : x) {
				CMDashboardGraphicalModel reqemp = new CMDashboardGraphicalModel(null,null,null,null,null,null,m[0],m[1]);
				
				req = reqemp;
			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<CMDashboardGraphicalModel>> response = new ResponseEntity<JsonResponse<CMDashboardGraphicalModel>>(
				resp, responseHeaders, HttpStatus.CREATED);
		
		logger.info("Method : cmDashboardRiskWisePatientno ends")	;
		return response;
	}
	

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<CMDashboardGraphicalModel>> cmDashboardPhcWisePatientno(String stateId,String date,String type) {
		logger.info("Method : cmDashboardTestWisePatientno starts");

		CMDashboardGraphicalModel req = new CMDashboardGraphicalModel();
		JsonResponse<CMDashboardGraphicalModel> resp = new JsonResponse<CMDashboardGraphicalModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_phcwise_patientno")
					.setParameter("stateId", stateId).setParameter("date", date).setParameter("type", type).getResultList();

			for (Object[] m : x) {
				CMDashboardGraphicalModel reqemp = new CMDashboardGraphicalModel(null,null,null,null,null,m[0],m[1],m[2]);
				
				req = reqemp;
			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<CMDashboardGraphicalModel>> response = new ResponseEntity<JsonResponse<CMDashboardGraphicalModel>>(
				resp, responseHeaders, HttpStatus.CREATED);
		
		logger.info("Method : cmDashboardRiskWisePatientno ends")	;
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<CMDashboardGraphicalModel>> cmDashboardDieaseWisePatientno(String stateId,String date,String type) {
		logger.info("Method : cmDashboardDieaseWisePatientno starts");

		CMDashboardGraphicalModel req = new CMDashboardGraphicalModel();
		JsonResponse<CMDashboardGraphicalModel> resp = new JsonResponse<CMDashboardGraphicalModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_dieaseswise_patientno")
					.setParameter("stateId", stateId).setParameter("date", date).setParameter("type", type).getResultList();

			for (Object[] m : x) {
				CMDashboardGraphicalModel reqemp = new CMDashboardGraphicalModel(null,null,null,null,m[0],null,null,m[1]);
				
				req = reqemp;
			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<CMDashboardGraphicalModel>> response = new ResponseEntity<JsonResponse<CMDashboardGraphicalModel>>(
				resp, responseHeaders, HttpStatus.CREATED);
		
		logger.info("Method : cmDashboardDieaseWisePatientno ends")	;
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<CMDashboardGraphicalModel>>cmDashboardpercentageDieaseWisePatientno(String stateId,String date,String type) {
		logger.info("Method : cmDashboardpercentageDieaseWisePatientno starts");

		CMDashboardGraphicalModel req = new CMDashboardGraphicalModel();
		JsonResponse<CMDashboardGraphicalModel> resp = new JsonResponse<CMDashboardGraphicalModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_percentagedieaseswise_patientno")
					.setParameter("stateId", stateId).setParameter("date", date).setParameter("type", type).getResultList();

			for (Object[] m : x) {
				CMDashboardGraphicalModel reqemp = new CMDashboardGraphicalModel(null,null,null,null,m[0],null,null,m[1]);
				
				req = reqemp;
			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<CMDashboardGraphicalModel>> response = new ResponseEntity<JsonResponse<CMDashboardGraphicalModel>>(
				resp, responseHeaders, HttpStatus.CREATED);
		
		logger.info("Method : cmDashboardDieaseWisePatientno ends")	;
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	
	//Total Health Checkup Status
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> totalHealthCheckup(String stateId,String date,String type) {
			logger.info("Method : totalHealthCheckup starts");

			List<CMDashboardGraphicalModel> req = new ArrayList<CMDashboardGraphicalModel>();
			JsonResponse<List<CMDashboardGraphicalModel>> resp = new JsonResponse<List<CMDashboardGraphicalModel>>();

			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_checkup_status")
						.setParameter("stateId", stateId).setParameter("date", date).setParameter("type", type).getResultList();
				for (Object[] m : x) {
					String date1 = null;
					
					/* date1 = DateFormatter.returnStringDateNew(m[0].toString()); */
					CMDashboardGraphicalModel reqemp = new CMDashboardGraphicalModel(null,null,null,null,null,null,m[0],m[1]);
				//	System.out.println("totalHealthCheckup1"+reqemp);
					req.add(reqemp);
				}
				resp.setBody(req);
			} catch (Exception e) {
				e.printStackTrace();
			} 

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("MyResponseHeader", "MyValue");

			ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> response = new ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>>(
					resp, responseHeaders, HttpStatus.CREATED);
			//System.out.println("totalHealthCheckup"+response);
			logger.info("Method : totalHealthCheckup ends")	;
			return response;
		}
		
		
		//High Risk Data Location Wise
			@SuppressWarnings("unchecked")
			public ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> getHighRiskLocationWise(String stateId,String date,String type) {
				logger.info("Method : totalHealthCheckup starts");

				List<CMDashboardGraphicalModel> req = new ArrayList<CMDashboardGraphicalModel>();
				JsonResponse<List<CMDashboardGraphicalModel>> resp = new JsonResponse<List<CMDashboardGraphicalModel>>();

				try {
					List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_areawise_highrisk")
							.setParameter("stateId", stateId).setParameter("date", date).setParameter("type", type).getResultList();
					for (Object[] m : x) {
						String date1 = null;
						
						/* date1 = DateFormatter.returnStringDateNew(m[0].toString()); */
						CMDashboardGraphicalModel reqemp = new CMDashboardGraphicalModel(null,null,null,null,null,m[0],m[1],m[2]);
						System.out.println("getHighRiskLocationWise"+reqemp);
						req.add(reqemp);
					}
					resp.setBody(req);
				} catch (Exception e) {
					e.printStackTrace();
				} 

				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.set("MyResponseHeader", "MyValue");

				ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> response = new ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>>(
						resp, responseHeaders, HttpStatus.CREATED);
				System.out.println("getHighRiskLocationWise22222222222222"+response);
				logger.info("Method : getHighRiskLocationWise ends")	;
				return response;
			}



	// Rest Disese wise total patient no DAo
		@SuppressWarnings("unchecked")
		public JsonResponse<List<CMDashboardGraphicalModel>> cmDashboardDieaseWisePatientnoDao(String state,String date,String type) {
			logger.info("Method : cmDashboardDieaseWisePatientno starts");

			List<CMDashboardGraphicalModel> req = new ArrayList<CMDashboardGraphicalModel>();
			JsonResponse<List<CMDashboardGraphicalModel>> resp = new JsonResponse<List<CMDashboardGraphicalModel>>();

			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_diseasewise_total")
						.setParameter("stateId", state).setParameter("date", date).setParameter("type", type).getResultList();

				for (Object[] m : x) {
					CMDashboardGraphicalModel reqemp = new CMDashboardGraphicalModel(null,null,null,null,m[0],null,null,m[1]);
					
					req.add(reqemp);
				}

				resp.setBody(req);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("cm_dashboard_dieaseswise_patientno "+resp);
			logger.info("Method : cmDashboardDieaseWisePatientno ends")	;
			return resp;
		}





	@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> cmDashboardTreandingDisease(String stateId,String date,String type) {
			logger.info("Method : cmDashboardTreandingDisease starts");

			
			List<CMDashboardGraphicalModel> req = new ArrayList<CMDashboardGraphicalModel>();
			JsonResponse<List<CMDashboardGraphicalModel>> resp = new JsonResponse<List<CMDashboardGraphicalModel>>();

			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_disease_trand")
						.setParameter("stateId", stateId).setParameter("date", date).setParameter("type", type).getResultList();
				for (Object[] m : x) {
					//String date1 = null;
					
					//date1 = DateFormatter.returnStringDateNew(m[0].toString());
					CMDashboardGraphicalModel reqemp = new CMDashboardGraphicalModel(null,null,null,null,m[0],null,null,m[1]);
					req.add(reqemp);
					System.out.println("Disease Treand"+reqemp);
				}
				resp.setBody(req);
			} catch (Exception e) {
				e.printStackTrace();
			} 

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("MyResponseHeader", "MyValue");

			ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>> response = new ResponseEntity<JsonResponse<List<CMDashboardGraphicalModel>>>(
					resp, responseHeaders, HttpStatus.CREATED);
			
			logger.info("Method : cmDashboardTreandingDisease ends")	;
			System.out.println(response);
			return response;
		}


		//count test details
		
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<CMDashboardGraphicalModel>> countTestDetailsDao(String stateId,String date,String type) {
			logger.info("Method : countTestDetailsDao starts");

			CMDashboardGraphicalModel req = new CMDashboardGraphicalModel();
			JsonResponse<CMDashboardGraphicalModel> resp = new JsonResponse<CMDashboardGraphicalModel>();

			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("cm_dashboard_count_test_details")
						.setParameter("stateId", stateId).setParameter("date", date).setParameter("type", type).getResultList();
				for (Object[] m : x) {
					/*
					 * String date1 = null;
					 * 
					 * date1 = DateFormatter.returnStringDateNew(m[0].toString());
					 */
					CMDashboardGraphicalModel countDetails = new CMDashboardGraphicalModel(m[0],m[1],m[2],m[3],m[4],m[5],m[6],m[7],null);
					
					req=countDetails;
				}
				resp.setBody(req);
			} catch (Exception e) {
				e.printStackTrace();
			} 

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("MyResponseHeader", "MyValue");

			ResponseEntity<JsonResponse<CMDashboardGraphicalModel>> response = new ResponseEntity<JsonResponse<CMDashboardGraphicalModel>>(
					resp, responseHeaders, HttpStatus.CREATED);
			logger.info("Method : countTestDetailsDao ends")	;
			return response;
		}
}
