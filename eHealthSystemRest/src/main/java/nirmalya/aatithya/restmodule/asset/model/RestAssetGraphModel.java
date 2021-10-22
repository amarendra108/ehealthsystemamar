package nirmalya.aatithya.restmodule.asset.model;

public class RestAssetGraphModel {
	private String month;
	private Integer totalAsset;
	private Integer mentainAsset;
	private Integer remainAsset;
	
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

	public RestAssetGraphModel(Object month, Object totalAsset, Object mentainAsset, Object remainAsset) {
		super();
		this.month = (String)month;
		this.totalAsset = (Integer)totalAsset;
		this.mentainAsset = (Integer)mentainAsset;
		this.remainAsset = (Integer)remainAsset;
	}
}
