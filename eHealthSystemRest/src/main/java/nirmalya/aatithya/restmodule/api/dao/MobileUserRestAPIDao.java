package nirmalya.aatithya.restmodule.api.dao;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.model.APIAllergyModel;
import nirmalya.aatithya.restmodule.api.model.APIBioMedicalModel;
import nirmalya.aatithya.restmodule.api.model.APIMedicationModel;
import nirmalya.aatithya.restmodule.api.model.APIPharmacyModel;
import nirmalya.aatithya.restmodule.api.model.APIVitalSignModel;
import nirmalya.aatithya.restmodule.api.model.AppointmentListModel;
import nirmalya.aatithya.restmodule.api.model.AppointmentModel;
import nirmalya.aatithya.restmodule.api.model.CountryModel;
import nirmalya.aatithya.restmodule.api.model.DoctorAppointmentModel;
import nirmalya.aatithya.restmodule.api.model.EmergencyContactAPIModel;
import nirmalya.aatithya.restmodule.api.model.IsEContactFDoctorValidModel;
import nirmalya.aatithya.restmodule.api.model.SearchDetailsModel;
import nirmalya.aatithya.restmodule.api.model.SignUpModel;
import nirmalya.aatithya.restmodule.api.model.UserProfileAPIModel;
import nirmalya.aatithya.restmodule.common.CommonUsed;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class MobileUserRestAPIDao {

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	PasswordEncoder passEncoder;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	Logger logger = LoggerFactory.getLogger(MobileUserRestAPIDao.class);

	public static String generateRandom(int length) { 
		Random random = new Random();
		char[] digits = new char[length];
		digits[0] = (char) (random.nextInt(9) + '1');
		for (int i = 1; i < length; i++) {
			digits[i] = (char) (random.nextInt(10) + '0');
		}
		return new String(digits);
	}

	public String saveAllImage(byte[] imageBytes, String ext, String pId) {
		logger.info("Method : saveAllImage starts");

		String imageName = null;

		try {

			if (imageBytes != null) {
				long nowTime = new Date().getTime();
				if (ext.contentEquals("jpeg")) {
					imageName = nowTime + ".jpg";
				} else {
					imageName = pId + "_" + nowTime + "." + ext;
				}

			}

			Path path = Paths.get(env.getFileUploadProfile() + imageName);
			if (imageBytes != null) {

				if (pId != null && pId != "") {
					Files.write(path, imageBytes);

					ByteArrayInputStream in = new ByteArrayInputStream(imageBytes);
					Integer height = 50;
					Integer width = 50;

					try {
						BufferedImage img = ImageIO.read(in);
						if (height == 0) {
							height = (width * img.getHeight()) / img.getWidth();
						}
						if (width == 0) {
							width = (height * img.getWidth()) / img.getHeight();
						}

						BufferedImage outputImage = new BufferedImage(width, height, img.getType());

						Graphics2D g2d = outputImage.createGraphics();
						g2d.drawImage(img, 0, 0, width, height, null);
						g2d.dispose();
						String outputImagePath = env.getFileUploadProfile() + "thumb/" + imageName;
						ImageIO.write(outputImage, ext, new File(outputImagePath));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : saveAllImage ends");
		return imageName;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> userSelfRegistrationApi(SignUpModel data) {
		logger.info("Method : userSelfRegistrationApi Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (data.getMobile() == null || data.getMobile() == "") {
			jsonResponse.setMessage("Mobile Number Required");
			validity = false;
		} else if (data.getfName() == null || data.getfName() == "") {
			jsonResponse.setMessage("First Name Required");
			validity = false;
		} else if (data.getlName() == null || data.getlName() == "") {
			jsonResponse.setMessage("Last Name Required");
			validity = false;
		} else if (data.getAge() == null && data.getDob() == null) {
			jsonResponse.setMessage("Age/DOB Required");
			validity = false;
		} else if (data.getCountry() == null) {
			jsonResponse.setMessage("Country Required");
			validity = false;
		} else if (data.getState() == null) {
			jsonResponse.setMessage("State Required");
			validity = false;
		} else if (data.getDistrictid() == null) {
			jsonResponse.setMessage("District Required");
			validity = false;
		} else if (data.getCityid() == null) {
			jsonResponse.setMessage("City Required");
			validity = false;
		}

		Boolean isMobileExist = false;

		Boolean isAadharExist = false;

		if (!isMobileExist && !isAadharExist) {
			if (data.getfName() != null && data.getfName() != "") {
				String noSpaceStr = data.getfName().replaceAll("\\s", "");
				String substr = noSpaceStr.substring(0, 3);
				substr = substr.toUpperCase();
				String random = generateRandom(5);
				data.setUserId(substr.concat(random));
			}
			String pId = null;
			if (data.getId() == null || data.getId() == "") {
				String ccode = "";
				String scode = "";
				if (data.getCountryCode() != null && data.getCountryCode() != "") {
					ccode = data.getCountryCode();
					ccode = ccode.substring(0, 2);
					if (data.getStateCode() != null && data.getStateCode() != "") {
						scode = data.getStateCode();
					} else {
						scode = "50";
					}
				} else {
					ccode = "10";
					scode = "50";
				}

				String random = generateRandom(12);
				pId = ccode.concat(scode).concat(random);
			}

			String image = "";
			if (data.getProfileImage().size() > 0) {

				if (data.getProfileImage().get(0) != null && data.getProfileImage().get(0) != "") {
					try {
						byte[] bytes = Base64.getDecoder().decode(data.getProfileImage().get(0));
						String imageName = saveAllImage(bytes, data.getProfileImageType(), pId);
						data.setProfileImageName(imageName);
						image = imageName;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			String gender = "";
			if (data.getGender() != null) {
				gender = data.getGender().toString();
			}

			String title = null;
			if (data.getTitle() != null) {
				title = data.getTitle().toString();
			}

			String city = "";
			if (data.getCityid() != null) {
				city = data.getCityid().toString();
			}

			String dist = "";
			if (data.getDistrictid() != null) {
				dist = data.getDistrictid().toString();
			}

			String state = "";
			if (data.getState() != null) {
				state = data.getState().toString();
			}

			String country = "";
			if (data.getCountry() != null) {
				country = data.getCountry().toString();
			}

			String ageyears = "";
			if (data.getAgeYears() != null && data.getAgeYears() != "") {
				ageyears = data.getAgeYears();
			}

			String patDOB = "";
			if (data.getDob() != null && data.getDob() != "") {
				patDOB = DateFormatter.getStringDateNew(data.getDob());
			}
			String age = "";
			if (data.getAge() != null) {
				age = data.getAge().toString();
			}

			String qrcodename = "QR" + pId + ".png";

			if (validity) {
				try {
					if (data.getId() == null || data.getId() == "") {

						List<Object[]> x = em.createNamedStoredProcedureQuery("userSelfRegistrationAPI")
								.setParameter("regid", pId).setParameter("userid", data.getUserId())
								.setParameter("patname", data.getfName()).setParameter("patlname", data.getlName())
								.setParameter("patmobile", data.getMobile()).setParameter("profileimage", image)
								.setParameter("patage", age).setParameter("ageyears", ageyears)
								.setParameter("patdob", patDOB).setParameter("patgender", gender)
								.setParameter("state", state).setParameter("country", country)
								.setParameter("password",
										"$2a$10$SkYOZORZ4PUSURzL1fmvk.RcUwCfLk/R826sxBXKx/ZZyoQvkcaa6")
								.setParameter("enteredby", pId).setParameter("ttl", title).setParameter("dist", dist)
								.setParameter("city", city).setParameter("qrcode", qrcodename).getResultList();

						jsonResponse.setBody(x.get(0));

					}

					jsonResponse.setCode("success");
					jsonResponse.setMessage("User Registered Successfully");

					String msg = "Welcome to eHealthSystem. You are registered successfully! Your UserId is " + pId
							+ " or " + data.getMobile() + " and password is User@123 ";

					String encodemsg = URLEncoder.encode(msg, "UTF-8");

					CommonUsed.sendSMS(data.getMobile(), encodemsg);

					CommonUsed.generateQRCode(qrcodename, pId, data.getMobile(), data.getfName(), env.getUserQrCode());
				} catch (Exception e) {
					e.printStackTrace();
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("Something went wrong. Registration failed.");
				}
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : userSelfRegistrationApi Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> updateUserProfileAPI(UserProfileAPIModel data) {
		logger.info("Method : updateUserProfileAPI Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
//		if (data.getfName() == null || data.getfName() == "") {
//			jsonResponse.setMessage("First Name Required");
//			validity = false;
//		} else if (data.getlName() == null || data.getlName() == "") {
//			jsonResponse.setMessage("Last Name Required");
//			validity = false;
//		} else 
		if (data.getDob() == null || data.getDob() == "") {
			jsonResponse.setMessage("Date Of Birth Required");
			validity = false;
		}

		Boolean isMobileExist = false;
		System.out.println(data);
		if (!isMobileExist) {

			System.out.println(data);

			String bgroup = null;
			if (data.getBloodGroup() != null) {
				bgroup = data.getBloodGroup().toString();
			}

			String ename = "";
			if (data.geteName() != null) {
				ename = data.geteName().toString();
			}

			String relation = "";
			if (data.geteRelation() != null) {
				relation = data.geteRelation().toString();
			}

			String emobile = "";
			if (data.geteMobile() != null) {
				emobile = data.geteMobile().toString();
			}

			String fdocname = "";
			if (data.getfDoctor() != null) {
				fdocname = data.getfDoctor().toString();
			}

			String speciality = "";
			if (data.getSpeciality() != null) {
				speciality = data.getSpeciality().toString();
			}

			String docnumber = "";
			if (data.getDocMobile() != null) {
				docnumber = data.getDocMobile().toString();
			}

			String patDOB = "";
			if (data.getDob() != null && data.getDob() != "") {
				patDOB = DateFormatter.getStringDateNew(data.getDob());
			}

//			String image = "";
//			if (data.getpImage().size() > 0) {
//
//				if (data.getpImage().get(0) != null && data.getpImage().get(0) != "") {
//					try {
//						byte[] bytes = Base64.getDecoder().decode(data.getpImage().get(0));
//						String imageName = saveAllImage(bytes, data.getProfileImageType(), data.geteCardNo());
//						data.setProfileImageName(imageName);
//						image = imageName;
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}

			if (validity) {
				try {

					System.out.println(data.geteCardNo());
					if (data.geteCardNo() != null && data.geteCardNo() != "") {

						System.out.println(data.geteCardNo());
						System.out.println(patDOB);
						System.out.println(bgroup);
						System.out.println(ename);
						System.out.println(relation);
						System.out.println(emobile);
						System.out.println(fdocname);
						System.out.println(speciality);
						System.out.println(docnumber);

						List<Object[]> x = em.createNamedStoredProcedureQuery("updateUserProfile")
								.setParameter("regid", data.geteCardNo()).setParameter("patdob", patDOB)
								.setParameter("bloodgroup", bgroup)
								.setParameter("ename", ename).setParameter("relation", relation)
								.setParameter("emobile", emobile).setParameter("fdocname", fdocname)
								.setParameter("speciality", speciality).setParameter("docnumber", docnumber)
								.getResultList();

						jsonResponse.setBody(x.get(0));

					}

					jsonResponse.setCode("success");
					jsonResponse.setMessage("User Profile Updated Successfully");
				} catch (Exception e) {
					e.printStackTrace();
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("Something went wrong. Updation failed.");
				}
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : updateUserProfileAPI Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> updateUserProfileImageAPI(UserProfileAPIModel data) {
		logger.info("Method : updateUserProfileImageAPI Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;

		System.out.println(data);

		String image = "";
		if (data.getpImage().size() > 0) {

			if (data.getpImage().get(0) != null && data.getpImage().get(0) != "") {
				try {
					byte[] bytes = Base64.getDecoder().decode(data.getpImage().get(0));
					String imageName = saveAllImage(bytes, data.getProfileImageType(), data.geteCardNo());
					data.setProfileImageName(imageName);
					image = imageName;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (validity) {
			try {

				System.out.println(data.geteCardNo());
				if (data.geteCardNo() != null && data.geteCardNo() != "") {

					System.out.println(data.geteCardNo());

					List<Object[]> x = em.createNamedStoredProcedureQuery("update_profile_image_api")
							.setParameter("regid", data.geteCardNo()).setParameter("profileimage", image)
							.getResultList();

					jsonResponse.setBody(x.get(0));

				}

				jsonResponse.setCode("success");
				jsonResponse.setMessage("User Profile Updated Successfully");
			} catch (Exception e) {
				e.printStackTrace();

				String filePath = env.getFileUploadEmployee() + image;

				File file = new File(filePath);

				if (file.delete()) {
					System.out.println("File deleted.");
				} else {
					System.out.println("File not deleted.");
				}

				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong. Updation failed.");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : updateUserProfileImageAPI Dao ends");
		return response;
	}

	public ResponseEntity<JsonResponse<IsEContactFDoctorValidModel>> userDashboard(String userid) {
		logger.info("Method : getPatientDetailsRestApi Dao starts");

		IsEContactFDoctorValidModel data = new IsEContactFDoctorValidModel();
		JsonResponse<IsEContactFDoctorValidModel> jsonResponse = new JsonResponse<IsEContactFDoctorValidModel>();

		Boolean isEContactAdded = false;
		Boolean isFDoctorAdded = false;

		isEContactAdded = checkDuplicateDao.isEContactAdded(userid);

		isFDoctorAdded = checkDuplicateDao.isFDoctorAdded(userid);

		data.setIsEContactAdded(isEContactAdded);
		data.setIsFDoctorAdded(isFDoctorAdded);

		jsonResponse.setBody(data);

		jsonResponse.setCode("success");
		jsonResponse.setMessage("Data Fetched Successfully.");

		ResponseEntity<JsonResponse<IsEContactFDoctorValidModel>> response = new ResponseEntity<JsonResponse<IsEContactFDoctorValidModel>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : userDashboard Dao ends");
		System.out.println(response);
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> doctorAvailableByDate(String docid, String date) {
		logger.info("Method : doctorAvailableByDate Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();
		List<Map<String, Object>> jsonArray = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> jsonArray1 = new ArrayList<Map<String, Object>>();

		if (date != null && date != "") {
			date = DateFormatter.getStringDateNew(date);
		}
		try {
			System.out.println(docid + " * " + date);
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_time_slot_api").setParameter("doctorid", docid)
					.setParameter("date", date).getResultList();
			if (x.size() > 0) {
				for (Object[] m : x) {
					Map<String, Object> ob = new HashMap<String, Object>();
					String data = null;
					if (m[0] != null) {
						String d = m[0].toString();
						String arr[] = d.split(":");
						data = arr[0].concat(":").concat(arr[1]);
					}
					ob.put("name", data); // Time
					ob.put("key", m[1]); // Booking Status
					ob.put("code", m[2]); // OPD Id
					jsonArray.add(ob);
				}
				if (x.get(0)[2] != null) {
					obj.put("timelist", jsonArray);
				} else {
					obj.put("timelist", jsonArray1);
				}
				obj.put("status", "success");
				obj.put("message", "Data fetched successfully");
			} else {
				obj.put("status", "failed");
				obj.put("message", "Data not found");
			}

		} catch (Exception e) {
			e.printStackTrace();
			obj.put("status", "failed");
			obj.put("message", "Data not found");
		}
		System.out.println(obj);
		logger.info("Method : doctorAvailableByDate Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
//	@SuppressWarnings("unchecked")
//	public ResponseEntity<JsonResponse<Object>> doctorAvailableByDate(String userid, String date) {
//		logger.info("Method : doctorAvailableByDate Dao starts");
//		
//		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
//		List<CountryModel> ddList = new ArrayList<CountryModel>();
//		Boolean validity = true;
//		if (date == null || date == "") {
//			jsonResponse.setMessage("Appointment Date Required");
//			validity = false;
//		}
//		String prevDate = date;
//		
//		if (validity) {
//			try {
//				
//				if (date != null && date != "") {
//					date = DateFormatter.getStringDateNew(date);
//				}
//				List<Object[]> x = em.createNamedStoredProcedureQuery("doctorAvailableByDate")
//						.setParameter("doctor", userid).setParameter("date", date).getResultList();
//				
//				for (Object[] m : x) {
//					CountryModel dd = new CountryModel(m[0], m[1], m[2]);
//					ddList.add(dd);
//				}
//				
//				if (ddList.size() > 0) {
//					HashMap<String, String> map = new HashMap<>();
//					map.put("fromTime", DateFormatter.getStringTimeMS(ddList.get(0).getKey()));
//					map.put("toTime", DateFormatter.getStringTimeMS(ddList.get(0).getName()));
//					map.put("opdId", ddList.get(0).getCode());
//					jsonResponse.setBody(map);
//					jsonResponse.setCode("success");
//					jsonResponse.setMessage("Doctor is available on " + prevDate + ".");
//				} else {
//					jsonResponse.setCode("failed");
//					jsonResponse.setMessage("Doctor is not available on " + prevDate + ".");
//				}
//				
//			} catch (Exception e) {
//				jsonResponse.setCode("failed");
//				jsonResponse.setMessage("Doctor is not available on " + prevDate + ".");
//			}
//		}
//		
//		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
//				HttpStatus.OK);
//		
//		logger.info("Method : doctorAvailableByDate Dao ends");
//		System.out.println(response);
//		return response;
//	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> doctorAvailableByTime(String userid, String date, String time) {
		logger.info("Method : doctorAvailableByTime Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		List<CountryModel> ddList = new ArrayList<CountryModel>();
		Boolean validity = true;
		if (date == null || date == "") {
			jsonResponse.setMessage("Appointment Date Required");
			validity = false;
		} else if (time == null || time == "") {
			jsonResponse.setMessage("Appointment Time Required");
			validity = false;
		}
		String prevDate = date;

		if (validity) {
			try {

				if (date != null && date != "") {
					date = DateFormatter.getStringDateNew(date);
				}
				List<Object[]> x = em.createNamedStoredProcedureQuery("doctorAvailableByTime")
						.setParameter("doctor", userid).setParameter("date", date).setParameter("time", time)
						.getResultList();

				for (Object[] m : x) {
					CountryModel dd = new CountryModel(m[0], m[1], m[2]);
					ddList.add(dd);
				}

				if (ddList.size() > 0) {
					HashMap<String, String> map = new HashMap<>();
					map.put("fromTime", DateFormatter.getStringTimeMS(ddList.get(0).getKey()));
					map.put("toTime", DateFormatter.getStringTimeMS(ddList.get(0).getName()));
					map.put("opdId", ddList.get(0).getCode());
					jsonResponse.setBody(map);
					jsonResponse.setCode("success");
					jsonResponse.setMessage("Doctor is available on " + prevDate + " " + time + ".");
				} else {
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("Doctor is not available on " + prevDate + " " + time + ".");
				}

			} catch (Exception e) {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Doctor is not available on " + prevDate + " " + time + ".");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : doctorAvailableByTime Dao ends");
		System.out.println(response);
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> postDoctorAppointment(DoctorAppointmentModel data) {
		logger.info("Method : postDoctorAppointment Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (data.getDate() == null || data.getDate() == "") {
			jsonResponse.setMessage("Appointment Date Required");
			validity = false;
		} else if (data.getTime() == null || data.getTime() == "") {
			jsonResponse.setMessage("Appointment Time Required");
			validity = false;
		} else if (data.getOpdid() == null || data.getOpdid() == "") {
			jsonResponse.setMessage("OPD ID Required");
			validity = false;
		} else if (data.getDoctor() == null || data.getDoctor() == "") {
			jsonResponse.setMessage("Doctor Required");
			validity = false;
		} else if (data.getHospitalid() == null || data.getHospitalid() == "") {
			jsonResponse.setMessage("Hospital Required");
			validity = false;
		}
		System.out.println(data.getUserid());
		String casepaper = checkDuplicateDao.getCasePaperNumber(data.getUserid());
		System.out.println(casepaper);
		Boolean isMobileExist = false;

		if (!isMobileExist) {

			String notes = "";
			if (data.getNotes() != null) {
				notes = data.getNotes();
			}

			String hospital = "";
			if (data.getHospitalid() != null) {
				hospital = data.getHospitalid().toString();
			}

			String doctor = "";
			if (data.getDoctor() != null) {
				doctor = data.getDoctor().toString();
			}

			String casep = "";
			if (casepaper != null) {
				casep = casepaper.toString();
			}

			String opdid = "";
			Integer opddata = 0;
			if (data.getOpdid() != null) {
				opdid = data.getOpdid().toString();
				opddata = Integer.parseInt(opdid);
			}

			String date = "";
			if (data.getDate() != null) {
				date = DateFormatter.getStringDateNew(data.getDate());
			}

			if (validity) {
				try {
					if (data.getUserid() != null || data.getUserid() != "") {

						System.out.println(date);
						System.out.println(casep);
						System.out.println(opdid);
						System.out.println(data.getTime());
						System.out.println(data.getNotes());
						System.out.println(hospital);
						System.out.println(doctor);

						List<Object[]> x = em.createNamedStoredProcedureQuery("post_doctor_appointment")
								.setParameter("p_datefrom", date).setParameter("p_caspaper", casep)
								.setParameter("p_opdid", opddata).setParameter("p_timefrom", data.getTime())
								.setParameter("p_doctor", doctor).setParameter("p_notes", notes)
								.setParameter("p_hospitalname", hospital).getResultList();

						jsonResponse.setBody(x.get(0));

					}

					jsonResponse.setCode("success");
					jsonResponse.setMessage("Appointment Booked Successfully");
				} catch (Exception e) {
					e.printStackTrace();
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("Something went wrong.");
				}
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : postDoctorAppointment Dao ends");
		return response;
	}

//	@SuppressWarnings("unchecked")
//	public ResponseEntity<JsonResponse<List<AppointmentListModel>>> getUserAppointmentList(String userid) {
//		logger.info("Method : getUserAppointmentList Dao starts");
//
//		List<AppointmentListModel> countryList = new ArrayList<AppointmentListModel>();
//		JsonResponse<List<AppointmentListModel>> jsonResponse = new JsonResponse<List<AppointmentListModel>>();
//		try {
//
//			List<Object[]> x = em.createNamedStoredProcedureQuery("get_user_appointment_list")
//					.setParameter("userid", userid).getResultList();
//			for (Object[] m : x) {
//
//				String appstatus = null;
//				if (m[5] != null) {
//					if (m[5].toString().contentEquals("7")) {
//						appstatus = "Requested";
//					}
//					if (m[5].toString().contentEquals("2")) {
//						appstatus = "Confirmed";
//					}
//					if (m[5].toString().contentEquals("4")) {
//						appstatus = "Cancelled";
//					}
//					if (m[5].toString().contentEquals("5")) {
//						appstatus = "Treated";
//					}
//				}
//				Object appdate = null;
//				if (m[2] != null) {
//					appdate = DateFormatter.returnStringDateNew(m[2].toString());
//				}
//
//				AppointmentListModel dropDownModel = new AppointmentListModel(userid, m[0], m[1], appdate, m[3], m[4],
//						appstatus, m[6], null);
//				countryList.add(dropDownModel);
//			}
//			jsonResponse.setBody(countryList);
//
//			if (countryList.size() > 0) {
//				jsonResponse.setCode("success");
//				jsonResponse.setMessage("Data Fetched Successfully");
//			} else {
//				jsonResponse.setCode("failed");
//				jsonResponse.setMessage("No Data Found");
//			}
//
//		} catch (Exception e) {
//			jsonResponse.setCode("failed");
//			jsonResponse.setMessage(e.getMessage());
//		}
//		ResponseEntity<JsonResponse<List<AppointmentListModel>>> response = new ResponseEntity<JsonResponse<List<AppointmentListModel>>>(
//				jsonResponse, HttpStatus.OK);
//
//		logger.info("Method : getUserAppointmentList Dao ends");
//		return response;
//	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<AppointmentListModel>>> getUserAppointmentList(String userid, String status,
			String date) {
		logger.info("Method : getUserAppointmentList Dao starts");
		
		List<AppointmentListModel> countryList = new ArrayList<AppointmentListModel>();
		JsonResponse<List<AppointmentListModel>> jsonResponse = new JsonResponse<List<AppointmentListModel>>();
		try {
			
			String cdate = null;
			
			if (date != null && date != "") {
				cdate = DateFormatter.getStringDateNew(date);
			}
			
			System.out.println(userid+"  "+status+"   "+date);
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("get_user_appointment_list")
					.setParameter("userid", userid).setParameter("status", status).setParameter("date", cdate)
					.getResultList();
			for (Object[] m : x) {
				
				String appstatus = null;
				if (m[5] != null) {
					if (m[5].toString().contentEquals("7")) {
						appstatus = "Requested";
					}
					if (m[5].toString().contentEquals("2")) {
						appstatus = "Confirmed";
					}
					if (m[5].toString().contentEquals("4")) {
						appstatus = "Cancelled";
					}
					if (m[5].toString().contentEquals("5")) {
						appstatus = "Treated";
					}
				}
				Object appdate = null;
				if (m[2] != null) {
					appdate = DateFormatter.returnStringDateNew(m[2].toString());
				}
				
				AppointmentListModel dropDownModel = new AppointmentListModel(userid, m[0], m[1], appdate, m[3], m[4],
						appstatus,m[6],m[7]);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			
			if (countryList.size() > 0) {
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
			} else {
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("No appointment found");
			}
			
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<AppointmentListModel>>> response = new ResponseEntity<JsonResponse<List<AppointmentListModel>>>(
				jsonResponse, HttpStatus.OK);
		
		logger.info("Method : getUserAppointmentList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<AppointmentModel>>> getUserScreenTestAppointmentList(String userid,
			String appointDate) {
		logger.info("Method : getUserScreenTestAppointmentList Dao starts");

		List<AppointmentModel> countryList = new ArrayList<AppointmentModel>();
		JsonResponse<List<AppointmentModel>> jsonResponse = new JsonResponse<List<AppointmentModel>>();
		try {

			if (appointDate != null && appointDate != "") {
				appointDate = DateFormatter.getStringDateNew(appointDate);
			}
			int slno = 0;
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_appointment_screentest_list")
					.setParameter("userid", userid).setParameter("appointdate", appointDate).getResultList();
			for (Object[] m : x) {
				slno = slno + 1;
				String appStatusName = null;
				if (m[3] != null) {
					if (m[3].toString().contentEquals("0")) {
						appStatusName = "Booked";
					} else if (m[3].toString().contentEquals("1")) {
						appStatusName = "In Progress";
					} else {
						appStatusName = "Completed";
					}
				}

				Object appdate = null;

				if (m[2] != null) {
					appdate = DateFormatter.returnStringDateNew(m[2].toString());
				}

				AppointmentModel dropDownModel = new AppointmentModel(m[0], m[1], null, null, null, null, appdate, null,
						null, appStatusName, slno);
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
		logger.info("Method : getUserScreenTestAppointmentList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<AppointmentModel>>> getUserCheckUpAppointmentList(String userid,
			String appointDate) {
		logger.info("Method : getUserCheckUpAppointmentList Dao starts");

		List<AppointmentModel> countryList = new ArrayList<AppointmentModel>();
		JsonResponse<List<AppointmentModel>> jsonResponse = new JsonResponse<List<AppointmentModel>>();
		try {

			if (appointDate != null && appointDate != "") {
				appointDate = DateFormatter.getStringDateNew(appointDate);
			}
			int slno = 0;
			List<Object[]> x = em.createNamedStoredProcedureQuery("user_appointment_checkup_list")
					.setParameter("userid", userid).setParameter("appointdate", appointDate).getResultList();
			for (Object[] m : x) {
				slno = slno + 1;
				String appStatusName = null;
				if (m[3] != null) {
					if (m[3].toString().contentEquals("0")) {
						appStatusName = "Booked";
					} else if (m[3].toString().contentEquals("1")) {
						appStatusName = "In Progress";
					} else {
						appStatusName = "Completed";
					}
				}

				Object appdate = null;

				if (m[2] != null) {
					appdate = DateFormatter.returnStringDateNew(m[2].toString());
				}

				AppointmentModel dropDownModel = new AppointmentModel(m[0], m[1], null, null, null, null, appdate, null,
						null, appStatusName, slno);
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
		logger.info("Method : getUserCheckUpAppointmentList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIVitalSignModel>>> getUserVitalSignDetails(String userid) {
		logger.info("Method : getUserVitalSignDetails Dao starts");

		List<APIVitalSignModel> countryList = new ArrayList<APIVitalSignModel>();
		JsonResponse<List<APIVitalSignModel>> jsonResponse = new JsonResponse<List<APIVitalSignModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_vitalsign_details")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {

				Double height = 0.00;
				if (m[0] != null && m[0] != "" && !m[0].toString().equals(null) && !m[0].toString().equals("")) {
					height = Double.parseDouble(m[0].toString());
				}

				Double weight = 0.00;
				if (m[1] != null && m[1] != "" && !m[1].toString().equals(null) && !m[1].toString().equals("")) {
					weight = Double.parseDouble(m[1].toString());
				}

				Double bmi = 0.00;
				if (m[2] != null && m[2] != "" && !m[2].toString().equals(null) && !m[2].toString().equals("")) {
					bmi = Double.parseDouble(m[2].toString());
				}

				Double tempcs = 0.00;
				if (m[3] != null && m[3] != "" && !m[3].toString().equals(null) && !m[3].toString().equals("")) {
					tempcs = Double.parseDouble(m[3].toString());
				}

				Double fh = 0.00;
				if (m[3] != null) {
					Double cs = (Double) m[3];
					fh = (cs * 1.8) + 32;
				}

				String val = String.format("%.2f", fh);
				Double newfh = Double.parseDouble(val);

				Integer sysbp = 0;
				if (m[4] != null && m[4] != "" && !m[4].toString().equals(null) && !m[4].toString().equals("")) {
					sysbp = Integer.parseInt(m[4].toString());
				}

				Integer diabp = 0;
				if (m[5] != null && m[5] != "" && !m[5].toString().equals(null) && !m[5].toString().equals("")) {
					diabp = Integer.parseInt(m[5].toString());
				}

				Integer pulse = 0;
				if (m[6] != null && m[6] != "" && !m[6].toString().equals(null) && !m[6].toString().equals("")) {
					pulse = Integer.parseInt(m[6].toString());
				}

				Integer resp = 0;
				if (m[7] != null && m[7] != "" && !m[7].toString().equals(null) && !m[7].toString().equals("")) {
					resp = Integer.parseInt(m[7].toString());
				}

				Integer oxy = 0;
				if (m[8] != null && m[8] != "" && !m[8].toString().equals(null) && !m[8].toString().equals("")) {
					oxy = Integer.parseInt(m[8].toString());
				}

				APIVitalSignModel dropDownModel = new APIVitalSignModel(height, weight, bmi, tempcs, newfh, sysbp,
						diabp, pulse, resp, oxy);
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
		ResponseEntity<JsonResponse<List<APIVitalSignModel>>> response = new ResponseEntity<JsonResponse<List<APIVitalSignModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : getUserVitalSignDetails Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> getEmergencyDetails(String userid) {
		logger.info("Method : getEmergencyDetails Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();
		List<Map<String, Object>> jsonArray = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> jsonArray1 = new ArrayList<Map<String, Object>>();

		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_user_emergency_contactdetails_api")
					.setParameter("userid", userid).getResultList();

			if (x.size() > 0) {
				Integer i = 0;
				for (Object[] m : x) {
					Map<String, Object> ob = new HashMap<String, Object>();
					ob.put("name", m[0]);
					ob.put("relation", m[1]);
					ob.put("mobile", m[2]);
					ob.put("id", m[5]);
					ob.put("rel_id", m[6]);
					if ((m[0] == null || m[0] == "") && (m[1] == null || m[1] == "") && (m[2] == null || m[2] == "")
							&& (m[5] == null || m[5] == "") && (m[6] == null || m[6] == "")) {
						i++;
					} else {
						jsonArray.add(ob);
					}
				}
				if (i > 0) {
					obj.put("emergency", jsonArray1);
				} else {
					obj.put("emergency", jsonArray);
				}

				obj.put("ambulance", x.get(0)[3]);
				obj.put("police", x.get(0)[4]);
				if (x.get(0)[7] != null) {
					obj.put("emer_msg", x.get(0)[7]);
				} else {
					obj.put("emer_msg", "Emergency Alert ! I need help.");
				}
				obj.put("status", "success");
				obj.put("message", "Data fetched successfully");
			} else {
				obj.put("status", "failed");
				obj.put("message", "Data not found");
			}

		} catch (Exception e) {
			e.printStackTrace();
			obj.put("status", "failed");
			obj.put("message", "Data not found");
		}
		System.out.println(obj);
		logger.info("Method : getEmergencyDetails Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> smsToEmergencyContactUsers(String userid, String mapurl, String longi, String lati) {
		logger.info("Method : smsToEmergencyContactUsers Dao starts");

		Map<String, Object> myMap = new HashMap<String, Object>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_user_emergency_contactnumber_api")
					.setParameter("userid", userid).getResultList();

			if (x.size() > 0) {
				String msg = null;
				if (x.get(0)[3] != null) {
					msg = x.get(0)[3].toString();
				} else { 
					msg = "Emeregency Alert ! I need help.";
				}
				List<String> mobNumList = new ArrayList<String>();
				for (Object[] m : x) {
					if (m[2] != null) {
						String mobileNumber = m[2].toString();
						mobNumList.add(mobileNumber);
					}
				}

				System.out.println(mobNumList + " * " + msg + " * " + longi + " * " + lati);

				String msg1 = "eHealthSystem Emergency Alert! I need help. My location is "+longi+" & "+lati+".";
//				String msg1 = "Welcome to eHealthSystem. Your OTP is 1234";
				String encodemsg = URLEncoder.encode(msg1, "UTF-8");
				
				CommonUsed.sendSMS(mobNumList.get(0), encodemsg);
				
				myMap.put("status", "success");
				myMap.put("message", "Message sent successfully");
			} else {
				myMap.put("status", "failed");
				myMap.put("message", "Something went wrong");
			}

		} catch (Exception e) {
			e.printStackTrace();
			myMap.put("status", "failed");
			myMap.put("message", "Something went wrong");
		}

		logger.info("Method : smsToEmergencyContactUsers Dao ends");
//		HashMap<String, Object> result =  new ObjectMapper().readValue(jsonString, new TypeReference<Map<String, Object>>(){}));
		return new ResponseEntity<>(myMap, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> postEmergencyContactApi(EmergencyContactAPIModel data) {
		logger.info("Method : postEmergencyContactApi Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();

		Boolean validity = true;
		if (data.getMobile() == null || data.getMobile() == "") {
			obj.put("status", "failed");
			obj.put("message", "Mobile Number Required");
			validity = false;
		}

		System.out.println(data);

		if (validity) {
			try {
				List<Object[]> x = null;
				if (data.getId() != null && data.getId() != "") {
					x = em.createNamedStoredProcedureQuery("update_emergency_contact_api_data")
							.setParameter("userid", data.getUserid()).setParameter("name", data.getName())
							.setParameter("relation", data.getRelation()).setParameter("mobile", data.getMobile())
							.setParameter("eid", data.getId()).getResultList();
					obj.put("userid", x.get(0));
					obj.put("status", "success");
					obj.put("message", "Data updated successfully");
				} else {
					x = em.createNamedStoredProcedureQuery("post_emergency_contact_api_data")
							.setParameter("userid", data.getUserid()).setParameter("name", data.getName())
							.setParameter("relation", data.getRelation()).setParameter("mobile", data.getMobile())
							.getResultList();
					obj.put("userid", x.get(0));
					obj.put("status", "success");
					obj.put("message", "Data saved successfully");
				}
			} catch (Exception e) {
				e.printStackTrace();
				obj.put("status", "failed");
				obj.put("message", "Something went wrong");
			}
		}

		logger.info("Method : postEmergencyContactApi Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<Object> updateVitalSigns(List<CountryModel> data) {
		logger.info("Method : updateVitalSigns Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();

		Boolean validity = true;
		if (data.get(0).getName() == null || data.get(0).getName() == "") {
			obj.put("status", "failed");
			obj.put("message", "User ID Required");
			validity = false;
		}
		System.out.println(data);

		if (validity) {

			String casepaper = checkDuplicateDao.getCasePaperNumber(data.get(0).getName());

			String value = "";
			if (data.size() > 0) {
				for (CountryModel m : data) {
					if (m.getCode() != null && m.getCode() != "" && !m.getCode().equals(" ") && !m.getCode().equals("")
							&& !m.getCode().equals(null)) {
						value = value + "(" + m.getKey() + ",CURRENT_TIMESTAMP," + m.getCode() + ",'" + casepaper
								+ "','" + casepaper + "',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP),";
					}
				}
				value = value.substring(0, value.length() - 1);
			}

			System.out.println(value);

			try {
				List<Object[]> x = em.createNamedStoredProcedureQuery("update_vital_sign_details")
						.setParameter("subquery", value).setParameter("casepaper", casepaper)
//						.setParameter("relation", data.getRelation()).setParameter("mobile", data.getMobile())
						.getResultList();

//				obj.put("userid", x.get(0));
				obj.put("status", "success");
				obj.put("message", "Vital sign details updated successfully");
			} catch (Exception e) {
				e.printStackTrace();
				obj.put("status", "failed");
				obj.put("message", "Something went wrong");
			}
		}

		logger.info("Method : updateVitalSigns Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> postUserEmergencyMessage(EmergencyContactAPIModel data) {
		logger.info("Method : postUserEmergencyMessage Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();

		Boolean validity = true;
		if (data.getUserid() == null || data.getUserid() == "") {
			obj.put("status", "failed");
			obj.put("message", "User ID Required");
			validity = false;
		} else if (data.getMsg() == null || data.getMsg() == "") {
			obj.put("status", "failed");
			obj.put("message", "Message Content Required");
			validity = false;
		}

		System.out.println(data);

		if (validity) {
			try {
				List<Object[]> x = null;
				x = em.createNamedStoredProcedureQuery("post_user_emergency_message")
						.setParameter("userid", data.getUserid()).setParameter("message", data.getMsg())
						.getResultList();
				obj.put("userid", x.get(0));
				obj.put("status", "success");
				obj.put("message", "Data saved successfully");
			} catch (Exception e) {
				e.printStackTrace();
				obj.put("status", "failed");
				obj.put("message", "Something went wrong");
			}
		}

		logger.info("Method : postUserEmergencyMessage Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIMedicationModel>>> getUserMedicineDetailsByAppNo(String appno) {
		logger.info("Method : getUserMedicineDetailsByAppNo Dao starts");

		List<APIMedicationModel> countryList = new ArrayList<APIMedicationModel>();
		JsonResponse<List<APIMedicationModel>> jsonResponse = new JsonResponse<List<APIMedicationModel>>();
		try {
System.out.println(appno);
			List<Object[]> x = em.createNamedStoredProcedureQuery("view_user_meddtls_byappno")
					.setParameter("appno", appno).getResultList();
			for (Object[] m : x) {

				Boolean status = false;

				if (m[13] != null) {
					if (m[13].toString().contentEquals("1")) {
						status = true;
					} else {
						status = false;
					}
				}

				APIMedicationModel dropDownModel = new APIMedicationModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						m[7], m[8], m[9], m[10], m[11], m[12], status, m[13], null,m[14]);
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
		ResponseEntity<JsonResponse<List<APIMedicationModel>>> response = new ResponseEntity<JsonResponse<List<APIMedicationModel>>>(
				jsonResponse, HttpStatus.OK);
		
		System.out.println(response);
		
		logger.info("Method : getUserMedicineDetailsByAppNo Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<SearchDetailsModel>>> findHealthProviderDetails(String longi, String lati,
			String addr, String city, String healthpro, String type) {
		logger.info("Method : findHealthProviderDetails Dao starts");

		List<SearchDetailsModel> countryList = new ArrayList<SearchDetailsModel>();
		JsonResponse<List<SearchDetailsModel>> jsonResponse = new JsonResponse<List<SearchDetailsModel>>();

		Boolean validity = true;
		if (longi == null || longi == "") {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Longitude Required");
			validity = false;
		} else if (lati == null || lati == "") {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Latitude Required");
			validity = false;
		} else if (addr == null || addr == "") {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Current Address Required");
			validity = false;
		} else if (city == null || city == "") {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Current City Required");
			validity = false;
		} else if (healthpro == null || healthpro == "") {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Health Provider Required");
			validity = false;
		}

		if (validity) {
			try {

				List<Object[]> x = em.createNamedStoredProcedureQuery("find_health_provider_details")
						.setParameter("longi", longi).setParameter("lati", lati).setParameter("addr", addr)
						.setParameter("city", city).setParameter("healthpro", healthpro).setParameter("type", type)
						.getResultList();
				for (Object[] m : x) {

					Object path = null;

					if (m[4] != null) {
						path = env.getBaseURL() + "nirmalyaRest/document/profile/" + m[4].toString();
					}

					System.out.println(path);

					SearchDetailsModel dropDownModel = new SearchDetailsModel(m[0], m[1], m[2], m[3], path, m[5]);
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
		}

		ResponseEntity<JsonResponse<List<SearchDetailsModel>>> response = new ResponseEntity<JsonResponse<List<SearchDetailsModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : findHealthProviderDetails Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> deleteMedicineByAppNo(String appno, String srlone, String srltwo) {
		logger.info("Method : deleteMedicineByAppNo Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("delete_medicine_byappno")
					.setParameter("appno", appno).setParameter("srlone", srlone).setParameter("srltwo", srltwo)
					.getResultList();

			if (x.size() > 0) {
				obj.put("status", "success");
				obj.put("message", "Medicine deleted successfully.");
			} else {
				obj.put("status", "failed");
				obj.put("message", "Something went wrong.");
			}

		} catch (Exception e) {
			obj.put("status", "failed");
			obj.put("message", "Something went wrong.");
		}

		logger.info("Method : deleteMedicineByAppNo Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getPharmacyListByLocation(SearchDetailsModel data) {
		logger.info("Method : getPharmacyListByLocation Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {
			System.out.println(data.getAddress()+"    "+data.getCity());
			List<Object[]> x = em.createNamedStoredProcedureQuery("pharmacy_list_by_location")
					.setParameter("location", data.getAddress()).setParameter("city", data.getCity()).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getPharmacyListByLocation Dao ends");
		System.out.println(response);
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getLabListByLocation(SearchDetailsModel data) {
		logger.info("Method : getLabListByLocation Dao starts");
		
		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {
			System.out.println(data.getAddress());
			System.out.println(data.getCity());
			List<Object[]> x = em.createNamedStoredProcedureQuery("lab_list_by_location")
					.setParameter("location", data.getAddress()).setParameter("city", data.getCity()).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		
		logger.info("Method : getLabListByLocation Dao ends");
		System.out.println(response);
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> postPharmacyRequestApi(APIPharmacyModel data) {
		logger.info("Method : postPharmacyRequestApi Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();
		System.out.println(data);
		try {
			List<Object[]> x = null;
			String value = "";
			long nowTime = new Date().getTime();
			String orderid = "ORDR" + generateRandom(5) + nowTime;
			System.out.println(orderid);
			String casepaper = checkDuplicateDao.getCasePaperNumber(data.getUserid());
			if (data.getMeddetails().size() > 0) {
				for (CountryModel m : data.getMeddetails()) {
					value = value + "(\'" + orderid + "\',\'" + casepaper + "\',\'" + data.getPharmacistid() + "\',"
							+ m.getName() + "," + m.getCode() + ",\'" + m.getKey() + "\',\'" + data.getPatientnote()
							+ "\',0,1,CURRENT_TIMESTAMP),";
				}

				value = value.substring(0, value.length() - 1);
			}

			System.out.println(value);

			x = em.createNamedStoredProcedureQuery("post_pharmacy_request_byuser").setParameter("testsubquery", value)
					.setParameter("userid", data.getUserid()).getResultList();

			if (x.size() > 0) {
				obj.put("status", "success");
				obj.put("message", "Medicine requested successfully.");
			} else {
				obj.put("status", "failed");
				obj.put("message", "Something went wrong.");
			}

		} catch (Exception e) {
			obj.put("status", "failed");
			obj.put("message", "Something went wrong.");
		}

		logger.info("Method : postPharmacyRequestApi Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<Object> postLabRequestApi(APIPharmacyModel data) {
		logger.info("Method : postLabRequestApi Dao starts");
		
		Map<String, Object> obj = new HashMap<String, Object>();
		System.out.println(data);
		try {
			List<Object[]> x = null;
			String value = "";
			long nowTime = new Date().getTime();
			String orderid = "ORDR" + generateRandom(5) + nowTime;
			System.out.println(orderid);
			if (data.getMeddetails().size() > 0) {
				for (CountryModel m : data.getMeddetails()) {
					value = value + "(\'" + orderid + "\',\'" + data.getUserid() + "\',\'" + data.getPharmacistid() + "\',\'"
							+ m.getKey() + "\',\'" + m.getName() + "\',\'" + data.getPatientnote()
							+ "\',0,1,CURRENT_TIMESTAMP,"+m.getCode()+","+m.getData()+"),";
				}
				
				value = value.substring(0, value.length() - 1);
			}
			 
			System.out.println(value);
			
			x = em.createNamedStoredProcedureQuery("post_lab_request_byuser").setParameter("testsubquery", value)
					.setParameter("userid", data.getUserid()).getResultList();
			
			if (x.size() > 0) {
				obj.put("status", "success");
				obj.put("message", "Test requested successfully.");
			} else {
				obj.put("status", "failed");
				obj.put("message", "Something went wrong.");
			}
			
		} catch (Exception e) {
			obj.put("status", "failed");
			obj.put("message", "Something went wrong.");
		}
		
		logger.info("Method : postLabRequestApi Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIAllergyModel>>> viewUserAllergyListApi(String userid) {
		logger.info("Method : viewUserAllergyListApi Dao starts");

		List<APIAllergyModel> infrmtnList = new ArrayList<APIAllergyModel>();
		JsonResponse<List<APIAllergyModel>> jsonResponse = new JsonResponse<List<APIAllergyModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_allergies_details")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
//				String severity = null;
//				if (m[2] != null) {
//					if (m[2].toString().contentEquals("1")) {
//						severity = "High";
//					}
//					if (m[2].toString().contentEquals("2")) {
//						severity = "Medium";
//					}
//					if (m[2].toString().contentEquals("3")) {
//						severity = "Low";
//					}
//				}

				APIAllergyModel dropDownModel = new APIAllergyModel(m[0], m[1], m[2], m[3], m[4]);
				infrmtnList.add(dropDownModel);
			}
			jsonResponse.setBody(infrmtnList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<APIAllergyModel>>> response = new ResponseEntity<JsonResponse<List<APIAllergyModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : viewUserAllergyListApi Dao ends");
		System.out.println(response);
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIBioMedicalModel>>> viewUserBioMedicalImplantListApi(String userid) {
		logger.info("Method : viewUserBioMedicalImplantListApi Dao starts");

		List<APIBioMedicalModel> infrmtnList = new ArrayList<APIBioMedicalModel>();
		JsonResponse<List<APIBioMedicalModel>> jsonResponse = new JsonResponse<List<APIBioMedicalModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_biomedical_details")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {

				Object date = null;

				if (m[2] != null) {
					date = DateFormatter.returnStringDateNew(m[2].toString());
				}

				APIBioMedicalModel dropDownModel = new APIBioMedicalModel(m[0], m[1], date);
				infrmtnList.add(dropDownModel);
			}
			jsonResponse.setBody(infrmtnList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<APIBioMedicalModel>>> response = new ResponseEntity<JsonResponse<List<APIBioMedicalModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : viewUserBioMedicalImplantListApi Dao ends");
		System.out.println(response);
		return response;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<Object> postAllergiesApiDao(APIAllergyModel data) {

		logger.info("Method : postAllergiesApiDao Dao starts");
		Map<String, Object> obj = new HashMap<String, Object>();

		try {
			List<Object[]> x = null;
			if (data.getUserid() != null && data.getUserid() != "") {
				x = em.createNamedStoredProcedureQuery("save_allergies_api_data")
						.setParameter("userid", data.getUserid()).setParameter("allnameid", data.getAllnameid())
						.setParameter("alltypeid", data.getAlltypeid()).setParameter("severity", data.getSeverity())
						.setParameter("reaction", data.getReaction()).setParameter("updatedby", data.getUpdatedby())
						.getResultList();

				obj.put("status", "success");
				obj.put("message", "Data saved successfully");
			} else {

				obj.put("status", "failed");
				obj.put("message", "Something went wrong");
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("status", "failed");
			obj.put("message", "Something went wrong");
		}

		logger.info("Method : postAllergiesApiDao Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public ResponseEntity<Object> postBioMedicalImplantsApi(APIBioMedicalModel data) {
		logger.info("Method : postBioMedicalImplantsApi Dao starts");

		Map<String, Object> obj = new HashMap<String, Object>();

		try {
			List<Object[]> x = null;
			if (data.getUserid() != null && data.getUserid() != "") {
				x = em.createNamedStoredProcedureQuery("save_biomed_implants_api_data")
						.setParameter("userid", data.getUserid()).setParameter("bioname", data.getBioMName())
						.setParameter("biodate", DateFormatter.getStringDateNew(data.getBioMDate()))
						.setParameter("bioreason", data.getBioMReason()).getResultList();

				obj.put("status", "success");
				obj.put("message", "Data saved successfully");
			} else {

				obj.put("status", "failed");
				obj.put("message", "Something went wrong");
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("status", "failed");
			obj.put("message", "Something went wrong");
		}

		logger.info("Method : postBioMedicalImplantsApi Dao ends");
		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getPathologyListByLocation(SearchDetailsModel data) {
		logger.info("Method : getPathologyListByLocation Dao starts");

		List<DropDownModel> pathologyList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("pathology_list_by_location")
					.setParameter("location", data.getAddress()).setParameter("city", data.getCity()).getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				pathologyList.add(dropDownModel);
			}
			jsonResponse.setBody(pathologyList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getPathologyListByLocation Dao ends");
		System.out.println(response);
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIMedicationModel>>> getUserTestDetailsByAppNo(String appno) {
		logger.info("Method : getUserTestDetailsByAppNo Dao starts");

		List<APIMedicationModel> testList = new ArrayList<APIMedicationModel>();
		JsonResponse<List<APIMedicationModel>> jsonResponse = new JsonResponse<List<APIMedicationModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("view_user_testdtls_byappno")
					.setParameter("appno", appno).getResultList();
			for (Object[] m : x) {

				Boolean boolean1 = false;
				if (m[7].toString() != null) {
					String data = m[7].toString();
					if (data.contentEquals("1")) {
						boolean1 = true;
					} else {
						boolean1 = false;
					}
				}
				
//				Boolean status = false;
//
//				if (m[13] != null) {
//					if (m[13].toString().contentEquals("1")) {
//						status = true;
//					} else {
//						status = false;
//					}
//				}

				APIMedicationModel dropDownModel = new APIMedicationModel(m[0], m[1], m[2], m[3], m[4], m[5], m[6],
						boolean1, m[8], m[9]);
				testList.add(dropDownModel);
			}
			jsonResponse.setBody(testList);

			if (testList.size() > 0) {
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
		ResponseEntity<JsonResponse<List<APIMedicationModel>>> response = new ResponseEntity<JsonResponse<List<APIMedicationModel>>>(
				jsonResponse, HttpStatus.OK);
		System.out.println(response);
		logger.info("Method : getUserTestDetailsByAppNo Dao ends");
		return response;
	}

}
