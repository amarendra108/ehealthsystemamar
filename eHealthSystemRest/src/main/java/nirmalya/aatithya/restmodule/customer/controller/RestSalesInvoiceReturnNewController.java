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
import nirmalya.aatithya.restmodule.customer.dao.RestSalesInvoiceReturnNewDao;
import nirmalya.aatithya.restmodule.customer.model.RestSalesInvoiceNewModel;
import nirmalya.aatithya.restmodule.customer.model.RestSalesInvoiceReturnNewModel;

@RestController
@RequestMapping("customer/")
public class RestSalesInvoiceReturnNewController {
	
	Logger logger = LoggerFactory.getLogger(RestSaleOrderNewController.class);

	@Autowired

	RestSalesInvoiceReturnNewDao RestSalesInvoiceReturnNewDao;
	
/*salesOrder Auto Search*/	
	@GetMapping(value = "salesOrderAutoSearchNewList")
	public ResponseEntity<JsonResponse<List<RestSalesInvoiceReturnNewModel>>> salesOrderAutoSearchNewList(
			@RequestParam String id) {
		logger.info("Method : salesOrderAutoSearchNewListRest starts");
		//System.out.println("salesOrderAutoSearchNewList" + id);
		logger.info("Method :salesOrderAutoSearchNewListRest endss");
		return RestSalesInvoiceReturnNewDao.salesOrderAutoSearchNewList(id);
	}
/*
 * salesInvoice AutoSearch	
 */
	@GetMapping(value = "getSalesInvoiceAutoSearchNewList")
	public ResponseEntity<JsonResponse<List<RestSalesInvoiceReturnNewModel>>> getSalesInvoiceAutoSearchNewList(
			@RequestParam String id) {
		logger.info("Method : getSalesInvoiceAutoSearchNewListRest starts");
		//System.out.println("getRestSalesInvoiceAutoSearchNewList" + id);
		logger.info("Method :getSalesInvoiceAutoSearchNewListRest endss");
		return RestSalesInvoiceReturnNewDao.getSalesInvoiceAutoSearchNewList(id);
	}

	/*
	 * add
	 */
	@PostMapping(value = "addsaleInvoicRtnenew")
	public ResponseEntity<JsonResponse<List<RestSalesInvoiceReturnNewModel>>> addsaleInvoicRtnenew(
			@RequestBody List<RestSalesInvoiceReturnNewModel> restSalesInvoiceReturnNewModel) {
		logger.info("Method :addsaleInvoicRtnenewRest starts");
		//System.out.println(restSalesInvoiceReturnNewModel);
		logger.info("Method :addsaleInvoicRtnenewRest endss");
		return RestSalesInvoiceReturnNewDao.addsaleInvoicRtnenew(restSalesInvoiceReturnNewModel);
	}

	/*
	 * view
	 * 
	 */
	@GetMapping(value = "getAllsalesInvoicertn")
	public JsonResponse<List<RestSalesInvoiceReturnNewModel>> getAllsalesInvoicertn() {
		logger.info("Method :getAllsalesInvoicertnrest starts");

		logger.info("Method :getAllsalesInvoicertnrest endss");
		return RestSalesInvoiceReturnNewDao.getAllsalesInvoicertn();

	}

	/*
	 * edit
	 */ @GetMapping(value = "viewsalesIvoicertnEdit")
	public List<RestSalesInvoiceReturnNewModel> viewsalesIvoicertnEdit(@RequestParam String id) {
		logger.info("Method : viewsalesIvoicertnEditREst starts");
		//System.out.println(id);
		logger.info("Method : viewsalesIvoicertnEditRest endss");
		return RestSalesInvoiceReturnNewDao.viewsalesIvoicertnEdit(id);
	}
	 
 /*delete
	 * 	 
  */
	@PostMapping(value = "deletesalesInvretn")
	public ResponseEntity<JsonResponse<Object>> deletesalesInvretn(
			@RequestBody RestSalesInvoiceReturnNewModel restSalesInvoiceReturnNewModel) {
		logger.info("Method : deletesalesInvretnrest starts");
		//System.out.println(restSalesInvoiceReturnNewModel);
		logger.info("Method : deletesalesInvretnrest ends");
		return RestSalesInvoiceReturnNewDao.deletesalesInvretn(restSalesInvoiceReturnNewModel);
	}
	
	/*
	 * edit
	 */ @GetMapping(value = "viewsalesIvoicertnEditrtn")
	public List<RestSalesInvoiceReturnNewModel> viewsalesIvoicertnEditrtn(@RequestParam String id) {
		logger.info("Method : viewsalesIvoicertnEditrtnREst starts");
		System.out.println(id);
		logger.info("Method : viewsalesIvoicertnEditrtnRest endss");
		return RestSalesInvoiceReturnNewDao.viewsalesIvoicertnEditrtn(id);
	}
	
}
