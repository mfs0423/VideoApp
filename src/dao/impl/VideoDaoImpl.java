/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.CommentsDao;
import dao.VideoDao;
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
public class VideoDaoImpl implements VideoDao{
    private String path = "src/resource/Library.csv";
    private static SimpleDateFormat logSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public Video getVideo(String name) {
        CommentsDao commentsDaoImpl = new CommentsDaoImpl();
        BufferedReader reader = Tool.getReader(path); 
        Video video = new Video();
        String line = null;
        try {                     
            while((line=reader.readLine())!=null){
                String[] result = line.split(",");
                if(name.equals(result[1])){
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
                }
            }        
        } catch (IOException e) {
             Tool.writeLog(logSdf.format(new Date())+": VideoDaoImpl.java can not get video");
             return null;
        }finally{
            Tool.closeReader(reader);
        }      
        return video;
    }
    
    @Override
    public Video getVideoById(String id) {
        CommentsDao commentsDaoImpl = new CommentsDaoImpl();
        BufferedReader reader = Tool.getReader(path); 
        Video video = new Video();
        String line = null;
        try {                     
            while((line=reader.readLine())!=null){
                String[] result = line.split(",");
                if(id.equals(result[0])){
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
                }
            }        
        } catch (IOException e) {
             Tool.writeLog(logSdf.format(new Date())+": VideoDaoImpl.java can not get video");
             return null;
        }finally{
            Tool.closeReader(reader);
        }      
        return video;
    }

    @Override
    public boolean addVideo(Video video) {
        //Comments are not in Library.csv, we use videoId to find comments
        Tool.path = path;
        boolean success = Tool.write(video.getId()+","+video.getName()+","+video.getReference()+","+video.isAddToList()+","+video.getWidth()+","+
                video.getLength()+","+Tool.getDate(video.getDate()));
        if(!success) Tool.writeLog(logSdf.format(new Date())+": VideoDaoImpl.java can not add Video");
        return success;
    }

    @Override
    public boolean updateVideo(Video video) {
        Tool.path = path;
        StringBuffer newFile = new StringBuffer();
        BufferedReader reader = Tool.getReader(path);
        boolean status = false;
        String line = null;
        try {                     
            while((line=reader.readLine())!=null){
                String[] result = line.split(",");
                if(result[0].equals(video.getId())){
                    newFile.append(line.replaceFirst(result[3], (video.isAddToList()+""))+"\n");       
                }else{
                    newFile.append(line+"\n");
                }
            }
            status = Tool.replace(newFile.toString());
        } catch (IOException e) {
            Tool.writeLog(logSdf.format(new Date())+": VideoDaoImpl.java can not update video");
            return false;
        }finally{
            Tool.closeReader(reader);
        }
        return status;
    }

    @Override
    public ArrayList<Video> getVideosByName(String videoName) {
        videoName = videoName.trim().toLowerCase();
        CommentsDao commentsDaoImpl = new CommentsDaoImpl();
        BufferedReader reader = Tool.getReader(path); 
        ArrayList<Video> results = new ArrayList<>();
        String line = null;
        try {                     
            while((line=reader.readLine())!=null){
                String[] result = line.split(",");
                if((result[1].toLowerCase().indexOf(videoName))!=-1){
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
                    results.add(video);
                }
            }        
        } catch (IOException e) {
             Tool.writeLog(logSdf.format(new Date())+": VideoDaoImpl.java can not get videos");
             return null;
        }finally{
            Tool.closeReader(reader);
        }      
        return results;
    }

    @Override
    public boolean changeVideoName(String oldNameString, String newName) {
        Tool.path = path;
        StringBuffer newFile = new StringBuffer();
        BufferedReader reader = Tool.getReader(path);
        boolean status = false;
        String line = null;
        try {                     
            while((line=reader.readLine())!=null){
                String[] result = line.split(",");
                if(result[1].equals(oldNameString)){
                    String newLine = line.replaceFirst(oldNameString,newName)+"\n";
                    newFile.append(newLine);     
//                    System.out.println(line.replaceFirst(",Welcome to Sheridan College in Canada,","9"));
                }else{
                    newFile.append(line+"\n");
                }
            }
            status = Tool.replace(newFile.toString());
        } catch (IOException e) {
            Tool.writeLog(logSdf.format(new Date())+": VideoDaoImpl.java can not update video name");
            return false;
        }finally{
            Tool.closeReader(reader);
        }
        return status;
    }

}