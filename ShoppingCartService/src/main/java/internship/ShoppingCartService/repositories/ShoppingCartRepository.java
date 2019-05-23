package internship.ShoppingCartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.ShoppingCartService.models.ShoppingCart;

/**
 * Represents connection for ShoppingCart model and ShoppingCart table in
 * database.
 * 
 * @author r.bogojevic
 *
 */
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}
