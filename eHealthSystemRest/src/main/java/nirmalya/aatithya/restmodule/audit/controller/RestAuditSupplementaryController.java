package nirmalya.aatithya.restmodule.audit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.audit.dao.RestAuditSupplementaryDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
@RestController
@RequestMapping(value = "audit/")
public class RestAuditSupplementaryController {

	Logger logger = LoggerFactory.getLogger(RestAuditDashboardController.class);

	@Autowired
	RestAuditSupplementaryDao auditSupplementaryDao;
	/**
	 * Rest Controller - Get Audit Type For Drop Down  
	 *
	 */
	@RequestMapping(value = "getSupplementaryTypeDashboard", method = { RequestMethod.GET })
	public List<DropDownModel> getSupplementaryTypeDashboard() {
		logger.info("Method : getSupplementaryTypeDashboard starts");
		
		logger.info("Method : getSupplementaryTypeDashboard ends");
		return auditSupplementaryDao.getSupplementaryTypeDashboard();
	} 
}
