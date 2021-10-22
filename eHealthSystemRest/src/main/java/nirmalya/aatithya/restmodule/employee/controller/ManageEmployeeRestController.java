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
import nirmalya.aatithya.restmodule.employee.dao.ManageEmployeeDao;
import nirmalya.aatithya.restmodule.employee.model.EmployeeDocumentModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeAddressRestModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeBankDetailsRestModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeBenifitrestModel;
/*import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeBenifitModel;*/
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeDependentRestModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeInsuranceDetailsrestModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeRestModel;
import nirmalya.aatithya.restmodule.employee.model.ManageEmployeeWorkdetailsRestModel;
import nirmalya.aatithya.restmodule.master.controller.LocationMasterRestController;
import nirmalya.aatithya.restmodule.master.dao.LocationMasterDao;
import nirmalya.aatithya.restmodule.master.model.LocationMasterModel;
import nirmalya.aatithya.restmodule.procurment.model.InventoryVendorDocumentModel;
import nirmalya.aatithya.restmodule.procurment.model.InventoryVendorRfqModel;

/**
 * @author NirmalyaLabs
 *
 */
@RestController
@RequestMapping("employee/")
public class ManageEmployeeRestController {

	Logger logger = LoggerFactory.getLogger(ManageEmployeeRestController.class);

	@Autowired
	ManageEmployeeDao manageemployeeDao;

	@RequestMapping(value = "getCountryList", method = { RequestMethod.GET })
	public List<DropDownModel> getCountryList() {
		logger.info("Method : getCountryList starts");

		logger.info("Method : getCountryList ends");
		return manageemployeeDao.getCountryList();

	}

	
	@RequestMapping(value = "getBandList", method = { RequestMethod.GET })
	public List<DropDownModel> getBandList() {
		logger.info("Method : getBandList starts");

		logger.info("Method : getBandList ends");
		return manageemployeeDao.getBandList();

	}
	
	@RequestMapping(value = "getgenderList1", method = { RequestMethod.GET })
	public List<DropDownModel> getgenderList1() {
		logger.info("Method : getgenderList1 starts");

		logger.info("Method : getgenderList1 ends");
		return manageemployeeDao.getgenderList1();
	}

	@RequestMapping(value = "getnationalityList1", method = { RequestMethod.GET })
	public List<DropDownModel> getnationalityList1() {
		logger.info("Method : getnationalityList1 starts");

		logger.info("Method : getnationalityList1 ends");
		return manageemployeeDao.getnationalityList1();
	}

	@RequestMapping(value = "getbloodgroupList1", method = { RequestMethod.GET })
	public List<DropDownModel> getbloodgroupList1() {
		logger.info("Method : getbloodgroupList1 starts");

		logger.info("Method : getbloodgroupList1 ends");
		return manageemployeeDao.getbloodgroupList1();
	}

	@RequestMapping(value = "getmaritalstatusList1", method = { RequestMethod.GET })
	public List<DropDownModel> getmaritalstatusList1() {
		logger.info("Method : getmaritalstatusList1 starts");

		logger.info("Method : getmaritalstatusList1 ends");
		return manageemployeeDao.getmaritalstatusList1();
	}

	@RequestMapping(value = "documentTypeList", method = { RequestMethod.GET })
	public List<DropDownModel> getdocumenttypeList1() {
		logger.info("Method : getdocumenttypeList1 starts");

		logger.info("Method : getdocumenttypeList1 ends");
		return manageemployeeDao.getdocumenttypeList1();
	}

	@RequestMapping(value = "getJobType1", method = { RequestMethod.GET })
	public List<DropDownModel> getJobType1() {
		logger.info("Method : getJobType1 starts");

		logger.info("Method : getJobType1 ends");
		return manageemployeeDao.getJobType1();
	}

