package internship.ShoppingCartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.ShoppingCartService.models.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

}
