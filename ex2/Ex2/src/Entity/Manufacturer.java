package Entity;

import java.util.Objects;

public class Manufacturer {
	private String id; //Pk
	private String FullName;
	private String phoneNumber;
	private String Address;
	private String Email;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	@Override
	public String toString() {
		return "Manufacturer [id=" + id + ", FullName=" + FullName + ", phoneNumber=" + phoneNumber + ", Address="
				+ Address + ", Email=" + Email + "]";
	}
	public Manufacturer(String id, String fullName, String phoneNumber, String address, String email) {
		super();
		this.id = id;
		FullName = fullName;
		this.phoneNumber = phoneNumber;
		Address = address;
		Email = email;
	}
	@Override
	public int hashCode() {
		return Objects.hash(Address, Email, FullName, id, phoneNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manufacturer other = (Manufacturer) obj;
		return Objects.equals(Address, other.Address) && Objects.equals(Email, other.Email)
				&& Objects.equals(FullName, other.FullName) && id == other.id && phoneNumber == other.phoneNumber;
	}
	
	
	

}
