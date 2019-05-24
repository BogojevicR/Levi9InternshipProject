package internship.UserService.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Represents enity of book category.
 * 
 * @author r.bogojevic
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Category implements Serializable {

	private static final long serialVersionUID = 3973289252893384476L;

	/**
	 * Auto-generated, unique key for a book category.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * Name of book category in text value.
	 */
	@Column(nullable = false)
	private String name;


	public Category(String name) {
		super();
		this.name = name;
	}

	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
