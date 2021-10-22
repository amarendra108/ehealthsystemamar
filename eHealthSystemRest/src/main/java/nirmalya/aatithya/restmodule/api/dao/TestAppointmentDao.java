package nirmalya.aatithya.restmodule.api.dao;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.model.AppointmentModel;
import nirmalya.aatithya.restmodule.api.model.MedTelModel;
import nirmalya.aatithya.restmodule.api.model.SearchModel;
import nirmalya.aatithya.restmodule.api.model.TestGroupModel;
import nirmalya.aatithya.restmodule.api.model.TestModel;
import nirmalya.aatithya.restmodule.api.model.TestReportAPIModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class TestAppointmentDao {

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	PasswordEncoder passEncoder;

	@Autowired
	EnvironmentVaribles env;

	Logger logger = LoggerFactory.getLogger(TestAppointmentDao.class);

	public ResponseEntity<JsonResponse<Object>> screeningReportByMedTel(MedTelModel data) {
		logger.info("Method : screeningReportByMedTel DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		if (data != null) {
			if (data.getScreening_details().size() > 0) {
				JSONArray jsArray = new JSONArray(data.getScreening_details());
				for (int i = 0; i < jsArray.length(); i++) {
					JSONObject jsonObj = null;
					try {
						jsonObj = jsArray.getJSONObject(i);
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JSONObject m = null;
					try {
						m = jsonObj.getJSONObject("pocResult");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String k = (String) jsonObj.keys().next();

					Iterator<?> keys = m.keys();

					while (keys.hasNext()) {
						String key = (String) keys.next();

						// System.out.println("Info, Key: " + k + ", value: " + jsonObj.getString(k) + "
						// * " + key + "---" + m.get(key) + " ///// ");
						
						try {
							if(jsonObj.getString(k) != null && jsonObj.getString(k) != "" && m.get(key) != null && m.get(key) != "") {
								try {

									@SuppressWarnings("unchecked")
									List<Object[]> x = em.createNamedStoredProcedureQuery("saveMedTelApiData")
											.setParameter("regNo", data.getPatient_uniqueid())
											.setParameter("medTelId", data.getMedteluniqueid())
											.setParameter("thpId", data.getThp_id()).setParameter("thpName", data.getThp_name())
											.setParameter("pName", data.getName()).setParameter("pMobile", data.getMobile())
											.setParameter("pGender", data.getGender()).setParameter("pAge", data.getAge())
											.setParameter("screeningDate", data.getScreening_date())
											.setParameter("reportUrl", data.getReport_url())
											.setParameter("testGroup", jsonObj.getString(k)).setParameter("testName", key)
											.setParameter("testResult", m.get(key)).getResultList();
									jsonResponse.setBody(x.get(0));
									jsonResponse.setCode("success");
									jsonResponse.setMessage("Data Saved Successfully");
								} catch (Exception e) {
									e.printStackTrace();
									jsonResponse.setCode("failed");
									jsonResponse.setMessage("Something went wrong.");
								}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : screeningReportByMedTel DAO ends");
		return response;
	}
	
	public ResponseEntity<JsonResponse<Object>> manualScreeningReportByMedTel(MedTelModel data) {
		logger.info("Method : manualScreeningReportByMedTel DAO starts");
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		
		if (data != null) {
			if (data.getScreening_details().size() > 0) {
				JSONArray jsArray = new JSONArray(data.getScreening_details());
				for (int i = 0; i < jsArray.length(); i++) {
					JSONObject jsonObj = null;
					try {
						jsonObj = jsArray.getJSONObject(i);
					} catch (JSONException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					JSONObject m = null;
					try {
						m = jsonObj.getJSONObject("pocResult");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String k = (String) jsonObj.keys().next();
					
					Iterator<?> keys = m.keys();
					
					while (keys.hasNext()) {
						String key = (String) keys.next();
						
						// System.out.println("Info, Key: " + k + ", value: " + jsonObj.getString(k) + "
						// * " + key + "---" + m.get(key) + " ///// ");
						
						try {
							if(jsonObj.getString(k) != null && jsonObj.getString(k) != "" && m.get(key) != null && m.get(key) != "") {
								try {
									
									@SuppressWarnings("unchecked")
									List<Object[]> x = em.createNamedStoredProcedureQuery("saveMedTelApiData")
									.setParameter("regNo", data.getPatient_uniqueid())
									.setParameter("medTelId", data.getMedteluniqueid())
									.setParameter("thpId", data.getThp_id()).setParameter("thpName", data.getThp_name())
									.setParameter("pName", data.getName()).setParameter("pMobile", data.getMobile())
									.setParameter("pGender", data.getGender()).setParameter("pAge", data.getAge())
									.setParameter("screeningDate", data.getScreening_date())
									.setParameter("reportUrl", data.getReport_url())
									.setParameter("testGroup", jsonObj.getString(k)).setParameter("testName", key)
									.setParameter("testResult", m.get(key)).getResultList();
									jsonResponse.setBody(x.get(0));
									jsonResponse.setCode("success");
									jsonResponse.setMessage("Data Saved Successfully");
								} catch (Exception e) {
									e.printStackTrace();
									jsonResponse.setCode("failed");
									jsonResponse.setMessage("Something went wrong.");
								}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
			}
		}
		
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		
		logger.info("Method : manualScreeningReportByMedTel DAO ends");
		return response;
	}
	
	public ResponseEntity<JsonResponse<Object>> systemScreeningReport(MedTelModel data) {
		logger.info("Method : systemScreeningReport DAO starts");
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		if (data != null) {
			if (data.getScreening_details().size() > 0) {
				JSONArray jsArray = new JSONArray(data.getScreening_details());
				for (int i = 0; i < jsArray.length(); i++) {
					JSONObject jsonObj = null;
					try {
						jsonObj = jsArray.getJSONObject(i);
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					String testId = null;
					try {
						testId = jsonObj.getString("Test ID");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//					String testName = jsonObj.getString("TestName");
					String testResult = null;
					try {
						testResult = jsonObj.getString("result");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String unit = null;
					try {
						unit = jsonObj.getString("Unit");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String sampleNo = null;
					try {
						sampleNo = jsonObj.getString("sample number");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String refRange = null;
					try {
						refRange = jsonObj.getString("Reference range");
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {

						@SuppressWarnings("unchecked")
						List<Object[]> x = em.createNamedStoredProcedureQuery("saveOtherSystemAPIData")
								.setParameter("regNo", data.getUniqueid())
								.setParameter("pName", data.getName()).setParameter("pMobile", data.getMobile())
								.setParameter("screeningDate", data.getScreening_date())
								.setParameter("testResult", testResult).setParameter("testUnit", unit)
								.setParameter("testId", testId).setParameter("refRange", refRange)
								.setParameter("sampleNo", sampleNo)
								.getResultList();
						jsonResponse.setBody(x.get(0));
						jsonResponse.setCode("success");
						jsonResponse.setMessage("Data Saved Successfully");
					} catch (Exception e) {
						e.printStackTrace();
						jsonResponse.setCode("failed");
						jsonResponse.setMessage("Something went wrong.");
					}
					
					
				}
			}
		}
		
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		
		logger.info("Method : systemScreeningReport DAO ends");
		return response;
	}
	
	public ResponseEntity<JsonResponse<Object>> saveTestReport(TestReportAPIModel data) {
		logger.info("Method : saveTestReport DAO starts");
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		if (data != null) {
			if (data.getScreentestreportlist().size() > 0) {
				
				String sdate = null;
				String cdate = null;
				
				sdate = DateFormatter.getStringDateNew(data.getScreentestdate());
				cdate = DateFormatter.getStringDateNew(data.getCreateddate());
				
				for (int i = 0; i < data.getScreentestreportlist().size(); i++) {
					
					try {
						
						@SuppressWarnings("unchecked")
						List<Object[]> x = em.createNamedStoredProcedureQuery("save_screen_test_report_data")
						.setParameter("uniqueid", data.getUniqueid())
						.setParameter("name", data.getName())
						.setParameter("mobile", data.getMobile())
						.setParameter("aadhaar", data.getAadhaar())
						.setParameter("gender", data.getGender())
						.setParameter("age", data.getAge())
						.setParameter("address", data.getAddress())
						.setParameter("phc", data.getPhc())
						.setParameter("screentestdate", sdate)
						.setParameter("createddate", cdate)
						.setParameter("testgroup", data.getScreentestreportlist().get(i).getTestgroup())
						.setParameter("testname", data.getScreentestreportlist().get(i).getTestname())
						.setParameter("testresult", data.getScreentestreportlist().get(i).getTestresult())
						.setParameter("unit", data.getScreentestreportlist().get(i).getUnit())
						.setParameter("fromrange", data.getScreentestreportlist().get(i).getFromrange())
						.setParameter("torange", data.getScreentestreportlist().get(i).getTorange())
						.setParameter("refrange", data.getScreentestreportlist().get(i).getRefrange())
						.setParameter("status", data.getScreentestreportlist().get(i).getStatus())
						.getResultList();
						jsonResponse.setBody(x.get(0));
						jsonResponse.setCode("success");
						jsonResponse.setMessage("Data Saved Successfully");
					} catch (Exception e) {
						e.printStackTrace();
						jsonResponse.setCode("failed");
						jsonResponse.setMessage("Something went wrong.");
					}
					
					
				}
			}
		}
		
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		
		logger.info("Method : systemScreeningReport DAO ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<SearchModel>>> getDataByPatId(String regNo) {
		logger.info("Method : getDataByPatId Dao starts");
		
		System.out.println(regNo);

		List<SearchModel> countryList = new ArrayList<SearchModel>();
		JsonResponse<List<SearchModel>> jsonResponse = new JsonResponse<List<SearchModel>>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("searchByPatientId").setParameter("regNo", regNo)
					.getResultList();
			for (Object[] m : x) {

				String gender = null;

				if (m[4] != null) {
					if (m[4].toString().contentEquals("1")) {
						gender = "Male";
					} else if (m[4].toString().contentEquals("2")) {
						gender = "Female";
					} else {
						gender = "Others";
					}
				}

				SearchModel dropDownModel = new SearchModel(m[0], m[1], m[2], m[3], gender);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);

			if (countryList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<SearchModel>>> response = new ResponseEntity<JsonResponse<List<SearchModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : getDataByPatId Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> screenTestEntry(TestModel data) {
		logger.info("Method : screenTestEntry DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (data.getRegNo() == null || data.getRegNo() == "") {
			jsonResponse.setMessage("Registration Number Required");
			validity = false;
		} else if (data.getAppontdt() == null || data.getAppontdt() == "") {
			jsonResponse.setMessage("Appointment Date Required");
			validity = false;
		} else if (data.getApponttime() == null || data.getApponttime() == "") {
			jsonResponse.setMessage("Appointment Time Required");
			validity = false;
		}

		if (validity) {
			try {
				String date = null;
				if (data.getAppontdt() != null && data.getAppontdt() != "") {
					date = DateFormatter.getStringDateNew(data.getAppontdt());
				}

				List<Object[]> x = em.createNamedStoredProcedureQuery("screeningTestAppointment")
						.setParameter("regNo", data.getRegNo()).setParameter("testDate", date)
						.setParameter("testTime", data.getApponttime()).getResultList();
				jsonResponse.setBody(x.get(0));
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Appointment Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong. Appointment failed.");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : screenTestEntry DAO ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> screenCheckUpEntry(TestModel data) {
		logger.info("Method : screenCheckUpEntry DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (data.getRegNo() == null || data.getRegNo() == "") {
			jsonResponse.setMessage("Registration Number Required");
			validity = false;
		} else if (data.getAppontdt() == null || data.getAppontdt() == "") {
			jsonResponse.setMessage("Appointment Date Required");
			validity = false;
		} else if (data.getApponttime() == null || data.getApponttime() == "") {
			jsonResponse.setMessage("Appointment Time Required");
			validity = false;
		}

		if (validity) {
			try {
				String date = null;
				if (data.getAppontdt() != null && data.getAppontdt() != "") {
					date = DateFormatter.getStringDateNew(data.getAppontdt());
				}

				List<Object[]> x = em.createNamedStoredProcedureQuery("screeningChkUpAppointment")
						.setParameter("regNo", data.getRegNo()).setParameter("testDate", date)
						.setParameter("testTime", data.getApponttime()).getResultList();
				jsonResponse.setBody(x.get(0));
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Appointment Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong. Appointment failed.");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : screenCheckUpEntry DAO ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<AppointmentModel>>> getScreeningTestListAppointmentByDate(
			String appointDate) {
		logger.info("Method : getScreeningTestListAppointmentByDate Dao starts");

		List<AppointmentModel> countryList = new ArrayList<AppointmentModel>();
		JsonResponse<List<AppointmentModel>> jsonResponse = new JsonResponse<List<AppointmentModel>>();
		try {
			
			if(appointDate != null && appointDate != "") {
				appointDate = DateFormatter.getStringDateNew(appointDate);
			}
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("appointmentScreenTestListData")
					.setParameter("appointDate", appointDate).getResultList();
			for (Object[] m : x) {

				String gender = null;
				if (m[4] != null) {
					if (m[4].toString().contentEquals("1")) {
						gender = "Male";
					} else if (m[4].toString().contentEquals("2")) {
						gender = "Female";
					} else {
						gender = "Others";
					}
				}

				String appStatusName = null;
				if (m[8] != null) {
					if (m[8].toString().contentEquals("0")) {
						appStatusName = "Booked";
					} else if (m[8].toString().contentEquals("1")) {
						appStatusName = "In Progress";
					} else {
						appStatusName = "Completed";
					}
				}

				AppointmentModel dropDownModel = new AppointmentModel(m[0], m[1], m[2], m[3], gender, m[5], m[6], m[7],
						m[8], appStatusName,null);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);

			if (countryList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<AppointmentModel>>> response = new ResponseEntity<JsonResponse<List<AppointmentModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : getScreeningTestListAppointmentByDate Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<AppointmentModel>>> getScreeningCheckUpListAppointmentByDate(
			String appointDate) {
		logger.info("Method : getScreeningCheckUpListAppointmentByDate Dao starts");

		List<AppointmentModel> countryList = new ArrayList<AppointmentModel>();
		JsonResponse<List<AppointmentModel>> jsonResponse = new JsonResponse<List<AppointmentModel>>();
		try {
			
			if(appointDate != null && appointDate != "") {
				appointDate = DateFormatter.getStringDateNew(appointDate);
			}
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("appointmentScreenChkUpListData")
					.setParameter("appointDate", appointDate).getResultList();
			for (Object[] m : x) {

				String gender = null;
				if (m[4] != null) {
					if (m[4].toString().contentEquals("1")) {
						gender = "Male";
					} else if (m[4].toString().contentEquals("2")) {
						gender = "Female";
					} else {
						gender = "Others";
					}
				}

				String appStatusName = null;
				if (m[8] != null) {
					if (m[8].toString().contentEquals("0")) {
						appStatusName = "Booked";
					} else if (m[8].toString().contentEquals("1")) {
						appStatusName = "In Progress";
					} else {
						appStatusName = "Completed";
					}
				}

				AppointmentModel dropDownModel = new AppointmentModel(m[0], m[1], m[2], m[3], gender, m[5], m[6], m[7],
						m[8], appStatusName,null);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);

			if (countryList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<AppointmentModel>>> response = new ResponseEntity<JsonResponse<List<AppointmentModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : getScreeningCheckUpListAppointmentByDate Dao ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> changeScreentTestStatus(TestModel data) {
		logger.info("Method : changeScreentTestStatus DAO starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (data.getId() == null || data.getId() == "") {
			jsonResponse.setMessage("Screening Test ID Required");
			validity = false;
		} else if (data.getAppontstatus() == null || data.getAppontstatus() == "") {
			jsonResponse.setMessage("Appointment Status Required");
			validity = false;
		}

		if (validity) {
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("changeScreeningTestStatus")
						.setParameter("screenTestId", data.getId()).setParameter("screenTestStatus", data.getAppontstatus())
						.getResultList();
				jsonResponse.setBody(x.get(0));
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Status Changed Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong.");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : changeScreentTestStatus DAO ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> changeCheckUpStatus(TestModel data) {
		logger.info("Method : changeCheckUpStatus DAO starts");
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		
		Boolean validity = true;
		if (data.getId() == null || data.getId() == "") {
			jsonResponse.setMessage("Health Check Up ID Required");
			validity = false;
		} else if (data.getAppontstatus() == null || data.getAppontstatus() == "") {
			jsonResponse.setMessage("Appointment Status Required");
			validity = false;
		}
		
		if (validity) {
			try {
				
				List<Object[]> x = em.createNamedStoredProcedureQuery("changeCheckUpStatus")
						.setParameter("screenTestId", data.getId()).setParameter("screenTestStatus", data.getAppontstatus())
						.getResultList();
				jsonResponse.setBody(x.get(0));
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Status Changed Successfully");
			} catch (Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong.");
			}
		}
		
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		
		logger.info("Method : changeCheckUpStatus DAO ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<MedTelModel>>> viewMedTeltestListdata(String page,String search) {
		logger.info("Method : viewMedTeltestListdata Dao starts");

		List<MedTelModel> countryList = new ArrayList<MedTelModel>();
		JsonResponse<List<MedTelModel>> jsonResponse = new JsonResponse<List<MedTelModel>>();
		try {
			Integer pageno = Integer.parseInt(page);
			System.out.println(pageno);
			List<Object[]> x = em.createNamedStoredProcedureQuery("viewMedTeltestList")
					.setParameter("start", pageno)
					.setParameter("search", search)
					.getResultList();
			for (Object[] m : x) {
				
				System.out.println(m[7]);
				
				String url = null;
				if(m[8] != null) {
					byte[] encodeId = Base64.getEncoder().encode(m[8].toString().getBytes());
					String id = new String(encodeId);
					url = "https://ehealthsystem.com/user/view-patient-test-report-pdf-download?id="+id+"&id2="+m[7].toString();
//					url = "http://ehealthsystem.com/user/view-patient-test-report-pdf-download?id="+id;
				}

				MedTelModel dropDownModel = new MedTelModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7],
						m[8], url);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);

			if (countryList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<MedTelModel>>> response = new ResponseEntity<JsonResponse<List<MedTelModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : viewMedTeltestListdata Dao ends");
		return response;
	}
	
	/*
	 * (through user id)
	 */
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<MedTelModel>>> viewMedTeltestListdataId(String regno) {
		logger.info("Method : viewMedTeltestListdataId Dao starts");

		List<MedTelModel> countryList = new ArrayList<MedTelModel>();
		JsonResponse<List<MedTelModel>> jsonResponse = new JsonResponse<List<MedTelModel>>();
		try {
			//String regno1="9121448674403477" ;
			List<Object[]> x = em.createNamedStoredProcedureQuery("viewMedTeltestListId").setParameter("regno", regno)
					.getResultList();
			for (Object[] m : x) {
				String url = null;
				if(m[8] != null) {
					byte[] encodeId = Base64.getEncoder().encode(m[8].toString().getBytes());
					String id = new String(encodeId);
					url = "https://ehealthsystem.com/user/view-patient-test-report-pdf-download?id="+id+"&id2="+m[7].toString();
//					url = "http://ehealthsystem.com/user/view-patient-test-report-pdf-download?id="+id;
				}

				MedTelModel dropDownModel = new MedTelModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6], m[7],
						m[8], url);
				countryList.add(dropDownModel); 
			}
			jsonResponse.setBody(countryList);

			if (countryList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<MedTelModel>>> response = new ResponseEntity<JsonResponse<List<MedTelModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : viewMedTeltestListdataId Dao ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> viewMedTelTestDetails(String medtelid) {
		logger.info("Method : viewMedTelTestDetails Dao starts");
		
		List<TestGroupModel> countryList = new ArrayList<TestGroupModel>();
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		try {
			List<Object[]> x = em.createNamedStoredProcedureQuery("viewMedTeltestDetails")
					.setParameter("medteluniqueid", medtelid)
					.getResultList();
			for (Object[] m : x) {
				TestGroupModel dropDownModel = new TestGroupModel(m[0], m[1], m[2]);
				countryList.add(dropDownModel);
			}
			List<String> groupList = new ArrayList<String>();
			for(TestGroupModel m: countryList) {
				groupList.add(m.getTestgroup());
			}
			HashSet<String> hset = new HashSet<String>(groupList);
			System.out.println(hset);
			
			HashMap<String, HashMap<String, String>> map = new HashMap<>();
			
			for(String m : hset) {
				HashMap<String, String> map1 = new HashMap<>();
				for(TestGroupModel a : countryList) {
					if(m.contentEquals(a.getTestgroup())) {
						map1.put(a.getTestname(), a.getTestresult());
					}
				}
				map.put(m, map1);
			}
			
			System.out.println(map);
			jsonResponse.setBody(map);
			if (countryList.size() > 0) {
				jsonResponse.setCode("success"); 
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Data not found");
			}
			
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : viewMedTelTestDetails Dao ends");
		return response;
	}
}
