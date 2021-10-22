package nirmalya.aatithya.restmodule.hospitality.controller;

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
import nirmalya.aatithya.restmodule.hospitality.dao.RestLaundryMasterDao;
import nirmalya.aatithya.restmodule.hospitality.model.RestHotelServiceModel;
import nirmalya.aatithya.restmodule.hospitality.model.RestLaundryItemCategoryModel;
import nirmalya.aatithya.restmodule.hospitality.model.RestLaundryItemModel;
import nirmalya.aatithya.restmodule.hospitality.model.RestLaundryItemPriceModel;
import nirmalya.aatithya.restmodule.hospitality.model.RestLaundryReturnInModel;
import nirmalya.aatithya.restmodule.hospitality.model.RestLaundryServiceModel;

@RestController
@RequestMapping(value = "hospitality/")
public class RestLaundryMasterController {

	Logger logger = LoggerFactory.getLogger(RestLaundryMasterController.class);

	@Autowired
	RestLaundryMasterDao lmDao;

	// add

	@PostMapping(value = "add-item-categories")
	public ResponseEntity<JsonResponse<Object>> addLItemCategory(@RequestBody RestLaundryItemCategoryModel task) {
		logger.info("Method : addLItemCategory starts");

		logger.info("Method : addLItemCategory ends");
		return lmDao.addLItemCategory(task);
	}
	// view

	@GetMapping(value = "view-item-categories")
	public JsonResponse<List<RestLaundryItemCategoryModel>> viewLItemCategory() {
		logger.info("Method : viewLItemCategory starts");

		logger.info("Method : viewLItemCategory ends");
		return lmDao.viewLItemCategory();
	}

	// delete

	@GetMapping(value = "delete-item-categories")
	public JsonResponse<RestLaundryItemCategoryModel> deleteLItemCategory(@RequestParam String id) {
		logger.info("Method : deleteLItemCategory starts");

		logger.info("Method : deleteLItemCategory ends");
		return lmDao.deleteLItemCategory(id);
	}

	// add

	@PostMapping(value = "add-hotel-service")
	public ResponseEntity<JsonResponse<Object>> addHotelService(@RequestBody RestHotelServiceModel task) {
		logger.info("Method : addHotelService starts");

		logger.info("Method : addHotelService ends");
		return lmDao.addHotelService(task);
	}
	// view

	@GetMapping(value = "view-hotel-service")
	public JsonResponse<List<RestHotelServiceModel>> viewHotelService() {
		logger.info("Method : viewHotelService starts");

		logger.info("Method : viewHotelService ends");
		return lmDao.viewHotelService();
	}

	// delete

	@GetMapping(value = "delete-hotel-service")
	public JsonResponse<RestHotelServiceModel> deleteHotelService(@RequestParam String id) {
		logger.info("Method : deleteHotelService starts");

		logger.info("Method : deleteHotelService ends");
		return lmDao.deleteHotelService(id);
	}

	// add

	@PostMapping(value = "add-laundry-service")
	public ResponseEntity<JsonResponse<Object>> addLaundryservice(@RequestBody RestLaundryServiceModel task) {
		logger.info("Method : addLaundryservice starts");

		logger.info("Method : addLaundryservice ends");
		return lmDao.addLaundryservice(task);
	}
	// view

	@GetMapping(value = "view-laundry-service")
	public JsonResponse<List<RestLaundryServiceModel>> viewLaundryservice() {
		logger.info("Method : viewLaundryservice starts");

		logger.info("Method : viewLaundryservice ends");
		return lmDao.viewLaundryservice();
	}

	// delete

	@GetMapping(value = "delete-laundry-service")
	public JsonResponse<RestLaundryServiceModel> deleteLaundryservice(@RequestParam String id) {
		logger.info("Method : deleteLaundryservice starts");

		logger.info("Method : deleteLaundryservice ends");
		return lmDao.deleteLaundryservice(id);
	}

	// add

	@PostMapping(value = "add-laundry-item")
	public ResponseEntity<JsonResponse<Object>> addLitem(@RequestBody RestLaundryItemModel task) {
		logger.info("Method : addLitem starts");

		logger.info("Method : addLitem ends");
		return lmDao.addLitem(task);
	}
	// view

