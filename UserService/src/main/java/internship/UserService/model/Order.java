package internship.UserService.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * This class represents entity of receipt.
 * @author s.krasic
 *
 */


@Entity
public class Order implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	@OneToOne
	private UserInfo userInfo;
	
	public Order() {
		super();
		this.totalPrice = 0;
	}
	public Order(List<ReceiptItem> itemList) {
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
	
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", itemList=" + itemList + ", totalPrice=" + totalPrice + "]";
	}
	
	
	
	
}
