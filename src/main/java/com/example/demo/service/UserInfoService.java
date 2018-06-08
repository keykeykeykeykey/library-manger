package com.example.demo.service;

import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    public List<UserInfo> findById(Integer id){
        try{
            return userInfoRepository.findById(id);
        }catch (Exception e){}
        return null;
    }

    public int change(String id,String phone,String email){
        try{
            return userInfoRepository.change(id,phone,email);
        }catch (Exception e){}
        return 0;
    }

    public String getUserId(String userBorrowNum){
        try{
            return userInfoRepository.getUserId(userBorrowNum);
        }catch (Exception e){}
        return "";
    }

    public int getMaxNum(String userBorrowNum){
        try{
            return userInfoRepository.getMaxNum(userBorrowNum);
        }catch (Exception e){}
        return 0;
    }

    public UserInfo save(UserInfo userInfo){
        try{
            return userInfoRepository.save(userInfo);
        }catch (Exception e){}
        return null;
    }

    public int getLendNum(String addUserLendNum){
        try{
            return userInfoRepository.getLendNum(addUserLendNum);
        }catch (Exception e){}
        return 0;
    }

    public List<UserInfo> sureUser(String lendNum){
        try{
            return userInfoRepository.sureUser(lendNum);
        }catch (Exception e){}
        return null;
    }

    public int deleteUser(String lendNum){
        try{
            return userInfoRepository.deleteUser(lendNum);
        }catch (Exception e){}
        return 0;
    }

    public int updateUser(String lendNum,long userId, String department,String major,String phone,
    String email,Integer maxNum,Integer time){
        try{
            return userInfoRepository.updateUser(lendNum,userId,department,major,phone,email,maxNum,time);
        }catch (Exception e){}
        return 0;
    }

    public List<UserInfo> searchUserInfo(String keyword){
        try{
            return userInfoRepository.searchUserInfo(keyword);
        }catch (Exception e){}
        return null;
    }
}
