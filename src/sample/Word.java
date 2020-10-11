package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Word {

    String word_target;
    String word_explain;

    public Word() {

    }

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    //setWord_target
    public void setWord_target(String n) {
        this.word_target = n;
    }

    //setWord_explain
    public void setWord_explain(String n) {
        this.word_explain = n;
    }

    //getWord_target
    public  String getWord_target() {
        return this.word_target;
    }

    //getWord_explain
    public String getWord_explain() {
        return this.word_explain;
    }

}
