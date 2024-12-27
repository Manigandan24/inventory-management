package sl.ms.inventorymanagement.config;

public class Person {

	private int id;
	private String first;
	private String last;
	private Double totalAmount;
	
	public Person(int id, String first, String last, Double totalAmount) {
		super();
		this.id = id;
		this.first = first;
		this.last = last;
		this.totalAmount = totalAmount;
	}
	public Person() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public String toString() {
		return first+", "+last+", "+id;
	}
}
