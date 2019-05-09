package internship.ShoppingCartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.ShoppingCartService.models.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	CartItem findByBookId(Long id);
}
