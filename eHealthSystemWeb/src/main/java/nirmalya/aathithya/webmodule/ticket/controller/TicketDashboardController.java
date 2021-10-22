package nirmalya.aathithya.webmodule.ticket.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.ticket.model.TicketDashboardModel;

@Controller
@RequestMapping(value = "ticket/")
public class TicketDashboardController {
	Logger logger = LoggerFactory.getLogger(TicketDashboardController.class);
	

	@Autowired
	RestTemplate restClient;
	
	@Autowired
	EnvironmentVaribles env;
	
	@GetMapping("view-ticket-dashbboard")
	public String viewTicketDashboard(Model model, HttpSession session) {
		logger.info("method : viewTicketDashboard starts");
		
		DropDownModel[] ticketCount = restClient.getForObject(env.getTicketUrl() + "ticketCount", DropDownModel[].class);
		List<DropDownModel> totalTicket = Arrays.asList(ticketCount);
		model.addAttribute("totalTicket", totalTicket);
		

		DropDownModel[] generalQuery = restClient.getForObject(env.getTicketUrl() + "generalQuery", DropDownModel[].class);
		List<DropDownModel> generalTicket = Arrays.asList(generalQuery);
		model.addAttribute("generalTicket", generalTicket);
		

		DropDownModel[] serviceRequest = restClient.getForObject(env.getTicketUrl() + "serviceRequest", DropDownModel[].class);
		List<DropDownModel> serviceTicket = Arrays.asList(serviceRequest);
		model.addAttribute("serviceTicket", serviceTicket);
		

		DropDownModel[] complains = restClient.getForObject(env.getTicketUrl() + "complains", DropDownModel[].class);
		List<DropDownModel> complainTicket = Arrays.asList(complains);
		model.addAttribute("complainTicket", complainTicket);
		

		DropDownModel[] sales = restClient.getForObject(env.getTicketUrl() + "sales", DropDownModel[].class);
		List<DropDownModel> salesTicket = Arrays.asList(sales);
		model.addAttribute("salesTicket", salesTicket);
		
		DropDownModel[] market = restClient.getForObject(env.getTicketUrl() + "market", DropDownModel[].class);
		List<DropDownModel> marketTicket = Arrays.asList(market);
		model.addAttribute("marketTicket", marketTicket);
		/*
		 * Dashboard Table
		 */
		DropDownModel[] generalChart = restClient.getForObject(env.getTicketUrl() + "generalChart", DropDownModel[].class);
		List<DropDownModel> generalChartTicket = Arrays.asList(generalChart);
		model.addAttribute("generalChartTicket", generalChartTicket);
		
		DropDownModel[] servicelChart = restClient.getForObject(env.getTicketUrl() + "servicelChart", DropDownModel[].class);
		List<DropDownModel> servicelChartTicket = Arrays.asList(servicelChart);
		model.addAttribute("servicelChartTicket", servicelChartTicket);
		
		DropDownModel[] complainChart = restClient.getForObject(env.getTicketUrl() + "complainChart", DropDownModel[].class);
		List<DropDownModel> complainChartTicket = Arrays.asList(complainChart);
		model.addAttribute("complainChartTicket", complainChartTicket);
		
		DropDownModel[] salesChart = restClient.getForObject(env.getTicketUrl() + "salesChart", DropDownModel[].class);
		List<DropDownModel> salesChartTicket = Arrays.asList(salesChart);
		model.addAttribute("salesChartTicket", salesChartTicket);
		
		DropDownModel[] marketChart = restClient.getForObject(env.getTicketUrl() + "marketChart", DropDownModel[].class);
		List<DropDownModel> marketChartTicket = Arrays.asList(marketChart);
		model.addAttribute("marketChartTicket", marketChartTicket);
		
		logger.info("method : viewTicketDashboard ends");
		return "ticket/ticket-dashboard";
	}
	/*
	 * General pie For Graph
	 */

	@GetMapping("/view-ticket-dashbboard-general-piechart")
	public @ResponseBody JsonResponse<Object> getPieChart(HttpSession session) {

		logger.info("Method : getPieChart starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			TicketDashboardModel[] auditTypes = restClient.getForObject(env.getTicketUrl() + "getGeneralTicket",
					TicketDashboardModel[].class);
			List<TicketDashboardModel> dataList = Arrays.asList(auditTypes);
			
			resp.setBody(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(resp);
		logger.info("Method : getPieChart ends");

		return resp;
	}
	/*
	 * General pie For Graph
	 */
	
	@GetMapping("/view-ticket-dashbboard-servicce-piechart")
	public @ResponseBody JsonResponse<Object> getServicePieChart(HttpSession session) {
		
		logger.info("Method : getServicePieChart starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			TicketDashboardModel[] auditTypes = restClient.getForObject(env.getTicketUrl() + "getServiceTicket",
					TicketDashboardModel[].class);
			List<TicketDashboardModel> dataList = Arrays.asList(auditTypes);
			
			resp.setBody(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(resp);
		logger.info("Method : getServicePieChart ends");
		
		return resp;
	}
	/*
	 * General pie For Graph
	 */
	
	@GetMapping("/view-ticket-dashbboard-complains-piechart")
	public @ResponseBody JsonResponse<Object> getComplaintsPieChart(HttpSession session) {
		
		logger.info("Method : getComplaintsPieChart starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			TicketDashboardModel[] auditTypes = restClient.getForObject(env.getTicketUrl() + "getComplaintsTicket",
					TicketDashboardModel[].class);
			List<TicketDashboardModel> dataList = Arrays.asList(auditTypes);
			
			resp.setBody(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(resp);
		logger.info("Method : getComplaintsPieChart ends");
		
		return resp;
	}
	/*
	 * Sales pie For Graph
	 */
	
	@GetMapping("/view-ticket-dashbboard-sales-piechart")
	public @ResponseBody JsonResponse<Object> getSalesPieChart(HttpSession session) {
		
		logger.info("Method : getSalesPieChart starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			TicketDashboardModel[] auditTypes = restClient.getForObject(env.getTicketUrl() + "getSalesTicket",
					TicketDashboardModel[].class);
			List<TicketDashboardModel> dataList = Arrays.asList(auditTypes);
			
			resp.setBody(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(resp);
		logger.info("Method : getSalesPieChart ends");
		
		return resp;
	}
	/*
	 * Marketing pie For Graph
	 */
	
	@GetMapping("/view-ticket-dashbboard-marketing-piechart")
	public @ResponseBody JsonResponse<Object> getmarketPieChart(HttpSession session) {
		
		logger.info("Method : getmarketPieChart starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			TicketDashboardModel[] auditTypes = restClient.getForObject(env.getTicketUrl() + "getMarketTicket",
					TicketDashboardModel[].class);
			List<TicketDashboardModel> dataList = Arrays.asList(auditTypes);
			
			resp.setBody(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(resp);
		logger.info("Method : getmarketPieChart ends");
		
		return resp;
	}
	/*
	 * Location wise customer ticket
	 */
	
	@GetMapping("/view-ticket-dashbboard-location-graph")
	public @ResponseBody JsonResponse<Object> getLocationCustomerCount(HttpSession session) {
		
		logger.info("Method : getLocationCustomerCount starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		try {
			TicketDashboardModel[] auditTypes = restClient.getForObject(env.getTicketUrl() + "getLocationCustomer",
					TicketDashboardModel[].class);
			List<TicketDashboardModel> dataList = Arrays.asList(auditTypes);
			
			resp.setBody(dataList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(resp);
		logger.info("Method : getLocationCustomerCount ends");
		
		return resp;
	}
}
