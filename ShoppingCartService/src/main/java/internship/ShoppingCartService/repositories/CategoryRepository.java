package internship.ShoppingCartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.ShoppingCartService.models.Category;
/**
 *  Represents connection for Category model and Category table in database.
 * @author r.bogojevic
 *
 */
public interface CategoryRepository extends JpaRepository<Category,Long>{
 /**
  * Finds category by name.
  * @param name name of category.
  * @return Category with chosen name.
  */
	Category findByName(String name);
}
