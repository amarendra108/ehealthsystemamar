package nirmalya.aatithya.restmodule.patient.dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.dao.CheckDuplicateDao;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.model.RestDoctorPrescriptionDetailsModel;
import nirmalya.aatithya.restmodule.patient.model.RestMedicationCemistModel;
import nirmalya.aatithya.restmodule.patient.model.RestPatientMedicationModel;
import nirmalya.aatithya.restmodule.patient.model.RestPatientMedicationRequestedMdcnModel;
import nirmalya.aatithya.restmodule.patient.model.RestRequestMedcineModel;
import nirmalya.aatithya.restmodule.patient.model.RestRequestTestModel;

@Repository
public class PatientMedicationDao {
	Logger logger = LoggerFactory.getLogger(PatientMedicationDao.class);

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;
	
	@Autowired
	CheckDuplicateDao checkDuplicateDao;// used to get casepaperno

	/*
	 * View current medicine Details
	 */

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestPatientMedicationModel>> viewCurrentMedicationDao(String uId) {

		logger.info("Method in Dao: viewCurrentMedicationDao starts");

		List<RestPatientMedicationModel> medicineList = new ArrayList<RestPatientMedicationModel>();
		System.out.println("user :"+uId);
		try {
			BigInteger userId = null;
			if (uId != null) {
				userId = new BigInteger(uId);
			}
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_medicine_details")
					.setParameter("userId", userId).getResultList();
			System.out.println("user :"+x.toString());
			for (Object[] m : x) {

				Object fromDt = null;
				Object toDate = null;

				if (m[7] != null) {
					fromDt = DateFormatter.returnStringDate(m[7]);
				}
				if (m[8] != null) {

					toDate = DateFormatter.returnStringDate(m[8]);
				}
				RestPatientMedicationModel medicineModel = new RestPatientMedicationModel(m[0], m[1], m[2], m[3], m[4],
						m[5], m[6], fromDt, toDate,m[9],m[10],m[11]);
				medicineList.add(medicineModel);
				System.out.println("$$$$$$$$$$$"+medicineModel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JsonResponse<List<RestPatientMedicationModel>> resp = new JsonResponse<List<RestPatientMedicationModel>>();
		resp.setBody(medicineList);
		System.out.println("response :"+resp);
		logger.info("Method in Dao: viewCurrentMedicationDao ends");
		return resp;
	}

	/*
	 * View current doctor Details
	 * 
	 */
	@SuppressWarnings("unchecked")

	public JsonResponse<List<RestPatientMedicationModel>> viewCurrentDrDetailsDao(String uId) {

		logger.info("Method in Dao: viewCurrentDrDetailsDao starts");

		List<RestPatientMedicationModel> doctorDetails = new ArrayList<RestPatientMedicationModel>();

		try {
			BigInteger userId = null;
			if (uId != null) {
				userId = new BigInteger(uId);
			}
			List<Object[]> x = em.createNamedStoredProcedureQuery("current_doctor_details")
					.setParameter("userId", userId).getResultList();
			for (Object[] m : x) {

				RestPatientMedicationModel drDetails = new RestPatientMedicationModel(m[0], m[1], m[2]);
				doctorDetails.add(drDetails);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		String s1 = "Date : N/A";
		if (doctorDetails != null && !doctorDetails.get(0).getPriscriptionDate().equals(s1)) {
			for (RestPatientMedicationModel a : doctorDetails) {
				List<RestDoctorPrescriptionDetailsModel> doctorPriscription = new ArrayList<RestDoctorPrescriptionDetailsModel>();
				try {
					BigInteger userId1 = null;
					if (uId != null) {
						userId1 = new BigInteger(uId);
					}
					
					List<Object[]> x1 = em.createNamedStoredProcedureQuery("current_doctor_prescription_details")
							.setParameter("userId", userId1).setParameter("dr_id", a.getDoctorId()).setParameter("prescr_date",a.getPriscriptionDate()).getResultList();
					for (Object[] m : x1) {

						RestDoctorPrescriptionDetailsModel dctrPriscription = new RestDoctorPrescriptionDetailsModel(
								m[0], m[1], m[2], m[3], m[4], m[5], m[6],m[7]);
						doctorPriscription.add(dctrPriscription);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				a.setPrescriptionList(doctorPriscription);
			}
		}

		JsonResponse<List<RestPatientMedicationModel>> resp = new JsonResponse<List<RestPatientMedicationModel>>();
		resp.setBody(doctorDetails);
		logger.info("Method in Dao: viewCurrentDrDetailsDao ends");
		return resp;
	}
	 /*************************************** Rest start function for agGrid to get chemist list Dao ******************************/
	public List<RestMedicationCemistModel> veiwChemistForMedicineDao(String place) {
		// TODO Auto-generated method stub
		logger.info("Method in Dao: veiwChemistForMedicineDao ends");
		List<RestMedicationCemistModel> res = new ArrayList<RestMedicationCemistModel>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_get_chemist_list").setParameter("p_place", place).getResultList();
			for(Object[] m : x) {
				RestMedicationCemistModel list = new RestMedicationCemistModel(m[0], m[1], m[2], m[3]);
				res.add(list);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		logger.info("Method in Dao: veiwChemistForMedicineDao ends");
		return res;
	}
	public static String generateRandom(int length) {
		Random random = new Random();
		char[] digits = new char[length];
		digits[0] = (char) (random.nextInt(9) + '1');
		for (int i = 1; i < length; i++) {
			digits[i] = (char) (random.nextInt(10) + '0');
		}
		return new String(digits);
	}
	/************************************** Dao function to request mediccine from chemist**************************************/
	public JsonResponse<RestRequestMedcineModel> addRequestMedicineDao(RestRequestMedcineModel requestMedcineModel) {
		logger.info("Method in Dao: addRequestMedicineDao starts");
		JsonResponse<RestRequestMedcineModel> resp = new JsonResponse<RestRequestMedcineModel>();
		
		/************************************** Generate parameter Starts **************************************/
		//String orderid = "ORDR" + generateRandom(12);
		long nowTime = new Date().getTime();
		String orderid = "ORDR" + generateRandom(5)  + nowTime;
		String s = "";
		String[] s1 = (requestMedcineModel.getMedicineids().split(","));//splits all medicine id
		String[] srl_no = requestMedcineModel.getSrlno().split(",");//splits all slrno 
		String[] sr_no = requestMedcineModel.getSrno().split(",");//splits all srno
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String p_casepaper = checkDuplicateDao.getCasePaperNumber(requestMedcineModel.getPatientId());
		for(int i=0; i <s1.length;i++) {
			s = s+"("+srl_no[i]+","+sr_no[i]+","+requestMedcineModel.getChemistid()+",'"+p_casepaper+"',"+s1[i]+",'"+requestMedcineModel.getPatientNote()+"',"+
		0+","+1+",'"+sdf3.format(timestamp)+"','"+orderid+"'),";// here we use \",\" because in database we have to split this string
		}
			if (s != "") {
				s = s.substring(0, s.length() - 1);//to reduce" "," " from end 
			}
			/************************************** Generate parameter ends **************************************/
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_chemist_request_add")
					.setParameter("p_values", s).getResultList();
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
		logger.info("Method in Dao: addRequestMedicineDao starts");

		return resp;
	}

	public List<RestPatientMedicationRequestedMdcnModel> viewRequestMdcnDao(String userId) {
		// TODO Auto-generated method stub
		logger.info("Method in Dao: viewRequestMdcnDao ends");
		String p_casepaper = checkDuplicateDao.getCasePaperNumber(userId);
		List<RestPatientMedicationRequestedMdcnModel> res = new ArrayList<RestPatientMedicationRequestedMdcnModel>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_get_request_mdcn_list").setParameter("userid", p_casepaper	).getResultList();
			for(Object[] m : x) {
				RestPatientMedicationRequestedMdcnModel list = new RestPatientMedicationRequestedMdcnModel(m[0], m[1], m[2], m[3],m[4],m[5]);
				res.add(list);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}//System.out.println("aedeacfw dfafewfdewfc sdea "+res);
		logger.info("Method in Dao: viewRequestMdcnDao ends");
		return res;
	}
	/*
	 * View Test Details(Medication)
	 */
	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestPatientMedicationModel>> viewTestDetailsPatientDao(String uId) {

		logger.info("Method in Dao: viewTestDetailsPatientDao starts");

		List<RestPatientMedicationModel> testList = new ArrayList<RestPatientMedicationModel>();
		try {
			BigInteger userid = null;
			if (uId != null) {
				userid = new BigInteger(uId);
			}
			//System.out.println(userid);
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_medication_test_view")
					.setParameter("userid", userid).getResultList();

			for (Object[] m : x) {

				Object testDt = null;

				if (m[3] != null) {
					testDt = DateFormatter.returnStringDate(m[3]);
				}
				//System.out.println("@Helo  : "+testList);
				RestPatientMedicationModel testModel = new RestPatientMedicationModel(m[0], m[1], m[2], testDt, m[4],m[5],m[6],m[7]);
				testList.add(testModel);

			}
		//	System.out.println("@Helo  : "+testList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		JsonResponse<List<RestPatientMedicationModel>> resp = new JsonResponse<List<RestPatientMedicationModel>>();
		resp.setBody(testList);
		logger.info("Method in Dao: viewTestDetailsPatientDao ends");
		return resp;
	}

	 /************************************** Rest start function for agGrid to get labList list Dao *****************************/
	public List<RestMedicationCemistModel> veiwLabListDao(String place) {
		// TODO Auto-generated method stub
		logger.info("Method in Dao: veiwLabListDao ends");
		//System.out.println(place);
		List<RestMedicationCemistModel> res = new ArrayList<RestMedicationCemistModel>();
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_get_Lab_list").setParameter("p_place", place).getResultList();
			for(Object[] m : x) {
				RestMedicationCemistModel list = new RestMedicationCemistModel(m[0], m[1], m[2], m[3]);
				res.add(list);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		logger.info("Method in Dao: veiwLabListDao  ends");
		return res;
	}
	/************************************** Dao function to request mediccine from chemist**************************************/
	public JsonResponse<RestRequestTestModel> addRequestTestDao(RestRequestTestModel requestTestModel) {
		logger.info("Method in Dao: addRequestTestDao starts");
		JsonResponse<RestRequestTestModel> resp = new JsonResponse<RestRequestTestModel>();
		
		/************************************* Generate parameter Starts *************************************/
		//String orderid = "ORDR" + generateRandom(12);
		long nowTime = new Date().getTime();
		String orderid = "ORDR" + generateRandom(5)  + nowTime;
		String s = "";
		String[] s1 = (requestTestModel.getTestGroupName().split(","));//splits all lab test group ""testGroupName":"Blood Pressure,Sugar,Hemoglobin"" to get the length
		String[] test_name = (requestTestModel.getTestName().split(","));
		String[] srl_no = requestTestModel.getSrlno().split(",");//splits all slrno 
		String[] sr_no = requestTestModel.getSrno().split(",");//splits all srno
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		for(int i=0; i <s1.length;i++) {
			s = s+"("+srl_no[i]+","+sr_no[i]+",'"+s1[i]+"','"+test_name[i]+"','"+requestTestModel.getPatientId()+"',"+requestTestModel.getLabid()+","+0+","+1+",'"+requestTestModel.getPatientNote()+"',"
					+ "'"+sdf3.format(timestamp)+"','"+orderid+"'),";// here we use " "," " because we have to split this string for insrt query
		}
			if (s != "") {
				s = s.substring(0, s.length() - 1);//to reduce" "," " from end 
			}
			//System.out.println(s);
			/************************************* Generate parameter ends *************************************/
		try {
			@SuppressWarnings("unchecked")
			List<Object[]> x = em.createNamedStoredProcedureQuery("patient_lab_request_add")
					.setParameter("p_values", s).getResultList();
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
		logger.info("Method in Dao: addRequestTestDao starts");

		return resp;
	}

}
