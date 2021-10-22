package nirmalya.aatithya.restmodule.patient.model;

import java.io.IOException;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestPatientDetailsNewModel {

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
	private Integer slNo;
	private String bName;
	private Integer bNameId;
	private String breson;
	private String implantDate;
	private String createdBy;

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
	private String famDocId;
	private String famDocs;
	private String bloodgroupId;
	private String genderId;
	private String maritalstatusId;
	private String distId;
	private Integer dist;
	private Integer state;
	private Integer city;
	
	private Integer speciality;
	private String patVitalId;
	private Integer allergyId;
	private Integer allergyTypesId;
	private Integer severityTypesId;
	private Integer country;
	

	public RestPatientDetailsNewModel(Object userId, Object eId, Object eName, Object eRealtion, Object eMobNo, Object eRelationId) {
		super();
		this.userId = (BigInteger) userId;
		this.eId = (Integer) eId;
		this.eName = (String) eName;
		this.eRealtion = (String) eRealtion;
		this.eMobNo = (String) eMobNo;
		this.eRelationId = (Integer) eRelationId;
	}
	public RestPatientDetailsNewModel(Object famMeberName, Object famAge, Object uhidCardNo) {
		super();
		this.famMeberName = (String) famMeberName;
		this.famAge = (Integer) famAge;
		this.uhidCardNo = (BigInteger) uhidCardNo;
	}
	public RestPatientDetailsNewModel(Object famMeberName,  Object uhidCardNo, Object famAge, Object famRelation, Integer eRelationId, Object slNo) {//here "eRelationId" is type of Integer because no of paramter is same as of another constructor
		super();
		this.famMeberName = (String) famMeberName;
		this.uhidCardNo = (BigInteger) uhidCardNo;
		this.famAge = (Integer) famAge;
		this.famRelation = (String) famRelation;
		this.eRelationId = eRelationId;
		this.slNo =(Integer)  slNo;
	}
	public RestPatientDetailsNewModel(Object userId, Object docName, Object docSpeciality, Object docMobNo, Object docSpecialityId,Object famDocId
			,Object famDocs) {
		super();
		this.userId = (BigInteger) userId;
		this.docName = (String) docName;
		this.docSpeciality = (String) docSpeciality;
		this.docMobNo = (String) docMobNo;
		this.docSpecialityId = (Integer) docSpecialityId;
		this.famDocId = (String) famDocId;
		this.famDocs = (String) famDocs;
	}
	
	
	public RestPatientDetailsNewModel(Object countryid,Object country,Object stateid,Object state,Object distId,
			Object dist,Object cityid,Object city,Object homeAdd,Object zipCode,Object cemail,Object phNo
	) {
		super();

		
		this.countryid = (String) countryid;
		this.country = (Integer) country;
		this.stateid = (String) stateid;
		this.state = (Integer) state;
		this.distId = (String) distId;
		this.dist = (Integer) dist;
		this.cityid = (String) cityid;
		this.city = (Integer) city;
		this.homeAdd = (String) homeAdd;
		this.zipCode = (String) zipCode;
		this.cemail = (String) cemail;
		this.phNo = (String) phNo;
		
	}

	public RestPatientDetailsNewModel(Object fileDetails, Object userId, Object firstnameid, Object lastnameid,
			Object uhidNo, Object bloodgrp, Object dobid, Object age, Object gender, Object maritialStatus,
			Object occupation, Object address, Object countryid, Object stateid, Object cityid, Object zipCode,
			Object email, Object mobNo, Object eiId, Object eName, Object eRealtion, Object eMobNo, Object docName,
			Object docSpeciality, Object docMobNo, Object heightId, Object weightid, Object bmi, Object tempCs,
			Object tempFah, Object bldpsr, Object sysDis, Object pulseMin, Object respBpm, Object oxygenPer,
			Object allerName, Object allerNameId, Object alergenType, Object alergenTypeId, Object allerSeverity,
			Object allerSeverityId, Object allerRection, Object drUpdatedby, Object slNo, Object bName, Object bNameId,
			Object breson, Object implantDate, Object pancardNo, Object passportNo, Object aadharNo, Object votarNo,
			Object licenseNo, Object licenseAuthority, Object homeAdd, Object phNo, Object cemail, Object qualification,
			Object specialization, Object famId, Object famMeberName, Object famRelation, Object famAge,
			Object uhidCardNo, Object vSlNo, Object vParameterId, Object heightSId, Object weightSid, Object bSmi,
			Object tempSCs, Object tempSFah, Object bldSpsr, Object sysSDis, Object pulseSMin, Object respSBpm,
			Object oxygenSPer, Object vCount, Object bloodgroupId,
			Object genderId, Object maritalstatusId,Object distId,Object dist,Object state,Object city
			,Object speciality,Object patVitalId,Object allergyId,Object allergyTypesId,Object severityTypesId) {
		super();

		this.fileDetails = (String) fileDetails;
		this.userId = (BigInteger) userId;
		this.firstnameid = (String) firstnameid;
		this.lastnameid = (String) lastnameid;
		this.uhidNo = (BigInteger) uhidNo;
		this.bloodgrp = (String) bloodgrp;
		this.dobid = (String) dobid;
		this.age = (Integer) age;
		this.gender = (String) gender;
		this.maritialStatus = (String) maritialStatus;
		this.occupation = (String) occupation;

		this.address = (String) address;
		this.countryid = (String) countryid;
		this.stateid = (String) stateid;
		this.cityid = (String) cityid;
		this.zipCode = (String) zipCode;
		this.email = (String) email;
		this.mobNo = (String) mobNo;
		this.eId = (Integer) eId;
		this.eName = (String) eName;
		this.eRealtion = (String) eRealtion;
		this.eMobNo = (String) eMobNo;
		this.docName = (String) docName;
		this.docSpeciality = (String) docSpeciality;
		this.docMobNo = (String) docMobNo;

		this.heightId = (Double) heightId;
		this.weightid = (Double) weightid;
		this.bmi = (Double) bmi;
		this.tempCs = (Double) tempCs;
		this.tempFah = (Double) tempFah;
		this.bldpsr = (Double) bldpsr;
		this.sysDis = (Double) sysDis;
		this.pulseMin = (Double) pulseMin;
		this.respBpm = (Double) respBpm;
		this.oxygenPer = (Double) oxygenPer;

		this.allerName = (String) allerName;
		this.allerNameId = (Integer) allerNameId;
		this.alergenType = (String) alergenType;
		this.alergenTypeId = (Integer) alergenTypeId;
		this.allerSeverity = (String) allerSeverity;
		this.allerSeverityId = (String) allerSeverityId;
		this.allerRection = (String) allerRection;
		this.drUpdatedby = (String) drUpdatedby;
		this.slNo = (Integer) slNo;
		this.bName = (String) bName;
		this.bNameId = (Integer) bNameId;
		this.breson = (String) breson;
		this.implantDate = (String) implantDate;

		this.pancardNo = (String) pancardNo;
		this.passportNo = (String) passportNo;
		this.aadharNo = (String) aadharNo;
		this.votarNo = (String) votarNo;
		this.licenseNo = (String) licenseNo;
		this.licenseAuthority = (String) licenseAuthority;

		this.homeAdd = (String) homeAdd;
		this.phNo = (String) phNo;
		this.cemail = (String) cemail;
		this.qualification = (String) qualification;
		this.specialization = (String) specialization;
		this.famId = (BigInteger) famId;
		this.famMeberName = (String) famMeberName;
		this.famRelation = (String) famRelation;
		this.famAge = (Integer) famAge;
		this.uhidCardNo = (BigInteger) uhidCardNo;

		this.vSlNo = (Integer) vSlNo;
		this.vParameterId = (Integer) vParameterId;
		this.heightSId = (Integer) heightSId;
		this.weightSid = (Double) weightSid;
		this.bSmi = (Double) bSmi;
		this.tempSCs = (Double) tempSCs;
		this.tempSFah = (Double) tempSFah;
		this.bldSpsr = (Double) bldSpsr;
		this.sysSDis = (Double) sysSDis;
		this.pulseSMin = (Double) pulseSMin;
		this.respSBpm = (Double) respSBpm;
		this.oxygenSPer = (Double) oxygenSPer;
		
		this.vCount = (String) vCount;
		
		this.bloodgroupId = (String) bloodgroupId;
		this.genderId = (String) genderId;
		this.maritalstatusId = (String) maritalstatusId;
		this.genderId = (String) genderId;
		this.maritalstatusId = (String) maritalstatusId;
		this.distId = (String) distId;
		this.dist = (Integer) dist;
		this.state = (Integer) state;
		this.city = (Integer) city;
		this.speciality=(Integer)speciality;
		this.patVitalId=(String)patVitalId;
		this.allergyId = (Integer) allergyId;
		this.allergyTypesId = (Integer) allergyTypesId;
		this.severityTypesId = (Integer) severityTypesId;
	}

	public RestPatientDetailsNewModel() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public Integer getSpeciality() {
		return speciality;
	}
	public void setSpeciality(Integer speciality) {
		this.speciality = speciality;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	public Integer getHeightSId() {
		return heightSId;
	}

	public void setHeightSId(Integer heightSId) {
		this.heightSId = heightSId;
	}

	public Integer getvParameterId() {
		return vParameterId;
	}

	public void setvParameterId(Integer vParameterId) {
		this.vParameterId = vParameterId;
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

	

	public Integer getbNameId() {
		return bNameId;
	}
	public void setbNameId(Integer bNameId) {
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

	public Integer getAllerNameId() {
		return allerNameId;
	}

	public Integer getAlergenTypeId() {
		return alergenTypeId;
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

	public String getAlergenType() {
		return alergenType;
	}

	public void setAlergenType(String alergenType) {
		this.alergenType = alergenType;
	}

	public void setAllerNameId(Integer allerNameId) {
		this.allerNameId = allerNameId;
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

	public String getDrUpdatedby() {
		return drUpdatedby;
	}

	public void setDrUpdatedby(String drUpdatedby) {
		this.drUpdatedby = drUpdatedby;
	}

	public Double getOxygenPer() {
		return oxygenPer;
	}

	public void setOxygenPer(Double oxygenPer) {
		this.oxygenPer = oxygenPer;
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

	public String getBloodgrp() {
		return bloodgrp;
	}

	public void setBloodgrp(String bloodgrp) {
		this.bloodgrp = bloodgrp;
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

	public String getDobid() {
		return dobid;
	}

	public void setDobid(String dobid) {
		this.dobid = dobid;
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

	public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
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

	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
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
