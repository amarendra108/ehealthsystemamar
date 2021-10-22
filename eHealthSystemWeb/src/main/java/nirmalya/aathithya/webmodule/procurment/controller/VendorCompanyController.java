package nirmalya.aathithya.webmodule.procurment.controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

import nirmalya.aathithya.webmodule.common.utils.ActivitylogModel;
import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EmailAttachmentSender;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.common.utils.MailService;
import nirmalya.aathithya.webmodule.employee.model.ManageEmployeeBankDetailsModel;
import nirmalya.aathithya.webmodule.master.model.LocationMasterModel;
import nirmalya.aathithya.webmodule.master.model.VendorContactMasterModel;
import nirmalya.aathithya.webmodule.master.model.VendorDocumentMaster;
import nirmalya.aathithya.webmodule.master.model.VendorLocationMasterModel;
import nirmalya.aathithya.webmodule.master.model.VendorMasterModel;

/**
 * @author NirmalyaLabs
 *
 */
@Controller
@RequestMapping(value = { "inventory/" })
public class VendorCompanyController {
	Logger logger = LoggerFactory.getLogger(VendorCompanyController.class);

	@Autowired
	RestTemplate restClient;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	PasswordEncoder pw;

	@Autowired
	MailService mailService;

	@Autowired
	FileUpload fileUpload;

