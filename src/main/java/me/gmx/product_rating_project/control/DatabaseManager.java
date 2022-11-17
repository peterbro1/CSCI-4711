package me.gmx.product_rating_project.control;

import me.gmx.product_rating_project.Main;
import me.gmx.product_rating_project.entity.Product;
import me.gmx.product_rating_project.entity.Review;
import me.gmx.product_rating_project.util.PasswordUtil;
import me.gmx.product_rating_project.entity.User;


import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Not sure what I want to do with this class yet.
 */
public class DatabaseManager {
    private static Connection connection;

    private static final String PATH = "./database.db";

    private final PRSApplication ins;

    private final DecimalFormat df = new DecimalFormat("#.#");
    public DatabaseManager(PRSApplication ins){
        this.ins = ins;
    }

    public PreparedStatement getPreparedStatement(String s){
        try {
            return connection.prepareStatement(s);
        }catch(SQLException e){
            e.printStackTrace();
            Main.logE("Failed to fetch statement " + s);
            return null;
        }
        }

    private void initDatabase(){
            File f = new File(PATH);
            if (f.exists())
                createConnection();
            else
                try {
                    System.out.println("Database not found. Creating one now...");
                    f.createNewFile();
                    createConnection();
                    createTables();
                    hardcodeValues();
                }catch (IOException e){
                    e.printStackTrace();
                    System.exit(1);
                }

    }


    private void createTables(){
        try {
            System.out.println("Creating tables...");
            //Create users table
            PreparedStatement st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS USERS (id integer primary key AUTOINCREMENT, username text UNIQUE, password text, type integer);");
            st.executeUpdate();
            //Create products table
            st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS PRODUCTS (id integer primary key AUTOINCREMENT, name text);");
            st.executeUpdate();
            //Create ratings table
            st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS RATINGS (user REFERENCES USERS, product REFERENCES PRODUCTS , rating integer, comment text, confirmed integer, primary key(user,product));"); //product, user, rating, comment
            st.executeUpdate();
            //Create purchases table
            st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS PURCHASES (user REFERENCES USERS, product REFERENCES PRODUCTS);");
            st.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void hardcodeValues(){
        System.out.println("Hardcoding values...");
        addUser("alice", "Password123", User.UserType.NORMAL);
        addUser("bob", "Sup3rSECURE", User.UserType.NORMAL);
        addUser("charlie", "Password456", User.UserType.ADMIN);
        addProduct("Viking Hat");
        addProduct("Blender");
        addProduct("Crazy Slime");
        addProduct("LG TV 60\"");

        addPurchase(User.loadUserFromName("alice"),Product.loadProductFromName("Viking Hat"));
        addPurchase(User.loadUserFromName("alice"),Product.loadProductFromName("Crazy Slime"));


    }

    private void addPurchase(User u, Product p){
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO PURCHASES VALUES (?,?)");
            st.setInt(1,u.getId());
            st.setInt(2,p.getId());
            st.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void addUser(String username, String password, User.UserType type){
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO USERS VALUES (?,?,?,?)");
            //st.setInt(1,0); //Just dont set, autoincrement ftw
            st.setString(2,username);
            st.setString(3, PasswordUtil.hashPassword(password, username));
            st.setInt(4, type.type);
            st.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void addProduct(String name){
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO PRODUCTS VALUES (?,?)");
            //st.setInt(1,0); //Just dont set, autoincrement ftw
            st.setString(2,name);
            st.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
            System.exit(1);
        }

    }

    public boolean hasUserRated(User u, Product p){
        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM RATINGS WHERE product = ? AND user = ?");
            st.setInt(1,p.getId());
            st.setInt(2,u.getId());
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return true;
            else return false;
        }catch (Exception e){

        }
        return false;
    }

    public boolean addUserReview(Review review, User user, Product product){
        try {//product, user, rating, comment
            PreparedStatement st = connection.prepareStatement("INSERT INTO RATINGS VALUES (?, ?, ?, ?, ?)");
            st.setInt(1,user.getId());
            st.setInt(2,product.getId());
            st.setInt(3,review.getRating());
            st.setString(4,review.getComment());
            st.setInt(5,0);//0 = unconfirmed
            System.out.println(st.toString());
            st.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void confirmUserReview(Review r){
        try{
            PreparedStatement st = connection.prepareStatement("UPDATE RATINGS SET confirmed = 1 WHERE user like ?" +
                    " and product like ?");
            st.setInt(1,r.getUser().getId());
            st.setInt(2,r.getProduct().getId());
            st.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteUserReview(Review r){
        try{
            PreparedStatement st = connection.prepareStatement("DELETE FROM RATINGS WHERE user LIKE ? AND product LIKE ?");
            st.setInt(1,r.getUser().getId());
            st.setInt(2,r.getProduct().getId());
            st.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Review> getUnconfirmedReviews(){
        List<Review> reviews = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM RATINGS WHERE confirmed = 0");
            ResultSet rs = st.executeQuery();
            while (rs.next())
                reviews.add(new Review(
                        User.loadUserFromId(rs.getInt("user")),
                        Product.loadProduct(rs.getInt("product")),
                        rs.getInt("rating"),
                        rs.getString("comment")
                ));

            return reviews;
        }catch (Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public String getAverageRatingByProduct(Product p){
        try{
            int i = 0; int sum = 0;
            PreparedStatement st = connection.prepareStatement("SELECT rating from RATINGS WHERE product = ? AND confirmed = 1");
            st.setInt(1,p.getId());
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                i++;
                sum+= rs.getInt("rating");
            }

            return df.format(sum/i);
        }catch (Exception e){
            //There are no more
            return "Product has not been rated by anyone yet";
        }
    }

    public List<Product> fetchAllProducts(){
        List<Product> products = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM PRODUCTS");
            ResultSet rs = st.executeQuery();
            while (rs.next())
                products.add(new Product(rs.getInt("id"),rs.getString("name")));

            return products;
        }catch (Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Product> fetchAllProductsByUser(User u){
        List<Product> products = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM PRODUCTS");
            ResultSet rs = st.executeQuery();
            while (rs.next())
                products.add(new Product(rs.getInt("id"),rs.getString("name")));

            return products;
        }catch (Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void init(){
        initDatabase();
        System.out.println("Opened database successfully");
    }

    public void createConnection(){
        try {
            System.out.println("Attempting to connect to: " + PATH);
            connection = DriverManager.getConnection("jdbc:sqlite:" + PATH);

        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public String getUserPassword(User user)throws SQLException{
        PreparedStatement st = connection.prepareStatement("SELECT password FROM USERS WHERE id = ?");
        st.setInt(1, user.getId());
        ResultSet rs = st.executeQuery();
        String s = rs.getString(1);
        return s;
    }

    public boolean comparePasswords(User user, String password){
        try {
            return getUserPassword(user).equals(password);
        }catch(SQLException e){
            e.printStackTrace();
            Main.logE("Failed to compare passwords!");
            return false;
        }
    }

    public boolean isNameTaken(String s){
        try{
            PreparedStatement st = connection.prepareStatement("SELECT * FROM USERS WHERE username = ?");
            st.setString(1, s);
            ResultSet rs = st.executeQuery();
            return rs.next();
        }catch(SQLException e){
            e.printStackTrace();
            Main.logE("Failed to check duplicate user name " + s);
            return true;
        }
    }

}
