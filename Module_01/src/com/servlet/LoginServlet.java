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
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        System.out.println("fo to doGet...");
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession();

//        if(session.getAttribute("username").equals(request.getAttribute("username"))){
//        在删除个人信息后，返回主页保持原来的登录状态
//        }

        System.out.println("try to login...");
        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username：" + username);
        System.out.println("password：" + password);

        //访问数据库
        UserService us = new UserService();
        User user = null;
        try {
            user = us.login(username, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (user == null) {
            System.out.println("no such user.");

            request.setAttribute("msg1", "Wrong Username Or Password.Try Again!");

            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            //接收时间信息
            String autoLogin = request.getParameter("autoLogin");
            System.out.println("接收到的自动登录的时间是：" + autoLogin);
            if (autoLogin != null) {//避免空指针异常
                //步骤1：创建Cookie对象，把用户名和密码存入Cookie
                Cookie cookie1 = new Cookie("username", username);
                Cookie cookie2 = new Cookie("password", password);
                //步骤2：设置Cookie的有效时间
                int timeLengthInt = Integer.parseInt(autoLogin);
                cookie1.setMaxAge(timeLengthInt * 60 * 60 * 24);
                cookie2.setMaxAge(timeLengthInt * 60 * 60 * 24);
                //步骤3：将Cookie信息发送给客户端浏览器
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }


            session.setAttribute("username",username);


            System.out.println("user exist!");

            request.setAttribute("username", username);
            request.setAttribute("password",password);
            request.setAttribute("user_id",user.getUser_id());

            request.getRequestDispatcher("index.jsp").forward(request,response);


        }

    }
}