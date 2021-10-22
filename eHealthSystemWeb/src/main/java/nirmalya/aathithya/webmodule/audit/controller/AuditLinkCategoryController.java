package nirmalya.aathithya.webmodule.audit.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import nirmalya.aathithya.webmodule.audit.model.AuditLinkCategoryModel;
import nirmalya.aathithya.webmodule.audit.model.AuditMeetingDocumentModel;
import nirmalya.aathithya.webmodule.audit.model.AuditMettingModel;
import nirmalya.aathithya.webmodule.audit.model.AuditUploadDocumentModel;
import nirmalya.aathithya.webmodule.audit.model.UploadDocumentModel;
import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.DropDownModelEmp;
import nirmalya.aathithya.webmodule.common.utils.EmailAttachmentSender;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.notice.model.InitiateNoticeDocumentModel;
import nirmalya.aathithya.webmodule.notice.model.IntiateNoticeModel;

@Controller
@RequestMapping(value = { "audit/" })
public class AuditLinkCategoryController {
	Logger logger = LoggerFactory.getLogger(AuditLinkCategoryController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;

	/*
	 * Get Mapping for audit Link
	 */

	@GetMapping("/audit-dashboard-link")
	public String auditLinkDashboard(Model model, @RequestParam("id") String id, @RequestParam("name") String name,
			HttpSession session) {

		byte[] encodeByte = Base64.getDecoder().decode(id.getBytes());
		String id1 = (new String(encodeByte));
		byte[] encodeByte1 = Base64.getDecoder().decode(name.getBytes());
		String name1 = (new String(encodeByte1));
		logger.info("Method : auditLinkDashboard starts");
		/*
		 * try { DropDownModel[] org = restTemplate.getForObject(env.getAuditUrl() +
		 * "getOrganizationId", DropDownModel[].class); List<DropDownModel> orgList =
		 * Arrays.asList(org);
		 * 
		 * model.addAttribute("auditorList", orgList);
		 * 
		 * } catch (RestClientException e) { e.printStackTrace(); }
		 */

		// *********** get region *************/
		try {
			DropDownModel[] org = restTemplate.getForObject(env.getAuditUrl() + "getRegion", DropDownModel[].class);
			List<DropDownModel> regionList = Arrays.asList(org);
			model.addAttribute("regionList", regionList);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		// *********** getQuarter *************/
		try {
			DropDownModel[] audit = restTemplate.getForObject(env.getAuditUrl() + "getQuarter", DropDownModel[].class);
			List<DropDownModel> getQuarterList = Arrays.asList(audit);

			model.addAttribute("getQuarterList", getQuarterList);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		model.addAttribute("auditType", id);
		model.addAttribute("auditTypeId", id1);
		model.addAttribute("auditTypeIdName", name1);
		model.addAttribute("auditTypeName", name);

		logger.info("Method : auditLinkDashboard ends");
		return "audit/auditLinkCategory";
	}

	/**
	 * get Organization
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "audit-dashboard-link-getOrganization" })
	public @ResponseBody JsonResponse<Object> getOrganization(Model model, @RequestBody String auditIdType,
			BindingResult result) {
		logger.info("Method : getOrganization starts");
		JsonResponse<Object> res = new JsonResponse<Object>();

		try {
			res = restTemplate.getForObject(env.getAuditUrl() + "getOrganization?id=" + auditIdType,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getOrganization ends");
		return res;
	}

	/**
	 * get Auditor
	 *
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "audit-dashboard-link-getAuditor" })
	public @ResponseBody JsonResponse<List<DropDownModel>> getAuditor(Model model,
			@RequestParam("region") String region, HttpSession session) {
		logger.info("Method : getAuditor starts");
		JsonResponse<List<DropDownModel>> res = new JsonResponse<List<DropDownModel>>();

		try {
			res = restTemplate.getForObject(env.getAuditUrl() + "getInternalAuditor?region=" + region,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getAuditor ends");
		return res;
	}

	/**
	 * get Auditor
	 *
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "audit-dashboard-link-getRegionalManager" })
	public @ResponseBody JsonResponse<List<DropDownModel>> getRegionalManager(Model model,
			@RequestParam("region") String region, HttpSession session) {
		logger.info("Method : getRegionalManager starts");
		JsonResponse<List<DropDownModel>> res = new JsonResponse<List<DropDownModel>>();

		try {
			res = restTemplate.getForObject(env.getAuditUrl() + "getRegionalManager?auditor=" + region,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getRegionalManager ends");
		return res;
	}

	/**
	 * get Auditor
	 *
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(value = { "audit-dashboard-link-getConcernFinance" })
	public @ResponseBody JsonResponse<List<DropDownModel>> getConcernFinance(Model model,
			@RequestParam("region") String region, HttpSession session) {
		logger.info("Method : getConcernFinance starts");
		JsonResponse<List<DropDownModel>> res = new JsonResponse<List<DropDownModel>>();

		try {
			res = restTemplate.getForObject(env.getAuditUrl() + "getConcernFinance?region=" + region,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getConcernFinance ends");
		return res;
	}

	/*
	 * dropDown value for Department Head through Ajax
	 */

	@SuppressWarnings("unchecked")

	@PostMapping(value = { "audit-dashboard-link-getDept-head" })
	public @ResponseBody JsonResponse<Object> getDeptHead(Model model, @RequestBody List<String> section,
			BindingResult result) {
		logger.info("Method : getDeptHead starts");

		String listProduct = "";
		for (String str : section) {
			str = str.replace("[", "").replace("]", "");
			listProduct = listProduct + "'" + str + "',";
		}
		listProduct = listProduct.substring(0, listProduct.length() - 1);
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {
			res = restTemplate.getForObject(env.getAuditUrl() + "getDeptHeadBySection?id=" + listProduct,
					JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getDeptHead ends");
		return res;

	}

	/*
	 * View audit Details
	 */

	@GetMapping("audit-dashboard-viewAudit")
	public @ResponseBody List<AuditLinkCategoryModel> viewAuditDetails(@RequestParam String id, HttpSession session,
			Model model) {
		logger.info("Method : viewAuditDetails starts");
		List<AuditLinkCategoryModel> auditList = new ArrayList<AuditLinkCategoryModel>();
		try {

			AuditLinkCategoryModel[] audit = restTemplate
					.getForObject(env.getAuditUrl() + "rest-viewAuditDetails?id=" + id, AuditLinkCategoryModel[].class);
			auditList = Arrays.asList(audit);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : viewAuditDetails ends");
		return auditList;
	}

	/*
	 * for edit Audit By Id
	 * 
	 * 
	 */
	@GetMapping(value = { "audit-dashboard-editAudit" })
	public @ResponseBody List<AuditLinkCategoryModel> editAudit(@RequestParam String id, HttpSession session,
			Model model) {
		logger.info("Method : editAudit starts");
		List<AuditLinkCategoryModel> audit = new ArrayList<AuditLinkCategoryModel>();
		List<UploadDocumentModel> documentList = new ArrayList<UploadDocumentModel>();
		// String dateFormat = "";
		if (id != null && id != "") {
			try {

				AuditLinkCategoryModel[] intiateNoticeModel = restTemplate
						.getForObject(env.getAuditUrl() + "rest-editAudit?id=" + id, AuditLinkCategoryModel[].class);
				audit = Arrays.asList(intiateNoticeModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				DropDownModel[] org = restTemplate.getForObject(env.getAuditUrl() + "getRegion", DropDownModel[].class);
				List<DropDownModel> regionList = Arrays.asList(org);
				model.addAttribute("regionList", regionList);
			} catch (RestClientException e) {
				e.printStackTrace();
			}

			// *********** getQuarter *************/
			try {
				DropDownModel[] quater = restTemplate.getForObject(env.getAuditUrl() + "getQuarter",
						DropDownModel[].class);
				List<DropDownModel> getQuarterList = Arrays.asList(quater);

				model.addAttribute("getQuarterList", getQuarterList);

			} catch (RestClientException e) {
				e.printStackTrace();
			}
			if (audit != null) {
				try {
					DropDownModel[] section = restTemplate.getForObject(
							env.getAuditUrl() + "getSectionforEdit?id=" + audit.get(0).getDept(),
							DropDownModel[].class);
					List<DropDownModel> sectionList = Arrays.asList(section);
					model.addAttribute("sectionList", sectionList);
				} catch (RestClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		if (audit != null) {
			documentList = audit.get(0).getDocumentList();

			if (documentList != null) {
				for (UploadDocumentModel m : documentList) {

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
							if (extension[1].equals("doc") || extension[1].equals("dox")
									|| extension[1].equals("docx")) {
								String docPath = " <i class=\"fa fa-file-word-o \" aria-hidden=\"true\"  title="
										+ m.getFileName() + "></i> ";
								m.setAction(docPath);
							}
							if (extension[1].equals("png") || extension[1].equals("jpg")
									|| extension[1].equals("jpeg")) {
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
					m.setAction("<a href=\"/document/procurment/" + m.getFileName() + "\" target=\"_blank\" >"
							+ m.getAction() + "</a>");

				}
			}
		}

		audit.get(0).setDocumentList(documentList);

		logger.info("Method : editAudit ends");
		return audit;
	}

	/*
	 *
	 * Delete Audit details
	 *
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("audit-dashboard-link-deleteAudit")
	public @ResponseBody JsonResponse<AuditLinkCategoryModel> deleteAuditDetails(@RequestParam String deleteId) {

		logger.info("Method : deleteAuditDetails starts");

		JsonResponse<AuditLinkCategoryModel> jsonResponse = new JsonResponse<AuditLinkCategoryModel>();

		try {
			jsonResponse = restTemplate.getForObject(env.getAuditUrl() + "delete-auditDetails?id=" + deleteId,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}

		logger.info("Method : deleteAuditDetails ends");
		return jsonResponse;

	}

	/********************** Document Upload **************************************/
	/*
	 * 
	 * Document Section
	 * 
	 */

	@GetMapping("/audit-dashboard-link-doc")
	public String auditDocument(Model model, @RequestParam("id") String id, @RequestParam("name") String name,
			HttpSession session) {

		byte[] encodeByte = Base64.getDecoder().decode(id.getBytes());
		String id1 = (new String(encodeByte));
		byte[] encodeByte1 = Base64.getDecoder().decode(name.getBytes());
		String name1 = (new String(encodeByte1));

		logger.info("Method : auditDocument starts");

		/**
		 * financial YearList
		 *
		 */
		try {
			DropDownModel[] audit = restTemplate.getForObject(env.getAuditUrl() + "getFinancialYear",
					DropDownModel[].class);
			List<DropDownModel> financialYearList = Arrays.asList(audit);

			model.addAttribute("financialYearList", financialYearList);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		/*
		 * Get Audit Folder
		 * 
		 */

		try {
			DropDownModel[] noticeFo = restTemplate.getForObject(env.getAuditUrl() + "getAuditFolder",
					DropDownModel[].class);
			List<DropDownModel> noticeFolder = Arrays.asList(noticeFo);
			model.addAttribute("noticeFolderList", noticeFolder);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		// *********** getQuarter *************/
		try {
			DropDownModel[] audit = restTemplate.getForObject(env.getAuditUrl() + "getQuarter", DropDownModel[].class);
			List<DropDownModel> getQuarterList = Arrays.asList(audit);

			model.addAttribute("getQuarterList", getQuarterList);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		model.addAttribute("auditType", id);
		model.addAttribute("auditTypeId", id1);
		model.addAttribute("auditTypeName", name1);
		logger.info("Method : auditDocument ends");
		return "audit/uploadDocument";
	}

	/*
	 * Add Document Section Details
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "audit-dashboard-link-addDocument")
	public @ResponseBody JsonResponse<Object> addDocument(
			@RequestBody List<AuditUploadDocumentModel> auditDocumentModel, HttpSession session, Model model) {
		logger.info("Method : audit-dashboard function starts");

		System.out.println("Data" + auditDocumentModel);

		JsonResponse<Object> res = new JsonResponse<Object>();
		List<AuditUploadDocumentModel> auditList = new ArrayList<AuditUploadDocumentModel>();
		List<UploadDocumentModel> docList = new ArrayList<UploadDocumentModel>();
		auditDocumentModel.stream().forEach(s -> s.setCreatedBy((String) session.getAttribute("USER_ID")));

		System.out.println("Inside Audit" + auditDocumentModel.get(0).getDocumentList());

		for (UploadDocumentModel a : auditDocumentModel.get(0).getDocumentList()) {

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

			res = restTemplate.postForObject(env.getAuditUrl() + "rest-addDocument", auditDocumentModel,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		auditList = mapper.convertValue(res.getBody(), new TypeReference<List<AuditUploadDocumentModel>>() {
		});
		if (auditList != null) {

			docList = auditDocumentModel.get(0).getDocumentList();
			for (UploadDocumentModel m : docList) {
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
				m.setAction("<a href=\"/document/procurment/" + m.getFileName() + "\" target=\"_blank\" >"
						+ m.getAction() + "</a>");

			}
		}
		String message = res.getMessage();
		auditDocumentModel.get(0).setDocumentList(docList);
		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		res.setBody(auditList);
		logger.info("Method : audit-dashboard function Ends");
		return res;
	}

	/*
	 * View audit Doc
	 */

	@GetMapping("audit-dashboard-link-viewAuditDoc")
	public @ResponseBody List<AuditUploadDocumentModel> viewAuditDoc(@RequestParam String id, HttpSession session,
			Model model) {
		logger.info("Method : viewAuditDoc starts");
		List<AuditUploadDocumentModel> auditDocList = new ArrayList<AuditUploadDocumentModel>();
		try {

			AuditUploadDocumentModel[] audit = restTemplate
					.getForObject(env.getAuditUrl() + "rest-viewAuditDoc?id=" + id, AuditUploadDocumentModel[].class);
			auditDocList = Arrays.asList(audit);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : viewAuditDoc ends");
		return auditDocList;
	}

	/*
	 * for edit Audit Document By Id
	 * 
	 * 
	 */
	@GetMapping(value = { "audit-dashboard-link-editAuditDoc" })
	public @ResponseBody List<AuditUploadDocumentModel> editAuditDoc(@RequestParam String id, HttpSession session,
			Model model) {
		logger.info("Method : editAuditDoc starts");
		List<AuditUploadDocumentModel> audit = new ArrayList<AuditUploadDocumentModel>();
		List<UploadDocumentModel> documentList = new ArrayList<UploadDocumentModel>();
		if (id != null && id != "") {
			try {

				AuditUploadDocumentModel[] auditDocModel = restTemplate.getForObject(
						env.getAuditUrl() + "rest-editAuditDoc?id=" + id, AuditUploadDocumentModel[].class);
				audit = Arrays.asList(auditDocModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (audit != null) {
			documentList = audit.get(0).getDocumentList();

			if (documentList != null) {
				for (UploadDocumentModel m : documentList) {

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
							if (extension[1].equals("doc") || extension[1].equals("dox")
									|| extension[1].equals("docx")) {
								String docPath = " <i class=\"fa fa-file-word-o \" aria-hidden=\"true\"  title="
										+ m.getFileName() + "></i> ";
								m.setAction(docPath);
							}
							if (extension[1].equals("png") || extension[1].equals("jpg")
									|| extension[1].equals("jpeg")) {
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
					m.setAction("<a href=\"/document/procurment/" + m.getFileName() + "\" target=\"_blank\" >"
							+ m.getAction() + "</a>");

				}
			}
		}

		audit.get(0).setDocumentList(documentList);

		logger.info("Method : editAuditDoc ends");
		return audit;
	}

	/*
	 *
	 * Delete Audit Doc
	 *
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("audit-dashboard-link-deleteAuditDoc")
	public @ResponseBody JsonResponse<AuditUploadDocumentModel> deleteAuditDoc(@RequestParam String deleteId) {

		logger.info("Method : deleteAuditDoc starts");

		JsonResponse<AuditUploadDocumentModel> jsonResponse = new JsonResponse<AuditUploadDocumentModel>();

		try {
			jsonResponse = restTemplate.getForObject(env.getAuditUrl() + "delete-auditDoc?id=" + deleteId,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}

		logger.info("Method : deleteAuditDoc ends");
		return jsonResponse;

	}

	/********************** Document Upload **************************************/
	/*
	 * 
	 * Document Section
	 * 
	 */

	@GetMapping("/audit-dashboard-link-trail")
	public String auditTrail(Model model, @RequestParam("id") String id, @RequestParam("name") String name,
			HttpSession session) {

		byte[] encodeByte = Base64.getDecoder().decode(id.getBytes());
		String id1 = (new String(encodeByte));
		byte[] encodeByte1 = Base64.getDecoder().decode(name.getBytes());
		String name1 = (new String(encodeByte1));

		logger.info("Method : auditDocument starts");

		model.addAttribute("auditType", id);
		model.addAttribute("auditTypeId", id1);
		model.addAttribute("auditTypeName", name1);
		logger.info("Method : auditDocument ends");
		return "audit/auditTrail";
	}

	/********************** Meeting Section *************************/
	/*
	 * Get Mapping for audit Link
	 */

	@GetMapping("/audit-dashboard-link-meeting")
	public String auditMeetingSection(Model model, @RequestParam("id") String id, @RequestParam("name") String name,
			HttpSession session) {
		byte[] encodeByte = Base64.getDecoder().decode(id.getBytes());
		String id1 = (new String(encodeByte));
		byte[] encodeByte1 = Base64.getDecoder().decode(name.getBytes());
		String name1 = (new String(encodeByte1));
		logger.info("Method : auditMeetingSection starts");

		try {
			DropDownModelEmp[] employee = restTemplate.getForObject(env.getAuditUrl() + "getempList",
					DropDownModelEmp[].class);
			List<DropDownModelEmp> empList = Arrays.asList(employee);
			model.addAttribute("empList", empList);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] dept = restTemplate.getForObject(env.getAuditUrl() + "getdeptList", DropDownModel[].class);
			List<DropDownModel> deptList = Arrays.asList(dept);

			model.addAttribute("deptList", deptList);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		model.addAttribute("auditType", id);
		model.addAttribute("auditTypeId", id1);
		model.addAttribute("auditTypeName", name1);
		logger.info("Method : auditMeetingSection ends");
		return "audit/manage-meeting";
	}
	/*
	 * add meeting
	 */

	@SuppressWarnings("unchecked")
	@PostMapping("/audit-dashboard-link-addMeeting")
	public @ResponseBody JsonResponse<Object> managemeetingaddajax(
			@RequestBody List<AuditMettingModel> noticeInitiateModel, HttpSession session, Model model) {
		logger.info("Method : meeting starts");

		JsonResponse<Object> res = new JsonResponse<Object>();
		List<AuditMettingModel> noticeList = new ArrayList<AuditMettingModel>();
		List<AuditMeetingDocumentModel> docList = new ArrayList<AuditMeetingDocumentModel>();
		String userId = "";
		String dateFormat = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (AuditMettingModel m : noticeInitiateModel) {
			m.setCreatedBy(userId);
			m.setDate(DateFormatter.inputDateFormat(m.getDate(), dateFormat));
			// m.setStartTime(DateFormatter.inputDateFormat(m.getStartTime(), dateFormat));
			// m.setEndTime(DateFormatter.inputDateFormat(m.getEndTime(), dateFormat));

		}
		for (AuditMeetingDocumentModel a : noticeInitiateModel.get(0).getDocumentList()) {

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

			res = restTemplate.postForObject(env.getAuditUrl() + "saveauditMaster", noticeInitiateModel,
					JsonResponse.class);

		}

		catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();
		noticeList = mapper.convertValue(res.getBody(), new TypeReference<List<AuditMettingModel>>() {
		});
		if (noticeList != null) {

			for (AuditMettingModel a : noticeList) {

				if (a.getDate() != null && a.getDate() != "") {
					a.setDate(DateFormatter.dateFormat(a.getDate(), dateFormat));
				}
				/*
				 * if (a.getStartTime() != null && a.getStartTime() != "") {
				 * a.setStartTime(DateFormatter.dateFormat(a.getStartTime(), dateFormat)); }
				 * 
				 * if (a.getEndTime() != null && a.getEndTime() != "") {
				 * a.setEndTime(DateFormatter.dateFormat(a.getEndTime(), dateFormat)); }
				 */
				if (a.getCreatedOn() != null && a.getCreatedOn() != "") {
					a.setCreatedOn(DateFormatter.dateFormat(a.getCreatedOn(), dateFormat));
				}

			}
			docList = noticeInitiateModel.get(0).getDocumentList();
			for (AuditMeetingDocumentModel m : docList) {
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
				m.setAction("<a href=\"/document/procurment/" + m.getFileName() + "\" target=\"_blank\" >"
						+ m.getAction() + "</a>");

			}
		}
		String message = res.getMessage();
		noticeInitiateModel.get(0).setDocumentList(docList);
		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		res.setBody(noticeList);

		System.out.println(res);
		logger.info("Method :  function Ends");
		return res;
	}

	/*
	 * View Meeting Details
	 */
	@SuppressWarnings("unchecked")

	@GetMapping("audit-dashboard-link-viewMeeting")
	public @ResponseBody List<AuditMettingModel> viewCandidates(HttpSession session) {
		logger.info("Method : viewmeeting starts");

		JsonResponse<List<AuditMettingModel>> resp = new JsonResponse<List<AuditMettingModel>>();

		try {
			resp = restTemplate.getForObject(env.getAuditUrl() + "viewmeeting", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		List<AuditMettingModel> cand = mapper.convertValue(resp.getBody(),
				new TypeReference<List<AuditMettingModel>>() {
				});

		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		resp.setBody(cand);

		System.out.println(resp.getBody());
		logger.info("Method : viewmeeting ends");
		return resp.getBody();
	}

	@GetMapping(value = { "audit-dashboard-link-editMeeting" })
	public @ResponseBody List<AuditMettingModel> managemeetingaddedit(@RequestParam String id, HttpSession session,
			Model model) {
		logger.info("Method : managemeetingaddedit starts");
		List<AuditMettingModel> noticeList = new ArrayList<AuditMettingModel>();
		List<AuditMeetingDocumentModel> documentList = new ArrayList<AuditMeetingDocumentModel>();
		String dateFormat = "";
		if (id != null && id != "") {
			try {
				dateFormat = (String) session.getAttribute("DATEFORMAT");
				AuditMettingModel[] intiateNoticeModel = restTemplate
						.getForObject(env.getAuditUrl() + "get-meeting-edit?id=" + id, AuditMettingModel[].class);
				noticeList = Arrays.asList(intiateNoticeModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (noticeList != null) {
			documentList = noticeList.get(0).getDocumentList();

			for (AuditMettingModel a : noticeList) {
				if (a.getDate() != null && a.getDate() != "") {
					a.setDate(DateFormatter.dateFormat(a.getDate(), dateFormat));
				}
				/*
				 * if (a.getEndDate() != null && a.getEndDate() != "") {
				 * a.setEndDate(DateFormatter.dateFormat(a.getEndDate(), dateFormat)); }
				 */
				if (a.getCreatedOn() != null && a.getCreatedOn() != "") {
					a.setCreatedOn(DateFormatter.dateFormat(a.getCreatedOn(), dateFormat));
				}
			}
			if (documentList != null) {
				for (AuditMeetingDocumentModel m : documentList) {

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
							if (extension[1].equals("doc") || extension[1].equals("dox")
									|| extension[1].equals("docx")) {
								String docPath = " <i class=\"fa fa-file-word-o \" aria-hidden=\"true\"  title="
										+ m.getFileName() + "></i> ";
								m.setAction(docPath);
							}
							if (extension[1].equals("png") || extension[1].equals("jpg")
									|| extension[1].equals("jpeg")) {
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
					m.setAction("<a href=\"/document/procurment/" + m.getFileName() + "\" target=\"_blank\" >"
							+ m.getAction() + "</a>");

				}
			}

		}

		noticeList.get(0).setDocumentList(documentList);

		logger.info("Method : noticeDetailsEdit ends");
		return noticeList;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("audit-dashboard-link-deleteMeeting")
	public @ResponseBody JsonResponse<AuditMettingModel> deleteNoticeDetails(@RequestParam String id) {

		logger.info("Method : deleteNoticeDetails starts");

		JsonResponse<AuditMettingModel> jsonResponse = new JsonResponse<AuditMettingModel>();

		try {
			jsonResponse = restTemplate.getForObject(env.getAuditUrl() + "delete-audit-details?id=" + id,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		if (jsonResponse.getMessage() != null && jsonResponse.getMessage() != "") {
			jsonResponse.setCode(jsonResponse.getMessage());
			jsonResponse.setMessage("Unsuccess");

		} else {
			jsonResponse.setMessage("Success");
		}

		logger.info("Method : deleteNoticeDetails ends");
		return jsonResponse;

	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "audit-dashboard-link-Meeting-saveDraft")
	public @ResponseBody JsonResponse<Object> addDraftDetails(@RequestBody List<AuditMettingModel> noticeInitiateModel,
			HttpSession session, Model model) {
		logger.info("Method : addDraftDetails function starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		List<AuditMettingModel> noticeList = new ArrayList<AuditMettingModel>();
		List<AuditMeetingDocumentModel> docList = new ArrayList<AuditMeetingDocumentModel>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (AuditMettingModel m : noticeInitiateModel) {
			m.setCreatedBy(userId);

		}
		for (AuditMeetingDocumentModel a : noticeInitiateModel.get(0).getDocumentList()) {

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

			res = restTemplate.postForObject(env.getAuditUrl() + "add-draft-details", noticeInitiateModel,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		noticeList = mapper.convertValue(res.getBody(), new TypeReference<List<AuditMettingModel>>() {
		});
		if (noticeList != null) {

			docList = noticeInitiateModel.get(0).getDocumentList();
			for (AuditMeetingDocumentModel m : docList) {
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
				m.setAction("<a href=\"/document/procurment/" + m.getFileName() + "\" target=\"_blank\" >"
						+ m.getAction() + "</a>");

			}
		}
		String message = res.getMessage();
		noticeInitiateModel.get(0).setDocumentList(docList);
		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		res.setBody(noticeList);
		logger.info("Method : addDraftDetails function Ends");
		return res;
	}

	/*
	 * View Draft Details
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("audit-dashboard-link-Meeting-viewSaveDraft")
	public @ResponseBody List<AuditMettingModel> viewDraft(@RequestParam String meetingId, HttpSession session) {

		logger.info("Method : viewDraft starts");
		String userId = "";
		List<AuditMettingModel> noticeList = new ArrayList<AuditMettingModel>();
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		JsonResponse<List<AuditMettingModel>> resp = new JsonResponse<List<AuditMettingModel>>();

		try {
			resp = restTemplate.getForObject(
					env.getAuditUrl() + "rest-viewDrafttable?meetingId=" + meetingId + "&createdBy=" + userId,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		noticeList = mapper.convertValue(resp.getBody(), new TypeReference<List<AuditMettingModel>>() {
		});

		logger.info("Method : viewDraft ends");
		return resp.getBody();
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "audit-dashboard-link-sendMeeting1")
	public @ResponseBody JsonResponse<Object> saveSendDetails1(@RequestBody List<AuditMettingModel> noticeInitiateModel,
			HttpSession session, Model model) {
		logger.info("Method : saveSendDetails function starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		List<AuditMettingModel> noticeList = new ArrayList<AuditMettingModel>();
		List<AuditMeetingDocumentModel> docList = new ArrayList<AuditMeetingDocumentModel>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (AuditMettingModel m : noticeInitiateModel) {
			m.setCreatedBy(userId);

		}
		for (AuditMeetingDocumentModel a : noticeInitiateModel.get(0).getDocumentList()) {

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

			res = restTemplate.postForObject(env.getAuditUrl() + "rest-send-details", noticeInitiateModel,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		noticeList = mapper.convertValue(res.getBody(), new TypeReference<List<AuditMettingModel>>() {
		});
		if (noticeList != null) {

			docList = noticeInitiateModel.get(0).getDocumentList();
			for (AuditMeetingDocumentModel m : docList) {
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
				m.setAction("<a href=\"/document/procurment/" + m.getFileName() + "\" target=\"_blank\" >"
						+ m.getAction() + "</a>");

			}
		}
		String message = res.getMessage();
		noticeInitiateModel.get(0).setDocumentList(docList);
		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		res.setBody(noticeList);
		logger.info("Method : saveSendDetails function Ends");
		return res;
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "audit-dashboard-link-sendMeeting")
	public @ResponseBody JsonResponse<Object> saveSendDetails(@RequestBody List<AuditMettingModel> noticeInitiateModel,
			HttpSession session, Model model) {
		logger.info("Method : saveSendDetails function starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		List<AuditMettingModel> noticeList = new ArrayList<AuditMettingModel>();
		List<AuditMeetingDocumentModel> docList = new ArrayList<AuditMeetingDocumentModel>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (AuditMettingModel m : noticeInitiateModel) {
			m.setCreatedBy(userId);
		}
		System.out.println("noticeInitiateModel " + noticeInitiateModel);
		try {

			res = restTemplate.postForObject(env.getAuditUrl() + "rest-send-details", noticeInitiateModel,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		String message = res.getMessage();
		noticeInitiateModel.get(0).setDocumentList(docList);
		if (message != null && message != "") {

		} else {
			res.setMessage("Success");

			String tolist = noticeInitiateModel.get(0).getMailparticipant();
			// System.out.println("bikash4 "+tolist);
			String cclist = noticeInitiateModel.get(0).getEmailId();
			System.out.println("rohan3" + cclist);
			List<String> toAddress = new ArrayList<String>();
			List<String> CcAddress = new ArrayList<String>();
			String[] cc = cclist.split(",");
			for (int i = 0; i < cc.length; i++) {
				toAddress.add(cc[i]);
			}
			String comma = ",";
			String newlist = tolist.concat(comma).concat(cclist);
			System.out.println("rohan2" + newlist);
			String str[] = tolist.split(",");
			System.out.println("str " + Arrays.toString(str));
			// System.out.println(Arrays.toString("bikash1 "+str));
			// ArrayList<String> mylist= Arrays.asList(str);
			ArrayList<String> mylist = new ArrayList<String>();
			mylist.addAll(Arrays.asList(str));
			// ArrayList<String> aList = new ArrayList<String>(Arrays.asList(question1));
			// aList.addAll(Arrays.asList(question2));

			// String x[]=mylist.split(",");

			System.out.println("rohan1" + mylist);
			// System.out.println("First element of the ArrayList: "+mylist.get(0));
			// String s="";
			// s=mylist.split()
			System.out.println("rohan" + mylist);
			JsonResponse<Object> resp = new JsonResponse<Object>();

			String userName = "";
			try {
				userName = (String) session.getAttribute("USER_NAME");
			} catch (Exception e) {
			}

			String subject = "From :-" + userName + System.lineSeparator() + "Subject:-"
					+ noticeInitiateModel.get(0).getMailsubject();

			// String date = noticeInitiateModel.get(0).getMailDate();
			String message1 = noticeInitiateModel.get(0).getMailComment();
			System.out.println("message1 " + message1);

			String[] attachFiles = null;
			// noticeInitiateModel.get(0).setDocumentList(docList);
			if (noticeInitiateModel.get(0).getDocumentList() != null) {
				attachFiles = new String[noticeInitiateModel.size()];
				for (int i = 0; i < attachFiles.length; i++) {
					if (noticeInitiateModel.get(i).getDocumentList() != null) {
						attachFiles[i] = env.getFileUploadProcurement() + noticeInitiateModel.get(i).getDocumentList();
					}
				}
			}

			for (int i = 0; i < mylist.size(); i++

			)

				for (int i1 = 0; i < toAddress.size(); i++

				) {
					try {

						resp = restTemplate.getForObject(env.getAuditUrl() + "getmaildetails?mylist=" + mylist.get(i),
								JsonResponse.class);

						// System.out.println("emails:- "+(String) resp.getBody());
						// toAddress.add((String) resp.getBody());
						System.out.println("Rohit " + mylist + toAddress);
						// resp.setBody(toAddress);
					} catch (RestClientException e) {
						e.printStackTrace();
					}
				}
			System.out.println("toAddress " + toAddress);
			// EmailAttachmentSender email = new EmailAttachmentSender();

			try {
				System.out.println("message1 " + message1);
				EmailAttachmentSender.sendEmailWithAttachments("Smtp.gmail.com", "587", "noticeperiod819@gmail.com",
						"notice@period@bik1997", toAddress, CcAddress, subject, message1, null);
				// email.sendEmailWithAttachments(host, port, null, password, toAddress
				// ,ccAddress, subject, Content, null);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			res.setBody(noticeList);
		}
		// res.setBody(noticeList);
		logger.info("Method : saveSendDetails function Ends");
		return res;
	}

	@GetMapping("audit-dashboard-link-Meeting-editSaveDraft")
	public @ResponseBody List<AuditMettingModel> editDraftDetials(@RequestParam String draftNo, HttpSession session,
			Model model) {
		logger.info("Method : editDraftDetials starts");
		List<AuditMettingModel> noticeList = new ArrayList<AuditMettingModel>();
		List<AuditMeetingDocumentModel> documentList = new ArrayList<AuditMeetingDocumentModel>();

		String dateFormat = "";

		if (draftNo != null && draftNo != "") {
			try {
				AuditMettingModel[] intiateNoticeModel = restTemplate.getForObject(
						env.getAuditUrl() + "edit-draft-details?id=" + draftNo, AuditMettingModel[].class);
				noticeList = Arrays.asList(intiateNoticeModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (noticeList != null) {
			documentList = noticeList.get(0).getDocumentList();

			for (AuditMettingModel a : noticeList) {
				/*
				 * if (a.getDraftDate() != null && a.getDraftDate() != "") {
				 * a.setDraftDate(DateFormatter.dateFormat(a.getDraftDate(), dateFormat)); }
				 */
			}

			if (documentList != null) {
				for (AuditMeetingDocumentModel m : documentList) {

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
							if (extension[1].equals("doc") || extension[1].equals("dox")
									|| extension[1].equals("docx")) {
								String docPath = " <i class=\"fa fa-file-word-o \" aria-hidden=\"true\"  title="
										+ m.getFileName() + "></i> ";
								m.setAction(docPath);
							}
							if (extension[1].equals("png") || extension[1].equals("jpg")
									|| extension[1].equals("jpeg")) {
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
					m.setAction("<a href=\"/document/procurment/" + m.getFileName() + "\" target=\"_blank\" >"
							+ m.getAction() + "</a>");

				}
			}

		}

		noticeList.get(0).setDocumentList(documentList);
		System.out.println("dhgcvdhcbdhcbhdbc"+noticeList);
		logger.info("Method : editDraftDetials ends");
		return noticeList;
	}

	@GetMapping(value = { "audit-dashboard-link-showDetails" })
	public @ResponseBody List<AuditMettingModel> viewDocumentsAudit(@RequestParam String id, HttpSession session,
			Model model) {
		logger.info("Method : managemeetingaddedit starts");
		List<AuditMettingModel> noticeList = new ArrayList<AuditMettingModel>();
		List<AuditMeetingDocumentModel> documentList = new ArrayList<AuditMeetingDocumentModel>();
		String dateFormat = "";
		if (id != null && id != "") {
			try {
				dateFormat = (String) session.getAttribute("DATEFORMAT");
				AuditMettingModel[] intiateNoticeModel = restTemplate
						.getForObject(env.getAuditUrl() + "audit-show-details?id=" + id, AuditMettingModel[].class);
				noticeList = Arrays.asList(intiateNoticeModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (noticeList != null) {
			documentList = noticeList.get(0).getDocumentList();

			for (AuditMettingModel a : noticeList) {
				if (a.getDate() != null && a.getDate() != "") {
					a.setDate(DateFormatter.dateFormat(a.getDate(), dateFormat));
				}
				/*
				 * if (a.getEndDate() != null && a.getEndDate() != "") {
				 * a.setEndDate(DateFormatter.dateFormat(a.getEndDate(), dateFormat)); }
				 */
				if (a.getCreatedOn() != null && a.getCreatedOn() != "") {
					a.setCreatedOn(DateFormatter.dateFormat(a.getCreatedOn(), dateFormat));
				}
			}
			if (documentList != null) {
				for (AuditMeetingDocumentModel m : documentList) {

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
							if (extension[1].equals("doc") || extension[1].equals("dox")
									|| extension[1].equals("docx")) {
								String docPath = " <i class=\"fa fa-file-word-o \" aria-hidden=\"true\"  title="
										+ m.getFileName() + "></i> ";
								m.setAction(docPath);
							}
							if (extension[1].equals("png") || extension[1].equals("jpg")
									|| extension[1].equals("jpeg")) {
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
					m.setAction("<a href=\"/document/procurment/" + m.getFileName() + "\" target=\"_blank\" >"
							+ m.getAction() + "</a>");

				}
			}

		}

		noticeList.get(0).setDocumentList(documentList);

		logger.info("Method : noticeDetailsEdit ends");
		return noticeList;
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "audit-dashboard-link-send-email-manage-audit")
	public @ResponseBody JsonResponse<Object> saveSendDetailsManageAudit(
			@RequestBody List<AuditLinkCategoryModel> noticeInitiateModel, HttpSession session, Model model) {
		logger.info("Method : rashmita function starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		List<AuditLinkCategoryModel> noticeList = new ArrayList<AuditLinkCategoryModel>();
		List<UploadDocumentModel> docList = new ArrayList<UploadDocumentModel>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (AuditLinkCategoryModel m : noticeInitiateModel) {
			m.setCreatedBy(userId);
		}

		try {

			res = restTemplate.postForObject(env.getAuditUrl() + "rest-send-details-mail-audit", noticeInitiateModel,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		String message = res.getMessage();
		noticeInitiateModel.get(0).setDocumentList(docList);
		if (message != null && message != "") {

		} else {
			res.setMessage("Success");

			String tolist = noticeInitiateModel.get(0).getToEmail();
			System.out.println("bikash4 " + tolist);
			String cclist = noticeInitiateModel.get(0).getToCc();
			System.out.println("bikash3 " + cclist);
			String comma = ",";
			String newlist = tolist.concat(comma).concat(cclist);
			System.out.println("bikash2 " + newlist);
			String str[] = newlist.split(",");
			System.out.println("str " + Arrays.toString(str));
			// System.out.println(Arrays.toString("bikash1 "+str));
			// ArrayList<String> mylist= Arrays.asList(str);
			ArrayList<String> mylist = new ArrayList<String>();
			mylist.addAll(Arrays.asList(str));

			System.out.println("str12 " + Arrays.asList(str));
			// ArrayList<String> aList = new ArrayList<String>(Arrays.asList(question1));
			// aList.addAll(Arrays.asList(question2));

			// String x[]=mylist.split(",");

			// System.out.println("bikash "+mylist);
			// System.out.println("First element of the ArrayList: "+mylist.get(0));
			// String s="";
			// s=mylist.split()
			JsonResponse<Object> resp = new JsonResponse<Object>();
			System.out.println("str12123 " + resp);
			List<String> toAddress = new ArrayList<String>();
			List<String> CcAddress = new ArrayList<String>();
			System.out.println("toAddress " + toAddress);
			System.out.println("CcAddress " + CcAddress);
			String userName = "";
			try {
				userName = (String) session.getAttribute("USER_NAME");
			} catch (Exception e) {
			}

			String subject = "From :-" + userName + System.lineSeparator() + "Subject:-"
					+ noticeInitiateModel.get(0).getMailsubject();
			String message1 = noticeInitiateModel.get(0).getComment();

			String[] attachFiles = null;

			noticeInitiateModel.get(0).setDocumentList(docList);

			System.out.print("@@@@");
			// System.out.println(noticeInitiateModel.get(0).getDocumentList());
			int a = docList.size();
			if (a != 0) {
				attachFiles = new String[docList.size()];
				for (int i = 0; i < attachFiles.length; i++) {
					if (docList.get(0).getDocumentFile() != null) {
						attachFiles[i] = env.getFileUploadProcurement() + docList.get(0).getFileName();
					}
				}
			}
			System.out.print("attachFiles");
			System.out.println(Arrays.toString(attachFiles));
			for (int i = 0; i < mylist.size(); i++) {
				try {

					resp = restTemplate.getForObject(env.getAuditUrl() + "getmaildetailsAudit?mylist=" + mylist.get(i),
							JsonResponse.class);

					System.out.println("emails:- " + (String) resp.getBody());
					toAddress.add((String) resp.getBody());
				} catch (RestClientException e) {
					e.printStackTrace();
				}
			}
			// CcAddress.add("bikashmohapatra1997@gmail.com");
			//CcAddress.add("rasmita.behera@nirmalyalabs.com");
			System.out.println("toAddress " + toAddress);
			EmailAttachmentSender email = new EmailAttachmentSender();

			try {
				System.out.println("message1 " + message1);

				EmailAttachmentSender.sendEmailWithAttachments("Smtp.gmail.com", "587", "noticeperiod819@gmail.com",

						"notice@period@bik1997", CcAddress,toAddress , subject, message1, null);
				
				
				// email.sendEmailWithAttachments(host, port, null, password, toAddress
				// ,CcAddress, subject, Content, null);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			res.setBody(noticeList);
		}
		// res.setBody(noticeList);
		System.out.println("rrrrrrrrrreeeeeesp" + res);
		logger.info("Method : saveSendDetails function Ends");
		return res;
	}

	/**********************
	 * Commensement Audit
	 **************************************/
	/*
	 * 
	 * Commensement Section
	 * 
	 */

	@GetMapping("/audit-dashboard-link-commensement")
	public String auditCommensement(Model model, @RequestParam("id") String id, @RequestParam("name") String name,
			HttpSession session) {
		byte[] encodeByte = Base64.getDecoder().decode(id.getBytes());
		String id1 = (new String(encodeByte));
		byte[] encodeByte1 = Base64.getDecoder().decode(name.getBytes());
		String name1 = (new String(encodeByte1));
		logger.info("Method : auditMeetingSection starts");

		/**
		 * financial YearList
		 *
		 */
		try {
			DropDownModel[] audit = restTemplate.getForObject(env.getAuditUrl() + "getFinancialYear",
					DropDownModel[].class);
			List<DropDownModel> financialYearList = Arrays.asList(audit);

			model.addAttribute("financialYearList", financialYearList);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		/** Department **/
		try {
			DropDownModel[] dept = restTemplate.getForObject(env.getAuditUrl() + "getDeptList", DropDownModel[].class);
			List<DropDownModel> deptList = Arrays.asList(dept);

			model.addAttribute("deptList", deptList);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		// To
		try {
			DropDownModelEmp[] employee = restTemplate.getForObject(env.getAuditUrl() + "toList",
					DropDownModelEmp[].class);
			List<DropDownModelEmp> empLists = Arrays.asList(employee);
			model.addAttribute("empLists", empLists);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		model.addAttribute("auditType", id);
		model.addAttribute("auditTypeId", id1);
		model.addAttribute("auditTypeIdName", name1);
		logger.info("Method : auditMeetingSection ends");
		return "audit/auditCommensement";
	}

	/**
	 * Department Section
	 * 
	 * @return
	 **/

	@SuppressWarnings("unchecked")
	@PostMapping(value = { "audit-dashboard-link-commensement-getDept-section" })
	public @ResponseBody JsonResponse<Object> getSectionList(Model model, @RequestBody String dept,
			BindingResult result) {
		logger.info("Method : getSectionList starts");
		JsonResponse<Object> res = new JsonResponse<Object>();

		try {
			res = restTemplate.getForObject(env.getAuditUrl() + "getSectionListByDept?id=" + dept, JsonResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("success");
		}
		logger.info("Method : getSectionList ends");
		return res;
	}

	/*
	 * Add Notice Details
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "audit-dashboard-link-addAudit")
	public @ResponseBody JsonResponse<Object> addAuditDetails(@RequestBody List<AuditLinkCategoryModel> auditModel,
			HttpSession session, Model model) {
		logger.info("Method : audit-dashboard function starts");

		System.out.println("Data=================" + auditModel);

		JsonResponse<Object> res = new JsonResponse<Object>();
		List<AuditLinkCategoryModel> auditList = new ArrayList<AuditLinkCategoryModel>();
		List<UploadDocumentModel> docList = new ArrayList<UploadDocumentModel>();
		auditModel.stream().forEach(s -> s.setCreatedBy((String) session.getAttribute("USER_ID")));
		String dateFormat = "";

		try {
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (auditModel.get(0).getInitiatedDate() != null && auditModel.get(0).getInitiatedDate() != "") {
			auditModel.get(0)
					.setInitiatedDate(DateFormatter.inputDateFormat(auditModel.get(0).getInitiatedDate(), dateFormat));
		}
		if (auditModel.get(0).getStartDate() != null && auditModel.get(0).getStartDate() != "") {
			auditModel.get(0).setStartDate(DateFormatter.inputDateFormat(auditModel.get(0).getStartDate(), dateFormat));
		}
		if (auditModel.get(0).getEndDate() != null && auditModel.get(0).getEndDate() != "") {
			auditModel.get(0).setEndDate(DateFormatter.inputDateFormat(auditModel.get(0).getEndDate(), dateFormat));
		}
		System.out.println("Inside Audit" + auditModel.get(0).getDocumentList());

		for (UploadDocumentModel a : auditModel.get(0).getDocumentList()) {

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

			res = restTemplate.postForObject(env.getAuditUrl() + "rest-addAudit", auditModel, JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		auditList = mapper.convertValue(res.getBody(), new TypeReference<List<AuditLinkCategoryModel>>() {
		});
		if (auditList != null) {

			docList = auditModel.get(0).getDocumentList();
			for (UploadDocumentModel m : docList) {
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
				m.setAction("<a href=\"/document/procurment/" + m.getFileName() + "\" target=\"_blank\" >"
						+ m.getAction() + "</a>");

			}
		}
		String message = res.getMessage();
		auditModel.get(0).setDocumentList(docList);
		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		res.setBody(auditList);
		logger.info("Method : audit-dashboard function Ends");
		return res;
	}

	@SuppressWarnings("unchecked")
	@PostMapping(value = "audit-dashboard-link-commensement-saveDraft")
	public @ResponseBody JsonResponse<Object> addDraftDetailsCommensement(
			@RequestBody List<AuditLinkCategoryModel> noticeInitiateModel, HttpSession session, Model model) {
		logger.info("Method : addDraftDetailsCommensement function starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		List<AuditLinkCategoryModel> noticeList = new ArrayList<AuditLinkCategoryModel>();
		List<UploadDocumentModel> docList = new ArrayList<UploadDocumentModel>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (AuditLinkCategoryModel m : noticeInitiateModel) {
			m.setCreatedBy(userId);

		}
		for (UploadDocumentModel a : noticeInitiateModel.get(0).getDocumentList()) {

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

			res = restTemplate.postForObject(env.getAuditUrl() + "add-draft-details-commensement", noticeInitiateModel,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		noticeList = mapper.convertValue(res.getBody(), new TypeReference<List<AuditLinkCategoryModel>>() {
		});
		if (noticeList != null) {

			docList = noticeInitiateModel.get(0).getDocumentList();
			for (UploadDocumentModel m : docList) {
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
				m.setAction("<a href=\"/document/procurment/" + m.getFileName() + "\" target=\"_blank\" >"
						+ m.getAction() + "</a>");

			}
		}
		String message = res.getMessage();
		noticeInitiateModel.get(0).setDocumentList(docList);
		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
		}
		res.setBody(noticeList);
		System.out.println("resp"+res);
		logger.info("Method : addDraftDetailsCommensement function Ends");
		return res;
	}

	/*
	 * View Draft Details
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("audit-dashboard-link-commensement-viewSaveDraft")
	public @ResponseBody List<AuditLinkCategoryModel> viewDraftCommensement(@RequestParam String auditInitiate,
			HttpSession session) {

		logger.info("Method : viewDraftCommensement starts");
		String userId = "";
		List<AuditLinkCategoryModel> noticeList = new ArrayList<AuditLinkCategoryModel>();
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		JsonResponse<List<AuditLinkCategoryModel>> resp = new JsonResponse<List<AuditLinkCategoryModel>>();

		try {
			resp = restTemplate.getForObject(env.getAuditUrl() + "rest-viewDrafttableCommennse?auditInitiate="
					+ auditInitiate + "&createdBy=" + userId, JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		noticeList = mapper.convertValue(resp.getBody(), new TypeReference<List<AuditLinkCategoryModel>>() {
		});
		System.out.println("sdrcfgvbhjnkm" + resp.getBody());
		logger.info("Method : viewDraftCommensement ends");
		return resp.getBody();
	}

	/*
	 * View Notice Details
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("audit-dashboard-link-view-Throughajax")
	public @ResponseBody List<AuditLinkCategoryModel> viewAuditInitiate() {

		logger.info("Method : viewNoticeInitiate starts");

		JsonResponse<List<AuditLinkCategoryModel>> resp = new JsonResponse<List<AuditLinkCategoryModel>>();

		try {
			resp = restTemplate.getForObject(env.getAuditUrl() + "rest-viewAuditManagee", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewNoticeInitiate ends");
		return resp.getBody();
	}

	@GetMapping("audit-dashboard-link-commensement-editSaveDraftcpommense")
	public @ResponseBody List<AuditLinkCategoryModel> editDraftCommensement(@RequestParam String draftNo,
			HttpSession session, Model model) {
		System.out.println(draftNo);
		logger.info("Method : editDraftDetials starts");
		List<AuditLinkCategoryModel> noticeList = new ArrayList<AuditLinkCategoryModel>();
		List<UploadDocumentModel> documentList = new ArrayList<UploadDocumentModel>();

		String dateFormat = "";

		if (draftNo != null && draftNo != "") {
			try {
				AuditLinkCategoryModel[] intiateNoticeModel = restTemplate.getForObject(
						env.getAuditUrl() + "edit-draft-details-commensement-manage?id=" + draftNo,
						AuditLinkCategoryModel[].class);
				noticeList = Arrays.asList(intiateNoticeModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		  if (noticeList != null) { documentList = noticeList.get(0).getDocumentList();
		  
		  for (AuditLinkCategoryModel a : noticeList) {
		 
		  
		  }
		  
		  if (documentList != null) { for (UploadDocumentModel m : documentList) {
		  
		  if (m.getFileName() != null && m.getFileName() != "") {
		  
		  String[] extension = m.getFileName().split("\\."); if (extension.length == 2)
		  { if (extension[1].equals("xls") || extension[1].equals("xlsx")) {
		  
		  String docPath = "<i class=\"fa fa-file-excel-o excel\" title= " +
		  m.getFileName() + "></i> ";
		  
		  m.setAction(docPath); } if (extension[1].equals("pdf")) { String docPath =
		  " <i class=\"fa fa-file-pdf-o excel pdf\"   title=" + m.getFileName() +
		  " ;></i> ";
		  
		  m.setAction(docPath); } if (extension[1].equals("doc") ||
		  extension[1].equals("dox") || extension[1].equals("docx")) { String docPath =
		  " <i class=\"fa fa-file-word-o \" aria-hidden=\"true\"  title=" +
		  m.getFileName() + "></i> "; m.setAction(docPath); } if
		  (extension[1].equals("png") || extension[1].equals("jpg") ||
		  extension[1].equals("jpeg")) { String docPath =
		  " <i class=\"fa fa-picture-o \"\" aria-hidden=\"true\" title=" +
		  m.getFileName() + "></i>  "; m.setAction(docPath); } } else {
		  m.setAction("N/A"); } } else { m.setAction("N/A"); }
		  m.setAction("<a href=\"/document/procurment/" + m.getFileName() +
		  "\" target=\"_blank\" >" + m.getAction() + "</a>");
		  
		  } }
		  
		  
		  }
		 

		noticeList.get(0).setDocumentList(documentList);
		logger.info("Method : editDraftDetials ends");
		return noticeList;
	}
	

}
