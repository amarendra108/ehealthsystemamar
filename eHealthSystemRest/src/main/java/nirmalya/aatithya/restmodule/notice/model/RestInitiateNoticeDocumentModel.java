package nirmalya.aatithya.restmodule.notice.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestInitiateNoticeDocumentModel {

	private String documnentName;
	private String fileName;
	private String noticeId;
	private String draftNo;
	private List<String> documentFile = new ArrayList<String>();
	private String action;
	private String imageNameEdit;
	private String createdBy;
	private String meetingNo;

	public RestInitiateNoticeDocumentModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestInitiateNoticeDocumentModel(Object documnentName, Object fileName, Object noticeId) {
		super();
		this.documnentName = (String) documnentName;
		this.fileName = (String) fileName;
		this.noticeId = (String) noticeId;
	}

	public RestInitiateNoticeDocumentModel(Object documnentName, Object fileName, Object noticeId, Object draftNo) {
		super();
		this.documnentName = (String) documnentName;
		this.fileName = (String) fileName;
		this.noticeId = (String) noticeId;
		this.draftNo = (String) draftNo;
	}

	public RestInitiateNoticeDocumentModel(Object documnentName, Object fileName, Object meetingNo ,Object noticeId, Object draftNo) {
		super();
		this.documnentName = (String) documnentName;
		this.fileName = (String) fileName;
		this.meetingNo = (String) meetingNo;
		this.noticeId = (String) noticeId;
		this.draftNo = (String) draftNo;
	}


	public String getMeetingNo() {
		return meetingNo;
	}

	public void setMeetingNo(String meetingNo) {
		this.meetingNo = meetingNo;
	}

	public String getDraftNo() {
		return draftNo;
	}

	public void setDraftNo(String draftNo) {
		this.draftNo = draftNo;
	}

	public String getDocumnentName() {
		return documnentName;
	}

	public void setDocumnentName(String documnentName) {
		this.documnentName = documnentName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public List<String> getDocumentFile() {
		return documentFile;
	}

	public void setDocumentFile(List<String> documentFile) {
		this.documentFile = documentFile;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getImageNameEdit() {
		return imageNameEdit;
	}

	public void setImageNameEdit(String imageNameEdit) {
		this.imageNameEdit = imageNameEdit;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		ObjectMapper mapperObj = new ObjectMapper();
		String jsonStr;
		try {
			jsonStr = mapperObj.writeValueAsString(this);
		} catch (IOException ex) {

			jsonStr = ex.toString();
		}
		return jsonStr;
	}

}
