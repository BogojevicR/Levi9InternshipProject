package internship.UserService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class ReceiptItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Book book;
	@Column(nullable = false)
	private int quantity;
	@Column(nullable = false)
	private double total;
	
	public ReceiptItem() {
		super();
	}

	public ReceiptItem(Book book, int quantity) {
		super();
		this.book = book;
		this.quantity = quantity;
		this.total = book.getPrice() * quantity;
	}

	public ReceiptItem(CartItem i) {
		this.book = i.getBook();
		this.quantity = i.getQuantity();
		this.total = i.getTotal();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "ReceiptItem [id=" + id + ", book=" + book + ", quantity=" + quantity + ", total=" + total + "]";
	}
	
}