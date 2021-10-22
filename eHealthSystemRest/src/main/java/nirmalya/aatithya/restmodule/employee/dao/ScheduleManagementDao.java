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
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateScheduleManagementParam;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.employee.model.ScheduleManagementModel;

@Repository
public class ScheduleManagementDao {

	Logger logger = LoggerFactory.getLogger(ScheduleManagementDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	EntityManager entityManager;

	/**
	 * DAO DROPDOWN department
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getDepartmentList() {
		logger.info("Method : getDepartment starts");
		List<DropDownModel> getDepartmentList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x = entityManager.createNamedStoredProcedureQuery("shiftScheduleRoutines")
					.setParameter("actionType", "getDepartmentList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getDepartmentList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getDepartment ends");
		return getDepartmentList;
	}

	/**
	 * DAO DROPDOWN schedule
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getScheduleList() {
		logger.info("Method : ScheduleList starts");
		List<DropDownModel> getScheduleList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x = entityManager.createNamedStoredProcedureQuery("shiftScheduleRoutines")
					.setParameter("actionType", "getScheduleList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getScheduleList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : ScheduleList ends");
		return getScheduleList;
	}

	/**
	 * DAO DROPDOWN section
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getSectionList() {
		logger.info("Method : SectionList starts");
		List<DropDownModel> getSectionList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x = entityManager.createNamedStoredProcedureQuery("shiftScheduleRoutines")
					.setParameter("actionType", "getSectionList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getSectionList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : SectionList ends");
		return getSectionList;
	}

	/**
	 * DAO DROPDOWN SHIFT
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getShiftList() {
		logger.info("Method : getShiftList starts");
		List<DropDownModel> getShiftList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x = entityManager.createNamedStoredProcedureQuery("shiftScheduleRoutines")
					.setParameter("actionType", "getShiftList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getShiftList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getShiftList ends");
		System.out.println(getShiftList);
		return getShiftList;
	}

	/**
	 * DAO DROPDOWN Employee
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getEmployeeList() {
		logger.info("Method : getEmployeeList starts");
		List<DropDownModel> getEmployeeList = new ArrayList<DropDownModel>();
		try {
			List<Object[]> x = entityManager.createNamedStoredProcedureQuery("shiftScheduleRoutines")
					.setParameter("actionType", "getEmployeeList").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				getEmployeeList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getShiftList ends");
		return getEmployeeList;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<ScheduleManagementModel>>> viewSchedule() {
		logger.info("Method : getEmployeeList starts");

		List<ScheduleManagementModel> viewSchedule = new ArrayList<ScheduleManagementModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("shiftScheduleRoutines")
					.setParameter("actionType", "viewSchedule").setParameter("actionValue", "").getResultList();

			for (Object[] m : x) {
				Object sDate = null;

				if (m[6] != null) {
					/* sDate = DateFormatter.returnStringDate(m[6]); */
					sDate = m[6].toString();
				}

				Object Date = null;
				if (m[7] != null) {
					/* Date = DateFormatter.returnStringDate(m[7]); */
					Date = m[7].toString();
				}

				Object createdOn = null;
				if (m[9] != null) {
					/* Date = DateFormatter.returnStringDate(m[7]); */
					createdOn = m[9].toString();
				}

				ScheduleManagementModel scheduleModel = new ScheduleManagementModel(m[0], null, null, m[1], m[2], m[3],
						null, m[4], m[5], sDate, Date, null, null, null, null, null, m[8], createdOn, null, null);
				viewSchedule.add(scheduleModel);
				System.out.println(scheduleModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonResponse<List<ScheduleManagementModel>> resp = new JsonResponse<List<ScheduleManagementModel>>();
		resp.setBody(viewSchedule);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		ResponseEntity<JsonResponse<List<ScheduleManagementModel>>> response = new ResponseEntity<JsonResponse<List<ScheduleManagementModel>>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : getScheduleList ends");
		System.out.println(response);
		return response;
	}

	/**
	 * DAO Function to Add schedule
	 *
	 */

