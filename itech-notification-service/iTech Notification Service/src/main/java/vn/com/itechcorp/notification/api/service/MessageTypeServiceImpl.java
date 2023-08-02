package vn.com.itechcorp.notification.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.itechcorp.base.persistence.repository.BaseRepository;
import vn.com.itechcorp.base.service.impl.BaseDtoJpaServiceImpl;
import vn.com.itechcorp.notification.api.jpa.MessageTypeRepository;
import vn.com.itechcorp.notification.api.jpa.entity.MessageType;
import vn.com.itechcorp.notification.api.service.dto.MessageTypeDTOGet;

@Service("messageTypeService")
public class MessageTypeServiceImpl extends BaseDtoJpaServiceImpl<MessageTypeDTOGet, MessageType, String> implements MessageTypeService {

    @Autowired
    private MessageTypeRepository messageTypeRepository;

    @Override
    public BaseRepository<MessageType, String> getRepository() {
        return messageTypeRepository;
    }

    @Override
    public MessageTypeDTOGet convert(MessageType messageType) {
        return new MessageTypeDTOGet(messageType);
    }
}
