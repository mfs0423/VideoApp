/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entity.Video;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import service.IndexPageService;
import service.impl.IndexPageServiceImpl;

/**
 *
 * @author Meng
 */
public class ShowPosters {
    private static IndexPageService indexPageServiceImpl = new IndexPageServiceImpl();
    public static ArrayList<Video> changeImages(Pane pane, String type){  
        if(type.trim().equals("Popular")){
            return indexPageServiceImpl.sortLibraryByViews();
        }else if(type.trim().equals("Date")){
            return indexPageServiceImpl.sortLibraryByDate();
        }else{
            return indexPageServiceImpl.sortLibraryByRating();
        }
    }
    public static void addImages(Pane pane){
        ArrayList<Video> popularVideos = indexPageServiceImpl.sortLibraryByViews();
        ArrayList<Video> newVideos = indexPageServiceImpl.sortLibraryByDate();
        ImageView pview1 = (ImageView)pane.getChildren().get(0);
        ImageView pview2 = (ImageView)pane.getChildren().get(1);
        ImageView pview3 = (ImageView)pane.getChildren().get(2);
        ImageView pview4 = (ImageView)pane.getChildren().get(3);
        ImageView pview5 = (ImageView)pane.getChildren().get(4);
        ImageView pview6 = (ImageView)pane.getChildren().get(5);

        pview1.setImage(indexPageServiceImpl.getPoster(popularVideos.get(0).getId()));
        pview2.setImage(indexPageServiceImpl.getPoster(popularVideos.get(1).getId()));
        pview3.setImage(indexPageServiceImpl.getPoster(popularVideos.get(2).getId()));
        pview4.setImage(indexPageServiceImpl.getPoster(popularVideos.get(3).getId()));
        pview5.setImage(indexPageServiceImpl.getPoster(popularVideos.get(4).getId()));
        pview6.setImage(indexPageServiceImpl.getPoster(popularVideos.get(5).getId()));

        ImageView nview7 = (ImageView)pane.getChildren().get(6);
        ImageView nview8 = (ImageView)pane.getChildren().get(7);
        ImageView nview9 = (ImageView)pane.getChildren().get(8);
        ImageView nview10 = (ImageView)pane.getChildren().get(9);
        ImageView nview11 = (ImageView)pane.getChildren().get(10);
        ImageView nview12 = (ImageView)pane.getChildren().get(11);

        nview7.setImage(indexPageServiceImpl.getPoster(newVideos.get(0).getId()));
        nview8.setImage(indexPageServiceImpl.getPoster(newVideos.get(1).getId()));
        nview9.setImage(indexPageServiceImpl.getPoster(newVideos.get(2).getId()));
        nview10.setImage(indexPageServiceImpl.getPoster(newVideos.get(3).getId()));
        nview11.setImage(indexPageServiceImpl.getPoster(newVideos.get(4).getId()));
        nview12.setImage(indexPageServiceImpl.getPoster(newVideos.get(5).getId()));
    }
}
