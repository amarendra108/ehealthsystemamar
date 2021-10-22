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
import nirmalya.aathithya.webmodule.customer.model.QuotationNewModel;
import nirmalya.aathithya.webmodule.customer.model.SalesInvoiceNewModel;
import nirmalya.aathithya.webmodule.customer.model.SalesOrderNewModel;

@Controller
@RequestMapping(value = { "customer/" })

public class SalesInvoiceNewController {

	Logger logger = LoggerFactory.getLogger(SalesOrderNewController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	SalesInvoiceNewController salesInvoiceNewController;
	
	@GetMapping(value = { "/view-saleInvoice" })
	public String salesInvoiceDetails(Model model, HttpSession session) {
		logger.info("Method : salesInvoiceDetails starts");
		
		try {
			DropDownModel[] payMode = restTemplate
					.getForObject(env.getCustomerUrl() + "GetStoreNameList", DropDownModel[].class);
			List<DropDownModel> storeList = Arrays.asList(payMode);
				//System.out.println("storeList@"+storeList);
			model.addAttribute("storeList", storeList);


		} catch (Exception e) {
			e.printStackTrace();

		}
		
		try {
			DropDownModel[] payMode = restTemplate
					.getForObject(env.getCustomerUrl() + "GetpaymentModeList", DropDownModel[].class);
			List<DropDownModel> paymentModeList = Arrays.asList(payMode);
				System.out.println("paymentModeList@"+paymentModeList);
			model.addAttribute("paymentModeList", paymentModeList);


		} catch (Exception e) {
			e.printStackTrace();

		}
		
		logger.info("Method : salesInvoiceDetails ends");
		return "customer/view-sale-invoice";
	}
	
/*
 * sales Order Auto Search	
 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "view-saleInvoice-get-customer-list" })
	public @ResponseBody JsonResponse<SalesInvoiceNewModel> getSalesOrderAutoSearchNewList(Model model,
			@RequestBody String searchValue, BindingResult result) {
		
		logger.info("Method : getSalesOrderAutoSearchNewList starts");
		System.out.println("SalesInvoiceNewModel"+searchValue);
		JsonResponse<SalesInvoiceNewModel> res = new JsonResponse<SalesInvoiceNewModel>();

		try {
			res = restTemplate.getForObject(env.getCustomerUrl()+ "getSalesOrderAutoSearchNewList?id=" + searchValue,
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
		System.out.println("response@"+res);
		logger.info("Method : getSalesOrderAutoSearchNewList ends");
		return res;
	}
	
	/*
	 * Add
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("view-saleInvoice-add")
	public @ResponseBody JsonResponse<Object> addsaleInvoicenew(HttpSession session,
			@RequestBody List<SalesInvoiceNewModel> salesInvoiceNewModel) {
		logger.info("Method : addsaleInvoicenew starts");
		System.out.println(salesInvoiceNewModel);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
	
		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {

		}
		for (SalesInvoiceNewModel m : salesInvoiceNewModel) {
			m.setQutCreatedBy(userId);
			
		}

		try {
			resp = restTemplate.postForObject(env.getCustomerUrl() + "addsaleInvoicenew", salesInvoiceNewModel,
					JsonResponse.class);
			ObjectMapper mapper = new ObjectMapper();
			
			  List<SalesInvoiceNewModel> quotation = mapper.convertValue(resp.getBody(), 
					  new TypeReference<List<SalesInvoiceNewModel>>() {
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

		logger.info("Method : addsaleInvoicenew ends");

		return resp;
	}

	/*
	 * view
	 */
	
	@SuppressWarnings("unchecked")

	@GetMapping("view-saleInvoice-through-ajax")
	public @ResponseBody List<SalesInvoiceNewModel> viewsalesInvoice(HttpSession session) {

		logger.info("Method :getAllsalesInvoice starts");
		JsonResponse<List<SalesInvoiceNewModel>> resp = new JsonResponse<List<SalesInvoiceNewModel>>();
			
		try {

			resp = restTemplate.getForObject(env.getCustomerUrl() + "getAllsalesInvoice", JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		List<SalesInvoiceNewModel> salesInvoiceNewModel = mapper.convertValue(resp.getBody(),
				new TypeReference<List<SalesInvoiceNewModel>>() {
				});
		String dateFormat = "";
		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		for (SalesInvoiceNewModel a : salesInvoiceNewModel) {
			a.setQuantitynew(a.getQuantity());
			  if (a.getQutUpdatedOn() != null && a.getQutUpdatedOn() != "") {
			  a.setQutUpdatedOn(DateFormatter.dateFormat(a.getQutUpdatedOn(), dateFormat));
			  }
			System.out.println(salesInvoiceNewModel); 

		}

		resp.setBody(salesInvoiceNewModel);
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method :getAllsalesInvoice ends");
		System.out.println("RESPONSEview" + resp);
		return resp.getBody();
	}
	
	/*
	 * edit SalesInvoice
	 */
	
	@GetMapping(value = { "view-saleInvoice-edit-new" })
	public @ResponseBody List<SalesInvoiceNewModel> viewsalesIvoiceEdit(@RequestParam String id,
			HttpSession session) {
		logger.info("Method : viewsalesIvoiceEdit starts");
		 System.out.println(id);
		List<SalesInvoiceNewModel> productList = new ArrayList<SalesInvoiceNewModel>();

		if (id != null && id != "") {
			try {
				SalesInvoiceNewModel[] salesInvoiceNewModel = restTemplate.getForObject(
						env.getCustomerUrl() + "viewsalesIvoiceEdit?id=" + id, SalesInvoiceNewModel[].class);

				productList = Arrays.asList(salesInvoiceNewModel);

				productList.forEach(s -> s.setSlNo(s.getSlNo()));

				int count = 0;

				for (SalesInvoiceNewModel m : salesInvoiceNewModel) {
					m.setQuantitynew(m.getQuantity());
					count++;
					m.setSlNo(count);

					
				}

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.info("Method : viewsalesIvoiceEdit ends");
		System.out.println("edit@@@@@@@@" + productList);
		return productList;
	}
	
/*
 * delete	
 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "view-saleInvoice-delete")
	public @ResponseBody JsonResponse<Object> deletesalesInvoice(
			@RequestBody SalesInvoiceNewModel salesInvoiceNewModel, HttpSession session) {
		logger.info("Method : deletesalesOrder starts");
		System.out.println("SalesInvoiceNewModel"+salesInvoiceNewModel);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			salesInvoiceNewModel.setQutCreatedBy(userId);
		} catch (Exception e) {

		}
		try {

			resp = restTemplate.postForObject(env.getCustomerUrl() + "deletesalesInvoice",
					salesInvoiceNewModel, JsonResponse.class);
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
		logger.info("Method : deletesalesInvoice Ends");
		return resp;
	}
	
	
	/*
	 *  payment  Add
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("view-saleInvoice-payment-add")
	public @ResponseBody JsonResponse<Object> addinvPaymentnew(Model model,HttpSession session,
			
			@RequestBody SalesInvoiceNewModel salesInvoiceNewModel) {
		logger.info("Method : addinvPaymentnew starts");
		System.out.println(salesInvoiceNewModel);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		String dateFormat = "";
	
		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
			salesInvoiceNewModel.setQutCreatedBy(userId);
			
		

		try {
			resp = restTemplate.postForObject(env.getCustomerUrl() + "addinvPaymentnew", salesInvoiceNewModel,
					JsonResponse.class);
			ObjectMapper mapper = new ObjectMapper();
			
			  List<SalesInvoiceNewModel> quotation = mapper.convertValue(resp.getBody(), 
					  new TypeReference<List<SalesInvoiceNewModel>>() {
			  });
			  
			 
					if (salesInvoiceNewModel.getPayDate() != null && salesInvoiceNewModel.getPayDate() != "") {
						salesInvoiceNewModel.setPayDate(DateFormatter.inputDateFormat(salesInvoiceNewModel.getPayDate(), dateFormat));
					
				}
			
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

		logger.info("Method : addinvPaymentnew ends");

		return resp;
	}	
}


