/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.CommentsDao;
import entity.Comments;
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
public class CommentsDaoImpl implements CommentsDao{
    private static SimpleDateFormat logSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    private String path = "src/resource/Comments.csv";
    
    @Override
    public ArrayList<Comments> getComments(String videoId) {
        ArrayList<Comments> comments = new ArrayList<Comments>();
        BufferedReader reader = Tool.getReader(path);  
        String line = null;
        try {                     
            while((line=reader.readLine())!= null){
                Comments comment = new Comments();
                String[] result = line.split(",");
                comment.setVideoId(result[0]);
                comment.setUser(result[1]);
                comment.setDate(Tool.getDate(result[2]));
                comment.setContent(new StringBuffer(result[3]));
                comments.add(comment);
            }
        } catch (IOException e ) {
            Tool.writeLog(logSdf.format(new Date())+": CommentsDaoImpl.java can not get comments");
            return null;
        } catch(RuntimeException ex){
            Tool.writeLog(logSdf.format(new Date())+": CommentsDaoImpl.java can not get comments");
            return null;
        }finally{
            Tool.closeReader(reader);
        } 
        return comments;
    }

    @Override
    public boolean addComment(int videoId, String name, StringBuffer content) {
        Tool.path = path;
        boolean success = Tool.write(videoId+","+name+","+Tool.getDate(new Date())+","+content.toString());
        if(!success) Tool.writeLog(logSdf.format(new Date())+": CommentsDaoImpl.java can not add Video");
        return success;
    }
    
}
