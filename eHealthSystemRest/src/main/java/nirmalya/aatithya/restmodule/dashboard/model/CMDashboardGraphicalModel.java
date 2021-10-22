package nirmalya.aatithya.restmodule.dashboard.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CMDashboardGraphicalModel {
	private String createdDate;
	private String gender;
	private String counter;
	private String area;
	private String age;
	private String testGroup;
	private String riskStatus;
	private String phc;
	
	
	private String totalRegs;
	private String total_tested;
	private String total_normal;
	private String total_abnrml;
	private String total_highrisk;
	private String todays_regs;
	private String total_smpl_clctd;
	private String total_smpl_prcsd;
	
	public CMDashboardGraphicalModel() {
		super();
		// TODO Auto-generated constructor stub
	}
public CMDashboardGraphicalModel(Object createdDate, Object gender, Object area,Object age,Object testGroup,Object phc,Object riskStatus,Object counter) {
		super();
		this.createdDate = (String) createdDate;
		this.gender = (String) gender;		
		this.area = (String) area;
		this.age = (String) age;
		this.testGroup = (String) testGroup;
		this.phc = (String) phc;
		this.riskStatus = (String) riskStatus;
		this.counter = (String) counter;
		
		
		
	}

public CMDashboardGraphicalModel( Object totalRegs, Object total_tested, Object total_normal,
		Object total_abnrml, Object total_highrisk, Object todays_regs, Object total_smpl_clctd,
		Object total_smpl_prcsd,Object createdDate) {
	super();
	this.totalRegs = (String)totalRegs;
	this.total_tested = (String)total_tested;
	this.total_normal = (String)total_normal;
	this.total_abnrml = (String)total_abnrml;
	this.total_highrisk = (String)total_highrisk;
	this.todays_regs = (String)todays_regs;
	this.total_smpl_clctd = (String)total_smpl_clctd;
	this.total_smpl_prcsd = (String)total_smpl_prcsd;
}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCounter() {
		return counter;
	}
	public void setCounter(String counter) {
		this.counter = counter;
	}
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getTestGroup() {
		return testGroup;
	}
	public void setTestGroup(String testGroup) {
		this.testGroup = testGroup;
	}
	
	public String getRiskStatus() {
		return riskStatus;
	}
	public void setRiskStatus(String riskStatus) {
		this.riskStatus = riskStatus;
	}
	
	public String getPhc() {
		return phc;
	}
	public void setPhc(String phc) {
		this.phc = phc;
	}
	
	
	public String getTotalRegs() {
		return totalRegs;
	}
	public void setTotalRegs(String totalRegs) {
		this.totalRegs = totalRegs;
	}
	public String getTotal_tested() {
		return total_tested;
	}
	public void setTotal_tested(String total_tested) {
		this.total_tested = total_tested;
	}
	public String getTotal_normal() {
		return total_normal;
	}
	public void setTotal_normal(String total_normal) {
		this.total_normal = total_normal;
	}
	public String getTotal_abnrml() {
		return total_abnrml;
	}
	public void setTotal_abnrml(String total_abnrml) {
		this.total_abnrml = total_abnrml;
	}
	public String getTotal_highrisk() {
		return total_highrisk;
	}
	public void setTotal_highrisk(String total_highrisk) {
		this.total_highrisk = total_highrisk;
	}
	public String getTodays_regs() {
		return todays_regs;
	}
	public void setTodays_regs(String todays_regs) {
		this.todays_regs = todays_regs;
	}
	public String getTotal_smpl_clctd() {
		return total_smpl_clctd;
	}
	public void setTotal_smpl_clctd(String total_smpl_clctd) {
		this.total_smpl_clctd = total_smpl_clctd;
	}
	public String getTotal_smpl_prcsd() {
		return total_smpl_prcsd;
	}
	public void setTotal_smpl_prcsd(String total_smpl_prcsd) {
		this.total_smpl_prcsd = total_smpl_prcsd;
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
