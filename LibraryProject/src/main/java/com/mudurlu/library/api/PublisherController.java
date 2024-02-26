package com.mudurlu.library.api;

import com.mudurlu.library.business.abstracts.IPublisherService;
import com.mudurlu.library.core.config.modelMapper.IModelMapperService;
import com.mudurlu.library.core.result.Result;
import com.mudurlu.library.core.result.ResultData;
import com.mudurlu.library.core.utilities.ResultHelper;
import com.mudurlu.library.dto.request.publisher.PublisherSaveRequest;
import com.mudurlu.library.dto.request.publisher.PublisherUpdateRequest;
import com.mudurlu.library.dto.response.CursorResponse;
import com.mudurlu.library.dto.response.publisher.PublisherResponse;
import com.mudurlu.library.entitites.Publisher;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {

    private final IPublisherService publisherService;
    private final IModelMapperService modelMapper;

    public PublisherController(IPublisherService publisherService, IModelMapperService modelMapper) {
        this.publisherService = publisherService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<PublisherResponse> save(@Valid @RequestBody PublisherSaveRequest publisherSaveRequest){
        Publisher savePublisher = this.modelMapper.forRequest().map(publisherSaveRequest,Publisher.class);
        this.publisherService.save(savePublisher);

        return ResultHelper.created(this.modelMapper.forResponse().map(savePublisher,PublisherResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> get(@PathVariable("id") int id){
        Publisher publisher = this.publisherService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(publisher, PublisherResponse.class));

    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<PublisherResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        Page<Publisher> publisherPage = this.publisherService.cursor(page,pageSize);
        Page<PublisherResponse> publisherResponsePage = publisherPage.
                map(publisher -> this.modelMapper.forResponse().map(publisher, PublisherResponse.class));

        return ResultHelper.cursor(publisherResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<PublisherResponse> update(@Valid @RequestBody PublisherUpdateRequest publisherUpdateRequest){
        this.publisherService.get(publisherUpdateRequest.getId());

        Publisher updatePublisher = this.modelMapper.forRequest().map(publisherUpdateRequest,Publisher.class);
        this.publisherService.update(updatePublisher);

        return ResultHelper.success(this.modelMapper.forResponse().map(updatePublisher, PublisherResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id){
        this.publisherService.delete(id);
        return ResultHelper.ok();
    }
}
