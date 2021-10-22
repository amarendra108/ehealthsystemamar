package nirmalya.aatithya.restmodule.customer.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateCustomerNewParameter;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.customer.model.RestCustoomerNewModel;




@Repository
public class CustomerNewDao {

	Logger logger = LoggerFactory.getLogger(CustomerNewDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	// private Object value;

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getcustcountrylist() {
		logger.info("Method : demo Dao starts");

		List<DropDownModel> getcustcountrylist = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("customermaster")
					.setParameter("actionType", "custcountrylist").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getcustcountrylist.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : demo Dao ends");

		return getcustcountrylist;
	}


	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getcuststateListNew(String id) {

		logger.info("Method : getcuststateListNew starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();

		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();

		String value = "SET @p_country='" + id + "';";
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("customermaster")
					.setParameter("actionType", "custstateList").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				resp, HttpStatus.CREATED);
		System.out.println(response);
		logger.info("Method : getcuststateListNew ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getcustdistListNew(String id) {

		logger.info("Method : getcustdistListNew starts");
		List<DropDownModel> distList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();

		String value = "SET @p_country='" + id + "';";
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("customermaster")
					.setParameter("actionType", "custdistrictList").setParameter("actionValue", value).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				distList.add(dropDownModel);
			}

			resp.setBody(distList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				resp, HttpStatus.CREATED);
		System.out.println(response);
		logger.info("Method : getcustdistListNew ends");
		return response;
	}

	public ResponseEntity<JsonResponse<Object>> addcustomer(RestCustoomerNewModel customerNewModel) {

		logger.info("Method : addcustomer starts");
		System.out.println("customerNewModel" + customerNewModel);

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String values = GenerateCustomerNewParameter.getAddempParam(customerNewModel);
			System.out.println(values);

			if (customerNewModel.getCustId() == null || customerNewModel.getCustId() == "") {
				System.out.println("Hii New");
				em.createNamedStoredProcedureQuery("customermaster").setParameter("actionType", "addcust")
						.setParameter("actionValue", values).execute();
				System.out.println("print in addEmp block");

			} else {
				System.out.println("print in modify block");
				em.createNamedStoredProcedureQuery("customermaster").setParameter("actionType", "modifycust")
						.setParameter("actionValue", values).execute();

			}

		} catch

		(Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		System.out.println(response);
		logger.info("Method : addcustomer ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestCustoomerNewModel>> getAllcustomer() {
		logger.info("Method : getcustomer Dao starts");

		List<RestCustoomerNewModel> getAllemployee = new ArrayList<RestCustoomerNewModel>();
		JsonResponse<List<RestCustoomerNewModel>> resp = new JsonResponse<List<RestCustoomerNewModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("customermaster")
					.setParameter("actionType", "viewcustomer").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				
				RestCustoomerNewModel viewdemo = new RestCustoomerNewModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7],m[8],m[9],m[10],m[11],m[12],m[13],m[14],m[15].toString());
				getAllemployee.add(viewdemo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setBody(getAllemployee);
		logger.info("Method : getcustomer Dao ends");

		return resp;

	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestCustoomerNewModel>>editcustomer(String id) {
		logger.info("Method : editcustomer starts");

		RestCustoomerNewModel req = new RestCustoomerNewModel();
		JsonResponse<RestCustoomerNewModel> resp = new JsonResponse<RestCustoomerNewModel>();

		try {

			String value = "SET @p_custId='" + id + "';";
			System.out.println(value);

			List<Object[]> x = em.createNamedStoredProcedureQuery("customermaster")
					.setParameter("actionType", "editcustomer").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {

				RestCustoomerNewModel reqemp = new RestCustoomerNewModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7],m[8],m[9],m[10],m[11],m[12],m[13],null,null);
				req=reqemp;

			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestCustoomerNewModel>> response = new ResponseEntity<JsonResponse<RestCustoomerNewModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : editcustomer ends");
		System.out.println(response);
		return response;
	}
	
	public JsonResponse<RestCustoomerNewModel> deleteCustomer(String deleteId) {
		logger.info("Method : deleteCustomer starts");

		RestCustoomerNewModel req = new RestCustoomerNewModel();
		JsonResponse<RestCustoomerNewModel> resp = new JsonResponse<RestCustoomerNewModel>();

		try {
			String value = "SET  @p_custId='(" + deleteId + ")';";
			System.out.println("DELETE " + value);

			em.createNamedStoredProcedureQuery("customermaster").setParameter("actionType", "deleteCustomer")
					.setParameter("actionValue", value).
					execute();
			
			
			resp.setBody(req);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		
		logger.info("Method : deleteCustomer ends");
		return resp;
		
	}
	
}
