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
import nirmalya.aathithya.webmodule.audit.model.AuditManageModel;
import nirmalya.aathithya.webmodule.audit.model.AuditMangeDocumentModel;
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

import nirmalya.aathithya.webmodule.audit.controller.AuditManageController;


@Controller
@RequestMapping(value = "audit")
public class AuditManageController {
	
	Logger logger = LoggerFactory.getLogger(AuditManageController.class);
	
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;
	
	@Autowired
	FileUpload fileUpload;
	
	
	@GetMapping("/audit-dashboard-manage")
	public String viewTrainingNomination(Model model, HttpSession session) {

		logger.info("Method : viewTrainingNomination starts");
		
		logger.info("Method : viewTrainingNomination ends");

		return "/audit/audit-dashboard-manage";
	}
	@SuppressWarnings("unchecked")

	@GetMapping("audit-dashboard-manage-viewqq")
	public @ResponseBody List<AuditManageModel> viewCandidates(HttpSession session) {
		logger.info("Method : viewmanageaudit starts");

		JsonResponse<List<AuditManageModel>> resp = new JsonResponse<List<AuditManageModel>>();

		try {
			resp = restTemplate.getForObject(env.getAuditUrl() + "viewmanageaudit", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();

		List<AuditManageModel> cand = mapper.convertValue(resp.getBody(),
				new TypeReference<List<AuditManageModel>>() {
				});


		String message = resp.getMessage();

		if (message != null && message != "") {

		} else {

			resp.setMessage("Success");
		}

		resp.setBody(cand);

		System.out.println(resp.getBody());
		logger.info("Method : viewmanageaudit ends");
		return resp.getBody();
	}
	
	@GetMapping("audit-dashboard-manage-view")
	public @ResponseBody List<AuditManageModel> viewAuditDetails(@RequestParam String id, HttpSession session,
			Model model) {
		logger.info("Method : viewAuditDetails starts");
		List<AuditManageModel> auditList = new ArrayList<AuditManageModel>();
		try {

			AuditManageModel[] audit = restTemplate
					.getForObject(env.getAuditUrl() + "rest-viewmanageaudit?id=" + id, AuditManageModel[].class);
			auditList = Arrays.asList(audit);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Method : viewAuditDetails ends");
		return auditList;
	}
	@GetMapping(value = { "audit-dashboard-manage-viewqq-view" })
	public @ResponseBody List<AuditManageModel> viewDocumentsAudit(@RequestParam String id, HttpSession session,
			Model model) {
		logger.info("Method : managemeetingaddedit starts");
		List<AuditManageModel> noticeList = new ArrayList<AuditManageModel>();
		List<AuditMangeDocumentModel> documentList = new ArrayList<AuditMangeDocumentModel>();
		String dateFormat = "";
		if (id != null && id != "") {
			try {
				dateFormat = (String) session.getAttribute("DATEFORMAT");
				AuditManageModel[] intiateNoticeModel = restTemplate
						.getForObject(env.getAuditUrl() + "audit-show-detailssss?id=" + id, AuditManageModel[].class);
				noticeList = Arrays.asList(intiateNoticeModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (noticeList != null) {
			documentList = noticeList.get(0).getDocumentList();

			for (AuditManageModel a : noticeList) {
				if (a.getStartDate() != null && a.getStartDate() != "") {
					a.setStartDate(DateFormatter.dateFormat(a.getStartDate(), dateFormat));
				}
				/*
				 * if (a.getEndDate() != null && a.getEndDate() != "") {
				 * a.setEndDate(DateFormatter.dateFormat(a.getEndDate(), dateFormat)); }
				 */
				
			}
			
			  if (documentList != null) { for (AuditMangeDocumentModel m : documentList) {
			  
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

		logger.info("Method : noticeDetailsEdit ends");
		return noticeList;
	}
}
