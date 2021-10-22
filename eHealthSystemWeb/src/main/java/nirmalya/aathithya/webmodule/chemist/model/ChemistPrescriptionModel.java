package nirmalya.aathithya.webmodule.chemist.model;
public class ChemistPrescriptionModel {
	private String time;
	private String name;
	private String cardnumber;
	private String status;
	private String orderid;
	private String address;
	
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

	public ChemistPrescriptionModel() {
		super();
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
		return "chemistDashboardModel1 [time=" + time + ", name=" + name + ", cardnumber=" + cardnumber +  "]";
	}
	
}

