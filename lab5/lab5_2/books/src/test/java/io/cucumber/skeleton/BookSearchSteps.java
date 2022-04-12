package io.cucumber.skeleton;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
 
public class BookSearchSteps {
	Library library = new Library();
	List<Book> result = new ArrayList<>();
	List<Book> resultOfAuthor = new ArrayList<>();


    
    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
	public LocalDateTime iso8601Date(String year, String month, String day){
		return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0);
	}
	
	private Date localDateTimeToDate(LocalDateTime dateToConvert) {
		return java.sql.Timestamp.valueOf(dateToConvert);
	}

	@Given("a book with the title {string}, written by {string}, published in {iso8601Date}")
	public void addNewBook(final String title, final String author, final LocalDateTime published) {
		Book book = new Book(title, author, localDateTimeToDate(published));
		library.addBook(book);
	}

	@Given("another book with the title {string}, written by {string}, published in {iso8601Date}")
	public void anotherBookWithTheTitleWrittenByPublishedIn(String title, String author, LocalDateTime published) {
		addNewBook(title, author, published);
	}
	
	@When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
	public void setSearchParameters(final LocalDateTime from, final LocalDateTime to) {
		result = library.findBooks(localDateTimeToDate(from), localDateTimeToDate(to));
	}
 
	@Then("{int} books should have been found")
	public void verifyAmountOfBooksFound(final int booksFound) {
        assertEquals(booksFound, result.size());
	}
 
	@Then("Book {int} should have the title {string}")
	public void verifyBookAtPosition(final int position, final String title) {
        assertEquals(title, result.get(position - 1).getTitle());
	}


	@Given("I have the following books in the store by map")
	public void haveBooksInTheStoreByMap(DataTable table) {
		
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		
		for (Map<String, String> columns : rows) {
			library.addBook(new Book(columns.get("title"), columns.get("author")));
		}
	}

	@When("I search for books by author {string}")
	public void setSearchForBooksByAuthor(final String author) {
		resultOfAuthor = library.findBooksByAuthor(author);
	}

	@Then("I find {int} books")
	public void verifyAmountOfBooksOfAuthorFound(final int booksFound) {
        assertEquals(booksFound, resultOfAuthor.size());
	}
}