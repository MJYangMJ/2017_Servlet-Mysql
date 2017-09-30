package com.servlet;

import com.bean.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
/**
 * Created by yang on 2017/9/4.
 */
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        UserService us = new UserService();
        request.setCharacterEncoding("utf-8");
        User user = new User();

        //获取页面中的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");

        user.setUsername(username);
        user.setPassword(password);

        System.out.println("用户名："+username);
        System.out.println("密码："+password);
        System.out.println("确认密码："+password2);

        //调用注册业务
        boolean flag = false;
        try {
            flag = us.register(user);
            System.out.println("flag="+flag);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(flag==true){
            response.sendRedirect("login.jsp");
        }else{
            System.out.println("Current User is already exist!,Try another name to register");
            request.setAttribute("msg", "Current User is already exist!,Try another name to register");
            //response.sendRedirect("register.jsp");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

}