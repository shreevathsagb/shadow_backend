package com.example.shadowspring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  NotifyRepo  extends  JpaRepository<Notification, Integer> {

}
