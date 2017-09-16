/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service;

import java.util.List;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.core.exceptions.RecordExistsException;

/**
 *
 * @author kmb
 */
public class UserService {
    private static final Logger logger = 
			Logger.getLogger(UserService.class.getName());
    
    private DAOFactoryImpl factory;
    private UserDao userDao;
    
    public UserService(){
        factory = new DAOFactoryImpl();
        userDao = factory.getUserDAO();
    }
    
    public List<User> findAllUsers() throws Exception{
        return userDao.loadAll();
    }
    
    public void processCreate(User user) throws Exception{
        try {
            userDao.getObject(user.getId());
            throw new RecordExistsException("User already exists.");
        } catch (NotFoundException e) {
            userDao.create(user);
        }
    }
    
    public void processModify(User user) throws Exception{
        userDao.save(user);
    }
    
    public void delete(String userId) throws Exception{
        User user = new User();
        user.setId(userId);
        userDao.delete(user);
    }
}
