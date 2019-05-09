package internship.ShoppingCartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.ShoppingCartService.models.ReceiptItem;

public interface ReceiptItemRepository extends JpaRepository<ReceiptItem, Long> {

}
