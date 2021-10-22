package nirmalya.aatithya.restmodule.patient.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestEHealthCardModel {

	private String eHealthCardNo;
	private String cardHldrName;
	private String date;
	private String profileImg;
	private String qrCode;
	private String stateLogo;
	private String stateName;

	public RestEHealthCardModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestEHealthCardModel(Object eHealthCardNo, Object cardHldrName, Object date, Object profileImg,
			Object qrCode, Object stateLogo,Object stateName) {
		super();
		this.eHealthCardNo = (String) eHealthCardNo;
		this.cardHldrName = (String) cardHldrName;
		this.date = (String) date;
		this.profileImg = (String) profileImg;
		this.qrCode = (String) qrCode;
		this.stateLogo = (String) stateLogo;
		this.stateName = (String) stateName;
		
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String geteHealthCardNo() {
		return eHealthCardNo;
	}

	public void seteHealthCardNo(String eHealthCardNo) {
		this.eHealthCardNo = eHealthCardNo;
	}

	public String getCardHldrName() {
		return cardHldrName;
	}

	public void setCardHldrName(String cardHldrName) {
		this.cardHldrName = cardHldrName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getStateLogo() {
		return stateLogo;
	}

	public void setStateLogo(String stateLogo) {
		this.stateLogo = stateLogo;
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
