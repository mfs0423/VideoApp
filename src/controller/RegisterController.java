/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.User;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import page.LoginPage;
import service.RegisterPageService;
import service.impl.RegisterPageServiceImpl;

/**
 *
 * @author Yunmi Song
 */
public class RegisterController implements Initializable {
    private static Pane root;
    private RegisterPageService registerPageServiceImpl = new RegisterPageServiceImpl();
  
    @FXML
    Button register;
    @FXML TextField name;
    @FXML TextField pwd;
    @FXML TextField email;
    @FXML Text errorName;
    @FXML Text errorEmail;
    @FXML Text errorPwd;
    @FXML
    private Button cancel;

  
    @FXML
    public boolean checkName(){
        errorName.setText("");
        String newName = name.getText();
        User newUser = registerPageServiceImpl.findUserByName(newName);
        if(newName.trim().length()<4){
            errorName.setText("At least 4 letters");
            return false;
        }
        if(newUser!=null){
            errorName.setText("Name is used");
            return false;
        }
        return true;
    }

    @FXML
    public boolean checkPwd(){
        errorPwd.setText("");
        String newName = name.getText();
        if("".equals(newName.trim())){
            errorPwd.setText("Please input Name first");
            return false;
        }
        String newPwd = pwd.getText();
        String pattern ="^(?!\\d+$)(?!\\W+$)[0-9A-Za-z]{8,16}$";
        if(newPwd.length()>16||newPwd.length()<8){
            errorPwd.setText("Password length must be in 8-16");
            return false;
        }
        if(!newPwd.matches(pattern)){
            errorPwd.setText("Password only includes numbers and letters");
            return false;
        }
        return true;
    }
    
    @FXML
    public boolean checkEmail(){
        errorEmail.setText("");
        String newEmail = email.getText();
        String pattern = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        if(!newEmail.matches(pattern)){
            errorEmail.setText("Invalid Email address");
            return false;
        }
        return true;
    }
    
    @FXML
    public void register() throws IOException{
        if(!(checkName()&&checkPwd()&&checkEmail())){
            return;
        }
        User newUser = new User();
        newUser.setId(UUID.randomUUID().toString());
        newUser.setName(name.getText());
        newUser.setDate(new Date());
        newUser.setPassword(pwd.getText());
        newUser.setEmail(email.getText());      
        if(registerPageServiceImpl.addUser(newUser)){
            LoginPage loginPage = new LoginPage();
            loginPage.start(new Stage());
            Stage stage = (Stage) register.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    private void cancel(MouseEvent event) throws IOException {
        LoginPage loginPage = new LoginPage();
        loginPage.start(new Stage());
        Stage stage = (Stage) register.getScene().getWindow();
        stage.close();
    }

 

}
