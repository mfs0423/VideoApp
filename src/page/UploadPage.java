/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package page;

import java.io.FileInputStream;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Meng
 */
public class UploadPage{
    public Stage s;
    public Pane start(Stage stage) throws Exception {
        s = stage;
        Pane root = (Pane) new FXMLLoader().load(new FileInputStream("src/fxml/UploadVideo.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Advengers");
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:src/images/logo.png"));
        stage.show();
        return root;
    }
    
}
