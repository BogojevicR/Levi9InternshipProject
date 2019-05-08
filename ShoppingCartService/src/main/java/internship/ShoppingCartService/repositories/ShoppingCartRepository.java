package internship.ShoppingCartService.repositories;

import org.springframework.data.repository.CrudRepository;

import internship.ShoppingCartService.models.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}
