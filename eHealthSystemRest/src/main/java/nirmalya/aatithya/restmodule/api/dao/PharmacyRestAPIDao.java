package nirmalya.aatithya.restmodule.api.dao;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import nirmalya.aatithya.restmodule.api.model.APIMedicationModel;
import nirmalya.aatithya.restmodule.api.model.APIPharmacyOrderModel;
import nirmalya.aatithya.restmodule.api.model.DoctorRegistrationModel;
import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.common.ServerDao;
import nirmalya.aatithya.restmodule.common.utils.DateFormatter;
import nirmalya.aatithya.restmodule.common.utils.DropDownModel;
import nirmalya.aatithya.restmodule.common.utils.JsonResponse;

@Repository
public class PharmacyRestAPIDao {

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

	Logger logger = LoggerFactory.getLogger(PharmacyRestAPIDao.class);
	
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
					imageName = pId+"_"+nowTime + ".jpg";
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
	public ResponseEntity<JsonResponse<Object>> pharmacyRegistration(DoctorRegistrationModel data) {
		logger.info("Method : pharmacyRegistration Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();

		Boolean validity = true;
		if (data.getAddress() == null || data.getAddress() == "") {
			jsonResponse.setMessage("Address Required");
			validity = false;
//		} else if (data.getBloodgroup() == null || data.getBloodgroup() == "") {
//			jsonResponse.setMessage("Blood Group Required");
//			validity = false;
		} else if (data.getOrganizationid() == null || data.getOrganizationid() == "") {
			jsonResponse.setMessage("Organization Required");
			validity = false;
		} else if (data.getGender() == null || data.getGender() == "") {
			jsonResponse.setMessage("Gender Required");
			validity = false;
		} else if (data.getTitleid() == null || data.getTitleid() == "") {
			jsonResponse.setMessage("Title Required");
			validity = false;
//		} else if (data.getAddress() == null || data.getAddress() == "") {
//			jsonResponse.setMessage("Address Required");
//			validity = false;
		} else if (data.getDocname() == null || data.getBloodgroup() == "") {
			jsonResponse.setMessage("Name Required");
			validity = false;
		} else if (data.getSpeciality() == null || data.getSpeciality() == "") {
			jsonResponse.setMessage("Speciality Required");
			validity = false;
		} else if (data.getCountryid() == null) {
			jsonResponse.setMessage("Country Required");
			validity = false;
		} else if (data.getStateid() == null) {
			jsonResponse.setMessage("State Required");
			validity = false;
		} else if (data.getDistrictid() == null) {
			jsonResponse.setMessage("District Required");
			validity = false;
		} else if (data.getCityid() == null) {
			jsonResponse.setMessage("City Required");
			validity = false;
		} else if (data.getPincode() == null || data.getPincode() == "") {
			jsonResponse.setMessage("Pin Required");
			validity = false;
//		} else if (data.getDob() == null || data.getDob() == "") {
//			jsonResponse.setMessage("DOB Required");
//			validity = false;
//		} else if (data.getEducationid() == null) {
//			jsonResponse.setMessage("Education Required");
//			validity = false;
		} else if (data.getEmail() == null) {
			jsonResponse.setMessage("Email id Required");
			validity = false;
		} else if (data.getRole() == null) {
			jsonResponse.setMessage("Role Required");
			validity = false;
		}
		Boolean isMobileExist = false;
//		Boolean isMobileExist = checkDuplicateDao.isPatientMobileNumberExist(data.getMobno());
//
//		if (isMobileExist) {
//
//			jsonResponse.setCode("failed");
//			jsonResponse.setMessage("Mobile number is already exist.");
//
//			ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
//					HttpStatus.OK);
//
//			return response;
//		}
		if (!isMobileExist) {
			if (data.getDocname() != null && data.getDocname() != "") {
				String noSpaceStr = data.getDocname().replaceAll("\\s", "");
				String substr = noSpaceStr.substring(0, 3);
				substr = substr.toUpperCase();
				String random = generateRandom(5);
				data.setUserid(substr.concat(random));
			}

			String image = "";
			if (data.getProfileImage().size() > 0) {

				if (data.getProfileImage().get(0) != null && data.getProfileImage().get(0) != "") {
					try {
						byte[] bytes = Base64.getDecoder().decode(data.getProfileImage().get(0));
						String imageName = saveAllImage(bytes, data.getProfileImageType(), data.getUserid());
						data.setProfileImageName(imageName);
						image = imageName;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			String docEmail = "";
			if (data.getEmail() != null && data.getEmail() != "") {
				docEmail = data.getEmail();
			}

			String address = "";
			if (data.getAddress() != null && data.getAddress() != "") {
				address = data.getAddress();
			}

			String pin = "";
			if (data.getPincode() != null && data.getPincode() != "") {
				pin = data.getPincode();
			}
			String title = "";
			if (data.getTitleid() != null && data.getTitleid() != "") {
				title = data.getTitleid();
			}
System.out.println(data);
			if (validity) {
				try {
					if (data.getDctrid() == null || data.getDctrid() == "") {

						List<Object[]> x = em.createNamedStoredProcedureQuery("pharmacy_registration_mobile")
								.setParameter("organizationid", data.getOrganizationid()).setParameter("titleid", title)
								.setParameter("docname", data.getDocname()).setParameter("experience", data.getExperience())
								.setParameter("speciality", data.getSpeciality())
								.setParameter("gender", data.getGender()).setParameter("address", address)
								.setParameter("countryid", data.getCountryid())
								.setParameter("stateid", data.getStateid())
								.setParameter("districtid", data.getDistrictid())
								.setParameter("cityid", data.getCityid()).setParameter("pin", pin)
								.setParameter("mobno", data.getMobno()).setParameter("email", docEmail)
								.setParameter("userid", data.getUserid())
								.setParameter("password",
										"$2a$10$SkYOZORZ4PUSURzL1fmvk.RcUwCfLk/R826sxBXKx/ZZyoQvkcaa6")
								.setParameter("profileImage", image).setParameter("role", data.getRole())
								.getResultList();

						jsonResponse.setBody(x.get(0));

					}

					jsonResponse.setCode("success");
					jsonResponse.setMessage("Pharmacist Registered Successfully");
				} catch (Exception e) {
					e.printStackTrace();
					jsonResponse.setCode("failed");
					jsonResponse.setMessage("Something went wrong. Registration failed.");
				}
			}
		}

		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(jsonResponse,
				HttpStatus.OK);

		logger.info("Method : pharmacyRegistration Dao ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIPharmacyOrderModel>>> getUserPharmacyOrderByIdDao(String userid) {
		logger.info("Method : getUserPharmacyOrderById Dao starts");

		List<APIPharmacyOrderModel> orderList = new ArrayList<APIPharmacyOrderModel>();
		JsonResponse<List<APIPharmacyOrderModel>> jsonResponse = new JsonResponse<List<APIPharmacyOrderModel>>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("view_medicine_orderlist_bypharmacist")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				
				String date = null;
				
				if(m[2] != null) {
					date = DateFormatter.returnStringDateNew(m[2].toString());
				}
				
				APIPharmacyOrderModel dropDownModel = new APIPharmacyOrderModel(m[0], m[1], date, m[3], m[4]);
				orderList.add(dropDownModel);
			}
			jsonResponse.setBody(orderList);

			if (orderList.size() > 0) {
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
		ResponseEntity<JsonResponse<List<APIPharmacyOrderModel>>> response = new ResponseEntity<JsonResponse<List<APIPharmacyOrderModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getUserPharmacyOrderByIdDao Dao ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIPharmacyOrderModel>>> getUserLabOrderByIdDao(String userid) {
		logger.info("Method : getUserLabOrderByIdDao Dao starts");
		
		List<APIPharmacyOrderModel> orderList = new ArrayList<APIPharmacyOrderModel>();
		JsonResponse<List<APIPharmacyOrderModel>> jsonResponse = new JsonResponse<List<APIPharmacyOrderModel>>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("view_medicine_orderlist_bypharmacist")
					.setParameter("userid", userid).getResultList();
			for (Object[] m : x) {
				
				String date = null;
				
				if(m[2] != null) {
					date = DateFormatter.returnStringDateNew(m[2].toString());
				}
				
				APIPharmacyOrderModel dropDownModel = new APIPharmacyOrderModel(m[0], m[1], date, m[3], m[4]);
				orderList.add(dropDownModel);
			}
			jsonResponse.setBody(orderList);
			
			if (orderList.size() > 0) {
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
		ResponseEntity<JsonResponse<List<APIPharmacyOrderModel>>> response = new ResponseEntity<JsonResponse<List<APIPharmacyOrderModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : getUserLabOrderByIdDao Dao ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIPharmacyOrderModel>>> viewAcceptedLabOrderById(String userid,String status) {
		logger.info("Method : viewAcceptedLabOrderById Dao starts");
		
		List<APIPharmacyOrderModel> orderList = new ArrayList<APIPharmacyOrderModel>();
		JsonResponse<List<APIPharmacyOrderModel>> jsonResponse = new JsonResponse<List<APIPharmacyOrderModel>>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("view_teststatus_orderlist")
					.setParameter("userid", userid).setParameter("status", status).getResultList();
			for (Object[] m : x) {
				
				String date = null;
				
				if(m[2] != null) {
					date = DateFormatter.returnStringDateNew(m[2].toString());
				}
				
				APIPharmacyOrderModel dropDownModel = new APIPharmacyOrderModel(m[0], m[1], date, m[3], m[4]);
				orderList.add(dropDownModel);
			}
			jsonResponse.setBody(orderList);
			
			if (orderList.size() > 0) {
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
		ResponseEntity<JsonResponse<List<APIPharmacyOrderModel>>> response = new ResponseEntity<JsonResponse<List<APIPharmacyOrderModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : viewAcceptedLabOrderById Dao ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<APIMedicationModel>>> viewRequestedMedicineDetails(String orderid) {
		logger.info("Method : viewRequestedMedicineDetails Dao starts");
		
		List<APIMedicationModel> orderList = new ArrayList<APIMedicationModel>();
		JsonResponse<List<APIMedicationModel>> jsonResponse = new JsonResponse<List<APIMedicationModel>>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("view_requested_medicine_details")
					.setParameter("orderid", orderid).getResultList();
			for (Object[] m : x) {
				
				APIMedicationModel dropDownModel = new APIMedicationModel(m[0], m[1], m[2]);
				orderList.add(dropDownModel);
			}
			jsonResponse.setBody(orderList);
			
			if (orderList.size() > 0) {
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
		logger.info("Method : viewRequestedMedicineDetails Dao ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<List<DropDownModel>>> viewRequestedTestDetails(String orderid) {
		logger.info("Method : viewRequestedTestDetails Dao starts");
		
		List<DropDownModel> orderList = new ArrayList<DropDownModel>();
		JsonResponse<List<DropDownModel>> jsonResponse = new JsonResponse<List<DropDownModel>>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("view_requested_test_details")
					.setParameter("orderid", orderid).getResultList();
			for (Object[] m : x) {
				
				DropDownModel dropDownModel = new DropDownModel(m[0], m[1]);
				orderList.add(dropDownModel);
			}
			jsonResponse.setBody(orderList);
			
			if (orderList.size() > 0) {
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
		ResponseEntity<JsonResponse<List<DropDownModel>>> response = new ResponseEntity<JsonResponse<List<DropDownModel>>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : viewRequestedTestDetails Dao ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> changePharmacyStatus(String orderid,String status) {
		logger.info("Method : changePharmacyStatus Dao starts");

		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		try {

			List<Object[]> x = em.createNamedStoredProcedureQuery("change_medicine_status")
					.setParameter("orderid", orderid).setParameter("status", status).getResultList();

			if (x.size() > 0) {
				jsonResponse.setCode("success");
				if(status.equals("4")) {
					jsonResponse.setMessage("Order accepted");
				} else {
					jsonResponse.setMessage("Order rejected");
				}
			} else { 
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong");
			}

		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Something went wrong");
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : changePharmacyStatus Dao ends");
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public ResponseEntity<JsonResponse<Object>> changeLabStatus(String orderid,String status) {
		logger.info("Method : changeLabStatus Dao starts");
		
		JsonResponse<Object> jsonResponse = new JsonResponse<Object>();
		try {
			
			List<Object[]> x = em.createNamedStoredProcedureQuery("change_test_status")
					.setParameter("orderid", orderid).setParameter("status", status).getResultList();
			
			if (x.size() > 0) {
				jsonResponse.setCode("success");
				if(status.equals("4")) {
					jsonResponse.setMessage("Order accepted");
				} else {
					jsonResponse.setMessage("Order rejected");
				}
			} else { 
				jsonResponse.setCode("failed");
				jsonResponse.setMessage("Something went wrong");
			}
			
		} catch (Exception e) {
			jsonResponse.setCode("failed");
			jsonResponse.setMessage("Something went wrong");
		}
		ResponseEntity<JsonResponse<Object>> response = new ResponseEntity<JsonResponse<Object>>(
				jsonResponse, HttpStatus.OK);
		logger.info("Method : changeLabStatus Dao ends");
		return response;
	}
}
