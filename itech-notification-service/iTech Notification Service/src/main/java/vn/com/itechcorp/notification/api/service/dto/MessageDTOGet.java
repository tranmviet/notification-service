package vn.com.itechcorp.notification.api.service.dto;

import lombok.Getter;
import lombok.Setter;
import vn.com.itechcorp.base.service.dto.DtoGet;
import vn.com.itechcorp.notification.api.jpa.entity.Message;

import java.util.Date;

@Getter @Setter
public class MessageDTOGet extends DtoGet<Message, Long> {

    private String messageType;

    private String subject;

    private String message;

    private Date deliveredTime;

    private Integer retryCount;

    public MessageDTOGet(Message message) {
        super(message);
    }

    @Override
    public void parse(Message message) {
        messageType = message.getMessageTypeID();
        subject = message.getSubject();
        this.message = message.getMessage();
        deliveredTime = message.getDeliveredTime();
        retryCount = message.getRetryCount();
    }
}
