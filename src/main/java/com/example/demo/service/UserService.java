package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepositoty;

    public List<User> findUserByName(String name,String password){
        try{
            return userRepositoty.findByUserName(name,password);
        }catch (Exception e){}
        return null;
    }

    public List<User> findNameById(String id){
        try{
            return userRepositoty.findNameById(id);
        }catch (Exception e){}
        return null;
    }

    public int changepwd(Integer id,String password,String newPassword){
        try{
            return userRepositoty.changepwd(id,password,newPassword);
        }catch (Exception e){}
        return 0;
    }
}
