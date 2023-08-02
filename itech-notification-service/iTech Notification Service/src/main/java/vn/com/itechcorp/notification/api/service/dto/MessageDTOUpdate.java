package vn.com.itechcorp.notification.api.service.dto;

import lombok.Getter;
import lombok.Setter;
import vn.com.itechcorp.base.service.dto.DtoUpdate;
import vn.com.itechcorp.notification.api.jpa.entity.Message;

import java.util.Date;

@Getter @Setter
public class MessageDTOUpdate extends DtoUpdate<Message, Long> {

    private String messageType;

    private String subject;

    private String message;

    private Date deliveredTime;

    private Integer retryCount;

    @Override
    public boolean apply(Message message) {
        boolean modified = false;

        if (messageType != null && !messageType.isEmpty()) {
            message.setMessageTypeID(messageType);
            modified = true;
        }

        if (subject != null && !subject.isEmpty()) {
            message.setSubject(subject);
            modified = true;
        }

        if (this.message != null & !this.message.isEmpty()) {
            message.setMessage(this.message);
            modified = true;
        }

        return modified;
    }
}
