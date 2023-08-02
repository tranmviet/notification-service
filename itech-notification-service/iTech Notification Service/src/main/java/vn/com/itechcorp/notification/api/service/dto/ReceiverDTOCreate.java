package vn.com.itechcorp.notification.api.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.itechcorp.base.service.dto.SerialIDDtoCreate;
import vn.com.itechcorp.notification.api.jpa.entity.Receiver;
import vn.com.itechcorp.notification.util.AccountType;

import javax.validation.constraints.NotNull;

@Getter @Setter
@NoArgsConstructor
public class ReceiverDTOCreate extends SerialIDDtoCreate<Receiver> {

    @NotNull
    private AccountType accountType;

    @NotNull
    private String account;

    private String config;

    private boolean enabled;

    @Override
    public Receiver toEntry() {
        Receiver receiver = new Receiver();
        receiver.setAccount(account);
        receiver.setAccountType(accountType);
        receiver.setConfig(config);
        receiver.setEnabled(enabled);

        return receiver;
    }
}
