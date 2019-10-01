/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.VideoDao;
import dao.impl.VideoDaoImpl;
import entity.Posters;
import entity.Video;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import page.AccountPage;
import page.LoginPage;
import page.MediaPage;
import page.UploadPage;
import service.IndexPageService;
import service.impl.IndexPageServiceImpl;
import utils.ShowPosters;

/**
 *
 * @author Meng
 */
public class IndexController  implements Initializable {
    ExecutorService threadPool = Executors.newFixedThreadPool(10);  
    VideoDao videoDaoImpl = new VideoDaoImpl();
    IndexPageService indexPageServiceImpl = new IndexPageServiceImpl();
    int distance = 0;
    @FXML Text userName;
    ImageView right_btn;
    @FXML ImageView leftBtn;
    @FXML ImageView leftBtn2;
    @FXML ImageView rightBtn;
    @FXML ImageView rightBtn2;
    @FXML ImageView view1 ;
    @FXML ImageView view2 ;
    @FXML ImageView view3 ;
    @FXML ImageView view4 ;
    @FXML ImageView view5 ;
    @FXML ImageView view6 ;
    @FXML ImageView view7 ;
    @FXML ImageView view8 ;
    @FXML ImageView view9 ;
    @FXML ImageView view10 ;
    @FXML ImageView view11 ;
    @FXML ImageView view12 ;
    @FXML Label upload;
    @FXML
    private AnchorPane subScreen;
    @FXML
    private Label library;
    @FXML
    private Label myList;
    @FXML
    private ImageView logout;
    @FXML
    private ImageView search;
    @FXML
    private TextField searchBox;
    @FXML
    private ImageView user;
    @FXML
    private ImageView index;
    private Pane indexPane;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void upload() throws Exception{
        saveIndexPage();
        UploadController.preStage = (Stage)user.getScene().getWindow();
        UploadPage newpage = new UploadPage();
        Pane p = newpage.start(new Stage());
        Stage stage = newpage.s;
        UploadController.p = p;
        UploadController.s = stage;
        Stage s = (Stage)upload.getScene().getWindow();
        s.close();
        
    }
    private void saveIndexPage() throws FileNotFoundException, IOException{
        Stage s = (Stage)user.getScene().getWindow();
        Pane p = (Pane)s.getScene().getRoot();
        indexPane = (Pane)new FXMLLoader().load(new FileInputStream("src/fxml/indexSub.fxml"));
    }

    @FXML
    private void searchVideo(MouseEvent event) throws IOException {
        String video = searchBox.getText().trim();
        if(video==null||"".equals(video)){
            LibraryController.videoName = "";
        }else{
            LibraryController.videoName = video;
        }
        LibraryController.isSearch = true;
        Stage s = (Stage)library.getScene().getWindow();
        Pane p = (Pane)s.getScene().getRoot();
        Pane subScreen = (Pane)p.getChildren().set(0, (Pane) new FXMLLoader().load(new FileInputStream("src/fxml/Library.fxml")));
    }


    @FXML
    public void goLibrary() throws FileNotFoundException, IOException{
        saveIndexPage();
        showLibrary();
        LibraryController.videoName="";
        LibraryController.pane = subScreen;
        Stage s = (Stage)library.getScene().getWindow();
        Pane p = (Pane)s.getScene().getRoot();
        Pane subScreen = (Pane)p.getChildren().set(0, (Pane) new FXMLLoader().load(new FileInputStream("src/fxml/Library.fxml")));

    }
    
    public void showLibrary(){
        ArrayList<Video> library = indexPageServiceImpl.getLibrary();
        LibraryController.library = library;
        MyListController.library = library;
        
    }
    
    
    @FXML
    public void goMyList() throws FileNotFoundException, IOException{
        saveIndexPage();
        showLibrary();
        Stage s = (Stage)library.getScene().getWindow();
        Pane p = (Pane)s.getScene().getRoot();
        Pane subScreen = (Pane)p.getChildren().set(0, (Pane) new FXMLLoader().load(new FileInputStream("src/fxml/MyList.fxml")));
//        System.out.println("execute go myList");
    }
    
    
    @FXML
    private void Account(MouseEvent event) throws IOException {
        AccountController.preStage = (Stage)user.getScene().getWindow();
        new AccountPage().start(new Stage());
        Stage s = (Stage)user.getScene().getWindow();
        s.close();
    }
    @FXML
    public void logout() throws IOException{
        new LoginPage().start(new Stage());
        Stage s = (Stage)logout.getScene().getWindow();
        s.close();
    }
    @FXML
    private void goIndex() {
        Stage s = (Stage)library.getScene().getWindow();
        Pane p = (Pane)s.getScene().getRoot();
        Pane subScreen = (Pane)p.getChildren().set(0, indexPane);
        ShowPosters.addImages(indexPane);
    }
    @FXML
    public void play(MouseEvent e) throws IOException{
        MediaController.preStage = (Stage)subScreen.getScene().getWindow();
        ImageView imageView = (ImageView)e.getSource();
        Posters p = (Posters)imageView.getImage();
        MediaPage newpage = new MediaPage();
        newpage.setUrl(videoDaoImpl.getVideo(p.getVideoId()).getReference());      
        MediaPage.url= videoDaoImpl.getVideoById(p.getVideoId()).getReference();
        newpage.start(new Stage());
        
        Stage s = (Stage)subScreen.getScene().getWindow();
        s.close();
        
    }
    
