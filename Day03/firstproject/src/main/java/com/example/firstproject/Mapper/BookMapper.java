package com.example.firstproject.Mapper;

import com.example.firstproject.DTO.BookDTO;
import com.example.firstproject.Entity.Book;
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