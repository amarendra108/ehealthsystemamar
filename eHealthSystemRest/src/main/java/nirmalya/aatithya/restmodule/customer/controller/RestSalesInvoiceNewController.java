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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.customer.dao.SalesInvoiceNewDao;
import nirmalya.aatithya.restmodule.customer.model.RestSaleOrderNewModel;
import nirmalya.aatithya.restmodule.customer.model.RestSalesInvoiceNewModel;

@RestController
@RequestMapping("customer/")
public class RestSalesInvoiceNewController {

	Logger logger = LoggerFactory.getLogger(RestSaleOrderNewController.class);

	@Autowired

	SalesInvoiceNewDao SalesInvoiceNewDao;
	
	/*
	 * store name drp down
	 */
	@GetMapping(value = "GetStoreNameList")
	public List<DropDownModel> GetStoreNameList() {
		logger.info("Method : GetStoreNameList starts");

		logger.info("Method : GetStoreNameList ends");
		return SalesInvoiceNewDao.GetStoreNameList();
	}

	@GetMapping(value = "getSalesOrderAutoSearchNewList")
	public ResponseEntity<JsonResponse<List<RestSalesInvoiceNewModel>>> getSalesOrderAutoSearchNewList(
			@RequestParam String id) {
		logger.info("Method : getSalesOrderAutoSearchNewList starts");
		//System.out.println("RestSalesInvoiceNewModel" + id);
		logger.info("Method :getSalesOrderAutoSearchNewList endss");
		return SalesInvoiceNewDao.getSalesOrderAutoSearchNewList(id);
	}

	/*
	 * add
	 */
	@PostMapping(value = "addsaleInvoicenew")
	public ResponseEntity<JsonResponse<List<RestSalesInvoiceNewModel>>> addsaleInvoicenew(
			@RequestBody List<RestSalesInvoiceNewModel> restSalesInvoiceNewModel) {
		logger.info("Method :addsaleInvoicenew starts");
		//System.out.println(restSalesInvoiceNewModel);
		logger.info("Method :addsaleInvoicenew endss");
		return SalesInvoiceNewDao.addsaleInvoicenew(restSalesInvoiceNewModel);
	}

	/*
	 * view
	 * 
	 */
	@GetMapping(value = "getAllsalesInvoice")
	public JsonResponse<List<RestSalesInvoiceNewModel>> getAllsalesInvoice() {
		logger.info("Method :getAllsalesInvoice starts");

		logger.info("Method :getAllsalesInvoice endss");
		return SalesInvoiceNewDao.getAllsalesInvoice();

	}

	/*
	 * edit
	 */ @GetMapping(value = "viewsalesIvoiceEdit")
	public List<RestSalesInvoiceNewModel> viewsalesIvoiceEdit(@RequestParam String id) {
		logger.info("Method : viewsalesIvoiceEdit starts");
		//System.out.println(id);
		logger.info("Method : viewsalesIvoiceEdit endss");
		return SalesInvoiceNewDao.viewsalesOrdeerEdit(id);
	}
/*delete
 * 	 
 */
	 @PostMapping(value = "deletesalesInvoice")
		public ResponseEntity<JsonResponse<Object>> deletesalesInvoice(
				@RequestBody RestSalesInvoiceNewModel restSalesInvoiceNewModel) {
			logger.info("Method : deletesalesInvoice starts");
			//System.out.println(restSalesInvoiceNewModel);
			logger.info("Method : deletesalesInvoice ends");
			return SalesInvoiceNewDao.deletesalesInvoice(restSalesInvoiceNewModel);
}
	 
	 /*
	  * payment mode drpdown
	  */
	 
	 @GetMapping(value = "GetpaymentModeList")
		public List<DropDownModel> GetpaymentModeList() {
			logger.info("Method : paymentModeListrest starts");

			logger.info("Method : paymentModeListrest ends");
			return SalesInvoiceNewDao.GetpaymentModeList();
		}
	 
	 /*
		 *  payment add
		 */
		@PostMapping(value = "addinvPaymentnew")
		public ResponseEntity<JsonResponse<Object>> addinvPaymentnew(@RequestBody RestSalesInvoiceNewModel salesInvoiceNewModel) {
			logger.info("Method :addinvPaymentnewrest starts");
			System.out.println(salesInvoiceNewModel);
			logger.info("Method :addinvPaymentnewrest endss");
			return SalesInvoiceNewDao.addinvPaymentnew(salesInvoiceNewModel);
		}
}
