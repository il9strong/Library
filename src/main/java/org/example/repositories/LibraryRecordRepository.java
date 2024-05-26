package org.example.repositories;

import org.example.entities.LibraryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRecordRepository extends JpaRepository<LibraryRecord, Long> {
    List<LibraryRecord> findByDueAtIsNull();
}
