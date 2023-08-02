package vn.com.itechcorp.notification.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import vn.com.itechcorp.base.api.method.AsyncAuditableDtoAPIMethod;
import vn.com.itechcorp.base.api.response.APIListResponse;
import vn.com.itechcorp.base.api.response.APIResponse;
import vn.com.itechcorp.base.api.response.APIResponseHeader;
import vn.com.itechcorp.base.api.response.APIResponseStatus;
import vn.com.itechcorp.base.service.filter.PaginationInfo;
import vn.com.itechcorp.notification.api.jpa.entity.Receiver;
import vn.com.itechcorp.notification.api.service.ReceiverService;
import vn.com.itechcorp.notification.api.service.dto.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Api(value = "receiver-api", tags = "receiver-api")
@RequestMapping("/secured/ws/rest/v1")
public class ReceiverController {

    @Autowired
    private ReceiverService receiverService;

    @Bean("receiverAPIMethod")
    @DependsOn("receiverService")
    public AsyncAuditableDtoAPIMethod<ReceiverDTOGet, Receiver, Long> getReceiverAPIMethod() {
        return new AsyncAuditableDtoAPIMethod<>(receiverService);
    }

    @Autowired
    private AsyncAuditableDtoAPIMethod<ReceiverDTOGet, Receiver, Long> receiverAPIMethod;

    @ApiOperation(value = "View a list of receivers")
    @GetMapping("/async/receiver")
    public CompletableFuture<ResponseEntity<APIListResponse<List<ReceiverDTOGet>>>> getAllReceivers(
            @RequestParam(required = false, name = "orderBy") String orderBy,
            @RequestParam(required = false, name = "offset", defaultValue = "0") int offset,
            @RequestParam(required = false, name = "limit", defaultValue = "100") int limit) {
        return receiverAPIMethod.getListAsync(new PaginationInfo(offset, limit, orderBy));
    }

    @ApiOperation(value = "View a receiver by ID")
    @GetMapping("/async/receiver/{id}")
    public CompletableFuture<ResponseEntity<APIResponse<ReceiverDTOGet>>> getReceiverById(@PathVariable("id") Long id) {
        return receiverAPIMethod.getByIdAsync(id);
    }

    @ApiOperation(value = "Create a new receiver")
    @PostMapping("/async/receiver")
    public CompletableFuture<ResponseEntity<APIResponse<Long>>> createReceiver(
            @Valid @RequestBody ReceiverDTOCreate entity,
            Errors error) {
        if(error.hasErrors()) return CompletableFuture.completedFuture(new ResponseEntity<>(new APIResponse<>(new APIResponseHeader(APIResponseStatus.INVALID_PARAMETER, error.toString()), null), HttpStatus.BAD_REQUEST));
        return receiverAPIMethod.createAsync(entity, 0L);
    }

    @ApiOperation(value = "Update receiver information")
    @PutMapping("/async/receiver")
    public CompletableFuture<ResponseEntity<APIResponse<Long>>> updateReceiver(
            @Valid @RequestBody ReceiverDTOUpdate entity,
            Errors error) {
        if(error.hasErrors()) return CompletableFuture.completedFuture(new ResponseEntity<>(new APIResponse<>(new APIResponseHeader(APIResponseStatus.INVALID_PARAMETER, error.toString()), null), HttpStatus.BAD_REQUEST));
        return receiverAPIMethod.updateAsync(entity, 0L);
    }

    @ApiOperation(value = "Delete receiver")
    @DeleteMapping("/async/receiver/{id}")
    public CompletableFuture<ResponseEntity<APIResponse<Long>>> deleteReceiver(
            @PathVariable Long id) {
        return receiverAPIMethod.deleteAsync(id, 0L);
    }
}
