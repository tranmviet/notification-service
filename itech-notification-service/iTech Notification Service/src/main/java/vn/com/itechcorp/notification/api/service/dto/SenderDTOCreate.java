package vn.com.itechcorp.notification.api.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import vn.com.itechcorp.base.service.dto.SerialIDDtoCreate;
import vn.com.itechcorp.notification.api.jpa.entity.Sender;
import vn.com.itechcorp.notification.util.AccountType;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class SenderDTOCreate extends SerialIDDtoCreate<Sender> {

    @NotNull
    private AccountType accountType;

    @NotNull
    private String account;

    private String secret;

    private String config;

    private boolean enabled;

    private Set<MessageTypeDTOCreate> messageTypes;

    @Override
    public Sender toEntry() {
        Sender object = new Sender();
        object.setAccountType(accountType);
        object.setAccount(account);
        object.setSecret(secret);
        object.setConfig(config);
        object.setEnabled(enabled);
        object.setMessageTypes(messageTypes.stream().map(MessageTypeDTOCreate::toEntry).collect(Collectors.toSet()));

        return object;
    }
}
