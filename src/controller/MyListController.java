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
import entity.Library;
import entity.Posters;
import entity.Video;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import page.MediaPage;
import utils.Tool;

/**
 *
 * @author Yunmi Song
 */
public class MyListController implements Initializable {

    @FXML
    private AnchorPane libraryScreen;
    @FXML
    private ListView<String> videos;
    @FXML
    private Label title;
    @FXML
    private ImageView image;
    @FXML
    private Button del_btn;
    @FXML
    private Label views;
    @FXML
    private Label date;
    public static ArrayList<Video> library = new ArrayList<>();
    public static ArrayList<Video> mylist = new ArrayList<>();
    private VideoDao videoDaoImpl = new VideoDaoImpl();
    private PosterDao posterDaoImpl = new PosterDaoImpl();

    @FXML
    private void show() {
        String name = videos.getSelectionModel().getSelectedItem();
        Video v = videoDaoImpl.getVideo(name);
        title.setText(v.getName());
        views.setText(v.getViews() + " views");
        date.setText(Tool.getDate(v.getDate()));
        image.setImage(posterDaoImpl.getPoster(v.getId()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        videos.getItems().clear();
        mylist.clear();
        for (Video v : library) {
            if (v.isAddToList() == true) {
                mylist.add(v);
            }
        }
        for (Video v : mylist) {
            if (videos.getItems().indexOf(v.getName()) < 0) {
                videos.getItems().add(v.getName());
            }
        }
        if (mylist.size() > 0) {
            videos.setStyle("-fx-font-size: 1.5em ;");
            title.setText(mylist.get(0).getName());
            views.setText(mylist.get(0).getViews() + " views");
            date.setText(Tool.getDate(mylist.get(0).getDate()));
            image.setImage(posterDaoImpl.getPoster(mylist.get(0).getId()));
        }
    }

    @FXML
    public void play(MouseEvent e) throws IOException {
        MediaController.preStage = (Stage) image.getScene().getWindow();
        ImageView imageView = (ImageView) e.getSource();

        Posters p = (Posters) image.getImage();
        MediaPage newpage = new MediaPage();
        newpage.setUrl(videoDaoImpl.getVideo(p.getVideoId()).getReference());
        MediaPage.url = videoDaoImpl.getVideoById(p.getVideoId()).getReference();
        newpage.start(new Stage());

        Stage s = (Stage) image.getScene().getWindow();
        s.close();

    }

    @FXML
    private void removeMyVideo(MouseEvent event) throws FileNotFoundException, IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("remove this video");
        alert.setContentText("Are you sure to remove this video from my list?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            mylist.remove(videos.getSelectionModel().getSelectedIndex());
            videos.getItems().clear();
            Posters p = (Posters) image.getImage();
            Video v = videoDaoImpl.getVideoById(p.getVideoId());
            v.setAddToList(false);
            System.out.println(videoDaoImpl.updateVideo(v));
            
            for (Video video : mylist) {
                if (videos.getItems().indexOf(video.getName()) < 0) {
                    videos.getItems().add(video.getName());
                }
            }
            if (mylist.size() > 0) {
                videos.setStyle("-fx-font-size: 1.5em ;");
                title.setText(mylist.get(0).getName());
                views.setText(mylist.get(0).getViews() + " views");
                date.setText(Tool.getDate(mylist.get(0).getDate()));
                image.setImage(posterDaoImpl.getPoster(mylist.get(0).getId()));
            }
        }
    }

}
