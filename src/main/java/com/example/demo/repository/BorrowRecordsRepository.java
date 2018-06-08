package com.example.demo.repository;

import com.example.demo.entity.BorrowRecords;
import javassist.compiler.ast.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface BorrowRecordsRepository extends JpaRepository<BorrowRecords,Long>{
    @Query("select count(id) from BorrowRecords where user_id = :id")
    int findById(@Param("id") String id);

    @Query("select bookName,returnTime,borrowTime,shouldTime from BorrowRecords where user_id = :id and status = :status")
    List<BorrowRecords> findContentById(@Param("id") Integer id,@Param("status") Integer status);

    @Query("select bookName,returnTime,borrowTime,shouldTime from BorrowRecords where user_id = :id and status = 1")
    List<BorrowRecords> findContentByIdAndSatus(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("update BorrowRecords set status = 0,returnTime = :date where book_info_id = :bookId")
    int updateStatusByBookId(@Param("bookId") Integer bookId,@Param("date") Date date);

    @Query("select id,bookInfoId,bookName,returnTime,borrowTime,shouldTime from BorrowRecords where book_name like CONCAT('%',:keyword,'%')")
    List<BorrowRecords> searchRecords(@Param("keyword") String keyword);
}
