package vn.com.itechcorp.notification.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.itechcorp.base.persistence.repository.AuditableRepository;
import vn.com.itechcorp.base.service.impl.AuditableDtoJpaServiceImpl;
import vn.com.itechcorp.notification.api.jpa.ReceiverRepository;
import vn.com.itechcorp.notification.api.jpa.entity.Receiver;
import vn.com.itechcorp.notification.api.service.dto.ReceiverDTOGet;

@Service("receiverService")
public class ReceiverServiceImpl extends AuditableDtoJpaServiceImpl<ReceiverDTOGet, Receiver, Long> implements ReceiverService {

    @Autowired
    private ReceiverRepository receiverRepository;

    @Override
    public AuditableRepository<Receiver, Long> getRepository() {
        return receiverRepository;
    }

    @Override
    public ReceiverDTOGet convert(Receiver receiver) {
        return new ReceiverDTOGet(receiver);
    }
}