	@GetMapping(value = { "vendor-company" })
	public String manageVendor(Model model, HttpSession session) {
		logger.info("Method : manageVenor starts");
		try {
			DropDownModel[] locationType = restClient.getForObject(env.getMasterUrl() + "getLocationTypeList",
					DropDownModel[].class);
			List<DropDownModel> locationTypeList = Arrays.asList(locationType);

			model.addAttribute("locationTypeList", locationTypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		try {

			DropDownModel[] jobType = restClient.getForObject(env.getRecruitment() + "jobTypeList",
					DropDownModel[].class);
			List<DropDownModel> jobTypeList = Arrays.asList(jobType);
			model.addAttribute("jobTypeList", jobTypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {

			DropDownModel[] location = restClient.getForObject(env.getRecruitment() + "jobLocationList",
					DropDownModel[].class);
			List<DropDownModel> jobLocationList = Arrays.asList(location);
			model.addAttribute("jobLocationList", jobLocationList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {

			DropDownModel[] department = restClient.getForObject(env.getRecruitment() + "DepartmentList",
					DropDownModel[].class);
			List<DropDownModel> departmentList = Arrays.asList(department);
			model.addAttribute("DepartmentList", departmentList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] category = restClient.getForObject(env.getMasterUrl() + "getCategoryList",
					DropDownModel[].class);
			List<DropDownModel> categoryTypeList = Arrays.asList(category);

			model.addAttribute("categoryTypeList", categoryTypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] country = restClient.getForObject(env.getMasterUrl() + "getCountryListForLocation",
					DropDownModel[].class);
			List<DropDownModel> countryList = Arrays.asList(country);

			model.addAttribute("countryList", countryList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		try {
			DropDownModel[] documentType = restClient.getForObject(env.getMasterUrl() + "getDocumentTypeList",
					DropDownModel[].class);
			List<DropDownModel> documentTypeList = Arrays.asList(documentType);

			model.addAttribute("documentTypeList", documentTypeList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		try {
			DropDownModel[] country = restClient.getForObject(env.getEmployeeUrl() + "getCountryList",
					DropDownModel[].class);
			List<DropDownModel> counntryList = Arrays.asList(country);

			model.addAttribute("counntryList", counntryList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		 
//		try {
//			DropDownModel[] city = restClient.getForObject(env.getEmployeeUrl() + "getcityList1",
//					DropDownModel[].class);
//			List<DropDownModel> cityList = Arrays.asList(city);
//
//			model.addAttribute("cityList", cityList);
//		} catch (RestClientException e) {
//			e.printStackTrace();
//		}
		try {
			DropDownModel[] bank = restClient.getForObject(env.getEmployeeUrl() + "getBankNameList",
					DropDownModel[].class);
			List<DropDownModel> bankNameList = Arrays.asList(bank);

			model.addAttribute("BankNameList", bankNameList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		try {
			LocationMasterModel[] location = restClient.getForObject(env.getMasterUrl() + "getLocationList",
					LocationMasterModel[].class);
			List<LocationMasterModel> locationList = Arrays.asList(location);

			int count = 0;

			for (LocationMasterModel m : locationList) {
				count = count + 1;
				if (m.getLocVirtual().equals("0")) {
					m.setLocVirtual("No");
				}
				if (m.getLocVirtual().equals("1")) {
					m.setLocVirtual("Yes");
				}
				if (m.getLocStatus().equals("0")) {
					m.setLocStatus("Inactive");
				}
				if (m.getLocStatus().equals("1")) {
					m.setLocStatus("Active");
				}
			}

			model.addAttribute("count", count);

			model.addAttribute("locationList", locationList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : manageLocation ends");
		return "procurement/vendor-company";
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/vendor-company-master-save")
	public @ResponseBody JsonResponse<Object> saveVendorMaster(@RequestBody VendorMasterModel vendorMasterModel,
			HttpSession session) {
		logger.info("Method : saveVendorMaster starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		vendorMasterModel.setCreatedBy(userId);

		MultipartFile inputFile = (MultipartFile) session.getAttribute("quotationPFile");
		byte[] bytes;
		String imageName = null;

		if (inputFile != null) {
			try {
				bytes = inputFile.getBytes();
				String[] fileType = inputFile.getContentType().split("/");
				imageName = saveAllImage(bytes, fileType[1]);

				vendorMasterModel.setFileVendor(imageName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		try {

			resp = restClient.postForObject(env.getMasterUrl() + "saveVendorMaster", vendorMasterModel,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			session.removeAttribute("quotationPFile");
			resp.setMessage("Success");
		}

		logger.info("Method : saveVendorMaster starts");
		return resp;
	}

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

			Path path = Paths.get(env.getFileUploadMaster() + imageName);
			if (imageBytes != null) {
				Files.write(path, imageBytes);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : saveAllImage ends");
		return imageName;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/vendor-company-master-location-save")
	public @ResponseBody JsonResponse<Object> saveVendorLocationMaster(
			@RequestBody VendorLocationMasterModel vendorLocationMasterModel, HttpSession session) {
		logger.info("Method : saveVendorLocationMaster starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		vendorLocationMasterModel.setCreatedBy(userId);

		try {
			resp = restClient.postForObject(env.getMasterUrl() + "saveVendorLocationMaster", vendorLocationMasterModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : saveVendorLocationMaster starts");
		return resp;
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = { "vendor-company-master-get-state-list" })
	public @ResponseBody JsonResponse<Object> getStateNameForLocationVendor(Model model, @RequestBody String tCountry,
			BindingResult result) {
		logger.info("Method : getStateNameForLocation starts");

		JsonResponse<Object> res = new JsonResponse<Object>();

		try {
			res = restClient.getForObject(env.getMasterUrl() + "getStateListForLoc?id=" + tCountry, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}

		logger.info("Method : getStateNameForLocation ends");
		return res;

	}

	@SuppressWarnings("unchecked")
	@GetMapping("vendor-company-master-data-through-ajax")
	public @ResponseBody List<VendorMasterModel> vendorThroughAjax(Model model, HttpServletRequest request,
			HttpSession session) {
		logger.info("Method : vendorThroughAjax starts");

		JsonResponse<List<VendorMasterModel>> jsonResponse = new JsonResponse<List<VendorMasterModel>>();
		String vendorId = "";
		try {
			vendorId = (String) session.getAttribute("VENDOR_ID");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		try {

			jsonResponse = restClient.getForObject(
					env.getMasterUrl() + "get-vendor-list-by-vendor?vendorId=" + vendorId, JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			List<VendorMasterModel> addreq = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<List<VendorMasterModel>>() {
					});
			String dateFormat = (String) (session).getAttribute("DATEFORMAT");
			for (VendorMasterModel m : addreq) {
				String date = m.getCreatedOn();
				if (date != null && date != "") {
					date = DateFormatter.dateFormat(date, dateFormat);
					m.setCreatedOn(date);
				}

			}

			jsonResponse.setBody(addreq);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method ; vendorThroughAjax ends");

		return jsonResponse.getBody();
	}

//vendor location through ajax start
	@SuppressWarnings("unchecked")
	@GetMapping("vendor-company-master-through-ajax")
	public @ResponseBody List<VendorLocationMasterModel> vendorLocationThroughAjax(Model model,
			HttpServletRequest request, @RequestParam String id, HttpSession session) {
		logger.info("Method : vendorLocationThroughAjax starts");

		JsonResponse<List<VendorLocationMasterModel>> jsonResponse = new JsonResponse<List<VendorLocationMasterModel>>();

		try {

			jsonResponse = restClient.getForObject(env.getMasterUrl() + "get-vendor-location-list?id=" + id,
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			List<VendorLocationMasterModel> addreq = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<List<VendorLocationMasterModel>>() {
					});
			String dateFormat = (String) (session).getAttribute("DATEFORMAT");
			for (VendorLocationMasterModel m : addreq) {
				String date = m.getCreatedOn();
				if (date != null && date != "") {
					date = DateFormatter.dateFormat(date, dateFormat);
					m.setCreatedOn(date);
				}

			}

			jsonResponse.setBody(addreq);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method ; vendorLocationThroughAjax ends");

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/vendor-company-master-location-edit")
	public @ResponseBody JsonResponse<Object> editVendorLocationMaster(@RequestBody String vendorId,
			HttpSession session) {
		logger.info("Method : editVendorLocationMaster starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restClient.getForObject(env.getMasterUrl() + "editVendorLoactionById?id=" + vendorId,
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			VendorLocationMasterModel locDetails = mapper.convertValue(resp.getBody(),
					new TypeReference<VendorLocationMasterModel>() {
					});
			try {
				DropDownModel[] state = restClient.getForObject(
						env.getMasterUrl() + "viewStateLocListByCountry?id=" + locDetails.getVendorCountry(),
						DropDownModel[].class);
				List<DropDownModel> stateList = Arrays.asList(state);

				locDetails.setStateList(stateList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {
				DropDownModel[] city = restClient.getForObject(
						env.getMasterUrl() + "viewCityLocListByState?id=" + locDetails.getVendorState(),
						DropDownModel[].class);
				List<DropDownModel> cityList = Arrays.asList(city);

				locDetails.setCityList(cityList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}
			resp.setBody(locDetails);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			resp.setMessage("success");
		}

		logger.info("Method : editVendorLocationMaster starts");
		return resp;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("vendor-company-master-location-delete")
	public @ResponseBody JsonResponse<Object> deleteVendorLocationMaster(Model model, @RequestParam String id,
			@RequestParam String simpleid, @RequestParam String vendorId, HttpSession session) {
		logger.info("Method : deleteVendorLocationMaster starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		String createdBy = "";

		try {
			createdBy = (String) session.getAttribute("USER_ID");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			resp = restClient.getForObject(env.getMasterUrl() + "deleteVendorLocation?id=" + id + "&createdBy="
					+ createdBy + "&simpleid=" + simpleid + "&vendorId=" + vendorId, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("success");
		}

		logger.info("Method : deleteVendorLocationMaster ends");
		return resp;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("vendor-company-master-delete")
	public @ResponseBody JsonResponse<Object> deleteVendorMaster(Model model, @RequestParam String id,
			@RequestParam String simpleid, HttpSession session) {
		logger.info("Method : deleteVendorLocationMaster starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();
		String createdBy = "";

		try {
			createdBy = (String) session.getAttribute("USER_ID");
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			resp = restClient.getForObject(
					env.getMasterUrl() + "deleteVendor?id=" + id + "&createdBy=" + createdBy + "&simpleid=" + simpleid,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() != null && resp.getMessage() != "") {
			resp.setCode(resp.getMessage());
			resp.setMessage("Unsuccess");
		} else {
			resp.setMessage("success");
		}

		logger.info("Method : deleteVendorLocationMaster ends");
		return resp;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/vendor-company-master-edit")
	public @ResponseBody JsonResponse<Object> editVendorMaster(@RequestBody String vendorId, HttpSession session) {
		logger.info("Method : editVendorMaster starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restClient.getForObject(env.getMasterUrl() + "editVendorById?id=" + vendorId, JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			VendorMasterModel vendorMasterModel = mapper.convertValue(resp.getBody(),
					new TypeReference<VendorMasterModel>() {
					});
			try {
				DropDownModel[] category = restClient.getForObject(env.getMasterUrl() + "getCategoryList",
						DropDownModel[].class);
				List<DropDownModel> categoryTypeList = Arrays.asList(category);
				vendorMasterModel.setCategoryList(categoryTypeList);

			} catch (RestClientException e) {
				e.printStackTrace();
			}
			if (vendorMasterModel.getFileVendor() != null && vendorMasterModel.getFileVendor() != ""
					&& !vendorMasterModel.getFileVendor().equals("null")) {
				String fileVendor = env.getBaseURL() + "document/module/" + vendorMasterModel.getFileVendor();

				vendorMasterModel.setFileVendor(fileVendor);
			}

			resp.setBody(vendorMasterModel);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			resp.setMessage("success");
		}

		logger.info("Method : editVendorMaster starts");
		return resp;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/vendor-company-master-contact-save")
	public @ResponseBody JsonResponse<Object> saveVendorContactMaster(
			@RequestBody VendorContactMasterModel vendorContactMasterModel, HttpSession session) {
		logger.info("Method : saveVendorContactMaster starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		vendorContactMasterModel.setCreatedBy(userId);

		try {
			resp = restClient.postForObject(env.getMasterUrl() + "saveVendorContactMaster", vendorContactMasterModel,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : saveVendorContactMaster starts");
		return resp;
	}

	// vendor contact through ajax start
	@SuppressWarnings("unchecked")
	@GetMapping("vendor-company-master-contact-through-ajax")
	public @ResponseBody List<VendorContactMasterModel> vendorContactThroughAjax(Model model,
			HttpServletRequest request, @RequestParam String id, HttpSession session) {
		logger.info("Method : vendorContactThroughAjax starts");

		JsonResponse<List<VendorContactMasterModel>> jsonResponse = new JsonResponse<List<VendorContactMasterModel>>();

		try {

			jsonResponse = restClient.getForObject(env.getMasterUrl() + "get-vendor-contact-list?id=" + id,
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			List<VendorContactMasterModel> addreq = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<List<VendorContactMasterModel>>() {
					});
			String dateFormat = (String) (session).getAttribute("DATEFORMAT");
			for (VendorContactMasterModel m : addreq) {
				String date = m.getCreatedOn();
				if (date != null && date != "") {
					date = DateFormatter.dateFormat(date, dateFormat);
					m.setCreatedOn(date);
				}

			}

			jsonResponse.setBody(addreq);

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method ; vendorContactThroughAjax ends");
		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/vendor-company-master-contact-edit")
	public @ResponseBody JsonResponse<Object> editVendorContactMaster(@RequestBody String vendorId,
			HttpSession session) {
		logger.info("Method : editVendorLocationMaster starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restClient.getForObject(env.getMasterUrl() + "editVendorContactById?id=" + vendorId,
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			VendorContactMasterModel contactDetails = mapper.convertValue(resp.getBody(),
					new TypeReference<VendorContactMasterModel>() {
					});

			resp.setBody(contactDetails);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			resp.setMessage("success");
		}

		logger.info("Method : editVendorContactMaster starts");
		return resp;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/vendor-company-master-document-save")
	public @ResponseBody JsonResponse<Object> saveVendorDocumentMaster(
			@RequestBody List<VendorDocumentMaster> vendorDocumentMaster, HttpSession session) {
		logger.info("Method : saveVendorDocumentMaster starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (VendorDocumentMaster m : vendorDocumentMaster) {
			m.setCreatedBy(userId);
			m.setDocumentImage(uploadPhoto(m.getDocumentImage(), m.getFile()));
		}

		try {
			resp = restClient.postForObject(env.getMasterUrl() + "saveVendorDocumentMaster", vendorDocumentMaster,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		logger.info("Method : saveVendorDocumentMaster starts");
		return resp;
	}

	public String uploadPhoto(String document, List<String> file) {

		if (document != null && document != "") {
			String delimiters = "\\.";
			String[] x = document.split(delimiters);
			if (x[1].matches("png") || x[1].matches("jpg") || x[1].matches("jpeg")) {
				for (String s1 : file) {

					try {

						byte[] bytes = new sun.misc.BASE64Decoder().decodeBuffer(s1);
						String imageName = fileUpload.saveAllImage(bytes);
						document = imageName;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else if (x[1].matches("pdf")) {
				for (String s1 : file) {
					try {

						byte[] bytes = new sun.misc.BASE64Decoder().decodeBuffer(s1);
						String pdfName = fileUpload.saveAllPdf(bytes);
						document = pdfName;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else if (x[1].matches("docx")) {
				for (String s1 : file) {
					try {
						byte[] bytes = new sun.misc.BASE64Decoder().decodeBuffer(s1);
						String pdfName = fileUpload.saveAllDocx(bytes);
						document = pdfName;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else if (x[1].matches("doc")) {
				for (String s1 : file) {
					try {
						byte[] bytes = new sun.misc.BASE64Decoder().decodeBuffer(s1);
						String pdfName = fileUpload.saveAllDoc(bytes);
						document = pdfName;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else if (x[1].matches("xls")) {
				for (String s1 : file) {
					try {
						byte[] bytes = new sun.misc.BASE64Decoder().decodeBuffer(s1);
						String pdfName = fileUpload.saveAllXls(bytes);
						document = pdfName;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else if (x[1].matches("xlsx")) {
				for (String s1 : file) {
					try {
						byte[] bytes = new sun.misc.BASE64Decoder().decodeBuffer(s1);
						String pdfName = fileUpload.saveAllXlsx(bytes);
						document = pdfName;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return document;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/vendor-company-master-bankdetails-save")
	public @ResponseBody JsonResponse<Object> saveemployeebankdetails(
			@RequestBody ManageEmployeeBankDetailsModel manageEmployeeBankDetails, HttpSession session) {
		logger.info("Method : saveemployeebankdetailsVendor starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		manageEmployeeBankDetails.setCreatedBy(userId);

		try {
			resp = restClient.postForObject(env.getMasterUrl() + "saveemployeebankdetailsVendor",
					manageEmployeeBankDetails, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		if (resp.getMessage() == null && resp.getMessage() != "") {
			resp.setMessage("Success");

		} else {
			resp.setMessage("unsucess");
		}

		logger.info("Method : saveemployeebankdetailsVendor starts");
		return resp;
	}
	// vendor contact through ajax start

	@SuppressWarnings("unchecked")
	@GetMapping("vendor-company-master-bank-through-ajax")
	public @ResponseBody List<ManageEmployeeBankDetailsModel> viewmanageemployeebankdetails(Model model,
			HttpServletRequest request, @RequestParam String id, HttpSession session) {
		logger.info("Method : viewmanageemployeebankdetails starts");

		JsonResponse<List<ManageEmployeeBankDetailsModel>> jsonResponse = new JsonResponse<List<ManageEmployeeBankDetailsModel>>();

		try {

			jsonResponse = restClient.getForObject(env.getMasterUrl() + "viewVendorBankDetails?id=" + id,
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			List<ManageEmployeeBankDetailsModel> addreq = mapper.convertValue(jsonResponse.getBody(),
					new TypeReference<List<ManageEmployeeBankDetailsModel>>() {
					});

			jsonResponse.setBody(addreq);
			String dateFormat = (String) (session).getAttribute("DATEFORMAT");
			for (ManageEmployeeBankDetailsModel m : addreq) {
				String date = m.getCreatedBy();
				if (date != null && date != "") {
					date = DateFormatter.dateFormat(date, dateFormat);
					m.setCreatedBy(date);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Method ; viewmanageemployeedependent ends");

		return jsonResponse.getBody();
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/vendor-company-master-bank-edit")
	public @ResponseBody JsonResponse<Object> editVendorBankMaster(@RequestBody String vendorId, HttpSession session) {
		logger.info("Method : editVendorBankMaster starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		try {
			resp = restClient.getForObject(env.getMasterUrl() + "editVendorBankById?id=" + vendorId,
					JsonResponse.class);

			ObjectMapper mapper = new ObjectMapper();

			ManageEmployeeBankDetailsModel bankDetails = mapper.convertValue(resp.getBody(),
					new TypeReference<ManageEmployeeBankDetailsModel>() {
					});
			try {
				DropDownModel[] state = restClient.getForObject(
						env.getMasterUrl() + "viewStateLocListByCountry?id=" + bankDetails.getEbankCountry(),
						DropDownModel[].class);
				List<DropDownModel> stateList = Arrays.asList(state);

				bankDetails.setStateList(stateList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			try {
				DropDownModel[] city = restClient.getForObject(
						env.getMasterUrl() + "viewCityLocListByState?id=" + bankDetails.getEbankState(),
						DropDownModel[].class);
				List<DropDownModel> cityList = Arrays.asList(city);

				bankDetails.setCityList(cityList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}
			resp.setBody(bankDetails);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			resp.setMessage("success");
		}

		logger.info("Method : editVendorBankMaster starts");
		return resp;
	}

	@PostMapping("/vendor-company-master-upload-file")
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

	@GetMapping(value = { "vendor-company-master-activity-through-ajax" })
	public @ResponseBody List<ActivitylogModel> getActivityLog(@RequestParam String id, HttpSession session) {
		logger.info("Method : getActivityLog starts");
		List<ActivitylogModel> activityLogList = new ArrayList<ActivitylogModel>();
		try {

			ActivitylogModel[] activityLog = restClient
					.getForObject(env.getMasterUrl() + "get-activity-log-vendor?id=" + id, ActivitylogModel[].class);
			activityLogList = Arrays.asList(activityLog);

			String dateFormat = (String) (session).getAttribute("DATEFORMAT");
			for (ActivitylogModel m : activityLogList) {
				String date = m.getOperationOn();
				if (date != null && date != "") {
					date = DateFormatter.dateFormat(date, dateFormat);
					m.setOperationOn(date);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : getActivityLog ends");
		return activityLogList;
	}

	/* Sagar Anchal - 21-07-2021 */

	@SuppressWarnings("unchecked")
	@PostMapping("/vendor-company-master-create-user")
	public @ResponseBody JsonResponse<Object> createVendorWiseUser(@RequestBody VendorContactMasterModel vendorId,
			HttpSession session) {
		logger.info("Method : createVendorWiseUser starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		vendorId.setCreatedBy(userId);

		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder(8);

		for (int i = 0; i < 8; i++) {
			int index = (int) (alphaNumericString.length() * Math.random());
			sb.append(alphaNumericString.charAt(index));
		}

		String password = sb.toString();
		if (password != null && password != "") {
			String pwd = pw.encode(password);
			vendorId.setPwd(pwd);
		}

		try {
			resp = restClient.postForObject(env.getMasterUrl() + "createVendorWiseUser", vendorId, JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			resp.setMessage("success");

			String subject = "Extend - Login Credentials";

			String emailMessage = "Hello " + vendorId.getContactName() + "," + System.lineSeparator()
					+ "Your login user id = " + vendorId.getPhone() + " & password = " + password
					+ System.lineSeparator() + "Link: " + "<a href='https://nerp.in'>https://nerp.in</a>";

			String attachFiles[] = null;

			List<String> emailIds = new ArrayList<String>();
			emailIds.add(vendorId.getEmail());
			List<String> ccUsers = new ArrayList<String>();

			try {
				EmailAttachmentSender.sendEmailWithAttachments("Smtp.gmail.com", "587", "sagaranchal4@gmail.com",
						"kharasrota", emailIds, ccUsers, subject, emailMessage, attachFiles);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

		logger.info("Method : createVendorWiseUser starts");
		return resp;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("vendor-company-master-reset-password")
	public @ResponseBody JsonResponse<Object> resetPasswordVendor(@RequestBody VendorContactMasterModel vendorId,
			HttpSession session) {
		logger.info("Method : resetPasswordVendor starts");

		JsonResponse<Object> resp = new JsonResponse<Object>();

		String userId = "";

		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		vendorId.setCreatedBy(userId);

		String password = "Extend@123";
		if (password != null && password != "") {
			String pwd = pw.encode(password);
			vendorId.setPwd(pwd);
		}

		try {
			resp = restClient.postForObject(env.getMasterUrl() + "resetPasswordVendor", vendorId, JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {
			resp.setMessage("success");

			String subject = "Extend - Reset Password";

			String emailMessage = "Hello " + vendorId.getContactName() + "," + System.lineSeparator()
					+ "Your new password = " + password + System.lineSeparator() + "Link: "
					+ "<a href='https://nerp.in'>https://nerp.in</a>";

			String attachFiles[] = null;

			List<String> emailIds = new ArrayList<String>();
			emailIds.add(vendorId.getEmail());
			List<String> ccUsers = new ArrayList<String>();

			try {
				EmailAttachmentSender.sendEmailWithAttachments("Smtp.gmail.com", "587", "sagaranchal4@gmail.com",
						"kharasrota", emailIds, ccUsers, subject, emailMessage, attachFiles);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}

		logger.info("Method : createVendorWiseUser starts");
		return resp;
	}

}
