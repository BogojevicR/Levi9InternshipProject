package internship.UserService.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


/**
 * This class represents entity of book.
 * @author s.krasic
 *
 */

@Entity
public class Book {
	
	/** 
	 * 
	 * @author s.krasic
	 * @param State - represents state of the book that can be ACTIVE or DELETED.
	 * @param id is generated value that is value to do identification of book.
	 * @param title represents title of the book.
	 * @param autor represents name of person that wrote book.
	 * @param category represents category of book.
	 * @param price is price of the book.
	 * @param quantity represents number of books that exists in shop that person can buy.
	 * @param soldAmount represents number of books that are sold.
	 *
	 */
	
	public enum State { ACTIVE, DELETED }
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String author;
	@ManyToOne
	private Category category;
	private double price;
	private State state;
	private int quantity;
	private int soldAmount;
	
	public Book() {
		super();
	}

	public Book(String title, String author, Category category, double price, State state, int quantity) {
		super();
		this.title = title;
		this.author = author;
		this.category = category;
		this.price = price;
		this.state = state;
		this.quantity = quantity;
		this.soldAmount = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSoldAmount() {
		return soldAmount;
	}

	public void setSoldAmount(int soldAmount) {
		this.soldAmount = soldAmount;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", category=" + category.getName() + ", price="
				+ price + ", state=" + state + ", quantity=" + quantity + ", soldAmount=" + soldAmount + "]";
	}

	/**
	 * This method is metod to edit some fields of book.
	 * @param book is an entity of book which parameters we want to edit.
	 * @return nothing.
	 */
	
	public void edit(Book book) {
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.category = book.getCategory();
		this.price = book.getPrice();
		this.state = book.getState();
		this.quantity = book.getQuantity();
		this.soldAmount = book.getSoldAmount();
	}
	
	
	
}
