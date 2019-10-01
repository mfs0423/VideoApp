/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Posters;

/**
 *
 * @author Meng
 */
public interface PosterDao {
    Posters getPoster(String videoId);
    boolean addPoster(Posters p);
}
