package com.example.Literalura.Repository;

import com.example.Literalura.Model.Book;
import com.example.Literalura.Model.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
}