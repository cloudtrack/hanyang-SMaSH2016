package info.androidhive.customlistviewvolley.model;

/**
 * Created by nayunhwan on 16. 1. 23..
 */
public class Book {
    String thumnail;
    String title;
    String author;
    String price;
    String category;
    String date;

    public Book(){

    }
    public Book(String thumnail, String author, String title, String price, String category, String date){
        this.thumnail = thumnail;
        this.title = title;
        this.author = author;
        this.price = price;
        this.category = category;
        this.date = date;
    }

    public String getThumnail(){
        return thumnail;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public String getPrice(){
        return price;
    }
    public String getCategory(){
        return category;
    }
    public String getDate(){
        return date;
    }

    public void setThumnail(String thumnail){
        this.thumnail = thumnail;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public void setPrice(String price){
        this.price = price;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setDate(String date){
        this.date = date;
    }

}
