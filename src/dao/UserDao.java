/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;

/**
 *
 * @author Fansheng Meng
 */
public interface UserDao {
    User getUser(String name);
    boolean addUser(User user);
    boolean updateUser(String oldName, String name, String pwd, String email);
}
