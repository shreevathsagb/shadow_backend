package com.example.shadowspring;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepo extends JpaRepository<Comments, Integer>{
	@Query("SELECT c FROM Comments c WHERE c.postid = ?1")
	List<Comments> findByPostId(int postid);
}
