package com.thi.challenge.litteratura.Repository;

import com.thi.challenge.litteratura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);

    @Query("SELECT b FROM Book b JOIN FETCH b.authors")
    List<Book> findAllWithBooks();

    @Query(value = "SELECT * FROM books WHERE :idiom = ANY(languages)", nativeQuery = true)
    List<Book> findBooksByIdiom(@Param("idiom") String idiom);
}
