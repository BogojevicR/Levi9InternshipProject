package internship.ShoppingCartService.repositories;

import org.springframework.data.repository.CrudRepository;

import internship.ShoppingCartService.models.Receipt;

public interface ReceiptRepository extends CrudRepository<Receipt, Long> {

}
