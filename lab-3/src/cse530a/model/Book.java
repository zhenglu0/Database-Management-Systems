package cse530a.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "books")
@SequenceGenerator(name = "BOOK_SEQ", sequenceName = "books_book_id_seq")
public class Book implements Serializable {
	private static final long serialVersionUID = -7387200517042216757L;
	
	private Long id;
    private String isbn;
    private String title;
    private String author;
    private Integer price;
    
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "BOOK_SEQ")
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name = "isbn")
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    @Column(name = "title")
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    
    @Transient
    public String getFormattedPrice() {
        int dollars = price / 100;
        int cents = price % 100;
        
        if (cents < 10) {
            return "$" + dollars + ".0" + cents;            
        } else {
            return "$" + dollars + "." + cents;
        }
    }
}
