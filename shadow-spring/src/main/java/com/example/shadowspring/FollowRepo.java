//package com.example.shadowspring;
//
//import org.springframework.data.annotation.LastModifiedBy;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import jakarta.transaction.Transactional;
//
//@Repository
//public interface FollowRepo extends JpaRepository<Follow, Integer> {
//	boolean existsByFollowbyAndFollowto(String followby, String followto);
//	@Transactional
//    @LastModifiedBy
//    @Query("DELETE FROM YourEntity e WHERE e.followby = :followby AND e.followto = :followto")
//    void deleteByFollowbyAndFollowto(String followby, String followto);
//}
package com.example.shadowspring;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//import javax.transaction.Transactional;

@Repository
public interface FollowRepo extends JpaRepository<Follow, Integer> {

    boolean existsByFollowbyAndFollowto(String followby, String followto);

    @jakarta.transaction.Transactional
    @Modifying
    @Query("DELETE FROM Follow f WHERE f.followby = :followby AND f.followto = :followto")
    void deleteByFollowbyAndFollowto(String followby, String followto);
    

    @Query("SELECT f FROM Follow f WHERE f.followby = ?1")
    List<Follow> findByFollowTo(String followby);

}
