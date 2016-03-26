package cse530a.model;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private Integer price;
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    
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
