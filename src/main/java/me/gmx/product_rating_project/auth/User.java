package me.gmx.product_rating_project.auth;

import java.util.UUID;

public abstract class User {

    protected UUID id;

    protected String displayName;

    public static User fromId(int id) throws NullPointerException{
        //TODO
    }

    public UUID getId(){
        return id;
    }

    public String getName(){
        return displayName;
    }

}
