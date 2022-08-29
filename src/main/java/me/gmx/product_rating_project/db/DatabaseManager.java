package me.gmx.product_rating_project.db;

import me.gmx.product_rating_project.Main;
import me.gmx.product_rating_project.PRSApplication;
import me.gmx.product_rating_project.auth.User;

import java.io.File;
import java.io.IOException;
import java.sql.*;

/*
Not sure what I want to do with this class yet.
 */
public class DatabaseManager {
    private static Connection connection;
    private static final String PATH = "./database.db";

    private PRSApplication ins;
    public DatabaseManager(PRSApplication ins){
        this.ins = ins;
    }


    private void initDatabase(){
        try {
            File f = new File(PATH);
            f.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        createConnection();
        try {
            //Create users table
            PreparedStatement st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS USERS (id integer primary key AUTOINCREMENT, username text UNIQUE, password text, type integer);");
            st.executeUpdate();
            //Create products table
            st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS PRODUCTS (id integer primary key AUTOINCREMENT, name text, price REAL, uploaded_by REFERENCES USERS);");
            st.executeUpdate();
            //Create ratings table
            st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS RATINGS (uuid text primary key, rating integer, comment text, user REFERENCES USERS);");
            st.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void init(){
        initDatabase();
        System.out.println("Opened database successfully");
    }

    public void createConnection(){
        try {
            connection = DriverManager.getConnection(PATH);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public String getUserPassword(User user)throws SQLException{
        //TODO
    }

    public boolean comparePasswords(User user, String password){
        try {
            PreparedStatement st = connection.prepareStatement("SELECT password FROM USERS WHERE id = ?");
            st.setString(1, user.getId().toString());
            ResultSet rs = st.executeQuery();
            String s = rs.getString(1);
            return s.equals(password);
        }catch(SQLException e){
            e.printStackTrace();
            Main.logE("Failed to compare passwords!");
            return false;
        }
    }

}