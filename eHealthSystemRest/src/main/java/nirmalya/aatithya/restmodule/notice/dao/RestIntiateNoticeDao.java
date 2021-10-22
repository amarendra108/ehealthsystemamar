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

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateNoticeInitiateParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.notice.model.RestInitiateNoticeDocumentModel;
import nirmalya.aatithya.restmodule.notice.model.RestIntiateNoticeModel;

@Repository
public class RestIntiateNoticeDao {
	Logger logger = LoggerFactory.getLogger(RestIntiateNoticeDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	// employee list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getempLists() {
		logger.info("Method : getempLists starts");

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

		logger.info("Method : getempLists ends");
		return dept;
	}
	
	// Department list
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getDeptListDao() {
		logger.info("Method : getDeptListDao starts");

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

		logger.info("Method : getDeptListDao ends");
		return dept;
	}

	// AutoSearch
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>> getNoticeListByAutoSearch(String id) {
		logger.info("Method : getNoticeListByAutoSearch starts");
		List<RestIntiateNoticeModel> itemNameList = new ArrayList<RestIntiateNoticeModel>();
		JsonResponse<List<RestIntiateNoticeModel>> resp = new JsonResponse<List<RestIntiateNoticeModel>>();
		String value = "SET @p_searchValue='" + id + "';";

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("initiateNotice")
					.setParameter("actionType", "getNoticeList").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
				RestIntiateNoticeModel dropDownModel = new RestIntiateNoticeModel(m[0], m[1], null);
				itemNameList.add(dropDownModel);
			}
			resp.setBody(itemNameList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>> response = new ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getNoticeListByAutoSearch ends");
		return response;
	}
	
	/*
	 * DAO Function to Add notice details
	 *
	 */

