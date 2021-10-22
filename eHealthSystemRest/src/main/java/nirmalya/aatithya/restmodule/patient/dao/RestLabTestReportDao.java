package nirmalya.aatithya.restmodule.patient.dao;

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
import nirmalya.aatithya.restmodule.patient.model.RestPatientTestNameModel;
import nirmalya.aatithya.restmodule.patient.model.RestLabTestReportModel;

@Repository
public class RestLabTestReportDao {
	Logger logger = LoggerFactory.getLogger(RestLabTestReportDao.class);

	@Autowired
	ServerDao serverDao;

	@Autowired
	EntityManager em;

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestLabTestReportModel>> getPatientTestDetails(String id) {
		logger.info("Method : getPatientTestDetails starts");
		List<RestLabTestReportModel> patientdetails = new ArrayList<RestLabTestReportModel>();
		
		JsonResponse<List<RestLabTestReportModel>> resp = new JsonResponse<List<RestLabTestReportModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_test_details")
					.setParameter("patientid", id).getResultList();

			for (Object[] m : x) {

				Object TODATE = null;
				if (m[6] != null) {
					TODATE = m[6].toString();
				}

				RestLabTestReportModel reqEdit = new RestLabTestReportModel(m[0], m[1], m[2], m[3], m[4], m[5], TODATE,
						m[7]);
				patientdetails.add(reqEdit);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
			if (patientdetails != null) {
				List<RestPatientTestNameModel> testgroups = new ArrayList<RestPatientTestNameModel>();
				String patientName = patientdetails.get(0).getPatientId();
	
				try {
	
					List<Object[]> x1 = em.createNamedStoredProcedureQuery("user_patient_test_names")
							.setParameter("username", patientName).getResultList();
					for (Object[] m : x1) {
	
						RestPatientTestNameModel dctrPriscription = new RestPatientTestNameModel(m[0], m[1]);
						testgroups.add(dctrPriscription);
					}
	
				} catch (Exception e) {
					e.printStackTrace();
				}
				//System.out.println("testgroups" + testgroups);
				for (RestPatientTestNameModel m : testgroups) {
					List<DropDownModel> testnames = new ArrayList<DropDownModel>();
					String groupname = m.getTestGroupName();
					try {
	
						List<Object[]> x2 = em.createNamedStoredProcedureQuery("user_patient_test_names_groupby")
								.setParameter("groupname", groupname)
								.setParameter("username", patientName).getResultList();
						for (Object[] n : x2) {
	
							DropDownModel dctrPriscription = new DropDownModel(n[0], n[1],n[2]);
							testnames.add(dctrPriscription);
						}
	
					} catch (Exception e) {
						e.printStackTrace();
					}
					//System.out.println("testgroups" + testnames);
					m.setTestlist(testnames);
				}
				//System.out.println("testgroups" + testgroups);
				patientdetails.get(0).setTestgroup(testgroups);
			}
			
		
		resp.setBody(patientdetails);
		logger.info("Method : getPatientTestDetails ends");

		return resp;
	}
	/******************************   Test Report   Download  ***************************/
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestLabTestReportModel>> getTestTetailssDao(String id,String id2) {
		logger.info("Method : getTestTetailssDao starts");
		List<RestLabTestReportModel> patienttestdetails = new ArrayList<RestLabTestReportModel>();
		System.out.println("id===="+id);
		System.out.println("date===="+id2);
		JsonResponse<List<RestLabTestReportModel>> resp = new JsonResponse<List<RestLabTestReportModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_test_details_download")
					.setParameter("patientid", id).setParameter("testdate", id2).getResultList();

			for (Object[] m : x) {

				Object TODATE = null;
				if (m[6] != null) {
					TODATE = m[6].toString();
				}

				RestLabTestReportModel reqEdit = new RestLabTestReportModel(m[0], m[1], m[2], m[3], m[4], m[5], TODATE,
						m[7]);
				patienttestdetails.add(reqEdit);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
			if (patienttestdetails != null) {
				List<RestPatientTestNameModel> testgroups = new ArrayList<RestPatientTestNameModel>();
				String patientName = patienttestdetails.get(0).getPatientId();
				
				System.out.println(patientName);
				System.out.println(id2);
				
	
				try {
	
					List<Object[]> x1 = em.createNamedStoredProcedureQuery("user_patient_test_names_pdf")
							.setParameter("username", patientName)
							.setParameter("testdate", id2).getResultList();
					for (Object[] m : x1) {
	
						RestPatientTestNameModel dctrPriscription = new RestPatientTestNameModel(m[0], m[1]);
						testgroups.add(dctrPriscription);
					}
					System.out.println("testgroups"+testgroups);
				} catch (Exception e) {
					e.printStackTrace();
				}
				//System.out.println("testgroups" + testgroups);
				for (RestPatientTestNameModel m : testgroups) {
					List<DropDownModel> testnames = new ArrayList<DropDownModel>();
					String groupname = m.getTestGroupName();
					try {
	
						List<Object[]> x2 = em.createNamedStoredProcedureQuery("user_patient_test_names_groupby_pdf")
								.setParameter("groupname", groupname)
								.setParameter("username", patientName)
								.setParameter("testdate", id2).getResultList();
						for (Object[] n : x2) {
	
							DropDownModel dctrPriscription = new DropDownModel(n[0], n[1],n[2],n[3],null);
							testnames.add(dctrPriscription);
						}
	
					} catch (Exception e) {
						e.printStackTrace();
					}
					//System.out.println("testgroups" + testnames);
					m.setTestlist(testnames);
				}
				//System.out.println("testgroups" + testgroups);
				patienttestdetails.get(0).setTestgroup(testgroups);
			}
		resp.setBody(patienttestdetails);
		System.out.println("DaOOOOO================"+resp);
		logger.info("Method : getTestTetailssDao ends");

		return resp;
	}
}
