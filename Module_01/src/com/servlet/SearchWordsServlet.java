package com.servlet;

import com.bean.CharacterBean;
import com.bean.ClassesBean;
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
public class SearchWordsServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public SearchWordsServlet() {
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

        UserService us = new UserService();
        List<WordBean> wordList = new ArrayList<WordBean>();
        List<CharacterBean> characterList = new ArrayList<CharacterBean>();
        List<ClassesBean> classesList = new ArrayList<ClassesBean>();

        String wordInfo = request.getParameter("wordInfo");
        String characterInfo = request.getParameter("characterInfo");
        String classesInfo = request.getParameter("classesInfo");
        String page = request.getParameter("page");
//        boolean transform = false;
//        int transform = Integer.parseInt(request.getParameter("transform"));
        String transform = request.getParameter("transform");
        if(wordInfo.equals("*"))    wordInfo="";

        System.out.println("try to search wordInfo:"+wordInfo+" characterInfo:"+characterInfo+
                " classesInfo:"+classesInfo+" transform:"+transform+" page:"+page+"...");

        try {

//            wordList = us.searchWord(wordInfo,characterInfo,classesInfo,page);
            wordList = us.searchWord(wordInfo,characterInfo,classesInfo);
            characterList = us.getAllCharacters();
            classesList = us.getAllClasses();

        }
        catch (Exception e){

            e.printStackTrace();

        }

        HttpSession session = request.getSession();

        session.setAttribute("wordList",wordList);
        session.setAttribute("pages",wordList.size());
        session.setAttribute("characterList",characterList);
        session.setAttribute("classesList",classesList);

        request.setAttribute("page",page);

        request.getRequestDispatcher("SearchWordsByPageServlet").forward(request,response);


    }
}
