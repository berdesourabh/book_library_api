package com.books.app.repository;

import com.books.app.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT COUNT(b.id) > 0 FROM Book b " +
            "WHERE (COALESCE (NULL, :id) IS NULL OR b.id = :id )" +
            "OR (COALESCE (NULL, :name) IS NULL OR b.id = :name )" +
            "AND (COALESCE (NULL, :author) IS NULL OR b.id = :author )")
    public boolean checkIfBookExists(@Param("id") Long id, @Param("name") String name, @Param("author") String author);
}
