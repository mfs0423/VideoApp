/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.User;

/**
 *
 * @author Yunmi Song
 */
public interface RegisterPageService {
    
    User findUserByName(String name);
    boolean addUser(User newUser);
    boolean updateUser(String oldName, String name,String pwd, String email);
}
