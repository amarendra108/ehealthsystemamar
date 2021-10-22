package nirmalya.aatithya.restmodule.demo.dao;

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
import nirmalya.aatithya.restmodule.common.utils.GenerateEmployeeNewParameter;

import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.demo.model.RestEmployeeNewModel;


@Repository
public class EmployeeNewDao {

	Logger logger = LoggerFactory.getLogger(EmployeeNewDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	// private Object value;

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getgenderlist2() {
		logger.info("Method : demo Dao starts");

		List<DropDownModel> getgenderlist2 = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("employeemaster")
					.setParameter("actionType", "genderlist").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getgenderlist2.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : demo Dao ends");

		return getgenderlist2;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getmaritalstatusList2() {
		logger.info("Method : demo Dao starts");

		List<DropDownModel> getmaritalstatusList2 = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("employeemaster")
					.setParameter("actionType", "maritalstatusList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getmaritalstatusList2.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : demo Dao ends");

		return getmaritalstatusList2;
	}

	public ResponseEntity<JsonResponse<Object>> addEmp(RestEmployeeNewModel employeeNewModel) {

		logger.info("Method : addEmp starts");
		System.out.println("employeeNewModel" + employeeNewModel);

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String values = GenerateEmployeeNewParameter.getAddempParam(employeeNewModel);
			System.out.println(values);

			if (employeeNewModel.getEmployee() == null || employeeNewModel.getEmployee() == "") {
				System.out.println("Hii New");
				em.createNamedStoredProcedureQuery("employeemaster").setParameter("actionType", "addempdeatils")
						.setParameter("actionValue", values).execute();
				System.out.println("print in addEmp block");

			} else {
				System.out.println("print in modify block");
				em.createNamedStoredProcedureQuery("employeemaster").setParameter("actionType", "modifyempdetails")
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
		logger.info("Method : addEmp ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestEmployeeNewModel>> getAllemployee() {
		logger.info("Method : viewdemo Dao starts");

		List<RestEmployeeNewModel> getAllemployee = new ArrayList<RestEmployeeNewModel>();
		JsonResponse<List<RestEmployeeNewModel>> resp = new JsonResponse<List<RestEmployeeNewModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("employeemaster")
					.setParameter("actionType", "viewemployee").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				RestEmployeeNewModel viewdemo = new RestEmployeeNewModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7],m[8],m[9]);
				getAllemployee.add(viewdemo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setBody(getAllemployee);
		logger.info("Method : viewEmployee Dao ends");

		return resp;

	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestEmployeeNewModel>>editemployee(String id) {
		logger.info("Method : editemployee starts");

		RestEmployeeNewModel req = new RestEmployeeNewModel();
		JsonResponse<RestEmployeeNewModel> resp = new JsonResponse<RestEmployeeNewModel>();

		try {

			String value = "SET @p_empId='" + id + "';";
			System.out.println(value);

			List<Object[]> x = em.createNamedStoredProcedureQuery("employeemaster")
					.setParameter("actionType", "editemployee").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {

				

				RestEmployeeNewModel reqemp = new RestEmployeeNewModel(m[0],m[1], m[2], m[3], m[4], m[5], m[6],
						m[7],m[8],m[9]);
				req=reqemp;

			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestEmployeeNewModel>> response = new ResponseEntity<JsonResponse<RestEmployeeNewModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : editemployee ends");
		System.out.println(response);
		return response;
	}
	
	public JsonResponse<RestEmployeeNewModel> deleteemp(String deleteId) {
		logger.info("Method : deleteemp starts");

		RestEmployeeNewModel req = new RestEmployeeNewModel();
		JsonResponse<RestEmployeeNewModel> resp = new JsonResponse<RestEmployeeNewModel>();

		try {
			String value = "SET  @p_empId='(" + deleteId + ")';";
			System.out.println("DELETE " + value);

			em.createNamedStoredProcedureQuery("employeemaster").setParameter("actionType", "deleteemp")
					.setParameter("actionValue", value).
					execute();
			
			
			resp.setBody(req);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		
		logger.info("Method : deleteemp ends");
		return resp;
		
	}


}
