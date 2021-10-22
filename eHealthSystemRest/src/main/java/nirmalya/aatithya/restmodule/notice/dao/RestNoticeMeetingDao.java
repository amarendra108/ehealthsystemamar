package nirmalya.aatithya.restmodule.notice.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateNoticeInitiateParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.notice.model.RestInitiateNoticeDocumentModel;
import nirmalya.aatithya.restmodule.notice.model.RestIntiateNoticeModel;

@Repository
public class RestNoticeMeetingDao {
	
	Logger logger = LoggerFactory.getLogger(RestIntiateNoticeDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	// employee list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getempListsMeetingDao() {
		logger.info("Method : getempListsMeetingDao starts");

		List<DropDownModel> dept = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("initiateNotice")
					.setParameter("actionType", "getEmployeeList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				dept.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getempListsMeetingDao ends");
		return dept;
	}
	
	// Department list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getDeptListMeetingDao() {
		logger.info("Method : getDeptListMeetingDao starts");

		List<DropDownModel> dept = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("initiateNotice")
					.setParameter("actionType", "getDepartmentList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				dept.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getDeptListMeetingDao ends");
		return dept;
	}
	
	/*
	 * DAO Function to Add Meeting details
	 *
	 */

	@SuppressWarnings({ "unused", "unchecked" })
	public ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>> addMeetingDetailsDao(
			List<RestIntiateNoticeModel> initiateNoticeModel) {

		logger.info("Method : addMeetingDetailsDao starts");
		boolean validation = true;
		RestIntiateNoticeModel req = new RestIntiateNoticeModel();
		JsonResponse<List<RestIntiateNoticeModel>> resp = new JsonResponse<List<RestIntiateNoticeModel>>();

		List<RestIntiateNoticeModel> listData = new ArrayList<RestIntiateNoticeModel>();
		List<RestInitiateNoticeDocumentModel> docList = new ArrayList<RestInitiateNoticeDocumentModel>();
		resp.setMessage("");
		resp.setCode("");
		String errorCode = "Field Validation Error";
		
			try {
				String value = GenerateNoticeInitiateParameter.getMeetingDetailsParam(initiateNoticeModel);
				if (initiateNoticeModel.get(0).getMeetingNo() == "") {
				
					em.createNamedStoredProcedureQuery("initiateNotice")
							.setParameter("actionType", "addMeetingData").setParameter("actionValue", value)
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
		resp.setBody(listData);
		ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>> response = new ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : addMeetingDetailsDao ends");
		return response;

	}
	
	/*
	 * View Meeting details
	 * 
	 */
	@SuppressWarnings("unchecked")
		public List<RestIntiateNoticeModel> viewMeetingDetailsDao() {
		logger.info("Method : viewMeetingDetailsDao starts");
		
		List<RestIntiateNoticeModel> getRequisitionTypeList = new ArrayList<RestIntiateNoticeModel>();
		
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("initiateNotice")
					.setParameter("actionType", "viewNewMeetingDetails").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				Object meetingDate = null;
				if (m[1] != null) {
					meetingDate = DateFormatter.returnStringDate(m[1]);

				}

				RestIntiateNoticeModel reqnotice = new RestIntiateNoticeModel(m[0],meetingDate, m[2], m[3], m[4],
						m[5],m[6],m[7]);

				getRequisitionTypeList.add(reqnotice);
			}
			System.out.println("getRequisitionTypeList:"+getRequisitionTypeList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (getRequisitionTypeList != null) {
			List<RestInitiateNoticeDocumentModel> docList = new ArrayList<RestInitiateNoticeDocumentModel>();
			try {
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("initiateNotice")
						.setParameter("actionType", "viewMeetingDocDetails").setParameter("actionValue", "")
						.getResultList();
				for (Object[] m : x1) {

					RestInitiateNoticeDocumentModel dropDownModel = new RestInitiateNoticeDocumentModel(m[0], m[1],m[2],null,null);
					docList.add(dropDownModel);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		System.out.println("docList"+docList);

			getRequisitionTypeList.get(0).setDocumentList(docList);
		}
		
		logger.info("Method : viewMeetingDetailsDao ends");
		return getRequisitionTypeList;
	}
}
