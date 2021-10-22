package nirmalya.aatithya.restmodule.audit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.audit.dao.RestAuditLinkCategoryDao;
import nirmalya.aatithya.restmodule.audit.model.RestAuditDocumentModel;
import nirmalya.aatithya.restmodule.audit.model.RestAuditLinkCategoryModel;
import nirmalya.aatithya.restmodule.audit.model.RestAuditMeetingRestModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModelEmp;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.notice.model.RestIntiateNoticeModel;
@RestController
@RequestMapping(value = "audit/")
public class RestAuditLinkCategoryController {

	Logger logger = LoggerFactory.getLogger(RestAuditDashboardController.class);

	@Autowired
	RestAuditLinkCategoryDao auditLinkCategoryDao;

	/**
	 * Rest Controller - Get Financial year Type For Drop Down
	 *
	 */
	@RequestMapping(value = "getFinancialYear", method = { RequestMethod.GET })
	public List<DropDownModel> getFinancialYear() {
		logger.info("Method : getFinancialYear starts");
		
		logger.info("Method : getFinancialYear ends");
		return auditLinkCategoryDao.getFinancialYear();
	}
	/**
	 * Rest Controller - Get Organization Type For Drop Down
	 *
	 */
	/*
	 * @RequestMapping(value = "getOrganizationId", method = { RequestMethod.GET })
	 * public List<DropDownModel> getOrganizationId() {
	 * logger.info("Method : getOrganizationId starts");
	 * 
	 * logger.info("Method : getOrganizationId ends"); return
	 * auditDashboardDao.getOrganizationId(); }
	 */
	
	/**
	 * Rest Controller - Get Department Type For Drop Down
	 *
	 */
	@RequestMapping(value="getDeptList" , method={RequestMethod.GET})
	public List<DropDownModel> getDeptList() {
		logger.info("Method : getDeptList starts");
		
		logger.info("Method : getDeptList ends");
		return auditLinkCategoryDao.getDeptList();
	}
	/**
	 * Rest Controller - Get Organization Name For Drop Down
	 *
	 */
	
