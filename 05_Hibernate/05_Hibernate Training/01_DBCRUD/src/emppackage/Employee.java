package emppackage;

public class Employee {
	private int id;
	private String name;
	private double salary;
	
	private Address address;

	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("Id = " + id);
		buf.append("\nName = " + name);
		buf.append("\nSalary = " + salary);
		return buf.toString();
	}
	
	public boolean equals(Employee e) {
		if (id == e.id)
			return true;
		else
			return false;
	}
	public int hashcode(){
		return id;
	}
}
