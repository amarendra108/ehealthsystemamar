package nirmalya.aathithya.webmodule.chemist.controller;

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

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.common.utils.PdfGeneratatorUtil;
import nirmalya.aathithya.webmodule.chemist.model.ChemistProfileDocumentModel;
import nirmalya.aathithya.webmodule.chemist.model.ChemistProfileModel;

@Controller
@RequestMapping(value = { "chemist/" })
public class ChemistProfileController {
	Logger logger = LoggerFactory.getLogger(ChemistProfileController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	
	@Autowired
	PdfGeneratatorUtil pdfGeneratorUtil;

	
	@GetMapping("my-profile-chemist")
	public String viewMyPatientProfile(Model model, HttpSession session) {

		logger.info("Method : viewMyPatient starts");
		// drop down for country list
		try {
			DropDownModel[] country = restTemplate.getForObject(env.getChemistUrl() + "getCountryLists-chemist",
					DropDownModel[].class);
			List<DropDownModel> countryList = Arrays.asList(country);
			model.addAttribute("countryList", countryList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
	
		
		logger.info("Method : viewMyPatient ends");
		return "chemist/chemist-Profile";
	}
	
	/*
	 * FOR EDIT DOCTOR PROFILE
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("my-profile-chemist-edit")
	public @ResponseBody JsonResponse<ChemistProfileModel> editdoctorProfile(HttpSession session) {

		logger.info("Method : editdoctorProfile starts");
		
		JsonResponse<ChemistProfileModel> response = new JsonResponse<ChemistProfileModel>();
		String userId = "";
		try {
			
			
			userId = (String) session.getAttribute("USER_ID");
			
			response = restTemplate.getForObject(env.getChemistUrl() + "editchemistProfiles?id=" + userId,
					JsonResponse.class);
				
		} catch (

		RestClientException e) {
			e.printStackTrace();
		}
		
		
		
		ObjectMapper mapper = new ObjectMapper();

		ChemistProfileModel customer = mapper.convertValue(response.getBody(),
				new TypeReference<ChemistProfileModel>() {
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
	@GetMapping("my-profile-chemist-identification")
	public @ResponseBody JsonResponse<ChemistProfileModel> editdoctorIdentification(HttpSession session,Model model) {

		logger.info("Method : editdoctorIdentification starts");
		
		JsonResponse<ChemistProfileModel> response = new JsonResponse<ChemistProfileModel>();
		String userId = "";
		try {
			
			
			userId = (String) session.getAttribute("USER_ID");
			
			response = restTemplate.getForObject(env.getChemistUrl() + "editchemistIdentifications?id=" + userId,
					JsonResponse.class);
		} catch (

		RestClientException e) {
			e.printStackTrace();
		}
		
		
		
		ObjectMapper mapper = new ObjectMapper();

		ChemistProfileModel customer = mapper.convertValue(response.getBody(),
				new TypeReference<ChemistProfileModel>() {
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
	@GetMapping("my-profile-chemist-home-address")
	public @ResponseBody JsonResponse<ChemistProfileModel> editdoctorHomeAdress(HttpSession session) {

		logger.info("Method : editdoctorHomeAdress starts");
		
		JsonResponse<ChemistProfileModel> response = new JsonResponse<ChemistProfileModel>();
		String userId = "";
		try {
			
			
			userId = (String) session.getAttribute("USER_ID");
			
			response = restTemplate.getForObject(env.getChemistUrl() + "editchemistHomeAdress?id=" + userId,
					JsonResponse.class);
				
		} catch (

		RestClientException e) {
			e.printStackTrace();
		}
		
		
		ObjectMapper mapper = new ObjectMapper();

		ChemistProfileModel customer = mapper.convertValue(response.getBody(),
				new TypeReference<ChemistProfileModel>() {
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
	@GetMapping("my-profile-chemist-home-address-view")
	public @ResponseBody JsonResponse<ChemistProfileModel> viewdoctorHomeAdress(HttpSession session) {

		logger.info("Method : viewdoctorHomeAdress starts");
		
		JsonResponse<ChemistProfileModel> response = new JsonResponse<ChemistProfileModel>();
		String userId = "";
		try {
			
			
			userId = (String) session.getAttribute("USER_ID");
			
			response = restTemplate.getForObject(env.getChemistUrl() + "viewchemistHomeAdress?id=" + userId,
					JsonResponse.class);
				
		} catch (

		RestClientException e) {
			e.printStackTrace();
		}
		
		
		ObjectMapper mapper = new ObjectMapper();

		ChemistProfileModel customer = mapper.convertValue(response.getBody(),
				new TypeReference<ChemistProfileModel>() {
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
	@PostMapping("/my-profile-chemist-add")
	public @ResponseBody JsonResponse<Object> addDoctorProfile(Model model, HttpSession session,

			@RequestBody ChemistProfileModel ChemistProfileModel) {

		logger.info("Method : addDoctorProfile starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		Integer pId = new Integer(userId);

		ChemistProfileModel.setDoctorId(pId);
		MultipartFile inputFile = (MultipartFile) session.getAttribute("ProfileImgDoc");
		byte[] bytes;
		String imageName = null;

		
		if (inputFile != null) {
			try {
				bytes = inputFile.getBytes();
				String[] fileType = inputFile.getContentType().split("/");
				imageName = saveAllImage(bytes, fileType[1]);
				System.out.println(imageName);

				ChemistProfileModel.setProfileImg(imageName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		try {
			resp = restTemplate.postForObject(env.getChemistUrl() + "addchemistProfile", ChemistProfileModel,
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

	@PostMapping("my-profile-chemist-upload-file")
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
	@PostMapping("/my-profile-chemist-add-identification")
	public @ResponseBody JsonResponse<Object> addDoctorIdentification(Model model, HttpSession session,

			@RequestBody ChemistProfileModel ChemistProfileModel) {

		logger.info("Method : addDoctorIdentification starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		Integer pId = new Integer(userId);

		ChemistProfileModel.setDoctorId(pId);
		
		MultipartFile inputFile = (MultipartFile) session.getAttribute("quotationPFile");
		byte[] bytes;
		String imageName = null;

		if (inputFile != null) {
			try {
				bytes = inputFile.getBytes();
				String[] fileType = inputFile.getContentType().split("/");
				imageName = saveAllImage(bytes, fileType[1]);

				ChemistProfileModel.setDigitalSign(imageName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			resp = restTemplate.postForObject(env.getChemistUrl() + "addchemistIdentification", ChemistProfileModel,
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
	@PostMapping("/my-profile-chemist-contact-details-add")
	public @ResponseBody JsonResponse<Object> addDoctorContactDetails(Model model, HttpSession session,

			@RequestBody ChemistProfileModel ChemistProfileModel) {

		logger.info("Method : addDoctorContactDetails starts");
		
		JsonResponse<Object> resp = new JsonResponse<Object>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");

		} catch (Exception e) {
			e.printStackTrace();
		}

		Integer pId = new Integer(userId);

		ChemistProfileModel.setDoctorId(pId);
		
		try {
			resp = restTemplate.postForObject(env.getChemistUrl() + "addchemistContactDetails", ChemistProfileModel,
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

		@GetMapping(value = { "/my-profile-chemist-getStateList" })
		public @ResponseBody JsonResponse<Object> getStateList(@RequestParam Integer country) {
			logger.info("Method : getStateList starts");
			JsonResponse<Object> res = new JsonResponse<Object>();
			try {
				res = restTemplate.getForObject(env.getChemistUrl() + "rest-getStateList-chemist?id=" + country, JsonResponse.class);
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

		@GetMapping(value = { "my-profile-chemist-getDistList" })
		public @ResponseBody JsonResponse<Object> getDistLists(@RequestParam String state) {
			logger.info("Method : getDistList starts");
			JsonResponse<Object> res = new JsonResponse<Object>();
			try {
				res = restTemplate.getForObject(env.getChemistUrl() + "rest-getDistList-chemist?state=" + state, JsonResponse.class);
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

		@GetMapping(value = { "my-profile-chemist-getCityList" })
		public @ResponseBody JsonResponse<Object> getCityList(@RequestParam String dist) {
			logger.info("Method : getCityList starts");
			JsonResponse<Object> res = new JsonResponse<Object>();
			try {
				res = restTemplate.getForObject(env.getChemistUrl() + "rest-getCityListchemist?dist=" + dist, JsonResponse.class);
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
		@PostMapping("/my-profile-chemist-upload-profileImage")
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
		@PostMapping(value = "my-profile-chemist-data-upload-add")
		public @ResponseBody JsonResponse<Object> addDocDocumentUpload(
				@RequestBody List<ChemistProfileModel> ChemistProfileModel,HttpSession session, Model model) {
			logger.info("Method : add Doc Document Upload starts");

			JsonResponse<Object> res = new JsonResponse<Object>();
			List<ChemistProfileModel> documentList = new ArrayList<ChemistProfileModel>();
			List<ChemistProfileDocumentModel> docList = new ArrayList<ChemistProfileDocumentModel>();
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
				for (ChemistProfileModel s : ChemistProfileModel) {
					s.setDoctorId(pId);
					s.setRoleId(demo.get(0));
				}

			
			for (ChemistProfileDocumentModel a : ChemistProfileModel.get(0).getDocumentList()) {

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

				res = restTemplate.postForObject(env.getChemistUrl() + "rest-adddrDocumentUpload-chemist", ChemistProfileModel,
						JsonResponse.class);
				
			}

			catch (RestClientException e) {
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();
			documentList = mapper.convertValue(res.getBody(), new TypeReference<List<ChemistProfileModel>>() {
			});
			
			if (documentList != null) {

			
				docList = ChemistProfileModel.get(0).getDocumentList();
				for (ChemistProfileDocumentModel m : docList) {
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
			ChemistProfileModel.get(0).setDocumentList(docList);
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
		@GetMapping("my-profile-chemist-my-attachment")
		public @ResponseBody JsonResponse<List<ChemistProfileModel>> viewDocumentAttachment(HttpSession session) {

			logger.info("Method :viewDocumentAttachment starts");

			JsonResponse<List<ChemistProfileModel>> resp = new JsonResponse<List<ChemistProfileModel>>();
			String userId = "";
			ArrayList<String> demo = new ArrayList<String>();

			try {
				userId = (String) session.getAttribute("USER_ID");
				demo = (ArrayList<String>) session.getAttribute("USER_ROLES");

				resp = restTemplate.getForObject(env.getChemistUrl() + "rest-viewDocumentAttachment-chemist?id=" + userId + "&rolid=" + demo.get(0),
						JsonResponse.class);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			ObjectMapper mapper = new ObjectMapper();

			List<ChemistProfileModel> patientHistoryModel = mapper.convertValue(resp.getBody(),
					new TypeReference<List<ChemistProfileModel>>() {
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
		
		@GetMapping(value = { "my-profile-chemist-pdf-download" })
		public void AttachmentDownload(HttpServletResponse response, Model model,HttpSession session) {
			logger.info("Method : AttachmentDownload starts");

			String userId = "";

			try {
				userId = (String) session.getAttribute("USER_ID");
			} catch (Exception e) {
				e.printStackTrace();
			}
			ChemistProfileDocumentModel title = new ChemistProfileDocumentModel();
			
			try {

				userId = (String) session.getAttribute("USER_ID");
				title = restTemplate.getForObject(env.getChemistUrl() + "rest-attachmentDownload-chemist?id=" + userId, ChemistProfileDocumentModel.class);
							System.out.println("eeeee"+userId);
			} catch (

			RestClientException e) {
				e.printStackTrace();
			}
			ObjectMapper mapper = new ObjectMapper();

			ChemistProfileDocumentModel gatPdfDownload = mapper.convertValue(title,
					new TypeReference<ChemistProfileDocumentModel>() {
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
