package vn.com.itechcorp.notification.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.itechcorp.base.persistence.repository.AuditableRepository;
import vn.com.itechcorp.base.service.impl.AuditableDtoJpaServiceImpl;
import vn.com.itechcorp.notification.api.jpa.MessageRepository;
import vn.com.itechcorp.notification.api.jpa.entity.Message;
import vn.com.itechcorp.notification.api.service.dto.MessageDTOGet;

@Service("messageService")
public class MessageServiceImpl extends AuditableDtoJpaServiceImpl<MessageDTOGet, Message, Long> implements MessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public AuditableRepository<Message, Long> getRepository() {
        return messageRepository;
    }

    @Override
    public MessageDTOGet convert(Message message) {
        return new MessageDTOGet(message);
    }
}
