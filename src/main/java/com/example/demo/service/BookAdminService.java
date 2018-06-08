package com.example.demo.service;

import com.example.demo.entity.BookAdmin;
import com.example.demo.repository.BookAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookAdminService {
    @Autowired
    private BookAdminRepository bookAdminRepository;

    public List<BookAdmin> findUserByName(String name, String password){
        try{
            return bookAdminRepository.findByUserName(name,password);
        }catch (Exception e){}
        return null;
    }

    public List<BookAdmin> getAdminById(Integer id){
        try{
            return bookAdminRepository.getAdminById(id);
        }catch (Exception e){}
        return null;
    }

    public String getPasswordById(Integer id){
        try{
            return bookAdminRepository.getPasswordById(id);
        }catch (Exception e){}
        return null;
    }

    public int updateInfo(Integer id,String newPassorrd,String phone,String email){
        try{
            return bookAdminRepository.updateInfo(id,newPassorrd,phone,email);
        }catch (Exception e){}
        return 0;
    }

    public int getIdById(Integer id){
        try{
            return bookAdminRepository.getIdById(id);
        }catch (Exception e){}
        return 0;
    }

    public BookAdmin save(BookAdmin bookAdmin){
        try{
            return bookAdminRepository.save(bookAdmin);
        }catch (Exception e){}
        return null;
    }

    public List<BookAdmin> sureAdmin(Integer adminNum){
        try{
            return bookAdminRepository.sureAdmin(adminNum);
        }catch (Exception e){}
        return null;
    }

    public int deleteAdmin(Integer id){
        try{
            return bookAdminRepository.deleteAdmin(id);
        }catch (Exception e){}
        return 0;
    }

    public int updateAdmin(Integer id,String name,String phone,String email){
        try{
            return bookAdminRepository.updateAdmin(id,name,phone,email);
        }catch (Exception e){}
        return 0;
    }

    public List<BookAdmin> searchAdminInfo(String keyword){
        try{
            return bookAdminRepository.searchAdminInfo(keyword);
        }catch (Exception e){}
        return null;
    }
}
