package com.mudurlu.library.api;

import com.mudurlu.library.business.abstracts.ICategoryService;
import com.mudurlu.library.core.config.modelMapper.IModelMapperService;
import com.mudurlu.library.core.result.Result;
import com.mudurlu.library.core.result.ResultData;
import com.mudurlu.library.core.utilities.ResultHelper;
import com.mudurlu.library.dto.request.category.CategorySaveRequest;
import com.mudurlu.library.dto.request.category.CategoryUpdateRequest;
import com.mudurlu.library.dto.response.CursorResponse;
import com.mudurlu.library.dto.response.category.CategoryResponse;
import com.mudurlu.library.entitites.Category;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final ICategoryService categoryService;
    private final IModelMapperService modelMapper;

    public CategoryController(ICategoryService categoryService, IModelMapperService modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> save(@Valid @RequestBody CategorySaveRequest categorySaveRequest){
        Category saveCategory = this.modelMapper.forRequest().map(categorySaveRequest,Category.class);
        this.categoryService.save(saveCategory);

        return ResultHelper.created(this.modelMapper.forResponse().map(saveCategory,CategoryResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CategoryResponse> get(@PathVariable("id") int id){
        Category category = this.categoryService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(category,CategoryResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CategoryResponse>> cursor(
            @RequestParam(name = "page",required = false,defaultValue = "0")int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")int pageSize
    ){
        Page<Category> categoryPage = this.categoryService.cursor(page,pageSize);
        Page<CategoryResponse> categoryResponsePage = categoryPage
                .map(category -> this.modelMapper.forResponse().map(category,CategoryResponse.class));

        return ResultHelper.cursor(categoryResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CategoryResponse> update (@Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest){
        this.categoryService.get(categoryUpdateRequest.getId());

        Category updateCategory = this.modelMapper.forRequest().map(categoryUpdateRequest,Category.class);
        this.categoryService.update(updateCategory);

        return ResultHelper.success(this.modelMapper.forResponse().map(updateCategory,CategoryResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.categoryService.delete(id);
        return ResultHelper.ok();
    }
}
