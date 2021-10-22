package nirmalya.aatithya.restmodule.employee.controller;

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

import nirmalya.aatithya.restmodule.common.utils.ActivitylogModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.employee.dao.ReimbursementRestDao;
import nirmalya.aatithya.restmodule.employee.model.ReimbrusementPaymentModel;
import nirmalya.aatithya.restmodule.employee.model.ReimbursementModel;


@RestController
@RequestMapping(value = { "employee/" })
public class ReimbursementRestController {
	Logger logger = LoggerFactory.getLogger(ReimbursementRestController.class);

	@Autowired
	ReimbursementRestDao reimbursementRestDao;
	
	
	@GetMapping(value = "get-reimbursement-type")
	public List<DropDownModel> getReimbursementType() {
		logger.info("Method : getReimbursementType starts");
		logger.info("Method : getReimbursementType endss");
		return reimbursementRestDao.getReimbursementTypeList();
	}

	/*
	 * 
	 * Get mapping for schedule
	 */
	@GetMapping(value = "get-policy-type")
	public List<DropDownModel> getPolicyType() {
		logger.info("Method : getPolicyType starts");
		logger.info("Method : getPolicyType endss");
		return reimbursementRestDao.getPolicyTypeList();
	}
	
	//Gender dropdown
		@RequestMapping(value = "getRequisitionList", method = { RequestMethod.GET })
		public List<DropDownModel> getRequisitionList() {
			logger.info("Method : getRequisitionList starts");

			logger.info("Method : getRequisitionList ends");
			return reimbursementRestDao.getRequisitionList();
		}
	/*
	 * View Reimbursement
	 * 
	 */
	@RequestMapping(value = "reimbursement-view", method = { RequestMethod.GET })
	ResponseEntity<JsonResponse<List<ReimbursementModel>>> viewReimbursement() {
		logger.info("Method : viewReimbursement start");

		logger.info("Method : viewReimbursement ends");
		return reimbursementRestDao.viewReimbursement();
	}

	/*
	 * Add Reimbursement rest
	 * 
	 */

	/*
	 * @PostMapping(value = "add-reimbursement") public JsonResponse<Object>
	 * addReimbursement(@RequestBody ReimbursementModel reimbursementModel) {
	 * logger.info("Method : addReimbursement rest starts");
	 * 
	 * logger.info("Method : addReimbursement rest ends"); return
	 * reimbursementRestDao.addReimbursementRestDao(reimbursementModel); }
	 */
	
	
	/*
	 * @RequestMapping(value = "add-reimbursement", method = { RequestMethod.POST })
	 * public ResponseEntity<JsonResponse<ReimbursementModel>> addReimbursement(
	 * 
	 * @RequestBody ReimbursementModel vendorMasterModel) {
	 * logger.info("Method : addReimbursement starts");
	 * 
	 * logger.info("Method : addReimbursement ends"); return
	 * reimbursementRestDao.addReimbursementRestDao(vendorMasterModel); }
	 */
	
	@PostMapping(value="add-reimbursement")
	public ResponseEntity<JsonResponse<Object>> addReimbursement(@RequestBody ReimbursementModel employee){
		logger.info("Method : addReimbursement starts");
		
		logger.info("Method : addReimbursement ends");
		return reimbursementRestDao.addReimbursementRestDao(employee);
	}
	/*
	 *
	 * Edit Reimbursement rest
	 *
	 */
	@RequestMapping(value = "edit-rest-reimbursement", method = { RequestMethod.GET })
	public JsonResponse<ReimbursementModel> editReimbursement(@RequestParam String id) {
		logger.info("Method : editReimbursement rest starts");

		logger.info("Method :editReimbursement rest ends");
		return reimbursementRestDao.editReimbursement(id);
	}
	
	/*
	 *
	 * Delete Reimbursement rest
	 *
	 */
	@GetMapping(value = "deleteReimbursement")
	public JsonResponse<ReimbursementModel> deleteReimbursement(@RequestParam String id) {
		logger.info("Method : Delete Reimbursement rest starts");

		logger.info("Method :Delete Reimbursement rest ends");
		return reimbursementRestDao.DeleteReimbursement(id);
	}
	
	

	
	
	@PostMapping(value = "add-reimbursement-travel-details")
	public ResponseEntity<JsonResponse<Object>> addReimbursementTravelDetails(@RequestBody  ReimbursementModel reimbursementModel) {
		logger.info("Method : addReimbursementTravelDetails starts");

		logger.info("Method : addReimbursementTravelDetails ends");
		return reimbursementRestDao.addReimbursementTravelDetails(reimbursementModel);
	}
	// Post mapping foe view Reimbuseiment

		@GetMapping(value = "viewReimbursementTravels")
		public JsonResponse<List<ReimbursementModel>> viewReimbursementTravels() {
			logger.info("Method : viewReimbursementTravels");

			logger.info("Method : viewReimbursementTravels ends");
			return reimbursementRestDao.viewReimbursementTravels();
		}
		
		
		
		/*
		 *
		 * Delete Reimbursement rest
		 *
		 */
		@GetMapping(value = "deleteReimbursementTravels")
		public JsonResponse<ReimbursementModel> deleteReimbursementTravels(@RequestParam String id) {
			logger.info("Method : Delete deleteReimbursementTravels rest starts");

			logger.info("Method :Delete deleteReimbursementTravels rest ends");
			return reimbursementRestDao.deleteReimbursementTravels(id);
		}
		
		/*
		 *
		 * Edit Reimbursement rest
		 *
		 */
		@RequestMapping(value = "edit-rest-reimbursement-travels", method = { RequestMethod.GET })
		public JsonResponse<ReimbursementModel> editReimbursementTravel(@RequestParam String id) {
			logger.info("Method : editReimbursementTravel rest starts");

			logger.info("Method :editReimbursementTravel rest ends");
			return reimbursementRestDao.editReimbursementTravel(id);
		}
		
		
		
		/*
		 * for drop down for grt Pay Mode type
		 */
		@RequestMapping(value = "getPaymentMode", method = { RequestMethod.GET })
		public List<DropDownModel> getPaymentMode() {

			logger.info("Method in rest: getPaymentMode starts");

			logger.info("Method in rest: getPaymentMode ends");

			return reimbursementRestDao.getPaymentMode();
		}
		
		/*
		 * for drop down for grt Pay Mode type
		 */
		@RequestMapping(value = "getBankNamesPay", method = { RequestMethod.GET })
		public List<DropDownModel> getBankNamesPay() {

			logger.info("Method in rest: getBankNamesPay starts");

			logger.info("Method in rest: getBankNamesPay ends");

			return reimbursementRestDao.getBankNamesPay();
		}
		
		/*
		 * Add Reimbursement rest
		 * 
		 */

		@PostMapping(value = "add-reimbursement-payment")
		public JsonResponse<Object> addReimbursementPayment(@RequestBody ReimbrusementPaymentModel reimbursementModel) {
			logger.info("Method : addReimbursementPayment rest starts");

			logger.info("Method : addReimbursementPayment rest ends");
			return reimbursementRestDao.addReimbursementPayment(reimbursementModel);
		}
		
		
		
		  @GetMapping(value = "get-activity-log-riemb") public List<ActivitylogModel>
		  getActivityLog(@RequestParam String id) {
		  logger.info("Method : getActivityLog starts");
		  logger.info("Method : getActivityLog endss"); return
		  reimbursementRestDao.getActivityLog(id); }
		 
		
		 
}
