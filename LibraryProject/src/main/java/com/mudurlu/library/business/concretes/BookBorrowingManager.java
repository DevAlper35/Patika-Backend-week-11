package com.mudurlu.library.business.concretes;

import com.mudurlu.library.business.abstracts.IBookBorrowingService;
import com.mudurlu.library.core.exception.NotFoundException;
import com.mudurlu.library.core.utilities.Msg;
import com.mudurlu.library.dao.BookBorrowingRepo;
import com.mudurlu.library.entitites.BookBorrowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookBorrowingManager implements IBookBorrowingService {
    private final BookBorrowingRepo bookBorrowingRepo;

    public BookBorrowingManager(BookBorrowingRepo bookBorrowingRepo) {
        this.bookBorrowingRepo = bookBorrowingRepo;
    }

    @Override
    public BookBorrowing save(BookBorrowing bookBorrowing) {
        return this.bookBorrowingRepo.save(bookBorrowing);
    }

    @Override
    public BookBorrowing get(int id) {
        return this.bookBorrowingRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<BookBorrowing> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.bookBorrowingRepo.findAll(pageable);
    }

    @Override
    public BookBorrowing update(BookBorrowing bookBorrowing) {
        this.get(bookBorrowing.getId());
        return this.bookBorrowingRepo.save(bookBorrowing);
    }

    @Override
    public boolean delete(int id) {
        BookBorrowing bookBorrowing = this.get(id);
        this.bookBorrowingRepo.delete(bookBorrowing);
        return true;
    }

    @Override
    public List<BookBorrowing> getAll() {
        return bookBorrowingRepo.findAll();
    }
}