    @FXML
    public synchronized void imageMoveRight() throws InterruptedException{
        if(rightBtn.isDisable()) return;
        rightBtn.setDisable(true);
        distance = 0;
        if(view1.getLayoutX()<25){
            threadPool.execute(new Runnable() {  
                @Override
                public void run() {  
                    new Timer().scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            if(distance++==525){
                                rightBtn.setDisable(false);
                                System.gc();
                                cancel();
                            }
                            view1.setLayoutX(view1.getLayoutX()+1);
                            view2.setLayoutX(view2.getLayoutX()+1);
                            view3.setLayoutX(view3.getLayoutX()+1);
                            view4.setLayoutX(view4.getLayoutX()+1);
                            view5.setLayoutX(view5.getLayoutX()+1);
                            view6.setLayoutX(view6.getLayoutX()+1);
                        }
                    }, new Date(), 1);
                }
            });
        }else{
            rightBtn.setDisable(false);
        }
        left_unclear();
        right_unclear();
    }
    @FXML
    public synchronized  void imageMoveLeft(){
        if(leftBtn.isDisable()) return;
        leftBtn.setDisable(true);
        distance = 0;
        if(view6.getLayoutX()>1075){
            threadPool.execute(new Runnable() {  
                @Override
                public void run() {
                    new Timer().scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            if((distance++)==525){
                                leftBtn.setDisable(false);
                                System.gc();
                                cancel();
                            }
                            view1.setLayoutX(view1.getLayoutX()-1);
                            view2.setLayoutX(view2.getLayoutX()-1);
                            view3.setLayoutX(view3.getLayoutX()-1);
                            view4.setLayoutX(view4.getLayoutX()-1);
                            view5.setLayoutX(view5.getLayoutX()-1);
                            view6.setLayoutX(view6.getLayoutX()-1);
                        }
                    }, new Date(), 1);
                }
            });
        }else{
            leftBtn.setDisable(false);
        }
        left_unclear();
        right_unclear();  
    }
    @FXML
    public synchronized void imageMoveLeft2(){
        if(leftBtn2.isDisable()) return;
        leftBtn2.setDisable(true);
        distance = 0;
        if(view12.getLayoutX()>1075){
            threadPool.execute(new Runnable() {  
                @Override
                public void run() {
                    new Timer().scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            if((distance++)==525){
                                leftBtn2.setDisable(false);
                                System.gc();
                                cancel();
                            }
                            view7.setLayoutX(view7.getLayoutX()-1);
                            view8.setLayoutX(view8.getLayoutX()-1);
                            view9.setLayoutX(view9.getLayoutX()-1);
                            view10.setLayoutX(view10.getLayoutX()-1);
                            view11.setLayoutX(view11.getLayoutX()-1);
                            view12.setLayoutX(view12.getLayoutX()-1);
                        }
                    }, new Date(), 1);
                }
            });
        }else{
            leftBtn2.setDisable(false);
        }
        left_unclear2();
        right_unclear2();
    }
    @FXML
    public synchronized void imageMoveRight2(){
        if(rightBtn2.isDisable()) return;
        rightBtn2.setDisable(true);
        distance = 0;
        if(view7.getLayoutX()<25){
            threadPool.execute(new Runnable() {  
                @Override
                public void run() {
                    new Timer().scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            if(distance++==525){
                                rightBtn2.setDisable(false);
                                System.gc();
                                cancel();
                            }
                            view7.setLayoutX(view7.getLayoutX()+1);
                            view8.setLayoutX(view8.getLayoutX()+1);
                            view9.setLayoutX(view9.getLayoutX()+1);
                            view10.setLayoutX(view10.getLayoutX()+1);
                            view11.setLayoutX(view11.getLayoutX()+1);
                            view12.setLayoutX(view12.getLayoutX()+1);
                        }
                    }, new Date(), 1);
                }
            });
        }else{
            rightBtn2.setDisable(false);
        }      
        left_unclear2();
        right_unclear2();
    }
    public void left_unclear(){
        leftBtn.setOpacity(0.5);
    }
    @FXML
    public void left_clear(){
        leftBtn.setOpacity(1.0);
    }
    public void right_unclear(){
        rightBtn.setOpacity(0.5);
    }
    @FXML
    public void right_clear(){
        rightBtn.setOpacity(1.0);
    }
    @FXML
    public void left_unclear2(){
        leftBtn2.setOpacity(0.5);
    }
    @FXML
    public void left_clear2(){
        leftBtn2.setOpacity(1.0);
    }
    @FXML
    public void right_unclear2(){
        rightBtn2.setOpacity(0.5);
    }
    @FXML
    public void right_clear2(){
        rightBtn2.setOpacity(1.0);
    }


}
