package nirmalya.aatithya.restmodule.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import nirmalya.aatithya.restmodule.recruitment.model.HireActionModel;

public class GenerateHireActionParameter {

	public static String scheduleInterview(HireActionModel action) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
		String s = "";
		String qItem = "";
		String id = "";
			
		for(int i = 0; i < action.getCandidateId().size(); i++) {
				for(int j = 0; j < action.getHiringManager().size(); j ++) {
					qItem = qItem + "(\"" + action.getRequisitionId() + "\",\"" + action.getCandidateId().get(i) + "\",\"" +  action.getFromDate() +
							"\",\"" +  action.getToDate() + "\",\"" +  action.getFromTime() + "\",\"" +  action.getToTime() +
							"\",\"" +  action.getLocation() + "\",\"" +  action.getSummary() + "\",\"" +
							action.getHiringManager().get(j) + "\",\"" + action.getDescription() +"\",\"" + dateFormat.format(cal.getTime()) + "\",\"" + action.getCreatedBy() + "\",\"" + action.getCreatedBy() + "\",\"" + action.getTitle()  + "\"),";
				}
			}
		qItem = qItem.substring(0, qItem.length() - 1);
		
		for(int i = 0; i < action.getCandidateId().size(); i++) {
			id = id + "\"" + action.getCandidateId().get(i) + "\",";
		}
		id = id.substring(0, id.length() - 1);
		
		s = s + "@p_action='" + qItem + "',";
		s = s + "@p_candId='(" + id + ")',";
		s = s + "@p_reqId='" + action.getRequisitionId() + "',";
		if (s != "") {
			s = s.substring(0, s.length() - 1);

			s = "SET " + s + ";";
		}

		System.out.println(s);
		
		return s;
		
	}
	
public static String editScheduleInterview(HireActionModel action) {
		
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	
	String s = "";
	String qItem = "";
	String id = "";
		
	for(int i = 0; i < action.getCandidateId().size(); i++) {
			for(int j = 0; j < action.getHiringManager().size(); j ++) {
				qItem = qItem + "(\"" + action.getRequisitionId() + "\",\"" + action.getCandidateId().get(i) + "\",\"" +  action.getFromDate() +
						"\",\"" +  action.getToDate() + "\",\"" +  action.getFromTime() + "\",\"" +  action.getToTime() +
						"\",\"" +  action.getLocation() + "\",\"" +  action.getSummary() + "\",\"" +
						action.getHiringManager().get(j) + "\",\"" + action.getDescription() +"\",\"" + dateFormat.format(cal.getTime()) + "\",\"" + action.getCreatedBy() + "\",\"" + action.getCreatedBy() + "\",\"" + action.getTitle()  + "\"),";
			}
		}
	qItem = qItem.substring(0, qItem.length() - 1);
	
	for(int i = 0; i < action.getCandidateId().size(); i++) {
		id = id + "" + action.getCandidateId().get(i) + ",";
	}
	id = id.substring(0, id.length() - 1);
	
	s = s + "@p_action='" + qItem + "',";
	s = s + "@p_candId='" + id + "',";
	s = s + "@p_reqId='" + action.getRequisitionId() + "',";
	if (s != "") {
		s = s.substring(0, s.length() - 1);

		s = "SET " + s + ";";
	}

		System.out.println(s);
		
		return s;
		
	}

	/*
	 * public static String addFeedback11 (HireActionModel action) {
	 * 
	 * DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); Calendar
	 * cal = Calendar.getInstance();
	 * 
	 * String s = ""; String qItem = ""; String id = "";
	 * 
	 * if(!action.getEditId().equals("1")) { for(int i = 0; i <
	 * action.getCandidateId().size(); i++) { qItem = qItem + "(\"" +
	 * action.getRequisitionId() + "\",\"" + action.getCandidateId().get(i) +
	 * "\",\"" + action.getRating() + "\",\"" + action.getFeedback() + "\",\"" +
	 * dateFormat.format(cal.getTime()) + "\",\"" + action.getCreatedBy() + "\",\""
	 * + action.getCreatedBy() + "\"),"; } qItem = qItem.substring(0, qItem.length()
	 * - 1);
	 * 
	 * for(int i = 0; i < action.getCandidateId().size(); i++) { id = id + "\"" +
	 * action.getCandidateId().get(i) + "\","; } id = id.substring(0, id.length() -
	 * 1); } else { qItem = qItem + "(\"" + action.getRequisitionId() + "\",\"" +
	 * action.getCandId() + "\",\"" + action.getRating() + "\",\"" +
	 * action.getFeedback() + "\",\"" + action.getCreatedBy() +
	 * "\",@p_createdOn,@p_createdBy),"; qItem = qItem.substring(0, qItem.length() -
	 * 1); }
	 * 
	 * s = s + "@p_feedback='" + qItem + "',"; s = s + "@p_candId='(" + id + ")',";
	 * s = s + "@p_reqId='" + action.getRequisitionId() + "',"; s = s +
	 * "@p_candidateId='" + action.getCandId() + "',"; if (s != "") { s =
	 * s.substring(0, s.length() - 1);
	 * 
	 * s = "SET " + s + ";"; }
	 * 
	 * System.out.println(s);
	 * 
	 * return s;
	 * 
	 * }
	 */
public static String addFeedback(List<HireActionModel> feedBackModel) {
	String s = "";
	String listdata ="";
	String firstName="";
	String requisitionId="";
	String position="";
	String createDate="";
	String modeOfInt="";
	String intViewerName="";
	String designationName="";
	String feedSummary="";
	String feedback="";
	String createdBy="";
	
	for (HireActionModel m : feedBackModel) {
		firstName=m.getFirstName();
		requisitionId=m.getRequisitionId();
		position=m.getPosition();
		createDate=m.getCreateDate();
		modeOfInt=m.getModeOfInt();
		intViewerName=m.getIntViewerName();
		designationName=m.getDesignationName();
		feedSummary=m.getFeedSummary();
		feedback=m.getFeedback();
		createdBy=m.getCreatedBy();
			
	}
	
	s = s + "@p_candName='" + firstName + "',";
	s = s + "@p_reqId='" + requisitionId + "',";
	s = s + "@p_position='" + position + "',";
	s = s + "@p_createDate='" + createDate + "',";
	s = s + "@p_modeOfInt='" + modeOfInt + "',";
	s = s + "@p_intViewer='" + intViewerName + "',";
	s = s + "@p_designation='" + designationName + "',";
	s = s + "@p_fSum='" + feedSummary + "',";
	s = s + "@p_feedback='" + feedback + "',";
	s = s + "@p_createdBy='" + createdBy + "',";
	
	
	if(!feedBackModel.get(0).getFirstName().contentEquals("1")) {
	for (HireActionModel m : feedBackModel) {

		listdata = listdata + "(@p_candName,@p_reqId,\"" + m.getRatingCat() + "\",\"" + m.getRatingType() + "\",\"" + m.getRating() + "\",\"" + m.getComment() + "\"),";
	}
	listdata = listdata.substring(0, listdata.length() - 1);

	s = s + "@p_litemSubQuery='" + listdata + "',";


	if (s != "") {
	s = s.substring(0, s.length() - 1);

	s = "SET " + s + ";";
	}
	}
	System.out.println(s);
	
	return s;
}
}
