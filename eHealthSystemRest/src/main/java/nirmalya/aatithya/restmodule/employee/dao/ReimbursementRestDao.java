package nirmalya.aatithya.restmodule.employee.dao;

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
import nirmalya.aatithya.restmodule.common.utils.ActivitylogModel;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

import nirmalya.aatithya.restmodule.common.utils.GenerateReimbrusementPaymentParam;
import nirmalya.aatithya.restmodule.common.utils.GenerateReimbursementParam;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.employee.model.ReimbrusementPaymentModel;
import nirmalya.aatithya.restmodule.employee.model.ReimbursementModel;

@Repository
public class ReimbursementRestDao {

	Logger logger = LoggerFactory.getLogger(ReimbursementRestDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	/**
	 * DAO DROPDOWN reimbursement type
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getReimbursementTypeList() {
		logger.info("Method : getReimbursementTypeList starts");
		List<DropDownModel> getReimbursementTypeList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("reimbursementRoutine")
					.setParameter("actionType", "getReimbTypeList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getReimbursementTypeList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getDepartment ends");
		return getReimbursementTypeList;
	}

	/**
	 * DAO DROPDOWN policy type
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getPolicyTypeList() {
		logger.info("Method : getPolicyTypeList starts");
		List<DropDownModel> getPolicyTypeList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("reimbursementRoutine")
					.setParameter("actionType", "getPolicyType").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getPolicyTypeList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getPolicyTypeList ends");
		return getPolicyTypeList;
	}

	

	/*
	 * View Reimbursement
	 */

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ReimbursementModel>>> viewReimbursement() {
		logger.info("Method : getEmployeeList starts");

		List<ReimbursementModel> reimbursementList = new ArrayList<ReimbursementModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("reimbursementRoutine")
					.setParameter("actionType", "viewAllReimDetails").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {

				Object FromDATE = null;
				if (m[4] != null) {
					FromDATE = m[4].toString();
				}
				Object ToDATE = null;
				if (m[5] != null) {
					ToDATE = m[5].toString();
				}

				/*
				 * SELECT TER_Reimbursement, TER_EmpReim_Requisition, TER_EmpReim_Employee,
				 * TER_EmpReim_PlaceName, TER_EmpReim_FromDate, TER_EmpReim_ToDate,
				 * TER_EmpReim_Purpose, TER_EmpReim_AdvRequired, TER_EmpReim_AdvAmount,
				 * 
				 * TER_EmpReim_AmtPaid
				 */
				ReimbursementModel reimbursementModel = new ReimbursementModel(null, m[0], m[1], m[2], m[3], FromDATE,
						ToDATE, m[6], (Double) m[7], (Double) m[8],(Double) m[9], null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null);
				reimbursementList.add(reimbursementModel);

				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<ReimbursementModel>> resp = new JsonResponse<List<ReimbursementModel>>();
		resp.setBody(reimbursementList);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<List<ReimbursementModel>>> response = new ResponseEntity<JsonResponse<List<ReimbursementModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getScheduleList ends");
		return response;
	}


	// For add employee
	public ResponseEntity<JsonResponse<Object>> addReimbursementRestDao(ReimbursementModel req) {
		logger.info("Method : addReimbursementRestDao starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String values = GenerateReimbursementParam.getReimbursementParam(req);
			System.out.println(values);

			if (req.getReimbursementReqId() == null || req.getReimbursementReqId() == "") {
				System.out.println("Hii New");
				em.createNamedStoredProcedureQuery("reimbursementRoutine")
						.setParameter("actionType", "addReimbursement").setParameter("actionValue", values).execute();
				System.out.println("print in addreq block");

			} else {
				System.out.println("print in modify block");
				em.createNamedStoredProcedureQuery("reimbursementRoutine")
						.setParameter("actionType", "modifyReimbursement").setParameter("actionValue", values)
						.execute();

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
		System.out.println("respfvbnm" + resp);

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		System.out.println("respfvbnmcfggggggggggggggggggggggggf" + response);

		logger.info("Method : addReimbursementRestDao ends");
		return response;
	}
	

	/*
	 * Edit Reimbursement
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<ReimbursementModel> editReimbursement(String id) {
		logger.info("Method : editReimbursement dao starts");

		ReimbursementModel req = new ReimbursementModel();
		JsonResponse<ReimbursementModel> resp = new JsonResponse<ReimbursementModel>();

		try {

			String value = "SET @p_reimbursementReqId='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("reimbursementRoutine")
					.setParameter("actionType", "editReimById").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {

				Object FromDATE = null;
				if (m[4] != null) {
					FromDATE = m[4].toString();
				}
				Object ToDATE = null;
				if (m[5] != null) {
					ToDATE = m[5].toString();
				}
				 
				ReimbursementModel reqEdit = new ReimbursementModel(null, m[0], m[1], m[2], m[3], FromDATE,
						ToDATE, m[6], (Double) m[7], (Double) m[8],(Double) m[9], null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null);
				
		
				req = reqEdit;
				
			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : editReimbursement dao ends");
		System.out.println("EDIT@@@@" + resp);
		return resp;
	}

	/*
	 * Delete Reimbursement
	 * 
	 */

	public JsonResponse<ReimbursementModel> DeleteReimbursement(String id) {
		logger.info("Method : DeleteReimbursement dao starts");
		System.out.println("idddddd" + id);
		ReimbursementModel req = new ReimbursementModel();
		JsonResponse<ReimbursementModel> resp = new JsonResponse<ReimbursementModel>();

		try {

			String value = "SET @p_reimbursementReqId='(" + id + ")';";
			System.out.println(value);
			em.createNamedStoredProcedureQuery("reimbursementRoutine").setParameter("actionType", "deleteReimbursement")
					.setParameter("actionValue", value).execute();

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("resp" + resp);
		logger.info("Method : DeleteReimbursement dao ends");
		return resp;
	}

	

	/*
	 * Add ReimbursementRest Dao
	 * 
	 */

	public ResponseEntity<JsonResponse<Object>> addReimbursementTravelDetails(ReimbursementModel reimbursementModel) {
		logger.info("Method : Rest Add addReimbursementTravelDetails Type Dao starts");
		Boolean validity = true;
		JsonResponse<Object> resp = new JsonResponse<Object>();

		if (validity)
			try {
				String values = GenerateReimbursementParam.getReimbursementParamTravel(reimbursementModel);
				System.out.println("idddddddddddd" + reimbursementModel);
				if (reimbursementModel.getSlNo() != null && reimbursementModel.getSlNo() != "") {
					em.createNamedStoredProcedureQuery("reimbursementRoutine")
							.setParameter("actionType", "modifyReimbruseimentTravel")
							.setParameter("actionValue", values).execute();
					System.out.println("Modify" + values);
				} else {
					em.createNamedStoredProcedureQuery("reimbursementRoutine")
							.setParameter("actionType", "addReimbursementTravel").setParameter("actionValue", values)
							.execute();
					System.out.println("ADDDD" + values);
				}

			} catch (Exception e) {
				try {
					String[] err = serverDao.errorProcedureCall(e);
					resp.setCode(err[0]);
					resp.setMessage(err[1]);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);
		System.out.println("REMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM" + resp);
		logger.info("Method : Rest Add addReimbursementTravelDetails Type Dao ends");

		return response;
	}
	// View Reimbursement

	@SuppressWarnings("unchecked")
	public JsonResponse<List<ReimbursementModel>> viewReimbursementTravels() {
		logger.info("Method : viewReimbursementTravels Dao starts");

		List<ReimbursementModel> viewReimbursementTravels = new ArrayList<ReimbursementModel>();

		// System.out.println("viewEmployeeDetails"+viewEmployeeDetails);
		JsonResponse<List<ReimbursementModel>> resp = new JsonResponse<List<ReimbursementModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("reimbursementRoutine")
					.setParameter("actionType", "viewAllReimDetailsTreavels").setParameter("actionValue", "")
					.getResultList();

			for (Object[] m : x) {

				Object DATE = null;
				if (m[4] != null) {

					DATE = m[4].toString();
				}

			

				ReimbursementModel restStudentModel = new ReimbursementModel(m[0], m[1], null, null, null, null, null,
						null, null, null, null, null, m[2], m[3], DATE, m[5], (Double) m[6], m[7], m[8], m[9], null,
						null, null, null, null, null, null, null);

			
				viewReimbursementTravels.add(restStudentModel);
				
				

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("esdrftgyhujikfghj" + resp);
		resp.setBody(viewReimbursementTravels);
		logger.info("Method : viewNewEmployeeAttendances Dao ends");
		return resp;
	}

	

	/*
	 * Delete Reimbursement
	 * 
	 */

	public JsonResponse<ReimbursementModel> deleteReimbursementTravels(String id) {
		logger.info("Method : DeleteReimbursement dao starts");
		System.out.println("idddddd" + id);
		ReimbursementModel req = new ReimbursementModel();
		JsonResponse<ReimbursementModel> resp = new JsonResponse<ReimbursementModel>();

		try {

			String value = "SET @p_reimbursementReqIdss='(" + id + ")';";
			System.out.println(value);
			em.createNamedStoredProcedureQuery("reimbursementRoutine")
					.setParameter("actionType", "deleteReimbursementTravels").setParameter("actionValue", value)
					.execute();

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : deleteReimbursementTravels dao ends");
		return resp;
	}

	/*
	 * Edit Reimbursement
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<ReimbursementModel> editReimbursementTravel(String id) {
		logger.info("Method : editReimbursementTravel dao starts");

		ReimbursementModel req = new ReimbursementModel();
		JsonResponse<ReimbursementModel> resp = new JsonResponse<ReimbursementModel>();

		try {

			String value = "SET @p_reimbursementReqIdss='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("reimbursementRoutine")
					.setParameter("actionType", "editReimbruseimentTravel").setParameter("actionValue", value)
					.getResultList();

			for (Object[] m : x) {

				Object EXPDATE = null;
				if (m[4] != null) {

					EXPDATE = m[4].toString();
				}

				ReimbursementModel reqEdit = new ReimbursementModel(m[0], m[1], null, null, null, null, null, null,
						null, null, null, null, m[2], m[3], EXPDATE, m[5], (Double) m[6], m[7], m[8], m[9], null, null,
						null, null, null, null, null, null);
				req = reqEdit;

				
			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : editReimbursementTravel dao ends");
		System.out.println("EDIT@@@@" + resp);
		return resp;
	}

	// Return gender
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getRequisitionList() {
		logger.info("Method : getRequisitionList Dao starts");

		List<DropDownModel> getRequisitionList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("reimbursementRoutine")
					.setParameter("actionType", "getRequisitionList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getRequisitionList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getRequisitionList Dao ends");

		return getRequisitionList;
	}

	/*
	 * drop down for payment Modes
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getPaymentMode() {

		logger.info("Method in Dao: getPaymentMode starts");

		List<DropDownModel> payModeList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("reimbursementRoutine")
					.setParameter("actionType", "getPaymentMode").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				payModeList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method in Dao: getPaymentMode ends");

		return payModeList;
	}

	/*
	 * drop down for bank names
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getBankNamesPay() {

		logger.info("Method in Dao: getBankNamesPay starts");

		List<DropDownModel> bankList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("reimbursementRoutine")
					.setParameter("actionType", "getBankNamesPay").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				bankList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method in Dao: getBankNamesPay ends");

		return bankList;
	}

	
	/*
	 * Add ReimbursementRest Dao
	 * 
	 */
	public JsonResponse<Object> addReimbursementPayment(ReimbrusementPaymentModel reimbursementModel) {

		logger.info("Method : addReimbursementPayment dao starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String values = GenerateReimbrusementPaymentParam.getReimbursementParamPayment(reimbursementModel);

			if (reimbursementModel.getPaymentId() != null && reimbursementModel.getPaymentId() != "") {
				System.out.println("ADD" + values);
				em.createNamedStoredProcedureQuery("reimbursementRoutine")
						.setParameter("actionType", "addReimbursement").setParameter("actionValue", values).execute();

			} else {
				System.out.println("M" + values);
				em.createNamedStoredProcedureQuery("reimbursementRoutine")
						.setParameter("actionType", "addReimbursementPayment").setParameter("actionValue", values)
						.execute();

			}
		} catch

		(Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				System.out.println(err);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		logger.info("Method : addReimbursementPayment dao ends");
		return resp;
	}
	

	@SuppressWarnings("unchecked")
	public List<ActivitylogModel> getActivityLog(String id) {
		System.out.println("id" + id);
		logger.info("Method : getActivityLog starts");
		List<ActivitylogModel> activitylogModelList = new ArrayList<ActivitylogModel>();
		try {
			String value = "SET @p_reimbursementReqId='" + id + "'";
			System.out.println("actvity vendor" + value);
			List<Object[]> x = em.createNamedStoredProcedureQuery("reimbursementRoutine")
					.setParameter("actionType", "getActivityLogReimbrusement").setParameter("actionValue", value)
					.getResultList();

			for (Object[] m : x) {
				Object oa = null;
				if (m[6] != null) {
					oa = m[6].toString();
				}
				ActivitylogModel activitylogModel = new ActivitylogModel(m[0], m[1], m[2], m[3], m[4], m[5], oa);
				activitylogModelList.add(activitylogModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		logger.info("Method : getActivityLog ends");
		return activitylogModelList;
	}

	
	
}