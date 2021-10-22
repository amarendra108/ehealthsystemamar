package nirmalya.aatithya.restmodule.patient.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
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
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.model.RestPatientDetailsNewModel;

@Repository
public class PatientDetailsDao {
	Logger logger = LoggerFactory.getLogger(PatientDetailsDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	/*
	 * view patient information
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> editpatientnew(String id) {
		logger.info("Method : editpatientnew starts");

		RestPatientDetailsNewModel req = new RestPatientDetailsNewModel();
		JsonResponse<RestPatientDetailsNewModel> resp = new JsonResponse<RestPatientDetailsNewModel>();

		try {

			// Integer id2 = Integer.parseInt(id);

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_edit_patient_details")
					.setParameter("userid", id).getResultList();

			for (Object[] m : x) {
				Integer age = null;
				if (m[7] != null) {
					BigDecimal bd = (BigDecimal) m[7];
					age = bd.intValue();
				}

				String data = null;
				if (m[6] != null) {
					data = m[6].toString();
				}

				RestPatientDetailsNewModel reqemp = new RestPatientDetailsNewModel(m[0], m[1], m[2], m[3], m[4], m[5],
						data, age, m[8], m[9], m[10], m[11], m[12], m[13], m[14], m[15], m[16], m[17], null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, m[18], m[19], m[20], m[21],
						m[22], m[23], m[24], m[25], m[26], m[27], m[28], null, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, m[29], m[30], m[31], m[32], m[33],
						m[34], null, null, null, null, null, null);
				req = reqemp;

			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> response = new ResponseEntity<JsonResponse<RestPatientDetailsNewModel>>(
				resp, responseHeaders, HttpStatus.CREATED);
		System.out.println("################" + response);
		logger.info("Method : editpatientnew ends");
		return response;
	}

	/*
	 * View data for emergency contact
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestPatientDetailsNewModel>> patientemercontactnew(String id) {
		logger.info("Method : Patient Emergency contact Dao Starts");

		List<RestPatientDetailsNewModel> getAllemployee = new ArrayList<RestPatientDetailsNewModel>();
		JsonResponse<List<RestPatientDetailsNewModel>> resp = new JsonResponse<List<RestPatientDetailsNewModel>>();

		try {
			BigInteger userId = null;
			if (id != null) {
				userId = new BigInteger(id);
			}
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_edit_patient_emercontact_details")
					.setParameter("userid", userId).getResultList();

			for (Object[] m : x) {

				RestPatientDetailsNewModel reqemp = new RestPatientDetailsNewModel(m[0], m[1], m[2], m[3], m[4], m[5]);
				getAllemployee.add(reqemp);
				// System.out.println("===getAllemployee====="+getAllemployee);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(getAllemployee);
		logger.info("Method : Patient emergency contact Dao Ends");
		return resp;
	}

	/*
	 * View data for Family Doctor
	 */

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestPatientDetailsNewModel>> editpatientfamdocnew(String id) {
		logger.info("Method : editpatientfamdocnew starts");

		List<RestPatientDetailsNewModel> getAllemployee = new ArrayList<RestPatientDetailsNewModel>();
		JsonResponse<List<RestPatientDetailsNewModel>> resp = new JsonResponse<List<RestPatientDetailsNewModel>>();

		try {
			BigInteger userId = null;
			if (id != null) {
				userId = new BigInteger(id);
			}
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_edit_patient_famdoc_details")
					.setParameter("userid", userId).getResultList();

			for (Object[] m : x) {

				RestPatientDetailsNewModel reqemp = new RestPatientDetailsNewModel(m[0], m[1], m[2], m[3], m[4], m[5],
						null);
				getAllemployee.add(reqemp);
				System.out.println("family ssss" + getAllemployee);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setBody(getAllemployee);
		System.out.println("editpatientfamdocnew" + resp);
		logger.info("Method : editpatientfamdocnew ends");
		return resp;
	}
	/*
	 * edit emergrncy contact details
	 */

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> editEmergencyContactNew(String id) {
		logger.info("Method : editEmergencyContactNew starts");

		RestPatientDetailsNewModel req = new RestPatientDetailsNewModel();
		JsonResponse<RestPatientDetailsNewModel> resp = new JsonResponse<RestPatientDetailsNewModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_emercontact_edit")
					.setParameter("userid", id).getResultList();
			for (Object[] m : x) {

				RestPatientDetailsNewModel reqemp = new RestPatientDetailsNewModel(m[0], m[1], m[2], m[3], m[4], m[5]);
				req = reqemp;
				System.out.println("req" + reqemp);
			}

			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> response = new ResponseEntity<JsonResponse<RestPatientDetailsNewModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : editEmergencyContactNew ends");
		System.out.println(response);
		return response;
	}

	/*
	 * view Vitals
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> editpatientvitalsignnew(String id) {
		logger.info("Method : editpatientvitalsignnew starts");

		RestPatientDetailsNewModel req = new RestPatientDetailsNewModel();
		JsonResponse<RestPatientDetailsNewModel> resp = new JsonResponse<RestPatientDetailsNewModel>();

		try {

			// Integer id2 = Integer.parseInt(id);
			System.out.println("@@@@" + id);
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_edit_patient_vitalsign_details")
					.setParameter("userid", id).getResultList();

			for (Object[] m : x) {
				String data = null;
				Double data1 = null;
				if (m[9] != null) {
					data = m[9].toString();
				}

				RestPatientDetailsNewModel reqemp = new RestPatientDetailsNewModel(null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, m[0], m[1], m[2], m[3], null, m[4], m[5], m[6], m[7], m[8], null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, data, null, null,
						null, null, null);
				req = reqemp;
			}

			if (req.getTempCs() != null) {
				Double fh = (req.getTempCs() * (9 / 5)) + 32;
				req.setTempFah(fh);

			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> response = new ResponseEntity<JsonResponse<RestPatientDetailsNewModel>>(
				resp, responseHeaders, HttpStatus.CREATED);
		System.out.println("VITAL" + response);
		logger.info("Method : editpatientvitalsignnew ends");
		return response;
	}

	/*
	 * drop down for allergyName
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getallerNamelist() {
		logger.info("Method : getallerNamelist starts");

		List<DropDownModel> alernameList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_allername_drpdwn_details")
					.getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				alernameList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getallerNamelist ends");
		return alernameList;
	}

	/*
	 * drop down for allergyType
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getallertypelist() {
		logger.info("Method : getallertypelist starts");

		List<DropDownModel> alernameList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_allertype_drpdwn_details")
					.getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				alernameList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getallertypelist ends");
		return alernameList;
	}

	/*
	 * save patient allergy
	 * 
	 */

	public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> savedpatallergy(
			RestPatientDetailsNewModel restPatientDetailsNewModel) {
		logger.info("Method : savedpatallergy starts");

		JsonResponse<RestPatientDetailsNewModel> resp = new JsonResponse<RestPatientDetailsNewModel>();
		try {

			if (restPatientDetailsNewModel.getUserId() != null) {

				em.createNamedStoredProcedureQuery("user_patient_details_alergy_add")
						.setParameter("p_id", restPatientDetailsNewModel.getUserId())
						.setParameter("p_alernm", restPatientDetailsNewModel.getAllerNameId())
						.setParameter("p_alertype", restPatientDetailsNewModel.getAlergenTypeId())
						.setParameter("p_alerseverty", restPatientDetailsNewModel.getAllerSeverity())
						.setParameter("p_alerrect", restPatientDetailsNewModel.getAllerRection())
						.setParameter("p_alerupdatedby", restPatientDetailsNewModel.getDrUpdatedby())

						.execute();

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

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> response = new ResponseEntity<JsonResponse<RestPatientDetailsNewModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : savedpatallergy ends");
		return response;
	}

	/*
	 * View Allergy
	 * 
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestPatientDetailsNewModel>> getallergynewview(String id) {
		logger.info("Method : getallergynewview starts");

		List<RestPatientDetailsNewModel> getAllemployee = new ArrayList<RestPatientDetailsNewModel>();
		JsonResponse<List<RestPatientDetailsNewModel>> resp = new JsonResponse<List<RestPatientDetailsNewModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_view_patient_details_alergy")
					.setParameter("userid", id).getResultList();

			for (Object[] m : x) {

				RestPatientDetailsNewModel reqemp = new RestPatientDetailsNewModel(null, m[0], null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, m[1], null, m[2],
						null, m[3], null, m[4], m[5], null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null);
				getAllemployee.add(reqemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setBody(getAllemployee);
		logger.info("Method : getallergynewview ends");
		return resp;
	}

	/*
	 * dropdown for biomedical
	 */
	@SuppressWarnings("unchecked")
	public List<DropDownModel> getbnamelist() {
		logger.info("Method : getallertypelist starts");

		List<DropDownModel> getbnamelist = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_bname_drpdwn_details").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				getbnamelist.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getallertypelist ends");
		return getbnamelist;
	}

	/*
	 * view biomedical
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestPatientDetailsNewModel>> getbiomedicalnewview(String id) {
		logger.info("Method : getbiomedicalnewview starts");

		List<RestPatientDetailsNewModel> getAllemployee = new ArrayList<RestPatientDetailsNewModel>();
		JsonResponse<List<RestPatientDetailsNewModel>> resp = new JsonResponse<List<RestPatientDetailsNewModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_view_patient_details_biomedical")
					.setParameter("userid", id).getResultList();
			for (Object[] m : x) {
				Object DATE = null;
				if (m[3] != null) {
					DATE = DateFormatter.returnStringDate(m[3]);
				}
				RestPatientDetailsNewModel reqemp = new RestPatientDetailsNewModel(null, m[0], null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null,

						null, null, null, null, null, null, null, null, null, m[1], null, m[2], DATE, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null);
				getAllemployee.add(reqemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setBody(getAllemployee);
		logger.info("Method : getbiomedicalnewview ends");
		return resp;
	}

	/*
	 * save biomedical
	 */
	public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> savebiomedical(
			RestPatientDetailsNewModel restPatientDetailsNewModel) {
		logger.info("Method : savebiomedical starts");

		JsonResponse<RestPatientDetailsNewModel> resp = new JsonResponse<RestPatientDetailsNewModel>();
		try {

			if (restPatientDetailsNewModel.getUserId() != null) {

				em.createNamedStoredProcedureQuery("user_patient_details_biomedical_add")
						.setParameter("p_id", restPatientDetailsNewModel.getUserId())
						.setParameter("p_bioname", restPatientDetailsNewModel.getbNameId())
						.setParameter("p_biorection", restPatientDetailsNewModel.getBreson())
						.setParameter("p_imdate", restPatientDetailsNewModel.getImplantDate())

						.execute();

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

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> response = new ResponseEntity<JsonResponse<RestPatientDetailsNewModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : savebiomedical ends");
		return response;
	}

	/*
	 * AutoSearch for Family Details
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestPatientDetailsNewModel>>> getFamilyListAutoSearch(String id) {
		logger.info("Method : getFamilyListAutoSearch starts");
		List<RestPatientDetailsNewModel> itemNameList = new ArrayList<RestPatientDetailsNewModel>();
		JsonResponse<List<RestPatientDetailsNewModel>> resp = new JsonResponse<List<RestPatientDetailsNewModel>>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_autoSearch_profile_familyList_list")
					.setParameter("searchValue", id).getResultList();

			for (Object[] m : x) {

				RestPatientDetailsNewModel reqemp = new RestPatientDetailsNewModel(m[0], m[1], m[2]);
				itemNameList.add(reqemp);
			}
			System.out.println(itemNameList);
			resp.setBody(itemNameList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseEntity<JsonResponse<List<RestPatientDetailsNewModel>>> response = new ResponseEntity<JsonResponse<List<RestPatientDetailsNewModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getFamilyListAutoSearch ends");
		return response;
	}
	/*
	 * save vitals
	 * 
	 */

	public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> addvitalsSign(
			RestPatientDetailsNewModel restPatientDetailsNewModel) {
		logger.info("Method : addvitalsSign starts");

		JsonResponse<RestPatientDetailsNewModel> resp = new JsonResponse<RestPatientDetailsNewModel>();
		try {

			if (restPatientDetailsNewModel.getUserId() == null) {

				em.createNamedStoredProcedureQuery("patient_details_vitals_add")

						.setParameter("actual_value1", restPatientDetailsNewModel.getHeightId())

						.setParameter("actual_value2", restPatientDetailsNewModel.getWeightid())

						.setParameter("actual_value3", restPatientDetailsNewModel.getBmi())

						.setParameter("actual_value4", restPatientDetailsNewModel.getTempCs())

						.setParameter("actual_value5", restPatientDetailsNewModel.getBldpsr())

						.setParameter("actual_value6", restPatientDetailsNewModel.getSysDis())

						.setParameter("actual_value7", restPatientDetailsNewModel.getPulseMin())

						.setParameter("actual_value8", restPatientDetailsNewModel.getRespBpm())

						.setParameter("actual_value9", restPatientDetailsNewModel.getOxygenPer())

						.execute();

			} else {

				em.createNamedStoredProcedureQuery("patient_details_vitals_modify")
						.setParameter("p_id", restPatientDetailsNewModel.getUserId())
						.setParameter("actual_value1", restPatientDetailsNewModel.getHeightId())

						.setParameter("actual_value2", restPatientDetailsNewModel.getWeightid())

						.setParameter("actual_value3", restPatientDetailsNewModel.getBmi())

						.setParameter("actual_value4", restPatientDetailsNewModel.getTempCs())

						.setParameter("actual_value5", restPatientDetailsNewModel.getBldpsr())

						.setParameter("actual_value6", restPatientDetailsNewModel.getSysDis())

						.setParameter("actual_value7", restPatientDetailsNewModel.getPulseMin())

						.setParameter("actual_value8", restPatientDetailsNewModel.getRespBpm())

						.setParameter("actual_value9", restPatientDetailsNewModel.getOxygenPer()).execute();
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

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> response = new ResponseEntity<JsonResponse<RestPatientDetailsNewModel>>(
				resp, responseHeaders, HttpStatus.CREATED);
		System.out.println("REST ADD VITAL SIGN" + response);
		logger.info("Method : addvitalsSign ends");
		return response;
	}

	/*
	 * update Emergency Contact
	 */

	public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> saveemercontactdetails(
			RestPatientDetailsNewModel restPatientDetailsNewModel) {
		logger.info("Method : saveemercontactdetails starts");

		System.out.println("saveemercontactdetails" + restPatientDetailsNewModel);

		JsonResponse<RestPatientDetailsNewModel> resp = new JsonResponse<RestPatientDetailsNewModel>();
		if (restPatientDetailsNewModel.geteId() != null) {
			try {
				if (restPatientDetailsNewModel.getUserId() != null) {
					System.out.println("bt@@@@@" + restPatientDetailsNewModel.geteId());
					em.createNamedStoredProcedureQuery("patient_details_emercontact_update")
							.setParameter("p_pid", restPatientDetailsNewModel.geteId())
							.setParameter("p_puserid", restPatientDetailsNewModel.getUserId())
							.setParameter("p_ename", restPatientDetailsNewModel.geteName())
							.setParameter("p_erelatoion", restPatientDetailsNewModel.geteRelationId())
							.setParameter("p_emobno", restPatientDetailsNewModel.geteMobNo()).execute();

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
		} else {
			try {
				if (restPatientDetailsNewModel.getUserId() != null) {
					System.out.println("bt@@@@@" + restPatientDetailsNewModel.geteId());
					em.createNamedStoredProcedureQuery("patient_details_emercontact_add")
							.setParameter("p_pid", restPatientDetailsNewModel.getUserId())
							.setParameter("p_ename", restPatientDetailsNewModel.geteName())
							.setParameter("p_erelatoion", restPatientDetailsNewModel.geteRelationId())
							.setParameter("p_emobno", restPatientDetailsNewModel.geteMobNo()).execute();

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
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> response = new ResponseEntity<JsonResponse<RestPatientDetailsNewModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : saveemercontactdetails ends");
		System.out.println("response" + response);
		return response;
	}

	/*
	 * drop down for Relation
	 */

	@SuppressWarnings("unchecked")
	public List<DropDownModel> erelationlist() {
		logger.info("Method : erelationlist starts");

		List<DropDownModel> erelationlist = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_emercontact_drpdwn_details")
					.getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				erelationlist.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : erelationlist ends");
		return erelationlist;
	}
	/*
	 * drop down for family doctor
	 */

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getspecialitylist() {
		logger.info("Method : getspecialitylist starts");

		List<DropDownModel> erelationlist = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_famdoc_drpdwn_details").getResultList();

			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				erelationlist.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getspecialitylist ends");
		return erelationlist;
	}

	/*
	 * Save Family Doctor
	 */

	public ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> savefamdocDetails(
			RestPatientDetailsNewModel restPatientDetailsNewModel) {
		logger.info("Method : savefamdocDetails starts");

		JsonResponse<RestPatientDetailsNewModel> resp = new JsonResponse<RestPatientDetailsNewModel>();
		try {

			if (restPatientDetailsNewModel.getUserId() != null) {

				em.createNamedStoredProcedureQuery("patient_details_famdoc_add")
						.setParameter("p_pid", restPatientDetailsNewModel.getUserId())
						.setParameter("p_ename", restPatientDetailsNewModel.getDocName())
						.setParameter("p_especiality", restPatientDetailsNewModel.getDocSpecialityId())
						.setParameter("p_emobno", restPatientDetailsNewModel.getDocMobNo())

						.execute();

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

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");

		ResponseEntity<JsonResponse<RestPatientDetailsNewModel>> response = new ResponseEntity<JsonResponse<RestPatientDetailsNewModel>>(
				resp, responseHeaders, HttpStatus.CREATED);

		logger.info("Method : savefamdocDetails ends");
		return response;
	}

	/*
	 * Save Identification
	 */
	public JsonResponse<Object> addidentidicationsave(RestPatientDetailsNewModel restPatientDetailsNewModel) {
		logger.info("Method : addvitalsSign starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			if (restPatientDetailsNewModel.getUserId() == null) {

				em.createNamedStoredProcedureQuery("patient_details_identification_add")
						.setParameter("p_pid", restPatientDetailsNewModel.getUserId())
						.setParameter("p_pan", restPatientDetailsNewModel.getPancardNo())
						.setParameter("p_pass", restPatientDetailsNewModel.getPassportNo())
						.setParameter("p_aadhra", restPatientDetailsNewModel.getAadharNo())
						.setParameter("p_votr", restPatientDetailsNewModel.getVotarNo())
						.setParameter("p_lNo", restPatientDetailsNewModel.getLicenseNo())
						.setParameter("p_lAuth", restPatientDetailsNewModel.getLicenseAuthority()).execute();
			} else {

				em.createNamedStoredProcedureQuery("patient_details_identification_modify")
						.setParameter("p_pid", restPatientDetailsNewModel.getUserId())
						.setParameter("p_pan", restPatientDetailsNewModel.getPancardNo())
						.setParameter("p_pass", restPatientDetailsNewModel.getPassportNo())
						.setParameter("p_aadhra", restPatientDetailsNewModel.getAadharNo())
						.setParameter("p_votr", restPatientDetailsNewModel.getVotarNo())
						.setParameter("p_lNo", restPatientDetailsNewModel.getLicenseNo())
						.setParameter("p_lAuth", restPatientDetailsNewModel.getLicenseAuthority()).execute();

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

		logger.info("Method : addidentidicationsave ends");
		return resp;
	}

	/*
	 * save Contact Information
	 */
	public JsonResponse<Object> addcontactsave(RestPatientDetailsNewModel restPatientDetailsNewModel) {
		logger.info("Method : addcontactsave starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			if (restPatientDetailsNewModel.getUserId() == null) {

				em.createNamedStoredProcedureQuery("patient_details_contactdtl_add")
						.setParameter("p_pid", restPatientDetailsNewModel.getUserId())
						.setParameter("p_pan", restPatientDetailsNewModel.getHomeAdd())
						.setParameter("p_pass", restPatientDetailsNewModel.getPhNo())
						.setParameter("p_aadhra", restPatientDetailsNewModel.getCemail())

						.setParameter("p_dist", restPatientDetailsNewModel.getDistId())
						.setParameter("p_city", restPatientDetailsNewModel.getCity())

						.execute();
			} else {

				em.createNamedStoredProcedureQuery("patient_details_contact_modify")
				.setParameter("p_pid", restPatientDetailsNewModel.getUserId())
				.setParameter("p_add", restPatientDetailsNewModel.getHomeAdd())
				.setParameter("p_phn", restPatientDetailsNewModel.getPhNo())
				.setParameter("p_pemail", restPatientDetailsNewModel.getCemail())
				.setParameter("p_dist", restPatientDetailsNewModel.getDist())
				.setParameter("p_city", restPatientDetailsNewModel.getCity())
				.setParameter("p_zipcode", restPatientDetailsNewModel.getZipCode())
					
						.execute();

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

		logger.info("Method : addcontactsave ends");
		return resp;
	}

	/*
	 * Update/Edit Education Details
	 */
	public JsonResponse<Object> saveEducationdtl(RestPatientDetailsNewModel restPatientDetailsNewModel) {
		logger.info("Method : saveEducationdtl starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			if (restPatientDetailsNewModel.getUserId() == null) {
				em.createNamedStoredProcedureQuery("user_patient_details_education_add")
						.setParameter("p_pid", restPatientDetailsNewModel.getUserId())
						.setParameter("p_qual", restPatientDetailsNewModel.getQualification())
						.setParameter("p_spl", restPatientDetailsNewModel.getSpecialization())

						.execute();

			} else {

				em.createNamedStoredProcedureQuery("user_patient_details_education_modify")
						.setParameter("p_pid", restPatientDetailsNewModel.getUserId())
						.setParameter("p_qual", restPatientDetailsNewModel.getQualification())
						.setParameter("p_spl", restPatientDetailsNewModel.getSpecialization())

						.execute();

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

		logger.info("Method : saveEducationdtl ends");
		return resp;
	}

	/*
	 * View Education
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestPatientDetailsNewModel>> getEducationView() {
		logger.info("Method : getEducationView starts");

		List<RestPatientDetailsNewModel> getAllemployee = new ArrayList<RestPatientDetailsNewModel>();
		JsonResponse<List<RestPatientDetailsNewModel>> resp = new JsonResponse<List<RestPatientDetailsNewModel>>();

		try {

			// Integer id2 = Integer.parseInt(id);

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_view_patient_details_education")
					.getResultList();

			for (Object[] m : x) {

				RestPatientDetailsNewModel reqemp = new RestPatientDetailsNewModel(null, m[0], null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, m[1], m[2], null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null);

				getAllemployee.add(reqemp);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setBody(getAllemployee);
		logger.info("Method : getEducationView ends");

		return resp;
	}

	/*
	 * Save Family Details
	 */
	@SuppressWarnings("unused")
	public JsonResponse<Object> savefaamilyDetails(String relation, String uhidCardNo, String userId, String slno) {
		logger.info("Method : savefaamilyDetails starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			if (slno == null || slno == "") {
				em.createNamedStoredProcedureQuery("patient_details_familydtl_add").setParameter("p_pid", userId)
						.setParameter("p_famrltn", relation).setParameter("p_famHealthId", uhidCardNo).execute();
			} else {
				em.createNamedStoredProcedureQuery("patient_details_familydtl_update").setParameter("p_slno", slno)
						.setParameter("p_pid", userId).setParameter("p_famrltn", relation)
						.setParameter("p_famHealthId", uhidCardNo).execute();
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

		logger.info("Method : savefaamilyDetails ends");
		return resp;
	}

	/*
	 * view Family Details
	 */
	@SuppressWarnings("unchecked")
	public  JsonResponse<List<RestPatientDetailsNewModel>> getfamilyView(String id) {
		logger.info("Method : getfamilyView starts");

		List<RestPatientDetailsNewModel> getAllemployee = new ArrayList<RestPatientDetailsNewModel>();
		JsonResponse<List<RestPatientDetailsNewModel>> resp = new JsonResponse<List<RestPatientDetailsNewModel>>();

		try {
			//Integer id2 = Integer.parseInt(id);

			
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_view_patient_details_familydts")
					.setParameter("userid", id).getResultList();

					

			for (Object[] m : x) {
				Integer age = null;
				if(m[2]!=null) {
					BigDecimal bd = (BigDecimal) m[2];
					age = bd.intValue();
				}
				RestPatientDetailsNewModel reqemp = new RestPatientDetailsNewModel(m[0],m[1],age,m[3],(Integer.parseInt(m[4].toString())),m[5]);//to call this parameterized construcotr we have to specify type of argument like we did for m{4
				getAllemployee.add(reqemp);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setBody(getAllemployee);
		logger.info("Method : getfamilyView ends");
	
		return resp;
	}
	// Drop down For genderList

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getgenderListnew() {
		logger.info("Method : getgenderListnew starts");

		List<DropDownModel> genderTypeList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_profile_gender_drpdwn").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				genderTypeList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getgenderListnew ends");
		return genderTypeList;
	}

	// Drop down for Blood GroupList

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getbloodgroupListnew() {
		logger.info("Method : getbloodgroupListnew starts");

		List<DropDownModel> bloodgroupList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_bloodGroup_list").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				bloodgroupList.add(dropDownModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getbloodgroupListnew ends");

		return bloodgroupList;
	}
	// Drop Down for MaritialStatus

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getmaritalstatusListnew() {
		logger.info("Method : getmaritalstatusListnew starts");

		List<DropDownModel> maritalstatusList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_profile_maritialsts_drpdwn")
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				maritalstatusList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : getmaritalstatusListnew ends");
		return maritalstatusList;
	}

	/*
	 * Save /Update Personal Information
	 */
	public JsonResponse<Object> addpersonalsave(RestPatientDetailsNewModel restPatientDetailsNewModel) {
		logger.info("Method : addcontactsave starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			if (restPatientDetailsNewModel.getUserId() == null) {

				em.createNamedStoredProcedureQuery("patient_details_personal_add")
						.setParameter("p_pid", restPatientDetailsNewModel.getUserId())
						.setParameter("p_img", restPatientDetailsNewModel.getFileDetails())
						.setParameter("p_fname", restPatientDetailsNewModel.getFirstnameid())
						.setParameter("p_lname", restPatientDetailsNewModel.getLastnameid())
						.setParameter("p_uhidno", restPatientDetailsNewModel.getUhidNo())

						.setParameter("p_bld", restPatientDetailsNewModel.getBloodgroupId())
						.setParameter("p_dob", restPatientDetailsNewModel.getDobid())
						.setParameter("p_age", restPatientDetailsNewModel.getAge())

						.setParameter("p_gen", restPatientDetailsNewModel.getGenderId())
						.setParameter("p_mart", restPatientDetailsNewModel.getMaritalstatusId())
						.setParameter("p_oct", restPatientDetailsNewModel.getOccupation())

						.execute();
			} else {

				em.createNamedStoredProcedureQuery("patient_details_personal_modify")
						.setParameter("p_pid", restPatientDetailsNewModel.getUserId())
						.setParameter("p_img", restPatientDetailsNewModel.getFileDetails())
						.setParameter("p_fname", restPatientDetailsNewModel.getFirstnameid())
						.setParameter("p_lname", restPatientDetailsNewModel.getLastnameid())

						.setParameter("p_bld", restPatientDetailsNewModel.getBloodgroupId())
						.setParameter("p_dob", restPatientDetailsNewModel.getDobid())
						.setParameter("p_age", restPatientDetailsNewModel.getAge())

						.setParameter("p_gen", restPatientDetailsNewModel.getGenderId())
						.setParameter("p_mart", restPatientDetailsNewModel.getMaritalstatusId())
						.setParameter("p_oct", restPatientDetailsNewModel.getOccupation())

						.execute();

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

		logger.info("Method : addpersonalsave ends");
		return resp;
	}

	/*******************
	 * * DELETE FAMILY DETAILS
	 **************************************************/

	public JsonResponse<Object> deleteFamilyMemberDao(String slno) {
		logger.info("Method : deleteFamilyMemberDao starts");
		JsonResponse<Object> resp = new JsonResponse<Object>();
		System.out.println(slno);
		try {
			em.createNamedStoredProcedureQuery("patient_details_familydtl_delete").setParameter("slno", slno).execute();
		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);

				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		logger.info("Method : deleteFamilyMemberDao ends");
		return resp;
	}

	/*******************
	 * * DELETE EMERGENCY CONTACT
	 ***********************************************/
	public JsonResponse<Object> deleteEmrgncyContactDao(String slno) {
		logger.info("Method : deleteEmrgncyContactDao starts");

		System.out.println(slno);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			em.createNamedStoredProcedureQuery("patient_details_emercontact_delete").setParameter("slno", slno)
					.execute();
		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);

				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		logger.info("Method : deleteEmrgncyContactDao ends");
		return resp;
	}

	/*******************
	 * * DELETE Family Doctor
	 ***********************************************/
	public JsonResponse<Object> deleteFamilyDoctorDao(String slno) {
		logger.info("Method : deleteFamilyDoctorDao starts");

		System.out.println(slno);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			em.createNamedStoredProcedureQuery("patient_details_familydoctor_delete").setParameter("slno", slno)
					.execute();
		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);

				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		logger.info("Method : deleteFamilyDoctorDao ends");
		return resp;
	}
	// City list

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getCityNewListDao(String distId) {

		logger.info("Method : getCityNewListDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_city_list").setParameter("districtId", distId)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString());
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getCityNewListDao ends");
		return resp;
	}

	// District list

	@SuppressWarnings("unchecked")
	public JsonResponse<List<DropDownModel>> getDistNewListDao(String state) {

		logger.info("Method : getDistNewListDao starts");
		List<DropDownModel> stateList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_district_list").setParameter("stateId", state)
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1].toString());
				stateList.add(dropDownModel);
			}

			resp.setBody(stateList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getDistNewListDao ends");
		return resp;
	}

	@SuppressWarnings("unchecked")
	public List<DropDownModel> getPateintPw(String id) {
		logger.info("Method : getPateintPw starts");
		System.out.println("id" + id);
		List<DropDownModel> alernameList = new ArrayList<DropDownModel>();

		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("doctor_user_password_by_id").setParameter("id", id)
					.getResultList();

			for (Object[] m : x) {
				BigInteger b = (BigInteger) m[0];
				String s1 = b.toString();

				DropDownModel dropDownModel = new DropDownModel(s1, m[1]);
				alernameList.add(dropDownModel);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getPateintPw ends");
		return alernameList;
	}
	
	/*
	 * For Edit Doctor contact details Edit
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<RestPatientDetailsNewModel> editcontactDetailsAddress(String id) {
		logger.info("Method : editcontactDetailsAddress starts");

		RestPatientDetailsNewModel req = new RestPatientDetailsNewModel();
		JsonResponse<RestPatientDetailsNewModel> resp = new JsonResponse<RestPatientDetailsNewModel>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_edit_contact_deatils")
					.setParameter("patid", id).getResultList();

			for (Object[] m : x) {
				RestPatientDetailsNewModel reqEdit = new RestPatientDetailsNewModel(m[0],m[1],m[2],m[3],m[4],m[5],m[6],m[7],m[8],m[9],m[10],m[11]);

				req = reqEdit;

			}
			resp.setBody(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
			System.out.println("@@@@@@@@@@@@@@@@@@@@"+resp);
		logger.info("Method : editcontactDetailsAddress ends");

		return resp;
	}


}
