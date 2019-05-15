package internship.UserService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.UserService.model.Adress;

public interface AdressRepository extends JpaRepository<Adress, Long> {

}
