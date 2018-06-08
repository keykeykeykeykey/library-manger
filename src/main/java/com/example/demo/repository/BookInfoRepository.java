package com.example.demo.repository;

import com.example.demo.entity.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface BookInfoRepository extends JpaRepository<BookInfo,Long> {
    @Query("select id from BookInfo where id = :userBorrowBookNum")
    int getBookIdById(@Param("userBorrowBookNum") Integer userBorrowBookNum);

    @Query("select status from BookInfo where id = :userBorrowBookNum")
    int getBookStstusById(@Param("userBorrowBookNum") Integer userBorrowBookNum);

    @Query("select bookName from BookInfo where id = :userBorrowBookNum")
    String getBookNameById(@Param("userBorrowBookNum") Integer userBorrowBookNum);

    @Modifying
    @Transactional
    @Query("update BookInfo set status = :status where id = :userBorrowBookNum")
    int updateStatusById(@Param("userBorrowBookNum") Integer userBorrowBookNum,@Param("status") Integer status);

    @Query("select id,bookName,author,translator,price,ISBNCode,publishCompany,comeUpTime,enteringMen from BookInfo where id = :id")
    List<BookInfo> getBookById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("delete from BookInfo where id = :id")
    int deleteBookById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("update BookInfo set bookName = :changeBookName,author = :changeBookAuthor, " +
            "translator = :changeBookTranslator, price = :changeBookPrice ,ISBNCode = :changeBookISBN, " +
            "publishCompany = :changeBookPublish,comeUpTime = :changeBookPublishTime,enteringMen = :changeBookEnterMen where id = :id")
    int updateBookById(@Param("id") Integer id,
                       @Param("changeBookName") String changeBookName, @Param("changeBookAuthor") String changeBookAuthor,
                       @Param("changeBookTranslator") String changeBookTranslator, @Param("changeBookPrice") Float changeBookPrice,
                       @Param("changeBookISBN") String changeBookISBN, @Param("changeBookPublish") String changeBookPublish,
                       @Param("changeBookPublishTime") Date changeBookPublishTime, @Param("changeBookEnterMen") String changeBookEnterMen);

    @Query("select id,bookName,author,translator,price,ISBNCode,publishCompany,comeUpTime,enteringMen,enteringDate,status from BookInfo where book_name like CONCAT('%',:keyword,'%')")
    List<BookInfo> searchBookInfo(@Param("keyword") String keyword);

    @Query("select id,bookName,author,translator,price,ISBNCode,publishCompany,comeUpTime,enteringMen,enteringDate from BookInfo where book_name like CONCAT('%',:keyword,'%') and status = 0")
    List<BookInfo> searchBookInfoAndStatus(@Param("keyword") String keyword);
}
