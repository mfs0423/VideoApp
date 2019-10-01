/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Yunmi Song
 */
public class IndexPage{
    
    public Pane start(Stage stage) throws IOException {
        Pane pane = (Pane) new FXMLLoader().load(new FileInputStream("src/fxml/Index.fxml"));
        Scene scene = new Scene(pane);
        stage.setTitle("AvengerMainPage");
        stage.getIcons().add(new Image("file:src/images/logo.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        return pane;
    }
}
