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
import nirmalya.aatithya.restmodule.customer.dao.CustomerNewDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.customer.model.RestCustoomerNewModel;




@RestController
@RequestMapping("customer/")

public class RestCustomerNewController<StudentNewDao> {

Logger logger = LoggerFactory.getLogger(RestCustomerNewController.class);
	
	@Autowired
	
	CustomerNewDao CustomerNewDao;
	
	
	
	
	@PostMapping(value = "addcustomer")
	public ResponseEntity<JsonResponse<Object>> addcustomer(@RequestBody RestCustoomerNewModel customerNewModel) {
		logger.info("Method :restAddcust starts");
		System.out.println(customerNewModel);
		logger.info("Method :restAddcust endss");
		return CustomerNewDao.addcustomer(customerNewModel);
	}
	
	@GetMapping(value = "get-custcountry-list")
	public List<DropDownModel> getcustcountrylist() {
		logger.info("Method : demoCust starts");

		logger.info("Method : demotCust ends");
		return CustomerNewDao.getcustcountrylist();
	}
	
	@GetMapping(value = "rest-get-custstateList-New")
	public ResponseEntity<JsonResponse<List<DropDownModel>>>getcuststateListNew(@RequestParam String id) {
		logger.info("Method : getstateList starts");
		logger.info("Method : getstateList ends");
		return CustomerNewDao.getcuststateListNew(id);
	}

	@GetMapping(value = "rest-get-custdistList-New")
	public ResponseEntity<JsonResponse<List<DropDownModel>>>getcustdistListNew(@RequestParam String id) {
		logger.info("Method : getdistList starts");

		logger.info("Method : getdistList end");
		return CustomerNewDao.getcustdistListNew(id);
	}
	
	@GetMapping(value = "getAllcustomer")
	public JsonResponse<List<RestCustoomerNewModel>> getAllcustomer() {
		logger.info("Method :Allcustomer starts");
		
		
		logger.info("Method :Allcustomer endss");
		return CustomerNewDao.getAllcustomer();
	}
	@GetMapping(value = "editcustomer")
	public ResponseEntity<JsonResponse<RestCustoomerNewModel>> editcustomer(@RequestParam String id) {
		logger.info("Method : editcustomer starts");

		logger.info("Method :editcustomer ends");
		return CustomerNewDao.editcustomer(id);
	}
	
	@GetMapping(value = "deleteCustomer")
	 public JsonResponse<RestCustoomerNewModel> deleteCustomer(@RequestParam String id) {
		logger.info("Method : deleteCustomer starts");

		logger.info("Method : deleteCustomer ends");
		return CustomerNewDao.deleteCustomer(id); 
	}
	
}
