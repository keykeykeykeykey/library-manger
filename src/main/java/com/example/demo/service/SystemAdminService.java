package com.example.demo.service;

import com.example.demo.entity.BookAdmin;
import com.example.demo.entity.SystemAdmin;
import com.example.demo.repository.BookAdminRepository;
import com.example.demo.repository.SystemAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemAdminService {
    @Autowired
    private SystemAdminRepository systemAdminRepository;

    public List<SystemAdmin> findUserByName(String name, String password){
        try{
            return systemAdminRepository.findByUserName(name,password);
        }catch (Exception e){}
        return null;
    }

    public List<SystemAdmin> getSystem(long id){
        try{
            return systemAdminRepository.getSystem(id);
        }catch (Exception e){}
        return null;
    }

    public String getPassword(long id){
        try{
            return systemAdminRepository.getPassword(id);
        }catch (Exception e){}
        return null;
    }

    public int updateSystemInfo(long id,String password,String phone,String email){
        try{
            return systemAdminRepository.updateSystemInfo(id,password,phone,email);
        }catch (Exception e){}
        return 0;
    }
}
