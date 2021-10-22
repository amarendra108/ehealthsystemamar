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
import nirmalya.aatithya.restmodule.common.utils.GenerateTestReportParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.lab.model.RestTestReportModel;

@Repository
public class RestTestReportDao {

	Logger logger = LoggerFactory.getLogger(RestTestReportDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	// Test Group
	@SuppressWarnings("unchecked")
	public List<DropDownModel> testGroupNameTypeLists() {
		logger.info("Method : testGroupNameTypeLists Dao starts");

		List<DropDownModel> getTestGroupNameLists = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_test_report_groupname").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(null, m[1]);
				getTestGroupNameLists.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : testGroupNameTypeLists Dao ends");

		return getTestGroupNameLists;
	}

	/*
	 * Test Report Details
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestTestReportModel>> getReportTypeDao(String id) {
		logger.info("Method : getReportTypeDao starts");

		List<RestTestReportModel> req = new ArrayList<RestTestReportModel>();
		JsonResponse<List<RestTestReportModel>> resp = new JsonResponse<List<RestTestReportModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_test_report_by_grpName")
					.setParameter("grpId", id).getResultList();
			for (Object[] m : x) {
				RestTestReportModel reqEdit = new RestTestReportModel(null, null, null, null, null, null, null, null,
						m[0], m[1], m[2], m[3], m[4],null,null,null);
				req.add(reqEdit);
				resp.setBody(req);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getReportTypeDao ends");

		return resp;
	}
	// save Test Report

	public JsonResponse<List<RestTestReportModel>> addLabTestReportDao(List<RestTestReportModel> testReport) {
		logger.info("Method : addLabTestReportDao starts");

		JsonResponse<List<RestTestReportModel>> resp = new JsonResponse<List<RestTestReportModel>>();
		List<RestTestReportModel> listData = new ArrayList<RestTestReportModel>();

		try {

			String value = GenerateTestReportParameter.getAddTestReportParam(testReport);
			em.createNamedStoredProcedureQuery("user_test_report_add_report")

					.setParameter("p_testSubQuery", value).execute();

		} catch (Exception e) {
			resp.setCode("Failed");
			resp.setMessage(e.getLocalizedMessage());
			e.printStackTrace();
		}

		resp.setBody(listData);

		logger.info("Method : addLabTestReportDao ends");
		return resp;
	}

	// View Test Report
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestTestReportModel>> viewLabTestReportDao(String id) {
		logger.info("Method : viewLabTestReportDao starts");
		System.out.println("id=========="+id);
		List<RestTestReportModel> patientdetails = new ArrayList<RestTestReportModel>();

		JsonResponse<List<RestTestReportModel>> resp = new JsonResponse<List<RestTestReportModel>>();
		
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_test_view_details")
					.setParameter("labid", id).getResultList();

			for (Object[] m : x) {

				RestTestReportModel reqEdit = new RestTestReportModel(m[0].toString(), m[1], null, null, null,
						null, m[2], null, null, null, null, null, null,m[3],m[4],m[5]);
				patientdetails.add(reqEdit);
				resp.setBody(patientdetails);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("resp=========="+resp);
		logger.info("Method : viewLabTestReportDao ends");

		return resp;
	}
}
