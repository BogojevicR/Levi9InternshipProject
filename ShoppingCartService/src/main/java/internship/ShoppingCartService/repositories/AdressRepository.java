package internship.ShoppingCartService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import internship.ShoppingCartService.models.Adress;

public interface AdressRepository extends JpaRepository<Adress, Long> {

}
