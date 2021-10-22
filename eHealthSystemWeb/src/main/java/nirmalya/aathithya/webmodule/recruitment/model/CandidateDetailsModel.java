package nirmalya.aathithya.webmodule.recruitment.model;

import java.util.ArrayList;
import java.util.List;

public class CandidateDetailsModel {

	private String candidateId;
	private String firstName;
	private String lastName;
	private String gender;
	private String dob;
	private String bloodGroup;
	private String maritalStatus;
	private String nationality;
	private String fatherName;
	private String motherName;
	private String mobileNo;
	private String personalEmail;
	private String workEmail;
	private String createdBy;
	private String createdOn;
	private String shortlistStatus;
	private String fileUpload;
	private String location;
	private String experience;
	private String source;
	private String resume;
	private String noApplications;
	private String requisitionId;
	private String rejectStatus;
	private String selectedStatus;
	
	
	public String getRequisitionId() {
		return requisitionId;
	}
	public void setRequisitionId(String requisitionId) {
		this.requisitionId = requisitionId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getPersonalEmail() {
		return personalEmail;
	}
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}
	public String getWorkEmail() {
		return workEmail;
	}
	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getShortlistStatus() {
		return shortlistStatus;
	}
	public void setShortlistStatus(String shortlistStatus) {
		this.shortlistStatus = shortlistStatus;
	}
	public String getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(String fileUpload) {
		this.fileUpload = fileUpload;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	
	public String getNoApplications() {
		return noApplications;
	}
	public void setNoApplications(String noApplications) {
		this.noApplications = noApplications;
	}
	public String getRejectStatus() {
		return rejectStatus;
	}
	public void setRejectStatus(String rejectStatus) {
		this.rejectStatus = rejectStatus;
	}
	public String getSelectedStatus() {
		return selectedStatus;
	}
	public void setSelectedStatus(String selectedStatus) {
		this.selectedStatus = selectedStatus;
	}
	@Override
	public String toString() {
		return "CandidateDetailsModel [candidateId=" + candidateId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", dob=" + dob + ", bloodGroup=" + bloodGroup + ", maritalStatus="
				+ maritalStatus + ", nationality=" + nationality + ", fatherName=" + fatherName + ", motherName="
				+ motherName + ", mobileNo=" + mobileNo + ", personalEmail=" + personalEmail + ", workEmail="
				+ workEmail + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", shortlistStatus="
				+ shortlistStatus + ", fileUpload=" + fileUpload + ", location=" + location + ", experience="
				+ experience + ", source=" + source + ", resume=" + resume
				+ "]";
	}
	
	

}
