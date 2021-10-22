package nirmalya.aathithya.webmodule.audit.model;




import java.util.ArrayList;
import java.util.List;

import nirmalya.aathithya.webmodule.common.utils.DropDownModel;

public class AuditMangeDocumentModel {
	
	private String documnentName;
	private String fileName;
	private String auditInitiate;
	private List<String> documentFile = new ArrayList<String>();
	private String action;
	private String imageNameEdit;
	private String createdBy;
	private List<DropDownModel> drop = new ArrayList<DropDownModel>();
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
	public List<DropDownModel> getDrop() {
		return drop;
	}
	public void setDrop(List<DropDownModel> drop) {
		this.drop = drop;
	}
	public String getAuditInitiate() {
		return auditInitiate;
	}
	public void setAuditInitiate(String auditInitiate) {
		this.auditInitiate = auditInitiate;
	}
	
	

}