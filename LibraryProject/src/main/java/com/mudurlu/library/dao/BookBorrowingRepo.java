package com.mudurlu.library.dao;

import com.mudurlu.library.entitites.BookBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookBorrowingRepo extends JpaRepository<BookBorrowing,Integer> {
}
