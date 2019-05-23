package internship.UserService.converter;

import internship.UserService.model.Adress;
import internship.UserService.model.UserInfo;
import internship.UserService.modelsDTO.AdressDTO;
import internship.UserService.modelsDTO.UserInfoDTO;

public class UserInfoConverter {

	public static UserInfoDTO fromEntity(UserInfo e) {

		if (e != null) {
			UserInfoDTO userInfoDTO = new UserInfoDTO();
			userInfoDTO.setId(e.getId());
			AdressDTO adressDTO = AdressConverter.formEnity(e.getAdress());
			userInfoDTO.setAdress(adressDTO);
			userInfoDTO.setEmail(e.getEmail());
			userInfoDTO.setName(e.getName());
			userInfoDTO.setSurname(e.getSurname());
			userInfoDTO.setPhone(e.getPhone());
			return userInfoDTO;

		} else {
			return null;
		}
	}

	public static UserInfo toEntity(UserInfoDTO d) {

		if (d != null) {
			UserInfo userInfo = new UserInfo();
			userInfo.setId(d.getId());
			Adress adress = AdressConverter.toEnity(d.getAdress());
			userInfo.setAdress(adress);
			userInfo.setEmail(d.getEmail());
			userInfo.setName(d.getName());
			userInfo.setSurname(d.getSurname());
			userInfo.setPhone(d.getPhone());
			return userInfo;

		} else {
			return null;
		}
	}

	private UserInfoConverter() {

	}

}
