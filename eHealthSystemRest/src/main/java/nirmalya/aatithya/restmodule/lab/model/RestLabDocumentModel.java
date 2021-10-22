package nirmalya.aatithya.restmodule.lab.model;


import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestLabDocumentModel {
	private String documnentName;
	private String fileName;
	private Integer doctorId;
	private String roleId;
	private String action;
	private String imageNameEdit;
	
	
	public RestLabDocumentModel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RestLabDocumentModel(Object documnentName, Object fileName, Object doctorId, Object roleId) {
		super();
		this.documnentName =(String) documnentName;
		this.fileName = (String)fileName;
		this.doctorId =(Integer) doctorId;
		this.roleId =(String) roleId;

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


	public Integer getDoctorId() {
		return doctorId;
	}


	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}



	public String getRoleId() {
		return roleId;
	}


	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	/*
	 * public List<String> getDocumentFile() { return documentFile; }
	 * 
	 * 
	 * public void setDocumentFile(List<String> documentFile) { this.documentFile =
	 * documentFile; }
	 * 
	 */
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
