package com.uksapps.custombooklist;


public class Book {
    private String names;
    private String author;
    private int images;
    private String publisher;
    private String url;
    public Book(String names, String author, String publisher, int images, String url) {
        this.names = names;
        this.author = author;
        this.images = images;
        this.publisher = publisher;
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}