package internship.ShoppingCartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.ShoppingCartService.models.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

}
