package internship.ShoppingCartService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * The ShoppingCartServiceApplication implements an application that manages shopping cart.
 * It contains various functions like item adding, removing, cart emptying.
 * @author r.bogojevic
 *
 */
@SpringBootApplication
public class ShoppingCartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartServiceApplication.class, args);
	}

}
