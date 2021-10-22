package nirmalya.aatithya.restmodule.procurment.model;

public class InventorySkuProductModel {

	private String sku;
	private String productId;
	private String productName;
	private String brandId;
	private String brandName;
	private String productCatId;
	private String productCatName;

	public InventorySkuProductModel() {
		super();
	}

	public InventorySkuProductModel(Object sku, Object productId, Object productName, Object brandId, Object brandName,
			Object productCatId ,Object productCatName) {
		super();
		this.sku = (String) sku;
		this.productId = (String) productId;
		this.productName = (String) productName;
		this.brandId = (String) brandId;
		this.brandName = (String) brandName;
		this.productCatId = (String) productCatId;
		this.productCatName = (String) productCatName;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProductCatId() {
		return productCatId;
	}

	public void setProductCatId(String productCatId) {
		this.productCatId = productCatId;
	}

	public String getProductCatName() {
		return productCatName;
	}

	public void setProductCatName(String productCatName) {
		this.productCatName = productCatName;
	}

}
