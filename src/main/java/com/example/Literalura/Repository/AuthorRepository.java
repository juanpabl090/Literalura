package com.example.Literalura.Repository;

import com.example.Literalura.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a WHERE a.birth_year <= :year AND (a.death_year IS NULL OR a.death_year > :year)")
    List<Author> listarAutoresVivosEnDeterminadoAño(@Param("year") Long year);
}
