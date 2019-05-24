package internship.ShoppingCartService.DTO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class UserInfoDTO {

	private Long id;
	private String name;
	private String surname;
	private String email;
	private String phone;
	private AdressDTO adress;

	
	public UserInfoDTO(Long id, String name, String surname, String email, String phone, AdressDTO adress) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.adress = adress;
	}

}
