package internship.UserService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.UserService.model.ShoppingCart;


/**
 * Represents connection for ShoppingCart model and ShoppingCart table in
 * database.
 * 
 * @author r.bogojevic
 *
 */
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}
