package vn.com.itechcorp.notification.api.service;

import vn.com.itechcorp.base.service.BaseDtoService;
import vn.com.itechcorp.notification.api.jpa.entity.MessageType;
import vn.com.itechcorp.notification.api.service.dto.MessageTypeDTOGet;

public interface MessageTypeService extends BaseDtoService<MessageTypeDTOGet, MessageType, String> {
}
