package com.example.library.controller;

import com.example.library.model.Library;
import com.example.library.repository.LibraryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin
public class LibraryController {

    private static final Logger log = LoggerFactory.getLogger(LibraryController.class);

    @Autowired
    LibraryRepository libraryRepository;
    
    //getall
    @GetMapping(value="/getlibrarys")
    public Iterable<Library> getLibrarys() {
        return libraryRepository.findAll();
    }

    //create & update
    @GetMapping(value = "/savelibrary/{id}/{name}")
    public Library saveLibrary(@PathVariable Integer id, @PathVariable String name) throws Exception{
        Library library = new Library(id, name);
        libraryRepository.save(library);
        return library;
    }

    //read
    @GetMapping(value = "/library/{id}")
    public Library getLibrary(@PathVariable Integer id) throws IOException {
        Optional<Library> library = libraryRepository.findById(id);
        if(library.isPresent()){
            return library.get();
        }else {
            return new Library();
        }
    }    

    //delete
    @GetMapping(value = "/deletelibrary/{id}")
    public Integer deleteLibrary(@PathVariable Integer id) {
        libraryRepository.deleteById(id);
        return id;
    }
    

}
