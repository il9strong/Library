package org.example.services;

import org.example.entities.Book;
import org.example.entities.LibraryRecord;
import org.example.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Optional<Book> getBookByISBN(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Book createBook(Book book) {
        Book savedBook = bookRepository.save(book);
        // Отправка запроса в LibraryService
        restTemplate.postForObject("http://localhost:8080/api/library/borrow?bookId=" + savedBook.getId(), null, LibraryRecord.class);
        return savedBook;
    }

    public Optional<Book> updateBook(Long id, Book bookDetails) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setDescription(bookDetails.getDescription());
            book.setGenre(bookDetails.getGenre());
            book.setIsbn(bookDetails.getIsbn());
            return bookRepository.save(book);
        });
    }

    public void deleteBook(Long id) {
        bookRepository.findById(id).ifPresent(book -> bookRepository.delete(book));
    }
}