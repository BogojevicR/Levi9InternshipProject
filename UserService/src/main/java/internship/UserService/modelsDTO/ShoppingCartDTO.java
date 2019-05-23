package internship.UserService.modelsDTO;

import java.util.List;

import internship.UserService.model.CartItem;

public class ShoppingCartDTO {

	private Long id;
	private List<CartItem> itemList;

	public ShoppingCartDTO() {
		super();
	}

	public ShoppingCartDTO(List<CartItem> itemList) {
		super();
		this.itemList = itemList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CartItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<CartItem> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "ShoppingCartDTO [id=" + id + ", itemList=" + itemList + "]";
	}

}
