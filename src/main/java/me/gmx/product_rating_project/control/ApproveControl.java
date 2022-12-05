package me.gmx.product_rating_project.control;

import me.gmx.product_rating_project.entity.Review;
import me.gmx.product_rating_project.entity.User;

public class ApproveControl {

  public static void approve(User u, Review review){
    try {
      Controller.getInstance().db.approve(review);
      Controller.getInstance().gui.openReviewPanel(review);
    }catch (Exception e){
      e.printStackTrace();
    }
  }
  public static void deny(Review r){
    Controller.getInstance().db.deleteUserReview(r);
    try {
      Controller.getInstance().gui.openAdminPanel();
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
