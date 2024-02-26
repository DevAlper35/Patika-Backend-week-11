package com.mudurlu.library.business.abstracts;

import com.mudurlu.library.entitites.Author;
import com.mudurlu.library.entitites.Book;
import org.springframework.data.domain.Page;

public interface IBookService {
    Book save(Book book);
    Book get(int id);
    Page<Book> cursor(int page, int pageSize);
    Book update(Book book);
    boolean delete(int id);
}
