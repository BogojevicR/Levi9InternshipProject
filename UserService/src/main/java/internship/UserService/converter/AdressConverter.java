package internship.UserService.converter;

import internship.UserService.model.Adress;
import internship.UserService.modelsDTO.AdressDTO;

public class AdressConverter extends AbstractConverter{

	public static AdressDTO formEnity(Adress e) {
		if (e != null) {
			AdressDTO adressDTO = new AdressDTO();
			adressDTO.setId(e.getId());
			adressDTO.setCity(e.getCity());
			adressDTO.setCountry(e.getCountry());
			adressDTO.setStreet(e.getStreet());
			adressDTO.setStreetNumber(e.getStreetNumber());
			
			return adressDTO;
		}else {
			
			return null;
		}
	}
	
	public static Adress toEnity(AdressDTO d) {
		if (d != null) {
			Adress adress = new Adress();
			adress.setId(d.getId());
			adress.setCity(d.getCity());
			adress.setCountry(d.getCountry());
			adress.setStreet(d.getStreet());
			adress.setStreetNumber(d.getStreetNumber());
			
			return adress;
		}else {
			
			return null;
		}
	}
	
	
	
}
