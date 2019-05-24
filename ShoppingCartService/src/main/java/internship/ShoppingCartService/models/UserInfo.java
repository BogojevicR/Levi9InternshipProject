package internship.ShoppingCartService.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3314503969967203292L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String surname;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String phone;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private Adress adress;


	public UserInfo(Long id, String name, String surname, String email, String phone, Adress adress) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.phone = phone;
		this.adress = adress;
	}
	
}
