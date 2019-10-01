/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.UserDao;
import entity.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import utils.Tool;

/**
 *
 * @author Fansheng Meng
 */
public class UserDaoImpl implements UserDao{
    private String path = "src/resource/User.csv";
    private static SimpleDateFormat logSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Override
    public User getUser(String name) {
        BufferedReader reader = Tool.getReader(path);        
        User user = null;
        String line = null;
        try {                     
            while((line=reader.readLine())!=null){
                String[] result = line.split(",");
                String userName = result[1];
                if(name.equals(userName)){
                    user = new User();
                    user.setId(result[0]);
                    user.setName(result[1]);
                    user.setPassword(result[2]);
                    user.setEmail(result[3]);
                    user.setDate(Tool.getDate(result[4]));
                }
            }
        } catch (IOException e) {
            Tool.writeLog(logSdf.format(new Date())+": UserDaoImpl.java can not get User");
        }finally{
            Tool.closeReader(reader);
        }      
        return user;
    }

    @Override
    public boolean addUser(User user) {       
        Tool.path = path;
        boolean success = Tool.write(user.getId()+","+user.getName()+","+user.getPassword()+","
                +user.getEmail()+","+Tool.getDate(new Date()));
        if(!success) Tool.writeLog(logSdf.format(new Date())+": UserDaoImpl.java can not add Video");
        return success;
    }

    @Override
    public boolean updateUser(String oldName, String name, String pwd, String email) {
        Tool.path = path;
        StringBuffer newFile = new StringBuffer();
        BufferedReader reader = Tool.getReader(path);
        boolean status = false;
        String line = null;
        try {                     
            while((line=reader.readLine())!=null){
                String[] result = line.split(",");
                if(result[1].equals(oldName)){
                    newFile.append(line.replaceFirst(result[1], (name==null?result[1]:name)).replaceFirst(result[2],(pwd==null?result[2]:pwd))
                            .replaceFirst(result[3], (email==null?result[3]:email))+"\n");       
                }else{
                    newFile.append(line+"\n");
                }
            }
            status = Tool.replace(newFile.toString());
        } catch (IOException e) {
            Tool.writeLog(logSdf.format(new Date())+": UserDaoImpl.java can not update User");
            return false;
        }finally{
            Tool.closeReader(reader);
        }
        return status;
    }

//    @Override
//    public boolean deleteUser(User user) {
//        StringBuffer newFile = new StringBuffer();
//        BufferedReader reader = Tool.getReader(path);
//        boolean status = false;
//        String line = null;
//        try {                     
//            while((line=reader.readLine())!=null){
//                String[] result = line.split(",");
//                if(!result[0].equals(user.getId())){
//                    newFile.append(line+"\n");
//                }
//            }
//            status = Tool.replace(newFile.toString());
//        } catch (IOException e) {
//            Tool.writeLog(logSdf.format(new Date())+": UserDaoImpl.java can not delete User");
//            return false;
//        }finally{
//            Tool.closeReader(reader);
//        }
//        return status;
//    }
    
}
