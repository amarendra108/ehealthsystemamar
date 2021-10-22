package nirmalya.aatithya.restmodule.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import nirmalya.aatithya.restmodule.procurment.model.InventoryVendorDocumentModel;
import nirmalya.aatithya.restmodule.recruitment.model.CandidateAddressModel;
import nirmalya.aatithya.restmodule.recruitment.model.CandidateApplyRequisitionModel;
import nirmalya.aatithya.restmodule.recruitment.model.CandidateAwardsModel;
import nirmalya.aatithya.restmodule.recruitment.model.CandidateDetailsModel;
import nirmalya.aatithya.restmodule.recruitment.model.CandidateDocumentModel;
import nirmalya.aatithya.restmodule.recruitment.model.CandidateEducationModel;
import nirmalya.aatithya.restmodule.recruitment.model.CandidateReferenceModel;
import nirmalya.aatithya.restmodule.recruitment.model.CandidateSkillsModel;
import nirmalya.aatithya.restmodule.recruitment.model.CandidateSourceModel;
import nirmalya.aatithya.restmodule.recruitment.model.CandidateWorkExperienceModel;

public class GenerateCandidateParameter {
	
	public static String addPersonalDetails(CandidateDetailsModel form) {
		String s = "";
		//String qItem = "";
		String addReq = "";
		
		if(form.getCandidateId() == null || form.getCandidateId() == "") {
			/*for (int i =0; i < form.getBenefits().size() ; i++) {
				qItem = qItem + "(@p_requisitionId,\"" + form.getBenefits().get(i) + "\",\"" + form.getCreatedBy()+ "\",\"" + form.getCreatedBy() + "\"),";
			}
			qItem = qItem.substring(0, qItem.length() - 1);*/

			if(form.getDob() == null || form.getDob() == "") {
				addReq = addReq + "(@p_candidateId,\"" + form.getFirstName() + "\",\"" + form.getLastName() + "\",\"" + form.getGender() + "\"," + 
						null + ",\"" + form.getBloodGroup() + "\",\"" + form.getMaritalStatus() + "\",\"" + form.getNationality() + "\",\"" +
						form.getFatherName() + "\",\"" + form.getMotherName() + "\",\"" + form.getMobileNo() + "\",\"" + form.getPersonalEmail() + "\",\"" +
						form.getWorkEmail() + "\",\"" + form.getFileUpload()+ "\",\"" + form.getCreatedBy()+ "\",\"" + form.getCreatedBy() +"\"),";
				addReq = addReq.substring(0, addReq.length() - 1);
			} else {
				addReq = addReq + "(@p_candidateId,\"" + form.getFirstName() + "\",\"" + form.getLastName() + "\",\"" + form.getGender() + "\",\"" + 
						form.getDob() + "\",\"" + form.getBloodGroup() + "\",\"" + form.getMaritalStatus() + "\",\"" + form.getNationality() + "\",\"" +
						form.getFatherName() + "\",\"" + form.getMotherName() + "\",\"" + form.getMobileNo() + "\",\"" + form.getPersonalEmail() + "\",\"" +
						form.getWorkEmail() + "\",\"" + form.getFileUpload()+ "\",\"" + form.getCreatedBy()+ "\",\"" + form.getCreatedBy() +"\"),";
				addReq = addReq.substring(0, addReq.length() - 1);
			}
			
			s = s + "@p_activityBy='" + form.getCreatedBy() + "',";
			
		} else {
			/*for (int i =0; i < form.getBenefits().size() ; i++) {
				qItem = qItem + "(\"" + form.getRequisitionId() + "\",\"" + form.getBenefits().get(i) + "\",\"" + form.getCreatedBy()+ "\",\"" + form.getCreatedBy() + "\"),";
			}
			qItem = qItem.substring(0, qItem.length() - 1);*/

			if(form.getDob() == null || form.getDob() == "") {
				addReq = addReq + "(\"" + form.getCandidateId() + "\",\"" + form.getFirstName() + "\",\"" + form.getLastName() + "\",\"" + form.getGender() + "\"," + 
						null + ",\"" + form.getBloodGroup() + "\",\"" + form.getMaritalStatus() + "\",\"" + form.getNationality() + "\",\"" +
						form.getFatherName() + "\",\"" + form.getMotherName() + "\",\"" + form.getMobileNo() + "\",\"" + form.getPersonalEmail() + "\",\"" +
						form.getWorkEmail() + "\",\"" + form.getFileUpload()+ "\",\"" + form.getCreatedBy() +"\"),";
				addReq = addReq.substring(0, addReq.length() - 1);
			} else {
				addReq = addReq + "(\"" + form.getCandidateId() + "\",\"" + form.getFirstName() + "\",\"" + form.getLastName() + "\",\"" + form.getGender() + "\",\"" + 
						form.getDob() + "\",\"" + form.getBloodGroup() + "\",\"" + form.getMaritalStatus() + "\",\"" + form.getNationality() + "\",\"" +
						form.getFatherName() + "\",\"" + form.getMotherName() + "\",\"" + form.getMobileNo() + "\",\"" + form.getPersonalEmail() + "\",\"" +
						form.getWorkEmail() + "\",\"" + form.getFileUpload()+ "\",\"" + form.getCreatedBy() +"\"),";
				addReq = addReq.substring(0, addReq.length() - 1);
			}
			
			s = s + "@p_candidateId='" + form.getCandidateId() + "',";
			s = s + "@p_activityBy='" + form.getCreatedBy() + "',";
		}
		
		//s = s + "@p_benefitsData='" + qItem + "',";
		s = s + "@p_addCandidate='" + addReq + "',";
		
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		return s;

	}
	
