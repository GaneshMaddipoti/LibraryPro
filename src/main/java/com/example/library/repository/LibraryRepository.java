package com.example.library.repository;

import com.example.library.model.Book;
import com.example.library.model.Library;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends CrudRepository<Library, Integer> {

    List<Library> findByName(String name);

}