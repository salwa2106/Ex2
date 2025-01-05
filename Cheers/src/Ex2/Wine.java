package Ex2;

import java.util.Objects;

import enums.SweetnessLevel;

public class Wine {
	
	private int catalogNumber;//pk
	private int manufacturerId ;//Fk
	private String name;
	private String description;
	private int productionYear;
	private double pricePerBottle;
	private SweetnessLevel sweetnessLevel;
	private String ProductImg;
	public Wine(int catalogNumber, int manufacturerId, String name, String description, int productionYear,
			double pricePerBottle, SweetnessLevel sweetnessLevel, String productImg) {
		super();
		this.catalogNumber = catalogNumber;
		this.manufacturerId = manufacturerId;
		this.name = name;
		this.description = description;
		this.productionYear = productionYear;
		this.pricePerBottle = pricePerBottle;
		this.sweetnessLevel = sweetnessLevel;
		ProductImg = productImg;
	}
	public int getCatalogNumber() {
		return catalogNumber;
	}
	public void setCatalogNumber(int catalogNumber) {
		this.catalogNumber = catalogNumber;
	}
	public int getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
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
	public int getProductionYear() {
		return productionYear;
	}
	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}
	public double getPricePerBottle() {
		return pricePerBottle;
	}
	public void setPricePerBottle(double pricePerBottle) {
		this.pricePerBottle = pricePerBottle;
	}
	public SweetnessLevel getSweetnessLevel() {
		return sweetnessLevel;
	}
	public void setSweetnessLevel(SweetnessLevel sweetnessLevel) {
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
	@Override
	public int hashCode() {
		return Objects.hash(ProductImg, catalogNumber, description, manufacturerId, name, pricePerBottle,
				productionYear, sweetnessLevel);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wine other = (Wine) obj;
		return Objects.equals(ProductImg, other.ProductImg) && catalogNumber == other.catalogNumber
				&& Objects.equals(description, other.description) && manufacturerId == other.manufacturerId
				&& Objects.equals(name, other.name)
				&& Double.doubleToLongBits(pricePerBottle) == Double.doubleToLongBits(other.pricePerBottle)
				&& productionYear == other.productionYear && sweetnessLevel == other.sweetnessLevel;
	}
	
	
	

}
