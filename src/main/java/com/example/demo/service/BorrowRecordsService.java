package com.example.demo.service;

import com.example.demo.entity.BorrowRecords;
import com.example.demo.repository.BorrowRecordsRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BorrowRecordsService {
    @Autowired
    private BorrowRecordsRepository borrowRecordsRepository;

    public int findById(String id){
        try{
            return borrowRecordsRepository.findById(id);
        }catch (Exception e){}
        return 0;
    }

    public List<BorrowRecords> findContentById(Integer id,Integer status){
        try{
            return borrowRecordsRepository.findContentById(id,status);
        }catch (Exception e){}
        return null;
    }

    public List<BorrowRecords> findContentByIdAndSatus(Integer id){
        try{
            return borrowRecordsRepository.findContentByIdAndSatus(id);
        }catch (Exception e){}
        return null;
    }

    public BorrowRecords save(BorrowRecords borrowRecords){
        try{
            return borrowRecordsRepository.save(borrowRecords);
        }catch (Exception e){}
        return null;
    }

    public int updateStatusByBookId(Integer bookId,Date date){
        try{
            return borrowRecordsRepository.updateStatusByBookId(bookId,date);
        }catch (Exception e){}
        return 0;
    }

    public List<BorrowRecords> searchRecords(String keyword){
        try{
            return borrowRecordsRepository.searchRecords(keyword);
        }catch (Exception e){}
        return null;
    }
}
