/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import page.MediaPage;

/**
 *
 * @author Yunmi Song
 */
public class MediaController implements Initializable{
    public static Stage preStage;
    @FXML
    private Label title;
    @FXML
    private ImageView star1;
    @FXML
    private ImageView star2;
    @FXML
    private ImageView star3;
    @FXML
    private ImageView star4;
    @FXML
    private ImageView star5;
    private boolean s1 = false;
    private boolean s2 = false;
    private boolean s3 = false;
    private boolean s4 = false;
    private boolean s5 = false;
    @FXML
    private ListView<?> comments;
    @FXML
    private ImageView back;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void empty1() {
        if(s1) return;
        star1.setImage(new Image("file:src/images/star1.png"));
    }

    @FXML
    private void empty2() {
        if(s2) return;
        empty1();
        star2.setImage(new Image("file:src/images/star1.png"));
    }

    @FXML
    private void empty3() {
        if(s3) return;
        empty1();empty2();
        star3.setImage(new Image("file:src/images/star1.png"));
    }

    @FXML
    private void empty4() {
        if(s4) return;
        empty1();empty2();empty3();
        star4.setImage(new Image("file:src/images/star1.png"));
    }

    @FXML
    private void empty5() {
        if(s5) return;
        empty1();empty2();empty3();empty4();
        star5.setImage(new Image("file:src/images/star1.png"));
    }
    @FXML
    private void full1() {
        if(s1) return;
        star1.setImage(new Image("file:src/images/star2.png"));
    }
    @FXML
    private void full2() {
        if(s2) return;
        full1();
        star2.setImage(new Image("file:src/images/star2.png"));
    }
    @FXML
    private void full3() {
        if(s3) return;
        full1();full2();
        star3.setImage(new Image("file:src/images/star2.png"));
    }
    @FXML
    private void full4() {
        if(s4) return;
        full1();full2();full3();
        star4.setImage(new Image("file:src/images/star2.png"));
    }
    @FXML
    private void full5() {
        if(s5) return;
        full1();full2();full3();full4();
        star5.setImage(new Image("file:src/images/star2.png"));
    }
    @FXML
    private void rate1(MouseEvent event) {        
        s1 = true;
        s2 = true;
        s3 = true;
        s4 = true;
        s5 = true;
    }
    @FXML
    private void rate2(MouseEvent event) {        
        s1 = true;
        s2 = true;
        s3 = true;
        s4 = true;
        s5 = true;
    }
    @FXML
    private void rate3(MouseEvent event) {        
        s1 = true;
        s2 = true;
        s3 = true;
        s4 = true;
        s5 = true;
    }
    @FXML
    private void rate4(MouseEvent event) {
        s1 = true;
        s2 = true;
        s3 = true;
        s4 = true;
        s5 = true;
    }
    @FXML
    private void rate5(MouseEvent event) {
        s1 = true;
        s2 = true;
        s3 = true;
        s4 = true;
        s5 = true;
    }
    private void disableStar(){
        star1.setDisable(true);
        star2.setDisable(true);
        star3.setDisable(true);
        star4.setDisable(true);
        star5.setDisable(true);
        
    }

    @FXML
    private void goBack(MouseEvent event) {
        preStage.show();
        Stage s = (Stage)back.getScene().getWindow();
        s.close();
    }
    

}
