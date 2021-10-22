package nirmalya.aathithya.webmodule.doctor.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.common.pagination.DataTableRequest;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.common.utils.PdfGeneratatorUtil;
import nirmalya.aathithya.webmodule.doctor.model.DoctorProfileDocumentModel;
import nirmalya.aathithya.webmodule.doctor.model.DoctorProfileModel;
import nirmalya.aathithya.webmodule.patient.model.LabTestReportModel;

@Controller
@RequestMapping(value = { "doctor/" })
public class DoctorProfileController {
	Logger logger = LoggerFactory.getLogger(DoctorProfileController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@Autowired
	PdfGeneratatorUtil pdfGeneratorUtil;

	
	@GetMapping("view-profile-doctor")
	public String viewMyPatientProfile(Model model, HttpSession session) {

		logger.info("Method : viewMyPatient starts");
		// drop down for country list
		try {
			DropDownModel[] country = restTemplate.getForObject(env.getDoctorUrl() + "getCountryLists-doctor",
					DropDownModel[].class);
			List<DropDownModel> countryList = Arrays.asList(country);
			model.addAttribute("countryList", countryList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	
		
		logger.info("Method : viewMyPatient ends");
		return "doctor/doctor-profile";
	}
	
	/*
	 * FOR EDIT DOCTOR PROFILE
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("view-profile-doctor-edit")
	public @ResponseBody JsonResponse<DoctorProfileModel> editdoctorProfile(HttpSession session) {

		logger.info("Method : editdoctorProfile starts");
		
		JsonResponse<DoctorProfileModel> response = new JsonResponse<DoctorProfileModel>();
		String userId = "";
		try {
			
			
			userId = (String) session.getAttribute("USER_ID");
			
			response = restTemplate.getForObject(env.getDoctorUrl() + "editdoctorProfiles?id=" + userId,
					JsonResponse.class);
				
		} catch (

		RestClientException e) {
			e.printStackTrace();
		}
		
		
		
		ObjectMapper mapper = new ObjectMapper();

		DoctorProfileModel customer = mapper.convertValue(response.getBody(),
				new TypeReference<DoctorProfileModel>() {
				});
	
		String drProfDoc=null;
		if (customer.getProfileImg() != null && customer.getProfileImg() != ""
				&& !customer.getProfileImg().equals("null")) {
			
			drProfDoc = env.getBaseURL() + "document/profile/"+customer.getProfileImg();
			customer.setProfileImg(drProfDoc);
			//System.out.println("Image"+drProfDoc);
		}
		response.setBody(customer);
		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}
		logger.info("Method : editdoctorProfile ends");
		return response;
	}
	
	/*
	 * FOR EDIT DOCTOR IDENTIFICATION
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("view-profile-doctor-identification")
	public @ResponseBody JsonResponse<DoctorProfileModel> editdoctorIdentification(HttpSession session,Model model) {

		logger.info("Method : editdoctorIdentification starts");
		
		JsonResponse<DoctorProfileModel> response = new JsonResponse<DoctorProfileModel>();
		String userId = "";
		try {
			
			
			userId = (String) session.getAttribute("USER_ID");
			
			response = restTemplate.getForObject(env.getDoctorUrl() + "editdoctorIdentifications?id=" + userId,
					JsonResponse.class);
		} catch (

		RestClientException e) {
			e.printStackTrace();
		}
		
		
		
		ObjectMapper mapper = new ObjectMapper();

		DoctorProfileModel customer = mapper.convertValue(response.getBody(),
				new TypeReference<DoctorProfileModel>() {
				});
		String drProfDoc=null;
		if (customer.getDigitalSign() != null && customer.getDigitalSign() != ""
				&& !customer.getDigitalSign().equals("null")) {
			
			drProfDoc = env.getBaseURL() + "document/profile/"+customer.getDigitalSign();
			customer.setDrProfDoc(drProfDoc);
			//System.out.println("Image"+drProfDoc);
		}
		
		response.setBody(customer);
		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}
		logger.info("Method : editdoctorIdentification ends");
		return response;
	}
	
	/*
	 * FOR Home Address Edit
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("view-profile-doctor-home-address")
	public @ResponseBody JsonResponse<DoctorProfileModel> editdoctorHomeAdress(HttpSession session) {

		logger.info("Method : editdoctorHomeAdress starts");
		
		JsonResponse<DoctorProfileModel> response = new JsonResponse<DoctorProfileModel>();
		String userId = "";
		try {
			
			
			userId = (String) session.getAttribute("USER_ID");
			
			response = restTemplate.getForObject(env.getDoctorUrl() + "editdoctorHomeAdress?id=" + userId,
					JsonResponse.class);
				
		} catch (

		RestClientException e) {
			e.printStackTrace();
		}
		
		
		ObjectMapper mapper = new ObjectMapper();

		DoctorProfileModel customer = mapper.convertValue(response.getBody(),
				new TypeReference<DoctorProfileModel>() {
				});
	
		
		response.setBody(customer);
		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}
		logger.info("Method : editdoctorHomeAdress ends");
		return response;
	}
	
	/*
	 * FOR Home Address View
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("view-profile-doctor-home-address-view")
	public @ResponseBody JsonResponse<DoctorProfileModel> viewdoctorHomeAdress(HttpSession session) {

		logger.info("Method : viewdoctorHomeAdress starts");
		
		JsonResponse<DoctorProfileModel> response = new JsonResponse<DoctorProfileModel>();
		String userId = "";
		try {
			
			
			userId = (String) session.getAttribute("USER_ID");
			
			response = restTemplate.getForObject(env.getDoctorUrl() + "viewdoctorHomeAdress?id=" + userId,
					JsonResponse.class);
				
		} catch (

		RestClientException e) {
			e.printStackTrace();
		}
		
		
		ObjectMapper mapper = new ObjectMapper();

		DoctorProfileModel customer = mapper.convertValue(response.getBody(),
				new TypeReference<DoctorProfileModel>() {
				});
	
		
		response.setBody(customer);
		if (response.getMessage() != null && response.getMessage() != "") {
			response.setCode(response.getMessage());
			response.setMessage("Unsuccess");

		} else {
			response.setMessage("Success");
		}
	logger.info("Method : viewdoctorHomeAdress ends");
		return response;
	}
	/*
	 * ADD FOR DOCTOR PROFILE
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/view-profile-doctor-add")
	public @ResponseBody JsonResponse<Object> addDoctorProfile(Model model, HttpSession session,

			@RequestBody DoctorProfileModel doctorProfileModel) {

		logger.info("Method : addDoctorProfile starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		Integer pId = new Integer(userId);

		doctorProfileModel.setDoctorId(pId);
		MultipartFile inputFile = (MultipartFile) session.getAttribute("ProfileImgDoc");
		byte[] bytes;
		String imageName = null;

		
		if (inputFile != null) {
			try {
				bytes = inputFile.getBytes();
				String[] fileType = inputFile.getContentType().split("/");
				imageName = saveAllImage(bytes, fileType[1]);
				System.out.println(imageName);

				doctorProfileModel.setProfileImg(imageName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		try {
			resp = restTemplate.postForObject(env.getDoctorUrl() + "addDoctorProfile", doctorProfileModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : addDoctorProfile ends");
		return resp;
	}
	//Upload image
		public String setFileProfileimg(byte[] imageBytes, String ext) {
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
	
	/*
	 * save Digital Signature
	 */

	@PostMapping("view-profile-doctor-upload-file")
	public @ResponseBody JsonResponse<Object> uploadFile(@RequestParam("file") MultipartFile inputFile,
			HttpSession session) {
		logger.info("Method : uploadFile controller function 'post-mapping' starts");
		JsonResponse<Object> response = new JsonResponse<Object>();

		try {
			response.setMessage(inputFile.getOriginalFilename());
			session.setAttribute("quotationPFile", inputFile);

		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method : uploadFile controller function 'post-mapping' ends");
		return response;
	}
	/*
	 * ADD FOR DOCTOR IDENTIFICATION
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/view-profile-doctor-add-identification")
	public @ResponseBody JsonResponse<Object> addDoctorIdentification(Model model, HttpSession session,

			@RequestBody DoctorProfileModel doctorProfileModel) {

		logger.info("Method : addDoctorIdentification starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		Integer pId = new Integer(userId);

		doctorProfileModel.setDoctorId(pId);
		
		MultipartFile inputFile = (MultipartFile) session.getAttribute("quotationPFile");
		byte[] bytes;
		String imageName = null;

		if (inputFile != null) {
			try {
				bytes = inputFile.getBytes();
				String[] fileType = inputFile.getContentType().split("/");
				imageName = saveAllImage(bytes, fileType[1]);

				doctorProfileModel.setDigitalSign(imageName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			resp = restTemplate.postForObject(env.getDoctorUrl() + "addDoctorIdentification", doctorProfileModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : addDoctorIdentification ends");
		return resp;
	}

	private String saveAllImage(byte[] imageBytes, String ext) {
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
	
	/*
	 * ADD FOR DOCTOR HOME ADDRESS
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/view-profile-doctor-contact-details-add")
	public @ResponseBody JsonResponse<Object> addDoctorContactDetails(Model model, HttpSession session,

			@RequestBody DoctorProfileModel doctorProfileModel) {

		logger.info("Method : addDoctorContactDetails starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		Integer pId = new Integer(userId);

		doctorProfileModel.setDoctorId(pId);
		
		try {
			resp = restTemplate.postForObject(env.getDoctorUrl() + "addDoctorContactDetails", doctorProfileModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != "" && resp.getMessage() != null) {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("Success");
		}

		logger.info("Method : addDoctorContactDetails ends");
		return resp;
	}
	// dropdown for state list

		@SuppressWarnings("unchecked")

		@GetMapping(value = { "/view-profile-doctor-getStateList" })
		public @ResponseBody JsonResponse<Object> getStateList(@RequestParam Integer country) {
			logger.info("Method : getStateList starts");
			JsonResponse<Object> res = new JsonResponse<Object>();
			try {
				res = restTemplate.getForObject(env.getDoctorUrl() + "rest-getStateList-doctor?id=" + country, JsonResponse.class);
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

		@GetMapping(value = { "view-profile-doctor-getDistList" })
		public @ResponseBody JsonResponse<Object> getDistLists(@RequestParam String state) {
			logger.info("Method : getDistList starts");
			JsonResponse<Object> res = new JsonResponse<Object>();
			try {
				res = restTemplate.getForObject(env.getDoctorUrl() + "rest-getDistList-doctor?state=" + state, JsonResponse.class);
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

		@GetMapping(value = { "view-profile-doctor-getCityList" })
		public @ResponseBody JsonResponse<Object> getCityList(@RequestParam String dist) {
			logger.info("Method : getCityList starts");
			JsonResponse<Object> res = new JsonResponse<Object>();
			try {
				res = restTemplate.getForObject(env.getDoctorUrl() + "rest-getCityListdoctor?dist=" + dist, JsonResponse.class);
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
		//Upload image
		public String saveAllImages(byte[] imageBytes, String ext) {
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
		@PostMapping("/view-profile-doctor-upload-profileImage")
		public @ResponseBody JsonResponse<Object> uploadProfileImg(@RequestParam("file") MultipartFile inputFile,
				HttpSession session) {
			logger.info("Method : user uploadimage controller  starts");

			JsonResponse<Object> response = new JsonResponse<Object>();

			try {
				response.setMessage(inputFile.getOriginalFilename());
				session.setAttribute("ProfileImgDoc", inputFile);

			} catch (RestClientException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			logger.info("Method : user uploadimage controller ' ends");
			return response;
		}

		/*
		 * Add Document Upload 
		 *
		 */
		@SuppressWarnings("unchecked")
		@PostMapping(value = "view-profile-doctor-data-upload-add")
		public @ResponseBody JsonResponse<Object> addDocDocumentUpload(
				@RequestBody List<DoctorProfileModel> doctorProfileModel,HttpSession session, Model model) {
			logger.info("Method : add Doc Document Upload starts");

			JsonResponse<Object> res = new JsonResponse<Object>();
			List<DoctorProfileModel> documentList = new ArrayList<DoctorProfileModel>();
			List<DoctorProfileDocumentModel> docList = new ArrayList<DoctorProfileDocumentModel>();
			String userId = "";
			//String roleId = "";
			
			ArrayList<String> demo = new ArrayList<String>();

			
			try {
				userId = (String) session.getAttribute("USER_ID");
				demo = (ArrayList<String>) session.getAttribute("USER_ROLES");

			} catch (Exception e) {
				e.printStackTrace();
			}
				Integer pId = new Integer(userId);
				for (DoctorProfileModel s : doctorProfileModel) {
					s.setDoctorId(pId);
					s.setRoleId(demo.get(0));
				}

			
			for (DoctorProfileDocumentModel a : doctorProfileModel.get(0).getDocumentList()) {

				if (a.getImageNameEdit() != null && a.getImageNameEdit() != "") {
					a.setFileName(a.getImageNameEdit());
				} else {
					if (a.getFileName() != null && a.getFileName() != "") {
						String delimiters = "\\.";
						String[] x = a.getFileName().split(delimiters);

						if (x[1].contentEquals("png") || x[1].contentEquals("jpg") || x[1].contentEquals("jpeg")) {

							for (String s1 : a.getDocumentFile()) {
								if (s1 != null)
									try {
										byte[] bytes = Base64.getDecoder().decode(s1);
										String imageName = fileUpload.saveAllImage(bytes);
										a.setFileName(imageName);
									} catch (Exception e) {
										e.printStackTrace();
									}
							}
						} else if (x[1].contentEquals("pdf")) {
							for (String s1 : a.getDocumentFile()) {
								try {
									byte[] bytes = Base64.getDecoder().decode(s1);
									String pdfName = fileUpload.saveAllPdf(bytes);
									a.setFileName(pdfName);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						} else if (x[1].contentEquals("docx")) {
							for (String s1 : a.getDocumentFile()) {
								try {
									byte[] bytes = Base64.getDecoder().decode(s1);
									String pdfName = fileUpload.saveAllDocx(bytes);
									a.setFileName(pdfName);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						} else if (x[1].contentEquals("doc")) {
							for (String s1 : a.getDocumentFile()) {
								try {
									byte[] bytes = Base64.getDecoder().decode(s1);
									String pdfName = fileUpload.saveAllDoc(bytes);
									a.setFileName(pdfName);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						} else if (x[1].contentEquals("xls")) {
							for (String s1 : a.getDocumentFile()) {
								try {
									byte[] bytes = Base64.getDecoder().decode(s1);
									String pdfName = fileUpload.saveAllXls(bytes);
									a.setFileName(pdfName);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						} else if (x[1].contentEquals("xlsx")) {
							for (String s1 : a.getDocumentFile()) {
								try {
									byte[] bytes = Base64.getDecoder().decode(s1);
									String pdfName = fileUpload.saveAllXlsx(bytes);
									a.setFileName(pdfName);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
			try {

				res = restTemplate.postForObject(env.getDoctorUrl() + "rest-adddrDocumentUpload", doctorProfileModel,
						JsonResponse.class);
				
			}

			catch (RestClientException e) {
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();
			documentList = mapper.convertValue(res.getBody(), new TypeReference<List<DoctorProfileModel>>() {
			});
			
			if (documentList != null) {

			
				docList = doctorProfileModel.get(0).getDocumentList();
				for (DoctorProfileDocumentModel m : docList) {
					if (m.getFileName() != null && m.getFileName() != "") {
						String[] extension = m.getFileName().split("\\.");
						if (extension.length == 2) {
							if (extension[1].equals("xls") || extension[1].equals("xlsx")) {

								String docPath = "<i class=\"fa fa-file-excel-o excel\" title= " + m.getFileName()
										+ "></i> ";

								m.setAction(docPath);
							}
							if (extension[1].equals("pdf")) {
								String docPath = " <i class=\"fa fa-file-pdf-o excel pdf\"   title=" + m.getFileName()
										+ " ;></i> ";

								m.setAction(docPath);
							}
							if (extension[1].equals("doc") || extension[1].equals("dox") || extension[1].equals("docx")) {
								String docPath = " <i class=\"fa fa-file-word-o \" aria-hidden=\"true\"  title="
										+ m.getFileName() + "></i> ";
								m.setAction(docPath);
							}
							if (extension[1].equals("png") || extension[1].equals("jpg") || extension[1].equals("jpeg")) {
								String docPath = " <i class=\"fa fa-picture-o \"\" aria-hidden=\"true\" title="
										+ m.getFileName() + "></i>  ";
								m.setAction(docPath);
							}
						} else {
							m.setAction("N/A");
						}
					} else {
						m.setAction("N/A");
					}
					m.setAction("<a href=\"/document/profile/" + m.getFileName() + "\" target=\"_blank\" >"
							+ m.getAction() + "</a>");

				}
			}
			String message = res.getMessage();
			doctorProfileModel.get(0).setDocumentList(docList);
			if (message != null && message != "") {

			} else {
				res.setMessage("Success");
			}
			res.setBody(documentList);
				System.out.println("@@@@@@@"+res);
			logger.info("Method :  add Doc Document Upload Ends");
			return res;
		}
		
		/*
		 * view Document Attachment
		 */
		@SuppressWarnings("unchecked")
		@GetMapping("view-profile-doctor-my-attachment")
		public @ResponseBody JsonResponse<List<DoctorProfileModel>> viewDocumentAttachment(HttpSession session) {

			logger.info("Method :viewDocumentAttachment starts");

			JsonResponse<List<DoctorProfileModel>> resp = new JsonResponse<List<DoctorProfileModel>>();
			String userId = "";
			ArrayList<String> demo = new ArrayList<String>();

			try {
				userId = (String) session.getAttribute("USER_ID");
				demo = (ArrayList<String>) session.getAttribute("USER_ROLES");

				resp = restTemplate.getForObject(env.getDoctorUrl() + "rest-viewDocumentAttachment?id=" + userId + "&rolid=" + demo.get(0),
						JsonResponse.class);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			ObjectMapper mapper = new ObjectMapper();

			List<DoctorProfileModel> patientHistoryModel = mapper.convertValue(resp.getBody(),
					new TypeReference<List<DoctorProfileModel>>() {
					});
			resp.setBody(patientHistoryModel);
			if (resp.getMessage() != "" && resp.getMessage() != null) {
				resp.setCode(resp.getMessage());
				resp.setMessage("Unsuccess");
			} else {
				resp.setMessage("Success");
			}
			logger.info("Method :viewDocumentAttachment ends");
			return resp;
		}
	/*
	 * document Download	
	 */
		
		@GetMapping(value = { "view-profile-doctor-pdf-download" })
		public void AttachmentDownload(HttpServletResponse response, Model model,HttpSession session) {
			logger.info("Method : AttachmentDownload starts");

			String userId = "";

			try {
				userId = (String) session.getAttribute("USER_ID");
			} catch (Exception e) {
				e.printStackTrace();
			}
			DoctorProfileDocumentModel title = new DoctorProfileDocumentModel();
			
			try {

				userId = (String) session.getAttribute("USER_ID");
				title = restTemplate.getForObject(env.getDoctorUrl() + "rest-attachmentDownload?id=" + userId, DoctorProfileDocumentModel.class);
							System.out.println("eeeee"+userId);
			} catch (

			RestClientException e) {
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();

			DoctorProfileDocumentModel gatPdfDownload = mapper.convertValue(title,
					new TypeReference<DoctorProfileDocumentModel>() {
					});
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("gatPdfDownload", gatPdfDownload);
			System.out.println("getpdfdownload"+gatPdfDownload);
			System.out.println("download"+data);
	
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline; filename=AttachmentDownload.pdf");
			File file;
			byte[] fileData = null;
			try {
				file = pdfGeneratorUtil.createPdf("patient/document-image-download", data);
				InputStream in = new FileInputStream(file);
				fileData = IOUtils.toByteArray(in);
				response.setContentLength(fileData.length);
				response.getOutputStream().write(fileData);
				response.getOutputStream().flush();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("Method : AttachmentDownload ends");
		}
		
		
}
