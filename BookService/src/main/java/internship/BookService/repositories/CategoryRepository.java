package internship.BookService.repositories;

import org.springframework.data.repository.CrudRepository;

import internship.BookService.models.Category;

public interface CategoryRepository extends CrudRepository<Category,Long>{
 
	Category findByName(String name);
}
