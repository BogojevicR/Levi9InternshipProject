package internship.ShoppingCartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.ShoppingCartService.models.Purchase;

/**
 * Represents connection for Receipt model and Receipt table in database.
 * 
 * @author r.bogojevic
 *
 */
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
