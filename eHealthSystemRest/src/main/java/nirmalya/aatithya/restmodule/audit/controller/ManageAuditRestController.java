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

import nirmalya.aatithya.restmodule.audit.dao.ManageAuditDao;
import nirmalya.aatithya.restmodule.audit.dao.RestAuditLinkCategoryDao;
import nirmalya.aatithya.restmodule.audit.model.ManageAuditModel;
import nirmalya.aatithya.restmodule.audit.model.RestAuditDocumentModel;
import nirmalya.aatithya.restmodule.audit.model.RestAuditLinkCategoryModel;
import nirmalya.aatithya.restmodule.audit.model.RestAuditMeetingRestModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModelEmp;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;


@RestController
@RequestMapping(value = "audit/")
public class ManageAuditRestController {
	
	Logger logger = LoggerFactory.getLogger(ManageAuditRestController.class);
	
	@Autowired
	ManageAuditDao manageAuditDao;
	
	@GetMapping(value = "viewmanageaudit")
	public JsonResponse<List<ManageAuditModel>> viewAuditDtlsss() {
		logger.info("Method : viewAuditDtls starts");
		logger.info("Method : viewAuditDtls endss");
		return manageAuditDao.viewAuditDtlssssDao();
	}
	@GetMapping(value = "audit-show-detailssss")
	public List<ManageAuditModel> viewDocumentsAuditsss(@RequestParam String id) {
		logger.info("Method : viewDocumentsAudit starts");
		logger.info("Method : viewDocumentsAudit ends");
		return manageAuditDao.viewDocumentsAuditsss(id);
	}
}
