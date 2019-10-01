package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entity.User;
import entity.Video;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import page.IndexPage;
import page.RegisterPage;
import service.IndexPageService;
import service.LoginPageService;
import service.impl.IndexPageServiceImpl;
import service.impl.LoginPageServiceImpl;
import utils.ShowPosters;

/**
 *
 * @author Meng
 */
public class LoginController implements Initializable {
    public static String userName;
    @FXML Button login;
    @FXML Button register;
    @FXML Text errorName;
    @FXML Text errorPwd;
    @FXML TextField name;
    @FXML TextField pwd;
    
    LoginPageService loginPageServiceImpl = new LoginPageServiceImpl();
    static IndexPageService indexPageServiceImpl = new IndexPageServiceImpl();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void signUp() throws IOException{
        new RegisterPage().start(new Stage());
        Stage s = (Stage)register.getScene().getWindow();
        System.out.println("999999999999");
        s.close();
    }
    public void login() throws IOException{
        errorName.setText("");
        errorPwd.setText("");
        String userName = name.getText().trim();
        String password = pwd.getText().trim();
        if("".equals(userName)){
            errorName.setText("Name is required");
            return;
        }
        if("".equals(password)){
            errorPwd.setText("Passward is required");
            return;
        }
        User user = loginPageServiceImpl.findUserByName(userName);
        if(user==null){
            System.out.println(user);
            errorName.setText("Name is wrong");
            return;
        }else if(!password.equals(user.getPassword())){
            errorPwd.setText("Password is wrong");
            return;
        }
        LoginController.userName = userName;
        
        openIndex();
        Stage s = (Stage)login.getScene().getWindow();
        s.close();
    }

    public void openIndex() throws IOException{             
        Pane p = new IndexPage().start(new Stage());
        if(LoginController.userName!=null){
            Text t = (Text)(p.getChildren().get(1));
            t.setText(LoginController.userName);
        }
        
        ShowPosters.addImages((Pane)(p.getChildren().get(0)));
    }

}
