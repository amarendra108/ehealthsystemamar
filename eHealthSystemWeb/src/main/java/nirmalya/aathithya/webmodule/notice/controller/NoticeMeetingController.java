package nirmalya.aathithya.webmodule.notice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import nirmalya.aathithya.webmodule.common.utils.DateFormatter;
import nirmalya.aathithya.webmodule.common.utils.DropDownModel;
import nirmalya.aathithya.webmodule.common.utils.EnvironmentVaribles;
import nirmalya.aathithya.webmodule.common.utils.FileUpload;
import nirmalya.aathithya.webmodule.common.utils.JsonResponse;
import nirmalya.aathithya.webmodule.notice.model.InitiateNoticeDocumentModel;
import nirmalya.aathithya.webmodule.notice.model.IntiateNoticeModel;



@Controller
@RequestMapping(value = { "notice/" })
public class NoticeMeetingController {
	Logger logger = LoggerFactory.getLogger(NoticeMeetingController.class);

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EnvironmentVaribles env;

	@Autowired
	FileUpload fileUpload;
	/*
	 * Get Mapping for notice page
	 */

	@GetMapping("notice-meeting")
	public String noticeMeetingDetails(Model model, HttpSession session) {

		logger.info("Method : noticeMeetingDetails starts");
		// drop down for employee list
		try {
			DropDownModel[] notice = restTemplate.getForObject(env.getNotice() + "getempListsMeeting",
					DropDownModel[].class);
			List<DropDownModel> noticelists = Arrays.asList(notice);
			model.addAttribute("emplists", noticelists);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		try {
			DropDownModel[] notice = restTemplate.getForObject(env.getNotice() + "getdeptListsMeeting",
					DropDownModel[].class);
			List<DropDownModel> noticelists = Arrays.asList(notice);
			model.addAttribute("deptList", noticelists);

		} catch (RestClientException e) {
			e.printStackTrace();
		}

		logger.info("Method : noticeMeetingDetails ends");
		return "notice/notice-meeting";
	}

	/*
	 * Add Meeting Schedule
	 *
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(value = "notice-meeting-save-meetingDetails")
	public @ResponseBody JsonResponse<Object> addMeetingDetails(
			@RequestBody List<IntiateNoticeModel> noticeInitiateModel, HttpSession session, Model model) {
		logger.info("Method : addMeetingDetails function starts");
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
		JsonResponse<Object> res = new JsonResponse<Object>();
		try {

			res = restTemplate.postForObject(env.getNotice() + "rest-add-meetingDetails", noticeInitiateModel,
					JsonResponse.class);

		} catch (RestClientException e) {
			e.printStackTrace();
		}
		if (res.getMessage() != "" && res.getMessage() != null) {
			res.setCode(res.getMessage());
			res.setMessage("Unsuccess");
		} else {
			res.setMessage("Success");
		}

		logger.info("Method : addMeetingDetails function Ends");
		return res;
	}

	/*
	 * For view All Details
	 * 
	 */
	@GetMapping(value = { "notice-meeting-view" })
	public @ResponseBody List<IntiateNoticeModel> viewMeetingDetails(HttpSession session) {
		logger.info("Method : viewMeetingDetails starts");

		List<IntiateNoticeModel> noticeList = new ArrayList<IntiateNoticeModel>();

		List<InitiateNoticeDocumentModel> documentList = new ArrayList<InitiateNoticeDocumentModel>();

		try {
			IntiateNoticeModel[] intiateNoticeModel = restTemplate
					.getForObject(env.getNotice() + "notice-meeting-view-rest", IntiateNoticeModel[].class);
			noticeList = Arrays.asList(intiateNoticeModel);

		} catch (Exception e) {
			e.printStackTrace();
		}
		documentList = noticeList.get(0).getDocumentList();

		
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
System.out.println("noticeList"+noticeList);
		logger.info("Method : viewMeetingDetails ends");
		return noticeList;

	}

}
