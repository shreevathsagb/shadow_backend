package com.example.shadowspring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Profilerepo extends  JpaRepository<Profile, String>{

}
