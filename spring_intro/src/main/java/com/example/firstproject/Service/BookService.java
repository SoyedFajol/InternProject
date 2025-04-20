package com.example.firstproject.Service;

import com.example.firstproject.DTO.BookDTO;
import com.example.firstproject.Entity.Book;
import com.example.firstproject.Mapper.BookMapper;
import com.example.firstproject.Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service  // Make sure this annotation is present
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

    public Optional<Book> getEntityById(Long id) {
        return repository.findById(id);
    }

    public void update(Long id, BookDTO dto) {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setName(dto.getName());
            book.setAuthor(dto.getAuthor());
            repository.save(book);
        }
    }

    public String deleteById(Long id) {
        if (!repository.existsById(id)) {
            return "Book not found with id: " + id;
        }
        repository.deleteById(id);
        return "Book deleted successfully";
    }

    public List<BookDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }
}
