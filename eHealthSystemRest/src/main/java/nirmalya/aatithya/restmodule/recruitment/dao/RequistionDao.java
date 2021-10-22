package nirmalya.aatithya.restmodule.recruitment.dao;

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
import nirmalya.aatithya.restmodule.common.utils.GenerateReqParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.recruitment.model.AddRecruitentModel;
import nirmalya.aatithya.restmodule.recruitment.model.RequisitionActivityModel;
import nirmalya.aatithya.restmodule.recruitment.model.RequisitionVendorModel;
import nirmalya.aatithya.restmodule.recruitment.model.RestRequisitonVendorAllocationModel;

@Repository
public class RequistionDao {
	Logger logger = LoggerFactory.getLogger(RequistionDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	// private Object value;

	@SuppressWarnings("unchecked")
	public List<DropDownModel> jobTypeList() {
		logger.info("Method : jobTypeList Dao starts");

		List<DropDownModel> jobList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("requistionRoutines")
					.setParameter("actionType", "jobType").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				jobList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : jobTypeList Dao ends");

		return jobList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> jobLocationList() {
		logger.info("Method : jobLocationList Dao starts");

		List<DropDownModel> jobLocationList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("requistionRoutines")
					.setParameter("actionType", "jobLocation").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				jobLocationList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : jobLocationList Dao ends");

		return jobLocationList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> DepartmentList() {
		logger.info("Method : DepartmentList Dao starts");

		List<DropDownModel> DepartmentList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("requistionRoutines")
					.setParameter("actionType", "department").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				DepartmentList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : DepartmentList Dao ends");

		return DepartmentList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> managerList() {
		logger.info("Method : managerList Dao starts");

		List<DropDownModel> managerList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("requistionRoutines")
					.setParameter("actionType", "employeeList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				managerList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : managerList Dao ends");

		return managerList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> bandList() {
		logger.info("Method : bandList Dao starts");

		List<DropDownModel> bandList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("requistionRoutines")
					.setParameter("actionType", "bandList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				bandList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : bandList Dao ends");

		return bandList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> educationList() {
		logger.info("Method : educationList Dao starts");

		List<DropDownModel> educationList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("requistionRoutines")
					.setParameter("actionType", "educationList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				educationList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : educationList Dao ends");

		return educationList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> workHourList() {
		logger.info("Method : workHourList Dao starts");

		List<DropDownModel> workHourList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("requistionRoutines")
					.setParameter("actionType", "workHourList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				workHourList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : workHourList Dao ends");

		return workHourList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> benefitsList() {
		logger.info("Method : benefitsList Dao starts");

		List<DropDownModel> benefitsList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("requistionRoutines")
					.setParameter("actionType", "benefitsList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				benefitsList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : benefitsList Dao ends");

		return benefitsList;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> aboutCompany() {
		logger.info("Method : aboutCompany Dao starts");

		List<DropDownModel> aboutCompany = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("requistionRoutines")
					.setParameter("actionType", "aboutCompany").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				aboutCompany.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : aboutCompany Dao ends");

		return aboutCompany;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> addressTypeList() {
		logger.info("Method : addressTypeList Dao starts");

		List<DropDownModel> educationList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("requistionRoutines")
					.setParameter("actionType", "addressTypeList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				educationList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : addressTypeList Dao ends");

		return educationList;
	}

	public ResponseEntity<JsonResponse<Object>> addRequisition(AddRecruitentModel req) {
		logger.info("Method : addRequisition starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		
			try {
				String values = GenerateReqParameter.getAddreqParam(req);
				
				System.out.println(values);
				if (req.getRequisitionId() == null || req.getRequisitionId() == "") {
					
					em.createNamedStoredProcedureQuery("requistionRoutines").setParameter("actionType", "addreq")
							.setParameter("actionValue", values).execute();
					
				} else {
					
					em.createNamedStoredProcedureQuery("requistionRoutines").setParameter("actionType", "modifyreq")
							.setParameter("actionValue", values).execute();

				}
			} catch (Exception e) {
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
		logger.info("Method : addRequisition ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<AddRecruitentModel>>> viewRequistion() {
		logger.info("Method : getRequistionview starts");

		List<AddRecruitentModel> viewreq = new ArrayList<AddRecruitentModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("requistionRoutines")
					.setParameter("actionType", "viewRequ").setParameter("actionValue", "").getResultList();
			
			for (Object[] m : x) {
				String sDate = null;
				if (m[7] != null) {
					sDate = m[7].toString();
				}
				String Date = null;
				if (m[8] != null) {
					Date = m[8].toString();
				}

				AddRecruitentModel reqModel = new AddRecruitentModel(m[0], null, m[1], null, null, null, m[2], null,
						m[3], m[4], m[5], null, m[6], sDate, null, null, null, null, null, null, Date, m[9], null,
						m[10].toString(),m[11].toString(),m[12].toString());
				viewreq.add(reqModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<AddRecruitentModel>> resp = new JsonResponse<List<AddRecruitentModel>>();
		resp.setBody(viewreq);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<List<AddRecruitentModel>>> response = new ResponseEntity<JsonResponse<List<AddRecruitentModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method :  getRequistionview ends");
		return response;
	}

	public ResponseEntity<JsonResponse<Object>> deleteRequistionById(String id) {
		logger.info("Method : deleteRequistionById starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		try {
			String value = "SET  @p_requistionId='(" + id + ")'";
			

			em.createNamedStoredProcedureQuery("requistionRoutines").setParameter("actionType", "deleterequition")
					.setParameter("actionValue", value).execute();

		} catch (Exception e) {
			e.printStackTrace();
			String[] err = serverDao.errorProcedureCall(e);
			resp.setCode(err[0]);
			resp.setMessage(err[1]);
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp, responseHeaders,
				HttpStatus.CREATED);

		logger.info("Method : deleteRequistionById ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<AddRecruitentModel>>> editRequisition(String id) {
		logger.info("Method : editRequisition starts");

		List<AddRecruitentModel> req = new ArrayList<AddRecruitentModel>();
		JsonResponse<List<AddRecruitentModel>> resp = new JsonResponse<List<AddRecruitentModel>>();

		try {

			String value = "SET @p_requisitionId='" + id + "';";
			

			List<Object[]> x = em.createNamedStoredProcedureQuery("requistionRoutines")
					.setParameter("actionType", "editReq").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {

				Object sDate = null;
				if (m[12] != null) {
					sDate = m[12].toString();
				}

				Object sDate2 = null;
				if (m[19] != null) {
					sDate2 = m[19].toString();
				}

				AddRecruitentModel reqEdit = new AddRecruitentModel(m[0], null, m[1], m[2], m[3], m[4], m[5], m[6],
						m[7], m[8], m[9], m[10], m[11], sDate, m[13], m[14], m[15], m[16], m[17], m[18], sDate2, m[20],
						m[21], m[22].toString(),m[23].toString(),m[24].toString());
				req.add(reqEdit);

			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<List<AddRecruitentModel>>> response = new ResponseEntity<JsonResponse<List<AddRecruitentModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : editRequisition ends");
		
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RequisitionActivityModel>>> activityRequisition(String id) {
		logger.info("Method : activityRequisition starts");

		List<RequisitionActivityModel> req = new ArrayList<RequisitionActivityModel>();
		JsonResponse<List<RequisitionActivityModel>> resp = new JsonResponse<List<RequisitionActivityModel>>();

		try {

			String value = "SET @p_requisitionId='" + id + "';";
			

			List<Object[]> x = em.createNamedStoredProcedureQuery("requistionRoutines")
					.setParameter("actionType", "reqActivity").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {

				String sDate = null;
				if (m[1] != null) {
					sDate = m[1].toString();
				}

				RequisitionActivityModel reqEdit = new RequisitionActivityModel(m[0], sDate, m[2], m[3]);
				req.add(reqEdit);

			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<List<RequisitionActivityModel>>> response = new ResponseEntity<JsonResponse<List<RequisitionActivityModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : activityRequisition ends");
		
		return response;
	}

	public ResponseEntity<JsonResponse<Object>> addRequisitionVendorAllocation(
			RestRequisitonVendorAllocationModel req) {
		logger.info("Method : addRequisitionVendorAllocation dao starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String values = GenerateReqParameter.getReqVenAllocationParam(req);

		em.createNamedStoredProcedureQuery("requistionRoutines").setParameter("actionType", "addreqvendor")
				.setParameter("actionValue", values).execute();

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);

		logger.info("Method : addRequisitionVendorAllocation dao ends");
		return response;
	}
	
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RequisitionVendorModel>>> viewRequisitionVendorList() {
		logger.info("Method : viewRequisitionVendorList starts");

		List<RequisitionVendorModel> venList = new ArrayList<RequisitionVendorModel>();
		 
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("requistionRoutines")
					.setParameter("actionType", "viewreqvendor").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {

				RequisitionVendorModel dropDownModel = new RequisitionVendorModel(m[0], m[1], m[2], null, null, null, null, null, null);
				venList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		JsonResponse<List<RequisitionVendorModel>> resp = new JsonResponse<List<RequisitionVendorModel>>();
		resp.setBody(venList);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<List<RequisitionVendorModel>>> response = new ResponseEntity<JsonResponse<List<RequisitionVendorModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : viewRequisitionVendorList ends");
		return response;
	}

}
