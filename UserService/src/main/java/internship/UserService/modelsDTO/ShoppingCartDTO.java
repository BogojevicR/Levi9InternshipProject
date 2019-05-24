package internship.UserService.modelsDTO;

import java.util.List;

import internship.UserService.model.CartItem;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ShoppingCartDTO {

	private Long id;
	private List<CartItem> itemList;


	public ShoppingCartDTO(List<CartItem> itemList) {
		super();
		this.itemList = itemList;
	}

}
