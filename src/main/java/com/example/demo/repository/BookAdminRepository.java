package com.example.demo.repository;

import com.example.demo.entity.BookAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookAdminRepository extends JpaRepository<BookAdmin,Long> {
    @Query("select id,email,phone,name from BookAdmin where name = :name and password = :password")
    List<BookAdmin> findByUserName(@Param("name") String name, @Param("password") String password);

    @Query("select id,email,phone from BookAdmin where id = :id")
    List<BookAdmin> getAdminById(@Param("id") Integer id);

    @Query("select password from BookAdmin where id = :id")
    String getPasswordById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("update BookAdmin set password = :newPassword,phone = :phone,email = :email where id = :id")
    int updateInfo(@Param("id") Integer id,@Param("newPassword") String newPassword,@Param("phone") String phone,@Param("email") String email);

    @Query("select id from BookAdmin where id = :id")
    int getIdById(@Param("id") Integer id);

    @Query("select name,email,phone from BookAdmin where id = :adminNum")
    List<BookAdmin> sureAdmin(@Param("adminNum") Integer adminNum);

    @Modifying
    @Transactional
    @Query("delete from BookAdmin where id = :id")
    int deleteAdmin(@Param("id") Integer id);

    @Query("select id,name,email,phone from BookAdmin where id like CONCAT('%',:keyword,'%')")
    List<BookAdmin> searchAdminInfo(@Param("keyword") String keyword);

    @Modifying
    @Transactional
    @Query("update BookAdmin set name = :adminName,phone = :adminPhone,email = :adminEmail where id = :id")
    int updateAdmin(@Param("id") Integer id,@Param("adminName") String adminName,@Param("adminPhone") String adminPhone,@Param("adminEmail") String adminEmail);
}