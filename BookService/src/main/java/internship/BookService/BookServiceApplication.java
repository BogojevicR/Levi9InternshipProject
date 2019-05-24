package internship.BookService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The BookService Application implements an application that manages Books. It
 * contains various functions like saving, reading, editing books. and their
 * categories.
 * 
 * 
 * @author r.bogojevic
 *
 */
@SpringBootApplication
public class BookServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

}
