package me.gmx.product_rating_project.entity;

public class Review {
    private int rating;
    private String comment;

    private User u;

    private Product p;

    public Review(User u, Product p, int rating, String comment){
        this.u = u;
        this.p = p;
        this.rating = rating;
        this.comment = comment;
    }

    public String getComment(){
        return comment;
    }
    public int getRating(){
        return rating;
    }
    public User getUser(){
        return u;
    }
    public Product getProduct(){
        return p;
    }

}
