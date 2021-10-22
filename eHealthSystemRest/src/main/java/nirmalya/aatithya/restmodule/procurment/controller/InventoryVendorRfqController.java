package nirmalya.aatithya.restmodule.procurment.controller;

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

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.procurment.dao.InventoryVendorRfqDao;
import nirmalya.aatithya.restmodule.procurment.model.InventoryActionRfqModel;
import nirmalya.aatithya.restmodule.procurment.model.InventoryVendorRfqModel;

/**
 * @author NirmalyaLabs
 *
 */
@RestController
@RequestMapping(value = { "inventory/" })
public class InventoryVendorRfqController {
	Logger logger = LoggerFactory.getLogger(InventoryVendorRfqController.class);

	@Autowired
	InventoryVendorRfqDao inventoryVendorRfqDao;

	/*
	 * get all requisition for view
	 */
	@GetMapping(value = "get-vendor-rfq-view-list")
	public List<InventoryActionRfqModel> getVendorRfqViewList(@RequestParam String userId) {
		logger.info("Method : getVendorRfqViewList starts");
		logger.info("Method : getVendorRfqViewList endss");
		return inventoryVendorRfqDao.getVendorRfqViewList(userId);
	}
	/*
	 * 
	 * PostMapping for add rest ItemRequisition
	 * 
	 * 
	 */
	 
	@PostMapping(value = "rest-add-vendor-rqf")
	public ResponseEntity<JsonResponse<List<InventoryVendorRfqModel>>> restAddRfq(
			@RequestBody List<InventoryVendorRfqModel> restItemRequisitonModel) {
		logger.info("Method : restAddRfq starts");
		logger.info("Method : restAddRfq ends");
		return inventoryVendorRfqDao.restAddRfq(restItemRequisitonModel);
	}
	
	/*
	 * 
	 * Get mapping for edit Rfq
	 * 
	 * 
	 */
	@GetMapping(value = "get-vendor-rfq-edit")
	public List<InventoryVendorRfqModel> getRfqEdit(@RequestParam String id ,@RequestParam String userId) {
		logger.info("Method : getRfqEdit starts");
		logger.info("Method : getRfqEdit endss");
		return inventoryVendorRfqDao.getVendorRfqEdit(id ,userId);
	}
}
