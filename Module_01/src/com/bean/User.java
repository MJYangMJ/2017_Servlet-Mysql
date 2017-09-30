package com.bean;

/**
 * Created by yang on 2017/8/29.
 */
public class User {

    public String getUsername() {return username;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    private int user_id;
    private String username;
    private String password;

    public User(){}

    public User(int user_id,String username,String password){
        super();
        this.user_id = user_id;
        this.username = username;
        this.password = password;
    }


}
