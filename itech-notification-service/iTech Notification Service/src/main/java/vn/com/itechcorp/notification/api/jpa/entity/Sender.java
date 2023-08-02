package vn.com.itechcorp.notification.api.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import vn.com.itechcorp.base.persistence.model.AuditableSerialIDEntry;
import vn.com.itechcorp.notification.util.AccountType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sender")
@Getter @Setter
public class Sender extends AuditableSerialIDEntry {

    private static final long serialVersionUID = 1L;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    @Column(name = "account", nullable = false)
    private String account;

    @Column(name = "secret")
    private String secret;

    @Column(name = "config")
    private String config;

    @Column(name = "enabled")
    private boolean enabled = true;

    @ManyToMany(mappedBy = "senders")
    private Set<MessageType> messageTypes;

	public Sender() {
        super();
    }

    public Sender(long id) {
        super(id);
    }
}