	public ResponseEntity<JsonResponse<List<ScheduleManagementModel>>> addScheduleManagement(
			List<ScheduleManagementModel> scheduleModel) {
		logger.info("Method : addScheduleManagement dao starts");
		System.out.println(scheduleModel);
		JsonResponse<List<ScheduleManagementModel>> resp = new JsonResponse<List<ScheduleManagementModel>>();

		List<ScheduleManagementModel> listData = new ArrayList<ScheduleManagementModel>();

		String value = GenerateScheduleManagementParam.getScheduleRequisitionParam(scheduleModel);

		try {
			if (scheduleModel.get(0).getShiftScheduleId() != null && scheduleModel.get(0).getShiftScheduleId() != "") {
				System.out.println("modifyyyyyyyyyyyyyyyy" + value);

				entityManager.createNamedStoredProcedureQuery("shiftScheduleRoutines")
						.setParameter("actionType", "modifySchedule").setParameter("actionValue", value).execute();

			} else {
				System.out.println("addddddddd" + value);

				entityManager.createNamedStoredProcedureQuery("shiftScheduleRoutines")
						.setParameter("actionType", "addScheduleType").setParameter("actionValue", value).execute();

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

		resp.setBody(listData);
		ResponseEntity<JsonResponse<List<ScheduleManagementModel>>> response = new ResponseEntity<JsonResponse<List<ScheduleManagementModel>>>(
				resp, HttpStatus.CREATED);
		System.out.println("Resp" + response);
		logger.info("Method : addScheduleManagement dao ends");
		return response;
	}

	/**
	 * DAO Function to for Requisition edit
	 *
	 */
	@SuppressWarnings("unchecked")
	public List<ScheduleManagementModel> getScheduleEdit(String id) {
		logger.info("Method : getScheduleEdit starts");
		List<ScheduleManagementModel> getScheduleTypeList = new ArrayList<ScheduleManagementModel>();

		try {
			String values = "SET @P_sch='" + id + "';";
			
			System.out.println("###"+values);

			List<Object[]> x = entityManager.createNamedStoredProcedureQuery("shiftScheduleRoutines")
					.setParameter("actionType", "getScheduleEdit").setParameter("actionValue", values).getResultList();

			for (Object[] m : x) {

				String fromDate = null;
				if (m[6] != null) {
					fromDate = m[6].toString();
				}
				String toDate = null;
				if (m[7] != null) {
					toDate = m[7].toString();
				}

				String as = null;
				if (m[8] != null) {
					as = m[8].toString();
				}

				if (as.equals("0")) {
					ScheduleManagementModel dropDownModel = new ScheduleManagementModel(m[0], null, null, m[1], m[2],
							m[3], null, m[4], m[5], fromDate, toDate, null, null, null, null, null, null, null, null,
							null);
					getScheduleTypeList.add(dropDownModel);

					System.out.println("child only" + getScheduleTypeList);
				} else {
					ScheduleManagementModel dropDownModel = new ScheduleManagementModel(m[0], null, null, m[1], m[2],
							m[3], null, m[4], m[5], fromDate, toDate, m[8], m[9], m[10], m[11], m[12], null, null, null,
							null);
					getScheduleTypeList.add(dropDownModel);
					System.out.println("all" + getScheduleTypeList);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getScheduleEdit ends");

		System.out.println("dao" + getScheduleTypeList);
		return getScheduleTypeList;
	}

	/**
	 * DAO Function to delete Schedule
	 *
	 */
	public ResponseEntity<JsonResponse<Object>> deleteSchedule(ScheduleManagementModel restScheduleModel) {
		logger.info("Method : deleteSchedule dao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		resp.setMessage("");
		resp.setCode("");

		try {
			String value = GenerateScheduleManagementParam.getDeleteParam(restScheduleModel);

			entityManager.createNamedStoredProcedureQuery("shiftScheduleRoutines")
					.setParameter("actionType", "deleteSchedule").setParameter("actionValue", value).execute();

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
		logger.info("Method : deleteSchedule dao ends" + response);

		return response;
	}

}
