/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.LibraryDao;
import dao.PosterDao;
import dao.impl.LibraryDaoImpl;
import dao.impl.PosterDaoImpl;
import entity.Posters;
import entity.Video;
import java.util.ArrayList;
import service.IndexPageService;

/**
 *
 * @author Yunmi Song
 */
public class IndexPageServiceImpl implements  IndexPageService{
    private LibraryDao libraryDaoImpl = new LibraryDaoImpl();
    private PosterDao posterDaoImpl = new PosterDaoImpl();
    
    @Override
    public ArrayList<Video> sortLibraryByViews() {
        return libraryDaoImpl.sortByViews();
    }

    @Override
    public ArrayList<Video> sortLibraryByDate() {
        return libraryDaoImpl.sortByDate();
    }

    @Override
    public ArrayList<Video> sortLibraryByRating() {
        return libraryDaoImpl.sortByRating();
    }

    @Override
    public Posters getPoster(String id) {
        return posterDaoImpl.getPoster(id);
    }
    @Override
    public ArrayList<Video> getLibrary(){
        return libraryDaoImpl.getLibrary();
    }
}
