package nirmalya.aatithya.restmodule.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OtherUsersProfileModel {

	private String id;
	private String useraccount;
	private String name;
	private String profileimage;
	private String gender;
	private String mobile;
	private String birthdate;
	private String email;
	private String state;
	private String country;
	private String education;
	private String speciality;
	private String organization;
	private String aadhaar;
	private String imano;
	private String pancardno;
	private String passportno;
	private String votercardno;
	private String licenceno;
	private String role;

	public OtherUsersProfileModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OtherUsersProfileModel(Object id, Object useraccount, Object name, Object profileimage, Object gender,
			Object mobile, Object birthdate, Object email, Object state, Object country, Object education,
			Object speciality, Object organization, Object aadhaar, Object imano, Object pancardno, Object passportno,
			Object votercardno, Object licenceno, Object role) {
		super();
		this.id = (String) id;
		this.useraccount = (String) useraccount;
		this.name = (String) name;
		this.profileimage = (String) profileimage;
		this.gender = (String) gender;
		this.mobile = (String) mobile;
		this.birthdate = (String) birthdate;
		this.email = (String) email;
		this.state = (String) state;
		this.country = (String) country;
		this.education = (String) education;
		this.speciality = (String) speciality;
		this.organization = (String) organization;
		this.aadhaar = (String) aadhaar;
		this.imano = (String) imano;
		this.pancardno = (String) pancardno;
		this.passportno = (String) passportno;
		this.votercardno = (String) votercardno;
		this.licenceno = (String) licenceno;
		this.role = (String) role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUseraccount() {
		return useraccount;
	}

	public void setUseraccount(String useraccount) {
		this.useraccount = useraccount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileimage() {
		return profileimage;
	}

	public void setProfileimage(String profileimage) {
		this.profileimage = profileimage;
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

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getAadhaar() {
		return aadhaar;
	}

	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}

	public String getImano() {
		return imano;
	}

	public void setImano(String imano) {
		this.imano = imano;
	}

	public String getPancardno() {
		return pancardno;
	}

	public void setPancardno(String pancardno) {
		this.pancardno = pancardno;
	}

	public String getPassportno() {
		return passportno;
	}

	public void setPassportno(String passportno) {
		this.passportno = passportno;
	}

	public String getVotercardno() {
		return votercardno;
	}

	public void setVotercardno(String votercardno) {
		this.votercardno = votercardno;
	}

	public String getLicenceno() {
		return licenceno;
	}

	public void setLicenceno(String licenceno) {
		this.licenceno = licenceno;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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
