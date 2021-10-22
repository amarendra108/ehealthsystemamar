package nirmalya.aatithya.restmodule.procurment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.procurment.dao.InventoryVendorPoDao;
import nirmalya.aatithya.restmodule.procurment.model.InventoryPoModel;

/**
 * @author NirmalyaLabs
 *
 */
@RestController
@RequestMapping(value = { "inventory/" })
public class InventoryVendorPoController {
	Logger logger = LoggerFactory.getLogger(InventoryPoController.class);

	@Autowired
	InventoryVendorPoDao inventoryVendorPoDao;
	
	/**
	 * get all po for view
	 */
	@GetMapping(value = "get-vendor-po-view-list")
	public List<InventoryPoModel> getVendorPoViewList(@RequestParam String userId) {
		logger.info("Method : getVendorPoViewList starts");
		logger.info("Method : getVendorPoViewList endss");
		return inventoryVendorPoDao.getVendorPoViewList(userId);
	}
}
