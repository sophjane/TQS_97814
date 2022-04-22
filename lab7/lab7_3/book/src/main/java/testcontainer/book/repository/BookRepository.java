package testcontainer.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import testcontainer.book.entity.Book;

import java.util.List;

@Repository
public interface BookRepository  extends JpaRepository<Book,Long>{
    List<Book> findByAuthor(String author);
    List<Book> findAll();
    Book findByTitle(String title);

}