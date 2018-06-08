package com.example.demo.repository;

import com.example.demo.entity.BorrowRecords;
import com.example.demo.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    @Query("select id,lendedNum,departments,major,phone,email,maxNum,time from UserInfo where user_id = :id")
    List<UserInfo> findById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("update UserInfo set phone = :phone,email = :email where user_id = :id")
    int change(@Param("id") String id,@Param("phone") String phone,@Param("email") String email);

    @Query("select userId from UserInfo where lendedNum = :userBorrowNum")
    String getUserId(@Param("userBorrowNum") String userBorrowNum);

    @Query("select maxNum from UserInfo where lendedNum = :userBorrowNum")
    int getMaxNum(@Param("userBorrowNum") String userBorrowNum);

    @Query("select lendedNum from UserInfo where lendedNum = :addUserLendNum")
    int getLendNum(@Param("addUserLendNum") String addUserLendNum);

    @Query("select id,lendedNum,userId,departments,major,phone,email,maxNum,time from UserInfo where lended_num = :lendNum")
    List<UserInfo> sureUser(@Param("lendNum") String lendNum);

    @Modifying
    @Transactional
    @Query("delete from UserInfo where lended_num = :lendNum")
    int deleteUser(@Param("lendNum") String lendNum);

    @Modifying
    @Transactional
    @Query("update UserInfo set userId = :userId ,departments = :department,major = :major, phone = :phone, email = :email, maxNum = :maxNum,time = :time  where lended_num = :lendNum")
    int updateUser(@Param("lendNum") String lendNum,@Param("userId") long userId,
    @Param("department") String department,@Param("major") String major,@Param("phone") String phone,
    @Param("email") String email,@Param("maxNum") Integer maxNum,@Param("time") Integer time);

    @Query("select lendedNum,userId,departments,major,phone,email,maxNum,time from UserInfo where lended_num like CONCAT('%',:keyword,'%')")
    List<UserInfo> searchUserInfo(@Param("keyword") String keyword);
}
