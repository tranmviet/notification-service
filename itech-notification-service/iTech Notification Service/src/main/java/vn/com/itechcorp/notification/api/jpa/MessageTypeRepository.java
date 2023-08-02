package vn.com.itechcorp.notification.api.jpa;

import org.springframework.stereotype.Repository;
import vn.com.itechcorp.base.persistence.repository.BaseRepository;
import vn.com.itechcorp.notification.api.jpa.entity.MessageType;

@Repository
public interface MessageTypeRepository extends BaseRepository<MessageType, String> {
}
