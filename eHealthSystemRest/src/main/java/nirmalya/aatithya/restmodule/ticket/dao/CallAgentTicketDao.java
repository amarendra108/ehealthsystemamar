package nirmalya.aatithya.restmodule.ticket.dao;

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

import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateCallAgentTicketParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.ticket.model.CallAgentTicketRestModel;
@Repository
public class CallAgentTicketDao {
	
	Logger logger = LoggerFactory.getLogger(CallAgentTicketDao.class);
	
	@Autowired
	ServerDao serverDao;
	
	@Autowired
	EntityManager em;
	
	/************************            Drop  Down               *************************************/
	/**
	 * DAO Function for drop down models of industrial type
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getIndustrialType() {
		logger.info("Method : getIndustrialType starts");
		List<DropDownModel> industrialList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "getIndustrialType").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				industrialList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getIndustrialType end");
		return industrialList;
	}

	/**
	 * DAO Function for drop down models of service center
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getServiceCenterList() {
		logger.info("Method : getServiceCenterList starts");
		List<DropDownModel> serviceCenterList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "getServiceCenter").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				serviceCenterList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getServiceCenterList end");
		return serviceCenterList;
	}

	/**
	 * DAO Function for drop down models of complaint category
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getComplaintCategory() {
		logger.info("Method : getComplaintCategory starts");
		List<DropDownModel> complaintCategoryList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "getComplaintCat").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				complaintCategoryList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getComplaintCategory end");
		return complaintCategoryList;
	}

	/**
	 * DAO Function for drop down models of complaint location
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getComplaintLocation() {
		logger.info("Method : getComplaintLocation starts");
		List<DropDownModel> complaintLocList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "getComplaintLoc").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				complaintLocList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getComplaintLocation end");
		return complaintLocList;
	}

	/**
	 * DAO Function for drop down models of user location
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getUserLocationForTicket() {
		logger.info("Method : getUserLocationForTicket starts");
		List<DropDownModel> userLocationList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "getUserLocName").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				userLocationList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getUserLocationForTicket end");
		return userLocationList;
	}

	/**
	 * DAO Function for drop down models of user type
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getCostmerType() {
		logger.info("Method : getUserType starts");
		List<DropDownModel> userTypeList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "getCostmerType").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				userTypeList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getUserType end");
		return userTypeList;
	}

	/**
	 * DAO Function for drop down models of call queue
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getCallQueueForTicket() {
		logger.info("Method : getCallQueueForTicket starts");
		List<DropDownModel> callQueueList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "getCallQueue").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				callQueueList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getCallQueueForTicket end");
		return callQueueList;
	}

	/**
	 * DAO Function for drop down models of call queue
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getProductTypeList() {
		logger.info("Method : getProductTypeList starts");
		List<DropDownModel> productList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "getProductType").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				productList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getCallQueueForTicket end");
		return productList;
	}
	/**
	 * DAO Function for drop down models of status
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getStatusForTicket() {
		logger.info("Method : getStatusForTicket starts");
		List<DropDownModel> statusList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "getStatusName").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				statusList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getUserLocationForTicket end");
		return statusList;
	}
	/**
	 * DAO Function for Drop down for call reason through on change
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getCallReasonList(String callQueue) {

		logger.info("Method : getCallReasonList starts");

		List<DropDownModel> callReasonList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();

		try {
			String value = "SET @p_callQueue='" + callQueue + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "getCallReasonList").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				callReasonList.add(dropDownModel);

			}

			resp.setBody(callReasonList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				resp, HttpStatus.CREATED);

		logger.info("Method : seatingplanList ends");
		//System.out.println("call reason dao======="+response);
		return response;
	}
	/************************            Call  Details / All Tickets              *************************************/
	
	// View all call details Agent Ticket Details 

	@SuppressWarnings("unchecked")
	public JsonResponse<List<CallAgentTicketRestModel>> viewTicketAgentDtlsDao() {

		logger.info("Method in Dao: viewTicketAgentDtlsDao starts");

		List<CallAgentTicketRestModel> ticketDtlsList = new ArrayList<CallAgentTicketRestModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "viewCallDetailsAgentTicket").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				Object DateTime = null;
				DateTime = DateFormatter.returnStringDate(m[6]);

