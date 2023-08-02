package vn.com.itechcorp.notification.api.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.itechcorp.base.service.dto.DtoGet;
import vn.com.itechcorp.notification.api.jpa.entity.Receiver;
import vn.com.itechcorp.notification.util.AccountType;

@Getter @Setter
@NoArgsConstructor
public class ReceiverDTOGet extends DtoGet<Receiver, Long> {

    private AccountType accountType;

    private String account;

    private String config;

    private boolean enabled = true;

    public ReceiverDTOGet(Receiver receiver) {
        super(receiver);
    }

    @Override
    public void parse(Receiver receiver) {
        this.account = receiver.getAccount();
        this.accountType = receiver.getAccountType();
        this.config = receiver.getConfig();
        this.enabled = receiver.isEnabled();
    }
}
