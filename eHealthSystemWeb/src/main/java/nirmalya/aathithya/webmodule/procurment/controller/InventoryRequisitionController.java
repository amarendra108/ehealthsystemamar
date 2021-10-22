package nirmalya.aathithya.webmodule.procurment.controller;

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

import nirmalya.aathithya.webmodule.common.utils.ActivitylogModel;
import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.master.model.ProductCategoryModel;
import nirmalya.aathithya.webmodule.master.model.ProductMasterModel;
import nirmalya.aathithya.webmodule.procurment.model.InventoryRequisitionModel;
import nirmalya.aathithya.webmodule.procurment.model.InventorySkuProductModel;

/*
 * @author NirmalyaLabs
 *
 */
@Controller
@RequestMapping(value = "inventory/")
public class InventoryRequisitionController {

	Logger logger = LoggerFactory.getLogger(InventoryRequisitionController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@GetMapping("view-requisition")
	public String generateInventoryStockReport(Model model, HttpSession session) {

		logger.info("Method : generateInventoryStockReport starts");

		/**
		 * get DropDown value for Requisition Type
		 *
		 */

		try {
			DropDownModel[] dropDownModel = restTemplate.getForObject(env.getInventoryUrl() + "get-requisition-type",
					DropDownModel[].class);
			List<DropDownModel> requisitionTypeList = Arrays.asList(dropDownModel);
			model.addAttribute("requisitionTypeList", requisitionTypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] dropDownModel = restTemplate
					.getForObject(env.getInventoryUrl() + "get-requisition-priority", DropDownModel[].class);
			List<DropDownModel> requisitionPrioList = Arrays.asList(dropDownModel);
			model.addAttribute("requisitionPrioList", requisitionPrioList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			InventoryRequisitionModel[] inventoryStockModel = restTemplate.getForObject(
					env.getInventoryUrl() + "get-requisition-item-list", InventoryRequisitionModel[].class);
			List<InventoryRequisitionModel> productList = Arrays.asList(inventoryStockModel);
			model.addAttribute("productList", productList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		/**
		 * get DropDown value for Requisition Type
		 *
		 */

		try {
			DropDownModel[] dropDownModel = restTemplate.getForObject(env.getInventoryUrl() + "get-cost-center",
					DropDownModel[].class);
			List<DropDownModel> costCenterList = Arrays.asList(dropDownModel);
			model.addAttribute("costCenterList", costCenterList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		/**
		 * get DropDown value for Requisition Type
		 *
		 */

		try {
			DropDownModel[] dropDownModel = restTemplate.getForObject(env.getInventoryUrl() + "get-location",
					DropDownModel[].class);
			List<DropDownModel> locationList = Arrays.asList(dropDownModel);
			model.addAttribute("locationList", locationList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		/**
		 * get DropDown value for Requisition Type
		 *
		 */

		try {
			DropDownModel[] dropDownModel = restTemplate.getForObject(env.getInventoryUrl() + "get-uom",
					DropDownModel[].class);
			List<DropDownModel> uomList = Arrays.asList(dropDownModel);
			model.addAttribute("uomList", uomList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		try {
			DropDownModel[] variationType = restTemplate.getForObject(env.getMasterUrl()+"getVariationTypeListtForProduct", DropDownModel[].class);
			List<DropDownModel> variationTypeList = Arrays.asList(variationType);
			
			model.addAttribute("variationTypeList", variationTypeList);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			DropDownModel[] brand = restTemplate.getForObject(env.getMasterUrl()+"getBrandListForProduct", DropDownModel[].class);
			List<DropDownModel> brandList = Arrays.asList(brand);
			
			model.addAttribute("brandList", brandList);
		} catch(Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : generateInventoryStockReport ends");
		return "procurement/view-requisition";

	}
	
//	@SuppressWarnings("unchecked")
//	@PostMapping(value = { "/view-requisition-get-product-list" })
//	public @ResponseBody JsonResponse<InventoryRequisitionModel> getItemAutoSearchList(Model model,
//			@RequestBody String searchValue, BindingResult result) {
//		logger.info("Method : getItemAutoSearchList starts");
//
//		JsonResponse<InventoryRequisitionModel> res = new JsonResponse<InventoryRequisitionModel>();
//
//		try {
//			res = restTemplate.getForObject(env.getInventoryUrl() + "getProductListByAutoSearch?id=" + searchValue,
//					JsonResponse.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		if (res.getMessage() != null) {
//
//			res.setCode(res.getMessage());
//			res.setMessage("Unsuccess");
//		} else {
//			res.setMessage("success");
//		}
//
//		logger.info("Method : getItemAutoSearchList ends");
//		return res;
//	}

	/*
	 * for copy
	 * 
	 *  
	 */
	@GetMapping(value = { "view-requisition-item-trough-ajax" })
	public @ResponseBody List<InventoryRequisitionModel> viewRequsitionEdit(@RequestParam String id,
			HttpSession session) {
		logger.info("Method : viewRequsitionEdit starts");
		List<InventoryRequisitionModel> productList = new ArrayList<InventoryRequisitionModel>();
		if (id != null && id != "") {
			try {
				InventoryRequisitionModel[] inventoryStockModel = restTemplate.getForObject(
						env.getInventoryUrl() + "get-requisition-edit?id=" + id, InventoryRequisitionModel[].class);
				productList = Arrays.asList(inventoryStockModel);
				productList.forEach(s -> s.setId(s.getSku()));
				
				for(InventoryRequisitionModel m : inventoryStockModel) {
					String dateFormat = (String) session.getAttribute("DATEFORMAT");
					if(m.getReceiveDate() != null && m.getReceiveDate() != "") {
						m.setReceiveDate(DateFormatter.dateFormat(m.getReceiveDate(), dateFormat));
					}
					if(m.getCreatedOn() != null && m.getCreatedOn() != "") {
						m.setCreatedOn(DateFormatter.dateFormat(m.getCreatedOn(), dateFormat));
					}
					if(m.getActiveDate() != null && m.getActiveDate() != "") {
						m.setActiveDate(DateFormatter.dateFormat(m.getActiveDate(), dateFormat));
					}
					if(m.getCompleteDate() != null && m.getCompleteDate() != "") {
						m.setCompleteDate(DateFormatter.dateFormat(m.getCompleteDate(), dateFormat));
					}
					if(m.getOnHoldDate() != null && m.getOnHoldDate() != "") {
						m.setOnHoldDate(DateFormatter.dateFormat(m.getOnHoldDate(), dateFormat));
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.info("Method : viewRequsitionEdit ends");
		return productList;
	}

	/*
	 * view throughAjax
	 * 
	 * 
	 */
	@GetMapping(value = { "view-requisition-activity-log" })
	public @ResponseBody List<ActivitylogModel> getActivityLog(@RequestParam String id, HttpSession session) {
		logger.info("Method : viewStockThroughAjax starts");
		List<ActivitylogModel> activityLogList = new ArrayList<ActivitylogModel>();
		try {

			ActivitylogModel[] activityLog = restTemplate
					.getForObject(env.getInventoryUrl() + "get-activity-log?id=" + id, ActivitylogModel[].class);
			activityLogList = Arrays.asList(activityLog);
			
			for(ActivitylogModel m : activityLog) {
				String dateFormat = (String) session.getAttribute("DATEFORMAT");
				if(m.getOperationOn()!=null && m.getOperationOn()!="") {
					m.setOperationOn(DateFormatter.dateFormat(m.getOperationOn(), dateFormat));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : viewStockThroughAjax ends");
		return activityLogList;
	}

	/*
	 * post Mapping for add ItemRequisition
	 * 
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "view-requisition-save-th-ajax")
	public @ResponseBody JsonResponse<Object> saveItemRequisition(
			@RequestBody List<InventoryRequisitionModel> inventoryItemRequisitionModel, HttpSession session) {
		logger.info("Method : saveItemRequisition function starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId = "";
		String dateFormat = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {

		}
		for (InventoryRequisitionModel m : inventoryItemRequisitionModel) {
			m.setCreatedBy(userId);
			m.setReceiveDate(DateFormatter.inputDateFormat(m.getReceiveDate(), dateFormat));
		}
		try {

			res = restTemplate.postForObject(env.getInventoryUrl() + "rest-add-requisition",
					inventoryItemRequisitionModel, JsonResponse.class);
			ObjectMapper mapper = new ObjectMapper();
			List<InventoryRequisitionModel> inventoryStockModel = mapper.convertValue(res.getBody(),
					new TypeReference<List<InventoryRequisitionModel>>() {
					});
			for(InventoryRequisitionModel m : inventoryStockModel) {
				if(m.getReceiveDate() != null && m.getReceiveDate() != "") {
					m.setReceiveDate(DateFormatter.dateFormat(m.getReceiveDate(), dateFormat));
				}
				if(m.getCreatedOn() != null && m.getCreatedOn() != "") {
					m.setCreatedOn(DateFormatter.dateFormat(m.getCreatedOn(), dateFormat));
				}
				if(m.getActiveDate() != null && m.getActiveDate() != "") {
					m.setActiveDate(DateFormatter.dateFormat(m.getActiveDate(), dateFormat));
				}
				if(m.getCompleteDate() != null && m.getCompleteDate() != "") {
					m.setCompleteDate(DateFormatter.dateFormat(m.getCompleteDate(), dateFormat));
				}
				if(m.getOnHoldDate() != null && m.getOnHoldDate() != "") {
					m.setOnHoldDate(DateFormatter.dateFormat(m.getOnHoldDate(), dateFormat));
				}
			}
			res.setBody(inventoryStockModel);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = res.getMessage();

		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		} 
		logger.info("Method : saveItemRequisition function Ends");
		return res;
	}

	/**
	 * Web Controller - Get Item List By AutoSearch
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-requisition-get-product-list" })
	public @ResponseBody JsonResponse<InventoryRequisitionModel> getItemAutoSearchList(Model model,
			@RequestBody String searchValue, BindingResult result) {
		logger.info("Method : getItemAutoSearchList starts");

		JsonResponse<InventoryRequisitionModel> res = new JsonResponse<InventoryRequisitionModel>();

		try {
			res = restTemplate.getForObject(env.getInventoryUrl() + "getProductListByAutoSearch?id=" + searchValue,
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

		logger.info("Method : getItemAutoSearchList ends");
		return res;
	}

	/*
	 * view throughAjax
	 * 
	 * 
	 */
	@GetMapping(value = { "view-requisition-trough-ajax" })
	public @ResponseBody List<InventoryRequisitionModel> viewRequisitionThroughAjax(HttpSession session) {
		logger.info("Method : viewRequisitionThroughAjax starts");
		List<InventoryRequisitionModel> productList = new ArrayList<InventoryRequisitionModel>();
		String userId = "";
		
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {

			InventoryRequisitionModel[] inventoryStockModel = restTemplate.getForObject(
					env.getInventoryUrl() + "get-requisition-view-list?id="+userId, InventoryRequisitionModel[].class);
			productList = Arrays.asList(inventoryStockModel);
			
			for(InventoryRequisitionModel m : inventoryStockModel) {
				String dateFormat = (String) session.getAttribute("DATEFORMAT");
				if(m.getReceiveDate() != null && m.getReceiveDate() != "") {
					m.setReceiveDate(DateFormatter.dateFormat(m.getReceiveDate(), dateFormat));
				}
				if(m.getCreatedOn() != null && m.getCreatedOn() != "") {
					m.setCreatedOn(DateFormatter.dateFormat(m.getCreatedOn(), dateFormat));
				}
				if(m.getActiveDate() != null && m.getActiveDate() != "") {
					m.setActiveDate(DateFormatter.dateFormat(m.getActiveDate(), dateFormat));
				}
				if(m.getCompleteDate() != null && m.getCompleteDate() != "") {
					m.setCompleteDate(DateFormatter.dateFormat(m.getCompleteDate(), dateFormat));
				}
				if(m.getOnHoldDate() != null && m.getOnHoldDate() != "") {
					m.setOnHoldDate(DateFormatter.dateFormat(m.getOnHoldDate(), dateFormat));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : viewRequisitionThroughAjax ends");
		return productList;
	}

	/*
	 * post Mapping for delete ItemRequisition
	 * 
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "view-requisition-delete-th-ajax")
	public @ResponseBody JsonResponse<Object> deleteItemRequisition(
			@RequestBody InventoryRequisitionModel inventoryItemRequisitionModel, HttpSession session) {
		logger.info("Method : deleteItemRequisition function starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			inventoryItemRequisitionModel.setCreatedBy(userId);
		} catch (Exception e) {

		}
		try {

			res = restTemplate.postForObject(env.getInventoryUrl() + "rest-delete-requisition",
					inventoryItemRequisitionModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = res.getMessage();

		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		logger.info("Method : deleteItemRequisition function Ends");
		return res;
	}

	/*
	 * post Mapping for approve ItemRequisition
	 * 
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "view-requisition-approve-th-ajax")
	public @ResponseBody JsonResponse<Object> approveItemRequisition(
			@RequestBody InventoryRequisitionModel inventoryItemRequisitionModel, HttpSession session) {
		logger.info("Method : approveItemRequisition function starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			inventoryItemRequisitionModel.setCreatedBy(userId);
		} catch (Exception e) {

		}
		try {

			res = restTemplate.postForObject(env.getInventoryUrl() + "rest-approve-requisition",
					inventoryItemRequisitionModel, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = res.getMessage();

		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		logger.info("Method : approveItemRequisition function Ends");
		return res;
	}

	/**
	 * Web Controller - Get Item List By AutoSearch
	 *
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "/view-requisition-get-product-by-req-sku" })
	public @ResponseBody JsonResponse<InventoryRequisitionModel> getProductByReqList(@RequestParam String id,
			@RequestParam String prodId) {
		logger.info("Method : getProductByReqList starts");

		JsonResponse<InventoryRequisitionModel> res = new JsonResponse<InventoryRequisitionModel>();

		try {
			res = restTemplate.getForObject(
					env.getInventoryUrl() + "getProductByReqList?id=" + id + "&prodId=" + prodId, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (res.getMessage() != null) {

			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}

		logger.info("Method : getProductByReqList ends");
		return res;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/view-requisition-product-category-get-total-list")
	public @ResponseBody JsonResponse<List<ProductCategoryModel>> getAllProductCategoryList(HttpSession session) {
		logger.info("Method : getAllProductCategoryList starts");

		JsonResponse<List<ProductCategoryModel>> resp = new JsonResponse<List<ProductCategoryModel>>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "getAllProductCategoryList", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : getAllProductCategoryList starts");
		return resp;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/view-requisition-product-category-get-category-list-by-id")
	public @ResponseBody JsonResponse<List<ProductCategoryModel>> getProductCategoryListById(@RequestBody String id,
			HttpSession session) {
		logger.info("Method : getProductCategoryListById starts");

		JsonResponse<List<ProductCategoryModel>> resp = new JsonResponse<List<ProductCategoryModel>>();

		try {
			resp = restTemplate.getForObject(env.getMasterUrl() + "getProductCategoryListById?id=" + id,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : getProductCategoryListById starts");
		return resp;
	}
	/*
	 * drop down for supervisor by job title
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "/view-requisition-get-product-by-cat" })
	public @ResponseBody JsonResponse<InventorySkuProductModel> getProduct(Model model, @RequestBody String index,
			BindingResult result) {
		logger.info("Method : getProduct starts");

		JsonResponse<InventorySkuProductModel> res = new JsonResponse<InventorySkuProductModel>();

		try {
			res = restTemplate.getForObject(env.getInventoryUrl() + "getProductByCat?id=" + index,
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

		logger.info("Method : getProduct  ends");
		return res;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/view-requisition-get-product-cat-list")
	public @ResponseBody JsonResponse<List<ProductCategoryModel>> getProductCategoryDataListModal(@RequestBody String yearDtls, HttpSession session) {
		logger.info("Method : getProductCategoryDataListModal starts");
		
		JsonResponse<List<ProductCategoryModel>> resp = new JsonResponse<List<ProductCategoryModel>>();
		
		try {
			resp = restTemplate.getForObject(env.getInventoryUrl() + "getProductCategoryDataListModal",
					JsonResponse.class);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("success");
		}
		
		logger.info("Method : getProductCategoryDataListModal starts");
		return resp;
	}
	
	@SuppressWarnings({ "unchecked" })
	@PostMapping("/view-requisition-product-save")
	public @ResponseBody JsonResponse<Object> saveOneProductMaster(@RequestBody ProductMasterModel product, HttpSession session) {
		logger.info("Method : saveOneProductMaster starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		
		String userId = "";
		
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		product.setCreatedBy(userId);
		
		try { 
			resp = restTemplate.postForObject(env.getInventoryUrl() + "saveOneProductMaster", product,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		DropDownModel dd = mapper.convertValue(resp.getBody(),
				new TypeReference<DropDownModel>() {
				});
		product.setProductId(dd.getKey());
		product.setMode(dd.getName());
		
		resp.setBody(product);
		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			resp.setMessage("Success");
		}
		
		logger.info("Method : saveOneProductMaster starts");
		return resp;
	}
}
