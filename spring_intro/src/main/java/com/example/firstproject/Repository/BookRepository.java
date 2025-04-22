package com.example.firstproject.Repository;

import com.example.firstproject.Model.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
