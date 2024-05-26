package org.example.services;

import org.example.entities.LibraryRecord;
import org.example.repositories.LibraryRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private LibraryRecordRepository libraryRecordRepository;

    public List<LibraryRecord> getAvailableBooks() {
        return libraryRecordRepository.findByDueAtIsNull();
    }

    public LibraryRecord borrowBook(Long bookId) {
        LibraryRecord record = new LibraryRecord();
        record.setBookId(bookId);
        record.setBorrowedAt(LocalDateTime.now());
        return libraryRecordRepository.save(record);
    }

    public LibraryRecord returnBook(Long recordId) {
        return libraryRecordRepository.findById(recordId).map(record -> {
            record.setDueAt(LocalDateTime.now());
            return libraryRecordRepository.save(record);
        }).orElseThrow(() -> new RuntimeException("Record not found"));
    }
}
