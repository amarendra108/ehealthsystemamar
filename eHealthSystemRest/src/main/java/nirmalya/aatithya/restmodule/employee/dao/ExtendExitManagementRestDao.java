package nirmalya.aatithya.restmodule.employee.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateExtendExitManagementParameter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.employee.model.ExtendExitManagementRestModel;

@Repository
public class ExtendExitManagementRestDao {
	Logger logger = LoggerFactory.getLogger(ExtendExitManagementRestDao.class);

	@Autowired
	EntityManager em;
	@Autowired
	ServerDao serverDao;

	/*
	 * add name list
	 * 
	 */
	
	@SuppressWarnings("unchecked")
	public List<DropDownModel> namelist() {
		logger.info("Method : namelist Dao starts");

		List<DropDownModel> namelist = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("exitmanagement")
					.setParameter("actionType", "getEmpNameList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				namelist.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : namelist Dao ends");

		return namelist;
	}

	/*
	 * Department list
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> deptList() {
		logger.info("Method : deptList Dao starts");

		List<DropDownModel> departmentList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("exitmanagement")
					.setParameter("actionType", "getDeptList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				departmentList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : deptList Dao ends");

		return departmentList;
	}

	/*
	 * Clearance Person list
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> clrncPersonList() {
		logger.info("Method : clrncPersonList Dao starts");

		List<DropDownModel> clrncPtList = new ArrayList<DropDownModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("exitmanagement")
					.setParameter("actionType", "getclrncPtList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				clrncPtList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : clrncPersonList Dao ends");

		return clrncPtList;
	}

	/*
	 *  job list
	 * 
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getDesignationList(String id) {

		logger.info("Method : getDesignationList starts");
		List<DropDownModel> jobList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();

		String value = "SET @p_tGuestName='" + id + "';";
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("exitmanagement")
					.setParameter("actionType", "getEmpDeginationList").setParameter("actionValue", value)
					.getResultList();
			for (Object[] m : x) {

				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				jobList.add(dropDownModel);
			}

			resp.setBody(jobList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getDesignationList ends");
		return resp;
	}

	/*
	 *  View Exit Management
	 */