	@GetMapping(value = "view-laundry-item")
	public JsonResponse<List<RestLaundryItemModel>> viewLitem() {
		logger.info("Method : viewLitem starts");

		logger.info("Method : viewLitem ends");
		return lmDao.viewLitem();
	}

	// delete

	@GetMapping(value = "delete-laundry-item")
	public JsonResponse<RestLaundryItemModel> deleteLitem(@RequestParam String id) {
		logger.info("Method : deleteLitem starts");

		logger.info("Method : deleteLitem ends");
		return lmDao.deleteLitem(id);
	}
	// add

	@PostMapping(value = "add-return-in")
	public ResponseEntity<JsonResponse<Object>> addReturnIn(@RequestBody RestLaundryReturnInModel task) {
		logger.info("Method : addReturnIn starts");

		logger.info("Method : addReturnIn ends");
		return lmDao.addReturnIn(task);
	}
	// view

	@GetMapping(value = "view-return-in")
	public JsonResponse<List<RestLaundryReturnInModel>> viewReturnIn() {
		logger.info("Method : viewReturnIn starts");

		logger.info("Method : viewReturnIn ends");
		return lmDao.viewReturnIn();
	}

	// delete

	@GetMapping(value = "delete-return-in")
	public JsonResponse<RestLaundryReturnInModel> deleteReturnIn(@RequestParam String id) {
		logger.info("Method : deleteReturnIn starts");

		logger.info("Method : deleteReturnIn ends");
		return lmDao.deleteReturnIn(id);
	}

	/*
	 * ItemCategory list
	 *
	 */
	@RequestMapping(value = "getLaundryItemCatgry", method = { RequestMethod.GET })
	public List<DropDownModel> getLaundryItemCategory() {
		logger.info("Method : getLaundryItemCategory starts");
		logger.info("Method : getLaundryItemCategory endss");
		return lmDao.getLaundryItemCategory();
	}

	// Item Group Type

	@RequestMapping(value = "getLaundItemName", method = { RequestMethod.GET })
	public List<DropDownModel> getLaundItemName() {
		logger.info("Method : getLaundItemName starts");
		logger.info("Method : getLaundItemName endss");
		return lmDao.getLaundItemName();
	}

	// Purchase

	@RequestMapping(value = "getLaundryServiceName", method = { RequestMethod.GET })
	public List<DropDownModel> getLaundryServicename() {
		logger.info("Method : getLaundryServicename starts");
		logger.info("Method : getLaundryServicename endss");
		return lmDao.getLaundryServicename();
	}

	// Sales

	@RequestMapping(value = "getHotelServiceName", method = { RequestMethod.GET })
	public List<DropDownModel> getHotelServiceNamep() {
		logger.info("Method : getHotelServiceNamep starts");
		logger.info("Method : getHotelServiceNamep endss");
		return lmDao.getHotelServiceNamep();
	}

	// User Type

	@RequestMapping(value = "getUserType", method = { RequestMethod.GET })
	public List<DropDownModel> getLaundryUserType() {
		logger.info("Method : getLaundryUserType starts");
		logger.info("Method : getLaundryUserType endss");
		return lmDao.getLaundryUserType();
	}

	// add

	@PostMapping(value = "add-item-price")
	public ResponseEntity<JsonResponse<Object>> addLaundryItemPrice(@RequestBody RestLaundryItemPriceModel task) {
		logger.info("Method : addLaundryItemPrice starts");

		logger.info("Method : addLaundryItemPrice ends");
		return lmDao.addLaundryItemPrice(task);
	}
	// view

	@GetMapping(value = "view-item-price")
	public JsonResponse<List<RestLaundryItemPriceModel>> viewLaundryItemPrice() {
		logger.info("Method : viewLaundryItemPrice starts");

		logger.info("Method : viewLaundryItemPrice ends");
		return lmDao.viewLaundryItemPrice();
	}

	// delete

	@GetMapping(value = "delete-item-price")
	public JsonResponse<RestLaundryItemPriceModel> deleteLaundryItemPrice(@RequestParam String id1,String id2,String id3,String id4,String id5) {
		logger.info("Method : deleteLaundryItemPrice starts");

		logger.info("Method : deleteLaundryItemPrice ends");
		return lmDao.deleteLaundryItemPrice(id1,id2,id3,id4,id5);
	}
}
