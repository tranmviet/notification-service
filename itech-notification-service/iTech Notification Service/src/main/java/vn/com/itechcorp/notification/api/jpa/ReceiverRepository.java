package vn.com.itechcorp.notification.api.jpa;

import org.springframework.stereotype.Repository;
import vn.com.itechcorp.base.persistence.repository.AuditableRepository;
import vn.com.itechcorp.notification.api.jpa.entity.Receiver;

@Repository
public interface ReceiverRepository extends AuditableRepository<Receiver, Long> {
}
