package com.entity;

import com.bean.WordBean;
import com.dao.WordDao;

import java.util.List;

/**
 * Created by yang on 2017/9/5.
 */
public class WordDaoImpl implements WordDao{

    @Override
    public List<WordBean> findWordByWordInfo(String wordinfo) {
        return null;
    }

    @Override
    public boolean deleteWordByWordInfo(String wordinfo) {
        return false;
    }

    @Override
    public boolean updateWord(WordBean word) {
        return false;
    }
}
