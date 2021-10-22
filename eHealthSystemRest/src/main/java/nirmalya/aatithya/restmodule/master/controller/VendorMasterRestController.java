package nirmalya.aatithya.restmodule.master.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.ActivitylogModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeBankDetailsRestModel;
import nirmalya.aatithya.restmodule.master.dao.VendorMasterDao;
import nirmalya.aatithya.restmodule.master.model.LocationMasterModel;
import nirmalya.aatithya.restmodule.master.model.VendorContactMasterModel;
import nirmalya.aatithya.restmodule.master.model.VendorDocumentMaster;
import nirmalya.aatithya.restmodule.master.model.VendorLocationMasterModel;
import nirmalya.aatithya.restmodule.master.model.VendorMasterModel;

/**
 * @author NirmalyaLabs
 *
 */
@RestController
@RequestMapping(value = "master")
public class VendorMasterRestController {

	Logger logger = LoggerFactory.getLogger(VendorMasterRestController.class);

	@Autowired
	VendorMasterDao vendorMasterDao;

	@RequestMapping(value = "saveVendorMaster", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<VendorMasterModel>> saveVendorMaster(
			@RequestBody VendorMasterModel vendorMasterModel) {
		logger.info("Method : saveVendorMaster starts");

		logger.info("Method : saveVendorMaster ends");
		return vendorMasterDao.saveVendorMaster(vendorMasterModel);
	}

	@RequestMapping(value = "getCategoryList", method = { RequestMethod.GET })
	public List<DropDownModel> getCategoryList() {
		logger.info("Method : getCategoryList starts");

		logger.info("Method : getCategoryList ends");
		return vendorMasterDao.getCategoryList();
	}

	@RequestMapping(value = "getDocumentTypeList", method = { RequestMethod.GET })
	public List<DropDownModel> getDocumentTypeList() {
		logger.info("Method : getDocumentTypeList starts");

		logger.info("Method : getDocumentTypeList ends");
		return vendorMasterDao.getDocumentTypeList();
	}

	@RequestMapping(value = "saveVendorLocationMaster", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<VendorLocationMasterModel>> saveVendorLocationMaster(
			@RequestBody VendorLocationMasterModel vendorLocationMasterModel) {
		logger.info("Method : saveVendorLocationMaster starts");

		logger.info("Method : saveVendorLocationMaster ends");
		return vendorMasterDao.saveVendorLocationMaster(vendorLocationMasterModel);
	}

	@RequestMapping(value = "/get-vendor-location-list", method = { RequestMethod.GET })
	ResponseEntity<JsonResponse<List<VendorLocationMasterModel>>> getVendorLocationview(@RequestParam("id") String id) {
		logger.info("Method : getVendorLocationview start");

		logger.info("Method : getVendorLocationview ends");

		return vendorMasterDao.getVendorLocationview(id);
	}

	@RequestMapping(value = "/get-vendor-list", method = { RequestMethod.GET })
	ResponseEntity<JsonResponse<List<VendorMasterModel>>> getVendorList() {
		logger.info("Method : getVendorList start");

		logger.info("Method : getVendorList ends");

		return vendorMasterDao.getVendorList();
	}

	@RequestMapping(value = "editVendorLoactionById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<VendorLocationMasterModel>> editVendorLoactionById(@RequestParam String id) {
		logger.info("Method : editVendorLoactionById starts");

		logger.info("Method : editVendorLoactionById ends");
		return vendorMasterDao.editVendorLoactionById(id);
	}

	@RequestMapping(value = "deleteVendorLocation", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deleteVendorLocation(@RequestParam String id,
			@RequestParam String createdBy, @RequestParam String simpleid, @RequestParam String vendorId) {
		logger.info("Method : deleteVendorLocation starts");

		logger.info("Method : deleteVendorLocation ends");
		return vendorMasterDao.deleteVendorLocation(id, createdBy, simpleid, vendorId);
	}

	@RequestMapping(value = "editVendorById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<VendorMasterModel>> editVendorById(@RequestParam String id) {
		logger.info("Method : editVendorById starts");

		logger.info("Method : editVendorById ends");
		return vendorMasterDao.editVendorById(id);
	}

	@RequestMapping(value = "saveVendorContactMaster", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<VendorContactMasterModel>> saveVendorContactMaster(
			@RequestBody VendorContactMasterModel vendorContactMasterModel) {
		logger.info("Method : saveVendorContactMaster starts");

		logger.info("Method : saveVendorContactMaster ends");
		return vendorMasterDao.saveVendorContactMaster(vendorContactMasterModel);
	}

	@RequestMapping(value = "/get-vendor-contact-list", method = { RequestMethod.GET })
	ResponseEntity<JsonResponse<List<VendorContactMasterModel>>> getVendorContact(@RequestParam("id") String id) {
		logger.info("Method : getVendorContact start");

		logger.info("Method : getVendorContact ends");

		return vendorMasterDao.getVendorContact(id);
	}

	@RequestMapping(value = "editVendorContactById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<VendorContactMasterModel>> editVendorContactById(@RequestParam String id) {
		logger.info("Method : editVendorContactById starts");

		logger.info("Method : editVendorContactById ends");
		return vendorMasterDao.editVendorContactById(id);
	}

	/*
	 * for All Initiated Audits
	 */
	@RequestMapping(value = "saveVendorDocumentMaster", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> saveVendorDocumentMaster(@RequestBody List<VendorDocumentMaster> obj) {
		logger.info("Method : saveVendorDocumentMaster starts");

		logger.info("Method : saveVendorDocumentMaster ends");

		return vendorMasterDao.saveVendorDocumentMaster(obj);
	}

	@RequestMapping(value = "saveemployeebankdetailsVendor", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<ManageEmployeeBankDetailsRestModel>> saveemployeebankdetailsVendor(
			@RequestBody ManageEmployeeBankDetailsRestModel manageEmployeeBankDetailsRestModel) {
		logger.info("Method : saveemployeebankdetailsVendor starts");

		logger.info("Method : saveemployeebankdetailsVendor ends");
		return vendorMasterDao.saveemployeebankdetailsVendor(manageEmployeeBankDetailsRestModel);
	}

	@RequestMapping(value = "/viewVendorBankDetails", method = { RequestMethod.GET })
	ResponseEntity<JsonResponse<List<ManageEmployeeBankDetailsRestModel>>> viewVendorBankDetails(
			@RequestParam("id") String id) {
		logger.info("Method : viewVendorBankDetails start");

		logger.info("Method : viewVendorBankDetails ends");

		return vendorMasterDao.viewVendorBankDetails(id);
	}

	@RequestMapping(value = "editVendorBankById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<ManageEmployeeBankDetailsRestModel>> editVendorBankById(
			@RequestParam String id) {
		logger.info("Method : editVendorBankById starts");

		logger.info("Method : editVendorBankById ends");
		return vendorMasterDao.editVendorBankById(id);
	}

	@GetMapping(value = "get-activity-log-vendor")
	public List<ActivitylogModel> getActivityLog(@RequestParam String id) {
		logger.info("Method : getActivityLog starts");
		logger.info("Method : getActivityLog endss");
		return vendorMasterDao.getActivityLogVendor(id);
	}

	@RequestMapping(value = "deleteVendor", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deleteVendor(@RequestParam String id, @RequestParam String createdBy,
			@RequestParam String simpleid) {
		logger.info("Method : deleteVendor starts");

		logger.info("Method : deleteVendor ends");
		return vendorMasterDao.deleteVendor(id, createdBy, simpleid);
	}

	/* Sagar Anchal - 21-01-2021 */
	@RequestMapping(value = "createVendorWiseUser", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> createVendorWiseUser(@RequestBody VendorContactMasterModel id) {
		logger.info("Method : createVendorWiseUser starts");

		logger.info("Method : createVendorWiseUser ends");
		return vendorMasterDao.createVendorWiseUser(id);
	}

	@RequestMapping(value = "resetPasswordVendor", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> resetPasswordVendor(@RequestBody VendorContactMasterModel id) {
		logger.info("Method : resetPasswordVendor starts");

		logger.info("Method : resetPasswordVendor ends");
		return vendorMasterDao.resetPasswordVendor(id);
	}

	@GetMapping(value = "get-vendor-list-by-vendor")
	ResponseEntity<JsonResponse<List<VendorMasterModel>>> getVendorListByVendor(@RequestParam String vendorId) {
		logger.info("Method : getVendorListByVendor start");

		logger.info("Method : getVendorListByVendor ends");
		return vendorMasterDao.getVendorListByVendor(vendorId);
	}
}
