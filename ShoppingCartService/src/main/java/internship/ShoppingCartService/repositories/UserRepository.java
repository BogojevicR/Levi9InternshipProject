package internship.ShoppingCartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import internship.ShoppingCartService.models.User;
import internship.ShoppingCartService.models.User.Role;

/**
 * Represents connection for User model and User table in database.
 * @author r.bogojevic
 *
 */
public interface UserRepository extends JpaRepository<User, Long>{
	
	/**
	 * Finds user by his email.
	 * @param email email of chosen user.
	 * @return User with chosen email.
	 */
	User findByUsername (String username);
	/**
	 * Gets role from selected user by his id.
	 * @param id id of user.
	 * @return role of chosen user.
	 */
	@Query(value = "SELECT role FROM user u WHERE u.id = ?1", nativeQuery = true)
	Role  getRole(Long id);
	/**
	 * Finds user by his email and password.
	 * @param email email of user.
	 * @param password password of user.
	 * @return User with chosen email and password.
	 */
	@Query(value = "SELECT * FROM user u WHERE u.email = ?1 and u.password = ?2", nativeQuery = true)
	User logInUser(String email, String password);
	
	
}
