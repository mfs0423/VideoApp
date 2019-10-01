/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import entity.User;
import service.RegisterPageService;

/**
 *
 * @author Yunmi Song
 */
public class RegisterPageServiceImpl implements RegisterPageService{
    UserDao userDaoImpl = new UserDaoImpl();
    @Override
    public User findUserByName(String name){
        return userDaoImpl.getUser(name);
    }

    @Override
    public boolean addUser(User newUser) {
        return userDaoImpl.addUser(newUser);
    }

    @Override
    public boolean updateUser(String oldName, String name, String pwd, String email) {
        return userDaoImpl.updateUser(oldName, name,pwd,email);
    }
}
