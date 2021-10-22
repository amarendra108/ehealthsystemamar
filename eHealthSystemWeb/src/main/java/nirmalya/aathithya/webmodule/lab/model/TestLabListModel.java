package nirmalya.aathithya.webmodule.lab.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;


public class TestLabListModel {

	private List<TestReportModel> obj = new ArrayList<TestReportModel>();
	
	public TestLabListModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestLabListModel(List<TestReportModel> obj) {
		super();
		this.obj = obj;
	}

	public List<TestReportModel> getObj() {
		return obj;
	}

	public void setObj(List<TestReportModel> obj) {
		this.obj = obj;
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
