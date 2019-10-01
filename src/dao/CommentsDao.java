/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Comments;
import java.util.ArrayList;


/**
 *
 * @author Fansheng Meng
 */
public interface CommentsDao {
    ArrayList<Comments> getComments(String videoId);
    boolean addComment(int videoId, String name,StringBuffer content);
}
