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


import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.ticket.model.CallAgentTicketRestModel;
import nirmalya.aatithya.restmodule.ticket.model.RestManageSupervisorModel;

@Repository
public class RestManageSupervisorDao {
	
	Logger logger = LoggerFactory.getLogger(CallAgentTicketDao.class);
	
	@Autowired
	ServerDao serverDao;
	
	@Autowired
	EntityManager em;
	
	/************************           Drop Down             *************************************/
	/**
	 * DAO Function for drop down models
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getStatusName() {
		logger.info("Method : getStatusName starts");
		List<DropDownModel> callList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("manageSupervisorRoutines").setParameter("actionType", "getStatusName")
					.setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				callList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getStatusName end");
		return callList;
	}
	

	/**
	 * DAO Function for drop down models
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getSupportsName() {
		logger.info("Method : getSupportsName starts");
		List<DropDownModel> callList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("manageSupervisorRoutines").setParameter("actionType", "getSupportsName")
					.setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				callList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getSupportsName end");
		return callList;
	}
	/************************            General  Enquiry               *************************************/
	
	// View General Enquiry Details 

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestManageSupervisorModel>> viewAllGenEnqDao() {

		logger.info("Method in Dao: viewAllGenEnqDao starts");

		List<RestManageSupervisorModel> ticketDtlsList = new ArrayList<RestManageSupervisorModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("manageSupervisorRoutines")
					.setParameter("actionType", "viewTicketGenEnq").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
			
				Object dateTime = null;
				dateTime = DateFormatter.returnStringDate(m[6]);
				RestManageSupervisorModel genEnqModel = new RestManageSupervisorModel(m[0], m[1], m[2], m[3], m[4], m[5], dateTime, null, null,
						null, null, null, null, null, null, null, null, null, null, m[7], null, null, null, null);
				ticketDtlsList.add(genEnqModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<RestManageSupervisorModel>> resp = new JsonResponse<List<RestManageSupervisorModel>>();
		resp.setBody(ticketDtlsList);

		logger.info("Method in Dao: viewAllGenEnqDao ends");
     System.out.println("view dao======="+resp);
		return resp;
	}
	
	/**
	 * DAO Function for modal view General Enquiry
	 *
	 */

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestManageSupervisorModel>> getGeneralSupervisorByIdDao(String id) {

		logger.info("Method : getGeneralTicketTypeModalById starts");

		List<RestManageSupervisorModel> ticketType = new ArrayList<RestManageSupervisorModel>();
		JsonResponse<RestManageSupervisorModel> resp = new JsonResponse<RestManageSupervisorModel>();

		try {

			String value = "SET @p_GeneralQueryId='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("manageSupervisorRoutines")
					.setParameter("actionType", "ModalViewGeneralEnquiry").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				RestManageSupervisorModel ticket1 = new RestManageSupervisorModel(m[0], m[1], m[2], m[3], null, null, null, m[4], m[5],
						m[6], null, null, null, null, null, null, m[7], null, null, null, null, null, null, null);

				ticketType.add(ticket1);
			}

			resp.setBody(ticketType.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestManageSupervisorModel>> response = new ResponseEntity<JsonResponse<RestManageSupervisorModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getGeneralTicketTypeModalById ends");

		return response;
	}

	/*
	 * for modal view general comment ticket
	 */

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestManageSupervisorModel>> getGeneralSupervisorCommentModalByIdDao(String id) {

		logger.info("Method : getTicketTGeneralCommentModalById starts");

		List<RestManageSupervisorModel> ticketType = new ArrayList<RestManageSupervisorModel>();
		JsonResponse<RestManageSupervisorModel> resp = new JsonResponse<RestManageSupervisorModel>();

		try {
			//System.out.println("value"+id);
			String value = "SET @p_GeneralId='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("manageSupervisorRoutines")
					.setParameter("actionType", "ViewCommentsGenEnq").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				Object lastTime = null;
				lastTime = DateFormatter.returnStringDate(m[3]);
				RestManageSupervisorModel ticket1 = new RestManageSupervisorModel(m[0], null, null, null, null, m[1], null, null, null,
						null, null, null, null, null, null, null, m[2], lastTime, m[4], null, null, null, null, null);

				ticketType.add(ticket1);
			}

			resp.setBody(ticketType.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestManageSupervisorModel>> response = new ResponseEntity<JsonResponse<RestManageSupervisorModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getTicketTServiceCommentModalById ends");
		//System.out.println("comment view ==============="+response);
		return response;
	}

	/**
	 * DAO Function for add comment model models
	 *
	 */

	public ResponseEntity<JsonResponse<Object>> addCommentsGeneralSupervisorByIdDao(String id, String comment, String status,
			String createdBy) {
		logger.info("Method : DAO addCommentsGeneralAgentById starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		try {
			String value = "SET @p_ticketId='" + id + "',@p_comment='" + comment + "',@p_status='" + status
					+ "',@p_createdBy='" + createdBy + "';";

			em.createNamedStoredProcedureQuery("manageSupervisorRoutines").setParameter("actionType", "addCommentsGenEnq")
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
	/**
	 * DAO Function for assignSupports model models
	 *
	 */

	public ResponseEntity<JsonResponse<Object>> assignSupportsGenEnqDao(String id, String comment, String supports, String createdBy,String CallQueue) {
		logger.info("Method : DAO assignSupports starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		try {
			
			String value = "SET @p_ticketId='" + id + "',@p_comments='" + comment + "',@p_supports='" + supports + "',@p_createdBy='" + createdBy + "',@p_CallQueue='" + CallQueue + "';";
			//System.out.println("add asign value======"+value);
			em.createNamedStoredProcedureQuery("manageSupervisorRoutines").setParameter("actionType", "assignSupportsGenEnq")
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

		logger.info("Method : DAO assignSupports ends");
		return response;
	}
	
	/****************************            Service Request          **********************************************************/
	// View Service Request Ticket Details 

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestManageSupervisorModel>> viewAllSerReqDao() {

		logger.info("Method in Dao: viewAllSerReqDao starts");

		List<RestManageSupervisorModel> ticketDtlsList = new ArrayList<RestManageSupervisorModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("manageSupervisorRoutines")
					.setParameter("actionType", "viewTicketSerReq").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				Object DateTime = null;
				DateTime = DateFormatter.returnStringDate(m[6]);

				RestManageSupervisorModel callAgentTicketModel = new RestManageSupervisorModel(m[0], m[1], m[2], m[3], m[4], m[5], DateTime, null, null,
						null, null, null, null, null, null, null, null, null, null, m[7], null, null, null, null);
				ticketDtlsList.add(callAgentTicketModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<RestManageSupervisorModel>> resp = new JsonResponse<List<RestManageSupervisorModel>>();
		resp.setBody(ticketDtlsList);

		logger.info("Method in Dao: viewAllSerReqDao ends");
//System.out.println("view dao======="+resp);
		return resp;
	}
	

	/**
	 * DAO Function for modal view service request
	 *
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestManageSupervisorModel>> modalSupervisorViewSerReqDao(String id) {
		logger.info("Method : getGeneralAgentById starts");

		List<RestManageSupervisorModel> form = new ArrayList<RestManageSupervisorModel>();

		try {
			String value = "SET @p_ServiceRequestId='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("manageSupervisorRoutines")
					.setParameter("actionType", "ModalViewServiceRequest").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
				/*
				 * Object DateTime = null; DateTime =
				 * DateFormatter.returnStringDate(m[17]);
				 */
				RestManageSupervisorModel callAgentTicketModel = new RestManageSupervisorModel(m[0], m[1], m[2], m[3], null, null, null, m[4], m[5],
						m[6], m[7], null, null, null, null, null, m[8], null, null, null, null, null, null, null);
				form.add(callAgentTicketModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JsonResponse<RestManageSupervisorModel> resp = new JsonResponse<RestManageSupervisorModel>();
		resp.setBody(form.get(0));

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestManageSupervisorModel>> response = new ResponseEntity<JsonResponse<RestManageSupervisorModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getGeneralAgentById end");
		//System.out.println("value"+response);
		return response;
	}
	/*
	 * for modal view service request
	 */

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestManageSupervisorModel>> getcommentSupervisorModalViewSerReqById(String id) {

		logger.info("Method : getTicketTServiceCommentModalById starts");

		List<RestManageSupervisorModel> ticketType = new ArrayList<RestManageSupervisorModel>();
		JsonResponse<RestManageSupervisorModel> resp = new JsonResponse<RestManageSupervisorModel>();

		try {

			String value = "SET @p_ServiceRequestId='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("manageSupervisorRoutines")
					.setParameter("actionType", "ViewServiceComments").setParameter("actionValue", value)
					.getResultList();

			for (Object[] m : x) {
				Object lastTime = null;
				lastTime = DateFormatter.returnStringDate(m[4]);

				RestManageSupervisorModel ticket1 = new RestManageSupervisorModel(m[0], null, m[1], null, null, m[2], null, null, null,
						null, null, null, null, null, null, null, m[3], lastTime, m[5], null, null, null, null, null);

				ticketType.add(ticket1);
			}

			resp.setBody(ticketType.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestManageSupervisorModel>> response = new ResponseEntity<JsonResponse<RestManageSupervisorModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getTicketTServiceCommentModalById ends");
		//System.out.println("service dao================"+response);
		return response;
	}

	/**
	 * DAO Function for add comment model models
	 *
	 */

	public ResponseEntity<JsonResponse<Object>> addServiceCommentsSupervisor(String id, String comment, String status,
			String createdBy) {
		logger.info("Method : DAO addCommentsAgent starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		try {
			String value = "SET @p_ticketId='" + id + "',@p_comment='" + comment + "',@p_status='" + status
					+ "',@p_createdBy='" + createdBy + "';";

			em.createNamedStoredProcedureQuery("manageSupervisorRoutines").setParameter("actionType", "addServiceComments")
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
	/**
	 * DAO Function for assignSupports model models
	 *
	 */

	public ResponseEntity<JsonResponse<Object>> assignSupportsSerReqDao(String id, String comment, String supports, String createdBy,String CallQueue) {
		logger.info("Method : DAO assignSupports starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		try {
			
			String value = "SET @p_ticketId='" + id + "',@p_comments='" + comment + "',@p_supports='" + supports + "',@p_createdBy='" + createdBy + "',@p_CallQueue='" + CallQueue + "';";
			//System.out.println("add asign value======"+value);
			em.createNamedStoredProcedureQuery("manageSupervisorRoutines").setParameter("actionType", "assignSupportsSerReq")
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

		logger.info("Method : DAO assignSupports ends");
		return response;
	}
	/************************            Complaints               *************************************/
	// View Complaint Ticket Details 

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestManageSupervisorModel>> viewAllCompDao() {

		logger.info("Method in Dao: viewAllCompDao starts");

		List<RestManageSupervisorModel> ticketDtlsList = new ArrayList<RestManageSupervisorModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("manageSupervisorRoutines")
					.setParameter("actionType", "viewTicketComp").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				Object DateTime = null;
				DateTime = DateFormatter.returnStringDate(m[6]);

				RestManageSupervisorModel callAgentTicketModel = new RestManageSupervisorModel(m[0], m[1], m[2], m[3], m[4], m[5], DateTime, null, null,
						null, null, null, null, null, null, null, null, null, null, m[7], null, null, null, null);
				ticketDtlsList.add(callAgentTicketModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<RestManageSupervisorModel>> resp = new JsonResponse<List<RestManageSupervisorModel>>();
		resp.setBody(ticketDtlsList);

		logger.info("Method in Dao: viewAllCompDao ends");
//System.out.println("view dao======="+resp);
		return resp;
		
	}	
	

	/**
	 * DAO Function for modal comp request
	 *
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestManageSupervisorModel>> modalSupervisorViewComplaintDao(String id) {
		logger.info("Method : getGeneralAgentById starts");

		List<RestManageSupervisorModel> form = new ArrayList<RestManageSupervisorModel>();

		try {
			String value = "SET @p_ComplaintsId='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("manageSupervisorRoutines")
					.setParameter("actionType", "ModalViewComplaint").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
				/*
				 * Object DateTime = null; DateTime =
				 * DateFormatter.returnStringDate(m[17]);
				 */
				RestManageSupervisorModel callAgentTicketModel = new RestManageSupervisorModel(m[0], m[1], m[2], m[3], null, null, null, m[4], m[5],
						m[6], m[7], m[8], m[9], null, null, null, m[10], null, null, null, null, null, null, null);
				form.add(callAgentTicketModel);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JsonResponse<RestManageSupervisorModel> resp = new JsonResponse<RestManageSupervisorModel>();
		resp.setBody(form.get(0));

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestManageSupervisorModel>> response = new ResponseEntity<JsonResponse<RestManageSupervisorModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getGeneralAgentById end");
		//System.out.println("value"+response);
		return response;
	}
	/*
	 * for modal view comment complaint
	 */

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestManageSupervisorModel>> getcommentSupervisorModalViewComByIdDao(String id) {

		logger.info("Method : getTicketTServiceCommentModalById starts");

		List<RestManageSupervisorModel> ticketType = new ArrayList<RestManageSupervisorModel>();
		JsonResponse<RestManageSupervisorModel> resp = new JsonResponse<RestManageSupervisorModel>();

		try {

			String value = "SET @p_ComplaintsId='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("manageSupervisorRoutines")
					.setParameter("actionType", "viewCommentComplaint").setParameter("actionValue", value)
					.getResultList();

			for (Object[] m : x) {
				Object lastTime = null;
				lastTime = DateFormatter.returnStringDate(m[4]);

				RestManageSupervisorModel ticket1 = new RestManageSupervisorModel(m[0], null, m[1], null, null, m[2], null, null, null,
						null, null, null, null, null, null, null, m[3], lastTime, m[5], null, null, null, null, null);

				ticketType.add(ticket1);
			}

			resp.setBody(ticketType.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestManageSupervisorModel>> response = new ResponseEntity<JsonResponse<RestManageSupervisorModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getTicketTServiceCommentModalById ends");
		//System.out.println("service dao================"+response);
		return response;
	}

	/**
	 * DAO Function for add comment model models
	 *
	 */

	public ResponseEntity<JsonResponse<Object>> addCompCommentsSupervisorDao(String id, String comment, String status,
			String createdBy) {
		logger.info("Method : DAO addCommentsAgent starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		try {
			String value = "SET @p_ticketId='" + id + "',@p_comment='" + comment + "',@p_status='" + status
					+ "',@p_createdBy='" + createdBy + "';";

			em.createNamedStoredProcedureQuery("manageSupervisorRoutines").setParameter("actionType", "addComplaintComments")
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
	/**
	 * DAO Function for assignSupports model models
	 *
	 */

	public ResponseEntity<JsonResponse<Object>> assignSupportsCompDao(String id, String comment, String supports, String createdBy,String CallQueue) {
		logger.info("Method : DAO assignSupports starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		try {
			
			String value = "SET @p_ticketId='" + id + "',@p_comments='" + comment + "',@p_supports='" + supports + "',@p_createdBy='" + createdBy + "',@p_CallQueue='" + CallQueue + "';";
			//System.out.println("add asign value======"+value);
			em.createNamedStoredProcedureQuery("manageSupervisorRoutines").setParameter("actionType", "assignSupportsComp")
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

		logger.info("Method : DAO assignSupports ends");
		return response;
	}	
	/************************            Sales              *************************************/	
	//View Sales Ticket Details 

		@SuppressWarnings("unchecked")
		public JsonResponse<List<RestManageSupervisorModel>> viewAllSaleDao() {

			logger.info("Method in Dao: viewAllSaleDao starts");

			List<RestManageSupervisorModel> ticketDtlsList = new ArrayList<RestManageSupervisorModel>();

			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("manageSupervisorRoutines")
						.setParameter("actionType", "viewTicketSale").setParameter("actionValue", "").getResultList();

				for (Object[] m : x) {
					Object DateTime = null;
					DateTime = DateFormatter.returnStringDate(m[6]);

					RestManageSupervisorModel callAgentTicketModel = new RestManageSupervisorModel(m[0], m[1], m[2], m[3], m[4], m[5], DateTime, null, null,
							null, null, null, null, null, null, null, null, null, null,  m[7], null, null, null, null);
					ticketDtlsList.add(callAgentTicketModel);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			JsonResponse<List<RestManageSupervisorModel>> resp = new JsonResponse<List<RestManageSupervisorModel>>();
			resp.setBody(ticketDtlsList);

			logger.info("Method in Dao: viewAllSaleDao ends");
	//System.out.println("view dao======="+resp);
			return resp;
		}

		/**
		 * DAO Function for modal Sale request
		 *
		 */
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<RestManageSupervisorModel>> modalSupervisorViewSaleDao(String id) {
			logger.info("Method : getGeneralAgentById starts");

			List<RestManageSupervisorModel> form = new ArrayList<RestManageSupervisorModel>();

			try {
				String value = "SET @p_SalesId='" + id + "';";

				List<Object[]> x = em.createNamedStoredProcedureQuery("manageSupervisorRoutines")
						.setParameter("actionType", "ModalSalesView").setParameter("actionValue", value).getResultList();
				for (Object[] m : x) {
					/*
					 * Object DateTime = null; DateTime =
					 * DateFormatter.returnStringDate(m[17]);
					 */
					RestManageSupervisorModel callAgentTicketModel = new RestManageSupervisorModel(m[0], m[1], m[2], m[3], null, null, null, m[4], m[5],
							m[6], m[7], null, null, m[8], m[9], null, m[10], null, null, null, null, null, null, null);
					form.add(callAgentTicketModel);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JsonResponse<RestManageSupervisorModel> resp = new JsonResponse<RestManageSupervisorModel>();
			resp.setBody(form.get(0));

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("MyResponseHeader", "MyValue");

			ResponseEntity<JsonResponse<RestManageSupervisorModel>> response = new ResponseEntity<JsonResponse<RestManageSupervisorModel>>(
					resp, responseHeaders, HttpStatus.CREATED);

			logger.info("Method : getGeneralAgentById end");
			//System.out.println("value"+response);
			return response;
		}
		/*
		 * for modal view comment sale
		 */

		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<RestManageSupervisorModel>> getcommentSupervisorModalViewSaleByIdDao(String id) {

			logger.info("Method : getTicketTServiceCommentModalById starts");

			List<RestManageSupervisorModel> ticketType = new ArrayList<RestManageSupervisorModel>();
			JsonResponse<RestManageSupervisorModel> resp = new JsonResponse<RestManageSupervisorModel>();

			try {

				String value = "SET @p_SalesId='" + id + "';";

				List<Object[]> x = em.createNamedStoredProcedureQuery("manageSupervisorRoutines")
						.setParameter("actionType", "viewCommentSale").setParameter("actionValue", value)
						.getResultList();

				for (Object[] m : x) {
					Object lastTime = null;
					lastTime = DateFormatter.returnStringDate(m[4]);

					RestManageSupervisorModel ticket1 = new RestManageSupervisorModel(m[0], null, m[1], null, null, m[2], null, null, null,
							null, null, null, null, null, null, null, m[3], lastTime, m[5], null, null, null, null, null);

					ticketType.add(ticket1);
				}

				resp.setBody(ticketType.get(0));
			} catch (Exception e) {
				e.printStackTrace();
			}

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("MyResponseHeader", "MyValue");

			ResponseEntity<JsonResponse<RestManageSupervisorModel>> response = new ResponseEntity<JsonResponse<RestManageSupervisorModel>>(
					resp, responseHeaders, HttpStatus.CREATED);

			logger.info("Method : getTicketTServiceCommentModalById ends");
			//System.out.println("service dao================"+response);
			return response;
		}

		/**
		 * DAO Function for add comment model models
		 *
		 */

		public ResponseEntity<JsonResponse<Object>> addSaleCommentsSupervisorDao(String id, String comment, String status,
				String createdBy) {
			logger.info("Method : DAO addCommentsAgent starts");

			JsonResponse<Object> resp = new JsonResponse<Object>();
			resp.setMessage("");
			resp.setCode("");

			try {
				String value = "SET @p_ticketId='" + id + "',@p_comment='" + comment + "',@p_status='" + status
						+ "',@p_createdBy='" + createdBy + "';";

				em.createNamedStoredProcedureQuery("manageSupervisorRoutines").setParameter("actionType", "addSaleComments")
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
		/**
		 * DAO Function for assignSupports model models
		 *
		 */

		public ResponseEntity<JsonResponse<Object>> assignSupportsSaleDao(String id, String comment, String supports, String createdBy,String CallQueue) {
			logger.info("Method : DAO assignSupports starts");

			JsonResponse<Object> resp = new JsonResponse<Object>();
			resp.setMessage("");
			resp.setCode("");

			try {
				
				String value = "SET @p_ticketId='" + id + "',@p_comments='" + comment + "',@p_supports='" + supports + "',@p_createdBy='" + createdBy + "',@p_CallQueue='" + CallQueue + "';";
				//System.out.println("add asign value======"+value);
				em.createNamedStoredProcedureQuery("manageSupervisorRoutines").setParameter("actionType", "assignSupportsSale")
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

			logger.info("Method : DAO assignSupports ends");
			return response;
		}
		/************************            Marketing  Request               *************************************/
		// View Marketing Ticket Details 

		@SuppressWarnings("unchecked")
		public JsonResponse<List<RestManageSupervisorModel>> viewAllMktgReqDao() {

			logger.info("Method in Dao: viewAllMktgReqDao starts");

			List<RestManageSupervisorModel> ticketDtlsList = new ArrayList<RestManageSupervisorModel>();

			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("manageSupervisorRoutines")
						.setParameter("actionType", "viewTicketMktgReq").setParameter("actionValue", "").getResultList();

				for (Object[] m : x) {
					Object DateTime = null;
					DateTime = DateFormatter.returnStringDate(m[6]);

					RestManageSupervisorModel callAgentTicketModel = new RestManageSupervisorModel(m[0], m[1], m[2], m[3], m[4], m[5], DateTime, null, null,
							null, null, null, null, null, null, null, null, null, null,  m[7],null, null, null, null);
					ticketDtlsList.add(callAgentTicketModel);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			JsonResponse<List<RestManageSupervisorModel>> resp = new JsonResponse<List<RestManageSupervisorModel>>();
			resp.setBody(ticketDtlsList);

			logger.info("Method in Dao: viewAllMktgReqDao ends");
	//System.out.println("view dao======="+resp);
			return resp;
		}
		/**
		 * DAO Function for modal MktgReq request
		 *
		 */
		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<RestManageSupervisorModel>> modalSupervisorViewMktgReqDao(String id) {
			logger.info("Method : getMktgReqById starts");

			List<RestManageSupervisorModel> form = new ArrayList<RestManageSupervisorModel>();

			try {
				String value = "SET @p_MarketingId='" + id + "';";

				List<Object[]> x = em.createNamedStoredProcedureQuery("manageSupervisorRoutines")
						.setParameter("actionType", "ModalMktgReqView").setParameter("actionValue", value).getResultList();
				for (Object[] m : x) {
					/*
					 * Object DateTime = null; DateTime =
					 * DateFormatter.returnStringDate(m[17]);
					 */
					RestManageSupervisorModel callAgentTicketModel = new RestManageSupervisorModel(m[0], m[1], m[2], m[3], null, null, null, m[4], m[5],
							m[6], m[7], null, null, m[8], null, m[9], m[10], null, null, null, null, null, null, null);
					form.add(callAgentTicketModel);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JsonResponse<RestManageSupervisorModel> resp = new JsonResponse<RestManageSupervisorModel>();
			resp.setBody(form.get(0));

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("MyResponseHeader", "MyValue");

			ResponseEntity<JsonResponse<RestManageSupervisorModel>> response = new ResponseEntity<JsonResponse<RestManageSupervisorModel>>(
					resp, responseHeaders, HttpStatus.CREATED);

			logger.info("Method : getGeneralAgentById end");
			//System.out.println("value"+response);
			return response;
		}
		/*
		 * for modal view comment MktgReq
		 */

		@SuppressWarnings("unchecked")
		public ResponseEntity<JsonResponse<RestManageSupervisorModel>> getcommentSupervisorModalViewMktgReqByIdDao(String id) {

			logger.info("Method : getTicketTMktgReqCommentModalById starts");

			List<RestManageSupervisorModel> ticketType = new ArrayList<RestManageSupervisorModel>();
			JsonResponse<RestManageSupervisorModel> resp = new JsonResponse<RestManageSupervisorModel>();

			try {

				String value = "SET @p_MarketingId='" + id + "';";

				List<Object[]> x = em.createNamedStoredProcedureQuery("manageSupervisorRoutines")
						.setParameter("actionType", "viewCommentMktgReq").setParameter("actionValue", value)
						.getResultList();

				for (Object[] m : x) {
					Object lastTime = null;
					lastTime = DateFormatter.returnStringDate(m[4]);

					RestManageSupervisorModel ticket1 = new RestManageSupervisorModel(m[0], null, m[1], null, null, m[2], null, null, null,
							null, null, null, null, null, null, null, m[3], lastTime, m[5], null, null, null, null, null);

					ticketType.add(ticket1);
				}

				resp.setBody(ticketType.get(0));
			} catch (Exception e) {
				e.printStackTrace();
			}

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("MyResponseHeader", "MyValue");

			ResponseEntity<JsonResponse<RestManageSupervisorModel>> response = new ResponseEntity<JsonResponse<RestManageSupervisorModel>>(
					resp, responseHeaders, HttpStatus.CREATED);

			logger.info("Method : getTicketTMktgReqCommentModalById ends");
			//System.out.println("service dao================"+response);
			return response;
		}

		/**
		 * DAO Function for add comment model models
		 *
		 */

		public ResponseEntity<JsonResponse<Object>> addMktgReqCommentsSupervisorDao(String id, String comment, String status,
				String createdBy) {
			logger.info("Method : DAO addCommentsAgent starts");

			JsonResponse<Object> resp = new JsonResponse<Object>();
			resp.setMessage("");
			resp.setCode("");

			try {
				String value = "SET @p_ticketId='" + id + "',@p_comment='" + comment + "',@p_status='" + status
						+ "',@p_createdBy='" + createdBy + "';";

				em.createNamedStoredProcedureQuery("manageSupervisorRoutines").setParameter("actionType", "addMktgReqComments")
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
		/**
		 * DAO Function for assignSupports model models
		 *
		 */

		public ResponseEntity<JsonResponse<Object>> assignSupportsMktgReqDao(String id, String comment, String supports, String createdBy,String CallQueue) {
			logger.info("Method : DAO assignSupports starts");

			JsonResponse<Object> resp = new JsonResponse<Object>();
			resp.setMessage("");
			resp.setCode("");

			try {
				
				String value = "SET @p_ticketId='" + id + "',@p_comments='" + comment + "',@p_supports='" + supports + "',@p_createdBy='" + createdBy + "',@p_CallQueue='" + CallQueue + "';";
				//System.out.println("add asign value======"+value);
				em.createNamedStoredProcedureQuery("manageSupervisorRoutines").setParameter("actionType", "assignSupportsMktgReq")
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

			logger.info("Method : DAO assignSupports ends");
			return response;
		}
}
