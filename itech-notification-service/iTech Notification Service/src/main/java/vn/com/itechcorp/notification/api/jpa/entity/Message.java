package vn.com.itechcorp.notification.api.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import vn.com.itechcorp.base.persistence.model.AuditableSerialIDEntry;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message")
@Getter
@Setter
public class Message extends AuditableSerialIDEntry {

    @Column(name = "message_type", nullable = false)
    private String messageTypeID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_type",  insertable = false, updatable = false)
    private MessageType messageType;

    @Column(name = "subject")
    private String subject;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "delivered_time")
    private Date deliveredTime;

    @Column(name = "retry_count", nullable = false)
    private Integer retryCount;
}
