package nirmalya.aathithya.webmodule.asset.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AssetGraphModel {
	private String month;
	private Integer totalAsset;
	private Integer mentainAsset;
	private Integer remainAsset;
	
	private List<String> monthList = new ArrayList<String>();

	private List<Integer> totalAssetList = new ArrayList<Integer>();
	private List<Integer> mentainAssetList = new ArrayList<Integer>();
	private List<Integer> remainAssetList = new ArrayList<Integer>();

	
	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	

	public Integer getTotalAsset() {
		return totalAsset;
	}


	public void setTotalAsset(Integer totalAsset) {
		this.totalAsset = totalAsset;
	}


	public Integer getMentainAsset() {
		return mentainAsset;
	}


	public void setMentainAsset(Integer mentainAsset) {
		this.mentainAsset = mentainAsset;
	}


	public Integer getRemainAsset() {
		return remainAsset;
	}


	public void setRemainAsset(Integer remainAsset) {
		this.remainAsset = remainAsset;
	}


	public List<String> getMonthList() {
		return monthList;
	}


	public void setMonthList(List<String> monthList) {
		this.monthList = monthList;
	}


	public List<Integer> getTotalAssetList() {
		return totalAssetList;
	}


	public void setTotalAssetList(List<Integer> totalAssetList) {
		this.totalAssetList = totalAssetList;
	}


	public List<Integer> getMentainAssetList() {
		return mentainAssetList;
	}


	public void setMentainAssetList(List<Integer> mentainAssetList) {
		this.mentainAssetList = mentainAssetList;
	}


	public List<Integer> getRemainAssetList() {
		return remainAssetList;
	}


	public void setRemainAssetList(List<Integer> remainAssetList) {
		this.remainAssetList = remainAssetList;
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
