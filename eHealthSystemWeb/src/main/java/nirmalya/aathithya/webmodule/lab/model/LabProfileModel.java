package nirmalya.aathithya.webmodule.lab.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;


public class LabProfileModel {
private Integer doctorId;
private String doctorName;
private String birthDate;
private String gender;
private String education;
private String speciality;
private String organisation;

// My Identification

private String imaNo;
private String panCardNo;
private String passportNo;
private String adharNo;
private String votorCardNo;
private String licenseNo;
private String licenseAuthority;
private String digitalSign;

// Home Address
private Integer country;
private String countryName;
private Integer state;
private String stateName;
private Integer district;
private String districtName;
private Integer city;
private String cityName;
private String address;
private String zipCode;
private String mobile;
private String office;
private String email;

private String drProfDoc;

//
private Integer attachmentId;
private String attachment;
private String roleId;
private String roleName;


private List<LabrProfileDocumentModel> documentList;
//
private String profileImg;
private String docExperience;

public LabProfileModel() {
	super();
	// TODO Auto-generated constructor stub
}

public Integer getDoctorId() {
	return doctorId;
}

public void setDoctorId(Integer doctorId) {
	this.doctorId = doctorId;
}

public String getDoctorName() {
	return doctorName;
}

public void setDoctorName(String doctorName) {
	this.doctorName = doctorName;
}

public String getBirthDate() {
	return birthDate;
}

public void setBirthDate(String birthDate) {
	this.birthDate = birthDate;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getEducation() {
	return education;
}

public void setEducation(String education) {
	this.education = education;
}

public String getSpeciality() {
	return speciality;
}

public void setSpeciality(String speciality) {
	this.speciality = speciality;
}

public String getOrganisation() {
	return organisation;
}

public void setOrganisation(String organisation) {
	this.organisation = organisation;
}

public String getImaNo() {
	return imaNo;
}

public void setImaNo(String imaNo) {
	this.imaNo = imaNo;
}

public String getPanCardNo() {
	return panCardNo;
}

public void setPanCardNo(String panCardNo) {
	this.panCardNo = panCardNo;
}

public String getPassportNo() {
	return passportNo;
}

public void setPassportNo(String passportNo) {
	this.passportNo = passportNo;
}

public String getAdharNo() {
	return adharNo;
}

public void setAdharNo(String adharNo) {
	this.adharNo = adharNo;
}

public String getVotorCardNo() {
	return votorCardNo;
}

public void setVotorCardNo(String votorCardNo) {
	this.votorCardNo = votorCardNo;
}

public String getLicenseNo() {
	return licenseNo;
}

public void setLicenseNo(String licenseNo) {
	this.licenseNo = licenseNo;
}

public String getLicenseAuthority() {
	return licenseAuthority;
}

public void setLicenseAuthority(String licenseAuthority) {
	this.licenseAuthority = licenseAuthority;
}

public String getDigitalSign() {
	return digitalSign;
}

public void setDigitalSign(String digitalSign) {
	this.digitalSign = digitalSign;
}

public Integer getCountry() {
	return country;
}

public void setCountry(Integer country) {
	this.country = country;
}

public String getCountryName() {
	return countryName;
}

public void setCountryName(String countryName) {
	this.countryName = countryName;
}

public Integer getState() {
	return state;
}

public void setState(Integer state) {
	this.state = state;
}

public String getStateName() {
	return stateName;
}

public void setStateName(String stateName) {
	this.stateName = stateName;
}

public Integer getDistrict() {
	return district;
}

public void setDistrict(Integer district) {
	this.district = district;
}

public String getDistrictName() {
	return districtName;
}

public void setDistrictName(String districtName) {
	this.districtName = districtName;
}

public Integer getCity() {
	return city;
}

public void setCity(Integer city) {
	this.city = city;
}

public String getCityName() {
	return cityName;
}

public void setCityName(String cityName) {
	this.cityName = cityName;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getZipCode() {
	return zipCode;
}

public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
}

public String getMobile() {
	return mobile;
}

public void setMobile(String mobile) {
	this.mobile = mobile;
}

public String getOffice() {
	return office;
}

public void setOffice(String office) {
	this.office = office;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getAttachment() {
	return attachment;
}

public void setAttachment(String attachment) {
	this.attachment = attachment;
}


public Integer getAttachmentId() {
	return attachmentId;
}

public void setAttachmentId(Integer attachmentId) {
	this.attachmentId = attachmentId;
}

public String getDrProfDoc() {
	return drProfDoc;
}

public void setDrProfDoc(String drProfDoc) {
	this.drProfDoc = drProfDoc;
}



public String getRoleId() {
	return roleId;
}

public void setRoleId(String roleId) {
	this.roleId = roleId;
}

public String getRoleName() {
	return roleName;
}

public void setRoleName(String roleName) {
	this.roleName = roleName;
}

public List<LabrProfileDocumentModel> getDocumentList() {
	return documentList;
}

public void setDocumentList(List<LabrProfileDocumentModel> documentList) {
	this.documentList = documentList;
}

public String getProfileImg() {
	return profileImg;
}

public void setProfileImg(String profileImg) {
	this.profileImg = profileImg;
}

public String getDocExperience() {
	return docExperience;
}

public void setDocExperience(String docExperience) {
	this.docExperience = docExperience;
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
