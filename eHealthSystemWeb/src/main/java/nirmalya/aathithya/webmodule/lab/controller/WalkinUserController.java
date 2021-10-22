package nirmalya.aathithya.webmodule.lab.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.net.URLEncoder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import nirmalya.aathithya.webmodule.common.utils.CommonUsed;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.user.model.UserRegistrationModel;

@Controller
@RequestMapping(value = { "lab/" })
public class WalkinUserController {
	Logger logger = LoggerFactory.getLogger(WalkinUserController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	/*
	 * Get Mapping for view-lab-walkin
	 */

	@GetMapping("view-walkin-user")
	public String viewWalkinUser(Model model, HttpSession session) {
		logger.info("Method : viewWalkinUser starts");

		// drop down for Title list
		try {
			DropDownModel[] title = restTemplate.getForObject(env.getUserUrl() + "geTitleLists", DropDownModel[].class);
			List<DropDownModel> tittleList = Arrays.asList(title);
			model.addAttribute("tittleList", tittleList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		// drop down for country list
		try {
			DropDownModel[] country = restTemplate.getForObject(env.getUserUrl() + "getCountryLists",
					DropDownModel[].class);
			List<DropDownModel> countryList = Arrays.asList(country);
			model.addAttribute("countryList", countryList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	
		logger.info("Method : viewWalkinUser ends");
		// return "lab/demo";
		return "lab/walkinUserRegistration";
	}
	/*
	 * get user address	
	 */
		@SuppressWarnings("unchecked")
		@GetMapping("view-walkin-user-getAddress")
		public @ResponseBody JsonResponse<UserRegistrationModel> getAddress(Model model,HttpSession session ) {
			logger.info("Method : getAddress starts");
			JsonResponse<UserRegistrationModel> jsonResponse = new JsonResponse<UserRegistrationModel>();
			String userId="";
			userId = (String) session.getAttribute("USER_ID");
			try {
				jsonResponse = restTemplate.getForObject(env.getLabUrl() + "rest-getAddress?userId="+userId,JsonResponse.class);
				ObjectMapper mapper = new ObjectMapper();
				UserRegistrationModel userModel = mapper.convertValue(jsonResponse.getBody(),
						new TypeReference<UserRegistrationModel>() {
						});
			jsonResponse.setBody(userModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
				jsonResponse.setCode(jsonResponse.getMessage());
				jsonResponse.setMessage("Unsuccess");
			} else {
				jsonResponse.setMessage("Success");
			}
			logger.info("Method : getAddress ends");
			return jsonResponse;
		}
	// dropdown for state list

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "/view-walkin-user-getStateList" })
	public @ResponseBody JsonResponse<Object> getStateListss(@RequestParam Integer country) {
		logger.info("Method : getStateList starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-getStateList?id=" + country, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getStateList ends");
		return res;

	}
	// dropdown for District list

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "view-walkin-user-getDistList" })
	public @ResponseBody JsonResponse<Object> getDistListss(@RequestParam String state) {
		logger.info("Method : getDistList starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-getDistList?state=" + state, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getDistList ends");
		return res;

	}
	// dropdown for City list

	@SuppressWarnings("unchecked")

	@GetMapping(value = { "view-walkin-user-getCityList" })
	public @ResponseBody JsonResponse<Object> getCityListss(@RequestParam String dist) {
		logger.info("Method : getCityList starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getUserUrl() + "rest-getCityList?dist=" + dist, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getCityList ends");
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

	/**
	 * Function to add registration walkinUser form for e-healthSystem
	 *
	 */

	@SuppressWarnings("unchecked")
	@PostMapping("/view-walkin-user-add-user")
	public @ResponseBody JsonResponse<Object> addWalkInUserRegistration(HttpSession session,
			@RequestBody UserRegistrationModel regdModel) {
		logger.info("Method : addWalkInUserRegistration starts");
		// int n = 8;
		// String pass = AutoPasswordGenerator.getAlphaNumericString(n);
		String pass = "$2a$10$SkYOZORZ4PUSURzL1fmvk.RcUwCfLk/R826sxBXKx/ZZyoQvkcaa6";
		String userId = "";
		JsonResponse<Object> resp = new JsonResponse<Object>();

		// QR CODE

		String file = null;
		BigInteger UId = regdModel.getPatientId();

		try {
			String ccode = null;
			String scode = null;
			if (regdModel.getCountryCode() != null) {

				ccode = regdModel.getCountryCode().toString();
				ccode = ccode.substring(0, 2);
				scode = regdModel.getStateCode().toString();
				scode = scode.substring(0, 2);
			} else {
				ccode = "10";
				scode = "50";
			}

			String randomNumber = generateRandom(12);
			String genCode = ccode.concat(scode).concat(randomNumber);

			BigInteger pId = new BigInteger(genCode);
			regdModel.setPatientId(pId);

			// QR CODE

			file = "QR" + pId + ".png";

			/*
			 * if (pass != null && pass != "") { pass = passwordEncoder.encode(pass);
			 * regdModel.setPassword(pass); }
			 */
			regdModel.setPassword(pass);
			userId = (String) session.getAttribute("USER_ID");
			regdModel.setCreatedBy(userId);

			// QR CODE

			regdModel.setqRFileName(file);

			MultipartFile inputFile = (MultipartFile) session.getAttribute("registerProfileImg");
			byte[] bytes;
			String imageName = null;

			if (inputFile != null) {
				try {
					bytes = inputFile.getBytes();
					String[] fileType = inputFile.getContentType().split("/");
					imageName = saveAllImage(bytes, fileType[1]);

					regdModel.setFileProfileimg(imageName);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			/*
			 * String imageName = ""; regdModel.setFileProfileimg(imageName);
			 */
			resp = restTemplate.postForObject(env.getUserUrl() + "rest-addUserRegistration", regdModel,
					JsonResponse.class);
			ObjectMapper mapper = new ObjectMapper();

			List<UserRegistrationModel> seat = mapper.convertValue(resp.getBody(),
					new TypeReference<List<UserRegistrationModel>>() {
					});

			resp.setBody(seat);
		} catch (Exception e) {

			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		}

		// QR CODE

		else {
			String msg = "Welcome to eHealthSystem. You are registered successfully! Your UserId is "
					+ regdModel.getPatientId() + " or " + regdModel.getMob() + " and password is User@123 ";

			String encodemsg = null;
			try {
				encodemsg = URLEncoder.encode(msg, "UTF-8" );
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				CommonUsed.sendSMS(regdModel.getMob(), encodemsg);
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String qrdata = "UniqueId : " + regdModel.getPatientId() + "\nMobile : " + regdModel.getMob() + "\nName : "
					+ regdModel.getfName() + "  " + regdModel.getlName();

			try {
				String qrCodeData = qrdata;
				String filePath = env.getUserqrcodeUrl() + file;

				String charset = "UTF-8";// "ISO-8859-1";

				Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();

				hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
				BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),
						BarcodeFormat.QR_CODE, 200, 200, hintMap);
				MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1),
						new File(filePath));

				logger.info("Method : qrcode function Ends");

			} catch (Exception e) {
				System.err.println(e);
			}
			resp.setMessage("Success");

		}
		logger.info("Method : addWalkInUserRegistration ends");

		return resp;
	}
	/*@SuppressWarnings("unchecked")

	@PostMapping("/view-walkin-user-add-user")
	public @ResponseBody JsonResponse<Object> addWalkinUserRegistration(HttpSession session,

			@RequestBody UserRegistrationModel regdModel) {
		logger.info("Method : addWalkinUserRegistration starts"); // int n = 8;
		// String pass = AutoPasswordGenerator.getAlphaNumericString(n); String
		String pass = "$2a$10$SkYOZORZ4PUSURzL1fmvk.RcUwCfLk/R826sxBXKx/ZZyoQvkcaa6";
		String userId = "";
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		try {
			String ccode = null;
			String scode = null;
			if (regdModel.getCountryCode() != null) {

				ccode = regdModel.getCountryCode().toString();
				ccode = ccode.substring(0, 2);
				scode = regdModel.getStateCode().toString();
				scode = scode.substring(0, 2);
			} else {
				ccode = "10";
				scode = "50";
			}

			String randomNumber = generateRandom(12);
			String genCode = ccode.concat(scode).concat(randomNumber);

			BigInteger pId = new BigInteger(genCode);
			regdModel.setPatientId(pId);

			
			 * if (pass != null && pass != "") { pass = passwordEncoder.encode(pass);
			 * regdModel.setPassword(pass); }
			 
			regdModel.setPassword(pass);

			userId = (String) session.getAttribute("USER_ID");
			regdModel.setCreatedBy(userId);

			MultipartFile inputFile = (MultipartFile) session.getAttribute("registerProfileImg");
			byte[] bytes;
			String imageName = null;

			if (inputFile != null) {
				try {
					bytes = inputFile.getBytes();
					String[] fileType = inputFile.getContentType().split("/");
					imageName = saveAllImage(bytes, fileType[1]);
					System.out.println(imageName);

					regdModel.setFileProfileimg(imageName);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			resp = restTemplate.postForObject(env.getUserUrl() + "rest-addUserRegistration", regdModel,
					JsonResponse.class);
			ObjectMapper mapper = new ObjectMapper();

			List<UserRegistrationModel> seat = mapper.convertValue(resp.getBody(),
					new TypeReference<List<UserRegistrationModel>>() {
					});

			resp.setBody(seat);
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}
		logger.info("Method : addWalkinUserRegistration ends");

		return resp;
	}*/

	// Upload image
	public String saveAllImage(byte[] imageBytes, String ext) {
		logger.info("Method : saveAllImage starts");

		String imageName = null;

		try {

			if (imageBytes != null) {
				long nowTime = new Date().getTime();
				if (ext.contentEquals("jpeg")) {
					imageName = nowTime + ".jpg";
				} else {
					imageName = nowTime + "." + ext;
				}

			}

			Path path = Paths.get(env.getFileUploadEmployee() + imageName);
			if (imageBytes != null) {
				Files.write(path, imageBytes);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : saveAllImage ends");
		return imageName;
	}

	/************************** profile picture upload ***************************/
	@PostMapping("/view-walkin-user-upload-profileImage")
	public @ResponseBody JsonResponse<Object> uploadProfileImg(@RequestParam("file") MultipartFile inputFile,
			HttpSession session) {
		logger.info("Method : user uploadimage controller  starts");

		JsonResponse<Object> response = new JsonResponse<Object>();

		try {
			response.setMessage(inputFile.getOriginalFilename());
			session.setAttribute("registerProfileImg", inputFile);

		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : user uploadimage controller ' ends");
		return response;
	}

	@PostMapping("/view-walkin-user-delete-profileImage")
	public @ResponseBody JsonResponse<Object> deleteFile(HttpSession session) {
		logger.info("Method : deleteFile register uploadimage controller starts");

		JsonResponse<Object> response = new JsonResponse<Object>();

		try {
			session.removeAttribute("registerProfileImg");
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : deleteFile register uploadimage controller ends");
		return response;
	}

}
