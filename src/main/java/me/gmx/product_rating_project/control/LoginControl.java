package me.gmx.product_rating_project.control;

import me.gmx.product_rating_project.boundary.ui.user.MainMenu;
import me.gmx.product_rating_project.entity.User;

public class LoginControl {

  static String username, password;
  public static boolean verifyAccount(){
    User u = User.tryLoadCredentialedUser(username, password);
    if (u == null)
      return false;
    Controller.currentUser = u;
    Controller.getInstance().db.saveLogin(u.getName());
    MainMenu.open(u.getName());
    return true;
  }

  public static void submit(String username, String password){
    LoginControl.username = username;
    LoginControl.password = password;//hashed btw
  }
}
