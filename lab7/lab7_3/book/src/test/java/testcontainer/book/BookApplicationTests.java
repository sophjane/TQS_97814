package testcontainer.book;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import testcontainer.book.entity.Book;
import testcontainer.book.repository.BookRepository;

@Testcontainers
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookApplicationTests {

	@Container
	public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:latest")
		.withUsername("admin")
		.withPassword("admin")
		.withDatabaseName("books");
  
	@Autowired
	private BookRepository bookRepository;
  
	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
	  registry.add("spring.datasource.url", container::getJdbcUrl);
	  registry.add("spring.datasource.password", container::getPassword);
	  registry.add("spring.datasource.username", container::getUsername);
	}
  
	@Test
	@Order(1)
	public void savesBook() {
  
	  Book book1 = new Book();
	  book1.setTitle("The Lion, the Witch and the Wardrobe");
	  book1.setAuthor("C.S. Lewis");
	  bookRepository.save(book1);


	  Book book2 = new Book("The Book Thief", "Markus Zusak");
	  bookRepository.save(book2);
	}


	@Test
	@Order(2)
	public void findsAllBooks() {

	}


	@Test
	@Order(3)
	public void findsBookByAuthor() {

	}

}
