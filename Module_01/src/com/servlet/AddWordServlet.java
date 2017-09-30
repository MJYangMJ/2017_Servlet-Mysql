package com.servlet;

import com.bean.CharacterBean;
import com.bean.ClassesBean;
import com.bean.WordBean;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang on 2017/9/7.
 */
public class AddWordServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public AddWordServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.doGet(req, resp);
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.doPost(request, response);

        UserService us = new UserService();
        HttpSession session = request.getSession();

        List<CharacterBean> characterList = new ArrayList<CharacterBean>();
        List<ClassesBean> classesList = new ArrayList<ClassesBean>();

        if(request.getParameter("time").equals("1"))
        {
            try {
                characterList = us.getAllCharacters();
                classesList = us.getAllClasses();

                session.setAttribute("characterList",characterList);
                session.setAttribute("classesList",classesList);
                request.getRequestDispatcher("add_word.jsp").forward(request,response);
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        else {
            boolean flag = false;

            String wordInfo = request.getParameter("wordInfo");
            String characterInfo = request.getParameter("characterInfo");
            String classesInfo = request.getParameter("classesInfo");
            String paraphrase = request.getParameter("paraphrase");
            String example = request.getParameter("example");
//            boolean transform = Boolean.getBoolean(request.getParameter("transform"));
//            boolean case_select = Boolean.getBoolean(request.getParameter("case_select"));
            String trans = request.getParameter("transform");
            String case_se = request.getParameter("case_select");

            System.out.println("wordInfo:"+wordInfo+" characterInfo:"+characterInfo+" classesInfo:"+classesInfo+
            " paraphrase:"+paraphrase+" example:"+example+" transform:"+trans+" case_select:"+case_se+"...");

            flag = us.addWord(wordInfo,characterInfo,classesInfo,paraphrase,example,true,true);

            if (!flag) {
                String msg_addword = "Insert Failed ,Try Again";
                request.setAttribute("msg_addword", msg_addword);
            }
            else {
                String msg_addword = "Insert Success!!";
                request.setAttribute("msg_addword",msg_addword);
            }

            request.getRequestDispatcher("add_word.jsp").forward(request, response);
        }
    }
}
