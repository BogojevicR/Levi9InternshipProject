package internship.ShoppingCartService.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class represents entity of receipt.
 * @author s.krasic
 *
 */


@Entity
public class Receipt {
	
	/** 
	 * 
	 * @author s.krasic
	 * id is generated value that is value to do identification of receipt.
	 * itemList represents list of items (book and number of copies of book and price) that person wants to buy.
	 * totalPrice represents final amount on receipt that person must pay.
	 *
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ElementCollection
	private List<ReceiptItem> itemList = new ArrayList<ReceiptItem>();
	private double totalPrice;
	
	public Receipt() {
		super();
		this.totalPrice = 0;
	}
	public Receipt(List<ReceiptItem> itemList) {
		super();
		this.itemList = itemList;
		this.totalPrice = 0;
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
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public void calculateTotalPrice() {
		for(ReceiptItem i : this.itemList) {
			totalPrice += i.getTotal();
		}
	}
	
	@Override
	public String toString() {
		return "Receipt [id=" + id + ", itemList=" + itemList + ", totalPrice=" + totalPrice + "]";
	}
	
	
	
}
