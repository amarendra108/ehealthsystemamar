package nirmalya.aatithya.restmodule.customer.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.customer.dao.RestSaleOrderNewDao;
import nirmalya.aatithya.restmodule.customer.model.RestQuotationNewModel;
import nirmalya.aatithya.restmodule.customer.model.RestSaleOrderNewModel;
import nirmalya.aatithya.restmodule.master.model.ProductCategoryModel;
import nirmalya.aatithya.restmodule.procurment.model.InventorySkuProductModel;

@RestController
@RequestMapping("customer/")
public class RestSaleOrderNewController {
Logger logger = LoggerFactory.getLogger(RestSaleOrderNewController.class);
	
	@Autowired
	
	RestSaleOrderNewDao RestSaleOrderNewDao;
	/*
	 * store name drp down
	 */
	@GetMapping(value = "GetStoreList")
	public List<DropDownModel> GetStoreList() {
		logger.info("Method : GetStoreList starts");

		logger.info("Method : GetStoreList ends");
		return RestSaleOrderNewDao.GetStoreList();
	}
	/*
	 * customer auto search
	 */
	@GetMapping(value = "getCustomerAutoSearchNewList")
	public ResponseEntity<JsonResponse<List<RestSaleOrderNewModel>>> getCustomerAutoSearchNewList(
			@RequestParam String id) {
		logger.info("Method : getCustomerAutoSearchNewList starts");

		logger.info("Method :getCustomerAutoSearchNewList endss");
		return RestSaleOrderNewDao.getCustomerAutoSearchNewList(id);
	}
/*
 * item autosearch
 * 
 */
	@GetMapping(value = "getItemQuotationAutoSearchNewList")
	public ResponseEntity<JsonResponse<List<RestSaleOrderNewModel>>> getItemQuotationAutoSearchNewList(
			@RequestParam String id) {
		logger.info("Method : getItemQuotationAutoSearchNewList starts");

		logger.info("Method :getItemQuotationAutoSearchNewList endss");
		return RestSaleOrderNewDao.getItemQuotationAutoSearchNewList(id);
	}
	
	//get product category list
	
	@RequestMapping(value = "getProductCategoryDataListModal", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<ProductCategoryModel>>> getProductCategoryDataListModal() {
		logger.info("Method : getProductCategoryDataListModal starts");
		
		logger.info("Method : getProductCategoryDataListModal ends");
		return RestSaleOrderNewDao.getProductCategoryDataListModal();
	}
		/* get product by cat
		 * 	
		 */
			@GetMapping(value = "getProductsNByCat")
			public ResponseEntity<JsonResponse<List<InventorySkuProductModel>>> getProductsNByCat(@RequestParam String id) {
				logger.info("Method in rest: getProductsByCat starts");
				//System.out.println(id);
				logger.info("Method in rest: getProductsByCat ends");
				return RestSaleOrderNewDao.getProductsNByCat(id);
			}
	/*
	 * add
	 */
			@PostMapping(value = "addsalenew")
			public ResponseEntity<JsonResponse<List<RestSaleOrderNewModel>>> addsalenew(@RequestBody List<RestSaleOrderNewModel> restSaleOrderNewModel) {
				logger.info("Method :addsalenew starts");
				System.out.println(restSaleOrderNewModel);
				logger.info("Method :addsalenew endss");
				return RestSaleOrderNewDao.addsalenew(restSaleOrderNewModel);
			}
/*view
 * 
 */
			@GetMapping(value = "getAllsalesOrder")
			public JsonResponse<List<RestSaleOrderNewModel>> getAllsalesOrder() {
				logger.info("Method :getAllsalesOrder starts");
				
				
				logger.info("Method :getAllsalesOrder endss");
				return RestSaleOrderNewDao.getAllsalesOrder();
				
			}
			
/*edit
 *  */				
			@GetMapping(value = "viewsalesOrdeerEdit")
			public List<RestSaleOrderNewModel> viewsalesOrdeerEdit(@RequestParam String id) {
				logger.info("Method : viewsalesOrdeerEdit starts");
				//System.out.println(id);
				logger.info("Method : viewsalesOrdeerEdit endss");
				return RestSaleOrderNewDao.viewsalesOrdeerEdit(id);
			}
/*
 * delete
 * 			
 */
			@PostMapping(value = "deletesalesOrder")
			public ResponseEntity<JsonResponse<Object>> deletesalesOrder(
					@RequestBody RestSaleOrderNewModel restSaleOrderNewModel) {
				logger.info("Method : deletesalesOrder starts");
				System.out.println(restSaleOrderNewModel);
				logger.info("Method : deletesalesOrder ends");
				return RestSaleOrderNewDao.deletesalesOrder(restSaleOrderNewModel);
}
}
