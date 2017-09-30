package com.servlet;

import com.bean.WordBean;
import com.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by yang on 2017/9/5.
 */
public class SearchWordsByPageServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public SearchWordsByPageServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.doGet(request, response);
        doPost(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.doPost(request, response);

        HttpSession session = request.getSession();

        UserService us = new UserService();
        List<WordBean> wordList = new ArrayList<WordBean>();

        wordList = (List<WordBean>)session.getAttribute("wordList");


        String page = request.getParameter("page");

        System.out.println("try to search page:"+page+"...");

        try {

            wordList = us.searchWordsByPage(wordList,page);


        }
        catch (Exception e){

            e.printStackTrace();

        }

        request.setAttribute("wordList",wordList);

        request.getRequestDispatcher("search_words.jsp").forward(request,response);


    }
}
