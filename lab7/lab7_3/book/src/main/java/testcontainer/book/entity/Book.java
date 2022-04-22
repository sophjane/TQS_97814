package testcontainer.book.entity;

import javax.persistence.*;

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="title")
    private String title;

    @Column(name="author")
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book() {};

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle( ) {
       return this.title;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return this.author;
    }

}
