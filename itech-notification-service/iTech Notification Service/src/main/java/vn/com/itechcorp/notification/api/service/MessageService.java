package vn.com.itechcorp.notification.api.service;

import vn.com.itechcorp.base.service.AuditableDtoService;
import vn.com.itechcorp.notification.api.jpa.entity.Message;
import vn.com.itechcorp.notification.api.service.dto.MessageDTOGet;

public interface MessageService extends AuditableDtoService<MessageDTOGet, Message, Long> {
}
