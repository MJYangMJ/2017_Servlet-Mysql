package com.dao;

import com.bean.WordBean;

import java.util.List;

/**
 * Created by yang on 2017/9/5.
 */
public interface WordDao {

    List<WordBean> findWordByWordInfo(String wordinfo);
    boolean deleteWordByWordInfo(String wordinfo);
    boolean updateWord(WordBean word);

}
