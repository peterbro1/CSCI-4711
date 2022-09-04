package me.gmx.product_rating_project.auth;

import me.gmx.product_rating_project.Main;
import me.gmx.product_rating_project.PRSApplication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class User {

    protected int id;

    public UserType type;

    protected String displayName;

    public static User loadUser(int id) throws NullPointerException{
        try{
            PreparedStatement st = PRSApplication.getInstance().db.getPreparedStatement("SELECT * FROM USERS WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()==false)
                throw new NullPointerException("Cannot find user with id: " + id);

            return new User(rs.getInt("id"),rs.getString("username")
                    ,UserType.fromCode(rs.getInt("type")));
        }catch(SQLException e){
            e.printStackTrace();
            Main.logE("Failed to fetch user from ID: " + id);
            return null;
        }
    }

    private User(int id, String username, UserType type){
        this.id = id;
        this.displayName = username;
        this.type = type;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return displayName;
    }

    public enum UserType{
        NORMAL(0),
        ADMIN(1);

        private int type;
        UserType(int i){
            type = i;
        }

        static UserType fromCode(int i){
            for (UserType t : values()){
                if (t.type == i)
                    return t;
            }
            return null;
        }
        int getCode(){ return type; }
    }

}
