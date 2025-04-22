package com.example.firstproject.Model.Entity.Mapper;

import com.example.firstproject.Model.Entity.DTO.BookDTO;
import com.example.firstproject.Model.Entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public Book toEntity(BookDTO dto) {
        Book book = new Book();
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        return book;
    }

    public BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setName(book.getName());
        dto.setAuthor(book.getAuthor());
        return dto;
    }
}
