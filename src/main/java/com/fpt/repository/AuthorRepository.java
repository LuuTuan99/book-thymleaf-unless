package com.fpt.repository;

import com.fpt.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("select a from Author as a where a.status = :status")
    List<Author> findAllByAuthor(@Param("status") int status);

    List<Author> findByName(String term);
}
