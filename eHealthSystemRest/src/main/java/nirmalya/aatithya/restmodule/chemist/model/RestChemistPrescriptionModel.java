package nirmalya.aatithya.restmodule.chemist.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestChemistPrescriptionModel {
	private String time;
	private String name;
	private String cardnumber;
	private String status;
	private String orderid;
	private String address;
	
	
	public RestChemistPrescriptionModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public RestChemistPrescriptionModel(Object time, Object name, Object cardnumber,  Object status, Object orderid, Object address) {
		super();
		this.time = (String) time;
		this.name = (String) name;
		this.cardnumber = (String) cardnumber;
		
		this.status =(String) status;
		this.orderid =(String) orderid;
		this.address = (String) address;
	}
	
	
	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getOrderid() {
		return orderid;
	}



	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}



	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}
	
	
	
	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
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