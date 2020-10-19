package sample;

import com.gtranslate.Audio;
import com.gtranslate.Language;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javazoom.jl.decoder.JavaLayerException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javafx.application.Application;

import javax.swing.text.TabableView;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TableView<Word> table;

    @FXML
    private TableView<Word> table2;

    @FXML
    private TableColumn<Word, String> targetColumn2;

    @FXML
    private TableColumn<Word, String> explainColumn2;

    @FXML
    private TableColumn<Word, String> targetColumn;

    @FXML
    private TableColumn<Word, String> explainColumn;

    private ObservableList<Word> WordList;

    private ObservableList<Word> WordSearch;

    private ObservableList<Word> WordData;

    @FXML
    private TextField targetText;

    @FXML
    private TextField explainText;

    @FXML
    private TextField repairText;

    @FXML
    private TextField searchText;

    @FXML
    private TableView<Word> listView;
    @FXML
    private TableColumn<Word, String> targetColumn3;

    @FXML
    private TableColumn<Word, String> explainColumn3;


    @FXML
    private Button hear;
    List<String> list = new ArrayList<String>();
    List<String> list1 = new ArrayList<String>();
    DictionaryManagement DM = new DictionaryManagement();
    List<Word> listWord = new ArrayList<Word>();
    DictionaryManagement DM1 = new DictionaryManagement();
    Dictionary Dict = new Dictionary();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = DM.List("C:\\Users\\PC\\Dictionary-Dohoa\\src\\sample\\target.txt");
        list1 = DM.List("C:\\Users\\PC\\Dictionary-Dohoa\\src\\sample\\explain.txt");
        for (int i=0; i<list.size(); i++) {
            Word newWord = new Word();
            newWord.setWord_explain(list1.get(i));
            newWord.setWord_target(list.get(i));
            listWord.add(newWord);
            Dict.getDictionary(newWord);
        }
        WordList = FXCollections.observableList(listWord);
        WordSearch = FXCollections.observableArrayList(
                new Word("search", "tìm kiếm")
        );
        WordData = FXCollections.observableArrayList(
                new Word("detail","chi tiết")
        );
        targetColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_target"));
        explainColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_explain"));
        table.setItems(WordList);
        targetColumn2.setCellValueFactory(new PropertyValueFactory<Word, String>("word_target"));
        explainColumn2.setCellValueFactory(new PropertyValueFactory<Word, String>("word_explain"));
        table2.setItems(WordSearch);
        targetColumn3.setCellValueFactory(new PropertyValueFactory<Word, String>("word_target"));
        explainColumn3.setCellValueFactory(new PropertyValueFactory<Word, String>("word_explain"));
        listView.setItems(WordData);
    }
    public void DataBase1(ActionEvent e) {
        DM1.insertFromMySQL(Dict);
        Word selected1 = table.getSelectionModel().getSelectedItem();
        for (int i=0; i<Dict.words.size(); i++) {
            if (selected1.getWord_target().equals(Dict.words.get(i).getWord_target()))
                selected1.setWord_explain(Dict.words.get(i).getWord_explain());
        }
        WordData.add(selected1);
    }
    public void DataBase2(ActionEvent e) {
        DM1.insertFromMySQL(Dict);
        Word selected2 = table2.getSelectionModel().getSelectedItem();
        for (int i=0; i<Dict.words.size(); i++) {
            if (selected2.getWord_target().equals(Dict.words.get(i).getWord_target()))
                selected2.setWord_explain(Dict.words.get(i).getWord_explain());
        }
        WordData.add(selected2);
    }
    public void Add(ActionEvent e){
        Word newWord = new Word();
        newWord.setWord_target(targetText.getText());
        newWord.setWord_explain(explainText.getText());
        //listWord.add(newWord);
        WordList.add(newWord);
    }

    public void Delete(ActionEvent e){
        Word selected = table.getSelectionModel().getSelectedItem();
        //listWord.remove(selected);
        WordList.remove(selected);
    }

    public void Repair(ActionEvent e) {
        Word selected = table.getSelectionModel().getSelectedItem();
        selected.setWord_explain(repairText.getText());
        WordList.add(selected);
        WordList.remove(selected);
        Word newWord = new Word(selected.getWord_target(), selected.getWord_explain());
        //listWord.add(newWord);
        WordList.add(newWord);
    }

    public void Search(ActionEvent e) {
        for (int i=0 ; i<WordList.size(); i++) {
            Word searchWord = new Word();
            if (WordList.get(i).getWord_target().startsWith(searchText.getText())) {
                searchWord.setWord_target(WordList.get(i).getWord_target());
                searchWord.setWord_explain(WordList.get(i).getWord_explain());
                WordSearch.add(searchWord);
            }
        }
    }
    public void DeleteSearch(ActionEvent e) {
        for (int i=0; i<WordSearch.size(); i++) {
            WordSearch.remove(WordSearch.get(i));
        }
    }
    public void DeleteDataBase(ActionEvent e) {
        for (int i=0; i<WordData.size(); i++) {
            WordData.remove(WordData.get(i));
        }
    }
    public void Hear() {
        Word selected = table.getSelectionModel().getSelectedItem();
        hear.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                InputStream sound = null;
                try {
                    Audio audio = Audio.getInstance();
                    sound = audio.getAudio(selected.getWord_target(), Language.ENGLISH);
                    audio.play(sound);
                    WordSearch.add(selected);
                } catch (IOException | JavaLayerException ex) {
                    java.util.logging.Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        sound.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
}
