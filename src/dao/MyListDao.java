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
public interface MyListDao {
    ArrayList<Video> getMyList();
    boolean addToMyList(int videoId);
    boolean removeFromMyList(int videoId);
}
