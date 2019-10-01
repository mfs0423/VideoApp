/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static controller.LibraryController.videoName;
import entity.Video;
import java.util.ArrayList;

/**
 *
 * @author Yunmi Song
 */
public interface VideoDao {
    Video getVideo(String names);
    Video getVideoById(String id);
    boolean addVideo(Video video);    
    boolean updateVideo(Video video);
    ArrayList<Video> getVideosByName(String videoName);
    boolean changeVideoName(String oldNameString, String newName);
}
