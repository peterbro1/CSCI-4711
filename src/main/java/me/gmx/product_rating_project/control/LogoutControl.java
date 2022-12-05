package me.gmx.product_rating_project.control;

public class LogoutControl {
  public static void logout(String username){
    Controller.getInstance().db.saveLogout(username);

    Controller.currentUser = null;
    try {
      GUIController.getInstance().openLoginPanel();
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
