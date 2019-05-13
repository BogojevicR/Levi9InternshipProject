package internship.ShoppingCartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.ShoppingCartService.models.CartItem;
/**
 * Represents connection for CartItem model and CartItem table in database.
 * @author r.bogojevic
 *
 */
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	/**
	 * Finds cart item by its id from database.
	 * @param id id of chosen item.
	 * @return CartItem with chosen id.
	 */
	CartItem findByBookId(Long id);
}
