package nirmalya.aatithya.restmodule.asset.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAssetPoAndInventoryModel {

	private String assetCodeID;
	private String assetCode;
	private String classification;
	private String grnId;
	private String grn;
	private String poId;
	private String poDate;
	private String quotationId;
	private String quotation;
	private String qrCode;
	
	public RestAssetPoAndInventoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestAssetPoAndInventoryModel(Object assetCodeID, Object assetCode, Object classification, Object grnId,
			Object grn, Object poId, Object poDate, Object quotationId, Object quotation, Object qrCode) {
		super();
		this.assetCodeID = (String) assetCodeID;
		this.assetCode = (String) assetCode;
		this.classification = (String) classification;
		this.grnId = (String) grnId;
		this.grn = (String) grn;
		this.poId = (String) poId;
		this.poDate = (String) poDate;
		this.quotationId = (String) quotationId;
		this.quotation = (String) quotation;
		this.qrCode = (String) qrCode;
	}

	public String getAssetCodeID() {
		return assetCodeID;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public String getClassification() {
		return classification;
	}

	public String getGrnId() {
		return grnId;
	}

	public String getGrn() {
		return grn;
	}

	public String getPoId() {
		return poId;
	}

	public String getPoDate() {
		return poDate;
	}

	public String getQuotationId() {
		return quotationId;
	}

	public String getQuotation() {
		return quotation;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setAssetCodeID(String assetCodeID) {
		this.assetCodeID = assetCodeID;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public void setGrnId(String grnId) {
		this.grnId = grnId;
	}

	public void setGrn(String grn) {
		this.grn = grn;
	}

	public void setPoId(String poId) {
		this.poId = poId;
	}

	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}

	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}

	public void setQuotation(String quotation) {
		this.quotation = quotation;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
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
