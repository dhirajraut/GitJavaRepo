package entities;

public class Address {
	private String street;
	private String city;
	private String pinCode;
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Street = " + street);
		buf.append("\nCity = " + city);
		buf.append("\nPin = " + pinCode);
		return buf.toString();
	}
}