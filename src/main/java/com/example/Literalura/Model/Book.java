package com.example.Literalura.Model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Author> authors;

    @Enumerated(EnumType.STRING)
    private List<Languages> languages;
    private Integer download_count;

    public Book() {
    }

    public Book(Long id, String title, List<Author> authors, List<Languages> languages, Integer download_count) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.languages = languages;
        this.download_count = download_count;
        setAuthors(authors);
    }

    public Book(Optional<Book> datos) {
        this.id = datos.get().getId();
        this.title = datos.get().getTitle();
        this.authors = datos.get().getAuthors();
        this.languages = datos.get().getLanguages();
        this.download_count = datos.get().getDownload_count();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
        for (Author author : authors) {
            author.setBook(this);
        }
    }

    public List<Languages> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Languages> languages) {
        this.languages = languages;
    }

    public Integer getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Integer download_count) {
        this.download_count = download_count;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", languages=" + languages +
                ", download_count=" + download_count +
                '}';
    }
}