	@SuppressWarnings({ "unused", "unchecked" })
	public ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>> restaddNoticeDao(
			List<RestIntiateNoticeModel> initiateNoticeModel) {

		logger.info("Method : restaddNoticeDao starts");
		boolean validation = true;
		RestIntiateNoticeModel req = new RestIntiateNoticeModel();
		JsonResponse<List<RestIntiateNoticeModel>> resp = new JsonResponse<List<RestIntiateNoticeModel>>();

		List<RestIntiateNoticeModel> listData = new ArrayList<RestIntiateNoticeModel>();
		List<RestInitiateNoticeDocumentModel> docList = new ArrayList<RestInitiateNoticeDocumentModel>();
		resp.setMessage("");
		resp.setCode("");
		String errorCode = "Field Validation Error";
		for (RestIntiateNoticeModel l : initiateNoticeModel) {

			if (l.getNoticeType() == null || l.getNoticeType() == "") {
				validation = false;
				resp.setCode(errorCode);
				resp.setMessage("Please Enter Notice Type");
				break;
			} else if (l.getNoticeNo() == null || l.getNoticeNo() == "") {
				validation = false;
				resp.setCode(errorCode);
				resp.setMessage("Please Enter Notice No.");
				break;
			} else if (l.getStartDate() == null || l.getStartDate() == "") {
				validation = false;
				resp.setCode(errorCode);
				resp.setMessage("Please Enter Notice Date.");
				break;
			} else if (l.getEndDate() == null || l.getEndDate() == "") {
				validation = false;
				resp.setCode(errorCode);
				resp.setMessage("Please Enter Due Date.");
				break;
			} else if (l.getComment() == null || l.getComment() == "") {
				validation = false;
				resp.setCode(errorCode);
				resp.setMessage("Please Enter Comment");
				break;
			} else if (l.getNoticeFrom() == null || l.getNoticeFrom() == "") {
				validation = false;
				resp.setCode(errorCode);
				resp.setMessage("please Enter From Field.");
				break;
			}
		}

		if (validation) {
			try {
				String value = GenerateNoticeInitiateParameter.getNoticeinitateParam(initiateNoticeModel);
				if (initiateNoticeModel.get(0).getNoticeId() != null
						&& initiateNoticeModel.get(0).getNoticeId() != "") {
					
					List<Object[]> x = em.createNamedStoredProcedureQuery("initiateNotice")
							.setParameter("actionType", "modifyintiateNotice").setParameter("actionValue", value)
							.getResultList();
					
					for (Object[] m : x) {
						Object initDate = null;
						if (m[6] != null) {
							initDate = m[6].toString();

						}
						Object startDate = null;
						if (m[3] != null) {
							startDate = m[3].toString();
						}
						Object dueDate = null;
						if (m[4] != null) {
							dueDate = m[4].toString();

						}
						
						RestIntiateNoticeModel reqnotice = new RestIntiateNoticeModel(m[0], m[1], null, m[2], startDate,
								dueDate, m[5], initDate, null, m[7]);
						listData.add(reqnotice);
					}
				} else {
					List<Object[]> x = em.createNamedStoredProcedureQuery("initiateNotice")
							.setParameter("actionType", "addInitiateNotice").setParameter("actionValue", value)
							.getResultList();

					for (Object[] m : x) {
						Object initDate = null;
						if (m[6] != null) {
							initDate = m[6].toString();

						}
						Object startDate = null;
						if (m[3] != null) {
							startDate = m[3].toString();
						}
						Object dueDate = null;
						if (m[4] != null) {
							dueDate = m[4].toString();

						}

						RestIntiateNoticeModel reqnotice = new RestIntiateNoticeModel(m[0], m[1], null, m[2], startDate,
								dueDate, m[5], initDate, null, m[7]);
						listData.add(reqnotice);
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
		ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>> response = new ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : restaddNoticeDao ends");
		return response;

	}
	
	/*
	 * View Notice Details
	 */

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestIntiateNoticeModel>> viewNoticeInitDao() {

		logger.info("Method in Dao: viewNoticeDtlsDao starts");

		List<RestIntiateNoticeModel> noticeList = new ArrayList<RestIntiateNoticeModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("initiateNotice")
					.setParameter("actionType", "viewNotice").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				Object initDate = null;
				Object startDate = null;
				Object dueDate = null;
				if (m[6] != null) {
					initDate = DateFormatter.returnStringDate(m[6]);
				}
				if (m[3] != null) {
					startDate = DateFormatter.returnStringDate(m[3]);
				}
				if (m[4] != null) {
					dueDate = DateFormatter.returnStringDate(m[4]);
				}

				RestIntiateNoticeModel noticeModel = new RestIntiateNoticeModel(m[0], m[1], m[2], null, startDate,
						dueDate, m[5], initDate, m[7], m[8]);
				noticeList.add(noticeModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JsonResponse<List<RestIntiateNoticeModel>> resp = new JsonResponse<List<RestIntiateNoticeModel>>();
		resp.setBody(noticeList);

		logger.info("Method in Dao: viewNoticeDtlsDao ends");
		return resp;
	}

	
	/*
	 * delete Notice Details
	 */

	public JsonResponse<RestIntiateNoticeModel> deleteNoticeDetailsDao(String id) {
		logger.info("Method : deleteNoticeDetailsDao starts");

		RestIntiateNoticeModel req = new RestIntiateNoticeModel();
		JsonResponse<RestIntiateNoticeModel> resp = new JsonResponse<RestIntiateNoticeModel>();

		try {

			String value = "SET @p_noticeId='(" + id + ")';";

			em.createNamedStoredProcedureQuery("initiateNotice").setParameter("actionType", "deleteNoticeDetails")
					.setParameter("actionValue", value).execute();

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : deleteNoticeDetailsDao ends");

		return resp;
	}

	/*
	 * View Drafts details
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestIntiateNoticeModel>> viewDraftDao(String noticeId, String createdBy) {

		logger.info("Method in Dao: viewDraftDao starts");

		String value = "SET @p_noticeId='" + noticeId + "',@p_createdby='" + createdBy + "';";
		List<RestIntiateNoticeModel> noticeList = new ArrayList<RestIntiateNoticeModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("initiateNotice")
					.setParameter("actionType", "viewDraftDetails").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {

				Object draftDate = null;
				if (m[4] != null) {
					draftDate = DateFormatter.returnStringDate(m[4]);
				}

				String sb = m[3].toString();
				int sblen = sb.length();
				String sb2 = sb.substring(3, sblen-5);

				RestIntiateNoticeModel noticeModel = new RestIntiateNoticeModel(m[0], m[1], m[2], sb2, draftDate, null,
						null);

				noticeList.add(noticeModel);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<RestIntiateNoticeModel>> resp = new JsonResponse<List<RestIntiateNoticeModel>>();
		resp.setBody(noticeList);

		logger.info("Method in Dao: viewDraftDao ends");
		return resp;
	}

	
	/*
	 * add draft details
	 */
	public ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>> restAddDraftsDao(
			List<RestIntiateNoticeModel> initiateNoticeModel) {

		logger.info("Method : restAddDraftsDao starts");

		JsonResponse<List<RestIntiateNoticeModel>> resp = new JsonResponse<List<RestIntiateNoticeModel>>();
		List<RestIntiateNoticeModel> listData = new ArrayList<RestIntiateNoticeModel>();

		try {
			String values = GenerateNoticeInitiateParameter.getSaveDraftParam(initiateNoticeModel);
			if (initiateNoticeModel.get(0).getDraftNo() == null || initiateNoticeModel.get(0).getDraftNo() == "") {
				em.createNamedStoredProcedureQuery("initiateNotice").setParameter("actionType", "addNoticeDraft")
						.setParameter("actionValue", values).execute();

			} else {
				em.createNamedStoredProcedureQuery("initiateNotice").setParameter("actionType", "modifyDraftDetails")
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
		ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>> response = new ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : restAddDraftsDao ends");
		return response;
	}

	/*
	 * DAO Function to Add Send details
	 *
	 */

	public ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>> restAddSendDetails(
			List<RestIntiateNoticeModel> initiateNoticeModel) {

		logger.info("Method : restAddSendDetails starts");
		JsonResponse<List<RestIntiateNoticeModel>> resp = new JsonResponse<List<RestIntiateNoticeModel>>();

		List<RestIntiateNoticeModel> listData = new ArrayList<RestIntiateNoticeModel>();

		try {
			String value = GenerateNoticeInitiateParameter.getSaveMailParam(initiateNoticeModel);
			if (initiateNoticeModel.get(0).getSendNo() != null && initiateNoticeModel.get(0).getSendNo() != "") {
				em.createNamedStoredProcedureQuery("initiateNotice").setParameter("actionType", "modifySendDetails")
						.setParameter("actionValue", value).execute();

			} else {
				em.createNamedStoredProcedureQuery("initiateNotice").setParameter("actionType", "addSendDetails")
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
		ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>> response = new ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : restAddSendDetails ends");
		return response;

	}
	
	//Method for mail API
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> getmaildetails(String mylist) {
		logger.info("Method : getmaildetails starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();

		//int i= mylist.size();
		
		//List<DropDownModel> emaillist = new ArrayList<DropDownModel>();
		//for (int i=0 ;i<mylist.size();i++ ) {
			try {
				//int sblen=mylist.length();
				//String value1[]=mylist.split("[");
				//String value1 = mylist.substring(1, sblen-1);
				//System.out.println("value1 "+value1);
				//String value2[]=value1.split("]");
				//String value2=value1.replaceFirst("'", "\"");
				//System.out.println("val2 "+value2);
				
				//select distinct(TEM_Employee),TEM_Emp_Work_Email from tbl_employee_mstr WHERE TEM_Employee IN("EMPL0001","EMPL0003");

			//	String value = "SET @p_empId='(" + value1 + ")';";
				//String value = "SET @p_empId='(" + value1 + ")'";
				
				String value = "SET @p_noticeId='" + mylist + "';";
				System.out.println(value);
				
				
				List<Object[]> x = em.createNamedStoredProcedureQuery("initiateNotice")
						.setParameter("actionType", "getEmailList").setParameter("actionValue", value).getResultList();
				//System.err.println(x);
				for (Object[] m : x) {
					//System.out.println(Arrays.toString(m));
					//DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
					//resp.setBody(dropDownModel);
					System.out.println("hi"+m[1]);
					resp.setBody(m[1]);
				}
				 
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		//}

		
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(
				resp, HttpStatus.CREATED);
		System.out.println(response.getBody());

		logger.info("Method : getmaildetails ends");
		return response;
	}
	/*
	 * Edit Draft Details
	 * 
	 */

	@SuppressWarnings("unchecked")
	public List<RestIntiateNoticeModel> editDraftDetailsDao(String id) {
		logger.info("Method : editDraftDetailsDao starts");
		List<RestIntiateNoticeModel> getRequisitionTypeList = new ArrayList<RestIntiateNoticeModel>();

		String value = "SET @p_draftId='" + id + "';";

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("initiateNotice")
					.setParameter("actionType", "editDraftDetails").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {

				Object DATE = null;

				if (m[4] != null) {
					DATE = DateFormatter.returnStringDate(m[4]);
				}

				RestIntiateNoticeModel reqnotice = new RestIntiateNoticeModel(m[0], m[1], m[2], m[3], DATE, m[5], m[6]);
				getRequisitionTypeList.add(reqnotice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (getRequisitionTypeList != null) {
			List<RestInitiateNoticeDocumentModel> docList = new ArrayList<RestInitiateNoticeDocumentModel>();
			try {
				String subValues = "SET @p_noticeId='" + getRequisitionTypeList.get(0).getNoticeId() + "';";
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("initiateNotice")
						.setParameter("actionType", "getDraftDocs").setParameter("actionValue", subValues)
						.getResultList();
				for (Object[] m : x1) {

					RestInitiateNoticeDocumentModel dropDownModel = new RestInitiateNoticeDocumentModel(m[0], m[1],
							m[2],m[3]);
					docList.add(dropDownModel);
				}
			
			} catch (Exception e) {
					e.printStackTrace();
			}
			getRequisitionTypeList.get(0).setDocumentList(docList);

		}

		logger.info("Method : editDraftDetailsDao ends");
		return getRequisitionTypeList;
	}
	
	/*
	 * Edit Notice Details
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<RestIntiateNoticeModel> editNoticeinitiate(String id) {
		logger.info("Method : editNoticeinitiate starts");
		List<RestIntiateNoticeModel> getRequisitionTypeList = new ArrayList<RestIntiateNoticeModel>();
		String value = "SET @p_noticeId='" + id + "';";

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("initiateNotice")
					.setParameter("actionType", "editInitiateNotice").setParameter("actionValue", value)
					.getResultList();

			for (Object[] m : x) {
				Object initDate = null;
				if (m[7] != null) {
					initDate = DateFormatter.returnStringDate(m[7]);

				}
				Object startDate = null;
				if (m[4] != null) {
					startDate = DateFormatter.returnStringDate(m[4]);
				}
				Object dueDate = null;
				if (m[5] != null) {
					dueDate = DateFormatter.returnStringDate(m[5]);

				}
				
				RestIntiateNoticeModel reqnotice = new RestIntiateNoticeModel(m[0], m[1], m[2], m[3], startDate,
						dueDate, m[6], initDate, m[8], m[9]);

				getRequisitionTypeList.add(reqnotice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (getRequisitionTypeList != null) {
			List<RestInitiateNoticeDocumentModel> docList = new ArrayList<RestInitiateNoticeDocumentModel>();
			try {
				String subValues = "SET @p_noticeId='" + getRequisitionTypeList.get(0).getNoticeId() + "';";
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("initiateNotice")
						.setParameter("actionType", "getNoticeDocs").setParameter("actionValue", subValues)
						.getResultList();
				for (Object[] m : x1) {

					RestInitiateNoticeDocumentModel dropDownModel = new RestInitiateNoticeDocumentModel(m[0], m[1],
							m[2]);

					docList.add(dropDownModel);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			getRequisitionTypeList.get(0).setDocumentList(docList);

		}

		logger.info("Method : editNoticeinitiate ends");
		return getRequisitionTypeList;
	}

	
	/*
	 * View document details
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<RestIntiateNoticeModel> viewDocumentsDao(String id) {
		logger.info("Method : viewDocumentsDao starts");
		
		List<RestIntiateNoticeModel> getRequisitionTypeList = new ArrayList<RestIntiateNoticeModel>();
		String value = "SET @p_noticeId='" + id + "';";
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("initiateNotice")
					.setParameter("actionType", "viewPageNotice").setParameter("actionValue", value)
					.getResultList();
			

				
			for (Object[] m : x) {
				Object initDate = null;
				if (m[7] != null) {
					initDate = DateFormatter.returnStringDate(m[7]);

				}
				Object startDate = null;
				if (m[4] != null) {
					startDate = DateFormatter.returnStringDate(m[4]);
				}
				Object dueDate = null;
				if (m[5] != null) {
					dueDate = DateFormatter.returnStringDate(m[5]);

				}
				
				RestIntiateNoticeModel reqnotice = new RestIntiateNoticeModel(m[0], m[1], m[2], m[3], startDate,
						dueDate, m[6], initDate, m[8], m[9]);

				getRequisitionTypeList.add(reqnotice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/* String value = "SET @p_noticeId='" + id + "';"; */
		if (getRequisitionTypeList != null) {
			List<RestInitiateNoticeDocumentModel> docList = new ArrayList<RestInitiateNoticeDocumentModel>();
			try {
				List<Object[]> x1 = em.createNamedStoredProcedureQuery("initiateNotice")
						.setParameter("actionType", "viewDocumentDetails").setParameter("actionValue", value)
						.getResultList();
				for (Object[] m : x1) {

					RestInitiateNoticeDocumentModel dropDownModel = new RestInitiateNoticeDocumentModel(m[0], m[1],
							m[2]);
					docList.add(dropDownModel);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		

			getRequisitionTypeList.get(0).setDocumentList(docList);
		}
		logger.info("Method : viewDocumentsDao ends");
		return getRequisitionTypeList;
	}
	
	/*
	 * DAO Function to Add Meeting details
	 *
	 */

	@SuppressWarnings({ "unused", "unchecked" })
	public ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>> addMeetingDetailsDao(
			List<RestIntiateNoticeModel> initiateNoticeModel) {

		logger.info("Method : addMeetingSceduleDao starts");
		boolean validation = true;
		RestIntiateNoticeModel req = new RestIntiateNoticeModel();
		JsonResponse<List<RestIntiateNoticeModel>> resp = new JsonResponse<List<RestIntiateNoticeModel>>();

		List<RestIntiateNoticeModel> listData = new ArrayList<RestIntiateNoticeModel>();
		List<RestInitiateNoticeDocumentModel> docList = new ArrayList<RestInitiateNoticeDocumentModel>();
		resp.setMessage("");
		resp.setCode("");
		String errorCode = "Field Validation Error";
		for (RestIntiateNoticeModel l : initiateNoticeModel) {

			if (l.getNoticeId() == null || l.getNoticeId() == "") {
				validation = false;
				resp.setCode(errorCode);
				resp.setMessage("Please Enter Notice No");
				break;
			} else if (l.getMeetingDate() == null || l.getMeetingDate() == "") {
				validation = false;
				resp.setCode(errorCode);
				resp.setMessage("Please Enter Meeting Date.");
				break;
			} else if (l.getStartTime() == null || l.getStartTime() == "") {
				validation = false;
				resp.setCode(errorCode);
				resp.setMessage("Please Enter Start time.");
				break;
			} else if (l.getEndTime() == null || l.getEndTime() == "") {
				validation = false;
				resp.setCode(errorCode);
				resp.setMessage("Please Enter End Time.");
				break;
			} else if (l.getMeetingSubject() == null || l.getMeetingSubject() == "") {
				validation = false;
				resp.setCode(errorCode);
				resp.setMessage("Please Enter Meeting Subject");
				break;
			} else if (l.getParticipants() == null || l.getParticipants() == "") {
				validation = false;
				resp.setCode(errorCode);
				resp.setMessage("please Enter Meeting Participants Field.");
				break;
			}
			else if (l.getParticipantDept()== null || l.getParticipantDept() == "") {
				validation = false;
				resp.setCode(errorCode);
				resp.setMessage("please Enter Participants Department Field.");
				break;
			}
		}

		if (validation) {
			try {
				String value = GenerateNoticeInitiateParameter.getMeetingScheduleParam(initiateNoticeModel);
				if (initiateNoticeModel.get(0).getMeetingNo() != null
						&& initiateNoticeModel.get(0).getMeetingNo() != "") {
					List<Object[]> x = em.createNamedStoredProcedureQuery("initiateNotice")
							.setParameter("actionType", "modifyMeetingSchedule").setParameter("actionValue", value)
							.getResultList();
					for (Object[] m : x) {
						Object meetingDate = null;
						if (m[2] != null) {
							meetingDate = m[2].toString();

						}
						Object startTime = null;
						if (m[3] != null) {
							startTime = m[3].toString();
						}
						Object endTime = null;
						if (m[4] != null) {
							endTime = m[4].toString();

						}

						RestIntiateNoticeModel reqnotice = new RestIntiateNoticeModel(m[0], m[1],meetingDate, startTime,
								endTime, m[5], m[6], m[7],m[8]);
						req = reqnotice;

					}

				} else {
					List<Object[]> x = em.createNamedStoredProcedureQuery("initiateNotice")
							.setParameter("actionType", "addMeetingScedule").setParameter("actionValue", value)
							.getResultList();

					for (Object[] m : x) {
						Object meetingDate = null;
						if (m[2] != null) {
							meetingDate = m[2].toString();

						}
						Object startTime = null;
						if (m[3] != null) {
							startTime = m[3].toString();
						}
						Object endTime = null;
						if (m[4] != null) {
							endTime = m[4].toString();

						}

						RestIntiateNoticeModel reqnotice = new RestIntiateNoticeModel(m[0], m[1],meetingDate, startTime,
								endTime, m[5], m[6], m[7],m[8]);
						listData.add(reqnotice);
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
		ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>> response = new ResponseEntity<JsonResponse<List<RestIntiateNoticeModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : addMeetingSceduleDao ends");
		return response;

	}
	
	
	
}
