package internship.BookService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.BookService.models.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>{
 
	Category findByName(String name);
}
