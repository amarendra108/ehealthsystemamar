package nirmalya.aatithya.restmodule.recruitment.model;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CandidateApplyRequisitionModel {

	private List<String> candidateId;
	private List<String> requisitionId;
	private String createdBy;
	private String requId;
	private String candId;

	public CandidateApplyRequisitionModel() {
		super();
// TODO Auto-generated constructor stub
	}

	public CandidateApplyRequisitionModel(Object candId, Object requId, Object createdBy) {
		super();
		this.candId = (String) candId;
		this.createdBy = (String) createdBy;
		this.requId = (String) requId;

	}

	public List<String> getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(List<String> candidateId) {
		this.candidateId = candidateId;
	}

	public List<String> getRequisitionId() {
		return requisitionId;
	}

	public void setRequisitionId(List<String> requisitionId) {
		this.requisitionId = requisitionId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getRequId() {
		return requId;
	}

	public void setRequId(String requId) {
		this.requId = requId;
	}

	public String getCandId() {
		return candId;
	}

	public void setCandId(String candId) {
		this.candId = candId;
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