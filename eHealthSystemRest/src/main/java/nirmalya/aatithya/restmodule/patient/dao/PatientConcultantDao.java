package nirmalya.aatithya.restmodule.patient.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.patient.model.RestConsultantTimeInterval;
import nirmalya.aatithya.restmodule.patient.model.RestDoctorProfileModel;
import nirmalya.aatithya.restmodule.patient.model.RestConsultantGetDoctorModel;
import nirmalya.aatithya.restmodule.patient.model.RestPatientConsultantRestModel;
import nirmalya.aatithya.restmodule.patient.model.RestViewPatientConsultantModel;

/**
 * @author Nirmalya Labs
 *
 */
@Repository
public class PatientConcultantDao {
	Logger logger = LoggerFactory.getLogger(PatientConcultantDao.class);
	@Autowired
	EntityManager em;
	@Autowired
	ServerDao serverDao;

	@SuppressWarnings({"unchecked" })
	public JsonResponse<Object> addPatientConsultantDao(RestPatientConsultantRestModel patientConsultantRestModel,String userid) {
		logger.info("Method : addPatientConsultantDao starts");
//		System.out.println("######" + patientConsultantRestModel);
//		System.out.println("######" + userid);
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_consultant_add")
					.setParameter("patientid", userid)
					.setParameter("p_datefrom", patientConsultantRestModel.getFormDate())
					.setParameter("p_timefrom", patientConsultantRestModel.getFormTime())
					.setParameter("p_doctor", patientConsultantRestModel.getDoctor())
					.setParameter("p_notes", patientConsultantRestModel.getNotes()).getResultList();
		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				resp.setCode(err[0]);
				resp.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
//		System.out.println("ADD DAO" + resp);
		logger.info("Method : addPatientConsultantDao ends");
		return resp;
	}
//
//	/**
//	 * 
//	 * 
//	 * DAO Function toget Opd Id
//	 * 
//	 *
//	 */

