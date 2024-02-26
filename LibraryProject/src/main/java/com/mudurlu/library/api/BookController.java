package com.mudurlu.library.api;

import com.mudurlu.library.business.abstracts.IAuthorService;
import com.mudurlu.library.business.abstracts.IBookService;
import com.mudurlu.library.business.abstracts.IPublisherService;
import com.mudurlu.library.core.config.modelMapper.IModelMapperService;
import com.mudurlu.library.core.result.Result;
import com.mudurlu.library.core.result.ResultData;
import com.mudurlu.library.core.utilities.ResultHelper;
import com.mudurlu.library.dto.request.book.BookSaveRequest;
import com.mudurlu.library.dto.request.book.BookUpdateRequest;
import com.mudurlu.library.dto.response.CursorResponse;
import com.mudurlu.library.dto.response.book.BookResponse;
import com.mudurlu.library.entitites.Author;
import com.mudurlu.library.entitites.Book;
import com.mudurlu.library.entitites.Publisher;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final IBookService bookService;
    private final IModelMapperService modelMapper;
    private final IAuthorService authorService;
    private final IPublisherService publisherService;

    public BookController(IBookService bookService, IModelMapperService modelMapper, IAuthorService authorService, IPublisherService publisherService) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest){
        Book saveBook = this.modelMapper.forRequest().map(bookSaveRequest, Book.class);
        Author author = this.authorService.get(bookSaveRequest.getAuthorId());
        saveBook.setAuthor(author);

        Publisher publisher = this.publisherService.get(bookSaveRequest.getPublisherId());
        saveBook.setPublisher(publisher);

        this.bookService.save(saveBook);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveBook, BookResponse.class));

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> get(@PathVariable("id") int id){
        Book book = this.bookService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(book, BookResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookResponse>> cursor(
            @RequestParam(name = "page", required = false,defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        Page<Book> bookPage = this.bookService.cursor(page, pageSize);
        Page<BookResponse> bookResponsePage = bookPage
                .map(book -> this.modelMapper.forResponse().map(book,BookResponse.class));

        return ResultHelper.cursor(bookResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> update(@Valid @RequestBody BookUpdateRequest bookUpdateRequest){
        this.bookService.get(bookUpdateRequest.getId());

        Book updateBook = this.modelMapper.forRequest().map(bookUpdateRequest, Book.class);
        this.bookService.update(updateBook);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateBook,BookResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id")int id){
        this.bookService.delete(id);
        return ResultHelper.ok();
    }
}
