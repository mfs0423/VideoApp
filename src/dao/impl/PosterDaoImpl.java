/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.PosterDao;
import entity.Posters;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import utils.Tool;

/**
 *
 * @author Meng
 */
public class PosterDaoImpl implements PosterDao{
    private String path = "src/resource/Posters.csv";
    private static SimpleDateFormat logSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Override
    public Posters getPoster(String videoId) {
                BufferedReader reader = Tool.getReader(path);        
        Posters poster = null;
        String line = null;
        try {                     
            while((line=reader.readLine())!=null){
                String[] result = line.split(",");
                String video = result[0];
                if(video.equals(videoId)){
                    poster = new Posters(result[1]);
                    poster.setVideoId(result[0]);
                    poster.setUrl(result[1]);
                }
            }
        } catch (IOException e) {
            Tool.writeLog(logSdf.format(new Date())+": PosterDaoImpl.java can not get poster");
        }finally{
            Tool.closeReader(reader);
        }      
        return poster;
    }

    @Override
    public boolean addPoster(Posters p) {
        Tool.path = path;
        boolean success = Tool.write(p.getVideoId()+","+p.getUrl());
        if(!success) Tool.writeLog(logSdf.format(new Date())+": PosterDaoImpl.java can not add poster");
        return success;
    }
    
}