	@RequestMapping(value = "getDepartmentList1", method = { RequestMethod.GET })
	public List<DropDownModel> getDepartmentList1() {
		logger.info("Method : getDepartmentList1 starts");

		logger.info("Method : getDepartmentList1 ends");
		return manageemployeeDao.getDepartmentList1();
	}
	@RequestMapping(value = "getTimesheetType1", method = { RequestMethod.GET })
	public List<DropDownModel> getTimesheetType1() {
		logger.info("Method : getTimesheetType1 starts");

		logger.info("Method : getTimesheetType1 ends");
		return manageemployeeDao.getTimesheetType1();
	}
	@RequestMapping(value = "getemploymentType1", method = { RequestMethod.GET })
	public List<DropDownModel> getemploymentType1() {
		logger.info("Method : getemploymentType1 starts");

		logger.info("Method : getemploymentType1 ends");
		return manageemployeeDao.getemploymentType1();
	}
	
	
	@RequestMapping(value = "getaddressList1", method = { RequestMethod.GET })
	public List<DropDownModel> getaddressList1() {
		logger.info("Method : getaddressList1 starts");

		logger.info("Method : getaddressList1 ends");
		return manageemployeeDao.getaddressList1();
	}

	@RequestMapping(value = "getJobType2", method = { RequestMethod.GET })
	public List<DropDownModel> getJobType2() {
		logger.info("Method : getJobType2 starts");

		logger.info("Method : getJobType2 ends");
		return manageemployeeDao.getJobType2();
	}

	@RequestMapping(value = "getBenefits", method = { RequestMethod.GET })
	public List<DropDownModel> getBenefits() {
		logger.info("Method : getBenefits starts");

		logger.info("Method : getBenefits ends");
		return manageemployeeDao.getBenefits();
	}

	@RequestMapping(value = "getBankNameList", method = { RequestMethod.GET })
	public List<DropDownModel> getBankNameList() {
		logger.info("Method : getBankNameList starts");

		logger.info("Method : getBankNameList ends");
		return manageemployeeDao.getBankNameList();
	}
	
	@RequestMapping(value = "dependentTypeList", method = { RequestMethod.GET })
	public List<DropDownModel> dependentTypeList() {
		logger.info("Method : dependentTypeList starts");

		logger.info("Method : dependentTypeList ends");
		return manageemployeeDao.dependentTypeList();
	}
	
	@RequestMapping(value = "relationshipList", method = { RequestMethod.GET })
	public List<DropDownModel> relationshipList() {
		logger.info("Method : relationshipList starts");

		logger.info("Method : relationshipList ends");
		return manageemployeeDao.relationshipList();
	}
	 @RequestMapping(value = "EmployeeList", method = { RequestMethod.GET })
	 public List<DropDownModel> EmployeeList() {
	 logger.info("Method : EmployeeList starts");
	 logger.info("Method : EmployeeList ends"); return
	 manageemployeeDao.EmployeeList(); }
	
	@GetMapping(value = "getManager")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getManager(@RequestParam String id) {
		logger.info("Method : getManager starts");

		logger.info("Method : getManager ends");
		return manageemployeeDao.getManager(id);
	}
	
	@GetMapping(value = "getTeam")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getTeam(@RequestParam String id) {
		logger.info("Method : getTeam starts");

		logger.info("Method : getTeam ends");
		return manageemployeeDao.getTeam(id);
	}

	@RequestMapping(value = "insuranceCompanyList", method = { RequestMethod.GET })
	public List<DropDownModel> insuranceCompanyList() {
		logger.info("Method : insuranceCompanyList starts");

		logger.info("Method : insuranceCompanyList ends");
		return manageemployeeDao.insuranceCompanyList();
	}
	
