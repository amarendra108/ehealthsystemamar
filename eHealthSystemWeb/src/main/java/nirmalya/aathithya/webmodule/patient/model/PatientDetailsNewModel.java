package nirmalya.aathithya.webmodule.patient.model;

import java.io.IOException;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PatientDetailsNewModel {
	private String fileDetails;
	private BigInteger userId;
	private String firstnameid;
	private String lastnameid;
	private BigInteger uhidNo;
	private String bloodgrp;
	private String dobid;
	private Integer age;
	private String gender;
	private String maritialStatus;
	private String occupation;
	private String address;
	private String countryid;
	private String stateid;
	private String cityid;
	private String zipCode;
	private String email;
	private String mobNo;
	private Integer eId;
	private String eName;
	private String eRealtion;
	private String eMobNo;
	private String docName;
	private String docSpeciality;
	private String docMobNo;
	private Double heightId;
	private Double weightid;
	private Double bmi;
	private Double tempCs;
	private Double tempFah;
	private Double bldpsr;
	private Double sysDis;
	private Double pulseMin;
	private Double respBpm;
	private Double oxygenPer;
	private String allerName;
	private Integer allerNameId;
	private String alergenType;
	private Integer alergenTypeId;
	private String allerSeverity;
	private String allerSeverityId;
	private String allerRection;
	private String drUpdatedby;
	private String createdBy;
	private Integer slNo;
	private String bName;
	private String bNameId;
	private String breson;
	private String implantDate;
	
	private String pancardNo;
	private String passportNo;
	private String aadharNo;
	private String votarNo;
	private String licenseNo;
	private String licenseAuthority;
	
	private String homeAdd;
	private String phNo;
	private String cemail;
	private String qualification;
	private String specialization;
	private BigInteger famId;
	private String famMeberName;
	private String famRelation;
	private Integer famAge;
	private BigInteger uhidCardNo;
	
	private Integer vSlNo;
	private Integer vParameterId;
	
	
	private Integer heightSId;
	private Double weightSid;
	private Double bSmi;
	private Double tempSCs;
	private Double tempSFah;
	private Double bldSpsr;
	private Double sysSDis;
	private Double pulseSMin;
	private Double respSBpm;
	private Double oxygenSPer;
	private Integer eRelationId;
	private String vCount;
	private Integer docSpecialityId;
	
	private String bloodgroupId;
	private String genderId;
	private String maritalstatusId;
	private String distId;
	private Integer dist;
	private Integer state;
	private Integer city;
	
	private String famDocId;
	private String famDocs;
	
	private Integer speciality;
	private String patVitalId;
	private Integer allergyId;
	private Integer allergyTypesId;
	private Integer severityTypesId;
	
	private Integer country;
	
	
	
	public String getPatVitalId() {
		return patVitalId;
	}

	public void setPatVitalId(String patVitalId) {
		this.patVitalId = patVitalId;
	}

	public Integer getAllergyId() {
		return allergyId;
	}

	public void setAllergyId(Integer allergyId) {
		this.allergyId = allergyId;
	}

	public Integer getAllergyTypesId() {
		return allergyTypesId;
	}

	public void setAllergyTypesId(Integer allergyTypesId) {
		this.allergyTypesId = allergyTypesId;
	}

	public Integer getSeverityTypesId() {
		return severityTypesId;
	}

	public void setSeverityTypesId(Integer severityTypesId) {
		this.severityTypesId = severityTypesId;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getDistId() {
		return distId;
	}

	public void setDistId(String distId) {
		this.distId = distId;
	}
	public Integer getDist() {
		return dist;
	}

	public void setDist(Integer dist) {
		this.dist = dist;
	}

	public String getBloodgroupId() {
		return bloodgroupId;
	}

	public void setBloodgroupId(String bloodgroupId) {
		this.bloodgroupId = bloodgroupId;
	}

	public String getGenderId() {
		return genderId;
	}

	public void setGenderId(String genderId) {
		this.genderId = genderId;
	}

	public String getMaritalstatusId() {
		return maritalstatusId;
	}

	public void setMaritalstatusId(String maritalstatusId) {
		this.maritalstatusId = maritalstatusId;
	}

	public Integer getDocSpecialityId() {
		return docSpecialityId;
	}

	public void setDocSpecialityId(Integer docSpecialityId) {
		this.docSpecialityId = docSpecialityId;
	}

	public String getvCount() {
		return vCount;
	}

	public void setvCount(String vCount) {
		this.vCount = vCount;
	}

	public Integer geteRelationId() {
		return eRelationId;
	}

	public void seteRelationId(Integer eRelationId) {
		this.eRelationId = eRelationId;
	}

	public Double getWeightSid() {
		return weightSid;
	}

	public void setWeightSid(Double weightSid) {
		this.weightSid = weightSid;
	}

	public Double getbSmi() {
		return bSmi;
	}

	public void setbSmi(Double bSmi) {
		this.bSmi = bSmi;
	}

	public Double getTempSCs() {
		return tempSCs;
	}

	public void setTempSCs(Double tempSCs) {
		this.tempSCs = tempSCs;
	}

	public Double getTempSFah() {
		return tempSFah;
	}

	public void setTempSFah(Double tempSFah) {
		this.tempSFah = tempSFah;
	}

	public Double getBldSpsr() {
		return bldSpsr;
	}

	public void setBldSpsr(Double bldSpsr) {
		this.bldSpsr = bldSpsr;
	}

	public Double getSysSDis() {
		return sysSDis;
	}

	public void setSysSDis(Double sysSDis) {
		this.sysSDis = sysSDis;
	}

	public Double getPulseSMin() {
		return pulseSMin;
	}

	public Integer getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Integer speciality) {
		this.speciality = speciality;
	}

	public void setPulseSMin(Double pulseSMin) {
		this.pulseSMin = pulseSMin;
	}

	public Double getRespSBpm() {
		return respSBpm;
	}

	public void setRespSBpm(Double respSBpm) {
		this.respSBpm = respSBpm;
	}

	public Double getOxygenSPer() {
		return oxygenSPer;
	}

	public void setOxygenSPer(Double oxygenSPer) {
		this.oxygenSPer = oxygenSPer;
	}

	public Integer getvParameterId() {
		return vParameterId;
	}

	public void setvParameterId(Integer vParameterId) {
		this.vParameterId = vParameterId;
	}

	public Integer getHeightSId() {
		return heightSId;
	}

	public void setHeightSId(Integer heightSId) {
		this.heightSId = heightSId;
	}

	public Integer getvSlNo() {
		return vSlNo;
	}

	public void setvSlNo(Integer vSlNo) {
		this.vSlNo = vSlNo;
	}

	public BigInteger getFamId() {
		return famId;
	}

	public void setFamId(BigInteger famId) {
		this.famId = famId;
	}

	public Integer getSlNo() {
		return slNo;
	}

	public void setSlNo(Integer slNo) {
		this.slNo = slNo;
	}

	
	public String getbNameId() {
		return bNameId;
	}

	public void setbNameId(String bNameId) {
		this.bNameId = bNameId;
	}

	public String getHomeAdd() {
		return homeAdd;
	}

	public void setHomeAdd(String homeAdd) {
		this.homeAdd = homeAdd;
	}

	public String getPhNo() {
		return phNo;
	}

	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}



	public String getCemail() {
		return cemail;
	}

	public void setCemail(String cemail) {
		this.cemail = cemail;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getFamMeberName() {
		return famMeberName;
	}

	public void setFamMeberName(String famMeberName) {
		this.famMeberName = famMeberName;
	}

	public String getFamRelation() {
		return famRelation;
	}

	public void setFamRelation(String famRelation) {
		this.famRelation = famRelation;
	}

	
	public Integer getFamAge() {
		return famAge;
	}

	public void setFamAge(Integer famAge) {
		this.famAge = famAge;
	}


	public BigInteger getUhidCardNo() {
		return uhidCardNo;
	}

	public void setUhidCardNo(BigInteger uhidCardNo) {
		this.uhidCardNo = uhidCardNo;
	}

	public String getPancardNo() {
		return pancardNo;
	}

	public void setPancardNo(String pancardNo) {
		this.pancardNo = pancardNo;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getVotarNo() {
		return votarNo;
	}

	public void setVotarNo(String votarNo) {
		this.votarNo = votarNo;
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

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getBreson() {
		return breson;
	}

	public void setBreson(String breson) {
		this.breson = breson;
	}

	public String getImplantDate() {
		return implantDate;
	}

	public void setImplantDate(String implantDate) {
		this.implantDate = implantDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDrUpdatedby() {
		return drUpdatedby;
	}

	public void setDrUpdatedby(String drUpdatedby) {
		this.drUpdatedby = drUpdatedby;
	}

	

	
	public String getAllerSeverityId() {
		return allerSeverityId;
	}

	public void setAllerSeverityId(String allerSeverityId) {
		this.allerSeverityId = allerSeverityId;
	}

	

	

	public String getAllerName() {
		return allerName;
	}

	public void setAllerName(String allerName) {
		this.allerName = allerName;
	}

	public Integer getAllerNameId() {
		return allerNameId;
	}

	public void setAllerNameId(Integer allerNameId) {
		this.allerNameId = allerNameId;
	}

	public String getAlergenType() {
		return alergenType;
	}

	public void setAlergenType(String alergenType) {
		this.alergenType = alergenType;
	}

	public Integer getAlergenTypeId() {
		return alergenTypeId;
	}

	public void setAlergenTypeId(Integer alergenTypeId) {
		this.alergenTypeId = alergenTypeId;
	}

	public String getAllerSeverity() {
		return allerSeverity;
	}

	public void setAllerSeverity(String allerSeverity) {
		this.allerSeverity = allerSeverity;
	}

	public String getAllerRection() {
		return allerRection;
	}

	public void setAllerRection(String allerRection) {
		this.allerRection = allerRection;
	}

	public Double getHeightId() {
		return heightId;
	}

	public void setHeightId(Double heightId) {
		this.heightId = heightId;
	}

	public Double getWeightid() {
		return weightid;
	}

	public void setWeightid(Double weightid) {
		this.weightid = weightid;
	}

	public Double getBmi() {
		return bmi;
	}

	public void setBmi(Double bmi) {
		this.bmi = bmi;
	}

	public Double getTempCs() {
		return tempCs;
	}

	public void setTempCs(Double tempCs) {
		this.tempCs = tempCs;
	}

	public Double getTempFah() {
		return tempFah;
	}

	public void setTempFah(Double tempFah) {
		this.tempFah = tempFah;
	}

	public Double getBldpsr() {
		return bldpsr;
	}

	public void setBldpsr(Double bldpsr) {
		this.bldpsr = bldpsr;
	}

	public Double getSysDis() {
		return sysDis;
	}

	public void setSysDis(Double sysDis) {
		this.sysDis = sysDis;
	}

	public Double getPulseMin() {
		return pulseMin;
	}

	public void setPulseMin(Double pulseMin) {
		this.pulseMin = pulseMin;
	}

	public Double getRespBpm() {
		return respBpm;
	}

	public void setRespBpm(Double respBpm) {
		this.respBpm = respBpm;
	}

	public Double getOxygenPer() {
		return oxygenPer;
	}

	public void setOxygenPer(Double oxygenPer) {
		this.oxygenPer = oxygenPer;
	}

	public String getFirstnameid() {
		return firstnameid;
	}

	public void setFirstnameid(String firstnameid) {
		this.firstnameid = firstnameid;
	}

	public String getLastnameid() {
		return lastnameid;
	}

	public void setLastnameid(String lastnameid) {
		this.lastnameid = lastnameid;
	}

	

	public String getFileDetails() {
		return fileDetails;
	}

	public void setFileDetails(String fileDetails) {
		this.fileDetails = fileDetails;
	}

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public String getBloodgrp() {
		return bloodgrp;
	}

	public void setBloodgrp(String bloodgrp) {
		this.bloodgrp = bloodgrp;
	}

	public String getDobid() {
		return dobid;
	}

	public void setDobid(String dobid) {
		this.dobid = dobid;
	}



	public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}


	public BigInteger getUhidNo() {
		return uhidNo;
	}

	public void setUhidNo(BigInteger uhidNo) {
		this.uhidNo = uhidNo;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritialStatus() {
		return maritialStatus;
	}

	public void setMaritialStatus(String maritialStatus) {
		this.maritialStatus = maritialStatus;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountryid() {
		return countryid;
	}

	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}

	public String getStateid() {
		return stateid;
	}

	public void setStateid(String stateid) {
		this.stateid = stateid;
	}

	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String geteRealtion() {
		return eRealtion;
	}

	public void seteRealtion(String eRealtion) {
		this.eRealtion = eRealtion;
	}

	public String geteMobNo() {
		return eMobNo;
	}

	public void seteMobNo(String eMobNo) {
		this.eMobNo = eMobNo;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocSpeciality() {
		return docSpeciality;
	}

	public void setDocSpeciality(String docSpeciality) {
		this.docSpeciality = docSpeciality;
	}

	public String getDocMobNo() {
		return docMobNo;
	}

	public void setDocMobNo(String docMobNo) {
		this.docMobNo = docMobNo;
	}

	public String getFamDocId() {
		return famDocId;
	}

	public void setFamDocId(String famDocId) {
		this.famDocId = famDocId;
	}

	public String getFamDocs() {
		return famDocs;
	}

	public void setFamDocs(String famDocs) {
		this.famDocs = famDocs;
	}

	public Integer getCountry() {
		return country;
	}

	public void setCountry(Integer country) {
		this.country = country;
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

