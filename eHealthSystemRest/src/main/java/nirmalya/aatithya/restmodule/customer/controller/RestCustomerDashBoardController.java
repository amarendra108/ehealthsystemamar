package nirmalya.aatithya.restmodule.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.asset.model.RestAssetCodeModel;
import nirmalya.aatithya.restmodule.asset.model.RestAssetGraphModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.customer.dao.RestCustomerDashboardDao;
import nirmalya.aatithya.restmodule.customer.model.RestCustomerGraphModel;
import nirmalya.aatithya.restmodule.customer.model.RestCustomerStoreGraphModel;

@RestController
@RequestMapping(value = "customer/")
public class RestCustomerDashBoardController {
Logger logger = LoggerFactory.getLogger(RestCustomerDashBoardController.class);
	
	@Autowired
	RestCustomerDashboardDao customerDashboardDao;
	/*
	 * totla assets count
	 */
	@GetMapping(value = "quotitionCount")
	public List<DropDownModel> totalQuotions() {
		logger.info("Method : totalQuotions starts");

		logger.info("Method : totalQuotions ends");
		return customerDashboardDao.totalQuotions();
	}
	/*
	 * Total Sales Order count
	 * 
	 */
	@GetMapping(value = "ordercount")
	public List<DropDownModel> totalSalesorder() {
		logger.info("Method : totalSalesorder starts");
		
		logger.info("Method : totalSalesorder ends");
		return customerDashboardDao.totalSalesorder();
	}
	/*
	 * Total Customer count
	 * 
	 */
	@GetMapping(value = "customercount")
	public List<DropDownModel> totalCustomer() {
		logger.info("Method : totalCustomer starts");
		
		logger.info("Method : totalCustomer ends");
		return customerDashboardDao.totalCustomer();
	}
	/*
	 * Total Customer count
	 * 
	 */
	@GetMapping(value = "salesInvoiceCount")
	public List<DropDownModel> totalSalesInvoice() {
		logger.info("Method : totalSalesInvoice starts");
		
		logger.info("Method : totalSalesInvoice ends");
		return customerDashboardDao.totalSalesInvoice();
	}
	/*
	 * Asset Report For Graph
	 */
	@RequestMapping(value = "customerGraph", method = { RequestMethod.GET })
	public List<RestCustomerGraphModel> customerGraph() {

		logger.info("Method : customerGraph starts");
		logger.info("Method : customerGraph ends");

		return customerDashboardDao.customerGraph();
	}
	/*
	 * store  Graph
	 */
	@RequestMapping(value = "customerStoreGraph", method = { RequestMethod.GET })
	public List<RestCustomerGraphModel> customerStoreGraph() {
		
		logger.info("Method : customerStoreGraph rest starts");
		logger.info("Method : customerStoreGraph rest ends");
		
		return customerDashboardDao.customerStoreGraph();
	}
	/*
	 * dash board Store dash board
	 */
	@GetMapping(value = "customerStore")
	public List<RestCustomerStoreGraphModel> restcustomerStore() {
		logger.info("Method : restcustomerStore starts");
		logger.info("Method : restcustomerStore ends");
		return customerDashboardDao.restcustomerStore();
	}
	/*
	 * dash board customer
	 */
	@GetMapping(value = "customer")
	public List<RestCustomerGraphModel> restcustomer() {
		logger.info("Method : restcustomer starts");
		logger.info("Method : restcustomer ends");
		return customerDashboardDao.restcustomer();
	}
}
