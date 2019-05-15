package internship.UserService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.UserService.model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

}
