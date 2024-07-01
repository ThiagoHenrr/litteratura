package com.thi.challenge.litteratura.Repository;

import com.thi.challenge.litteratura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByName(String authorsName);

    @Query("SELECT a FROM Author a WHERE CAST(a.birthYear AS int) <= :year AND (a.deathYear IS NULL OR CAST(a.deathYear AS int) > :year)")
    List<Author> findLivingAuthorsByYear(@Param("year") Integer year);
}
