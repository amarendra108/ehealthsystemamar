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

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.employee.dao.ExtendExitManagementRestDao;
import nirmalya.aatithya.restmodule.employee.model.ExtendExitManagementRestModel;

@RestController
@RequestMapping("employee/")
public class ExtendExitManagementRestController {

	Logger logger = LoggerFactory.getLogger(ExtendExitManagementRestController.class);

	@Autowired
	ExtendExitManagementRestDao exitManagementDao;

	// Name list

	@RequestMapping(value = "getNamelist", method = { RequestMethod.GET })
	public List<DropDownModel> namelist() {
		logger.info("Method : namelist starts");

		logger.info("Method : namelist ends");
		return exitManagementDao.namelist();
	}

	// Department list(Clearance)

	@RequestMapping(value = "getDeptlist", method = { RequestMethod.GET })
	public List<DropDownModel> departmentList() {
		logger.info("Method : departmentList starts");

		logger.info("Method : departmentList ends");
		return exitManagementDao.deptList();
	}

	// "Clearance By" List

	@RequestMapping(value = "getClrncPersonList", method = { RequestMethod.GET })
	public List<DropDownModel> clrncPersonList() {
		logger.info("Method : clrncPersonList starts");

		logger.info("Method : clrncPersonList ends");
		return exitManagementDao.clrncPersonList();
	}

	// job

	@GetMapping(value = "rest-get-designationList")
	public JsonResponse<List<DropDownModel>> getDesignationList(@RequestParam String id) {
		logger.info("Method : getDesignationList starts");

		logger.info("Method : getDesignationList ends");
		return exitManagementDao.getDesignationList(id);
	}

	/*
	 * View Exit Management
	 * 
	 */
	@GetMapping(value = "viewExitdetails")
	public JsonResponse<List<ExtendExitManagementRestModel>> viewExtendExitManagementDtls() {
		logger.info("Method : viewExtendExitManagementDtls starts");

		logger.info("Method : viewExtendExitManagementDtls ends");

		return exitManagementDao.viewExtendExitManagementDtls();
	}

	/*
	 * Add Exit Management
	 * 
	 */

	@PostMapping(value = "addExitdetails")
	public JsonResponse<Object> addExitManagement(@RequestBody ExtendExitManagementRestModel exit) {
		logger.info("Method : addExitManagement starts");

		logger.info("Method : addExitManagement ends");
		return exitManagementDao.addExitManagement(exit);
	}

	/*
	 * Add Initiate & Clearance Details
	 * 
	 */
	@PostMapping(value = "addinitiatedata")

	public ResponseEntity<JsonResponse<List<ExtendExitManagementRestModel>>> addClearanceDetails(
			@RequestBody List<ExtendExitManagementRestModel> exit) {
		logger.info("Method : addClearanceDetails starts");

		logger.info("Method : addClearanceDetails ends");
		return exitManagementDao.addClearanceDetailsDao(exit);
	}

	/*
	 * View Clearance
	 */
	/*
	 * @GetMapping(value = "viewClrcDtls") public
	 * JsonResponse<List<ExtendExitManagementRestModel>> viewClrncDtls() {
	 * logger.info("Method : viewClrncDtls starts");
	 * 
	 * logger.info("Method : viewClrncDtls ends");
	 * 
	 * return exitManagementDao.viewClrncDtls(); }
	 */
	/*
	 *
	 * Edit Exit details
	 *
	 */
	@RequestMapping(value = "editExitManagement", method = { RequestMethod.GET })
	public JsonResponse<ExtendExitManagementRestModel> editManagementDetails(@RequestParam String id) {
		logger.info("Method : editManagementDetails starts");

		logger.info("Method :editManagementDetails ends");
		return exitManagementDao.editManagementDetails(id);
	}
	
	/*
	 *
	 * check clearance details
	 *
	 */
	@GetMapping(value = "getclearancedetails")
	public JsonResponse<List<ExtendExitManagementRestModel>> checkClearanceDetails(@RequestParam String id) {
		logger.info("Method : checkClearanceDetails starts");

		logger.info("Method :checkClearanceDetails ends");
		return exitManagementDao.checkClearanceDetails(id);
	}
	
	/*
	 *
	 * Delete Student details
	 *
	 */
	@GetMapping(value = "exitManagementdelete")
	public JsonResponse<ExtendExitManagementRestModel> deleteExitDetails(@RequestParam String id) {
		logger.info("Method : deleteExitDetails starts");

		logger.info("Method :deleteExitDetails ends");
		return exitManagementDao.deleteExitDetails(id);
	}
	/*
	 * Add Finance Details
	 * 
	 */

	@PostMapping(value = "addFinancedetails")
	public JsonResponse<Object> addFinanceDetails(@RequestBody ExtendExitManagementRestModel exit) {
		logger.info("Method : addFinanceDetails starts");

		logger.info("Method : addFinanceDetails ends");
		return exitManagementDao.addFinanceDetails(exit);
	}


}