	public static String addAddress(CandidateAddressModel form) {
		
		String s = "";
		String addReq = "";

		if(form.getAddressId() == null || form.getAddressId() == "") {
			
			addReq = addReq + "(@p_addressId,\"" + form.getCandidateId() + "\",\"" + form.getType() + "\",\"" + form.getAddress() + "\",\"" + form.getCountry() + "\",\"" + 
					form.getState() + "\",\"" + form.getCity() + "\",\"" + form.getPinCode() + "\",\"" + form.getCreatedBy() + "\",\"" + form.getCreatedBy() +"\"),";
			addReq = addReq.substring(0, addReq.length() - 1);
		} else {
			
			addReq = addReq + "(\""+ form.getAddressId() + "\",\"" + form.getCandidateId() + "\",\"" + form.getType() + "\",\"" + form.getAddress() + "\",\"" + form.getCountry() + "\",\"" + 
					form.getState() + "\",\"" + form.getCity() + "\",\"" + form.getPinCode() + "\",\"" + form.getCreatedBy() + "\",\"" + form.getCreatedBy() +"\"),";
			addReq = addReq.substring(0, addReq.length() - 1);
		}
		
		s = s + "@p_addAddress='" + addReq + "',";
		s = s + "@p_addressId='" + form.getAddressId() + "',";
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		return s;
		
	}
	
public static String addEducation(CandidateEducationModel form) {
		
		String s = "";
		String addReq = "";

		if(form.getEducationId() == null || form.getEducationId() == "") {
			addReq = addReq + "(@p_educationId,\"" + form.getCandidaateId() + "\",\"" + form.getQualification() + "\",\""  +
					form.getInstitution() + "\",\"" + form.getPassingYear() + "\",\"" + form.getCreatedBy() + "\",\"" + form.getCreatedBy() +"\"),";
			addReq = addReq.substring(0, addReq.length() - 1);
			
		} else {
			addReq = addReq + "(\""+ form.getEducationId() + "\",\"" + form.getCandidaateId() + "\",\"" + form.getQualification() + "\",\""  +
					form.getInstitution() + "\",\"" + form.getPassingYear() + "\",\"" + form.getCreatedBy() + "\",\"" + form.getCreatedBy() +"\"),";
					addReq = addReq.substring(0, addReq.length() - 1);
		}
		
		s = s + "@p_addEducation='" + addReq + "',";
		s = s + "@p_educationId='" + form.getEducationId() + "',";
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		return s;
		
	}

public static String addSkills(CandidateSkillsModel form) {
	
	String s = "";
	String addReq = "";

	if(form.getSkillId() == null || form.getSkillId() == "") {
		addReq = addReq + "(@p_skillsId,\"" + form.getCandidateId() + "\",\"" + form.getSkills() + "\",\""  +
				form.getSkillDesc() + "\",\"" + form.getSkillLevel() + "\",\"" + form.getExperience() + "\",\"" + form.getCreatedBy() + "\",\"" + form.getCreatedBy() +"\"),";
		addReq = addReq.substring(0, addReq.length() - 1);
		
	} else {
		addReq = addReq + "(\""+ form.getSkillId() + "\",\"" + form.getCandidateId() + "\",\"" + form.getSkills() + "\",\""  +
				form.getSkillDesc() + "\",\"" + form.getSkillLevel() + "\",\"" + form.getExperience() + "\",\"" + form.getCreatedBy() + "\",\"" + form.getCreatedBy() +"\"),";
		addReq = addReq.substring(0, addReq.length() - 1);
	}
	
	s = s + "@p_addSkills='" + addReq + "',";
	s = s + "@p_skillsId='" + form.getSkillId() + "',";
	
	if (s != "") {
		s = s.substring(0, s.length() - 1);

		s = "SET " + s + ";";
	}

	return s;
	
}

public static String addWorkExperience(CandidateWorkExperienceModel form) {
	
	String s = "";
	String addReq = "";

	if(form.getWorkExperineceId() == null || form.getWorkExperineceId() == "") {
		
		addReq = addReq + "(@p_workExperienceId,\"" + form.getCandidateId() + "\",\"" + form.getDesignation() + "\",\""  +
				form.getOrganization() + "\",\"" + form.getWorkFrom() + "\",\"" + form.getWorkTill() + "\",\"" + form.getNoticePeriod() + "\",\"" + form.getDescription() + "\",\"" + form.getCreatedBy() + "\",\"" + form.getCreatedBy() +"\"),";
		addReq = addReq.substring(0, addReq.length() - 1);
	} else {
		
		addReq = addReq + "(\""+ form.getWorkExperineceId() + "\",\"" + form.getCandidateId() + "\",\"" + form.getDesignation() + "\",\""  +
				form.getOrganization() + "\",\"" + form.getWorkFrom() + "\",\"" + form.getWorkTill() + "\",\"" + form.getNoticePeriod() + "\",\"" + form.getDescription() + "\",\"" + form.getCreatedBy() + "\",\"" + form.getCreatedBy() +"\"),";
		addReq = addReq.substring(0, addReq.length() - 1);
	}
	
	s = s + "@p_addWorkExperience='" + addReq + "',";
	s = s + "@p_workExperienceId='" + form.getWorkExperineceId() + "',";
	
	if (s != "") {
		s = s.substring(0, s.length() - 1);

		s = "SET " + s + ";";
	}

	return s;
	
}

public static String addAward(CandidateAwardsModel form) {
	
	String s = "";
	String addReq = "";

	if(form.getAwardId() == null || form.getAwardId() == "") {
		addReq = addReq + "(@p_awardId,\"" + form.getCandidateId() + "\",\""  + form.getAwardName() + "\",\"" + 
				form.getAwardYear() + "\",\"" + form.getAwardDescription() + "\",\"" + form.getCreatedBy() + "\",\"" + form.getCreatedBy() +"\"),";
		addReq = addReq.substring(0, addReq.length() - 1);
		
	} else {
		
		addReq = addReq + "(\""+ form.getAwardId() + "\",\"" + form.getCandidateId() + "\",\""  + form.getAwardName() + "\",\"" + 
				form.getAwardYear() + "\",\"" + form.getAwardDescription() + "\",\"" + form.getCreatedBy() + "\",\"" + form.getCreatedBy() +"\"),";
		addReq = addReq.substring(0, addReq.length() - 1);
	}
	
	s = s + "@p_addAward='" + addReq + "',";
	s = s + "@p_awardId='" + form.getAwardId() + "',";
	
	if (s != "") {
		s = s.substring(0, s.length() - 1);

		s = "SET " + s + ";";
	}

	return s;
	
}

public static String addReference(CandidateReferenceModel form) {
	
	String s = "";
	String addReq = "";

	if(form.getReferenceId() == null || form.getReferenceId() == "") {
		addReq = addReq + "(@p_referenceId,\"" + form.getCandidateId() + "\",\""  + form.getName() + "\",\"" + 
				form.getMobileNo() + "\",\"" + form.getEmailId() + "\",\"" + form.getDescription() + "\",\"" + form.getCreatedBy() + "\",\"" + form.getCreatedBy() +"\"),";
		addReq = addReq.substring(0, addReq.length() - 1);
		
	} else {
		
		addReq = addReq + "(\""+ form.getReferenceId() + "\",\"" + form.getCandidateId() + "\",\""  + form.getName() + "\",\"" + 
				form.getMobileNo() + "\",\"" + form.getEmailId() + "\",\"" + form.getDescription() + "\",\"" + form.getCreatedBy() + "\",\"" + form.getCreatedBy() +"\"),";
		addReq = addReq.substring(0, addReq.length() - 1);
	}
	
	s = s + "@p_addReference='" + addReq + "',";
	s = s + "@p_referenceId='" + form.getReferenceId() + "',";
	
	if (s != "") {
		s = s.substring(0, s.length() - 1);

		s = "SET " + s + ";";
	}

	return s;
	
}

public static String addSource(CandidateSourceModel form) {
	
	String s = "";
	String addReq = "";

	if(form.getSourceId() == null || form.getSourceId() == "") {
		addReq = addReq + "(@p_sourceId,\"" + form.getCandidateId() + "\",\""  + form.getName() + "\",\"" + 
				 form.getDescription() + "\",\"" + form.getCreatedBy() + "\",\"" + form.getCreatedBy() +"\"),";
		addReq = addReq.substring(0, addReq.length() - 1);
		
	} else {
	
		addReq = addReq + "(\""+ form.getSourceId() + "\",\"" + form.getCandidateId() + "\",\""  + form.getName() + "\",\"" + 
				 form.getDescription() + "\",\"" + form.getCreatedBy() + "\",\"" + form.getCreatedBy() +"\"),";
		addReq = addReq.substring(0, addReq.length() - 1);
	}
	
	s = s + "@p_addSource='" + addReq + "',";
	s = s + "@p_sourceId='" + form.getSourceId() + "',";
	
	if (s != "") {
		s = s.substring(0, s.length() - 1);

		s = "SET " + s + ";";
	}

	return s;
	
}

public static String addShortList(CandidateDocumentModel candidate) {

	String s = "";
	String qItem = "";
	
	s = s + "@p_candId='" + candidate.getCandidateId() + "',";
	  s = s + "@p_createdBy='" + candidate.getCreatedBy() + "',";
	  
	  for (InventoryVendorDocumentModel a : candidate.getDocumentList()) {
		  qItem = qItem + "(@p_candId,\"" + a.getDocumnentName() + "\",\"" + a.getFileName() + "\",@p_createdBy),";
		}
		if(!qItem.isEmpty()) {
			qItem = qItem.substring(0, qItem.length() - 1);
			s = s + "@p_candDocuments='" + qItem + "',";
		}

	  if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

System.out.println(s);
	
	return s;

}

public static String applyReq(CandidateApplyRequisitionModel applyReq) {
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	
	String s = "";
	String qItem = "";
	String id = "";
	
	for(int i = 0; i < applyReq.getRequisitionId().size(); i++) {
		for(int j = 0; j < applyReq.getCandidateId().size(); j++) {
			qItem = qItem + "(\"" + applyReq.getCandidateId().get(j) + "\",\"" + applyReq.getRequisitionId().get(i) + "\",\"" + dateFormat.format(cal.getTime()) + "\",\"" + applyReq.getCreatedBy() + "\",\"" + applyReq.getCreatedBy() + "\"),";
		}
	}
	qItem = qItem.substring(0, qItem.length() - 1);
	
	for(int j = 0; j < applyReq.getCandidateId().size(); j++) {
		id = id + "\"" + applyReq.getCandidateId().get(j)  + "\",";
	}
	id = id.substring(0, id.length() - 1);
	
	
	s = s + "@p_applyReq='" + qItem + "',";
	s = s + "@p_candId='(" + id + ")',";
	
	if (s != "") {
		s = s.substring(0, s.length() - 1);

		s = "SET " + s + ";";
	}

	System.out.println(s);
	
	return s;
	
}

}
