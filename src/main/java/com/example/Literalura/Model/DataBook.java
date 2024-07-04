package com.example.Literalura.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataBook(
        @JsonProperty("id") Long id,
        @JsonProperty("title") String title,
        @JsonProperty("authors") List<Author> authors,
        @JsonProperty("languages") List<Languages> languages,
        @JsonProperty("download_count") Integer download_count) {
    @Override
    public String toString() {
        return "DatosBook{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", languages=" + languages +
                ", download_count=" + download_count +
                '}';
    }
}