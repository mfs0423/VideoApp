/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author Meng
 */
public class PlayVideoDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
//        // media play
//        final String MEDIA_URL = PlayVideoDemo.class.getResource("demo.mp4").toString();
//        MediaPlayer m = new MediaPlayer(new Media(MEDIA_URL));
//        MediaView mp4 = new MediaView(m);
//        primaryStage.setScene(new Scene(new Group(mp4),500,500));
//        primaryStage.show();
//        m.play();

        // web play
        WebView webview = new WebView();
        webview.getEngine().load("https://www.youtube.com/embed/FWzW5VXSuHc");
        webview.setPrefSize(640, 390);

        primaryStage.setScene(new Scene(webview));
        primaryStage.show();


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
