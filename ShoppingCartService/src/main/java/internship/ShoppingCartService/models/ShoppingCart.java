package internship.ShoppingCartService.models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ElementCollection
	private List<CartItem> itemList;

	
	public ShoppingCart() {
		super();
	}

	public ShoppingCart(List<CartItem> itemList) {
		super();
		this.itemList = itemList;
	//	this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<CartItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<CartItem> itemList) {
		this.itemList = itemList;
	}


	public boolean checkBook(Long id2) {
		for(CartItem i : this.itemList) {
			if(i.getBook().getId() == id2)
				return true;
		}
		return false;
	}
	
	public CartItem getItemByBookId(Long id2) {
		for(CartItem i : this.itemList) {
			if(i.getBook().getId() == id2)
				return i;
		}
		return null;
	}

	public void removeItemById(Long cartItemId) {
		for(CartItem i : this.itemList) {
			if(i.getId() == cartItemId)
				this.itemList.remove(i);
				return;
		}
		
	}
	
}
