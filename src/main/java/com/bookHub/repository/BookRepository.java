package com.bookHub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookHub.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>  {

}
