/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Yunmi Song
 */
public class LoginPage extends Application {
    
    @Override
    public void start(Stage stage) throws IOException
    { 
        Pane root = (Pane) new FXMLLoader().load(new FileInputStream("src/fxml/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Advengers");
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:src/images/logo.png"));
        stage.show();
    }
    
}
