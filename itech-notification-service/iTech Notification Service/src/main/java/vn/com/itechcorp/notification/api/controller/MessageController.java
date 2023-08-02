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
import vn.com.itechcorp.notification.api.jpa.entity.Message;
import vn.com.itechcorp.notification.api.service.MessageService;
import vn.com.itechcorp.notification.api.service.dto.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Api(value = "message-api", tags = "message-api")
@RequestMapping("/secured/ws/rest/v1")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Bean("messageAPIMethod")
    @DependsOn("messageService")
    public AsyncAuditableDtoAPIMethod<MessageDTOGet, Message, Long> getMessageAPIMethod() {
        return new AsyncAuditableDtoAPIMethod<>(messageService);
    }

    @Autowired
    private AsyncAuditableDtoAPIMethod<MessageDTOGet, Message, Long> messageAPIMethod;

    @ApiOperation(value = "View a list of messages")
    @GetMapping("/async/message")
    public CompletableFuture<ResponseEntity<APIListResponse<List<MessageDTOGet>>>> getAllMessages(
            @RequestParam(required = false, name = "orderBy") String orderBy,
            @RequestParam(required = false, name = "offset", defaultValue = "0") int offset,
            @RequestParam(required = false, name = "limit", defaultValue = "100") int limit) {
        return messageAPIMethod.getListAsync(new PaginationInfo(offset, limit, orderBy));
    }

    @ApiOperation(value = "View a message by ID")
    @GetMapping("/async/message/{id}")
    public CompletableFuture<ResponseEntity<APIResponse<MessageDTOGet>>> getMessageById(@PathVariable("id") Long id) {
        return messageAPIMethod.getByIdAsync(id);
    }

    @ApiOperation(value = "Create a new message")
    @PostMapping("/async/message")
    public CompletableFuture<ResponseEntity<APIResponse<Long>>> createMessage(
            @Valid @RequestBody MessageDTOCreate entity,
            Errors error) {
        if(error.hasErrors()) return CompletableFuture.completedFuture(new ResponseEntity<>(new APIResponse<>(new APIResponseHeader(APIResponseStatus.INVALID_PARAMETER, error.toString()), null), HttpStatus.BAD_REQUEST));
        return messageAPIMethod.createAsync(entity, 0L);
    }

    @ApiOperation(value = "Update messsage")
    @PutMapping("/async/messsage")
    public CompletableFuture<ResponseEntity<APIResponse<Long>>> updateMessage(
            @Valid @RequestBody MessageDTOUpdate entity,
            Errors error) {
        if(error.hasErrors()) return CompletableFuture.completedFuture(new ResponseEntity<>(new APIResponse<>(new APIResponseHeader(APIResponseStatus.INVALID_PARAMETER, error.toString()), null), HttpStatus.BAD_REQUEST));
        return messageAPIMethod.updateAsync(entity, 0L);
    }

    @ApiOperation(value = "Delete message")
    @DeleteMapping("/async/message/{id}")
    public CompletableFuture<ResponseEntity<APIResponse<Long>>> deleteMessage(
            @PathVariable Long id) {
        return messageAPIMethod.deleteAsync(id, 0L);
    }
}
