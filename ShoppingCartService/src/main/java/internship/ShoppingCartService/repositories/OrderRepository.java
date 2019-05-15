package internship.ShoppingCartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.ShoppingCartService.models.Order;
/**
 * Represents connection for Receipt model and Receipt table in database.
 * @author r.bogojevic
 *
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

}
