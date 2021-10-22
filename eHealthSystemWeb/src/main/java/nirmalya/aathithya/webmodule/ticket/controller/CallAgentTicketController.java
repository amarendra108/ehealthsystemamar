package nirmalya.aathithya.webmodule.ticket.controller;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.ticket.model.CallAgentTicketModel;

@Controller
@RequestMapping(value = "ticket/")
public class CallAgentTicketController {

	Logger logger = LoggerFactory.getLogger(CallAgentTicketController.class);

	@Autowired
	RestTemplate restClient;

	@Autowired
	EnvironmentVaribles env;
	/***************************         Page Return & Drop Down              *************************************************************/
	@GetMapping("view-agentTicket")
	public String viewAgentTicket(Model model, HttpSession session) {

		logger.info("Method : viewAgentTicket starts");

		try {
			DropDownModel[] userLocation = restClient.getForObject(env.getTicketUrl() + "getUserLocationForTicket",
					DropDownModel[].class);

			List<DropDownModel> userLocationList = Arrays.asList(userLocation);
			model.addAttribute("userLocationList", userLocationList);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			DropDownModel[] industrialType = restClient.getForObject(env.getTicketUrl() + "getIndustrialType",
					DropDownModel[].class);

			List<DropDownModel> industrialTypeList = Arrays.asList(industrialType);
			model.addAttribute("industrialTypeList", industrialTypeList);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			DropDownModel[] userType = restClient.getForObject(env.getTicketUrl() + "getCostmerType",
					DropDownModel[].class);

			List<DropDownModel> userTypeList = Arrays.asList(userType);
			model.addAttribute("userTypeList", userTypeList);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			DropDownModel[] callQueue = restClient.getForObject(env.getTicketUrl() + "getCallQueueForTicket",
					DropDownModel[].class);

			List<DropDownModel> callQueueList = Arrays.asList(callQueue);
			model.addAttribute("callQueueList", callQueueList);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			DropDownModel[] productType = restClient.getForObject(env.getTicketUrl() + "getProductTypeList",
					DropDownModel[].class);

			List<DropDownModel> productTypeList = Arrays.asList(productType);
			model.addAttribute("productTypeList", productTypeList);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			DropDownModel[] serviceCenter = restClient.getForObject(env.getTicketUrl() + "getServiceCenterList",
					DropDownModel[].class);

			List<DropDownModel> serviceCenterList = Arrays.asList(serviceCenter);
			model.addAttribute("serviceCenterList", serviceCenterList);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			DropDownModel[] complaintCategory = restClient.getForObject(env.getTicketUrl() + "getComplaintCategory",
					DropDownModel[].class);

			List<DropDownModel> complaintCategoryList = Arrays.asList(complaintCategory);
			model.addAttribute("complaintCategoryList", complaintCategoryList);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			DropDownModel[] complaintLocation = restClient.getForObject(env.getTicketUrl() + "getComplaintLocation",
					DropDownModel[].class);

			List<DropDownModel> complaintLocationList = Arrays.asList(complaintLocation);
			model.addAttribute("complaintLocationList", complaintLocationList);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			DropDownModel[] status = restClient.getForObject(env.getTicketUrl() + "getStatusForTicket",
					DropDownModel[].class);

			List<DropDownModel> statusList = Arrays.asList(status);
			model.addAttribute("statusList", statusList);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Method : viewAgentTicket ends");

		return "ticket/viewAgentTicket";
	}

	/*
	 * for drop down for call reason through onchange
	 */

	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-agentTicket-getCallReason" })
	public @ResponseBody JsonResponse<DropDownModel> getCallReasonList(Model model, @RequestBody String index,
			BindingResult result) {
		logger.info("Method : getCallReasonList starts");

		JsonResponse<DropDownModel> res = new JsonResponse<DropDownModel>();
		// System.out.println("Index web controller======="+index);
		try {
			res = restClient.getForObject(env.getTicketUrl() + "getCallReasonList?callQueue=" + index,
					JsonResponse.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (res.getMessage() != null) {
			System.out.println("if block getmsg() not false : " + res.getMessage());
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}

		logger.info("Method : getCallReasonList ends");
		//System.out.println("call reason web controller=======" + res);
		return res;
	}
	/***************************         Call Details/All Tickets              *************************************************************/
	/*
	 * View Call Details
	 * 
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("view-agentTicket_throughAjax")
	public @ResponseBody List<CallAgentTicketModel> viewAllAgentTicket() {

		logger.info("Method : viewAllAgentTicket starts");

		JsonResponse<List<CallAgentTicketModel>> resp = new JsonResponse<List<CallAgentTicketModel>>();

		try {
			resp = restClient.getForObject(env.getTicketUrl() + "rest-viewAgentTicket", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<CallAgentTicketModel> Ticket = mapper.convertValue(resp.getBody(),
				new TypeReference<List<CallAgentTicketModel>>() {
				});


		for (CallAgentTicketModel m : Ticket) {
			
			String cId = m.getCallQueueId();

			m.setAction(cId);
		
		}
		logger.info("Method : viewAllAgentTicket ends");

		 //System.out.println("View web controller======="+resp);
		return resp.getBody();
	}
	/***************************         Add agent tickets details              *************************************************************/
	/*
	 * Add Agent Ticket
	 *
	 */

	@SuppressWarnings("unchecked")
	@PostMapping("view-agentTicket_addDetails")
	public @ResponseBody JsonResponse<Object> addAgentTicket(@RequestBody CallAgentTicketModel ticketModel,
			HttpSession session) {

		logger.info("Method : addAgentTicket starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		//System.out.println("Add Agent Ticket============="+ticketModel);
		try {
			String userId = "";
			try {
				userId = (String) session.getAttribute("USER_ID");
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*
			 * String agentId = ""; try { userId = (String)
			 * session.getAttribute("USER_ID"); } catch (Exception e) {
			 * e.printStackTrace(); }
			 */

			ticketModel.setCreatedBy(userId);
			//ticketModel.setAgentId(userId);
			// escalationMatrixModel.setCreatedBy("u0001");
			resp = restClient.postForObject(env.getTicketUrl() + "rest-addAgentTicket", ticketModel, JsonResponse.class);

		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : addAgentTicket ends");

		return resp;
	}
	/*
	 * post Mapping for auto complete for customer details in call agent
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-agentTicket-add-customerautofill" })
	public @ResponseBody JsonResponse<CallAgentTicketModel> getCustomerListMob(Model model,
			@RequestBody String searchValue, BindingResult result) {
		logger.info("Method : getCustomerListMob starts");

		JsonResponse<CallAgentTicketModel> res = new JsonResponse<CallAgentTicketModel>();

		try {
			res = restClient.getForObject(env.getTicketUrl() + "getCustomerAutofillMob?id=" + searchValue,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (res.getMessage() != null) {
			System.out.println("if block getmsg() not false : " + res.getMessage());
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getCustomerListMob ends");
		return res;
	}
	/***************************         General Enquiry              *************************************************************/
	/*
	 * View General Enquiry
	 * 
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("view-agentTicket-generalEnquiry")
	public @ResponseBody List<CallAgentTicketModel> viewAllGeneralEnquiry() {

		logger.info("Method : viewAllGeneralEnquiry starts");

		JsonResponse<List<CallAgentTicketModel>> resp = new JsonResponse<List<CallAgentTicketModel>>();

		try {
			resp = restClient.getForObject(env.getTicketUrl() + "rest-viewAllGeneralEnquiry", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewAllGeneralEnquiry ends");

		//System.out.println("View web controller======="+resp);
		return resp.getBody();
	}
	//model View for General Enquiry
			@SuppressWarnings("unchecked")
			@PostMapping(value = { "/view-agentTicket-modalViewGenEnq" })
			public @ResponseBody JsonResponse<CallAgentTicketModel> modelViewGeneralEnquiry(Model model, @RequestBody String index,
					BindingResult result) {

				logger.info("Method : modelViewGeneralEnquiry starts");

				JsonResponse<CallAgentTicketModel> res = new JsonResponse<CallAgentTicketModel>();

				try {
					//System.out.println("try Block--------------------------" + index);
					res = restClient.getForObject(env.getTicketUrl() + "getGeneralEnquiryAgentById?id=" + index, JsonResponse.class);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if (res.getMessage() != null) {

					res.setCode(res.getMessage());
					res.setMessage("Unsuccess");
				} else {
					res.setMessage("success");
				}

				logger.info("Method :modelViewGeneralEnquiry ends");

				//System.out.println("Modal View=============" + res);
				return res;
			}
			
			/*
			 * for All view model general query ticket Master
			 */
			@SuppressWarnings("unchecked")
			@PostMapping(value = { "/view-agentTicket-commentViewGenEnq" })
			public @ResponseBody JsonResponse<Object> modalTicketGeneralAgentType(Model model, @RequestBody String index,
					BindingResult result) {

				logger.info("Method : modalTicketGeneralAgentType starts");
				/*
				 * byte[] encodeByte = Base64.getDecoder().decode(index.getBytes()); String id =
				 * (new String(encodeByte));
				 */
				JsonResponse<Object> res = new JsonResponse<Object>();

				try {
					System.out.println("Try Id=============" + index);
					res = restClient.getForObject(env.getTicketUrl() + "rest-getTicketTGeneralCommentModalById?id=" + index,
							JsonResponse.class);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (res.getMessage() != null) {

					res.setCode(res.getMessage());
					res.setMessage("Unsuccess");
				} else {
					res.setMessage("success");
				}

				logger.info("Method : modalTicketGeneralAgentType ends");
				System.out.println("Try Id=============" + res);
				return res;
			}
			/*
			 * for All add model service ticket comments Master
			 */

			@SuppressWarnings("unchecked")
			@GetMapping("/view-agentTicket-addCommentsGenEnq")
			public @ResponseBody JsonResponse<Object> addCommentsForGeneral(Model model, @RequestParam("id") String id1,
					@RequestParam("comment") String comment, @RequestParam("status") String status, HttpSession session) {

				logger.info("Method : addCommentsForAgent starts");
				byte[] encodeByte1 = Base64.getDecoder().decode(id1.getBytes());
				String id = (new String(encodeByte1));

				JsonResponse<Object> resp = new JsonResponse<Object>();
				//System.out.println("add comment web controller======="+resp);
				try {
					String userId = "";
					try {
						userId = (String) session.getAttribute("USER_ID");
					} catch (Exception e) {
						e.printStackTrace();
					}
					// escalationMatrixModel.setCreatedBy(userId);
					String createdBy = userId;

					resp = restClient.getForObject(env.getTicketUrl() + "rest-addCommentsGeneralAgentById?id=" + id + "&comment="
							+ comment + "&status=" + status + "&createdBy=" + createdBy, JsonResponse.class);

				} catch (RestClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (resp.getMessage() != null && resp.getMessage() != "") {
					System.out.println("if block getmsg() not false : " + resp.getMessage());
					resp.setCode(resp.getMessage());
					resp.setMessage("Unsuccess");
				} else {
					resp.setMessage("success");
				}
				logger.info("Method : addCommentsForAgent ends");
				return resp;
			}
			
	/* *************************          Service Request              **************************************************/
			
	/*
	 * View Service Request
	 * 
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("view-agentTicket-serviceRquest")
	public @ResponseBody List<CallAgentTicketModel> viewAllServiceRequest() {

		logger.info("Method : viewAllServiceRequest starts");

		JsonResponse<List<CallAgentTicketModel>> resp = new JsonResponse<List<CallAgentTicketModel>>();

		try {
			resp = restClient.getForObject(env.getTicketUrl() + "rest-viewAllServiceRequest", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewAllServiceRequest ends");

		//System.out.println("View web controller======="+resp);
		return resp.getBody();
	}
	//model View for Service Request
		@SuppressWarnings("unchecked")
		@PostMapping(value = { "/view-agentTicket-modalViewSerReq" })
		public @ResponseBody JsonResponse<CallAgentTicketModel> modelViewServiceRequest(Model model, @RequestBody String index,
				BindingResult result) {

			logger.info("Method : modelViewServiceRequest starts");

			JsonResponse<CallAgentTicketModel> res = new JsonResponse<CallAgentTicketModel>();

			try {
				//System.out.println("try Block--------------------------" + index);
				res = restClient.getForObject(env.getTicketUrl() + "getServiceRequestAgentById?id=" + index, JsonResponse.class);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (res.getMessage() != null) {

				res.setCode(res.getMessage());
				res.setMessage("Unsuccess");
			} else {
				res.setMessage("success");
			}

			logger.info("Method :modelViewServiceRequest ends");

			//System.out.println("Modal View=============" + res);
			return res;
		}

		/*
		 * for All view model service ticket Master
		 */
		@SuppressWarnings("unchecked")
		@PostMapping(value = { "/view-agentTicket-commentModalViewSerReq" })
		public @ResponseBody JsonResponse<Object> modalTicketType(Model model, @RequestBody String index,
				BindingResult result) {

			logger.info("Method : modalTicketType starts");
		
			JsonResponse<Object> res = new JsonResponse<Object>();

			try {
				res = restClient.getForObject(env.getTicketUrl() + "rest-getTicketTServiceCommentModalById?id=" + index,
						JsonResponse.class);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (res.getMessage() != null) {

				res.setCode(res.getMessage());
				res.setMessage("Unsuccess");
			} else {
				res.setMessage("success");
			}

			logger.info("Method : modalTicketType ends");
			return res;
		}
		/*
		 * for All add model service ticket comments Master
		 */

		@SuppressWarnings("unchecked")
		@GetMapping("/view-agentTicket-addCommentsSerReq")
		public @ResponseBody JsonResponse<Object> addCommentsForAgent(Model model, @RequestParam("id") String id1,
				@RequestParam("comment") String comment, @RequestParam("status") String status, HttpSession session) {

			logger.info("Method : addCommentsForAgent starts");
			byte[] encodeByte1 = Base64.getDecoder().decode(id1.getBytes());
			String id = (new String(encodeByte1));

			JsonResponse<Object> resp = new JsonResponse<Object>();

			try {
				String userId = "";
				try {
					userId = (String) session.getAttribute("USER_ID");
				} catch (Exception e) {
					e.printStackTrace();
				}
				// escalationMatrixModel.setCreatedBy(userId);
				String createdBy = userId;

				resp = restClient.getForObject(env.getTicketUrl() + "rest-addServiceCommentsAgent?id=" + id + "&comment=" + comment
						+ "&status=" + status + "&createdBy=" + createdBy, JsonResponse.class);

			} catch (RestClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (resp.getMessage() != null && resp.getMessage() != "") {
				System.out.println("if block getmsg() not false : " + resp.getMessage());
				resp.setCode(resp.getMessage());
				resp.setMessage("Unsuccess");
			} else {
				resp.setMessage("success");
			}
			logger.info("Method : addCommentsForAgent ends");
			return resp;
		}

		
		/***************************         Complaints              *************************************************************/
	/*
	 * View All Complaint
	 * 
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("view-agentTicket-complaint")
	public @ResponseBody List<CallAgentTicketModel> viewAllComplaint() {

		logger.info("Method : viewAllComplaint starts");

		JsonResponse<List<CallAgentTicketModel>> resp = new JsonResponse<List<CallAgentTicketModel>>();

		try {
			resp = restClient.getForObject(env.getTicketUrl() + "rest-viewAllComplaint", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewAllComplaint ends");

		//System.out.println("View web controller======="+resp);
		return resp.getBody();
	}
	//model View for Service Request
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-agentTicket-modalViewComp" })
	public @ResponseBody JsonResponse<CallAgentTicketModel> modelViewComplaint(Model model, @RequestBody String index,
			BindingResult result) {

		logger.info("Method : modelViewComplaint starts");

		JsonResponse<CallAgentTicketModel> res = new JsonResponse<CallAgentTicketModel>();

		try {
			//System.out.println("try Block--------------------------" + index);
			res = restClient.getForObject(env.getTicketUrl() + "getComplaintAgentById?id=" + index, JsonResponse.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (res.getMessage() != null) {

			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}

		logger.info("Method :modelViewComplaint ends");

		//System.out.println("Modal View=============" + res);
		return res;
	}

	/*
	 * for All view comment model Complaints ticket Master
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-agentTicket-commentModalViewComp" })
	public @ResponseBody JsonResponse<Object> modalTicketComplaintAgentType(Model model, @RequestBody String index,
			BindingResult result) {

		logger.info("Method : modalTicketComplaintAgentType starts");
	
		JsonResponse<Object> res = new JsonResponse<Object>();

		try {
			res = restClient.getForObject(env.getTicketUrl() + "rest-getTicketComplaintCommentModalById?id=" + index,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (res.getMessage() != null) {

			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}

		logger.info("Method : modalTicketComplaintAgentType ends");
		return res;
	}
	/*
	 * for All add model Complaints comments Master
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("/view-agentTicket-addCommentsComp")
	public @ResponseBody JsonResponse<Object> addCommentsForComplaintAgent(Model model, @RequestParam("id") String id1,
			@RequestParam("comment") String comment, @RequestParam("status") String status, HttpSession session) {

		logger.info("Method : addCommentsForComplaintAgent starts");
		byte[] encodeByte1 = Base64.getDecoder().decode(id1.getBytes());
		String id = (new String(encodeByte1));

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String userId = "";
			try {
				userId = (String) session.getAttribute("USER_ID");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// escalationMatrixModel.setCreatedBy(userId);
			String createdBy = userId;

			resp = restClient.getForObject(env.getTicketUrl() + "rest-addCommentsComplaintAgentById?id=" + id + "&comment="
					+ comment + "&status=" + status + "&createdBy=" + createdBy, JsonResponse.class);

		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (resp.getMessage() != null && resp.getMessage() != "") {
			System.out.println("if block getmsg() not false : " + resp.getMessage());
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("success");
		}
		logger.info("Method : addCommentsForComplaintAgent ends");
		return resp;
	}
	
	/***************************         Sales              *************************************************************/
	/*
	 * View All Sales
	 * 
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("view-agentTicket-sales")
	public @ResponseBody List<CallAgentTicketModel> viewAllSales() {

		logger.info("Method : viewAllSales starts");

		JsonResponse<List<CallAgentTicketModel>> resp = new JsonResponse<List<CallAgentTicketModel>>();

		try {
			resp = restClient.getForObject(env.getTicketUrl() + "rest-viewAllSales", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewAllSales ends");

		//System.out.println("View web controller======="+resp);
		return resp.getBody();
	}
	//model View for Sales
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-agentTicket-modalViewSale" })
	public @ResponseBody JsonResponse<CallAgentTicketModel> modelViewSale(Model model, @RequestBody String index,
			BindingResult result) {

		logger.info("Method : modelViewSale starts");

		JsonResponse<CallAgentTicketModel> res = new JsonResponse<CallAgentTicketModel>();

		try {
			//System.out.println("try Block--------------------------" + index);
			res = restClient.getForObject(env.getTicketUrl() + "getSaleAgentById?id=" + index, JsonResponse.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (res.getMessage() != null) {

			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}

		logger.info("Method :modelViewSale ends");

		//System.out.println("Modal View=============" + res);
		return res;
	}
	/*
	 * for All view model Sales ticket Master
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-agentTicket-commentModalViewSale" })
	public @ResponseBody JsonResponse<Object> modalTicketSalesAgentType(Model model, @RequestBody String index,
			BindingResult result) {

		logger.info("Method : modalTicketSalesAgentType starts");

		JsonResponse<Object> res = new JsonResponse<Object>();

		try {
			res = restClient.getForObject(env.getTicketUrl() + "rest-getTicketSalesCommentModalById?id=" + index,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (res.getMessage() != null) {

			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}

		logger.info("Method : modalTicketSalesAgentType ends");
		return res;
	}
	/*
	 * for All add model Sales ticket comments Master
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("/view-agentTicket-addCommentsSale")
	public @ResponseBody JsonResponse<Object> addCommentsForSalesAgent(Model model, @RequestParam("id") String id1,
			@RequestParam("comment") String comment, @RequestParam("status") String status, HttpSession session) {

		logger.info("Method : addCommentsForSalesAgent starts");
		byte[] encodeByte1 = Base64.getDecoder().decode(id1.getBytes());
		String id = (new String(encodeByte1));

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String userId = "";
			try {
				userId = (String) session.getAttribute("USER_ID");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// escalationMatrixModel.setCreatedBy(userId);
			String createdBy = userId;

			resp = restClient.getForObject(env.getTicketUrl() + "rest-addCommentsForSalesAgentById?id=" + id + "&comment="
					+ comment + "&status=" + status + "&createdBy=" + createdBy, JsonResponse.class);

		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (resp.getMessage() != null && resp.getMessage() != "") {
			System.out.println("if block getmsg() not false : " + resp.getMessage());
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("success");
		}
		logger.info("Method : addCommentsForSalesAgent ends");
		return resp;
	}
	
	/***************************         Marketing Request              *************************************************************/
	/*
	 * View All Marketing Request
	 * 
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("view-agentTicket-marketingRequest")
	public @ResponseBody List<CallAgentTicketModel> viewAllMarketingRequest() {

		logger.info("Method :  viewAllMarketingRequest starts");

		JsonResponse<List<CallAgentTicketModel>> resp = new JsonResponse<List<CallAgentTicketModel>>();

		try {
			resp = restClient.getForObject(env.getTicketUrl() + "rest- viewAllMarketingRequest", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method :  viewAllMarketingRequest ends");

		//System.out.println("View web controller======="+resp);
		return resp.getBody();
	}
	
	//model View for Marketing Request
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-agentTicket-modalViewMktgReq" })
	public @ResponseBody JsonResponse<CallAgentTicketModel> modelViewMarketingRequest(Model model, @RequestBody String index,
			BindingResult result) {

		logger.info("Method : modelViewMarketingRequest starts");

		JsonResponse<CallAgentTicketModel> res = new JsonResponse<CallAgentTicketModel>();

		try {
			//System.out.println("try Block--------------------------" + index);
			res = restClient.getForObject(env.getTicketUrl() + "getMarketingRequestAgentById?id=" + index, JsonResponse.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (res.getMessage() != null) {

			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}

		logger.info("Method :modelViewMarketingRequest ends");

		//System.out.println("Modal View=============" + res);
		return res;
	}

	/*
	 * for All view model marketing ticket Master
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-agentTicket-commentModalViewMktgReq" })
	public @ResponseBody JsonResponse<Object> modalTicketMarketingAgentType(Model model, @RequestBody String index,
			BindingResult result) {

		logger.info("Method : modalTicketComplaintAgentType starts");
	
		JsonResponse<Object> res = new JsonResponse<Object>();

		try {
			res = restClient.getForObject(env.getTicketUrl() + "rest-getTicketMarketingCommentModalById?id=" + index,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (res.getMessage() != null) {

			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}

		logger.info("Method : modalTicketComplaintAgentType ends");
		return res;
	}
	/*
	 * for All add model marketing comments Master
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("/view-agentTicket-addCommentsMktgReq")
	public @ResponseBody JsonResponse<Object> addCommentsForMarketingAgent(Model model, @RequestParam("id") String id1,
			@RequestParam("comment") String comment, @RequestParam("status") String status, HttpSession session) {

		logger.info("Method : addCommentsForMarketingAgent starts");
		byte[] encodeByte1 = Base64.getDecoder().decode(id1.getBytes());
		String id = (new String(encodeByte1));
		
		//System.out.println(""+id1+comment+status);

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String userId = "";
			try {
				userId = (String) session.getAttribute("USER_ID");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// escalationMatrixModel.setCreatedBy(userId);
			String createdBy = userId;

			resp = restClient.getForObject(env.getTicketUrl() + "rest-addCommentsMarketingAgentById?id=" + id + "&comment="
					+ comment + "&status=" + status + "&createdBy=" + createdBy, JsonResponse.class);

		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (resp.getMessage() != null && resp.getMessage() != "") {
			System.out.println("if block getmsg() not false : " + resp.getMessage());
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("success");
		}
		logger.info("Method : addCommentsForMarketingAgent ends");
		return resp;
	}
	
}
