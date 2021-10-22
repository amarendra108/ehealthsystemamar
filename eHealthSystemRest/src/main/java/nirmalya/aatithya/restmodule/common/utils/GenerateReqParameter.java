package nirmalya.aatithya.restmodule.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import nirmalya.aatithya.restmodule.recruitment.model.AddRecruitentModel;
import nirmalya.aatithya.restmodule.recruitment.model.RestRequisitonVendorAllocationModel;

public class GenerateReqParameter {
	public static String getAddreqParam(AddRecruitentModel form) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
				
		String s = "";
		String qItem = "";
		String addReq = "";
		
		if(form.getRequisitionId() == null || form.getRequisitionId() == "") {
			for (int i =0; i < form.getBenefits().size() ; i++) {
				qItem = qItem + "(@p_requisitionId,\"" + form.getBenefits().get(i) + "\",\"" + form.getCreatedBy()+ "\",\"" + form.getCreatedBy() + "\"),";
			}
			qItem = qItem.substring(0, qItem.length() - 1);

			addReq = addReq + "(@p_requisitionId,\"" + form.getJobTitle() + "\",\"" + form.getJobType() + "\",\"" + form.getJobLocation() + "\",\"" + 
					form.getMinEducation() + "\"," + form.getMinSalary() + "," + form.getMaxSalary() + ",\"" + form.getDepartment() + "\",\"" +
					form.getHiringManager() + "\",\"" + form.getNoPosition() + "\",\"" + form.getWorkHour() + "\",\"" + form.getBand() + "\",\"" + form.getJoinDate() + "\",\"" + 
					form.getPositionSummary() + "\",\"" + form.getPositionResponsibility() + "\",\"" + form.getRequiredSkillExperience() +
					"\",\"" + form.getApprover() + "\",\"" + form.getAbout() + "\",\"" + form.getCreatedBy()+ "\",\"" + dateFormat.format(cal.getTime()) + "\",\"" + form.getCreatedBy() +"\",1),";
			addReq = addReq.substring(0, addReq.length() - 1);
			
			s = s + "@p_activityBy='" + form.getCreatedBy() + "',";
			s = s + "@p_activityStatus='1',";
			
		} else {
			for (int i =0; i < form.getBenefits().size() ; i++) {
				qItem = qItem + "(\"" + form.getRequisitionId() + "\",\"" + form.getBenefits().get(i) + "\",\"" + form.getCreatedBy()+ "\",\"" + form.getCreatedBy() + "\"),";
			}
			qItem = qItem.substring(0, qItem.length() - 1);

			if(form.getRequisitionId() != null && form.getRequisitionId() != "") {
				s = s + "@p_requisitionId='" + form.getRequisitionId() + "',";
			}
			if(form.getJobTitle() != null && form.getJobTitle() != "") {
				s = s + "@p_jobTitle='" + form.getJobTitle() + "',";
			}
			if(form.getJobType() != null && form.getJobType() != "") {
				s = s + "@p_jobType='" + form.getJobType() + "',";
			}
			if(form.getJobLocation() != null && form.getJobLocation() != "") {
				s = s + "@p_jobLocation='" + form.getJobLocation() + "',";
			}
			if(form.getMinEducation() != null && form.getMinEducation() != "") {
				s = s + "@p_minEducation='" + form.getMinEducation() + "',";
			}
			if(form.getMinSalary() != null) {
				s = s + "@p_minSalary='" + form.getMinSalary() + "',";
			}
			if(form.getMaxSalary() != null) {
				s = s + "@p_maxSalary='" + form.getMaxSalary() + "',";
			}
			if(form.getDepartment() != null && form.getDepartment() != "") {
				s = s + "@p_department='" + form.getDepartment() + "',";
			}
			if(form.getHiringManager() != null && form.getHiringManager() != "") {
				s = s + "@p_hireManager='" + form.getHiringManager() + "',";
			}
			if(form.getNoPosition() != null) {
				s = s + "@p_noPosition='" + form.getNoPosition() + "',";
			}
			if(form.getWorkHour() != null && form.getWorkHour() != "") {
				s = s + "@p_workHour='" + form.getWorkHour() + "',";
			}
			if(form.getBand() != null && form.getBand() != "") {
				s = s + "@p_band='" + form.getBand() + "',";
			}
			if(form.getJoinDate() != null && form.getJoinDate() != "") {
				s = s + "@p_joinDate='" + form.getJoinDate() + "',";
			}
			if(form.getPositionSummary() != null && form.getPositionSummary() != "") {
				s = s + "@p_positionSummary='" + form.getPositionSummary() + "',";
			}
			if(form.getPositionResponsibility() != null && form.getPositionResponsibility() != "") {
				s = s + "@p_positionResponsibility='" + form.getPositionResponsibility() + "',";
			}
			if(form.getRequiredSkillExperience() != null && form.getRequiredSkillExperience() != "") {
				s = s + "@p_requiredSkillExperience='" + form.getRequiredSkillExperience() + "',";
			}
			if(form.getApprover() != null && form.getApprover() != "") {
				s = s + "@p_approver='" + form.getApprover() + "',";
			}
			if(form.getAbout() != null && form.getAbout() != "") {
				s = s + "@p_about='" + form.getAbout() + "',";
			}
			if(form.getCreatedBy() != null && form.getCreatedBy() != "") {
				s = s + "@p_updatedBy='" + form.getCreatedBy() + "',";
			}
			s = s + "@p_status='1',";
			s = s + "@p_updatedOn='" + dateFormat.format(cal.getTime()) + "',";
			
			/*addReq = addReq + "(\"" + form.getRequisitionId() + "\",\"" + form.getJobTitle() + "\",\"" + form.getJobType() + "\",\"" + form.getJobLocation() + "\",\"" + 
					form.getMinEducation() + "\",\"" + form.getMinSalary() + "\",\"" + form.getMaxSalary() + "\",\"" + form.getDepartment() + "\",\"" +
					form.getHiringManager() + "\",\"" + form.getNoPosition() + "\",\"" + form.getWorkHour() + "\",\"" + form.getBand() + "\",\"" + form.getJoinDate() + "\",\"" + 
					form.getPositionSummary() + "\",\"" + form.getPositionResponsibility() + "\",\"" + form.getRequiredSkillExperience() +
					"\",\"" + form.getApprover() + "\",\"" + form.getAbout() + "\",\"" + form.getCreatedBy()+ "\",\"" + form.getCreatedBy() +"\",1),";
			addReq = addReq.substring(0, addReq.length() - 1);*/
			s = s + "@p_requisitionId='" + form.getRequisitionId() + "',";
			s = s + "@p_activityBy='" + form.getCreatedBy() + "',";
			s = s + "@p_activityStatus='2',";
		}
		
		s = s + "@p_benefitsData='" + qItem + "',";
		s = s + "@p_addRequisition='" + addReq + "',";
		
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		return s;

	}
	
public static String getReqVenAllocationParam(RestRequisitonVendorAllocationModel vendorReq) {
		
		String s = "";
		String qItem = "";
		String id = "";
		
		for(int i = 0; i < vendorReq.getRequisitionId().size(); i++) {
			for(int j = 0; j < vendorReq.getVendorId().size(); j++) {
				qItem = qItem + "(\"" + vendorReq.getVendorId().get(j) + "\",\"" + vendorReq.getRequisitionId().get(i) + "\",\"" + vendorReq.getCreatedBy() + "\"),";
			}
		}
		qItem = qItem.substring(0, qItem.length() - 1);
		
		for(int j = 0; j < vendorReq.getRequisitionId().size(); j++) {
			
			id = id + "\"" + vendorReq.getRequisitionId().get(j)  + "\",";
		}
		id = id.substring(0, id.length() - 1);
		
		
		s = s + "@p_vendorReq='" + qItem + "',";
		s = s + "@p_requisitionId='(" + id + ")',";
		
		
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}
		
		return s;
		
	}
	
}


