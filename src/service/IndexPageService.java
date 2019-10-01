/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Posters;
import entity.Video;
import java.util.ArrayList;

/**
 *
 * @author Yunmi Song
 */
public interface IndexPageService {
    ArrayList<Video> sortLibraryByViews();
    ArrayList<Video> sortLibraryByDate();
    ArrayList<Video> sortLibraryByRating();
    Posters getPoster(String id);
    ArrayList<Video> getLibrary();
}
