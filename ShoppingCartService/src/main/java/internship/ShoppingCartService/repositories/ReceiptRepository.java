package internship.ShoppingCartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.ShoppingCartService.models.Receipt;
/**
 * Represents connection for Receipt model and Receipt table in database.
 * @author r.bogojevic
 *
 */
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

}
