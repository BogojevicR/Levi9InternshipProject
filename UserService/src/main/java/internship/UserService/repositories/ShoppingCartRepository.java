package internship.UserService.repositories;

import org.springframework.data.repository.CrudRepository;

import internship.UserService.model.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}
