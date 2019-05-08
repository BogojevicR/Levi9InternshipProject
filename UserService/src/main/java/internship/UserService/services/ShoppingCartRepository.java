package internship.UserService.services;

import org.springframework.data.repository.CrudRepository;

import internship.UserService.model.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}
