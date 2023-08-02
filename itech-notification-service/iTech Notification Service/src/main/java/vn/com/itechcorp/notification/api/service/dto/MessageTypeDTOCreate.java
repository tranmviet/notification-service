package vn.com.itechcorp.notification.api.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.itechcorp.base.service.dto.DtoCreate;
import vn.com.itechcorp.notification.api.jpa.entity.MessageType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
public class MessageTypeDTOCreate extends DtoCreate<MessageType, String> {

    @NotNull @NotEmpty
    private String name;

    private List<SenderDTOCreate> senders;

    @Override
    public MessageType toEntry() {
        MessageType messageType = new MessageType();
        messageType.setId(getId());
        messageType.setName(name);
        if (senders != null && !senders.isEmpty()) {
            messageType.setSenders(senders.stream().map(SenderDTOCreate::toEntry).collect(Collectors.toSet()));
        }

        return messageType;
    }
}
