package nirmalya.aatithya.restmodule.master.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.master.dao.RestProcurementDao; 
import nirmalya.aatithya.restmodule.master.model.RestProcurementMasterModel; 

@RestController
@RequestMapping(value = { "master" })
public class RestProcurementMasterController {

	Logger logger = LoggerFactory.getLogger(RestProcurementMasterController.class);

	@Autowired
	RestProcurementDao restProcurementDao;

	/////////// MEASUREMENT///////

	@RequestMapping(value = "addMeasureType", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> restaddMeasureType(
			@RequestBody RestProcurementMasterModel restProcurementMasterModel) {
		logger.info("Method : restaddMeasureType starts");

		logger.info("Method : restaddMeasureType ends");
		return restProcurementDao.addMeasureType(restProcurementMasterModel);
	}

	@RequestMapping(value = "viewMeasureType", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<RestProcurementMasterModel>>> viewMeasureType() {
		logger.info("Method : viewMeasureType starts");

		logger.info("Method : viewMeasureType ends");
		return restProcurementDao.viewMeasureType();
	}

	@RequestMapping(value = "deleteMeasureType", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deleteMeasureType(@RequestParam String id) {
		logger.info("Method : deleteMeasureType starts");

		logger.info("Method : deleteMeasureType ends");
		return restProcurementDao.deleteMeasureType(id);
	}

	/////////// RequisitionType///////

	@RequestMapping(value = "addReqiType", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> addReqiType(
			@RequestBody RestProcurementMasterModel restProcurementMasterModel) {
		logger.info("Method : addReqiType starts");

		logger.info("Method : addReqiType ends");
		return restProcurementDao.addReqiType(restProcurementMasterModel);
	}

	@RequestMapping(value = "viewRequisitionType", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<RestProcurementMasterModel>>> viewRequisitionType() {
		logger.info("Method : viewRequisitionType starts");

		logger.info("Method : viewRequisitionType ends");
		return restProcurementDao.viewRequisitionType();
	}

	@RequestMapping(value = "deleteRequiType", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deleteRequiType(@RequestParam String id) {
		logger.info("Method : deleteRequiType starts");

		logger.info("Method : deleteRequiType ends");
		return restProcurementDao.deleteRequiType(id);
	}

///////////Requisition Priority Type///////

	@RequestMapping(value = "addReqiPriorityType", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> addReqiPriorityType(
			@RequestBody RestProcurementMasterModel restProcurementMasterModel) {
		logger.info("Method : addReqiPriorityType starts");

		logger.info("Method : addReqiPriorityType ends");
		return restProcurementDao.addReqiPriorityType(restProcurementMasterModel);
	}

	@RequestMapping(value = "viewReqiPriorityType", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<RestProcurementMasterModel>>> viewReqiPriorityType() {
		logger.info("Method : viewReqiPriorityType starts");

		logger.info("Method : viewReqiPriorityType ends");
		return restProcurementDao.viewReqiPriorityType();
	}

	@RequestMapping(value = "deleteRequiPriorityType", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deleteRequiPriorityType(@RequestParam String id) {
		logger.info("Method : deleteRequiPriorityType starts");

		logger.info("Method : deleteRequiPriorityType ends");
		return restProcurementDao.deleteRequiPriorityType(id);
	}

///////////Payment Term Type///////

	@RequestMapping(value = "addPayTerm", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> addPayTerm(
			@RequestBody RestProcurementMasterModel restProcurementMasterModel) {
		logger.info("Method : addPayTerm starts");

		logger.info("Method : addPayTerm ends");
		return restProcurementDao.addPayTerm(restProcurementMasterModel);
	}

	@RequestMapping(value = "viewPaymentTerm", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<RestProcurementMasterModel>>> viewPaymentTerm() {
		logger.info("Method : viewPaymentTerm starts");

		logger.info("Method : viewPaymentTerm ends");
		return restProcurementDao.viewPaymentTerm();
	}

	@RequestMapping(value = "deletePaymentTerm", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deletePaymentTerm(@RequestParam String id) {
		logger.info("Method : deletePaymentTerm starts");

		logger.info("Method : deletePaymentTerm ends");
		return restProcurementDao.deletePaymentTerm(id);
	}

///////////Legal Term Type///////

	@RequestMapping(value = "addLegalTerm", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> addLegalTerm(
			@RequestBody RestProcurementMasterModel restProcurementMasterModel) {
		logger.info("Method : addLegalTerm starts");

		logger.info("Method : addLegalTerm ends");
		return restProcurementDao.addLegalTerm(restProcurementMasterModel);
	}

	@RequestMapping(value = "viewLegalTerm", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<RestProcurementMasterModel>>> viewLegalTerm() {
		logger.info("Method : viewLegalTerm starts");

		logger.info("Method : viewLegalTerm ends");
		return restProcurementDao.viewLegalTerm();
	}

	@RequestMapping(value = "deleteLegalTerm", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deleteLegalTerm(@RequestParam String id) {
		logger.info("Method : deleteLegalTerm starts");

		logger.info("Method : deleteLegalTerm ends");
		return restProcurementDao.deleteLegalTerm(id);
	}

///////////Payment Status Type///////

	@RequestMapping(value = "addPaymentStatus", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> addPaymentStatus(
			@RequestBody RestProcurementMasterModel restProcurementMasterModel) {
		logger.info("Method : addPaymentStatus starts");

		logger.info("Method : addPaymentStatus ends");
		return restProcurementDao.addPaymentStatus(restProcurementMasterModel);
	}

	@RequestMapping(value = "viewPaymentStatus", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<RestProcurementMasterModel>>> viewPaymentStatus() {
		logger.info("Method : viewPaymentStatus starts");

		logger.info("Method : viewPaymentStatus ends");
		return restProcurementDao.viewPaymentStatus();
	}

	@RequestMapping(value = "deletePaymentStatus", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deletePaymentStatus(@RequestParam String id) {
		logger.info("Method : deletePaymentStatus starts");

		logger.info("Method : deletePaymentStatus ends");
		return restProcurementDao.deletePaymentStatus(id);
	}

///////////Delivery Method Type///////

	@RequestMapping(value = "addDeliveryMethod", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<Object>> addDeliveryMethod(
			@RequestBody RestProcurementMasterModel restProcurementMasterModel) {
		logger.info("Method : addDeliveryMethod starts");

		logger.info("Method : addDeliveryMethod ends");
		return restProcurementDao.addDeliveryMethod(restProcurementMasterModel);
	}

	@RequestMapping(value = "viewDeliveryMethod", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<List<RestProcurementMasterModel>>> viewDeliveryMethod() {
		logger.info("Method : viewDeliveryMethod starts");

		logger.info("Method : viewDeliveryMethod ends");
		return restProcurementDao.viewDeliveryMethod();
	}

	@RequestMapping(value = "deleteDeliveryMethod", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<Object>> deleteDeliveryMethod(@RequestParam String id) {
		logger.info("Method : deleteDeliveryMethod starts");

		logger.info("Method : deleteDeliveryMethod ends");
		return restProcurementDao.deleteDeliveryMethod(id);
	}
	/**
	 * add requisition type data from csv
	 * @param reqtypeList
	 * @return
	 */
	@PostMapping(value = "addReqiTypeFromCsv")
	public ResponseEntity<JsonResponse<Object>> addReqiTypeFromCsv(
			@RequestBody List<RestProcurementMasterModel> reqtypeList) {
		logger.info("Method : addReqiTypeFromCsv starts");  
		logger.info("Method : addReqiTypeFromCsv ends");
		return restProcurementDao.addReqiTypeFromCsv(reqtypeList);
	}
	/**
	 * add requisition priority from csv
	 * @param reqtypeList
	 * @return
	 */
	@PostMapping(value = "addReqiPrioTypeFromCsv")
	public ResponseEntity<JsonResponse<Object>> addReqiPrioTypeFromCsv(
			@RequestBody List<RestProcurementMasterModel> reqtypeList) {
		logger.info("Method : addReqiPrioTypeFromCsv starts");  
		logger.info("Method : addReqiPrioTypeFromCsv ends");
		return restProcurementDao.addReqiPrioTypeFromCsv(reqtypeList);
	}
	/**
	 * add requisition priority from csv
	 * @param reqtypeList
	 * @return
	 */
	@PostMapping(value = "addPaymentTermFromCsv")
	public ResponseEntity<JsonResponse<Object>> addPaymentTermFromCsv(
			@RequestBody List<RestProcurementMasterModel> reqtypeList) {
		logger.info("Method : addPaymentTermFromCsv starts");  
		logger.info("Method : addPaymentTermFromCsv ends");
		return restProcurementDao.addPaymentTermFromCsv(reqtypeList);
	}
	/**
	 * add requisition priority from csv
	 * @param reqtypeList
	 * @return
	 */
	@PostMapping(value = "addLegalTermFromCsv")
	public ResponseEntity<JsonResponse<Object>> addLegalTermFromCsv(
			@RequestBody List<RestProcurementMasterModel> reqtypeList) {
		logger.info("Method : addLegalTermFromCsv starts");  
		logger.info("Method : addLegalTermFromCsv ends");
		return restProcurementDao.addLegalTermFromCsv(reqtypeList);
	}
	/**
	 * add requisition priority from csv
	 * @param reqtypeList
	 * @return
	 */
	@PostMapping(value = "addMeasureTypeFromCsv")
	public ResponseEntity<JsonResponse<Object>> addMeasureTypeFromCsv(
			@RequestBody List<RestProcurementMasterModel> reqtypeList) {
		logger.info("Method : addMeasureTypeFromCsv starts");  
		logger.info("Method : addMeasureTypeFromCsv ends");
		return restProcurementDao.addMeasureTypeFromCsv(reqtypeList);
	}
	
	/**
	 * add requisition priority from csv
	 * @param reqtypeList
	 * @return
	 */
	@PostMapping(value = "addPayStatusFromCsv")
	public ResponseEntity<JsonResponse<Object>> addPayStatusFromCsv(
			@RequestBody List<RestProcurementMasterModel> reqtypeList) {
		logger.info("Method : addPayStatusFromCsv starts");  
		logger.info("Method : addPayStatusFromCsv ends");
		return restProcurementDao.addPayStatusFromCsv(reqtypeList);
	}
	/**
	 * add requisition priority from csv
	 * @param reqtypeList
	 * @return
	 */
	@PostMapping(value = "addDlvMethodFromCsv")
	public ResponseEntity<JsonResponse<Object>> addDlvMethodFromCsv(
			@RequestBody List<RestProcurementMasterModel> reqtypeList) {
		logger.info("Method : addDlvMethodFromCsv starts");  
		logger.info("Method : addDlvMethodFromCsv ends");
		return restProcurementDao.addDlvMethodFromCsv(reqtypeList);
	}
}
