package nirmalya.aathithya.webmodule.customer.controller;

import java.util.ArrayList;
import java.util.Arrays;
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

import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.customer.model.SalesInvoiceNewModel;
import nirmalya.aathithya.webmodule.customer.model.SalesInvoiceReturnNewModel;
import nirmalya.aathithya.webmodule.customer.model.SalesOrderNewModel;

@Controller
@RequestMapping(value = { "customer/" })
public class SalesInvoiceReturnNewController {

	
	Logger logger = LoggerFactory.getLogger(SalesInvoiceReturnNewController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	SalesInvoiceReturnNewController salesInvoiceReturnNewController;
	
	@GetMapping(value = { "/view-saleInvoicereturn" })
	public String salesInvoiceRetrnDetails(Model model, HttpSession session) {
		logger.info("Method : salesInvoiceRetrnDetails starts");
		
		
		logger.info("Method : salesInvoiceRetrnDetails ends");
		return "customer/view-salesInvoice-return";
	}
	/*
	 * salesOrder  Auto search
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "view-saleInvoicereturn-get-customer-list" })
	public @ResponseBody JsonResponse<SalesInvoiceReturnNewModel> salesOrderAutoSearchNewList(Model model,
			@RequestBody String searchValue, BindingResult result) {
		
		logger.info("Method : salesOrderAutoSearchNewList starts");
		System.out.println("getSalesOrderAutoSearchNewList"+searchValue);
		JsonResponse<SalesInvoiceReturnNewModel> res = new JsonResponse<SalesInvoiceReturnNewModel>();

		try {
			res = restTemplate.getForObject(env.getCustomerUrl()+ "salesOrderAutoSearchNewList?id=" + searchValue,
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
	System.out.println("resp"+res);
		logger.info("Method : salesOrderAutoSearchNewList ends");
		return res;
	}
	/*
	 * salesInvoice AutoSearch
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "view-saleInvoicereturn-get-invoice-list" })
	public @ResponseBody JsonResponse<SalesInvoiceReturnNewModel> getSalesInvoiceAutoSearchNewList(Model model,
			@RequestBody String searchValue, BindingResult result) {
		
		logger.info("Method : getSalesInvoiceAutoSearchNewList starts");
		System.out.println("getSalesInvoiceAutoSearchNewList"+searchValue);
		JsonResponse<SalesInvoiceReturnNewModel> res = new JsonResponse<SalesInvoiceReturnNewModel>();

		try {
			res = restTemplate.getForObject(env.getCustomerUrl()+ "getSalesInvoiceAutoSearchNewList?id=" + searchValue,
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
	System.out.println("resp"+res);
		logger.info("Method : getSalesInvoiceAutoSearchNewList ends");
		return res;
	}	
	
	/*
	 * Add
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("view-saleInvoicereturn-add")
	public @ResponseBody JsonResponse<Object> addsaleInvoicRtnenew(HttpSession session,
			@RequestBody List<SalesInvoiceReturnNewModel> salesInvoiceReturnNewModel) {
		logger.info("Method : addsaleInvoicRtnenew starts");
		System.out.println(salesInvoiceReturnNewModel);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
	
		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {

		}
		for (SalesInvoiceReturnNewModel m : salesInvoiceReturnNewModel) {
			m.setQutCreatedBy(userId);
			
		}

		try {
			resp = restTemplate.postForObject(env.getCustomerUrl() + "addsaleInvoicRtnenew", salesInvoiceReturnNewModel,
					JsonResponse.class);
			ObjectMapper mapper = new ObjectMapper();
			
			  List<SalesInvoiceReturnNewModel> quotation = mapper.convertValue(resp.getBody(), 
					  new TypeReference<List<SalesInvoiceReturnNewModel>>() {
			  });
			 
			
			resp.setBody(quotation);
		} catch (Exception e) {

			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		System.out.println("Sradha" + resp);

		logger.info("Method : addsaleInvoicRtnenew ends");

		return resp;
	}

	/*
	 * view
	 */
	
	@SuppressWarnings("unchecked")

	@GetMapping("view-saleInvoicereturn-through-ajax")
	public @ResponseBody List<SalesInvoiceReturnNewModel> viewsalesInvoicertn(HttpSession session) {

		logger.info("Method :getAllsalesInvoicertn starts");
		System.out.println("vewww"+salesInvoiceReturnNewController);
		JsonResponse<List<SalesInvoiceReturnNewModel>> resp = new JsonResponse<List<SalesInvoiceReturnNewModel>>();
			
		try {

			resp = restTemplate.getForObject(env.getCustomerUrl() + "getAllsalesInvoicertn", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<SalesInvoiceReturnNewModel> salesInvoiceReturnNewModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<SalesInvoiceReturnNewModel>>() {
				});
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		for (SalesInvoiceReturnNewModel a : salesInvoiceReturnNewModel) {
			a.setQuantitynew(a.getQuantity());
			  if (a.getQutUpdatedOn() != null && a.getQutUpdatedOn() != "") {
			  a.setQutUpdatedOn(DateFormatter.dateFormat(a.getQutUpdatedOn(), dateFormat));
			  }
			System.out.println(salesInvoiceReturnNewModel); 

		}

		resp.setBody(salesInvoiceReturnNewModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :getAllsalesInvoicertn ends");
		System.out.println("RESPONSEview" + resp);
		return resp.getBody();
	}
	
	/*
	 * edit SalesInvoice
	 */
	
	@GetMapping(value = { "view-saleInvoicereturn-edit-new" })
	public @ResponseBody List<SalesInvoiceReturnNewModel> viewsalesIvoicertnEdit(@RequestParam String id,
			HttpSession session) {
		logger.info("Method : viewsalesIvoicertnEdit starts");
		 System.out.println(id);
		List<SalesInvoiceReturnNewModel> productList = new ArrayList<SalesInvoiceReturnNewModel>();

		if (id != null && id != "") {
			try {
				SalesInvoiceReturnNewModel[] salesInvoiceReturnNewModel = restTemplate.getForObject(
						env.getCustomerUrl() + "viewsalesIvoicertnEdit?id=" + id, SalesInvoiceReturnNewModel[].class);

				productList = Arrays.asList(salesInvoiceReturnNewModel);

				productList.forEach(s -> s.setSlNo(s.getSlNo()));

				int count = 0;

				for (SalesInvoiceReturnNewModel m : salesInvoiceReturnNewModel) {
					m.setQuantitynew(m.getQuantity());
					count++;
					m.setSlNo(count);

					
				}

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.info("Method : viewsalesIvoicertnEdit ends");
		System.out.println("edit@@@@@@@@" + productList);
		return productList;
	}
	
	/*
	 * delete	
	 */
		@SuppressWarnings("unchecked")
		@PostMapping(value = "view-saleInvoicereturn-delete")
		public @ResponseBody JsonResponse<Object> deletesalesInvretn(
				@RequestBody SalesInvoiceReturnNewModel salesInvoiceReturnNewModel, HttpSession session) {
			logger.info("Method : deletesalesInvretn  starts");
			System.out.println("SalesInvoiceReturnNewModel"+salesInvoiceReturnNewModel);
			JsonResponse<Object> resp = new JsonResponse<Object>();
			String userId = "";
			try {
				userId = (String) session.getAttribute("USER_ID");
				salesInvoiceReturnNewModel.setQutCreatedBy(userId);
			} catch (Exception e) {

			}
			try {

				resp = restTemplate.postForObject(env.getCustomerUrl() + "deletesalesInvretn",
						salesInvoiceReturnNewModel, JsonResponse.class);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			

			if (resp.getMessage() != null && resp.getMessage() != "") {
				resp.setCode(resp.getMessage());
				resp.setMessage("Unsuccess");

			} else {
				resp.setMessage("Success");
			}
			System.out.println("delete@" + resp);
			logger.info("Method : deletesalesInvretn Ends");
			return resp;
		}
		
	
		
		/*
		 * Invoice return
		 */
		@GetMapping(value = { "view-saleInvoicereturn-edit-return-new" })
		public @ResponseBody List<SalesInvoiceReturnNewModel> viewsalesIvoicertnEditrtn(@RequestParam String id,
				HttpSession session) {
			logger.info("Method : viewsalesIvoicertnEditrtn starts");
			 System.out.println(id);
			List<SalesInvoiceReturnNewModel> productList = new ArrayList<SalesInvoiceReturnNewModel>();

			if (id != null && id != "") {
				try {
					SalesInvoiceReturnNewModel[] salesInvoiceReturnNewModel = restTemplate.getForObject(
							env.getCustomerUrl() + "viewsalesIvoicertnEditrtn?id=" + id, SalesInvoiceReturnNewModel[].class);

					productList = Arrays.asList(salesInvoiceReturnNewModel);

					productList.forEach(s -> s.setSlNo(s.getSlNo()));

					int count = 0;

					for (SalesInvoiceReturnNewModel m : salesInvoiceReturnNewModel) {
						m.setQuantitynew(m.getQuantity());
						count++;
						m.setSlNo(count);

						
					}

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			logger.info("Method : viewsalesIvoicertnEditrtn ends");
			System.out.println("edit@@@@@@@@" + productList);
			return productList;
		}
}
