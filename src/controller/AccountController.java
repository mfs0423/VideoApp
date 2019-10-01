/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import page.IndexPage;
import service.RegisterPageService;
import service.impl.RegisterPageServiceImpl;

/**
 * FXML Controller class
 *
 * @author Meng
 */
public class AccountController implements Initializable {

    @FXML
    private Label lbName;
    @FXML
    private Font x1;
    @FXML
    private Label lbPwd;
    @FXML
    private TextField name;
    @FXML
    private TextField pwd;
    @FXML
    private TextField email;
    @FXML
    private Label lbEmail;
    @FXML
    private Text errorName;
    @FXML
    private Font x2;
    @FXML
    private Text errorEmail;
    @FXML
    private Text errorPwd;
    @FXML
    private Button cancel;
    @FXML
    private Button submit;
    public static Stage preStage;
    
    private RegisterPageService registerPageServiceImpl = new RegisterPageServiceImpl();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

  
    @FXML
    public boolean checkName(){
        errorName.setText("");
        if(!"".equals(name.getText())){
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
        }
        return true;
    }

    @FXML
    public boolean checkPwd(){
        errorPwd.setText("");
        if(!"".equals(pwd.getText())){
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
        }
        return true;
    }
    
    @FXML
    public boolean checkEmail(){
        errorEmail.setText("");
        if(!"".equals(email.getText())){
            String newEmail = email.getText();
            String pattern = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
            if(!newEmail.matches(pattern)){
                errorEmail.setText("Invalid Email address");
                return false;
            }
        }
        return true;
    }

    @FXML
    private void cancelRegisterHandler(ActionEvent event) {
        preStage.show();
        Stage stage = (Stage) submit.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void submitRegisterHandler(ActionEvent event) {
        if(!(checkName()&&checkPwd()&&checkEmail())){
            return;
        }
        String name2 = null;
        String pwd2 = null;
        String email2 = null;
        if(!"".equals(name.getText())){
            name2 = name.getText();
        }
        if(!"".equals(pwd.getText())){
            pwd2 = pwd.getText();
        }
        if(!"".equals(email.getText())){
            email2 = email.getText();
        }   
        boolean a = registerPageServiceImpl.updateUser(LoginController.userName,name2,pwd2,email2);
        if(registerPageServiceImpl.updateUser(LoginController.userName,name2,pwd2,email2)){
            if(name2!=null)LoginController.userName = name2;
        }
        preStage.show();
        Stage stage = (Stage) submit.getScene().getWindow();
        stage.close();
    }
    
}
