package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

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

    @FXML
    private TextField targetText;

    @FXML
    private TextField explainText;

    @FXML
    private TextField repairText;

    @FXML
    private TextField searchText;

    @FXML
    private HBox hBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WordList = FXCollections.observableArrayList(
                new Word("english","Tiếng Anh"),
                new Word("vietnamese", "Việt Nam"),
                new Word("time"," thời gian"),
                new Word("year"," năm"),
                new Word("people"," con người"),
                new Word("way"," con đường"),
                new Word("day"," ngày"),
                new Word("man"," đàn ông"),
                new Word("thing"," sự vật"),
                new Word("woman"," phụ nữ"),
                new Word("life"," cuộc sống"),
                new Word("child"," con cái"),
                new Word("world"," thế giới"),
                new Word("school"," trường học"),
                new Word("state"," trạng thái"),
                new Word("family"," gia đình"),
                new Word("student"," học sinh"),
                new Word("group"," nhóm"),
                new Word("country"," đất nước"),
                new Word("problem"," vấn đề"),
                new Word("hand"," bàn tay"),
                new Word("part"," bộ phận"),
                new Word("place"," vị trí"),
                new Word("case"," trường hợp"),
                new Word("week"," tuần"),
                new Word("company"," công ty"),
                new Word("system"," hệ thống"),
                new Word("program"," chương trình"),
                new Word("question"," câu hỏi"),
                new Word("work"," công việc"),
                new Word("government"," chính phủ"),
                new Word("number"," con số")
        );
        WordSearch = FXCollections.observableArrayList(
                new Word("example: test", "thử")
        );
        targetColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_target"));
        explainColumn.setCellValueFactory(new PropertyValueFactory<Word, String>("word_explain"));
        table.setItems(WordList);
        targetColumn2.setCellValueFactory(new PropertyValueFactory<Word, String>("word_target"));
        explainColumn2.setCellValueFactory(new PropertyValueFactory<Word, String>("word_explain"));
        table2.setItems(WordSearch);
    }

    public void Add(ActionEvent e){
        Word newWord = new Word();
        newWord.setWord_target(targetText.getText());
        newWord.setWord_explain(explainText.getText());
        WordList.add(newWord);
    }

    public void Delete(ActionEvent e){
        Word selected = table.getSelectionModel().getSelectedItem();
        WordList.remove(selected);
    }

    public void Repair(ActionEvent e) {
        Word selected = table.getSelectionModel().getSelectedItem();
        selected.setWord_explain(repairText.getText());
        WordList.remove(selected);
        Word newWord = new Word(selected.getWord_target(), selected.getWord_explain());
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
}
