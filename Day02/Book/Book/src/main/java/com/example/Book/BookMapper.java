package com.example.Book;


import org.springframework.stereotype.Component;

public class BookMapper {public Book map(BookDto dto) {
    Book book = new Book();
    book.setName(dto.getName());
    return book;
}

    public BookDto map(Book entity) {
        BookDto dto = new BookDto();
        dto.setName(book.getName());
        return dto;
    }
}
