package nirmalya.aatithya.restmodule.audit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.audit.dao.RestAuditDashboardDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@RestController
@RequestMapping(value = "audit/")
public class RestAuditDashboardController {

	Logger logger = LoggerFactory.getLogger(RestAuditDashboardController.class);

	@Autowired
	RestAuditDashboardDao auditDashboardDao;
	
	/**
	 * Rest Controller - Get Audit Type For Drop Down
	 *
	 */
	@RequestMapping(value = "getAuditTypeDashboard", method = { RequestMethod.GET })
	public List<DropDownModel> getAuditTypeDashboard() {
		logger.info("Method : getAuditTypeDashboard starts");
		
		logger.info("Method : getAuditTypeDashboard ends");
		
		return auditDashboardDao.getAuditTypeDashboardDao();
	}
 
}
