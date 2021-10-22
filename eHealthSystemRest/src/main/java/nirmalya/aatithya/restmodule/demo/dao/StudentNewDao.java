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
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

import nirmalya.aatithya.restmodule.common.utils.GenerateStudentNewParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.demo.model.RestStudentNewModel;
import nirmalya.aatithya.restmodule.demo.model.RestStudentNewModel;
import nirmalya.aatithya.restmodule.demo.model.RestStudentNewModel;

@Repository
public class StudentNewDao {

	Logger logger = LoggerFactory.getLogger(StudentNewDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	// private Object value;

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getcountrylist() {
		logger.info("Method : demo Dao starts");

		List<DropDownModel> getcountrylist = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("studentmaster")
					.setParameter("actionType", "countrylist").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getcountrylist.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : demo Dao ends");

		return getcountrylist;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getbloodGroup() {
		logger.info("Method : demo Dao starts");

		List<DropDownModel> getbloodGroup = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("studentmaster")
					.setParameter("actionType", "bloodGroup").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getbloodGroup.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : demo Dao ends");

		return getbloodGroup;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getstateList(String id) {

		logger.info("Method : getstateList starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();

		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();

		String value = "SET @p_country='" + id + "';";
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("studentmaster")
					.setParameter("actionType", "stateList").setParameter("actionValue", value).getResultList();
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
		logger.info("Method : getstateList ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getdistList(String id) {

		logger.info("Method : getdistList starts");
		List<DropDownModel> distList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();

		String value = "SET @p_country='" + id + "';";
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("studentmaster")
					.setParameter("actionType", "districtList").setParameter("actionValue", value).getResultList();
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
		logger.info("Method : getdistList ends");
		return response;
	}

	public ResponseEntity<JsonResponse<Object>> addstud(RestStudentNewModel studentNewModel) {

		logger.info("Method : addstud starts");
		System.out.println("studentNewModel" + studentNewModel);

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String values = GenerateStudentNewParameter.getAddempParam(studentNewModel);
			System.out.println(values);

			if (studentNewModel.getStudent() == null || studentNewModel.getStudent() == "") {
				System.out.println("Hii New");
				em.createNamedStoredProcedureQuery("studentmaster").setParameter("actionType", "addstudd")
						.setParameter("actionValue", values).execute();
				System.out.println("print in addEmp block");

			} else {
				System.out.println("print in modify block");
				em.createNamedStoredProcedureQuery("studentmaster").setParameter("actionType", "modifystudd")
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
		logger.info("Method : addstud ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestStudentNewModel>> getAllstudentt() {
		logger.info("Method : viewdemoo Dao starts");

		List<RestStudentNewModel> getAllstudentt = new ArrayList<RestStudentNewModel>();
		JsonResponse<List<RestStudentNewModel>> resp = new JsonResponse<List<RestStudentNewModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("studentmaster")
					.setParameter("actionType", "viewstudent").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				
				Object DATE = null;
				if (m[5] != null) {
					DATE = m[5].toString();
				}
				Object DATEE = null;
				if (m[13] != null) {
					DATEE = m[13].toString();
				}
				Object DATEET = null;
				if (m[14] != null) {
					DATEET = m[14].toString();
				}
				RestStudentNewModel viewdemo = new RestStudentNewModel(m[0], m[1], m[2], m[3], m[4], DATE, m[6],
						m[7],m[8],m[9],m[10],m[11],m[12],DATEE,DATEET,m[15]);
				getAllstudentt.add(viewdemo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setBody(getAllstudentt);
		logger.info("Method : viewdemoo Dao ends");

		return resp;

	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestStudentNewModel>>editstudentt(String id) {
		logger.info("Method : editstudentt starts");

		RestStudentNewModel req = new RestStudentNewModel();
		JsonResponse<RestStudentNewModel> resp = new JsonResponse<RestStudentNewModel>();

		try {

			String value = "SET @p_studId='" + id + "';";
			System.out.println(value);

			List<Object[]> x = em.createNamedStoredProcedureQuery("studentmaster")
					.setParameter("actionType", "editstudentt").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {


				Object DATE = null;
				if (m[5] != null) {
					DATE = m[5].toString();
				}
				Object DATEE = null;
				if (m[13] != null) {
					DATEE = m[13].toString();
				}
				Object DATEET = null;
				if (m[14] != null) {
					DATEET = m[14].toString();
				}
				
				RestStudentNewModel reqstud = new RestStudentNewModel(m[0], m[1], m[2], m[3], m[4], DATE, m[6],
						m[7],m[8],m[9],m[10],m[11],m[12],DATEE,DATEET,m[15]);
				
				req=reqstud;

			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestStudentNewModel>> response = new ResponseEntity<JsonResponse<RestStudentNewModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : editstudentt ends");
		System.out.println(response);
		return response;
	}
	
	public JsonResponse<RestStudentNewModel> deletestudd(String deleteId) {
		logger.info("Method : deletestudent starts");

		RestStudentNewModel req = new RestStudentNewModel();
		JsonResponse<RestStudentNewModel> resp = new JsonResponse<RestStudentNewModel>();

		try {
			String value = "SET  @p_studId='(" + deleteId + ")';";
			System.out.println("DELETE " + value);

			em.createNamedStoredProcedureQuery("studentmaster").setParameter("actionType", "deletestudd")
					.setParameter("actionValue", value).
					execute();
			
			
			resp.setBody(req);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		
		logger.info("Method : deletestudent ends");
		return resp;
		
	}

}
