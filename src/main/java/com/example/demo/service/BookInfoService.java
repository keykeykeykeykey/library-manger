package com.example.demo.service;

import com.example.demo.entity.BookInfo;
import com.example.demo.repository.BookInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookInfoService {
    @Autowired
    private BookInfoRepository bookInfoRepository;

    public int getBookIdById(Integer userBorrowBookNum){
        try{
            return bookInfoRepository.getBookIdById(userBorrowBookNum);
        }catch (Exception e){}
        return 0;
    }

    public int getBookStstusById(Integer userBorrowBookNum){
        try{
            return bookInfoRepository.getBookStstusById(userBorrowBookNum);
        }catch (Exception e){}
        return 0;
    }

    public String getBookNameById(Integer userBorrowBookNum){
        try{
            return bookInfoRepository.getBookNameById(userBorrowBookNum);
        }catch (Exception e){}
        return "";
    }

    public int updateStatusById(Integer userBorrowBookNum,Integer status){
        try{
            return bookInfoRepository.updateStatusById(userBorrowBookNum,status);
        }catch (Exception e){}
        return 0;
    }

    public List<BookInfo> getBookById(Integer id){
        try{
            return bookInfoRepository.getBookById(id);
        }catch (Exception e){}
        return null;
    }

    public BookInfo save(BookInfo bookInfo){
        try{
            return bookInfoRepository.save(bookInfo);
        }catch (Exception e){}
        return null;
    }

    public int deleteBookById(Integer id){
        try{
            return bookInfoRepository.deleteBookById(id);
        }catch (Exception e){}
        return 0;
    }

    public int updateBookById(Integer id,String changeBookName,String changeBookAuthor,
                                   String changeBookTranslator,Float changeBookPrice,
                                   String changeBookISBN,String changeBookPublish,
                                   Date changeBookPublishTime,String changeBookEnterMen){
        try{
            return bookInfoRepository.updateBookById(id,changeBookName,changeBookAuthor,changeBookTranslator,changeBookPrice,changeBookISBN,changeBookPublish,changeBookPublishTime,changeBookEnterMen);
        }catch (Exception e){}
        return 0;
    }

    public List<BookInfo> searchBookInfo(String keyword){
        try{
            return bookInfoRepository.searchBookInfo(keyword);
        }catch (Exception e){}
        return null;
    }

    public List<BookInfo> searchBookInfoAndStatus(String keyword){
        try{
            return bookInfoRepository.searchBookInfoAndStatus(keyword);
        }catch (Exception e){}
        return null;
    }
}
