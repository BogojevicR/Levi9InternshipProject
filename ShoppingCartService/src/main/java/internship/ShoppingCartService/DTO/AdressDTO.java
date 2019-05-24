package internship.ShoppingCartService.DTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AdressDTO {

	private Long id;
	private String city;
	private String country;
	private String street;
	private String streetNumber;

	
	public AdressDTO(Long id, String city, String country, String street, String streetNumber) {
		super();
		this.id = id;
		this.city = city;
		this.country = country;
		this.street = street;
		this.streetNumber = streetNumber;
	}

}
