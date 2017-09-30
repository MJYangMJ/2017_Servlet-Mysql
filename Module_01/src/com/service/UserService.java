package com.service;

import com.bean.CharacterBean;
import com.bean.ClassesBean;
import com.bean.User;
import com.bean.WordBean;
import com.entity.UserDaoImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yang on 2017/8/29.
 */
public class UserService {

    public User login(String username, String password) throws SQLException{

        UserDaoImpl udi = new UserDaoImpl();

        return udi.findUserByUsernameAndPassword(username, password);

    }

    //注册功能
    public boolean register(User user){

        boolean flag = false;

        UserDaoImpl udi = new UserDaoImpl();
        User userFind = udi.findUserByUsername(user.getUsername());

        if(userFind!=null){
            flag = false;
        }
        else{
            flag = udi.addUser(user);
        }

        return flag;
    }

    public List<User> findAllUser(){

        UserDaoImpl udi = new UserDaoImpl();

        return udi.findAllUsers();

    }

    public boolean deleteUser(String username){

        UserDaoImpl udi = new UserDaoImpl();

        return udi.deleteUser(username);

    }

    public boolean updateUser(User user){

        UserDaoImpl udi = new UserDaoImpl();

        return udi.updateUser(user);
    }

    public User findUserByUsername(String username){

        UserDaoImpl udi = new UserDaoImpl();

        return udi.findUserByUsername(username);
    }

    public List<WordBean> searchWord(String wordInfo, String characterInfo, String classesInfo) {

        UserDaoImpl udi = new UserDaoImpl();

        return udi.searchWord(wordInfo,characterInfo,classesInfo);
    }

    public List<ClassesBean> getAllClasses(){

        UserDaoImpl udi = new UserDaoImpl();

        return udi.findAllClasses();
    }

    public List<CharacterBean> getAllCharacters(){

        UserDaoImpl udi = new UserDaoImpl();

        return udi.findAllCharacter();
    }

    public List<WordBean> searchWordsByPage(List<WordBean> wordList, String page) {

        UserDaoImpl udi = new UserDaoImpl();

        return udi.searchWordByPage(wordList,page);

    }

    public boolean addWord(String wordInfo, String characterInfo, String classesInfo, String paraphrase, String example, boolean transform, boolean case_select){

        UserDaoImpl udi = new UserDaoImpl();

        return udi.addWord(wordInfo,characterInfo,classesInfo,paraphrase,example,transform,case_select);
    }
}
