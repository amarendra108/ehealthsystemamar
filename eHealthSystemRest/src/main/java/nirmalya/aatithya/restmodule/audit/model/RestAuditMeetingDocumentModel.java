package nirmalya.aatithya.restmodule.audit.model;

import java.util.ArrayList;
import java.util.List;

import nirmalya.aatithya.restmodule.common.utils.DropDownModel;

public class RestAuditMeetingDocumentModel {
	private String documnentName;
	private String fileName;
	private String meetingId;
	private String draftNo;
	private List<String> documentFile = new ArrayList<String>();
	private String action;
	private String createdBy;
	private List<DropDownModel> drop = new ArrayList<DropDownModel>();

	public RestAuditMeetingDocumentModel() {
		super();
	}

	public RestAuditMeetingDocumentModel(Object documnentName, Object fileName, Object meetingId) {
		super();
		this.documnentName = (String) documnentName;
		this.fileName = (String) fileName;
		this.meetingId = (String) meetingId;
	}
	public RestAuditMeetingDocumentModel(Object documnentName, Object fileName, Object meetingId, Object draftNo) {
		super();
		this.documnentName = (String) documnentName;
		this.fileName = (String) fileName;
		this.meetingId = (String) meetingId;
		this.draftNo = (String) draftNo;
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

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public List<DropDownModel> getDrop() {
		return drop;
	}

	public void setDrop(List<DropDownModel> drop) {
		this.drop = drop;
	}

}
