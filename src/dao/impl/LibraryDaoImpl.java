/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.CommentsDao;
import dao.LibraryDao;
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
public class LibraryDaoImpl implements LibraryDao{
    private String path = "src/resource/Library.csv";
    private static SimpleDateFormat logSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
   
    @Override
    public ArrayList<Video> getLibrary() {
        ArrayList<Video> library = new ArrayList<>();
        CommentsDao commentsDaoImpl = new CommentsDaoImpl();
        BufferedReader reader = Tool.getReader(path);
        String line = null;
        try {           
            while((line=reader.readLine())!= null){
                String[] result = line.split(",");
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
                library.add(video);
            }
            return library;
            
        } catch (IOException e) {
            Tool.writeLog(logSdf.format(new Date())+": LibraryDaoImpl.java can not get library");
            return null;
        } catch(RuntimeException ex){
            Tool.writeLog(logSdf.format(new Date())+": LibraryDaoImpl.java can not get library");
            return null;
        }finally{
            Tool.closeReader(reader);
        }      
        
    }

    @Override
    public ArrayList<Video> sortByDate() {
        ArrayList<Video> videos = getLibrary();
        for(int i=0;i<videos.size();i++){
            for(int j=1;j<videos.size()-i;j++){
                if(videos.get(j-1).getDate().getTime()<videos.get(j).getDate().getTime()){
                    Video v = videos.get(j-1);
                    videos.set((j-1),videos.get(j));
                    videos.set(j,v);
                }
            }
        }
        return videos;
    }

    @Override
    public ArrayList<Video> sortByViews() {
        ArrayList<Video> videos = getLibrary();
       
        for(int i=0;i<videos.size();i++){
            for(int j=1;j<videos.size()-i;j++){
                if(videos.get(j-1).getViews()<videos.get(j).getViews()){
                    Video v = videos.get(j-1);
                    videos.set((j-1),videos.get(j));
                    videos.set(j,v);
                }
            }
        }
        return videos;
    }
   
    @Override
    public ArrayList<Video> sortByRating() {
        ArrayList<Video> videos = getLibrary();
        for(int i=0;i<videos.size();i++){
            for(int j=1;j<videos.size()-i;j++){
                if(videos.get(j-1).getRating()<videos.get(j).getRating()){
                    Video v = videos.get(j-1);
                    videos.set((j-1),videos.get(j));
                    videos.set(j,v);
                }
            }
        }
        return videos;
    }
    

}
