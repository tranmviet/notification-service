package vn.com.itechcorp.notification.api.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.itechcorp.base.service.dto.DtoUpdate;
import vn.com.itechcorp.notification.api.jpa.entity.MessageType;

@Getter @Setter
@NoArgsConstructor
public class MessageTypeDTOUpdate extends DtoUpdate<MessageType, String> {

    private String name;

    @Override
    public boolean apply(MessageType messageType) {
        boolean modified = false;

        if (name != null && !name.isEmpty()) {
            messageType.setName(name);
            modified = true;
        }

        return modified;
    }
}
