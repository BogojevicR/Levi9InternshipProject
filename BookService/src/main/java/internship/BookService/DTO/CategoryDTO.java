package internship.BookService.DTO;

import internship.BookService.models.Category;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CategoryDTO {

	private Long id;
	private String name;

	
	public CategoryDTO(Category category) {
		this.id = category.getId();
		this.name = category.getName();
	}
}
