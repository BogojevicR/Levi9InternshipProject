package internship.ShoppingCartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.ShoppingCartService.models.Category;


public interface CategoryRepository extends JpaRepository<Category,Long>{
 
	Category findByName(String name);
}