	// 1dao function to view all data this month

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestViewPatientConsultantModel>> getAllconsolation(String userid) {
		logger.info("Method : All getAllconsolation Dao starts");

		List<RestViewPatientConsultantModel> getAllemployee = new ArrayList<RestViewPatientConsultantModel>();
		JsonResponse<List<RestViewPatientConsultantModel>> resp = new JsonResponse<List<RestViewPatientConsultantModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_consultationView")
					.setParameter("userid", userid).getResultList();

			for (Object[] m : x) {
				Object DATE = null;
				if (m[0] != null) {
					DATE = m[0].toString();
				}
				Object time = null;
				if (m[1] != null) {
					time = m[1].toString();
				}

				RestViewPatientConsultantModel viewdemo = new RestViewPatientConsultantModel(DATE, time, m[2], m[3],
						m[4], m[5], m[6]);
				getAllemployee.add(viewdemo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setBody(getAllemployee);
		logger.info("Method : getAllconsolation Dao ends");

		return resp;

	}
	// 1dao function to view all data excluding this month

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestViewPatientConsultantModel>> viewconsultantMorethanMonth(String userid) {
		logger.info("Method : All viewconsultantMorethanMonth Dao starts");

		List<RestViewPatientConsultantModel> getAllemployee = new ArrayList<RestViewPatientConsultantModel>();
		JsonResponse<List<RestViewPatientConsultantModel>> resp = new JsonResponse<List<RestViewPatientConsultantModel>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_view_exclude_month").setParameter("userid", userid).getResultList();

			for (Object[] m : x) {
				Object DATE = null;
				if (m[0] != null) {
					DATE = m[0].toString();
				}
				Object time = null;
				if (m[1] != null) {
					time = m[1].toString();
				}

				RestViewPatientConsultantModel viewdemo = new RestViewPatientConsultantModel(DATE, time, m[2], m[3],
						m[4], m[5],m[6]);
				getAllemployee.add(viewdemo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setBody(getAllemployee);
		logger.info("Method : viewconsultantMorethanMonth Dao ends");

		return resp;

	}
	// 1dao function to view all doctor

	@SuppressWarnings("unchecked")
	public JsonResponse<List<RestConsultantGetDoctorModel>> getDoctorList(String id) {
		logger.info("Method : All getDoctorList Dao starts");
System.out.println("Doctor List :"+id);
		List<RestConsultantGetDoctorModel> getAllemployee = new ArrayList<RestConsultantGetDoctorModel>();
		JsonResponse<List<RestConsultantGetDoctorModel>> resp = new JsonResponse<List<RestConsultantGetDoctorModel>>();

		try {
			System.out.println("searchvalue"+id);
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_consultant_doctorlist")
					.setParameter("searchvalue", id).getResultList();
			

			for (Object[] m : x) {

				RestConsultantGetDoctorModel viewdemo = new RestConsultantGetDoctorModel(m[0], m[1], m[2], m[3], m[4],
						m[5]);
				getAllemployee.add(viewdemo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("getDoctorList : " + getAllemployee);
		resp.setBody(getAllemployee);
		logger.info("Method : getDoctorList Dao ends");

		return resp;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<RestConsultantTimeInterval>>> getDoctorAvaible(String doctorid) {
		logger.info("Method : getDoctorAvaible Dao starts");
//		System.out.println(doctorid);
		List<RestConsultantTimeInterval> datetime = new ArrayList<RestConsultantTimeInterval>();
		JsonResponse<List<RestConsultantTimeInterval>> resp = new JsonResponse<List<RestConsultantTimeInterval>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_doctor_date_time")
					.setParameter("doctorid", doctorid).getResultList();
			for (Object[] m : x) {
				RestConsultantTimeInterval dropDownModel = new RestConsultantTimeInterval(m[0].toString(),
						m[1].toString());
				datetime.add(dropDownModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(datetime);
		for (RestConsultantTimeInterval m : datetime) {
			List<DropDownModel> testnames = new ArrayList<DropDownModel>();
			// to get time interval

			String[] arr1 = m.getTime().split("-");// splits the time which has conated by -
			String[] time1 = arr1[0].split(":");// splits that stat time 08:00:00
			String[] time2 = arr1[1].split(":");// splits that stat time 20:00:00

			System.out.println(time1[0]);
			int timestart = Integer.parseInt(time1[0]);// get hour in integer "8" from 08:00:00
			int timeend = Integer.parseInt(time2[0]);// get hour int integer "20" from 20:00:00
			System.out.println(timestart+" " +m+" "+timeend);
			for (int i = timestart; i <= timeend; i++) {
				String o1 = "";
				String s1 = timestart + "";
				String s2 = timestart + 1+"";
				
				if (s1.length() == 1) {
					o1 = "0" + s1 + ":00";
				} else {
					o1 = s1 + ":00";
				}
				String o2 = "";
				if (s2.length() == 1) {
					o2 = "0" + s2+ ":00";
				} else {
					o2 = s2+ ":00";
				}
				String o3 = o1.concat(o2);
				String o4 = o3.replaceAll(":", "");
//					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//					Date date1 = sdf.parse(o1);
//					Date date2 = sdf.parse(o2);
//					DateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
				DropDownModel dctrPriscription = new DropDownModel(o1.toString(), o2.toString(),o4);
				testnames.add(dctrPriscription);
				
				timestart++;
			}
			m.setTimeinterval(testnames);

		}
		System.out.println(datetime);
		resp.setBody(datetime);
		ResponseEntity<JsonResponse<List<RestConsultantTimeInterval>>> response = new ResponseEntity<JsonResponse<List<RestConsultantTimeInterval>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getDoctorAvaible Dao ends");
		return response;
	}
	//get all time slot for booking appointment
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getAllTimeSlot() {
		logger.info("Method : getAllTimeSlot Dao starts");
		List<DropDownModel> datetime = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_consultant_all_time_slot").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),
						m[1].toString());
				datetime.add(dropDownModel);
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
		resp.setBody(datetime);
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getAllTimeSlot Dao ends");
		return response;
	}
	//get  time slot for booking appointment
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getTimeSlot(String time, String date,String doctorid) {
		logger.info("Method : getTimeSlot Dao starts");
		List<DropDownModel> datetime = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> resp = new JsonResponse<List<DropDownModel>>();
		try {
			String[] arr1 = time.split("-");
			String time1 = date+" "+arr1[0]+":00";
			String time2 = date+" "+arr1[1]+":00";
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_time_interval_list")
					.setParameter("doctorid", doctorid)
					.setParameter("bookdate", date)
					.setParameter("fromtime", time1)
					.setParameter("totime", time2).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),
						m[1].toString());
				datetime.add(dropDownModel);
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
//		System.out.println(datetime);
		resp.setBody(datetime);
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				resp, HttpStatus.CREATED);
		logger.info("Method : getTimeSlot Dao ends");
		return response;
	}
	/********************** Dao function for view doctor profile***********************/
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<RestDoctorProfileModel>> getDoctorProfile(String id) {
		logger.info("Method : getDoctorProfile Dao start");
		// TODO Auto-generated method stub
		RestDoctorProfileModel viewProfile =new  RestDoctorProfileModel();
		JsonResponse<RestDoctorProfileModel> res = new JsonResponse<RestDoctorProfileModel>();
		try {
		List<Object[]> x = em.createNamedStoredProcedureQuery("view_dcotor_consultant_profile")
				.setParameter("useraccount", id).getResultList();
		
		for(Object[] m : x) {
			RestDoctorProfileModel profile = new RestDoctorProfileModel(m[0], m[1], m[2], m[3], m[4], m[5],null, m[6]);
			viewProfile = profile;
			System.out.println(profile);
		}
		res.setBody(viewProfile);
		} catch (Exception e) {
			try {
				String[] err = serverDao.errorProcedureCall(e);
				res.setCode(err[0]);
				res.setMessage(err[1]);

			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		ResponseEntity<JsonResponse<RestDoctorProfileModel>> response = new ResponseEntity<JsonResponse<RestDoctorProfileModel>>(
				res, HttpStatus.CREATED);
		logger.info("Method : getDoctorProfile Dao ends");
		return response;
	}

	public ResponseEntity<JsonResponse<Object>> saveRatingDao(String ratingid, String doctorid,
			String patientid) {

		logger.info("Method : saveRatingDao Dao starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			
			System.out.println(doctorid+" "+patientid+" "+ratingid);
			 em.createNamedStoredProcedureQuery("user_consultant_set_rating")
					.setParameter("doctorid",doctorid)
					.setParameter("patientid", patientid)
					.setParameter("ratingid", ratingid).execute();
			
			} catch (Exception e) {
				try {
					String[] err = serverDao.errorProcedureCall(e);
					res.setCode(err[0]);
					res.setMessage(err[1]);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(
				res, HttpStatus.CREATED);
		logger.info("Method : saveRatingDao Dao ends");
		return response;
	}
}
