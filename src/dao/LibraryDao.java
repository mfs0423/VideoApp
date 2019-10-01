/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Video;
import java.util.ArrayList;

/**
 *
 * @author Yunmi Song
 */
public interface LibraryDao {
    ArrayList<Video> getLibrary();
    ArrayList<Video> sortByDate();
    ArrayList<Video> sortByViews();
    ArrayList<Video> sortByRating();
}
