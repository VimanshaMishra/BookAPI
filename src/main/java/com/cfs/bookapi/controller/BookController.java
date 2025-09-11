package com.cfs.bookapi.controller;

import com.cfs.bookapi.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/books")
public class BookController {

    private Map<Long , Book> BookDB = new HashMap<>();
    //private long c = 1;

    @GetMapping
    public ResponseEntity<List<Book>> GetAllBook() {
        return ResponseEntity.ok(new ArrayList<>(BookDB.values()));
    }

    @PostMapping
    public ResponseEntity<Book> AddBook(@RequestBody Book book) {
            BookDB.put(book.getId(), book);
            return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Book> GetBookById(@PathVariable Long Id){
        Book book  = BookDB.get(Id);
        if (book == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<Void> UpdateBook(@PathVariable Long Id , @RequestBody Book book){
        Book tobook = BookDB.get(Id);
        if (tobook == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        BookDB.put(Id , book);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{Id}/Price")
    public ResponseEntity<Book> UpdatePrice(@PathVariable Long Id , @RequestBody Long NewPrice){
        Book tobook = BookDB.get(Id);
        if (tobook == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        tobook.setPrice(NewPrice);
        BookDB.put(Id , tobook);
        return ResponseEntity.ok(tobook);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> DeleteBook(@PathVariable Long Id , @RequestBody Book book){
        Book tobook = BookDB.remove(Id);
        if (tobook == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        BookDB.put(Id , book);
        return ResponseEntity.noContent().build();
    }


}
