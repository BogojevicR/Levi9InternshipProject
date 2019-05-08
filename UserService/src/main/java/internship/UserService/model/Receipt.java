package internship.UserService.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Receipt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ElementCollection
	private List<ReceiptItem> itemList = new ArrayList<ReceiptItem>();
	
	public Receipt() {
		super();
	}
	public Receipt(List<ReceiptItem> itemList) {
		super();
		this.itemList = itemList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<ReceiptItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<ReceiptItem> itemList) {
		this.itemList = itemList;
	}
	@Override
	public String toString() {
		return "Receipt [id=" + id + ", itemList=" + itemList + "]";
	}
	
}
