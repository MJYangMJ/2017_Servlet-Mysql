package com.test;

import com.bean.User;
import com.entity.UserDaoImpl;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by yang on 2017/8/28.
 */
public class Test1 {

    public static void main(String[] args) throws Exception{

        User user = new User();

        UserDaoImpl test1 = new UserDaoImpl();

//        user = test1.findUser("Yang","123456");

        System.out.println(user.getUsername()+"  "+user.getPassword());

    }
}
