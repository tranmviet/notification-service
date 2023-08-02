package vn.com.itechcorp.notification.api.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.itechcorp.base.service.dto.DtoGet;
import vn.com.itechcorp.notification.api.jpa.entity.MessageType;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
public class MessageTypeDTOGet extends DtoGet<MessageType, String> {

    private String name;

    private List<SenderDTOGet> senders;

    public MessageTypeDTOGet(MessageType messageType) {
        super(messageType);
    }

    @Override
    public void parse(MessageType messageType) {
        this.name = messageType.getName();
        if (messageType.getSenders() != null && !messageType.getSenders().isEmpty()) {
            this.senders = messageType.getSenders().stream().map(SenderDTOGet::new).collect(Collectors.toList());
        }
    }
}
