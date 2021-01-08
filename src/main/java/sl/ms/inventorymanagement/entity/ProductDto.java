package sl.ms.inventorymanagement.entity;

public class ProductDto {

	private int Id;
	private String name;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		
		ProductDto pro=(ProductDto) obj;
		return this.getName().equals(pro.getName());
	}
	
}
