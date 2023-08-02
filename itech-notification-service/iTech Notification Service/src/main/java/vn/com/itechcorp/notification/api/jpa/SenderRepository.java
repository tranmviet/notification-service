package vn.com.itechcorp.notification.api.jpa;

import org.springframework.stereotype.Repository;
import vn.com.itechcorp.base.persistence.repository.AuditableRepository;
import vn.com.itechcorp.notification.api.jpa.entity.Sender;

@Repository("senderRepository")
public interface SenderRepository extends AuditableRepository<Sender, Long> {
}
