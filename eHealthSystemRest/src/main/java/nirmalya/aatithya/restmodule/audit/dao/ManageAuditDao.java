package nirmalya.aatithya.restmodule.audit.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.audit.model.AuditMangeDocumentModel;
import nirmalya.aatithya.restmodule.audit.model.ManageAuditModel;
import nirmalya.aatithya.restmodule.audit.model.RestAuditDocumentModel;
import nirmalya.aatithya.restmodule.audit.model.RestAuditLinkCategoryModel;
import nirmalya.aatithya.restmodule.audit.model.RestAuditMeetingDocumentModel;
import nirmalya.aatithya.restmodule.audit.model.RestAuditMeetingRestModel;
import nirmalya.aatithya.restmodule.audit.model.RestUpdateDocumentModel;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class ManageAuditDao {

	Logger logger = LoggerFactory.getLogger(ManageAuditDao.class);

	@Autowired
	ServerDao serverDao;

	@Autowired
	EntityManager em;

	/*
	 * @SuppressWarnings("unchecked") public List<ManageAuditModel>
	 * viewAuditDtlssssDaoq() { logger.info("Method : viewAuditDtlsDao starts");
	 * List<ManageAuditModel> auditList = new ArrayList<ManageAuditModel>(); try {
	 * String value = "SET @p_auditInitiateId='" + id + "'";
	 * 
	 * List<Object[]> x = em.createNamedStoredProcedureQuery("auditManageRoutines")
	 * .setParameter("actionType", "viewAuditDtls").setParameter("actionValue",
	 * value).getResultList();
	 * 
	 * for (Object[] m : x) { Object initiateDate = null; if (m[3] != null) {
	 * initiateDate = DateFormatter.returnStringDate(m[3]); } Object status = ""; if
	 * (m[4].equals("0")) { status = "Open"; }else if (m[4].equals("1")) { status =
	 * "Draft"; }else { status = "Final"; }
	 * 
	 * ManageAuditModel auditModel = new ManageAuditModel(m[0], m[1], m[2], null,
	 * initiateDate, null, null, null,null, null, null, null,null, null, null,null,
	 * null, null,null, null,status); auditList.add(auditModel); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * logger.info("Method : viewAuditDtlsDao ends");
	 * System.out.println("Qual Dao=======" + auditList); return auditList; }
	 */

	@SuppressWarnings("unchecked")
	public JsonResponse<List<ManageAuditModel>> viewAuditDtlssssDao() {

		logger.info("Method in Dao: viewNoticeDtlsDao starts");

		List<ManageAuditModel> noticeList = new ArrayList<ManageAuditModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("auditManageRoutines")
					.setParameter("actionType", "viewAuditDtls").setParameter("actionValue", "").getResultList();

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

				ManageAuditModel noticeModel = new ManageAuditModel(m[0], m[1], m[2], null, initiateDate, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, status);
				noticeList.add(noticeModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<ManageAuditModel>> resp = new JsonResponse<List<ManageAuditModel>>();
		resp.setBody(noticeList);

		logger.info("Method in Dao: viewNoticeDtlsDao ends");
		return resp;

	}

	@SuppressWarnings("unchecked")
	public List<ManageAuditModel> viewDocumentsAuditsss(String id) {
		logger.info("Method : viewDocumentsAudit starts");

		List<ManageAuditModel> getRequisitionTypeList = new ArrayList<ManageAuditModel>();
		System.out.println(getRequisitionTypeList);
		String value = "SET @p_auditInitiate='" + id + "';";
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("auditManageRoutines")
					.setParameter("actionType", "viewPageAudit").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				Object initDate = null;
				if (m[2] != null) {
					initDate = m[2].toString();

				}
				Object date = null;
				if (m[4] != null) {
					date = m[4].toString();

				}

				ManageAuditModel reqnotice = new ManageAuditModel(m[0], m[1], initDate, m[3], date, m[5], m[6], null,
						null, null, null, null, null, null, null, null, null, null, null, null, null);

				getRequisitionTypeList.add(reqnotice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// String values = "SET @p_mettingId='" + id + "';";

		if (getRequisitionTypeList != null) {
			List<AuditMangeDocumentModel> docList = new ArrayList<AuditMangeDocumentModel>();
			try {
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("auditmeetingRoutines")
						.setParameter("actionType", "viewDocumentDetailss").setParameter("actionValue", value)
						.getResultList();
				for (Object[] m : x1) {

					AuditMangeDocumentModel dropDownModel = new AuditMangeDocumentModel(m[0], m[1], m[2]);
					docList.add(dropDownModel);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			getRequisitionTypeList.get(0).setDocumentList(docList);
		}

		System.out.print("value@@");
		System.out.print("docList");
		System.out.println("friday+++++docList" + getRequisitionTypeList);
		logger.info("Method : viewDocumentsDao ends");
		return getRequisitionTypeList;
	}

}
