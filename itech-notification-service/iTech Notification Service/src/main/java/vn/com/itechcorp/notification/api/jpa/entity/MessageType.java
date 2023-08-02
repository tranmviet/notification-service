package vn.com.itechcorp.notification.api.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import vn.com.itechcorp.base.persistence.model.BaseDbEntry;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "message_type")
@Getter @Setter
public class MessageType extends BaseDbEntry<String> {

    private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "message_type_sender",
            joinColumns = @JoinColumn(name = "message_type_id"),
            inverseJoinColumns = @JoinColumn(name = "sender_id")
    )
    private Set<Sender> senders = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "message_type_receiver",
            joinColumns = @JoinColumn(name = "message_type_id"),
            inverseJoinColumns = @JoinColumn(name = "receiver_id")
    )
    private Set<Sender> receivers = new HashSet<>();

	public MessageType() {
        super();
    }

    public MessageType(String id) {
        super(id);
    }

}