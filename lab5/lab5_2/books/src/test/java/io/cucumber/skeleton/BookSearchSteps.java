package io.cucumber.skeleton;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
 
public class BookSearchSteps {
	Library library = new Library();
	List<Book> result = new ArrayList<>();
    
    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
	public LocalDateTime iso8601Date(String year, String month, String day){
		return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0);
	}
 
	@Given("book with the title {string}, written by {string}, published in {iso8601Date}")
	public void addNewBook(final String title, final String author, final LocalDateTime published) {
		Book book = new Book(title, author, published);
		library.addBook(book);
	}
 
	
	@When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
	public void setSearchParameters(final LocalDateTime from, final LocalDateTime to) {
		result = library.findBooks(from, to);
	}
 
	@Then("{int} books should have been found$")
	public void verifyAmountOfBooksFound(final int booksFound) {
        assertEquals(booksFound, result.size());
	}
 
	@Then("Book {int} should have the title {string}")
	public void verifyBookAtPosition(final int position, final String title) {
        assertEquals(title, result.get(position - 1).getTitle());
	}
}