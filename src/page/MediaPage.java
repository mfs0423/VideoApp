/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author Yunmi Song
 */
public class MediaPage extends Application{
    public static String url;
    public void start(Stage stage) throws IOException
    { 
        Pane root = (Pane) new FXMLLoader().load(new FileInputStream("src/fxml/MediaPage.fxml"));
        WebView webview = (WebView)root.getChildren().get(0);
        webview.getEngine().load(url);
        webview.setPrefSize(600, 560);
        Scene scene = new Scene(root);     
        stage.setScene(scene);
        stage.setTitle("Advengers");
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:src/images/logo.png"));
        stage.show();
    }
    
    public void start2(Stage stage) throws IOException
    { 
        Pane root = (Pane) new FXMLLoader().load(new FileInputStream("src/fxml/MediaPage2.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Advengers");
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:src/images/logo.png"));
        stage.show();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
