package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.io.IOException;

import java.awt.*;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            //scene2 nghe
            Button button3 = new Button("Load: https://translate.google.com/");
            WebView browser = new WebView();
            WebEngine webEngine = browser.getEngine();
            String url = "https://translate.google.com/";
            webEngine.load(url);
            VBox root2 = new VBox();
            root2.getChildren().addAll(button3, browser);
            Scene scene2 = new Scene(root2);
            //scene1 them sua xoa
            Parent root1 = FXMLLoader.load(this.getClass().getResource("sample.fxml"));
            Scene scene1 = new Scene(root1);
            //Scene start
            Label label = new Label("Chọn cậu lệnh bạn muốn thực hiện");
            Button button1 = new Button("Thêm, Sửa, Xóa, Tìm Kiếm");
            button1.setOnAction(event -> {
                primaryStage.setScene(scene1);
            });
            Button button2 = new Button("Nghe");
            button2.setOnAction(event -> {
                primaryStage.setScene(scene2);
            });
            VBox layout1 = new VBox();
            layout1.getChildren().addAll(label, button1, button2);
            Scene scene = new Scene(layout1, 300, 200);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}