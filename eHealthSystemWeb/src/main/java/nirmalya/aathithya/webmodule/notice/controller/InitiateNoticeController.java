package nirmalya.aathithya.webmodule.notice.controller;

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

import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EmailAttachmentSender;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.notice.model.InitiateNoticeDocumentModel;
import nirmalya.aathithya.webmodule.notice.model.IntiateNoticeModel;

@Controller
@RequestMapping(value = { "notice/" })
public class InitiateNoticeController {
	Logger logger = LoggerFactory.getLogger(InitiateNoticeController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;

	/*
	 * Get Mapping for notice page
	 */

	@GetMapping("create-notice")
	public String noticeDetails(Model model, HttpSession session) {

		logger.info("Method : noticeDetails starts");

		// drop down for employee list
		try {
			DropDownModel[] notice = restTemplate.getForObject(env.getNotice() + "getempLists", DropDownModel[].class);
			List<DropDownModel> noticelists = Arrays.asList(notice);
			model.addAttribute("emplists", noticelists);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] notice = restTemplate.getForObject(env.getNotice() + "getdeptLists", DropDownModel[].class);
			List<DropDownModel> noticelists = Arrays.asList(notice);
			model.addAttribute("deptList", noticelists);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		logger.info("Method : noticeDetails ends");
		return "notice/noticeInitiate";
	}

	/*
	 * View Notice Details
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("create-notice-view-Throughajax")
	public @ResponseBody List<IntiateNoticeModel> viewNoticeInitiate() {

		logger.info("Method : viewNoticeInitiate starts");

		JsonResponse<List<IntiateNoticeModel>> resp = new JsonResponse<List<IntiateNoticeModel>>();

		try {
			resp = restTemplate.getForObject(env.getNotice() + "rest-viewNoticeinitiate", JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : viewNoticeInitiate ends");
		return resp.getBody();
	}

	/*
	 * Notice type autoSearch
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = { "create-notice-autosearch-notice" })
	public @ResponseBody JsonResponse<IntiateNoticeModel> getNoticeAutoSearchList(Model model,
			@RequestBody String searchValue, BindingResult result) {
		logger.info("Method : getNoticeAutoSearchList starts");
		JsonResponse<IntiateNoticeModel> res = new JsonResponse<IntiateNoticeModel>();

		try {
			res = restTemplate.getForObject(env.getNotice() + "getNoticeListByAutoSearch?id=" + searchValue,
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
		logger.info("Method : getNoticeAutoSearchList ends");
		return res;
	}

	/*
	 * Add Notice Details
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "create-notice-add")
	public @ResponseBody JsonResponse<Object> saveNoticeDetails(
			@RequestBody List<IntiateNoticeModel> noticeInitiateModel, HttpSession session, Model model) {
		logger.info("Method : saveNoticeDetails function starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		List<IntiateNoticeModel> noticeList = new ArrayList<IntiateNoticeModel>();
		List<InitiateNoticeDocumentModel> docList = new ArrayList<InitiateNoticeDocumentModel>();
		String userId = "";
		String dateFormat = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (IntiateNoticeModel m : noticeInitiateModel) {
			m.setCreatedBy(userId);
			m.setStartDate(DateFormatter.inputDateFormat(m.getStartDate(), dateFormat));
			m.setEndDate(DateFormatter.inputDateFormat(m.getEndDate(), dateFormat));

		}
		for (InitiateNoticeDocumentModel a : noticeInitiateModel.get(0).getDocumentList()) {

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

			res = restTemplate.postForObject(env.getNotice() + "rest-add-notice", noticeInitiateModel,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		noticeList = mapper.convertValue(res.getBody(), new TypeReference<List<IntiateNoticeModel>>() {
		});
		if (noticeList != null) {

			for (IntiateNoticeModel a : noticeList) {

				if (a.getStartDate() != null && a.getStartDate() != "") {
					a.setStartDate(DateFormatter.dateFormat(a.getStartDate(), dateFormat));
				}
				if (a.getEndDate() != null && a.getEndDate() != "") {
					a.setEndDate(DateFormatter.dateFormat(a.getEndDate(), dateFormat));
				}
				if (a.getCreatedOn() != null && a.getCreatedOn() != "") {
					a.setCreatedOn(DateFormatter.dateFormat(a.getCreatedOn(), dateFormat));
				}

			}
			docList = noticeInitiateModel.get(0).getDocumentList();
			for (InitiateNoticeDocumentModel m : docList) {
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
		logger.info("Method : saveNoticeDetails function Ends");
		return res;
	}

	/*
	 *
	 * Delete employee details
	 *
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("create-notice-delete")
	public @ResponseBody JsonResponse<IntiateNoticeModel> deleteNoticeDetails(@RequestParam String deleteId) {

		logger.info("Method : deleteNoticeDetails starts");

		JsonResponse<IntiateNoticeModel> jsonResponse = new JsonResponse<IntiateNoticeModel>();

		try {
			jsonResponse = restTemplate.getForObject(env.getNotice() + "delete-notice-details?id=" + deleteId,
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

	/*
	 * Add Draft Details
	 * 
	 */

	@SuppressWarnings("unchecked")
	@PostMapping("create-notice-save-draft")
	public @ResponseBody JsonResponse<Object> addDraftDetails(@RequestBody List<IntiateNoticeModel> noticeInitiateModel,
			HttpSession session, Model model) {
		logger.info("Method : addDraftDetails function starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		List<IntiateNoticeModel> noticeList = new ArrayList<IntiateNoticeModel>();
		List<InitiateNoticeDocumentModel> docList = new ArrayList<InitiateNoticeDocumentModel>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (IntiateNoticeModel m : noticeInitiateModel) {
			m.setCreatedBy(userId);

		}
		for (InitiateNoticeDocumentModel a : noticeInitiateModel.get(0).getDocumentList()) {

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

			res = restTemplate.postForObject(env.getNotice() + "add-draft-details", noticeInitiateModel,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		noticeList = mapper.convertValue(res.getBody(), new TypeReference<List<IntiateNoticeModel>>() {
		});
		if (noticeList != null) {

			docList = noticeInitiateModel.get(0).getDocumentList();
			for (InitiateNoticeDocumentModel m : docList) {
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
	@GetMapping("create-notice-viewdraft")
	public @ResponseBody List<IntiateNoticeModel> viewDraft(@RequestParam String noticeId, HttpSession session) {

		logger.info("Method : viewDraft starts");
		String userId = "";
		List<IntiateNoticeModel> noticeList = new ArrayList<IntiateNoticeModel>();
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}

		JsonResponse<List<IntiateNoticeModel>> resp = new JsonResponse<List<IntiateNoticeModel>>();

		try {
			resp = restTemplate.getForObject(
					env.getNotice() + "rest-viewDrafttable?noticeId=" + noticeId + "&createdBy=" + userId,
					JsonResponse.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		noticeList = mapper.convertValue(resp.getBody(), new TypeReference<List<IntiateNoticeModel>>() {
		});

		logger.info("Method : viewDraft ends");
		return resp.getBody();
	}

	/*
	 * Edit Draft Details
	 * 
	 */
	@GetMapping("create-notice-draft-edit")
	public @ResponseBody List<IntiateNoticeModel> editDraftDetials(@RequestParam String draftNo, HttpSession session,
			Model model) {
		logger.info("Method : editDraftDetials starts");
		List<IntiateNoticeModel> noticeList = new ArrayList<IntiateNoticeModel>();
		List<InitiateNoticeDocumentModel> documentList = new ArrayList<InitiateNoticeDocumentModel>();

		String dateFormat = "";

		if (draftNo != null && draftNo != "") {
			try {
				IntiateNoticeModel[] intiateNoticeModel = restTemplate
						.getForObject(env.getNotice() + "edit-draft-details?id=" + draftNo, IntiateNoticeModel[].class);
				noticeList = Arrays.asList(intiateNoticeModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (noticeList != null) {
			documentList = noticeList.get(0).getDocumentList();

			for (IntiateNoticeModel a : noticeList) {
				if (a.getDraftDate() != null && a.getDraftDate() != "") {
					a.setDraftDate(DateFormatter.dateFormat(a.getDraftDate(), dateFormat));
				}
			}
			if (documentList != null) {
				for (InitiateNoticeDocumentModel m : documentList) {

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

		logger.info("Method : editDraftDetials ends");
		return noticeList;
	}

	/*
	 * Add Send Details
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "create-notice-send")
	public @ResponseBody JsonResponse<Object> saveSendDetails(@RequestBody List<IntiateNoticeModel> noticeInitiateModel,
			HttpSession session, Model model) {
		logger.info("Method : saveSendDetails function starts");
		JsonResponse<Object> res = new JsonResponse<Object>();
		List<IntiateNoticeModel> noticeList = new ArrayList<IntiateNoticeModel>();
		List<InitiateNoticeDocumentModel> docList = new ArrayList<InitiateNoticeDocumentModel>();
		String userId = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (IntiateNoticeModel m : noticeInitiateModel) {
			m.setCreatedBy(userId);
		}

		try {

			res = restTemplate.postForObject(env.getNotice() + "rest-send-details", noticeInitiateModel,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		String message = res.getMessage();
		noticeInitiateModel.get(0).setDocumentList(docList);
		if (message != null && message != "") {

		} else {
			res.setMessage("Success");
			
			String tolist = noticeInitiateModel.get(0).getSendPersonTo();
			//System.out.println("bikash4 "+tolist);
			String cclist = noticeInitiateModel.get(0).getSendPersonCc();
			//System.out.println("bikash3 "+cclist);
			String comma=",";
			String newlist= tolist.concat(comma).concat(cclist);
			//System.out.println("bikash2 "+newlist);
			String str[]=newlist.split(",");
			System.out.println("str "+Arrays.toString(str));
			//System.out.println(Arrays.toString("bikash1 "+str));			
			//ArrayList<String> mylist= Arrays.asList(str);
			ArrayList<String> mylist= new ArrayList<String>();
			mylist.addAll(Arrays.asList(str));
			//ArrayList<String> aList = new ArrayList<String>(Arrays.asList(question1));
			//aList.addAll(Arrays.asList(question2));
			
			//String x[]=mylist.split(",");
			
			//System.out.println("bikash "+mylist);		
			//System.out.println("First element of the ArrayList: "+mylist.get(0));
			//String s="";
			//s=mylist.split()
			JsonResponse<Object> resp = new JsonResponse<Object>();
			List<String> toAddress = new ArrayList<String>();
			List<String> CcAddress = new ArrayList<String>();
			
			String userName="";
			try {
				userName = (String) session.getAttribute("USER_NAME");
			} catch (Exception e) {
			}
			
			String subject = "From :-"+userName+System.lineSeparator() +"Subject:-"+noticeInitiateModel.get(0).getSendSubject();
			String message1 = noticeInitiateModel.get(0).getSendComment();
			
			String[] attachFiles=null;
			//noticeInitiateModel.get(0).setDocumentList(docList);
			System.out.print("@@@@");
			System.out.println(noticeInitiateModel.get(0).getDocumentList());
			int a =docList.size();
			if(a != 0) {
				 attachFiles = new String[docList.size()];
				for(int i=0; i<attachFiles.length;i++) {
					if(docList.get(0).getDocumentFile()!=null ) {
				attachFiles[i] = env.getFileUploadProcurement() + docList.get(0).getFileName();
					}
				}
				}
			System.out.print("attachFiles");
			System.out.println(Arrays.toString(attachFiles));
			for (int i=0 ;i<mylist.size();i++ )
			{
			try {

				resp = restTemplate.getForObject(env.getNotice() + "getmaildetails?mylist="+mylist.get(i) ,JsonResponse.class);
				
				System.out.println("emails:- "+(String) resp.getBody());
				toAddress.add((String) resp.getBody());
			} catch (RestClientException e) {
				e.printStackTrace();
			}
			}
			CcAddress.add("bikashmohapatra1997@gmail.com");
			//System.out.println("toAddress "+toAddress);
			//EmailAttachmentSender email = new EmailAttachmentSender();
			
			try {
				System.out.println("message1 "+message1);
				EmailAttachmentSender.sendEmailWithAttachments("Smtp.gmail.com", "587", "noticeperiod819@gmail.com",
						"notice@period@bik1997", CcAddress, null, subject, message1, null);
				
				//email.sendEmailWithAttachments(host, port, null, password, toAddress ,ccAddress, subject, Content, null);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
						
						res.setBody(noticeList);	
		}
		//res.setBody(noticeList);
		logger.info("Method : saveSendDetails function Ends");
		return res;
	}


	/*
	 * For view All Details
	 * 
	 */
	@GetMapping(value = { "create-notice-show-details" })
	public @ResponseBody List<IntiateNoticeModel> viewDocuments(@RequestParam String id, HttpSession session,
			Model model) {
		logger.info("Method : viewDocuments starts");
		List<IntiateNoticeModel> noticeList = new ArrayList<IntiateNoticeModel>();
		List<InitiateNoticeDocumentModel> documentList = new ArrayList<InitiateNoticeDocumentModel>();
		
		String dateFormat = "";
		if (id != null && id != "") {
			try {
				IntiateNoticeModel[] intiateNoticeModel = restTemplate
						.getForObject(env.getNotice() + "notice-show-details?id=" + id, IntiateNoticeModel[].class);
				noticeList = Arrays.asList(intiateNoticeModel);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		documentList = noticeList.get(0).getDocumentList();

		for (IntiateNoticeModel a : noticeList) {
			if (a.getDraftDate() != null && a.getDraftDate() != "") {
				a.setDraftDate(DateFormatter.dateFormat(a.getDraftDate(), dateFormat));
			}
		}
		if (documentList != null) {
			for (InitiateNoticeDocumentModel m : documentList) {

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

	

	noticeList.get(0).setDocumentList(documentList);

	logger.info("Method : viewDocuments ends");
	return noticeList;

}

	/*
	 * for edit Notice Details
	 */
	@GetMapping(value = { "create-notice-edit" })
	public @ResponseBody List<IntiateNoticeModel> noticeDetailsEdit(@RequestParam String id, HttpSession session,
			Model model) {
		logger.info("Method : noticeDetailsEdit starts");
		List<IntiateNoticeModel> noticeList = new ArrayList<IntiateNoticeModel>();
		List<InitiateNoticeDocumentModel> documentList = new ArrayList<InitiateNoticeDocumentModel>();
		String dateFormat = "";
		if (id != null && id != "") {
			try {
				dateFormat = (String) session.getAttribute("DATEFORMAT");
				IntiateNoticeModel[] intiateNoticeModel = restTemplate
						.getForObject(env.getNotice() + "get-notice-edit?id=" + id, IntiateNoticeModel[].class);
				noticeList = Arrays.asList(intiateNoticeModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (noticeList != null) {
			documentList = noticeList.get(0).getDocumentList();

			for (IntiateNoticeModel a : noticeList) {
				if (a.getStartDate() != null && a.getStartDate() != "") {
					a.setStartDate(DateFormatter.dateFormat(a.getStartDate(), dateFormat));
				}
				if (a.getEndDate() != null && a.getEndDate() != "") {
					a.setEndDate(DateFormatter.dateFormat(a.getEndDate(), dateFormat));
				}
				if (a.getCreatedOn() != null && a.getCreatedOn() != "") {
					a.setCreatedOn(DateFormatter.dateFormat(a.getCreatedOn(), dateFormat));
				}
			}
			if (documentList != null) {
				for (InitiateNoticeDocumentModel m : documentList) {

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

	/*
	 * Add Meeting Schedule
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "create-notice-save-meetingDetails")
	public @ResponseBody JsonResponse<Object> addMeetingScedule(
			@RequestBody List<IntiateNoticeModel> noticeInitiateModel, HttpSession session, Model model) {
		logger.info("Method : addMeetingScedule function starts");

		JsonResponse<Object> res = new JsonResponse<Object>();
		List<IntiateNoticeModel> noticeList = new ArrayList<IntiateNoticeModel>();
		List<InitiateNoticeDocumentModel> docList = new ArrayList<InitiateNoticeDocumentModel>();
		String userId = "";
		String dateFormat = "";
		try {
			userId = (String) session.getAttribute("USER_ID");
			dateFormat = (String) session.getAttribute("DATEFORMAT");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (IntiateNoticeModel m : noticeInitiateModel) {
			m.setCreatedBy(userId);
			m.setMeetingDate(DateFormatter.inputDateFormat(m.getMeetingDate(), dateFormat));
		}
		for (InitiateNoticeDocumentModel a : noticeInitiateModel.get(0).getDocumentList()) {

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

			res = restTemplate.postForObject(env.getNotice() + "rest-add-meetingdetails", noticeInitiateModel,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		ObjectMapper mapper = new ObjectMapper();

		noticeList = mapper.convertValue(res.getBody(), new TypeReference<List<IntiateNoticeModel>>() {
		});
		if (noticeList != null) {

			for (IntiateNoticeModel a : noticeList) {

				if (a.getMeetingDate() != null && a.getMeetingDate() != "") {
					a.setMeetingDate(DateFormatter.dateFormat(a.getMeetingDate(), dateFormat));
				}

			}
			docList = noticeInitiateModel.get(0).getDocumentList();
			for (InitiateNoticeDocumentModel m : docList) {
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
		logger.info("Method : addMeetingScedule function Ends");
		return res;
	}

}