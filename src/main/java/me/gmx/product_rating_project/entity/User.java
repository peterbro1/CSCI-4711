package me.gmx.product_rating_project.entity;

import me.gmx.product_rating_project.Main;
import me.gmx.product_rating_project.control.PRSApplication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    protected int id;

    public UserType type;

    protected String displayName;

    public static User loadUserFromId(int id) throws NullPointerException{
        try{
            PreparedStatement st = PRSApplication.getInstance().db.getPreparedStatement("SELECT * FROM USERS WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next())
                throw new NullPointerException("Cannot find user with id: " + id);

            return new User(rs.getInt("id"),rs.getString("username")
                    ,UserType.fromCode(rs.getInt("type")));
        }catch(SQLException e){
            e.printStackTrace();
            Main.logE("Failed to fetch user from ID: " + id);
            return null;
        }
    }


    //Insecure?
    public static User loadUserFromName(String name) throws NullPointerException{
        try{
            PreparedStatement st = PRSApplication.getInstance().db.getPreparedStatement("SELECT * FROM USERS WHERE username = ?");
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (!rs.next())
                throw new NullPointerException("Cannot find user with name: " + name);

            return new User(rs.getInt("id"),rs.getString("username")
                    ,UserType.fromCode(rs.getInt("type")));
        }catch(SQLException e){
            e.printStackTrace();
            Main.logE("Failed to fetch user from name: " + name);
            return null;
        }
    }

    public static User tryLoadCredentialedUser(String name, String password) throws NullPointerException{
        try{
            PreparedStatement st = PRSApplication.getInstance().db.getPreparedStatement("SELECT * FROM USERS WHERE username = ? AND password = ?");
            st.setString(1, name);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (!rs.next())
                throw new NullPointerException("Cannot find user with name: " + name);

            return new User(rs.getInt("id"),rs.getString("username")
                    ,UserType.fromCode(rs.getInt("type")));
        }catch(SQLException e){
            e.printStackTrace();
            Main.logE("Failed to fetch user from name: " + name);
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

        public int type;
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
