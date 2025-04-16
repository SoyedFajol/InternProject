package com.example.firstproject.Service;

import com.example.firstproject.DTO.BookDTO;
import com.example.firstproject.Entity.Book;
import com.example.firstproject.Mapper.BookMapper;
import com.example.firstproject.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository repository;
    private final BookMapper bookMapper;

    public BookService(BookRepository repository, BookMapper bookMapper) {
        this.repository = repository;
        this.bookMapper = bookMapper;
    }

    public void save(BookDTO dto) {
        Book book = bookMapper.toEntity(dto);
        repository.save(book);
    }

    public Optional<BookDTO> findById(Long id) {
        return repository.findById(id).map(bookMapper::toDTO);
    }

    public void update(Long id, BookDTO dto) {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setName(dto.getName());
            repository.save(book);
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
    public List<BookDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

}