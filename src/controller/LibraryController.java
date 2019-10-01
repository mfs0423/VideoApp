/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PosterDao;
import dao.VideoDao;
import dao.impl.PosterDaoImpl;
import dao.impl.VideoDaoImpl;
import entity.Posters;
import entity.Video;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import page.MediaPage;
import utils.ShowPosters;
import utils.Tool;

/**
 * FXML Controller class
 *
 * @author Meng
 */
public class LibraryController implements Initializable {
    private static SimpleDateFormat sdf = new SimpleDateFormat("M d y");
    private VideoDao videoDaoImpl = new VideoDaoImpl();
    private PosterDao posterDaoImpl = new PosterDaoImpl();
    public static ArrayList<Video> library;
    public static boolean isSearch = false;
    public static String videoName = "";
    public static Pane pane;
    @FXML
    private AnchorPane libraryScreen;
    @FXML
    private ChoiceBox<String> choices;
    @FXML
    private ListView<String> videos;
    @FXML
    private Button pre_btn;
    @FXML
    private Button next_btn;
    @FXML
    private Label title;
    @FXML
    private ImageView image;
    @FXML
    private Button add_btn;
    @FXML
    private Label views;
    @FXML
    private Label date;

    @FXML
    private void sortVideos(MouseEvent event) {
        String type = choices.getSelectionModel().getSelectedItem();
        if("".equals(type.trim())|| type == null){
            return;
        }
        Stage s = (Stage)choices.getScene().getWindow();
        Pane p = (Pane)s.getScene().getRoot();
        library = ShowPosters.changeImages((Pane)(p.getChildren().get(0)),type.trim());
        
        int index = 0;
        for(Video v : library){
            videos.getItems().set(index++, v.getName());
            System.out.println(v.getName());
        }
        videos.setStyle("-fx-font-size: 1.5em ;");
        title.setText(library.get(0).getName());
        views.setText(library.get(0).getViews()+" views");
        date.setText(Tool.getDate(library.get(0).getDate()));
        image.setImage(posterDaoImpl.getPoster(library.get(0).getId()));
 
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!"".equals(videoName)){
            library = videoDaoImpl.getVideosByName(videoName);
        }
        for(Video v : library){
            videos.getItems().add(v.getName());
        }
        if(library.size()<0) return;
        videos.setStyle("-fx-font-size: 1.5em ;");
        title.setText(library.get(0).getName());
        views.setText(library.get(0).getViews()+" views");
        date.setText(Tool.getDate(library.get(0).getDate()));
        image.setImage(posterDaoImpl.getPoster(library.get(0).getId()));
    }

    @FXML
    private void show(MouseEvent click) {
        if(click.getClickCount() == 2){
            System.out.println("222");
            editVideo();
            return;
        }
        String name = videos.getSelectionModel().getSelectedItem();
        Video v = videoDaoImpl.getVideo(name);
        title.setText(v.getName());
        views.setText(v.getViews()+" views");
        date.setText(Tool.getDate(v.getDate()));
        image.setImage(posterDaoImpl.getPoster(v.getId()));
    }
    
    private void editVideo() {
        String videoName = videos.getSelectionModel().getSelectedItem();
        TextInputDialog dialog = new TextInputDialog(videoName);
        dialog.setTitle("Editing");
        dialog.setHeaderText("You can change video's name here.");
        dialog.setContentText("Give it a new Name : ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            boolean b = videoDaoImpl.changeVideoName(videoName,result.get());
            System.out.println(b);
            videos.getItems().set(videos.getSelectionModel().getSelectedIndex(), result.get());
        } 
        
    }
    @FXML
    private void addToMyList(MouseEvent event) {
        Posters p = (Posters)image.getImage();
        Video v = videoDaoImpl.getVideoById(p.getVideoId());
        v.setAddToList(true);
        System.out.println("update video: "+v.getName());
        System.out.println(videoDaoImpl.updateVideo(v));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.titleProperty().set("Infromation");
        alert.headerTextProperty().set("You have added this video to myList");
        alert.showAndWait();
    }

    
    @FXML
    public void play(MouseEvent e) throws IOException{
        MediaController.preStage = (Stage)image.getScene().getWindow();
        ImageView imageView = (ImageView)e.getSource();
        
        Posters p = (Posters)image.getImage();
        MediaPage newpage = new MediaPage();
        newpage.setUrl(videoDaoImpl.getVideo(p.getVideoId()).getReference());      
        MediaPage.url= videoDaoImpl.getVideoById(p.getVideoId()).getReference();
        newpage.start(new Stage());
        
        Stage s = (Stage)image.getScene().getWindow();
        s.close();
        
    }
}
