package nirmalya.aatithya.restmodule.recruitment.model;

public class CandidateWorkExperienceModel {

	private String candidateId;
	private String workExperineceId;
	private String designation;
	private String organization;
	private String description;
	private String workFrom;
	private String workTill;
	private String noticePeriod;
	private String createdBy;
	
	
	
	public CandidateWorkExperienceModel(Object candidateId, Object workExperineceId, Object designation,
			Object organization, Object description, Object workFrom, Object workTill, Object noticePeriod,
			Object createdBy) {
		
		this.candidateId = (String) candidateId;
		this.workExperineceId = (String) workExperineceId;
		this.designation = (String) designation;
		this.organization = (String) organization;
		this.description = (String) description;
		this.workFrom = (String) workFrom;
		this.workTill = (String) workTill;
		this.noticePeriod = (String) noticePeriod;
		this.createdBy = (String) createdBy;
	}
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public String getWorkExperineceId() {
		return workExperineceId;
	}
	public void setWorkExperineceId(String workExperineceId) {
		this.workExperineceId = workExperineceId;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWorkFrom() {
		return workFrom;
	}
	public void setWorkFrom(String workFrom) {
		this.workFrom = workFrom;
	}
	public String getWorkTill() {
		return workTill;
	}
	public void setWorkTill(String workTill) {
		this.workTill = workTill;
	}
	public String getNoticePeriod() {
		return noticePeriod;
	}
	public void setNoticePeriod(String noticePeriod) {
		this.noticePeriod = noticePeriod;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
}
