package MyProject.bookStore.entities;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private int id;
    private static int autoID;
    private String title;
    private String genre;
    private double price;
    private double rating; // Danh gia trung binh
    private int stock;
    private List<Double> ratings; // Danh sach danh gia tu khach hang
    private int condition;

    public Book(String title, String genre, double price, double rating, int stock, int condition) {
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.stock = stock;
        this.condition = condition;
        this.rating = 0.0;
        this.ratings = new ArrayList<>();
        this.id = ++autoID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // them danh gia va cap nhap danh gia trung binh
    public void addRating(double newRating) {
        ratings.add(newRating);
        updateAverageRating();
    }

    // tinh diem danh gia trung binh
    private void updateAverageRating() {
        double sum = 0;
        for (double r : ratings) {
            sum += r;
        }
        this.rating = sum / ratings.size();
    }

    @Override
    public String toString() {
        return "Book{" +
                "Id=" + id +
                ", Title='" + title + '\'' +
                ", Genre='" + genre + '\'' +
                ", Price=" + price +
                ", Rating=" + rating +
                ", Stock=" + stock +
                ", Condition=" + condition +
                '}';
    }
}




