package me.gmx.product_rating_project.control;

import me.gmx.product_rating_project.entity.Product;
import me.gmx.product_rating_project.entity.Review;
import me.gmx.product_rating_project.entity.User;

public class RateControl {

  public static void submit(Review review){
    Controller.getInstance().db.save(review);
  }

  public static void rate(User u, Product product){
    try {
      Controller.getInstance().gui.createReviewPage(product);
    }catch (Exception e ){
      e.printStackTrace();
    }
  }
}
