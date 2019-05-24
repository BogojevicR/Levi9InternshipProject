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

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Adress implements Serializable {

	private static final long serialVersionUID = -6497586504327034394L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String country;

	@Column(nullable = false)
	private String street;

	@Column(nullable = false)
	private String streetNumber;


	public Adress(Long id, String city, String country, String street, String streetNumber) {
		super();
		this.id = id;
		this.city = city;
		this.country = country;
		this.street = street;
		this.streetNumber = streetNumber;
	}

}
