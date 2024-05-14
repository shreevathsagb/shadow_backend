package com.example.shadowspring;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface  NotifyRepo  extends  JpaRepository<Notification, Integer> {

	   @Query("SELECT p from Notification p WHERE p.status=1")
	    List<Notification> getNotify();
	
}
