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

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.ticket.model.ManageSupervisorModel;

@Controller
@RequestMapping(value = "ticket/")
public class ManageSupervisorController {

	Logger logger = LoggerFactory.getLogger(ManageSupervisorController.class);

	@Autowired
	RestTemplate restClient;

	@Autowired
	EnvironmentVaribles env;

	/***************************
	 * Page Return
	 *************************************************************/

	@GetMapping("view-manageSupervisor")
	public String viewPageManageSupervisor(Model model, HttpSession session) {

		logger.info("Method : viewPageManageSupervisor starts");

		JsonResponse<Object> Ticket = new JsonResponse<Object>();
		model.addAttribute("Ticket", Ticket);
		try {
			DropDownModel[] Status = restClient.getForObject(env.getTicketUrl() + "get-Status-Name",
					DropDownModel[].class);

			List<DropDownModel> statusList = Arrays.asList(Status);
			model.addAttribute("statusList", statusList);

		}

		catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			DropDownModel[] Supports = restClient.getForObject(env.getTicketUrl() + "get-Supports-Name",
					DropDownModel[].class);

			List<DropDownModel> supportList = Arrays.asList(Supports);
			model.addAttribute("supportList", supportList);

		}

		catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("Method : viewPageManageSupervisor ends");

		return "ticket/viewManageSupervisor";
	}

	/***************************
	 * General Enquiry
	 *************************************************************/
	/*
	 * View General Enquiry
	 * 
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("view-manageSupervisor-generalEnquiry")
	public @ResponseBody List<ManageSupervisorModel> viewAllGenEnq() {

		logger.info("Method : viewAllGeneralEnquiry starts");

		JsonResponse<List<ManageSupervisorModel>> resp = new JsonResponse<List<ManageSupervisorModel>>();

		try {
			resp = restClient.getForObject(env.getTicketUrl() + "rest-viewAllGenEnq", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewAllGeneralEnquiry ends");

		// System.out.println("View web controller======="+resp.getBody());
		return resp.getBody();
	}
	// model View for General Enquiry

	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-manageSupervisor-modalViewGenEnq" })
	public @ResponseBody JsonResponse<Object> modalViewGeneral(Model model, @RequestBody String index,
			BindingResult result) {

		logger.info("Method : modalTicketType starts");

		JsonResponse<Object> res = new JsonResponse<Object>();

		try {
			res = restClient.getForObject(env.getTicketUrl() + "modalViewGeneralSupervisorById?id=" + index,
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

		logger.info("Method : modalViewGeneral ends");
		return res;
	}

	/*
	 * for All view model general query ticket Master
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-manageSupervisor-commentModalViewGenEnq" })
	public @ResponseBody JsonResponse<Object> modalTicketGeneralSupervisor(Model model, @RequestBody String index,
			BindingResult result) {

		logger.info("Method : modalTicketGeneralSupervisor starts");
		/*
		 * byte[] encodeByte = Base64.getDecoder().decode(index.getBytes()); String id =
		 * (new String(encodeByte));
		 */
		JsonResponse<Object> res = new JsonResponse<Object>();

		try {
			//System.out.println("Try Id=============" + index);
			res = restClient.getForObject(env.getTicketUrl() + "rest-getGeneralSupervisorCommentModalById?id=" + index,
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

		logger.info("Method : modalTicketGeneralSupervisor ends");
		//System.out.println("Try Id=============" + res);
		return res;
	}
	/*
	 * for All add model service ticket comments Master
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("/view-manageSupervisor-addCommentsGenEnq")
	public @ResponseBody JsonResponse<Object> addCommentsForGeneral(Model model, @RequestParam("id") String id1,
			@RequestParam("comment") String comment, @RequestParam("status") String status, HttpSession session) {

		logger.info("Method : addCommentsForAgent starts");
		byte[] encodeByte1 = Base64.getDecoder().decode(id1.getBytes());
		String id = (new String(encodeByte1));

		JsonResponse<Object> resp = new JsonResponse<Object>();
		// System.out.println("add comment web controller======="+resp);
		try {
			String userId = "";
			try {
				userId = (String) session.getAttribute("USER_ID");
			} catch (Exception e) {
				e.printStackTrace();
			}
			// escalationMatrixModel.setCreatedBy(userId);
			String createdBy = userId;

			resp = restClient.getForObject(env.getTicketUrl() + "rest-addCommentsGeneralSupervisorById?id=" + id
					+ "&comment=" + comment + "&status=" + status + "&createdBy=" + createdBy, JsonResponse.class);

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

	/*
	 * for All add model general ticket Assign Master
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("/view-manageSupervisor-addAssignSupportsGenEnq")
	public @ResponseBody JsonResponse<Object> assignSupports(Model model, @RequestParam("id") String id1,
			@RequestParam("comment") String comment, @RequestParam("supports") String supports,
			@RequestParam("CallQueue") String CallQueue, HttpSession session) {

		logger.info("Method : assignSupports starts");
		byte[] encodeByte1 = Base64.getDecoder().decode(id1.getBytes());
		String id = (new String(encodeByte1));

		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// escalationMatrixModel.setCreatedBy(userId);
		String createdBy = userId;

		JsonResponse<Object> resp = new JsonResponse<Object>();
		//System.out.println("assign web controller======="+supports+comment+CallQueue);
		try {
			resp = restClient.getForObject(env.getTicketUrl() + "assignSupportsGenEnq?id=" + id + "&comment=" + comment + "&supports="
									+ supports + "&createdBy=" + createdBy + "&CallQueue=" + CallQueue,JsonResponse.class);

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
		logger.info("Method : assignSupports ends");
		//System.out.println("assign web controller======="+resp);
		return resp;
	}

	 /************************* Service Request**************************************************/

	/*
	 * View Service Request
	 * 
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("view-manageSupervisor-serviceRquest")
	public @ResponseBody List<ManageSupervisorModel> viewAllSerReq() {

		logger.info("Method : viewAllSerReq starts");

		JsonResponse<List<ManageSupervisorModel>> resp = new JsonResponse<List<ManageSupervisorModel>>();

		try {
			resp = restClient.getForObject(env.getTicketUrl() + "rest-viewAllSerReq", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewAllSerReq ends");

		// System.out.println("View web controller======="+resp);
		return resp.getBody();
	}

//model View for Service Request
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-manageSupervisor-modalViewSerReq" })
	public @ResponseBody JsonResponse<ManageSupervisorModel> modelViewServiceRequest(Model model,
			@RequestBody String index, BindingResult result) {

		logger.info("Method : modelViewServiceRequest starts");

		JsonResponse<ManageSupervisorModel> res = new JsonResponse<ManageSupervisorModel>();

		try {
			// System.out.println("try Block--------------------------" + index);
			res = restClient.getForObject(env.getTicketUrl() + "rest-modalSupervisorViewSerReq?id=" + index,
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

		logger.info("Method :modelViewServiceRequest ends");

		// System.out.println("Modal View=============" + res);
		return res;
	}

	/*
	 * for All view model service ticket Master
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-manageSupervisor-commentModalViewSerReq" })
	public @ResponseBody JsonResponse<Object> commentSupervisorModalViewSerReq(Model model, @RequestBody String index,
			BindingResult result) {

		logger.info("Method : commentSupervisorModalViewSerReq starts");

		JsonResponse<Object> res = new JsonResponse<Object>();

		try {
			res = restClient.getForObject(
					env.getTicketUrl() + "rest-getcommentSupervisorModalViewSerReqById?id=" + index,
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

		logger.info("Method : commentSupervisorModalViewSerReq ends");
		return res;
	}
	/*
	 * for All add model service ticket comments Master
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("/view-manageSupervisor-addCommentsSerReq")
	public @ResponseBody JsonResponse<Object> addCommentsForSupervisor(Model model, @RequestParam("id") String id1,
			@RequestParam("comment") String comment, @RequestParam("status") String status, HttpSession session) {

		logger.info("Method : addCommentsForSupervisor starts");
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

			resp = restClient.getForObject(env.getTicketUrl() + "rest-addServiceCommentsSupervisor?id=" + id
					+ "&comment=" + comment + "&status=" + status + "&createdBy=" + createdBy, JsonResponse.class);

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
		logger.info("Method : addCommentsForSupervisor ends");
		return resp;
	}

	/*
	 * for All add model service ticket Assign Master
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("/view-manageSupervisor-addAssignSupportsSerReq")
	public @ResponseBody JsonResponse<Object> assignSupportsSerReq(Model model, @RequestParam("id") String id1,
			@RequestParam("comment") String comment, @RequestParam("supports") String supports,
			@RequestParam("CallQueue") String CallQueue, HttpSession session) {

		logger.info("Method : assignSupports starts");
		byte[] encodeByte1 = Base64.getDecoder().decode(id1.getBytes());
		String id = (new String(encodeByte1));

		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// escalationMatrixModel.setCreatedBy(userId);
		String createdBy = userId;

		JsonResponse<Object> resp = new JsonResponse<Object>();
		//System.out.println("assign web controller======="+supports+comment+CallQueue);
		try {
			resp = restClient.getForObject(env.getTicketUrl() + "assignSupportsSerReq?id=" + id + "&comment=" + comment + "&supports="
									+ supports + "&createdBy=" + createdBy + "&CallQueue=" + CallQueue,JsonResponse.class);

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
		logger.info("Method : assignSupports ends");
		//System.out.println("assign web controller======="+resp);
		return resp;
	}
	/*************************** Complaints*************************************************************/
	/*
	 * View All Complaint
	 * 
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("view-manageSupervisor-complaint")
	public @ResponseBody List<ManageSupervisorModel> viewAllComp() {

		logger.info("Method : viewAllComp starts");

		JsonResponse<List<ManageSupervisorModel>> resp = new JsonResponse<List<ManageSupervisorModel>>();

		try {
			resp = restClient.getForObject(env.getTicketUrl() + "rest-viewAllComp", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewAllComplaint ends");

//System.out.println("View web controller======="+resp);
		return resp.getBody();
	}

//model View for Complaints Request
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-manageSupervisor-modalViewComp" })
	public @ResponseBody JsonResponse<ManageSupervisorModel> modelViewSupervisorComp(Model model,
			@RequestBody String index, BindingResult result) {

		logger.info("Method : modelViewSupervisorComp starts");

		JsonResponse<ManageSupervisorModel> res = new JsonResponse<ManageSupervisorModel>();

		try {
			// System.out.println("try Block--------------------------" + index);
			res = restClient.getForObject(env.getTicketUrl() + "rest-modalSupervisorViewComp?id=" + index,
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

		logger.info("Method :modelViewSupervisorComp ends");

		// System.out.println("Modal View=============" + res);
		return res;
	}

	/*
	 * for All view model comp ticket Master
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-manageSupervisor-commentModalViewComp" })
	public @ResponseBody JsonResponse<Object> commentSupervisorModalViewComp(Model model, @RequestBody String index,
			BindingResult result) {

		logger.info("Method : commentSupervisorModalViewComp starts");

		JsonResponse<Object> res = new JsonResponse<Object>();

		try {
			res = restClient.getForObject(env.getTicketUrl() + "rest-getcommentSupervisorModalViewCompById?id=" + index,
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

		logger.info("Method : commentSupervisorModalViewComp ends");
		return res;
	}
	/*
	 * for All add model comp ticket comments Master
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("/view-manageSupervisor-addCommentsComp")
	public @ResponseBody JsonResponse<Object> addCommentsForSupervisorComp(Model model, @RequestParam("id") String id1,
			@RequestParam("comment") String comment, @RequestParam("status") String status, HttpSession session) {

		logger.info("Method : addCommentsForSupervisorComp starts");
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

			resp = restClient.getForObject(env.getTicketUrl() + "rest-addServiceCommentsSupervisorComp?id=" + id
					+ "&comment=" + comment + "&status=" + status + "&createdBy=" + createdBy, JsonResponse.class);

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
		logger.info("Method : addCommentsForSupervisorComp ends");
		return resp;
	}
	/*
	 * for All add model general ticket Assign Master
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("/view-manageSupervisor-addAssignSupportsComp")
	public @ResponseBody JsonResponse<Object> assignSupportsComp(Model model, @RequestParam("id") String id1,
			@RequestParam("comment") String comment, @RequestParam("supports") String supports,
			@RequestParam("CallQueue") String CallQueue, HttpSession session) {

		logger.info("Method : assignSupports starts");
		byte[] encodeByte1 = Base64.getDecoder().decode(id1.getBytes());
		String id = (new String(encodeByte1));

		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// escalationMatrixModel.setCreatedBy(userId);
		String createdBy = userId;

		JsonResponse<Object> resp = new JsonResponse<Object>();
		//System.out.println("assign web controller======="+supports+comment+CallQueue);
		try {
			resp = restClient.getForObject(env.getTicketUrl() + "assignSupportsComp?id=" + id + "&comment=" + comment + "&supports="
									+ supports + "&createdBy=" + createdBy + "&CallQueue=" + CallQueue,JsonResponse.class);

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
		logger.info("Method : assignSupports ends");
		//System.out.println("assign web controller======="+resp);
		return resp;
	}
	/**************************** Sales         ****************************************************/
	/*
	 * View All Sales
	 * 
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("view-manageSupervisor-sales")
	public @ResponseBody List<ManageSupervisorModel> viewAllSale() {

		logger.info("Method : viewAllSale starts");

		JsonResponse<List<ManageSupervisorModel>> resp = new JsonResponse<List<ManageSupervisorModel>>();

		try {
			resp = restClient.getForObject(env.getTicketUrl() + "rest-viewAllSale", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewAllSale ends");

		// System.out.println("View web controller======="+resp);
		return resp.getBody();
	}

//model View for Complaints Request
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-manageSupervisor-modalViewSale" })
	public @ResponseBody JsonResponse<ManageSupervisorModel> modelViewSupervisorSale(Model model,
			@RequestBody String index, BindingResult result) {

		logger.info("Method : modelViewSupervisorSale starts");

		JsonResponse<ManageSupervisorModel> res = new JsonResponse<ManageSupervisorModel>();

		try {
			// System.out.println("try Block--------------------------" + index);
			res = restClient.getForObject(env.getTicketUrl() + "rest-modalSupervisorViewSale?id=" + index,
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

		logger.info("Method :modelViewSupervisorSale ends");

		// System.out.println("Modal View=============" + res);
		return res;
	}

	/*
	 * for All view model comp ticket Master
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-manageSupervisor-commentModalViewSale" })
	public @ResponseBody JsonResponse<Object> commentSupervisorModalViewSale(Model model, @RequestBody String index,
			BindingResult result) {

		logger.info("Method : commentSupervisorModalViewSale starts");

		JsonResponse<Object> res = new JsonResponse<Object>();

		try {
			res = restClient.getForObject(env.getTicketUrl() + "rest-getcommentSupervisorModalViewSaleById?id=" + index,
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

		logger.info("Method : commentSupervisorModalViewSale ends");
		return res;
	}
	/*
	 * for All add model comp ticket comments Master
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("/view-manageSupervisor-addCommentsSale")
	public @ResponseBody JsonResponse<Object> addCommentsForSupervisorSale(Model model, @RequestParam("id") String id1,
			@RequestParam("comment") String comment, @RequestParam("status") String status, HttpSession session) {

		logger.info("Method : addCommentsForSupervisorComp starts");
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

			resp = restClient.getForObject(env.getTicketUrl() + "rest-addServiceCommentsSupervisorSale?id=" + id
					+ "&comment=" + comment + "&status=" + status + "&createdBy=" + createdBy, JsonResponse.class);

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
		logger.info("Method : addCommentsForSupervisorSale ends");
		return resp;
	}
	/*
	 * for All add model general ticket Assign Master
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("/view-manageSupervisor-addAssignSupportsSale")
	public @ResponseBody JsonResponse<Object> assignSupportsSale(Model model, @RequestParam("id") String id1,
			@RequestParam("comment") String comment, @RequestParam("supports") String supports,
			@RequestParam("CallQueue") String CallQueue, HttpSession session) {

		logger.info("Method : assignSupports starts");
		byte[] encodeByte1 = Base64.getDecoder().decode(id1.getBytes());
		String id = (new String(encodeByte1));

		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// escalationMatrixModel.setCreatedBy(userId);
		String createdBy = userId;

		JsonResponse<Object> resp = new JsonResponse<Object>();
		//System.out.println("assign web controller======="+supports+comment+CallQueue);
		try {
			resp = restClient.getForObject(env.getTicketUrl() + "assignSupportsSale?id=" + id + "&comment=" + comment + "&supports="
									+ supports + "&createdBy=" + createdBy + "&CallQueue=" + CallQueue,JsonResponse.class);

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
		logger.info("Method : assignSupports ends");
		//System.out.println("assign web controller======="+resp);
		return resp;
	}
	/***************************
	 * Marketing Request
	 *************************************************************/
	/*
	 * View All Marketing Request
	 * 
	 */

	@SuppressWarnings("unchecked")

	@GetMapping("view-manageSupervisor-marketingRequest")
	public @ResponseBody List<ManageSupervisorModel> viewAllMktgReq() {

		logger.info("Method :  viewAllMktgReq starts");

		JsonResponse<List<ManageSupervisorModel>> resp = new JsonResponse<List<ManageSupervisorModel>>();

		try {
			resp = restClient.getForObject(env.getTicketUrl() + "rest- viewAllMktgReq", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method :  viewAllMktgReq ends");

		// System.out.println("View web controller======="+resp);
		return resp.getBody();
	}

//model View for MktgReq Request
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-manageSupervisor-modalViewMktgReq" })
	public @ResponseBody JsonResponse<ManageSupervisorModel> modelViewSupervisorMktgReq(Model model,
			@RequestBody String index, BindingResult result) {

		logger.info("Method : modelViewSupervisorMktgReq starts");

		JsonResponse<ManageSupervisorModel> res = new JsonResponse<ManageSupervisorModel>();

		try {
			// System.out.println("try Block--------------------------" + index);
			res = restClient.getForObject(env.getTicketUrl() + "rest-modalSupervisorViewMktgReq?id=" + index,
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

		logger.info("Method :modelViewSupervisorMktgReq ends");

		// System.out.println("Modal View=============" + res);
		return res;
	}

	/*
	 * for All view model MktgReq ticket Master
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-manageSupervisor-commentModalViewMktgReq" })
	public @ResponseBody JsonResponse<Object> commentSupervisorModalViewMktgReq(Model model, @RequestBody String index,
			BindingResult result) {

		logger.info("Method : commentSupervisorModalViewMktgReq starts");

		JsonResponse<Object> res = new JsonResponse<Object>();

		try {
			res = restClient.getForObject(
					env.getTicketUrl() + "rest-getcommentSupervisorModalViewMktgReqById?id=" + index,
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

		logger.info("Method : commentSupervisorModalViewMktgReq ends");
		return res;
	}
	/*
	 * for All add model MktgReq ticket comments Master
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("/view-manageSupervisor-addCommentsMktgReq")
	public @ResponseBody JsonResponse<Object> addCommentsForSupervisorMktgReq(Model model,
			@RequestParam("id") String id1, @RequestParam("comment") String comment,
			@RequestParam("status") String status, HttpSession session) {

		logger.info("Method : addCommentsForSupervisorMktgReq starts");
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

			resp = restClient.getForObject(env.getTicketUrl() + "rest-addServiceCommentsSupervisorMktgReq?id=" + id
					+ "&comment=" + comment + "&status=" + status + "&createdBy=" + createdBy, JsonResponse.class);

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
		logger.info("Method : addCommentsForSupervisorMktgReq ends");
		return resp;
	}
	/*
	 * for All add model general ticket Assign Master
	 */

	@SuppressWarnings("unchecked")
	@GetMapping("/view-manageSupervisor-addAssignSupportsMktgReq")
	public @ResponseBody JsonResponse<Object> assignSupportsMktgReq(Model model, @RequestParam("id") String id1,
			@RequestParam("comment") String comment, @RequestParam("supports") String supports,
			@RequestParam("CallQueue") String CallQueue, HttpSession session) {

		logger.info("Method : assignSupports starts");
		byte[] encodeByte1 = Base64.getDecoder().decode(id1.getBytes());
		String id = (new String(encodeByte1));

		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// escalationMatrixModel.setCreatedBy(userId);
		String createdBy = userId;

		JsonResponse<Object> resp = new JsonResponse<Object>();
		//System.out.println("assign web controller======="+supports+comment+CallQueue);
		try {
			resp = restClient.getForObject(env.getTicketUrl() + "assignSupportsMktgReq?id=" + id + "&comment=" + comment + "&supports="
									+ supports + "&createdBy=" + createdBy + "&CallQueue=" + CallQueue,JsonResponse.class);

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
		logger.info("Method : assignSupports ends");
		//System.out.println("assign web controller======="+resp);
		return resp;
	}
}
