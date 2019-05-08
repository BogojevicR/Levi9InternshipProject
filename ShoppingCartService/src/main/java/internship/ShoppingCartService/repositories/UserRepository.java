package internship.ShoppingCartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import internship.ShoppingCartService.models.User;


public interface UserRepository extends JpaRepository<User, Long>{

	//@Query("SELECT * FROM user u WHERE u.email=?1")
	User findByEmail (String email);
	
	@Query(value = "SELECT role FROM user u WHERE u.id = ?1", nativeQuery = true)
	String getRole(Long id);
	
	@Query(value = "SELECT * FROM user u WHERE u.email = ?1 and u.password = ?2", nativeQuery = true)
	User logInUser(String email, String password);
	
	
}
