package com.example.shadowspring;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface Postrepo extends JpaRepository<Post, Integer> {

    @Transactional
    @Modifying
    @Query("update Post p set p.likes = p.likes + 1 where p.id = ?1")
    int setAddLike(int id);
    
    @Query("SELECT p FROM Post p WHERE p.report < 10 ORDER BY p.id DESC")
    List<Post> findAllOrderByPriceDesc();

    @Query("SELECT p FROM Post p ORDER BY p.report DESC")
    List<Post> findAllbyreport();

        
    @Query("SELECT p from Post p WHERE p.username=?1 ")
    List<Post> findPostu(String username);
    
    @Query("SELECT p from Post p WHERE p.genre=?1 ")
    List<Post> Search(String genre);
    
    @Transactional
    @Modifying
    @Query("update Post p set p.likes = p.likes - 1 where p.id = ?1")
    int setMinusLike(int id);
    
    @Transactional
    @Modifying
    @Query("update Post p set p.report = p.report + 1 where p.id = ?1")
    int setPlusreport(int id);
}
