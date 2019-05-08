package internship.ShoppingCartService.repositories;

import org.springframework.data.repository.CrudRepository;

import internship.ShoppingCartService.models.Category;


public interface CategoryRepository extends CrudRepository<Category,Long>{
 
	Category findByName(String name);
}
