
package nirmalya.aatithya.restmodule.master.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import nirmalya.aatithya.restmodule.master.model.RestAttendanceModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DataTableRequest;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.GenerateParameter;
import nirmalya.aatithya.restmodule.common.utils.GenerateParameterAttendance;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class RestAttendanceDao {
	Logger logger = LoggerFactory.getLogger(RestAttendanceDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	// For add employee 
	public ResponseEntity<JsonResponse<Object>> addEmployeeAttendances(RestAttendanceModel req) {
		logger.info("Method : addEmployeeAttendances starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String values = GenerateParameterAttendance.getAddempParam(req);

			if (req.getIsOut() == null || req.getIsOut() == "") {
				System.out.println("Hii New");
				em.createNamedStoredProcedureQuery("attendenceEmployeeRoutines")
						.setParameter("actionType", "addAttendance").setParameter("actionValue", values).execute();
				System.out.println("print in addreq block");

			} else {
				System.out.println("print in modify block");
				em.createNamedStoredProcedureQuery("attendenceEmployeeRoutines")
						.setParameter("actionType", "modifyEmployee").setParameter("actionValue", values).execute();

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

		logger.info("Method : addEmployeeAttendances ends");
		return response;
	}

	// View employee

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestAttendanceModel>> viewNewEmployeeAttendances() {
			logger.info("Method : viewNewEmployeeAttendances Dao starts");
  
			List<RestAttendanceModel> viewEmployeeDetails = new ArrayList<RestAttendanceModel>();
  
				// System.out.println("viewEmployeeDetails"+viewEmployeeDetails);
				JsonResponse<List<RestAttendanceModel>> resp = new JsonResponse<List<RestAttendanceModel>>(); 
				try {
  
					List<Object[]> x =em.createNamedStoredProcedureQuery("attendenceEmployeeRoutines") .setParameter("actionType", "viewAttendance").setParameter("actionValue", "").getResultList();
  
					for (Object[] m : x) {
						Object JDATE = null;
						if (m[1] != null) {

							JDATE = m[1].toString();
						}
						Object DATE = null;
						if (m[2] != null) {

							DATE = m[2].toString();
						}

						Object PDATE = null;
						if (m[5] != null) {

							PDATE = m[5].toString();
						}
  
  
						RestAttendanceModel restStudentModel = new RestAttendanceModel(m[0], JDATE, DATE, m[3], m[4],
								PDATE, m[6], m[7]);
						viewEmployeeDetails.add(restStudentModel);
						System.out.println("viewEmployeeDetails viewwwwwwwwwwwwwwwwwww" + viewEmployeeDetails);
						System.out.println("restStudentModeldao viewwwwwwwwwwwwwwwwwww" + restStudentModel);

					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("esdrftgyhujikfghj" + resp);
				resp.setBody(viewEmployeeDetails);
				logger.info("Method : viewNewEmployeeAttendances Dao ends");
				return resp;
			}

	/*
	 * DAO Function to get punchIn time
	 */

	@SuppressWarnings("unchecked")

	public List<DropDownModel> getDetailsPunchout(String empId, String date) {
		logger.info("Method : getDetailsPunchout starts");
		List<DropDownModel> details = new ArrayList<DropDownModel>(); // String date1 =
		DateFormatter.getStringDate(date);
		try {
			String values = "SET @p_attendanceId='" + empId + "',@p_date='" + date + "';";
			System.out.println(values);
			List<Object[]> x = em.createNamedStoredProcedureQuery("attendenceEmployeeRoutines")
					.setParameter("actionType", "getDetailsPunchout").setParameter("actionValue", values)
					.getResultList();

			for (Object[] m : x) {

				DropDownModel properties = new DropDownModel(null, m[1].toString());
				details.add(properties);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getDetailsPunchout ends");
		System.out.println("@@@@dfghjkldfghjk" + details);

		return details;
	}

	/*
	 * DAO Function to View all attendence details
	 */

	@SuppressWarnings("unchecked")

	public ResponseEntity<JsonResponse<List<RestAttendanceModel>>> viewAttendencePunchoutThroughAjax(
			DataTableRequest request) {
		logger.info("Method : getAllAttendence starts");
		if (request.getParam1() != "") {
			String param1 = request.getParam1();
			String data = DateFormatter.getStringDate(param1);
			request.setParam1(data);

		}
		List<RestAttendanceModel> form = new ArrayList<RestAttendanceModel>();
		String values = GenerateParameter.getSearchParam(request);

		Integer total = 0;

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("attendenceEmployeeRoutines")
					.setParameter("actionType", "viewAttendencePunchoutThroughAjax").setParameter("actionValue", values)
					.getResultList();

			for (Object[] m : x) {
				Object punchinDate = null;
				if (m[1] != null) {
					punchinDate = DateFormatter.returnStringDate(m[1]);
				}

				Object punchinTime = null;
				if (m[2] != null) {
					punchinTime = DateFormatter.returnStringDateTime(m[2]);
				}
				Object punchOutTime = null;
				if (m[5] != null) {
					punchOutTime = DateFormatter.returnStringDateTime(m[5]);
				}
				RestAttendanceModel properties = new RestAttendanceModel(m[0], punchinDate, punchinTime, m[3], m[4],
						punchOutTime, m[6], m[7]);
				form.add(properties);
			}

			if (x.get(0).length > 8) {
				BigInteger t = (BigInteger) x.get(0)[8];

				total = Integer.parseInt((t.toString()));
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		JsonResponse<List<RestAttendanceModel>> resp = new JsonResponse<List<RestAttendanceModel>>();
		resp.setBody(form);
		resp.setTotal(total);

		ResponseEntity<JsonResponse<List<RestAttendanceModel>>> response = new ResponseEntity<JsonResponse<List<RestAttendanceModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : viewAttendencePunchoutThroughAjax ends");

		return response;
	}

	// Add Punch out

	public ResponseEntity<JsonResponse<Object>> addEmployeeAttendancePunchOut(RestAttendanceModel req) {
		logger.info("Method : addEmployeeAttendancePunchOut starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			String values = GenerateParameterAttendance.getAddempParam(req);

			if (req.getIsOut() == null || req.getIsOut() == "") {
				em.createNamedStoredProcedureQuery("attendenceEmployeeRoutines")
						.setParameter("actionType", "modifyAttendencePuncchOut").setParameter("actionValue", values)
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

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(resp,
				HttpStatus.CREATED);

		logger.info("Method : addEmployeeAttendancePunchOut ends");
		return response;
	}

}