	@SuppressWarnings("unchecked")
	public JsonResponse<List<ExtendExitManagementRestModel>> viewExtendExitManagementDtls() {

		logger.info("Method in Dao: viewExtendExitManagementDtls starts");

		List<ExtendExitManagementRestModel> exitDtlsList = new ArrayList<ExtendExitManagementRestModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("exitmanagement")
					.setParameter("actionType", "viewExitManagement").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {

				Object DATE = null;
				if (m[7] != null) {
					DATE = DateFormatter.returnStringDate(m[7]);
				}
				Object DATER = null;
				if (m[8] != null) {
					DATER = DateFormatter.returnStringDate(m[8]);
				}

				ExtendExitManagementRestModel exitManagementModel = new ExtendExitManagementRestModel(m[0], m[1], m[2],
						m[3], m[4], m[5], m[6], DATE, DATER, m[9], m[10], m[11], m[12], m[13], m[14], m[15], m[16],
						null,m[17].toString(),m[18],m[19],m[20],m[21]);
				exitDtlsList.add(exitManagementModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<ExtendExitManagementRestModel>> resp = new JsonResponse<List<ExtendExitManagementRestModel>>();
		resp.setBody(exitDtlsList);

		logger.info("Method in Dao: viewExtendExitManagementDtls ends");

		return resp;
	}

	/*
	 * View Clearance
	 */

	@SuppressWarnings("unchecked")
	public JsonResponse<List<ExtendExitManagementRestModel>> viewClrncDtls() {

		logger.info("Method in Dao: viewClrncDtls starts");

		List<ExtendExitManagementRestModel> clrncDtlsList = new ArrayList<ExtendExitManagementRestModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("exitmanagement")
					.setParameter("actionType", "viewClrncDtls").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {

				ExtendExitManagementRestModel exitManagementModel = new ExtendExitManagementRestModel(m[0], m[1], m[2],
						m[3], m[4], m[5], m[6], m[7], m[8], m[9],m[10],null,null,null,null,null,null,null,null,null,null,null,null);
				clrncDtlsList.add(exitManagementModel);
			}
		}

		/*
		 * if (x.get(0).length > 14) { BigInteger t = (BigInteger) x.get(0)[14];
		 *
		 * total = Integer.parseInt((t.toString())); }
		 */

		catch (Exception e) {
			e.printStackTrace();
		}

		JsonResponse<List<ExtendExitManagementRestModel>> resp = new JsonResponse<List<ExtendExitManagementRestModel>>();
		resp.setBody(clrncDtlsList);

		logger.info("Method in Dao: viewClrncDtls ends");

		return resp;
	}

	/*
	 * Add Exit Management
	 * 
	 */

	public JsonResponse<Object> addExitManagement(ExtendExitManagementRestModel exit) {

		logger.info("Method : addExitManagement starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String values = GenerateExtendExitManagementParameter.getAddExitManagementParam(exit);

			if (exit.getEmployeeExit() == null || exit.getEmployeeExit() == "") {

				em.createNamedStoredProcedureQuery("exitmanagement").setParameter("actionType", "addExitManagement")
						.setParameter("actionValue", values).execute();

			} else {
				em.createNamedStoredProcedureQuery("exitmanagement").setParameter("actionType", "modifyExitManagement")
						.setParameter("actionValue", values).execute();

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
		logger.info("Method : addExitManagement ends");
		return resp;
	}

	/*
	 * Add Clearance and Initiate Details
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ExtendExitManagementRestModel>>> addClearanceDetailsDao(
			List<ExtendExitManagementRestModel> exit) {

		logger.info("Method : addClearanceDetailsDao starts");
		JsonResponse<List<ExtendExitManagementRestModel>> resp = new JsonResponse<List<ExtendExitManagementRestModel>>();
		List<ExtendExitManagementRestModel> listData = new ArrayList<ExtendExitManagementRestModel>();
		try {
			String values = GenerateExtendExitManagementParameter.getAddClearanceParam(exit);

			if (exit.get(0).getInitId() == null || exit.get(0).getInitId() == "") {
				List<Object[]> x = em.createNamedStoredProcedureQuery("exitmanagement")
						.setParameter("actionType", "addExitInitate").setParameter("actionValue", values)
						.getResultList();

				for (Object[] m : x) {
					
					
					ExtendExitManagementRestModel dropDownModel = new ExtendExitManagementRestModel(m[0], m[1], m[2],
							m[3], m[4], m[5], m[6],null, m[7],null,m[8],m[9]);
					listData.add(dropDownModel);
				}

			} else {

				List<Object[]> x = em.createNamedStoredProcedureQuery("exitmanagement")
						.setParameter("actionType", "modifyClrncDtls").setParameter("actionValue", values)
						.getResultList();

				for (Object[] m : x) {
					ExtendExitManagementRestModel dropDownModel = new ExtendExitManagementRestModel(m[0], m[1], m[2],
							m[3], m[4], m[5], m[6],null, m[7],null,m[8],m[9]);
					listData.add(dropDownModel);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				String[] err = serverDao.errorProcedureCall(e);
				System.out.println(err);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		resp.setBody(listData);
		ResponseEntity<JsonResponse<List<ExtendExitManagementRestModel>>> response = new ResponseEntity<JsonResponse<List<ExtendExitManagementRestModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : addClearanceDetailsDao ends");
		return response;
	}

	/*
	 * Edit Exit Management
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<ExtendExitManagementRestModel> editManagementDetails(String id) {
		logger.info("Method : editManagementDetails starts");

		ExtendExitManagementRestModel req = new ExtendExitManagementRestModel();
		JsonResponse<ExtendExitManagementRestModel> resp = new JsonResponse<ExtendExitManagementRestModel>();

		try {

			String value = "SET @p_exitMangementId='" + id + "';";

			List<Object[]> x = em.createNamedStoredProcedureQuery("exitmanagement")
					.setParameter("actionType", "editExitManagement").setParameter("actionValue", value)
					.getResultList();

			for (Object[] m : x) {

				Object DATE = null;
				if (m[7] != null) {
					DATE = DateFormatter.returnStringDate(m[7]);
				}
				Object DATER = null;
				if (m[8] != null) {
					DATER = DateFormatter.returnStringDate(m[8]);
				}

				ExtendExitManagementRestModel reqEdit = new ExtendExitManagementRestModel(m[0], m[1], m[2], m[3], m[4],
						m[5], m[6], DATE, DATER, m[9], m[10], m[11], m[12], null, null, null, null, null,null,null,null,null,null);
				req = reqEdit;

			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : editManagementDetails ends");

		return resp;
	}
	
	/*
	 * Check clearance details
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<ExtendExitManagementRestModel>> checkClearanceDetails(String id) {
		logger.info("Method : checkClearanceDetails starts");

		List<ExtendExitManagementRestModel> req = new ArrayList<ExtendExitManagementRestModel>();
		JsonResponse<List<ExtendExitManagementRestModel>> resp = new JsonResponse<List<ExtendExitManagementRestModel>>();

		try {

			String value = "SET @p_empId='" + id + "';";
			List<Object[]> x = em.createNamedStoredProcedureQuery("exitmanagement")
					.setParameter("actionType", "checkClrncDetails").setParameter("actionValue", value).getResultList();

			for (Object[] m : x) {
				ExtendExitManagementRestModel reqEdit = new ExtendExitManagementRestModel(m[0], null, m[1], null,null,m[2], 
						m[3], m[4], m[5],m[6], m[7],m[8]);
				req.add(reqEdit);
			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : checkClearanceDetails ends");

		return resp;

	}
	/*
	 * Delete Exit Management Details
	 * 
	 */

	public JsonResponse<ExtendExitManagementRestModel> deleteExitDetails(String deleteId) {
		logger.info("Method : deleteExitDetails starts");

		ExtendExitManagementRestModel req = new ExtendExitManagementRestModel();
		JsonResponse<ExtendExitManagementRestModel> resp = new JsonResponse<ExtendExitManagementRestModel>();

		try {

			String value = "SET @p_exitMangementId='(" + deleteId + ")';";

			em.createNamedStoredProcedureQuery("exitmanagement").setParameter("actionType", "deleteExitDetails")
					.setParameter("actionValue", value).execute();

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}


		logger.info("Method : deleteExitDetails ends");

		return resp;
	}
	
	
	/*
	 * Add Finance Details
	 * 
	 */

	public JsonResponse<Object> addFinanceDetails(ExtendExitManagementRestModel exit) {

		logger.info("Method : addFinanceDetails starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String values = GenerateExtendExitManagementParameter.getAddFinanceDetailsParam(exit);

			if (exit.getFinanceId() == null || exit.getFinanceId() == "") {

				em.createNamedStoredProcedureQuery("exitmanagement").setParameter("actionType", "addFinanceDetails")
						.setParameter("actionValue", values).execute();

			} else {
				em.createNamedStoredProcedureQuery("exitmanagement").setParameter("actionType", "modifyFinanceDetails")
						.setParameter("actionValue", values).execute();

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
		logger.info("Method : addFinanceDetails ends");
		return resp;
	}
	
}