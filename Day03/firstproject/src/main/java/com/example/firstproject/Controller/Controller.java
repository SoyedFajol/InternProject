package com.example.firstproject.Controller;

import com.example.firstproject.DTO.BookDTO;
import com.example.firstproject.Service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/book")
public class Controller {
    private final BookService bookService;

    public Controller(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody BookDTO dto) {
        bookService.save(dto);
        return ResponseEntity.ok("Book saved successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable Long id) {
        Optional<BookDTO> book = bookService.findById(id);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody BookDTO dto) {
        bookService.update(id, dto);
        return ResponseEntity.ok("Book updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.ok("Book deleted successfully");
    }
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAll() {
        List<BookDTO> books = bookService.getAll();
        return ResponseEntity.ok(books);
    }
}
