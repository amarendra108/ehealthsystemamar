package nirmalya.aatithya.restmodule.patient.model;

import java.io.IOException;
import java.math.BigInteger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestImmunizationModel {
	private BigInteger slNo;
	private Integer immunizationId;
	private String immunizationName;
	private String status;
	private String immunizationDate;
	private String prescribedBy;
	private String comment;
	private BigInteger patientId;
	private String patientName;

	public RestImmunizationModel(Object slNo, Object immunizationId, Object immunizationName,Object status, Object immunizationDate,
			Object prescribedBy, Object comment) {
		super();
		this.slNo = (BigInteger) slNo;
		this.immunizationId = (Integer) immunizationId;
		this.immunizationName = (String) immunizationName;
		this.status = (String) status;
		this.immunizationDate = (String) immunizationDate;
		this.prescribedBy = (String) prescribedBy;
		this.comment = (String) comment;
	}

	public RestImmunizationModel() {
		super();
	}

	public BigInteger getSlNo() {
		return slNo;
	}

	public void setSlNo(BigInteger slNo) {
		this.slNo = slNo;
	}

	public Integer getImmunizationId() {
		return immunizationId;
	}

	public void setImmunizationId(Integer immunizationId) {
		this.immunizationId = immunizationId;
	}

	public String getImmunizationName() {
		return immunizationName;
	}

	public void setImmunizationName(String immunizationName) {
		this.immunizationName = immunizationName;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImmunizationDate() {
		return immunizationDate;
	}

	public void setImmunizationDate(String immunizationDate) {
		this.immunizationDate = immunizationDate;
	}

	public String getPrescribedBy() {
		return prescribedBy;
	}

	public void setPrescribedBy(String prescribedBy) {
		this.prescribedBy = prescribedBy;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BigInteger getPatientId() {
		return patientId;
	}

	public void setPatientId(BigInteger patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
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
