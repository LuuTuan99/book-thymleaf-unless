package com.fpt.repository;

import com.fpt.entity.Book;
import com.fpt.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "SET FOREIGN_KEY_CHECKS=0;")
    void disableForeignKeyCheck();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "SET FOREIGN_KEY_CHECKS=1;")
    void enableForeignKeyCheck();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "alter table member AUTO_INCREMENT = 1")
    void resetIncrement();

    Optional<Member> findByEmail(String email);

    Optional<Member> findByUsername(String username);
}
