package vn.com.itechcorp.notification.api.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.itechcorp.base.service.dto.DtoGet;
import vn.com.itechcorp.notification.api.jpa.entity.Sender;
import vn.com.itechcorp.notification.util.AccountType;

@Getter @Setter @NoArgsConstructor
public class SenderDTOGet extends DtoGet<Sender, Long> {

    private AccountType accountType;

    private String account;

    @JsonIgnore
    private String secret;

    private int secretLength;

    private String config;

    private boolean enabled;

    public SenderDTOGet(Sender sender) {
        super(sender);
    }
    @Override
    public void parse(Sender sender) {
        accountType = sender.getAccountType();
        account = sender.getAccount();
        secret = sender.getSecret();
        secretLength = (secret == null) ? 0 : secret.length();
        config = sender.getConfig();
        enabled = sender.isEnabled();
    }
}
