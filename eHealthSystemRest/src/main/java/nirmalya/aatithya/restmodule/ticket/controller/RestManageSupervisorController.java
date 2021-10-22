package nirmalya.aatithya.restmodule.ticket.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.ticket.dao.RestManageSupervisorDao;
import nirmalya.aatithya.restmodule.ticket.model.CallAgentTicketRestModel;
import nirmalya.aatithya.restmodule.ticket.model.RestManageSupervisorModel;

/**
 * @author Nirmalya Labs
 *
 */
@RestController
@RequestMapping("ticket")
public class RestManageSupervisorController {
	Logger logger = LoggerFactory.getLogger(RestManageSupervisorController.class);
	@Autowired
	RestManageSupervisorDao restManageSupervisorDao;

	/**************************             Drop Down             ***************************************************** */
	
	/**
	 * returns Status for drop down model
	 *
	 */
	@RequestMapping(value = "/get-Status-Name", method = { RequestMethod.GET })
	public List<DropDownModel> getStatusName() {
		logger.info("Method : getStatusName starts");

		logger.info("Method : getStatusName end");
		return restManageSupervisorDao.getStatusName();
	}
	/**
	 * returns Status for drop down model
	 *
	 */
	@RequestMapping(value = "/get-Supports-Name", method = { RequestMethod.GET })
	public List<DropDownModel> getSupportsName() {
		logger.info("Method : getSupportsName starts");

		logger.info("Method : getSupportsName end");
		return restManageSupervisorDao.getSupportsName();
	}
/**************************             General Enquiry              ***************************************************** */
	
	/*
	 * for All view general enquiry ticket master agent
	 */
	@RequestMapping(value = "/rest-viewAllGenEnq")
	public JsonResponse<List<RestManageSupervisorModel>> viewAllGenEnq() {
		logger.info("Method : viewAllGeneralEnquiry starts");
		logger.info("Method : viewAllGeneralEnquiry ends");
		return restManageSupervisorDao.viewAllGenEnqDao();
	}
	
	
	/**
	 * returns General Enquiry modal  view agent
	 *
	 */
	@RequestMapping(value = "/modalViewGeneralSupervisorById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<RestManageSupervisorModel>> getGeneralSupervisorById(@RequestParam String id
			) {
		logger.info("Method : getGeneralSupervisorById starts");

		logger.info("Method : getGeneralSupervisorById end");

		return restManageSupervisorDao.getGeneralSupervisorByIdDao(id);
	}
	

	/*
	 * for All model view service Request comments ticket Master
	 */
	@RequestMapping(value = "/rest-getGeneralSupervisorCommentModalById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<RestManageSupervisorModel>> getGeneralSupervisorCommentModalById(
			@RequestParam String id) {
		logger.info("Method : getGeneralSupervisorCommentModalById starts");

		logger.info("Method : getGeneralSupervisorCommentModalById ends");
		return restManageSupervisorDao.getGeneralSupervisorCommentModalByIdDao(id);
	}

	/**
	 * add comments in modal
	 *
	 */

