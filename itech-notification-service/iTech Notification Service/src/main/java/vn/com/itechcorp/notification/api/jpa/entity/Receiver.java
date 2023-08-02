package vn.com.itechcorp.notification.api.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import vn.com.itechcorp.base.persistence.model.AuditableSerialIDEntry;
import vn.com.itechcorp.notification.util.AccountType;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "receiver")
@Getter
@Setter
public class Receiver extends AuditableSerialIDEntry {

    @Enumerated(value = EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    @Column(name = "account", nullable = false)
    private String account;

    @Column(name = "config")
    private String config;

    @Column(name = "enabled")
    private boolean enabled = true;

    @ManyToMany(mappedBy = "receivers")
    private Set<MessageType> messageTypes;
}
