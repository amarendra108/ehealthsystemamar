package nirmalya.aatithya.restmodule.patient.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestPatientMedicationRequestedMdcnModel {
private String medicinename;
private String medicinetype;
private String quantity;
private String hospitalname;
private String status;
private String orderid;
public RestPatientMedicationRequestedMdcnModel(Object medicinename, Object medicinetype, Object quantity, Object hospitalname,
		Object status, Object orderid) {
	// TODO Auto-generated constructor stub
	this.medicinename = (String)medicinename;
	this.medicinetype = (String)medicinetype;
	this.quantity = (String)quantity;
	this.hospitalname = (String)hospitalname;
	this.status = (String)status;
	this.orderid = (String)orderid;
}
public String getMedicinename() {
	return medicinename;
}
public void setMedicinename(String medicinename) {
	this.medicinename = medicinename;
}
public String getMedicinetype() {
	return medicinetype;
}
public void setMedicinetype(String medicinetype) {
	this.medicinetype = medicinetype;
}
public String getQuantity() {
	return quantity;
}
public void setQuantity(String quantity) {
	this.quantity = quantity;
}
public String getHospitalname() {
	return hospitalname;
}
public void setHospitalname(String hospitalname) {
	this.hospitalname = hospitalname;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getOrderid() {
	return orderid;
}
public void setOrderid(String orderid) {
	this.orderid = orderid;
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
