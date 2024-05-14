package com.example.shadowspring;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface Repo extends JpaRepository<Entity, String> {
	@Query("SELECT u FROM Entity u WHERE u.username = ?1")
	Entity getUserByUsername(String username);
	@Query("SELECT  u FROM Entity u WHERE u.email =?1")
	Optional<Entity> getUserByEmail(String email);
	    @Transactional
	  	@Modifying
	    @Query("UPDATE Entity e SET e.status = :status WHERE e.username = :username")
	    int setBlocked(@Param("status") String status, @Param("username") String username);	
	}

