package me.gmx.product_rating_project;

import javafx.scene.image.Image;
import me.gmx.product_rating_project.auth.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {

    public Image image;
    private String name;
    private int id;
    private short rating;


    public Product(int id, String name, FileInputStream image_file){
        this.id = id;
        this.name = name;
        this.image = new Image(image_file);
    }

    public static Product loadProduct(int id) {
        File f = null;
        try {
            PreparedStatement st = PRSApplication.getInstance().db.getPreparedStatement("SELECT * FROM PRODUCTS WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next())
                throw new NullPointerException("Cannot find product with id: " + id);

            f = new File(PRSApplication.getInstance().getClass().getClassLoader().getResource(rs.getString("img_path")).getPath());

            return new Product(rs.getInt("id"), rs.getString("name")
                    , new FileInputStream(f));
        } catch (SQLException e) {
            e.printStackTrace();
            Main.logE("Failed to fetch product from ID: " + id);
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Main.logE("Could not find file: " + f.getPath());
            System.exit(1);
            return null;
        }

    }

    public static void loadProductsIntoMemory(){
        File f = null;
        try {
            PreparedStatement st = PRSApplication.getInstance().db.getPreparedStatement("SELECT * FROM PRODUCTS");
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                f = new File(PRSApplication.getInstance().getClass().getClassLoader().getResource(rs.getString("img_path")).getPath());
                Product p = new Product(rs.getInt("id"), rs.getString("name")
                        , new FileInputStream(f));
                PRSApplication.getInstance().productList.add(p);
            }

            ;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Main.logE("Could not find file: " + f.getPath());
            System.exit(1);
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