				CallAgentTicketRestModel callAgentTicketModel = new CallAgentTicketRestModel(null, m[0], null, null, null, null,
						null, null, m[1], m[2], m[3], m[4], m[5], DateTime, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, m[7], null, null);
				ticketDtlsList.add(callAgentTicketModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<CallAgentTicketRestModel>> resp = new JsonResponse<List<CallAgentTicketRestModel>>();
		resp.setBody(ticketDtlsList);

		logger.info("Method in Dao: viewTicketAgentDtlsDao ends");
//System.out.println("view dao======="+resp);
		return resp;
	}
	/************************            Add  Agent Tickets               *************************************/
//Add Agent Ticket Details
	public ResponseEntity<JsonResponse<Object>> addAgentTicketDao(CallAgentTicketRestModel form) {
		logger.info("Method : addAgentTicketDao starts");

		Boolean validity = true;
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");
		if (form.getPhoneNo() == null || form.getPhoneNo() == "") {
			resp.setMessage("phone no required");

			validity = false;

		} else if (form.getCustomerName() == null || form.getCustomerName() == "") {
			resp.setMessage("customer name required");
			validity = false;

		} else if (form.getCustomerLocation() == null || form.getCustomerLocation() == "") {
			resp.setMessage("customer location required");
			validity = false;
		} else if (form.getIndustrialType() == null || form.getIndustrialType() == "") {
			resp.setMessage("industrial type required");
			validity = false;
		} else if (form.getCustomerType() == null || form.getCustomerType() == "") {
			resp.setMessage("customer type required");
			validity = false;
		} else if (form.getAlternatePhoneNo() == null || form.getAlternatePhoneNo() == "") {
			resp.setMessage("alternate phone no required");
			validity = false;
		} else if (form.getEmail() == null || form.getEmail() == "") {
			resp.setMessage("email required");
			validity = false;
		}

		else if (form.getCallDisconnected() == null) {
			resp.setMessage("status required");
			validity = false;
		}

		else if (form.getCallQueue() == null || form.getCallQueue() == "") {
			resp.setMessage("call queue required");
			validity = false;
		} else if (form.getCallReason() == null || form.getCallReason() == "") {
			resp.setMessage("call reason required");
			validity = false;
		} else if (form.getProductType() == null || form.getProductType() == "") {
			resp.setMessage("product type required");
			validity = false;
		} else if (form.getCommentBox() == null || form.getCommentBox() == "") {
			resp.setMessage("comment box required");
			validity = false;
		}

		if (validity)
			try {
				String values = GenerateCallAgentTicketParameter.getAddCallAgentTicket(form);

				if (form.getTicketId() != null && form.getTicketId() != "") {

					em.createNamedStoredProcedureQuery("ticketRoutines").setParameter("actionType", "modifyAgentTicket")
							.setParameter("actionValue", values).execute();
				} else {

					em.createNamedStoredProcedureQuery("ticketRoutines").setParameter("actionType", "addAgentTicket")
							.setParameter("actionValue", values).execute();

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
				try {
					// String[] err = ServerValidation.geterror(e);
					String[] err = serverDao.errorProcedureCall(e);
					resp.setCode(err[0]);
					resp.setMessage(err[1]);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);

		logger.info("Method : addAgentTicketDao end");
		
		//System.out.println("Add Agent ticket Dao==="+response);
		return response;
	}

	/**
	 * DAO Function for auto fill of customer name property booking
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CallAgentTicketRestModel>>> getCustomerAutofillMob(String id) {
		logger.info("Method : getCustomerAutofillMob starts");

		List<CallAgentTicketRestModel> form = new ArrayList<CallAgentTicketRestModel>();
		JsonResponse<List<CallAgentTicketRestModel>> resp = new JsonResponse<List<CallAgentTicketRestModel>>();
		try {
			String value = "SET @p_mob='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "getCustAutoSrchMob").setParameter("actionValue", value)
					.getResultList();

			for (Object[] m : x) {
				CallAgentTicketRestModel callAgentTicketModel = new CallAgentTicketRestModel(m[0], m[1], m[2], m[3], m[4], m[5],
						m[6], m[7], null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null);

				form.add(callAgentTicketModel);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		resp.setBody(form);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<List<CallAgentTicketRestModel>>> response = new ResponseEntity<JsonResponse<List<CallAgentTicketRestModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getCustomerAutofillMob Dao ends");
		return response;
	}
	
	/************************            General  Enquiry               *************************************/
	
	// View General Enquiry Details 

	@SuppressWarnings("unchecked")
	public JsonResponse<List<CallAgentTicketRestModel>> viewAllGeneralEnquiryDao() {

		logger.info("Method in Dao: viewAllGeneralEnquiryDao starts");

		List<CallAgentTicketRestModel> ticketDtlsList = new ArrayList<CallAgentTicketRestModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "viewTicketGeneralEnquiery").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				Object DateTime = null;
				DateTime = DateFormatter.returnStringDate(m[6]);

				CallAgentTicketRestModel callAgentTicketModel = new CallAgentTicketRestModel(null, m[0], null, null, null, null,
						null, null, m[1], m[2], m[3], m[4], m[5], DateTime, m[7], null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null);
				ticketDtlsList.add(callAgentTicketModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<CallAgentTicketRestModel>> resp = new JsonResponse<List<CallAgentTicketRestModel>>();
		resp.setBody(ticketDtlsList);

		logger.info("Method in Dao: viewAllGeneralEnquiryDao ends");
//System.out.println("view dao======="+resp);
		return resp;
	}
	/**
	 * DAO Function for modal view General Enquiry
	 *
	 */
	@SuppressWarnings("unchecked") 
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getGeneralEnquiryAgentByIdDao(String id) {
		logger.info("Method : getGeneralEnquiryAgentByIdDao starts");

		List<CallAgentTicketRestModel> form = new ArrayList<CallAgentTicketRestModel>();

		try {
			String value = "SET @p_generalQueryAgent='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "ModalViewGeneralEnquiry").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
			
				CallAgentTicketRestModel callAgentTicketModel = new CallAgentTicketRestModel(m[0], m[1], m[2], m[3], m[4], m[5],
						m[6], m[7], m[8], m[9], m[10], m[11], m[12], null, m[13], m[14], m[15], null, null, null, null,
						null, null, null, null, null, null, null, null, null, null);
				form.add(callAgentTicketModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JsonResponse<CallAgentTicketRestModel> resp = new JsonResponse<CallAgentTicketRestModel>();
		resp.setBody(form.get(0));

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<CallAgentTicketRestModel>> response = new ResponseEntity<JsonResponse<CallAgentTicketRestModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getGeneralEnquiryAgentByIdDao end");
		//System.out.println("value"+response);
		return response;

	}
	/*
	 * for modal view general comment ticket
	 */

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getTicketTGeneralCommentModalById(String id) {

		logger.info("Method : getTicketTGeneralCommentModalById starts");

		List<CallAgentTicketRestModel> ticketType = new ArrayList<CallAgentTicketRestModel>();
		JsonResponse<CallAgentTicketRestModel> resp = new JsonResponse<CallAgentTicketRestModel>();

		try {
			//System.out.println("value"+id);
			String value = "SET @p_generalQueryAgent='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "ViewCommentsGenEnq").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				Object DateTime = null;
				DateTime = DateFormatter.returnStringDate(m[16]);
				CallAgentTicketRestModel ticket1 = new CallAgentTicketRestModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7],
						m[8], m[9], m[10], m[11], m[12], null, m[13], m[14], m[15], null, null, null, null, null, null,
						null, null, null, DateTime, m[17], null, null, null);

				ticketType.add(ticket1);
			}

			resp.setBody(ticketType.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<CallAgentTicketRestModel>> response = new ResponseEntity<JsonResponse<CallAgentTicketRestModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getTicketTServiceCommentModalById ends");
		//System.out.println("comment view ==============="+response);
		return response;
	}

	/**
	 * DAO Function for add comment model models
	 *
	 */

	public ResponseEntity<JsonResponse<Object>> addCommentsGeneralAgentById(String id, String comment, String status,
			String createdBy) {
		logger.info("Method : DAO addCommentsGeneralAgentById starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		try {
			String value = "SET @p_ticketId='" + id + "',@p_comment='" + comment + "',@p_status='" + status
					+ "',@p_createdBy='" + createdBy + "';";

			em.createNamedStoredProcedureQuery("ticketRoutines").setParameter("actionType", "addCommentsGenEnq")
					.setParameter("actionValue", value).execute();
		} catch (Exception e) {
			e.printStackTrace();
			String[] err = serverDao.errorProcedureCall(e);
			resp.setCode(err[0]);
			resp.setMessage(err[1]);
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp, responseHeaders,
				HttpStatus.CREATED);

		logger.info("Method : DAO addCommentsAgent ends");
		//System.out.println("add comment======"+response);
		return response;
	}

	/****************************            Service Request          **********************************************************/
	// View Service Request Ticket Details 

	@SuppressWarnings("unchecked")
	public JsonResponse<List<CallAgentTicketRestModel>> viewAllServiceRequestDao() {

		logger.info("Method in Dao: viewAllServiceRequestDao starts");

		List<CallAgentTicketRestModel> ticketDtlsList = new ArrayList<CallAgentTicketRestModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "viewTicketServiceRequest").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				Object DateTime = null;
				DateTime = DateFormatter.returnStringDate(m[6]);

				CallAgentTicketRestModel callAgentTicketModel = new CallAgentTicketRestModel(null, m[0], null, null,
						null, null, null, null, m[1], m[2], m[3], m[4], m[5], DateTime, m[7], null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null);
				ticketDtlsList.add(callAgentTicketModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<CallAgentTicketRestModel>> resp = new JsonResponse<List<CallAgentTicketRestModel>>();
		resp.setBody(ticketDtlsList);

		logger.info("Method in Dao: viewAllServiceRequestDao ends");
//System.out.println("view dao======="+resp);
		return resp;
	}
	
	/**
	 * DAO Function for modal view service request
	 *
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getServiceRequestAgentById(String id) {
		logger.info("Method : getGeneralAgentById starts");

		List<CallAgentTicketRestModel> form = new ArrayList<CallAgentTicketRestModel>();

		try {
			String value = "SET @p_serviceRequest='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "ModalViewServiceRequest").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
				/*
				 * Object DateTime = null; DateTime =
				 * DateFormatter.returnStringDate(m[17]);
				 */
				CallAgentTicketRestModel callAgentTicketModel = new CallAgentTicketRestModel(m[0], m[1], m[2], m[3], m[4], m[5],
						m[6], m[7], m[8], m[9], m[10], m[11], m[12], null, m[13], m[14], m[15], m[16], null, null, null,
						null, null, null, null, null, null, null, null, null, null);
				form.add(callAgentTicketModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JsonResponse<CallAgentTicketRestModel> resp = new JsonResponse<CallAgentTicketRestModel>();
		resp.setBody(form.get(0));

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<CallAgentTicketRestModel>> response = new ResponseEntity<JsonResponse<CallAgentTicketRestModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getGeneralAgentById end");
		//System.out.println("value"+response);
		return response;
	}
	/*
	 * for modal view marketing ticket
	 */

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getTicketTServiceCommentModalById(String id) {

		logger.info("Method : getTicketTServiceCommentModalById starts");

		List<CallAgentTicketRestModel> ticketType = new ArrayList<CallAgentTicketRestModel>();
		JsonResponse<CallAgentTicketRestModel> resp = new JsonResponse<CallAgentTicketRestModel>();

		try {

			String value = "SET @p_serviceRequest='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "ViewServiceComments").setParameter("actionValue", value)
					.getResultList();

			for (Object[] m : x) {
				Object DateTime = null;
				DateTime = DateFormatter.returnStringDate(m[17]);

				CallAgentTicketRestModel ticket1 = new CallAgentTicketRestModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7],
						m[8], m[9], m[10], m[11], m[12], null, m[13], m[14], m[15], m[16], null, null, null, null, null,
						null, null, null, DateTime, m[18], null, null, null);

				ticketType.add(ticket1);
			}

			resp.setBody(ticketType.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<CallAgentTicketRestModel>> response = new ResponseEntity<JsonResponse<CallAgentTicketRestModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getTicketTServiceCommentModalById ends");
		//System.out.println("service dao================"+response);
		return response;
	}

	/**
	 * DAO Function for add comment model models
	 *
	 */

	public ResponseEntity<JsonResponse<Object>> addServiceCommentsAgent(String id, String comment, String status,
			String createdBy) {
		logger.info("Method : DAO addCommentsAgent starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		try {
			String value = "SET @p_ticketId='" + id + "',@p_comment='" + comment + "',@p_status='" + status
					+ "',@p_createdBy='" + createdBy + "';";

			em.createNamedStoredProcedureQuery("ticketRoutines").setParameter("actionType", "addServiceComments")
					.setParameter("actionValue", value).execute();
		} catch (Exception e) {
			e.printStackTrace();
			String[] err = serverDao.errorProcedureCall(e);
			resp.setCode(err[0]);
			resp.setMessage(err[1]);
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp, responseHeaders,
				HttpStatus.CREATED);

		logger.info("Method : DAO addCommentsAgent ends");
		return response;
	}
	
	/************************            Complaints               *************************************/
	// View Complaint Ticket Details 

	@SuppressWarnings("unchecked")
	public JsonResponse<List<CallAgentTicketRestModel>> viewAllComplaintDao() {

		logger.info("Method in Dao: viewAllComplaintDao starts");

		List<CallAgentTicketRestModel> ticketDtlsList = new ArrayList<CallAgentTicketRestModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "viewTicketComplaint").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				Object DateTime = null;
				DateTime = DateFormatter.returnStringDate(m[6]);

				CallAgentTicketRestModel callAgentTicketModel = new CallAgentTicketRestModel(null, m[0], null, null, null, null,
						null, null, m[1], m[2], m[3], m[4], m[5], DateTime, m[7], null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null);
				ticketDtlsList.add(callAgentTicketModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<CallAgentTicketRestModel>> resp = new JsonResponse<List<CallAgentTicketRestModel>>();
		resp.setBody(ticketDtlsList);

		logger.info("Method in Dao: viewAllComplaintDao ends");
//System.out.println("view dao======="+resp);
		return resp;
	}
	/**
	 * DAO Function for modal view Complaint
	 *
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getComplaintAgentByIdDao(String id) {
		logger.info("Method : getGeneralAgentById starts");

		List<CallAgentTicketRestModel> form = new ArrayList<CallAgentTicketRestModel>();

		try {
			String value = "SET @p_complaintAgent='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "ModalViewComplaint").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
			
				CallAgentTicketRestModel callAgentTicketModel = new CallAgentTicketRestModel(m[0], m[1], m[2], m[3], m[4], m[5],
						m[6], m[7], m[8], m[9], m[10], m[11], m[12], null, m[13], m[14], m[15], m[16], m[17], m[18],
						m[19], m[20], null, null, null, null, null, null, null, null, null);
				form.add(callAgentTicketModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JsonResponse<CallAgentTicketRestModel> resp = new JsonResponse<CallAgentTicketRestModel>();
		resp.setBody(form.get(0));

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<CallAgentTicketRestModel>> response = new ResponseEntity<JsonResponse<CallAgentTicketRestModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getGeneralAgentById end");
		//System.out.println("value"+response);
		return response;

	}
	/*
	 * for modal view Complaint comment ticket
	 */

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getTicketComplaintCommentModalById(String id) {

		logger.info("Method : getTicketComplaintCommentModalById starts");

		List<CallAgentTicketRestModel> ticketType = new ArrayList<CallAgentTicketRestModel>();
		JsonResponse<CallAgentTicketRestModel> resp = new JsonResponse<CallAgentTicketRestModel>();

		try {

			String value = "SET @p_complaintAgent='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "ViewComplaintComments").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				Object DateTime = null;
				DateTime = DateFormatter.returnStringDate(m[21]);
				CallAgentTicketRestModel ticket1 = new CallAgentTicketRestModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7],
						m[8], m[9], m[10], m[11], m[12], null, m[13], m[14], m[15], m[16], m[17], m[18], m[19], m[20],
						null, null, null, null, DateTime, m[22], null, null, null);
				ticketType.add(ticket1);
			}

			resp.setBody(ticketType.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<CallAgentTicketRestModel>> response = new ResponseEntity<JsonResponse<CallAgentTicketRestModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getTicketTServiceCommentModalById ends");

		return response;
	}

	/**
	 * DAO Function for add comment model models
	 *
	 */

	public ResponseEntity<JsonResponse<Object>> addCommentsComplaintAgentById(String id, String comment, String status,
			String createdBy) {
		logger.info("Method : DAO addCommentsComplaintAgentById starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		try {
			String value = "SET @p_ticketId='" + id + "',@p_comment='" + comment + "',@p_status='" + status
					+ "',@p_createdBy='" + createdBy + "';";

			em.createNamedStoredProcedureQuery("ticketRoutines").setParameter("actionType", "addComplaintComments")
					.setParameter("actionValue", value).execute();
		} catch (Exception e) {
			e.printStackTrace();
			String[] err = serverDao.errorProcedureCall(e);
			resp.setCode(err[0]);
			resp.setMessage(err[1]);
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp, responseHeaders,
				HttpStatus.CREATED);

		logger.info("Method : DAO addCommentsComplaintAgentById ends");
		return response;
	}
	
	/************************            Sales              *************************************/	
// View Sales Ticket Details 

	@SuppressWarnings("unchecked")
	public JsonResponse<List<CallAgentTicketRestModel>> viewAllSalesDao() {

		logger.info("Method in Dao: viewAllSalesDao starts");

		List<CallAgentTicketRestModel> ticketDtlsList = new ArrayList<CallAgentTicketRestModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "viewAllSales").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				Object DateTime = null;
				DateTime = DateFormatter.returnStringDate(m[6]);

				CallAgentTicketRestModel callAgentTicketModel = new CallAgentTicketRestModel(null, m[0], null, null, null, null,
						null, null, m[1], m[2], m[3], m[4], m[5], DateTime, m[7], null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null);
				ticketDtlsList.add(callAgentTicketModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<CallAgentTicketRestModel>> resp = new JsonResponse<List<CallAgentTicketRestModel>>();
		resp.setBody(ticketDtlsList);

		logger.info("Method in Dao: viewAllSalesDao ends");
//System.out.println("view dao======="+resp);
		return resp;
	}
	/**
	 * DAO Function for modal view Sales
	 *
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getSaleAgentByIdDao(String id) {
		logger.info("Method : getSaleAgentByIdDao starts");

		List<CallAgentTicketRestModel> form = new ArrayList<CallAgentTicketRestModel>();

		try {
			String value = "SET @p_saleAgent='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "ModalViewSale").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
			
				CallAgentTicketRestModel callAgentTicketModel = new CallAgentTicketRestModel(m[0], m[1], m[2], m[3], m[4], m[5],
						m[6], m[7], m[8], m[9], m[10], m[11], m[12], null, m[13], m[14], m[15], m[16], null, null, null,
						null, null, null, m[17], m[18], null, null, null, null, null);
				form.add(callAgentTicketModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JsonResponse<CallAgentTicketRestModel> resp = new JsonResponse<CallAgentTicketRestModel>();
		resp.setBody(form.get(0));

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<CallAgentTicketRestModel>> response = new ResponseEntity<JsonResponse<CallAgentTicketRestModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getSaleAgentByIdDao end");
		//System.out.println("value"+response);
		return response;
	}
	/*
	 * for modal view Sales comment ticket
	 */

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getTicketSalesCommentModalById(String id) {

		logger.info("Method : getTicketSalesCommentModalById starts");

		List<CallAgentTicketRestModel> ticketType = new ArrayList<CallAgentTicketRestModel>();
		JsonResponse<CallAgentTicketRestModel> resp = new JsonResponse<CallAgentTicketRestModel>();

		try {

			String value = "SET @p_salesAgent='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "ViewSaleComments").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				Object DateTime = null;
				DateTime = DateFormatter.returnStringDate(m[19]);
				CallAgentTicketRestModel ticket1 = new CallAgentTicketRestModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7],
						m[8], m[9], m[10], m[11], m[12], null, m[13], m[14], m[15], m[16], null, null, null, null, null,
						null, m[17], m[18], DateTime, m[20], null, null, null);
				ticketType.add(ticket1);
			}

			resp.setBody(ticketType.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<CallAgentTicketRestModel>> response = new ResponseEntity<JsonResponse<CallAgentTicketRestModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getTicketTServiceCommentModalById ends");

		return response;
	}

	/**
	 * DAO Function for add comment model models
	 *
	 */

	public ResponseEntity<JsonResponse<Object>> addCommentsForSalesAgentById(String id, String comment, String status,
			String createdBy) {
		logger.info("Method : DAO addCommentsForSalesAgentById starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		try {
			String value = "SET @p_ticketId='" + id + "',@p_comment='" + comment + "',@p_status='" + status
					+ "',@p_createdBy='" + createdBy + "';";

			em.createNamedStoredProcedureQuery("ticketRoutines").setParameter("actionType", "addSaleComments")
					.setParameter("actionValue", value).execute();
		} catch (Exception e) {
			e.printStackTrace();
			String[] err = serverDao.errorProcedureCall(e);
			resp.setCode(err[0]);
			resp.setMessage(err[1]);
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp, responseHeaders,
				HttpStatus.CREATED);

		logger.info("Method : DAO addCommentsForSalesAgentById ends");
		return response;
	}
	
	/************************            Marketing  Request               *************************************/
	// View Marketing Ticket Details 

	@SuppressWarnings("unchecked")
	public JsonResponse<List<CallAgentTicketRestModel>> viewAllMarketingRequestDao() {

		logger.info("Method in Dao: viewAllMarketingRequestDao starts");

		List<CallAgentTicketRestModel> ticketDtlsList = new ArrayList<CallAgentTicketRestModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "viewAllMarketingRequest").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				Object DateTime = null;
				DateTime = DateFormatter.returnStringDate(m[6]);

				CallAgentTicketRestModel callAgentTicketModel = new CallAgentTicketRestModel(null, m[0], null, null, null, null,
						null, null, m[1], m[2], m[3], m[4], m[5], DateTime, m[7], null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null);
				ticketDtlsList.add(callAgentTicketModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<CallAgentTicketRestModel>> resp = new JsonResponse<List<CallAgentTicketRestModel>>();
		resp.setBody(ticketDtlsList);

		logger.info("Method in Dao: viewAllMarketingRequestDao ends");
//System.out.println("view dao======="+resp);
		return resp;
	}
	/**
	 * DAO Function for modal view Marketing Request
	 *
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getMarketingRequestAgentByIdDao(String id) {
		logger.info("Method : getMarketingRequestAgentByIdDao starts");

		List<CallAgentTicketRestModel> form = new ArrayList<CallAgentTicketRestModel>();

		try {
			String value = "SET @p_marketAgent='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "ModalViewMarketingRequest").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
			
				CallAgentTicketRestModel callAgentTicketModel = new CallAgentTicketRestModel(m[0], m[1], m[2], m[3], m[4], m[5],
						m[6], m[7], m[8], m[9], m[10], m[11], m[12], null, m[13], m[14], m[15], m[16], null, null, null,
						null, m[17], m[18], null, null, null, null, null, null, null);
				form.add(callAgentTicketModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JsonResponse<CallAgentTicketRestModel> resp = new JsonResponse<CallAgentTicketRestModel>();
		resp.setBody(form.get(0));

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<CallAgentTicketRestModel>> response = new ResponseEntity<JsonResponse<CallAgentTicketRestModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getMarketingRequestAgentByIdDao end");
		//System.out.println("value"+response);
		return response;
	}
	/*
	 * for modal view Marketing comment ticket
	 */

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getTicketMarketingCommentModalById(String id) {

		logger.info("Method : getTicketMarketingCommentModalById starts");

		List<CallAgentTicketRestModel> ticketType = new ArrayList<CallAgentTicketRestModel>();
		JsonResponse<CallAgentTicketRestModel> resp = new JsonResponse<CallAgentTicketRestModel>();

		try {

			String value = "SET @p_marketAgent='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("ticketRoutines")
					.setParameter("actionType", "ViewMarketingComments").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				Object DateTime = null;
				DateTime = DateFormatter.returnStringDate(m[19]);
				CallAgentTicketRestModel ticket1 = new CallAgentTicketRestModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7],
						m[8], m[9], m[10], m[11], m[12], null, m[13], m[14], m[15], m[16], null, null, null, null,
						m[17], m[18], null, null, DateTime, m[20], null, null, null);
				ticketType.add(ticket1);
			}

			resp.setBody(ticketType.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<CallAgentTicketRestModel>> response = new ResponseEntity<JsonResponse<CallAgentTicketRestModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getTicketTServiceCommentModalById ends");

		return response;
	}

	/**
	 * DAO Function for add comment model models
	 *
	 */

	public ResponseEntity<JsonResponse<Object>> addCommentsMarketingAgentById(String id, String comment, String status,
			String createdBy) {
		logger.info("Method : DAO addCommentsMarketingAgentById starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		try {
			String value = "SET @p_ticketId='" + id + "',@p_comment='" + comment + "',@p_status='" + status
					+ "',@p_createdBy='" + createdBy + "';";

			em.createNamedStoredProcedureQuery("ticketRoutines").setParameter("actionType", "addMarketingComments")
					.setParameter("actionValue", value).execute();
		} catch (Exception e) {
			e.printStackTrace();
			String[] err = serverDao.errorProcedureCall(e);
			resp.setCode(err[0]);
			resp.setMessage(err[1]);
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp, responseHeaders,
				HttpStatus.CREATED);

		logger.info("Method : DAO addCommentsMarketingAgentById ends");
		return response;
	}
	
	
}
