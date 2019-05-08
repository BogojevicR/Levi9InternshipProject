package internship.ShoppingCartService.repositories;

import org.springframework.data.repository.CrudRepository;

import internship.ShoppingCartService.models.ReceiptItem;

public interface ReceiptItemRepository extends CrudRepository<ReceiptItem, Long> {

}