	@RequestMapping(value = "saveemployeeMaster", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<ManageEmployeeRestModel>> saveemployeeMaster(
			@RequestBody ManageEmployeeRestModel employeee) {
		logger.info("Method : saveemployeeMaster starts");

		logger.info("Method : saveemployeeMaster ends");
		return manageemployeeDao.saveemployeeMaster(employeee);
	}

	@RequestMapping(value = "view-manage-employee-maste", method = { RequestMethod.GET })
	ResponseEntity<JsonResponse<List<ManageEmployeeRestModel>>> viewmanageemployeemaste() {
		logger.info("Method : viewEmployeeadd start");

		logger.info("Method : viewEmployeeadd ends");

		return manageemployeeDao.viewmanageemployeemaste();

	}

	@RequestMapping(value = "saveemployeeaddress", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<ManageEmployeeAddressRestModel>> saveemployeeaddress(
			@RequestBody ManageEmployeeAddressRestModel manageEmployeeAddressModel) {
		logger.info("Method : saveemployeeaddress starts");

		logger.info("Method : saveemployeeaddress ends");
		return manageemployeeDao.saveemployeeaddress(manageEmployeeAddressModel);
	}

	@RequestMapping(value = "/viewEmployeeadd", method = { RequestMethod.GET })
	ResponseEntity<JsonResponse<List<ManageEmployeeAddressRestModel>>> viewEmployeeadd(@RequestParam String id) {
		logger.info("Method : viewEmployeeadd start");

		logger.info("Method : viewEmployeeadd ends");

		return manageemployeeDao.viewEmployeeadd(id);
	}

	@RequestMapping(value = "saveemployeeworkdetails", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<ManageEmployeeWorkdetailsRestModel>> saveemployeeworkdetails(
			@RequestBody ManageEmployeeWorkdetailsRestModel manageEmployeeWorkdetailsRestModel) {
		logger.info("Method : saveemployeeaddress starts");

		logger.info("Method : saveemployeeaddress ends");
		return manageemployeeDao.saveemployeeworkdetails(manageEmployeeWorkdetailsRestModel);
	}

	@RequestMapping(value = "/viewEmployeework", method = { RequestMethod.GET })
	ResponseEntity<JsonResponse<List<ManageEmployeeWorkdetailsRestModel>>> viewEmployeework(@RequestParam String id) {
		logger.info("Method : viewEmployeeadd start");

		logger.info("Method : viewEmployeeadd ends");

		return manageemployeeDao.viewEmployeework(id);
	}

	@RequestMapping(value = "saveemployeedependent", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<ManageEmployeeDependentRestModel>> saveemployeedependent(
			@RequestBody ManageEmployeeDependentRestModel manageEmployeeDependentRestModel) {
		logger.info("Method : saveemployeedependent starts");

		logger.info("Method : saveemployeedependent ends");
		return manageemployeeDao.saveemployeedependent(manageEmployeeDependentRestModel);
	}

	@RequestMapping(value = "/viewempdepent", method = { RequestMethod.GET })
	ResponseEntity<JsonResponse<List<ManageEmployeeDependentRestModel>>> viewempdepent(@RequestParam String id) {
		logger.info("Method : viewempdepent start");

		logger.info("Method : viewempdepent ends");

		return manageemployeeDao.viewempdepent(id);
	}

	@RequestMapping(value = "saveemployeebank", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<ManageEmployeeBankDetailsRestModel>> saveemployeebank(
			@RequestBody ManageEmployeeBankDetailsRestModel manageEmployeeBankDetailsRestModel) {
		logger.info("Method : saveemployeebankdetails starts");

		logger.info("Method : saveemployeebankdetails ends");
		return manageemployeeDao.saveemployeebank(manageEmployeeBankDetailsRestModel);
	}

	@RequestMapping(value = "/viewebank", method = { RequestMethod.GET })
	ResponseEntity<JsonResponse<List<ManageEmployeeBankDetailsRestModel>>> viewebank(@RequestParam("id") String id) {
		logger.info("Method : viewVendorBankDetails start");

		logger.info("Method : viewVendorBankDetails ends");

		return manageemployeeDao.viewebank(id);
	}

	@RequestMapping(value = "saveempinsurance", method = { RequestMethod.POST })
	public ResponseEntity<JsonResponse<ManageEmployeeInsuranceDetailsrestModel>> saveempinsurance(

			@RequestBody ManageEmployeeInsuranceDetailsrestModel manageEmployeeInsuranceDetailsrestModel) {
		logger.info("Method : saveempinsurance starts");

		logger.info("Method : saveempinsurance ends");
		return manageemployeeDao.saveempinsurance(manageEmployeeInsuranceDetailsrestModel);
	}

	@RequestMapping(value = "/vieweinsurance", method = { RequestMethod.GET })
	ResponseEntity<JsonResponse<List<ManageEmployeeInsuranceDetailsrestModel>>> vieweinsurance(
			@RequestParam("id") String id) {
		logger.info("Method : vieweinsurance start");

		logger.info("Method : vieweinsurance ends");

		return manageemployeeDao.vieweinsurance(id);
	}

	@RequestMapping(value = "editmanageEmployeemasterById", method = { RequestMethod.GET })
	public ResponseEntity<JsonResponse<ManageEmployeeRestModel>> editmanageEmployeemasterById(@RequestParam String id) {
		logger.info("Method : editmanageEmployeemasterById starts");

		logger.info("Method : editmanageEmployeemasterById ends");
		return manageemployeeDao.editmanageEmployeemasterById(id);
	}

	@GetMapping(value = "deleteAddress")
	public ResponseEntity<JsonResponse<Object>> deleteAddress(@RequestParam String id) {
		logger.info("Method : deleteAddress starts");

		logger.info("Method : deleteAddress ends");
		return manageemployeeDao.deleteAddress(id);
	}

	@GetMapping(value = "deletework")
	public ResponseEntity<JsonResponse<Object>> deletework(@RequestParam String id) {
		logger.info("Method : deletework starts");

		logger.info("Method : deletework ends");
		return manageemployeeDao.deletework(id);
	}

	@GetMapping(value = "deletedependent")
	public ResponseEntity<JsonResponse<Object>> deletedependent(@RequestParam String id) {
		logger.info("Method : deletedependent starts");

		logger.info("Method : deletedependent ends");
		return manageemployeeDao.deletedependent(id);
	}

	@GetMapping(value = "deletebank")
	public ResponseEntity<JsonResponse<Object>> deletebank(@RequestParam String id) {
		logger.info("Method : deletebank starts");

		logger.info("Method : deletebank ends");
		return manageemployeeDao.deletebank(id);
	}

	@GetMapping(value = "deleteinsurance")
	public ResponseEntity<JsonResponse<Object>> deleteinsurance(@RequestParam String id) {
		logger.info("Method : deleteinsurance starts");

		logger.info("Method : deleteinsurance ends");
		return manageemployeeDao.deleteinsurance(id);
	}

	
	  @RequestMapping(value = "savebenifit", method = { RequestMethod.POST })
	  public ResponseEntity<JsonResponse<Object>> savebenifit(
	  
	  @RequestBody ManageEmployeeBenifitrestModel savebenifit) {
	  logger.info("Method : savebenifit starts");
	  
	  logger.info("Method : savebenifit ends"); return
	  manageemployeeDao.savebenifit(savebenifit); }
	  
	  
	
	 @RequestMapping(value = "/viewbenifit", method = {RequestMethod.GET})
	 ResponseEntity<JsonResponse<List<ManageEmployeeBenifitrestModel>>>
	 viewbenifit(@RequestParam String id) {
	 logger.info("Method : viewbenifit start");
	 
	 logger.info("Method : viewbenifit ends");
	 return manageemployeeDao.viewBenefits(id); 
	 }
	
	 @PostMapping(value = "addDoc")
		public ResponseEntity<JsonResponse<Object>> addDoc(
				@RequestBody EmployeeDocumentModel employeeDocumentModel) {
			logger.info("Method : addDoc starts");
			logger.info("Method : addDoc ends");
			return manageemployeeDao.addDoc(employeeDocumentModel);
		}

	 @GetMapping(value = "viewEmpDocEdit")
		public List<InventoryVendorDocumentModel> viewEmpDocEdit(@RequestParam String id) {
			logger.info("Method : viewEmpDocEdit starts");
			logger.info("Method : viewEmpDocEdit endss");
			return manageemployeeDao.viewEmpDocEdit(id);
		}
}
