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


import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.customer.dao.RestQuotationDao;
import nirmalya.aatithya.restmodule.customer.model.RestCustoomerNewModel;
import nirmalya.aatithya.restmodule.customer.model.RestQuotationNewModel;
import nirmalya.aatithya.restmodule.master.model.ProductCategoryModel;
import nirmalya.aatithya.restmodule.procurment.model.InventoryRequisitionModel;
import nirmalya.aatithya.restmodule.procurment.model.InventoryRfqModel;
import nirmalya.aatithya.restmodule.procurment.model.InventorySkuProductModel;


@RestController
@RequestMapping("customer/")
public class RestQuotationNewController {
Logger logger = LoggerFactory.getLogger(RestQuotationNewController.class);
	
	@Autowired
	
	RestQuotationDao RestQuotationDao;
	
	@GetMapping(value = "getAllquotation")
	public JsonResponse<List<RestQuotationNewModel>> getAllquotation() {
		logger.info("Method :Allquotation starts");
		
		
		logger.info("Method :Allquotation endss");
		return RestQuotationDao.getAllquotation();
		
	}
	
	@GetMapping(value = "getAllquotationItem")
	public JsonResponse<List<RestQuotationNewModel>> getAllquotationItem() {
		logger.info("Method :getAllquotationItem starts");
		
		
		logger.info("Method :getAllquotationItem endss");
		return RestQuotationDao.getAllquotationItem();
		
	}
		
		@GetMapping(value = "getCustomerListByAutoSearch")
		public ResponseEntity<JsonResponse<List<RestQuotationNewModel>>> getCustomerListByAutoSearch(
				@RequestParam String id) {
			logger.info("Method : getCustomerListByAutoSearch starts");

			logger.info("Method :getCustomerListByAutoSearch endss");
			return RestQuotationDao.getCustomerListByAutoSearch(id);
		}
		
		@GetMapping(value = "getItemQuotationAutoSearchList")
		public ResponseEntity<JsonResponse<List<RestQuotationNewModel>>> getItemQuotationAutoSearchList(
				@RequestParam String id) {
			logger.info("Method : getItemQuotationAutoSearchList starts");

			logger.info("Method :getItemQuotationAutoSearchList endss");
			return RestQuotationDao.getItemQuotationAutoSearchList(id);
		}
		
		
		@PostMapping(value = "addquotationnew")
		public ResponseEntity<JsonResponse<List<RestQuotationNewModel>>> addquotationnew(@RequestBody List<RestQuotationNewModel> restQuotationNewModel) {
			logger.info("Method :addquotationnew starts");
			System.out.println(restQuotationNewModel);
			logger.info("Method :addquotationnew endss");
			return RestQuotationDao.addquotationnew(restQuotationNewModel);
		}

		
		//get product category list
		@PostMapping(value = "getProductCategoryDataListModal")
		public ResponseEntity<JsonResponse<List<ProductCategoryModel>>> getProductCategoryDataListModal() {
		logger.info("Method : getProductCategoryDataListModal starts");

		logger.info("Method : getProductCategoryDataListModal ends");
		return RestQuotationDao.getProductCategoryDataListModal();
		}

		
		@GetMapping(value = "getProductsByCat")
		public ResponseEntity<JsonResponse<List<InventorySkuProductModel>>> getProductsByCat(@RequestParam String id) {
			logger.info("Method in rest: getProductsByCat starts");
			//System.out.println(id);
			logger.info("Method in rest: getProductsByCat ends");
			return RestQuotationDao.getProductsByCat(id);
		}
		@GetMapping(value = "viewQuotationEdit")
		public List<RestQuotationNewModel> viewQuotationEdit(@RequestParam String id) {
			logger.info("Method : viewQuotationEdit starts");
			//System.out.println(id);
			logger.info("Method : viewQuotationEdit endss");
			return RestQuotationDao.viewQuotationEdit(id);
		}
		
		
		@PostMapping(value = "deleteItemQuotation")
		public ResponseEntity<JsonResponse<Object>> deleteItemQuotation(

				@RequestBody RestQuotationNewModel restQuotationNewModel) {
			logger.info("Method : deleteItemQuotation starts");
			System.out.println(restQuotationNewModel);
			logger.info("Method : deleteItemQuotation ends");
			return RestQuotationDao.deleteItemQuotation(restQuotationNewModel);
		}

		
		@PostMapping(value = "addpoNo")
		public ResponseEntity<JsonResponse<Object>> addpoNo(@RequestBody RestQuotationNewModel quotationNewModel) {
			logger.info("Method :addpoNo starts");
			System.out.println(quotationNewModel);
			logger.info("Method :addpoNo endss");
			return RestQuotationDao.addpoNo(quotationNewModel);
		}
}
