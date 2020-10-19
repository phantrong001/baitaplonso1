package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class Dictionary {
    List<Word> words = new ArrayList<Word>();
    Dictionary(){

    }
    public void getDictionary(Word rs){
        words.add(rs);
    }
}
