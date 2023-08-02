package vn.com.itechcorp.notification.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import vn.com.itechcorp.base.api.method.AsyncAuditableDtoAPIMethod;
import vn.com.itechcorp.base.api.method.AsyncBaseDtoAPIMethod;
import vn.com.itechcorp.base.api.response.APIListResponse;
import vn.com.itechcorp.base.api.response.APIResponse;
import vn.com.itechcorp.base.api.response.APIResponseHeader;
import vn.com.itechcorp.base.api.response.APIResponseStatus;
import vn.com.itechcorp.base.service.filter.PaginationInfo;
import vn.com.itechcorp.notification.api.jpa.entity.Sender;
import vn.com.itechcorp.notification.api.service.dto.SenderDTOCreate;
import vn.com.itechcorp.notification.api.service.dto.SenderDTOGet;
import vn.com.itechcorp.notification.api.service.SenderService;
import vn.com.itechcorp.notification.api.service.dto.SenderDTOUpdate;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Api(value = "sender-api", tags = "sender-api")
@RequestMapping("/secured/ws/rest/v1")
public class SenderController {

    @Autowired
    private SenderService senderService;

    @Bean("senderAPIMethod")
    @DependsOn("senderService")
    public AsyncAuditableDtoAPIMethod<SenderDTOGet, Sender, Long> getSenderAPIMethod() {
        return new AsyncAuditableDtoAPIMethod<>(senderService);
    }

    @Autowired
    private AsyncAuditableDtoAPIMethod<SenderDTOGet, Sender, Long> senderAPIMethod;

    @ApiOperation(value = "View a list of senders")
    @GetMapping("/async/sender")
    public CompletableFuture<ResponseEntity<APIListResponse<List<SenderDTOGet>>>> getAllSenders(
            @RequestParam(required = false, name = "orderBy") String orderBy,
            @RequestParam(required = false, name = "offset", defaultValue = "0") int offset,
            @RequestParam(required = false, name = "limit", defaultValue = "100") int limit) {
        return senderAPIMethod.getListAsync(new PaginationInfo(offset, limit, orderBy));
    }

    @ApiOperation(value = "View a sender by ID")
    @GetMapping("/async/sender/{id}")
    public CompletableFuture<ResponseEntity<APIResponse<SenderDTOGet>>> getSenderById( @PathVariable("id") Long id) {
        return senderAPIMethod.getByIdAsync(id);
    }

    @ApiOperation(value = "Create a new sender")
    @PostMapping("/async/sender")
    public CompletableFuture<ResponseEntity<APIResponse<Long>>> createSender(
            @Valid @RequestBody SenderDTOCreate entity,
            Errors error) {
        if(error.hasErrors()) return CompletableFuture.completedFuture(new ResponseEntity<>(new APIResponse<>(new APIResponseHeader(APIResponseStatus.INVALID_PARAMETER, error.toString()), null), HttpStatus.BAD_REQUEST));
        return senderAPIMethod.createAsync(entity, 0L);
    }

    @ApiOperation(value = "Update sender information")
    @PutMapping("/async/sender")
    public CompletableFuture<ResponseEntity<APIResponse<Long>>> updateSender(
            @Valid @RequestBody SenderDTOUpdate entity,
            Errors error) {
        if(error.hasErrors()) return CompletableFuture.completedFuture(new ResponseEntity<>(new APIResponse<>(new APIResponseHeader(APIResponseStatus.INVALID_PARAMETER, error.toString()), null), HttpStatus.BAD_REQUEST));
        return senderAPIMethod.updateAsync(entity, 0L);
    }

    @ApiOperation(value = "Delete sender information")
    @DeleteMapping("/async/sender/{id}")
    public CompletableFuture<ResponseEntity<APIResponse<Long>>> deleteSender(
            @PathVariable long id,
            Errors error) {
        if(error.hasErrors()) return CompletableFuture.completedFuture(new ResponseEntity<>(new APIResponse<>(new APIResponseHeader(APIResponseStatus.INVALID_PARAMETER, error.toString()), null), HttpStatus.BAD_REQUEST));
        return senderAPIMethod.deleteAsync(id, 0L);
    }
}
