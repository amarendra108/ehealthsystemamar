package nirmalya.aatithya.restmodule.ticket.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.ticket.dao.TicketDashboardDao;
import nirmalya.aatithya.restmodule.ticket.model.TicketDashboardRestModel;

@RestController
@RequestMapping(value = "ticket/")
public class TicketDashboardRestController {

	Logger logger = LoggerFactory.getLogger(TicketDashboardRestController.class);
	
	@Autowired
	TicketDashboardDao ticketDashboardDao;
	/*
	 * totla assets
	 */
	@GetMapping(value = "ticketCount")
	public List<DropDownModel> totalTicket() {
		logger.info("Method : totalAsset starts");

		logger.info("Method : totalAsset ends");
		return ticketDashboardDao.totalTicket();
	}
	/*
	 * totla generalTicket
	 */
	@GetMapping(value = "generalQuery")
	public List<DropDownModel> generalTicket() {
		logger.info("Method : generalTicket starts");
		
		logger.info("Method : generalTicket ends");
		return ticketDashboardDao.generalTicket();
	}
	/*
	 * total serviceTicket
	 */
	@GetMapping(value = "serviceRequest")
	public List<DropDownModel> serviceTicket() {
		logger.info("Method : serviceTicket starts");
		
		logger.info("Method : serviceTicket ends");
		return ticketDashboardDao.serviceTicket();
	}
	/*
	 * total complains
	 */
	@GetMapping(value = "complains")
	public List<DropDownModel> complainTicket() {
		logger.info("Method : complainTicket starts");
		
		logger.info("Method : complainTicket ends");
		return ticketDashboardDao.complainTicket();
	}
	/*
	 * total sales
	 */
	@GetMapping(value = "sales")
	public List<DropDownModel> salesTicket() {
		logger.info("Method : salesTicket starts");
		
		logger.info("Method : salesTicket ends");
		return ticketDashboardDao.salesTicket();
	}
	
	/*
	 * totla market
	 */
	@GetMapping(value = "market")
	public List<DropDownModel> marketTicket() {
		logger.info("Method : marketTicket starts");
		
		logger.info("Method : marketTicket ends");
		return ticketDashboardDao.marketTicket();
	}
	/*
	 * totla generalTicket
	 */
	@GetMapping(value = "generalChart")
	public List<DropDownModel> generalTicketTable() {
		logger.info("Method : generalTicketTable starts");
		
		logger.info("Method : generalTicketTable ends");
		return ticketDashboardDao.generalTicketTable();
	}
	/*
	 * total serviceTicket
	 */
	@GetMapping(value = "servicelChart")
	public List<DropDownModel> serviceTicketTable() {
		logger.info("Method : serviceTicketTable starts");
		
		logger.info("Method : serviceTicketTable ends");
		return ticketDashboardDao.serviceTicketTable();
	}
	/*
	 * total complains
	 */
	@GetMapping(value = "complainChart")
	public List<DropDownModel> complainTicketTable() {
		logger.info("Method : complainTicketTable starts");
		
		logger.info("Method : complainTicketTable ends");
		return ticketDashboardDao.complainTicketTable();
	}
	/*
	 * total sales
	 */
	@GetMapping(value = "salesChart")
	public List<DropDownModel> salesTicketTable() {
		logger.info("Method : salesTicketTable starts");
		
		logger.info("Method : salesTicketTable ends");
		return ticketDashboardDao.salesTicketTable();
	}
	
	/*
	 * Market table
	 */
	@GetMapping(value = "marketChart")
	public List<DropDownModel> marketTicketTable() {
		logger.info("Method : marketTicketTable starts");
		
		logger.info("Method : marketTicketTable ends");
		return ticketDashboardDao.marketTicketTable();
	}
	/*
	 *General tickets For Graph
	 */
	@RequestMapping(value = "getGeneralTicket", method = { RequestMethod.GET })
	public List<TicketDashboardRestModel> getGeneralTicketPie() {

		logger.info("Method : getGeneralTicketPie starts");
		logger.info("Method : getGeneralTicketPie ends");

		return ticketDashboardDao.getGeneralTicketPie();
	}
	/*
	 *Service tickets For Graph
	 */
	@RequestMapping(value = "getServiceTicket", method = { RequestMethod.GET })
	public List<TicketDashboardRestModel> getServicePieChart() {
		
		logger.info("Method : getServicePieChart starts");
		logger.info("Method : getServicePieChart ends");
		
		return ticketDashboardDao.getServicePieChart();
	}
	/*
	 *Complaints tickets For Graph
	 */
	@RequestMapping(value = "getComplaintsTicket", method = { RequestMethod.GET })
	public List<TicketDashboardRestModel> getComplaintsPieChart() {
		
		logger.info("Method : getComplaintsPieChart starts");
		logger.info("Method : getComplaintsPieChart ends");
		
		return ticketDashboardDao.getComplaintsPieChart();
	}
	/*
	 *Sales tickets For Graph
	 */
	@RequestMapping(value = "getSalesTicket", method = { RequestMethod.GET })
	public List<TicketDashboardRestModel> getSalesPieChart() {
		
		logger.info("Method : getSalesPieChart starts");
		logger.info("Method : getSalesPieChart ends");
		
		return ticketDashboardDao.getSalesPieChart();
	}
	/*
	 *Market tickets For Graph
	 */
	@RequestMapping(value = "getMarketTicket", method = { RequestMethod.GET })
	public List<TicketDashboardRestModel> getmarketPieChart() {
		
		logger.info("Method : getmarketPieChart starts");
		logger.info("Method : getmarketPieChart ends");
		
		return ticketDashboardDao.getmarketPieChart();
	}
	/*
	 * Location wise customer ticket
	 */
	@RequestMapping(value = "getLocationCustomer", method = { RequestMethod.GET })
	public List<TicketDashboardRestModel> getLocationCustomerCount() {
		
		logger.info("Method : getLocationCustomerCount starts");
		logger.info("Method : getLocationCustomerCount ends");
		
		return ticketDashboardDao.getLocationCustomerCount();
	}
}
