package com.dao;

import com.bean.User;
import com.bean.WordBean;

import java.util.List;

/**
 * Created by yang on 2017/8/29.
 */
public interface UserDao {

    User findUserByUsernameAndPassword(String username, String password);

    User findUserByUsername(String username);

    List<User> findAllUsers();

    boolean deleteUser(String username);

    boolean addUser(User user);

    boolean updateUser(User user);

    public List<WordBean> searchWord(String wordInfo, String characterInfo, String classesInfo);

    List<WordBean> searchWordByPage(List<WordBean> wordList, String page);

    boolean addWord(String wordInfo,String characterInfo,String classesInfo,String paraphrase,String example,boolean transform,boolean case_select);
}
