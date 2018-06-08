package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    @Query("select id,name from User where name = :name and password = :password")
    List<User> findByUserName(@Param("name") String name,@Param("password") String password);

    @Query("select name from User c where c.id = :id")
    List<User> findNameById(@Param("id") String id);

    @Modifying
    @Transactional
    @Query("update User set password = :newPassword where id = :id and password = :password")
    int changepwd(@Param("id") Integer id,@Param("password") String password,@Param("newPassword") String newPassword);
}
