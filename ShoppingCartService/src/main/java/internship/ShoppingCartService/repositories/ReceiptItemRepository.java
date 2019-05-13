package internship.ShoppingCartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.ShoppingCartService.models.ReceiptItem;
/**
 * Represents connection for ReceiptItem model and ReceiptItem table in database.
 * @author r.bogojevic
 *
 */
public interface ReceiptItemRepository extends JpaRepository<ReceiptItem, Long> {

}
