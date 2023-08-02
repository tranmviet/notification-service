package vn.com.itechcorp.notification.api.service.dto;

import lombok.Getter;
import lombok.Setter;
import vn.com.itechcorp.base.service.dto.SerialIDDtoCreate;
import vn.com.itechcorp.notification.api.jpa.entity.Message;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter
public class MessageDTOCreate extends SerialIDDtoCreate<Message> {

    @NotNull @NotEmpty
    private String messageType;

    private String subject;

    @NotNull
    private String message;

    private Date deliveredTime;

    @NotNull
    private Integer retryCount;

    @Override
    public Message toEntry() {
        Message message = new Message();
        message.setMessageTypeID(messageType);
        message.setSubject(subject);
        message.setMessage(this.message);
        message.setDeliveredTime(deliveredTime);
        message.setRetryCount(retryCount);

        return message;
    }
}