	@RequestMapping(value = "/rest-addCommentsGeneralSupervisorById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> addCommentsGeneralSupervisorById(@RequestParam String id,
			@RequestParam String comment, @RequestParam String status, @RequestParam String createdBy) {
		logger.info("Method : addCommentsGeneralSupervisorById starts");

		logger.info("Method : addCommentsGeneralSupervisorById ends");
		return restManageSupervisorDao.addCommentsGeneralSupervisorByIdDao(id, comment, status, createdBy);
	}
	/**
	 * add assign in modal
	 *
	 */
@RequestMapping(value="/assignSupportsGenEnq" , method={RequestMethod.GET})
public ResponseEntity<JsonResponse<Object>> assignSupports(@RequestParam String id, @RequestParam String comment, @RequestParam String supports, @RequestParam String createdBy, @RequestParam String CallQueue) {
	logger.info("Method : assignSupports starts");
	
	logger.info("Method : assignSupports ends");
	return restManageSupervisorDao.assignSupportsGenEnqDao(id,comment,supports,createdBy,CallQueue);
}
	/**************************             Service Request              ***************************************************** */
	/*
	 * for All view service request ticket master agent
	 */
	@RequestMapping(value = "/rest-viewAllSerReq")
	public JsonResponse<List<RestManageSupervisorModel>> viewAllSerReq() {
		logger.info("Method : viewAllSerReq starts");
		logger.info("Method : viewAllSerReq ends");
		return restManageSupervisorDao.viewAllSerReqDao();
	}
	/**
	 * returns call details modal  view agent
	 *
	 */
	@RequestMapping(value = "/rest-modalSupervisorViewSerReq", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<RestManageSupervisorModel>> modalSupervisorViewSerReq(@RequestParam String id
			) {
		logger.info("Method : getServiceRequestAgentById starts");

		logger.info("Method : modalSupervisorViewSerReq end");

		return restManageSupervisorDao.modalSupervisorViewSerReqDao(id);
	}
	/*
	 * for All model view service Request comments ticket Master
	 */
	@RequestMapping(value = "/rest-getcommentSupervisorModalViewSerReqById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<RestManageSupervisorModel>> getcommentSupervisorModalViewSerReqById(
			@RequestParam String id) {
		logger.info("Method : getcommentSupervisorModalViewSerReqById starts");

		logger.info("Method : getTicketTServiceCommentModalById ends");
		return restManageSupervisorDao.getcommentSupervisorModalViewSerReqById(id);
	}

	/**
	 * add comments in modal
	 *
	 */

	@RequestMapping(value = "/rest-addServiceCommentsSupervisor", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> addServiceCommentsSupervisor(@RequestParam String id, @RequestParam String comment,
			@RequestParam String status, @RequestParam String createdBy) {
		logger.info("Method : addServiceCommentsSupervisor starts");

		logger.info("Method : addServiceCommentsSupervisor ends");
		return restManageSupervisorDao.addServiceCommentsSupervisor(id, comment, status, createdBy);
	}
	/**
	 * add assign in modal
	 *
	 */
@RequestMapping(value="/assignSupportsSerReq" , method={RequestMethod.GET})
public ResponseEntity<JsonResponse<Object>> assignSupportsService(@RequestParam String id, @RequestParam String comment, @RequestParam String supports, @RequestParam String createdBy, @RequestParam String CallQueue) {
	logger.info("Method : assignSupports starts");
	
	logger.info("Method : assignSupports ends");
	return restManageSupervisorDao.assignSupportsSerReqDao(id,comment,supports,createdBy,CallQueue);
}

	/**************************             Complaint              ***************************************************** */
	/*
	 * for All view complaint  ticket master agent
	 */
	@RequestMapping(value = "/rest-viewAllComp")
	public JsonResponse<List<RestManageSupervisorModel>> viewAllComp() {
		logger.info("Method : viewAllComp starts");
		logger.info("Method : viewAllComp ends");
		return restManageSupervisorDao.viewAllCompDao();
	}
	
	/**
	 * returns call details modal  view agent
	 *
	 */
	@RequestMapping(value = "/rest-modalSupervisorViewComp", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<RestManageSupervisorModel>> modalSupervisorViewComplaint(@RequestParam String id
			) {
		logger.info("Method : modalSupervisorViewComplaint starts");

		logger.info("Method : modalSupervisorViewComplaint end");

		return restManageSupervisorDao.modalSupervisorViewComplaintDao(id);
	}
	/*
	 * for All model view service Request comments ticket Master
	 */
	@RequestMapping(value = "/rest-getcommentSupervisorModalViewCompById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<RestManageSupervisorModel>> getcommentSupervisorModalViewComById(
			@RequestParam String id) {
		logger.info("Method : getcommentSupervisorModalViewComById starts");

		logger.info("Method : getcommentSupervisorModalViewComById ends");
		return restManageSupervisorDao.getcommentSupervisorModalViewComByIdDao(id);
	}

	/**
	 * add comments in modal
	 *
	 */

	@RequestMapping(value = "/rest-addServiceCommentsSupervisorComp", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> addCompCommentsSupervisor(@RequestParam String id, @RequestParam String comment,
			@RequestParam String status, @RequestParam String createdBy) {
		logger.info("Method : addCompCommentsSupervisor starts");

		logger.info("Method : addCompCommentsSupervisor ends");
		return restManageSupervisorDao.addCompCommentsSupervisorDao(id, comment, status, createdBy);
	}
	/**
	 * add assign in modal
	 *
	 */
@RequestMapping(value="/assignSupportsComp" , method={RequestMethod.GET})
public ResponseEntity<JsonResponse<Object>> assignSupportsComplaint(@RequestParam String id, @RequestParam String comment, @RequestParam String supports, @RequestParam String createdBy, @RequestParam String CallQueue) {
	logger.info("Method : assignSupports starts");
	
	logger.info("Method : assignSupports ends");
	return restManageSupervisorDao.assignSupportsCompDao(id,comment,supports,createdBy,CallQueue);
}
	/**************************             Sales              ***************************************************** */
	/*
	 * for All view sales  ticket master agent
	 */
	@RequestMapping(value = "/rest-viewAllSale")
	public JsonResponse<List<RestManageSupervisorModel>> viewAllSale() {
		logger.info("Method : viewAllSale starts");
		logger.info("Method : viewAllSale ends");
		return restManageSupervisorDao.viewAllSaleDao();
	}
	/**
	 * returns call details modal  view agent
	 *
	 */
	@RequestMapping(value = "/rest-modalSupervisorViewSale", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<RestManageSupervisorModel>> modalSupervisorViewSale(@RequestParam String id
			) {
		logger.info("Method : modalSupervisorViewSale starts");

		logger.info("Method : modalSupervisorViewSale end");

		return restManageSupervisorDao.modalSupervisorViewSaleDao(id);
	}
	/*
	 * for All model view service Request comments ticket Master
	 */
	@RequestMapping(value = "/rest-getcommentSupervisorModalViewSaleById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<RestManageSupervisorModel>> getcommentSupervisorModalViewSaleById(
			@RequestParam String id) {
		logger.info("Method : getcommentSupervisorModalViewSaleById starts");

		logger.info("Method : getcommentSupervisorModalViewSaleById ends");
		return restManageSupervisorDao.getcommentSupervisorModalViewSaleByIdDao(id);
	}

	/**
	 * add comments in modal
	 *
	 */

	@RequestMapping(value = "/rest-addServiceCommentsSupervisorSale", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> addSaleCommentsSupervisor(@RequestParam String id, @RequestParam String comment,
			@RequestParam String status, @RequestParam String createdBy) {
		logger.info("Method : addSaleCommentsSupervisor starts");

		logger.info("Method : addSaleCommentsSupervisor ends");
		return restManageSupervisorDao.addSaleCommentsSupervisorDao(id, comment, status, createdBy);
	}
	/**
	 * add assign in modal
	 *
	 */
@RequestMapping(value="/assignSupportsSale" , method={RequestMethod.GET})
public ResponseEntity<JsonResponse<Object>> assignSupportsSales(@RequestParam String id, @RequestParam String comment, @RequestParam String supports, @RequestParam String createdBy, @RequestParam String CallQueue) {
	logger.info("Method : assignSupports starts");
	
	logger.info("Method : assignSupports ends");
	return restManageSupervisorDao.assignSupportsSaleDao(id,comment,supports,createdBy,CallQueue);
}
	/**************************             Marketing              ***************************************************** */
	/*
	 * for All view Marketing  ticket master agent
	 */
	@RequestMapping(value = "/rest- viewAllMktgReq")
	public JsonResponse<List<RestManageSupervisorModel>>viewAllMktgReq() {
		logger.info("Method :  viewAllMktgReq starts");
		logger.info("Method :  viewAllMktgReq ends");
		return restManageSupervisorDao. viewAllMktgReqDao();
	}
	/**
	 * returns MktgReq modal  view agent
	 *
	 */
	@RequestMapping(value = "/rest-modalSupervisorViewMktgReq", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<RestManageSupervisorModel>> modalSupervisorViewMktgReq(@RequestParam String id
			) {
		logger.info("Method : modalSupervisorViewMktgReq starts");

		logger.info("Method : modalSupervisorViewMktgReq end");

		return restManageSupervisorDao.modalSupervisorViewMktgReqDao(id);
	}
	/*
	 * for All model view MktgReq Request comments ticket Master
	 */
	@RequestMapping(value = "/rest-getcommentSupervisorModalViewMktgReqById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<RestManageSupervisorModel>> getcommentSupervisorModalViewMktgReqById(
			@RequestParam String id) {
		logger.info("Method : getcommentSupervisorModalViewMktgReqById starts");

		logger.info("Method : getcommentSupervisorModalViewMktgReqById ends");
		return restManageSupervisorDao.getcommentSupervisorModalViewMktgReqByIdDao(id);
	}

	/**
	 * add comments in modal
	 *
	 */

	@RequestMapping(value = "/rest-addServiceCommentsSupervisorMktgReq", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> addMktgReqCommentsSupervisor(@RequestParam String id, @RequestParam String comment,
			@RequestParam String status, @RequestParam String createdBy) {
		logger.info("Method : addMktgReqCommentsSupervisor starts");

		logger.info("Method : addMktgReqCommentsSupervisor ends");
		return restManageSupervisorDao.addMktgReqCommentsSupervisorDao(id, comment, status, createdBy);
	}
	/**
	 * add assign in modal
	 *
	 */
@RequestMapping(value="/assignSupportsMktgReq" , method={RequestMethod.GET})
public ResponseEntity<JsonResponse<Object>> assignSupportsMktg(@RequestParam String id, @RequestParam String comment, @RequestParam String supports, @RequestParam String createdBy, @RequestParam String CallQueue) {
	logger.info("Method : assignSupports starts");
	
	logger.info("Method : assignSupports ends");
	return restManageSupervisorDao.assignSupportsMktgReqDao(id,comment,supports,createdBy,CallQueue);
}
}
