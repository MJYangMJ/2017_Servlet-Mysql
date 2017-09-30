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
public class GetUserInfoServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        System.out.println("go to doPost...");
        //在doGet方法中调用doPost
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");

        if(username.equals("null")){
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
        else {
            System.out.println("try to find user..." + username);
            UserService us = new UserService();
            User user = null;
            try {
                user = us.findUserByUsername(username);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            request.setAttribute("user_old", user);

            request.getRequestDispatcher("modify_info.jsp").forward(request, response);
        }
    }
}
