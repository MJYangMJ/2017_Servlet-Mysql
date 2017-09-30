package com.entity;

import com.bean.CharacterBean;
import com.bean.ClassesBean;
import com.bean.User;
import com.bean.WordBean;
import com.dao.UserDao;
import com.dbtool.DBTool;
import com.mysql.cj.core.util.EscapeTokenizer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yang on 2017/8/29.
 */
public class UserDaoImpl implements UserDao {

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {

        User user = null;
        Connection conn = DBTool.getConnect();
        PreparedStatement psmt = null;
        ResultSet rs = null;

        String sql = "select * from user where username=? and password=?";

        try {
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString(1,username);
            psmt.setString(2,password);


            rs = psmt.executeQuery();
            while(rs.next()){
                user = new User(rs.getInt("user_id"),username,password);
            }

            System.out.println(user.getUser_id()+"  "+user.getUsername()+"  "+user.getPassword());
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        DBTool.close(rs);
        DBTool.close(psmt);
        DBTool.close(conn);


        return user;
    }

    @Override
    public User findUserByUsername(String username) {

        Connection conn = DBTool.getConnect();
        User user = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        String sql = "select * from user where username=?";
        try {
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString(1, username);
            rs = psmt.executeQuery();

            if (rs.next()) {
                String temp = rs.getString("username");
//                System.out.println("temp="+temp);
                user = new User(rs.getInt("user_id"),username,rs.getString("password"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();

        }

        DBTool.close(rs);
        DBTool.close(psmt);
        DBTool.close(conn);
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        System.out.println("finding all users...");

        List<User> users = new ArrayList<>();

        Connection conn = DBTool.getConnect();
        PreparedStatement psmt = null;
        ResultSet rs = null;

        String sql = "select * from user";

        try {
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            rs = (ResultSet) psmt.executeQuery();
            while(rs.next()){
                int user_id = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");

                User user = new User(user_id,username,password);
                users.add(user);
//                System.out.println(users.size());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        DBTool.close(rs);
        DBTool.close(psmt);
        DBTool.close(conn);

        return users;
    }

    @Override
    public boolean deleteUser(String username) {
        boolean flag = false;
        Connection conn =  DBTool.getConnect();
        PreparedStatement psmt = null;

        String sql = "delete from user where username=?";
        try {
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString(1, username);
            int num = psmt.executeUpdate();

            if(num>0){
                flag = true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        DBTool.close(psmt);
        DBTool.close(conn);

        return flag;
    }

    @Override
    public boolean addUser(User user){
        // TODO Auto-generated method stub
        boolean flag = false;

        Connection conn =  DBTool.getConnect();
        PreparedStatement psmt = null;
        int num = 0;

        String sql = "insert into user(username,password) values(?,?)";

        try {
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString(1,user.getUsername() );
            psmt.setString(2,user.getPassword());
            num = psmt.executeUpdate();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        if(num>0){
            flag = true;
        }

        DBTool.close(psmt);
        DBTool.close(conn);

        return flag;
    }

    @Override
    public boolean updateUser(User user) {
        boolean flag = false;

        Connection conn =  DBTool.getConnect();
        PreparedStatement psmt = null;
        ResultSet rs = null;
        int num = 0;
        String sql;

        sql = "select * from user where username=?";

        try {
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString(1, user.getUsername());
            rs = psmt.executeQuery();

            if (rs.next()) {
                num++;
            }

            if(num>1){
                System.out.println("username input is invalid!");
                return flag;
            }
        }
        catch (SQLException e){
            e.printStackTrace();

        }

        sql = "update user set username=?,password=? where user_id=?";
        num=0;

        try {
            psmt = (PreparedStatement) conn.prepareStatement(sql);
            psmt.setString(1,user.getUsername() );
            psmt.setString(2,user.getPassword());
            psmt.setInt(3,user.getUser_id());

            num = psmt.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        if(num>0){
            flag = true;
        }

        DBTool.close(rs);
        DBTool.close(psmt);
        DBTool.close(conn);

        return flag;

    }

    private String findWordModeCheck(String chara,String cla,String wo){

        String sql = "";

        if(chara.equals("*")&&cla.equals("*")&&wo.equals(""))
        {
            sql = "SELECT * FROM WORD";
            return sql;
        }
        else if(chara.equals("*")&&cla.equals("*")&&!wo.equals(""))
        {
            sql = "SELECT * FROM WORD WHERE WORDINFO='"+wo+"'";
            return sql;
        }
        else if(chara.equals("*")&&!cla.equals("*")&&wo.equals(""))
        {
            sql = "SELECT * FROM WORD WHERE CLASSESID=(SELECT CLASSESID FROM CLASSES WHERE CLASSESINFO='"+cla+"')";
            return sql;
        }
        else if(chara.equals("*")&&!cla.equals("*")&&!wo.equals(""))
        {
            sql = "SELECT * FROM WORD WHERE WORDINFO='"+wo+"' AND CLASSESID=(SELECT CLASSESID FROM CLASSES WHERE CLASSESINFO='"+cla+"')";
            return sql;
        }
        else if(!chara.equals("*")&&cla.equals("*")&&wo.equals(""))
        {
            sql = "SELECT * FROM WORD WHERE CHARACTERID=(SELECT CHARACTERID FROM CHARACTERS WHERE CHARACTERINFO='"+chara+"')";
            return sql;
        }
        else if(!chara.equals("*")&&cla.equals("*")&&!wo.equals(""))
        {
            sql = "SELECT * FROM WORD WHERE WORDINFO='"+wo+"' AND CHARACTERID=(SELECT CHARACTERID FROM CHARACTERS WHERE CHARACTERINFO='"+chara+"')";
            return sql;
        }
        else if(!chara.equals("*")&&!cla.equals("*")&&wo.equals(""))
        {
            sql = "SELECT * FROM WORD WHERE CHARACTERID=(SELECT CHARACTERID FROM CHARACTERS WHERE CHARACTERINFO='"+chara+"') AND CLASSESID=(SELECT CLASSESID FROM CLASSES WHERE CLASSESINFO='"+cla+"')";
            return sql;
        }
        else if(!chara.equals("*")&&!cla.equals("*")&&!wo.equals(""))
        {
            sql = "SELECT * FROM WORD WHERE WORDINFO='"+wo+"' AND CHARACTERID=(SELECT CHARACTERID FROM CHARACTERS WHERE CHARACTERINFO='"+chara+"') AND CLASSESID=(SELECT CLASSESID FROM CLASSES WHERE CLASSESINFO='"+cla+"')";
            return sql;
        }
        else return null;
    }

    private void frequencyIncrease(long wid){
        System.out.println("frequency has to increase");
        System.out.println("wordId="+wid+"++");

        Connection conn =  DBTool.getConnect();
        PreparedStatement psmt = null;

        String sql = "update word set frequency=frequency+1 where wordid='"+ wid + "'";
        int i = 0;

        try {
            psmt = conn.prepareStatement(sql);

            i = psmt.executeUpdate();
            System.out.println("update resutl: " + i);


        } catch (Exception e) {
            e.printStackTrace();
        }

        DBTool.close(psmt);
        DBTool.close(conn);

    }

    public List<CharacterBean> findAllCharacter() {
        System.out.println("get all character info ...");

        List<CharacterBean> characterList = new ArrayList<CharacterBean>();
        CharacterBean character;

        Connection conn = DBTool.getConnect();
        PreparedStatement psmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM characters";

        try {
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                character = new CharacterBean();
//				character.setCharacterId(rs.getInt("characterId"));
                character.setCharacterInfo(rs.getString("characterInfo"));
                characterList.add(character);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DBTool.close(rs);
        DBTool.close(psmt);
        DBTool.close(conn);

        return characterList;
    }

    public List<ClassesBean> findAllClasses() {
        System.out.println("get all classes info ...");

        List<ClassesBean> classesList = new ArrayList<ClassesBean>();
        ClassesBean classes;

        Connection conn = DBTool.getConnect();
        PreparedStatement psmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM classes";

        try {
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while (rs.next()) {
                classes = new ClassesBean();
//				character.setCharacterId(rs.getInt("characterId"));
                classes.setClassesInfo(rs.getString("classesInfo"));
                classesList.add(classes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        DBTool.close(rs);
        DBTool.close(psmt);
        DBTool.close(conn);

        return classesList;
    }

    public List<WordBean> searchWord(String wordInfo, String characterInfo, String classesInfo) {

        List<WordBean> wordList = new ArrayList<>();
        WordBean word;

        Connection conn =  DBTool.getConnect();
        PreparedStatement psmt = null;
        ResultSet rs = null;

        String sql = findWordModeCheck(characterInfo,classesInfo,wordInfo);

        try {
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                word = new WordBean();
                word.setWordId(rs.getInt("wordId"));
                word.setWordinfo(rs.getString("wordInfo"));
//                word.setCharacterId(rs.getInt("characterId"));
//                word.setClassesId(rs.getInt("classesId"));
                word.setParaphrase(rs.getString("paraphrase"));
                word.setExample(rs.getString("example"));
                word.setFrequency(rs.getInt("frequency"));
                frequencyIncrease(word.getWordId());
                wordList.add(word);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        DBTool.close(rs);
        DBTool.close(psmt);
        DBTool.close(conn);

        return wordList;
    }

    @Override
    public List<WordBean> searchWordByPage(List<WordBean> wordList, String page) {

        List<WordBean> wordList_new = new ArrayList<>();

//        System.out.println("original wordList_new size:"+ wordList_new.size());
        int begin = (Integer.parseInt(page)-1)*10;
        int end = Integer.parseInt(page)*10;

        if(end>wordList.size())
            end=wordList.size();

        System.out.println("now is finding page:"+page+" begin:"+begin+" end:"+end+"...");
        for (int i=begin;i<end;i++){
            wordList_new.add(wordList.get(i));
        }

        return wordList_new;
    }

    @Override
    public boolean addWord(String wordInfo, String characterInfo, String classesInfo, String paraphrase, String example, boolean transform, boolean case_select) {

        boolean flag = false;
        int i = 0;

        Connection conn =  DBTool.getConnect();
        PreparedStatement psmt = null;
        ResultSet rs = null;


        String sql = "insert into word(wordid,wordinfo,characterid,classesid,paraphrase,example,frequency)" +
                "VALUES(1,?,(SELECT CHARACTERID FROM CHARACTERS WHERE CHARACTERINFO=?)," +
                "(SELECT CLASSESID FROM CLASSES WHERE CLASSESINFO=?),?,?,1)";

        try {

            psmt = conn.prepareStatement(sql);

            psmt.setString(2,characterInfo);
            psmt.setString(3,classesInfo);
            psmt.setString(4,paraphrase);
            psmt.setString(5,example);

            if(!transform) {
                if(!case_select) {
                    psmt.setString(1,wordInfo.toUpperCase());
                }
                else{
                    psmt.setString(1,wordInfo.toLowerCase());
                }
            }
            else {
                psmt.setString(1,wordInfo);
            }

            System.out.println("try to "+sql);
            i = psmt.executeUpdate();
            if(i>0)
                flag = true;
            System.out.println("insert result: " + i);


        }
        catch (Exception e) {

            e.printStackTrace();
        }

        DBTool.close(psmt);
        DBTool.close(conn);

        return flag;
    }
}
