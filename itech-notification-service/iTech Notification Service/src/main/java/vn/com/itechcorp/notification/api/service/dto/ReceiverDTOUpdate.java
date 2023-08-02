package vn.com.itechcorp.notification.api.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.itechcorp.base.service.dto.DtoUpdate;
import vn.com.itechcorp.notification.api.jpa.entity.Receiver;
import vn.com.itechcorp.notification.util.AccountType;

@Getter @Setter
@NoArgsConstructor
public class ReceiverDTOUpdate extends DtoUpdate<Receiver, Long> {

    private String account;

    private AccountType accountType;

    private String config;

    private boolean enabled;

    @Override
    public boolean apply(Receiver receiver) {
        boolean modified = false;

        if (accountType != null) {
            receiver.setAccountType(accountType);
            modified = true;
        }

        if (account != null && !account.isEmpty()) {
            receiver.setAccount(account);
            modified = true;
        }

        if (config != null && !config.isEmpty()) {
            receiver.setConfig(config);
            modified = true;
        }

        if (enabled != receiver.isEnabled()) {
            receiver.setEnabled(enabled);
            modified = true;
        }

        return modified;
    }
}
