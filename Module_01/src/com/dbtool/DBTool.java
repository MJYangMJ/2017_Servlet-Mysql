package com.dbtool;

import java.sql.*;

/**
 * Created by yang on 2017/8/29.
 */
public class DBTool {

    public static Connection getConnect(){

        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_for_tianruan?serverTimezone=UTC", "root","");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return conn;

    }

    public static void close(Connection connection){

        try {
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void close(PreparedStatement psmt){

        try {
            psmt.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void close(ResultSet rs){

        try {
            rs.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
