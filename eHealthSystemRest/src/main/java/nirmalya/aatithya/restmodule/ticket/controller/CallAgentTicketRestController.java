package nirmalya.aatithya.restmodule.ticket.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.ticket.dao.CallAgentTicketDao;
import nirmalya.aatithya.restmodule.ticket.model.CallAgentTicketRestModel;

/**
 * @author Nirmalya Labs
 *
 */
@RestController
@RequestMapping("ticket")
public class CallAgentTicketRestController {
	Logger logger = LoggerFactory.getLogger(CallAgentTicketRestController.class);
	@Autowired
	CallAgentTicketDao callAgentTicketDao;
	/**************************             Drop Down              ***************************************************** */
	/**
	 * returns industrial type for drop down model
	 *
	 */
	@RequestMapping(value = "/getIndustrialType", method = { RequestMethod.GET })
	public List<DropDownModel> getIndustrialType() {
		logger.info("Method : getIndustrialType starts");

		logger.info("Method : getIndustrialType end");
		return callAgentTicketDao.getIndustrialType();
	}

	/**
	 * returns user location for drop down model
	 *
	 */
	@RequestMapping(value = "/getUserLocationForTicket", method = { RequestMethod.GET })
	public List<DropDownModel> getUserLocationForTicket() {
		logger.info("Method : getUserLocationForTicket starts");

		logger.info("Method : getUserLocationForTicket end");
		return callAgentTicketDao.getUserLocationForTicket();
	}

	/**
	 * returns user type for drop down model
	 *
	 */
	@RequestMapping(value = "/getCostmerType", method = { RequestMethod.GET })
	public List<DropDownModel> getCostmerType() {
		logger.info("Method : getCostmerType starts");

		logger.info("Method : getCostmerType end");
		return callAgentTicketDao.getCostmerType();
	}

	/**
	 * returns call queue for drop down model
	 *
	 */
	@RequestMapping(value = "/getCallQueueForTicket", method = { RequestMethod.GET })
	public List<DropDownModel> getCallQueueForTicket() {
		logger.info("Method : getCallQueueForTicket starts");

		logger.info("Method : getCallQueueForTicket end");
		return callAgentTicketDao.getCallQueueForTicket();
	}

	/**
	 * returns call queue for drop down model
	 *
	 */
	@RequestMapping(value = "/getProductTypeList", method = { RequestMethod.GET })
	public List<DropDownModel> getProductTypeList() {
		logger.info("Method : getProductTypeList starts");

		logger.info("Method : getProductTypeList end");
		return callAgentTicketDao.getProductTypeList();
	}

	/**
	 * returns service center for drop down model
	 *
	 */
	@RequestMapping(value = "/getServiceCenterList", method = { RequestMethod.GET })
	public List<DropDownModel> getServiceCenterList() {
		logger.info("Method : getServiceCenterList starts");

		logger.info("Method : getServiceCenterList end");
		return callAgentTicketDao.getServiceCenterList();
	}

	/**
	 * returns service center for drop down model
	 *
	 */
	@RequestMapping(value = "/getComplaintCategory", method = { RequestMethod.GET })
	public List<DropDownModel> getComplaintCategory() {
		logger.info("Method : getComplaintCategory starts");

		logger.info("Method : getComplaintCategory end");
		return callAgentTicketDao.getComplaintCategory();
	}

