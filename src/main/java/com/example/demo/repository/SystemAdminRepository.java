package com.example.demo.repository;

import com.example.demo.entity.BookAdmin;
import com.example.demo.entity.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SystemAdminRepository extends JpaRepository<SystemAdmin,Long> {
    @Query("select id,name from SystemAdmin where name = :name and password = :password")
    List<SystemAdmin> findByUserName(@Param("name") String name, @Param("password") String password);

    @Query("select phone,email from SystemAdmin where id = :id")
    List<SystemAdmin> getSystem(@Param("id") long id);

    @Query("select password from SystemAdmin where id = :id")
    String getPassword(@Param("id") long id);

    @Modifying
    @Transactional
    @Query("update SystemAdmin set password = :password,phone = :phone,email = :email where id = :id")
    int updateSystemInfo(@Param("id") long id,@Param("password") String password,@Param("phone") String phone,@Param("email") String email);
}
