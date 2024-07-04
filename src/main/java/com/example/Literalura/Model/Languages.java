package com.example.Literalura.Model;

public enum Languages {
    en("en"),
    es("es"),
    hu("hu"),
    fr("fr"),
    fi("fi");

    private final String idiom;

    Languages(String idiom) {
        this.idiom = idiom;
    }

    public static Languages fromString(String text) {
        for (Languages language : Languages.values()) {
            if (language.idiom.equalsIgnoreCase(text)) {
                return language;
            }
        }
        throw new IllegalArgumentException("No enum constant for code: " + text);
    }

    public String getIdiom() {
        return idiom;
    }
}