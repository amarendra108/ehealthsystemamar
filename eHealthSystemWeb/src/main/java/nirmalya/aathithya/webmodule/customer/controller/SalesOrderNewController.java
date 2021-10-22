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
import nirmalya.aathithya.webmodule.customer.model.SalesOrderNewModel;
import nirmalya.aathithya.webmodule.master.model.ProductCategoryModel;
import nirmalya.aathithya.webmodule.procurment.model.InventorySkuProductModel;

@Controller
@RequestMapping(value = { "customer/" })
public class SalesOrderNewController {
	
	Logger logger = LoggerFactory.getLogger(SalesOrderNewController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	SalesOrderNewController salesOrderNewController;

	@GetMapping(value = { "/view-saleorder" })
	public String salesOrderDetails(Model model, HttpSession session) {
		logger.info("Method : salesOrderDetails starts");
		
		try {
			DropDownModel[] payMode = restTemplate
					.getForObject(env.getCustomerUrl() + "GetStoreList", DropDownModel[].class);
			List<DropDownModel> storeList = Arrays.asList(payMode);
				//System.out.println("storeList@"+storeList);
			model.addAttribute("storeList", storeList);


		} catch (Exception e) {
			e.printStackTrace();

		}
		 
		
		logger.info("Method : salesOrderDetails ends");
		return "customer/view-salesorder";
	}
	
	/*
	 * customer Auto search
	 */
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "view-saleorder-get-customer-list" })
	public @ResponseBody JsonResponse<SalesOrderNewModel> getCustomerAutoSearchNewList(Model model,
			@RequestBody String searchValue, BindingResult result) {
		logger.info("Method : getCustomerAutoSearchNewList starts");
		JsonResponse<SalesOrderNewModel> res = new JsonResponse<SalesOrderNewModel>();

		try {
			res = restTemplate.getForObject(env.getCustomerUrl()+ "getCustomerAutoSearchNewList?id=" + searchValue,
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
	
		logger.info("Method : getCustomerAutoSearchNewList ends");
		return res;
	}
	
	/*
	 * Item auto search
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "view-saleorder-item-get-customer-list" })
	public @ResponseBody JsonResponse<SalesOrderNewModel> getItemQuotationAutoSearchNewList(Model model,
			@RequestBody String searchValue, BindingResult result) {
		logger.info("Method : getItemQuotationAutoSearchNewList starts");
		
		JsonResponse<SalesOrderNewModel> res = new JsonResponse<SalesOrderNewModel>();

		try {
			res = restTemplate.getForObject(env.getCustomerUrl()+ "getItemQuotationAutoSearchNewList?id=" + searchValue,
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
		
		logger.info("Method : getItemQuotationAutoSearchNewList ends");
		return res;
	}
	//get Product Category Data List Modal

	@SuppressWarnings("unchecked")
	@PostMapping("view-saleorder-item-get-product-list")
	public @ResponseBody JsonResponse<List<ProductCategoryModel>> getProductCategoryListModal(
			@RequestBody String yearDtls, HttpSession session) {
		logger.info("Method : getProductCategoryListModal starts");

		JsonResponse<List<ProductCategoryModel>> resp = new JsonResponse<List<ProductCategoryModel>>();

		try {
			resp = restTemplate.getForObject(env.getInventoryUrl() + "getProductCategoryDataListModal",
					JsonResponse.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("success");
		}
		
		logger.info("Method : getProductCategoryListModal starts");
		return resp;
	}
		
		// grt product by cat

		@SuppressWarnings("unchecked")

		@PostMapping(value = { "view-saleorder-item-product-by-cat" })
		public @ResponseBody JsonResponse<InventorySkuProductModel> getProductsByCat(Model model, @RequestBody String index,
				BindingResult result) {
			logger.info("Method : getProductsByCat starts");
			//System.out.println(index);
			String indexValue = index.substring(0, index.length() - 1);

			JsonResponse<InventorySkuProductModel> res = new JsonResponse<InventorySkuProductModel>();

			try {
				res = restTemplate.getForObject(env.getCustomerUrl() + "getProductsNByCat?id=" + indexValue, JsonResponse.class);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (res.getMessage() != null) {

				res.setCode(res.getMessage());
				res.setMessage("Unsuccess");
			} else {
				res.setMessage("success");
			}
			//System.out.println(res);
			logger.info("Method : getProductsByCat ends");
			return res;
		}
		
		/*
		 * Add
		 */
		@SuppressWarnings("unchecked")
		@PostMapping("view-saleorder-add")
		public @ResponseBody JsonResponse<Object> addsalenew(HttpSession session,
				@RequestBody List<SalesOrderNewModel> salesOrderNewModel) {
			logger.info("Method : addsalenew starts");
			System.out.println(salesOrderNewModel);
			JsonResponse<Object> resp = new JsonResponse<Object>();
			String userId = "";
			String dateFormat = "";
			try {
				userId = (String) session.getAttribute("USER_ID");
				dateFormat = (String) session.getAttribute("DATEFORMAT");

			} catch (Exception e) {

			}
			for (SalesOrderNewModel m : salesOrderNewModel) {
				m.setQutCreatedBy(userId);
				
			}

			try {
				resp = restTemplate.postForObject(env.getCustomerUrl() + "addsalenew", salesOrderNewModel,
						JsonResponse.class);
				ObjectMapper mapper = new ObjectMapper();
				
				  List<SalesOrderNewModel> quotation = mapper.convertValue(resp.getBody(), 
						  new TypeReference<List<SalesOrderNewModel>>() {
				  });
				 
				for (SalesOrderNewModel m : salesOrderNewModel) {
					if (m.getQutValidDate() != null && m.getQutValidDate() != "") {
						m.setQutValidDate(DateFormatter.inputDateFormat(m.getQutValidDate(), dateFormat));
					}
					if (m.getOrderReceiveDate() != null && m.getOrderReceiveDate() != "") {
						m.setOrderReceiveDate(DateFormatter.inputDateFormat(m.getOrderReceiveDate(), dateFormat));
					}
					/*
					 * if (m.getOrderReceiveTime()!= null && m.getOrderReceiveTime() != "") {
					 * m.setOrderReceiveTime(DateFormatter.inputDateFormat(m.getOrderReceiveTime(),
					 * dateFormat)); }
					 */
				}

				resp.setBody(quotation);
				System.out.println(salesOrderNewModel);
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

			logger.info("Method : addsalenew ends");

			return resp;
		}
		
		/*
		 * view
		 */
		@SuppressWarnings("unchecked")

		@GetMapping("view-saleorder-through-ajax")
		public @ResponseBody List<SalesOrderNewModel> viewsalesOrder(HttpSession session) {

			logger.info("Method :getAllsalesOrder starts");
			JsonResponse<List<SalesOrderNewModel>> resp = new JsonResponse<List<SalesOrderNewModel>>();
				
			try {

				resp = restTemplate.getForObject(env.getCustomerUrl() + "getAllsalesOrder", JsonResponse.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();

			List<SalesOrderNewModel> salesOrderNewModel = mapper.convertValue(resp.getBody(),
					new TypeReference<List<SalesOrderNewModel>>() {
					});
			String dateFormat = "";
			try {
				dateFormat = (String) session.getAttribute("DATEFORMAT");
			} catch (Exception e) {

			}
			for (SalesOrderNewModel a : salesOrderNewModel) {
				if (a.getQutValidDate() != null && a.getQutValidDate() != "") {
					a.setQutValidDate(DateFormatter.dateFormat(a.getQutValidDate(), dateFormat));
				}
				if (a.getOrderReceiveDate() != null && a.getOrderReceiveDate() != "") {
					a.setOrderReceiveDate(DateFormatter.dateFormat(a.getOrderReceiveDate(), dateFormat));
				}

				/*
				 * if (a.getOrderReceiveTime() != null && a.getOrderReceiveTime() != "") {
				 * a.setOrderReceiveTime(DateFormatter.dateFormat(a.getOrderReceiveTime(),
				 * dateFormat)); }
				 */
				  if (a.getQutUpdatedOn() != null && a.getQutUpdatedOn() != "") {
				  a.setQutUpdatedOn(DateFormatter.dateFormat(a.getQutUpdatedOn(), dateFormat));
				  }
				System.out.println(salesOrderNewModel); 

			}

			resp.setBody(salesOrderNewModel);
			if (resp.getMessage() != "" && resp.getMessage() != null) {
				resp.setCode(resp.getMessage());
				resp.setMessage("Unsuccess");
			} else {
				resp.setMessage("Success");
			}

			logger.info("Method :getAllsalesOrder ends");
			System.out.println("RESPONSEview" + resp);
			return resp.getBody();
		}
		
		
		/*
		* for editing using employee id
		*
		*
		*/
		@GetMapping(value = { "view-saleorder-edit-new" })
		public @ResponseBody List<SalesOrderNewModel> viewsalesOrdeerEdit(@RequestParam String id,
				HttpSession session) {
			logger.info("Method : viewsalesOrdeerEdit starts");
			 System.out.println(id);
			List<SalesOrderNewModel> productList = new ArrayList<SalesOrderNewModel>();

			if (id != null && id != "") {
				try {
					SalesOrderNewModel[] salesOrderNewModel = restTemplate.getForObject(
							env.getCustomerUrl() + "viewsalesOrdeerEdit?id=" + id, SalesOrderNewModel[].class);

					productList = Arrays.asList(salesOrderNewModel);

					productList.forEach(s -> s.setSlNo(s.getSlNo()));

					int count = 0;

					for (SalesOrderNewModel m : salesOrderNewModel) {

						count++;
						m.setSlNo(count);

						String dateFormat = (String) session.getAttribute("DATEFORMAT");

						if (m.getQutValidDate() != null && m.getQutValidDate() != "") {
							m.setQutValidDate(DateFormatter.dateFormat(m.getQutValidDate(), dateFormat));

						}
						if (m.getOrderReceiveDate() != null && m.getOrderReceiveDate() != "") {
							m.setOrderReceiveDate(DateFormatter.dateFormat(m.getOrderReceiveDate(), dateFormat));

						}
						
					}

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			logger.info("Method : viewsalesOrdeerEdit ends");
			System.out.println("edit@@@@@@@@" + productList);
			return productList;
		}
		
/*
 * * Delete
 */
		
		@SuppressWarnings("unchecked")
		@PostMapping(value = "view-saleorder-delete")
		public @ResponseBody JsonResponse<Object> deletesalesOrder(
				@RequestBody SalesOrderNewModel salesOrderNewModel, HttpSession session) {
			logger.info("Method : deletesalesOrder starts");
			System.out.println("SalesOrderNewModel"+salesOrderNewModel);
			JsonResponse<Object> resp = new JsonResponse<Object>();
			String userId = "";
			try {
				userId = (String) session.getAttribute("USER_ID");
				salesOrderNewModel.setQutCreatedBy(userId);
			} catch (Exception e) {

			}
			try {

				resp = restTemplate.postForObject(env.getCustomerUrl() + "deletesalesOrder",
						salesOrderNewModel, JsonResponse.class);
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
			logger.info("Method : deletesalesOrder Ends");
			return resp;
		}
		
}
