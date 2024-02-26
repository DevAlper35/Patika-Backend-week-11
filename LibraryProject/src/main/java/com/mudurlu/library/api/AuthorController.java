package com.mudurlu.library.api;

import com.mudurlu.library.business.abstracts.IAuthorService;
import com.mudurlu.library.core.config.modelMapper.IModelMapperService;
import com.mudurlu.library.core.result.Result;
import com.mudurlu.library.core.result.ResultData;
import com.mudurlu.library.core.utilities.ResultHelper;
import com.mudurlu.library.dto.request.author.AuthorSaveRequest;
import com.mudurlu.library.dto.request.author.AuthorUpdateRequest;
import com.mudurlu.library.dto.response.CursorResponse;
import com.mudurlu.library.dto.response.author.AuthorResponse;
import com.mudurlu.library.entitites.Author;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {

    private final IAuthorService authorService;
    private final IModelMapperService modelMapper;

    public AuthorController(IAuthorService authorService, IModelMapperService modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AuthorResponse> save(@Valid @RequestBody AuthorSaveRequest authorSaveRequest) {
        Author saveAuthor = this.modelMapper.forRequest().map(authorSaveRequest, Author.class);
        this.authorService.save(saveAuthor);

        return ResultHelper.created(this.modelMapper.forResponse().map(saveAuthor, AuthorResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> get(@PathVariable("id") int id) {
        Author author = this.authorService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(author, AuthorResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AuthorResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Author> authorPage = this.authorService.cursor(page, pageSize);
        Page<AuthorResponse> authorResponsePage = authorPage.map(
                author -> this.modelMapper.forResponse().map(author, AuthorResponse.class));

        return ResultHelper.cursor(authorResponsePage);

    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AuthorResponse> update(@Valid @RequestBody AuthorUpdateRequest authorUpdateRequest){
        this.authorService.get(authorUpdateRequest.getId());

        Author updateAuthor = this.modelMapper.forRequest().map(authorUpdateRequest,Author.class);
        this.authorService.update(updateAuthor);

        return ResultHelper.success(this.modelMapper.forResponse().map(updateAuthor,AuthorResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.authorService.delete(id);
        return ResultHelper.ok();
    }
}
