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
import vn.com.itechcorp.base.api.method.AsyncBaseDtoAPIMethod;
import vn.com.itechcorp.base.api.response.APIListResponse;
import vn.com.itechcorp.base.api.response.APIResponse;
import vn.com.itechcorp.base.api.response.APIResponseHeader;
import vn.com.itechcorp.base.api.response.APIResponseStatus;
import vn.com.itechcorp.base.service.filter.PaginationInfo;
import vn.com.itechcorp.notification.api.jpa.entity.MessageType;
import vn.com.itechcorp.notification.api.service.MessageTypeService;
import vn.com.itechcorp.notification.api.service.dto.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Api(value = "message-type-api", tags = "message-type-api")
@RequestMapping("/secured/ws/rest/v1")
public class MessageTypeController {

    @Autowired
    private MessageTypeService messageTypeService;

    @Bean("messageTypeAPIMethod")
    @DependsOn("messageTypeService")
    public AsyncBaseDtoAPIMethod<MessageTypeDTOGet, MessageType, String> getMessageTypeAPIMethod() {
        return new AsyncBaseDtoAPIMethod<>(messageTypeService);
    }

    @Autowired
    private AsyncBaseDtoAPIMethod<MessageTypeDTOGet, MessageType, String> messageTypeAPIMethod;

    @ApiOperation(value = "View a list of message types")
    @GetMapping("/async/type")
    public CompletableFuture<ResponseEntity<APIListResponse<List<MessageTypeDTOGet>>>> getAllMessageTypes(
            @RequestParam(required = false, name = "orderBy") String orderBy,
            @RequestParam(required = false, name = "offset", defaultValue = "0") int offset,
            @RequestParam(required = false, name = "limit", defaultValue = "100") int limit) {
        return messageTypeAPIMethod.getListAsync(new PaginationInfo(offset, limit, orderBy));
    }

    @ApiOperation(value = "View a message type by ID")
    @GetMapping("/async/type/{id}")
    public CompletableFuture<ResponseEntity<APIResponse<MessageTypeDTOGet>>> getMessageTypeById(@PathVariable("id") String id) {
        return messageTypeAPIMethod.getByIdAsync(id);
    }

    @ApiOperation(value = "Search a list of message types")
    @PostMapping("/async/search/type")
    public CompletableFuture<ResponseEntity<APIListResponse<List<MessageTypeDTOGet>>>> searchMessageTypes(
            @RequestBody @Valid MessageTypeFilter filter,
            @RequestParam(required = false, name = "orderBy") String orderBy,
            @RequestParam(required = false, name = "offset", defaultValue = "0") int offset,
            @RequestParam(required = false, name = "limit", defaultValue = "100") int limit) {
        return messageTypeAPIMethod.searchAsync(filter, new PaginationInfo(offset, limit, orderBy));
    }

    @ApiOperation(value = "Create a new message type")
    @PostMapping("/async/type")
    public CompletableFuture<ResponseEntity<APIResponse<String>>> createMessageType(
            @Valid @RequestBody MessageTypeDTOCreate entity,
            Errors error) {
        if(error.hasErrors()) return CompletableFuture.completedFuture(new ResponseEntity<>(new APIResponse<>(new APIResponseHeader(APIResponseStatus.INVALID_PARAMETER, error.toString()), null), HttpStatus.BAD_REQUEST));
        return messageTypeAPIMethod.createAsync(entity);
    }

    @ApiOperation(value = "Update message type information")
    @PutMapping("/async/type")
    public CompletableFuture<ResponseEntity<APIResponse<String>>> updateMessageType(
            @Valid @RequestBody MessageTypeDTOUpdate entity,
            Errors error) {
        if(error.hasErrors()) return CompletableFuture.completedFuture(new ResponseEntity<>(new APIResponse<>(new APIResponseHeader(APIResponseStatus.INVALID_PARAMETER, error.toString()), null), HttpStatus.BAD_REQUEST));
        return messageTypeAPIMethod.updateAsync(entity);
    }

    @ApiOperation(value = "Delete message type")
    @DeleteMapping("/async/type/{id}")
    public CompletableFuture<ResponseEntity<APIResponse<String>>> deleteMessageType(
            @PathVariable String id) {
//        if(error.hasErrors()) return CompletableFuture.completedFuture(new ResponseEntity<>(new APIResponse<>(new APIResponseHeader(APIResponseStatus.INVALID_PARAMETER, error.toString()), null), HttpStatus.BAD_REQUEST));
        return messageTypeAPIMethod.deleteAsync(id);
    }
}