	@RequestMapping(value = "/getOrganization", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getOrganization(@RequestParam String id) {
		logger.info("Method : getOrganization starts");

		logger.info("Method : getOrganization ends");
		return auditLinkCategoryDao.getOrganization(id);
	}
	/**
	 * Rest Controller - Get Region Type For Drop Down
	 *
	 */
	@RequestMapping(value="getRegion" , method={RequestMethod.GET})
	public List<DropDownModel> getRegion() {
		logger.info("Method : getRegion starts");
		
		logger.info("Method : getRegion ends");
		return auditLinkCategoryDao.getRegion();
	}
	/**
	 * Rest Controller - Get Quarter Type For Drop Down
	 *
	 */

	@RequestMapping(value = "getQuarter", method = { RequestMethod.GET })
	public List<DropDownModel>getQuarter() {
		logger.info("Method : getQuarter starts");

		logger.info("Method : getQuarter ends");
		return auditLinkCategoryDao.getQuarter();
	}
	/**
	 * Rest Controller - Get Quarter Type For Drop Down
	 *
	 */
	@RequestMapping(value = "toList", method = { RequestMethod.GET })
	public List<DropDownModelEmp> getempListss() {
		logger.info("Method : getempListss starts");

		logger.info("Method : getempListss ends");
		return auditLinkCategoryDao.getempListss();

	}
	
	/**
	 * Internal Audit - Get Auditor Type For Drop Down
	 *
	 */

	@RequestMapping(value = "getInternalAuditor", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getInternalAuditor(@RequestParam("region") String region) {
		logger.info("Method : getInternalAuditor starts");

		logger.info("Method : getInternalAuditor ends");
		return auditLinkCategoryDao.getInternalAuditor(region);
	}
	
	@RequestMapping(value = "getRegionalManager", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getRegionalManager(@RequestParam("auditor") String id) {
		logger.info("Method : getRegionalManager starts");
		
		logger.info("Method : getRegionalManager ends");
		return auditLinkCategoryDao.getRegionalManager(id);
	}
	@RequestMapping(value = "getConcernFinance", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getConcernFinance(@RequestParam("region")  String id) {
		logger.info("Method : getConcernFinance starts");
		
		logger.info("Method : getConcernFinance ends");
		return auditLinkCategoryDao.getConcernFinance(id);
	}
	/**
	 * Rest Controller - Get Department Type For Drop Down
	 *
	 */
	
	@RequestMapping(value = "/getSectionListByDept", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getSectionListByDept(@RequestParam String id) {
		logger.info("Method : getSectionListByDept starts");

		logger.info("Method : getSectionListByDept ends");
		return auditLinkCategoryDao.getSectionListByDept(id);
	}
	/**
	 * 
	 * @return State list
	 */
	@RequestMapping(value = "getDeptHeadBySection", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getDeptHeadBySection(@RequestParam String id) {
		logger.info("Method : getDeptHead starts");
		logger.info("Method : getDeptHead ends");
		return auditLinkCategoryDao.getDeptHeadBySection(id);
	}
	/*
	 * Add initiate Audit with Doc
	 */
	@PostMapping(value = "rest-addAudit")
	public ResponseEntity<JsonResponse<List<RestAuditLinkCategoryModel>>> restAddAudit(
			@RequestBody List<RestAuditLinkCategoryModel> auditModel) {
		logger.info("Method : restAddAudit starts");
		logger.info("Method : restAddAudit ends");
		return auditLinkCategoryDao.addAuditDao(auditModel);
	}
	/*
	 * view Audit Details
	 */

	@GetMapping(value = "rest-viewAuditDetails")
	public List<RestAuditLinkCategoryModel> viewAuditDtls(@RequestParam String id) {
		logger.info("Method : viewAuditDtls starts");
		logger.info("Method : viewAuditDtls endss");
		return auditLinkCategoryDao.viewAuditDtlsDao(id);
	}
	/*
	 * Delete Audit Details
	 *
	 */

	@GetMapping(value = "delete-auditDetails")
	public JsonResponse<RestAuditLinkCategoryModel> deleteAuditDetails(@RequestParam String id) {
		logger.info("Method : deleteAuditDetails starts");
		logger.info("Method : deleteAuditDetails endss");
		return auditLinkCategoryDao.deleteAuditDetailsDao(id);
	}
	/*
	 * Edit Audit By Id
	 *
	 */
	@GetMapping(value = "rest-editAudit")
	public List<RestAuditLinkCategoryModel> editAudit(@RequestParam String id) {
		logger.info("Method : editAudit starts");
		logger.info("Method : editAudit endss");
		return auditLinkCategoryDao.editAuditDao(id);
	}
	/**
	 * 
	 * @return State list
	 */
	@RequestMapping(value = "getSectionforEdit", method = { RequestMethod.GET })
	public List<DropDownModel> getSectionName(@RequestParam String id) {
		logger.info("Method : getSectionName starts");
		logger.info("Method : getSectionName ends");
		return auditLinkCategoryDao.getSectionNameDao(id);
	}
	/*************************************           Document Upload Section            ***********************************************/
	/*
	 * 
	 * Drop Down Model Through Ajax(Audit Type)
	 * 
	 */
	
	@GetMapping(value = "getAuditFolder")
	public List<DropDownModel> auditFolder() {

		logger.info("Method : auditFolder starts");

		logger.info("Method : auditFolder ends");
		return auditLinkCategoryDao.auditFolder();
	}
	/*
	 * Add initiate Audit with Doc
	 */
	@PostMapping(value = "rest-addDocument")
	public ResponseEntity<JsonResponse<List<RestAuditDocumentModel>>> restAddDocument(
			@RequestBody List<RestAuditDocumentModel> auditDocModel) {
		logger.info("Method : restAddDocument starts");
		logger.info("Method : restAddDocument ends");
		return auditLinkCategoryDao.restAddDocumentDao(auditDocModel);
	}
	/*
	 * view Audit Doc
	 */

	@GetMapping(value = "rest-viewAuditDoc")
	public List<RestAuditDocumentModel> viewAuditDoc(@RequestParam String id) {
		logger.info("Method : viewAuditDoc starts");
		logger.info("Method : viewAuditDoc endss");
		return auditLinkCategoryDao.viewAuditDocDao(id);
	}
	/*
	 * Edit Audit By Id
	 *
	 */
	@GetMapping(value = "rest-editAuditDoc")
	public List<RestAuditDocumentModel> editAuditDoc(@RequestParam String id) {
		logger.info("Method : editAuditDoc starts");
		logger.info("Method : editAuditDoc endss");
		return auditLinkCategoryDao.editAuditDocDao(id);
	}
	/*
	 * Delete Audit Doc
	 *
	 */

	@GetMapping(value = "delete-auditDoc")
	public JsonResponse<RestAuditDocumentModel> deleteAuditDoc(@RequestParam String id) {
		logger.info("Method : deleteAuditDoc starts");
		logger.info("Method : deleteAuditDoc endss");
		return auditLinkCategoryDao.deleteAuditDocDao(id);
	}
	/********************** Meeting Section *************************/
	@RequestMapping(value = "getempList", method = { RequestMethod.GET })
	public List<DropDownModelEmp> getempList() {
		logger.info("Method : getCountryList starts");

		logger.info("Method : getCountryList ends");
		return auditLinkCategoryDao.getempList();

	}

	@RequestMapping(value = "getdeptList", method = { RequestMethod.GET })
	public List<DropDownModel> getdeptList() {
		logger.info("Method : getBandList starts");

		logger.info("Method : getBandList ends");
		return auditLinkCategoryDao.getdeptList();

	}
	/*
	 * add Meeting Details
	 */
	@PostMapping(value = "saveauditMaster")
	public ResponseEntity<JsonResponse<List<RestAuditMeetingRestModel>>> saveauditMaster(
			@RequestBody List<RestAuditMeetingRestModel> initiateNoticeModel) {
		logger.info("Method : saveemployeeMaster starts");
		logger.info("Method : saveemployeeMaster ends");
		return auditLinkCategoryDao.saveauditMaster(initiateNoticeModel);
	}
	/*
	 * View Meeting Details
	 */
	@GetMapping(value = "viewmeeting")
	public JsonResponse<List<RestAuditMeetingRestModel>> viewmeeting() {
		logger.info("Method : viewNoticeDtls starts");

		logger.info("Method : viewNoticeDtls ends");

		return auditLinkCategoryDao.viewmeeting();
	}

	@GetMapping(value = "get-meeting-edit")
	public List<RestAuditMeetingRestModel> editNotice(@RequestParam String id) {
		logger.info("Method : editNotice starts");
		logger.info("Method : editNotice ends");
		return auditLinkCategoryDao.editNoticeinitiate(id);
	}

	@GetMapping(value = "delete-audit-details")
	public ResponseEntity<JsonResponse<Object>> deleteuditMeetingDetails(@RequestParam String id) {
		logger.info("Method : deleteNoticeDetails starts");
		logger.info("Method : deleteNoticeDetails ends");
		return auditLinkCategoryDao.deleteuditMeetingDetails(id);
	}

	@PostMapping(value = "add-draft-details")
	public ResponseEntity<JsonResponse<List<RestAuditMeetingRestModel>>> restAddDrafts(

			@RequestBody List<RestAuditMeetingRestModel> initiateNoticeModel) {
		logger.info("Method : restAddDrafts starts");
		logger.info("Method : restAddDrafts ends");
		return auditLinkCategoryDao.restAddDraftsDao(initiateNoticeModel);
	}
	@PostMapping(value = "rest-send-details")
	public ResponseEntity<JsonResponse<List<RestAuditMeetingRestModel>>> restAddSendDetails(
			@RequestBody List<RestAuditMeetingRestModel> initiateNoticeModel) {
		logger.info("Method : restAddSendDetails starts");
		logger.info("Method : restAddSendDetails ends");
		return auditLinkCategoryDao.restAddSendDetails(initiateNoticeModel);
	}
	
	
	  @RequestMapping(value = "getmaildetails", method = { RequestMethod.GET })
	  public ResponseEntity<JsonResponse<Object>> getmaildetails(@RequestParam
	  String mylist) {
	  logger.info("Method : getmaildetails starts");
	  
	  logger.info("Method : getmaildetails ends"); return
	  auditLinkCategoryDao.getmaildetails(mylist); }
	 
	
	@GetMapping(value = "rest-viewDrafttable")
	public JsonResponse<List<RestAuditMeetingRestModel>> viewDraftDao(@RequestParam String meetingId,
			@RequestParam String createdBy) {
		logger.info("Method : viewNoticeDtls starts");

		logger.info("Method : viewNoticeDtls ends");

		return auditLinkCategoryDao.viewDraftDao(meetingId, createdBy);
	}
	@GetMapping(value = "edit-draft-details")
	public List<RestAuditMeetingRestModel> EditDraftDetails(@RequestParam String id) {
		logger.info("Method : EditDraftDetails starts");
		logger.info("Method : EditDraftDetails ends");
		return auditLinkCategoryDao.editDraftDetailsDao(id);
	}
	
	@GetMapping(value = "audit-show-details")
	public List<RestAuditMeetingRestModel> viewDocumentsAudit(@RequestParam String id) {
		logger.info("Method : viewDocumentsAudit starts");
		logger.info("Method : viewDocumentsAudit ends");
		return auditLinkCategoryDao.viewDocumentsAudit(id);
	}
	
	 @RequestMapping(value = "getmaildetailsAudit", method = { RequestMethod.GET })
	  public ResponseEntity<JsonResponse<Object>> getmaildetailsAudit(@RequestParam
	  String mylist) {
	  logger.info("Method : getmaildetailsAudit starts");
	  
	  logger.info("Method : getmaildetailsAudit ends"); return
	  auditLinkCategoryDao.getmaildetailsAudit(mylist); }
	 
	 
	 
	/*
	 * @PostMapping(value = "rest-send-details-mail") public
	 * ResponseEntity<JsonResponse<List<RestAuditLinkCategoryModel>>>
	 * restAddSendDetailsMails(
	 * 
	 * @RequestBody List<RestAuditLinkCategoryModel> initiateNoticeModel) {
	 * logger.info("Method : restAddSendDetailsMail starts");
	 * logger.info("Method : restAddSendDetailsMail ends"); return
	 * auditLinkCategoryDao.restAddSendDetailsMail(initiateNoticeModel); }
	 */
		

		/*
		 * Add send Details
		 */
		@PostMapping(value = "rest-send-details-mail-audit")
		public ResponseEntity<JsonResponse<List<RestAuditLinkCategoryModel>>> restAddSendDetailsMails(
				@RequestBody List<RestAuditLinkCategoryModel> initiateNoticeModel) {
			logger.info("Method : restAddSendDetails starts");
			logger.info("Method : restAddSendDetails ends");
			return auditLinkCategoryDao.restAddSendDetailsMails(initiateNoticeModel);
		}
		
		@PostMapping(value = "add-draft-details-commensement")
		public ResponseEntity<JsonResponse<List<RestAuditLinkCategoryModel>>> restAddDraftsCommensement(

				@RequestBody List<RestAuditLinkCategoryModel> initiateNoticeModel) {
			logger.info("Method : restAddDrafts starts");
			logger.info("Method : restAddDrafts ends");
			return auditLinkCategoryDao.restAddDraftsCommensement(initiateNoticeModel);
		}
		
		@GetMapping(value = "rest-viewDrafttableCommennse")
		public JsonResponse<List<RestAuditLinkCategoryModel>> viewDraftDaoCommense(@RequestParam String auditInitiate,
				@RequestParam String createdBy) {
			logger.info("Method : viewDraftDaoCommense starts");

			logger.info("Method : viewDraftDaoCommense ends");

			return auditLinkCategoryDao.viewDraftDaoCommense(auditInitiate, createdBy);
		}
		
		/*
		 * view Notice Initiate
		 */
		
		@GetMapping(value = "rest-viewAuditManagee")
		public List<RestAuditLinkCategoryModel> viewAuditInitDao() {
			logger.info("Method : viewAuditInitDao starts");

			logger.info("Method : viewAuditInitDao ends");

			return auditLinkCategoryDao.viewAuditInitDao();
		}
		
		@GetMapping(value = "edit-draft-details-commensement-manage")
		public List<RestAuditLinkCategoryModel> EditDraftCommensement(@RequestParam String id) {
			logger.info("Method : EditDraftCommensement starts");
			logger.info("Method : EditDraftCommensement ends");
			return auditLinkCategoryDao.editDraftCommensement(id);
		}
		
}
