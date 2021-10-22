package nirmalya.aatithya.restmodule.audit.dao;

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

import nirmalya.aatithya.restmodule.audit.model.RestAuditMeetingDocumentModel;

import nirmalya.aatithya.restmodule.audit.model.RestAuditDocumentModel;
import nirmalya.aatithya.restmodule.audit.model.RestAuditLinkCategoryModel;
import nirmalya.aatithya.restmodule.audit.model.RestUpdateDocumentModel;
import nirmalya.aatithya.restmodule.audit.model.RestAuditMeetingRestModel;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModelEmp;
import nirmalya.aatithya.restmodule.common.utils.GenerateAddAuditDetailsParameter;
import nirmalya.aatithya.restmodule.common.utils.GenerateAuditDocumentParameter;
import nirmalya.aatithya.restmodule.common.utils.GenerateAuditParameter;
import nirmalya.aatithya.restmodule.common.utils.GenerateNoticeInitiateParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.notice.model.RestInitiateNoticeDocumentModel;
import nirmalya.aatithya.restmodule.notice.model.RestIntiateNoticeModel;

@Repository
public class RestAuditLinkCategoryDao {

	Logger logger = LoggerFactory.getLogger(RestAuditLinkCategoryDao.class);

	@Autowired
	ServerDao serverDao;

	@Autowired
	EntityManager em;

	/**
	 * DAO - Get Financial year Type For Drop Down
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getFinancialYear() {
		logger.info("Method : getFinancialYear starts");

		List<DropDownModel> financialYear = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("auditLinkRoutines")
					.setParameter("actionType", "getFinancialYear").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				financialYear.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getFinancialYear ends");
		return financialYear;
	}
	/**
	 * DAO - Get Organization Type For Drop Down
	 *
	 */
	/*
	 * @SuppressWarnings("unchecked") public List<DropDownModel> getOrganizationId()
	 * { logger.info("Method : getOrganizationId starts");
	 * 
	 * List<DropDownModel> auditType = new ArrayList<DropDownModel>();
	 * 
	 * try { List<Object[]> x =
	 * em.createNamedStoredProcedureQuery("auditDashboardRoutines")
	 * .setParameter("actionType", "getOrganizationId").setParameter("actionValue",
	 * "").getResultList();
	 * 
	 * for (Object[] m : x) { DropDownModel dropDownModel = new DropDownModel(m[0],
	 * m[1]); auditType.add(dropDownModel); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * logger.info("Method : getOrganizationId ends"); return auditType; }
	 */

	/**
	 * DAO - Get Department Type For Drop Down
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getDeptList() {
		logger.info("Method : getDeptList starts");

		List<DropDownModel> dept = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("auditLinkRoutines")
					.setParameter("actionType", "getDeptList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				dept.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getDeptList ends");

		return dept;

	}

	/**
	 * DAO - Get Department Section Type For Drop Down
	 *
	 */

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getOrganization(String id) {

		logger.info("Method : getOrganization starts");

		List<DropDownModel> organization = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		String value = "SET @p_organization='" + id + "';";
		// System.out.println(value);
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("auditLinkRoutines")
					.setParameter("actionType", "getOrganizationName").setParameter("actionValue", value)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				organization.add(dropDownModel);
			}

			resp.setBody(organization);

		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : getOrganization ends");

