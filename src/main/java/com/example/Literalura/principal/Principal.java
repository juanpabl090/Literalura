package com.example.Literalura.principal;

import com.example.Literalura.Model.Book;
import com.example.Literalura.Model.ResponseApiBook;
import com.example.Literalura.Repository.AuthorRepository;
import com.example.Literalura.Repository.BookRepository;
import com.example.Literalura.helpers.DataConverter;
import com.example.Literalura.service.ApiGutendexService;

import java.util.Optional;
import java.util.Scanner;

import static java.lang.System.out;

public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private final ApiGutendexService apiGutendexService = new ApiGutendexService();
    private final DataConverter dataConverter = new DataConverter();
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private String nombreLibro;

    public Principal(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void menu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                     1 - Buscar Libros\s
                     2 - mostrar Libros\s
                     3 - mostrar Autores Registrados\s
                     4 - mostrar autores vivos en un determinado año\s
                     5 - listar libros por idioma\s
                     \s
                     0 - Salir
                    \s""";
            out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    getDataFromApi();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarlibrosporidioma();
                    break;
                case 0:
                    out.println("Cerrando la aplicación...");
                    break;
                default:
                    out.println("Opción inválida");
            }
        }
    }

    /**
     * 1.- Pregunta al usuario que libro desea buscar
     * 2.- convierte los datos a la clase ResponseApiBook.class
     * 3.- filtra los resultados, para que coincida con el nombre del libro dado
     * 3.1 - selecciona solo el primero
     * 3.2 - mapea los datos en la clase Book
     * 4- retorna los datos
     */
    private void getDataFromApi() {
        out.println("Escribe el nombre de la serie que deseas buscar");
        nombreLibro = teclado.nextLine();
        String json = apiGutendexService.obtenerDatos(nombreLibro);
        var datosApi = dataConverter.obtenerDatos(json, ResponseApiBook.class);
        Optional<Book> data = datosApi.books().stream()
                .filter(book1 -> book1.title().equalsIgnoreCase(nombreLibro))
                .findFirst()
                .map(bookApi -> {
                    Book book = new Book();
                    book.setId(bookApi.id());
                    book.setTitle(bookApi.title());
                    book.setAuthors(bookApi.authors());
                    book.setLanguages(bookApi.languages());
                    book.setDownload_count(bookApi.download_count());
                    bookRepository.save(book);
                    return book;
                });
        data.ifPresent(out::println);
    }

    /**
     * listar libros registrados
     */
    private void mostrarLibrosRegistrados() {
        bookRepository.findAll().forEach(out::println);
    }

    /**
     * mostrar autores registrados
     */
    private void mostrarAutoresRegistrados() {
        authorRepository.findAll().forEach(out::println);
    }

    /**
     * Mostrar autores vivos en un determinado año
     */
    private void listarAutoresVivos() {
        out.println("Ingresa el año que quieres consultar si alguien esta/estuvo vivo");
        var year = teclado.nextLong();
        teclado.nextLine();
        authorRepository.listarAutoresVivosEnDeterminadoAño(year).forEach(out::println);
    }

    /**
     * listar libros por idioma
     */
    private void listarlibrosporidioma() {
        out.println("no disponible");
    }
}