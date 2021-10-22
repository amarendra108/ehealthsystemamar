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
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.model.ApiUserModel;
import nirmalya.aatithya.restmodule.api.model.CountryModel;
import nirmalya.aatithya.restmodule.api.model.OtherUsersProfileModel;
import nirmalya.aatithya.restmodule.api.model.SignUpModel;
import nirmalya.aatithya.restmodule.api.model.UserProfileAPIModel;
import nirmalya.aatithya.restmodule.common.CommonUsed;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;
import nirmalya.aatithya.restmodule.security.config.JwtTokenUtil;
import nirmalya.aatithya.restmodule.service.JwtUserDetailsService;

@Repository
public class SignUpLogInDao {

	@Autowired
	EntityManager em;

	@Autowired
	ServerDao serverDao;

	@Autowired
	PasswordEncoder passEncoder;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	CheckDuplicateDao checkDuplicateDao;

	Logger logger = LoggerFactory.getLogger(SignUpLogInDao.class);

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
	public ResponseEntity<JsonResponse<ApiUserModel>> getLoginDetails(String mobileNo, String password) {
		logger.info("Method : getLoginDetails starts");

		JsonResponse<ApiUserModel> jsonResponse = new JsonResponse<ApiUserModel>();
		jsonResponse.setCode("");
		jsonResponse.setMessage("");

		List<ApiUserModel> userArray = new ArrayList<ApiUserModel>();
		List<String> userRole = new ArrayList<String>();

		try {
			System.out.println(mobileNo);
			List<Object[]> x = em.createNamedStoredProcedureQuery("getLogInDetails")
					.setParameter("p_username", mobileNo).getResultList();

			for (Object[] m : x) {
				String role = (String) m[6];

				if (role != null && role.length() > 0) {
					String[] roles = role.split(",");
					userRole = Arrays.asList(roles);
				}

				Boolean boolean1 = false;
				if (m[5].toString() != null) {
					String data = m[5].toString();
					System.out.println(data);
					if (data.contentEquals("1")) {
						boolean1 = true;
					} else {
						boolean1 = false;
					}
				}

				// boolean b1=Boolean.getBoolean((String) m[11]);
				ApiUserModel user = new ApiUserModel(m[0].toString(), m[1], m[2], m[3], m[4], null, null, null, null,
						null, null, boolean1, null, null, userRole, m[7], m[8], m[9], m[10], m[11], null);
				userArray.add(user);
			}
			final UserDetails userDetails = userDetailsService.loadUserByUsername(mobileNo);

			logger.info("Method : getLoginDetails ends");
			if (userArray.size() > 0) {
				userArray.get(0).setToken("Bearer " + jwtTokenUtil.generateToken(userDetails));
				if (passEncoder.matches(password, userArray.get(0).getUserPassword())) {
					jsonResponse.setBody(userArray.get(0));
				} else {
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("Password is incorrect");

					ResponseEntity<JsonResponse<ApiUserModel>> response = new ResponseEntity<JsonResponse<ApiUserModel>>(
							jsonResponse, HttpStatus.OK);
					logger.info("Method : getLoginDetails ends");

					return response;
				}

				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");

			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Mobile number is incorrect");
		}

		ResponseEntity<JsonResponse<ApiUserModel>> response = new ResponseEntity<JsonResponse<ApiUserModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getLoginDetails ends");

		return response;

	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<ApiUserModel>> getLoginDetailsByMob(String mobileNo) {
		logger.info("Method : getLoginDetailsByMob starts");
		
		JsonResponse<ApiUserModel> jsonResponse = new JsonResponse<ApiUserModel>();
		jsonResponse.setCode("");
		jsonResponse.setMessage("");
		
		List<ApiUserModel> userArray = new ArrayList<ApiUserModel>();
		List<String> userRole = new ArrayList<String>();
		
		try {
			System.out.println(mobileNo);
			List<Object[]> x = em.createNamedStoredProcedureQuery("getLogInDetails")
					.setParameter("p_username", mobileNo).getResultList();
			
			for (Object[] m : x) {
				String role = (String) m[6];
				
				if (role != null && role.length() > 0) {
					String[] roles = role.split(",");
					userRole = Arrays.asList(roles);
				}
				
				Boolean boolean1 = false;
				if (m[5].toString() != null) {
					String data = m[5].toString();
					System.out.println(data);
					if (data.contentEquals("1")) {
						boolean1 = true;
					} else {
						boolean1 = false;
					}
				}
				
				// boolean b1=Boolean.getBoolean((String) m[11]);
				ApiUserModel user = new ApiUserModel(m[0].toString(), m[1], m[2], m[3], m[4], null, null, null, null,
						null, null, boolean1, null, null, userRole, m[7], m[8], m[9], m[10], m[11], null);
				userArray.add(user);
			}
			final UserDetails userDetails = userDetailsService.loadUserByUsername(mobileNo);
			
			logger.info("Method : getLoginDetails ends");
			if (userArray.size() > 0) {
				userArray.get(0).setToken("Bearer " + jwtTokenUtil.generateToken(userDetails));
				jsonResponse.setBody(userArray.get(0));
				String otp = generateRandom(4);
				userArray.get(0).setOtp(otp);
				System.out.println(otp);
				jsonResponse.setCode("success");
				jsonResponse.setMessage("Data Fetched Successfully");
				
				String msg = "Welcome to eHealthSystem. Your OTP is "+otp;
				String encodemsg = URLEncoder.encode(msg, "UTF-8");
				
				CommonUsed.sendSMS(mobileNo, encodemsg);
				
			}
			
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Mobile number is incorrect");
		}
		
		ResponseEntity<JsonResponse<ApiUserModel>> response = new ResponseEntity<JsonResponse<ApiUserModel>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getLoginDetailsByMob ends");
		
		return response;
		
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CountryModel>>> getCountryList() {
		logger.info("Method : getCountryList Dao starts");

		List<CountryModel> countryList = new ArrayList<CountryModel>();
		JsonResponse<List<CountryModel>> jsonResponse = new JsonResponse<List<CountryModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("getCountryList").getResultList();
			for (Object[] m : x) {
				CountryModel dropDownModel = new CountryModel(m[0].toString(), m[1], m[2].toString());
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<CountryModel>>> response = new ResponseEntity<JsonResponse<List<CountryModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getCountryList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getUserTitleList() {
		logger.info("Method : getUserTitleList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_title_list").getResultList();
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
		logger.info("Method : getUserTitleList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getHospitalList() {
		logger.info("Method : getHospitalList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("get_hospital_for_doc_app").getResultList();
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
		logger.info("Method : getHospitalList Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getGenderList() {
		logger.info("Method : getGenderList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("getGenderList").getResultList();
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
		logger.info("Method : getGenderList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getBloodGroupList() {
		logger.info("Method : getBloodGroupList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_bloodGroup_list").getResultList();
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
		logger.info("Method : getBloodGroupList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CountryModel>>> getStateList(Integer country) {
		logger.info("Method : getStateList Dao starts");

		List<CountryModel> countryList = new ArrayList<CountryModel>();
		JsonResponse<List<CountryModel>> jsonResponse = new JsonResponse<List<CountryModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_state_list").setParameter("country", country)
					.getResultList();
			for (Object[] m : x) {
				CountryModel dropDownModel = new CountryModel(m[0].toString(), m[1], m[2].toString());
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<CountryModel>>> response = new ResponseEntity<JsonResponse<List<CountryModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getStateList Dao ends");
		System.out.println(response);
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CountryModel>>> getDistrictList(Integer state) {
		logger.info("Method : getDistrictList Dao starts");

		List<CountryModel> countryList = new ArrayList<CountryModel>();
		JsonResponse<List<CountryModel>> jsonResponse = new JsonResponse<List<CountryModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_district_list")
					.setParameter("stateId", state.toString()).getResultList();
			for (Object[] m : x) {
				CountryModel dropDownModel = new CountryModel(m[0].toString(), m[1], null);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<CountryModel>>> response = new ResponseEntity<JsonResponse<List<CountryModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getDistrictList Dao ends");
		System.out.println(response);
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CountryModel>>> getCityList(Integer district) {
		logger.info("Method : getCityList Dao starts");

		List<CountryModel> countryList = new ArrayList<CountryModel>();
		JsonResponse<List<CountryModel>> jsonResponse = new JsonResponse<List<CountryModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_city_list")
					.setParameter("districtId", district.toString()).getResultList();
			for (Object[] m : x) {
				CountryModel dropDownModel = new CountryModel(m[0].toString(), m[1], null);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<CountryModel>>> response = new ResponseEntity<JsonResponse<List<CountryModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getCityList Dao ends");
		System.out.println(response);
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CountryModel>>> getHospitalList(Integer doctor) {
		logger.info("Method : getHospitalList Dao starts");

		List<CountryModel> countryList = new ArrayList<CountryModel>();
		JsonResponse<List<CountryModel>> jsonResponse = new JsonResponse<List<CountryModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("hospital_for_appointment")
					.setParameter("doctor", doctor.toString()).getResultList();
			for (Object[] m : x) {
				CountryModel dropDownModel = new CountryModel(m[0].toString(), m[1], null);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<CountryModel>>> response = new ResponseEntity<JsonResponse<List<CountryModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getHospitalList Dao ends");
		System.out.println(response);
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CountryModel>>> organizationList() {
		logger.info("Method : organizationList Dao starts");

		List<CountryModel> organizationList = new ArrayList<CountryModel>();
		JsonResponse<List<CountryModel>> jsonResponse = new JsonResponse<List<CountryModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_all_organization_list").getResultList();
			for (Object[] m : x) {
				CountryModel dropDownModel = new CountryModel(m[0].toString(), m[1], null);
				organizationList.add(dropDownModel);
			}
			jsonResponse.setBody(organizationList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<CountryModel>>> response = new ResponseEntity<JsonResponse<List<CountryModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : organizationList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getDoctorSpecialityList() {
		logger.info("Method : getDoctorSpecialityList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_speciality_list").setParameter("roleid", 2)
					.getResultList();
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
		logger.info("Method : getDoctorSpecialityList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getHealthProviderList() {
		logger.info("Method : getHealthProviderList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("health_provider_list").getResultList();
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
		logger.info("Method : getHealthProviderList Dao ends");

		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getRelationList() {
		logger.info("Method : getRelationList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_emercontact_drpdwn_details")
					.getResultList();
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
		logger.info("Method : getRelationList Dao ends");
		System.out.println(response);
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getPharmacyList() {
		logger.info("Method : getPharmacyList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("pharmacy_list").getResultList();
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
		logger.info("Method : getPharmacyList Dao ends");
		System.out.println(response);
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getAdmEquipmentList() {
		logger.info("Method : getAdmEquipmentList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_bname_drpdwn_details").getResultList();
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
		logger.info("Method : getAdmEquipmentList Dao ends");
		System.out.println(response);
		return response;
	}

	// get AllergyName List
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getallergyNameListDao() {
		logger.info("Method : allergyNameList Dao starts");

		List<DropDownModel> allergyNameList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_allername_drpdwn_details")
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				allergyNameList.add(dropDownModel);
			}
			jsonResponse.setBody(allergyNameList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : allergyNameList Dao ends");
		System.out.println(response);
		return response;
	}

	// get AllergyType List
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getallergyTypeListDao() {
		logger.info("Method : getallergyTypeList Dao starts");

		List<DropDownModel> allergyTypeList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_patient_allertype_drpdwn_details")
					.getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(), m[1]);
				allergyTypeList.add(dropDownModel);
			}
			jsonResponse.setBody(allergyTypeList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : allergyNameList Dao ends");
		System.out.println(response);
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CountryModel>>> getDoctorList(Integer type, Integer city) {
		logger.info("Method : getDoctorList Dao starts");

		List<CountryModel> countryList = new ArrayList<CountryModel>();
		JsonResponse<List<CountryModel>> jsonResponse = new JsonResponse<List<CountryModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("user_doctor_list")
					.setParameter("specialistId", type.toString()).setParameter("cityId", city.toString())
					.getResultList();
			for (Object[] m : x) {
				CountryModel dropDownModel = new CountryModel(m[0].toString(), m[1], null);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<CountryModel>>> response = new ResponseEntity<JsonResponse<List<CountryModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getDoctorList Dao ends");
		System.out.println(response);
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> signUpByPathologistDao(SignUpModel data) {
		logger.info("Method : signUpByPathologistDao Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (data.getMobile() == null || data.getMobile() == "") {
			jsonResponse.setMessage("Mobile Number Required");
			validity = false;
		} else if (data.getfName() == null || data.getfName() == "") {
			jsonResponse.setMessage("Name Required");
			validity = false;
		} else if (data.getAge() == null) {
			jsonResponse.setMessage("Age Required");
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
		} else if (data.getHeight() == null) {
			jsonResponse.setMessage("Height Required");
			validity = false;
		} else if (data.getWeight() == null) {
			jsonResponse.setMessage("Weight Required");
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
			String patEmail = "";
			if (data.getEmail() != null && data.getEmail() != "") {
				patEmail = data.getEmail();
			}

			String patAadhar = "";
			if (data.getAadhar() != null && data.getAadhar() != "") {
				patAadhar = data.getAadhar();
			}
			
			String qrcodename = "QR"+pId+".png";

			if (validity) {
				try {
					if (data.getId() == null || data.getId() == "") {

						List<Object[]> x = em.createNamedStoredProcedureQuery("registerPatientByPathologist")
								.setParameter("regId", pId).setParameter("userId", data.getUserId())
								.setParameter("patName", data.getfName()).setParameter("patMobile", data.getMobile())
								.setParameter("profileImage", image).setParameter("patAge", data.getAge())
								.setParameter("patGender", data.getGender()).setParameter("state", data.getState())
								.setParameter("country", data.getCountry()).setParameter("height", data.getHeight())
								.setParameter("weight", data.getWeight()).setParameter("patEmail", patEmail)
								.setParameter("aadhar", patAadhar)
								.setParameter("password",
										"$2a$10$SkYOZORZ4PUSURzL1fmvk.RcUwCfLk/R826sxBXKx/ZZyoQvkcaa6")
								.setParameter("enteredBy", data.getEnteredBy())
								.setParameter("dist", data.getDistrictid()).setParameter("city", data.getCityid())
								.setParameter("qrcode", qrcodename)
								.getResultList();

						jsonResponse.setBody(x.get(0));

					}

					jsonResponse.setCode("success");
					jsonResponse.setMessage("User Registered Successfully");
					
					String msg = "Welcome to eHealthSystem. You are registered successfully! Your UserId is "+pId+" or "+data.getMobile()+" and password is User@123 ";
					
					String encodemsg = URLEncoder.encode(msg, "UTF-8" );
					
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

		logger.info("Method : signUpByPathologistDao Dao ends");
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<SignUpModel>>> getPatientRegistrationList(String userid) {
		logger.info("Method : getPatientRegistrationList Dao starts");

		List<SignUpModel> countryList = new ArrayList<SignUpModel>();
		JsonResponse<List<SignUpModel>> jsonResponse = new JsonResponse<List<SignUpModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("getPatientRegistrationList")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {

				String gender = null;
				String email = null;
				String aadhar = null;

				if (m[5] != null) {
					if (m[5].toString().contentEquals("1")) {
						gender = "Male";
					} else if (m[5].toString().contentEquals("2")) {
						gender = "Female";
					} else {
						gender = "Others";
					}
				}

				if (m[9] == null || m[9] == "") {
					email = "N/A";
				} else {
					email = m[9].toString();
				}
				if (m[10] == null || m[10] == "") {
					aadhar = "N/A";
				} else {
					aadhar = m[10].toString();
				}

				String imgUrl = null;

				if (m[11] != null && m[11] != "" && !m[11].toString().contentEquals("null")) {
					imgUrl = env.getBaseURL() + "nirmalyaRest/document/profile/" + m[11].toString();
				}

				SignUpModel dropDownModel = new SignUpModel(m[0], m[1], m[2], m[3], m[4], gender, m[6], m[7], m[8],
						email, aadhar, imgUrl, null, null, null);
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
		ResponseEntity<JsonResponse<List<SignUpModel>>> response = new ResponseEntity<JsonResponse<List<SignUpModel>>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getPatientRegistrationList Dao ends");
		System.out.println(response);
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<UserProfileAPIModel>> getPatientDetailsRestApi(String userid) {
		logger.info("Method : getPatientDetailsRestApi Dao starts");

		List<UserProfileAPIModel> countryList = new ArrayList<UserProfileAPIModel>();
		JsonResponse<UserProfileAPIModel> jsonResponse = new JsonResponse<UserProfileAPIModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("getPatientDetailsAPI").setParameter("userid", userid)
					.getResultList();
			for (Object[] m : x) {

				System.out.println(m[0]);

				String gender = null;
				String fullname = null;

				if (m[4] != null) {
					if (m[4].toString().contentEquals("1")) {
						gender = "Male";
					} else if (m[4].toString().contentEquals("2")) {
						gender = "Female";
					} else {
						gender = "Others";
					}
				}

				String imgUrl = null;

				if (m[1] != null && m[1] != "" && !m[1].toString().contentEquals("null") && !m[1].toString().equals("")) {
					imgUrl = env.getBaseURL() + "nirmalyaRest/document/profile/" + m[1].toString();
				}

				if (m[14] != null) {
					String lastname = m[14].toString();
					String firstname = m[13].toString();
					String title = null;
					if (m[12] != null) {
						title = m[12].toString();
						fullname = title.concat(" ").concat(firstname).concat(" ").concat(lastname);
					} else {
						fullname = firstname.concat(" ").concat(lastname);
					}

				} else {
					String title = null;
					if (m[12] != null) {
						title = m[12].toString();
						fullname = title.concat(" ").concat(m[13].toString());
					} else {
						fullname = m[13].toString();
					}

				}
				
				String dob = null;
				if(m[2] != null && m[2] != "") { 
					dob = DateFormatter.returnStringDateNew(m[2].toString());
				}

				UserProfileAPIModel dropDownModel = new UserProfileAPIModel(m[0], imgUrl, fullname, dob, m[3], gender,
						m[5], m[6], m[7], m[8], m[9], m[10], m[11], m[12], m[13], m[14],m[15]);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList.get(0));
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
		ResponseEntity<JsonResponse<UserProfileAPIModel>> response = new ResponseEntity<JsonResponse<UserProfileAPIModel>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : getPatientDetailsRestApi Dao ends");
		System.out.println(response);
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<OtherUsersProfileModel>> othersUserProfile(String userid) {
		logger.info("Method : othersUserProfile Dao starts");

		List<OtherUsersProfileModel> countryList = new ArrayList<OtherUsersProfileModel>();
		JsonResponse<OtherUsersProfileModel> jsonResponse = new JsonResponse<OtherUsersProfileModel>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("other_users_profile").setParameter("userid", userid)
					.getResultList();
			for (Object[] m : x) {

				String profileimg = null;
				if (m[3] != null && m[3] != "" && m[3] != " " && !m[3].toString().equals(" ")
						&& !m[3].toString().equals(null) && !m[3].toString().equals("")) {
					profileimg = env.getBaseURL() + "document/profile/" + m[3].toString();
				} else {
					profileimg = "N/A";
				}

				String dob = null;
				if (m[6] != null && m[6] != "") {
					dob = DateFormatter.returnStringDateNew(m[6].toString());
				} else {
					dob = "N/A";
				}

				String email = null;
				if (m[7] != null && m[7] != "") {
					email = m[7].toString();
				} else {
					email = "N/A";
				}

				String state = null;
				if (m[8] != null && m[8] != "") {
					state = m[8].toString();
				} else {
					state = "N/A";
				}

				String country = null;
				if (m[9] != null && m[9] != "") {
					country = m[9].toString();
				} else {
					country = "N/A";
				}

				String edu = null;
				if (m[10] != null && m[10] != "") {
					edu = m[10].toString();
				} else {
					edu = "N/A";
				}

				String aadhaar = null;
				if (m[13] != null && m[13] != "") {
					aadhaar = m[13].toString();
				} else {
					aadhaar = "N/A";
				}

				String ima = null;
				if (m[14] != null && m[14] != "") {
					ima = m[14].toString();
				} else {
					ima = "N/A";
				}

				String pancard = null;
				if (m[15] != null && m[15] != "") {
					pancard = m[15].toString();
				} else {
					pancard = "N/A";
				}

				String passport = null;
				if (m[16] != null && m[16] != "") {
					passport = m[16].toString();
				} else {
					passport = "N/A";
				}

				String votercard = null;
				if (m[17] != null && m[17] != "") {
					votercard = m[17].toString();
				} else {
					votercard = "N/A";
				}

				String licence = null;
				if (m[18] != null && m[18] != "") {
					licence = m[18].toString();
				} else {
					licence = "N/A";
				}

				OtherUsersProfileModel dropDownModel = new OtherUsersProfileModel(m[0], m[1], m[2], profileimg, m[4],
						m[5], dob, email, state, country, edu, m[11], m[12], aadhaar, ima, pancard, passport, votercard,
						licence, m[19]);
				countryList.add(dropDownModel);
			}
			jsonResponse.setBody(countryList.get(0));
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
		ResponseEntity<JsonResponse<OtherUsersProfileModel>> response = new ResponseEntity<JsonResponse<OtherUsersProfileModel>>(
				jsonResponse, HttpStatus.OK);

		logger.info("Method : othersUserProfile Dao ends");
		System.out.println(response);
		return response;
	}

	// get Ambulance List
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> ambulanceList() {
		logger.info("Method : getAmbulanceList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("ambulance_list").getResultList();
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
		logger.info("Method : getAmbulanceList Dao ends");
		System.out.println(response);
		return response;
	}

	// get Bloodbank List
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> bloodbankList() {
		logger.info("Method : getBloodbankList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("bloodbank_list").getResultList();
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
		logger.info("Method : getBloodbankList Dao ends");
		System.out.println(response);
		return response;
	}

	// get NGO List
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> ngoList() {
		logger.info("Method : getNgoList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("ngo_list").getResultList();
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
		logger.info("Method : getNgoList Dao ends");
		System.out.println(response);
		return response;
	}

	// get Pathology Lab list
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getpathologylabList() {
		logger.info("Method : getpathologylabList Dao starts");

		List<DropDownModel> countryList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("pathology_lab_list").getResultList();
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
		logger.info("Method : getpathologylabList Dao ends");
		System.out.println(response);
		return response;
	}

	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<CountryModel>>> gettestNameList() {
		logger.info("Method : gettestNameList Dao starts");

		List<CountryModel> testnameList = new ArrayList<CountryModel>();
		JsonResponse<List<CountryModel>> jsonResponse = new JsonResponse<List<CountryModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("test_name_list").getResultList();
			for (Object[] m : x) {
				CountryModel dropDownModel = new CountryModel(m[0].toString(), m[2], m[1]);
				testnameList.add(dropDownModel);
			}
			jsonResponse.setBody(testnameList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<CountryModel>>> response = new ResponseEntity<JsonResponse<List<CountryModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : gettestNameList Dao ends");

		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getOrganList() {
		logger.info("Method : getOrganList Dao starts");
		
		List<DropDownModel> testnameList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("organ_list").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),m[1]);
				testnameList.add(dropDownModel);
			}
			jsonResponse.setBody(testnameList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getOrganList Dao ends");
		
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> getTissueList() {
		logger.info("Method : getTissueList Dao starts");
		
		List<DropDownModel> testnameList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("tissue_list").getResultList();
			for (Object[] m : x) {
				DropDownModel dropDownModel = new DropDownModel(m[0].toString(),m[1]);
				testnameList.add(dropDownModel);
			}
			jsonResponse.setBody(testnameList);
			jsonResponse.setCode("success");
			jsonResponse.setMessage("Data Fetched Successfully");
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage(e.getMessage());
		}
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getTissueList Dao ends");
		
		return response;
	}
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> getOTPForFOrgotPassword(DropDownModel data) {
		logger.info("Method : getOTPForFOrgotPassword Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (data.getKey() == null || data.getKey() == "") {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("UHID or Mobile Number Required");
			validity = false;
		}
		System.out.println(data.getKey()+"   "+data.getName());
		if (validity) {
			try {
				if (data.getKey() != null && data.getKey() != "") {
					List<Object[]> x = em.createNamedStoredProcedureQuery("check_userid_exist")
							.setParameter("userid", data.getKey())
							.getResultList();
					
					if(x.size() > 0) {
						String otp = generateRandom(4);
						DropDownModel dd = new DropDownModel();
						dd.setKey(x.get(0)[0].toString());
						dd.setCode(otp);
						
						jsonResponse.setBody(dd);
						jsonResponse.setCode("success");
						jsonResponse.setMessage("OTP sent successfully");
						
						String msg = "Welcome to eHealthSystem. Your OTP is "+otp;
						String encodemsg = URLEncoder.encode(msg, "UTF-8");
						
						CommonUsed.sendSMS(x.get(0)[1].toString(), encodemsg);
						
					} else {
						jsonResponse.setCode("failed");
						jsonResponse.setMessage(data.getName()+" is not exist");
					}
					
				} else {
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("UHID or Mobile number required");
				}
			} catch(Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong");
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : getOTPForFOrgotPassword Dao ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> changePassword(DropDownModel data) {
		logger.info("Method : changePassword Dao starts");
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		
		Boolean validity = true;
		if (data.getKey() == null || data.getKey() == "") {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("UHID required");
			validity = false;
		}
		
		if (validity) {
			try {
				if (data.getKey() != null && data.getKey() != "") {
					
					String password = null;
					if(data.getCode() != null && data.getCode() != "") {
						password = passEncoder.encode(data.getCode());
					}
					
					List<Object[]> x = em.createNamedStoredProcedureQuery("change_password")
							.setParameter("userid", data.getKey()).setParameter("password", password)
							.getResultList();
					
					if(x.size() > 0) {
						jsonResponse.setCode("success");
						jsonResponse.setMessage("Password updated successfully");
						
//						String msg = "Welcome to eHealthSystem. Your OTP is "+otp;
//						String encodemsg = URLEncoder.encode(msg, "UTF-8");
//						
//						CommonUsed.sendSMS(x.get(0)[0].toString(), encodemsg);
						
					} else {
						jsonResponse.setCode("failed");
						jsonResponse.setMessage("Password not updated");
					}
					
				} else {
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("UHID required");
				}
			} catch(Exception e) {
				e.printStackTrace();
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Password not updated");
			}
		}
		
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);
		
		logger.info("Method : changePassword Dao ends");
		return response;
	}

}
