package Entity;

import java.util.Objects;

import enums.SweetnessLevel;

public class Wine {
	
	private String catalogNumber;//pk
	private String manufacturerId ;//Fk
	private String wineTypeSerialNum;
	private String name;
	private String description;
	private String productionYear;
	private String pricePerBottle;
	private String sweetnessLevel;
	private String ProductImg;
	public Wine(String catalogNumber, String manufacturerId,String wineTypeSerialNum, String name, String description, String productionYear,
			String pricePerBottle, String sweetnessLevel, String productImg) {
		super();
		this.catalogNumber = catalogNumber;
		this.manufacturerId = manufacturerId;
		this.setWineTypeSerialNum(wineTypeSerialNum);
		this.name = name;
		this.description = description;
		this.productionYear = productionYear;
		this.pricePerBottle = pricePerBottle;
		this.sweetnessLevel = sweetnessLevel;
		ProductImg = productImg;
	}
	public String getCatalogNumber() {
		return catalogNumber;
	}
	public void setCatalogNumber(String catalogNumber) {
		this.catalogNumber = catalogNumber;
	}
	public String getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(String manufacturer) {
		this.manufacturerId = manufacturer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProductionYear() {
		return productionYear;
	}
	public void setProductionYear(String productionYear) {
		this.productionYear = productionYear;
	}
	public String getPricePerBottle() {
		return pricePerBottle;
	}
	public void setPricePerBottle(String pricePerBottle) {
		this.pricePerBottle = pricePerBottle;
	}
	public String getSweetnessLevel() {
		return sweetnessLevel;
	}
	public void setSweetnessLevel(String sweetnessLevel) {
		this.sweetnessLevel = sweetnessLevel;
	}
	public String getProductImg() {
		return ProductImg;
	}
	public void setProductImg(String productImg) {
		ProductImg = productImg;
	}
	@Override
	public String toString() {
		return "Wine [catalogNumber=" + catalogNumber + ", manufacturerId=" + manufacturerId + ", name=" + name
				+ ", description=" + description + ", productionYear=" + productionYear + ", pricePerBottle="
				+ pricePerBottle + ", sweetnessLevel=" + sweetnessLevel + ", ProductImg=" + ProductImg + "]";
	}
	public String getWineTypeSerialNum() {
		return wineTypeSerialNum;
	}
	public void setWineTypeSerialNum(String wineTypeSerialNum) {
		this.wineTypeSerialNum = wineTypeSerialNum;
	}
	
	}
	
	
	