	/**
	 * returns service center for drop down model
	 *
	 */
	@RequestMapping(value = "/getComplaintLocation", method = { RequestMethod.GET })
	public List<DropDownModel> getComplaintLocation() {
		logger.info("Method : getComplaintLocation starts");

		logger.info("Method : getComplaintLocation end");
		return callAgentTicketDao.getComplaintLocation();
	}
	/**
	 * returns status type for drop down model
	 *
	 */
	@RequestMapping(value = "/getStatusForTicket", method = { RequestMethod.GET })
	public List<DropDownModel> getStatusForTicket() {
		logger.info("Method : getStatusForTicket starts");

		logger.info("Method : getStatusForTicket end");
		return callAgentTicketDao.getStatusForTicket();
	}
	/**
	 * return for drop down of call reason through onchange
	 */
	@RequestMapping(value = "/getCallReasonList", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getCallReasonList(@RequestParam String callQueue) {
		logger.info("Method in rest: getCallReasonList starts");

		logger.info("Method in rest: getCallReasonList ends");
		return callAgentTicketDao.getCallReasonList(callQueue);
	}
	/**************************             Call Details/All Tickets              ***************************************************** */
	/*
	* View Agent Ticket Details
	*
	*/

	@GetMapping(value = "rest-viewAgentTicket")
	public JsonResponse<List<CallAgentTicketRestModel>> viewTicketAgentDtls() {
		logger.info("Method : viewTicketAgentDtls starts");

		logger.info("Method : viewTicketAgentDtls ends");

		return callAgentTicketDao.viewTicketAgentDtlsDao();
	}
	 
	/**************************             Add Call Details              ***************************************************** */
	/**
	 * returns for add escalation matrix
	 *
	 */
	@RequestMapping(value = "/rest-addAgentTicket", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> addAgentTicket(@RequestBody CallAgentTicketRestModel form) {
		logger.info("Method : addAgentTicket starts");
//System.out.println("rest con+++"+form);
		logger.info("Method : addAgentTicket end");

		return callAgentTicketDao.addAgentTicketDao(form);

	}

	/**
	 * return for dropdown of auto fill of customer name
	 */
	@RequestMapping(value = "/getCustomerAutofillMob", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<CallAgentTicketRestModel>>> getCustomerAutofillMob(@RequestParam String id) {
		logger.info("Method : getCustomerAutofillMob starts");

		logger.info("Method : getCustomerAutofillMob ends");
		return callAgentTicketDao.getCustomerAutofillMob(id);
	}
	/**************************             General Enquiry              ***************************************************** */
	
	/*
	 * for All view general enquiry ticket master agent
	 */
	@RequestMapping(value = "/rest-viewAllGeneralEnquiry")
	public JsonResponse<List<CallAgentTicketRestModel>> viewAllGeneralEnquiry() {
		logger.info("Method : viewAllGeneralEnquiry starts");
		logger.info("Method : viewAllGeneralEnquiry ends");
		return callAgentTicketDao.viewAllGeneralEnquiryDao();
	}
	
	/**
	 * returns General Enquiry modal  view agent
	 *
	 */
	@RequestMapping(value = "/getGeneralEnquiryAgentById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getGeneralEnquiryAgentById(@RequestParam String id
			) {
		logger.info("Method : getGeneralEnquiryAgentById starts");

		logger.info("Method : getGeneralEnquiryAgentById end");

		return callAgentTicketDao.getGeneralEnquiryAgentByIdDao(id);
	}
	

	/*
	 * for All model view service Request comments ticket Master
	 */
	@RequestMapping(value = "/rest-getTicketTGeneralCommentModalById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getTicketTGeneralCommentModalById(
			@RequestParam String id) {
		logger.info("Method : getTicketTGeneralCommentModalById starts");

		logger.info("Method : getTicketTGeneralCommentModalById ends");
		return callAgentTicketDao.getTicketTGeneralCommentModalById(id);
	}

	/**
	 * add comments in modal
	 *
	 */

	@RequestMapping(value = "/rest-addCommentsGeneralAgentById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> addCommentsGeneralAgentById(@RequestParam String id,
			@RequestParam String comment, @RequestParam String status, @RequestParam String createdBy) {
		logger.info("Method : addCommentsGeneralAgentById starts");

		logger.info("Method : addCommentsGeneralAgentById ends");
		return callAgentTicketDao.addCommentsGeneralAgentById(id, comment, status, createdBy);
	}
	
	/**************************             Service Request              ***************************************************** */
	/*
	 * for All view service request ticket master agent
	 */
	@RequestMapping(value = "/rest-viewAllServiceRequest")
	public JsonResponse<List<CallAgentTicketRestModel>> viewAllServiceRequest() {
		logger.info("Method : viewAllServiceRequest starts");
		logger.info("Method : viewAllServiceRequest ends");
		return callAgentTicketDao.viewAllServiceRequestDao();
	}
	/**
	 * returns call details modal  view agent
	 *
	 */
	@RequestMapping(value = "/getServiceRequestAgentById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getServiceRequestAgentById(@RequestParam String id
			) {
		logger.info("Method : getServiceRequestAgentById starts");

		logger.info("Method : getServiceRequestAgentById end");

		return callAgentTicketDao.getServiceRequestAgentById(id);
	}
	/*
	 * for All model view service Request comments ticket Master
	 */
	@RequestMapping(value = "/rest-getTicketTServiceCommentModalById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getTicketTServiceCommentModalById(
			@RequestParam String id) {
		logger.info("Method : getTicketTServiceCommentModalById starts");

		logger.info("Method : getTicketTServiceCommentModalById ends");
		return callAgentTicketDao.getTicketTServiceCommentModalById(id);
	}

	/**
	 * add comments in modal
	 *
	 */

	@RequestMapping(value = "/rest-addServiceCommentsAgent", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> addCommentsAgent(@RequestParam String id, @RequestParam String comment,
			@RequestParam String status, @RequestParam String createdBy) {
		logger.info("Method : addCommentsAgent starts");

		logger.info("Method : addCommentsAgent ends");
		return callAgentTicketDao.addServiceCommentsAgent(id, comment, status, createdBy);
	}
	
	/**************************             Complaint              ***************************************************** */
	/*
	 * for All view complaint  ticket master agent
	 */
	@RequestMapping(value = "/rest-viewAllComplaint")
	public JsonResponse<List<CallAgentTicketRestModel>> viewAllComplaint() {
		logger.info("Method : viewAllComplaint starts");
		logger.info("Method : viewAllComplaint ends");
		return callAgentTicketDao.viewAllComplaintDao();
	}
	/**
	 * returns complaint details modal  view agent
	 *
	 */
	@RequestMapping(value = "/getComplaintAgentById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getComplaintAgentById(@RequestParam String id
			) {
		logger.info("Method : getComplaintAgentById starts");

		logger.info("Method : getComplaintAgentById end");

		return callAgentTicketDao.getComplaintAgentByIdDao(id);
	}
	/*
	 * for All model view Complaints comments ticket Master
	 */
	@RequestMapping(value = "/rest-getTicketComplaintCommentModalById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getTicketComplaintCommentModalById(
			@RequestParam String id) {
		logger.info("Method : getTicketComplaintCommentModalById starts");

		logger.info("Method : getTicketComplaintCommentModalById ends");
		return callAgentTicketDao.getTicketComplaintCommentModalById(id);
	}

	/**
	 * add comments in modal
	 *
	 */

	@RequestMapping(value = "/rest-addCommentsComplaintAgentById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> addCommentsComplaintAgentById(@RequestParam String id,
			@RequestParam String comment, @RequestParam String status, @RequestParam String createdBy) {
		logger.info("Method : addCommentsComplaintAgentById starts");

		logger.info("Method : addCommentsComplaintAgentById ends");
		return callAgentTicketDao.addCommentsComplaintAgentById(id, comment, status, createdBy);
	}

	
	/**************************             Sales              ***************************************************** */
	/*
	 * for All view sales  ticket master agent
	 */
	@RequestMapping(value = "/rest-viewAllSales")
	public JsonResponse<List<CallAgentTicketRestModel>> viewAllSales() {
		logger.info("Method : viewAllSales starts");
		logger.info("Method : viewAllSales ends");
		return callAgentTicketDao.viewAllSalesDao();
	}
	/**
	 * returns Sale modal  view agent
	 *
	 */
	@RequestMapping(value = "/getSaleAgentById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getSaleAgentById(@RequestParam String id
			) {
		logger.info("Method : getSaleAgentById starts");

		logger.info("Method : getSaleAgentById end");

		return callAgentTicketDao.getSaleAgentByIdDao(id);
	}

	/*
	 * for All model view Sales comments ticket Master
	 */
	@RequestMapping(value = "/rest-getTicketSalesCommentModalById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getTicketSalesCommentModalById(@RequestParam String id) {
		logger.info("Method : getTicketSalesCommentModalById starts");

		logger.info("Method : getTicketSalesCommentModalById ends");
		return callAgentTicketDao.getTicketSalesCommentModalById(id);
	}

	/**
	 * add comments in modal
	 *
	 */

	@RequestMapping(value = "/rest-addCommentsForSalesAgentById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> addCommentsForSalesAgentById(@RequestParam String id,
			@RequestParam String comment, @RequestParam String status, @RequestParam String createdBy) {
		logger.info("Method : addCommentsForSalesAgentById starts");

		logger.info("Method : addCommentsForSalesAgentById ends");
		return callAgentTicketDao.addCommentsForSalesAgentById(id, comment, status, createdBy);
	}

	
	/**************************             Marketing              ***************************************************** */
	/*
	 * for All view Marketing  ticket master agent
	 */
	@RequestMapping(value = "/rest- viewAllMarketingRequest")
	public JsonResponse<List<CallAgentTicketRestModel>>  viewAllMarketingRequest() {
		logger.info("Method :  viewAllMarketingRequest starts");
		logger.info("Method :  viewAllMarketingRequest ends");
		return callAgentTicketDao. viewAllMarketingRequestDao();
	}
	/**
	 * returns Marketing Request modal  view agent
	 *
	 */
	@RequestMapping(value = "/getMarketingRequestAgentById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getMarketingRequestAgentById(@RequestParam String id
			) {
		logger.info("Method : getMarketingRequestAgentById starts");

		logger.info("Method : getMarketingRequestAgentById end");

		return callAgentTicketDao.getMarketingRequestAgentByIdDao(id);
	}

	/*
	 * for All model view Marketing comments ticket Master
	 */
	@RequestMapping(value = "/rest-getTicketMarketingCommentModalById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<CallAgentTicketRestModel>> getTicketMarketingCommentModalById(
			@RequestParam String id) {
		logger.info("Method : getTicketMarketingCommentModalById starts");

		logger.info("Method : getTicketMarketingCommentModalById ends");
		return callAgentTicketDao.getTicketMarketingCommentModalById(id);
	}

	/**
	 * add comments in modal
	 *
	 */

	@RequestMapping(value = "/rest-addCommentsMarketingAgentById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> addCommentsMarketingAgentById(@RequestParam String id,
			@RequestParam String comment, @RequestParam String status, @RequestParam String createdBy) {
		logger.info("Method : addCommentsMarketingAgentById starts");

		logger.info("Method : addCommentsMarketingAgentById ends");
		return callAgentTicketDao.addCommentsMarketingAgentById(id, comment, status, createdBy);
	}
}
