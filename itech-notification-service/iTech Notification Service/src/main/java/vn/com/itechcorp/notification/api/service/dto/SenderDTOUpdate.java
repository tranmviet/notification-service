package vn.com.itechcorp.notification.api.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.itechcorp.base.service.dto.DtoUpdate;
import vn.com.itechcorp.notification.api.jpa.entity.Sender;
import vn.com.itechcorp.notification.util.AccountType;

@Getter @Setter
@NoArgsConstructor
public class SenderDTOUpdate extends DtoUpdate<Sender, Long> {

    private AccountType accountType;

    private String secret;

    private String config;

    private boolean enabled;

    @Override
    public boolean apply(Sender sender) {
        boolean modified = false;

        if (accountType != null) {
            sender.setAccountType(accountType);
            modified = true;
        }

        if (secret != null && !secret.isEmpty()) {
            sender.setSecret(secret);
            modified = true;
        }

        if (config != null && !config.isEmpty()) {
            sender.setConfig(config);
            modified = true;
        }

        if (enabled != sender.isEnabled()) {
            sender.setEnabled(enabled);
            modified = true;
        }

        return modified;
    }
}