		return response;
	}

	/*
	 * for all Region
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getRegion() {
		logger.info("Method : getRegion starts");

		List<DropDownModel> auditType = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("auditLinkRoutines")
					.setParameter("actionType", "getRegion").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				auditType.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getRegion ends");
		return auditType;
	}

	/*
	 * for all Quarter
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getQuarter() {

		logger.info("Method in Dao: getQuarter starts");

		List<DropDownModel> auditInitiateModelList = new ArrayList<DropDownModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("auditLinkRoutines")
					.setParameter("actionType", "getQuarter").setParameter("actionValue", "").getResultList();
			if (!x.isEmpty()) {
				for (Object[] m : x) {

					DropDownModel auditModel = new DropDownModel(m[0], m[1]);
					auditInitiateModelList.add(auditModel);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method in Dao: getQuarter ends");
		return auditInitiateModelList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModelEmp> getempListss() {
		logger.info("Method : getCountryListemployee starts");

		List<DropDownModelEmp> CountryList = new ArrayList<DropDownModelEmp>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("auditLinkRoutines")
					.setParameter("actionType", "getempListss").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModelEmp dropDownModel = new DropDownModelEmp(m[0], m[1], m[2]);
				CountryList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getCountryListForemployee ends");

		System.out.println("email" + CountryList);
		return CountryList;
	}

	/*
	 * for all audit internal- auditor list
	 */
	/*
	 * for all audit initiated
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getInternalAuditor(String region) {

		logger.info("Method in Dao: getInternalAuditor starts");

		List<DropDownModel> auditInitiateModelList = new ArrayList<DropDownModel>();

		String value = "SET @p_region='" + region + "';";
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("auditLinkRoutines")
					.setParameter("actionType", "auditorName").setParameter("actionValue", value).getResultList();
			if (!x.isEmpty()) {
				for (Object[] m : x) {

					DropDownModel auditInitiateModel = new DropDownModel(m[0], m[1]);
					auditInitiateModelList.add(auditInitiateModel);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		resp.setBody(auditInitiateModelList);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);
		logger.info("Method in Dao: getInternalAuditor ends");
		return response;
	}

	/*
	 * for Regional Manager
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getRegionalManager(String id) {

		logger.info("Method in Dao: getRegionalManager starts");

		List<DropDownModel> auditInitiateModelList = new ArrayList<DropDownModel>();

		String value = "SET @p_region='" + id + "';";
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("auditLinkRoutines")
					.setParameter("actionType", "regionalManager").setParameter("actionValue", value).getResultList();
			if (!x.isEmpty()) {
				for (Object[] m : x) {

					DropDownModel auditInitiateModel = new DropDownModel(m[0], m[1]);
					auditInitiateModelList.add(auditInitiateModel);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		resp.setBody(auditInitiateModelList);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);
		logger.info("Method in Dao: getRegionalManager ends");
		return response;
	}

	/*
	 * for Concern Finance
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getConcernFinance(String id) {

		logger.info("Method in Dao: getConcernFinance starts");

		List<DropDownModel> auditInitiateModelList = new ArrayList<DropDownModel>();

		String value = "SET @p_region='" + id + "';";
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("auditLinkRoutines")
					.setParameter("actionType", "getConcernFinance").setParameter("actionValue", value).getResultList();
			if (!x.isEmpty()) {
				for (Object[] m : x) {

					DropDownModel auditInitiateModel = new DropDownModel(m[0], m[1]);
					auditInitiateModelList.add(auditInitiateModel);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		resp.setBody(auditInitiateModelList);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);
		logger.info("Method in Dao: getConcernFinance ends");
		return response;
	}

	/**
	 * DAO - Get Department Section Type For Drop Down
	 *
	 */

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getSectionListByDept(String id) {

		logger.info("Method : getSectionListByDept starts");

		List<DropDownModel> dept = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		String value = "SET @p_dept='" + id + "';";

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("auditLinkRoutines")
					.setParameter("actionType", "getSectionList").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				dept.add(dropDownModel);
			}

			resp.setBody(dept);

		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : getSectionListByDept ends");

		return response;
	}

	/**
	 * for DeptHeadBySection list
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getDeptHeadBySection(String id) {

		logger.info("Method : getDeptHeadBySection starts");
		List<DropDownModel> DeptHeadList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();

		String value = "SET @p_section= \"(" + id + ")\";";
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("auditLinkRoutines")
					.setParameter("actionType", "getDeptHeadBySection").setParameter("actionValue", value)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				DeptHeadList.add(dropDownModel);
			}

			resp.setBody(DeptHeadList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				resp, HttpStatus.CREATED);
		System.out.println(response);
		logger.info("Method : getDeptHeadBySection ends");
		return response;
	}

	/*
	 * DAO Function to Add notice details
	 *
	 */

	@SuppressWarnings({ "unused" })
	public ResponseEntity<JsonResponse<List<RestAuditLinkCategoryModel>>> addAuditDao(
			List<RestAuditLinkCategoryModel> auditModel) {
		System.out.println(" initiateNoticeModel " + auditModel);
		logger.info("Method : restaddNoticeDocumentDao starts");
		boolean validity = true;
		RestAuditLinkCategoryModel req = new RestAuditLinkCategoryModel();
		JsonResponse<List<RestAuditLinkCategoryModel>> resp = new JsonResponse<List<RestAuditLinkCategoryModel>>();

		List<RestAuditLinkCategoryModel> listData = new ArrayList<RestAuditLinkCategoryModel>();
		List<RestAuditLinkCategoryModel> docList = new ArrayList<RestAuditLinkCategoryModel>();
		resp.setMessage("");
		resp.setCode("");
		String errorCode = "Field Validation Error";

		/*
		 * for (RestAuditLinkCategoryModel l : auditModel) {
		 * 
		 * }
		 */

		if (validity) {
			try {
				int i = 0;
				for (RestAuditLinkCategoryModel l : auditModel) {
					i++;
					String value = GenerateAddAuditDetailsParameter.getAuditManageParam(l, i);
					if (auditModel.get(0).getAuditInitiate() != null && auditModel.get(0).getAuditInitiate() != "") {
						/*
						 * @SuppressWarnings("unchecked") List<Object[]> x =
						 * em.createNamedStoredProcedureQuery("auditLinkRoutines")
						 * .setParameter("actionType", "modifyAudit").setParameter("actionValue", value)
						 * .getResultList();
						 */
						em.createNamedStoredProcedureQuery("auditLinkRoutines")
								.setParameter("actionType", "modifyAudit").setParameter("actionValue", value).execute();
						resp.setMessage("Success");
					} else {
						/*
						 * @SuppressWarnings("unchecked") List<Object[]> x =
						 * em.createNamedStoredProcedureQuery("auditLinkRoutines")
						 * .setParameter("actionType",
						 * "addAuditwithoutDept").setParameter("actionValue", value) .getResultList();
						 */
						em.createNamedStoredProcedureQuery("auditLinkRoutines")
								.setParameter("actionType", "addAuditwithoutDept").setParameter("actionValue", value)
								.execute();
						resp.setMessage("Success");
					}
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
		}
		resp.setBody(listData);
		ResponseEntity<JsonResponse<List<RestAuditLinkCategoryModel>>> response = new ResponseEntity<JsonResponse<List<RestAuditLinkCategoryModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : restaddNoticeDocumentDao ends");
		return response;

	}

// View Audit Details
	@SuppressWarnings("unchecked")
	public List<RestAuditLinkCategoryModel> viewAuditDtlsDao(String id) {
		logger.info("Method : viewAuditDtlsDao starts");
		List<RestAuditLinkCategoryModel> auditList = new ArrayList<RestAuditLinkCategoryModel>();
		try {
			String value = "SET @p_auditType='" + id + "'";

			List<Object[]> x = em.createNamedStoredProcedureQuery("auditLinkRoutines")
					.setParameter("actionType", "viewAuditDtls").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				Object initiateDate = null;
				if (m[3] != null) {
					initiateDate = DateFormatter.returnStringDate(m[3]);
				}
				Object status = "";
				if (m[4].equals("0")) {
					status = "Open";
				} else if (m[4].equals("1")) {
					status = "Draft";
				} else {
					status = "Final";
				}

				RestAuditLinkCategoryModel auditModel = new RestAuditLinkCategoryModel(m[0], m[1], m[2], null,
						initiateDate, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, status);
				auditList.add(auditModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : viewAuditDtlsDao ends");
		// System.out.println("Qual Dao=======" + qualList);
		return auditList;
	}
	/*
	 * delete Audit Details
	 */

	public JsonResponse<RestAuditLinkCategoryModel> deleteAuditDetailsDao(String id) {
		logger.info("Method : deleteAuditDetailsDao starts");

		RestAuditLinkCategoryModel req = new RestAuditLinkCategoryModel();
		JsonResponse<RestAuditLinkCategoryModel> resp = new JsonResponse<RestAuditLinkCategoryModel>();

		try {
			String value = "SET @p_auditInitiate='" + id + "';";
			em.createNamedStoredProcedureQuery("auditLinkRoutines").setParameter("actionType", "deleteAuditDetails")
					.setParameter("actionValue", value).execute();

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : deleteAuditDetailsDao ends");

		return resp;
	}

	/*
	 * Edit Audit Details
	 */
	@SuppressWarnings("unchecked")
	public List<RestAuditLinkCategoryModel> editAuditDao(String id) {
		logger.info("Method : editAudit starts");
		List<RestAuditLinkCategoryModel> getAuditDetails = new ArrayList<RestAuditLinkCategoryModel>();
		String value = "SET @p_auditInitiate='" + id + "';";

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("auditLinkRoutines")
					.setParameter("actionType", "editAuditDetails").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {

				Object initiateDate = null;
				if (m[4] != null) {
					initiateDate = DateFormatter.returnStringDate(m[4]);
				}
				Object startDate = null;
				if (m[14] != null) {
					startDate = DateFormatter.returnStringDate(m[14]);
				}
				Object endDate = null;
				if (m[15] != null) {
					endDate = DateFormatter.returnStringDate(m[15]);
				}
				RestAuditLinkCategoryModel auditModel = new RestAuditLinkCategoryModel(m[0], m[1], m[2], m[3],
						initiateDate, m[5], m[6], m[7], null, null, m[8], m[9], m[10], null, m[11], m[12], m[13],
						startDate, endDate, null, null);

				getAuditDetails.add(auditModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (getAuditDetails != null) {
			List<RestUpdateDocumentModel> docList = new ArrayList<RestUpdateDocumentModel>();
			try {
				String subValues = "SET @p_auditInitiate='" + getAuditDetails.get(0).getAuditInitiate() + "';";
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("auditLinkRoutines")
						.setParameter("actionType", "editAuditDocument").setParameter("actionValue", subValues)
						.getResultList();
				for (Object[] m : x1) {

					RestUpdateDocumentModel dropDownModel = new RestUpdateDocumentModel(m[0], m[1], m[2], m[3]);

					docList.add(dropDownModel);
				}
			} catch (Exception e) {

			}
			getAuditDetails.get(0).setDocumentList(docList);

		}

		logger.info("Method : editAudit ends");
		return getAuditDetails;
	}

	/**
	 * Edit for section list
	 * 
	 */

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getSectionNameDao(String id) {

		logger.info("Method : getSectionNameDao starts");

		List<DropDownModel> sectionList = new ArrayList<DropDownModel>();

		try {
			String value = "SET @p_dept='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("auditLinkRoutines")
					.setParameter("actionType", "getSectionforEdit").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				sectionList.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getSectionNameDao ends");

		return sectionList;
	}

	/*************************************
	 * Document Upload Section
	 ***********************************************/
	/*
	 * get audit Folder list
	 * 
	 */

	@SuppressWarnings("unchecked")
	public List<DropDownModel> auditFolder() {
		logger.info("Method : auditFolder Dao starts");

		List<DropDownModel> auditFolder = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("auditDocumentRoutines")
					.setParameter("actionType", "getAuditFolder").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				auditFolder.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : auditFolder Dao ends");

		return auditFolder;
	}
	/*
	 * DAO Function to Add Audit Doc
	 *
	 */

	@SuppressWarnings({ "unused" })
	public ResponseEntity<JsonResponse<List<RestAuditDocumentModel>>> restAddDocumentDao(
			List<RestAuditDocumentModel> auditDocModel) {
		System.out.println(" initiateNoticeModel " + auditDocModel);
		logger.info("Method : restaddNoticeDocumentDao starts");
		boolean validation = true;
		RestAuditDocumentModel req = new RestAuditDocumentModel();
		JsonResponse<List<RestAuditDocumentModel>> resp = new JsonResponse<List<RestAuditDocumentModel>>();

		List<RestAuditDocumentModel> listData = new ArrayList<RestAuditDocumentModel>();
		List<RestAuditDocumentModel> docList = new ArrayList<RestAuditDocumentModel>();
		resp.setMessage("");
		resp.setCode("");
		String errorCode = "Field Validation Error";

		try {
			String value = GenerateAuditDocumentParameter.getAuditManageParam(auditDocModel);
			if (auditDocModel.get(0).getAuditUploadId() != null && auditDocModel.get(0).getAuditUploadId() != "") {
				em.createNamedStoredProcedureQuery("auditDocumentRoutines").setParameter("actionType", "modifyAuditDoc")
						.setParameter("actionValue", value).execute();

			} else {
				em.createNamedStoredProcedureQuery("auditDocumentRoutines")
						.setParameter("actionType", "addAuditDocument").setParameter("actionValue", value).execute();

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

		resp.setBody(listData);
		ResponseEntity<JsonResponse<List<RestAuditDocumentModel>>> response = new ResponseEntity<JsonResponse<List<RestAuditDocumentModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : restaddNoticeDocumentDao ends");
		return response;

	}

	// View Audit Details
	@SuppressWarnings("unchecked")
	public List<RestAuditDocumentModel> viewAuditDocDao(String id) {
		logger.info("Method : viewAuditDtlsDao starts");
		List<RestAuditDocumentModel> auditDocList = new ArrayList<RestAuditDocumentModel>();
		try {
			String value = "SET @p_auditType='" + id + "'";
			List<Object[]> x = em.createNamedStoredProcedureQuery("auditDocumentRoutines")
					.setParameter("actionType", "viewAuditDoc").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {

				RestAuditDocumentModel auditModel = new RestAuditDocumentModel(m[0], m[1], m[2], null, m[3]);
				auditDocList.add(auditModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : viewAuditDtlsDao ends");

		return auditDocList;
	}

	/*
	 * Edit Audit Doc
	 */
	@SuppressWarnings("unchecked")
	public List<RestAuditDocumentModel> editAuditDocDao(String id) {
		logger.info("Method : editAudit starts");
		List<RestAuditDocumentModel> auditDocList = new ArrayList<RestAuditDocumentModel>();
		String value = "SET @p_auditUploadId='" + id + "';";

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("auditDocumentRoutines")
					.setParameter("actionType", "editAuditDoc").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {

				RestAuditDocumentModel auditModel = new RestAuditDocumentModel(m[0], m[1], m[2], m[3], m[4]);
				auditDocList.add(auditModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (auditDocList != null) {
			List<RestUpdateDocumentModel> docList = new ArrayList<RestUpdateDocumentModel>();
			try {
				String subValues = "SET @p_auditUploadId='" + auditDocList.get(0).getAuditUploadId() + "';";
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("auditDocumentRoutines")
						.setParameter("actionType", "editDocument").setParameter("actionValue", subValues)
						.getResultList();
				for (Object[] m : x1) {

					RestUpdateDocumentModel dropDownModel = new RestUpdateDocumentModel(m[0], m[1], m[2], m[3]);

					docList.add(dropDownModel);
				}
			} catch (Exception e) {

			}
			auditDocList.get(0).setDocumentList(docList);

		}

		logger.info("Method : editAuditDoc Dao ends");
		return auditDocList;
	}
	/*
	 * delete Audit Doc
	 */

	public JsonResponse<RestAuditDocumentModel> deleteAuditDocDao(String id) {
		logger.info("Method : deleteAuditDocDao starts");

		RestAuditDocumentModel req = new RestAuditDocumentModel();
		JsonResponse<RestAuditDocumentModel> resp = new JsonResponse<RestAuditDocumentModel>();

		try {
			String value = "SET @p_auditUploadId='" + id + "';";
			em.createNamedStoredProcedureQuery("auditDocumentRoutines").setParameter("actionType", "deleteAuditDoc")
					.setParameter("actionValue", value).execute();

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : deleteAuditDocDao ends");

		return resp;
	}

	/********************** Meeting Section *************************/
	@SuppressWarnings("unchecked")
	public List<DropDownModelEmp> getempList() {
		logger.info("Method : getCountryListemployee starts");

		List<DropDownModelEmp> CountryList = new ArrayList<DropDownModelEmp>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("auditmeetingRoutines")
					.setParameter("actionType", "getempList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModelEmp dropDownModel = new DropDownModelEmp(m[0], m[1], m[2]);
				CountryList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getCountryListForemployee ends");

		System.out.println("email" + CountryList);
		return CountryList;
	}

	/********************** Meeting Section *************************/

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getdeptList() {
		logger.info("Method : getstateListemployee starts");

		List<DropDownModel> stateList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("auditmeetingRoutines")
					.setParameter("actionType", "getdeptList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				stateList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getstateListForemployee ends");
		return stateList;
	}

	@SuppressWarnings({ "unused", "unchecked" })
	public ResponseEntity<JsonResponse<List<RestAuditMeetingRestModel>>> saveauditMaster(
			List<RestAuditMeetingRestModel> initiateNoticeModel) {

		logger.info("Method : restaddNoticeDao starts");
		boolean validation = true;
		RestAuditMeetingRestModel req = new RestAuditMeetingRestModel();
		req = initiateNoticeModel.get(0);
		JsonResponse<List<RestAuditMeetingRestModel>> resp = new JsonResponse<List<RestAuditMeetingRestModel>>();

		List<RestAuditMeetingRestModel> listData = new ArrayList<RestAuditMeetingRestModel>();
		List<RestAuditMeetingDocumentModel> docList = new ArrayList<RestAuditMeetingDocumentModel>();
		resp.setMessage("");
		resp.setCode("");
		String errorCode = "Field Validation Error";
		for (RestAuditMeetingRestModel l : initiateNoticeModel) {

			if (l.getDate() == null || l.getDate() == "") {
				validation = false;
				resp.setCode(errorCode);
				resp.setMessage("Please Enter ");
				break;
			} else if (l.getStartTime() == null || l.getStartTime() == "") {
				validation = false;
				resp.setCode(errorCode);
				resp.setMessage("Please Enter .");
				break;
			} else if (l.getEndTime() == null || l.getEndTime() == "") {
				validation = false;
				resp.setCode(errorCode);
				resp.setMessage("Please Enter .");
				break;
			} else if (l.getSubject() == null || l.getSubject() == "") {
				validation = false;
				resp.setCode(errorCode);
				resp.setMessage("Please  Due Date.");
				break;
			} else if (l.getDepartmentName() == null || l.getDepartmentName() == "") {
				validation = false;
				resp.setCode(errorCode);
				resp.setMessage("Please  Comment");
				break;
			} else if (l.getCommentck() == null || l.getCommentck() == "") {
				validation = false;
				resp.setCode(errorCode);
				resp.setMessage("pleaseFrom Field.");
				break;
			}
		}

		if (validation) {
			try {

				System.out.println("modify");
				String value = GenerateAuditParameter.getAuditParam(req);
				if (initiateNoticeModel.get(0).getMeetingId() != null
						&& initiateNoticeModel.get(0).getMeetingId() != "") {
					em.createNamedStoredProcedureQuery("auditmeetingRoutines")
							.setParameter("actionType", "modifynitiateNotice").setParameter("actionValue", value)
							.getResultList();
					// resp.setMessage("Success");

					System.out.println("getResultList");

				} else {
					System.out.println("add");
					em.createNamedStoredProcedureQuery("auditmeetingRoutines").setParameter("actionType", "addmeeting")
							.setParameter("actionValue", value).execute();
					// resp.setMessage("Success");

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
		}

		resp.setBody(listData);
		ResponseEntity<JsonResponse<List<RestAuditMeetingRestModel>>> response = new ResponseEntity<JsonResponse<List<RestAuditMeetingRestModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : restaddNoticeDao ends");
		return response;

	}

	/*
	 * View Meeting Details
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestAuditMeetingRestModel>> viewmeeting() {

		logger.info("Method in Dao: viewNoticeDtlsDao starts");

		List<RestAuditMeetingRestModel> noticeList = new ArrayList<RestAuditMeetingRestModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("auditmeetingRoutines")
					.setParameter("actionType", "viewmeetingdetails").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {

				RestAuditMeetingRestModel noticeModel = new RestAuditMeetingRestModel(m[0], m[1], m[2], m[3], m[4],
						null, m[5], null, m[6], m[7], null, null);

				noticeList.add(noticeModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<RestAuditMeetingRestModel>> resp = new JsonResponse<List<RestAuditMeetingRestModel>>();
		resp.setBody(noticeList);

		logger.info("Method in Dao: viewNoticeDtlsDao ends");
		return resp;

	}

	@SuppressWarnings("unchecked")
	public List<RestAuditMeetingRestModel> editNoticeinitiate(String id) {
		logger.info("Method : editNoticeinitiate starts");
		List<RestAuditMeetingRestModel> getRequisitionTypeList = new ArrayList<RestAuditMeetingRestModel>();
		String value = "SET @p_mettingId='" + id + "';";

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("auditmeetingRoutines")
					.setParameter("actionType", "editInitiateNotice").setParameter("actionValue", value)
					.getResultList();

			for (Object[] m : x) {
				Object initDate = null;
				if (m[1] != null) {
					initDate = m[1].toString();

				}
				/*
				 * meeting_id, meeting_date, meeting_fromtime, meeting_totime, meeting_subject,
				 * meeting_deparment, meeting_particpate, meeting_commentck
				 */
				RestAuditMeetingRestModel reqnotice = new RestAuditMeetingRestModel(m[0], initDate, m[2], m[3], m[4],
						m[5], null, m[6], null, m[7], null, null);
				/*
				 * Object meetingId, Object date, Object startTime, Object endTime, Object
				 * subject, Object departmentid,Object departmentName,Object participantid,
				 * Object participantName, Object commentck,Object createdBy,Object updatedBy
				 */
				getRequisitionTypeList.add(reqnotice);
			}
			System.out.println(getRequisitionTypeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (getRequisitionTypeList != null) {
			List<RestAuditMeetingDocumentModel> docList = new ArrayList<RestAuditMeetingDocumentModel>();
			try {
				String subValues = "SET @p_mettingId='" + getRequisitionTypeList.get(0).getMeetingId() + "';";
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("auditmeetingRoutines")
						.setParameter("actionType", "getNoticeDocs").setParameter("actionValue", subValues)
						.getResultList();
				for (Object[] m : x1) {

					RestAuditMeetingDocumentModel dropDownModel = new RestAuditMeetingDocumentModel(m[0], m[1], m[2]);

					docList.add(dropDownModel);
				}
			} catch (Exception e) {

			}
			getRequisitionTypeList.get(0).setDocumentList(docList);
			System.out.println(docList);
		}
		System.out.println("editNoticeinitiate" + getRequisitionTypeList);
		logger.info("Method : editNoticeinitiate ends");
		return getRequisitionTypeList;
	}

	public ResponseEntity<JsonResponse<Object>> deleteuditMeetingDetails(String id) {
		logger.info("Method : deletedependent starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String values = "SET @p_id='(" + id + ")'";
			em.createNamedStoredProcedureQuery("auditmeetingRoutines").setParameter("actionType", "deletemeetiug")
					.setParameter("actionValue", values).execute();

		} catch

		(Exception e) {
			e.printStackTrace();

		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		System.out.println(response);
		logger.info("Method : deletedependent ends");
		return response;
	}

	/*
	 * add draft details
	 */
	public ResponseEntity<JsonResponse<List<RestAuditMeetingRestModel>>> restAddDraftsDao(
			List<RestAuditMeetingRestModel> initiateNoticeModel) {

		logger.info("Method : restAddDraftsDao starts");

		JsonResponse<List<RestAuditMeetingRestModel>> resp = new JsonResponse<List<RestAuditMeetingRestModel>>();
		List<RestAuditMeetingRestModel> listData = new ArrayList<RestAuditMeetingRestModel>();

		try {
			String values = GenerateAuditParameter.getSaveDraftParam(initiateNoticeModel);
			if (initiateNoticeModel.get(0).getDraftNo() == null || initiateNoticeModel.get(0).getDraftNo() == "") {
				em.createNamedStoredProcedureQuery("auditmeetingRoutines").setParameter("actionType", "addNoticeDraft")
						.setParameter("actionValue", values).execute();

			} else {
				em.createNamedStoredProcedureQuery("initiateNotice").setParameter("actionType", "modifyExitManagement")
						.setParameter("actionValue", values).execute();

			}
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
		resp.setBody(listData);
		ResponseEntity<JsonResponse<List<RestAuditMeetingRestModel>>> response = new ResponseEntity<JsonResponse<List<RestAuditMeetingRestModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : restAddDraftsDao ends");
		return response;
	}
	/*
	 * DAO Function to Add Send details
	 *
	 */

	public ResponseEntity<JsonResponse<List<RestAuditMeetingRestModel>>> restAddSendDetails(
			List<RestAuditMeetingRestModel> initiateNoticeModel) {

		logger.info("Method : restAddSendDetails starts");
		JsonResponse<List<RestAuditMeetingRestModel>> resp = new JsonResponse<List<RestAuditMeetingRestModel>>();

		List<RestAuditMeetingRestModel> listData = new ArrayList<RestAuditMeetingRestModel>();

		try {
			String value = GenerateAuditParameter.getSaveMailParam(initiateNoticeModel);
			if (initiateNoticeModel.get(0).getSendNo() != null && initiateNoticeModel.get(0).getSendNo() != "") {
				em.createNamedStoredProcedureQuery("initiateNotice").setParameter("actionType", "modifySendDetsils")
						.setParameter("actionValue", value).execute();

			} else {
				em.createNamedStoredProcedureQuery("auditmeetingRoutines").setParameter("actionType", "addSendDetails")
						.setParameter("actionValue", value).execute();

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

		resp.setBody(listData);
		ResponseEntity<JsonResponse<List<RestAuditMeetingRestModel>>> response = new ResponseEntity<JsonResponse<List<RestAuditMeetingRestModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : restAddSendDetails ends");
		return response;

	}

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestAuditMeetingRestModel>> viewDraftDao(String meetingId, String createdBy) {

		logger.info("Method in Dao: viewDraftDao starts");

		String value = "SET @p_mettingId='" + meetingId + "',@p_createdby='" + createdBy + "';";
		List<RestAuditMeetingRestModel> noticeList = new ArrayList<RestAuditMeetingRestModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("auditmeetingRoutines")
					.setParameter("actionType", "viewDraftDetails").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {

				RestAuditMeetingRestModel noticeModel = new RestAuditMeetingRestModel(m[0], m[1], m[2], m[3], null,
						null, null, null, null, null, null, null);

				noticeList.add(noticeModel);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<RestAuditMeetingRestModel>> resp = new JsonResponse<List<RestAuditMeetingRestModel>>();
		resp.setBody(noticeList);

		logger.info("Method in Dao: viewDraftDao ends");
		return resp;
	}

	@SuppressWarnings("unchecked")
	public List<RestAuditMeetingRestModel> editDraftDetailsDao(String id) {
		logger.info("Method : editDraftDetailsDao starts");
		System.out.println(id);
		List<RestAuditMeetingRestModel> getRequisitionTypeList = new ArrayList<RestAuditMeetingRestModel>();

		String value = "SET @p_draftId='" + id + "';";

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("auditmeetingRoutines")
					.setParameter("actionType", "editDraftDetails").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {

				RestAuditMeetingRestModel reqnotice = new RestAuditMeetingRestModel(m[0], m[1], m[2], m[3], m[4], m[5],
						m[6], null, null, m[7]);
				getRequisitionTypeList.add(reqnotice);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (getRequisitionTypeList != null) {
			List<RestAuditMeetingDocumentModel> docList = new ArrayList<RestAuditMeetingDocumentModel>();
			try {
				String subValues = "SET @p_mettingId='" + getRequisitionTypeList.get(0).getMeetingId() + "';";
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("auditmeetingRoutines")
						.setParameter("actionType", "getDraftDocs").setParameter("actionValue", subValues)
						.getResultList();
				for (Object[] m : x1) {

					RestAuditMeetingDocumentModel dropDownModel = new RestAuditMeetingDocumentModel(m[0], m[1], m[2],
							m[3]);
					docList.add(dropDownModel);
				}
				System.out.println("Draft document@" + docList);
			} catch (Exception e) {

			}
			getRequisitionTypeList.get(0).setDocumentList(docList);

		}

		logger.info("Method : editDraftDetailsDao ends");
		return getRequisitionTypeList;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> getmaildetails(String mylist) {
		logger.info("Method : getmaildetails starts");
		System.out.println("email++mylist");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		// int i= mylist.size();

		// List<DropDownModel> emaillist = new ArrayList<DropDownModel>();
		// for (int i=0 ;i<mylist.size();i++ ) {
		try {
			// int sblen=mylist.length();
			// String value1[]=mylist.split("[");
			// String value1 = mylist.substring(1, sblen-1);
			// System.out.println("value1 "+value1);
			// String value2[]=value1.split("]");
			// String value2=value1.replaceFirst("'", "\"");
			// System.out.println("val2 "+value2);

			// select distinct(TEM_Employee),TEM_Emp_Work_Email from tbl_employee_mstr WHERE
			// TEM_Employee IN("EMPL0001","EMPL0003");

			// String value = "SET @p_empId='(" + value1 + ")';";
			// String value = "SET @p_empId='(" + value1 + ")'";

			String value = "SET @p_mettingId='" + mylist + "';";
			System.out.println(value);

			List<Object[]> x = em.createNamedStoredProcedureQuery("auditmeetingRoutines")
					.setParameter("actionType", "getEmailList").setParameter("actionValue", value).getResultList();
			// System.err.println(x);
			for (Object[] m : x) {

				// System.out.println(Arrays.toString(m));
				// DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				// resp.setBody(dropDownModel);
				System.out.println("dao rohan" + m[1] + mylist);
				resp.setBody(m[1]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// }

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		System.out.println(response.getBody());

		logger.info("Method : getmaildetails ends");
		return response;
	}

	/*
	 * View document details
	 * 
	 */
	/*
	 * View All details
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<RestAuditMeetingRestModel> viewDocumentsAudit(String id) {
		logger.info("Method : viewDocumentsAudit starts");

		List<RestAuditMeetingRestModel> getRequisitionTypeList = new ArrayList<RestAuditMeetingRestModel>();
		System.out.println(getRequisitionTypeList);
		String value = "SET @p_mettingId='" + id + "';";
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("auditmeetingRoutines")
					.setParameter("actionType", "viewPageAudit").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				Object initDate = null;
				if (m[1] != null) {
					initDate = m[1].toString();

				}

				RestAuditMeetingRestModel reqnotice = new RestAuditMeetingRestModel(m[0], initDate, m[2], m[3], m[4],
						m[5], null, null, null, m[6], null, null);

				getRequisitionTypeList.add(reqnotice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// String values = "SET @p_mettingId='" + id + "';";

		if (getRequisitionTypeList != null) {
			List<RestAuditMeetingDocumentModel> docList = new ArrayList<RestAuditMeetingDocumentModel>();
			try {
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("auditmeetingRoutines")
						.setParameter("actionType", "viewDocumentDetailss").setParameter("actionValue", value)
						.getResultList();
				for (Object[] m : x1) {

					RestAuditMeetingDocumentModel dropDownModel = new RestAuditMeetingDocumentModel(m[0], m[1], m[2]);
					docList.add(dropDownModel);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			getRequisitionTypeList.get(0).setDocumentList(docList);
		}

		System.out.print("getNoticeTypeTrailList@@");
		System.out.print(getRequisitionTypeList);
		System.out.println("friday+++++docList" + getRequisitionTypeList);
		logger.info("Method : viewDocumentsDao ends");
		return getRequisitionTypeList;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> getmaildetailsAudit(String mylist) {
		logger.info("Method : getmaildetailsAudit starts");
		System.out.println("email+" + mylist);
		JsonResponse<Object> resp = new JsonResponse<Object>();

		// int i= mylist.size();

		// List<DropDownModel> emaillist = new ArrayList<DropDownModel>();
		// for (int i=0 ;i<mylist.size();i++ ) {
		try {
			// int sblen=mylist.length();
			// String value1[]=mylist.split("[");
			// String value1 = mylist.substring(1, sblen-1);
			// System.out.println("value1 "+value1);
			// String value2[]=value1.split("]");
			// String value2=value1.replaceFirst("'", "\"");
			// System.out.println("val2 "+value2);

			// select distinct(TEM_Employee),TEM_Emp_Work_Email from tbl_employee_mstr WHERE
			// TEM_Employee IN("EMPL0001","EMPL0003");

			// String value = "SET @p_empId='(" + value1 + ")';";
			// String value = "SET @p_empId='(" + value1 + ")'";

			String value = "SET @p_auditInitiate='" + mylist + "';";
			System.out.println(value);

			List<Object[]> x = em.createNamedStoredProcedureQuery("auditLinkRoutines")
					.setParameter("actionType", "getEmailListAudit").setParameter("actionValue", value).getResultList();
			System.out.println("abc " + x);
			for (Object[] m : x) {

				// System.out.println(Arrays.toString(m));
				// DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				// resp.setBody(dropDownModel);
				System.out.println("dao rohan" + m[1]);
				resp.setBody(m[1]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// }

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		System.out.println(response.getBody());

		logger.info("Method : getmaildetailsAudit ends");
		return response;
	}
	// DAO Function to Add Send details

	/*
	 * public ResponseEntity<JsonResponse<List<RestAuditLinkCategoryModel>>>
	 * restAddSendDetailsMail( List<RestAuditLinkCategoryModel> initiateNoticeModel)
	 * {
	 * 
	 * logger.info("Method : restAddSendDetails starts");
	 * JsonResponse<List<RestAuditLinkCategoryModel>> resp = new
	 * JsonResponse<List<RestAuditLinkCategoryModel>>();
	 * 
	 * List<RestAuditLinkCategoryModel> listData = new
	 * ArrayList<RestAuditLinkCategoryModel>();
	 * 
	 * try { String value =
	 * GenerateAddAuditDetailsParameter.getSaveMailParams(initiateNoticeModel); if
	 * (initiateNoticeModel.get(0).getManageAuditNo() != null &&
	 * initiateNoticeModel.get(0).getManageAuditNo() != "") {
	 * em.createNamedStoredProcedureQuery("auditLinkRoutines").setParameter(
	 * "actionType", "modifySendDetsils") .setParameter("actionValue",
	 * value).execute();
	 * 
	 * } else {
	 * em.createNamedStoredProcedureQuery("auditLinkRoutines").setParameter(
	 * "actionType", "addSendDetailsMail") .setParameter("actionValue",
	 * value).execute();
	 * 
	 * } } catch (Exception e) { try { String[] err =
	 * serverDao.errorProcedureCall(e); resp.setCode(err[0]);
	 * resp.setMessage(err[1]); } catch (Exception e1) { e1.printStackTrace(); }
	 * e.printStackTrace(); }
	 * 
	 * resp.setBody(listData);
	 * ResponseEntity<JsonResponse<List<RestAuditLinkCategoryModel>>> response = new
	 * ResponseEntity<JsonResponse<List<RestAuditLinkCategoryModel>>>( resp,
	 * HttpStatus.CREATED); logger.info("Method : restAddSendDetails ends"); return
	 * response;
	 * 
	 * }
	 */

	/*
	 * DAO Function to Add Send details
	 *
	 */

	public ResponseEntity<JsonResponse<List<RestAuditLinkCategoryModel>>> restAddSendDetailsMails(
			List<RestAuditLinkCategoryModel> initiateNoticeModel) {

		logger.info("Method : restAddSendDetails starts");
		JsonResponse<List<RestAuditLinkCategoryModel>> resp = new JsonResponse<List<RestAuditLinkCategoryModel>>();

		List<RestAuditLinkCategoryModel> listData = new ArrayList<RestAuditLinkCategoryModel>();

		try {
			String value = GenerateAddAuditDetailsParameter.getSaveMailParams(initiateNoticeModel);
			if (initiateNoticeModel.get(0).getManageAuditNo() != null
					&& initiateNoticeModel.get(0).getManageAuditNo() != "") {
				em.createNamedStoredProcedureQuery("auditLinkRoutines").setParameter("actionType", "modifySendDetails")
						.setParameter("actionValue", value).execute();

			} else {
				em.createNamedStoredProcedureQuery("auditLinkRoutines").setParameter("actionType", "addSendDetailsMail")
						.setParameter("actionValue", value).execute();

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

		resp.setBody(listData);
		ResponseEntity<JsonResponse<List<RestAuditLinkCategoryModel>>> response = new ResponseEntity<JsonResponse<List<RestAuditLinkCategoryModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : restAddSendDetails ends");
		return response;

	}

	/*
	 * add draft details
	 */
	public ResponseEntity<JsonResponse<List<RestAuditLinkCategoryModel>>> restAddDraftsCommensement(
			List<RestAuditLinkCategoryModel> initiateNoticeModel) {

		logger.info("Method : restAddDraftsCommensement starts");

		JsonResponse<List<RestAuditLinkCategoryModel>> resp = new JsonResponse<List<RestAuditLinkCategoryModel>>();
		List<RestAuditLinkCategoryModel> listData = new ArrayList<RestAuditLinkCategoryModel>();

		try {
			String values = GenerateAddAuditDetailsParameter.getSaveDraftParamCommense(initiateNoticeModel);
			if (initiateNoticeModel.get(0).getDraftNo() == null || initiateNoticeModel.get(0).getDraftNo() == "") {
				em.createNamedStoredProcedureQuery("auditLinkRoutines")
						.setParameter("actionType", "addAuditCommenseDraft").setParameter("actionValue", values)
						.execute();

			} else {
				em.createNamedStoredProcedureQuery("auditLinkRoutines")
						.setParameter("actionType", "modifyExitManagement").setParameter("actionValue", values)
						.execute();

			}
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
		resp.setBody(listData);
		ResponseEntity<JsonResponse<List<RestAuditLinkCategoryModel>>> response = new ResponseEntity<JsonResponse<List<RestAuditLinkCategoryModel>>>(
				resp, HttpStatus.CREATED);
		System.out.println("restAddDraftsCommensement"+response);
		logger.info("Method : restAddDraftsCommensement ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestAuditLinkCategoryModel>> viewDraftDaoCommense(String auditInitiate, String createdBy) {

		logger.info("Method in Dao: viewDraftDao starts");

		String value = "SET @p_auditInitiate='" + auditInitiate + "',@p_createdby='" + createdBy + "'";
		List<RestAuditLinkCategoryModel> noticeList = new ArrayList<RestAuditLinkCategoryModel>();
		System.out.println("noticeList" + noticeList);
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("auditLinkRoutines")
					.setParameter("actionType", "viewDrafts").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {

				/*
				 * Object draftDate = null; if (m[4] != null) { draftDate =
				 * DateFormatter.returnStringDate(m[4]); }
				 */

				/*
				 * Object draftNo, Object noticeId, Object draftSubject, Object draftComment,
				 * Object draftDate, Object draftpersonTo, Object draftpersonCc
				 */
				RestAuditLinkCategoryModel noticeModel = new RestAuditLinkCategoryModel(m[0], m[1], null, null, m[2],
						m[3],null);

				noticeList.add(noticeModel);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<RestAuditLinkCategoryModel>> resp = new JsonResponse<List<RestAuditLinkCategoryModel>>();
		resp.setBody(noticeList);
		System.out.println("resp" + resp);
		logger.info("Method in Dao: viewDraftDao ends");
		return resp;
	}
	/*
	 * View Notice Details
	 */

	@SuppressWarnings("unchecked")
	public List<RestAuditLinkCategoryModel> viewAuditInitDao() {
		logger.info("Method : viewAuditInitDao starts");
		List<RestAuditLinkCategoryModel> auditList = new ArrayList<RestAuditLinkCategoryModel>();
		try {
			//String value = "SET @p_auditType='" + id + "'";

			List<Object[]> x = em.createNamedStoredProcedureQuery("auditLinkRoutines")
					.setParameter("actionType", "viewAudit").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				Object initiateDate = null;
				if (m[3] != null) {
					initiateDate = DateFormatter.returnStringDate(m[3]);
				}
				Object status = "";
				if (m[4].equals("0")) {
					status = "Open";
				} else if (m[4].equals("1")) {
					status = "Draft";
				} else {
					status = "Final";
				}

				RestAuditLinkCategoryModel auditModel = new RestAuditLinkCategoryModel(m[0], m[1], m[2], null,
						initiateDate, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, status);
				auditList.add(auditModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		logger.info("Method : viewAuditInitDao ends");
		// System.out.println("Qual Dao=======" + qualList);
		return auditList;
	}

	@SuppressWarnings("unchecked")
	public List<RestAuditLinkCategoryModel> editDraftCommensement(String id) {
		System.out.println(id);
		logger.info("Method : editDraftCommensement starts");
		List<RestAuditLinkCategoryModel> getRequisitionTypeList = new ArrayList<RestAuditLinkCategoryModel>();

		String value = "SET @p_draftno='" + id + "';";
		System.out.println("id111"+value);
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("auditLinkRoutines")
					.setParameter("actionType", "editDrafts").setParameter("actionValue", value)
					.getResultList();

			for (Object[] m : x) {
				/*
				 * Object draftNo, Object auditInitiate,Object draftDate ,Object draftpersonTo,
				 * Object draftpersonCc, Object draftSubject, Object draftComment
				 */
				
				Object Date = null;
				if (m[2] != null) {
					Date = m[2].toString();

				}
				RestAuditLinkCategoryModel reqnotice = new RestAuditLinkCategoryModel(m[0], m[1],Date, m[3], m[4],
						m[5],m[6]);
				 System.out.println("Draft document@@@@@@@" + reqnotice);
			/*	  TACD_AuditNo, 
					TACD_DraftNo,
				    TACD_Date,
				    TACD_PersonTo,
				    TACD_PersonCc,
					TACD_Subject, 
					TACD_Comment*/
				getRequisitionTypeList.add(reqnotice);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		  if (getRequisitionTypeList != null) { 
			  List<RestUpdateDocumentModel> docList = new ArrayList<RestUpdateDocumentModel>(); 
			  try {
				  String subValues ="SET @p_auditInitiate='" + getRequisitionTypeList.get(0).getAuditInitiate() +"';";
				  List<Object[]> x1 = em.createNamedStoredProcedureQuery("auditLinkRoutines").setParameter("actionType", "getDraftDoc")
						  .setParameter("actionValue",  subValues) .getResultList();
				  for (Object[] m : x1) { 
					  RestUpdateDocumentModel dropDownModel = new RestUpdateDocumentModel(m[0],m[1],m[2],m[3]);
		  
		
					  docList.add(dropDownModel); } 
				  System.out.println("Draft document@" + docList); }
			  catch (Exception e) {
		  
		  } 
			  getRequisitionTypeList.get(0).setDocumentList(docList);
		  
		  }
		 
		System.out.println(getRequisitionTypeList);
		logger.info("Method : editDraftDetailsDao ends");
		return getRequisitionTypeList;
	}


}
