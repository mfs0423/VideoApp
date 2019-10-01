/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import service.LoginPageService;

/**
 *
 * @author Yunmi Song
 */
public class LoginPageServiceImpl implements LoginPageService{

    private UserDao userDaoImpl = new UserDaoImpl();
    
    @Override
    public User findUserByName(String userName) {
        return userDaoImpl.getUser(userName);
    }
    
}
