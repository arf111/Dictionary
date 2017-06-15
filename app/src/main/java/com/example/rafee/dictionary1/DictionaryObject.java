package com.example.rafee.dictionary1;

/**
 * Created by Rafee on 1/15/2017.
 */

public class DictionaryObject {

    private String word;
    private String meaning;
    private String synonym,antonym,sentence;

    public DictionaryObject(String word, String meaning,String synonym,String antonym,String sentence) {
        this.word = word;
        this.meaning = meaning;
        this.antonym = antonym;
        this.sentence = sentence;
        this.synonym = synonym;
    }

    public String getWord() {
        return word;
    }

    public String getSynonym() {
        return synonym;
    }

    public String getAntonym() {
        return antonym;
    }

    public String getSentence() {
        return sentence;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
