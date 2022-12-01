package me.gmx.product_rating_project.entity;

import me.gmx.product_rating_project.Main;
import me.gmx.product_rating_project.control.Controller;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {

    private String name;
    private int id;
    private short rating;


    public Product(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static Product loadProduct(int id) {
        try {
            PreparedStatement st = Controller.getInstance().db.getPreparedStatement("SELECT * FROM PRODUCTS WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next())
                throw new NullPointerException("Cannot find product with id: " + id);

            return new Product(rs.getInt("id"), rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
            Main.logE("Failed to fetch product from ID: " + id);
            return null;
        }

    }

    public static Product loadProductFromName(String s) {
        try {
            PreparedStatement st = Controller.getInstance().db.getPreparedStatement("SELECT * FROM PRODUCTS WHERE name = ?");
            st.setString(1, s);
            ResultSet rs = st.executeQuery();
            if (!rs.next())
                throw new NullPointerException("Cannot find product with name: " + s);

            return new Product(rs.getInt("id"), rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
            Main.logE("Failed to fetch product from name: " + s);
            return null;
        }

    }



    public static void loadProductsIntoMemory(){
        File f = null;
        try {
            PreparedStatement st = Controller.getInstance().db.getPreparedStatement("SELECT * FROM PRODUCTS");
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                f = new File(Main.class.getResource(rs.getString("img_path")).getPath());
                Product p = new Product(rs.getInt("id"), rs.getString("name"));
                Controller.getInstance().productList.add(p);
            }

            ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getName(){
        return name;
    }

    public short getRating(){
        return rating;
    }

    public int getId(){
        return id;
    }

}
