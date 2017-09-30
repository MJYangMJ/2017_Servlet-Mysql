package com.servlet;

import com.bean.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;


/**
 * Created by yang on 2017/9/5.
 */
public class ModifyInfoServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        System.out.println("go to doGet...");
        //在doGet方法中调用doPost
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        UserService us = new UserService();

        User user_old = us.findUserByUsername(request.getParameter("username"));
        int user_id = user_old.getUser_id();
        String username_old = user_old.getUsername();
        String password_old = user_old.getPassword();

        User user_new = new User();
        user_new.setUser_id(user_id);
        user_new.setUsername(request.getParameter("username_new"));
        user_new.setPassword(request.getParameter("password_new"));
        System.out.println("username_new"+user_new.getUsername());
        System.out.println("password_new"+user_new.getPassword());

        boolean flag = false;

        try{
            flag = us.updateUser(user_new);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        if(flag){
            response.sendRedirect("login.jsp");
        }
        else {
            System.out.println("Current username is already exist!,Try another name to update.UPDATE FAILED");
            request.setAttribute("msg2", "Current username is already exist!,Try another name to update.UPDATE FAILED");

            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
