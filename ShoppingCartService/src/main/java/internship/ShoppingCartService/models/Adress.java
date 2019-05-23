package internship.ShoppingCartService.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Adress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 466068299659178985L;

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

	
	public Adress() {
		super();
	}

	public Adress(Long id, String city, String country, String street, String streetNumber) {
		super();
		this.id = id;
		this.city = city;
		this.country = country;
		this.street = street;
		this.streetNumber = streetNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	@Override
	public String toString() {
		return "Adress [id=" + id + ", city=" + city + ", country=" + country + ", street=" + street + ", streetNumber="
				+ streetNumber + "]";
	}

}
