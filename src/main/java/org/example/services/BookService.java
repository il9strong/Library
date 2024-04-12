package org.example.services;

import org.example.entities.Book;
import org.example.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

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
        return bookRepository.save(book);
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