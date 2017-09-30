package com.bean;

/**
 * Created by yang on 2017/9/5.
 */
public class WordBean {

        private int wordId;

    public String getWordinfo() {
        return wordinfo;
    }

    public void setWordinfo(String wordinfo) {
        this.wordinfo = wordinfo;
    }

    private String wordinfo;
        private int characterId;
        private int classesId;
        private String paraphrase;
        private String example;
        private int frequency;

        public int getWordId() {
            return wordId;
        }

        public void setWordId(int wordId) {
            this.wordId = wordId;
        }



        public int getCharacterId() {
            return characterId;
        }

        public void setCharacterId(int characterId) {
            this.characterId = characterId;
        }

        public int getClassesId() {
            return classesId;
        }

        public void setClassesId(int classesId) { this.classesId = classesId;}

        public String getParaphrase() {
            return paraphrase;
        }

        public void setParaphrase(String paraphrase) {
            this.paraphrase = paraphrase;
        }

        public String getExample() { return example;}

        public void setExample(String example) { this.example = example;}

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }

}
