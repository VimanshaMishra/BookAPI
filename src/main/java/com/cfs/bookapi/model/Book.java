package com.cfs.bookapi.model;

public class Book {
    private long Id ;
    private String BookName;
    private String Author;
    private double Price;

    public Book(){

    }

    public long getId() {
        return Id;
    }

    public String getBookName() {
        return BookName;
    }

    public void setId(long id) {
        Id = id;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public Book(long id, String bookName, String author, double price) {
        Id = id;
        BookName = bookName;
        Author = author;
        Price = price;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getAuthor() {
        return Author;
    }

    public double getPrice() {
        return Price;
    }
}
