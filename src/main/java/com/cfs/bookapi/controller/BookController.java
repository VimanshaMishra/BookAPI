package com.cfs.bookapi.controller;

import com.cfs.bookapi.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final Map<Long, Book> bookDB = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(new ArrayList<>(bookDB.values()));
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        if (book.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        bookDB.put(book.getId(), book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookDB.get(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        if (!bookDB.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        book.setId(id);
        bookDB.put(id, book);
        return ResponseEntity.ok(book);
    }

    @PatchMapping("/{id}/price")
    public ResponseEntity<Book> updatePrice(@PathVariable Long id, @RequestBody Long newPrice) {
        Book book = bookDB.get(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        book.setPrice(newPrice);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (!bookDB.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        bookDB.remove(id);
        return ResponseEntity.noContent().build();
    }
}
