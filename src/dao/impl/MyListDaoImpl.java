/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.CommentsDao;
import dao.MyListDao;
import entity.Video;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import utils.Tool;

/**
 *
 * @author Yunmi Song
 */
public class MyListDaoImpl implements MyListDao{
    private String path = "src/resource/Library.csv";
    private static SimpleDateFormat logSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Override
    public ArrayList<Video> getMyList() {
        ArrayList<Video> myList = new ArrayList<>();
        CommentsDao commentsDaoImpl = new CommentsDaoImpl();
        BufferedReader reader = Tool.getReader(path);
        String line = null;
        try {                     
            while((line=reader.readLine())!= null){
                String[] result = line.split(",");
                if(Boolean.parseBoolean(result[3])==true){
                    Video video = new Video();
                    video.setId(result[0]);
                    video.setName(result[1]);
                    video.setReference(result[2]);
                    video.setAddToList(Boolean.parseBoolean(result[3]));
                    video.setWidth(Double.parseDouble(result[4]));
                    video.setLength(Double.parseDouble(result[5]));
                    video.setDate(Tool.getDate(result[6]));
                    video.setComments(commentsDaoImpl.getComments(video.getId()));
                    video.setRating(Integer.parseInt(result[7]));
                    video.setViews(Integer.parseInt(result[8]));
                    myList.add(video);
                }      
            }
        } catch (IOException e) {
            Tool.writeLog(logSdf.format(new Date())+": MyListDaoImpl.java can not get MyList");
            return null;
        }finally{
            Tool.closeReader(reader);
        }      
        return myList;
    }
    public static void main(String[] args) {
        Tool.writeLog(logSdf.format(new Date())+": MyListDaoImpl.java can not get MyList");
    }

    @Override
    public boolean addToMyList(int videoId) {
        StringBuffer newFile = new StringBuffer();
        BufferedReader reader = Tool.getReader(path);
        boolean status = false;
        String line = null;
        try {                     
            while((line=reader.readLine())!= null){
                String[] result = line.split(",");
                if(videoId == (Integer.parseInt(result[0]))){
                    newFile.append(line.replaceFirst(result[3],"true")+"\n");       
                }else{
                    newFile.append(line+"\n");
                }
            }
            status = Tool.replace(newFile.toString());
        } catch (IOException e) {
            Tool.writeLog(logSdf.format(new Date())+": MyListDaoImpl.java can not get add video to myList");
            return false;
        }finally{
            Tool.closeReader(reader);
        }
        return true;
    }

    @Override
    public boolean removeFromMyList(int videoId) {
        StringBuffer newFile = new StringBuffer();
        BufferedReader reader = Tool.getReader(path);
        boolean status = false;
        String line = null;
        try {                     
            while((line=reader.readLine())!= null){
                String[] result = line.split(",");
                if(videoId != (Integer.parseInt(result[0]))){
                    newFile.append(line+"\n");
                }
            }
            status = Tool.replace(newFile.toString());
        } catch (IOException e) {
            Tool.writeLog(logSdf.format(new Date())+": UserDaoImpl.java can not remove video from myList");
            return false;
        }finally{
            Tool.closeReader(reader);
        }
        return true;
    }
    
}
