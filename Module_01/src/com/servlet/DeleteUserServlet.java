package com.servlet;

/**
 * Created by yang on 2017/9/5.
 */
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.UserService;

/**
 * Servlet implementation class DeleteUserServlet
 */
public class DeleteUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
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

        String username = request.getParameter("username");
        System.out.println("try to delete user..."+username);
        UserService us = new UserService();
        try {
            us.deleteUser(username);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        request.getRequestDispatcher("FindAllUserServlet").forward(request, response);

    }

}
