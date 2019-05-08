package internship.ShoppingCartService.repositories;

import org.springframework.data.repository.CrudRepository;

import internship.ShoppingCartService.models.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem, Long> {

	CartItem findByBookId(Long id);
}
