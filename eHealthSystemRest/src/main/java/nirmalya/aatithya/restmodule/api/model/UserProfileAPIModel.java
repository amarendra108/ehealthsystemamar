package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserProfileAPIModel {

	private String eCardNo;
	private String profileImage;
	private String fullName;
	private String dob;
	private String bloodGroup;
	private String gender;
	private String mobile;
	private String eName;
	private String eRelation;
	private String eMobile;
	private String fDoctor;
	private String speciality;
	private String docMobile;
	private String title;
	private String fName;
	private String lName;
	private String profileImageName;
	private String profileImageType;
	private List<String> pImage = new ArrayList<String>();
	private String address;
	
	public UserProfileAPIModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserProfileAPIModel(Object eCardNo, Object profileImage, Object fullName, Object dob, Object bloodGroup,
			Object gender, Object mobile, Object eName, Object eRelation, Object eMobile, Object fDoctor,
			Object speciality, Object docMobile, Object title, Object fName, Object lName, Object address) {
		super();
		this.eCardNo = (String) eCardNo;
		this.profileImage = (String) profileImage;
		this.fullName = (String) fullName;
		this.dob = (String) dob;
		this.bloodGroup = (String) bloodGroup;
		this.gender = (String) gender;
		this.mobile = (String) mobile;
		this.eName = (String) eName;
		this.eRelation = (String) eRelation;
		this.eMobile = (String) eMobile;
		this.fDoctor = (String) fDoctor;
		this.speciality = (String) speciality;
		this.docMobile = (String) docMobile;
		this.title = (String) title;
		this.fName = (String) fName;
		this.lName = (String) lName;
		this.address = (String) address;
	}

	public String geteCardNo() {
		return eCardNo;
	}

	public void seteCardNo(String eCardNo) {
		this.eCardNo = eCardNo;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String geteRelation() {
		return eRelation;
	}

	public void seteRelation(String eRelation) {
		this.eRelation = eRelation;
	}

	public String geteMobile() {
		return eMobile;
	}

	public void seteMobile(String eMobile) {
		this.eMobile = eMobile;
	}

	public String getfDoctor() {
		return fDoctor;
	}

	public void setfDoctor(String fDoctor) {
		this.fDoctor = fDoctor;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getDocMobile() {
		return docMobile;
	}

	public void setDocMobile(String docMobile) {
		this.docMobile = docMobile;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}
	
	public String getProfileImageName() {
		return profileImageName;
	}

	public void setProfileImageName(String profileImageName) {
		this.profileImageName = profileImageName;
	}

	public String getProfileImageType() {
		return profileImageType;
	}

	public void setProfileImageType(String profileImageType) {
		this.profileImageType = profileImageType;
	}

	public List<String> getpImage() {
		return pImage;
	}

	public void setpImage(List<String> pImage) {
		this.pImage = pImage;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
