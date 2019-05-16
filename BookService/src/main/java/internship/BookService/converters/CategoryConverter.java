package internship.BookService.converters;

import internship.BookService.DTO.CategoryDTO;
import internship.BookService.models.Category;

public abstract class CategoryConverter extends AbstractConverter {

	public static CategoryDTO fromEntity(Category c) {
		CategoryDTO category = new CategoryDTO(c);
		return category;	
	}
	
	public static Category toEntity (CategoryDTO c) {
		Category category = new Category();
		category.setId(c.getId());
		category.setName(c.getName());
		return category;
	}
}
