package vn.com.itechcorp.notification.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.itechcorp.base.service.impl.AuditableDtoJpaServiceImpl;
import vn.com.itechcorp.notification.api.jpa.SenderRepository;
import vn.com.itechcorp.notification.api.jpa.entity.Sender;
import vn.com.itechcorp.notification.api.service.dto.SenderDTOGet;

@Service("senderService")
public class SenderServiceImpl extends AuditableDtoJpaServiceImpl<SenderDTOGet, Sender, Long> implements SenderService {

    @Autowired
    private SenderRepository senderRepository;

    @Override
    public SenderRepository getRepository() {
        return senderRepository;
    }

    @Override
    public SenderDTOGet convert(Sender object) {
        return new SenderDTOGet(object);
    }
}
