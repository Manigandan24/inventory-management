package sl.ms.inventorymanagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@Entity(name = "sl_product")
@JacksonXmlRootElement(localName = "Product")
@XmlRootElement(name="Product")
public class Product {

	@Id
	@SequenceGenerator(name = "productseq", sequenceName = "product_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productseq")
	@JsonIgnore
	private int prodId;
	private int Id;
	private String name;
	private Double price;
	private int quantity;

	public Product(int Id,String name,Double price,int quantity) {
	this.Id=Id;
	this.name=name;
	this.price=price;
	this.quantity=quantity;
	}
	
	public Product() {
		
	}
	
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@JsonIgnore
	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		
		Product pro=(Product) obj;
		return this.getName().equals(pro.getName());
	}
}
