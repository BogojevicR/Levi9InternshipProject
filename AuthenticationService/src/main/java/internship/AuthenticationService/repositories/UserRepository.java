package internship.AuthenticationService.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import internship.AuthenticationService.models.User;
import internship.AuthenticationService.models.User.Role;

/**
 * Represents connection for User model and User table in database.
 * 
 * @author r.bogojevic
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Finds user by his username.
	 * 
	 * @param username username of chosen user.
	 * @return User with chosen username.
	 */
	User findByUsername(String username);

	/**
	 * Gets role from selected user by his id.
	 * 
	 * @param id id of user.
	 * @return role of chosen user in textual representation.
	 */
	@Query(value = "SELECT role FROM user u WHERE u.id = ?1", nativeQuery = true)
	Role getRole(Long id);

	/**
	 * Finds user by his email and password.
	 * 
	 * @param email    email of user.
	 * @param password password of user.
	 * @return User with chosen email and password.
	 */
	@Query(value = "SELECT * FROM user u WHERE u.username = ?1 and u.password = ?2", nativeQuery = true)
	User login(String username, String password);

	/**
	 * Finds user by his token.
	 * 
	 * @param token token of user.
	 * @return Optional value of User with chosen token.
	 */
	Optional<User> findByToken(String token);

}
