package com.mudurlu.library.api;

import com.mudurlu.library.business.abstracts.IBookBorrowingService;
import com.mudurlu.library.business.abstracts.IBookService;
import com.mudurlu.library.core.config.modelMapper.IModelMapperService;
import com.mudurlu.library.core.result.Result;
import com.mudurlu.library.core.result.ResultData;
import com.mudurlu.library.core.utilities.ResultHelper;
import com.mudurlu.library.dto.request.bookborrowing.BookBorrowingSaveRequest;
import com.mudurlu.library.dto.request.bookborrowing.BookBorrowingUpdateRequest;
import com.mudurlu.library.dto.response.CursorResponse;
import com.mudurlu.library.dto.response.bookborrowing.BookBorrowingResponse;
import com.mudurlu.library.entitites.Book;
import com.mudurlu.library.entitites.BookBorrowing;
import com.mudurlu.library.entitites.Category;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/borrowings")
public class BookBorrowingController {
    private final IBookBorrowingService bookBorrowingService;
    private final IModelMapperService modelMapper;
    private final IBookService bookService;

    public BookBorrowingController(IBookBorrowingService bookBorrowingService, IModelMapperService modelMapper, IBookService bookService) {
        this.bookBorrowingService = bookBorrowingService;
        this.modelMapper = modelMapper;
        this.bookService = bookService;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookBorrowingResponse> save(@Valid @RequestBody BookBorrowingSaveRequest bookBorrowingSaveRequest){
        BookBorrowing saveBookBorrowing = this.modelMapper.forRequest().map(bookBorrowingSaveRequest, BookBorrowing.class);
        Book book = this.bookService.get(bookBorrowingSaveRequest.getBookId());
        saveBookBorrowing.setBook(book);

        this.bookBorrowingService.save(saveBookBorrowing);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveBookBorrowing,BookBorrowingResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> get(@PathVariable("id") int id){
        BookBorrowing bookBorrowing = this.bookBorrowingService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(bookBorrowing,BookBorrowingResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookBorrowingResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        Page<BookBorrowing> bookBorrowingPage = this.bookBorrowingService.cursor(page,pageSize);
        Page<BookBorrowingResponse> bookBorrowingResponsePage = bookBorrowingPage
                .map(bookBorrowing -> this.modelMapper.forResponse().map(bookBorrowing,BookBorrowingResponse.class));

        return ResultHelper.cursor(bookBorrowingResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookBorrowingResponse> update(@Valid @RequestBody BookBorrowingUpdateRequest bookBorrowingUpdateRequest){
        this.bookBorrowingService.get(bookBorrowingUpdateRequest.getId());

        BookBorrowing updateBookBorrowing = this.modelMapper.forRequest().map(bookBorrowingUpdateRequest, BookBorrowing.class);

        this.bookBorrowingService.update(updateBookBorrowing);

        return ResultHelper.success(this.modelMapper.forResponse().map(updateBookBorrowing,BookBorrowingResponse.class));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.bookBorrowingService.delete(id);
        return ResultHelper.ok();
    }

    @GetMapping("/borrowedBooks")
    public List<BookBorrowing> getAll(){
        return bookBorrowingService.getAll();
    }
}
