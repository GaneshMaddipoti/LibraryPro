package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookRepository bookRepository;

    //getall
    @GetMapping(value="/getbooks")
    public List<Book> getBooks() {
        List<Book> list = new ArrayList<>();
        bookRepository.findAll().forEach(book -> {
            list.add(book);
        });
        return list;
    }

    //create & update
    @GetMapping(value = "/savebook/{id}/{name}")
    public Book saveBook(@PathVariable Integer id, @PathVariable String name) throws Exception{
        Book book = new Book(id, name);
        bookRepository.save(book);
        return book;
    }

    //read
    @GetMapping(value = "/book/{id}")
    public Book getBook(@PathVariable Integer id) throws IOException {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()){
            return book.get();
        }else {
            return new Book();
        }
    }

    //delete
    @GetMapping(value = "/deletebook/{id}")
    public Integer deleteBook(@PathVariable Integer id) {
        bookRepository.deleteById(id);
        return id;
    }


}
