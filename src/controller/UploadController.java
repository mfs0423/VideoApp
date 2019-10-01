/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.VideoDao;
import dao.impl.VideoDaoImpl;
import entity.Video;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import page.IndexPage;
import utils.ShowPosters;
import utils.Tool;

/**
 * FXML Controller class
 *
 * @author Meng
 */
public class UploadController implements Initializable {
   
    private static SimpleDateFormat logSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private VideoDao videoDaoImpl = new VideoDaoImpl();
    public static Stage preStage;
    FileChooser fileChooser = new FileChooser();
    private String fileName;
    private File video = null;
    private File posterFile = null;
    private String poster;
    static Pane p;
    static Stage s;
    @FXML ImageView uploadPoster;
    @FXML ImageView uploadVideo;
    @FXML ImageView submit;
    @FXML ImageView back;
    @FXML Label videoName;
    @FXML Label posterName;
    @FXML
    private AnchorPane AnchorPane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    @FXML
    public void uploadVideo(){
        fileChooser.setTitle("Upload a Video");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Videos", "*.*"),
            new FileChooser.ExtensionFilter("MP4", "*.mp4")
        );
        boolean success =false;
        while(!success||video==null) {
            video = fileChooser.showOpenDialog(s);
            fileName = video.getName();
            if(!fileName.matches(".*(.mp4|.MP4)$")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.titleProperty().set("Warning!");
                alert.headerTextProperty().set("Only support mp4 ");
                alert.showAndWait();
                continue;
            }
            success = true;
            videoName.setText(fileName);
        }

    }

    @FXML
    private void returnBack(MouseEvent event) {
        preStage.show();
        Stage s = (Stage)back.getScene().getWindow();
        s.close();
    }
    
 
    
    @FXML
    public void uploadPoster(){
        fileChooser.setTitle("Upload a Poster");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
        boolean success =false;
        while(!success||posterFile==null) {
            posterFile = fileChooser.showOpenDialog(s);
            poster = posterFile.getName();
            if(!poster.matches(".*(.png|.PNG|.JPG|.jpg)$")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.titleProperty().set("Warning!");
                alert.headerTextProperty().set("Only support jpg and png ");
                alert.showAndWait();
                continue;
            }
            success = true;
            posterName.setText(poster);
        }
    }
    @FXML
    public void submit() throws IOException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.getDialogPane().setGraphic();

        if(fileName==null||"".equals(fileName)||poster==null||"".equals(poster)){
            alert.titleProperty().set("Warning");
            alert.headerTextProperty().set("You did not upload poster or video");
            alert.showAndWait();
            return;
        }
        try{
            Tool.copy(video.getPath(), "src/videos/"+fileName);
            Tool.copy(posterFile.getPath(), "src/images/posters"+poster);
        }catch(RuntimeException e){
            alert.titleProperty().set("Error");
            alert.headerTextProperty().set("Something wrong with uploding, please upload again");
            alert.showAndWait();
        }
        Video v = new Video();
        v.setId(UUID.randomUUID().toString());
        v.setName(fileName);
        v.setDate(new Date());
        v.setComments(new ArrayList<>());
        v.setRating(0);
        v.setViews(0);
        v.setWidth(0);
        v.setLength(0);
        v.setAddToList(true);
        v.setReference("src/videos/"+fileName);
        videoDaoImpl.addVideo(v);
        
        alert.titleProperty().set("Successful");
        alert.headerTextProperty().set("Go to index page ");
        alert.showAndWait();
        
        
        Stage s = (Stage)submit.getScene().getWindow();
        s.close();
        Pane p = new IndexPage().start(new Stage());
        ShowPosters.addImages(p);
        
    }


  
}
