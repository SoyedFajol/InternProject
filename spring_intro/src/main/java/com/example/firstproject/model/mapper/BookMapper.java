package com.example.firstproject.model.mapper;

import com.example.firstproject.model.dto.BookDTO;
import com.example.firstproject.model.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public Book toEntity(BookDTO dto) {
        Book book = new Book();
        book.setName(dto.getName());
        return book;
    }

    public BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setName(book.getName());
        return dto;
    }
}
